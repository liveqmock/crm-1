package com.deppon.crm.module.sysmail.server.dao;

import java.util.List;

import com.deppon.crm.module.sysmail.shared.domain.MailAccount;

/**
 * 
 * <p>
 * 邮件发到Dao接口<br />
 * </p>
 * @title ISysMailSendDao.java
 * @package com.deppon.crm.module.sysmail.server.dao 
 * @author suyujun
 * @version 0.1 2013-9-25
 */
public interface ISysMailSendDao {
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
