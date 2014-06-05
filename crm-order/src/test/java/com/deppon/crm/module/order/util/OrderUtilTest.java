package com.deppon.crm.module.order.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.server.manager.impl.AreaAddressManager;
import com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager;
import com.deppon.crm.module.order.server.dao.IOrderDao;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.server.util.OrderUtil;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title OrderUtilTest.java
 * @package com.deppon.crm.module.order.util
 * @author 华龙
 * @version 0.1 2012-12-6
 */
public class OrderUtilTest extends TestCase {
	static IOrderDao orderDao;
	static OrderUtil orderUtil;
	static Order order = null;
	Order newOrder = null;
	List<String> ids = new ArrayList<String>();
	static User user = null;
	static LadingstationDepartmentManager ladingstationDepartmentManager;
	static AreaAddressManager areaAddressManager;
	static {
		ladingstationDepartmentManager = (LadingstationDepartmentManager) TestUtil.ladingstationDepartmentManager;
		areaAddressManager = TestUtil.areaAddressManager;
		orderUtil = TestUtil.orderUtil;
		user = new User();
		user.setId("23195");
		Department dept = new Department();
		dept.setId("223");
		dept.setDeptCode("dp129182");
		dept.setDeptName("少林寺");
		dept.setStandardCode("110");
		Employee emp = new Employee();
		emp.setDeptId(dept);
		emp.setEmpCode("08888");
		emp.setEmpName("张三丰");
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);

		order = new Order();
		order.setContactManId("0");
		order.setLastHastenTime(null);
		order.setHastenCount(0);
		order.setShipperId("1233332");
		order.setShipperNumber("dp123");
		order.setShipperName("德邦物流");
		order.setContactName("李艳芳");
		order.setContactMobile("11111111111");
		order.setContactPhone("029-38542154");
		order.setContactProvince("天津");
		order.setContactCity("天津市");
		order.setContactArea("宁河县");
		order.setContactAddress("明珠路1018号");
		order.setIsReceiveGoods(false);
		order.setBeginAcceptTime(null);
		order.setEndAcceptTime(null);
		order.setReceiverCustId(null);
		order.setReceiver("德邦物流");
		order.setReceiverCustName("李艳芳");
		order.setReceiverCustNumber("dp123");
		order.setReceiverCustProvince("安徽省");
		order.setReceiverCustCity("马鞍市市");
		order.setReceiverCustArea("马化腾");
		order.setReceiverCustAddress("金家庄区");
		order.setContactAddress("明珠路1018号");
		order.setIsSendmessage(true);
		order.setTransportMode("JZCY");
		order.setStartStation("2");
		order.setStartStationName("上海营业部");
		order.setGoodsName("炸药");
		order.setPacking("发个人");
		order.setChannelCustId("cust001");
		order.setGoodsType("A");
		order.setTotalWeight(12434.0);
		order.setTotalVolume(1243423.00);
		order.setPaymentType("CASH");
		order.setChannelType(System.currentTimeMillis() + "12133");
		order.setChannelCustId(null);
		order.setDeliveryMode("PICKSELF");
 		order.setReviceMoneyAmount(546546.0);
		order.setInsuredAmount(546546.0);
		order.setOrderTime(null);
		order.setAcceptDept("2");
		order.setAcceptDeptName("德邦物流===");
		order.setAcceptDeptName("顺风");
		order.setModifyDate(null);
		order.setResource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		order.setOrderNumber("dp" + System.currentTimeMillis());
		order.setTransNote("潘光均独用数据，不准搞！！！");
		order.setOrderPerson("上海营1");
		order.setStartStation("2");
		order.setWaybillNumber("2322313121");
		order.setReceivingToPoint("179774");
		order.setReturnBillType("CUSTOMER_SIGNED_ORIGINAL");
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		order.setGoodsNumber(111);
		order.setDealPerson("xuhualong");

	}

	@Test
	public void testGetStationNameById() {
		OrderUtil.getStationNameById(null,ladingstationDepartmentManager);
		OrderUtil.getStationNameById("",ladingstationDepartmentManager);
		orderUtil.getStationNameById("11111",ladingstationDepartmentManager);
		orderUtil.getStationNameById("10",ladingstationDepartmentManager);

	}

	@Test
	public void testGetProvinceNameById() {
		OrderUtil.getProvinceNameById(null,areaAddressManager);
		OrderUtil.getProvinceNameById("",areaAddressManager);
		orderUtil.getProvinceNameById("11111",areaAddressManager);
		orderUtil.getProvinceNameById("10",areaAddressManager);
	}

	@Test
	public void testCityNameById() {
		OrderUtil.getCityNameById(null,areaAddressManager);
		OrderUtil.getCityNameById("",areaAddressManager);
		orderUtil.getCityNameById("111111111",areaAddressManager);
		orderUtil.getCityNameById("4",areaAddressManager);
	}

	@Test
	public void testAreaNameById() {
		OrderUtil.getAreaNameById(null,areaAddressManager);
		OrderUtil.getAreaNameById("",areaAddressManager);
		orderUtil.getAreaNameById("111111111",areaAddressManager);
		orderUtil.getAreaNameById("4",areaAddressManager);
	}

	@Test
	public void testGetOrderTransMode() {
		OrderUtil.getOrderTransMode(Constant.ORDER_TRANSTYPE_JZKY);
		OrderUtil.getOrderTransMode(Constant.ORDER_TRANSTYPE_JZQY_SHORT);
		OrderUtil.getOrderTransMode(Constant.ORDER_TRANSTYPE_JZKH);
		OrderUtil.getOrderTransMode(Constant.ORDER_TRANSTYPE_JZCY);
		OrderUtil.getOrderTransMode(Constant.ORDER_TRANSTYPE_AGENT_VEHICLE);
		OrderUtil.getOrderTransMode(Constant.ORDER_TRANSTYPE_JZQY_LONG);
		try {
			OrderUtil.getOrderTransMode("hh");
			fail("没有异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					OrderExceptionType.NO_SUCH_TRANSTYPE.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("异常异常");
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:约车历史<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */
	@Test
	public void testProduceVehicleHistory() {
		String vehicleTeamName = "HelloWorld";
		BookVehicleInfo info = new BookVehicleInfo();
		info.setUseVehicleDept("dp111");
		info.setVehicleTeam("保定集配站");
		VehicleHistory history = OrderUtil.produceVehicleHistory(order, info,
				user, vehicleTeamName);
		assertNotNull(history);

	}

	/**
	 * 
	 * <p>
	 * Description:日期转换测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */
	@Test
	public void testConvertDate() {
		OrderUtil.convertDate(new Date());
		OrderUtil.convertDate(null);

	}

	/**
	 * 
	 * <p>
	 * Description:null转换测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */
	@Test
	public void testConvertNull() {
		OrderUtil.convertNull(null);
		String obj = "";
		OrderUtil.convertNull(obj);
		obj = "Roy";
		OrderUtil.convertNull(obj);

	}

	/**
	 * 
	 * <p>
	 * Description:是否转换测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */
	@Test
	public void testConvertYesOn() {
		OrderUtil.convertYesOn(false);
		OrderUtil.convertNull(true);
		OrderUtil.convertNull(null);

	}

	/**
	 * 
	 * <p>
	 * Description:比较两个对象是否不等测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */
	@Test
	public void testNotEqual() {
		Object obj = new Object();
		OrderUtil.notEqual(obj, obj);
		OrderUtil.notEqual(obj, null);
		OrderUtil.notEqual(null, obj);
		OrderUtil.notEqual(null, null);

	}

	/**
	 * 
	 * <p>
	 * Description:根据订单的运输类型生成erp中运输类型编码测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */
	@Test
	public void testGetOrderDeliverMode() {
		OrderUtil.getOrderDeliverMode(Constant.ORDER_DELIVERMODE_PICKSELF);
		OrderUtil.getOrderDeliverMode(Constant.ORDER_DELIVERMODE_PICKONAIEPORT);
		OrderUtil.getOrderDeliverMode(Constant.ORDER_DELIVERMODE_PICKUPSTAIRS);
		OrderUtil
				.getOrderDeliverMode(Constant.ORDER_DELIVERMODE_PICKNOTUPSTAIRS);
		OrderUtil
				.getOrderDeliverMode(Constant.ORDER_DELIVERMODE_PICKNOTUPSTAIRS);

		try {
			OrderUtil.getOrderDeliverMode("helloWorld");
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据订单信息产生操作日志测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */

	@Test
	public void testGenerateOrderOperationLog() {
		try {
			OrderUtil.generateOrderOperationLog(order, "酱油", "打的好", null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		User user1 = new User();
		user1.setEmpCode(null);
		try {
			OrderUtil.generateOrderOperationLog(order, "酱油", "打的好", user1);
		} catch (Exception e1) {
		}
		Employee e = new Employee();
		e.setEmpName(null);
		try {
			OrderUtil.generateOrderOperationLog(order, "酱油", "打的好", user1);
		} catch (Exception e2) {
		}
		e.setEmpName("xuhualong");
		e.setEmpCode(null);
		try {
			OrderUtil.generateOrderOperationLog(order, "酱油", "打的好", user);
		} catch (Exception e3) {
		}
		e.setEmpCode("106138");
		try {
			OrderUtil.generateOrderOperationLog(null, "酱油", "打的好", user);
		} catch (Exception e4) {
		}
		order.setContactName(null);
		try {
			OrderUtil.generateOrderOperationLog(order, "酱油", "打的好", user);
		} catch (Exception e5) {
		}
		order.setContactName("zhangpei");
		try {
			OrderUtil.generateOrderOperationLog(order, "酱油", "打的好", user);
		} catch (Exception e5) {
		}

	}

	/**
	 * 
	 * <p>
	 * Description:根据订单信息产生操作日志测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */

	@Test
	public void testProduceERPOrder() {
		BookVehicleInfo info = new BookVehicleInfo();
		info.setBookVehicelNumber("11111");
		info.setErpId("11111");
		info.setIsTailBoard(false);
		info.setUseVehicleDept("2222222");
		info.setVehicleTeam("shanghai");
		info.setVehicleType("hello");
		order.setShipperName(null);
		order.setDeliveryMode(Constant.ORDER_DELIVERMODE_PICKUPSTAIRS);
		OrderUtil.produceERPOrder(order, info);
		order.setShipperName("");
		OrderUtil.produceERPOrder(order, info);
		order.setGoodsType("");
		OrderUtil.produceERPOrder(order, info);
		order.setGoodsType(null);
		OrderUtil.produceERPOrder(order, info);
	}

	/**
	 * 
	 * <p>
	 * Description:根据订单信息产生操作日志测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */

	@Test
	public void testProduceModifyLog() {
		order = new Order();
		order.setContactManId("0");
		order.setLastHastenTime(null);
		order.setHastenCount(0);
		order.setShipperId("1233332");
		order.setShipperNumber("dp123");
		order.setShipperName("德邦物流");
		order.setContactName("李艳芳");
		order.setContactMobile("11111111111");
		order.setContactPhone("029-38542154");
		order.setContactProvince("天津");
		order.setContactCity("天津市");
		order.setContactArea("宁河县");
		order.setContactAddress("明珠路1018号");
		order.setIsReceiveGoods(false);
		order.setBeginAcceptTime(null);
		order.setEndAcceptTime(null);
		order.setReceiverCustId(null);
		order.setReceiver("德邦物流");
		order.setReceiverCustName("李艳芳");
		order.setReceiverCustNumber("dp123");
		order.setReceiverCustProvince("安徽省");
		order.setReceiverCustCity("马鞍市市");
		order.setReceiverCustArea("马化腾");
		order.setReceiverCustAddress("金家庄区");
		order.setContactAddress("明珠路1018号");
		order.setIsSendmessage(true);
		order.setTransportMode("JZCY");
		order.setStartStation("2");
		order.setStartStationName("上海营业部");
		order.setGoodsName("炸药");
		order.setPacking("发个人");
		order.setChannelCustId("cust001");
		order.setGoodsType("A");
		order.setTotalWeight(12434.0);
		order.setTotalVolume(1243423.00);
		order.setPaymentType("CASH");
		order.setChannelType(System.currentTimeMillis() + "12133");
		order.setChannelCustId(null);
		order.setDeliveryMode("PICKNOTUPSTAIRS");
		order.setReciveLoanType("NORMAL");
		order.setReviceMoneyAmount(546546.0);
		order.setInsuredAmount(546546.0);
		order.setOrderTime(null);
		order.setAcceptDept("2");
		order.setAcceptDeptName("德邦物流===");
		order.setAcceptDeptName("顺风");
		order.setModifyDate(null);
		order.setResource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		order.setOrderNumber("dp" + System.currentTimeMillis());
		order.setTransNote("潘光均独用数据，不准搞！！！");
		order.setOrderPerson("上海营1");
		order.setStartStation("2");
		order.setWaybillNumber("2322313121");
		order.setReceivingToPoint("179774");
		order.setReturnBillType("BILL_SIGNED_FAX");
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		order.setReceiverCustcompany("111s");
		newOrder = new Order();
		newOrder.setContactManId("0");
		newOrder.setLastHastenTime(null);
		newOrder.setHastenCount(0);
		newOrder.setShipperId("1233332");
		newOrder.setShipperNumber("dp1231");
		newOrder.setShipperName("德邦物流1");
		newOrder.setContactName("李艳芳");
		newOrder.setContactMobile("11111111111");
		newOrder.setContactPhone("029-38542154");
		newOrder.setContactProvince("天津");
		newOrder.setContactCity("天津市");
		newOrder.setContactArea("宁河县");
		newOrder.setContactAddress("明珠路1018号");
		newOrder.setIsReceiveGoods(true);
		newOrder.setBeginAcceptTime(null);
		newOrder.setEndAcceptTime(null);
		newOrder.setReceiverCustId(null);
		newOrder.setReceiver("德邦物流");
		newOrder.setReceiverCustName("李艳芳");
		newOrder.setReceiverCustNumber("dp123");
		newOrder.setReceiverCustProvince("安徽省");
		newOrder.setReceiverCustCity("马鞍市市");
		newOrder.setReceiverCustArea("马化腾");
		newOrder.setReceiverCustAddress("金家庄区");
		newOrder.setContactAddress("明珠路1018号");
		newOrder.setIsSendmessage(true);
		newOrder.setTransportMode("JZCY");
		newOrder.setStartStation("2");
		newOrder.setStartStationName("上海营业部");
		newOrder.setGoodsName("炸药");
		newOrder.setPacking("发个人");
		newOrder.setChannelCustId("cust001");
		newOrder.setGoodsType("A");
		newOrder.setTotalWeight(12434.0);
		newOrder.setTotalVolume(1243423.00);
		newOrder.setPaymentType("MONTH_PAY");
		newOrder.setChannelType(System.currentTimeMillis() + "12133");
		newOrder.setChannelCustId(null);
		newOrder.setDeliveryMode("PICKSELF");
		newOrder.setReviceMoneyAmount(546546.0);
		newOrder.setInsuredAmount(546546.0);
		newOrder.setOrderTime(null);
		newOrder.setAcceptDept("2");
		newOrder.setAcceptDeptName("德邦物流===");
		newOrder.setAcceptDeptName("顺风");
		newOrder.setModifyDate(null);
		newOrder.setResource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		newOrder.setOrderNumber("dp" + System.currentTimeMillis());
		newOrder.setTransNote("潘光均独用数据，不准搞！！！");
		newOrder.setOrderPerson("上海营1");
		newOrder.setStartStation("2");
		newOrder.setWaybillNumber("2322313121");
		newOrder.setReceivingToPoint("179774");
		newOrder.setReturnBillType("NO_RETURN_SIGNED");
		newOrder.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		newOrder.setChannelNumber(System.currentTimeMillis() + "");
		newOrder.setReciveLoanType("NORMAL");
		newOrder.setReturnBillType("NO_RETURN_SIGNED");
		newOrder.setTransportMode("JZQY_LONG");
		newOrder.setDeliveryMode("PICKSELF");
		newOrder.setPaymentType("PAY_ONLINE");
		order.setIsSendmessage(false);
		newOrder.setIsSendmessage(true);
		String buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.err.println(buffer);
		newOrder.setIsSendmessage(false);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		newOrder.setContactName("许华龙");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.err.println(buffer);
		newOrder.setContactMobile("15921594568");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.err.println(buffer);
		newOrder.setContactPhone("11111112222222");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.err.println(buffer);
		newOrder.setIsSendmessage(false);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.err.println(buffer);
		newOrder.setBeginAcceptTime(new Date());
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setEndAcceptTime(new Date());
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setContactProvince("福建");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setContactCity("泉州");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setContactArea("德化县");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setContactAddress("双鱼新村");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiver("邹明");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustName("邹明");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustMobile("123432432");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustPhone("123456789");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustProvince("123000");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustCity("123000");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustArea("123000");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustAddress("123000");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceivingToPoint("123000");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceivingToPointName("123000");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setGoodsName("哈哈");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setTotalVolume(222d);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setPacking("aaaa");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setTotalWeight(11d);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setGoodsType("AAA");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setInsuredAmount(23d);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReviceMoneyAmount(111d);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReciveLoanType("NOTHING");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReturnBillType("NO_RETURN_SIGNED");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setTransportMode("JZQY_LONG");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setDeliveryMode("PICKSELF");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setPaymentType("PAY_ONLINE");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setTransNote("11111111");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustNumber("dp12");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustNumber("");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustNumber(null);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		order.setReceiverCustNumber("");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		order.setReceiverCustNumber(null);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);

		order.setReceiverCustId("");
		newOrder.setReceiverCustId("");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		order.setReceiverCustId(null);
		newOrder.setReceiverCustId(null);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		order.setReceiverCustId("1111");
		newOrder.setReceiverCustId("22222");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		order.setReceiverCustId("");
		newOrder.setReceiverCustId(null);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		order.setReceiverCustId(null);
		newOrder.setReceiverCustId("");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setGoodsNumber(112);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setDealPerson("zouming");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setDealPerson(null);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setDealPerson("");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setDealPerson("zouming");
		order.setDealPerson("");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		order.setDealPerson(null);
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);
		newOrder.setReceiverCustcompany("111s111");
		buffer = OrderUtil.produceModifyLog(order, newOrder,ladingstationDepartmentManager);
		System.out.println(buffer);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */

	@Test
	public void testGetLoginEmpNoName() {
		UserContext.setCurrentUser(user);
		OrderUtil.getLoginEmpNoName();
		UserContext.setCurrentUser(null);
		OrderUtil.getLoginEmpNoName();
		user.setEmpCode(null);
		OrderUtil.getLoginEmpNoName();

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20 void
	 */

	@Test
	public void testGetLoginEmpId() {
		UserContext.setCurrentUser(user);
		OrderUtil.getLoginEmpId();
		UserContext.setCurrentUser(null);
		OrderUtil.getLoginEmpId();

	}
	/**
	 * 
	 * <p>
	 * Description:根据字符串来源转换成数字型.<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-21
	 * void
	 */
	@Test
	public void testProduceSource() {
		OrderUtil.produceSource(null);
		OrderUtil.produceSource(Constant.ORDER_SOURCE_ALIBABA);
		OrderUtil.produceSource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		OrderUtil.produceSource(Constant.ORDER_SOURCE_CALLCENTER);
		OrderUtil.produceSource(Constant.ORDER_SOURCE_SHANGCHENG);
		OrderUtil.produceSource(Constant.ORDER_SOURCE_TAOBAO);
		OrderUtil.produceSource(Constant.ORDER_SOURCE_YOUSHANG);
		OrderUtil.produceSource(Constant.ORDER_SOURCE_ONLINE);
		OrderUtil.produceSource("xuROiy");
		

	}

}
