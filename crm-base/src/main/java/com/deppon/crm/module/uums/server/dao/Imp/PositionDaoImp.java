package com.deppon.crm.module.uums.server.dao.Imp;

import java.util.List;

import com.deppon.crm.module.uums.server.dao.IPositionDao;
import com.deppon.crm.module.uums.shared.domain.PostionInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class PositionDaoImp extends iBatis3DaoImpl implements IPositionDao{
	private final String NAMESPACE="com.deppon.crm.module.uumms.shared.domain.Position.";
	private final String INSERTSQL="insert";
	private final String UPDATEByCodeSQL="UpdateByCode";
	private final String UPDATEByIdSQL="UpdateById";
	private final String DELETEByCodeSQL="DeleteByCode";
	private final String DELETEByIdSQL="DeleteById";
	private final String SELECTByCodeSQL="selectByCode";
	private final String SELECTByNameSQL="selectByName";
	private final String SELECTByIdSQL="selectById";
	@Override
	public int insert(PostionInfo entity) {
		int res=this.getSqlSession().insert(NAMESPACE+INSERTSQL, entity);
		return res;
	}

	@Override
	public int updateById(PostionInfo entity, String positionId) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByIdSQL, entity);
		return res;
	}

	@Override
	public int updateByCode(PostionInfo entity, String positionId) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByCodeSQL, entity);
		return res;
	}

	@Override
	public int deleteByCode(String positonCode) {
		int res=this.getSqlSession().update(NAMESPACE+DELETEByCodeSQL, positonCode);
		return res;
	}

	@Override
	public int deleteById(String positonId) {
		int res=this.getSqlSession().update(NAMESPACE+DELETEByIdSQL, positonId);
		return res;
	}
	@Override
	public PostionInfo searchById(String positionId) {
		PostionInfo res=(PostionInfo) this.getSqlSession().selectOne(NAMESPACE+SELECTByIdSQL, positionId);
		return res;
	}

	@Override
	public List<PostionInfo> searchByName(String positionName) {
		List<PostionInfo> res= this.getSqlSession().selectList(NAMESPACE+SELECTByNameSQL, positionName);
		return res;
	}

	@Override
	public PostionInfo searchByCode(String positionCode) {
		PostionInfo res=(PostionInfo) this.getSqlSession().selectOne(NAMESPACE+SELECTByCodeSQL, positionCode);
		return res;
	}

}
