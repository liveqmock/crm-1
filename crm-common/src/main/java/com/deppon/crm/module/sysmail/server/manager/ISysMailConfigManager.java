package com.deppon.crm.module.sysmail.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;

/**
 * 
 * <p>
 * c<br />
 * </p>
 * @title ISysMailConfigManager.java
 * @package com.deppon.crm.module.sysmail.server.manager 
 * @author suyujun
 * @version 0.1 2013-9-18
 */
public interface ISysMailConfigManager {
	/**
	 * 
	 * <p>
	 * 添加邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-18
	 * @param ma
	 * void
	 */
	void addMailAccount(MailAccount ma,AccountGroup ag);

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
	 * 
	 * <p>
	 * 修改邮件账号信息<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mailAccountId
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
	 * 删除邮件组<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mg
	 * void
	 */
	void deleteMailGroupBatch(String mailGroupId);
	
	/**
	 * 
	 * <p>
	 * 查询所有分组信息<br />
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
	 * 根据分组ID查询邮件账号信息<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-23
	 * @param groupId
	 * @return
	 * List<MailAccount>
	 */
	Map<String,Object> queryMailAccountByGroupId(String groupId,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * 新增映射关系<br />
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
	 * 删除映射关系<br />
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
	Map<String,Object> queryMailAccountByCondition(String whatString,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * 批量划分<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @param accoundIds
	 * @param groupId
	 * void
	 */
	void batchDivide(List<String> accoundIds,String groupId);
	
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
	 * 批量删除收件人<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-24
	 * @param accoundIds
	 * void
	 */
	void deleteAccountBatch(List<String> accoundIds);
}
