package com.deppon.crm.module.keycustomer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.keycustomer.server.dao.IKeyCustomerDao;
import com.deppon.crm.module.keycustomer.server.service.IKeyCustomerService;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerVO;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;

/**
 * 
 * <p>
 * Description:大客户资格管理service<br />
 * </p>
 * @title KeyCustomerService.java
 * @package com.deppon.crm.module.keycustomer.server.service.impl 
 * @author 106138
 * @version 0.1 2014-3-4
 */ 
public class KeyCustomerService implements IKeyCustomerService {
	private IKeyCustomerDao keyCustomerDao;
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
	@Override
	public List<KeyCustomer> listKeyCustomerList(KeyCustomerSearchCondition condition) {
		return keyCustomerDao.searchKeyCustomerList(condition);
	}
	@Override
	public long countListKeyCustomerList(KeyCustomerSearchCondition condition) {
		return keyCustomerDao.countKeyCustomerList(condition);
	}
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
	@Override
	public List<KeyCustomerWorkflowInfo> searchWorkflowList(KeyCustomerSearchCondition condtion ) {
		return keyCustomerDao.searchWorkflowList(condtion);
	}
	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param condtion
	 * @return
	 *
	 */
	@Override
	public long countSearchWorkflowList(KeyCustomerSearchCondition condtion){
		return keyCustomerDao.countWorkflowList(condtion);
	}
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
	public List<KeyCustomerWorkflowInfo> findWorkflowListByCustId(String custId ,int start,int limit ) {
		
		return keyCustomerDao.findWorkflowListByCustId(custId, start, limit);
	}
	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param custId
	 * @return
	 *
	 */
	@Override
	public long countFindWorkflowListByCustId(String custId ){
		return keyCustomerDao.countWorkflowListByCustId(custId);
	}
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
	@Override
	public void saveWorkflowInfo( KeyCustomerWorkflowInfo info ) {
		keyCustomerDao.saveWorkflowInfo(info);
	}
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
	@Override
	public void updateKeyListStatus(String custId,String status) {
		keyCustomerDao.updateKeyListStatus(custId,status);
	}
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
	@Override
	public List<KeyCustomer> findKeyCustomerListByCustId( String custId ) {
		return keyCustomerDao.findKeyCustomerListByCustId(custId);
	}
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
	public void  updateKeyCustomerWorkflowInfo(KeyCustomerWorkflowInfo info){
		keyCustomerDao.updateKeyCustomerWorkflowInfo(info);
	}
	
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
	@Override
	public KeyCustomerWorkflowInfo findWorkflowInfoByBusino(String busino) {
		return keyCustomerDao.findWorkflowInfoByBusino(busino);
	}	
	

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
	@Override
	public void saveKeyCustomer(KeyCustomerVO keyCustomerVO) {
			keyCustomerDao.	saveKeyCustomer(keyCustomerVO);	
	}
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
	 public KeyCustomerVO selectKeyCustomer(String custId){
		return keyCustomerDao.selectKeyCustomer(custId);}
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
   public 	void updateKeyCustomer(KeyCustomerVO keyCustomerVO){
	   keyCustomerDao.updateKeyCustomer(keyCustomerVO);
	   
   }
   /**
    * 
    * <p>
    * Description:这里写描述<br />
    * </p>
    * @author 106138
    * @version 0.1 2014-3-11
    * @param createkeyinlist
    *
    */
	@Override
	public void callStoredProcedure(String storeName) {
		keyCustomerDao.callStoredProcedure(storeName);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @return
	 * IKeyCustomerDao
	 */
	public IKeyCustomerDao getKeyCustomerDao() {
		return keyCustomerDao;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param keyCustomerDao
	 * void
	 */
	public void setKeyCustomerDao(IKeyCustomerDao keyCustomerDao) {
		this.keyCustomerDao = keyCustomerDao;
	}
	
}
