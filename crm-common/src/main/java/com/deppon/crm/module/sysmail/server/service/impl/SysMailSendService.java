package com.deppon.crm.module.sysmail.server.service.impl;

import java.util.List;

import com.deppon.crm.module.sysmail.server.dao.ISysMailSendDao;
import com.deppon.crm.module.sysmail.server.service.ISysMailSendService;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;

public class SysMailSendService implements ISysMailSendService {
	private ISysMailSendDao sysMailSendDao;
	
	/**
	 * @return sysMailSendDao : return the property sysMailSendDao.
	 */
	public ISysMailSendDao getSysMailSendDao() {
		return sysMailSendDao;
	}

	/**
	 * @param sysMailSendDao : set the property sysMailSendDao.
	 */
	public void setSysMailSendDao(ISysMailSendDao sysMailSendDao) {
		this.sysMailSendDao = sysMailSendDao;
	}

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
	@Override
	public List<MailAccount> queryMailAccountByGroupCode(String groupCode) {
		return sysMailSendDao.queryMailAccountByGroupCode(groupCode);
	}
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
	@Override
	public void saveSendLog(List<String> toSend) {
		sysMailSendDao.saveSendLog(toSend);
	}
}
