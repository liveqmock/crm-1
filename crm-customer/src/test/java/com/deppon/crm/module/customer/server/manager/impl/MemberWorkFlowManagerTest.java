package com.deppon.crm.module.customer.server.manager.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.bpmsapi.module.shared.hessian.IWorkFlowService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IWorkFLowManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.exception.WorkFlowException;
import com.deppon.crm.module.customer.shared.exception.WorkFlowExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class MemberWorkFlowManagerTest extends BeanUtil{
	
	private Mockery context = new Mockery();
	
	@Before
	public void setUp(){
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
	}
	
	@After
	public void tearDown(){
		UserContext.setCurrentUser(null);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:test创建一个会员归属部门变更工作流<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-28
	 * void
	 * @throws SQLException 
	 */
	@Test
	public void test_createChangeMemberDeptWorkFlow() throws SQLException{
		long workFlowId = 0l;
		try {
			workFlowId = memberWorkFlowManager.createChangeMemberDeptWorkFlow("1258","400532478");
			Assert.assertNotNull(workFlowId);
		} catch (WorkFlowException e) {
			assertEquals(e.getErrorCode(), WorkFlowExceptionType.ErrorCreateWorkFlow.getErrCode());
		}

		ExamineRecord record = new ExamineRecord();
		record.setWorkFlowId(workFlowId);
		record.setResult(true);
		try{
		   memberWorkFlowManager.commitModifyMemberExamin(record);
	   }catch(WorkFlowException wfe){
//		   Assert.assertEquals(WorkFlowExceptionType.NoAuthor.getErrCode(), wfe.getErrorCode());
	    }
		
		createContactVaryData(workFlowId);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试 创建联系人变更工作流<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-28
	 * void
	 * @throws SQLException 
	 */
	@Test
	public void test_createContactVaryWorkFlow() throws SQLException{
		
	
		long workFlowId = 1L;
		
		Assert.assertNotNull(workFlowId);
		
		
		ExamineRecord record = new ExamineRecord();
		record.setWorkFlowId(workFlowId);
		record.setResult(true);
		try{
		memberWorkFlowManager.commitContactVaryExamin(record);
		}catch(WorkFlowException wfe){
			Assert.assertEquals(WorkFlowExceptionType.NoAuthor.getErrCode(), wfe.getErrorCode());
		}
		
		createContactVaryData(workFlowId);
		
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:test 创建特殊会员工作流<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-28
	 * void
	 */
	@Test
	public void test_createSepCreateMemberWorkFlow(){
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		long workFlowId = 0l;
		try {
			workFlowId = memberWorkFlowManager.createSepCreateMemberWorkFlow("400236541");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试特殊会员创建代办<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-28
	 * void
	 */
	@Test
	public void test_commitSepCreateMemberExamin(){
		

		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		try{
		ExamineRecord record = new ExamineRecord();
		record.setWorkFlowId(400206204);
		record.setResult(true);
		memberWorkFlowManager.commitSepCreateMemberExamin(record);
		}catch(WorkFlowException wfe){
			Assert.assertEquals(WorkFlowExceptionType.NoAuthor.getErrCode(), wfe.getErrorCode());
		}
		
		try{
		ExamineRecord record = new ExamineRecord();
		record.setWorkFlowId(400206204);
		record.setResult(false);
		memberWorkFlowManager.commitSepCreateMemberExamin(record);
		}catch(WorkFlowException wfe){
			Assert.assertEquals(WorkFlowExceptionType.NoAuthor.getErrCode(), wfe.getErrorCode());
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 启动一个会员修改工作流<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-28
	 * void
	 */
	@Test
	public void test_createNewModifyMemberWorkFlow(){
		
		try{
			User user = new User();
			Department depart = new Department();
			depart.setId("86301");
			depart.setDeptName("deptName");
			depart.setStandardCode("DP00003");
			Employee e = new Employee();
			e.setDeptId(depart);
			e.setId("98564");
			user.setEmpCode(e);
			UserContext.setCurrentUser(user);
			Set<String> set = new TreeSet<String>();
			set.add("dataType");
			memberWorkFlowManager.createNewModifyMemberWorkFlow(set, "400002267",new ArrayList<ApproveData>());
		}catch(WorkFlowException wfe){
			Assert.assertEquals(WorkFlowExceptionType.DataTypeError.getErrCode(), wfe.getErrorCode());
		}
		
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		Set<String> set = new TreeSet<String>();
		set.add(Constant.BaseDateType);
		set.add(Constant.AccountDateType);
		try {
			memberWorkFlowManager.createNewModifyMemberWorkFlow(set, "400002267",new ArrayList<ApproveData>());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-28
	 * void
	 */
	@Test
	public void test_commitModifyMemberExamin(){
		
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		ExamineRecord record = new ExamineRecord();
		try{
		record.setWorkFlowId(400206204);
		record.setResult(true);

		memberWorkFlowManager.commitModifyMemberExamin(record);
		}catch(WorkFlowException wfe){
			Assert.assertEquals(WorkFlowExceptionType.WorkFlowEnd.getErrCode(), wfe.getErrorCode());
		}

	}
	
	/**
	 * 
	 * <p>
	 * Description:测试审批合同新增--月发月送增值折扣 --工作流<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-28
	 * void
	 */
	@Test
	public void test_commitContractAddExamin(){
		try{
		ExamineRecord record = new ExamineRecord();
		record.setWorkFlowId(29543567435l);
		record.setResult(false);
		memberWorkFlowManager.commitContractAddExamin(record);

	  }catch(WorkFlowException wfe){
		   Assert.assertEquals(WorkFlowExceptionType.NoAuthor.getErrCode(), wfe.getErrorCode());
	    }
	}
	
	
	
	
	private void createContactVaryData(long workFlowId) throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete T_CUST_TO_WORKFLOW where WORKFLOWID = workFlowId");
		SpringTestHelper.executeBatch(sqlList);
	}
}
