package com.deppon.crm.module.uums.server.manager.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.server.service.IOrgService;
import com.deppon.crm.module.uums.shared.domain.BaseUUEntity;
import com.deppon.crm.module.uums.shared.domain.OrgInfo;
/**
 * 
 * @description CRM同步uums 组织架构数据实现类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class SyncUumsOrgInfoManager implements ISyncUumsDataManager {
	private IOrgService orgService;
	private ISyncManager orgSyncManager;
	//插入
	public static final String INSERT="1";
	//修改
	public static final String UPDATE="2";
	//异动
	public static final String CHANGED="3";
	//待撤销 如何处理？
	public static final String EXIST="4";
	//已撤销
	public static final String DELETED="5";
		public IOrgService getOrgService() {
		return orgService;
	}
	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}
	
	public ISyncManager getOrgSyncManager() {
		return orgSyncManager;
	}
	public void setOrgSyncManager(ISyncManager orgSyncManager) {
		this.orgSyncManager = orgSyncManager;
	}
	/**
	 * 组织数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(BaseUUEntity entity) {
		OrgInfo orgInfo=(OrgInfo)entity;
	    int res=orgService.insert(orgInfo);
		return res;
	}
	/**
	 * 组织数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int update(BaseUUEntity entity) {
		String orgId=entity.getTheMainId();
		OrgInfo orgInfo=(OrgInfo)entity;
	    int res=orgService.updateById(orgInfo, orgId);
		return res;
	}
	/**
	 * 组织数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int delete(BaseUUEntity entity) {
		String orgId=entity.getTheMainId();
	    int res=orgService.deleteById(orgId);
		return res;

	}
	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	@Transactional
	public int check(BaseUUEntity entity) {
		OrgInfo org=(OrgInfo)entity;
		String orgId=entity.getTheMainId();
		String changeType=entity.getChangeType();
		OrgInfo orgInfo=orgService.searchById(orgId);
		String parentId=org.getParentOrgId();
		String parentStandCode=org.getParentOrgBenchmarkCode();
		String standCode=org.getOrgBenchmarkCode();
		if(StringUtils.isEmpty(parentStandCode)||StringUtils.isEmpty(parentId)||StringUtils.isEmpty(standCode)){
			throw new SyncException("the parentStandCode or the standCode "+parentStandCode+" "+standCode+" "+parentId+" is not exists");
		}
		int res=0;
		if(orgInfo!=null){
			res=1;
		}
		orgInfo=(OrgInfo)entity;
		if(INSERT.equals(changeType)||EXIST.equals(changeType)||UPDATE.equals(changeType)||CHANGED.equals(changeType)){
			if(res==0){
				res=orgService.insert(orgInfo);
			}else{
				res=orgService.updateById(orgInfo, orgId);
				//throw new SyncException("SyncUumsOrgInfoManager.INSERT the id "+orgId +" exsits");
			}
		}else if(DELETED.equals(changeType)){
			if(res>0){
				res=orgService.deleteById(orgId);
			}else{
					throw new SyncException("SyncUumsOrgInfoManager.DELETED the id "+orgId +" nofount");
			}
		}else{
			throw new SyncException("the changType"+changeType+"is NOT defined");
		}
		Map<String,String> tmp=new HashMap<String,String>();
		tmp.put(ISyncManager.CHANGETYPE, changeType);
		tmp.put(ISyncManager.DEPSTANDCODE, standCode);
		tmp.put(ISyncManager.PARENTSTANDCODE, parentStandCode);
		orgSyncManager.check(tmp);
		return SUCCESS;
	}
}
