package com.deppon.crm.module.complaint.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.ibatis.session.RowBounds;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.IWorkOrderDao;
import com.deppon.crm.module.complaint.server.dao.impl.WorkOrderDaoImpl;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.complaint.shared.domain.WorkOrderCondition;

public class WorkOrderDaoImplTest extends TestCase{

	private IWorkOrderDao workOrderDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			workOrderDao = ctx.getBean(WorkOrderDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testsaveWorkOrder() {
		WorkOrder wo=new WorkOrder();
		wo.setComplaintId(new BigDecimal(232));
		wo.setCreateDate(new Date());
		wo.setCreateUser("567");
		wo.setCurrentState(Constants.COMPLAINT_STATUS_PENDING);
		wo.setModifyDate(new Date());
		wo.setModifyUser("789");
		wo.setOperatorId(new BigDecimal(231));
		wo.setOperatorName("wanwu");
		wo.setOperatorRecord("nihao");
		wo.setPreviousState(Constants.COMPLAINT_STATUS_UNSUBMIT);
		wo.setOperatorType("SUBMIT");
		wo.setOperatorTime(new Date());
		wo.setOperatorRoleId(new BigDecimal(235));
		Integer num=workOrderDao.saveWorkOrder(wo);
		System.out.println(wo.getId()+"到了");
	}
	
	public void testGetWorkOrderByComplaintId(){
		BigDecimal complaintId=new BigDecimal(232);
		List<WorkOrder> workOrderList=workOrderDao.getWorkOrderByComplaintId(complaintId);
		assertNotNull(workOrderList);
	}
	
	public void testGetLastWorkOrderRecordByComplaintId() {
		String complaintId= "232";
		WorkOrder workOrder = workOrderDao.getLastWorkOrderRecordByComplaintId(complaintId);
		assertNotNull(workOrder);
	}
	
	public void testUpdateWorkOrder(){
		WorkOrder workOrder=new WorkOrder();
		workOrder.setFid(new BigDecimal(2));
		workOrder.setComplaintId(new BigDecimal(1));
		workOrder.setOperatorId(new BigDecimal(2));
		workOrder.setOperatorName("lee");
		workOrder.setOperatorTime(new Date());
		workOrderDao.updateWorkOrder(workOrder);
		//workOrder.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_FEEDBACK);
		
	}
	@Test
	public void testgetAllSuggestionsByComplaintId(){
		BigDecimal compId = new BigDecimal(1);
		workOrderDao.getAllSuggestionsByComplaintId(compId);
	}

}
