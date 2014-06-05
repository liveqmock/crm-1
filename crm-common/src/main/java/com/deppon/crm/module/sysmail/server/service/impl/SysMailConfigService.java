package com.deppon.crm.module.sysmail.server.service.impl;

import java.util.List;

import com.deppon.crm.module.sysmail.server.dao.ISysMailConfigDao;
import com.deppon.crm.module.sysmail.server.service.ISysMailConfigService;
import com.deppon.crm.module.sysmail.shared.domain.AccGroResultVO;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;

/**
 * 
 * <p>
 * service impl<br />
 * </p>
 * @title SysMailConfigService.java
 * @package com.deppon.crm.module.sysmail.server.service.impl 
 * @author suyujun
 * @version 0.1 2013-9-22
 */
public class SysMailConfigService implements ISysMailConfigService {
	private ISysMailConfigDao sysMailConfigDao;
	
	/**
	 * @return sysMailConfigDao : return the property sysMailConfigDao.
	 */
	public ISysMailConfigDao getSysMailConfigDao() {
		return sysMailConfigDao;
	}


	/**
	 * @param sysMailConfigDao : set the property sysMailConfigDao.
	 */
	public void setSysMailConfigDao(ISysMailConfigDao sysMailConfigDao) {
		this.sysMailConfigDao = sysMailConfigDao;
	}


	/**
	 * 邮件账号新增
	 */
	@Override
	public void addMailAccount(MailAccount ma) {
		sysMailConfigDao.addMailAccount(ma);
	}


	/**
	 * 邮件账号删除
	 */
	@Override
	public void deleteMailAccountById(String mailAccountId) {
		sysMailConfigDao.deleteMailAccountById(mailAccountId);
	}


	/**
	 * 修改邮件账号
	 */
	@Override
	public void modifyMailAccount(MailAccount ma) {
		sysMailConfigDao.modifyMailAccount(ma);
	}


	/**
	 * 新增邮件组
	 */
	@Override
	public void addMailGroup(MailGroup mg) {
		sysMailConfigDao.addMailGroup(mg);
	}

	/**
	 * 修改邮件组
	 */
	@Override
	public void modifyMailGroup(MailGroup mg) {
		sysMailConfigDao.modifyMailGroup(mg);
	}


	@Override
	public List<MailGroup> queryAllMailGroup() {
		return sysMailConfigDao.queryAllMailGroup();
	}


	/**
	 * 查询分组下的账号信息
	 */
	@Override
	public List<AccGroResultVO> queryMailAccountByGroupId(String groupId,int start,int limit) {
		return sysMailConfigDao.queryMailAccountByGroupId(groupId,start,limit);
	}


	@Override
	public void addAccGroRelation(AccountGroup ag) {
		sysMailConfigDao.addAccGroRelation(ag);
	}


	@Override
	public void deleteAccGroRelation(AccountGroup ag) {
		sysMailConfigDao.deleteAccGroRelation(ag);
	}

	/**
	 * 
	 * <p>
	 * 查询符合条件的账号，，whatString 代表 工号 或者 姓名<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-23
	 * @param whatString
	 * @return
	 * List<MailAccount>
	 */
	@Override
	public List<MailAccount> queryMailAccountByCondition(String whatString,int start,int limit) {
		return sysMailConfigDao.queryMailAccountByCondition(whatString,start,limit);
	}


	/**
	 * 分页计数
	 */
	@Override
	public long countMailAccountByGroupId(String groupId) {
		return sysMailConfigDao.countMailAccountByGroupId(groupId);
	}


	@Override
	public long countMailAccountByCondition(String whatString) {
		return sysMailConfigDao.countMailAccountByCondition(whatString);
	}


	@Override
	public List<MailAccount> queryAllMailAccount() {
		return sysMailConfigDao.queryAllMailAccount();
	}


	@Override
	public void removeRelationBatch(List<String> relationIds) {
		sysMailConfigDao.removeRelationBatch(relationIds);
	}


	@Override
	public void deleteMailGroupById(String mailGroupId) {
		sysMailConfigDao.deleteMailGroupById(mailGroupId);
	}

}
