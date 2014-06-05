package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import junit.framework.TestCase;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IGoodTradeMappingManager;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IGoodTradeMappingService;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;
import com.deppon.crm.module.keycustomer.server.service.IKeyCustomerService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class GoodTradeMappingManagerTest extends TestCase {
	private static GoodTradeMappingManager goodTradeMappingManager = new GoodTradeMappingManager();

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 * @throws Exception
	 * 
	 */
	@Override
	protected void setUp() throws Exception {
		Mockery goodTradeMappingServiceMockery = new Mockery();
		final IGoodTradeMappingService goodTradeMappingService = goodTradeMappingServiceMockery.mock(IGoodTradeMappingService.class);
		goodTradeMappingServiceMockery.checking(new Expectations() {
			{
				allowing(goodTradeMappingService).countGoodTradeMappingByCondition(with(any(GoodTradeMappingSearchCondition.class)));
				will(returnValue(1));
				allowing(goodTradeMappingService).searchGoodTradeMappingByCondition(with(any(GoodTradeMappingSearchCondition.class)));
				will(returnValue(new ArrayList<GoodTradeMapping>()));
				allowing(goodTradeMappingService).updateGoodTradeMapping(with(any(GoodTradeMapping.class)));
			}
		});
		goodTradeMappingManager.setGoodTradeMappingService(goodTradeMappingService);
		Mockery keyCustomerServiceMockery = new Mockery();
		final IKeyCustomerService keyCustomerService = keyCustomerServiceMockery.mock(IKeyCustomerService.class);

		keyCustomerServiceMockery.checking(new Expectations() {
			{
				allowing(keyCustomerService).callStoredProcedure(with(any(String.class)));
			}
		});
		goodTradeMappingManager.setKeyCustomerService(keyCustomerService);
		User user = new User();
		Department depart = new Department();
		depart.setId("92974");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("1");
		user.setEmpCode(e);
		
		UserContext.setCurrentUser(user);

	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 * @throws Exception
	 * 
	 */
	@Override
	protected void tearDown() throws Exception {
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月15日 void
	 */
	public void testSearchGoodTradeMappingByCondition() {
		GoodTradeMappingSearchCondition condition = new GoodTradeMappingSearchCondition();
		condition.setAssociatedStatus(Constant.ALL_STATUS);
		goodTradeMappingManager.searchGoodTradeMappingByCondition(condition);
		condition.setAssociatedStatus("1");
		goodTradeMappingManager.searchGoodTradeMappingByCondition(condition);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 * @param goodTradeMapping
	 *            void
	 */
	@Test
	public void testUpdateGoodTradeMapping() {
		try {

			goodTradeMappingManager.updateGoodTradeMapping(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		GoodTradeMapping g = new GoodTradeMapping();
		try {

			goodTradeMappingManager.updateGoodTradeMapping(g);
		} catch (Exception e) {
			// TODO: handle exception
		}
		g.setFirstTradeCode("1");
		try {

			goodTradeMappingManager.updateGoodTradeMapping(g);
		} catch (Exception e) {
			// TODO: handle exception
		}
		g.setSecondTradeCode("1");
		try {

			goodTradeMappingManager.updateGoodTradeMapping(g);
		} catch (Exception e) {
			// TODO: handle exception
		}
		goodTradeMappingManager.updateGoodTradeMapping(g);

		goodTradeMappingManager.getGoodTradeMappingService();
		goodTradeMappingManager.getKeyCustomerService();

	}

	public void testupdateCustomerTradeMonth() {
		goodTradeMappingManager.updateCustomerTradeMonth();

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月15日 void
	 */
	public void testupdateCustomerTradeWeek() {
		goodTradeMappingManager.updateCustomerTradeWeek();
	}

}
