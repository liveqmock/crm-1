package com.deppon.crm.module.keycustomer.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.keycustomer.shared.domain.KeyStatusVO;

/**
 * 
 * <p>
 * Description:大客户资格管理<br />
 * </p>
 * @title IKeyCustomerManager.java
 * @package com.deppon.crm.module.keycustomer.server.manager 
 * @author 106138
 * @version 0.1 2014-3-4
 */
  
public interface IKeyCustomerManager {
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
	List<KeyCustomer> listKeyCustomerList(KeyCustomerSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户近三个月发货金额<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return
	 * String
	 */
	String findAmountOfSignMent(String custId);
	
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
	 * Description:根据客户id查询客户的审批历史<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return
	 * List<KeyCustomerWorkflowInfo>
	 */
	List<KeyCustomerWorkflowInfo> findWorkflowListByCustId(String custId,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * Description:根据客户id对大客户进行退出处理<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * void
	 */
	void exitHadlle(String custId,String deptId);
	/**
	 * 
	 * <p>
	 * Description:大客户保留工作流申请<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param info
	 * void
	 * @return 
	 */
	String processWorkFlow(KeyCustomerWorkflowInfo info);
	/**
	 * 
	 * <p>
	 * Description:系统自动删除大客户标记<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-6
	 * void
	 */
	void autoDeleteKeyCustomerFlag();
	/**
	 * 
	 * <p>
	 * Description:系统自动生成待准入大客户列表-定时器使用<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-6
	 * void
	 */
	void createKeyCustomerInList();
	/**
	 * 
	 * <p>
	 * Description:系统自动生成待准入大客户列表---定时器使用<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-6
	 * void
	 */
	void createKeyCustomerOutList();
	/**
	 * 
	 * <p>
	 * Description:工作流审批调用方法<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-6
	 * void
	 */
	 void workflowApprove(String busino,Boolean wkStatus,String wkMan,Date approveDate,String approvalSuggestin);
	 /**
		 * 
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014-3-8
		 * @param custId
		 * void
	 * @return 
		 */
		KeyCustomerWorkflowInfo findCustomerInfo(String custId);
		/**
		 * 
		 * <p>
		 * Description:校验是否满足条件<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014-3-8
		 * @param custId
		 * @return
		 * KeyStatusVO
		 */
		KeyStatusVO checkKeyStatusVO(String custId);
		/**
		 * 
		 * <p>
		 * Description:根据工作流号查询工作流信息<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年3月26日
		 * @param bizCode
		 * @return
		 * KeyCustomerWorkflowInfo
		 */
		KeyCustomerWorkflowInfo findWorkflowInfoByBusino(String bizCode);
		/**
		 * 
		 * <p>
		 * Description:统计该客户的工作流历史记录条数<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年3月26日
		 * @param custId
		 * @return
		 * long
		 */
		long countFindWorkflowListByCustId(String custId);
		/**
		 * 
		 * <p>
		 * Description:按条件统计工作流历史记录条数<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年3月26日
		 * @param condition
		 * @return
		 * long
		 */
		long countListKeyCustomerList(KeyCustomerSearchCondition condition);
}
