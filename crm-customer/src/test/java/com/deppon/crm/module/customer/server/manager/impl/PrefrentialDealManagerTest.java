package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.manager.IPrefrentialDealManager;
import com.deppon.crm.module.customer.server.service.IPrefrentialDealService;
import com.deppon.crm.module.customer.server.service.impl.PrefrentialDealService;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;

public class PrefrentialDealManagerTest extends TestCase{
	private IPrefrentialDealManager prefrentialDealManager 
		= SpringTestHelper.getBean(PrefrentialDealManager.class);
	private IPrefrentialDealService prefrentialDealService 
		= SpringTestHelper.getBean(PrefrentialDealService.class);
	
	@Before
	public void setUp() throws Exception{
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
	 * @Title: testCreatePrefrentialDeal
	 *  <p>
	 * @Description:测试添加合同月发月送优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	*/
	@Test
	public void testCreatePrefrentialDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		PrefrentialDealItem item = new PrefrentialDealItem();
		List<PrefrentialDealItem> dealItems = new ArrayList<PrefrentialDealItem>();
		
		item.setDegree("1");
		item.setItemDesc("测试用的数据而已");
		item.setMaxAmount(100000);
		item.setMinAmount(1);
		item.setRate(0.85);
		dealItems.add(item);
		
		deal.setBeginTime(new Date(ContractUtil.getNowTime(null).getTime()+100));
		deal.setEndTime(new Date(ContractUtil.getNowTime(null).getTime()+1000));
		deal.setDealName("测试方案");
		deal.setDealItems(dealItems);
		prefrentialDealManager.createPrefrentialDeal(deal);
		//查询数据库中是否成功增加改套方案
		Assert.assertNotNull(prefrentialDealManager.searchPrefrentialDealById(deal.getId()));
		//清除测试方法增加的方案
		prefrentialDealService.deletePrefrentialDeal(deal.getId());
		
	}
	/**
	 * 
	 * @Title: testSearchPrefrentialDealById
	 *  <p>
	 * @Description:测试查询合同月发月送优惠方案 <br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchPrefrentialDealById(){
		//正确传入id查询优惠方案
		Assert.assertEquals(prefrentialDealManager.searchPrefrentialDealById("2").getId(),"2");
		//传入空id查询优惠方案
		try {
			prefrentialDealManager.searchPrefrentialDealById("");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.NULLIDERROR.getErrCode(), e.getErrorCode());
		}
	}
	/**
	 * 
	 * @Title: testSearchAllPrefrentialDeal
	 *  <p>
	 * @Description: 测试初始化所有合同月发月送优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchAllPrefrentialDeal(){
		Assert.assertNotNull(prefrentialDealManager.searchAllPrefrentialDeal(new PrefrentialDeal(),0,20));
	}
	/**
	 * 
	 * @Title: testEndPrefrentialDeal
	 *  <p>
	 * @Description: 测试作废合同月发月送优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	@Test
	public void testEndPrefrentialDeal(){
		//测试输入正确值的情况
		prefrentialDealManager.endPrefrentialDeal("2");
		Assert.assertTrue(
				prefrentialDealManager.searchPrefrentialDealById("2").getStatus()
				.equals(Constant.CONTRACT_STATUS_UNEFFECT));
		//测试输入空值的情况
		try {
			prefrentialDealManager.endPrefrentialDeal(null);
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.NULLIDERROR.getErrCode(), e.getErrorCode());
		}
		//测试传入了非有效方案id的情况
		try {
			prefrentialDealManager.endPrefrentialDeal("3");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.NOTEFFECTDEALERROR.getErrCode(), e.getErrorCode());
		}
	}
	/**
	 * 
	 * @Title: testUpdatePrefrentialDeal
	 *  <p>
	 * @Description:测试修改合同月发月送优惠方案 <br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	@Test
	public void testUpdatePrefrentialDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		PrefrentialDealItem preItem = new PrefrentialDealItem();
		preItem.setItemDesc("第二套测试方案的描述");
		preItem.setDegree("1");
		preItem.setMinAmount(99);
		preItem.setMaxAmount(9999);
		List<PrefrentialDealItem> list = new ArrayList<PrefrentialDealItem>();
		list.add(preItem);
		deal.setDealItems(list);
		deal.setId("2");
		//校验当传入对象为空的情况
		try {
			prefrentialDealManager.updatePrefrentialDeal(null);
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.NULLDEALERROR.getErrCode(), e.getErrorCode());
		}
		//校验折扣未修改的情况
		try {
			deal.getDealItems().get(0).setRate(0.98);
			prefrentialDealManager.updatePrefrentialDeal(deal);
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.RATEISNOTCHANGED.getErrCode(), e.getErrorCode());
		}
		//校验正确输入的情况
		//先查到数据库中有效的方案
		PrefrentialDeal dealInDataBase = new PrefrentialDeal();
		dealInDataBase.setStatus(Constant.CONTRACT_STATUS_EFFECT);
		List<PrefrentialDeal> dealList 
			= prefrentialDealService.getEffectPrefrentialDeal(dealInDataBase);
		preItem.setRate(0.7);
		preItem.setId("2");
		//执行修改方法
		prefrentialDealManager.updatePrefrentialDeal(deal);
		//比较折扣率是否修改成功
		Assert.assertTrue(
				prefrentialDealManager.searchPrefrentialDealById(deal.getId()).getDealItems()
				.get(0).getRate() == preItem.getRate());
		//测试完毕清除由于修改产生的垃圾数据
		prefrentialDealService.deletePrefrentialDeal(deal.getId());
		//如果之前存在被作废的有效方案，则恢复被测试作废掉的方案
		//否则就不进行任何操作
		if (!ValidateUtil.objectIsEmpty(dealList)) {
			for(PrefrentialDeal prefrentialDeal:dealList){
				prefrentialDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
				prefrentialDealService.updatePrefrentialDeal(prefrentialDeal);
			}
		}
		//校验不是有效方案的情况
		try {
			deal.setId("3");
			deal.getDealItems().get(0).setId("3");
			deal.getDealItems().get(0).setRate(0.01);
			deal.setStatus("3");
			preItem.setItemDesc("第三套测试方案的描述");
			prefrentialDealManager.updatePrefrentialDeal(deal);
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.ONLYCANMODIFYEFFECTCONTRACT.getErrCode(), e.getErrorCode());
		}
	}
	/**
	 * 
	 * @Title: testDeletePrefrentialDeal
	 *  <p>
	 * @Description: 测试修改合同月发月送优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeletePrefrentialDeal(){
		//删除待生效的方案
		prefrentialDealManager.deletePrefrentialDeal("3");
		Assert.assertNull(
				prefrentialDealManager.searchPrefrentialDealById("3"));
		//删除非待生效方案
		try {
			prefrentialDealManager.deletePrefrentialDeal("2");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.DEALSTATUSERROR.getErrCode(), e.getErrorCode());
		}
		//测试传入空值
		try {
			prefrentialDealManager.deletePrefrentialDeal(null);
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.NULLIDERROR.getErrCode(), e.getErrorCode());
		}
	}
	/**
	 * 
	 * @Title: testSearchEffectPrefrentialDeal
	 *  <p>
	 * @Description: 测试按条件查询优惠方案的方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-13
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchEffectPrefrentialDeal(){
		PrefrentialDeal prefrentialDeal = new PrefrentialDeal();
		prefrentialDeal.setId("2");
		//正常情传入值查询
		Assert.assertNotNull(
			prefrentialDealManager.searchEffectPrefrentialDeal(prefrentialDeal));
		//传入空值查询
		try {
			prefrentialDealManager.searchEffectPrefrentialDeal(null);
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.NULLIDERROR.getErrCode(), e.getErrorCode());
		}
	}
	/**
	 * 
	 * @Title: testCountPrefrentialDeal
	 *  <p>
	 * @Description: 测试统计月发月送新优惠方案条目的方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-14
	 * @return void
	 * @throws
	 */
	@Test
	public void testCountPrefrentialDeal(){
		PrefrentialDeal deal = new PrefrentialDeal();
		deal.setPreferType("MONTH_SEND");
		Assert.assertNotNull(prefrentialDealManager.countPrefrentialDeal(deal));
		
	}
	/**
	 * 
	 * @Title: testActivePrefrentialDeal
	 *  <p>
	 * @Description: 测试方案激活方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-21
	 * @return void
	 * @throws
	 */
	@Test
	public void testActivePrefrentialDeal(){
		//测试输入正常值的时候
		PrefrentialDeal deal = new PrefrentialDeal();
		deal.setStatus(Constant.CONTRACT_STATUS_WAIT_EFFECT);
		deal.setPreferType("MONTH_SEND");
		deal.setId("3");
		//先查到数据库中有效的方案
		List<PrefrentialDeal> dealList 
			= prefrentialDealService.getEffectPrefrentialDeal(deal);
		prefrentialDealManager.activePrefrentialDeal(deal);
		Assert.assertTrue(
				prefrentialDealManager.searchPrefrentialDealById("3").getStatus()
				.equals(Constant.CONTRACT_STATUS_EFFECT));		
		//如果之前存在被作废的有效方案，则恢复被测试作废掉的方案
		//否则就不进行任何操作
		if (!ValidateUtil.objectIsEmpty(dealList)) {
			for(PrefrentialDeal prefrentialDeal:dealList){
				prefrentialDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
				prefrentialDealService.updatePrefrentialDeal(prefrentialDeal);
			}
		}
		//测试传入空值的情况
		try {
			prefrentialDealManager.activePrefrentialDeal(null);
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.NULLIDERROR.getErrCode(), e.getErrorCode());
		}
		//测试传入状态不为待生效的对象
		try {
			PrefrentialDeal pp = new PrefrentialDeal();
			pp.setId("2");
			prefrentialDealManager.activePrefrentialDeal(pp);
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.DEALCANNOTACTIVE.getErrCode(), e.getErrorCode());
		}
	}
	/**
	 * 
	 * @Title: testPrefrentialdealAutoWork
	 *  <p>
	 * @Description: 测试定时器调用的方法<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-22
	 * @return void
	 * @throws
	 */
	@Test
	public void testPrefrentialdealAutoWork(){
		prefrentialDealManager.prefrentialdealAutoWork();
	}
}
