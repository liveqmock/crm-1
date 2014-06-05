package com.deppon.crm.module.recompense.server.manager.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.service.impl.UserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.sms.impl.SmsInfoSenderImpl;
import com.deppon.crm.module.common.server.manager.impl.ExpressPointBusinessDeptManager;
import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.common.server.manager.impl.PilotCityManager;
import com.deppon.crm.module.common.server.service.impl.CityService;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.server.service.impl.EmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.ClaimValidator;
import com.deppon.crm.module.recompense.server.manager.IClaimManager;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.IClaimService;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.ClaimConstants;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.crm.module.recompense.shared.exception.ClaimException;
import com.deppon.crm.module.recompense.shared.exception.ClaimExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * 
 * <p>
 * Description:索赔管理<br />
 * </p>
 * 
 * @title ClaimManager.java
 * @package com.deppon.crm.module.recompense.server.manager.impl
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimManager implements IClaimManager {
	// 索赔服务
	private IClaimService claimService;
	// 理赔管理
	private RecompenseManager recompenseManager;
	// 理赔服务
	private RecompenseService recompenseService;
	// 部门
	private DepartmentService departmentService;
	// 短信
	private SmsInfoSenderImpl smsInfoSender;
	// 代办
	private MessageManager messageManager;
	// 员工
	private EmployeeService employeeService;
	// User
	private UserService userService;
	// 试点城市manager
	private PilotCityManager pilotCityManager;
	// 营业部与点部映射关系
	private ExpressPointBusinessDeptManager expressPointBusinessDeptManager;

	/**
	 * <p>
	 * Description:smsInfoSender<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public SmsInfoSenderImpl getSmsInfoSender() {
		return smsInfoSender;
	}

	/**
	 * <p>
	 * Description:smsInfoSender<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setSmsInfoSender(SmsInfoSenderImpl smsInfoSender) {
		this.smsInfoSender = smsInfoSender;
	}

	/**
	 * <p>
	 * Description:messageManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * <p>
	 * Description:messageManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * <p>
	 * Description:employeeService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * <p>
	 * Description:employeeService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * <p>
	 * Description:departmentService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-6
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * <p>
	 * Description:departmentService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-6
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * <p>
	 * Description:claimService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public IClaimService getClaimService() {
		return claimService;
	}

	/**
	 * <p>
	 * Description:recompenseService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	/**
	 * <p>
	 * Description:recompenseService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	/**
	 * <p>
	 * Description:claimService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public void setClaimService(IClaimService claimService) {
		this.claimService = claimService;
	}

	/**
	 * <p>
	 * Description:recompenseManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	/**
	 * <p>
	 * Description:recompenseManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	/**
	 * 
	 * <p>
	 * Description:生成运单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-8
	 * @param voucherNo
	 * @param recompenseMethod
	 * @param waybillInfo
	 * @param unbilledOaAccident
	 * @return Waybill
	 */
	public Waybill createWaybill(String voucherNo, String recompenseMethod,
			FossWaybillInfo waybillInfo, OaAccidentInfo unbilledOaAccident) {
		Waybill wb = null;
		if (Constants.UNBILLED.equals(recompenseMethod)) {
			// 通过构造函数生成运单
			wb = new Waybill(voucherNo, unbilledOaAccident.getCargoName(),
					unbilledOaAccident.getTransportType(),
					unbilledOaAccident.getReceivingDept(),
					unbilledOaAccident.getInsuredAmount(),
					unbilledOaAccident.getContactName(),
					unbilledOaAccident.getContactType(),
					unbilledOaAccident.getStartStation(),
					unbilledOaAccident.getDestinationStation(),
					unbilledOaAccident.getPackaging(),
					unbilledOaAccident.getCargoWeight(),
					unbilledOaAccident.getDateShiped(), null, null);
			// 把收货部门名字转换为id
			String receivingdeptName = wb.getReceiveDept();
			if (null != receivingdeptName && !"".equals(receivingdeptName)) {
				List<Department> departments = departmentService
						.getDepartmentByDeptName(receivingdeptName);
				if (null != departments && departments.size() != 0) {

					wb.setReceiveDept(departments.get(0).getId());
				}else{
					ClaimException re = new ClaimException(
							ClaimExceptionType.RECEIVEDEPT_ERROR);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};
				}
				// 把运输类型转换中文到英文
				if (null != unbilledOaAccident.getTransportType()) {
					// 汽运
					if (Constants.TRANS_VEHICLE_NAME.equals(unbilledOaAccident
							.getTransportType())) {
						wb.setTransType(Constants.TRANS_VEHICLE);
						// 空运
					} else if (Constants.TRANS_AIRCRAFT_NAME
							.equals(unbilledOaAccident.getTransportType())) {
						wb.setTransType(Constants.TRANS_AIRCRAFT);
						// 快递
					} else if (Constants.TRANS_EXPRESS_NAME
							.equals(unbilledOaAccident.getTransportType())) {
						wb.setTransType(Constants.TRANS_EXPRESS);
					}
				} else {
					wb.setTransType(Constants.TRANS_VEHICLE);
				}

			}

			return wb;

		} else {
			String tel = waybillInfo.getSenderMobile();
			if (tel == null) {
				tel = waybillInfo.getSenderPhone();
			} else if (waybillInfo.getSenderPhone() != null) {
				tel = waybillInfo.getSenderPhone() + "/" + tel;
			}

			// 通过构造函数生成运单
			wb = new Waybill(voucherNo, waybillInfo.getGoodName(),
					waybillInfo.getTranType(),
					waybillInfo.getDepartureDeptName(), waybillInfo
							.getInsuranceValue().doubleValue(),
					waybillInfo.getSender(), tel, waybillInfo.getDeparture(),
					waybillInfo.getDestination(), waybillInfo.getPacking(),
					waybillInfo.getPieces() + "/" + waybillInfo.getWeight()
							+ "/" + waybillInfo.getCubage(),
					waybillInfo.getSendTime(), waybillInfo.getSenderNumber(),
					waybillInfo.getConsigneeNumber());
			// 把发货部门名字转换为id
			String receivingdeptCode = waybillInfo.getReceiveDeptNumber();
			if (null != receivingdeptCode && !"".equals(receivingdeptCode)) {
				Department receivingDept = departmentService
						.getDeptByStandardCode(receivingdeptCode);

				wb.setReceiveDept(receivingDept.getId());

			}
			// 到达部门标杆编码
			String arrivedDeptCode = waybillInfo.getLadingStationNumber();
			// 根据部门标杆编码查出部门信息
			if (null != arrivedDeptCode && !"".equals(arrivedDeptCode)) {
				Department arrivedDept = departmentService
						.getDeptByStandardCode(arrivedDeptCode);
				if (null != arrivedDept) {
					wb.setArrivedDept(arrivedDept.getId());
				}
			}
			wb.setConsignee(waybillInfo.getConsignee());
			String comsigneeTel = waybillInfo.getConsigneePhone();
			if (comsigneeTel == null) {
				comsigneeTel = waybillInfo.getConsigneeMobile();
			} else if (waybillInfo.getConsigneeMobile() != null) {
				comsigneeTel = waybillInfo.getConsigneeMobile() + "/"
						+ comsigneeTel;
			}
			wb.setConsigneePhone(comsigneeTel);

			return wb;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据凭证号查询运单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5
	 * @param voucherNo
	 * @param recompenseType
	 * @return
	 * 
	 */

	@Override
	public Claim getWayBillAndType(String voucherNo, String recompenseMethod,
			User user) {
		Waybill wb = null;
		Claim clm = new Claim();
		// 校验是否是正确的运单号
		if (ClaimValidator.validateWaybillNumber(voucherNo, recompenseMethod)) {
			// 根据凭证号号查询OA差错信息
			List<OaAccidentInfo> waybillAccidentList = this.recompenseService
					.getAccidentByWaybillNum(voucherNo);
			// 根据凭证号号查询未开单差错信息
			OaAccidentInfo unbilledOaAccident = this.recompenseService
					.getAccidentByAccidentCode(voucherNo);

			// 根据凭证号查询运单信息
			FossWaybillInfo waybillInfo = this.recompenseService
					.getWaybillRecompense(voucherNo);
			// 根据凭证号查询理赔信息
			RecompenseApplication oldApp = recompenseService
					.getRecompenseApplicationByVoucherNo(voucherNo);
			// 根据凭证号查询是在线理赔信息
			List<OnlineApply> onlineApplyList = recompenseService
					.searchOnlineApplyByWaybillNum(voucherNo);
			OnlineApply onlineApply = new OnlineApply();
			// 如果存在在线理赔转换为实体
			if (onlineApplyList != null && onlineApplyList.size() != 0) {
				onlineApply = onlineApplyList.get(0);

			}

			Claim claim = claimService.getClaimByWaybillNumber(voucherNo);
			// 获得当前登录用户的部门信息
			Department currentDept = user.getEmpCode().getDeptId();
			// 检验是否可以上报索赔
			if (ClaimValidator.canCreateClaim(recompenseMethod, waybillInfo,
					waybillAccidentList, unbilledOaAccident, oldApp,
					onlineApply, claim)) {
				wb = this.createWaybill(voucherNo, recompenseMethod,
						waybillInfo, unbilledOaAccident);
			}
			// 当选择的理赔类型为非未开单时并且运输类型为快递
			if (wb.getTransType().equals(Constants.TRANS_EXPRESS)) {
				// 如果是未开单理赔
				if (recompenseMethod.equals(Constants.UNBILLED)) {
					// 如果部门为点部和统计组 则提示
					Department receivingDept = departmentService
							.getDepartmentById(wb.getReceiveDept());
					if(receivingDept==null){
						ClaimException re = new ClaimException(
								ClaimExceptionType.NO_RECEIVE_DEPT);
						throw new GeneralException(re.getErrorCode(),
								re.getMessage(), re, new Object[] {}) {
						};
					}
					if (receivingDept.getDeptName().contains("统计组")
							|| receivingDept.getDeptName().contains("点部")) {
						ClaimException re = new ClaimException(
								ClaimExceptionType.EXPRESS_DEPT_IS_ERROR);
						throw new GeneralException(re.getErrorCode(),
								re.getMessage(), re, new Object[] {}) {
						};
					}
					// 查询出来当前登录用户所在大区
					Department currentBigArea = 
							this.searchBigArea(user.getEmpCode().getDeptId().getStandardCode(),false);
					// 收货部门标杆编码
					String deptCode = departmentService.getDepartmentById(
							wb.getReceiveDept()).getStandardCode();

					ExpressPointBusinessDept exPointBusinessDept = expressPointBusinessDeptManager
							.getExpressPointBusinessDeptByDeptCode(deptCode);
					// 是否区域客服上报
					boolean temp = false;
					if(exPointBusinessDept!=null){
						Department expressPointDept = departmentService
								.getDeptByStandardCode(exPointBusinessDept.getExpressPointCode());
						Employee princinal = employeeService
								.getEmpByCode(expressPointDept.getPrincipal());
						if(princinal!=null){
							if(Constants.RECOMPENSE_EXPRESS_POSITION_MANAGER
									.equals(princinal.getPosition())){
								temp=true;
							}
						}
					}
					//营业部上报
					if (exPointBusinessDept == null||!temp) {
						if (!user.getRoleids().contains(Constants.ROLE_EXPRESS)
								&& !user.getRoleids().contains(
										Constants.ROLE_CUSTOMSERVICE)) {
							
							User currentUser = (User)(UserContext.getCurrentUser());
							if(!currentUser.getEmpCode().getDeptId().getId().equals(wb.getReceiveDept())) {
								ClaimException re = new ClaimException(
										ClaimExceptionType.NULLCURENEXPRESSBIGAREA);
								throw new GeneralException(re.getErrorCode(),
										re.getMessage(), re, new Object[] {}) {
								};
							}

							if (ClaimValidator.vadlidateDept(currentDept,
									recompenseMethod, wb, waybillInfo)) {
								clm.setReceiveDept(wb.getReceiveDept());
								clm.setArrivedDept(wb.getArrivedDept());
								clm.setInsuranceValue(wb.getInsurAmount());
								clm.setWaybillNumber(wb.getWaybillNumber());
								// 如果当前登录部门与到达部门一样的 这自动带出收货方
								if (currentDept.getId().equals(
										wb.getArrivedDept())) {
									clm.setClaimer(ClaimConstants.CONSIGNEE);
									clm.setLinkMan(wb.getConsignee());
									clm.setLinkMobile(wb.getConsigneePhone());
								} else {

									clm.setClaimer(ClaimConstants.SHIPPER);
									clm.setLinkMan(wb.getInsured());
									// 发货联系人
									clm.setLinkMobile(wb.getTelephone());
								}
								clm.setInsuranceValue(wb.getInsurAmount());
								clm.setConsignee(wb.getConsignee());
								clm.setConsigneePhone(wb.getConsigneePhone());
								clm.setShipper(wb.getInsured());
								clm.setShipperPhone(wb.getTelephone());
								clm.setTransType(wb.getTransType());

								return clm;
							}
						} else {

							ClaimException re = new ClaimException(
									ClaimExceptionType.CURRENTNOTBUSSINESS);
							throw new GeneralException(re.getErrorCode(),
									re.getMessage(), re, new Object[] {}) {
							};

						}
					} else {
						//收货部门点部或大区
						Department receiveBigArea = this.searchBigArea(
								user.getEmpCode().getDeptId().getStandardCode(),true);
						
						if (ClaimValidator.canCreateClaimDeptForExpress(user,
								currentBigArea, wb, receiveBigArea))
							;
						// 点部所在大区
						List<Department> departmentList = departmentService
								.queryDeptByNameAndStandardCode(receiveBigArea
										.getStandardCode());
						Department officeDept = null;
						// 如果不为空，且list长度大于1，则有统计组
						if (null != departmentList
								&& departmentList.size() >= 1) {

							officeDept = departmentList.get(0);
						} else {
							ClaimException re = new ClaimException(
									ClaimExceptionType.NOTEXPRESSOFFICE);
							throw new GeneralException(re.getErrorCode(),
									re.getMessage(), re, new Object[] {}) {
							};

						}
						clm.setWaybillNumber(wb.getWaybillNumber());
						clm.setConsignee(wb.getConsignee());
						clm.setConsigneePhone(wb.getConsigneePhone());
						clm.setShipper(wb.getInsured());
						clm.setShipperPhone(wb.getTelephone());
						clm.setTransType(wb.getTransType());
						clm.setArrivedDept(wb.getArrivedDept());
						clm.setInsuranceValue(wb.getInsurAmount());
						clm.setReceiveDept(officeDept.getId());
						// 如果当前登录部门与到达部门一样的 这自动带出收货方
						if (currentDept.getId().equals(wb.getArrivedDept())) {
							clm.setClaimer(ClaimConstants.CONSIGNEE);
							clm.setLinkMan(wb.getConsignee());
							clm.setLinkMobile(wb.getConsigneePhone());
						} else {
							clm.setClaimer(ClaimConstants.SHIPPER);
							clm.setLinkMan(wb.getInsured());
							// 发货联系人
							clm.setLinkMobile(wb.getTelephone());
						}
						return clm;

					}

				} else {
					// 查询出来当前登录用户所在点部或大区
					Department currentBigArea = 
							this.searchBigArea(user.getEmpCode().getDeptId().getStandardCode(),false);
					// 出发部门对于点部或大区
					Department exLeaveBigArea = 
							this.searchBigArea(waybillInfo.getReceiveDeptNumber(),true);
					//到达部门对应点部或大区
					Department exArriveBigArea = 
							this.searchBigArea(waybillInfo.getLadingStationNumber(),true);
					//是：出发区域客服理赔；否：出发营业部
					boolean isLeaveExpressDept = this.isExpressPoint(
							waybillInfo.getReceiveDeptNumber());
					//是：到达区域客服理赔；否：到达营业部
					boolean isArriveExpressDept = this.isExpressPoint(
							waybillInfo.getLadingStationNumber());
					if (ClaimValidator.CreateClaimDeptForExpress(user,
							currentBigArea, waybillInfo, wb, exLeaveBigArea,
							exArriveBigArea,isLeaveExpressDept,isArriveExpressDept)) {			
						clm.setInsuranceValue(wb.getInsurAmount());
						clm.setWaybillNumber(wb.getWaybillNumber());
						clm.setConsignee(wb.getConsignee());
						clm.setConsigneePhone(wb.getConsigneePhone());
						clm.setShipper(wb.getInsured());
						clm.setShipperPhone(wb.getTelephone());
						clm.setTransType(wb.getTransType());
						clm.setInsuranceValue(wb.getInsurAmount());
						
						Department officeDept = null;
						//如果出发区域客服理赔
						if(isLeaveExpressDept){
							List<Department> departments = departmentService
									.queryDeptByNameAndStandardCode(exLeaveBigArea
											.getStandardCode());
							if (null != departments
									&& departments.size() >= 1) {
								officeDept = departments.get(0);
							} else {
								ClaimException re = new ClaimException(
										ClaimExceptionType.NOTEXPRESSOFFICE);
								throw new GeneralException(
										re.getErrorCode(),
										re.getMessage(), re,
										new Object[] {}) {
								};
							}
							clm.setReceiveDept(officeDept.getId());
						}else{
							clm.setReceiveDept(wb.getReceiveDept());
						}
						//如果到达区域客服理赔
						if(isArriveExpressDept){
							List<Department> departments = departmentService
									.queryDeptByNameAndStandardCode(exArriveBigArea
											.getStandardCode());
							if (null != departments
									&& departments.size() >= 1) {
								officeDept = departments.get(0);
							} else {
								ClaimException re = new ClaimException(
										ClaimExceptionType.NOTEXPRESSOFFICE);
								throw new GeneralException(
										re.getErrorCode(),
										re.getMessage(), re,
										new Object[] {}) {
								};
							}
							clm.setArrivedDept(officeDept.getId());
						}else{
							clm.setArrivedDept(wb.getArrivedDept());
						}
						//设置默认带出出发方还是收货方
						if(exArriveBigArea==null
								||currentBigArea.getId().equals(exLeaveBigArea.getId())
								||user.getEmpCode().getDeptId().getId()
									.equals(wb.getReceiveDept())){
							clm.setClaimer(ClaimConstants.SHIPPER);
							clm.setLinkMan(wb.getInsured());
							clm.setLinkMobile(wb.getTelephone());
						}else{
							clm.setClaimer(ClaimConstants.CONSIGNEE);
							clm.setLinkMan(wb.getConsignee());
							clm.setLinkMobile(wb.getConsigneePhone());
						}
					}
					return clm;
				}
			} else if (ClaimValidator.vadlidateDept(currentDept,
					recompenseMethod, wb, waybillInfo)) {
				clm.setReceiveDept(wb.getReceiveDept());
				clm.setArrivedDept(wb.getArrivedDept());
				clm.setInsuranceValue(wb.getInsurAmount());
				clm.setWaybillNumber(wb.getWaybillNumber());
				// 如果当前登录部门与到达部门一样的 这自动带出收货方
				if (currentDept.getId().equals(wb.getArrivedDept())) {
					clm.setClaimer(ClaimConstants.CONSIGNEE);
					clm.setLinkMan(wb.getConsignee());
					clm.setLinkMobile(wb.getConsigneePhone());
				} else {

					clm.setClaimer(ClaimConstants.SHIPPER);
					clm.setLinkMan(wb.getInsured());
					// 发货联系人
					clm.setLinkMobile(wb.getTelephone());
				}
				clm.setInsuranceValue(wb.getInsurAmount());
				clm.setConsignee(wb.getConsignee());
				clm.setConsigneePhone(wb.getConsigneePhone());
				clm.setShipper(wb.getInsured());
				clm.setShipperPhone(wb.getTelephone());
				clm.setTransType(wb.getTransType());

				return clm;
			}
		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:保存索赔录入<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claim
	 * @return Claim
	 */
	public Claim saveClaim(Claim claim, User user) {
		if (null != claim) {
			List<OaAccidentInfo> waybillAccidentList = this.recompenseService
					.getAccidentByWaybillNum(claim.getWaybillNumber());
			// 根据凭证号号查询未开单差错信息
			OaAccidentInfo unbilledOaAccident = this.recompenseService
					.getAccidentByAccidentCode(claim.getWaybillNumber());

			// 根据凭证号查询运单信息
			FossWaybillInfo waybillInfo = this.recompenseService
					.getWaybillRecompense(claim.getWaybillNumber());
			// 根据凭证号查询理赔信息
			RecompenseApplication oldApp = recompenseService
					.getRecompenseApplicationByVoucherNo(claim
							.getWaybillNumber());
			// 根据凭证号查询是在线理赔信息
			List<OnlineApply> onlineApplyList = recompenseService
					.searchOnlineApplyByWaybillNum(claim.getWaybillNumber());
			OnlineApply onlineApply = new OnlineApply();
			// 如果存在在线理赔转换为实体
			if (onlineApplyList != null && onlineApplyList.size() != 0) {
				onlineApply = onlineApplyList.get(0);

			}
			Claim oldClaim = claimService.getClaimByWaybillNumber(claim
					.getWaybillNumber());
			// 获得当前登录用户的部门信息
			Department currentDept = user.getEmpCode().getDeptId();
			// 生成运单信息

			// 检验是否可以上报索赔
			Waybill wb = this.createWaybill(claim.getWaybillNumber(),
					claim.getRecompenseMethod(), waybillInfo,
					unbilledOaAccident);
			if (ClaimValidator.canCreateClaim(claim.getRecompenseMethod(),
					waybillInfo, waybillAccidentList, unbilledOaAccident,
					oldApp, onlineApply, oldClaim)
					&& ClaimValidator.vadlidateDept(currentDept,
							claim.getRecompenseMethod(), wb, waybillInfo)) {
				// 封装数据
				// 创建日期
				claim.setCreateDate(new Date());
				// 修改日期
				claim.setModifyDate(new Date());
				// 创建人
				claim.setCreateUser(user.getEmpCode().getId());
				// 修改人
				claim.setModifyUser(user.getEmpCode().getId());
				// 索赔状态
				claim.setClaimStatus(ClaimConstants.CLAIM_STATUS_WAIT_DO);
				claim.setStatusSeq("1");
				// 报案人
				claim.setReporter(user.getEmpCode().getId());
				// 当前处理人
				claim.setProcessor(user.getEmpCode().getId());
				// 报案部门
				claim.setReportDept(user.getEmpCode().getDeptId().getId());
				// 当前处理部门
				claim.setProcessDept(user.getEmpCode().getDeptId().getId());
				claim.setTranferCount(0);
				return claimService.saveClaim(claim);
			}
		}
		return null;

	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询索赔列表<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimSearchCondition
	 * @return List<Claim>
	 */
	public Map<String, Object> searchClaimsByCondition(
			ClaimSearchCondition claimSearchCondition, User user) {
		if (null != claimSearchCondition.getProcessDepts().get(0)
				&& "".equals(claimSearchCondition.getProcessDepts().get(0))) {
			claimSearchCondition.getProcessDepts().remove(0);
			claimSearchCondition.getProcessDepts().add(
					user.getEmpCode().getDeptId().getId());
		}
		// 校验查询条件
		if (ClaimValidator.checkClaimSearchCondition(claimSearchCondition)) {
			// 理赔专员调用getAuthDepts查询权限部门
			if (user.getEmpCode().getDeptId().getId()
					.equals(claimSearchCondition.getProcessDepts().get(0))) {

				claimSearchCondition.getProcessDepts().addAll(
						this.getAuthDepts(user, claimSearchCondition));
			}
			// 如果是理赔研究小组则默认查询全国
			String processDept = claimSearchCondition.getProcessDepts().get(0);
			Department department = null;
			if (null != processDept && !"".equals(processDept)) {
				department = departmentService.getDepartmentById(processDept);
			}

			if (user.getEmpCode().getDeptId().getStandardCode()
					.equals(ClaimConstants.RECOMPENSEGROUPCODE)
					&& null != department
					&& department.getStandardCode().equals(
							ClaimConstants.RECOMPENSEGROUPCODE)
					&& user.getEmpCode()
							.getDeptId()
							.getId()
							.equals(claimSearchCondition.getProcessDepts().get(
									0))) {
				claimSearchCondition.setProcessDepts(null);
			}

			ClaimSearchCondition condition = tranCondition(claimSearchCondition);
			// 根据条件查询
			List<Claim> claimList = claimService
					.searchClaimsByCondition(condition);
			if (null != claimList && claimList.size() != 0) {
				for (int i = 0; i < claimList.size(); i++) {
					Claim c = claimList.get(i);
					if (c.getProcessDept().equals(c.getReportDept())) {
						claimList.get(i).setShowTime(c.getCreateDate());

					} else {
						if (null == claimList.get(i).getClaimTime()) {
							claimList.get(i).setShowTime(c.getCreateDate());
						} else {
							claimList.get(i).setShowTime(c.getClaimTime());
						}
					}
				}
			}
			Integer count = claimService.countClaimsByCondition(condition);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("claimList", claimList);
			map.put("count", count);
			return map;

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("claimList", null);
		map.put("count", 0);
		return map;
	}

	/**
	 * <p>
	 * Description:转换搜索条件<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-6
	 * @param claimSearchCondition
	 * @return ClaimSearchCondition
	 */
	public ClaimSearchCondition tranCondition(
			ClaimSearchCondition claimSearchCondition) {
		if (null != claimSearchCondition
				&& claimSearchCondition.getWaybillNumber() != null
				&& claimSearchCondition.getWaybillNumber().length() != 0) {
			ClaimSearchCondition condition = new ClaimSearchCondition();
			condition.setWaybillNumber(claimSearchCondition.getWaybillNumber());
			condition.setStart(claimSearchCondition.getStart());
			condition.setLimit(claimSearchCondition.getLimit());
			condition.setStartTime(null);
			condition.setEndTime(null);

			return condition;

		}
		claimSearchCondition.setWaybillNumber(null);
		return claimSearchCondition;
	}

	public List<String> getAuthDepts(User user,
			ClaimSearchCondition claimSearchCondition) {
		// 如果是传入是本部门
		List<String> deptIds = new ArrayList<String>();

		// 如果当前登录用户为理赔专员则查询其权限大区

		// 如果是理赔专员的角色就在大区设置里查询权限大区
		if (user.getRoleids().contains(Constants.ROLE_ADMIN)) {
			// 根据用户id去大区设置查询权限大区部门角色关系
			List<UserRoleDeptRelation> urds = recompenseService
					.getUserRoleDeptRelationByUserId(user.getId());
			// 校验是否进行了大区设置
			if (null != urds) {
				// 循环遍历每个大区查询出所有子部门
				for (int i = 0; i < urds.size(); i++) {
					// 获得大区id
					String deptId = urds.get(i).getDept().getId();
					// 查询所有的子部门
					List<Department> departments = departmentService
							.queryAllChildDeptByDeptId(deptId);
					for (Department department : departments) {

						deptIds.add(department.getId());
					}
				}
			}
		}

		return deptIds;

	}

	/**
	 * 
	 * <p>
	 * Description:根据id获取<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-6
	 * @param claimId
	 * @return Claim
	 */
	public Claim getClaimById(String claimId) {
		if (null != claimId) {
			// 根据id查询索赔
			Claim claim = claimService.getClaimById(claimId);
			if (null != claim) {
				if (claim.getProcessDept().equals(claim.getReportDept())) {
					claim.setShowTime(claim.getCreateDate());
				} else {
					if (null == claim.getClaimTime()) {
						claim.setShowTime(claim.getCreateDate());
					} else {
						claim.setShowTime(claim.getClaimTime());
					}
				}

			}
			return claim;
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:根据凭证号查询操作日志<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param VoucherNo
	 * @return List<ClaimOperationLog>
	 */
	public List<ClaimOperationLog> getOperationLogListByClaimId(String claimId) {
		if (null != claimId) {
			return claimService.getOperationLogListByClaimId(claimId);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description根据凭证号查询索赔跟进消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param voucherNo
	 * @return List<ClaimMessage>
	 */
	public List<ClaimMessage> getClaimMessageListByClaimId(String claimId) {
		if (null != claimId) {
			return claimService.getClaimMessageListByClaimId(claimId);

		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:发送到对方部门<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claim
	 *            void
	 */
	@Transactional
	public void sendToAnotherDept(String claimId, String reason, User user) {
		if (ClaimValidator.checkReasonNotNull(reason)) {
			if (null != claimId) {
				Claim claim = claimService.getClaimById(claimId);
				if (!claim.getProcessDept().equals(
						user.getEmpCode().getDeptId().getId())) {
					ClaimException re = new ClaimException(
							ClaimExceptionType.ERROR_PROCESS_DEPT);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};

				}
				String currentProceessDeptName = claim.getProcessDeptName();
				String toSendDeptName = "";
				Employee employee = null;
				Department dept = null;
				if (null != claim && ClaimValidator.canSendToAnotherDept(claim))
					// 发货方
					if (claim.getClaimer().equals(ClaimConstants.SHIPPER)) {
						claim.setClaimer(ClaimConstants.CONSIGNEE);
						claim.setProcessDept(claim.getArrivedDept());
						// 根据id查询出部门信息
						if (null != claim.getArrivedDept()) {
							dept = departmentService.getDepartmentById(claim
									.getArrivedDept());
							toSendDeptName = dept.getDeptName();
							// 如果部门名字含有统计组,则当前处理人为空
							if (toSendDeptName.contains("统计组")) {
								claim.setProcessor(null);
							} else {

								// 获得其部门负责人的code
								String processorCode = dept.getPrincipal();
								employee = employeeService
										.getEmpByCode(processorCode);
								claim.setProcessor(employee.getId());
							}
							claim.setLinkMan(claim.getConsignee());
							claim.setLinkMobile(claim.getConsigneePhone());
						}
					} else
					// 收货方
					{
						claim.setClaimer(ClaimConstants.SHIPPER);
						claim.setProcessDept(claim.getReceiveDept());
						// 根据id查询出部门信息
						dept = departmentService.getDepartmentById(claim
								.getReceiveDept());
						// 发送部门的名字
						toSendDeptName = dept.getDeptName();
						// 获得其部门负责人的id
						if (toSendDeptName.contains("统计组")) {
							claim.setProcessor(null);
						} else {
							String processorCode = dept.getPrincipal();
							employee = employeeService
									.getEmpByCode(processorCode);
							claim.setProcessor(employee.getId());
						}
						claim.setLinkMan(claim.getShipper());
						claim.setLinkMobile(claim.getShipperPhone());

					}
				// 如果是第一次发送，则需要修改第一次发送时间
				if (claim.getTranferCount() == 0) {

					claim.setFirstRefuseTime(new Date());
				}
				// 设置流转次数+1
				claim.setTranferCount(claim.getTranferCount() + 1);

				claim.setModifyDate(new Date());
				// 发送是设置为待受理
				claim.setClaimStatus(ClaimConstants.CLAIM_STATUS_WAIT_ACCEPT);
				claim.setStatusSeq("2");
				// 进行更新操作
				Claim newClaim = claimService.updateClaim(claim);
				// 如果原因为空，则提示
				if (null == reason && "".equals(reason)) {
					ClaimException re = new ClaimException(
							ClaimExceptionType.NULL_FOLLOW_MESSAGE);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {

					};
				}

				// 生成操作日志内容
				String operationContent = MessageFormat
						.format(ClaimConstants.SEND_CLAIM_OPERATION_LOG,
								new Object[] { claim.getWaybillNumber(),
										currentProceessDeptName,
										toSendDeptName, reason });
				// /保存操作日志
				addOperationLog(operationContent, claimId, user);

				// 发送短信
				String smsContent = MessageFormat.format(
						ClaimConstants.MESEAGE_CONTENT,
						new Object[] { currentProceessDeptName,
								claim.getWaybillNumber(), });
				// 生成待办
				String todoContent = MessageFormat.format(
						ClaimConstants.TODO_MESSAGE,
						new Object[] { claim.getWaybillNumber() });
				com.deppon.crm.module.common.shared.domain.Message message = new Message();
				message.setTasktype(Constants.TODO_TASK_TYPE);
				message.setIshaveinfo(todoContent);
				message.setTaskcount(1);
				// 如果是统计组，则发送的部门为整个部门
				if (toSendDeptName.contains("统计组")) {
					List<Employee> employeeList = employeeService
							.getAllEmployeesByDeptId(dept);
					for (Employee e : employeeList) {
						// 发送短信
						recompenseService.sendSmsInfo(e.getMobileNumber(),
								smsContent, e.getEmpCode(),
								dept.getStandardCode());
						User processUser = userService.findByLoginName(e
								.getEmpCode());
						message.setUserid(Integer.parseInt(processUser.getId()));
						// 生成代办
						recompenseManager.addTodoMessage(message);
					}

				} else {

					// 调用理赔servicc发送短信
					recompenseService.sendSmsInfo(employee.getMobileNumber(),
							smsContent, employee.getEmpCode(),
							dept.getStandardCode());
					// 发送给人的Id
					Employee emp = employeeService.getEmpById(newClaim
							.getProcessor());
					User processUser = userService.findByLoginName(emp
							.getEmpCode());

					message.setUserid(Integer.parseInt(processUser.getId()));
					// 生成代办
					recompenseManager.addTodoMessage(message);
				}

			}

		}
	}

	/**
	 * 
	 * <p>
	 * Description免赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param voucherNo
	 *            void
	 */
	public void remitClaimByClaimId(String claimId, User user) {
		if (null != claimId) {
			Claim claim = claimService.getClaimById(claimId);
			// 部门校验
			if (!claim.getProcessDept().equals(
					user.getEmpCode().getDeptId().getId())) {
				ClaimException re = new ClaimException(
						ClaimExceptionType.ERROR_PROCESS_DEPT);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};

			}
			// 更新状态到免赔
			claim.setClaimStatus(ClaimConstants.CLAIM_STATUS_REMITTED);
			claim.setStatusSeq("4");
			claim.setModifyDate(new Date());
			claim.setModifyUser(user.getEmpCode().getId());
			Claim newClaim = claimService.updateClaim(claim);
			// 生成操作日志
			String operationContent = MessageFormat.format(
					ClaimConstants.REMIT_CLAIM_OPERATION_LOG, new Object[] {
							newClaim.getWaybillNumber(),
							user.getEmpCode().getDeptId().getDeptName() });
			// /保存操作日志
			addOperationLog(operationContent, claimId, user);
		}

	}

	/**
	 * 
	 * <p>
	 * Description:增加操作日志<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimOperationLog
	 * @return ClaimOperationLog
	 */
	public ClaimOperationLog addOperationLog(String content, String claimId,
			User user) {
		ClaimOperationLog claimOperationLog = new ClaimOperationLog();
		// 封装数据
		claimOperationLog.setClaimId(claimId);
		claimOperationLog.setCreateDate(new Date());
		claimOperationLog.setCreateUser(user.getEmpCode().getId());
		claimOperationLog.setModifyDate(new Date());
		claimOperationLog.setModifyUser(user.getEmpCode().getId());
		claimOperationLog
				.setOperatorDept(user.getEmpCode().getDeptId().getId());
		claimOperationLog.setOperatorTime(new Date());
		claimOperationLog.setOperator(user.getEmpCode().getId());
		claimOperationLog.setOperatorContent(content);
		// 调用service保存
		return claimService.addOperationLog(claimOperationLog);

	}

	/**
	 * 
	 * <p>
	 * Description:增加跟进消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimMessage
	 * @return ClaimMessage
	 */
	public ClaimMessage addFollowClaimMessage(ClaimMessage claimMessage,
			User user) {
		// 非空校验
		if (null != claimMessage) {
			if (null == claimMessage.getContent()) {
				ClaimException re = new ClaimException(
						ClaimExceptionType.NULL_FOLLOW_MESSAGE);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {

				};
			}
			claimMessage.setCreateDate(new Date());
			claimMessage.setCreateUser(user.getEmpCode().getId());
			claimMessage.setFollowDept(user.getEmpCode().getDeptId().getId());
			claimMessage.setCreateTime(new Date());
			claimMessage.setFollowUser(user.getEmpCode().getEmpName());
			claimMessage.setFollowUserId(user.getEmpCode().getId());
			claimMessage.setModifyDate(new Date());
			claimMessage.setModifyUser(user.getEmpCode().getId());
			// 调用service保存

			return claimService.addFollowClaimMessage(claimMessage);

		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:免赔解冻<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 * @param claimId
	 *            void
	 * @return
	 */
	public Claim cancelRemitClaim(String claimId, User user) {
		// 如果是理赔研究小组则才能进行免赔解冻

		if (user.getEmpCode().getDeptId().getStandardCode()
				.equals(ClaimConstants.RECOMPENSEGROUPCODE)) {
			Claim claim = claimService.getClaimById(claimId);
			claim.setClaimStatus(ClaimConstants.CLAIM_STATUS_WAIT_DO);
			claim.setStatusSeq("2");
			claim.setModifyDate(new Date());
			claim.setModifyUser(user.getEmpCode().getId());
			// 更新状态
			Claim newClaim = claimService.updateClaim(claim);
			// 生成操作日志
			String operationContent = MessageFormat.format(
					ClaimConstants.CANCEL_REMIT_CLAIM_OPERATION_LOG,
					new Object[] { newClaim.getWaybillNumber(),
							user.getEmpCode().getEmpName() });
			// /保存操作日志
			addOperationLog(operationContent, claimId, user);
			return newClaim;
		} else {
			ClaimException re = new ClaimException(
					ClaimExceptionType.ERROR_CANNEL_REMIT_DEPT);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {

			};
		}

	}

	/**
	 * 
	 * <p>
	 * Description:同意受理<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 * @param claimId
	 * @param user
	 *            void
	 */
	public Claim acceptClaim(Claim claim, User user) {
		// 同意受理到待受理状态

		Claim oldClaim = claimService.getClaimById(claim.getId());
		if (!oldClaim.getProcessDept().equals(
				user.getEmpCode().getDeptId().getId())) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.ERROR_PROCESS_DEPT);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		}
		//
		if (!oldClaim.getReportDept().equals(
				user.getEmpCode().getDeptId().getId())
				&& null == oldClaim.getClaimTime()) {
			claim.setClaimTime(new Date());
		}

		claim.setClaimStatus(ClaimConstants.CLAIM_STATUS_WAIT_DO);
		claim.setStatusSeq("1");
		claim.setModifyDate(new Date());
		claim.setModifyUser(user.getEmpCode().getId());
		Claim newClaim = claimService.updateClaim(claim);

		Claim toClaim = claimService.getClaimById(newClaim.getId());

		if (null != toClaim) {
			if (toClaim.getProcessDept().equals(toClaim.getReportDept())) {
				toClaim.setShowTime(toClaim.getCreateDate());
			} else {
				if (null == toClaim.getClaimTime()) {
					toClaim.setShowTime(toClaim.getCreateDate());
				} else {
					toClaim.setShowTime(toClaim.getClaimTime());
				}
			}

		}

		return toClaim;

	}

	/**
	 * <p>
	 * Description:userService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-13
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * <p>
	 * Description:userService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-13
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public PilotCityManager getPilotCityManager() {
		return pilotCityManager;
	}

	public void setPilotCityManager(PilotCityManager pilotCityManager) {
		this.pilotCityManager = pilotCityManager;
	}

	public ExpressPointBusinessDeptManager getExpressPointBusinessDeptManager() {
		return expressPointBusinessDeptManager;
	}

	public void setExpressPointBusinessDeptManager(
			ExpressPointBusinessDeptManager expressPointBusinessDeptManager) {
		this.expressPointBusinessDeptManager = expressPointBusinessDeptManager;
	}
	/**
	 * 根据部门标杆编码查询分部或者大区
	 */
	private Department searchBigArea(String standCode,Boolean searchExpressPoint){
		if(standCode ==null||"".equals(standCode)){
			return null;
		}
		if(searchExpressPoint){
			ExpressPointBusinessDept epbd =expressPointBusinessDeptManager
					.getExpressPointBusinessDeptByDeptCode(standCode);
			if(epbd!=null&&epbd.getExpressPointCode()!=null
					&&!"".equals(epbd.getExpressPointCode())){
				standCode=epbd.getExpressPointCode();
			}
		}
		Map<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("standCode",standCode);
		paramsMap.put("lastWord","快递分部");
		Department BigArea = null;
		BigArea = departmentService.getAllParentDeptBystandCode(paramsMap);
		if(BigArea ==null){
			paramsMap.put("lastWord", "大区");
			BigArea = departmentService.getAllParentDeptBystandCode(paramsMap);
		}
		return BigArea;
	}
	/**
	 * 判断是否是正常由区域客服上报
	 */
	private boolean isExpressPoint(String StandCode){
		if(!(StandCode==null||"".equals(StandCode))){
			ExpressPointBusinessDept epbd=expressPointBusinessDeptManager
					.getExpressPointBusinessDeptByDeptCode(StandCode);
			if(epbd!=null){
				Department expressDept=departmentService
						.getDeptByStandardCode(epbd.getExpressPointCode());
				if(expressDept!=null&&expressDept.getPrincipal()!=null
						&&!"".equals(expressDept.getPrincipal())){
					Employee principal=
							employeeService.getEmpByCode(expressDept.getPrincipal());
					if(principal.getPosition()!=null&&!"".equals(principal.getPosition())
							&&Constants.RECOMPENSE_EXPRESS_POSITION_MANAGER
								.equals(principal.getPosition())){
						return true;
					}
				}
			}
		}
		return false;
	}
}
