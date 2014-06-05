package com.deppon.crm.module.complaint.server.dao.impl;


import java.util.List;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import com.deppon.crm.module.complaint.server.dao.IWorkOrderDao;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class WorkOrderDaoImpl extends iBatis3DaoImpl implements IWorkOrderDao{
	
	private static final String NAMESPACE_WORKORDER = "com.deppon.crm.module.complaint.shared.domain.WorkOrder.";
	private static final String SAVE_WORKORDER  =  "saveWorkOrder";
	private static final String UPDATE_WORKORDER = "updateWorkOrder";

	private static final String SELECT_WORKORDER_BY_COMPLAINTID = "selectWorkOrderByCompId";
	private static final String GET_WOKEORDER_BY_COMPLAINTID = "getWorkOrderRecordByComplaintId";
	private static final String GET_SUGGESTION_BY_COMPLAINTID="getSuggestionByComplaintId";
	/**
	 * 
	 * <p>
	 * Description:工单操作记录表<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * Integer
	 */
	@Override
	public Integer saveWorkOrder(WorkOrder workOrder) {
		return this.getSqlSession().insert(NAMESPACE_WORKORDER+SAVE_WORKORDER, workOrder);
	}
	/**
	 * 
	 * <p>
	 * Description: 更新工单操作记录<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-19
	 * @param workOrder
	 * @return
	 * Integer
	 */
	public Integer updateWorkOrder(WorkOrder workOrder) {
		return this.getSqlSession().update(NAMESPACE_WORKORDER + UPDATE_WORKORDER, workOrder);
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
		Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("compId", compId);
		List<WorkOrder> workOrderList=this.getSqlSession()
				.selectList(NAMESPACE_WORKORDER+SELECT_WORKORDER_BY_COMPLAINTID, map);
		return workOrderList;
	}
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
	@Override
	public WorkOrder getLastWorkOrderRecordByComplaintId(String complaintId) {
		return (WorkOrder) this.getSqlSession().selectOne(NAMESPACE_WORKORDER + GET_WOKEORDER_BY_COMPLAINTID, complaintId);
	}
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
	@Override
	public List<WorkOrder> getAllSuggestionsByComplaintId(BigDecimal compId) {
		// TODO Auto-generated method stub
		Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("complaintId", compId);
		List<WorkOrder> workOrderList=this.getSqlSession()
				.selectList(NAMESPACE_WORKORDER+GET_SUGGESTION_BY_COMPLAINTID, map);
		return workOrderList;
	}	
	
}
