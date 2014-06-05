package com.deppon.crm.module.customer.server.manager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.velocity.app.VelocityEngine;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.customer.server.manager.impl.CustCreditManagerImpl;
import com.deppon.crm.module.customer.server.service.ICustCreditService;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.utils.CustCreditUtil;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.deppon.crm.module.frameworkimpl.server.cache.ServerParameterCache;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.sysmail.server.service.ISysMailSendService;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.adapter.mail.IMailSender;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.context.AppContext;
import com.deppon.foss.framework.server.context.UserContext;
import junit.framework.TestCase;

public class CustCreditManagerTest extends TestCase {
	private User user = null;
	
	private ICustCreditManager custCreditManager = (ICustCreditManager)SpringTestHelper
			.getBean("custCreditManager");
	private CustCreditManagerImpl custCreditManagerForMock = new CustCreditManagerImpl();
	private MailSenderService mailSenderService = (MailSenderService)SpringTestHelper
			.getBean("mailSenderService");
	private ICustCreditService custCreditService = (ICustCreditService)SpringTestHelper
			.getBean("custCreditService");
	
	static {
		AppContext.initAppContext("application", "","");
		 Mockery mock = new Mockery();
			final ICache<String, Head> cache = mock.mock(ICache.class);
				mock.checking(new Expectations(){
				{
					allowing(cache).getUUID();
					will(returnValue(ServerParameterCache.class.getName()));	
					final DataDictionaryCacheProvider dataDictionaryCacheProvider = SpringTestHelper.getBean(DataDictionaryCacheProvider.class);
					allowing(cache).get("MANAGEMENT_GROUP_MARKETING_DEPARTMENT");
					will(returnValue("事业部营销管理组"));
					allowing(cache).get("DISTRICT_STATISTICS_GROUP");
					will(returnValue("大区统计组"));
					allowing(cache).get("DIVISION_STATISTICS_GROUP");
					will(returnValue("分部统计组"));
					allowing(cache).get("DEPARTMENT_OFFICE");
					will(returnValue("事业部办公室"));
					allowing(cache).get("BIGAREA_DEPARTMENT_OFFICE");
					will(returnValue("大区办公室"));
					allowing(cache).get("EXPRESS_BRANCH_DEPTNAME");
					will(returnValue("快递分部"));
					allowing(cache).get("EXPRESS_BIG_AREA_DEPTNAME");
					will(returnValue("快递大区"));
					allowing(cache).get("EXPRESS_BIGAREA_DEPARTMENT_OFFICE");
					will(returnValue("快递大区办公室"));
					allowing(cache).get("EXPRESS_DISTRICT_STATISTICS_GROUP");
					will(returnValue("快递大区统计组"));
					
				}
			});
			
			CacheManager.getInstance().registerCacheProvider(cache);
	}
	static {
	}

	@Before
	public void setUp(){
		Employee employee = new Employee();
		employee.setId("639764");
		employee.setEmpCode("136146");
		Department dept = new Department();
		dept.setId("49708");
		dept.setStandardCode("DP02169");
		employee.setDeptId(dept);
		user = new User();
		user.setEmpCode(employee);
		UserContext.setCurrentUser(user);
		custCreditServiceJMock();
		departmentServiceJMock();
		employeeServiceJMock();
		fileManagerJMock();
		sysMailSendServiceJMock();
		messageManagerJMock();
		userServiceJMock();
	}
	
	
	public void custCreditServiceJMock(){
		Mockery custCreditServiceJMock = new Mockery();
		final ICustCreditService custCreditService = custCreditServiceJMock.mock(ICustCreditService.class);
		custCreditServiceJMock.checking(new Expectations() {
			{
				CustCreditConfig config = new CustCreditConfig();
				config.setMaxOverdraftDay(100);
				config.setMonthEndOvertake(200);
				config.setOverdueMonth(300);
				config.setEarliestDay(400);
				config.setBeforePaymentdateDay(500);
				config.setBeforeMonthPaymentDay(600);
				config.setUsedcreditrate(700);
				config.setDeptUsedrate(800);
				config.setModifyUser("1");
				List<CustCredit> creditList = new ArrayList<CustCredit>();
				CustCredit cc = new CustCredit();
				cc.setTemporarydebitDate(new Date());
				cc.setNomonthlyDate(new Date());
				cc.setRepaymentDate(28);
				creditList.add(cc);
				List<Map<String, String>> managementDeptList = new ArrayList<Map<String,String>>();
				Map<String,String> map1 = new HashMap<String, String>();
				map1.put("fmanagementDeptId", "98989");
				map1.put("fdeptname", "W0133");
				map1.put("fdeptcode", "W0133");
				managementDeptList.add(map1);
				Map<String,String> map2 = new HashMap<String, String>();
				map2.put("fmanagementDeptId", "98989");
				map2.put("fdeptname", "W0133");
				map2.put("fdeptcode", "W0132");
				managementDeptList.add(map2);
				Map<String,String> map3 = new HashMap<String, String>();
				map3.put("fmanagementDeptId", "98989");
				map3.put("fdeptname", "W0133");
				map3.put("fdeptcode", "W0131");
				managementDeptList.add(map3);
				List<Map<String, String>> busDeptList = new ArrayList<Map<String,String>>();
				Map<String,String> map4 = new HashMap<String, String>();
				map4.put("fbusDeptId", "463");
				map4.put("fdeptname", "浙北事业部");
				busDeptList.add(map4);
				List<Map<String, String>> bigAreaDeptList = new ArrayList<Map<String,String>>();
				Map<String,String> map5 = new HashMap<String, String>();
				map5.put("fbigAreaDeptId", "630");
				map5.put("fdeptname", "杭州大区");
				bigAreaDeptList.add(map5);
				List<Map<String, String>> areaDeptList = new  ArrayList<Map<String,String>>();
				Map<String,String> map6 = new HashMap<String, String>();
				map6.put("fareaDeptId", "11396");
				map6.put("fdeptname", "杭州江干营业区");
				areaDeptList.add(map6);
				List<Map<String, String>> responsibilityDeptList = new  ArrayList<Map<String,String>>();
				Map<String,String> map7 = new HashMap<String, String>();
				map7.put("fresponsibilityDeptId", "48392");
				map7.put("fattributionDeptId", "48392");
				map7.put("fdeptname", "杭州江干区九堡四季青营业部");
				responsibilityDeptList.add(map7);
				allowing(custCreditService).getDeliverMoneyByCondtion(with(any(Date.class)),with(any(Date.class)));
				allowing(custCreditService).getCustCreditByCustId(with(any(String.class)));
				will(returnValue(with(any(CustCredit.class))));
				allowing(custCreditService).updateCustCreditConfig(with(any(CustCreditConfig.class)));
				allowing(custCreditService).getCustCreditConfig();
				will(returnValue(config));
				allowing(custCreditService).getCustBadCreditList();
				will(returnValue(with(any(List.class))));
				allowing(custCreditService).updateEarlyWarnInfo(with(any(String.class)), with(any(Boolean.class)));
				allowing(custCreditService).insertCustBadCreditReport(with(any(CustCredit.class)), with(any(Boolean.class)));
				allowing(custCreditService).getCustBadCreditExcelList();
				will(returnValue(creditList));
				allowing(custCreditService).getCustCreditListByCondition(with(any(CustCredit.class)));
				will(returnValue(creditList));
				allowing(custCreditService).getManagementDeptList();
				will(returnValue(managementDeptList));
				allowing(custCreditService).getBusDeptList();
				will(returnValue(busDeptList));
				allowing(custCreditService).getBigAreaDeptList();
				will(returnValue(bigAreaDeptList));
				allowing(custCreditService).getAreaDeptList();
				will(returnValue(areaDeptList));
				allowing(custCreditService).getResponsibilityDeptList();
				will(returnValue(responsibilityDeptList));
				allowing(custCreditService).getCustCreditListByOtherConditions(with(any(Map.class)), with(any(Integer.class)), with(any(Integer.class)));
				allowing(custCreditService).getCustCreditListByConditions(with(any(Map.class)), with(any(Integer.class)), with(any(Integer.class)));
				allowing(custCreditService).insertCustBadCreditReport();
				allowing(custCreditService).generationCustCreditToDo();
			}
		});
		custCreditManagerForMock.setCustCreditService(custCreditService);
	}
	
	public void departmentServiceJMock(){
		Mockery departmentServiceJMock = new Mockery();
		final IDepartmentService departmentService = departmentServiceJMock.mock(IDepartmentService.class);
		departmentServiceJMock.checking(new Expectations() {
			{
				Department department1 = new Department();
				department1.setId("379");
				Department department2 = new Department();
				department2.setId("465185");
				Department department3 = new Department();
				department3.setId("464232");
				Department department4 = new Department();
				department4.setId("464289");
				Department department5 = new Department();
				department5.setId("464290");
				Department department6 = new Department();
				department6.setId("465082");
				Department department7 = new Department();
				department7.setId("464438");
				Department department8 = new Department();
				department8.setId("464437");
				Department department9 = new Department();
				department9.setId("465997");
				Department department0 = new Department();
				department0.setId("464400");
				Department department10 = new Department();
				department0.setId("464399");
				allowing(departmentService).getDeptByCode("W01030601");
				will(returnValue(department1));
				allowing(departmentService).getDeptByCode("W01330103");
				will(returnValue(department2));
				allowing(departmentService).getDeptByCode("W01000201083108");
				will(returnValue(department3));
				allowing(departmentService).getDeptByCode("W013301");
				will(returnValue(department4));
				allowing(departmentService).getDeptByCode("W01330101");
				will(returnValue(department5));
				allowing(departmentService).getDeptByCode("W01320103");
				will(returnValue(department6));
				allowing(departmentService).getDeptByCode("W01320101");
				will(returnValue(department7));
				allowing(departmentService).getDeptByCode("W013201");
				will(returnValue(department8));
				allowing(departmentService).getDeptByCode("W01310704");
				will(returnValue(department9));
				allowing(departmentService).getDeptByCode("W01310701");
				will(returnValue(department0));
				allowing(departmentService).getDeptByCode("W013107");
				will(returnValue(department10));
				
			}
		});
		custCreditManagerForMock.setDepartmentService(departmentService);
	}
	
	public void employeeServiceJMock(){
		Mockery employeeServiceJMock = new Mockery();
		final IEmployeeService employeeService = employeeServiceJMock.mock(IEmployeeService.class);
		employeeServiceJMock.checking(new Expectations() {
			{
				
			}
		});
		custCreditManagerForMock.setEmployeeService(employeeService);
	}
	
	public void fileManagerJMock(){
		Mockery fileManagerJMock = new Mockery();
		final IFileManager fileManager = fileManagerJMock.mock(IFileManager.class);
		fileManagerJMock.checking(new Expectations() {
			{
				FileInfo fileInfo = new FileInfo();
				allowing(fileManager).fileUpload(with(any(File.class)),with(any(String.class)),with(any(String.class)),with(any(String.class)));
				will(returnValue(fileInfo));
			}
		});
		custCreditManagerForMock.setFileManager(fileManager);
	}
	
	public void sysMailSendServiceJMock(){
		Mockery sysMailSendServiceJMock = new Mockery();
		final ISysMailSendService sysMailSendService = sysMailSendServiceJMock.mock(ISysMailSendService.class);
		sysMailSendServiceJMock.checking(new Expectations() {
			{
				allowing(sysMailSendService).saveSendLog(with(any(List.class)));
			}
		});
		custCreditManagerForMock.setSysMailSendService(sysMailSendService);
	}
	
	public void messageManagerJMock(){
		Mockery messageManagerJMock = new Mockery();
		final IMessageManager messageManager = messageManagerJMock.mock(IMessageManager.class);
		messageManagerJMock.checking(new Expectations() {
			{
				allowing(messageManager).deleteMessage(with(any(String.class)));
				allowing(messageManager).deleteMessageByType(with(any(String.class)));
				allowing(messageManager).addMessageList(with(any(List.class)));
			}
		});
		custCreditManagerForMock.setMessageManager(messageManager);
	}
	
	public void userServiceJMock(){
		Mockery userServiceJMock = new Mockery();
		final IUserService userService = userServiceJMock.mock(IUserService.class);
		userServiceJMock.checking(new Expectations() {
			{
				
			}
		});
		custCreditManagerForMock.setUserService(userService);
	}
	
	
	@Test
	public void testGetDeliverMoneyByCondtion() {
		custCreditManagerForMock.getDeliverMoneyByCondtion();
	}
	/**
	 * 根据客户id，查询客户信用信息
	 */
	@Test
	public void testGetCustCreditByCustId() {
		custCreditManager.getCustCreditByCustId("9999");
	}
	
	@Test
	public void testUpdateEarlyWarnInfo() {
		List<CustCredit> custBadCreditList = new ArrayList<CustCredit>();
		CustCredit cust = new CustCredit();
		cust.setCustId("199999");
		custBadCreditList.add(cust);
		
		custCreditManagerForMock.updateEarlyWarnInfo(custBadCreditList);
		custCreditManagerForMock.getUserService();
		custCreditManagerForMock.getCustCreditService();
		custCreditManagerForMock.getDepartmentService();
		custCreditManagerForMock.getEmployeeService();
		custCreditManagerForMock.getFileManager();
		custCreditManagerForMock.getMailSenderService();
		custCreditManagerForMock.getMessageManager();
		custCreditManagerForMock.getSysMailSendService();
	}
	
	/**
	 * 定时恢复散客、固客的临欠额度
	 */
	@Test
	public void testUpdateMonthSendRate() {
		custCreditManager.getDeliverMoneyByCondtion();
	}
	
	/**
	 * 更新信用较差客户配置信息
	 * @param config
	 */
	@Test
	public void testUpdateCustCreditConfig() {
		CustCreditConfig config = new CustCreditConfig();
		config.setMaxOverdraftDay(100);
		config.setMonthEndOvertake(200);
		config.setOverdueMonth(300);
		config.setEarliestDay(400);
		config.setBeforePaymentdateDay(500);
		config.setBeforeMonthPaymentDay(600);
		config.setUsedcreditrate(700);
		config.setDeptUsedrate(800);
		config.setModifyUser("1");
		custCreditManager.updateCustCreditConfig(config);
	}
	
	/**
	 * 获取信用较差客户配置信息
	 * @return
	 */
	@Test
	public void testGetCustCreditConfig() {
		CustCreditConfig config = custCreditManager.getCustCreditConfig();
	}
	
	/**
	 * 查询客户未还款信息
	 * @return
	 */
	@Test
	public void testGetCustBadCreditList() {
		List<CustCredit> list = custCreditManager.getCustBadCreditList();
	}
	
	/**
	 * 标记信用较差客户
	 * @return
	 */
	@Test
	public void testUpdateCustBadCredit() throws ParseException {
		custCreditManager.updateCustBadCredit();
	}
	
	/**
	 * 获取信用较差客户名单
	 * @return
	 */
	@Test
	public void testGetCustBadCreditExcelList() throws ParseException {
		custCreditManager.getCustBadCreditExcelList();
	}
	
	/**
	 * 插入信用较差客户名单
	 * @throws ParseException
	 */
	@Test
	public void testInsertCustBadCreditReport() throws ParseException {
		custCreditManager.insertCustBadCreditReport();
	}
	
	/**
	 * 信用较差客户邮件推送
	 */
	@Test
	public void testSendCustCreditMail() throws ParseException, IOException, MessagingException{
		try{
			custCreditManagerForMock.setMailSenderService(mailSenderService);
			IFileManager  fileManager= (IFileManager)SpringTestHelper.getBean("fileManager");
			custCreditManagerForMock.setFileManager(fileManager);
			VelocityEngine velocityEngine = (VelocityEngine)SpringTestHelper.getBean("velocityEngine");
			IEmployeeService  employeeService= (IEmployeeService)SpringTestHelper.getBean("employeeService");
			IDepartmentService  departmentService= (IDepartmentService)SpringTestHelper.getBean("departmentService");
			custCreditManagerForMock.setVelocityEngine(velocityEngine);
			custCreditManagerForMock.setEmployeeService(employeeService);
			custCreditManagerForMock.setDepartmentService(departmentService);
			custCreditManagerForMock.setCustCreditService(custCreditService);
			CustCredit custCredit = new CustCredit();
			custCredit.setExcelDay(CustCreditUtil.getHalfMonthDate(15));
			custCredit.setBeginDate(CustCreditUtil.dateFormat("yyyy-MM-dd", CustCreditUtil.getHalfMonthDate(1)));
			custCredit.setEndDate(CustCreditUtil.dateFormat("yyyy-MM-dd", CustCreditUtil.getHalfMonthDate(15)));
			Department department = departmentService.getDeptByCode("W01030601");
			custCreditManagerForMock.sendMail(custCredit, department.getId(), "全国", "W01030601", "", null);
			//custCreditManagerForMock.sendCustCreditMail();
		}catch(Exception ee){
			System.out.println("邮件发送异常");
		}
		
	}
	@Test
	public void testGetCustCreditToDo() throws ParseException{
		custCreditManagerForMock.getCustCreditToDo();
	}
	@Test
	public void testDeleteCustCreditTodo() throws ParseException{
		custCreditManagerForMock.deleteCustCreditTodo();
	}
	@Test
	public void testGenerationCustCreditToDo() throws ParseException{
		custCreditManagerForMock.generationCustCreditToDo();
	}
	/**
	 * 信用较差客户报表查询
	 */
	@Test
	public void testGetCustCreditListByConditions() throws ParseException {
		IDepartmentService  departmentService= (IDepartmentService)SpringTestHelper.getBean("departmentService");
		custCreditManagerForMock.setDepartmentService(departmentService);
		try {
			custCreditManagerForMock.getCustCreditListByConditions("466970", "2014-4-1", 0, 20);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 信用较差客户报表导出
	 */
	@Test
	public void testGetExcel() throws ParseException, IOException {
		String deptId = "10341";
		String date = "2014-03-16";
		IFileManager  fileManager= (IFileManager)SpringTestHelper.getBean("fileManager");
		custCreditManagerForMock.setFileManager(fileManager);
		try{
			custCreditManagerForMock.getExcel(deptId, date);
		}catch(Exception e){
			System.out.println(" 对应文件不存在");
		}
		
	}
	
}
