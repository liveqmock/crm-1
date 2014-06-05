package com.deppon.crm.module.duty.dao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

import com.deppon.crm.module.duty.server.dao.IInformUserDao;
import com.deppon.crm.module.duty.server.util.DateCreateUtils;
import com.deppon.crm.module.duty.shared.domain.InformUser;
import com.deppon.crm.module.duty.util.TestUtil;

public class InformUserDaoTest extends TestCase{

	private IInformUserDao informUserDao =  TestUtil.informUserDao;;
	
	
	//通知对象结果查询
	@Test
	public void testSearchInformUser(){
		InformUser informUser = DateCreateUtils.createBean(InformUser.class);
		informUserDao.searchInformUser(informUser);
	}
	
	//通知对象结果查询
	@Test
	public void testSelectInformUserByPrimaryKey(){
		informUserDao.selectInformUserByPrimaryKey(new BigDecimal(111));
	}
	
	//通知对象修改
	@Test
	public void testUpdateInformUser(){
		//InformUser informUser = DateCreateUtils.createBean(InformUser.class);
		//informUserDao.updateInformUser(informUser);
	}

	//通知对象删除
	@Test
	public void testDeleteInformUsers(){
		List<String> ids = new ArrayList<String>();
		ids.add("111");
		informUserDao.deleteInformUsers(ids);
	}
	
	//通知对象删除
	@Test
	public void testDeleteInformUsersByDutyId(){
		informUserDao.deleteInformUsersByDutyId("111");
	}
	
	
	//通知对象新增
	@Test
	public void testInsertInformUser(){
		InformUser informUser = DateCreateUtils.createBean(InformUser.class);
		informUser.setId("22222");
		informUserDao.insertInformUser(informUser);
	}
	
}


