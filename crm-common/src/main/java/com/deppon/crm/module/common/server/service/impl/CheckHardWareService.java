package com.deppon.crm.module.common.server.service.impl;

import com.deppon.crm.module.common.server.dao.ICheckHardWareDao;
import com.deppon.crm.module.common.server.service.ICheckHardWareService;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.crm.module.frameworkimpl.server.cache.HardWareCache;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;

public class CheckHardWareService implements ICheckHardWareService {

	private ICheckHardWareDao iCheckHardWareDao = null;
	public ICheckHardWareDao getiCheckHardWareDao() {
		return iCheckHardWareDao;
	}
	public void setiCheckHardWareDao(ICheckHardWareDao iCheckHardWareDao) {
		this.iCheckHardWareDao = iCheckHardWareDao;
	}
	@Override
	public HardWareInfo getHardWareInfoByClient(HardWareInfo info) {
	    ICache<String,HardWareInfo> hardWareCache = CacheManager.getInstance().getCache(HardWareCache.class.getName());
	    HardWareInfo hardWareInfo = hardWareCache.get(info.getHostName());
	    return hardWareInfo;
	}

}
