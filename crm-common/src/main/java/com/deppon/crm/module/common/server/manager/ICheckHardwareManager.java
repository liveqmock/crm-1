package com.deppon.crm.module.common.server.manager;

import com.deppon.crm.module.common.shared.domain.CheckResultInfo;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;


public interface ICheckHardwareManager {

	/**
	 * <p>
	 * Description:检查客户端硬件信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-7-8
	 * @param info
	 * @return
	 * String 成功则返回加密Token
	 */
	public String checkHardwareInfo(HardWareInfo info,String v,String s,String version);	

	/**
	 * <p>
	 * Description:检查客户端提交的TOKEN是否合法<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-7-8
	 * @param token
	 * @return
	 * boolean
	 */
	public CheckResultInfo checkHardWareToken(String token);
}
