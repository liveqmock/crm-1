package com.deppon.crm.module.sysmail.server.service;

import java.util.List;

import com.deppon.crm.module.sysmail.shared.domain.MailAccount;

/**
 * 
 * <p>
 * 邮件发送相关<br />
 * </p>
 * @title ISysMailSendService.java
 * @package com.deppon.crm.module.sysmail.server.service 
 * @author suyujun
 * @version 0.1 2013-9-25
 */
public interface ISysMailSendService {

	/**
	 * 
	 * <p>
	 * 根据分组编码查询账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-25
	 * @param groupCode
	 * @return
	 * List<MailAccount>
	 */
	List<MailAccount> queryMailAccountByGroupCode(String groupCode);

	/**
	 * 
	 * <p>
	 * 保留发送记录<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-25
	 * @param toSend
	 * void
	 */
	void saveSendLog(List<String> toSend);

}
