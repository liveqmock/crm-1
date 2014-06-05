package com.deppon.crm.module.common.server.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.ExpressPointBusinessDeptDao;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;

public class ExpressPointBusinessDeptDaoTest {

	private static ExpressPointBusinessDeptDao expressPointBusinessDeptDao;
	private static ApplicationContext ctx=null;
	static String[] xmls=new String[]{"DaoBeanTest.xml"};
	
	@BeforeClass
	public  static void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			expressPointBusinessDeptDao = ctx.getBean(ExpressPointBusinessDeptDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static ExpressPointBusinessDept generateTestExpressPointBusinessDept(){
		ExpressPointBusinessDept expressPointBusinessDept=new ExpressPointBusinessDept();
		expressPointBusinessDept.setExpressPointCode("10101");
		expressPointBusinessDept.setExpressPointName("上海徐泾明珠家园快递点");
		expressPointBusinessDept.setBusinessDeptCode("102");
		expressPointBusinessDept.setBusinessDeptName("上海徐泾镇营业部");
		return expressPointBusinessDept;
	}
	@Test
	public void testInsertExpressPointBusinessDept(){
		ExpressPointBusinessDept expressPointBusinessDept=generateTestExpressPointBusinessDept();
		expressPointBusinessDeptDao.insertExpressPointBusinessDept(expressPointBusinessDept);
	}
	
	
	
	@Test
	public void testgetExpressPointBusinessDeptByDeptCode(){
		ExpressPointBusinessDept expressPointBusinessDept=expressPointBusinessDeptDao.getExpressPointBusinessDeptByDeptCode(
				generateTestExpressPointBusinessDept().getBusinessDeptCode());
		System.out.println(generateTestExpressPointBusinessDept().getBusinessDeptCode());
		System.out.println("id:"+expressPointBusinessDept.getId()+"businesscode:"+expressPointBusinessDept.getBusinessDeptCode());
	}

	@Test
	public void testUpdateExpressPointBusinessDept(){
		ExpressPointBusinessDept expressPointBusinessDept=expressPointBusinessDeptDao.getExpressPointBusinessDeptByDeptCode(
				generateTestExpressPointBusinessDept().getBusinessDeptCode());
		System.out.println("expName："+expressPointBusinessDept.getExpressPointName()+"expcode:"+expressPointBusinessDept.getExpressPointCode());
		String expName="最牛叉的快递点";
		expressPointBusinessDept.setExpressPointName(expName);
		expressPointBusinessDeptDao.updateExpressPointBusinessDept(expressPointBusinessDept);
	}
}
