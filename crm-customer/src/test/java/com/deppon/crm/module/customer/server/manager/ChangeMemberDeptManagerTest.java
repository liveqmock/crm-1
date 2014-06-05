package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;

public class ChangeMemberDeptManagerTest extends BeanUtil{
	
	@Before
	public void setUp() throws SQLException{
		clearData();
	}
	
	@After
	public void tearDown(){}
	
	/**
	 * 
	 * <p>
	 * Description:测试保存 会员归属部门变更<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-24
	 * void
	 * @throws SQLException 
	 */
	@Test
	public void test_saveChangeMemberDept() throws SQLException{
		
		long workFlowId = 1374586987l;
         // 初始化数据
		ChangeMemberDept changeMemberDept = new ChangeMemberDept();
		changeMemberDept.setMemberId("870");
		changeMemberDept.setMemberNumber("441900724774482");
		changeMemberDept.setFromDeptId("98993");
		changeMemberDept.setFromDeptName("淮安营业区");
		changeMemberDept.setToDeptId("98994");
		changeMemberDept.setToDeptName("长春宽城营业区");
		changeMemberDept.setReason("操作员输入错误");
		changeMemberDept.setWorkFlowId(workFlowId);
		// 执行保存
		changeMemberDeptManager.saveChangeMemberDept(changeMemberDept);
		
		Assert.assertNotNull(changeMemberDept.getId());
		// 验证保存数据
		ChangeMemberDept r_changeMemberDept = changeMemberDeptManager.getChangeMemberDeptByWorkFlowId(""+changeMemberDept.getWorkFlowId());
		
		Assert.assertEquals(changeMemberDept.getMemberId(), r_changeMemberDept.getMemberId());
		Assert.assertEquals(changeMemberDept.getMemberNumber(), r_changeMemberDept.getMemberNumber());
		Assert.assertEquals(changeMemberDept.getFromDeptId(), r_changeMemberDept.getFromDeptId());
		Assert.assertEquals(changeMemberDept.getFromDeptName(), r_changeMemberDept.getFromDeptName());
		Assert.assertEquals(changeMemberDept.getToDeptId(), r_changeMemberDept.getToDeptId());
		Assert.assertEquals(changeMemberDept.getToDeptName(), r_changeMemberDept.getToDeptName());
		Assert.assertEquals(changeMemberDept.getReason(), r_changeMemberDept.getReason());
		Assert.assertEquals(changeMemberDept.getWorkFlowId(), r_changeMemberDept.getWorkFlowId());
		// 清理产生数据
		clearData();
		
	}
	

	private void clearData() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("  delete t_cust_changeMemberdept t where t.fworkflowid = 1374586987");
	    SpringTestHelper.executeBatch(sqlList);
	}

}
