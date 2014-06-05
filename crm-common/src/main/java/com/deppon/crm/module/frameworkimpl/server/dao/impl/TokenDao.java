package com.deppon.crm.module.frameworkimpl.server.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.frameworkimpl.server.dao.ITokenDao;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 令牌数据处理DAO
 * @author ztjie
 *
 */
public class TokenDao extends iBatis3DaoImpl implements ITokenDao {
    
    //令牌命名空间
    private static final String TOKENMAPER = "com.deppon.crm.framework.server.sso.config.Token.";
    //得到合法的令牌
    private static final String FINDVALIDATETOKEN = "findValidateToken";
            
	public void insert(String id, String token) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",id);
		map.put("token", token);
		getSqlSession().insert("com.deppon.crm.framework.server.sso.config.Token.insert", map);
	}

	public String getById(String id) {
		String token = (String) getSqlSession().selectOne("com.deppon.crm.framework.server.sso.config.Token.getById", id);
		return token;
	}

	public void deleteById(String id) {
		getSqlSession().delete("com.deppon.crm.framework.server.sso.config.Token.deleteById", id);
	}

	public void deleteAll() {
		getSqlSession().delete("com.deppon.crm.framework.server.sso.config.Token.deleteAll");
	}

	/**
	 * @description 保存令牌对象
	 * @param key
	 * @param value
	 * @param validTime
	 * @see com.deppon.crm.module.frameworkimpl.server.dao.ITokenDao#insert(java.lang.String, java.lang.String, java.sql.Timestamp)
	 */
	@Override
	public void insert(String key, String value, Timestamp validTime) {
		Map<String,Object> paras = new HashMap<String, Object>();
		paras.put("id", key);
		paras.put("token", value);
		paras.put("validTime", validTime);
		this.getSqlSession().insert("com.deppon.crm.framework.server.sso.config.Token.insertWithValidTime",paras);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.frameworkimpl.server.dao.ITokenDao#removeAll(java.sql.Timestamp)
	 */
	@Override
	public void removeAll(Timestamp validTime) {
		this.getSqlSession().delete("com.deppon.crm.framework.server.sso.config.Token.removeWithValidTime", validTime);
	}

    @Override
    public String getById(String id, Timestamp currTime) {
        Map<String,Object> paras = new HashMap<String, Object>();
        paras.put("id", id);
        paras.put("currTime", currTime);
        return (String) getSqlSession().selectOne(TOKENMAPER + FINDVALIDATETOKEN, paras);
    }

}