package com.deppon.crm.module.servicerecovery.server.manager;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;

/**
 * 
 * @description：服务补救Manager接口
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:14:39
 */
public interface IServiceRecoveryManager {
	/**
	 * 
	 * @description ：为退运费提高根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	public Waybill findWaybillByNumForBackFreight(String waybillNumber);

	/**
	 * 
	 * @description ：根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @param user
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	public Waybill findWaybillByNum(String waybillNumber, User user);

	/**
	 * 
	 * @description ：保存服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param user
	 *            TODO
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	public void submitServiceRecovery(ServiceRecovery serviceRecovery, User user);

	/**
	 * 
	 * @description ：根据申请状态和工作留号查询审批状态
	 * @author 华龙
	 * @version 1.0
	 * @param verifyStatus
	 *            TODO
	 * @param verifyTime
	 *            TODO
	 * @param :String applyStatus：申请状态 String oaWorkflowNum： 工作流号
	 * @date 2012-10-29 下午2:36:11
	 * @return String 状态 同意或者拒绝
	 */
	public boolean returnServiceRecoveryStatus(String oaWorkflowNum,
			String employeeNum, boolean verifyStatus, Date verifyTime,
			String verifyDesc);

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

	public List<ServiceRecovery> searchServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition);

	/**
	 * 
	 * @description ：转换查询条件
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:41:57
	 * @return ServiceRecoverySearchCondition 服务补救搜索条件
	 */
	public ServiceRecoverySearchCondition transServiceRecoveryByCondition(
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
	 * @Description: 查询有效的服务补救
	 * @author huangzhanming
	 * @param waybillNumber
	 * @return
	 * @return ServiceRecovery
	 * @date 2012-11-9 下午3:33:09
	 * @version V1.0
	 */
	public ServiceRecovery findValidServiceRecoveryByNum(String waybillNumber);

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
	public String getSubsidiaryByDeptStandardCode(String standardCode);
	

	List<Department> searchMyDataAuth(String type, int start, int limit); 

}
