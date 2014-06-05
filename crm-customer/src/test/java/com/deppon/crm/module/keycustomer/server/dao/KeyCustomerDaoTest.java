package com.deppon.crm.module.keycustomer.server.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.keycustomer.server.dao.impl.KeyCustomerDao;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerVO;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:大客户管理DAO测试类<br />
 * </p>
 * 
 * @title KeyCustomerDaoTest.java
 * @package com.deppon.crm.module.keycustomer.server.dao
 * @author 106138
 * @version 0.1 2014-3-5
 */
public class KeyCustomerDaoTest extends TestCase {
	// 大客户管理dao
	private static KeyCustomerDao keyCustomerDao;
	private static KeyCustomerSearchCondition condition;
	private static String custId;
	private static KeyCustomerVO keyCustomerVO;
	private static String[] sqls;
	public void setUp() {
		// 通过测试类加载
		condition = new KeyCustomerSearchCondition();
		condition.setLimit(20);
		condition.setStart(1);
		condition.setBelongDept("49311");
		keyCustomerDao = (KeyCustomerDao) SpringTestHelper.getBean("keyCustomerDao");
		custId = "700310";
		keyCustomerVO = new KeyCustomerVO();
		keyCustomerVO.setCustId(custId);
		keyCustomerVO.setCreateUser("1");
		keyCustomerVO.setCreateDate(new Date());
		int index=2;
		sqls = new String[index];
		sqls[--index] = "delete T_CUST_KEYCUSTOMERWORKFLOWINFO k where k.FCUSTID="+custId;
		sqls[--index] = "delete T_CUST_KEYCUSTOMER k where k.FCUSTID="+custId;
	}
	@Override
	public void tearDown() throws Exception {
		SpringTestHelper.executeBatch(sqls);
	}

	/**
	 * 
	 * <p>
	 * Description:测试查询大客户列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-5 void
	 */
	@Test
	public void testSearchKeyCustomerList() {
		keyCustomerDao.searchKeyCustomerList(condition);

	}

	/**
	 * 
	 * <p>
	 * Description:测试统计大客户列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-5 void
	 */
	@Test
	public void testCountKeyCustomerList() {
		keyCustomerDao.countKeyCustomerList(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询大客户的工作流历史<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return List<KeyCustomerWorkflowInfo>
	 */
	@Test
	public void testsearchWorkflowList() {
		keyCustomerDao.searchWorkflowList(condition);
	}

	public void testCountWorkflowList() {
		keyCustomerDao.countWorkflowList(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:测试查询大客户列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-5 void
	 */
	@Test
	public void testFindKeyCustomerListByCustId() {
		keyCustomerDao.findKeyCustomerListByCustId(custId);
	}

	/**
	 * 
	 * <p>
	 * Description:测试查询大客户列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-5 void
	 */
	@Test
	public void testCountKeyCustomerListByCustId() {
		keyCustomerDao.countWorkflowListByCustId(custId);
	}

	@Test
	public void testFindWorkflowListByCustId() {
		keyCustomerDao.findWorkflowListByCustId(custId, 0, 20);
		keyCustomerDao.findWorkflowListByCustId(custId, 0, 0);
	}

	/**
	 * 
	 * <p>
	 * Description:保存工作流审批信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-20 void
	 */
	@Test
	public void testsaveWorkflowInfo() {
		keyCustomerDao.saveWorkflowInfo(null);
		KeyCustomerWorkflowInfo info = new KeyCustomerWorkflowInfo();
		info.setId(null);
		info.setCustId(custId);
		keyCustomerDao.saveWorkflowInfo(info);
		info.setId("111");
		keyCustomerDao.saveWorkflowInfo(info);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询大客户列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-20 void
	 */
	@Test
	public void testUpdateKeyCustomerWorkflowInfo() {
		KeyCustomerWorkflowInfo info = new KeyCustomerWorkflowInfo();
		info.setBusino("1");
		info.setModifyDate(new Date());
		info.setModifyUser("86301");
		info.setStatus("1");
		keyCustomerDao.updateKeyCustomerWorkflowInfo(info);
		keyCustomerDao.updateKeyCustomerWorkflowInfo(null);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-20 void
	 */
	@Test
	public void testUpdateKeyListStatus() {
		keyCustomerDao.updateKeyListStatus(custId, "1");
		keyCustomerDao.updateKeyListStatus(null, "1");

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-20 void
	 */
	@Test
	public void testFindWorkflowInfoByBusino() {
		keyCustomerDao.findWorkflowInfoByBusino("1");

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-20 void
	 */
	@Test
	public void testSaveKeyCustomer() {
		keyCustomerDao.saveKeyCustomer(keyCustomerVO);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-20 void
	 */
	@Test
	public void testUpdateKeyCustomer() {
		keyCustomerDao.updateKeyCustomer(keyCustomerVO);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-20 void
	 */
	@Test
	public void testSelectKeyCustomer() {
		keyCustomerDao.selectKeyCustomer(custId);
	}
	@Test
	public void testcallStoredProcedure(){
		try {
			
			keyCustomerDao.callStoredProcedure("tt11111");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
