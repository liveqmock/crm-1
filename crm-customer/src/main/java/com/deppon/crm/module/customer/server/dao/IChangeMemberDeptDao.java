package com.deppon.crm.module.customer.server.dao;

import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;

/**
 * 
 * <p>
 * 会员归属部门变更持久层<br />
 * </p>
 * @title IChangeMemberDeptDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author bxj
 * @version 0.2 2012-6-6
 */
public interface IChangeMemberDeptDao {
	
	/**
	 * 
	 * <p>
	 * 根据workFLowId查询会员归属部门变更<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-6
	 * @param workFLowId
	 * @return
	 * ChangeMemberDept
	 */
	public ChangeMemberDept getChangeMemberDeptByWorkFlowId(String workFLowId);
	
	/**
	 * 
	 * <p>
	 * 保存会员归属部门变更<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-6
	 * @param changeMemberDept
	 * void
	 */
	public void insertChangeMemberDept(ChangeMemberDept changeMemberDept);
	
}
