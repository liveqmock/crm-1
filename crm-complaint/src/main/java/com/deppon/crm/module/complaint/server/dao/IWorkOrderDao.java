package com.deppon.crm.module.complaint.server.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.complaint.shared.domain.WorkOrderCondition;
/**
 * 
 * <p>
 * Description:工单操作记录表<br />
 * </p>
 * @title IBulletinDao.java
 * @package com.deppon.crm.module.complaint.server.dao 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public interface IWorkOrderDao  {
	/**
	 * <p>
	 * Description:工单操作记录表<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * Integer
	 */
	 Integer saveWorkOrder(WorkOrder workOrder);
	/**
	 * <p>
	 * Description: 更新工单操作记录<br />
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
	 * @Title:      getLastWorkOrderRecordByComplaintId
	 * <p>
	 * Description: 根据工单ID获得该工单最后一次操作记录<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-19
	 * @param complaintId
	 * @return
	 * WorkOrder
	 */
	 WorkOrder getLastWorkOrderRecordByComplaintId(String complaintId);
	/**
	 * @Title:      getAllWorkOrderByComplaintId
	 * <p>
	 * Description: 根据工单ID获得所有调查建议的操作记录<br />
	 * </p>
	 * @author 邢悦
	 * @version 0.1 2012-7-25
	 * @param complaintId
	 * @return
	 * WorkOrder
	 */	
	 List<WorkOrder> getAllSuggestionsByComplaintId(BigDecimal compId);	
}
