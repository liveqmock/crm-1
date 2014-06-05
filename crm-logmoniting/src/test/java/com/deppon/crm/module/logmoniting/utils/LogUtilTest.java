package com.deppon.crm.module.logmoniting.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.logmoniting.server.utils.LogUtil;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;

public class LogUtilTest  extends BeanUtils{

	@Before
	public void setUP() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * @Description:验证参数的合法性<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 * void
	 */
	@Test
	public void testValidateBasicLoginInfoIsRight(){
	}
	
	@Test
	public void testAssemblingDataForCondition(){
		LogInfoCondition logInfoCondition = new LogInfoCondition();
		logInfoCondition.setStatisticalFrequency(Constant.STATISTICALFREQUENCY_MONTH);
		LogUtil.assemblingDataForCondition(logInfoCondition);
	}
}
