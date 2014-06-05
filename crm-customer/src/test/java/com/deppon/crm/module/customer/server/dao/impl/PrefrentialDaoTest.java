package com.deppon.crm.module.customer.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.dao.IPrefrentialDealDao;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;

public class PrefrentialDaoTest extends TestCase{
	private IPrefrentialDealDao prefrentialDealDao;
	@Before
	public void setUp() throws Exception{
		prefrentialDealDao = SpringTestHelper.getBean(
				PrefrentialDealDao.class);
		//初始化DAO层测试数据
		DataTestUtil.cleanPrefrentialDaoData();
		DataTestUtil.initPrefrentialDaoData();
	}
	@After
	public void cleanData() throws Exception{
		//清理DAO层数据
		DataTestUtil.cleanPrefrentialDaoData();
	}
	/**
	 * 
	 * @Title: testSavePrefrentialDeal
	 *  <p>
	 * @Description: 月发月送新优惠方案保存<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testSavePrefrentialDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		PrefrentialDealItem item = new PrefrentialDealItem();
		List<PrefrentialDealItem> dealItems = 
				new ArrayList<PrefrentialDealItem>();
		
		item.setDealId("1");
		item.setDegree("1");
		item.setItemDesc("测试用的数据而已");
		item.setMaxAmount(100000);
		item.setMinAmount(1);
		item.setModifyDate(new Date());
		item.setCreateDate(new Date());
		item.setCreateUser("86301");
		item.setModifyUser("86301");
		item.setRate(0.85);
		item.setId("1");
		dealItems.add(item);
		
		deal.setBeginTime(new Date());
		deal.setCreateDate(new Date());
		deal.setCreateUser("105873");
		deal.setDealName("测试方案");
		deal.setDealNumber("000001");
		deal.setEndTime(new Date());
		deal.setId("1");
		deal.setModifyDate(new Date());
		deal.setModifyUser("86301");
		deal.setStatus("1");
		deal.setDealItems(dealItems);
		
		prefrentialDealDao.savePrefrentialDeal(deal);
		//由于优惠方案和基础资料是关联的，在插入优惠方案时必须有对应的基础资料
		testAddPrefrentialDealItem();
		//查询优惠方方案和基础资料是否保存成功
		PrefrentialDeal pDeal = prefrentialDealDao.queryPrefrentialDealById("1");
		Assert.assertEquals(deal.getStatus(),pDeal.getStatus());
		Assert.assertEquals(item.getItemDesc(),pDeal.getDealItems().get(0).getItemDesc());
		
	}
	/**
	 * 
	 * @Title: testAddPrefrentialDealItem
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料保存<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testAddPrefrentialDealItem(){
		PrefrentialDealItem item = new PrefrentialDealItem();
		
		item.setDealId("1");
		item.setDegree("2");
		item.setItemDesc("测试用的数据而已");
		item.setMaxAmount(100000);
		item.setMinAmount(1);
		item.setModifyDate(new Date());
		item.setCreateDate(new Date());
		item.setCreateUser("86301");
		item.setModifyUser("86301");
		item.setRate(0.85);
		item.setId("002");
		//执行基础资料保存操作
		prefrentialDealDao.addPrefrentialDealItem(item);
	}
	/**
	 * 
	 * @Title: testQueryPrefrentialDealById
	 *  <p>
	 * @Description: 测试通过Id查找优惠方案的方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testQueryPrefrentialDealById(){
		Assert.assertNotNull(
		prefrentialDealDao.queryPrefrentialDealById("2"));
	}
	/**
	 * 
	 * @Title: testModifyPrefrentialDeal
	 *  <p>
	 * @Description: 测试优惠方法修改方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testModifyPrefrentialDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		PrefrentialDealItem item = new PrefrentialDealItem();
		List<PrefrentialDealItem> dealItems = 
				new ArrayList<PrefrentialDealItem>();
		
		item.setDealId("2");
		item.setDegree("1");
		item.setItemDesc("修改测试用的数据而已");
		item.setMaxAmount(100000);
		item.setMinAmount(1);
		item.setModifyDate(new Date());
		item.setCreateDate(new Date());
		item.setCreateUser("86301");
		item.setModifyUser("105873");
		item.setRate(0.85);
		item.setId("2");
		dealItems.add(item);
		
		deal.setBeginTime(new Date());
		deal.setCreateDate(new Date());
		deal.setCreateUser("86301");
		deal.setDealName("测试方案");
		deal.setDealNumber("000001");
		deal.setEndTime(new Date());
		deal.setId("2");
		deal.setModifyDate(new Date());
		deal.setModifyUser("105873");
		deal.setStatus("1");
		deal.setDealItems(dealItems);
		//执行修改操作
		prefrentialDealDao.modifyPrefrentialDeal(deal);
		testModifyPrefrentialDealItem();
		//校验修改结果是否和预期一样
		PrefrentialDeal preDeal = prefrentialDealDao.queryPrefrentialDealById("2");
		Assert.assertTrue(preDeal.getModifyUser().equals(
				deal.getModifyUser()));
		Assert.assertTrue(preDeal.getDealItems().get(0).getModifyUser().equals(
				deal.getDealItems().get(0).getModifyUser()));
	}
	/**
	 * 
	 * @Title: testModifyPrefrentialDealItem
	 *  <p>
	 * @Description: 基础资料测试方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testModifyPrefrentialDealItem(){
		PrefrentialDealItem item = new PrefrentialDealItem();
		
		item.setDealId("1");
		item.setDegree("1");
		item.setItemDesc("修改测试用的数据而已");
		item.setMaxAmount(100000);
		item.setMinAmount(1);
		item.setModifyDate(new Date());
		item.setCreateDate(new Date());
		item.setCreateUser("86301");
		item.setModifyUser("105873");
		item.setRate(0.85);
		item.setId("2");
		//执行修改基础资料操作
		prefrentialDealDao.modifyPrefrentialDealItem(item);
	}
	/**
	 * 
	 * @Title: testEndPrefrential
	 *  <p>
	 * @Description: 通过Id作废优惠方案测试方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testEndPrefrential(){
		PrefrentialDeal deal = new PrefrentialDeal();
		deal.setId("2");
		deal.setStatus("2");
		//执行作废操作
		prefrentialDealDao.endPrefrential(deal);
		//校验作废结果是否和预期一致
		Assert.assertTrue(prefrentialDealDao.queryPrefrentialDealById("2")
				.getStatus().equals(deal.getStatus()));
	}
	/**
	 * 
	 * @Title: testActivePrefrentialDeal
	 *  <p>
	 * @Description: 方案生效测试方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testActivePrefrentialDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		deal.setStatus("1");
		deal.setId("3");
		//执行激活操作
		prefrentialDealDao.activePrefrentialDeal(deal);
		//校验激活的结果是否和预期的一样
		Assert.assertTrue(prefrentialDealDao.queryPrefrentialDealById("3")
				.getStatus().equals(deal.getStatus()));
	}
	/**
	 * 
	 * @Title: testSearchAllDeals
	 *  <p>
	 * @Description: 查询出所有优惠方案测试方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchAllDeals(){
		Assert.assertNotNull(prefrentialDealDao.searchAllDeals(new PrefrentialDeal(),0, 20));
	}
	/**
	 * 
	 * @Title: testDeletePrefrentialDeal
	 *  <p>
	 * @Description: 优惠方法删除方法测试<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-19
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeletePrefrentialDeal(){
		//执行删除操作
		prefrentialDealDao.deletePrefrentialDeal("3");
		//在数据库中不应再查询到该条信息
		Assert.assertNull(prefrentialDealDao.queryPrefrentialDealById("3"));
	}
	/**
	 * 
	 * @Title: testDeletePrefrentialDealItem
	 *  <p>
	 * @Description: 基础资料删除操作<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeletePrefrentialDealItem(){
		//执行删除操作
		prefrentialDealDao.deletePrefrentialDealItem("3");
		//此时数据库中不应再有该条数据
		Assert.assertNull(prefrentialDealDao.queryPrefrentialDealById("3"));
	}
	/**
	 * 
	 * @Title: testGetEffectPrefrentialDeal
	 *  <p>
	 * @Description: 根据传入条件查询优惠方案测试<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testGetEffectPrefrentialDeal(){
		PrefrentialDeal preDeal = new PrefrentialDeal();
		preDeal.setId("3");
		Assert.assertNotNull(
				prefrentialDealDao.getEffectPrefrentialDeal(preDeal));
	}
	/**
	 * 
	 * @Title: testCountPrefrentialDeal
	 *  <p>
	 * @Description: 统计优惠方案条数测试<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testCountPrefrentialDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		deal.setPreferType("MONTH_SEND");
		Assert.assertNotNull(
				prefrentialDealDao.countPrefrentialDeal(deal));
		
	}
	/**
	 * 
	 * @Title: testEndEffectiveDeal
	 *  <p>
	 * @Description: 作废有效方案测试<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testEndEffectiveDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		deal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
		// 先拿到数据库中之前有效的方案
		List<PrefrentialDeal> deaList 
			= prefrentialDealDao.getEffectPrefrentialDeal(deal);
		PrefrentialDeal prefrentialDeal = new PrefrentialDeal();
		prefrentialDeal.setId("2");
		prefrentialDeal.setStatus("2");
		//执行作废操作
		prefrentialDealDao.endEffectiveDeal(prefrentialDeal);
		Assert.assertTrue(
			prefrentialDealDao.queryPrefrentialDealById("2")
					.getStatus().equals(prefrentialDeal.getStatus())
				);
		//如果确实存在有效数据被干掉了,则恢复
		//否则就不进行任何操作了
		if (!ValidateUtil.objectIsEmpty(deaList)) {
			for(PrefrentialDeal pDeal:deaList){
				pDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
				prefrentialDealDao.modifyPrefrentialDeal(pDeal);
			}
		}
	}
}
