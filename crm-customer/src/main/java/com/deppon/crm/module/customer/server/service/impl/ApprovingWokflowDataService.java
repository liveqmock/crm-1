/**   
 * @title ContactWokflowDataService.java
 * @package com.deppon.crm.module.customer.server.service.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-7-17 下午1:42:29
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;
import java.util.Set;

import com.deppon.crm.module.customer.server.dao.IApprovingWokflowDataDao;
import com.deppon.crm.module.customer.server.service.IApprovingWokflowDataService;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;

/**
 * @description fuction
 * @author 潘光均
 * @version 0.1 2012-7-17
 * @date 2012-7-17
 */

public class ApprovingWokflowDataService implements
		IApprovingWokflowDataService {
	IApprovingWokflowDataDao approvingWokflowDataDao;

	public IApprovingWokflowDataDao getApprovingWokflowDataDao() {
		return approvingWokflowDataDao;
	}

	public void setApprovingWokflowDataDao(
			IApprovingWokflowDataDao approvingWokflowDataDao) {
		this.approvingWokflowDataDao = approvingWokflowDataDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IContactWokflowDataService
	 * #insertContactWorkflowData
	 * (com.deppon.crm.module.customer.shared.domain.ContactWorkflowData)
	 */
	@Override
	public ApprovingWorkflowData insertContactWorkflowData(
			ApprovingWorkflowData workflowData) {
		return approvingWokflowDataDao.insertContactWorkflowData(workflowData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IContactWokflowDataService
	 * #updateConWorkflowData
	 * (com.deppon.crm.module.customer.shared.domain.ContactWorkflowData)
	 */
	@Override
	public boolean updateConWorkflowData(ApprovingWorkflowData workflowData) {
		return approvingWokflowDataDao.updateConWorkflowData(workflowData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IContactWokflowDataService
	 * #deleteConWorkflowData(java.lang.String)
	 */
	@Override
	public boolean deleteConWorkflowData(String id) {
		return approvingWokflowDataDao.deleteConWorkflowData(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IContactWokflowDataService
	 * #queryConWorkflowData
	 * (com.deppon.crm.module.customer.shared.domain.ContactWorkflowData)
	 */
	@Override
	public List<ApprovingWorkflowData> queryConWorkflowData(
			ApprovingWorkflowData workflowData) {
		workflowData.setStatus(true);
		return approvingWokflowDataDao.queryConWorkflowData(workflowData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IContactWokflowDataService
	 * #queyById(java.lang.String)
	 */
	@Override
	public ApprovingWorkflowData queyById(String id) {
		return approvingWokflowDataDao.queyById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IContactWokflowDataService
	 * #updateByWorkflowId(java.lang.String, boolean)
	 */
	@Override
	public boolean updateByWorkflowId(String workFlowId, boolean status) {
		return approvingWokflowDataDao.updateByWorkflowId(workFlowId, status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IContactWokflowDataService
	 * #batchCreateContactWorkflowData(java.util.Set)
	 */
	@Override
	public void batchCreateContactWorkflowData(
			Set<ApprovingWorkflowData> workflowDatas) {
		if (null != workflowDatas && 0 < workflowDatas.size()) {
			for (ApprovingWorkflowData contactWorkflowData : workflowDatas) {
				this.insertContactWorkflowData(contactWorkflowData);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.service.IApprovingWokflowDataService
	 * #getExistInfoCount(com.deppon.crm.module.customer.shared.domain.
	 * ApprovingWorkflowData)
	 */
	@Override
	public int getExistInfoCount(ApprovingWorkflowData workflowData) {
		List<ApprovingWorkflowData> list = this
				.queryConWorkflowData(workflowData);
		return list==null?0:list.size();
	}

}
