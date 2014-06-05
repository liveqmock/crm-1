package com.deppon.crm.module.uums.server.service.Imp;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.dao.ICompanyDao;
import com.deppon.crm.module.uums.server.service.ICompanyService;
import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.crm.module.uums.shared.exception.invalidIdException;



public class CompanyService implements ICompanyService {
	private ICompanyDao companyDao;

	public ICompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(ICompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public int insert(CompanyInfo entity) {
		int res=companyDao.insert(entity);	
		return res;
	}

	@Override
	public int updateByCompanyId(CompanyInfo entity, String companyId) {
		int res=companyDao.updateByCompanyId(entity, companyId);
		return res;
	}

	@Override
	public int updateByCompanyCode(CompanyInfo entity, String companyCode) {
		int res=companyDao.updateByCompanyCode(entity, companyCode);
		return res;
	}

	@Override
	public int deleteById(String companyId) {
		if(StringUtils.isEmpty(companyId)){
				throw new SyncException("CompanyService.deleteById(String companyId) 无效companyID");
		}
		int res=companyDao.deleteById(companyId);
		return res;
	}

	@Override
	public int deleteByCode(String companyCode) {
		if(StringUtils.isEmpty(companyCode)){
				throw new SyncException("CompanyService.deleteByCode(String companyCode) 无效companyCode");
		}
		int res=companyDao.deleteByCode(companyCode);
		return res;
	}

	@Override
	public CompanyInfo searchByCode(String companyCode) {
		if(StringUtils.isEmpty(companyCode)){	
				throw new SyncException("CompanyService.searchByCode(String companyCode) 无效companyCode");		
		}
		CompanyInfo companyInfo=companyDao.searchByCode(companyCode);
		return companyInfo;
	}

	@Override
	public CompanyInfo searchById(String companyId) {	
		if(StringUtils.isEmpty(companyId)){
				throw new SyncException("CompanyService.searchById(String companyId) 无效companyId");
		}
		CompanyInfo companyInfo=companyDao.searchById(companyId);
		return companyInfo;
	}

}
