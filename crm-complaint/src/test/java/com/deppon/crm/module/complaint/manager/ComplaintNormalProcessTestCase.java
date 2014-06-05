package com.deppon.crm.module.complaint.manager;


import com.deppon.crm.module.complaint.server.manager.impl.ComplaintManager;
import com.deppon.crm.module.duty.util.TestUtil;

import junit.framework.TestCase;

public class ComplaintNormalProcessTestCase extends TestCase {
	
	
	
//	private static final String COMPLAINT_MANAGER = "89863";   //投诉处理组经理059224
//	private static final String COMPLAINT_PROCESSOR = "89173"; //投诉处理员 058647
//	private static final String EXCEPTION_PROCESSOR = "57457"; //异常处理员 048902
//	private static final String EXCEPTION_MANAGER = "28157";   //异常处理组经理006537
//	private static final String ADMIN = "1";//超级管理员000000
//	private static final String PA = "24473";  //品牌公关部027457
//	private static final String REPORTOR = "89862";//客服专员 (上报人)059228 
//	private static final String OPERATOR_OFFICEER = "20665";//营运办公室人员004000

	protected ComplaintManager complaintManager = TestUtil.complaintManager; 
//	protected Log log;

//	ClassPathXmlApplicationContext factory;
//	UserCacheProvider userCacheProvider;
//	User compManager, complaintProcessor, pa, exceptionManager, exceptionProcessor, admin, reportor, operatorOfficer;

//	@Before
//	protected void setUp() throws Exception {
//		log = LogFactory.getLog(getClass());
//		AppContext.initAppContext("application", "","");
//		String[] resource = { 
//				"com/deppon/crm/module/complaint/server/META-INF/springTest.xml",
//				"classpath*:com/deppon/crm/module/**/META-INF/spring.xml"
////				"classpath*:com/deppon/crm/module/frameworkimpl/META-INF/spring.xml",
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
//		operatorOfficer = (User)userCacheProvider.get(OPERATOR_OFFICEER);
//	}
	//工单上报->b、工单处理->c、部门工单管理(退回)->d、待办工单(工单升级)->
	//e、升级退回->f、待办工单(完成处理)
	
	public void testNormalCase() throws Exception{
		System.out.println("aaaaaaaaaaaaaaaaaaaa");
	}

//	private Bulletin createBulletin(Complaint complaint, BigDecimal bullId,
//			String jobId, String bullDepName) {
//		Bulletin bulletin = new Bulletin();
//	    bulletin.setCompid(complaint.getFid());
//	    bulletin.setBulletinDeptName(bullDepName);
//	    bulletin.setBulletinid(bullId); 
//	    bulletin.setBulletinJobId(jobId);
//		return bulletin;
//	}
//
//	private Result createResult(BigDecimal complaintId, BigDecimal departId,
//			String dealMatters, Date currDate) {
//		Result result = new Result();
//		result.setComplainid(complaintId);
//		result.setCreateDate(currDate);
//		result.setCreatetime(currDate);
//		result.setCreateuserid(new Integer(compManager.getId()));
//		result.setCreateUser(compManager.getEmpCode().getEmpName());
//		result.setDealman(compManager.getEmpCode().getCreateUser());
//		result.setLastupdatetime(currDate);
//		result.setLastmodifyuserid(new BigDecimal(compManager.getId()));
//		result.setDealmatters(dealMatters);
//		result.setDealtime(currDate);
//		result.setTaskpartmentid(departId);
//		result.setDealType("department");
//		return result;
//	}
//
//	private Complaint createComplaint(String bill, String reportType, User user) {
//		Complaint complaint = new Complaint();
//		Date sysDate = new Date(System.currentTimeMillis());
//		complaint.setCreatetime(sysDate);
//		complaint.setLastupdatetime(sysDate);
//		complaint.setCreateuserid(new BigDecimal(user.getEmpCode().getId()));
//		complaint.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
//		complaint.setDealbill("2012052401500");
//		complaint.setReporttype(reportType);
//		complaint.setTimelimit(new BigDecimal(100));
//		complaint.setTilimitcycle("MINUTE");
//		complaint.setFeedback("0");
//		complaint.setPririty("HIGH");
//		
//
//		complaint.setBill(bill);
//		complaint.setComplaintsource("TELEPHONE");
//		complaint.setCompman("justin.xu");
//		complaint.setCustrequire("客户要求");
//		complaint.setFeedback("0");
//		complaint.setPririty("NORMAL");
//		complaint.setReportcontent("上报内容");
//		complaint.setReporttype(reportType);
//		complaint.setSendorreceive("SHIPMAN");
//		complaint.setTel("13800");
//		complaint.setTilimitcycle("minute");
//		complaint.setTimelimit(new BigDecimal(10));
//
//	
//		return complaint;
//	}
//	
//	private Complaint createExceptionComplaint(String bill, String reportType) {
//		Complaint unusual=new Complaint();
//		unusual.setBill(bill);
//		unusual.setComplaintsource("TELEPHONE");
//		unusual.setCompman("justin.xu");
//		unusual.setCustrequire("客户要求");
//		unusual.setFeedback("0");
//		unusual.setPririty("NORMAL");
//		unusual.setReportcontent("上报内容");
//		reportType = "UNUSUAL";
//		unusual.setReporttype(reportType);
//		unusual.setSendorreceive("SHIPMAN");
//		unusual.setTel("13800");
//		unusual.setTilimitcycle("minute");
//		unusual.setTimelimit(new BigDecimal(10));
//		
//		return unusual;
//	}
}
