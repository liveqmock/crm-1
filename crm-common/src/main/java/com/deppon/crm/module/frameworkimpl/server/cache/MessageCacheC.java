package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.RefreshableCache;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;
import com.deppon.foss.framework.cache.storage.exception.KeyIsNotFoundException;
import com.deppon.foss.framework.cache.storage.exception.RedisConnectionException;
import com.deppon.foss.framework.cache.storage.exception.ValueIsBlankException;
import com.deppon.foss.framework.cache.storage.exception.ValueIsNullException;

public class MessageCacheC implements RefreshableCache<String, Map<String,Integer>>, InitializingBean, DisposableBean {
	public final static String UUID = MessageCacheC.class.getName();
	  private IBatchCacheProvider<String, Map<String,Integer>> cacheProvider;
	    
	    private RedisCacheStorage<String, Map<String,Integer>> cacheStorage;
	
	  /**
     * 自动刷新间隔时间，单位秒，默认15分钟。
     */
    private long interval = 15L * 60 * 1000;
    
    /**
     * 缓存最后一次修改时间,作为刷新缓存的时间戳
     */
    private Date modifyTime;
    
    /**
     * 自动刷新缓存线程
     */
    private ReloadThread thread;
    
    private String prefix = "DPAP.redis.strong.initialization.";
    
    public void setInterval(int seconds) {
        this.interval = (long) seconds * 1000;
    }

	
    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        CacheManager.getInstance().registerCacheProvider(this);
//        cacheStorage.initializationStrongCache(getUUID(), cacheProvider.get());
//        modifyTime = cacheProvider.getLastModifyTime();
        //启动刷新线程
        thread = new ReloadThread("STRONG_REDIS_CACHE_" + this.getUUID());
        thread.setDaemon(true);
        thread.start();
    }
    
    @Override
    public Map<String,Integer> get(String key) {
    	Map<String,Integer> value = null;
        try {
            value = cacheStorage.hget(getUUID(),key);
        } catch(ValueIsBlankException e) {
            //key存在，value为空串
            return null;
        } catch(ValueIsNullException ex) {
            //key存在，value为null
            return null;
        } catch(KeyIsNotFoundException ex1) {
            //key不存在
            return null;
        } catch(RedisConnectionException exx) {
            //redis 连接出现异常
            return null;
        }
        return value;
    }

	
	
	
	@Override
	public String getUUID() {
		return UUID;
	}
	
	
	 @Override
	    public Map<String, Map<String,Integer>> get() {
	        try {
	            return cacheStorage.hget(getUUID());
	        } catch(RedisConnectionException e) {
	            //redis 连接出现异常  
	            return cacheProvider.get();
	        }
	        
	    }
	    
	    public void setCacheProvider(IBatchCacheProvider<String, Map<String,Integer>> cacheProvider) {
	        this.cacheProvider = cacheProvider;
	    }
	    
	    public void setCacheStorage(RedisCacheStorage<String, Map<String,Integer>> cacheStorage) {
	        this.cacheStorage = cacheStorage;
	    }

	    @Override
	    public void destroy() throws Exception {
	    }
	    /**
	     * 
	     * 自动刷新缓存
	     * <p style="display:none">modifyRecord</p>
	     * <p style="display:none">version:V1.0,author:ningyu,date:2013-3-28 上午10:41:15,content: </p>
	     * @author wmm
	     * @date 2013-3-28 上午10:41:15
	     * @since
	     * @version
	     */
	    private class ReloadThread extends Thread {
	        @SuppressWarnings("unused")
			private volatile int state;

	        ReloadThread(String threadName) {
	            super(threadName);
	        }

	        @Override
	        public void run() {
	            while (true) {
	                try {
	                    state = 2;
	                    //刷新间隔时间
	                    Thread.sleep(interval);
	                } catch (InterruptedException e) {
	                    Log.error(e.getMessage());
	                    break;
	                }
	                try {
	                    state = 1;
	                    //如果最后更新时间早于当前时间
	                    //更新所有数据
	                    refresh();
	                } catch (Exception ex) {
	                    Log.error(ex.getMessage());
	                    break;
	                }
	            }
	        }   

	    }

	    /**
	     * 
	     * <p>刷新策略</p> 
	     * @author ningyu
	     * @date 2013-3-28 上午10:40:04
	     * @return 
	     * @see com.deppon.foss.framework.cache.RefreshableCache#refresh()
	     */
	    @Override
	    public boolean refresh() {
	        Date lastTime = cacheProvider.getLastModifyTime();
	        if (modifyTime==null||(modifyTime != null && lastTime != null && lastTime.after(modifyTime))) {
	            Map<String, Map<String,Integer>> map = cacheProvider.get();
	            for (Map.Entry<String, Map<String,Integer>> entry : map.entrySet()) {
	                cacheStorage.hset(getUUID(), entry.getKey(), entry.getValue());
	            }
	            modifyTime = lastTime;
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public boolean refresh(String... keys) {
	    	boolean res=true;
			for(String key:keys){
				res=refresh(key,null);
				if(!res){
					return res;
				}
			}
			return res;
	    }
	    /**
	     * 
	     * <p>refresh the special key of the cache</p> 
	     * @author Zhangzw
	     * @date 2013-11-07 上午10:40:04
	     * @return 
	     * @see com.deppon.foss.framework.cache.RefreshableCache#refresh(String key,String para)
	     * the addition para of the key
	     */
	    public boolean refresh(String key,String para) {	
	    	String tmp=key;
	    	if(null==key||"".equals(key)||""==key){
		        throw new RuntimeException(key+"the key is null!");
	    	}
	    	if(""==para||"".equals(para)||null==para){
	    		
	    	}else{
	    		key=para+key;
	    	}
	    	Map<String, Integer> mapTmp=((MessageCacheProviderC)cacheProvider).getByUserOrDept(tmp, para);
	    	synchronized(cacheStorage){
		    	cacheStorage.remove(key);
		    	cacheStorage.hset(getUUID(), key, mapTmp);
	    	}
			return false;
	    }
	    /**
	     * 
	     * <p>重新初始化缓存数据</p> 
	     * @author ningyu
	     * @date 2013-3-28 上午10:40:28 
	     * @see com.deppon.foss.framework.cache.ICache#invalid()
	     */
	    public void invalid() {
	        cacheStorage.hremove(getUUID());
	        cacheStorage.hremove(prefix + getUUID());
	        //因为是批量加载所以全部失效考虑重新加载的问题
	        cacheStorage.initializationStrongCache(getUUID(), cacheProvider.get());
	        modifyTime = cacheProvider.getLastModifyTime();
	    }
	    
	    public void invalid(String key) {
	        throw new RuntimeException("Strong Cache Cannot Invalid Part!");
	        //cacheStorage.hremove(getUUID(),key);
	    }
	    
	    public void invalidMulti(String ... keys) {
	        throw new RuntimeException("Strong Cache Cannot Invalid Part!");
	    }
	    
//	    public Boolean exists(K key) {
//	        return cacheStorage.hexists(getUUID(),key);
//	    }

}
