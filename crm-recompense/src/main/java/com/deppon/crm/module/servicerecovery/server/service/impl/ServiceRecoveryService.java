package com.deppon.crm.module.servicerecovery.server.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.esb.IEsbToFossAsyOperate;
import com.deppon.crm.module.client.fin.IDepartmentOperate;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.workflow.IPaymentApplyOperate;
import com.deppon.crm.module.client.workflow.IRecompenseApplyOperate;
import com.deppon.crm.module.client.workflow.domain.ContractNounInfo;
import com.deppon.crm.module.client.workflow.domain.ServiceRecoveryInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;

import com.deppon.crm.module.servicerecovery.server.dao.IServiceRecoveryDao;
import com.deppon.crm.module.servicerecovery.server.service.IServiceRecoveryService;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.crm.module.servicerecovery.shared.exception.ServiceRecoveryException;
import com.deppon.crm.module.servicerecovery.shared.exception.ServiceRecoveryExceptionType;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * @description：服务补救service接口实现类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:16:13
 */
public class ServiceRecoveryService implements IServiceRecoveryService {
	private IServiceRecoveryDao serviceRecoveryDao;
	// 运单相关接口
	private IWaybillOperate waybillOperate;
	private IEsbToFossAsyOperate esbToFossAsyOperate;
	private IPaymentApplyOperate paymentApplyOperate;


	public IRecompenseApplyOperate getRecompenseApplyOperate() {
		return recompenseApplyOperate;
	}

	public void setRecompenseApplyOperate(
			IRecompenseApplyOperate recompenseApplyOperate) {
		this.recompenseApplyOperate = recompenseApplyOperate;
	}

	public IDepartmentOperate getDepartmentOperate() {
		return departmentOperate;
	}

	public void setDepartmentOperate(IDepartmentOperate departmentOperate) {
		this.departmentOperate = departmentOperate;
	}

	private IRecompenseApplyOperate recompenseApplyOperate;

	private IDepartmentOperate departmentOperate;

	public IServiceRecoveryDao getServiceRecoveryDao() {
		return serviceRecoveryDao;
	}

	public void setServiceRecoveryDao(IServiceRecoveryDao serviceRecoveryDao) {
		this.serviceRecoveryDao = serviceRecoveryDao;
	}
	//根据运单号得到运单
	@Override
	public Waybill findWaybillByNum(String waybillNumber) {

		try {
			// 从接口读取数据
			FossWaybillInfo waybillInfo = waybillOperate
					.queryWaybillInfo(waybillNumber);
			if (null == waybillInfo) {
				ServiceRecoveryException re = new ServiceRecoveryException(
						ServiceRecoveryExceptionType.WAYBILL_NOT_EXIST_EXCEPTION);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};
			}
			Waybill waybill = new Waybill();
			waybill.setWaybillNumber(waybillNumber);
			// 发货联系人
			waybill.setSenderContactName(waybillInfo.getSender());
			// 收货联系人
			waybill.setConsigneeContactName(waybillInfo.getConsignee());
			// 发货客户编号
			waybill.setSenderCustomerNum(waybillInfo.getSenderNumber());
			// 收货客户编号
			waybill.setConsigneeCustomerNum(waybillInfo.getConsigneeNumber());
			// 到达部门标杆编码
			waybill.setConsigneeDeptStandardCode(waybillInfo
					.getLadingStationNumber());
			// 收货部门标杆编码
			waybill.setSenderDeptStandardCode(waybillInfo
					.getReceiveDeptNumber());
			// 配载部门标杆编码
			waybill.setStowageDeptStandardCode(waybillInfo.getStowageDept());

			waybill.setTotalPackage(waybillInfo.getPieces());
			// 纯运费
			waybill.setTotalCharge(waybillInfo.getPublishCharge());
			waybill.setLaborRebate(waybillInfo.getLaborRebate());
			//是否签收
			waybill.setIsSigned(waybillInfo.getIsSigned());
			//是否正常签收
			waybill.setIsNormalSigned(waybillInfo.getIsNormalSigned());
			
			// 出库日期//TODO 改为第一次签收时间
			waybill.setSignedDate(waybillInfo.getFirstSignedDate());
			// 运输方式

			// 精准城运
			if ("AF".equals(waybillInfo.getTranProperty())) {
				waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR);
			}
			// 精准空运
			if ("FSF".equals(waybillInfo.getTranProperty())) {
				waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY);
			}
			/*
			 * 运输性质(三级产品)： 精准汽运（长途）LRF 精准卡航 FLF 精准汽运（短途） SRF 精准城运 FSF 汽运偏线 PLF
			 * 精准空运 AF 整车 FV
			 */
			if (BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_LRF
					.equals(waybillInfo.getTranProperty())) {
				waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_LRF);
			}
			if (BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_FLF
					.equals(waybillInfo.getTranProperty())) {
				waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_FLF);
			}
			if (BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_SRF
					.equals(waybillInfo.getTranProperty())) {
				waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_SRF);
			}
			if (BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_PLF
					.equals(waybillInfo.getTranProperty())) {
				waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_PLF);
			}
			if (BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_FV
					.equals(waybillInfo.getTranProperty())) {
				waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_FV);
			}
			if (null == waybill.getTranType()) {
				waybill.setTranType(waybillInfo.getTranProperty());
			}
			// 付款方式
			// CH 现金
			// CD 银行卡
			// TT 电汇
			// NT 支票
			// OL 网上支付
			// CT 月结
			// DT 临时欠款
			// FC 到付
			if (waybillInfo.getPayment().equals("CH")) {
				waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_CASH);
			}
			if (waybillInfo.getPayment().equals("CD")) {
				waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_CARD);
			}
			if (waybillInfo.getPayment().equals("CT")) {
				waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_MONTHLY);
			}
			if (waybillInfo.getPayment().equals("DT")) {
				waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_ARREARS);
			}
			if (waybillInfo.getPayment().equals("FC")) {
				waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_ARRIVE);
			}
			if (waybillInfo.getPayment().equals("OL")) {
				waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_ONLINE);
			}
		
			waybill.setWaybillAmount(waybillInfo.getTotalCharge());
			// 开单金额（不含代收货款、劳务费）
			if (null != waybill.getWaybillAmount()) {
				if (null != waybillInfo.getLaborRebate()) {
					waybill.setWaybillAmount(waybill.getWaybillAmount()
							.subtract(waybillInfo.getLaborRebate()));
				}
				if (null != waybillInfo.getRefund()) {
					waybill.setWaybillAmount(waybill.getWaybillAmount()
							.subtract(waybillInfo.getRefund()));
				}
			}

			return waybill;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}

	}

	/**
	 * 
	 * @description ：根据运单号查有效的服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            waybillNumber :运单号
	 * @date 2012-10-29 下午3:34:49
	 * @return ServiceRecovery
	 */
	//根据运单号得到有效的服务补救
	@Override
	public ServiceRecovery findValidServiceRecoveryByNum(String waybillNumber) {
		return serviceRecoveryDao.findValidServiceRecoveryByNum(waybillNumber);

	}

	/**
	 * 
	 * @description ：根据运单号查询时效差错记录
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            waybillNumber :运单号
	 * @date 2012-10-29 下午3:46:09
	 * @return DelayAccident 时效差错实体
	 */
	@Override
	public Boolean findDelayAccidentByNum(String waybillNumber) {
		try {

			return recompenseApplyOperate.checkOaGoodDelayError(waybillNumber);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description ：保存服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	@Override
	public ServiceRecovery addServiceRecovery(ServiceRecovery serviceRecovery) {
		return serviceRecoveryDao.addServiceRecovery(serviceRecovery);
	}

	/**
	 * 
	 * @description ：根据条件查询服务补救列表
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<ServiceRecovery> 服务补救列表
	 */

	@Override
	public List<ServiceRecovery> searchServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		return serviceRecoveryDao.searchServiceRecoveryByCondition(condition);

	}

	/**
	 * 
	 * @description ：根据条件统计总条数
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:47:47
	 * @return int 总条数
	 */
	@Override
	public int countServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		return serviceRecoveryDao.countServiceRecoveryByCondition(condition);

	}

	/**
	 * 
	 * @description ：根据条件导出服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition :服务补救搜索条件
	 * @date 2012-10-29 下午2:42:06
	 * @return List<ServiceRecovery> 服务补救列表
	 */
	@Override
	public List<ServiceRecovery> exportServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		return serviceRecoveryDao.exportServiceRecoveryByCondition(condition);

	}

	/**
	 * 
	 * @description ：根据ID查询服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            serviceRecoveryId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return ServiceRecovery 服务补救实体
	 */
	@Override
	public ServiceRecovery findServiceRecoveryById(String serviceRecoveryId) {
		return serviceRecoveryDao.getServiceRecoveryById(serviceRecoveryId);

	}

	@Override
	public String submitServiceRecoveryWorkflow(ServiceRecovery serviceRecovery) {
		// 封装给接口的数据
		ServiceRecoveryInfo serviceRecoveryInfo = new ServiceRecoveryInfo();
		serviceRecoveryInfo.setApplyName(serviceRecovery.getApplicantName());
		serviceRecoveryInfo.setApplyPersonCode(serviceRecovery.getEmpCode());
		serviceRecoveryInfo.setDeductiblecategory(serviceRecovery
				.getReductionType());
		serviceRecoveryInfo.setHandler(serviceRecovery.getOperatorName());
		serviceRecoveryInfo.setLocalFinical(serviceRecovery
				.getFinanceDeptCode());
		serviceRecoveryInfo.setPosition(serviceRecovery.getPosition());
		serviceRecoveryInfo.setReason(serviceRecovery.getRecoveryReason());
		serviceRecoveryInfo.setSubsidiary(serviceRecovery.getSubsidiary());
		serviceRecoveryInfo.setWaybillNo(serviceRecovery.getWaybillNumber());
		serviceRecoveryInfo.setDeductibleMoney(serviceRecovery
				.getReductionAmount());

		// 附件
		List<ContractNounInfo> files = new ArrayList<ContractNounInfo>();
		List<FileInfo> fileInfoList = serviceRecovery.getFileInfoList();
		for (FileInfo fileInfo : fileInfoList) {
			ContractNounInfo ifile = new ContractNounInfo();
			ifile.setFileName(fileInfo.getFileName());
			ifile.setSavePath(fileInfo.getSavePath());
			ifile.setContractId("");
			ifile.setContractName("");
			files.add(ifile);
		}
		serviceRecoveryInfo.setFile(files);

		try {
			// 提交工作流
			return recompenseApplyOperate
					.applyServiceRecovery(serviceRecoveryInfo);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	@Override
	public void updateServiceRecoveryWorkflowNum(String id, String workflowNum, String workflowNo) {
		serviceRecoveryDao.updateServiceRecoveryWorkflowNum(id, workflowNum, workflowNo);
	}

	/**
	 * 
	 * @description ：根据工作流号查询服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            serviceRecoveryId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return ServiceRecovery 服务补救实体
	 */
	public ServiceRecovery getServiceRecoveryByOaWorkFlowNum(
			String oaWorkFlowNum) {
		return serviceRecoveryDao
				.getServiceRecoveryByOaWorkflowNum(oaWorkFlowNum);
	}

	/**
	 * 
	 * 
	 * @Description: 生成FOSS应付单
	 * @author 华龙
	 * @param serviceRecovery
	 * @return void
	 * @date 2012-11-7 下午4:07:24
	 * @version V1.0
	 */
	@Override
	public boolean submitServiceRecoveryPayment(ServiceRecovery serviceRecovery) {
		/**
		 * // 理赔类型： 异常签收理赔、丢货理赔 protected int claimType; // 理赔方式: 正常理赔、快速理赔、在线理赔
		 * protected int claimWay; // 业务类型: 理赔、退运费、服务补救 protected int
		 * businessType; // 部门标杆编码
		 * 
		 * protected String deptNo; // 客户编码 protected String custNo;
		 * 
		 * protected BigDecimal claimMoney; // 运单号 protected String billNo;
		 * 
		 * protected String creatorNo; // 责任信息
		 * 
		 * @XmlElement(required = true)
		 */
		ClaimsPayBillGenerateRequest billGenerateRequest = new ClaimsPayBillGenerateRequest();
		billGenerateRequest.setClaimType("0");// 理赔类型
		billGenerateRequest.setClaimWay("0");// 理赔方式
		billGenerateRequest.setClaimMoney(new BigDecimal(serviceRecovery
				.getReductionAmount())); // 金额
		billGenerateRequest.setBusinessType("3");// 业务类型
		billGenerateRequest.setBillNo(serviceRecovery.getWaybillNumber());// 运单号
		billGenerateRequest.setCreatorNo(serviceRecovery.getApplicant());// 创建人工号
		billGenerateRequest.setCustNo(serviceRecovery.getCustomerNum());// 客户编码
		billGenerateRequest.setDeptNo(serviceRecovery.getApplyDept());// 部门标杆编码
		billGenerateRequest.setPaymentCategory("");
		try {
		return	getPaymentApplyOperate().claimsApbill(billGenerateRequest);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description ：更新服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            serviceRecoveryId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return ServiceRecovery 服务补救实体
	 */
	@Override
	public void updateServiceRecovery(ServiceRecovery serviceRecovery) {
		serviceRecoveryDao.updateServiceRecovery(serviceRecovery);
	}

	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
	}

	/**
	 * 
	 * @Description: 通过部门标杆编码查询子公司
	 * @author huangzhanming
	 * @param standardCode
	 * @return
	 * @return String
	 * @date 2012-11-15 下午1:41:33
	 * @version V1.0
	 */
	@Override
	public String getSubsidiaryByDeptStandardCode(String standardCode) {
		try {
			return departmentOperate.querySubCompanyNameByCode(standardCode);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	public IEsbToFossAsyOperate getEsbToFossAsyOperate() {
		return esbToFossAsyOperate;
	}

	public void setEsbToFossAsyOperate(IEsbToFossAsyOperate esbToFossAsyOperate) {
		this.esbToFossAsyOperate = esbToFossAsyOperate;
	}

	/**
	 * <p>
	 * Description:paymentApplyOperate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-6-6
	 */
	public IPaymentApplyOperate getPaymentApplyOperate() {
		return paymentApplyOperate;
	}

	/**
	 * <p>
	 * Description:paymentApplyOperate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-6-6
	 */
	public void setPaymentApplyOperate(IPaymentApplyOperate paymentApplyOperate) {
		this.paymentApplyOperate = paymentApplyOperate;
	}

	@Override
	public ServiceRecovery findServiceRecoveryByWorkflowNo(String workflowNo) {
		return serviceRecoveryDao.findServiceRecoveryByWorkflowNo(workflowNo);
	}

}
