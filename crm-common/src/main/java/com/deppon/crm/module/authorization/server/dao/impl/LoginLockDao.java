package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.Date;

import com.deppon.crm.module.authorization.server.dao.ILoginLockDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class LoginLockDao extends iBatis3DaoImpl implements ILoginLockDao {
	private static final String NAMESPACE = "com.deppon.crm.module.authorization.shared.domain.User.";
	private static final String QUERY_LASTERR_TIME = "queryUserlastErrTime";
	private static final String INSERT_LASTERR_TIME = "insertLastErrTime";
	private static final String CLEAN_LASTERR_TIME = "cleanUserlastErrTime";
	private static final String CLEAN_ERROR_TIMES="cleanErrorTimes";
	private static final String QUERY_ERROR_TIMES="queryErrorTimes";
	private static final String UPDATE_ERROR_TIMES = "updateErrorTimes";
	/**
	 * 查询最后一次输入错误密码的时间
	 */
	@Override
	public Date queryUserlastErrTime(User user) {
		return (Date) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_LASTERR_TIME, user);
	}

	/**
	 * 更新最后一次输入错误密码的时间
	 */
	@Override
	public boolean insertUserlastErrTime(User user) {
		return this.getSqlSession().insert(NAMESPACE + INSERT_LASTERR_TIME,
				user) > 0 ? true : false;
	}

	/**
	 * 清除最后一次输入错误密码时间
	 */
	@Override
	public boolean cleanUserlastErrTime(User user) {
		return this.getSqlSession().update(NAMESPACE+CLEAN_LASTERR_TIME, user) > 0 ? true : false;
	}


	/**
	 * 清除错误次数
	 */
	@Override
	public void cleanErrorTimes(User user) {
		this.getSqlSession().update(NAMESPACE+CLEAN_ERROR_TIMES, user);
	}


	/**
	 * 查询错误次数
	 */
	@Override
	public int queryErrorTimes(User user) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE+QUERY_ERROR_TIMES, user);
	}


	/**
	 * 更新用户输入密码错误次数
	 */
	@Override
	public boolean updateErrorTimes(User user) {
		return this.getSqlSession().update(NAMESPACE+UPDATE_ERROR_TIMES, user) > 0 ? true : false;
	}

}
