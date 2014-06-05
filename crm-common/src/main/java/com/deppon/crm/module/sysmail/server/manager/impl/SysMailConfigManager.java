package com.deppon.crm.module.sysmail.server.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.sysmail.server.manager.ISysMailConfigManager;
import com.deppon.crm.module.sysmail.server.service.ISysMailConfigService;
import com.deppon.crm.module.sysmail.server.utils.UserUtils;
import com.deppon.crm.module.sysmail.server.utils.ValidateMailUtils;
import com.deppon.crm.module.sysmail.shared.domain.AccGroResultVO;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;

/**
 * 
 * <p>
 * 邮件配置管理核心业务处理实现类<br />
 * </p>
 * @title SysMailConfigManager.java
 * @package com.deppon.crm.module.sysmail.server.manager 
 * @author suyujun
 * @version 0.1 2013-9-18
 */
public class SysMailConfigManager implements ISysMailConfigManager {
	private static final int TEMP_START = 0;
	private static final int TEMP_LIMIT = 10000;
	private ISysMailConfigService sysMailConfigService;
	
	
	/**
	 * @return sysMailConfigService : return the property sysMailConfigService.
	 */
	public ISysMailConfigService getSysMailConfigService() {
		return sysMailConfigService;
	}


	/**
	 * @param sysMailConfigService : set the property sysMailConfigService.
	 */
	public void setSysMailConfigService(ISysMailConfigService sysMailConfigService) {
		this.sysMailConfigService = sysMailConfigService;
	}


	/**
	 * 
	 * <p>
	 * 添加邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-18
	 * @param ma
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public synchronized void addMailAccount(MailAccount ma,AccountGroup ag) {
		//验证邮件对象中的属性是否正确
		ValidateMailUtils.validateAccount(ma);
		//验证邮件地址重复性
		List<MailAccount> list = sysMailConfigService.queryAllMailAccount();
		ValidateMailUtils.validateRepeatAddress(list,ma);
		//插入操作
		ma.setCreateUser(UserUtils.getUserName());
		ma.setModifyUser(UserUtils.getUserName());
		sysMailConfigService.addMailAccount(ma);
        if(StringUtils.isNotEmpty(ag.getMailGroupId())){
        	ag.setMailAccountId(ma.getId());  
        	ag.setCreateUser(UserUtils.getUserName());
        	ag.setModifyUser(UserUtils.getUserName());
        	sysMailConfigService.addAccGroRelation(ag);
        }
	}


	/**
	 * 
	 * <p>
	 * 删除邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mailAccountId
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void deleteMailAccountById(String mailAccountId) {
		//1.删除映射关系
		AccountGroup ag = new AccountGroup();
		ag.setMailAccountId(mailAccountId);
		sysMailConfigService.deleteAccGroRelation(ag);
		//2.删除邮件账号
		sysMailConfigService.deleteMailAccountById(mailAccountId);
	}

	/**
	 * 
	 * <p>
	 * 更新邮件账号<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param ma
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void modifyMailAccount(MailAccount ma) {
		//验证修改信息
		ValidateMailUtils.validateAccount(ma);
		//验证重复
		List<MailAccount> list = sysMailConfigService.queryAllMailAccount();
		ValidateMailUtils.validateRepeatAddress(list, ma);
		//update
		ma.setModifyUser(UserUtils.getUserName());
		sysMailConfigService.modifyMailAccount(ma);
	}


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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public synchronized void  addMailGroup(MailGroup mg) {
		//验证邮件组对象
		ValidateMailUtils.validateGroup(mg);
		//重复性校验
		List<MailGroup> mgs = sysMailConfigService.queryAllMailGroup();
		ValidateMailUtils.validateRepeatGroup(mgs,mg);
		//add
		mg.setCreateUser(UserUtils.getUserName());
		mg.setModifyUser(UserUtils.getUserName());
		sysMailConfigService.addMailGroup(mg);
	}

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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void modifyMailGroup(MailGroup mg) {
		//验证邮件组对象
		ValidateMailUtils.validateGroup(mg);
		//验证重复
		List<MailGroup> mgs = sysMailConfigService.queryAllMailGroup();
		ValidateMailUtils.validateRepeatGroup(mgs, mg);
		//update
		mg.setModifyUser(UserUtils.getUserName());
		sysMailConfigService.modifyMailGroup(mg);
	}

	/**
	 * 
	 * <p>
	 * 删除邮件组,批量删除<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-9-22
	 * @param mg
	 * void
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void deleteMailGroupBatch(String mailGroupId) {
		//1.先删除映射关系
		AccountGroup ag = new AccountGroup();
		ag.setMailGroupId(mailGroupId);
		sysMailConfigService.deleteAccGroRelation(ag);
		//2.删除组
		sysMailConfigService.deleteMailGroupById(mailGroupId);
	}

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
	@Override
	public List<MailGroup> queryAllMailGroup() {
		return sysMailConfigService.queryAllMailGroup();
	}

	
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
	@Override
	public Map<String,Object> queryMailAccountByGroupId(String groupId,int start,int limit) {
		Map<String,Object> returnValue = new HashMap<String, Object>();
		List<AccGroResultVO> result = sysMailConfigService.queryMailAccountByGroupId(groupId,start,limit);
		long count = sysMailConfigService.countMailAccountByGroupId(groupId);
		returnValue.put("result", result);
		returnValue.put("count", count);
		return returnValue;
	}

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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void addAccGroRelation(AccountGroup ag) {
		sysMailConfigService.addAccGroRelation(ag);
	}

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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void deleteAccGroRelation(AccountGroup ag) {
		sysMailConfigService.deleteAccGroRelation(ag);
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
	public Map<String,Object> queryMailAccountByCondition(String whatString,int start,int limit) {
		Map<String,Object> returnValue = new HashMap<String,Object>();
		List<MailAccount> result =  sysMailConfigService.queryMailAccountByCondition(whatString,start,limit);
		long count = sysMailConfigService.countMailAccountByCondition(whatString);
		returnValue.put("result", result);
		returnValue.put("count", count);
		return returnValue;
	}

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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public synchronized void batchDivide(List<String> accoundIds, String groupId) {
		//1.查询该分组下的账号集合
		List<String> orignalAccoundIds = new ArrayList<String>();
		List<AccGroResultVO> result = sysMailConfigService.queryMailAccountByGroupId(groupId, TEMP_START, TEMP_LIMIT);
		for(AccGroResultVO vo:result){
			orignalAccoundIds.add(vo.getAccountId());
		}
		
		//2.取消选择的账号中的重复ID
		accoundIds = new ArrayList<String>(accoundIds);
		HashSet<String> h = new HashSet<String>(accoundIds);
		accoundIds.clear();
		accoundIds.addAll(h);
		
		
		//3.去掉在当前组重复的元素
		String accountId = "";
		for(int i =0;i<accoundIds.size();i++){
			accountId = accoundIds.get(i);
			if(orignalAccoundIds.contains(accountId)){
				accoundIds.remove(accountId);
				i--;
			}
		}
		
		//3.封装对象
		AccountGroup ag = new AccountGroup();
		for(String accoundId:accoundIds){
			ag.setMailAccountId(accoundId);
			ag.setMailGroupId(groupId);
			ag.setCreateUser(UserUtils.getUserName());
			ag.setModifyUser(UserUtils.getUserName());
			sysMailConfigService.addAccGroRelation(ag);
		}
	}

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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void removeRelationBatch(List<String> relationIds) {
		if(relationIds!=null && relationIds.size() > 0){
			sysMailConfigService.removeRelationBatch(relationIds);
		}
	}

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
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void deleteAccountBatch(List<String> accoundIds) {
		AccountGroup ag = new AccountGroup();
		for(String accoundId : accoundIds){
			ag.setMailAccountId(accoundId);
			//1、批量删除关系
			sysMailConfigService.deleteAccGroRelation(ag);
			//2、批量删除收件人
			sysMailConfigService.deleteMailAccountById(accoundId);
		}
	}
	
}
