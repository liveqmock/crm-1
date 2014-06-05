package com.deppon.crm.module.customer.server.dao.impl;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;

public class ContactVaryDaoTest extends BeanUtil{
	public String id;
	
	public void setUp(){
		id = contactVaryDao.getContactVaryId();
		try {
			ContactVary contactVary = TestUtils.createBean(ContactVary.class);
			contactVary.setId(id);
			contactVaryDao.insertContactVary(contactVary);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	public void tearDown(){
		String sql ="delete from t_cust_contactvary where fid='"+id+"'";
		try {
			SpringTestHelper.getConnection().createStatement().execute(sql);
		} catch (SQLException e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetContactVaryById(){
		ContactVary c = contactVaryDao.getContactVaryById(id);
		assertNotNull(c);
	}
	@Test
	public void testSearchContactVarys(){
		contactVaryDao.searchContactVarys(contactVaryDao.getContactVaryById(id));
	}
	@Test
	public void testGetContactVaryId() {
		String id = contactVaryDao.getContactVaryId();
		Assert.assertNotNull(id);
	}
	@Test
	public void testIsContactVary2Member() {
		String memberId = "123";
		Assert.assertNull(contactVaryDao.isContactVary2Member(memberId));
	}
}

