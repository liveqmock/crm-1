package com.deppon.crm.module.sysmail.server.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.sysmail.server.manager.ISysMailConfigManager;
import com.deppon.crm.module.sysmail.shared.domain.AccGroResultVO;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.deppon.foss.framework.shared.util.string.StringUtil;
/**
 * <p>
 * Description:邮件配置管理action<br />
 * </p>
 * @title SysmailAction.java
 * @package com.deppon.crm.module.sysmail.server.action 
 * @author 石应华
 * @version 0.1 2013-9-23
 */
public class SysmailAction extends AbstractAction {

    /** 
     * @fields serialVersionUID 
     */ 
    private static final long serialVersionUID = -5629277408245815802L;
    
    //需要使用的manager
    private ISysMailConfigManager sysMailConfigManager;
    //邮件组集合
    private List<MailGroup> mailGroupList;
    //收件人集合
    private List<AccGroResultVO> accGroResultList;
    //邮件组id
    private String mailGroupId;
    //收件人ids
    private String[] mailAccountIds;
    //邮件组_收件人关系ids
    private String[] relationIds;
    //查询条件
    private String searchString;
    //邮件组
    private MailGroup mailGroup;
    //收件人信息
    private MailAccount mailAccount;
    //邮件组关系
    private AccountGroup accountGroup;
    /**
     * <p>
     * Description:划分收件人到邮件组<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-24
     * @return
     * String
     */
    @JSON
    public String dividePeopleTo(){
        sysMailConfigManager.batchDivide(Arrays.asList(mailAccountIds), mailGroupId);
        return SUCCESS;
    }
    /**
     * <p>
     * Description:删除收件人<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-24
     * @return
     * String
     */
    @JSON
    public String deleteMailAccounts(){
        sysMailConfigManager.deleteAccountBatch(Arrays.asList(mailAccountIds));
        return SUCCESS;
    }
    /**
     * <p>
     * Description:移出收件人<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @return
     * String
     */
    @JSON
    public String removeMailAccounts(){
        sysMailConfigManager.removeRelationBatch(Arrays.asList(relationIds));
        return SUCCESS;
    }
    /**
     * <p>
     * Description:根据邮件组或条件查询收件人信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @return
     * String
     */
    @JSON
    @SuppressWarnings("unchecked")
    public String searchMailAccount(){
        Map<String,Object> map = null;
        //如果邮件组id为空则为根据条件查询收件人
        if(StringUtil.isEmpty(mailGroupId)){
            map = sysMailConfigManager.queryMailAccountByCondition(searchString, start, limit);
        }else{//如果邮件组id不为空则根据邮件组id查询收件人
            map = sysMailConfigManager.queryMailAccountByGroupId(mailGroupId, start, limit);
        }
        accGroResultList = (List<AccGroResultVO>) map.get("result");
        totalCount = (Long) map.get("count");
        //如果totalCount为0则返回1，用于前端显示页数
        totalCount = totalCount==0?1:totalCount;
        return SUCCESS;
    }
    
    /**
     * <p>
     * Description:查询所有的邮件组信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @return
     * String
     */
    @JSON
    public String searchAllMailGroup(){
        mailGroupList = sysMailConfigManager.queryAllMailGroup();
        return SUCCESS;
    }
    /**
     * 
     * @Title: addMailGroup
     * @Description: 添加邮件分组
     * @author 李春雨
     * @param @return    
     * @return String  
     */
    @JSON
    public String addMailGroup(){
    	sysMailConfigManager.addMailGroup(mailGroup);
        return SUCCESS;
    }
    /**
     * 
     * @Title: updateMailGroup
     * @Description: 修改邮件分组
     * @author 李春雨
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    @JSON
    public String updateMailGroup(){
        sysMailConfigManager.modifyMailGroup(mailGroup);
        return SUCCESS;
    }
    /**
     * 
     * @Title: deleteMailGroup
     * @Description: 删除邮件分组
     * @author 李春雨
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    @JSON
    public String deleteMailGroup(){
        sysMailConfigManager.deleteMailGroupBatch(mailGroupId);
        return SUCCESS;
    }
    /**
     * 
     * @Title: addMailPeople
     * @Description: 添加收件人信息
     * @author 李春雨
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    @JSON
    public String addMailPeople(){
        //先添加人员之后再添加关系
        sysMailConfigManager.addMailAccount(mailAccount,accountGroup);
        return SUCCESS;
    }
    /**
     * 
     * @Title: addMailPeople
     * @Description: 修改收件人
     * @author 李春雨
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    @JSON
    public String updateMailPeople(){
        //只修改人员基本信息
        sysMailConfigManager.modifyMailAccount(mailAccount);
        return SUCCESS;
    }
    /****************************getter setter*********************************/
    /**
     * <p>
     * Description: relationIds getter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-24
     * @return
     * String[]
     */
    public String[] getRelationIds() {
        return relationIds;
    }
    /**
     * <p>
     * Description: sysMailConfigManager setter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-24
     * @param sysMailConfigManager
     * void
     */
    public void setSysMailConfigManager(ISysMailConfigManager sysMailConfigManager) {
        this.sysMailConfigManager = sysMailConfigManager;
    }
    /**
     * <p>
     * Description: relationIds setter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-24
     * @param groupAccountIds
     * void
     */
    public void setRelationIds(String[] relationIds) {
        this.relationIds = relationIds;
    }
    
    /**
     * <p>
     * Description: searchString getter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @return
     * List<MailGroup>
     */
    public String getSearchString() {
        return searchString;
    }
    /**
     * <p>
     * Description: searchString setter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @param mailGroupList
     * void
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    /**
     * <p>
     * Description:mailAccountIds getter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @return
     * List<MailGroup>
     */
    public String[] getMailAccountIds() {
        return mailAccountIds;
    }
    /**
     * <p>
     * Description:mailAccountIds setter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @param mailGroupList
     * void
     */
    public void setMailAccountIds(String[] mailAccountIds) {
        this.mailAccountIds = mailAccountIds;
    }
    /**
     * <p>
     * Description:mailGroupList getter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @return
     * List<MailGroup>
     */
    public List<MailGroup> getMailGroupList() {
        return mailGroupList;
    }

    /**
     * <p>
     * Description:mailGroupList setter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @param mailGroupList
     * void
     */
    public void setMailGroupList(List<MailGroup> mailGroupList) {
        this.mailGroupList = mailGroupList;
    }

    /**
     * <p>
     * Description:mailGroupId getter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @param mailGroupList
     * void
     */
    public String getMailGroupId() {
        return mailGroupId;
    }
    /**
     * <p>
     * Description:mailGroupId setter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-23
     * @param mailGroupList
     * void
     */
    public void setMailGroupId(String mailGroupId) {
        this.mailGroupId = mailGroupId;
    }
    /**
     * <p>
     * Description: accGroResultList getter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-24
     * @return
     * List<AccGroResultVO>
     */
    public List<AccGroResultVO> getAccGroResultList() {
        return accGroResultList;
    }
    /**
     * <p>
     * Description:accGroResultList setter<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-9-24
     * @param accGroResultList
     * void
     */
    public void setAccGroResultList(List<AccGroResultVO> accGroResultList) {
        this.accGroResultList = accGroResultList;
    }
    /**
     * <p>
     * Description:mailAccount getter<br />
     * </p>
     * @author 李春雨
     * @version 0.1 2013-9-28
     * @return
     * MailAccount
     */
	public MailAccount getMailAccount() {
		return mailAccount;
	}
	/**
	 * <p>
	 * Description:mailAccount setter<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-9-28
	 * @param mailAccount
	 * void
	 */
	public void setMailAccount(MailAccount mailAccount) {
		this.mailAccount = mailAccount;
	}
	/**
	 * 
	 * <p>
	 * Description:mailGroup getter<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2013-9-28
	 * @return
	 * MailGroup
	 */
    public MailGroup getMailGroup() {
        return mailGroup;
    }
    /**
     * 
     * <p>
     * Description:mailGroup setter<br />
     * </p>
     * @author 李春雨
     * @version 0.1 2013-9-28
     * @param mailGroup
     * void
     */
    public void setMailGroup(MailGroup mailGroup) {
        this.mailGroup = mailGroup;
    }
    /**
     * 
     * <p>
     * Description:accountGroup getter<br />
     * </p>
     * @author 李春雨
     * @version 0.1 2013-9-28
     * @return
     * AccountGroup
     */
    public AccountGroup getAccountGroup() {
        return accountGroup;
    }
    /**
     * 
     * <p>
     * Description:accountGroup setter<br />
     * </p>
     * @author 李春雨
     * @version 0.1 2013-9-28
     * @param accountGroup
     * void
     */
    public void setAccountGroup(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
    }
}
