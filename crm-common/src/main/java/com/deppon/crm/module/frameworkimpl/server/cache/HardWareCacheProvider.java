package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.ICheckHardWareDao;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;

public class HardWareCacheProvider implements IBatchCacheProvider<String, HardWareInfo> {

    private ICheckHardWareDao checkHardWareDao = null;
    
    /**
     * 得到缓存最后一次修改时间,作为刷新缓存的时间戳
     */
    @Override
    public Date getLastModifyTime() {
        return checkHardWareDao.getLastModifyTime();
    }
    
    /**
     * 得到所有的硬件信息
     */
    @Override
    public Map<String, HardWareInfo> get() {
        Map<String, HardWareInfo> map = new HashMap<String, HardWareInfo>();
        //查询出所有的硬件信息
        List<HardWareInfo> list = checkHardWareDao.getAllHardWareInfo();
        //把硬件信息放入map中(键:主机名,值:硬件信息)
        for (HardWareInfo hardWareInfo : list) {
            map.put(hardWareInfo.getHostName(), hardWareInfo);
        }
        return map;
    }
    
       /**
         * 
    	 * 
    	 * <p>
    	 * Description:根据hostName 查询硬件信息<br />
    	 * </p>
    	 * @author 王明明
    	 * @version 0.1 2013-7-30
         *@param hostName
         *@return
    	 * HardWareInfo
     */
    public HardWareInfo get(String hostName){
    	HardWareInfo hardWareInfo = new HardWareInfo();
    	hardWareInfo.setHostName(hostName);
    	return checkHardWareDao.getHardWareInfo(hardWareInfo);
    }

    public ICheckHardWareDao getCheckHardWareDao() {
        return checkHardWareDao;
    }

    public void setCheckHardWareDao(ICheckHardWareDao checkHardWareDao) {
        this.checkHardWareDao = checkHardWareDao;
    }

}
