package com.deppon.crm.module.logmoniting.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;
import com.deppon.crm.module.logmoniting.utils.BeanUtils;
import com.deppon.crm.module.logmoniting.utils.UserUtil;

public class ExceptionErrorCodeManagerTest extends BeanUtils{
	
	@Before
	public void setUP() throws Exception {
		UserUtil.setUserToAdmin();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSaveExceptionErrorCode() {
		ExceptionErrorCode exceptionErrorCode = new ExceptionErrorCode();
		exceptionErrorCode.setErrorCode("e001");
		exceptionErrorCode.setExceptionInfo("excptionInfo");
		exceptionErrorCode.setModuleName("m001");
		exceptionErrorCode.setId("i001");
		exceptionErrorCodeManager.saveExceptionErrorCode(exceptionErrorCode);
		System.out.println("异常编码类的创建时间："+exceptionErrorCode.getCreateTime());
	}
	
	@Test
	public void testUpdateExceptionErrorCode(){
		ExceptionErrorCode exceptionErrorCode = new ExceptionErrorCode();
		exceptionErrorCode.setErrorCode("e002");
		exceptionErrorCode.setModuleName("m002");
		exceptionErrorCode.setId("i002");
		exceptionErrorCodeManager.updateExceptionErrorCode(exceptionErrorCode);
		System.out.println("异常编码类的更新时间："+exceptionErrorCode.getUpdateTime());
	}
	
	@Test
	public void testDeleteExceptionErrorCodeById(){
		exceptionErrorCodeManager.deleteExceptionErrorCodeById("e003");
		System.out.println("testDeleteExceptionErrorCodeById");
	}

}
