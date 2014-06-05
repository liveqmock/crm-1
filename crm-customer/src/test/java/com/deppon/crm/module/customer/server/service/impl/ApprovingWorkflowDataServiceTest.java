/**
 * 
 */
package com.deppon.crm.module.customer.server.service.impl;


import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.opensymphony.xwork2.interceptor.annotations.After;

/**
 * @author Administrator
 *
 */
public class ApprovingWorkflowDataServiceTest extends BeanUtil{
	ApprovingWorkflowData workflowData = new ApprovingWorkflowData();
	
	@Before
	public void setUp() throws Exception {
		// 初始化修改会员模块数据
		DataTestUtil.cleanContactDaoData();
		DataTestUtil.initAlterMemberData();
	}
	
	@After
	public void tearDown() throws Exception {
		DataTestUtil.cleanContactDaoData();
	}

	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testInsertContactWorkflowData(){
		Assert.assertNotNull(approvingWokflowDataService.insertContactWorkflowData(workflowData));
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */ 
	@Test
	public void testUpdateConWorkflowData(){
		Contact contactId = new Contact();
		workflowData.setId("1234");
		workflowData.setContactName("马关条约");
		workflowData.setContactTel("132321");
		workflowData.setContactMobile("1233445645");
		workflowData.setCredentialsType("IdCard");
		workflowData.setIdCardNo("511622198710001235");
		workflowData.setStatus(true);
		workflowData.setContactId(contactId);
		workflowData.setWorkflowId("08465645");
		workflowData.setContactNum("S1232132121");
		workflowData.setTaxregNumber("4008305555");
		Assert.assertNotNull(approvingWokflowDataService.updateConWorkflowData(workflowData));
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testDeleteConWorkflowData(){
		Assert.assertNotNull(approvingWokflowDataService.deleteConWorkflowData("123654"));
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testQueryConWorkflowData(){
		Assert.assertNotNull(approvingWokflowDataService.queryConWorkflowData(workflowData));
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testQueyById(){
		approvingWokflowDataService.queyById("32156798646354");
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testUpdateByWorkflowId(){
		approvingWokflowDataService.updateByWorkflowId("12312", true);
		approvingWokflowDataService.updateByWorkflowId("12312", false);
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetExistInfoCount(){
		//1.输入参数条件不为空 ，输出：list.size
		ApprovingWorkflowData app = this.createObject("113254654624", "1324646546", null);
		approvingWokflowDataService.getExistInfoCount(app);
		
		//2.输入参数条件为空， 输出：0
		app = this.createObject(null, null, null);
		approvingWokflowDataService.getExistInfoCount(app);
	}
	/**
	 * @description批量插入工作流审批的联系人信息
	 @author  唐亮
	 @date 2012-12-27
	 @return void
	 */
	@Test
	public void testBatchCreateContactWorkflowData() {
		//1.输入 参数条件不为空，输出：成功
		ApprovingWorkflowData app = this.createObject("113254654624", "1324646546", null);
		ApprovingWorkflowData app2 = this.createObject("113254654624", "1324646546", null);
		Set<ApprovingWorkflowData> list = new HashSet<ApprovingWorkflowData>();
		list.add(app);
		approvingWokflowDataService.batchCreateContactWorkflowData(list);
		
		//2.输入：参数条件为空， 输出：不执行代码块
		list = new HashSet<ApprovingWorkflowData>();
		approvingWokflowDataService.batchCreateContactWorkflowData(list);
		
		//
	}
	
	/**
	 @Discript  构造实例，需要清理新增的数据
	 @author  唐亮
	 @date 2012-12-27
	 @return ApprovingWorkflowData
	 */
	public static ApprovingWorkflowData createObject(String contactMobile,String contactTel,String id) {
		ApprovingWorkflowData app = new ApprovingWorkflowData();
		app.setContactMobile(contactMobile);
		app.setContactTel(contactTel);
		app.setId(id);
		return app;
	}
}