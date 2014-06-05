package com.deppon.crm.test.client.order;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.test.client.common.OperaterTest;

public class OrderOperateTest1 extends OperaterTest {
	
	@Test
	public void TestAppointmentCar() throws CrmBusinessException{
		
		AppointmentCarInfo info=new AppointmentCarInfo();
		info.setCreatorId("creatorId");
		info.setCreatorNum("11");
		info.setCubage(new BigDecimal(8));
		info.setCustAddress("11");
		info.setCustArea("11");
		info.setCustCity("11");
		info.setCustMobile("11");
		info.setCustName("111");
		info.setCustProvince("1111");
		info.setCustTel("111");
		info.setDeliverMode("111");
		info.setDeliveryVehId("111");
		info.setDeliveryVehNum("111");
		info.setDeptCode("111");
		info.setFirstPickTime(new Date());
		info.setGoodsName("111");
		info.setGoodsType("111");
		info.setGoodsType("111");
		info.setLastPickTime(new Date());
		info.setOrderedTime(new Date());
		info.setOrderNumber("111");
		info.setOrderType("111");
		info.setPacking("111");
		info.setPieces(111);
		info.setRemark("dddfddf");
		info.setWeight(new BigDecimal(6));
		info.setVehicleType("dd");
		info.setVehDeptNum("dd");
		info.setVehDeptId("fgfg");
		info.setUsingVehicleDeptNum("6");
		info.setUsingVehicleDeptId("fgfg");
		info.setTransProperty("dddf");
		info.setSize("6");
		info.setMemberType("CXT");
		boolean response=orderOperate.appointmentCar(info);
	}

}
