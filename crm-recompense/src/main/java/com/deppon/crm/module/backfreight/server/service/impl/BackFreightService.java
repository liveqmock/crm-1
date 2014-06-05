package com.deppon.crm.module.backfreight.server.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.backfreight.server.dao.IBackFreightDao;
import com.deppon.crm.module.backfreight.server.service.IBackFreightService;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.esb.IEsbToFossAsyOperate;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.workflow.IPaymentApplyOperate;
import com.deppon.crm.module.client.workflow.IRecompenseApplyOperate;
import com.deppon.crm.module.client.workflow.domain.BackFreightInfo;
import com.deppon.crm.module.client.workflow.domain.ContractNounInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * @description：退运费service接口实现类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:21:00
 */
public class BackFreightService implements IBackFreightService {
	private IBackFreightDao backFreightDao;
	private IWaybillOperate waybillOperate;
	private IRecompenseApplyOperate recompenseApplyOperate;
	private IEsbToFossAsyOperate esbToFossAsyOperate;
	// 付款工作流相关接口
	private IPaymentApplyOperate paymentApplyOperate;


	public IRecompenseApplyOperate getRecompenseApplyOperate() {
		return recompenseApplyOperate;
	}

	public void setRecompenseApplyOperate(
			IRecompenseApplyOperate recompenseApplyOperate) {
		this.recompenseApplyOperate = recompenseApplyOperate;
	}

	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
	}

	public IBackFreightDao getBackFreightDao() {
		return backFreightDao;
	}

	public void setBackFreightDao(IBackFreightDao backFreightDao) {
		this.backFreightDao = backFreightDao;
	}

	/**
	 * 
	 * @description ：根据运单号查有效的服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            waybillNumber :运单号
	 * @date 2012-10-29 下午3:34:49
	 * @return BackFreight
	 */
	public BackFreight findValidBackFreightByNum(String waybillNumber) {
		return backFreightDao.findValidBackFreightByNum(waybillNumber);
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
	public Boolean findDelayAccidentByNum(String waybillNumber) {
		try {
			return recompenseApplyOperate.checkOaGoodDelayError(waybillNumber);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @description ：保存服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :BackFreight BackFreight：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	public BackFreight addBackFreight(BackFreight backFreight) {
		return backFreightDao.addBackFreight(backFreight);
	}

	/**
	 * 
	 * @description ：根据条件查询服务补救列表
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<BackFreight> 服务补救列表
	 */

	public List<BackFreight> searchBackFreightByCondition(
			BackFreightSearchCondition condition) {
		return backFreightDao.searchBackFreightByCondition(condition);
	}

	/**
	 * 
	 * @description ：根据条件统计总条数
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:47:47
	 * @return int 总条数
	 */
	public int countBackFreightByCondition(BackFreightSearchCondition condition) {
		return backFreightDao.countBackFreightByCondition(condition);
	}

	/**
	 * 
	 * @description ：根据条件导出服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition :服务补救搜索条件
	 * @date 2012-10-29 下午2:42:06
	 * @return List<BackFreight> 服务补救列表
	 */
	public List<BackFreight> exportBackFreightByCondition(
			BackFreightSearchCondition condition) {
		return backFreightDao.exportBackFreightByCondition(condition);
	}

	/**
	 * 
	 * @description ：根据ID查询服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 服务补救实体
	 */
	public BackFreight getBackFreightById(String backFreightId) {
		return backFreightDao.getBackFreightById(backFreightId);
	}

	/**
	 * 
	 * @description ：根据运单号查询坏账
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            waybillNumber :运单号
	 * @date 2012-10-30 上午11:30:34
	 * @return BadDebt 坏账
	 */
	public Boolean findBadDebtByNum(String waybillNumber) {
		try {
			return recompenseApplyOperate.checkBadDebt(waybillNumber);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};		}
	
	}

	/**
	 * @description ：根据工作流号查询退运费
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
	 */
	@Override
	public BackFreight getBackFreightByOaWorkFlowNum(String oaWorkflowNum) {
		return backFreightDao.findBackFreightByOaWorkflowNum(oaWorkflowNum);
	}

	/**
	 * @description ：更新退运费
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreight
	 *            backFreight
	 * @date 2012-10-29 下午2:42:14
	 * @return void
	 */
	@Override
	public void updateBackFreight(BackFreight backFreight) {
		backFreightDao.updateBackFreight(backFreight);

	}

	/**
	 * 
	 * @Description: 生成FOSS应付单
	 * @author 华龙
	 * @param serviceRecovery
	 * @return void
	 * @date 2012-11-7 下午4:07:24
	 * @version V1.0
	 */
	public boolean submitBackFreightPayment(BackFreight backFreight) {
//		// 理赔类型： 1,异常签收理赔、2,丢货理赔,3，无
//		protected int claimType;
//		// 理赔方式: 1，正常理赔,2，快速理赔,3，在线理赔，4，无
//		protected int claimWay;
//		// 业务类型: 1，理赔、2，退运费、3，服务补救
		ClaimsPayBillGenerateRequest billGenerateRequest=new ClaimsPayBillGenerateRequest();
		billGenerateRequest.setClaimType("0");// 理赔类型
		billGenerateRequest.setClaimWay("0");//理赔方式
		billGenerateRequest.setClaimMoney(new BigDecimal(backFreight.getApplyAmount())); // 金额
		billGenerateRequest.setBusinessType("2");//业务类型
		billGenerateRequest.setBillNo(backFreight.getWaybillNumber());// 运单号
		billGenerateRequest.setCreatorNo(backFreight.getApplicant());// 创建人工号
		billGenerateRequest.setCustNo(backFreight.getCustomerNum());// 客户编码
		billGenerateRequest.setDeptNo(backFreight.getApplyDept());// 部门标杆编码
		billGenerateRequest.setPaymentCategory("");
		try {
			//调用接口在FOSS生成应付单
		return 	getPaymentApplyOperate().claimsApbill(billGenerateRequest);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};		}
	}

	public IEsbToFossAsyOperate getEsbToFossAsyOperate() {
		return esbToFossAsyOperate;
	}

	public void setEsbToFossAsyOperate(IEsbToFossAsyOperate esbToFossAsyOperate) {
		this.esbToFossAsyOperate = esbToFossAsyOperate;
	}

	/**
	 * @description ：根据id更新工作流号
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id String oaWorkflowNum :工作流号
	 * @date 2012-10-29 下午2:42:14
	 * @return void
	 */
	public void updateBackFreightWorkflowNum(String id, String oaWorkflowNum) {
		backFreightDao.updateBackFreightWorkflowNum(id, oaWorkflowNum);

	}

	/**
	 * 
	 * @Description: 提交退运费的工作流
	 * @author 华龙
	 * @param BackFreight
	 *            backFreight :退运费实体
	 * @return String
	 * @date 2012-11-7 下午4:02:00
	 * @version V1.0
	 */
	public String submitBackFreightWorkflow(BackFreight backFreight) {
		//封装传递接口参数
		BackFreightInfo backFreightInfo=new  BackFreightInfo();
		backFreightInfo.setApplyPersonCode(backFreight.getEmpCode());
		System.err.println(backFreight.getEmpCode());
		backFreightInfo.setApplyAmount((long) (backFreight.getApplyAmount()));
		backFreightInfo.setApplyDept(backFreight.getApplyDept());
		backFreightInfo.setApplyPersonName(backFreight.getApplicantName());
		backFreightInfo.setApplyReason(backFreight.getApplyReason());
		backFreightInfo.setProposerPosition(backFreight.getPosition());
		if(null!=backFreight.getCustomerName()){
			
			backFreightInfo.setCustomerInfor(backFreight.getCustomerName());
		}else{
			backFreightInfo.setCustomerInfor(backFreight.getContactName());
			
		}
		backFreightInfo.setLocalFinancial(backFreight.getFinanceDeptCode());
		backFreightInfo.setPaymentType(backFreight.getPaymentType());
		backFreightInfo.setPureCarriage(backFreight.getWaybillAmount());
		backFreightInfo.setSigninTime(backFreight.getOutboundTime());
		backFreightInfo.setStowageDept(backFreight.getStowageDept());
		backFreightInfo.setTransportType(backFreight.getTranType());
		backFreightInfo.setWaybillNo(backFreight.getWaybillNumber());

		// 附件
		List<ContractNounInfo> files = new ArrayList<ContractNounInfo>();
		List<FileInfo> fileInfoList = backFreight.getFileInfoList();
		for (FileInfo fileInfo : fileInfoList) {
			ContractNounInfo ifile = new ContractNounInfo();
			ifile.setFileName(fileInfo.getFileName());
			ifile.setSavePath(fileInfo.getSavePath());
			ifile.setContractId("");
			ifile.setContractName("");
			files.add(ifile);
		}
		backFreightInfo.setFile(files);

		try {
			 return recompenseApplyOperate.applyBackFreight(backFreightInfo);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};	
		}
		
		
	}
	/**
	 * 
	 * <p>
	 * Description:劳务费状态修改<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-4
	 * @param waybillNum
	 * @param status
	 * void
	 */
	@Override
	public void ServiceChargeStatusUpdate(String waybillNum, boolean status) {
		try {
			//通知劳务费校验接口
			recompenseApplyOperate.ServiceChargeStatusUpdate(waybillNum, status);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};	
		}
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

}
