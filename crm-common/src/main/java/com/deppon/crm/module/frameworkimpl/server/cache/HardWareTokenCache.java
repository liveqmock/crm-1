package com.deppon.crm.module.frameworkimpl.server.cache;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.deppon.crm.module.frameworkimpl.server.dao.ITokenDao;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.cache.ICacheStorage;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;

/**
 * 
 * <p>
 * Description:主机硬件Token缓存类<br />
 * </p>
 * @title HardWareTokenCache.java
 * @package com.deppon.crm.module.frameworkimpl.server.cache 
 * @author 石应华
 * @version 0.1 2013-6-27
 */
public class HardWareTokenCache implements ICache<String, String>,ICacheStorage<String, String>,InitializingBean {

    public final static String UUID = HardWareTokenCache.class.getName();
    
    private RedisCacheStorage<String, String> cacheStorage;
    
    //主机硬件Token DAO
    private ITokenDao tokenDao = null;
    
    /**
     * 过期时间5分钟。
     */
    private int expire = 5 * 60;
    
    public String getUUID() {
        return UUID;
    }

    /**
     * <p>
     * Description:设置键、值及过期时间<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-8-29
     * @param key
     * @param value
     * @see com.deppon.foss.framework.cache.ICacheStorage#set(java.lang.Object, java.lang.Object)
     */
    @Override
    public void set(String key, String value) {
        //如果没有保存到缓存，则存入数据库中
        boolean isInsertRedisSuccess = cacheStorage.set(key, value,expire);
        //如果存入Redis不成功，则存入数据库中
        if(!isInsertRedisSuccess){
            //设置有效时间
            long validateTime = System.currentTimeMillis() + expire*1000L;
            //把token信息存入数据库
            tokenDao.insert(key,value,new Timestamp(validateTime));
        }
    }
    /**
     * <p>
     * Description:通过键获得token信息值<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-8-29
     * @param key
     * @return
     * @see com.deppon.foss.framework.cache.ICache#get(java.lang.Object)
     */
    @Override
    public String get(String key) {
        //如果得不到到数据库里查token时间小于5分钟的
        try{
            return cacheStorage.get(key);
        }catch(Exception e){
            return tokenDao.getById(key,new Timestamp(System.currentTimeMillis()));
        }
    }

    /**
     * 查询是否包含指定键
     */
    @Override
    public Boolean exists(String key) {
        String value = cacheStorage.get(key);
        return value != null && "".equals(value);
    }
    /**
     * 移除指定键的值
     */
    @Override
    public void remove(String key) {
        cacheStorage.remove(key);
    }
    
    @Override
    public Map<String, String> get() {
        return null;
    }

    @Override
    public void invalid() {
    }

    @Override
    public void invalid(String key) {
    }

    @Override
    public void invalidMulti(String... keys) {
    }

    /**
     * 注册到CacheManager
     */
    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        CacheManager.getInstance().registerCacheProvider(this);
    }

    
    @Override
    public void set(Map<String, String> values) {
    }

    @Override
    public void clear() {
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public RedisCacheStorage<String, String> getCacheStorage() {
        return cacheStorage;
    }

    public void setCacheStorage(RedisCacheStorage<String, String> cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    public ITokenDao getTokenDao() {
        return tokenDao;
    }

    public void setTokenDao(ITokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }
}
