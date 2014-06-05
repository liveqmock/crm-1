package com.deppon.crm.module.frameworkimpl.server.ssoImpl;             

import com.deppon.foss.framework.server.sso.impl.TokenMemCacheStore;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.deppon.foss.framework.server.sso.ITokenStore;


/**
 * @Classname:TokenMemcacheStore
 * @Description:在集中式缓存中存放token
 * @author ztjie
 * @date 2011-11-21  上午10:23
 * 
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class TokenMemcacheStore implements ITokenStore{

	private static TokenMemcacheStore INSTANCE = new TokenMemcacheStore();

	private TokenMemCacheStore memcached;
	
	public TokenMemcacheStore(){
		memcached = (TokenMemCacheStore) WebApplicationContextHolder.getWebApplicationContext().getBean("memcached");
	}
	
	public TokenMemcacheStore getInstance(){
		if(INSTANCE==null)
			INSTANCE = new TokenMemcacheStore();
		return INSTANCE;
	}
	
	/**
	 * @description 将Token存放到集中式缓存中
	 * @author suyujun
	 * @param key Token对应的key
	 * @param value token值
	 * @param cacheTime token缓存有效时间
	 * @see com.deppon.foss.framework.server.sso.ITokenStore#put(java.lang.Object, java.lang.Object, java.lang.Long)
	 */
	@Override
	public void put(Object key, Object value, Long cacheTime) {
		memcached.put(key == null ? "" : key.toString(), value == null ? "" : value.toString(), cacheTime);
	}
	
	/**
     * 根据token到集中式缓存中获取数据
     * @Title:getToken
     * @Description:根据token到集中式缓存中获取数据
     * @param @param token
     * @param @return
     * @return String
     * @throws
     */
	@Override
	public Object get(Object key) {
		return memcached.get(key == null ? "" : key.toString());
	}
	
	/**
     * 删除token对应的缓存值并返回该值
     * @Title:removeToken
     * @Description:删除token对应的缓存值并返回该值
     * @param @param token
     * @param @return
     * @return String
     * @throws
     */
	@Override
	public void remove(Object key) {
		memcached.remove(key == null ? "" : key.toString());
	}
	
	/**
	 * @Description 删除所有的Token值
	 * @author suyujun
	 * @param timeLong Token的有效时间
	 * @return void
	 * @throws 
	 * @see com.deppon.foss.framework.server.sso.ITokenStore#removeAll(java.lang.Long)
	 */
	@Override
	public void removeAll(Long timeLong) {
		//不需要实现, 因为到期memcache会自动删除的
	}
	
	/**
     * 根据token到集中式缓存中判断是否存在此token值
     * @Title:constainsToken
     * @Description:根据token到集中式缓存中判断是否存在此token值
     * @param @param token
     * @param @return
     * @return boolean
     * @throws
     */
	public boolean exists(String key) {
		if(memcached.get(key)!=null){
			return true;			
		}
		return false;
	}
	
	
	/**
     * 把value添加到集中式缓存中
     * @Title:getToken
     * @Description:把value添加到集中式缓存中
     * @param @param token
     * @throws
     */
	@Deprecated
	public void put(Object key, Object value) {
		//memcached.set(key, value);
	}
	/**
	 * 不能删除集中式缓存中的所有数据
	 */
	@Deprecated
	public void removeAll() {
		throw new RuntimeException("Memcache cannot remove all!");
	}
}
