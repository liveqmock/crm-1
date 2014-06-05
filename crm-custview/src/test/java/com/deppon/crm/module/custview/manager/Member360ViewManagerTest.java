package com.deppon.crm.module.custview.manager;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.custview.server.manager.IMember360ViewManager;
import com.deppon.crm.module.custview.shared.domain.SearchFastCondition;
import com.deppon.crm.module.custview.shared.domain.TwelveMonth;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.foss.framework.server.context.AppContext;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * @description 客户360视图manager测试用例
 * @author 安小虎
 * @version 0.1 2012-4-25
 * @date 2012-4-25
 */

public class Member360ViewManagerTest {
	private static ClassPathXmlApplicationContext factory;
	private static IMember360ViewManager member360ViewManager;

	private String custId = "95080";
	private String custNo = "20110927-08009301";
	private Date startDate = new Date(2012-1900, 6, 1);
	private Date endDate = new Date(2012-1900, 9, 1);

	static {
		AppContext.initAppContext("application", "","");
		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
		factory = new ClassPathXmlApplicationContext(resource);
		member360ViewManager = (IMember360ViewManager) factory
				.getBean("member360ViewManager");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMemberIntegratedInfo() {
		// 调用外部接口中需要用到——客户模块方法
		User user = new User();
		user.setId("1");
		Set<String> deptids = new HashSet<String>();
		deptids.add("1");
		user.setDeptids(deptids);
		UserContext.setCurrentUser(user);
		member360ViewManager.getMemberIntegratedInfo(custNo,
				"customerCode");

		member360ViewManager.getMemberIntegratedInfo(custNo,
				"contactCoding");
		
		member360ViewManager.getMemberIntegratedInfo(custNo,
				"mobileNumber");
		
		member360ViewManager.getMemberIntegratedInfo(null,
				"mobileNumber");
		
		member360ViewManager.getMemberIntegratedInfo(custNo,
				System.currentTimeMillis()+"");
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getMemberBasicInfoByCustId(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetMemberBasicInfoByCustId() {
		member360ViewManager.getMemberBasicInfoByCustId("");
		member360ViewManager.getMemberBasicInfoByCustId(custId);
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getAllContactInfoByCustId(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetAllContactInfoByCustId() {
		this.member360ViewManager.getAllContactInfoByCustId(custId);
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getContactInfoById(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetContactInfoByID() {
		List<Contact> list = member360ViewManager
				.getAllContactInfoByCustId(custId);
		if (list != null && list.size() > 0){
			member360ViewManager.getContactInfoById(list.get(0).getId());
		}
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getAccountListByCustId(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetAccountListByCustId() {
		this.member360ViewManager.getAccountListByCustId(custId);
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getAccountInfoById(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetAccountInfoByID() {
		member360ViewManager.getAccountInfoById("");
		
		List<Account> list = this.member360ViewManager
				.getAccountListByCustId(custId);
		if (list != null && list.size() > 0)
			this.member360ViewManager.getAccountInfoById(list.get(0).getId());
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getContractListByCustId(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetContractListByCustId() {
		member360ViewManager.getContractListByCustId(custId);
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getContractInfoById(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetContractInfoByID() {
		member360ViewManager.getContractInfoById("");
		List<ContractDetailView> list = this.member360ViewManager
				.getContractListByCustId(custId);
		if (list != null && list.size() > 0) {
			this.member360ViewManager.getContractInfoById(list.get(0).getId());
		}
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getIntegratedCustDevByCustId(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetIntegratedCustDevByCustId() {
		this.member360ViewManager.getIntegratedCustDevByCustId(custId);
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getOrderListByCustId(java.lang.String, java.util.Date, java.util.Date, int, int)}
	 * 的测试方法。
	 */
	@Test
	public void testGetOrderListByCustId() {
		List<Order> list = this.member360ViewManager.getOrderListByCustId(
				custId, null, new Date(), 0, 10);
		if (list != null && list.size() > 0){
			System.out.println(list.size());
		}
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getOrderCountByCustId(java.lang.String, java.util.Date, java.util.Date)}
	 * 的测试方法。
	 */
	@Test
	public void testGetOrderCountByCustId() {
		this.member360ViewManager.getOrderCountByCustId(custId, startDate,endDate);
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getMemberPointByCustId(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetMemberPointByCustId() {
		this.member360ViewManager.getMemberPointByCustId(custId);
	}

	/**
	 * {@link com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager#getComplaintRecompense(java.lang.String, java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetComplaintRecompense() {
		member360ViewManager.getComplaintRecompense(custId, custNo);
	}

	@Test
	public void testGetOperationAnalysisListByCustId() {
		List<TwelveMonth> tml = member360ViewManager
				.getOperationAnalysisListByCustId(null,custId, startDate, endDate);
		for (TwelveMonth tm : tml) {
			System.out.println(tm.getMonth1() + "," + tm.getMonth2() + ","
					+ tm.getMonth3() + "," + tm.getMonth4() + ","
					+ tm.getMonth5() + "," + tm.getMonth6() + ","
					+ tm.getMonth7() + "," + tm.getMonth8() + ","
					+ tm.getMonth9() + "," + tm.getMonth10() + ","
					+ tm.getMonth10() + "," + tm.getMonth12());
		}
	}

	@Test
	public void testGetSummaryPlusListByCustId() {
		Date endDate = new Date();
		Date startDate = new Date(endDate.getYear(), endDate.getMonth() - 6,
				endDate.getDay());
		System.out.println(startDate);

		List<TwelveMonth> tml = member360ViewManager
				.getSummaryPlusListByCustId(null,null);
		for (TwelveMonth tm : tml) {
			System.out.println(tm.getMonth1() + "," + tm.getMonth2() + ","
					+ tm.getMonth3() + "," + tm.getMonth4() + ","
					+ tm.getMonth5() + "," + tm.getMonth6() + ","
					+ tm.getMonth7() + "," + tm.getMonth8() + ","
					+ tm.getMonth9() + "," + tm.getMonth10() + ","
					+ tm.getMonth10() + "," + tm.getMonth12());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:testSearchMenberInfoView<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-21 void
	 */
	@Test
	public void testSearchMenberInfoView() {
		SearchFastCondition searchFastCondition = new SearchFastCondition();
		searchFastCondition.setCustNumber("");
		searchFastCondition.setIdCard("");
		searchFastCondition.setLinkManNumber("");
		searchFastCondition.setMobile("");
		searchFastCondition.setTaxregNumber("");
		
		member360ViewManager.searchMemberInfoFast(searchFastCondition);
		
		searchFastCondition.setCustNumber("111");
		member360ViewManager.searchMemberInfoFast(searchFastCondition);
		searchFastCondition.setCustNumber(null);
		
		searchFastCondition.setLinkManNumber("1111111");
		member360ViewManager.searchMemberInfoFast(searchFastCondition);
		searchFastCondition.setLinkManNumber(null);
		
		searchFastCondition.setMobile("11111111");
		member360ViewManager.searchMemberInfoFast(searchFastCondition);
		searchFastCondition.setMobile(null);
		
		searchFastCondition.setTaxregNumber("11111111111");
		member360ViewManager.searchMemberInfoFast(searchFastCondition);
		searchFastCondition.setTaxregNumber(null);
		
		searchFastCondition.setIdCard("111111111");
		member360ViewManager.searchMemberInfoFast(searchFastCondition);
		searchFastCondition.setIdCard(null);
		
		member360ViewManager.searchMemberInfoFast(searchFastCondition);

	}
	/**
	 * 
	 * <p>
	 * Description:testGetComplaintStatisticsByCustId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-21 void
	 */
	@Test
	public void testGetComplaintStatisticsByCustId() {
		member360ViewManager.getComplaintStatisticsByCustId("1111111");
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试： 統計明細<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-1-23
	 * void
	 */
	@Test
	public void testGetOperationAnalysisDetailListByCustId(){
		member360ViewManager.getOperationAnalysisDetailListByCustId(null,null,custId,startDate,endDate);
	}
}
