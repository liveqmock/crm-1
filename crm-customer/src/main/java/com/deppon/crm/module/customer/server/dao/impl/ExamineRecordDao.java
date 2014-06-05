package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.server.dao.IExamineRecordDao;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * <p>
 * Description:审批记录的dao<br />
 * </p>
 * 
 * @title IExamineRecordDao.java
 * @package com.deppon.crm.module.customer.server.dao
 * @author 李国文
 * @version 0.1 2013-2-26
 */
public class ExamineRecordDao extends iBatis3DaoImpl implements IExamineRecordDao {
	// 命名空间
	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.ExamineRecord.";

	/**
	 * <p>
	 * Description:保存审批记录 实体信息<br />
	 * </p>
	 * 
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param record
	 *            void
	 */
	public void saveExamineRecord(ExamineRecord record) {
		// 保存审批记录 实体信息
		this.getSqlSession().insert(NAME_SPACE + "saveExamineRecord", record);
	}

	/**
	 * <p>
	 * Description:根据工作流号ID 得到想要的审批记录信息<br />
	 * </p>
	 * 
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param workflowId
	 * @return List<ExamineRecord>
	 */
	@SuppressWarnings("unchecked")
	public List<ExamineRecord> getExamineRecordByWorkflowId(long workflowId) {
		// 根据工作流号ID 得到想要的审批记录信息
		return this.getSqlSession().selectList(NAME_SPACE + "getExamineRecordByWorkflowId", workflowId);
	}

	/**
	 * <p>
	 * Description: 通过工作流号 得到当前审批人的 得到 角色id，角色名称，部门id,部门名称 <br />
	 * </p>
	 * 
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param workflowId
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map getDeptIdAndRoleIdByWorkflowId(long workflowId) {
		// 通过部门ID和角色ID 得到当前审批人的 其相应的工号empcode和名字empname
		// / 对应是去找客户最近的一个工作流，未审批的
		return (Map) this.getSqlSession().selectOne(NAME_SPACE + "getDeptIdAndRoleIdByWorkflowId", workflowId);
	}

	/**
	 * <p>
	 * Description:通过部门ID和角色ID 得到当前审批人的 其相应的工号empcode和名字empname
	 * 对应是去找客户最近的一个工作流，未审批的 <br />
	 * </p>
	 * 
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param map
	 * @return List<Map<String,String>>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getApproverByDeptRoleMap(Map<String, String> map) {
		/**
		 * 通过部门ID和角色ID 得到当前审批人的 其相应的工号empcode和名字empname 对应是去找客户最近的一个工作流，未审批的
		 */
		return this.getSqlSession().selectList(NAME_SPACE + "getApproverByWorkflowId", map);

	}
}
