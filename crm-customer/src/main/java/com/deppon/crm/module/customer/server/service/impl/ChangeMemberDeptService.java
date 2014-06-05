package com.deppon.crm.module.customer.server.service.impl;

import com.deppon.crm.module.customer.server.dao.IChangeMemberDeptDao;
import com.deppon.crm.module.customer.server.service.IChangeMemberDeptService;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;
/**
 * 
 * <p>
 * Description:会员归属变更service<br />
 * </p>
 * @title ChangeMemberDeptService.java
 * @package com.deppon.crm.module.customer.server.service.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ChangeMemberDeptService implements IChangeMemberDeptService{
	/**
	 * changeMemberDeptDao
	 */
	private IChangeMemberDeptDao changeMemberDeptDao;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param changeMemberDeptDao
	 * void
	 */
	public void setChangeMemberDeptDao(IChangeMemberDeptDao changeMemberDeptDao) {
		this.changeMemberDeptDao = changeMemberDeptDao;
	}
	/**
	 * 
	 * <p>
	 * Description:保存会员归属部门变更信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param changeMemberDept
	 *
	 */
	@Override
	public void saveChangeMemberDept(ChangeMemberDept changeMemberDept) {
		//保存会员归属部门变更信息
		changeMemberDeptDao.insertChangeMemberDept(changeMemberDept);
	}
	/**
	 * 
	 * <p>
	 * Description:获得会员归属部门变更信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param workFLowId
	 * @return
	 *
	 */
	@Override
	public ChangeMemberDept getChangeMemberDeptByWorkFlowId(String workFLowId) {
		//获得会员归属部门变更信息
		return changeMemberDeptDao.getChangeMemberDeptByWorkFlowId(workFLowId);
	}

}
