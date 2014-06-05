package com.deppon.crm.module.complaint.server.service;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
/**
 * 
 * <p>
 * Description:保存操作记录<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public interface IWorkOrderService {
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
	 Integer saveWorkOrder(WorkOrder workOrder);
	
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
	 Integer updateWorkOrder(WorkOrder workOrder);
	
	/**
	* @Title: 		getWorkOrderByComplaintId
	* @Description: TODO(根据工单ID查询工单操作记录)
	* @param 		@param compId
	* @param 		@return    设定文件
	* @return 		List<WorkOrder>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<WorkOrder> getWorkOrderByComplaintId(BigDecimal compId);
	
	/**
	 * @Title: 		getLastWorkOrderRecordByComplaintId
	 * Description: 根据工单ID查询最后的工单操作记录<br />
	 * @author admin
	 * @version 0.1 2012-4-26
	 * @param complaintId
	 * @return
	 * WorkOrder
	 */
	 WorkOrder getLastWorkOrderRecordByComplaintId(String complaintId);
	
	
	
	/**
	* @Title: 		getAllWorkOrderByComplaintId
	* @Description: TODO(根据工单ID查询工单所有调查建议的操作记录)
	* @param 		@param compId
	* @param 		@return    设定文件
	* @return 		List<WorkOrder>    返回类型
	* @throws
	*/
	 List<WorkOrder> getAllSuggestionsByComplaintId(BigDecimal compId);	
	
	
	
}
