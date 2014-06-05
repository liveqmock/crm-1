package com.deppon.crm.module.sysmail.server.dao;

import java.util.List;

import com.deppon.crm.module.sysmail.shared.domain.AccGroResultVO;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;

/**
 * 
 * <p>
 * 系统邮件DAO<br />
 * </p>
 * @title ISysMailConfigDao.java
 * @package com.deppon.crm.module.sysmail.server.dao 
 * @author suyujun
 * @version 0.1 2013-9-22
 */
public interface ISysMailConfigDao {
	/**
	 * 
	 * <p>
	 * 添加邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param ma
	 * void
	 */
	void addMailAccount(MailAccount ma);
	
	/**
	 * 
	 * <p>
	 * 根据Id删除邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param id
	 * void
	 */
	void deleteMailAccountById(String id);
	
	/**
	 * 
	 * <p>
	 * 更新邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param ma
	 * void
	 */
	void modifyMailAccount(MailAccount ma);

	/**
	 * 
	 * <p>
	 * 查询账号信息<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param id
	 * @return
	 * MailAccount
	 */
	MailAccount getMailAccountById(String id);
	
	/**
	 * 
	 * <p>
	 * 新增邮件组<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mg
	 * void
	 */
	void addMailGroup(MailGroup mg);
	
	/**
	 * 
	 * <p>
	 * 删除邮件组<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mg
	 * void
	 */
	void deleteMailGroupById(String id);

	/**
	 * 
	 * <p>
	 * 修改邮件组<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mg
	 * void
	 */
	void modifyMailGroup(MailGroup mg);

	/**
	 * 
	 * <p>
	 * 根据Id查询分组<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param id
	 * @return
	 * MailGroup
	 */
	MailGroup getMailGroupById(String id);

	/**
	 * 
	 * <p>
	 * 查询所有分组<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-23
	 * @return
	 * List<MailGroup>
	 */
	List<MailGroup> queryAllMailGroup();

	/**
	 * 
	 * <p>
	 * 查询分组下的账号信息<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-23
	 * @param groupId
	 * @return
	 * List<MailAccount>
	 */
	List<AccGroResultVO> queryMailAccountByGroupId(String groupId,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * 新增配置关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-23
	 * @param ag
	 * void
	 */
	void addAccGroRelation(AccountGroup ag);
	
	/**
	 * 
	 * <p>
	 * 删除配置关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-23
	 * @param ag
	 * void
	 */
	void deleteAccGroRelation(AccountGroup ag);

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
	List<MailAccount> queryMailAccountByCondition(String whatString,int start,int limit);

	/**
	 * 
	 * <p>
	 * 分页计数<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @param groupId
	 * @return
	 * int
	 */
	long countMailAccountByGroupId(String groupId);

	/**
	 * 
	 * <p>
	 * 分页计数<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @param whatString
	 * @return
	 * int
	 */
	long countMailAccountByCondition(String whatString);

	/**
	 * 
	 * <p>
	 * 查询所有的账号信息<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @return
	 * List<MailAccount>
	 */
	List<MailAccount> queryAllMailAccount();

	/**
	 * 
	 * <p>
	 * 批量移除关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @param relationIds
	 * void
	 */
	void removeRelationBatch(List<String> relationIds);
}
