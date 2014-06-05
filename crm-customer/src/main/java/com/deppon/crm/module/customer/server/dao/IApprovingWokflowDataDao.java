/**   
 * @title ContactWokflowDataDao.java
 * @package com.deppon.crm.module.customer.server.dao
 * @description what to do
 * @author 潘光均
 * @update 2012-7-16 上午11:25:21
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;

/**
 * @description 工作流审批 联系人信息接口  
 * @author 潘光均
 * @version 0.1 2012-7-16
 *@date 2012-7-16
 */

public interface IApprovingWokflowDataDao {
	
	/**
	 * 
	 * @description   插入工作流审批的联系人信息
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return ContactWorkflowData
	 * @update 2012-7-16 上午11:28:20
	 */
	public ApprovingWorkflowData insertContactWorkflowData(ApprovingWorkflowData workflowData);
	
	/**
	 * 
	 * @description 更新工作流审批的联系人信息.  
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return boolean
	 * @update 2012-7-16 上午11:32:06
	 */
	public boolean updateConWorkflowData(ApprovingWorkflowData workflowData);
	
	/**
	 * 
	 * @description 根据id删除工作流审批的联系人信息.  
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return boolean
	 * @update 2012-7-16 上午11:32:54
	 */
	public boolean deleteConWorkflowData(String id);
	
	/**
	 * 
	 * @description 根据条件查询工作流审批的联系人信息.  
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return List<ContactWorkflowData>
	 * @update 2012-7-16 上午11:34:00
	 */
	public List<ApprovingWorkflowData> queryConWorkflowData(ApprovingWorkflowData workflowData);

	/**
	 * @description 根据id查询工作流审批的联系人信息.  
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return ContactWorkflowData
	 * @update 2012-7-16 下午1:55:27
	 */
	public ApprovingWorkflowData queyById(String id);
	
	/**
	 * 
	 * @description 根据工作流号更改审批中联系人信息的状态.  
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return boolean
	 * @update 2012-7-16 下午3:21:30
	 */
	public boolean updateByWorkflowId(String workFlowId,boolean status);
}
