package com.deppon.crm.module.uums.server.dao.Imp;

import com.deppon.crm.module.uums.server.dao.ICompanyDao;
import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class CompanyDaoImp extends iBatis3DaoImpl implements ICompanyDao{
	private final String NAMESPACE="com.deppon.crm.module.uums.shared.domain.CompanyInfo.";
	private final String INSERTSQL="insert";
	private final String UPDATEByCodeSQL="UpdateByCode";
	private final String UPDATEByIdSQL="UpdateById";
	private final String DELETEByCodeSQL="DeleteByCode";
	private final String DELETEByIdSQL="DeleteById";
	private final String SELECTByCodeSQL="selectCompanyByCode";
	private final String SELECTByIdSQL="selectCompanyById";
	@Override
	public int insert(CompanyInfo entity) {
		int res=this.getSqlSession().insert(NAMESPACE+INSERTSQL, entity);
		return res;
	}

	@Override
	public int updateByCompanyId(CompanyInfo entity, String companyId) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByIdSQL, entity);
		return res;
	}

	@Override
	public int updateByCompanyCode(CompanyInfo entity, String companyId) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByCodeSQL, entity);
		return res;
	}

	@Override
	public int deleteById(String companyId) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByIdSQL, companyId);
		return res;
	}

	@Override
	public int deleteByCode(String companyCode) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByCodeSQL, companyCode);
		return res;
	}

	@Override
	public CompanyInfo searchByCode(String companyCode) {
		CompanyInfo companyInfo=(CompanyInfo)this.getSqlSession().selectOne(NAMESPACE+SELECTByCodeSQL, companyCode);
		return companyInfo;
	}
	@Override
	public CompanyInfo searchById(String companyId) {
		CompanyInfo companyInfo=(CompanyInfo)this.getSqlSession().selectOne(NAMESPACE+SELECTByIdSQL, companyId);
		return companyInfo;
	}

}
