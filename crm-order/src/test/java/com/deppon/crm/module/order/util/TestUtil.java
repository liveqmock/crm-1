package com.deppon.crm.module.order.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.server.manager.impl.AreaAddressManager;
import com.deppon.crm.module.order.server.dao.IOrderDao;
import com.deppon.crm.module.order.server.dao.IOrderOperationLogDao;
import com.deppon.crm.module.order.server.dao.IOrderReminderDao;
import com.deppon.crm.module.order.server.dao.IVehicleHistoryDao;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.server.manager.IVehicleHistoryManager;
import com.deppon.crm.module.order.server.manager.impl.UnboundContactNumManager;
import com.deppon.crm.module.order.server.service.IOrderService;
import com.deppon.crm.module.order.server.service.impl.UnboundContactNumService;
import com.deppon.crm.module.order.server.util.OrderUtil;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.foss.framework.server.context.AppContext;



public class TestUtil {
	 static ClassPathXmlApplicationContext factory;
	public static IOrderDao orderDao;
	public static IOrderService orderService;
	public static IOrderOperationLogDao orderOperationLogDao;
	public static IOrderReminderDao orderReminderDao;
	public static IVehicleHistoryDao vehicleHistoryDao;
	public static IOrderManager orderManager;
	public static ILadingstationDepartmentManager ladingstationDepartmentManager;
	public static UnboundContactNumManager manager;
	public static IVehicleHistoryManager vehicleHistoryManager;
	private static UnboundContactNumService service;
	public static UserDao userDao ;
	public static OrderUtil orderUtil;
	public static AreaAddressManager areaAddressManager;
	public static IDepartmentService departmentService;






	static {
		AppContext.initAppContext("application", "","");
		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
		factory = new ClassPathXmlApplicationContext(resource);
		orderDao = (IOrderDao) factory.getBean("orderDao");
		orderService = (IOrderService) factory.getBean("orderService");
		orderOperationLogDao = (IOrderOperationLogDao) factory.getBean("orderOperationLogDao");
		orderReminderDao = (IOrderReminderDao) factory.getBean("orderReminderDao");
		vehicleHistoryDao = (IVehicleHistoryDao) factory.getBean("vehicleHistoryDao");
		orderManager = (IOrderManager) factory.getBean("orderManager");
		ladingstationDepartmentManager = (ILadingstationDepartmentManager) factory
				.getBean("ladingstationDepartmentManager");
		manager = (UnboundContactNumManager) factory.getBean("unboundContactNumManager");
		vehicleHistoryManager = (IVehicleHistoryManager) factory
				.getBean("vehicleHistoryManager");
		service = (UnboundContactNumService) factory.getBean("unboundContactNumService");
		userDao = (UserDao) factory.getBean("userDao");
		orderUtil = (OrderUtil)factory.getBean("orderUtil");
		areaAddressManager=(AreaAddressManager) factory.getBean("areaAddressManager");
		departmentService=(DepartmentService) factory.getBean("departmentService");

		
	}

}
