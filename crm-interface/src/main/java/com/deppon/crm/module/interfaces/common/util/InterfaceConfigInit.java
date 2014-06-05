//package com.deppon.crm.module.interfaces.common.util;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.deppon.crm.module.client.common.FileInterfaceSerialize;
//import com.deppon.crm.module.client.common.InterfaceSerialize;
//import com.deppon.crm.module.client.common.domain.InterfaceConfig;
//import com.deppon.crm.module.client.common.domain.MethodConfig;
//import com.deppon.crm.module.client.common.util.Constant;
//import com.deppon.crm.module.client.customer.impl.CustomerOperateImpl;
//import com.deppon.crm.module.client.order.impl.OaAccidentOperateImpl;
//import com.deppon.crm.module.client.order.impl.OrderOperateImpl;
//import com.deppon.crm.module.client.order.impl.WaybillOperateImpl;
//import com.deppon.crm.module.client.sms.impl.SmsSenderImpl;
//import com.deppon.crm.module.client.workflow.impl.ContractApplyOperateImpl;
//import com.deppon.crm.module.client.workflow.impl.GiftApplyOperateImpl;
//import com.deppon.crm.module.client.workflow.impl.PaymentApplyOperateImpl;
//import com.deppon.crm.module.client.workflow.impl.RecompenseApplyOperateImpl;
//import com.deppon.crm.module.interfaces.customer.impl.CustomerServiceImpl;
//import com.deppon.crm.module.interfaces.order.impl.OrderServiceImpl;
//import com.deppon.crm.module.interfaces.workflow.impl.ESBWorkFlowResultServiceImpl;
//import com.deppon.crm.module.interfaces.workflow.impl.GiftApplyResultServiceImpl;
//import com.deppon.crm.module.interfaces.workflow.impl.PaymentApplyResultServiceImpl;
//import com.deppon.crm.module.interfaces.workflow.impl.RecompenseOnlineServiceImpl;
//
//public class InterfaceConfigInit {
//
//	public static void main(String[] args) {
//		InterfaceSerialize serialize = new FileInterfaceSerialize(
//				Constant.INTERFACE_DEFAULT_FILE_PATH);
//		serialize.serialize(getOutwardService(getOutwardService()));
//	}
//	
//	public static List<Class> getInsideService(){
//		List<Class> insideCls = new ArrayList<Class>();
//		insideCls.add(CustomerOperateImpl.class);
//		insideCls.add(OaAccidentOperateImpl.class);
//		insideCls.add(OrderOperateImpl.class);
//		insideCls.add(WaybillOperateImpl.class);
//		insideCls.add(SmsSenderImpl.class);
//		insideCls.add(ContractApplyOperateImpl.class);
//		insideCls.add(GiftApplyOperateImpl.class);
//		insideCls.add(PaymentApplyOperateImpl.class);
//		insideCls.add(RecompenseApplyOperateImpl.class);
//		return insideCls;
//	}
//	
//	public static List<Class> getOutwardService(){
//		List<Class> outwardCls = new ArrayList<Class>();
//		outwardCls.add(CustomerServiceImpl.class);
//		outwardCls.add(OrderServiceImpl.class);
//		outwardCls.add(ESBWorkFlowResultServiceImpl.class);
//		outwardCls.add(GiftApplyResultServiceImpl.class);
//		outwardCls.add(PaymentApplyResultServiceImpl.class);
//		outwardCls.add(RecompenseOnlineServiceImpl.class);
//		return outwardCls;
//	}
//	public static List<InterfaceConfig> getOutwardService(List<Class> clses) {
//
//		List<InterfaceConfig> interfaceConfigs = new ArrayList<InterfaceConfig>();
//
//		for (Class cls : clses) {
//			InterfaceConfig interfaceConfig = new InterfaceConfig();
//			interfaceConfig.setClassName(cls.getName());
//			interfaceConfig.setOutwardService(true);
//			Method[] mtd = cls.getDeclaredMethods();
//
//			List<MethodConfig> methodConfigs = new ArrayList<MethodConfig>();
//			for (Method method : mtd) {
//				MethodConfig methodConfig = new MethodConfig();
//				methodConfig.setName(method.getName());
//				methodConfig.setRecordPerformanceLog(true);
//				methodConfig.setRecordExceptionLog(true);
//				methodConfigs.add(methodConfig);
//			}
//			interfaceConfig.setMethodConfigs(methodConfigs);
//			interfaceConfigs.add(interfaceConfig);
//		}
//		return interfaceConfigs;
//	}
//
//	public static List<InterfaceConfig> getInsideService(List<Class> clses) {
//
//		List<InterfaceConfig> interfaceConfigs = new ArrayList<InterfaceConfig>();
//
//		for (Class cls : clses) {
//			InterfaceConfig interfaceConfig = new InterfaceConfig();
//			interfaceConfig.setClassName(cls.getName());
//			interfaceConfig.setOutwardService(false);
//			//获取public方法，包括继承的
//			Method[] mtd = cls.getDeclaredMethods();
//			
//			List<MethodConfig> methodConfigs = new ArrayList<MethodConfig>();
//			for (Method method : mtd) {
//				MethodConfig methodConfig = new MethodConfig();
//				methodConfig.setName(method.getName());
//				methodConfig.setRecordPerformanceLog(true);
//				methodConfig.setRecordExceptionLog(true);
//				methodConfigs.add(methodConfig);
//			}
//			interfaceConfig.setMethodConfigs(methodConfigs);
//			interfaceConfigs.add(interfaceConfig);
//		}
//		return interfaceConfigs;
//	}
//}
