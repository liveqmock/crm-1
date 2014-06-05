package com.deppon.crm.module.uums.server.dao.Imp;

import com.deppon.crm.module.uums.server.dao.IOrgDao;
import com.deppon.crm.module.uums.shared.domain.OrgInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class OrgDaoImp extends iBatis3DaoImpl implements IOrgDao{
	private final String NAMESPACE="com.deppon.crm.module.uums.shared.domain.Org.";
	private final String INSERTSQL="insert";
	private final String UPDATEByCodeSQL="UpdateByCode";
	private final String UPDATEByIdSQL="UpdateById";
	private final String DELETEByCodeSQL="DeleteByCode";
	private final String DELETEByIdSQL="DeleteById";
	private final String SELECTByCodeSQL="selectByCode";
	private final String SELECTByIdSQL="selectById";
	private final String REDELETEBYCODE="REDeleteByCode";

	@Override
	public int insert(OrgInfo entity) {
		int res=this.getSqlSession().insert(NAMESPACE+INSERTSQL,entity);
		return res;
	}

	@Override
	public int updateById(OrgInfo entity, String orgId) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByIdSQL,entity);
		return res;
	}

	@Override
	public int updateByCode(OrgInfo entity, String orgCode) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByCodeSQL,entity);
		return res;
	}

	@Override
	public int deleteById(String orgId) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByIdSQL,orgId);
		return res;
	}

	@Override
	public int deleteByCode(String orgCode) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByCodeSQL,orgCode);
		return res;
	}

	@Override
	public OrgInfo searchById(String orgId) {
		OrgInfo res=(OrgInfo) this.getSqlSession().selectOne(NAMESPACE+SELECTByIdSQL,orgId);
		return res;
	}

	@Override
	public OrgInfo searchByCode(String orgCode) {
		OrgInfo res=(OrgInfo) this.getSqlSession().selectOne(NAMESPACE+SELECTByCodeSQL,orgCode);
		return res;
	}

	@Override
	public int reDeleteByCode(String orgCode) {
		int res=this.getSqlSession().update(NAMESPACE+REDELETEBYCODE,orgCode);
		return res;
	}

}
