package com.deppon.crm.module.uums.server.manager.impl;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.server.service.ICompanyService;
import com.deppon.crm.module.uums.shared.domain.BaseUUEntity;
import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.crm.module.uums.shared.exception.invalidIdException;
/**
 * 
 * @description CRM同步uums 子公司数据实现类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class SyncUumsCompanyInfoManager implements ISyncUumsDataManager {
	private ICompanyService companyService;
	//插入
	public static final String INSERT="1";
	//修改
	public static final String UPDATE="2";
	//删除
	public static final String DELETE="3";
	public ICompanyService getCompanyService() {
		return companyService;
	}
	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}
	/**
	 * 子公司数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(BaseUUEntity entity) {
		int res=companyService.insert(((CompanyInfo)entity));
		return res;
	}
	/**
	 * 子公司数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int update(BaseUUEntity entity) {
		CompanyInfo companyInfo=(CompanyInfo)entity;
		String CompanyCode=companyInfo.getCompanyStandCode();
		int res=companyService.updateByCompanyCode(companyInfo, CompanyCode);
		return res;
	}
	/**
	 * 子公司数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int delete(BaseUUEntity entity) {
		CompanyInfo companyInfo=(CompanyInfo)entity;
		String CompanyCode=companyInfo.getCompanyStandCode();
		int res=companyService.deleteByCode(CompanyCode);
		return res;
	}
	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	@Transactional
	public int check(BaseUUEntity entity) {
		String changeType=entity.getChangeType();
		String companySDCode=((CompanyInfo)entity).getCompanyStandCode();
		CompanyInfo com=companyService.searchByCode(companySDCode);
		int res=0;
		if(null!=com){
			res=1;
		}
		if(UPDATE.equals(changeType)||INSERT.equals(changeType)){
			if(res==1){
				res=update(entity);
			}else {
				res=insert(entity);
			}
		}else if(DELETE.equals(changeType)){
			if(res==1){
				res=delete(entity);
			}else {
					throw new SyncException("SyncUumsCompanyInfoManager.delete the code "+companySDCode +" nofount");
			}
		}else{
			throw new SyncException("the changType"+changeType+"is NOT defined");
		}
		return SUCCESS;
	}
}
