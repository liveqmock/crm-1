package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IWorkOrderDao;
import com.deppon.crm.module.complaint.server.service.IWorkOrderService;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
/**
 * 
 * <p>
 * Description:通报对象<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public class WorkOrderServiceImpl implements IWorkOrderService{
	private IWorkOrderDao workOrderDao;
	/**
	 * 
	 * <p>
	 * Description:保存操作记录<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	@Override
	public Integer saveWorkOrder(WorkOrder workOrder) {
		// TODO Auto-generated method stub
		return workOrderDao.saveWorkOrder(workOrder);
	}
	/**
	 * 
	 * <p>
	 * Description: 更新操作记录<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-19
	 * @param workOrder
	 * @return
	 * Integer
	 */
	public Integer updateWorkOrder(WorkOrder workOrder) {
		return workOrderDao.updateWorkOrder(workOrder);
	}

	public IWorkOrderDao getWorkOrderDao() {
		return workOrderDao;
	}

	public void setWorkOrderDao(IWorkOrderDao workOrderDao) {
		this.workOrderDao = workOrderDao;
	}
	/**
	* @Title: 		getWorkOrderByComplaintId
	* @Description: TODO(根据工单ID查询工单操作记录)
	* @param 		@param compId
	* @param 		@return    设定文件
	* @return 		List<WorkOrder>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public List<WorkOrder> getWorkOrderByComplaintId(BigDecimal compId) {
		// TODO Auto-generated method stub
		return workOrderDao.getWorkOrderByComplaintId(compId);
	}

	/**
	 * @Title: 		getLastWorkOrderRecordByComplaintId
	 * Description: 根据工单ID查询最后的工单操作记录<br />
	 * @author admin
	 * @version 0.1 2012-4-26
	 * @param complaintId
	 * @return
	 * WorkOrder
	 */
	@Override
	public WorkOrder getLastWorkOrderRecordByComplaintId(String complaintId) {
		return workOrderDao.getLastWorkOrderRecordByComplaintId(complaintId);
	}

	/**
	* @Title: 		getAllWorkOrderByComplaintId
	* @Description: TODO(根据工单ID查询工单所有调查建议的操作记录)
	* @param 		@param compId
	* @param 		@return    设定文件
	* @return 		List<WorkOrder>    返回类型
	* @throws
	*/
	public List<WorkOrder> getAllSuggestionsByComplaintId(BigDecimal compId){
		return workOrderDao.getAllSuggestionsByComplaintId(compId);
	}
	
}
