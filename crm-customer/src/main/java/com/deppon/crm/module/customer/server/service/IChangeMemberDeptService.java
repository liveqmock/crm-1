package com.deppon.crm.module.customer.server.service;

import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;

public interface IChangeMemberDeptService {

	/**
	 * <p>
	 * Description:保存会员变跟的实体信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param changeMemberDept
	 * void
	 */
	void saveChangeMemberDept(ChangeMemberDept changeMemberDept);

	/**
	 * <p>
	 * Description:通过工作流号得到会员变跟实体信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param workFLowId
	 * @return
	 * ChangeMemberDept
	 */
	ChangeMemberDept getChangeMemberDeptByWorkFlowId(String workFLowId);

}
