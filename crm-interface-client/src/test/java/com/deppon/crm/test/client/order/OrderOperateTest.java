package com.deppon.crm.test.client.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.util.DateUtils;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.DataFormatUtil;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.ErpRemind;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.ebm.OrderStatusReqInfo;
import com.deppon.erp.custAndOrder.ExceptionOrder;
import com.deppon.foss.crm.CancelOrderResponse;

public class OrderOperateTest extends OperaterTest {

	/**
	 * 订单消息在ERP提醒
	 */
	@Test
	public void sendErpRemind() {
		try {
			List<ErpRemind> reminds = new ArrayList<ErpRemind>();
			ErpRemind remind = new ErpRemind();
			remind.setFdescription(ErpRemind.ORDER_ACCEPT_DESC);
			remind.setFlastupdateDate(new Date());
			remind.setFmsgNum(5);
			remind.setFstandardcode("DP");
			remind.setFsubModule(ErpRemind.ORDER_ACCEPT_MODULE);
			remind.setFsubModuleNum(ErpRemind.ORDER_ACCEPT);
			reminds.add(remind);
			String result = orderOperate.sendErpRemind(reminds);
			System.out.println(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryEBMOrderStatusTest() {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - 1);
			List<OrderStatusReqInfo> list = orderOperate.queryEBMOrderStatus(
					c.getTime(), new Date());
			for (OrderStatusReqInfo info : list) {
				System.out.println(JsonMapperUtil.writeValue(info));
			}
			System.out.println(list.size());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryERPOrderStatusTest() {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - 1);
			System.out.println(DateUtils.format(c.getTime(),
					"yyyy-MM-dd HH:mm:ss")
					+ DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			List<ExceptionOrder> list = orderOperate.queryERPOrderStatus(
					c.getTime(), new Date());
			System.out.println(list.size());
			for (ExceptionOrder info : list) {
				System.out.println("订单号：" + info.getOrderNum() + " 反馈信息:"
						+ info.getRemark() + " 运单号:" + info.getWayBillNum()
						+ " 异常原因:" + info.getExceptionReason() + " 订单状态:"
						+ info.getOrderStatus() + " 时间:"
						+ info.getOperateDate());
			}
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void appointmentCarTest() {
		try {
			// System.out.println(orderOperate2.appointmentCar(getAppointmentCarInfo()));
			System.out.println(orderOperate
					.appointmentCar(getAppointmentCarInfo()));

		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateOrderStatusTest() {
		try {
			// boolean flag =
			// orderOperate2.updateOrderStatus(getChannelOrderStatusInfo());
			boolean flag = orderOperate
					.updateOrderStatus(getChannelOrderStatusInfo());
			System.out.println(flag);

		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCancelOrder(){
		try {
			CancelOrderResponse response=orderOperate.cancelOrder("K130624769449");
			System.out.println(response.getResult());
		} catch (CrmBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ChannelOrderStatusInfo getChannelOrderStatusInfo() {
		ChannelOrderStatusInfo channelOrderStatusInfo = new ChannelOrderStatusInfo();
		channelOrderStatusInfo.setOrderNumber("1");
		channelOrderStatusInfo.setChannelOrderNumber("AL566377620031");
		channelOrderStatusInfo.setOrderSource("ALIBABA");
		channelOrderStatusInfo.setOrderStatus("GOT");
		channelOrderStatusInfo.setComment("1");
		channelOrderStatusInfo.setWaybillNumber("1");

		return channelOrderStatusInfo;
	}

	
	 /* ReceiveGoodsBill bill = new ReceiveGoodsBill();
	 * bill.setCustName("custName"); bill.setCustTel("custTel");
	 * bill.setCustAddress("custAddress"); bill.setGoodsName("goodName");
	 * bill.setWeight(new BigDecimal(11)); bill.setCubage(new BigDecimal(10));
	 * bill.setPacking("packing");
	 * bill.setLadingStationId("ctLyVgEcEADgAWW5wKgCyaKVhPk=");
	 * bill.setGoodsType("0"); bill.setUsingVehicleDeptNum("W19011102");
	 * bill.setFirstPickTime(new Date()); bill.setLastPickTime(new Date());
	 * bill.setDeliveryVehNum("W220002070503"); bill.setOrderNumber("orderNum");
	 * bill.setOrderedTime(new Date()); bill.setOrderType("0");
	 * bill.setVehDeptNum("W19011102");
	 * 
	 * @param
	 * @return
	 * @Throws
	 * @author davidcun 2012-5-9
	 */
	 

	public AppointmentCarInfo getAppointmentCarInfo() {
		AppointmentCarInfo appointmentCarInfo = new AppointmentCarInfo();

		appointmentCarInfo.setCustName("11111");
		appointmentCarInfo.setCustTel("1");
		appointmentCarInfo.setCustAddress("1");
		appointmentCarInfo.setGoodsName("1");
		appointmentCarInfo.setWeight(new BigDecimal(123));
		appointmentCarInfo.setCubage(new BigDecimal(123));
		appointmentCarInfo.setPacking("1");
		appointmentCarInfo.setPieces(Integer.valueOf("1"));
		appointmentCarInfo.setSize("1");
		appointmentCarInfo.setRemark("1");
		/*appointmentCarInfo.setTransProperty(Integer.valueOf("1"));
		appointmentCarInfo.setDeliverMode(Integer.valueOf("1"));
		appointmentCarInfo.setLadingStationId("ctLyVgEcEADgAWW5wKgCyaKVhPk=");*/
		appointmentCarInfo.setGoodsType("A");
		appointmentCarInfo.setUsingVehicleDeptNum("DP00837");
		appointmentCarInfo.setUsingVehicleDeptId("1");
		appointmentCarInfo.setFirstPickTime(DataFormatUtil.parseDate(
				"2012-06-04 20:12:21", "yyyy-MM-dd HH:mm:ss"));
		appointmentCarInfo.setLastPickTime(DataFormatUtil.parseDate(
				"2012-06-05 12:12:21", "yyyy-MM-dd HH:mm:ss"));
		appointmentCarInfo.setDeliveryVehNum("DP07291");
		appointmentCarInfo.setDeliveryVehId("1");
		appointmentCarInfo.setOrderNumber("1");
		appointmentCarInfo.setOrderedTime(new Date());
		appointmentCarInfo.setOrderType("1");
		appointmentCarInfo.setVehDeptNum("DP08374");
		appointmentCarInfo.setVehDeptId("1");
		appointmentCarInfo.setCreatorNum("053951");
		appointmentCarInfo.setPaidMethod("信用卡");
		/**
		 * ReceiveGoodsBill bill = new ReceiveGoodsBill();
		 * bill.setCustName("custName"); bill.setCustTel("custTel");
		 * bill.setCustAddress("custAddress"); bill.setGoodsName("goodName");
		 * bill.setWeight(new BigDecimal(11)); bill.setCubage(new
		 * BigDecimal(10)); bill.setPacking("packing");
		 * bill.setLadingStationId("ctLyVgEcEADgAWW5wKgCyaKVhPk=");
		 * bill.setGoodsType("0"); bill.setUsingVehicleDeptNum("W19011102");
		 * bill.setFirstPickTime(new Date()); bill.setLastPickTime(new Date());
		 * bill.setDeliveryVehNum("W220002070503");
		 * bill.setOrderNumber("orderNum"); bill.setOrderedTime(new Date());
		 * bill.setOrderType("0"); bill.setVehDeptNum("W19011102");
		 */
		return appointmentCarInfo;
	}
}
