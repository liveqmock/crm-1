package com.deppon.crm.module.complaint.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.complaint.server.dao.IComplaintDao;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;

public class ComplaintDaoTest extends TestCase {

	private IComplaintDao complaintDao;
	private static ApplicationContext ctx = null;
	private Complaint complaint = new Complaint();
	String[] xmls = new String[]{"DaoBeanTest.xml"};

	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			complaintDao = (IComplaintDao) ctx
					.getBean("complaintDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		complaint.setCreatetime(new Date());
//		complaint.setCreateuserid(new BigDecimal(0));
//		complaint.setLastmodifyuserid(new BigDecimal(0));
//		complaint.setLastupdatetime(new Date());
//		complaint.setBill("12345678");
//		complaint.setRecomcode("D123");
//
//		complaint.setDealbill("D123456");
//		complaint.setRebindno("");
//		complaint.setReporttype(Constants.COMPLAINT_REPORTTYE_REPORT);
//		complaint.setComplainid(new BigDecimal(0));
//		complaint.setComplaincust("test");
//		complaint.setCompman("abcdListwggg王伟");
//		complaint.setCustlevel("123");
//		complaint.setCusttype("test");
//		complaint.setRelatcusid(new BigDecimal(0));
//		complaint.setRelatcus("test");
//		complaint.setTimelimit(new BigDecimal(0));
//		complaint.setTilimitcycle("123");
//		complaint.setFeedback("1");
//		complaint.setPririty("12");
//		complaint.setFeedbackrecode("1");
//		complaint.setApprodelay(new BigDecimal(0));
//		complaint.setParabasilevelid(new BigDecimal(0));
//		complaint.setBasiclevelid(new BigDecimal(0));
//		complaint.setCompmanid(new BigDecimal(0));
//		complaint.setReporterid(new BigDecimal(0));
//		complaint.setReporter("test");
//		complaint.setReporttime(new Date());
//		complaint.setReportcontent("投诉");
//		complaint.setCustrequire("张三");
//		complaint.setRequireway("电子");
//		complaint.setTel("12333");
//		complaint.setDealstatus(Constants.COMPLAINT_STATUS_PENDING);
//		complaint.setReturnid(new BigDecimal(0));
//
//		complaint.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
	}
//
	@Test
	public void testSearchComplaints() {
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		// csc.setDealbill("");
		// csc.setBill("23");
		csc.setLimit(10);
		csc.setStart(1);
		csc.setVisitor("丽");
//		csc.setVisitorTime(new Date());
		csc.setCustsatisfy("BAD");
//		csc.setProcessTime(new Date());
		List<Complaint> bvList1 = complaintDao.searchComplaints(csc);
		csc.setLimit(null);
		List<Complaint> bvList2 = complaintDao.searchComplaints(csc);
		csc.setLimit(null);
		List<Complaint> bvList3 = complaintDao.searchComplaints(csc);
		csc.setLimit(-1);
		csc.setStart(0);
		List<Complaint> bvList4 = complaintDao.searchComplaints(csc);
//		assertEquals(bvList.size()>0, true);
//		assertEquals(bvList1.size()>0, true);
	}
//
	@Test
	public void testSearchWaitProccesComplaint() {
		List<String> commonStatus=new ArrayList<String>();
		commonStatus.add(Constants.COMPLAINT_STATUS_APPROVAL_RETURNED);  //审批退回
		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS); //单个部门退回 完成处理
		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);//单个部门退回  待审批（退回层级同退回完成处理）
		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);//单个部门退回 已升级
		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL);//所有部门退回，从处理过来的
		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL);//所有部门退回，从审批过来的
		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL);//所有部门退回，从升级过来的
		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS); //工单申请延时,工单来自处理
		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL); //工单申请延时,工单来自审批
		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE); //工单申请延时,工单来自升级
	
		
		List<String> specialStatus=new ArrayList<String>();		
		specialStatus.add(Constants.COMPLAINT_STATUS_UPGRADED);
		specialStatus.add(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
	
		List<String> feedbackStatus=new ArrayList<String>();
		feedbackStatus.add(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE); 
		feedbackStatus.add(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE);			
		
		List<Complaint> list = complaintDao.searchWaitProccesComplaint(commonStatus, specialStatus,feedbackStatus,"000000","1", 0, 15);
		boolean flag = false;
		if (null != list && list.size() > 0) {
			flag = true;
		}
		assertTrue(flag);
		
//		assertEquals(list.size(), 0);
		//complaint.setProstatus(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
		// complaintDao.saveComplaint(complaint);

		// comStatus.clear();
		// comStatus.add(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
		// //待审批
		// proStatus= new ArrayList();
		// List list2 =
		// complaintDao.searchWaitProccesComplaint(comStatus, proStatus,
		// "000000",0, 15);
		// assertEquals(list2.size(),1);
	}
//
	@Test
	public void testSearchComplaintsCount() {
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		csc.setBill("23");
		csc.setLimit(5);
		csc.setStart(0);
		Integer count = complaintDao.searchComplaintsCount(csc);
		System.out.println(count);
	}

	//@Test
	public void testSearchReportComplaints() {
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		// csc.setDealbill("");
		// csc.setBill("23");
		csc.setProstatus("");
		csc.setLimit(2);
		csc.setStart(0);
		List<Complaint> bvList = complaintDao
				.searchReportComplaints(csc);
		Integer fid = new Integer("106139");
		csc.setFid(fid);
		csc.setLimit(null);
		complaintDao.searchReportComplaints(csc);
		csc.setLimit(-1);
		complaintDao.searchReportComplaints(csc);
//		csc.setLimit(2);
//		csc.setStart(0);
//		List<Complaint> bvList1 = complaintDao
//				.searchReportComplaints(csc);
//		assertEquals(bvList.size()>0,true);
//		assertEquals(bvList1.size()>0,true);
	}

	@Test
	public void testSearchReportComplaintsCount() {
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		csc.setBill("23");
		csc.setLimit(2);
		csc.setStart(0);
		Integer count = complaintDao.searchReportComplaintsCount(csc);
		assertEquals(count>=0, true);
	}

	@Test
	public void testGetFreeExpiredComplaintForAccess() {
		Map<String,Object> praMap = new HashMap<String,Object>();
		praMap.put("reportType", Constants.COMPLAINT_REPORTTYE_REPORT);
		praMap.put("userId",new BigDecimal(12));
		praMap.put("limitCount", null);
		praMap.put("requiredDate", new Date());
		praMap.put("businessModel", Constants.BUSINESS_MODEL_UNEXPRESS);
		
		
		praMap.put("dealstatus",Constants.COMPLAINT_ACCESS_STATUS );
		List<Complaint> complaints = complaintDao.getFreeExpiredComplaintForAccess(praMap);
		
		praMap.put("limitCount",10);
		complaintDao.getFreeExpiredComplaintForAccess(praMap);
		
	}

	@Test
	public void testGetLockingComplaintsByReportType() {
		String reportType = "r";
		Map praMap = new HashMap();
		praMap.put("reportType", Constants.COMPLAINT_REPORTTYE_REPORT);
		praMap.put("userId",new BigDecimal(12));
		praMap.put("limitCount", 3);

		List<Complaint> complaints = complaintDao.getLockingComplaintsByReportType(praMap);
//		int count = 0;
//		if (null != complaints) {
//			count = complaints.size();
//		}
//		System.out.println("Three High Priority Complaints size: " + count);
	}
//	@Test
//	public void testUpdateComplaints() {
//		int id = complaintDao.saveComplaint(complaint);
//		complaint.setFid(new BigDecimal(id));
//		complaint.setBill("123");
//		int row = complaintDao.updateComplaint(complaint);
//		Complaint c = complaintDao.getComplaintById(complaint.getFid());
//		assertEquals(c.getBill(), "123");
//		assertEquals(row, 1);
//	}
//
//	@Test
//	public void testGetComplaintById() {
//		int id = complaintDao.saveComplaint(complaint);
//		complaint.setFid(new BigDecimal(id));
//		Complaint c = complaintDao.getComplaintById(complaint.getFid());
//		assertNotNull(c);
//	}
	@Test
	public void testSearchTaskComplaints(){
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		csc.setLimit(-1);
		csc.setStart(1);
		List list=complaintDao.searchTaskComplaints(csc);
		
		
		csc.setLimit(10);
		list=complaintDao.searchTaskComplaints(csc);
		
	}
	
	@Test
	public void testSearchTaskComplaintsCount(){
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		csc.setLimit(-1);
		csc.setStart(1);
		complaintDao.searchComplaintsCount(csc);
	}
	
	
	@Test
	public void testSearchCustComplaints(){
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		String date="2012-04";
		condition.setMaintainDateFrom(date);
		StringBuffer dateTo=new StringBuffer(date);
		dateTo.append(" 23:59:59 ");
		condition.setMaintainDateTo(dateTo.toString());
		List<String> custCodes=complaintDao.searchCustComplaints(condition);
		
	}
	
//	public void testGetAllComplaintByCustCode(){
//		ComplaintSearchCondition condition = new ComplaintSearchCondition();
//		condition.setComplainid("100");
//		List<Complaint> complaints=complaintDao.getAllComplaintByCustCode(condition);
//		assertEquals(null != complaints,true);
//	}
//
//	@Override
//	protected void tearDown() throws Exception {
//		if (complaint != null && complaint.getFid() != null) {
//			int row = complaintDao.deleteComplaint(this.complaint
//					.getFid());
//			assertEquals(row, 1);
//		}
//		super.tearDown();
//	}
	@Test
	public void testdeleteComplaint(){
		BigDecimal id = new BigDecimal(1);
		complaintDao.deleteComplaint(id);
	}
	@Test
	public void testGetReturnComplaintList(){
//		Complaint complaint=new Complaint();
//		complaint.setCreatetime(new Date());
//		complaint.setCreateuserid(new BigDecimal(69));
//		complaint.setLastmodifyuserid(new BigDecimal(0));
//		complaint.setLastupdatetime(new Date());
//		complaint.setBill("12345678");
//		complaint.setRecomcode("D123");
//
//		complaint.setDealbill("D123456");
//		complaint.setRebindno("");
//		complaint.setReporttype(Constants.COMPLAINT_REPORTTYE_REPORT);
//		complaint.setComplainid(new BigDecimal(0));
//		complaint.setComplaincust("test");
//		complaint.setCompman("abcdListwggg王伟");
//		complaint.setCustlevel("123");
//		complaint.setCusttype("test");
//		complaint.setRelatcusid(new BigDecimal(0));
//		complaint.setRelatcus("test");
//		complaint.setTimelimit(new BigDecimal(0));
//		complaint.setTilimitcycle("123");
//		complaint.setFeedback("1");
//		complaint.setPririty("12");
//		complaint.setFeedbackrecode("1");
//		complaint.setApprodelay(new BigDecimal(0));
//		complaint.setParabasilevelid(new BigDecimal(0));
//		complaint.setBasiclevelid(new BigDecimal(0));
//		complaint.setCompmanid(new BigDecimal(0));
//		complaint.setReporterid(new BigDecimal(0));
//		complaint.setReporter("test");
//		complaint.setReporttime(new Date());
//		complaint.setReportcontent("投诉");
//		complaint.setCustrequire("张三");
//		complaint.setRequireway("电子");
//		complaint.setTel("12333");
//		complaint.setProstatus(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE);
//		complaint.setReturnid(new BigDecimal(0));
//		Integer compid=complaintDao.saveComplaint(complaint);
		
		ComplaintSearchCondition condition=new ComplaintSearchCondition();
		condition.setProstatus(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE);
//		condition.setUserid("69");
		condition.setStart(0);
		condition.setLimit(2);
		List<Complaint> compList= complaintDao.getReturnComplaintList(condition);
		condition.setLimit(null);
		String userid = "106139";
		condition.setUserid(userid);
		complaintDao.getReturnComplaintList(condition);
		condition.setLimit(-1);
		complaintDao.getReturnComplaintList(condition);
//		assertEquals(compList.size()>0, true);
		
//		complaintDao.deleteComplaint(new BigDecimal(compid));
	}
	@Test
	public void testGetVeryfiyComplaints(){
//		ComplaintSearchCondition condition=new ComplaintSearchCondition();
//		condition.setLimit(0);
//		condition.setStart(0);
//		List<Complaint> list=complaintDao.getVeryfiyComplaints(condition);
//		assertEquals(list.size()>0, true);
		
		ComplaintSearchCondition condition2=new ComplaintSearchCondition();
		condition2.setLimit(2);
		condition2.setStart(0);
		List<Complaint> list2=complaintDao.getVeryfiyComplaints(condition2);
		String userid = "106139";
		condition2.setUserid(userid);
		condition2.setLimit(-1);
		complaintDao.getVeryfiyComplaints(condition2);
//		assertEquals(list2.size()>0, true);
	}
	@Test
	public void testGetVeryfiyComplaintsCount(){
		ComplaintSearchCondition condition2=new ComplaintSearchCondition();
		condition2.setLimit(2);
		condition2.setStart(0);
		int count=complaintDao.getVeryfiyComplaintsCount(condition2);
		assertEquals(count>0, true);
	}
	
//	public void testGetDealBill(){
//		String str=complaintDao.getDealBill();
//		System.out.println(str);
//		assertNotNull(str);
//	}
	@Test
	public void testGetComplaintResult(){
		ComplaintSearchCondition condition=new ComplaintSearchCondition();
		condition.setLimit(2);
		condition.setStart(0);
		List<Complaint> list=complaintDao.getComplaintResult(condition);
		String userid = "106139";
		condition.setLimit(null);
		condition.setUserid(userid);
		complaintDao.getComplaintResult(condition);
		condition.setLimit(-1);
		complaintDao.getComplaintResult(condition);
//		assertEquals(list.size()>0, true);
//		
//		ComplaintSearchCondition condition2=new ComplaintSearchCondition();
//		condition2.setLimit(10);
//		condition2.setStart(0);
//		List<Complaint> list2=complaintDao.getComplaintResult(condition2);
//		assertEquals(list2.size()>0, true);
	}
//	
//	public void testGetComplaintResultCount(){
//		ComplaintSearchCondition condition=new ComplaintSearchCondition();
//		condition.setLimit(0);
//		condition.setStart(0);
//		int count=complaintDao.getComplaintResultCount(condition);
//		assertEquals(count>0, true);
//	}
	@Test
	public void testGetReturnComplaintCount(){
		ComplaintSearchCondition condition=new ComplaintSearchCondition();
		int count =complaintDao.getReturnComplaintCount(condition);
//		assertEquals(count>0, true);
	}

	
	public void testSearchWaitProccesComplaintCount(){
		String status = "106139";
		List<String> commonStatus=new ArrayList<String>();
		commonStatus.add(status);
//		commonStatus.add(Constants.COMPLAINT_STATUS_APPROVAL_RETURNED);  //审批退回
//		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS); //单个部门退回 完成处理
//		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);//单个部门退回  待审批（退回层级同退回完成处理）
//		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);//单个部门退回 已升级
//		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL);//所有部门退回，从处理过来的
//		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL);//所有部门退回，从审批过来的
//		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL);//所有部门退回，从升级过来的
//		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS); //工单申请延时,工单来自处理
//		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL); //工单申请延时,工单来自审批
//		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE); //工单申请延时,工单来自升级
		List<String> specialStatus=new ArrayList<String>();		
		specialStatus.add(status);
//		specialStatus.add(Constants.COMPLAINT_STATUS_UPGRADED);
//		specialStatus.add(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
		List<String> feedbackStatus=new ArrayList<String>();
		feedbackStatus.add(status);
//		feedbackStatus.add(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE); 
//		feedbackStatus.add(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE);
		Integer total1=complaintDao.searchWaitProccesComplaintCount(commonStatus, specialStatus,feedbackStatus, "000000", "1");
		List<String> commonStatus1=new ArrayList<String>();
		String status1 = "'DEPT_RETURNED_PROCESS'";
		commonStatus1.add(status);  //审批退回
		List<String> specialStatus1=new ArrayList<String>();		
		specialStatus1.add(status1);
		List<String> feedbackStatus1=new ArrayList<String>();
		feedbackStatus1.add(status1); 
		Integer total2=complaintDao.searchWaitProccesComplaintCount(commonStatus1, specialStatus1,feedbackStatus1, "000000", "1");
//		
//		assertNotNull(total);
//		assertEquals(total>0, true);
	}
	@Test
	public void testGetAccessComplaints(){
		ComplaintSearchCondition condition=new ComplaintSearchCondition();
		condition.setUserid(85041+"");
		condition.setReportType("COMPLAINT");
		condition.setDealstatus(Constants.COMPLAINT_ACCESS_STATUS);
		condition.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		condition.setLockingtime(new Date());
		List list=complaintDao.getAccessComplaints(condition);
		condition.setLimit(2);
		condition.setStart(0);
		complaintDao.getAccessComplaints(condition);
		condition.setLimit(-1);
		condition.setStart(0);
		complaintDao.getAccessComplaints(condition);
	}
	@Test
	public void testGetAccessComplaintsCount(){
		ComplaintSearchCondition condition=new ComplaintSearchCondition();
		condition.setUserid(85041+"");
		condition.setReportType("COMPLAINT");
		condition.setDealstatus(Constants.COMPLAINT_ACCESS_STATUS);
		condition.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		condition.setLockingtime(new Date());
		Integer i=complaintDao.getAccessComplaintsCount(condition);
		assertEquals(i>=0, true);
	}
	
//	public void testGetPendingComplaintsCount(){
//		ComplaintSearchCondition condition=new ComplaintSearchCondition();
//		condition.setReportType("COMPLAINT");
//		condition.setLockingtime(new Date());
//		Integer i=complaintDao.getPendingComplaintsCount(condition);
//		assertNotNull(i);
//	}
	@Test
	public void testGetComplaintIncludeSatisfy(){
		ComplaintSearchCondition condition=new ComplaintSearchCondition();
		condition.setComplainid(525+"");
		condition.setLimit(2);
		condition.setStart(0);
		List<Complaint> i=complaintDao.getComplaintIncludeSatisfy(condition);
		String complainid = "106139";
		condition.setComplainid(complainid);
		condition.setLimit(null);
		complaintDao.getComplaintIncludeSatisfy(condition);
		condition.setLimit(-1);
		complaintDao.getComplaintIncludeSatisfy(condition);
//		assertNotNull(i);
	}
	@Test
	public void testGetComplaintIncludeSatisfyCount(){
		ComplaintSearchCondition condition=new ComplaintSearchCondition();
		condition.setComplainid(525+"");
		Integer i=complaintDao.getComplaintIncludeSatisfyCount(condition);
		assertNotNull(i);
	}
	@Test
	public void testGetUserLockedUnExpiredComplaints(){
		Map<String,Object> parameterMap=new HashMap<String,Object>();
		parameterMap.put("reportType", Constants.COMPLAINT_REPORTTYE_REPORT);
		parameterMap.put("prostatus",Constants.COMPLAINT_STATUS_PENDING );
		parameterMap.put("dealstatus",Constants.COMPLAINT_ACCESS_STATUS );
		parameterMap.put("requiredDate", new Date());
		parameterMap.put("limitCount", null);
		parameterMap.put("userId", 89173);
		List<Complaint> results=complaintDao.getUserLockedUnExpiredComplaints(parameterMap);
		assertNotNull(results);
	}
	
//	@Test
//	public void testAddNewRelation(){	
//		Integer compId = 20000 ;
//		String strCompId = compId.toString();
//		String serviceId = null;
//		try{
//			complaintDao.addNewRelation(strCompId, serviceId,"043260");			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void testInsertAccessLog(){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("complaintId", 111);
		map.put("accName", "苏玉军");
		map.put("accCode", "043260");
		map.put("accDate",new Date());
		boolean b = complaintDao.insertCompAccessLog(map);
	}
	@Test
	public void testInquireAllUserComplaintResultCount(){
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setProstatus("110");
		complaintDao.inquireAllUserComplaintResultCount(condition);
	}
	@Test
	public void testInquireAllUserVeryfiyComplaintsCount(){
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setProstatus("'DEPT_RETURNED_PROCESS'");
		complaintDao.inquireAllUserVeryfiyComplaintsCount(condition);
	}
	@Test
	public void testInquireAllTaskComplaintsCount(){
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		 List<String> prostatusList = new ArrayList<String>();
		 prostatusList.add("'DEPT_RETURNED_PROCESS'");
		 condition.setProstatusList(prostatusList);
		complaintDao.inquireAllTaskComplaintsCount(condition);
	}
	@Test
	public void testInquireAllUserTaskComplaintsCount(){
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		 List<String> prostatusList = new ArrayList<String>();
		 prostatusList.add("'DEPT_RETURNED_PROCESS'");
		 condition.setProstatusList(prostatusList);
		complaintDao.inquireAllUserTaskComplaintsCount(condition);
	}
	@Test
	public void testInquireAllUserWaitProcessCount(){
		List<String> commonStatus=new ArrayList<String>();
		String status = "'DEPT_RETURNED_PROCESS'";
		commonStatus.add(status);  //审批退回
		List<String> specialStatus=new ArrayList<String>();		
		specialStatus.add(status);
		List<String> feedbackStatus=new ArrayList<String>();
		feedbackStatus.add(status); 
		complaintDao.inquireAllUserWaitProcessCount(commonStatus, specialStatus, feedbackStatus);
	}
	@Test
	public void testinquireUserIdByEmployeeId(){
		String employeeId = "106139";
		complaintDao.inquireUserIdByEmployeeId(employeeId);
	}
}
