package com.deppon.crm.module.servicerecovery.server.service;

import java.util.List;

import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;

/**
 * 
 * @description：服务补救service接口
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:15:57
 */
public interface IServiceRecoveryService {
	/**
	 * 
	 * @description ：根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	//根据运单号查询运单实体
	public Waybill findWaybillByNum(String waybillNumber);

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
	//根据运单号查有效的服务补救
	public ServiceRecovery findValidServiceRecoveryByNum(String waybillNumber);

	/**
	 * 
	 * @description ：根据运单号查询时效差错记录
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            waybillNumber :运单号
	 * @date 2012-10-29 下午3:46:09
	 * @return DelayAccident 时效差错记实体
	 */
	//根据运单号查询时效差错记录
	public Boolean findDelayAccidentByNum(String waybillNumber);

	/**
	 * 
	 * @description ：保存服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return TODO
	 * @return void
	 */
	//保存服务补救
	public ServiceRecovery addServiceRecovery(ServiceRecovery serviceRecovery);

	/**
	 * @description ：更新服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return TODO
	 * @return void
	 */
	//更新服务补救
	public void updateServiceRecovery(ServiceRecovery serviceRecovery);

	/**
	 * @description ：更新服务补救工作流号
	 * @author 华龙
	 * @version 1.0
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return TODO
	 * @return void
	 */
	//更新服务补救工作流号
	public void updateServiceRecoveryWorkflowNum(String id, String oaWorkflowNum, String workflowNo);

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
	//根据条件查询服务补救列表
	public List<ServiceRecovery> searchServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition);

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
	//根据条件统计总条数
	public int countServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition);

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
	//根据条件导出服务补救
	public List<ServiceRecovery> exportServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition);

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
	//根据ID查询服务补救
	public ServiceRecovery findServiceRecoveryById(String serviceRecoveryId);

	/**
	 * 
	 * @Description: 提交服务补救的工作流
	 * @author huangzhanming
	 * @param serviceRecovery
	 * @return String
	 * @date 2012-11-7 下午4:02:00
	 * @version V1.0
	 */
	//提交服务补救的工作流
	public String submitServiceRecoveryWorkflow(ServiceRecovery serviceRecovery);

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
			String oaWorkFlowNum);

	/**
	 * 
	 * @Description: 生成FOSS应付单
	 * @author huangzhanming
	 * @param serviceRecovery
	 * @return void
	 * @date 2012-11-7 下午4:07:24
	 * @version V1.0
	 */
	public boolean submitServiceRecoveryPayment(ServiceRecovery serviceRecovery);

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
	//通过部门标杆编码查询子公司
	public String getSubsidiaryByDeptStandardCode(String standardCode);
	
	/**
	 * 
	 * <p>
	 * Description:新工作流号查询业务编号<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-11
	 * @param workflowNo
	 * @return
	 * ServiceRecovery
	 */
	public ServiceRecovery findServiceRecoveryByWorkflowNo(String workflowNo);

}
