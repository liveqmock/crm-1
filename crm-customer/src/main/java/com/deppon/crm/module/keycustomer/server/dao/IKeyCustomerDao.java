package com.deppon.crm.module.keycustomer.server.dao;

import java.util.List;

import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerVO;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;

/**
 * 
 * <p>
 * Description:大客户资格管理Dao<br />
 * </p>
 * @title IKeyCustomerDao.java
 * @package com.deppon.crm.module.keycustomer.server.dao 
 * @author 106138
 * @version 0.1 2014-3-4
 */
 
public interface IKeyCustomerDao {
	/**
	 * 
	 * <p>
	 * Description:根据查询条件查询对应的大客户准入或退出列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condition
	 * @return
	 * List<KeyCustomerList>
	 */
	List<KeyCustomer> searchKeyCustomerList(KeyCustomerSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:根据查询条件統計 对应的大客户准入或退出列表条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condition
	 * @return List<KeyCustomerList>
	 */
	public int countKeyCustomerList(
			KeyCustomerSearchCondition condition) ;
	
	/**
	 * 
	 * <p>
	 * Description:根据条件查询大客户的工作流历史<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return
	 * List<KeyCustomerWorkflowInfo>
	 */
	List<KeyCustomerWorkflowInfo> searchWorkflowList(KeyCustomerSearchCondition condtion );
	/**
	 * 
	 * <p>
	 * Description::根据条件查询大客户的工作流条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return
	 * int
	 */
	int countWorkflowList(KeyCustomerSearchCondition condtion );

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户的审批历史<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return
	 * List<KeyCustomerWorkflowInfo>
	 */
	List<KeyCustomerWorkflowInfo> findWorkflowListByCustId(String custId,int start,int limit );
	/**
	 * 
	 * <p>
	 * Description:保存工作流审批信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param info
	 * void
	 */
	/**
	 * 
	 * <p>
	 * Description::根据条件查询大客户的工作流条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return
	 * int
	 */
	  int countWorkflowListByCustId(String custId );
	  /**
	   * 
	   * <p>
	   * Description:保存工作流信息<br />
	   * </p>
	   * @author 106138
	   * @version 0.1 2014年3月26日
	   * @param info
	   * @return
	   * KeyCustomerWorkflowInfo
	   */
	  KeyCustomerWorkflowInfo saveWorkflowInfo( KeyCustomerWorkflowInfo info );
	/**
	 * 
	 * <p>
	 * Description:更新大客户列表的状态<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * void
	 */
	void updateKeyListStatus(String custId,String status);
	/**
	 * 
	 * <p>
	 * Description:根据客户id查询大客户列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return
	 * KeyCustomerList
	 */
	List<KeyCustomer> findKeyCustomerListByCustId( String custId );
	/**
	 * 
	 * <p>
	 * Description:更新大客户工作流信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param info
	 * void
	 */
	void  updateKeyCustomerWorkflowInfo(KeyCustomerWorkflowInfo info);
	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询对于的大客户审批信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param busino
	 * @return
	 * KeyCustomerWorkflowInfo
	 */
	KeyCustomerWorkflowInfo findWorkflowInfoByBusino(String busino);
	/**
	 * 
	 * <p>
	 * Description:保存大客户信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param keyCustomerVO
	 * void
	 */
	void saveKeyCustomer(KeyCustomerVO keyCustomerVO);
	/**
	 * 
	 * <p>
	 * Description:更新大客户信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param keyCustomerVO
	 * void
	 */
	void updateKeyCustomer(KeyCustomerVO keyCustomerVO);
	/**
	 * 
	 * <p>
	 * Description:查询大客户信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param custId
	 * @return
	 * KeyCustomerVO
	 */
	KeyCustomerVO selectKeyCustomer(String custId);
	/**
	 * 
	 * <p>
	 * Description:调用存储过程<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-11
	 * @param storedName
	 * void
	 */
	 void callStoredProcedure(String storedName);

}
