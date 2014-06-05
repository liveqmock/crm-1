package com.deppon.crm.module.authorization.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.IRoleDao;
import com.deppon.crm.module.authorization.server.dao.IVirtualPositionDao;
import com.deppon.crm.module.authorization.server.dao.impl.RoleDao;
import com.deppon.crm.module.authorization.server.dao.impl.VirtualPositionDao;
import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.VirtualMapEhrPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRole;
import com.deppon.crm.module.authorization.shared.domain.VirtualPositionRoleVo;
public class VirtualPositionDaoTest {
	private static ApplicationContext factory;
	private IVirtualPositionDao virtualPositionDao;
	private IRoleDao roleDao;
//	private ICompanyDao companyDao;
	private static String virtualPositionId;
	@Before
	public void setUp(){
		String[] resource = new String[] { "authorization/virtualpositionBeanTest.xml" };
		if (factory == null) {
			factory = new ClassPathXmlApplicationContext(resource);
		}
		virtualPositionDao = factory.getBean(VirtualPositionDao.class);
		roleDao = factory.getBean(RoleDao.class);
//		companyDao = factory.getBean(CompanyDaoImp.class);
	}
	
//	@Test
//	public void testAddCompany(){
////		CompanyInfo com = new CompanyInfo();
//		com.setBriefintro("fsdfsd");
//		com.setChangeDate(new Date());
//		com.setTheMainId("3334");
//		com.setTheMainCode("543543");
//		companyDao.insert(com);
//	}
	
	@Test
	public void testInsert() {
		VirtualPosition vp = new VirtualPosition();
		vp.setCreateDate(new Date());
		vp.setCreateUser("1234");
		vp.setDesc("虚拟岗位");
		vp.setModifyDate(new Date());
		vp.setModifyUser("5678");
		vp.setVirtualPositionName("营业部经理");
		System.out.println(vp);
		vp = virtualPositionDao.insert(vp);
		virtualPositionId = vp.getId();
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		VirtualPosition vp = virtualPositionDao.selectByPrimaryKey(virtualPositionId);
		vp.setVirtualPositionName("叉车死机");
		virtualPositionDao.updateByPrimaryKeySelective(vp);
		vp = virtualPositionDao.selectByPrimaryKey(virtualPositionId);
		System.out.println(vp.getVirtualPositionName());
		String name = vp.getVirtualPositionName();
		virtualPositionDao.deleteByPrimaryKey(virtualPositionId);
		vp = virtualPositionDao.selectByPrimaryKey(virtualPositionId);
		Assert.assertNull(vp);
		boolean b = virtualPositionDao.isExistVirtualPositionName(name,vp.getId());
		Assert.assertFalse(b);
	}
	
	@Test
	public void testGetRolesByVpId(){
		String vpId = "12345";
		List<VirtualPositionRole>  roles =  virtualPositionDao.getRolesByVpId(vpId);
		Assert.assertNotNull(roles);
		System.out.println(CollectionUtils.isEmpty(roles));
	}
	
	@Test
	public void testGetStaPositionHaveMappedWithVP(){
		String vpId = "1234";
		int start = 0;
		int limit = 20;
		List<EhrPosition> list = virtualPositionDao.getStaPositionHaveMappedWithVP(vpId);
		int i = virtualPositionDao.countStaPositionHaveMappedWithVP(vpId);
		System.out.println(list.size());
		System.out.println(i);
		list = virtualPositionDao.getStaPositionWaitToMapWithVP(vpId,"");
		System.out.println(list.size());
	}
	
	@Test
	public void testQueryVirtualPosition(){
		int start = 0,limit = 20;
		String positionName ="虚拟岗位";
		String ehrPositionName = "";
		List<VirtualPosition> list = virtualPositionDao.queryVirtualPosition(positionName, ehrPositionName, start, limit);
		int count = virtualPositionDao.countVirtualPosition(positionName, ehrPositionName);
		System.out.println(list.size());
		System.out.println(count);
		positionName ="";
	    ehrPositionName = "标准岗位";
	    list = virtualPositionDao.queryVirtualPosition(positionName, ehrPositionName, start, limit);
	    count = virtualPositionDao.countVirtualPosition(positionName, ehrPositionName);
	    System.out.println(list.size());
		System.out.println(count);

	}
	
	@Test
	public void testList(){
		String[] arr1 = new String[]{"2","3","4","5","6"};
		String[] arr2 = new String[]{"1","3","5","7"};
		List<String> list1 = Arrays.asList(arr1);
		List<String> list2 = Arrays.asList(arr2);
		List<String> l1 = (List<String>) CollectionUtils.retainAll(list1, list2);//3,5
		List<String> l2 = (List<String>) CollectionUtils.removeAll(list1, list2);//2,4,6
		List<String> l3 = (List<String>) CollectionUtils.intersection(list1, list2);//3,5
		List<String> l4 = (List<String>) CollectionUtils.subtract(list1, list2);
		System.out.println(l1.toArray());
		System.out.println(l2.toArray());
	}
	
	@Test
	public void testAddList(){
		String[] oldArr = new String[]{"2","5"};
		String[] newArr = new String[]{"1","2","3","4"};
		List<String> oldList = Arrays.asList(oldArr);
		List<String> newList = Arrays.asList(newArr);
		List<String> toAdd = this.toAddList(newList, oldList);
		List<String> toDelete =  this.toDeleteList(newList, oldList);
		System.out.println(toAdd);
		System.out.println(toDelete);
	}
	
	/**
	 * 
	 * <p>
	 * 找到需要新增的列表<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param newList
	 * @param orgList
	 * @return
	 * List<String>
	 */
	public List<String> toAddList(List<String> newList,List<String> orgList){
		List<String> result = new ArrayList<String>();
		for(String str : newList){
			if(!orgList.contains(str)){
				result.add(str);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * <p>
	 * 找到需要删除的列表<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param newList
	 * @param orgList
	 * @return
	 * List<String>
	 */
	public List<String> toDeleteList(List<String> newList,List<String> orgList){
		List<String> result = new ArrayList<String>();
		for(String str : orgList){
			if(!newList.contains(str)){
				result.add(str);
			}
		}
		return result;
	}
	
	@Test
	public void testDeleteVirtualMapStandard(){
		VirtualMapEhrPosition vmp = new VirtualMapEhrPosition();
		vmp.setSpid("123");
		vmp.setVpid("456");
		virtualPositionDao.deleteVirtualMapStandard(vmp);
	}
	
	@Test
	public void testAddVirtualMapStandard(){
		VirtualMapEhrPosition vmp = new VirtualMapEhrPosition();
		vmp.setSpid("123");
		vmp.setVpid("456");
		virtualPositionDao.addVirtualMapStandard(vmp);
	}
	
	@Test
	public void testQueryVirPositionRole(){
		int start = 0;
		int limit = 20;
		String virPositionName = "";
		String roleName = "";
		List<VirtualPositionRoleVo> list = virtualPositionDao.queryVirPositionRole(virPositionName,roleName, start, limit);
		int count = virtualPositionDao.countQueryVirPositionRole(virPositionName, roleName);
		System.out.println(list);
		System.out.println(count);
		
		virPositionName = "";
		roleName = "责任大区统计员测试";
		list = virtualPositionDao.queryVirPositionRole(virPositionName,roleName, start, limit);
		count = virtualPositionDao.countQueryVirPositionRole(virPositionName, roleName);
		System.out.println(list);
		System.out.println(count);

	}
	
	@Test
	public void testQueryVirtualPositionByName(){
		String virtualPositionName = "lala";
		String vpId = "99";
		int start = 0;
		int limit = 20;
		boolean b = virtualPositionDao.isExistVirtualPositionName(virtualPositionName,vpId);
		
		
		
		List<VirtualPosition> list = virtualPositionDao.queryVirtualPositionByName(virtualPositionName, start, limit);
		int count = virtualPositionDao.countQueryVirtualPositionByName(virtualPositionName);
		for(VirtualPosition vp : list){
			System.out.println(vp.getVirtualPositionName());			
		}
		System.out.println(count);
	}
	
	@Test
	public void testQueryRoleByVirtualPositionName(){
		String virtualPositionId="";
		String roleName = "责任";
		String isMap = "NO";
		
		List<Role> roles = roleDao.queryRoleByVirtualPositionName(virtualPositionId, roleName,isMap);
		System.out.println(roles);
		
		virtualPositionId = "100";
		isMap = "YES";
		roles = roleDao.queryRoleByVirtualPositionName(virtualPositionId, roleName,isMap);
		System.out.println(roles);
	}
	
	@Test
	public void testRefreshUserRole(){
		try{
			String virtualPositionId = "123";
			String result = virtualPositionDao.refreshUserRole(virtualPositionId);
			System.out.println(result);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
