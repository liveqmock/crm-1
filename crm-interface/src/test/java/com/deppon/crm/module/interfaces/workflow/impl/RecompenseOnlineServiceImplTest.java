package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.Date;
import java.util.List;

import javax.xml.ws.Holder;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IRecompenseOnlineService;
import com.deppon.crm.module.interfaces.workflow.domain.OnlineApplyInfo;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineQueryCondition;

/**
 * @作者：罗典
 * @描述：在线理赔测试
 * @时间：2012-4-26
 * */
public class RecompenseOnlineServiceImplTest {
	IRecompenseOnlineService recompenseOnlineService;

	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.getInInterceptors().add(new LoggingInInterceptor());
		jax.getOutInterceptors().add(new LoggingOutInterceptor());
		jax.setAddress("http://localhost:8088/crm/ws/recompenseOnlineService");
		jax.setServiceClass(IRecompenseOnlineService.class);
		recompenseOnlineService = (IRecompenseOnlineService) jax.create();
	}

	/**
	 * @作者：罗典
	 * @描述：撤销在线理赔
	 * @时间：2012-4-26
	 * */
	public void testCancelRecompenseOnline() {
		try {
			recompenseOnlineService.cancelRecompenseOnline("111", "111");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @作者：罗典
	 * @描述：创建在线理赔
	 * @时间：2012-4-26
	 * */
	@Test
	public void testCreateRecompenseOfOnline() {
		OnlineApplyInfo onlineApplyInfo = new OnlineApplyInfo();
		onlineApplyInfo.setRecompenseNumber("1");
		onlineApplyInfo.setClaimant("1");
		onlineApplyInfo.setRecompenseDate(new Date());
		onlineApplyInfo.setLastModifyTime(new Date());
		onlineApplyInfo.setPartA("1");
		onlineApplyInfo.setPartB("1");
		onlineApplyInfo.setPartAsign("1");
		onlineApplyInfo.setPartAsignDate(new Date());
		onlineApplyInfo.setPartBAsign("1");
		onlineApplyInfo.setPartBAsignDate(new Date());
		onlineApplyInfo.setRecompenseId("1");
		onlineApplyInfo.setWaybillNumber("37614731");
		onlineApplyInfo.setCustomerNum("1");
		onlineApplyInfo.setCustomerId("1");
		onlineApplyInfo.setRecompenseAmount(Double.valueOf("1"));
		onlineApplyInfo.setInsurAmount(Double.valueOf("1"));
		onlineApplyInfo.setRecompenseReason("1");
		onlineApplyInfo.setRejectReason("1");
		onlineApplyInfo.setApplyTime(new Date());
		onlineApplyInfo.setRejectTime(new Date());
		onlineApplyInfo.setIdCard("1");
		onlineApplyInfo.setBankName("1");
		onlineApplyInfo.setBranchName("1");
		onlineApplyInfo.setOpenName("1");
		onlineApplyInfo.setAccount("1");
		onlineApplyInfo.setMobile("1");
		onlineApplyInfo.setTelphone("1");
		onlineApplyInfo.setProvince("1");
		onlineApplyInfo.setCity("1");
		onlineApplyInfo.setCounty("1");
		onlineApplyInfo.setOnlineUser("1");
		onlineApplyInfo.setTransType("1");
		onlineApplyInfo.setStartStation("1");
		onlineApplyInfo.setEndStation("1");
		onlineApplyInfo.setSendDate(new Date());
		onlineApplyInfo.setApplyDeptId("DP08558");
		System.out.println("luodiann".lastIndexOf("l"));
		try {
			recompenseOnlineService.createRecompenseOfOnline(onlineApplyInfo);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @作者：罗典
	 * @描述：修改在线理赔
	 * @时间：2012-4-26
	 * */
	public void testUpdateRecompenseOfOnline() {
		OnlineApplyInfo onlineApplyInfo = new OnlineApplyInfo();
		onlineApplyInfo.setRecompenseNumber("1");
		onlineApplyInfo.setClaimant("1");
		onlineApplyInfo.setRecompenseDate(new Date());
		onlineApplyInfo.setLastModifyTime(new Date());
		onlineApplyInfo.setPartA("1");
		onlineApplyInfo.setPartB("1");
		onlineApplyInfo.setPartAsign("1");
		onlineApplyInfo.setPartAsignDate(new Date());
		onlineApplyInfo.setPartBAsign("1");
		onlineApplyInfo.setPartBAsignDate(new Date());
		onlineApplyInfo.setRecompenseId("1");
		onlineApplyInfo.setWaybillNumber("12312349");
		onlineApplyInfo.setCustomerNum("1");
		onlineApplyInfo.setCustomerId("1");
		onlineApplyInfo.setRecompenseAmount(Double.valueOf("1"));
		onlineApplyInfo.setInsurAmount(Double.valueOf("1"));
		onlineApplyInfo.setRecompenseReason("1");
		onlineApplyInfo.setRejectReason("1");
		onlineApplyInfo.setApplyTime(new Date());
		onlineApplyInfo.setRejectTime(new Date());
		onlineApplyInfo.setIdCard("1");
		onlineApplyInfo.setBankName("1");
		onlineApplyInfo.setBranchName("1");
		onlineApplyInfo.setOpenName("1");
		onlineApplyInfo.setAccount("1");
		onlineApplyInfo.setMobile("1");
		onlineApplyInfo.setTelphone("1");
		onlineApplyInfo.setProvince("1");
		onlineApplyInfo.setCity("1");
		onlineApplyInfo.setCounty("1");
		onlineApplyInfo.setOnlineUser("1");
		onlineApplyInfo.setTransType("1");
		onlineApplyInfo.setStartStation("1");
		onlineApplyInfo.setEndStation("1");
		onlineApplyInfo.setSendDate(new Date());
		onlineApplyInfo.setApplyDeptId("1");
		try {
			recompenseOnlineService.updateRecompenseOfOnline(onlineApplyInfo);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @作者：罗典
	 * @描述：根据条件查询在线理赔
	 * @时间：2012-4-26
	 * */
	@Test
	public void testQueryRecompenseOnlineInfos() {
		RecompenseOnlineQueryCondition recompenseOnlineQueryCondition = new RecompenseOnlineQueryCondition();
//		recompenseOnlineQueryCondition.setStartDate(new Date());
//		recompenseOnlineQueryCondition.setEndDate(new Date());
		recompenseOnlineQueryCondition.setCurrentPage(1);
		recompenseOnlineQueryCondition.setPageSize(100);
		recompenseOnlineQueryCondition.setUsername("qwe1234");
//		recompenseOnlineQueryCondition.setWaybillNumber("1");
//		recompenseOnlineQueryCondition.setCount(1);
		Holder<RecompenseOnlineQueryCondition> holder = new Holder<RecompenseOnlineQueryCondition>(
				recompenseOnlineQueryCondition);
		try {
			List<OnlineApplyInfo> list = recompenseOnlineService
					.queryRecompenseOnlineInfos(holder);
			System.out.println(list.size());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}

	}

	public void testQueryRecompenseOnlineEntity() {
		try {
			recompenseOnlineService.queryRecompenseOnlineEntity("111", "");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetRecompenseOnlineVerifyInfo() {
		try {
			recompenseOnlineService.getRecompenseOnlineVerifyInfo("5171044419");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-27
	 * @描述：申请再次付款
	 * */
	@Test
	public void testPaymentUpdate() {
		OnlineApplyInfo onlineApplyInfo = new OnlineApplyInfo();
		onlineApplyInfo.setRecompenseNumber("1");
		onlineApplyInfo.setClaimant("1");
		onlineApplyInfo.setRecompenseDate(new Date());
		onlineApplyInfo.setLastModifyTime(new Date());
		onlineApplyInfo.setPartA("1");
		onlineApplyInfo.setPartB("1");
		onlineApplyInfo.setPartAsign("1");
		onlineApplyInfo.setPartAsignDate(new Date());
		onlineApplyInfo.setPartBAsign("1");
		onlineApplyInfo.setPartBAsignDate(new Date());
		onlineApplyInfo.setRecompenseId("1");
		onlineApplyInfo.setWaybillNumber("12312349");
		onlineApplyInfo.setCustomerNum("1");
		onlineApplyInfo.setCustomerId("1");
		onlineApplyInfo.setRecompenseAmount(Double.valueOf("1"));
		onlineApplyInfo.setInsurAmount(Double.valueOf("1"));
		onlineApplyInfo.setRecompenseReason("1");
		onlineApplyInfo.setRejectReason("1");
		onlineApplyInfo.setApplyTime(new Date());
		onlineApplyInfo.setRejectTime(new Date());
		onlineApplyInfo.setIdCard("1");
		onlineApplyInfo.setBankName("1");
		onlineApplyInfo.setBranchName("1");
		onlineApplyInfo.setOpenName("1");
		onlineApplyInfo.setAccount("1");
		onlineApplyInfo.setMobile("1");
		onlineApplyInfo.setTelphone("1");
		onlineApplyInfo.setProvince("1");
		onlineApplyInfo.setCity("1");
		onlineApplyInfo.setCounty("1");
		onlineApplyInfo.setOnlineUser("1");
		onlineApplyInfo.setTransType("1");
		onlineApplyInfo.setStartStation("1");
		onlineApplyInfo.setEndStation("1");
		onlineApplyInfo.setSendDate(new Date());
		onlineApplyInfo.setApplyDeptId("1");
		try {
			recompenseOnlineService.paymentUpdate(onlineApplyInfo);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

}
