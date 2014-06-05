package com.deppon.crm.module.frameworkimpl.server.ssoImpl;             

import java.sql.Timestamp;

import com.deppon.crm.module.frameworkimpl.server.service.ITokenService;
import com.deppon.crm.module.frameworkimpl.shared.exception.TokenException;
import com.deppon.crm.module.frameworkimpl.shared.exception.TokenExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.deppon.foss.framework.server.sso.ITokenStore;


/**
 * @Classname:TokenDatabaseStore
 * @Description:在数据库中存放token
 * @author ztjie
 * @date 2011-11-21  上午10:23
 * 
 */
@SuppressWarnings("rawtypes")
public class TokenDatabaseStore implements ITokenStore{

	private static TokenDatabaseStore INSTANCE = new TokenDatabaseStore();

	private ITokenService tokenService;
	
	public TokenDatabaseStore(){
		tokenService = (ITokenService) WebApplicationContextHolder.getWebApplicationContext().getBean("tokenService");
	}
	
	public TokenDatabaseStore getInstance(){
		if(INSTANCE==null)
			INSTANCE = new TokenDatabaseStore();
		return INSTANCE;
	}

	/**
     * 根据token到令牌数据库表中获取数据
     * @Title:getToken
     * @Description:根据token到令牌数据库表中获取数据
     * @param @param token
     * @param @return
     * @return String
     * @throws
     */
	@SuppressWarnings("serial")
	public Object get(Object key) {
		if(key instanceof String){
			return tokenService.queryById((String)key);			
		}
		TokenException e = new TokenException(TokenExceptionType.TokenKeyNoStringType);
		throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
	}
	/**
     * 根据token到令牌数据库表中判断是否存在此token值
     * @Title:constainsToken
     * @Description:根据token到令牌数据库表中判断是否存在此token值
     * @param @param token
     * @param @return
     * @return boolean
     * @throws
     */
	public boolean exists(String key) {
		if(key instanceof String){
			if(tokenService.queryById((String)key)!=null){
				return true;			
			}	
		}
		return false;
	}
	/**
     * 删除令牌数据库表中对应的key值
     * @Title:removeToken
     * @Description:删除令牌数据库表中对应的key值
     * @param @param token
     * @param @return
     * @return String
     * @throws
     */
	@SuppressWarnings("serial")
	public void remove(Object key) {
		if(key instanceof String){		
			tokenService.removeById((String)key);
			return;
		}
		TokenException e = new TokenException(TokenExceptionType.TokenKeyNoStringType);
		throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
	}

	/**
	 * 将Token存放到数据库保存
	 * @author suyujun
	 * @param key
	 * @param value 
	 * @param cacheTime 过期时间
	 * @see com.deppon.foss.framework.server.sso.ITokenStore#put(java.lang.Object, java.lang.Object, java.lang.Long)
	 */
	@Override
	public void put(Object key, Object value, Long cacheTime) {
		if(key instanceof String){
			if(value instanceof String){
				tokenService.save((String)key,(String)value,new Timestamp(cacheTime));				
			}
		}
	}

	/**
	 * 删除过期时间之前的token
	 * @see com.deppon.foss.framework.server.sso.ITokenStore#removeAll(java.lang.Long)
	 */
	@Override
	public void removeAll(Long timeLong) {
		tokenService.removeAll(new Timestamp(timeLong));
	}
	
	/**
     * 把value添加到令牌数据库表中
     * @Title:getToken
     * @Description:把value添加到令牌数据库表中
     * @param @param token
     * @throws
     */
	@Deprecated
	public void put(Object key, Object value) {
		if(key instanceof String){
			if(value instanceof String){
				tokenService.save((String)key,(String)value);				
			}
		}
	}
	
	/**
	 * 不能删除令牌数据库表中的所有数据
	 */
	@Deprecated
	public void removeAll() {
		
	}
}
