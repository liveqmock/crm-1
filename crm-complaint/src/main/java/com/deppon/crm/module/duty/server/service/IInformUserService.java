package com.deppon.crm.module.duty.server.service;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.duty.shared.domain.InformUser;
 
/**
 * 
 * <p>
 * Description:通知对象 Dao<br />
 * </p>
 * @title IInformUserDao.java
 * @package com.deppon.crm.module.duty.server.dao 
 * @author zhangbin
 * @version 0.1 2013-3-6
 */
public interface IInformUserService {
	/**
	 * <p>
	 * Description:根据条件查询通知对象<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return List<InformUser>
	 */
	public List<InformUser> searchInformUser(InformUser informUser);
	
	
	/**
	 * <p>
	 * Description:根据ID查询通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fid
	 * @return List
	 */
	public InformUser selectInformUserByPrimaryKey(BigDecimal fid);
	
	/**
	 * <p>
	 * Description:修改通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return void
	 */
	public void updateInformUser(InformUser informUser);
	
	/**
	 * <p>
	 * Description:批量删除通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param fids
	 * @return void
	 */
	public void deleteInformUsers(List<String> fids);
	
	/**
	 * <p>
	 * Description:根据责任ID删除通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param dutyId
	 * @return void
	 */
	public void deleteInformUsersByDutyId(String dutyId);
	
	/**
	 * <p>
	 * Description:插入通知对象<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-6
	 * @param informUser
	 * @return void
	 */
	public void insertInformUser(InformUser informUser);
	
	
}
