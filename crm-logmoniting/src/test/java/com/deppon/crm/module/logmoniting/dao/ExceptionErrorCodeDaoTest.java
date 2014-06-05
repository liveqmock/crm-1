package com.deppon.crm.module.logmoniting.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.logmoniting.server.utils.LogUtil;
import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;
import com.deppon.crm.module.logmoniting.utils.BeanUtils;

public class ExceptionErrorCodeDaoTest extends BeanUtils{

	@Before
	public void setUP() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSaveExceptionErrorCode(){
		ExceptionErrorCode exceptionErrorCode = new ExceptionErrorCode();
		exceptionErrorCode.setCreateTime(System.currentTimeMillis());
		exceptionErrorCode.setCreateUser("5");
		exceptionErrorCode.setErrorCode("5");
		exceptionErrorCode.setModuleName("2");
		exceptionErrorCode.setId(LogUtil.getUUID());
		exceptionErrorCodeDao.saveExceptionErrorCode(exceptionErrorCode);
	}
	
	@Test
	public void testUpdateExceptionErrorCode(){
		ExceptionErrorCode exceptionErrorCode = new ExceptionErrorCode();
		exceptionErrorCode.setErrorCode("i18n..code....test");
		exceptionErrorCode.setModuleName("customer_test");
		exceptionErrorCode.setUpdateTime(System.currentTimeMillis());
		exceptionErrorCode.setUpdateUser("coco");
		exceptionErrorCode.setId("86589450-69b3-4f9f-92e6-9c724f232e4d");
		exceptionErrorCodeDao.updateExceptionErrorCode(exceptionErrorCode);
	}
	
	@Test
	public void testDeleteExceptionErrorCodeByQuery(){
		ExceptionErrorCode exceptionErrorCode = new ExceptionErrorCode();
		exceptionErrorCode.setId("46660d82-9e74-41b2-83ab-876cc5a408be");
		exceptionErrorCodeDao.removeExceptionErrorCode(exceptionErrorCode);
	}
	
	@Test
	public void testGetAllExceptionErrorCodes(){
		List<ExceptionErrorCode> list = null;
		list = exceptionErrorCodeDao.getAllExceptionErrorCodes();
		for (ExceptionErrorCode exceptionErrorCode : list) {
			System.out.println(exceptionErrorCode.getErrorCode());
		}
	}
	
	@Test
	public void testGetExceptionErrorCodesByQuery(){
		List<ExceptionErrorCode> list = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("moduleName").is("2"));
		list = exceptionErrorCodeDao.getExceptionErrorCodesByQuery(query, ExceptionErrorCode.class);
		for (ExceptionErrorCode exceptionErrorCode : list) {
			System.out.println(exceptionErrorCode.getErrorCode());
		}
	}
}
