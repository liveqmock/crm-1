/**   
 * @title ContactWokflowDataDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-7-16 上午11:35:47
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.server.dao.IApprovingWokflowDataDao;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description fuction  
 * @author 潘光均
 * @version 0.1 2012-7-16
 *@date 2012-7-16
 */

public class ApprovingWorkflowDataDao extends iBatis3DaoImpl implements IApprovingWokflowDataDao {
	private static final String NAMESPANCE = "com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData.";
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactWokflowDataDao#insertContactWorkflowData(com.deppon.crm.module.customer.shared.domain.ContactWorkflowData)
	 */
	@Override
	public ApprovingWorkflowData insertContactWorkflowData(
			ApprovingWorkflowData workflowData) {
		getSqlSession().insert(NAMESPANCE+"insertContactWorkflowData",workflowData);
		return workflowData;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactWokflowDataDao#updateConWorkflowData(com.deppon.crm.module.customer.shared.domain.ContactWorkflowData)
	 */
	@Override
	public boolean updateConWorkflowData(ApprovingWorkflowData workflowData) {
		return getSqlSession().update(NAMESPANCE+"updateContactWorkflowData",workflowData)>0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactWokflowDataDao#deleteConWorkflowData(java.lang.String)
	 */
	@Override
	public boolean deleteConWorkflowData(String id) {
		return getSqlSession().delete(NAMESPANCE+"deleteContactWorkflowData",id)>0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactWokflowDataDao#queryConWorkflowData(com.deppon.crm.module.customer.shared.domain.ContactWorkflowData)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ApprovingWorkflowData> queryConWorkflowData(
			ApprovingWorkflowData workflowData) {
		return getSqlSession().selectList(NAMESPANCE+"queryWorkflowContactByCondition", workflowData);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactWokflowDataDao#updateByWorkflowId(java.lang.String, java.lang.String)
	 */
	@Override
	public ApprovingWorkflowData queyById(String id ){
		return (ApprovingWorkflowData) getSqlSession().selectOne(NAMESPANCE+"getContactWorkflowDataById", id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactWokflowDataDao#updateByWorkflowId(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateByWorkflowId(String workFlowId, boolean status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workflowId", workFlowId);
		map.put("status", status);
		return getSqlSession().update(NAMESPANCE+"updateByWorkflowId", map)>0;
	}
}
