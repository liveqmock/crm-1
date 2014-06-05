package com.deppon.crm.module.complaint.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.complaint.server.manager.impl.ComplaintManager;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.TaskResult;
import com.deppon.crm.module.duty.util.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Department;

public class ComplaintPostponeWithdrawTestCase extends TestCase {

	
	/**
	 * 客服专员上报工单->投诉处理员处理工单（设置两个业务部门）->投诉经理审批->
	 * 
	 * 业务部门1退回->投诉处理员查看待办工单->业务部门2申请延时->投诉处理员延时审批
	 * 
	 * ->投诉处理员查看待办工单（应该还在）
	 */
	
	
//	protected static final String COMPLAINT_MANAGER = "89863";   //投诉处理组经理059224
//	protected static final String COMPLAINT_PROCESSOR = "89173"; //投诉处理员 058647
//	protected static final String EXCEPTION_PROCESSOR = "57457"; //异常处理员 048902
//	protected static final String EXCEPTION_MANAGER = "28157";   //异常处理组经理006537
//	protected static final String ADMIN = "1";//超级管理员000000
//	protected static final String PA = "24473";  //品牌公关部027457
//	protected static final String REPORTOR = "89862";//客服专员 (上报人)059228 
//	protected static final String OPERATOR_OFFICEER_ID = "20665";//营运办公室人员004000
//	protected static final String SHANGHAI_OFFICEER_ID = "22097";//上海事业部人员003919
//	protected static final String OPERATION_OFFICE_CODE = "W012101";   //营运办公室
//	protected static final String SHANGHAI_DEPARTMENT_CODE= "W011302";   //上海事业部
	
//	protected Log log;
	private ComplaintManager complaintManager = TestUtil.complaintManager; 
//	ClassPathXmlApplicationContext factory;
//	UserCacheProvider userCacheProvider;
	User compManager = TestUtil.compManager;
	User complaintProcessor = TestUtil.complaintProcessor;
	User pa = TestUtil.pa;
	User exceptionManager = TestUtil.exceptionManager;
	User exceptionProcessor = TestUtil.exceptionProcessor;
	User admin = TestUtil.admin;
	User reportor = TestUtil.reportor;
	User operatorOfficer = TestUtil.operatorOfficer;
	User shanghaiOfficer = TestUtil.shanghaiOfficer;	
	Department shanghaiDepartment = TestUtil.shanghaiDepartment;
	Department operationOfficeDept =TestUtil.operationOfficeDept;
	
	

	@Before
	protected void setUp() throws Exception {
		System.out.println("aaaa");
//		log = LogFactory.getLog(getClass());
//		AppContext.initAppContext("application","","");
//		String[] resource = { 
//				"com/deppon/crm/module/complaint/server/META-INF/springTest.xml",
//				"classpath*:com/deppon/crm/module/**/META-INF/spring.xml",
//				
//				};
//
//		factory = new ClassPathXmlApplicationContext(resource);
//		userCacheProvider = (UserCacheProvider) factory
//				.getBean("userCacheProvider");
//		complaintManager = (ComplaintManager) factory.getBean("complaintManager");
//		
//		compManager = (User) userCacheProvider.get(COMPLAINT_MANAGER);
//		complaintProcessor = (User)userCacheProvider.get(COMPLAINT_PROCESSOR);
//		exceptionManager = (User)userCacheProvider.get(EXCEPTION_MANAGER);
//		exceptionProcessor = (User)userCacheProvider.get(EXCEPTION_PROCESSOR);
//		pa = (User)userCacheProvider.get(PA);
//		admin = (User)userCacheProvider.get(ADMIN);
//		reportor = (User)userCacheProvider.get(REPORTOR);
//		operatorOfficer = (User)userCacheProvider.get(OPERATOR_OFFICEER_ID);
//		shanghaiOfficer = (User)userCacheProvider.get(SHANGHAI_OFFICEER_ID);
//	    DepartmentService dpService=(DepartmentService)factory.getBean("departmentService");
//	    shanghaiDepartment=dpService.getDeptByCode(SHANGHAI_DEPARTMENT_CODE);
//	    operationOfficeDept= dpService.getDeptByCode("W011302");
//	    
	}	
	
	@Test
	public void testComplaintPostponeWithdrawCase() throws Exception{
		String bill = "complaintPostponeWwithdrawTesCase";
		//String detailBill = "2012052401500";
		String reportType = "COMPLAINT";
		Result result=null;
		
		/**
		 * 上报人 工单上报
		 */		
		Complaint complaint = createComplaint(bill, reportType, reportor);		
//		complaint.setDealbill(String.valueOf(new Date().getTime()));
//		boolean saveFlag = complaintManager.submitComplaint(complaint,"", reportor,"");
		
		//测试上报人是否可以上报工单
//		assertEquals(true, saveFlag);	
		
		/**
		 * 投诉处理人处理工单(这里考虑到由于接入会影响其他数据库中的单子，所以不做接入处理，直接处理工单)
		 */
		
		Map submitApprovalMap = new HashMap();
		List<Result> results=new ArrayList<Result>();
		results.add(createResult(complaint.getFid(), new BigDecimal(shanghaiDepartment.getId()), "投诉处理人完成处理", new Date()));
		results.add(createResult(complaint.getFid(), new BigDecimal(operationOfficeDept.getId()), "投诉处理人完成处理", new Date()));
		submitApprovalMap.put("complaint", complaint);
		submitApprovalMap.put("resultList", results);
		submitApprovalMap.put("sessionUser", complaintProcessor);
		complaintManager.submitForApproval(submitApprovalMap);
		
		//提交后验证处理人自己查不到该工单，经理查得到该工单
//		assertNotWaitContainComplaint(complaint, complaintProcessor);
//		assertWaitContainComplaint(complaint, compManager);
		
		
		/**
		 * 投诉经理 完成处理
		 */
		
		ResultSearchCondition resultCondition = new ResultSearchCondition();
		resultCondition.setComplainid(new BigDecimal(110));
		List<Result> currResults =  complaintManager.getResultByCondition(resultCondition);	
		complaint.setFid(new BigDecimal(110));
//		complaintManager.finishedComplaintProccess(complaint, currResults, null, null, compManager);		
		
//		//判断经理待办中没有该工单
//		assertNotWaitContainComplaint(complaint, compManager);
//		//判断两个部门有工单
//		assertDeptContainComplaint(complaint,operatorOfficer);
//		assertDeptContainComplaint(complaint,shanghaiOfficer);
		
		/**
		 * 部门1退回(营运办公室)
		 */
		
 		result=selectResultByCondition(complaint.getFid(), new BigDecimal(operationOfficeDept.getId()), new Integer(operatorOfficer.getEmpCode().getId()));		

//		complaintManager.returnToProcess(complaint.getFid().toString(), result.getFid().toString(), "营运办公室退回", operatorOfficer);		
		
//		//判断退回的部门的部门工单列表中已经没有了该工单
//		assertDeptNotContainComplaint(complaint, operatorOfficer);
//		//判断另外一个部门的部门工单列表中还有该工单
//		assertDeptContainComplaint(complaint, shanghaiOfficer);
//		//判断处理员待办中存在该工单
//		assertWaitContainComplaint(complaint, complaintProcessor);
		
		/**
		 * 部门2申请延时(上海办公室)
		 */
		
		result=selectResultByCondition(complaint.getFid(), new BigDecimal(shanghaiDepartment.getId()), new Integer(shanghaiOfficer.getEmpCode().getId()));
//		complaintManager.delayApplication(complaint.getFid().toString(), result.getFid().toString(), "", "3", shanghaiOfficer);
		
//		//判断延时申请的部门工单上还有该工单
//		assertDeptContainComplaint(complaint, shanghaiOfficer);		
//		//判断处理员待办中存在该工单
//		assertWaitContainComplaint(complaint, complaintProcessor);		
		
		/**
		 * 处理员延时申请审批（否决）
		 */
		resultCondition = new ResultSearchCondition();
		resultCondition.setComplainid(complaint.getFid());
		resultCondition.setTaskpartmentid(new BigDecimal(shanghaiDepartment.getId()));//上海事业部
		resultCondition.setCreateuserid(new Integer(complaintProcessor.getEmpCode().getId()));
		
		result=selectResultByCondition(complaint.getFid(), new BigDecimal(shanghaiDepartment.getId()), new Integer(complaintProcessor.getEmpCode().getId()));		
		currResults =  complaintManager.getResultByCondition(resultCondition);
		try {
			complaintManager.applyMoreTimeApproval(complaint, currResults, Constants.IF_DELAY_APPLICATION_EFFECTIVE_REFUSE,complaintProcessor, "拒绝");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		//判断延时审批后，该单子还在处理员的待办工单中
//		assertWaitContainComplaint(complaint,complaintProcessor);
		
	}
	
	
	
	
	/**
	 * 查看待办工单
	 * @return
	 */
	protected List<Complaint> searchWaitProcessComplaints(User user){
		Map<String, Object> waitMap=complaintManager.searchWaitProccesComplaint(user, 0, 999);
		List<Complaint> returnList;
		returnList=(List<Complaint>)waitMap.get(ComplaintManager.WAIT_COMPLAINT_LIST_KEY);
		return returnList;
	}
	
	/**
	 * 查看部门工单
	 */
	
	protected List<TaskResult> searchDepartmentComplaints(User user){
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setUser(user);
		Map departmentMap = complaintManager.searchTaskComplaints(condition);
		List<TaskResult> taskResultList = (List<TaskResult>)departmentMap.get("taskResultList");
		return taskResultList;
	}
	
	
	
	/**
	 * 判断部门工单存在相应的complaint
	 */
	protected void assertDeptContainComplaint(Complaint complaint,User user){
		List<TaskResult> taskResultList=searchDepartmentComplaints(user);
		int idx = -1;
		for (TaskResult taskResult: taskResultList) {
			if (complaint.getFid().intValue() ==  taskResult.getComplaintId().intValue()) {
				idx = complaint.getFid().intValue();
				break;
			}
		}
		assertTrue(idx!=-1);
	
	}
	
	/**
	 * 判断部门工单不存在相应的complaint
	 */
	protected void assertDeptNotContainComplaint(Complaint complaint,User user){
		List<TaskResult> taskResultList=searchDepartmentComplaints(user);
		int idx = -1;
		for (TaskResult taskResult: taskResultList) {
			if (complaint.getFid().intValue() ==  taskResult.getComplaintId().intValue()) {
				idx = complaint.getFid().intValue();
				break;
			}
		}
		assertTrue(idx==-1);
	
	}	
	
	
	/**
	 *判断待办工单存在相应的complaint 
	 */
	protected void assertWaitContainComplaint(Complaint complaint,User user){
		List<Complaint> complaints=searchWaitProcessComplaints(user);
		assertNotNull(complaints);
		assertTrue(complaints.contains(complaint));
		
	}
	
	/**
	 * 判断待办工单不存在相应的complaint 
	 * @param complaint
	 * @param user
	 */
	protected void assertNotWaitContainComplaint(Complaint complaint,User user){
		List<Complaint> complaints=searchWaitProcessComplaints(user);
		assertNotNull(complaints);
//		assertFalse(complaints.contains(complaint));
		
	}	
	
	/**
	 * 创建投诉
	 * @param bill
	 * @param reportType
	 * @param user
	 * @return
	 */
	protected Complaint createComplaint(String bill, String reportType, User user) {
		Complaint complaint = new Complaint();
		Date sysDate = new Date(System.currentTimeMillis());
		complaint.setCreatetime(sysDate);
		complaint.setLastupdatetime(sysDate);
		complaint.setCreateuserid(new BigDecimal(user.getEmpCode().getId()));
		complaint.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		complaint.setDealbill("2012052401500");
		complaint.setReporttype(reportType);
		complaint.setTimelimit(new BigDecimal(100));
		complaint.setTilimitcycle("MINUTE");
		complaint.setFeedback("0");
		complaint.setPririty("HIGH");
		

		complaint.setBill(bill);
		complaint.setComplaintsource("TELEPHONE");
		complaint.setCompman("justin.xu");
		complaint.setCustrequire("客户要求");
		complaint.setFeedback("0");
		complaint.setPririty("NORMAL");
		complaint.setReportcontent("上报内容");
		complaint.setReporttype(reportType);
		complaint.setSendorreceive("SHIPMAN");
		complaint.setTel("13800");
		complaint.setTilimitcycle("minute");
		complaint.setTimelimit(new BigDecimal(10));

	
		return complaint;
	}	
	
	
	/**
	 * 创建处理结果的方法
	 * @param complaintId
	 * @param departId
	 * @param dealMatters
	 * @param currDate
	 * @return
	 */
	protected Result createResult(BigDecimal complaintId, BigDecimal departId,
			String dealMatters, Date currDate) {
		Result result = new Result();
		result.setComplainid(complaintId);
		result.setCreateDate(currDate);
		result.setCreatetime(currDate);
		result.setCreateuserid(new Integer(compManager.getId()));
		result.setCreateUser(compManager.getEmpCode().getEmpName());
		result.setDealman(compManager.getEmpCode().getCreateUser());
		result.setLastupdatetime(currDate);
		result.setLastmodifyuserid(new BigDecimal(compManager.getId()));
		result.setDealmatters(dealMatters);
		result.setDealtime(currDate);
		result.setTaskpartmentid(departId);
		result.setDealType("department");
		return result;
	}	
	
	/**
	 * 根据条件查询result
	 * @return
	 */
	protected Result selectResultByCondition(BigDecimal complaintId,BigDecimal departmentId,Integer creatorEmpId){

		ResultSearchCondition resultCondition = new ResultSearchCondition();
		resultCondition.setComplainid(complaintId);
		resultCondition.setTaskpartmentid(departmentId);//营运办公室
		resultCondition.setCreateuserid(creatorEmpId);		
		List<Result> results= complaintManager.getResultByCondition(resultCondition);
//		assertTrue(results.size() > 0);
		if(null == results || 0 == results.size()){
			return null;
		}else{
		return results.get(0);}
		
	}
}
