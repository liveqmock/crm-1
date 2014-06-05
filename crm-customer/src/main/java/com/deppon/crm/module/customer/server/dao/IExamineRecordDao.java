package com.deppon.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.ExamineRecord;

/**   
 * <p>
 * Description:审批记录的dao<br />
 * </p>
 * @title IExamineRecordDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 李国文
 * @version 0.1 2013-2-26
 */
public interface IExamineRecordDao {

	/**
	 * <p>
	 * Description:保存审批记录 实体信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param record
	 * void
	 */
	void saveExamineRecord(ExamineRecord record);

	/**
	 * <p>
	 * Description:根据工作流号ID 得到想要的审批记录信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param workflowId
	 * @return
	 * List<ExamineRecord>
	 */
	List<ExamineRecord> getExamineRecordByWorkflowId(long workflowId);

	
	/**
	 * <p>
	 * Description:
	 * 通过工作流号 得到当前审批人的  得到 角色id，角色名称，部门id,部门名称
	 *  <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param workflowId
	 * @return
	 * Map
	 */
	@SuppressWarnings("rawtypes")
	Map getDeptIdAndRoleIdByWorkflowId(long workflowId);

	/**
	 * <p>
	 * Description:通过部门ID和角色ID 得到当前审批人的  其相应的工号empcode和名字empname
	 * 				对应是去找客户最近的一个工作流，未审批的
	 * <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param map
	 * @return
	 * List<Map<String,String>>
	 */
	List<Map<String, String>> getApproverByDeptRoleMap(Map<String, String> map);
	
}
