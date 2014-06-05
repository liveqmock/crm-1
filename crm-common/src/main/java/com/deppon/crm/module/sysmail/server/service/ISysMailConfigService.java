package com.deppon.crm.module.sysmail.server.service;

import java.util.List;

import com.deppon.crm.module.sysmail.shared.domain.AccGroResultVO;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;

/**
 * 
 * <p>
 * 邮件账号配置<br />
 * </p>
 * @title ISysMailConfigService.java
 * @package com.deppon.crm.module.sysmail.server.service 
 * @author suyujun
 * @version 0.1 2013-9-22
 */
public interface ISysMailConfigService {
	/**
	 * 
	 * <p>
	 * 新增邮件账号<br />
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
	 * 删除邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mailAccountId
	 * void
	 */
	void deleteMailAccountById(String mailAccountId);

	/**
	 * 更新邮件账号信息
	 * <p>
	 * Description:这里写描述<br />
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
	 * Description:查询条件计数<br />
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
	 * 查询所有的邮件账号<br />
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

	/**
	 * 
	 * <p>
	 * 删除邮件组<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-25
	 * @param mailGroupId
	 * void
	 */
	void deleteMailGroupById(String mailGroupId);
}
