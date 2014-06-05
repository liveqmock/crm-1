package com.deppon.crm.module.common.server.service;

import com.deppon.crm.module.common.shared.domain.HardWareInfo;

public interface ICheckHardWareService {

	/**
	 * <p>
	 * Description:根据客户端提交信息查询硬件验证信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-7-8
	 * @param info
	 * @return
	 * HardWareInfo
	 */
	public HardWareInfo getHardWareInfoByClient(HardWareInfo info);
}
