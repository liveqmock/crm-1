package com.deppon.crm.module.servicerecovery.server.dao;

import java.util.List;

import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;

/**
 * 
 * @description：服务补救DAO接口
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29上午11:13:34
 */
public interface IServiceRecoveryDao {
	/**
	 * @description ：根据运单号查询服务补救实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	public ServiceRecovery findValidServiceRecoveryByNum(String waybillNumber);

	/**
	 * @description ：保存服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	public ServiceRecovery addServiceRecovery(ServiceRecovery serviceRecovery);

	/**
	 * @description ：更新服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :String applyStatus：申请状态 String oaWorkflowNum： 工作流号
	 * @date 2012-10-29 下午2:36:11
	 * @return String 状态 同意或者拒绝
	 */
	public void updateServiceRecovery(ServiceRecovery serviceRecovery);

	/**
	 * @description ：根据条件查询服务补救列表
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<ServiceRecovery> 服务补救列表
	 */

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
	public ServiceRecovery getServiceRecoveryById(String serviceRecoveryId);

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
	public ServiceRecovery getServiceRecoveryByOaWorkflowNum(String waybillNum);

	/**
	 * @description ：根据id
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            serviceRecoveryId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return ServiceRecovery 服务补救实体
	 */
	public void updateServiceRecoveryWorkflowNum(String id, String workflowNum, String workflowNo);

	
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
