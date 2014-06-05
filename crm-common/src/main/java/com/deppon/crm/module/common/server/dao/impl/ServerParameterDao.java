package com.deppon.crm.module.common.server.dao.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.server.dao.IServerParameterDao;
import com.deppon.crm.module.common.shared.domain.ServerParameter;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;


public class ServerParameterDao extends iBatis3DaoImpl implements IServerParameterDao{
	private static final String NAME_SPACE_MAPPING = "com.deppon.crm.module.common.shared.domain.ServerParameter.";

	/**
	 * 
	 * <p>
	 * Description:c<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-6
	 * @return
	 *
	 */
	@Override
	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession()
					.selectOne(NAME_SPACE_MAPPING + "getLastModifyTime");
		return lastModyfyTime;	}

	@Override
	public List<ServerParameter> getAllServerParameter() {
		List<ServerParameter> serverParameters=	getSqlSession().selectList(NAME_SPACE_MAPPING+"getAll");		
		return serverParameters;
	}

	@Override
	public String getValueByKey(String key) {
		return (String) getSqlSession()
				.selectOne(NAME_SPACE_MAPPING + "getValueByKey",key);
	}
	

}
