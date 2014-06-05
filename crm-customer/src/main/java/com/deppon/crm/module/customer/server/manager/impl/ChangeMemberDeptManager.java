package com.deppon.crm.module.customer.server.manager.impl;

import com.deppon.crm.module.customer.server.manager.IChangeMemberDeptManager;
import com.deppon.crm.module.customer.server.service.IChangeMemberDeptService;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;

public class ChangeMemberDeptManager implements IChangeMemberDeptManager{
	
	private IChangeMemberDeptService changeMemberDeptService;
	
	public void setChangeMemberDeptService(
			IChangeMemberDeptService changeMemberDeptService) {
		this.changeMemberDeptService = changeMemberDeptService;
	}
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
	@Override
	public ChangeMemberDept getChangeMemberDeptByWorkFlowId(String workFLowId) {
		return changeMemberDeptService.getChangeMemberDeptByWorkFlowId(workFLowId);
	}
	/**
	 * 
	 * <p>
	 * 保存会员归属部门变更<br />
	 * </p>
	 * @author 王明明
	 * @version 0.2 2012-6-6
	 * @param changeMemberDept
	 * void
	 */
	@Override
	public void saveChangeMemberDept(ChangeMemberDept changeMemberDept) {
		changeMemberDeptService.saveChangeMemberDept(changeMemberDept);
	}

}
