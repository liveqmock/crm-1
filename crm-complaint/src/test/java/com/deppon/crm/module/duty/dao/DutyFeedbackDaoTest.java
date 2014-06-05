package com.deppon.crm.module.duty.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.duty.server.dao.IDutyFeedbackDao;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
import com.deppon.crm.module.duty.util.TestUtil;

import junit.framework.TestCase;
/**
 * “工单责任反馈”测试
 * @author Administrator
 *
 */
public class DutyFeedbackDaoTest extends TestCase {
	IDutyFeedbackDao dutyFeedbackDao = TestUtil.dutyFeedbackDao;
	@Before
	public void setUp() throws Exception {
		
	}
	@Test
	public void testSearchDutyFeedbackByDutyId(){
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("dutyId", "2");
		List<DutyFeedback> feedbackList = dutyFeedbackDao.searchDutyFeedbackByDutyId(searchParam);
		System.out.println(feedbackList.size());
	}
	
	@Test
	public void testDutyApproval(){
		DutyFeedback dutyFeedback = new DutyFeedback();
		dutyFeedback.setFeedbackId("2");
		dutyFeedback.setDetailId("2");
		dutyFeedback.setCallCenUserId("123");
		dutyFeedback.setStatus("反馈有效");
		dutyFeedback.setApprovalResult("不成立");
		dutyFeedbackDao.dutyApproval(dutyFeedback);
		
	}
	@Test
	public void testSearchDutyFeedbackByFeedbackId(){
		String dutyFeedbackId = "2";
		DutyFeedback db = dutyFeedbackDao.searchDutyFeedbackByFeedbackId(dutyFeedbackId);
		System.out.print(db.getStatus());
	}
	
	@Test
	public void testSearchDutyFeedbackByDutyResultId(){
		String dutyResultId = "2";
		DutyFeedback db = dutyFeedbackDao.searchDutyFeedbackByDutyResultId(dutyResultId);
		System.out.print(db.getStatus());
	}
}
