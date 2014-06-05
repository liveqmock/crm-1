package com.deppon.crm.module.interfaces.foss.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.esb.trans.EsbUtil;
import com.deppon.crm.module.client.sync.dao.IDataReceiveDao;
import com.deppon.crm.module.client.sync.domain.OrderRightInfo;
import com.deppon.crm.module.client.sync.domain.OrderRightRequest;
import com.deppon.crm.module.common.server.manager.IExpressPointBusinessDeptManager;
import com.deppon.crm.module.common.server.manager.IPilotCityManager;
import com.deppon.crm.module.common.server.service.IAreaAddressService;
import com.deppon.crm.module.common.server.service.ICityService;
import com.deppon.crm.module.common.server.service.ILadingstationDepartmentService;
import com.deppon.crm.module.common.server.service.IProvinceService;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
import com.deppon.crm.module.common.shared.domain.PilotCity;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.foss.EsbToCrmService;
import com.deppon.foss.express.RalationDetail;
import com.deppon.foss.express.SyncExpressCityRequest;
import com.deppon.foss.express.SyncExpressCityResponse;
import com.deppon.foss.express.SyncExpressDeptRelationRequest;
import com.deppon.foss.express.SyncExpressDeptRelationResponse;
import com.deppon.foss.express.SyncExpressDeptResult;
import com.deppon.crm.module.interfaces.foss.domain.CommException;
import com.deppon.crm.module.interfaces.foss.domain.HasUsedAmountInfo;
import com.deppon.crm.module.interfaces.foss.domain.ReceiveCreditUsedRequest;
import com.deppon.crm.module.interfaces.foss.domain.ReceiveCreditUsedResponse;
import com.deppon.crm.module.interfaces.foss.domain.ReturnVoucherPaymentResultRequest;
import com.deppon.crm.module.interfaces.foss.domain.UpdateOrderRequest;
import com.deppon.crm.module.interfaces.foss.domain.UpdateOrderResponse;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.MotorcadeInfo;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.ResultInfo;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.SyncMotorcadeRequest;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.SyncMotorcadeResponse;
import com.deppon.crm.module.interfaces.foss.domain.scatter.CreateScatterRequest;
import com.deppon.crm.module.interfaces.foss.domain.scatter.CreateScatterResponse;
import com.deppon.crm.module.interfaces.foss.domain.scatter.DealingResult;
import com.deppon.crm.module.interfaces.foss.domain.scatter.ScatterInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.DepartmentInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.DistrictInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.FailInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.OrganizationInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncDistrictRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncDistrictResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncOrganizationRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncOrganizationResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncSalesDepartmentRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncSalesDepartmentResponse;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * @作者：罗典
 * @描述：与FOSS对接的ESB异步接口
 * @时间：2012-11-8
 * */
public class EsbToCrmServiceImpl implements EsbToCrmService {
	// 订单mamager
	private IOrderManager orderManager;
	// 员工模块
	private IUserService userService;
	private IDataReceiveDao dataReceiveDao;
	// 部门服务
	private IDepartmentService departmentService;
	// 理赔模块
	private RecompenseManager recompenseManager;
	// 合同模块
	private IContractManager contractManager;
	// 行政城市服务
	private ICityService cityService;
	// 行政省份服务
	private IProvinceService provinceService;
	// 行政区域服务
	private IAreaAddressService areaAddressService;
	// 网点信息服务
	private ILadingstationDepartmentService ladingstationDepartmentService;
	// 试点城市、落地配城市
	private IPilotCityManager pilotCityManager;
	//
	private IExpressPointBusinessDeptManager expressPointBusinessDeptManager;
	//客户模块
	private IMemberManager memberManager;

	private static final Logger logger = Logger.getLogger(EsbToCrmServiceImpl.class);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-14
	 * @param createScatterrequest
	 * @return
	 * @throws CommException
	 */
	@Override
	public CreateScatterResponse createScatter(CreateScatterRequest request)
			throws CommException {
		CreateScatterResponse response = new CreateScatterResponse();
		int successCount=0;
		int failCount=0;
		for (ScatterInfo scatterInfo : request.getScatterInfos()) {
			DealingResult dealingResult=new DealingResult();
			try {
				Province province = null;
				String provinceName="";
				String cityName="";
				String areaName="";
				if (scatterInfo.getProvinceCode() != null
						&& !"".equals(scatterInfo.getProvinceCode())) {
					province = provinceService
							.queryVaildProvinceByNum(scatterInfo
									.getProvinceCode());
					if(null!=province){
						scatterInfo.setProvinceCode(province.getId());
						provinceName=province.getName();
					}

				}
				// 获取组织城市消息
				City city = null;
				if (scatterInfo.getCityCode() != null
						&& !"".equals(scatterInfo.getCityCode())) {
					city = cityService.queryVaildCityByNum(scatterInfo
							.getCityCode());
					if(null!=city){
						scatterInfo.setCityCode(city.getId());
						cityName=city.getName();
					}
				}
				// 获取组织区县消息
				Area area = null;
				if (scatterInfo.getAreaCode() != null
						&& !"".equals(scatterInfo.getAreaCode())) {
					area = areaAddressService.queryVaildAreaByNum(scatterInfo
							.getAreaCode());
					if(null!=area){
						scatterInfo.setAreaCode(area.getFid().toString());
						areaName=area.getName();
					}
				}
				ChannelCustomer customer=IntefacesTool.convertToChannelCustomer(scatterInfo,provinceName,cityName,areaName);
				String result=memberManager.createScatterCustomer(customer);
				if(null!=result&&!("".equals(result))){
					dealingResult.setFossId(scatterInfo.getFossId());
					dealingResult.setIfSuccess(true);
					dealingResult.setFailResean(result);
					successCount=successCount+1;
				}
			}
			//i18n.member.IsProcReditNull
			catch(MemberException e){
				dealingResult.setFossId(scatterInfo.getFossId());
				dealingResult.setIfSuccess(false);
				String errorMsg = IntefacesTool.getMessage("customer",
	    				e.getErrorCode());
				dealingResult.setFailResean(errorMsg);
				failCount=failCount+1;
				e.printStackTrace();
			}
			catch(CustomerException e){
				dealingResult.setFossId(scatterInfo.getFossId());
				dealingResult.setIfSuccess(false);
				String errorMsg = IntefacesTool.getMessage("customer",
	    				e.getErrorCode());
				dealingResult.setFailResean(errorMsg);
				failCount=failCount+1;
				e.printStackTrace();
			}
			catch (Exception e) {
				dealingResult.setFossId(scatterInfo.getFossId());
				dealingResult.setIfSuccess(false);
				dealingResult.setFailResean(e.getMessage());
				failCount=failCount+1;
				e.printStackTrace();
			}
			response.getDealingResult().add(dealingResult);
		}
		response.setFailCount(failCount);
		response.setSuccessCount(successCount);
		return response;
	}
	/**
	 * @作者：罗典
	 * @描述：同步FOSS营业组织信息
	 * @时间：2013-1-31
	 * */
	@Override
	public SyncOrganizationResponse syncOrganizea(SyncOrganizationRequest request) throws CommException{
		SyncOrganizationResponse response = new SyncOrganizationResponse();
		List<FailInfo> failInfos = response.getFailInfos();
		List<OrganizationInfo> organizationInfoLists = request.getOrganizationInfo();
		for(OrganizationInfo organizationInfo:organizationInfoLists){
			try{
			/*NullOrEmptyValidator.checkNull(organizationInfo.getProvCode(), "organizationInfo.getProvCode()");
			NullOrEmptyValidator.checkNull(organizationInfo.getCityCode(), "organizationInfo.getCityCode()");
			NullOrEmptyValidator.checkNull(organizationInfo.getCountyCode(), "organizationInfo.getCountyCode()");*/
			// 获取省份消息
			Province province = null;
			if(organizationInfo.getProvCode()!=null&&!"".equals(organizationInfo.getProvCode())){
				province = provinceService.queryVaildProvinceByNum(organizationInfo.getProvCode());
			}
			// 获取组织城市消息
			City city = null;
			if(organizationInfo.getCityCode()!=null&&!"".equals(organizationInfo.getCityCode())){
				city = cityService.queryVaildCityByNum(organizationInfo.getCityCode());
			}
			// 获取组织区县消息
			Area area = null;
			if(organizationInfo.getCountyCode()!=null&&!"".equals(organizationInfo.getCountyCode())){
				area = areaAddressService.queryVaildAreaByNum(organizationInfo.getCountyCode());
			}
			// 转换为CRM网点消息
			BussinessDept dept =IntefacesTool.convertToBussinessDept(organizationInfo);
			dept.setProvince(province);
			dept.setCity(city);
			dept.setRegion(area);
			Department department = departmentService.getDeptByStandardCode(organizationInfo.getUnifiedCode());
			if(department!=null){
				dept.setOrganizeId(Integer.parseInt(department.getId()));
				dept.setId(department.getId());
			}
			BussinessDept oldDept = ladingstationDepartmentService.getBusDeptByCode(organizationInfo.getUnifiedCode());
			// 可能存在有部分没有标杆编码的组织，则用FOSS的ID查找数据
			if(oldDept == null){
				oldDept = ladingstationDepartmentService.queryBussinessDeptByERPID(organizationInfo.getId());
			}
			if(oldDept == null){
				// 初始化成非营业部
				dept.setIfBussinessDept(false);
				ladingstationDepartmentService.saveLadingstationDepartment(dept);
			}else{
				 // 更新信息没有出发到达属性 默认为 NULL
				// 设置是否出发
				dept.setIfLeave(null);
				// 设置是否到达
				dept.setIfArrive(null);
				// 自提外发
				dept.setIfHomeDelivery(null);
				dept.setIfSelfDelivery(null);
				ladingstationDepartmentService.updateLadingstationDepartment(dept);
			}
			}
			catch(Exception e){
				FailInfo failInfo = new FailInfo();
				failInfo.setReason(e.getMessage());
				failInfo.setId(organizationInfo.getId());
				failInfos.add(failInfo);
				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * @作者：罗典
	 * @描述：同步FOSS是否出发到达营业部信息
	 * @时间：2013-1-31
	 * */
	@Override
	public SyncSalesDepartmentResponse syncStaArrDept(SyncSalesDepartmentRequest request) throws CommException{
		List<DepartmentInfo> departmentInfos = request.getDepts();
		SyncSalesDepartmentResponse response = new SyncSalesDepartmentResponse();
		List<FailInfo> failInfos = response.getFailInfos();
		for(DepartmentInfo departmentInfo : departmentInfos){
		try{
			NullOrEmptyValidator.checkNull(departmentInfo.getCode(), "departmentInfo.getCode()");
			// 根据标杆编码查询网点信息
			BussinessDept oldDept = ladingstationDepartmentService.getBusDeptByCode(departmentInfo.getUnifiedCode());
			if(oldDept != null){
				oldDept.setIfOpen(IntefacesTool.isTrue(departmentInfo.getActive()));
				// 设置是否出发
				oldDept.setIfLeave(IntefacesTool.isTrue(departmentInfo.getLeave()));
				// 设置是否到达
				oldDept.setIfArrive(IntefacesTool.isTrue(departmentInfo.getArrive()));
				// 设置是否送货上门
				oldDept.setIfHomeDelivery(IntefacesTool.isTrue(departmentInfo.getDelivery()));
				//是否可自提
				oldDept.setIfSelfDelivery(IntefacesTool.isTrue(departmentInfo.getPickupSelf()));

				// 设置标杆编码
				oldDept.setDeptCode(departmentInfo.getUnifiedCode());
				// 默认为营业部
				oldDept.setIfBussinessDept(true);
				// 设置派送区坐标编码
				oldDept.setDeliveryCoordinate(departmentInfo.getDeliveryCoordinate());
				ladingstationDepartmentService.updateLadingstationDepartment(oldDept);
			}else{
				FailInfo failInfo = new FailInfo();
				failInfo.setReason("standCode is not found "+departmentInfo.getCode());
				failInfo.setId(departmentInfo.getId());
				failInfos.add(failInfo);
			}
		}catch (CrmBusinessException e) {
			FailInfo failInfo = new FailInfo();
			failInfo.setReason(e.getMessage());
			failInfo.setId(departmentInfo.getId());
			failInfos.add(failInfo);
		}catch(Exception e){
			e.printStackTrace();
			throw new CommException(e.getMessage());
		}
		}
		return response;
	}

	/**
	 * @作者：王明明
	 * @描述：同步FOSS是否顶级车队--车队信息
	 * @时间：2013-7-29
	 * */
	public SyncMotorcadeResponse syncMotorcadeInfo(SyncMotorcadeRequest request)
			throws CommException {

		List<MotorcadeInfo> motorcadeInfos =  request.getMotorcadeInfo();
		SyncMotorcadeResponse response = new SyncMotorcadeResponse();
		List<ResultInfo> resultInfos = response.getResultInfos();
		ResultInfo resultInfo;
		for (MotorcadeInfo motorcadeInfo : motorcadeInfos) {
			 resultInfo = new ResultInfo();
			try {
				logger.info(motorcadeInfo.getUnifiedCode()+"同步-->"+motorcadeInfo.getIsTopMotorcade());
				NullOrEmptyValidator.checkNull(motorcadeInfo.getUnifiedCode(),
						"motorcadeInfo.getUnifiedCode()");
				// 根据标杆编码查询网点信息
				BussinessDept oldDept = ladingstationDepartmentService
						.getBusDeptByCode(motorcadeInfo.getUnifiedCode());

				if (oldDept != null) {
					if(oldDept.getModifyDate()!=null&&motorcadeInfo.getModifyTime().before(oldDept.getModifyDate())){
						resultInfo.setResultMessage("UnifiedCode Concurrent delay "
								+ motorcadeInfo.getUnifiedCode());
						resultInfo.setResultCode(EsbUtil.RESULT_CODE_STATUS_FAIL
								+ "");
						resultInfos.add(resultInfo);
						continue;
					}
					oldDept.setIsTopFleet(IntefacesTool.isTrue(motorcadeInfo
							.getIsTopMotorcade()));
					ladingstationDepartmentService
							.updateLadingstationDepartment(oldDept);
					resultInfo.setResultCode(EsbUtil.RESULT_CODE_STATUS_SUCCESS
							+ "");
				} else {
					resultInfo.setResultMessage("UnifiedCode is not found "
							+ motorcadeInfo.getUnifiedCode());
					resultInfo.setResultCode(EsbUtil.RESULT_CODE_STATUS_FAIL
							+ "");
				}
			}catch (CrmBusinessException e) {
				resultInfo.setResultMessage(e.getMessage());
				resultInfo.setResultCode(EsbUtil.RESULT_CODE_STATUS_FAIL+"");
			}catch(Exception e){
				throw new CommException(e.getMessage());
			}
			resultInfo.setId(motorcadeInfo.getId());
			resultInfos.add(resultInfo);
		}

		return response;
	}
	/**
	 * @作者：吴根斌
	 * @描述：同步营业部-快递点部映射关系
	 * @时间：2013-8-8
	 * */
	@Override
	public SyncExpressDeptRelationResponse sysExpressDeptRelation(
			SyncExpressDeptRelationRequest request) throws CommException {
		List<RalationDetail> ralationDetails = request.getRelations();
		SyncExpressDeptRelationResponse response = new SyncExpressDeptRelationResponse();
		List<SyncExpressDeptResult> syncExpressDeptResults = response
				.getResult();
		for (RalationDetail ralationDetail : ralationDetails) {
			try {
				NullOrEmptyValidator.checkNull(ralationDetail.getId(),
						"ralationDetail.getId()");
				NullOrEmptyValidator.checkNull(
						ralationDetail.getSaleDeptStandardNumber(),
						"ralationDetail.getSaleDeptStandardNumber()");
				NullOrEmptyValidator.checkNull(
						ralationDetail.getNewExDeptStandardNumber(),
						"ralationDetail.getNewExDeptStandardNumber()");

				if (ralationDetail.getOperateType().equals("3")) {
					expressPointBusinessDeptManager
							.deleteExpressPointBusinessDeptByDeptCode(
									ralationDetail.getSaleDeptStandardNumber(),
									ralationDetail.getNewExDeptStandardNumber());
				} else {
					ExpressPointBusinessDept oldExpressPointBusinessDept = expressPointBusinessDeptManager
							.getExpressPointBusinessDeptByDeptCode(ralationDetail
									.getSaleDeptStandardNumber());
					ExpressPointBusinessDept newExpressPointBusinessDept = IntefacesTool
							.changeRalationDetailToExpressPointBusinessDept(ralationDetail);
					if (null == oldExpressPointBusinessDept) {
						expressPointBusinessDeptManager
								.insertExpressPointBusinessDept(newExpressPointBusinessDept);
					} else {
						expressPointBusinessDeptManager
								.updateExpressPointBusinessDept(newExpressPointBusinessDept);
					}
				}
				SyncExpressDeptResult deptResult = new SyncExpressDeptResult();
				deptResult.setReason("");
				deptResult.setId(ralationDetail.getId());
				deptResult.setResult(1);
				syncExpressDeptResults.add(deptResult);
			} catch (CrmBusinessException e) {
				SyncExpressDeptResult deptResult = new SyncExpressDeptResult();
				deptResult.setReason(e.getMessage());
				deptResult.setId(ralationDetail.getId());
				deptResult.setResult(0);
				syncExpressDeptResults.add(deptResult);
			} catch (Exception e) {
				throw new CommException(e.getMessage());
			}
		}
		return response;
	}

	/**
	 * @作者：吴根斌
	 * @描述：同步FOSS试点城市和落地配城市
	 * @时间：2013-1-31
	 * */
	@Override
	public SyncExpressCityResponse syncExpressCity(
			SyncExpressCityRequest request) throws CommException {
		List<com.deppon.foss.express.CityInfo> cityInfos = request.getCitys();
		SyncExpressCityResponse response = new SyncExpressCityResponse();
		List<com.deppon.foss.express.FailInfo> failInfos = response.getFailInfos();
		for (com.deppon.foss.express.CityInfo cityInfo : cityInfos) {
			try {
				NullOrEmptyValidator.checkNull(cityInfo.getCode(), "cityInfo.getCode()");
				PilotCity condition = new PilotCity();
				condition.setCityCode(cityInfo.getCode());
				PilotCity newPilotCity = IntefacesTool
						.changeCityInfoToPilotCity(cityInfo);
				PilotCity oldPilotCity = pilotCityManager.getPilotCity(condition);
				if (null == oldPilotCity) {
					pilotCityManager.insertPilotCity(newPilotCity);
				} else {
					pilotCityManager.updatePilotCity(newPilotCity);
				}
			} catch(CrmBusinessException e){
				com.deppon.foss.express.FailInfo failInfo = new com.deppon.foss.express.FailInfo();
				failInfo.setReason(e.getMessage());
				failInfo.setId(cityInfo.getId());
				failInfos.add(failInfo);
			}catch(Exception e){
				throw new CommException(e.getMessage());
			}
		}
		return response;
	}

	/**
	 * @作者：罗典
	 * @描述：同步FOSS行政区域信息
	 * @时间：2013-1-31
	 * */
	@Override
	public SyncDistrictResponse syncAdminArea(SyncDistrictRequest request) throws CommException{
		List<DistrictInfo> DistrictInfos = request.getDistrictInfo();
		SyncDistrictResponse response = new SyncDistrictResponse();
		List<FailInfo> failInfos = response.getFailInfos();
		for(DistrictInfo districtInfo:DistrictInfos){
		try{
			// 如果是省份
			if(IntefacesTool.DISTRICT_PROVINCE.equals(districtInfo.getDegree())){
				Province province =IntefacesTool.convertToProvince(districtInfo);
				//存在有效的此省份信息则修改，否则新增
				Province oldProvince = provinceService.queryVaildProvinceByNum(districtInfo.getCode());
				if(oldProvince != null){
					province.setFid(oldProvince.getFid());
					provinceService.updateProvince(province);
				}else{
					// 转换新的省份信息，后新增
					provinceService.insertProvince(province);
				}
			}
			// 如果是城市
			else if(IntefacesTool.DISTRICT_CITY.equals(districtInfo.getDegree())){
				// 通过编码获取上级省份信息
				Province province = provinceService.queryVaildProvinceByNum(districtInfo.getParentDistrictCode());
				NullOrEmptyValidator.checkNull(province,districtInfo.getParentDistrictCode(),"0041");
				// 通过编码查询有效城市信息
				City city = IntefacesTool.convertToCity(districtInfo);
				City oldCity = cityService.queryVaildCityByNum(districtInfo.getCode());
				if(oldCity!=null){
					if(province != null){
						city.setFid(oldCity.getFid());
						city.setProvince(province);
						city.setProvinceId(Integer.parseInt(province.getId()));
						city.setProvinceName(province.getName());
					}
					cityService.updateCity(city);
				}else{
					// 转换成新的城市信息
					city.setProvince(province);
					city.setProvinceId(Integer.parseInt(province.getId()));
					city.setProvinceName(province.getName());
					cityService.insertCity(city);
				}
			}
			// 如果是区县
			else if(IntefacesTool.DISTRICT_COUNTY.equals(districtInfo.getDegree())){
				// 通过编码获取上级城市信息
				City city = cityService.queryVaildCityByNum(districtInfo.getParentDistrictCode());
				NullOrEmptyValidator.checkNull(city,districtInfo.getParentDistrictCode(),"0042");
				Area area = IntefacesTool.convertToArea(districtInfo);
				Area oldArea = areaAddressService.queryVaildAreaByNum(districtInfo.getCode());
				if(oldArea != null){
					if(city != null){
						area.setFid(oldArea.getFid());
						area.setCity(city);
						area.setCityId(Integer.parseInt(city.getId()));
						area.setCityName(city.getName());
						area.setProvinceId(city.getProvinceId());
					}
					areaAddressService.updateArea(area);
				}else{
					area.setCity(city);
					area.setCityId(Integer.parseInt(city.getId()));
					area.setCityName(city.getName());
					area.setProvinceId(city.getProvinceId());
					areaAddressService.insertArea(area);
				}
			}else{
				throw new CrmBusinessException("this info is not expected"+districtInfo.getDegree());
			}

		}catch(CrmBusinessException e){
			FailInfo failInfo = new FailInfo();
			failInfo.setReason(e.getMessage());
			failInfo.setId(districtInfo.getId());
			failInfos.add(failInfo);
		}catch(Exception e){
			throw new CommException(e.getMessage());
		}
	}
		return response;
	}
	/**
	 * @作者：罗典
	 * @描述：通知理赔付款状态
	 * @时间：2012-11-8
	 * */
	public void claimsState(ReturnVoucherPaymentResultRequest claimsStateRequest)
			throws CommException {
		try{
			if (claimsStateRequest.isPayResult()) {
				// 通过
				recompenseManager.paymentApprove(claimsStateRequest.getWaybillNum());
			} else {
				// 不通过
				recompenseManager.paymentRefuse(claimsStateRequest.getWaybillNum());
			}
		}catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("recompense",
					e.getErrorCode());
			throw new CommException(errorMsg);
		} catch(Exception e){
			throw new CommException(e.getMessage(),e);
		}
	}

	/**
	 * @作者：罗典
	 * @描述：修改订单状态
	 * @时间：2012-11-8
	 * */
	public UpdateOrderResponse updateOrderStatus(
			UpdateOrderRequest updateOrderRequest) throws CommException {
		try {
			// 订单编号
			String orderNumber = updateOrderRequest.getOrderNumber();
			// 运单编号
			String waybillNum = updateOrderRequest.getWaybillNumber();
			// 操作人工号
			String oprateUserNum = updateOrderRequest.getOprateUserNum();
			// 操作部门标杆编码
			String opreateDeptCode = updateOrderRequest.getOprateDeptCode();
			// 司机姓名
			String driverName = updateOrderRequest.getDriverName();
			// 司机手机
			String driverMobile = updateOrderRequest.getDriverMobile()==null?"":updateOrderRequest.getDriverMobile();
			// 备注信息
			String backInfo = updateOrderRequest.getBackInfo();
			// 验证订单为空
			IntefacesTool.validateStringNull(orderNumber, "0016", orderNumber);
			// 验证操作部门为空
			IntefacesTool.validateStringNull(opreateDeptCode, "0023");
			// 验证操作人为空
			IntefacesTool.validateStringNull(oprateUserNum, "0019");
			// 验证货物状态为空
			IntefacesTool.validateStringNull(
					updateOrderRequest.getGoodsStatus(), "0039");
			// 货物状态
			String newStatus = updateOrderRequest.getGoodsStatus();
			Order dpOrder = null;
			// 传入的订单长度为24位时，则为ERP订单中的ID
			// 加入try，如果調用orderManager业务逻辑出错ERP就不需要再次重发
			try {
				if (orderNumber.length() == 28 || orderNumber.length() == 32) {
					List<Order> orderList = orderManager
							.getOrderByErpId(orderNumber);
					if (orderList != null && orderList.size() > 0) {
						dpOrder = orderList.get(0);
					}
				} else {
					dpOrder = orderManager.getOrderByNumber(orderNumber);
				}
			} catch (Exception e) {
				throw new CrmBusinessException(
						ErrorMessageCode.EXCEPTION_DYNAMIC, e);
			}
			IntefacesTool.validateStringNull(dpOrder, "0012", orderNumber);

			// 订单当前状态
			String oldStatus = dpOrder.getOrderStatus();
			// 作为参数传入订单模块订单
			Order paramOrder = new Order();
			paramOrder.setId(dpOrder.getId());
			paramOrder.setContactName(dpOrder.getContactName());
			paramOrder.setContactMobile(dpOrder.getContactMobile());
			// 获取用户信息
			User user = new User();
			user.setLoginName(oprateUserNum);
			List<User> userList = userService.queryAll(user);
			Employee employee = new Employee();
			if (userList.size() > 0) {
				user = userList.get(0);
				employee = user.getEmpCode();
			} else {
				employee.setEmpCode(oprateUserNum);
				employee.setEmpName(oprateUserNum);
			}
			Department dept = departmentService
					.getDeptByStandardCode(opreateDeptCode);
			if (dept == null) {
				dept = new Department();
				dept.setDeptName(opreateDeptCode);
			}
			employee.setDeptId(dept);
			user.setEmpCode(employee);
			String errorDesc = "[" + orderNumber
					+ IntefacesTool.getOrderStatusDesc(oldStatus) + "]";
			// 异常信息，如果有异常则记录异常，反之则不记录
			String errorInfo = "";
			// 操作内容日志记录
			String operContent = "订单状态由"
					+ IntefacesTool.getOrderStatusDesc(oldStatus) + "更改为"
					+ IntefacesTool.getOrderStatusDesc(newStatus);
			if (waybillNum != null && !waybillNum.equals("")) {
				operContent += waybillNum;
			}
			// 操作类型
			String operType = "";
			// 新订单状态
			paramOrder.setOrderStatus(newStatus);
			// 只有已约车，接货中订单才能修改为接货中!
			if (newStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_RECEIPTING
							+ errorDesc;
				}
				operType = IntefacesTool.ORDER_OPERATION_PICK_GOODS;
				operContent += "(司机姓名:" + driverName + ",司机手机:" + driverMobile
						+ ")";
			}
			// 只有已受理和已退回才能修改为已约车
			else if (newStatus.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_ACCEPT)
						&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_SHOUTCAR
							+ errorDesc;
				}
				operType = IntefacesTool.ORDER_OPERATION_BOOK_VEHICLE;
			}
			// 只有已约车，接货中订单才能修改为已退回!
			else if (newStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_GOBACK
							+ errorDesc;
				}
				operType = IntefacesTool.ORDER_OPERATION_GOBACK;
				// 退回需记录反馈信息
				if(backInfo!=null){
					operContent += backInfo + "/";
				}

			}
			// 只有接货中，已退回，已受理，已开单订单才能修改为已开单!
			else if (newStatus.equals(IntefacesTool.ORDER_STATUS_GOT)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)
						&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)
						&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
						&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_ACCEPT)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_GOT + errorDesc;
				}
				if (waybillNum == null || waybillNum.equals("")) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_NOWAYBILL
							+ errorDesc;
				}
				paramOrder.setWaybillNumber(waybillNum);
				operType = IntefacesTool.ORDER_OPERATION_GOT;
			}
			// 只有待受理，已约车，已开单,接货中,正常签收，异常签收，已退回才能修改为已受理!
			else if (newStatus.equals(IntefacesTool.ORDER_STATUS_ACCEPT)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_WAIT_ACCEPT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)
						&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)
						&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_ACCEPT
							+ errorDesc;
				}
				operType = IntefacesTool.ORDER_OPERATION_WAYBILL_CANCEL;
				// 运单作废需记录反馈信息
				if(backInfo!=null){
					operContent += backInfo + "/";
				}
				// 清空运单信息
				paramOrder.setWaybillNumber("");
			}
			// 只有已开单，运输中才能修改为运输中!
			else if (newStatus.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_INTRANSIT
							+ errorDesc;
				}
				operType = IntefacesTool.ORDER_OPERATION_TRANSIT;
			}
			// 签收成功的可修改状态为：已开单，运输中，正常签收，异常签收
			else if (newStatus.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)
						&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_SINGSUCCESS
							+ errorDesc;
				}
				operType = IntefacesTool.ORDER_OPERATION_SING;
			}
			// 签收失败的可修改状态为：已开单，运输中，正常签收，异常签收
			else if (newStatus.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)) {
				if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)
						&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)
						&& !oldStatus
								.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)) {
					errorInfo = IntefacesTool.ORDER_EXCEPTION_SIGNFAIL
							+ errorDesc;
				}
				operType = IntefacesTool.ORDER_OPERATION_SING;
				// 异常签收需记录反馈信息

				if(backInfo!=null){
					paramOrder.setFeedbackInfo((dpOrder.getFeedbackInfo()==null?"":dpOrder.getFeedbackInfo())
							+ backInfo+ "/");
				}
			}
			// 不允许其他状态的更改
			else {
				errorInfo = IntefacesTool.ORDER_EXCEPTION_STATUSERROR
						+ orderNumber + newStatus;
			}
			boolean isSuccess = false;
			if ("".equals(errorInfo)) {
				isSuccess = orderManager.updateChannelOrder(user, paramOrder,
						operContent, operType,updateOrderRequest.getEarningDeptStandardCode());
			}
			UpdateOrderResponse response = new UpdateOrderResponse();
			response.setWaybillNumber(orderNumber);
			response.setErrorInfo(errorInfo);
			response.setResult(isSuccess);
			return response;
		} catch (Exception e) {
			throw new CommException(e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：同步合同月结最长账龄日期
	 * @时间：2012-01-11
	 * */
	@Override
	public ReceiveCreditUsedResponse receiveCreditUsed(
			ReceiveCreditUsedRequest request) throws CommException {
		List<HasUsedAmountInfo> infoLists = request.getHasUsedAmountList();
		for(HasUsedAmountInfo amountInfo : infoLists){
			ContractDebtDay contractDebtDay  = IntefacesTool.convertToContractDebtDay(amountInfo);
			contractManager.saveContractDebt(contractDebtDay);
		}
		// FOSS那边反馈不需要CRM的响应信息(原因是要了没用，但又不想需求变更)
		return null;
	}

	/**
	 * @作者：罗典
	 * @描述：同步网点信息
	 * @时间：2012-11-8
	 * *//*
	public void receiveSiteSync(SiteReceiveRequest request)
			throws CommException {
		dataReceiveDao.saveSiteReceive(request);
	}*/

	/**
	 * @作者：罗典
	 * @描述：同步组织权限
	 * @时间：2012-11-8
	 * */
	public void orderRightSync(OrderRightRequest request) throws CommException {
		try {
			NullOrEmptyValidator
					.checkNull(request, "OrderRightRequest", "1007");
			NullOrEmptyValidator.checkNull(request.getOrderRightInfo(),
					"OrderRightRequest.getOrderRightInfo()", "1007");
			OrderRightInfo info = request.getOrderRightInfo();
			if (IntefacesTool.SYNC_ORDERRIGHT_NEW.equals(info.getOperateType())) {
				dataReceiveDao.saveOrderRight(info);
			} else if (IntefacesTool.SYNC_ORDERRIGHT_CANCEL.equals(info
					.getOperateType())) {
				dataReceiveDao.cancelOrderRight(info);
			} else {
				throw new CommException(
						"crm check fail: OrderRightInfo.operateType is invalid");
			}
		} catch (CrmBusinessException e) {
			throw new CommException(e.getMessage());
		}
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public IDataReceiveDao getDataReceiveDao() {
		return dataReceiveDao;
	}

	public void setDataReceiveDao(IDataReceiveDao dataReceiveDao) {
		this.dataReceiveDao = dataReceiveDao;
	}

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IContractManager getContractManager() {
		return contractManager;
	}

	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(IProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	public IAreaAddressService getAreaAddressService() {
		return areaAddressService;
	}

	public void setAreaAddressService(IAreaAddressService areaAddressService) {
		this.areaAddressService = areaAddressService;
	}

	public ILadingstationDepartmentService getLadingstationDepartmentService() {
		return ladingstationDepartmentService;
	}

	public void setLadingstationDepartmentService(
			ILadingstationDepartmentService ladingstationDepartmentService) {
		this.ladingstationDepartmentService = ladingstationDepartmentService;
	}

	public IPilotCityManager getPilotCityManager() {
		return pilotCityManager;
	}

	public void setPilotCityManager(IPilotCityManager pilotCityManager) {
		this.pilotCityManager = pilotCityManager;
	}

	public IExpressPointBusinessDeptManager getExpressPointBusinessDeptManager() {
		return expressPointBusinessDeptManager;
	}

	public void setExpressPointBusinessDeptManager(
			IExpressPointBusinessDeptManager expressPointBusinessDeptManager) {
		this.expressPointBusinessDeptManager = expressPointBusinessDeptManager;
	}
	public IMemberManager getMemberManager() {
		return memberManager;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}




}
