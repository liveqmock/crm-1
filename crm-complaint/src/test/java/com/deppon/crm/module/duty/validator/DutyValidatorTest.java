package com.deppon.crm.module.duty.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.duty.server.util.DutyValidator;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.foss.framework.exception.GeneralException;

public class DutyValidatorTest extends TestCase {
	@Test
	public void testQueryDutyListValidator(){
		QueryDutyCondition queryDutyCondition = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date date1 = null;
		Date date2 = null;
		try {
			date1 =  sdf.parse("2013-03-03 18:18:19");
			date2 =  sdf.parse("2013-03-03 18:18:18");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			// 工单责任查询条件为空
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
		
		// 上报时间：开始时间和结束时间只选择了一个
		try {
			queryDutyCondition = new QueryDutyCondition();
			queryDutyCondition.setReportTimeBegin(date);
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
		// 上报时间：时间范围大于31天
		try {
			queryDutyCondition = new QueryDutyCondition();
			queryDutyCondition.setReportTimeBegin(date1);
			queryDutyCondition.setReportTimeEnd(date2);
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
		
		// 反馈时间：开始时间和结束时间只选择了一个
		try {
			queryDutyCondition = new QueryDutyCondition();
			queryDutyCondition.setFeedBackTimeBegin(date);
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
		// 反馈时间：时间范围大于31天
		try {
			queryDutyCondition = new QueryDutyCondition();
			queryDutyCondition.setFeedBackTimeBegin(date1);
			queryDutyCondition.setFeedBackTimeEnd(date2);
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
		
		// 责任划分时间：开始时间和结束时间只选择了一个
		try {
			queryDutyCondition = new QueryDutyCondition();
			queryDutyCondition.setAppDutyTimeBegin(date);
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
		// 责任划分时间：时间范围大于31天
		try {
			queryDutyCondition = new QueryDutyCondition();
			queryDutyCondition.setAppDutyTimeBegin(date1);
			queryDutyCondition.setAppDutyTimeEnd(date2);
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
		//责任划分人、责任部门、反馈部门、调查结果、责任状态、呼叫中心审批人组合查询时必须有一
		//个时间条件。否则提示“请选择查询时间段”
		try {
			queryDutyCondition = new QueryDutyCondition();
			queryDutyCondition.setAppDutyUser("11");
			queryDutyCondition.setDutyDept("11");
			queryDutyCondition.setFeedbackDept("11");
			queryDutyCondition.setDutyStates("11");
			queryDutyCondition.setSurveyResult("11");
			queryDutyCondition.setCallCenterUser("11");
			DutyValidator.queryDutyListValidator(queryDutyCondition);
			fail("未抛出异常");
		} catch (GeneralException e) {
			 assertTrue(true);
		}
	}
}
