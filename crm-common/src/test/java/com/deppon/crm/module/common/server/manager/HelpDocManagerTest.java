/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title HelpDocManagerTest.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-26
 */
package com.deppon.crm.module.common.server.manager;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.manager.impl.HelpDocManager;
import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title HelpDocManagerTest.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-26
 */

public class HelpDocManagerTest extends TestCase {
	private static IHelpDocManager helpDocManager;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"CommonBeanTest.xml"};
	static String num = String.valueOf(System.currentTimeMillis());
	static String helpId = "0";
	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			helpDocManager = ctx.getBean(HelpDocManager.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddHelpDoc(){
		HelpDoc doc = new HelpDoc();
		doc.setHelpTitle("ZPJ Test Case");
		try {
			// doc is null
			helpDocManager.addHelpDoc(null);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// doc.getWindowNum() is null
			helpDocManager.addHelpDoc(doc);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 新增help
			doc.setWindowNum(num);
			helpDocManager.addHelpDoc(doc);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 新增help，重复num
			doc.setWindowNum(num);
			helpDocManager.addHelpDoc(doc);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		helpId = doc.getId();
	}
	
	@Test
	public void testUpdateHelpDocById(){
		HelpDoc doc = new HelpDoc();
		doc.setHelpTitle("ZPJ Test Case");
		try {
			// doc is null
			helpDocManager.updateHelpDocById(null);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// doc.getWindowNum() is null
			helpDocManager.updateHelpDocById(doc);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// oldHelp == null
			doc.setWindowNum("12121");
			helpDocManager.updateHelpDocById(doc);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 修改
			doc.setWindowNum("1355716339046");
			helpDocManager.updateHelpDocById(doc);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 修改
			doc.setId("61");
			doc.setWindowNum("1355716339046");
			helpDocManager.updateHelpDocById(doc);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testSearchHelpDocByCondition(){
		HelpDocSearchCondition condition = new HelpDocSearchCondition();
		condition.setHelpTitle("【潜客管理页面】");
		helpDocManager.searchHelpDocByCondition(condition);
	}
	
	@Test
	public void testGetHelpDocById(){
		// 根据ID查询
		helpDocManager.getHelpDocById(helpId);
	}

	@Test
	public void testGetHelpDocByWindowNum(){
		// 根据窗号查询
		helpDocManager.getHelpDocByWindowNum("123");
	}
	
	@Test
	public void testGetCountByCondition(){
		HelpDocSearchCondition condition = new HelpDocSearchCondition();
		condition.setHelpTitle("【潜客管理页面】");
		helpDocManager.getCountByCondition(condition);
	}
	
	@Test
	public void testDeleteHelpDocByIds(){
		helpDocManager.deleteHelpDocByIds(new String[]{"0", helpId});
	}

	@Test
	public void testDeleteHelpDocById(){
		helpDocManager.deleteHelpDocById("0");
	}
	
	@Test
	public void testGetFunction(){
		HelpDocManager hdm = new HelpDocManager();
		hdm.getHelpDocService();
	}
}
