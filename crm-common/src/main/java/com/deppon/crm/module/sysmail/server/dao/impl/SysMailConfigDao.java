package com.deppon.crm.module.sysmail.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.sysmail.server.dao.ISysMailConfigDao;
import com.deppon.crm.module.sysmail.shared.domain.AccGroResultVO;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * 邮件配置操作dao实现<br />
 * </p>
 * @title SysMailConfigDao.java
 * @package com.deppon.crm.module.sysmail.server.dao.impl 
 * @author suyujun
 * @version 0.1 2013-9-22
 */
public class SysMailConfigDao extends iBatis3DaoImpl implements ISysMailConfigDao {
	private String namespace  ="com.deppon.crm.module.sysmail.shared.domain.MailAccount.";
	@Override
	public void addMailAccount(MailAccount ma) {
		this.getSqlSession().insert(namespace + "insertMailAccount" , ma);
	}
	
	@Override
	public void deleteMailAccountById(String id) {
		this.getSqlSession().delete(namespace + "deleteMailAccountById", id);
	}

	@Override
	public void modifyMailAccount(MailAccount ma) {
		this.getSqlSession().update(namespace + "updateMailAccountById", ma);
	}

	@Override
	public MailAccount getMailAccountById(String id) {
		return (MailAccount) this.getSqlSession().selectOne(namespace + "getMailAccountById", id);
	}

	@Override
	public void addMailGroup(MailGroup mg) {
		this.getSqlSession().insert(namespace + "insertMailGroup", mg);
	}

	@Override
	public void deleteMailGroupById(String id) {
		this.getSqlSession().delete(namespace + "deleteMailGroupById", id);
	}

	@Override
	public void modifyMailGroup(MailGroup mg) {
		this.getSqlSession().update(namespace + "updateMailGroupById", mg);
	}

	@Override
	public MailGroup getMailGroupById(String id) {
		return (MailGroup) this.getSqlSession().selectOne(namespace + "getMailGroupById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MailGroup> queryAllMailGroup() {
		return this.getSqlSession().selectList(namespace + "queryAllMailGroup");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccGroResultVO> queryMailAccountByGroupId(String groupId,int start,int limit) {
		RowBounds rb = new RowBounds(start, limit);
		return this.getSqlSession().selectList(namespace + "queryMailAccountByGroupId",groupId,rb);
	}

	@Override
	public void addAccGroRelation(AccountGroup ag) {
		this.getSqlSession().insert(namespace + "addAccGroRelation", ag);
	}

	@Override
	public void deleteAccGroRelation(AccountGroup ag) {
		this.getSqlSession().delete(namespace + "deleteAccGroRelation", ag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MailAccount> queryMailAccountByCondition(String str,int start,int limit) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("str", str);
		RowBounds rb = new RowBounds(start, limit);
		return this.getSqlSession().selectList(namespace + "queryMailAccountByCondition", map,rb);
	}

	@Override
	public long countMailAccountByGroupId(String groupId) {
		return  (Long) this.getSqlSession().selectOne(namespace + "countMailAccountByGroupId", groupId);
	}

	@Override
	public long countMailAccountByCondition(String str) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("str", str);
		return (Long) this.getSqlSession().selectOne(namespace + "countMailAccountByCondition",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MailAccount> queryAllMailAccount() {
		return this.getSqlSession().selectList(namespace + "queryAllMailAccount");
	}

	@Override
	public void removeRelationBatch(List<String> relationIds) {
		this.getSqlSession().delete(namespace + "removeRelationBatch",relationIds);
	}

}
