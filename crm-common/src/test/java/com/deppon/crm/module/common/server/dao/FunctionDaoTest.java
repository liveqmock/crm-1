package com.deppon.crm.module.common.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.impl.FunctionDao;
import com.deppon.crm.module.authorization.shared.domain.Function;

public class FunctionDaoTest extends TestCase {

	private FunctionDao functionDao;

	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };

	@Override
	protected void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			functionDao = ctx.getBean(FunctionDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetFunction() {
		Function function = new Function();
		function.setFunctionLevel(3);
		List<Function> fl = functionDao.getAll(function);
		System.out.println("fl.size()===>" + fl.size());
	}

	public void testGetMenu() {
		List<Function> fl = functionDao.getFunctionByParentCode("01001");
		System.out.println("fl.size()===>" + fl.size());
	}
}
