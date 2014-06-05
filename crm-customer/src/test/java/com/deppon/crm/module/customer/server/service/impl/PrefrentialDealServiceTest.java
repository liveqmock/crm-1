package com.deppon.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.service.IPrefrentialDealService;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;

public class PrefrentialDealServiceTest extends TestCase{
	private IPrefrentialDealService prefrentialDealService;
	@Before
	public void setUp() throws Exception{
		prefrentialDealService = SpringTestHelper.getBean(
				PrefrentialDealService.class);
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
	 * @Title: testCreatePrefrentialDeal
	 *  <p>
	 * @Description: 测试新优惠方案创建的方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testCreatePrefrentialDeal(){
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
		//执行创建操作
		prefrentialDealService.createPrefrentialDeal(deal);
		//校验数据库中是否存在该套方案和基础资料
		PrefrentialDeal pDeal = prefrentialDealService.searchPrefrentialDealById("1");
		Assert.assertNotNull(pDeal);
		Assert.assertNotNull(pDeal.getDealItems());
	}
	/**
	 * 
	 * @Title: testUpdatePrefrentialDeal
	 *  <p>
	 * @Description: 测试优惠方案修改的方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testUpdatePrefrentialDeal(){
		PrefrentialDeal prefrentialDeal = prefrentialDealService.searchPrefrentialDealById("2");
		prefrentialDeal.setModifyUser("105873");
		//执行修改操作
		prefrentialDealService.updatePrefrentialDeal(prefrentialDeal);
		//校验是否修改成功
		Assert.assertTrue(
				prefrentialDealService.searchPrefrentialDealById("2")
				.getModifyUser().equals("105873"));
	}
	
	/**
	 * 
	 * @Title: testActivePrefrentialDeal
	 *  <p>
	 * @Description: 测试优惠方案激活的方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testActivePrefrentialDeal(){
		PrefrentialDeal prefrentialDeal = new PrefrentialDeal();
		prefrentialDeal.setStatus("1");
		prefrentialDeal.setId("3");
		//执行激活操作
		prefrentialDealService.activePrefrentialDeal(prefrentialDeal);
		//查看状态是否已激活
		Assert.assertTrue(prefrentialDealService.searchPrefrentialDealById("3")
				.getStatus().equals("1"));
	}
	/**
	 * 
	 * @Title: testSearchAllPrefrentialDeal
	 *  <p>
	 * @Description: 初始化所有优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchAllPrefrentialDeal(){
		Assert.assertNotNull(
				prefrentialDealService.searchAllPrefrentialDeal(new PrefrentialDeal(),0, Integer.MAX_VALUE));
	}
	/**
	 * 
	 * @Title: testSearchPrefrentialDealById
	 *  <p>
	 * @Description: 通过id查询优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchPrefrentialDealById(){
		Assert.assertNotNull(
				prefrentialDealService.searchPrefrentialDealById("2"));
	}
	/**
	 * 
	 * @Title: testEndPrefrentialDeal
	 *  <p>
	 * @Description: 通过id作废优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-20
	 * @return void
	 * @throws
	 */
	@Test
	public void testEndPrefrentialDeal(){
		PrefrentialDeal prefrentialDeal = new PrefrentialDeal();
		prefrentialDeal.setId("2");
		prefrentialDeal.setStatus("2");
		//执行作废操作
		prefrentialDealService.endPrefrentialDeal(prefrentialDeal);
		//校验是否作废成功
		Assert.assertTrue(
				prefrentialDealService.searchPrefrentialDealById("2")
				.getStatus().equals("2"));
	}
	
}	
