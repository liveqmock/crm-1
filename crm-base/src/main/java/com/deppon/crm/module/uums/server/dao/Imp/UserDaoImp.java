package com.deppon.crm.module.uums.server.dao.Imp;

import java.util.Map;

import com.deppon.crm.module.uums.server.dao.IUserDao;
import com.deppon.crm.module.uums.shared.domain.UserInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class UserDaoImp extends iBatis3DaoImpl implements IUserDao{
	private final String NAMESPACE="com.deppon.crm.module.uumms.shared.domain.User.";
	private final String INSERTSQL="insert";
	private final String UPDATEByCodeSQL="UpdateByCode";
	private final String UPDATEByIdSQL="UpdateById";
	private final String DELETEByCodeSQL="DeleteByCode";
	private final String DELETEByIdSQL="DeleteById";
	private final String SELECTByCodeSQL="selectByCode";
	private final String SELECTByIdSQL="selectById";
	private final String RETURNByCodeSQL="ReturnByCode";
	private final String RETURNByIdSQL="ReturnById";
	@Override
	public int insert(UserInfo entity) {
		int res=this.getSqlSession().insert(NAMESPACE+INSERTSQL,entity);
		return res;
	}

	@Override
	public int updateById(UserInfo entity, String userId) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByIdSQL,entity);
		return res;
	}

	@Override
	public int updateByCode(UserInfo entity, String userId) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByCodeSQL,entity);
		return res;
	}

	@Override
	public int deleteByUserid(String userId) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByIdSQL,userId);
		return res;
	}

	@Override
	public int deleteByUserCode(Map<String,String> userMap) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByCodeSQL,userMap);
		return res;
	}

	@Override
	public UserInfo searchByUserId(String userId) {
		UserInfo res=(UserInfo) this.getSqlSession().selectOne(NAMESPACE+SELECTByIdSQL,userId);
		return res;
	}

	@Override
	public UserInfo searchByUserCode(String userCode) {
		UserInfo res=(UserInfo) this.getSqlSession().selectOne(NAMESPACE+SELECTByCodeSQL,userCode);
		return res;
	}

	@Override
	public int returnById(String userid) {
		int res=this.getSqlSession().update(NAMESPACE+RETURNByIdSQL,userid);
		return res;
	}

	@Override
	public int returnByCode(UserInfo entity) {
		int res=this.getSqlSession().update(NAMESPACE+RETURNByCodeSQL,entity);
		return res;
	}

}
