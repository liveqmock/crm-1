package com.deppon.crm.module.order.manager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.order.server.dao.IOrderDao;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.crm.module.order.shared.domain.OrderSearchView;
import com.deppon.crm.module.order.shared.domain.OrderView;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.crm.module.order.util.TestUtil;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.crm.ResultDetal;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

public class OrderManagerTest extends TestCase {
	static IOrderManager orderManager;
	static IOrderDao orderDao;
	static ILadingstationDepartmentManager ladingstationDepartmentManager;
	static IDepartmentService departmentService;
	OrderOperationLog log;
	Order order = null;
	List<String> ids = new ArrayList<String>();
	User user = null;

	static {

		orderManager = TestUtil.orderManager;
		orderDao = TestUtil.orderDao;
		ladingstationDepartmentManager = TestUtil.ladingstationDepartmentManager;
		departmentService=TestUtil.departmentService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {

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
		order.setPaymentType("REMIT");
		order.setChannelType(System.currentTimeMillis() + "12133");
		order.setChannelCustId(null);
		order.setDeliveryMode("PICKFOOR");
		order.setReciveLoanType("0");
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
		order.setReturnBillType(" CUST_ORIGINAL");
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		order.setChannelNumber(System.currentTimeMillis() + "");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		for (int i = 0; i < ids.size(); i++) {
			orderManager.deleteOrder(ids.get(i));
		}
	}
	public void testUpdateOrderNoValidate() {
		order.setId("11");
		orderManager.updateOrderNoValidate(order);
	}

	/**
	 * @作者：罗典
	 * @描述：根据订单号查询订单详情(接口用)
	 * @时间：2012-11-8
	 * @参数：orderNumer 渠道单号或订单号
	 * @返回值: Order 订单信息
	 * */
	@Test
	public void testQueryOrderByOrderNumber() {
		Order orderx = this.orderManager
				.queryOrderByOrderNumber("C130813319311");
		orderx = orderManager.getOrderById(orderx.getId());
		String phone = "4008305555";
		if (null != orderx.getStartStation()
				&& !"".equals(orderx.getStartStation())) {
			Department department = departmentService.getDepartmentById(orderx
					.getStartStation());
			phone = department.getPhone();
		}
		String message = MessageFormat.format(
				Constant.ORDER_MESSAGE_ACCEPT,
				new Object[] {
						orderx.getReceiverCustProvince()
								+ orderx.getReceiverCustCity()
								+ orderx.getReceiverCustAddress(),
						orderx.getReceivingToPointName(),
						orderx.getReceiverCustName(),
						orderx.getChannelNumber(),
						orderx.getStartStationName(), phone });
		System.out.println(message);

	}
	
	@Test
	public void testDelayOrder(){
		order.setResource(Constant.ORDER_SOURCE_CALLCENTER);
		order.setChannelNumber(System.currentTimeMillis() + "");
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		orderManager.createOrder(order);
		System.err.println("?>>>>>>>>>>"+order.getChannelNumber()+">>>>>>>>>>");
		//orderManager.delayOrder(order.getId(), user, "DELAY", new Date());
		
		
		
	}
	
	@Test
	public void testOrderStatusMonitoring() {
		// this.orderManager.orderStatusMonitoring(new Date(112, 11, 29), null);
		// this.orderManager.orderStatusMonitoring(null, null);
		// this.orderManager.orderStatusMonitoring(new Date(112, 12, 29), null);
		// this.orderManager.orderStatusMonitoring(new Date(112, 10, 29), new
		// Date(112, 11, 3));
	}
	
	@Test
	public void testGetOrderWithLinkManCode() {
		this.orderManager.getOrderWithLinkManCode("W8882712");
		this.orderManager
				.getOrderWithLinkManCode("GxF5UQEdEADgAnaxwKgCyaAG4IU=");
		this.orderManager
				.getOrderWithLinkManCode("12345678912345678912345678912345");

		this.orderManager.getOrderWithLinkManCode("W8882712");
	}

	@Test
	public void testGetOrderListByCustId() {
		this.orderManager.getOrderListByCustId("1233332", new Date(112, 12, 1),
				new Date(), 0, 10);
		this.orderManager.getOrderCountByCustId("1233332",
				new Date(112, 12, 1), new Date());
	}

	@Test
	public void testQueryOrderByChannelNumber() {
		this.orderManager.queryOrderByChannelNumber("1");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerCode", "1");
		map.put("startLine", 0);
		map.put("endLine", 10);
		// this.orderManager.queryOrders(map);
		this.orderManager.countOrders(map);
	}

	@Test
	public void testGetOrderAndRegInfoByOrderNum() {
		// this.orderManager.getOrderAndRegInfoByOrderNum("6246");
	}
	
	@Test
	public void testCreateOrderInit(){
		String deptId = null;
		//dept == null
		deptId = "321123";
		orderManager.createOrderInit(deptId);
		
		//dept != null / dept.getIfLeave() == false 
		deptId = "123775";
		orderManager.createOrderInit(deptId);
		//dept != null / dept.getIfLeave() == true 
		deptId = "323223";
		orderManager.createOrderInit(deptId);
	}
	
	@Test
	public void testIsChannelOrderNumber(){
		//String sign = "ATYCWMK";
		String orderNumber = null;
		
		orderNumber = "Mdsdggf";
		orderManager.isChannelOrderNumber(orderNumber);
		
		orderNumber = "125441";
		orderManager.isChannelOrderNumber(orderNumber);
		
	}
	
	@Test
	public void testBussinessDeptMethods() {
		this.orderManager.callCenterOrderInit("49192");
		this.orderManager.callCenterOrderInit("1");
		this.orderManager.createOrderInit("49192");
		this.orderManager.createOrderInit("1");
		this.orderManager.searchBusDeptByErpId("Jwa3LAEuEADgJ0fmwKgCZcznrtQ=");
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试：通过标杆编码查询营业部门<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-1-9
	 * void
	 */
//	?Test
//	public void testBusDeptByStandardCode() {
//		String standardCode = "DP01421";
//		orderManager.searchBusDeptByStandardCode(standardCode);
//	}

	@Test
	public void testSearchMemberByName() {
		this.orderManager.searchMemberByName("1");
		this.orderManager.searchMemberByPhone("1");
		this.orderManager.getMenberByMobile("1");
	}

	@Test
	public void testGetOrderByErpId() {
		orderManager.getOrderByErpId("1");
	}

	/**
	 * {@link com.deppon.crm.module.order.server.manager.impl.OrderManager#createOrder(com.deppon.crm.module.order.shared.domain.Order)}
	 * 的测试方法。
	 */
	@Test
	public void testCreateOrder() {
		order.setResource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		order.setStartStation("11174");
		order.setIsReceiveGoods(false);
		order.setChannelNumber(null);
		order.setContactMobile("15012852109");
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		order.setContactCity("重庆");
		orderManager.createOrder(order);
		order.setShipperId(null);
		
		order.setResource(Constant.ORDER_SOURCE_YOUSHANG);
		order.setStartStation("");
		order.setChannelNumber(null);
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		order.setContactCity("重庆");
		order.setContactMobile("13632710708");
		orderManager.createOrder(order);
		
		
		order.setChannelNumber(System.currentTimeMillis() + "");
		order.setResource(Constant.ORDER_SOURCE_ONLINE);
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		order.setContactCity("重庆");
		order.setStartStation(null);
		orderManager.createOrder(order);
		
		order.setChannelNumber(System.currentTimeMillis() + "");
		order.setResource(Constant.ORDER_SOURCE_ALIBABA);
		order.setDeliveryMode(Constant.ORDER_DELIVERMODE_PICKFOOR);
		order.setContactCity("重庆");
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		orderManager.createOrder(order);
		
		/*//有异常
		order.setResource(Constant.ORDER_SOURCE_ALIBABA);
		order.setStartStation("14716");
		order.setChannelNumber("1355460104406");
		order.setContactCity("重庆");
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		//order.setContactMobile("13632750708");
		try {
			orderManager.createOrder(order);
			fail("木有异常");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.CREATEORDER_DOUBLECHANNELNUMBER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}*/
////		 ids.add(order.getId());
//	
////		 order.setContactProvince("北京");
////		 order.setContactCity("北京");
////		 order.setContactArea("天安门");
////		 order.setStartStation("14721");
////		 order.setResource(Constant.ORDER_SOURCE_CALLCENTER);
////		 order.setChannelNumber(System.currentTimeMillis() + "");
////		 orderManager.createOrder(order);
////		
////		 这个方法要把全部数据查出来
////		 order.setResource(Constant.ORDER_SOURCE_ONLINE);
////		 order.setChannelNumber(null);
////		 try {
////		 orderManager.createOrder(order);
////		 } catch (Exception e) {
////		 assertTrue(true);
////		 }

	}

	/**
	 * {@link com.deppon.crm.module.order.server.manager.impl.OrderManager#updateOrder(com.deppon.crm.module.order.shared.domain.Order)}
	 * 的测试方法。
	 */
	@Test
	public void testModifyOrder() {
		order.setAcceptDeptName("诗圣杜甫");
		orderManager.createOrder(order);
		ids.add(order.getId());
		orderManager.updateOrder(order, user);

		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		orderManager.createOrder(order);
		ids.add(order.getId());
		assertNotNull(orderManager.updateOrder(order, user));

		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		orderManager.createOrder(order);
		ids.add(order.getId());
		assertNotNull(orderManager.updateOrder(order, user));

		order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		orderManager.createOrder(order);
		ids.add(order.getId());
		assertNotNull(orderManager.updateOrder(order, user));

		order.setOrderStatus(Constant.ORDER_STATUS_REJECT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		orderManager.createOrder(order);
		ids.add(order.getId());
		assertNotNull(orderManager.updateOrder(order, user));
		
	}

	@Test
	public void testSearchAssignAndRefuseOrders() {
		OrderSearchCondition osc = new OrderSearchCondition();
		
		orderManager.searchAssignAndRefuseOrders(osc);
		
		osc.setEndDate(new Date());
		osc.setResource(Constant.ORDER_STATUS_ALL);
		osc.setOrderStatus(Constant.ORDER_STATUS_ALL);
		osc.setStart(1);
		osc.setLimit(3);
		osc.setOrderNum("1");
		orderManager.searchAssignAndRefuseOrders(osc);
		
		osc.setOrderNum("");
		osc.setWaybillNum("1");
		orderManager.searchAssignAndRefuseOrders(osc);
		
		osc.setWaybillNum("");
		osc.setContactMobile("1");
		orderManager.searchAssignAndRefuseOrders(osc);
		
		osc.setContactMobile("");
		osc.setContactPhone("1");
		orderManager.searchAssignAndRefuseOrders(osc);
		
		osc.setContactPhone("");
		osc.setStartDate(new Date());
		orderManager.searchAssignAndRefuseOrders(osc);
	}

	@Test
	public void testGetOrderById() {
		orderManager.createOrder(order);
		orderManager.getOrderById(order.getId());
	}

	@Test
	public void testAssignOrder() {
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		order.setResource(Constant.ORDER_SOURCE_ONLINE);

		List<String> orderIds = new ArrayList<String>();

		order.setChannelNumber(System.currentTimeMillis() + "");
		Order order1 = orderManager.createOrder(order);
		ids.add(order.getId());

		order.setChannelNumber(System.currentTimeMillis() + "");
		Order order2 = orderManager.createOrder(order);
		ids.add(order.getId());

		orderIds.add(order1.getId());
		orderIds.add(order2.getId());

		Order o = new Order();

		Map<String, Object> map = ladingstationDepartmentManager
				.getAllLandingStationDepartment(0, 1);
		if (map != null && !map.isEmpty()) {
			List<LadingstationDepartment> list = (List<LadingstationDepartment>) map
					.get("LANDINGSTATIONDEPARTMENT_LIST");
			if (list != null && list.size() > 0) {
				o.setStartStation(list.get(0).getBeginDeptStandardCode());
				o.setStartStationName(list.get(0).getBeginDepName());
				this.orderManager.assignOrder(orderIds, o, user);
			}
		}
	}

	@Test
	public void testRefuseOrder() {
		order.setOrderStatus(Constant.ORDER_STATUS_REJECT);
		orderManager.createOrder(order);
		ids.add(order.getId());

		// 不符合拒绝条件(状态不是未分配、待受理)
		try {
			orderManager.refuseOrder(order.getId(), user, "refuse");
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}

		// 校验打回原因为空
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		this.orderManager.updateOrder(order, user);
		try {
			orderManager.refuseOrder(order.getId(), user, null);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}

		// 符合拒绝条件
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		this.orderManager.updateOrder(order, user);
		try {
			orderManager.refuseOrder(order.getId(), user, "refuse");
		} catch (Exception e) {
			assertTrue(true);
		}

	}

	@Test
	public void testGetOrderOperationLogList() {
		OrderView orderView = new OrderView();
		orderView.setOrder(order);
		OrderView order1 = new OrderView();
		order1.setOrder(orderManager.createOrder(orderView.getOrder()));
		ids.add(order1.getOrder().getId());
		orderManager.getOrderOperationLogList("10348");
	}

	@Test
	public void testRemindOrderMonitor() {
		// orderManager.remindOrderMonitor();
	}

	@Test
	public void testSearchProcessAndReturnOrders() {
		OrderSearchView view = new OrderSearchView();
		OrderSearchCondition condition = new OrderSearchCondition();
		condition.setStart(0);
		condition.setLimit(10);
		view.setOrderSearchCondition(condition);
		condition.setEndDate(new Date());
		OrderSearchCondition vosc = view.getOrderSearchCondition();
		Map map = orderManager.searchProcessAndReturnOrders(vosc, user);
		List<Order> orderList = (List) map.get("orderList");
		Long totalCount = Long.valueOf(map.get("count").toString());
		vosc = (OrderSearchCondition) map.get("vosc");
		view.setOrderSearchCondition(vosc);
		view.setOrderList(orderList);
		view.setTotalCount(totalCount);
		
		vosc.setCreateEmpName(System.currentTimeMillis()+"");
		orderManager.searchProcessAndReturnOrders(vosc, user);
		
		orderManager.createOrder(order);
		vosc.setOrderNum(order.getId());
		vosc.setCreateEmpName(null);
		vosc.setStartStationId(System.currentTimeMillis()+"");
		orderManager.searchProcessAndReturnOrders(vosc, user);
		
		vosc.setOrderNum(null);
		vosc.setContactMobile(order.getContactMobile());
		vosc.setStartStationId("");
		vosc.setResource(Constant.ORDER_STATUS_ALL);
		vosc.setOrderStatus(Constant.ORDER_STATUS_ALL);
		condition.setStart(null);
		condition.setLimit(null);
		orderManager.searchProcessAndReturnOrders(vosc, user);
		
		vosc.setContactMobile(null);
		vosc.setWaybillNum(order.getWaybillNumber());
		vosc.setCreateEmpName(null);
		vosc.setStartStationId(System.currentTimeMillis()+"");
		orderManager.searchProcessAndReturnOrders(vosc, user);
		
		
		vosc.setWaybillNum(null);
		vosc.setContactPhone(order.getContactPhone());
		vosc.setCreateEmpName(null);
		vosc.setStartStationId(System.currentTimeMillis()+"");
		orderManager.searchProcessAndReturnOrders(vosc, user);
		
	}

	@Test
	public void testProcessOrder() {
		orderManager.createOrder(order);
		ids.add(order.getId());
		try {
			orderManager.processOrder(order.getId(), user, null);
			
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetOrderIfCanBookVehicle() {
		orderManager.getOrderIfCanBookVehicle("");
		
		order.setStartStation("323223");
		order.setReceivingToPoint("252223");
		orderManager.createOrder(order);
		orderManager.getOrderIfCanBookVehicle(order.getId());
	}

	@Test
	public void testBookVehicle() {
		order.setResource(Constant.ORDER_SOURCE_TAOBAO);
		order.setGoodsNumber(1);
		order.setTotalVolume(1D);
		order.setTotalWeight(1D);
		this.orderManager.createOrder(order);

		order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
		user.setId("DP0000");
		this.orderManager.updateOrder(order, user);

		BookVehicleInfo bkinfo = new BookVehicleInfo();
		bkinfo.setIsTailBoard(true);
		bkinfo.setUseVehicleDept("1");
		bkinfo.setVehicleTeam("1");
		try {
			this.orderManager.bookVehicle(order, bkinfo, user);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testQueryOrders() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startLine",1);
		map.put("endLine",1);
		map.put("customerCode","cust001");
		map.put("orderNumber","T121214532557");
		orderManager.queryOrders(map);
	}
	
	@Test
	public void testWaybill() {//跟进运单测不通
		this.orderManager.getOrderWaybill("1");
		this.orderManager.getWaybillByNumber("1");
		WaybillInfo waybill = new WaybillInfo();
		waybill.setWaybillNumber("1");
		try {
			this.orderManager.associateOrderWaybill(waybill, "1");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.WAYBILL_NOT_EXIST.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
	}

	@Test
	public void testGetOrderWithValidation() {
		OrderView view = new OrderView();
		OrderView orderView = new OrderView();
		orderView.setOrder(order);
		OrderView ord = new OrderView();
		ord.setOrder(orderManager.createOrder(orderView.getOrder()));
		ids.add(ord.getOrder().getId());

		Map map = orderManager.getOrderWithValidation(ord.getOrder().getId(),user);
		Order order = (Order) map.get("order");
		WaybillInfo waybill = (WaybillInfo) map.get("waybill");
		BussinessDept beginDept = (BussinessDept) map.get("beginDept");
		BussinessDept endDept = (BussinessDept) map.get("endDept");
		view.setOrder(order);
		view.setWaybill(waybill);
		view.setBeginDept(beginDept);
		view.setEndDept(endDept);

		Map map1 = orderManager.getOrderWithValidation(ord.getOrder().getId(),user);
		Order order1 = (Order) map1.get("order");
		WaybillInfo waybill1 = (WaybillInfo) map1.get("waybill");
		BussinessDept beginDept1 = (BussinessDept) map1.get("beginDept");
		BussinessDept endDept1 = (BussinessDept) map1.get("endDept");
		view.setOrder(order1);
		view.setWaybill(waybill1);
		view.setBeginDept(beginDept1);
		view.setEndDept(endDept1);

		order.setOrderStatus("undeal");
		order.setStartStationName("xxx");
		try {
			ord.setOrder(orderManager.createOrder(order));
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.CREATEORDER_DOUBLECHANNELNUMBER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		ids.add(ord.getOrder().getId());

		Map map2 = orderManager.getOrderWithValidation(ord.getOrder().getId(),user);
		Order order2 = (Order) map.get("order");
		WaybillInfo waybill2 = (WaybillInfo) map.get("waybill");
		BussinessDept beginDept2 = (BussinessDept) map.get("beginDept");
		BussinessDept endDept2 = (BussinessDept) map.get("endDept");
		view.setOrder(order2);
		view.setWaybill(waybill2);
		view.setBeginDept(beginDept2);
		view.setEndDept(endDept2);
	}

	@Test
	public void testGetWaybillByNumber() {
		WaybillInfo wb = orderManager.getWaybillByNumber("23");
	}

	@Test
	public void testAssociateOrderWaybill() {

		OrderView ov = new OrderView();
		ov.setOrder(order);
		OrderView ord = new OrderView();
		ord.setOrder(orderManager.createOrder(ov.getOrder()));
		ids.add(ord.getOrder().getId());
		ord.setOrderId(ord.getOrder().getId());

		WaybillInfo waybillInfo = new WaybillInfo();
		waybillInfo.setShipperCustomer("周杰伦");
		waybillInfo.setShipperPhone("021-31350806");
		waybillInfo.setWaybillNumber("23");
		waybillInfo.setWaybillStatus((int) (Math.random() * 10));
		ord.setWaybill(waybillInfo);

		try {
			orderManager.associateOrderWaybill(waybillInfo, ord.getOrder()
					.getId());
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testReturnOrder() {
		// 状态不满足
		order.setOrderStatus("1");
		order.setChannelNumber(System.currentTimeMillis() + "");
		orderManager.createOrder(order);
		ids.add(order.getId());
		try {
			orderManager.returnOrder(order.getId(), "return", user);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}

		// 反馈信息不满足
		order.setResource(Constant.ORDER_SOURCE_ONLINE);
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		orderManager.createOrder(order);
		ids.add(order.getId());
		try {
			orderManager.returnOrder(order.getId(), null, user);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}

		// 都满足
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		order.setChannelNumber(System.currentTimeMillis() + "");
		orderManager.createOrder(order);
		ids.add(order.getId());
		try {
			orderManager.returnOrder(order.getId(), "return", user);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testCancelOrder() {
		 // 状态不满足
//		 order.setOrderStatus("1");
//		 order.setResource(Constant.ORDER_SOURCE_CALLCENTER);
//		 order.setChannelNumber(System.currentTimeMillis() + "");
//		 orderManager.createOrder(order);
//		 ids.add(order.getId());
//		 try {
//		 orderManager.cancelOrder(this.order.getId(), "cancel", user);
//		 } catch (Exception e1) {
//		 assertTrue(true);
//		 }
//		 
//		 // 反馈信息不满足
//		 order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
//		 order.setChannelNumber(System.currentTimeMillis() + "");
//		 orderManager.createOrder(order);
//		 ids.add(order.getId());
//		 try {
//		 orderManager.cancelOrder(order.getId(), "", user);
//		 fail();
//		 } catch (Exception e) {
//		 assertTrue(true);
//		 }
//		
//		 // 来源不满足
//		 order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
//		 order.setResource("110");
//		 order.setChannelNumber(System.currentTimeMillis() + "");
//		 orderManager.createOrder(order);
//		 ids.add(order.getId());
//		 try {
//		 orderManager.cancelOrder(order.getId(), "cancel", user);
//		 fail();
//		 } catch (Exception e) {
//		 assertTrue(true);
//		 }
//
//		 // 都满足
//		 order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
//		 order.setResource(Constant.ORDER_SOURCE_ONLINE + "");
//		 order.setChannelNumber(System.currentTimeMillis() + "");
//		 orderManager.createOrder(order);
//		 ids.add(order.getId());
//		 try {
//		 orderManager.cancelOrder(order.getId(), "cancel", user);
//		 } catch (Exception e) {
//		 assertTrue(true);
//		 }
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUrgeOrder() {
		// 时间不满足
		// order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		orderManager.createOrder(order);
		try {
			orderManager.urgeOrder(order.getId(), user);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		ids.add(order.getId());

		// 状态不满足
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		order.setCreateDate(new Date(112, 3, 1));
		orderManager.updateOrder(order, user);
		try {
			orderManager.urgeOrder(order.getId(), user);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		ids.add(order.getId());

		// 都满足
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		order.setCreateDate(new Date(112, 3, 1));
		orderManager.updateOrder(order, user);
		try {
			orderManager.urgeOrder(order.getId(), user);
		} catch (Exception e) {
			fail();
		}
		ids.add(order.getId());
	}

	@Test
	public void testAccpetFail() {
		// 状态不满足
		order.setOrderStatus("1");
		OrderView orderView = new OrderView();
		orderView.setOrder(order);
		OrderView o = new OrderView();
		o.setOrder(orderManager.createOrder(orderView.getOrder()));
		ids.add(o.getOrder().getId());
		try {
			Order order = (Order) orderManager.accpetFail(o.getOrder().getId(),
					"accpetFail", user).get("order");
			o.setOrder(order);
		} catch (Exception e) {
			assertTrue(true);
		}

		// 反馈信息不满足
		order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
		orderView = new OrderView();
		orderView.setOrder(order);
		try {
			o.setOrder(orderManager.createOrder(orderView.getOrder()));
		} catch (GeneralException e1) {
			if(e1.getErrorCode().equals(OrderExceptionType.CREATEORDER_DOUBLECHANNELNUMBER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		ids.add(o.getOrder().getId());
		try {
			orderManager.accpetFail(o.getOrder().getId(), "", user);
		} catch (Exception e) {
			assertTrue(true);
		}

		// 都满足
		order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
		orderView = new OrderView();
		orderView.setOrder(order);
		try {
			o.setOrder(orderManager.createOrder(orderView.getOrder()));
		} catch (GeneralException e1) {
			if(e1.getErrorCode().equals(OrderExceptionType.CREATEORDER_DOUBLECHANNELNUMBER.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		ids.add(o.getOrder().getId());
		try {
			orderManager.accpetFail(o.getOrder().getId(), "accpetFail", user);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCallCenterOrderInit() {
		orderManager.createOrder(order);
		ids.add(order.getId());
		orderManager.callCenterOrderInit(order
				.getShipperId());
		
		orderManager.callCenterOrderInit("12454");
	}

	@Test
	public void testSearchInitReceiveGoodsAddress() {

		BussinessDept dept1 = null;
		Map map = orderManager.searchInitReceiveGoodsAddress(dept1);
		dept1 = new BussinessDept();
		map = orderManager.searchInitReceiveGoodsAddress(dept1);
		dept1.setDeptName("惠州营业部");
		Province province = new Province();
		province.setName("广东省");

		City city = new City();
		city.setName("惠州市");

		Area area = new Area();
		area.setName("区域");
		dept1.setProvince(province);
		dept1.setCity(city);
		dept1.setRegion(area);

		dept1.setDeptAddress("详细地址");
		map = orderManager.searchInitReceiveGoodsAddress(dept1);
		System.out.println(map.get("provinceName"));
		System.out.println(map.get("cityName"));
		System.out.println(map.get("regionName"));
		System.out.println(map.get("deptAddress"));

		dept1.setDeptName("徐泾营业部");
		map = orderManager.searchInitReceiveGoodsAddress(dept1);
		System.out.println(map.get("provinceName"));
		System.out.println(map.get("cityName"));
		System.out.println(map.get("regionName"));
		System.out.println(map.get("deptAddress"));

		dept1.setDeptName("");
		map = orderManager.searchInitReceiveGoodsAddress(dept1);
		System.out.println(map.get("provinceName"));
		System.out.println(map.get("cityName"));
		System.out.println(map.get("regionName"));
		System.out.println(map.get("deptAddress"));

		String id = "10384";
		BussinessDept dept3 = orderManager.createOrderInit(id);

		map = orderManager.searchInitReceiveGoodsAddress(dept3);
		System.out.println(map.get("provinceName"));
		System.out.println(map.get("cityName"));
		System.out.println(map.get("regionName"));
		System.out.println(map.get("deptAddress"));

	}

/*	@Test
	public void testGetStartStationName() {
		String phone1 = "13816174681";

		Department dept = new Department();
		dept.setId("10384");
		Employee emp = new Employee();
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		Map<String, Object> map = orderManager.searchMemberInfoByPhone(phone1,
				user);
		Member m = (Member) map.get("member");
		System.out.println(m.getCustName());
		BussinessDept mm = (BussinessDept) map.get("busDept");
		System.out.println(mm.getDeptName());

		String phone2 = "13833233097";
		Department dept1 = new Department();
		dept1.setId("14154");
		Employee emp1 = new Employee();
		emp1.setDeptId(dept1);
		user.setEmpCode(emp1);
		Map<String, Object> map1 = orderManager.searchMemberInfoByPhone(phone2,
				user);
		Member m1 = (Member) map1.get("member");
		BussinessDept m11 = (BussinessDept) map1.get("busDept");

		String phone3 = "138000000000000";
		dept1 = new Department();
		dept1.setId("10384");
		emp1 = new Employee();
		emp1.setDeptId(dept1);
		user.setEmpCode(emp1);
		map1 = orderManager.searchMemberInfoByPhone(phone3, user);
		m1 = (Member) map1.get("member");
		m11 = (BussinessDept) map1.get("busDept");
		System.out.println(m1 == null);
		System.out.println(m11 == null);

		String phone4 = null;
		dept = new Department();
		dept.setId("10384");
		emp = new Employee();
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		map = orderManager.searchMemberInfoByPhone(phone4, user);
		m = (Member) map.get("member");
		mm = (BussinessDept) map.get("busDept");

		String phone5 = null;
		dept = new Department();
		dept.setId("11111111111");
		emp = new Employee();
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		map = orderManager.searchMemberInfoByPhone(phone5, user);
		m = (Member) map.get("member");
		mm = (BussinessDept) map.get("busDept");
	}*/

	@Test
	public void testSaveOrderRemarkInfo() {
		log = new OrderOperationLog();
		log.setContactName("增红");
		log.setOperatorType("REMARK");
		log.setOrderId("123456");
		log.setOperatorContent("备注信息已保存");
		Employee emp = new Employee();
		Department dept = new Department();
		dept.setDeptName("惠州惠城区江北营业部");
		emp.setEmpName("李晶");
		emp.setEmpCode("005072");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		orderManager.saveOrderRemarkInfo(log, user);
		System.out.println(log.getOperatorId());
		System.out.println(log.getOperatorOrgId());
		System.out.println(log.getOperatorTime());
		try {
			log.setContactName(null);
			orderManager.saveOrderRemarkInfo(log, user);
			System.out.println(log.getOperatorId());
			System.out.println(log.getOperatorOrgId());
			System.out.println(log.getOperatorTime());
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					OrderExceptionType.CANNOT_ISNULL_CONCATNAME.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			log.setContactName("增红");
			log.setOrderId(null);
			orderManager.saveOrderRemarkInfo(log, user);
			System.out.println(log.getOperatorId());
			System.out.println(log.getOperatorOrgId());
			System.out.println(log.getOperatorTime());
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					OrderExceptionType.CANNOT_ISNULL_ORDERID.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			log.setContactName("增红");
			log.setOrderId("123456");
			log.setOperatorContent(null);
			orderManager.saveOrderRemarkInfo(log, user);
			System.out.println(log.getOperatorId());
			System.out.println(log.getOperatorOrgId());
			System.out.println(log.getOperatorTime());
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					OrderExceptionType.CANNOT_ISNULL_OPERATIONCONTENT
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
	}

	@Test
	public void testSendFossOrderAcceptInfo() {
		List<ResultDetal> r = orderManager.sendFossOrderAcceptInfo();
		for (ResultDetal resultDetal : r) {
			System.out.println(resultDetal.getDeptCode() + " "
					+ resultDetal.getResultCode() + " "
					+ resultDetal.getReason());

		}
	}
	@Test
	public void testUpdateDelayToUnaccept(){
		orderManager.updateDelayToUnaccept();
	}

}
