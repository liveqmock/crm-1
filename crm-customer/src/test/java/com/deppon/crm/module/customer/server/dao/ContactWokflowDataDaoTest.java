/**   
 * @title ContactWokflowDataDaoTest.java
 * @package com.deppon.crm.module.customer.server.dao
 * @description what to do
 * @author 潘光均
 * @update 2012-7-16 下午1:38:56
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.crm.module.customer.shared.domain.Contact;

/**
 * @description fuction  
 * @author 潘光均
 * @version 0.1 2012-7-16
 *@date 2012-7-16
 */

public class ContactWokflowDataDaoTest extends BeanUtil{
	ApprovingWorkflowData data;
	/**
	 * @description setUp.  
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return void
	 * @update 2012-7-16 下午1:38:56
	 */
	@Before
	public void setUp() throws Exception {
		
		Contact contact = TestUtils.createBean(Contact.class);
		Assert.assertNotNull(approvingWokflowDataDao);
		 data =TestUtils.createBean(ApprovingWorkflowData.class);
		 data.setContactId(contact);
	}

	/**
	 * @description tearDown.  
	 * @author 潘光均
	 * @version 0.1 2012-7-16
	 * @param 
	 *@date 2012-7-16
	 * @return void
	 * @update 2012-7-16 下午1:38:56
	 */
	@After
	public void tearDown() throws Exception {
		approvingWokflowDataDao.deleteConWorkflowData(data.getId());
	}

	/**
	 * Test method for {@link com.deppon.crm.module.customer.server.dao.impl.ApprovingWorkflowDataDao#insertContactWorkflowData(com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData)}.
	 */
	@Test
	public void testInsertContactWorkflowData() {
		ApprovingWorkflowData newData = approvingWokflowDataDao.insertContactWorkflowData(data);
		ApprovingWorkflowData databaseData= approvingWokflowDataDao.queyById(newData.getId());
		System.out.println(newData);
		System.out.println(databaseData);
		Assert.assertEquals(newData, databaseData);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.customer.server.dao.impl.ApprovingWorkflowDataDao#updateConWorkflowData(com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData)}.
	 */
	@Test
	public void testUpdateConWorkflowData() {
		ApprovingWorkflowData newData = approvingWokflowDataDao.insertContactWorkflowData(data);
		newData.setContactMobile("13524073834");
		newData.setContactTel("010-1010110");
		boolean result = approvingWokflowDataDao.updateConWorkflowData(newData);
		Assert.assertTrue(result);
		ApprovingWorkflowData databaseData= approvingWokflowDataDao.queyById(newData.getId());
		
		data.setContactMobile("13524073834");
		data.setContactTel("010-1010110");
		Assert.assertEquals(data, databaseData);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.customer.server.dao.impl.ApprovingWorkflowDataDao#deleteConWorkflowData(java.lang.String)}.
	 */
	@Test
	public void testDeleteConWorkflowData() {
		ApprovingWorkflowData newData = approvingWokflowDataDao.insertContactWorkflowData(data);
		ApprovingWorkflowData databaseData= approvingWokflowDataDao.queyById(newData.getId());
		Assert.assertNotNull(databaseData);
		
		approvingWokflowDataDao.deleteConWorkflowData(data.getId());
		databaseData= approvingWokflowDataDao.queyById(newData.getId());
		Assert.assertNull(databaseData);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.customer.server.dao.impl.ApprovingWorkflowDataDao#queryConWorkflowData(com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData)}.
	 */
	@Test
	public void testQueryConWorkflowData() {
		approvingWokflowDataDao.insertContactWorkflowData(data);
		ApprovingWorkflowData newData = new ApprovingWorkflowData();
		newData.setContactMobile("1");
		newData.setContactMobile("1");
		newData.setContactName("1");
		List<ApprovingWorkflowData> databaseDatas = approvingWokflowDataDao.queryConWorkflowData(newData);
		Assert.assertEquals(1, databaseDatas.size());
	}
	
	/**
	 * Test method for {@link com.deppon.crm.module.customer.server.dao.impl.ApprovingWorkflowDataDao#updateByWorkflowId(com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData)}.
	 */
	@Test
	public void testUpdateByWorkflowId() {
		approvingWokflowDataDao.insertContactWorkflowData(data);
		Assert.assertTrue(data.getStatus());
		approvingWokflowDataDao.updateByWorkflowId("1", false);
		ApprovingWorkflowData databaseData= approvingWokflowDataDao.queyById(data.getId());
		Assert.assertFalse(databaseData.getStatus());
	}
}
