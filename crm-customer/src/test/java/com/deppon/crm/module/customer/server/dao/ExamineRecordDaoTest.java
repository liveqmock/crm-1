package com.deppon.crm.module.customer.server.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
/**
 * 
 * @description 积分工作流测试类  
 * @author 潘光均
 * @version 0.1 2012-6-16
 *@date 2012-6-16
 */
public class ExamineRecordDaoTest extends BeanUtil{
	static{
		try {
			DateInitUtil.executeCleanSql();
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					try {
						DateInitUtil.executeInitSql();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception{
		
		DateInitUtil.executeInitSql();
	}
	
	@After
	public void shutDown() throws Exception{
		DateInitUtil.executeCleanSql();
	}
	
	@Test
	public void testSaveExamineRecord(){
		ExamineRecord record = TestUtils.createBean(ExamineRecord.class);
		record.setCreateUser("7");
		examineRecordDao.saveExamineRecord(record);
	}
	
	@Test
	public void testGetExamineRecordByWorkflowId(){
		List<ExamineRecord> list = examineRecordDao.getExamineRecordByWorkflowId(1);
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testGetDeptIdAndRoleIdByWorkflowId(){
		long workFlowId = 800022490;
		examineRecordDao.getDeptIdAndRoleIdByWorkflowId(workFlowId);
	}

	@Test
	public void testGetApproverByDeptRoleMap(){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptId", "11333");
		map.put("roleId", "1004");
		list.add(map);
		
		Assert.assertNotNull(examineRecordDao.getApproverByDeptRoleMap(map));
	}
}
