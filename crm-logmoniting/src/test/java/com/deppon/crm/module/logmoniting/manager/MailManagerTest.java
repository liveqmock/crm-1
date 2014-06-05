package com.deppon.crm.module.logmoniting.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfo;
import com.deppon.crm.module.logmoniting.shared.domain.ErrorCodeInfo;
import com.deppon.crm.module.logmoniting.utils.BeanUtils;

public class MailManagerTest extends BeanUtils{

	
	@Before
	public void setUP() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testActionInfoErrorForOneHour(){
		List<ActionLogInfo> actionLogInfos = new ArrayList<ActionLogInfo>();
		ActionLogInfo actionLogInfo = new ActionLogInfo();
		actionLogInfo.setActionName("CoCo");
		actionLogInfos.add(actionLogInfo);
		
		mailManager.actionInfoErrorForOneHour(actionLogInfos);
	}
	
	@Test
	public void testCompareTheTopTenActionLogInfoByOneDay(){
		List<ActionLogInfo> actionLogInfos = new ArrayList<ActionLogInfo>();
		ActionLogInfo actionLogInfo = null;
		for (int i = 0; i < 20; i++) {
			actionLogInfo = new ActionLogInfo();
			actionLogInfo.setActionName("CoCo");
			actionLogInfos.add(actionLogInfo);
		}
		mailManager.CompareTheTopTenActionLogInfoByOneDay(actionLogInfos,actionLogInfos);
	}
	
	@Test
	public void testCompareActionAverageContrastByOneDay(){
		List<ActionLogInfo> actionLogInfos = new ArrayList<ActionLogInfo>();
		ActionLogInfo actionLogInfo = null;
		for (int i = 0; i < 20; i++) {
			actionLogInfo = new ActionLogInfo();
			actionLogInfo.setActionName("CoCo");
			actionLogInfos.add(actionLogInfo);
		}
		mailManager.compareActionAverageContrastByOneDay(actionLogInfos);
	}
	
	@Test
	public void testCompleteExceptionErrorCode(){
		ErrorCodeInfo errorCodeInfo = new ErrorCodeInfo();
		errorCodeInfo.setCount(100);
		errorCodeInfo.setErrorCode("i18n.contract.contractSubject_is_DifferentCompanies");
		errorCodeInfo.setExceptionInfo("不同子公司 不能绑定");
		errorCodeInfo.setModuleName("customer");
		List<ErrorCodeInfo> list = new ArrayList<ErrorCodeInfo>();
		list.add(errorCodeInfo);
		mailManager.completeExceptionErrorCode(list);
	}
}
