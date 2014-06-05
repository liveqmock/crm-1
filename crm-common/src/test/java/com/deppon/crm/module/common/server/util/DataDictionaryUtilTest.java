package com.deppon.crm.module.common.server.util;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.exception.DataDictionaryException;
import com.deppon.crm.module.common.shared.exception.LadingStationDepartmentException;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCache;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;

public class DataDictionaryUtilTest extends TestCase{
	private static  IBatchCacheProvider dataDictionaryCacheProvider;
	private static  ICache dataDictionaryCache;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"CommonBeanTest.xml"};

	static BigDecimal provinceId = new BigDecimal(0);
	static BigDecimal citysId = new BigDecimal(0);
	static BigDecimal areasId = new BigDecimal(0);
	
	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
//			dataDictionaryCacheProvider = ctx.getBean(DataDictionaryCacheProvider.class);
			dataDictionaryCache = ctx.getBean(DataDictionaryCache.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void testGetCode(){
		DataHeadTypeEnum type = null; 
		String value = null;
		DataDictionaryUtil.getCode(type, value);
		value = "";
		DataDictionaryUtil.getCode(type, value);
		type = DataHeadTypeEnum.BUSINESS_OPPORTUNITY_STATUS; 
		value = "hello";
		try{
			DataDictionaryUtil.getCode(type, value);
		}catch(DataDictionaryException e){}
	}
	public void testGetCodeDesc(){
		DataHeadTypeEnum type = null; 
		String code = null;
		DataDictionaryUtil.getCodeDesc(type, code);
		code = "";
		DataDictionaryUtil.getCodeDesc(type, code);
		type = DataHeadTypeEnum.BUSINESS_OPPORTUNITY_STATUS; 
		code = "hello";
		LadingStationDeptValidatorUtil ldUtil = 
				new LadingStationDeptValidatorUtil();
		try{
			ldUtil.canSaveLadingstationDepartment(2);
		}catch(LadingStationDepartmentException e){}
		try{
			DataDictionaryUtil.getCodeDesc(type, code);
		}catch(DataDictionaryException e){}
	}
}
