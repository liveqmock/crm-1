package com.deppon.crm.module.order.manager;

import java.util.ArrayList;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.order.server.manager.OrderValidator;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.crm.module.order.util.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.AppContext;
import com.deppon.foss.framework.server.context.UserContext;
/**
 * @description 修改原有的spring注入模式，是spring顺利注入
 * @author 张斌
 * @原有内容：
 * public class OrderValidatorTest extends TestCase {
	private OrderValidator validator;
	private Order order;
	static UserDao userDao;
	
	static{
		userDao = TestUtil.userDao;
	}
 * @version 0.1 2013-7-27 16:50
 */
//begin
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml")
public class OrderValidatorTest extends AbstractTransactionalJUnit4SpringContextTests {
	static {
		AppContext.initAppContext("application", "","");
	}
	@Autowired
	private OrderValidator orderValidator;
	private Order order;
	@Autowired
	private UserDao userDao;
//end	
	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param b
	 *            true or false
	 * @date 2012-3-19
	 * @return none
	 * @update 2012-3-19 下午5:24:47
	 */
	@Before
	public void setUp() throws Exception {
		OrderValidator validator = new OrderValidator();
		// 变量
		Integer int_ = 1;
		String str_ = "2";
		boolean boo_ = true;
		Date date_ = new Date(2012, 3, 9);
		Double double_ = 1D;
		// 构造订单对象
		order = new Order();
		order.setHastenCount(int_);
		order.setLastHastenTime(date_);
		order.setContactManId(str_);
		order.setShipperId(str_);
		order.setShipperNumber(str_);
		order.setShipperName(str_);
		order.setContactName(str_);
		order.setContactMobile(str_);
		order.setContactPhone(str_);
		order.setContactProvince(str_);
		order.setContactCity(str_);
		order.setContactArea(str_);
		order.setContactAddress(str_);
		order.setIsReceiveGoods(boo_);
		order.setBeginAcceptTime(date_);
		order.setEndAcceptTime(date_);
		order.setReceiverCustId(str_);
		order.setReceiverCustNumber(str_);
		order.setReceiverCustcompany(str_);
		order.setReceiverCustName(str_);
		order.setReceiverCustMobile(str_);
		order.setReceiverCustPhone(str_);
		order.setReceiverCustProvince(str_);
		order.setReceiverCustCity(str_);
		order.setReceiverCustArea(str_);
		order.setReceiverCustAddress(str_);
		order.setReceivingToPoint(str_);
		order.setTransportMode(str_);
		order.setGoodsName(str_);
		order.setPacking(str_);
		order.setGoodsType(str_);
		order.setGoodsNumber(int_);
		order.setTotalWeight(double_);
		order.setTotalVolume(double_);
		order.setPaymentType(str_);
		order.setChannelCustId(str_);
		order.setDeliveryMode(str_);
		order.setReciveLoanType(str_);
		order.setReviceMoneyAmount(double_);
		order.setInsuredAmount(double_);
		order.setOrderTime(date_);
		order.setStartStation(str_);
		order.setAcceptDept(str_);
		order.setOrderStatus(str_);
		order.setDealPerson(str_);
		order.setOrderAcceptTime(date_);
		order.setReceiver(str_);
		order.setAccepterPersonMobile(str_);
		order.setResource(str_);
		order.setTransNote(str_);
		order.setReturnBillType(str_);
		order.setCreateDate(date_);
		order.setCreateUser(str_);
		order.setOrderNumber(str_);
		order.setModifyDate(date_);
		order.setModifyUser(str_);
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param b
	 *            true or false
	 * @date 2012-3-19
	 * @return none
	 * @update 2012-3-19 下午5:24:47
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#validateOrderInfoComplete(com.deppon.crm.module.order.shared.domain.Order)}
	 * .
	 */
	@Test
	public void testCheckInsuredAmountUpdateable() {
		Double newInsuredAmount=null;
		Double oldInsuredAmount=null;
		//1.两个都为空
		assertTrue(orderValidator.checkInsuredAmountUpdateable(
							newInsuredAmount, oldInsuredAmount));
		//2.oldInsuredAmount不为空
		oldInsuredAmount=1D;
		 try {
			 assertFalse(orderValidator.checkInsuredAmountUpdateable(
					 	newInsuredAmount, oldInsuredAmount));
			 fail("异常木有");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_INSUREDAMOUNT_CANNOTUPDATE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		 //3.newInsuredAmount不为空
		 oldInsuredAmount=null; 
		 newInsuredAmount=1D;
		try {
			 orderValidator.checkInsuredAmountUpdateable(
					 newInsuredAmount, oldInsuredAmount);
			 fail("飞流直下三千尺，没有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_INSUREDAMOUNT_CANNOTUPDATE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		oldInsuredAmount=2D;
		 //4.两个都不为空，且不相等
		try {
			 orderValidator.checkInsuredAmountUpdateable(
					 newInsuredAmount, oldInsuredAmount);
			 fail("飞流直下三千尺，没有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_INSUREDAMOUNT_CANNOTUPDATE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		//5.两个都不为空，且相等
		oldInsuredAmount = 1D;
		assertTrue(orderValidator.checkInsuredAmountUpdateable(
					 newInsuredAmount, oldInsuredAmount));
		
	}
	@Test
	public void testValidateOrderInfoComplete() {
		order.setShipperName(null);
		try {
			orderValidator.validateOrderInfoComplete(order,true);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		/********验证始发接货***********/
		//1.IsReceiveGoods为空
		order.setIsReceiveGoods(null);
		try {
			orderValidator.validateOrderInfoComplete(order,true);
			fail("没有错");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals( OrderExceptionType.
					ORDER_INFORMATION_INCOMPLETE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常199");
			}
		}
		
		//2.
		order.setIsReceiveGoods(true);
		order.setBeginAcceptTime(null);
		try {
			orderValidator.validateOrderInfoComplete(order,true);
			fail("没有错");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_INFORMATION_ACCEPTTIME_INCOMPLETE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常214");
			}
		}
		
		order.setBeginAcceptTime(new Date());
		order.setEndAcceptTime(null);
		try {
			orderValidator.validateOrderInfoComplete(order,true);
			fail("没有错");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_INFORMATION_ACCEPTTIME_INCOMPLETE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常228");
			}
		}
		//3.
		try {
			orderValidator.validateOrderInfoComplete(order,true);
			fail("没有错");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_INFORMATION_ACCEPTTIME_INCOMPLETE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常240");
			}
		}
		try {
			orderValidator.validateOrderInfoComplete(null,true);
		} catch (Exception e) {
			assertTrue(true);
		}
		try {
			order.setShipperNumber("");
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//4.
		order.setIsReceiveGoods(false);
		assertTrue(orderValidator.validateOrderInfoComplete(order,true));
		
		/**********验证订单信息完整**************/
		//5.
		Order order01 = order;
		order01.setIsReceiveGoods(false);
		order01.setContactName(null);
		try {
			orderValidator.validateOrderInfoComplete(order01,true);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_INFORMATION_INCOMPLETE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常270");
			}
		}
		
		/**********验证手机和电话是否为空**************/
		Order order02 = order;
		order02.setContactPhone(null);
		order02.setContactMobile(null);
		try {
			orderValidator.validateOrderInfoComplete(order02,true);
			fail("没有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_MOBILEANDPHONE_MUSTBEONE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常286");
			}
		}
		
		order02.setContactPhone("02112351235");
		try {
			orderValidator.validateOrderInfoComplete(order02,true);
			fail("没有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_MOBILEANDPHONE_MUSTBEONE.getErrorCode())){
				assertTrue(true);
			}else{
//				fail("异常异常299行");//TODO
			}
		}
		
		order02.setContactPhone(null);
		order02.setContactMobile("15921594568");
		try {
			orderValidator.validateOrderInfoComplete(order02,true);
			fail("没有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_MOBILEANDPHONE_MUSTBEONE.getErrorCode())){
				assertTrue(true);
			}else{
//				fail("异常异常313");//TODO
			}
		}
	}
	
	
	@Test
	public void testBookVehicleValidator(){
		Order od = order;
		//1.条件都为假
		assertTrue(orderValidator.bookVehicleValidator(order));
		//2.GoodsNumber满足条件
		od.setGoodsNumber(null);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_GOODSNUMBER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		od.setGoodsNumber(-1);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_GOODSNUMBER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		od.setGoodsNumber(999999999);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_GOODSNUMBER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		//3.体积TotalVolume满足条件
		od.setGoodsNumber(10);
		od.setTotalVolume(null);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_TOTALVOLUME.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		od.setTotalVolume(-1d);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_TOTALVOLUME.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		od.setTotalVolume(11111d);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_TOTALVOLUME.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		//4.重量TotalWeight满足条件
		od.setTotalVolume(55d);
		od.setTotalWeight(null);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_TOTALWEIGHT.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		od.setTotalWeight(-1d);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_TOTALWEIGHT.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		od.setTotalWeight(999999999d);
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_NUM_TOTALWEIGHT.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		//5.储运事项TransNote满足条件
		od.setTotalWeight(10d);
		od.setTransNote("裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！" +
				"裤儿都落咯，你娃想干哪样！");
		try {
			assertTrue(orderValidator.bookVehicleValidator(order));
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().
					equals(OrderExceptionType.
							BOOKVIHICE_TRANSNOTE_CANNOTOVER80.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		od.setTransNote("      ");
		assertTrue(orderValidator.bookVehicleValidator(order));
		
		od.setTransNote(null);
		assertTrue(orderValidator.bookVehicleValidator(order));
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#isObjectNotNull(java.lang.Object)}
	 * .
	 */
	@Test
	public void testIsObjectNotNull() {
		String str = "";
		List list= new ArrayList();
		Map map = new HashMap();
		assertFalse(orderValidator.isObjectNotNull(null));
		assertFalse(orderValidator.isObjectNotNull(""));
		assertFalse(orderValidator.isObjectNotNull(list));
		assertFalse(orderValidator.isObjectNotNull(map));
		
		str = "4545";
		list.add(str);
		map.put(1, str);
		assertTrue(orderValidator.isObjectNotNull(str));
		assertTrue(orderValidator.isObjectNotNull(list));
		assertTrue(orderValidator.isObjectNotNull(map));
		
		Integer i = new Integer(11);
		assertTrue(orderValidator.isObjectNotNull(i));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#isOrderIsUpdateable(com.deppon.crm.module.order.shared.domain.Order)}
	 * .
	 */
	@Test
	public void testIsOrderIsUpdateable() {
		User user = new User();
		Employee emp = new Employee();
		Department dpt = new Department();
		emp.setDeptId(dpt);
		
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
		
		order.setOrderStatus(Constant.ORDER_STATUS_CANCEL);
		try {
			orderValidator.isOrderUpdateable(order,user);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.ORDER_CANNOT_UPDATE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常");
			}
		}
		
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		orderValidator.isOrderUpdateable(order,user);
		
		order.setResource(Constant.ORDER_SOURCE_CALLCENTER);
		dpt.setId(System.currentTimeMillis()+"");
		try {
			orderValidator.isOrderUpdateable(order,user);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.CANNOT_MODIFY_ONLYRESOURCEDEPTCC.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常");
			}
		}
		
		dpt.setId(order.getAcceptDept());
		orderValidator.isOrderUpdateable(order,user);
		
		dpt.setId(System.currentTimeMillis()+"");
		dpt.setStandardCode(Constant.STANDAR_CODE_400);
		orderValidator.isOrderUpdateable(order,user);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#isRefuseableOrder(com.deppon.crm.module.order.shared.domain.Order)}
	 * .
	 */
	@Test
	public void testIsRefuseableOrder() {
		order.setOrderStatus(System.currentTimeMillis()+"");
		try {
			orderValidator.isRefuseableOrder(order);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.ORDER_NOT_REFUSEABLE.getErrorCode())){
				assertFalse(false);
			}else{
				fail("异常");
			}
		}
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		assertTrue(orderValidator.isRefuseableOrder(order));
	}

	@Test
	public void testIsFeedbackInfoNotNull(){
		String feedbackInfo = null;
		try{
			orderValidator.isFeedbackInfoNotNull(feedbackInfo);
		}catch(GeneralException e){
			if(e.getErrorCode().equals(OrderExceptionType.ORDER_FIGHTBACKREASON_MUSTBEREQUIRED.getErrorCode())){
				assertFalse(false);
			}else{
				fail("异常");
			}
		}
		feedbackInfo = System.currentTimeMillis()+"";
		assertTrue(orderValidator.isFeedbackInfoNotNull(feedbackInfo));
	}
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#isInDayRange(java.util.Date, java.util.Date, int)}
	 * .
	 */
	@Test
	public void testIsInDayRange() {
		Date start = new Date(2012, 8, 01);
		Date end = new Date(2012, 11, 01);
		int range = 9;

		try {
			orderValidator.isInDayRange(start, end, range);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
 
		end = new Date(2012, 8, 3);
		assertTrue(orderValidator.isInDayRange(start, end, range));
		
		end = new Date(2012,7,31);
		try {
			orderValidator.isInDayRange(start, end, range);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	
	@Test 
	public void testIsSameOrderInfo(){//TODO
		List<Order>list = new ArrayList<Order>();
		list.add(order);
		orderValidator.isSameOrderInfo(list);
		
		Order newOdr = new Order();
		String value = System.currentTimeMillis()+"";
		newOdr.setOrderStatus(value);
		newOdr.setContactName(value);
		newOdr.setContactAddress(value);
		list.add(newOdr);
		try {
			orderValidator.isSameOrderInfo(list);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_NOT_SAME_INFO.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		} 
		
		newOdr.setOrderStatus(order.getOrderStatus());
		try {
			orderValidator.isSameOrderInfo(list);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_NOT_SAME_INFO.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		} 
		
		newOdr.setContactName(order.getContactName());
		try {
			orderValidator.isSameOrderInfo(list);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_NOT_SAME_INFO.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
	}
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#isAssignableOrder(java.util.List)}
	 * .
	 */
	@Test
	public void testIsAssignableOrder() {
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		try {
			orderValidator.isAssignableOrder(orders);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		assertTrue(orderValidator.isAssignableOrder(orders));
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		assertTrue(orderValidator.isAssignableOrder(orders));
		order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
		assertTrue(orderValidator.isAssignableOrder(orders));
	}



	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#isReturnOrderStatus(com.deppon.crm.module.order.shared.domain.Order)}
	 * .
	 */
	@Test
	public void testIsNotReturnOrderStatus() {
		try {
			orderValidator.isReturnOrderStatus(order);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		assertTrue(orderValidator.isReturnOrderStatus(order));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.OrderValidator#isCancelOrderStatus(com.deppon.crm.module.order.shared.domain.Order)}
	 * .
	 */
	@Test
	public void testIsNotCancelOrderStatus() {
		User user = new User();
		Employee emp = new Employee();
		Department dpt = new Department();
		emp.setDeptId(dpt);
		
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
		try {
			orderValidator.isCancelOrderStatus(order,user);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		assertTrue(orderValidator.isCancelOrderStatus(order,user));
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		assertTrue(orderValidator.isCancelOrderStatus(order,user));
		order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
		assertTrue(orderValidator.isCancelOrderStatus(order,user));
		order.setOrderStatus(Constant.ORDER_STATUS_REJECT);
		try {
			orderValidator.isCancelOrderStatus(order,user);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testIsOrderFromCrm(){
		String orderSource = "CALLCENTER";
		assertTrue(orderValidator.isOrderFromCrm(orderSource));
		
		orderSource = "BUSINESS_DEPT";
		assertTrue(orderValidator.isOrderFromCrm(orderSource));

		orderSource = "xxxxoooo";
		try {
			orderValidator.isOrderFromCrm(orderSource);
//			fail("没有错");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					OrderExceptionType.ORDER_CANNOT_CANCEL.getErrorCode())) {
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
	}
	
	@Test
	public void testIsOrderHaveNoStateStation(){
		assertFalse(orderValidator.isOrderHaveNoStateStation(order));
	}
	
	@Test//TODO 源代码有问题
	public void testIsReciveGoodsInfoChange(){
		Order o = null;
		Order dataSourceOrder = null;
		assertFalse(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder = order;
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder = null;
		o = order;
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder = order;
		assertFalse(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder = new Order();
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder.setIsReceiveGoods(o.getIsReceiveGoods());
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder.setContactProvince(o.getContactProvince());
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder.setContactCity(o.getContactCity());
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder.setContactArea(o.getContactArea());
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder.setContactAddress(o.getContactAddress());
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
		
		dataSourceOrder.setTotalVolume(o.getTotalVolume());
		assertTrue(orderValidator.isReciveGoodsInfoChange(o,dataSourceOrder));
	}
	
	@Test
	public void testValidateOrderProcessable(){
		try {
			orderValidator.validateOrderProcessable(order);
		} catch (GeneralException e) {
			 if(e.getErrorCode().equals(
					 OrderExceptionType.ORDER_CANNOT_ACCEPT.getErrorCode())){
				 assertTrue(true);
			 }else{
				 fail("异常异常");
			 }
		}
		
		Order o = order;
		o.setOrderStatus("WAIT_ALLOT");
		assertTrue(orderValidator.validateOrderProcessable(order));
	}
	
	@Test
	public void testIsOrderCanBookVehice(){
		try {
			orderValidator.isOrderCanBookVehice(order);
		} catch (GeneralException e) {
			 if(e.getErrorCode().equals(
					 OrderExceptionType.ORDER_BOOKVIHICE_FAIL.getErrorCode())){
				 assertTrue(true);
			 }else{
				 fail("异常异常");
			 }
		}
		
		order.setOrderStatus(Constant.ORDER_SATUTS_GOBACK);
		orderValidator.isOrderCanBookVehice(order);
	}
	
	@Test
	public void testIsBookVehicleInfoComplete(){
		BookVehicleInfo info = new BookVehicleInfo();
		//1.
		try {
			orderValidator.isBookVehicleInfoComplete(info);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_BOOKVIHICEINFO_ISTAILBOARD.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		//2.
		info.setIsTailBoard(true);
		try {
			orderValidator.isBookVehicleInfoComplete(info);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_BOOKVIHICEINFO_USEVEHICLEDEPT.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		//3.
		info.setUseVehicleDept("切糕店");
		try {
			orderValidator.isBookVehicleInfoComplete(info);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.
					ORDER_BOOKVIHICEINFO_VEHICLETEAM.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		//4.
		info.setVehicleTeam("切糕大队");
		assertTrue(orderValidator.isBookVehicleInfoComplete(info));
		
	}
	
	@Test
	public void testIsSendOrderSuccess(){
		boolean result = false;
		try {
			orderValidator.isSendOrderSuccess(result);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_SENDORDERTOERP_FAIL.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		result = true;
		assertTrue(orderValidator.isSendOrderSuccess(result));
	}
	
	@Test
	public void testIsWaybillSigned(){
		orderValidator.isWaybillSigned(null);
		WaybillInfo waybill = new WaybillInfo();
		try {
			orderValidator.isWaybillSigned(waybill);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.WAYBILL_STAUTS_ASSIGNED.getErrorCode())){
				assertFalse(false);
			}else{
				fail("异常不是所期望的异常");
			}
		}
		waybill.setWaybillStatus(1);
		
		orderValidator.isWaybillSigned(waybill);
	}
	
	@Test
	public void testIsAccpetFailOrderStatus(){
		order.setOrderStatus(System.currentTimeMillis()+"");
		try{
			orderValidator.isAccpetFailOrderStatus(order);
		}catch(GeneralException e){
			if(e.getErrorCode().equals(OrderExceptionType.ORDER_NOT_ACCPETFAIL.getErrorCode())){
				assertFalse(false);
			}else{
				fail("异常");
			}
		}
		
		order.setOrderStatus(Constant.ORDER_SATUTS_RECEIPTING);
		assertTrue(orderValidator.isAccpetFailOrderStatus(order));
	}
	
	@Test
	public void testIsUrgeOrderStatus(){
		order.setOrderStatus(System.currentTimeMillis()+"");
		try{
			orderValidator.isUrgeOrderStatus(order);
		}catch(GeneralException e){
			if(e.getErrorCode().equals(OrderExceptionType.ORDER_NOT_URGE.getErrorCode())){
				assertFalse(false);
			}else{
				fail("异常");
			}
		}
		
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		assertTrue(orderValidator.isUrgeOrderStatus(order));
	}
	
//	@Test
//	public void testIsUrgeOrderStatus(){
//		order.setOrderStatus(System.currentTimeMillis()+"");
//		try{
//			validator.isUrgeOrderStatus(order);
//		}catch(GeneralException e){
//			if(e.getErrorCode().equals(OrderExceptionType.ORDER_NOT_URGE.getErrorCode())){
//				assertFalse(false);
//			}else{
//				fail("异常");
//			}
//		}
//		
//		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
//		assertTrue(validator.isUrgeOrderStatus(order));
//	}
	
	@Test
	public void testIsWaybillAssociated(){
		Order o = order;
		try {
			orderValidator.isWaybillAssociated(o);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.WAYBILL_ALREADYASSOCIATE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		o = null;
		try {
			orderValidator.isWaybillAssociated(o);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.WAYBILL_ALREADYASSOCIATE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
	}
	
	@Test
	public void testIsUserLogin(){
		User  u= new User();
		Employee emp = new Employee();
		Department dpt = new Department();
		dpt.setId(System.currentTimeMillis()+"");
		
		try {
			orderValidator.isUserLogin(null);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_USER_NOTLOGIN.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		u.setId(null);
		try {
			orderValidator.isUserLogin(u);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_USER_NOTLOGIN.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		u.setId("");
		try {
			orderValidator.isUserLogin(u);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_USER_NOTLOGIN.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		
		u.setId(System.currentTimeMillis()+"");
		u.setEmpCode(null);
		try {
			orderValidator.isUserLogin(u);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_USER_NOTLOGIN.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		
		emp.setDeptId(null);
		u.setEmpCode(emp);
		try {
			orderValidator.isUserLogin(u);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_USER_NOTLOGIN.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		
		dpt.setId(null);
		emp.setDeptId(dpt);
		try {
			orderValidator.isUserLogin(u);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_USER_NOTLOGIN.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		dpt.setId(System.currentTimeMillis()+"");
		assertTrue(orderValidator.isUserLogin(u));
	}
	
	@Test
	public void testIsCallCenterOrder(){
		Order o = null;
		assertFalse(orderValidator.isCallCenterOrder(o));
		
		o = order;
		o.setResource(null);
		assertFalse(orderValidator.isCallCenterOrder(o));
		
		o.setResource("CALLCENTER");
		assertTrue(orderValidator.isCallCenterOrder(o));
		
		
		o.setResource("XXXXX");
		assertFalse(orderValidator.isCallCenterOrder(o));
		
		o.setResource(Constant.ORDER_SOURCE_ALIBABA);
		assertFalse(orderValidator.isCallCenterOrder(o));
	}
	
	@Test
	public void testIsWebChannelOrder(){
		Order order = new Order();
		order.setResource(Constant.ORDER_SOURCE_ALIBABA);
		assertTrue(orderValidator.isWebChannelOrder(order));
		
		order.setResource(Constant.ORDER_SOURCE_SHANGCHENG);
		assertTrue(orderValidator.isWebChannelOrder(order));
		
		order.setResource(Constant.ORDER_SOURCE_YOUSHANG);
		assertTrue(orderValidator.isWebChannelOrder(order));
		
		order.setResource(Constant.ORDER_SOURCE_TAOBAO);
		assertTrue(orderValidator.isWebChannelOrder(order));
		
		order.setResource(System.currentTimeMillis()+"");
		assertFalse(orderValidator.isWebChannelOrder(order));
		
		
	}
	
	@Test
	public void testIsChannelNumber(){
		String orderNumber = "11111";
		assertFalse(orderValidator.isChannelNumber(orderNumber));
		orderNumber = "ATXY";
		assertTrue(orderValidator.isChannelNumber(orderNumber));

	}
	
	@Test
	public void testIsNeedCheck(){
		String orderSource = null;
		assertTrue(orderValidator.isNeedCheck(orderSource));
		orderSource = "";
		assertTrue(orderValidator.isNeedCheck(orderSource));
		orderSource = "SKDHJKSDHFK";
		assertTrue(orderValidator.isNeedCheck(orderSource));
		
		orderSource = Constant.ORDER_SOURCE_ONLINE;
		assertFalse(orderValidator.isNeedCheck(orderSource));
	}
	
	@Test
	public void testIsOrderChannelModify(){
		User u = userDao.getById("532922");
		Order o = order;
		o.setResource("sldfhg");
		try {
			orderValidator.isOrderChannelModify(u, o);
			fail("没有错");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.CANNOT_MODITY_WEBORDER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		u.setId("DP0000");
		orderValidator.isOrderChannelModify(u, o);
		u.setId("");
		
		o.setResource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		orderValidator.isOrderChannelModify(u, o);
		
		o.setResource(Constant.ORDER_SOURCE_CALLCENTER);
		orderValidator.isOrderChannelModify(u, o);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUrgeTimeAndRateLimit(){
		Order o = order;
		List<OrderOperationLog> orderOperationLogList = null;
		Date date = new Date();
		date.setMinutes(date.getMinutes()-12);
		o.setLastHastenTime(date);
		Date date01 = new Date();
		date01.setMinutes(date01.getMinutes()-50);
		o.setCreateDate(date01);
		try {
			orderValidator.urgeTimeAndRateLimit(o, orderOperationLogList);
			fail("异常没有");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_NOT_URGE15.getErrorCode())){
				assertTrue(true);
			}else{
//				fail("异常异常");//TODO
			}
		}
		
		
		date.setMinutes(date.getMinutes()-22);
		o.setLastHastenTime(date);
		assertTrue(orderValidator.
				urgeTimeAndRateLimit(o, orderOperationLogList));
	}
	
	@Test
	public void testIsChannelNumberNull(){
		try {
			orderValidator.isChannelNumberNull(null);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.ORDER_CHANNELNUMBER_MUST.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		orderValidator.isChannelNumberNull(System.currentTimeMillis()+"");
	}
	
	
	@Test
	public void testCheckOpeLogIsNull(){
		OrderOperationLog log  = new OrderOperationLog();
		try {
			orderValidator.checkOpeLogIsNull(log);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.CANNOT_ISNULL_CONCATNAME.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		log.setContactName("contactName001");
		try {
			orderValidator.checkOpeLogIsNull(log);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.CANNOT_ISNULL_ORDERID.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		log.setOrderId("0000112");
		try {
			orderValidator.checkOpeLogIsNull(log);
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					OrderExceptionType.CANNOT_ISNULL_OPERATIONCONTENT.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		log.setOperatorContent("下单成功...");
		orderValidator.checkOpeLogIsNull(log);
	}

}
