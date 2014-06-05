package com.deppon.crm.module.frameworkimpl.server.cache;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import com.deppon.foss.framework.cache.CacheUtils;
import com.deppon.foss.framework.cache.IRemoteCacheStore;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;
import com.deppon.foss.framework.cache.storage.exception.KeyIsNotFoundException;
import com.deppon.foss.framework.cache.storage.exception.RedisCacheStorageException;
import com.deppon.foss.framework.cache.storage.exception.RedisConnectionException;
import com.deppon.foss.framework.cache.storage.exception.ValueIsBlankException;
import com.deppon.foss.framework.cache.storage.exception.ValueIsNullException;

/**
 * 通过redis存储缓存数据
 * 1.支持序列化json
 * 
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:wmm,date:2013-9-11 11:00,content: </p>
 * @author wmm
 * @date 2013-9-11 11:00
 * @since
 * @version 1.1.1
 */
public class CRMRedisCacheStorage<K, V> extends RedisCacheStorage<K, V> implements IRemoteCacheStore<K, V>,
		InitializingBean {
	
	private JedisPool client;
	

    /**
     * 日志
     */
    Log log = LogFactory.getLog(getClass());

    /**
     * 默认数据过期时间
     */
    private int expire = 3600 * 24;
    
    /**
     * 初始化Strong cache任务
     */
    private StrongCacheTask strongTask;
    
    /**
     * 稍后重试初始化Strong cache 延迟时间，默认1分钟
     */
    private int strongDelayTime = 60 * 1000;
    
    public void setClient(JedisPool client) {
        this.client = client;
    }


    /** 
     * <p>存入数据，默认时效：3600 * 24</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:24:47
     * @param key
     * @param value 
     * @return boolean 是否执行成功
     * @see com.deppon.foss.framework.cache.IRemoteCacheStore#set(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean set(K key, V value) {
        return set(key, value, expire);
    }

    
    /** 
     * <p>存入有时效的数据</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:23:59
     * @param key
     * @param value
     * @param exp 
     * @return boolean 是否执行成功
     * @see com.deppon.foss.framework.cache.IRemoteCacheStore#set(java.lang.Object, java.lang.Object, int)
     */
    @Override
    public boolean set(K key, V value, int exp) {
       
        Jedis j = null;
        String skey = CacheUtils.toJsonString(key);
        String svalue = CacheUtils.toJsonString(value);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = client.getResource();
            j.setex(skey, exp, svalue);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            return false;
//          client.handoverToSlave();
//          set(key, value, exp);
        } finally {
            if (borrowOrOprSuccess) {
                //返回到资源池
                client.returnResource(j);
            }
        }
        return true;
    }

    /** 
     * <p>获取key对应的数据</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:23:29
     * @param key
     * @return 
     * @see com.deppon.foss.framework.cache.IRemoteCacheStore#get(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
       
        Jedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        try {
            j = client.getResource();
            String re = j.get(skey);
            //返回到资源池
            client.returnResource(j);
            if(re != null) {
                if(StringUtils.isBlank(re)) {
                    //key存在，value为空串
                    throw new ValueIsBlankException("key exists, value is blank!");
                } else if(StringUtils.equalsIgnoreCase(re, "nil")) {
                    //key不存在
                    throw new KeyIsNotFoundException("key is not found!");
                } else if(StringUtils.equalsIgnoreCase(re, "null")) {
                    //key存在，value为null
                    throw new ValueIsNullException("key exists, value is null!");
                } else {
                    return (V) CacheUtils.jsonParseObject(re);
                }
            } else {
                //key不存在
                throw new KeyIsNotFoundException("key is not found!");
            }
        } catch (JedisException e) {
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            throw new RedisConnectionException(e);
//            client.handoverToSlave();
//            return get(key);
        }
    }

    /** 
     * <p>删除指定的缓存信息</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:23:17
     * @param key 
     * @see com.deppon.foss.framework.cache.IRemoteCacheStore#remove(java.lang.Object)
     */
    @Override
    public void remove(K key) {
      
        Jedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = client.getResource();
            j.del(skey);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//          client.handoverToSlave();
//          remove(key);
        } finally {
            if (borrowOrOprSuccess) {
                client.returnResource(j);//返回到资源池
            }
        }
    }
    
    /** 
     * <p>删除多个key的缓存信息</p> 
     * @author ningyu
     * @date 2013-4-9 下午3:54:48
     * @param keys 
     * @see com.deppon.foss.framework.cache.IRemoteCacheStore#removeMulti(K[])
     */
    @Override
    public void removeMulti(K... keys) {
      
        if(keys == null) {
            throw new RedisCacheStorageException("keys does not allow for null!");
        }
        Jedis j = null;
        String[] skeys = new String[keys.length];
        for(int i = 0;i<keys.length;i++) {
            skeys[i] = CacheUtils.toJsonString(keys[i]);
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = client.getResource();
            j.del(skeys);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//          client.handoverToSlave();
//          remove(key);
        } finally {
            if (borrowOrOprSuccess) {
                client.returnResource(j);//返回到资源池
            }
        }
    }
    
    /**
     * <p>true为存在，false为不存在，null表示出错了</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:23:07
     * @param key
     * @return true存在，false不存在，null为服务器切换中redisPool初始中
     * @see
     */
//  public Boolean exists(final K key) {
//      if(!client.getPoolInited()) return null;
//      Jedis j = null;
//      String skey = CacheUtils.toJsonString(key);
//      if(skey == null) {
//          throw new RedisCacheStorageException("key does not allow for null!");
//        }
//      boolean borrowOrOprSuccess = true;
//      try {
//          j = client.getResource();
//          Boolean result = j.exists(skey);
//          if(result) return result;
//      } catch (JedisException e) {
//          borrowOrOprSuccess = false;
//            if (j != null) {
//                client.returnBrokenResource(j);
//            }
//            LOG.error(e);
//          client.handoverToSlave();
//          return exists(key);
//      } finally {
//            if (borrowOrOprSuccess) {
//                client.returnResource(j);
//            }
//        }
//      return false;
//  }

    /**
     * 获取key对应的数据
     * @author ningyu
     * @date 2012-10-22 下午5:22:46
     * @param cacheId
     * @param key
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    public V hget(String cacheId,K key){
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        Jedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        String re = null;
        try {
            j = client.getResource();
            re = j.hget(cacheId, skey);
            client.returnResource(j);//返回到资源池
            if(re != null) {
                if(StringUtils.isBlank(re)) {
                    //key存在，value为空串
                    throw new ValueIsBlankException("key exists, value is blank!");
                } else if(StringUtils.equalsIgnoreCase(re, "nil")) {
                    //key不存在
                    throw new KeyIsNotFoundException("key is not found!");
                } else if(StringUtils.equalsIgnoreCase(re, "null")) {
                    //key存在，value为null
                    throw new ValueIsNullException("key exists, value is null!");
                } else {
                    return (V) CacheUtils.jsonParseObject(re);
                }
            } else {
                //key不存在
                throw new KeyIsNotFoundException("key is not found!");
            }
        } catch (JedisException e) {
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            throw new RedisConnectionException(e);
//            client.handoverToSlave();
//            return hget(cacheId, key);
        }
    }
    
    /**
     * 存入数据
     * @author ningyu
     * @date 2012-10-22 下午5:22:21
     * @param cacheId
     * @param key
     * @param value
     * @return 执行成功与否
     * @see
     */
    public boolean hset(String cacheId,K key,V value){
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        Jedis j = null;
        String skey = CacheUtils.toJsonString(key);
        String svalue = CacheUtils.toJsonString(value);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = client.getResource();
            j.hset(cacheId, skey, svalue);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            return false;
//          client.handoverToSlave();
//          hset(cacheId,key,value);
        } finally {
            if (borrowOrOprSuccess) {
                client.returnResource(j);
            }
        }
        return true;
    }
    
    /**
     * 获取数据
     * @author ningyu
     * @date 2012-10-22 下午5:21:42
     * @param cacheId
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    public Map<K, V> hget(String cacheId){
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        Jedis j = null;
        try {
            j = client.getResource();
            Map<String,String> map = j.hgetAll(cacheId);
            client.returnResource(j);//返回到资源池
            Map<K, V> result = null;
            //由string转成对象
            if(map != null) {
                for(Map.Entry<String,String> entry : map.entrySet()) {
                    if(result == null) {
                        result = new HashMap<K,V>();
                    }
                    result.put((K) CacheUtils.jsonParseObject(entry.getKey()), (V) CacheUtils.jsonParseObject(entry.getValue()));
                }
                return result;
            }
        } catch (JedisException e) {
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            throw new RedisConnectionException(e);
//          client.handoverToSlave();
//          return hget(cacheId);
        }
        return null;
    }
    
    /**
     * <p>true为存在，false为不存在，null表示出错了</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:21:24
     * @param cacheId
     * @param key
     * @return true为存在，false为不存在，null为服务器切换中初始化redisPool
     * @see
     */
//  public Boolean hexists(final String cacheId, final K key) {
//      if(!client.getPoolInited()) return null;
//      if(StringUtils.isBlank(cacheId)) {
//            throw new RedisCacheStorageException("cacheId does not allow for null!");
//        }
//      Jedis j = null;
//      String skey = CacheUtils.toJsonString(key);
//      if(skey == null) {
//          throw new RedisCacheStorageException("key does not allow for null!");
//        }
//      boolean borrowOrOprSuccess = true;
//      try {
//          j = client.getResource();
//          Boolean result = j.hexists(cacheId, skey);
//          if(result) return result;
//      } catch (JedisException e) {
//          borrowOrOprSuccess = false;
//            if (j != null) {
//                client.returnBrokenResource(j);
//            }
//            LOG.error(e);
////            client.handoverToSlave();
////            return hexists(cacheId, key);
//      } finally {
//            if (borrowOrOprSuccess) {
//                client.returnResource(j);
//            }
//        }
//      return false;
//  }
    
    /**
     * 删除
     * @author ningyu
     * @date 2012-11-12 下午1:46:54
     * @param cacheId
     * @param key
     * @see
     */
    public void hremoveMulti(final String cacheId,final K ... keys) {
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        if(keys == null) {
            throw new RedisCacheStorageException("keys does not allow for null!");
        }
        Jedis j = null;
        String[] skeys = new String[keys.length];
        for(int i = 0;i<keys.length;i++) {
            skeys[i] = CacheUtils.toJsonString(keys[i]);
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = client.getResource();
            j.hdel(cacheId, skeys);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            hremove(cacheId, key);
        } finally {
            if (borrowOrOprSuccess) {
                client.returnResource(j);
            }
        }
    }
    
    /**
     * 删除
     * @author ningyu
     * @date 2012-11-12 下午1:46:54
     * @param cacheId
     * @param key
     * @see
     */
    public void hremove(final String cacheId,final K key) {
    
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        Jedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = client.getResource();
            j.hdel(cacheId, skey);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            hremove(cacheId, key);
        } finally {
            if (borrowOrOprSuccess) {
                client.returnResource(j);
            }
        }
    }
    
    public void hremove(final String cacheId) {

        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        Jedis j = null;
        try {
            j = client.getResource();
            j.del(cacheId);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            hremove(cacheId);
        } finally {
            if (borrowOrOprSuccess) {
                client.returnResource(j);
            }
        }
    }
    
    /** 
     * <p>初始化</p>
     * 
     * @author ningyu
     * @date 2012-10-20 上午10:29:53
     * @throws Exception 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
    }
    
    /**
     * 开启初始化StrongCache线程任务
     * @author ningyu
     * @date 2012-10-22 上午9:29:58
     * @see
     */
    private void retryStorage(String cacheId,Map<K,V> map) {
        if(strongTask == null) {
            strongTask = new StrongCacheTask("重试初始化Strong Cache任务",cacheId,map);
            strongTask.setDaemon(true);
            strongTask.start();
        } else if (strongTask.getState().name().equals(State.NEW.name())){
            strongTask.start();
        } else if(strongTask.getState().name().equals(State.TERMINATED.name())) {
            strongTask = new StrongCacheTask("重试初始化Strong Cache任务",cacheId,map);
            strongTask.setDaemon(true);
            strongTask.start();
        }
    }

    /**
     * <p>具体环境情况</p>
     * 1.获取storage缓存初始化状态
     * 2.需要初始化时锁定并初始化
     * 3.如果锁定不成功，稍后再试
     *  
     * @author ningyu
     * @date 2012-10-22 下午1:29:08
     * @param cacheId
     * @param map
     * @see
     */
    public void initializationStrongCache(final String cacheId, Map<K,V> map) {
        if(map == null || map.isEmpty()) {
            return;
        }
        boolean borrowOrOprSuccess = true;
        Jedis j = null;
        try {
            j = client.getResource();
            String isSuccess = j.get("DPAP.redis.strong.initialization."+cacheId);
            if(StringUtils.isBlank(isSuccess) || isSuccess.equals("false")) {//如果对应的cacheId 没有初始化过或者初始化失败
                boolean lock = getLock(j,"DPAP.redis.lock."+cacheId);
                if(lock) {
                    for(Map.Entry<K, V> entry : map.entrySet()) {
                        String key = CacheUtils.toJsonString(entry.getKey());
                        String value = CacheUtils.toJsonString(entry.getValue());
                        if(key == null || value == null) {
                            log.error("storage cache initialization error: key and value does not allow for null!");
                            j.setnx("DPAP.redis.strong.initialization."+cacheId, "false");
                            releaseLock(j,"DPAP.redis.lock."+cacheId);
                            return;
                        }
                        j.hsetnx(cacheId, key, value);
                    }
                    j.setnx("DPAP.redis.strong.initialization."+cacheId, "true");
                    releaseLock(j,"DPAP.redis.lock."+cacheId);
                } else {
                    // 稍后重试
                    retryStorage(cacheId,map);
                    return;
                }
            }
        } catch(JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
                client.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            initializationStrongCache(cacheId, map);
        } finally {
            if (borrowOrOprSuccess) {
                client.returnResource(j);
            }
        }
    }

    /**
     * 初始化Strong Cache任务
     * <p style="display:none">modifyRecord</p>
     * <p style="display:none">version:V1.0,author:ningyu,date:2012-10-22 下午1:43:11</p>
     * @author ningyu
     * @date 2012-10-22 下午1:43:11
     * @since
     * @version
     */
    class StrongCacheTask extends Thread {
        int count = 1;
        String cacheId;
        Map<K,V> map;
        public StrongCacheTask(String name,String cacheId,Map<K,V> map) {
            super(name);
            count = 1;
            this.cacheId = cacheId;
            this.map = map;
        }
        
        @SuppressWarnings("static-access")
        @Override
        public void run() {
            log.debug("初始化Strong Cache任务开始，延迟"+strongDelayTime+"后开始执行!");
            
            while(true) {
                if( map == null || map.isEmpty()) {
                    return;
                }
                
                try {
                    this.sleep(strongDelayTime);
                } catch (InterruptedException e1) {
                    log.error(e1.getMessage(),e1);
                }
                
                log.debug("第"+count+"次尝试初始化Strong Cache!");
                count++;
                boolean borrowOrOprSuccess = true;
                Jedis j = null;
                try {
                    j = client.getResource();
                    String isSuccess = j.get("DPAP.redis.strong.initialization."+cacheId);
                    if(StringUtils.isBlank(isSuccess) || isSuccess.equals("false")) {//如果对应的cacheId 没有初始化过或者初始化失败
                        boolean lock = getLock(j,"DPAP.redis.lock."+cacheId);
                        if(lock) {
                            for(Map.Entry<K, V> entry : map.entrySet()) {
                                String key = CacheUtils.toJsonString(entry.getKey());
                                String value = CacheUtils.toJsonString(entry.getValue());
                                if(key == null || value == null) {
                                    log.error("storage cache initialization error: key and value does not allow for null!");
                                    j.setnx("DPAP.redis.strong.initialization."+cacheId, "false");
                                    releaseLock(j,"DPAP.redis.lock."+cacheId);
                                    return;
                                }
                                j.hsetnx(cacheId, key, value);
                            }
                            j.setnx("DPAP.redis.strong.initialization."+cacheId, "true");
                            releaseLock(j,"DPAP.redis.lock."+cacheId);
                        } else {
                            // 稍后重试
                            continue;
                        }
                    }
                } catch(JedisConnectionException e) {
                    borrowOrOprSuccess = false;
                    if (j != null) {
                        client.returnBrokenResource(j);
                    }
                    log.error(e.getMessage(),e);
//                    client.handoverToSlave();
                    //稍后重试
                    continue;
                } finally {
                    if (borrowOrOprSuccess) {
                        client.returnResource(j);
                    }
                }
            }
        }
        
    }

    
    
    /**
     * <p>锁定，节点锁定服务器的锁定标记</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:13:13
     * @param j
     * @param lockKey
     * @return
     * @see
     */
    private boolean getLock(Jedis j,String lockKey) {
        long result = 0;
        try {
            result = j.setnx(lockKey,lockKey);
            if (result == 1) {
                return true;
            } 
        } catch (JedisConnectionException e) {
            log.error(e.getMessage(),e);
        }
        return false;
    }
    
    /**
     * <p>释放锁，戒掉释放对服务器的锁定标记</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:13:58
     * @param j
     * @param lockKey
     * @see
     */
    private void releaseLock(Jedis j,String lockKey) {
        try {
            j.del(lockKey);
        } catch(JedisException e) {
            log.error(e.getMessage(),e);
        }
    }
}
