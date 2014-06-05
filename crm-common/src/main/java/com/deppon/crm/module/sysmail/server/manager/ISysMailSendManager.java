package com.deppon.crm.module.sysmail.server.manager;

import com.deppon.foss.framework.server.adapter.mail.MailException;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;

/**
 * 
 * <p>
 * 邮件发送处理接口<br />
 * </p>
 * @title ISysMailSendManager.java
 * @package com.deppon.crm.module.sysmail.server.manager 
 * @author suyujun
 * @version 0.1 2013-9-23
 */
public interface ISysMailSendManager {
	/**
	 * 
	 * <p>
	 * 邮件发送接口<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-25
	 * @param info
	 * @param groupCode
	 * void
	 */
	void sendEmail(MailInfo info,String groupCode) throws MailException;
}
