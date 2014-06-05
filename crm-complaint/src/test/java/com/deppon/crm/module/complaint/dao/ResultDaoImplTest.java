package com.deppon.crm.module.complaint.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.ResultDaoImpl;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;

public class ResultDaoImplTest extends TestCase{
	private ResultDaoImpl resultDaoImpl;
	private static ApplicationContext ctx = null;
	private Result result = new Result();
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			resultDaoImpl = ctx.getBean(ResultDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		Date cDate = new Date(System.currentTimeMillis());
		
		//result.setAppdelay(new BigDecimal(500));
		
		result.setComplainid(new BigDecimal(1));
		//result.setFid(new BigDecimal(200));
		//result.setCreatetime(cDate);
		//result.setLastupdatetime(cDate);
		result.setCreateuserid(new Integer(0105));
		result.setLastmodifyuserid(new BigDecimal(0250));
		result.setDealmatters("测试处理事项22");	
		result.setTaskpartmentid(new BigDecimal(505));
		result.setTaskproperties("测试反馈人222");
		result.setDealman("TestCase User22");
		result.setFtaskDeptName("aaa");
		result.setDealtime(cDate);
		
		
//		result.setFeedtimelimit(new BigDecimal(200));
//		result.setIfmaturity("否");
//		result.setIfovertime("否");
//		result.setProcesstimelimit(new BigDecimal(300));
//		result.setProrecord("测试部门处理记录");
//		result.setStat("测试反馈");
//		result.setResults("测试处理结果");
			 
		
				
	}
	@Test
	public void testSearchResultByCompId() {
		BigDecimal bd=new BigDecimal(567);
		List<Result> resultList=resultDaoImpl.searchResultById(bd);
		System.out.println(resultList.size());
	}
	
	@Test
	public void testGetResultById() {
		//BigDecimal resultId=new BigDecimal(3);
		List<Result> results = new ArrayList<Result>();
		results.add(this.result);
		int addCount = 0;
		addCount = resultDaoImpl.saveTaskDepartmentResult(results);
		
		Result result=resultDaoImpl.getResultById(results.get(0).getFid());
		assertNotNull(result);
	}
	
	public void testUpdateResult(){
		BigDecimal resultId=new BigDecimal(3);
		//Result result=resultDaoImpl.getResultById(resultId);
		List<Result> results = new ArrayList<Result>();
		results.add(this.result);
		int addCount = 0;
		addCount = resultDaoImpl.saveTaskDepartmentResult(results);
		
		result.setDealman("lee");
		result.setAppdelay(new BigDecimal(5));
		result.setDelay(Constants.IF_DELAY_APPLICATION_EFFECTIVE_NO);
		result.setFtaskDeptName("bbb");
		resultDaoImpl.updateResult(result);
	}
	
	public void testSaveTaskDepartmentResult() {
		List<Result> results = new ArrayList<Result>();
		results.add(this.result);
		int addCount = 0;
		addCount = resultDaoImpl.saveTaskDepartmentResult(results);
		assertEquals(1, addCount);
	}
	
//	public void testDeleteResult() {
//		int delCount = 0;	
//		Result result = new Result();
//		int delId = 9;
//		boolean flag = Boolean.TRUE;
//		BigDecimal delKey = null;
//		while (flag) {
//			delKey = new BigDecimal(delId);
//			result = resultDaoImpl.getResultById(delKey);
//			if (null != result && null != result.getFid()) {
//				result.setFid(delKey);
//				flag = Boolean.FALSE;
//			} else {
//				delId++;
//			}
//		}
//		delCount = resultDaoImpl.deleteResult(result);
//		assertEquals(1, delCount);
//	}
	
	public void testGetResultByCondition() {
		ResultSearchCondition condition = new ResultSearchCondition();
		condition.setDelay("0");
		List<Result> results = resultDaoImpl.getResultByCondition(condition);
		boolean flag = Boolean.FALSE;
		if (null != results && results.size() > 0) {
			flag = Boolean.TRUE;
		}
		assertTrue(flag);
	}
	
	public void testGetExpiredResultById(){
		BigDecimal complaintId=new BigDecimal("2");
		List<Result> results = resultDaoImpl.getExpiredResultById(complaintId);
	}
	
	public void testGetExpiredFeedbackResultById(){
		BigDecimal complaintId=new BigDecimal("2");
		List<Result> results = resultDaoImpl.getExpiredFeedbackResultById(complaintId);
	}
	
	public void testGetResultInfoByCompId(){
		BigDecimal complaintId=new BigDecimal("691");
		List<Result> results = resultDaoImpl.getResultInfoByCompId(complaintId);
		assertEquals(null!=results, true);
	}
	
	public void testGetReturnResultInfoByCompId(){
		BigDecimal complaintId=new BigDecimal("1221");
		List<Result> results = resultDaoImpl.getReturnResultInfoByCompId(complaintId);
		assertEquals(null!=results, true);
	}
}
