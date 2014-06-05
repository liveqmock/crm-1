package com.deppon.crm.module.custrepeat.server.manager;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.custrepeat.server.manager.impl.RepeatedCustManagerImpl;
import com.deppon.crm.module.custrepeat.server.service.IRepeatedCustService;
import com.deppon.crm.module.custrepeat.shared.domain.CustRepeatConstants;
import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.custrepeat.shared.exception.CustRepeatException;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:疑似重复客户DAO测试类<br />
 * </p>
 * @title RepeatedCustDaoTest.java
 * @package com.deppon.crm.module.custrepeat.server.dao 
 * @author 120750
 * @version 0.1 2014-3-5
 */
public class RepeatedCustManagerTest extends TestCase{
	//大客户管理dao
	private static IRepeatedCustManager repeatedCustManager;
	private static RepeatedCustManagerImpl repeatedCustManagerForWorkFlow = new RepeatedCustManagerImpl();
	
	static{
		//通过测试类加载
		repeatedCustManager = BeanUtil.repeatedCustManager;
	}
	@Before
	public void setUp(){
		bpsWorkFlowManagerJMock();
		repeatedCustServiceJMock();
		memberServiceJMock();
		departmentServiceJMock();
		alterMemberServiceJMock();
	}
	public void memberServiceJMock(){
		Mockery memberServiceJMock = new Mockery();
		final IMemberService memberService = memberServiceJMock.mock(IMemberService.class);
		memberServiceJMock.checking(new Expectations() {
			{
				allowing(memberService).updateMemberStauts(with(any(String.class)), with(any(String.class)), with(any(String.class)));
			}
		});
		repeatedCustManagerForWorkFlow.setMemberService(memberService);
	}
	
	public void alterMemberServiceJMock(){
		Mockery alterMemberServiceJMock = new Mockery();
		final IAlterMemberService alterMemberService = alterMemberServiceJMock.mock(IAlterMemberService.class);
		alterMemberServiceJMock.checking(new Expectations() {
			{
				List<MemberResult> memberList= new ArrayList<MemberResult>();
				MemberResult  mr = new MemberResult();
				mr.setCustId("111");
				memberList.add(mr);
				allowing(alterMemberService).queryBasicMemberByCustNum(with(any(String.class)));
				will(returnValue(memberList));
				allowing(alterMemberService).getMemberAllById(with(any(String.class)));
				will(returnValue(new Member()));
				allowing(alterMemberService).deleteMember(with(any(Member.class)));
			}
		});
		repeatedCustManagerForWorkFlow.setAlterMemberService(alterMemberService);
	}
	
	public void departmentServiceJMock(){
		Mockery departmentServiceJMock = new Mockery();
		final IDepartmentService departmentService = departmentServiceJMock.mock(IDepartmentService.class);
		departmentServiceJMock.checking(new Expectations() {
			{
				Department dept = new Department();
				dept.setDeptName("南京珠江路营业部");
				allowing(departmentService).getDepartmentById(with(any(String.class)));
				will(returnValue(dept));
			}
		});
		repeatedCustManagerForWorkFlow.setDepartmentService(departmentService);
	}
	public void bpsWorkFlowManagerJMock(){
		Mockery bpsWorkFlowManagerJMock = new Mockery();
		final IBpsWorkflowManager bpsWorkflowManager = bpsWorkFlowManagerJMock.mock(IBpsWorkflowManager.class);
		bpsWorkFlowManagerJMock.checking(new Expectations() {
			{
				allowing(bpsWorkflowManager).createWorkflow(with(any(String.class)),with(any(String.class)),with(any(String.class)),with(any(String.class)));
				will(returnValue(with(any(Map.class))));
				allowing(bpsWorkflowManager).bizCode();
				will(returnValue("ICRM201403250000635"));
			}
			
		});
		repeatedCustManagerForWorkFlow.setBpsWorkflowManager(bpsWorkflowManager);
	}
	public void repeatedCustServiceJMock(){
		Mockery repeatedCustServiceJMock = new Mockery();
		final IRepeatedCustService repeatedCustService = repeatedCustServiceJMock.mock(IRepeatedCustService.class);
		repeatedCustServiceJMock.checking(new Expectations() {
			{
				List<RepeatedCustomer> list = new ArrayList<RepeatedCustomer>();
				List<RepeatedCustomer> list1 = new ArrayList<RepeatedCustomer>();
				RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
				info.setCustomerCode("111");
				allowing(repeatedCustService).searchCustomerList(with(any(SearchCondition.class)));
				will(returnValue(list));
				allowing(repeatedCustService).searchRepeatCustList(with(any(SearchCondition.class)));
				will(returnValue(list1));
				allowing(repeatedCustService).bactchAddRepeatCust(with(any(List.class)));
				allowing(repeatedCustService).bactchUpdateRepeatCustStatus(with(any(List.class)));
				allowing(repeatedCustService).bactchAddRepeatCustToHistory(with(any(List.class)));
				allowing(repeatedCustService).batchMarkCustRepeat(with(any(List.class)));
				allowing(repeatedCustService).deleteRepeatCustList(with(any(List.class)));
				allowing(repeatedCustService).saveWorkFlowInfo(with(any(RepetitiveCustWorkFlowInfo.class)));
				allowing(repeatedCustService).searchRepeatCustCount(with(any(SearchCondition.class)));
				allowing(repeatedCustService).findRepetitiveCustWorkFlowInfoByWorkNo(with(any(String.class)));
				will(returnValue(info));
				allowing(repeatedCustService).updateRepetitiveCustWorkFlowInfo(with(any(RepetitiveCustWorkFlowInfo.class)));
				allowing(repeatedCustService).searchWorkFlowNoByCustId(with(any(String.class)));
			}
		});
		repeatedCustManagerForWorkFlow.setRepeatedCustService(repeatedCustService);
	}
	/**
	 * 
	 * <p>
	 * Description:测试测试查询客户信息<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testSearchCustomerList(){
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setCustCode("724470");
		repeatedCustManagerForWorkFlow.searchCustomerList(searchCondition);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试定时器：清空疑似重复状态表<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testClearALLRepeatCustMark(){
		System.out.println("开始时间："+new Date());
		repeatedCustManager.clearALLRepeatCustMark();
		System.out.println("结束时间："+new Date());
	}
	
	/**
	 * 查询疑似客户集合 通过主客户信息
	 */
	@Test
	public void testSearchRepeatedCustList(){
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setMainCustId("20219");
		repeatedCustManagerForWorkFlow.searchRepeatedCustList(searchCondition);
	}
	
	@Test
	public void testConfirmRepeat(){
		String disposeOpinion = "这里是-----处理意见-------";
		User user = new User();
		Employee e= new Employee();
		e.setEmpCode("084544");
		e.setId("324465");
		user.setEmpCode(e);
		user.getEmpCode().setId("324465");
		Department dept = new Department();
		List<RepeatedCustomer> custList = new ArrayList<RepeatedCustomer>();
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setIsRandomOneGroup(1);
		custList = repeatedCustManager.searchRepeatedCustList(searchCondition);
		
		if(null != custList && custList.size()>0){
			for(RepeatedCustomer temp : custList){
				if(temp.getIsMainCust() ==1){
					dept.setId(temp.getDeptId());
					dept.setDeptName(temp.getDeptName());
					user.getEmpCode().setDeptId(dept);
					UserContext.setCurrentUser(user);
					break;
				}
			}
		}
		RepeatedCustomer newCust1 = new RepeatedCustomer();
		RepeatedCustomer newCust2 = new RepeatedCustomer();
		RepeatedCustomer newCust3 = new RepeatedCustomer();
		RepeatedCustomer newCust4 = new RepeatedCustomer();
		RepeatedCustomer newCust5 = new RepeatedCustomer();
		RepeatedCustomer newCust6 = new RepeatedCustomer();
		newCust1.setId("55555551");
		newCust1.setDeptId(dept.getId());
		newCust1.setCustId("55555551");
		newCust1.setCustGroup("RC_CUSTOMER");
		newCust1.setCustLevel("DIAMOND");
		newCust1.setDeliveryMoneyTotal(8888);
		newCust1.setIsAdd(1);
		newCust1.setIsExistHistoryContract(0);
		newCust1.setIsExistValidContract(0);
		newCust1.setIsBigcustomer(0);
		newCust1.setIsContractCust(0);
		newCust1.setCreateDate(new Date());
		newCust1.setCustCreateTime(new Date());
		newCust2.setId("55555552");
		newCust2.setCustId("55555552");
		newCust2.setCustGroup("RC_CUSTOMER");
		newCust2.setCustLevel("DIAMOND");
		newCust2.setDeliveryMoneyTotal(8888);
		newCust2.setIsAdd(1);
		newCust2.setIsExistHistoryContract(0);
		newCust2.setIsExistValidContract(0);
		newCust2.setIsBigcustomer(0);
		newCust2.setIsContractCust(0);
		newCust2.setDeptId(dept.getId());
		newCust2.setCreateDate(new Date());
		newCust2.setCustCreateTime(new Date());
		newCust3.setId("55555553");
		newCust3.setCustId("55555553");
		newCust3.setCustGroup("RC_CUSTOMER");
		newCust3.setCustLevel("PLATINUM");
		newCust3.setIsAdd(1);
		newCust3.setIsExistHistoryContract(0);
		newCust3.setIsExistValidContract(0);
		newCust3.setIsBigcustomer(0);
		newCust3.setIsContractCust(0);
		newCust3.setDeptId(dept.getId());
		newCust4.setId("55555554");
		newCust4.setCustId("55555554");
		newCust4.setCustGroup("RC_CUSTOMER");
		newCust4.setCustLevel("GOLD");
		newCust4.setIsAdd(1);
		newCust4.setIsExistHistoryContract(0);
		newCust4.setIsExistValidContract(0);
		newCust4.setIsBigcustomer(0);
		newCust4.setIsContractCust(0);
		newCust4.setDeptId(dept.getId());
		newCust5.setId("55555555");
		newCust5.setCustId("55555555");
		newCust5.setCustGroup("SC_CUSTOMER");
		newCust5.setIsAdd(1);
		newCust5.setIsExistHistoryContract(0);
		newCust5.setIsExistValidContract(0);
		newCust5.setIsBigcustomer(0);
		newCust5.setIsContractCust(0);
		newCust5.setDeptId(dept.getId());
		newCust6.setId("55555556");
		newCust6.setCustId("55555556");
		newCust6.setCustGroup("SC_CUSTOMER");
		newCust6.setIsAdd(1);
		newCust6.setIsExistHistoryContract(0);
		newCust6.setIsExistValidContract(0);
		newCust6.setIsBigcustomer(0);
		newCust6.setIsContractCust(0);
		newCust6.setDeptId(dept.getId());
		custList.add(newCust1);
		custList.add(newCust2);
		custList.add(newCust3);
		custList.add(newCust4);
		custList.add(newCust5);
		custList.add(newCust6);
		
		
		repeatedCustManagerForWorkFlow.confirmRepeat(user, custList, disposeOpinion);
		
	}
	
	@Test
	public void testConfirmNotRepeat(){
		List<RepeatedCustomer> custList = new ArrayList<RepeatedCustomer>();
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setIsRandomOneGroup(1);
		custList = repeatedCustManager.searchRepeatedCustList(searchCondition);
		repeatedCustManager.confirmNotRepeat(custList);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试定时器：赛选疑似重复数据组<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testDisposeRepeatCustData(){
//		repeatedCustManager.disposeRepeatCustData();
	}
	/**
	 * 查询工作流详情
	 */
	@Test
	public void testFindRepetitiveCustWorkFlowInfoByWorkNo(){
		repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo("ICRM201403250000635", "");
	}
	/**
	 * 测试工作流审批
	 */
	@Test
	public void testWorkflowApprove(){
		
		String busino = "ICRM201403250000635";
		String wkMan = "324465";
		repeatedCustManagerForWorkFlow.workflowApprove(busino, false, wkMan, new Date(), "审批建议");
	}
	/**
	 * 判断客户是否在疑似重复列表中
	 */
	@Test 
	public void testIsCustExistsRepeat(){
		String memberId = "710593";
		repeatedCustManager.isCustExistsRepeat(memberId);
	}
	/**
	 * 根据客户ID删除疑似重复标记
	 */
	@Test
	public void testDeleteMarkCustByCustId(){
		String custId= "710593";
		repeatedCustManager.deleteMarkCustByCustId(custId);
	}
	/**
	 * 查询当前登录人的部门数据
	 */
	@Test
	public void testSearchRepeatCustDeptList(){
		User user = new User();;
		Employee e= new Employee();
		
		user.setEmpCode(e);
		Department dept = new Department();
		
		user.getEmpCode().setDeptId(dept); 
		UserContext.setCurrentUser(user);
		
		e.setEmpCode("089049");
		e.setEmpName("刘亚魁");
		e.setId("46135");
		dept.setDeptName("南京新港开发区营业部");
		e.setPosition(null);
		repeatedCustManager.searchRepeatCustDeptList("南京派送部", 1, 5);
		
		e.setPosition("派送部经理");
		repeatedCustManager.searchRepeatCustDeptList("南京派送部", 1, 5);
		
		e.setEmpCode("076860");
		e.setEmpName("周扬");
		e.setId("69324");
		dept.setDeptName("南京新港开发区营业部");
		e.setPosition("营业部经理");
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
		
		dept.setDeptName("南京栖霞营业区");
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
		
		dept.setDeptName("南京大区");
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
		
		dept.setDeptName("苏北事业部");
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
		
		dept.setDeptName("上海派送中心");
		repeatedCustManager.searchRepeatCustDeptList("上海派送中心", 1, 5);
		
		dept.setDeptName("佛山顺德区点部");
		e.setPosition("点部经理");
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
		
		dept.setDeptName("佛山快递分部");
		e.setPosition("分部高级经理");
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
		
		dept.setDeptName("四川快递大区");
		e.setPosition("快递大区总经理");
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
		
		
		dept.setDeptName("CRM管理组");
		e.setPosition(null);
		repeatedCustManager.searchRepeatCustDeptList("南京新港开发区营业部", 1, 5);
	}
	@Test
	public void testSearchRepeatCustDeptCount(){
		User user = new User();;
		Employee e= new Employee();
		
		user.setEmpCode(e);
		Department dept = new Department();
		
		user.getEmpCode().setDeptId(dept); 
		UserContext.setCurrentUser(user);
		
		e.setEmpCode("089049");
		e.setEmpName("刘亚魁");
		e.setId("46135");
		dept.setDeptName("南京新港开发区营业部");
		e.setPosition(null);
		repeatedCustManager.searchRepeatCustDeptCount("南京派送部");
		
		e.setPosition("派送部经理");
		repeatedCustManager.searchRepeatCustDeptCount("南京派送部");
		
		e.setEmpCode("076860");
		e.setEmpName("周扬");
		e.setId("69324");
		dept.setDeptName("南京新港开发区营业部");
		e.setPosition("营业部经理");
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
		
		dept.setDeptName("南京栖霞营业区");
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
		
		dept.setDeptName("南京大区");
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
		
		dept.setDeptName("苏北事业部");
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
		
		dept.setDeptName("上海派送中心");
		repeatedCustManager.searchRepeatCustDeptCount("上海派送中心");
		
		dept.setDeptName("佛山顺德区点部");
		e.setPosition("点部经理");
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
		
		dept.setDeptName("佛山快递分部");
		e.setPosition("分部高级经理");
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
		
		dept.setDeptName("四川快递大区");
		e.setPosition("快递大区总经理");
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
		
		
		dept.setDeptName("CRM管理组");
		e.setPosition(null);
		repeatedCustManager.searchRepeatCustDeptCount("南京新港开发区营业部");
	}
	/**
	 * 
	 * <p>
	 * Description:测试：疑似客户查询<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testSearchMainCustomerMap(){
		SearchCondition sc = new SearchCondition();
		sc.setDeptId("24242");
		sc.setDeptLevel(CustRepeatConstants.DEPT_ORDINARY);
		repeatedCustManagerForWorkFlow.searchMainCustomerMap(sc);
	}
	/**
	 * 
	 * <p>
	 * Description:测试：疑似客户查询<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testSearchMainCustDeptName(){
		String aa =
		repeatedCustManager.searchMainCustDeptName("008563");
		System.out.println(aa);
		repeatedCustManager.searchMainCustDeptName("sdafsa");
		try{
			repeatedCustManager.searchMainCustDeptName(null);
		}catch(CustRepeatException ex){}
		try{
			repeatedCustManager.searchMainCustDeptName("");
		}catch(CustRepeatException ex){}
		
	}
	
	@Test
	public void testSearchWorkFlowNoByCustId(){
		String custCode = "7897";
		repeatedCustManagerForWorkFlow.searchWorkFlowNoByCustId(custCode);
	}
	
}
