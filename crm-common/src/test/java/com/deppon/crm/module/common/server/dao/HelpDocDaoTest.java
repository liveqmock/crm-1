package com.deppon.crm.module.common.server.dao;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.HelpDocDao;
import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;

public class HelpDocDaoTest extends TestCase {

	private static HelpDocDao helpDocDao;

	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };

	@BeforeClass
	protected void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			helpDocDao = ctx.getBean(HelpDocDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static HelpDoc generateTestHelpDoc() {
		Date currentDate = new Date();
		String currentTime = Long.toString(currentDate.getTime());
		HelpDoc helpDoc = new HelpDoc();
		helpDoc.setHelpTitle("CRM测试环境");
		helpDoc.setHelpContent("德邦物流有限公司");
		helpDoc.setBelongModule("BelongModule" + currentTime);
		helpDoc.setBelongMenu("BelongMenu" + currentTime);
		helpDoc.setWindowNum(currentTime);
		helpDoc.setActive(true);
		return helpDoc;
	}

	public void testAddHelpDoc() {
		HelpDoc helpDoc = generateTestHelpDoc();
		helpDocDao.addHelpDoc(helpDoc);
	}

	public void testGetHelpDoc() {
		HelpDoc helpDoc = generateTestHelpDoc();
		helpDocDao.addHelpDoc(helpDoc);
		HelpDoc hd = helpDocDao.getHelpDocById(helpDoc.getId());
		System.out.println("hd.isActive()===>" + hd.isActive());
		helpDocDao.getHelpDocById(null);
		helpDocDao.getHelpDocById("");

	}

	public void testGetHelpDocByWindowNum() {
		HelpDoc helpDoc = generateTestHelpDoc();
		helpDocDao.addHelpDoc(helpDoc);
		HelpDoc hd = helpDocDao.getHelpDocByWindowNum(helpDoc.getWindowNum());
		System.out.println("hd.isActive()===>" + hd.isActive());
		helpDocDao.getHelpDocByWindowNum(null);
		helpDocDao.getHelpDocByWindowNum("");

	}

	public void testDeleteHelpDoc() {
		HelpDoc helpDoc = generateTestHelpDoc();
		helpDocDao.addHelpDoc(helpDoc);
		helpDocDao.deleteHelpDocById(helpDoc.getId());

	}

	public void testUpdateHelpDocById() {
		HelpDoc helpDoc = generateTestHelpDoc();
		helpDocDao.addHelpDoc(helpDoc);
		helpDocDao.updateHelpDocById(helpDoc);
		helpDocDao.addHelpDoc(null);
		helpDoc = new HelpDoc();
		helpDocDao.updateHelpDocById(helpDoc);
		helpDoc.setId("11");
		helpDocDao.updateHelpDocById(helpDoc);
		helpDoc.setId("");
		helpDocDao.updateHelpDocById(helpDoc);
		
	}

	public void testSearchHelpDoc() {
		HelpDocSearchCondition condition = new HelpDocSearchCondition();
		// condition.setStart(0);
		// condition.setLimit(10);// Integer.MAX_VALUE
		// condition.setHelpTitle("CRM测试环境");
		// condition.setBelongModule("BelongModule");
		// condition.setBelongMenu("BelongMenu");
		// condition.setWindowNum("123");
		// condition.setActive(true);
		// condition.setStartDate(new Date());
		// condition.setEndDate(new Date());
		List<HelpDoc> hdl = helpDocDao.searchHelpDocByCondition(condition);
		int count = helpDocDao.getCountByCondition(condition);
		System.out.println("hdl.size()===>" + hdl.size());
		System.out.println("count===>" + count);
		condition.setLimit(2);
		helpDocDao.searchHelpDocByCondition(condition);

	}
}
