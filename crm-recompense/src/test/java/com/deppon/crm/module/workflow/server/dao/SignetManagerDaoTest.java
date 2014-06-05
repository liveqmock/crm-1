package com.deppon.crm.module.workflow.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.workflow.server.dao.impl.SignetManagerDaoImpl;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;

/**
 * 
 * <p>
 * Description:印章管理员设置Dao测试<br />
 * </p>
 * @title SignetManagerDaoTest.java
 * @package com.deppon.crm.module.workflow.server.dao 
 * @author liuHuan
 * @version 0.1 2013-8-1
 */
public class SignetManagerDaoTest extends TestCase {
	
	private static SignetManagerDaoImpl signetManagerDao  ;
	static {
		signetManagerDao= TestUtil.signetManagerDao;
	}

	
	@Test
	public void testFindSignetManager() {
//		Integer empId=null;
		Integer empId=22139;
		List<SignetManager> list=signetManagerDao.findSignetManager(empId,0,9);
		for(SignetManager sm : list){
			System.out.println(sm.getDeptName());
			System.out.println(sm.getEmpCode());
			System.out.println(sm.getEmpName());
		}
		
	}
	
	@Test
	public void testFindSignetManagerCount() {
		Integer empId=null;
//		Integer empId=22139;
		long count=signetManagerDao.findSignetManagerCount(empId);
		System.out.println(count);
		
	}
	
	
	@Test
	public void testAddSignetManager() {
		SignetManager sm = new SignetManager();
		sm.setDeptId(111);
		sm.setEmpId(12);
		signetManagerDao.addSignetManager(sm);
		System.out.println(sm.getId());
		System.out.println(sm.getDeptId());
		System.out.println(sm.getEmpId());
		
	}
	
	@Test
	public void testDeleteSignetManagerById() {
		signetManagerDao.deleteSignetManagerById(52);
	}
	
	
	@Test
	public void testFindEmpCount() {
		System.out.println(signetManagerDao.findEmpCount(12));
	}
}
