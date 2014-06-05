package com.deppon.crm.module.uums.server.manager.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.server.service.IOrgService;
import com.deppon.crm.module.uums.server.service.IUserService;
import com.deppon.crm.module.uums.shared.domain.BaseUUEntity;
import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
import com.deppon.crm.module.uums.shared.domain.OrgInfo;
import com.deppon.crm.module.uums.shared.domain.UserInfo;
/**
 * 
 * @description CRM同步uums 用户数据实现类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class SyncUumsUserInfoManager implements ISyncUumsDataManager {
	private IUserService userService;
	private IOrgService orgService;
	private ISyncManager userSync;
	//插入
	public static final String INSERT="1";
	//修改
	public static final String UPDATE="2";
	//删除
	public static final String DELETE="3";
	//返聘
	public static final String REUSED="4";
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	public IOrgService getOrgService() {
		return orgService;
	}
	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}
	public ISyncManager getUserSync() {
		return userSync;
	}
	public void setUserSync(ISyncManager userSync) {
		this.userSync = userSync;
	}
	/**
	 * 用户数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(BaseUUEntity entity) {
		return 0;
	}
	/**
	 * 用户数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int update(BaseUUEntity entity) {
		return 0;

	}
	/**
	 * 用户数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int delete(BaseUUEntity entity) {
		return 0;

	}
	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	@Transactional
	public int check(BaseUUEntity entity) {
		int res=0;
		String empCode=entity.getTheMainCode();
		String changeType=entity.getChangeType();
		UserInfo userInfo=userService.searchByUserCode(empCode);
		String depStandCode=((UserInfo)entity).getOrgBenchmarkCode();
		boolean status=((UserInfo)entity).isActive();
		if(userInfo!=null){
			res=1;
		}
		userInfo=(UserInfo)entity;
		if(INSERT.equals(changeType)||UPDATE.equals(changeType)){
			if(res==0){
				res=userService.insert(userInfo);
			}else{
				res=userService.updateByCode(userInfo, empCode);
			}
		}else if(DELETE.equals(changeType)){
			if(res!=0){
				Map<String,String> m=new HashMap<String,String>();
				m.put("userCode", empCode);
				m.put("status", (status)?"1":"0");
				res=userService.deleteByUserCode(m);
			}else {
					throw new SyncException("SyncUumsUserInfoManager.delete the code "+empCode +" nofount");	
			}
		}else if(REUSED.equals(changeType)){
			if(res>0){
				res=userService.returnByCode((UserInfo)entity);
			}else {
				res=userService.insert(userInfo);
			}
		}else{
			throw new SyncException("the changType"+changeType+"is NOT defined");
		}
		Map<String,String> tmp=new HashMap<String,String>();
		tmp.put(ISyncManager.CHANGETYPE, changeType);
		tmp.put(ISyncManager.MAINCODE, empCode);
		userSync.check(tmp);
		return SUCCESS;
	}

}
