package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.IDetailDao;
import com.deppon.crm.module.common.server.dao.IHeadDao;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;

/**
 * 功能权限缓存数据提供对象
 * 
 * @author ztjie
 * 
 */
public class DataDictionaryCacheProvider implements
		IBatchCacheProvider<String, Head> {

	private IHeadDao headDao;
	private IDetailDao detailDao;

	/**
	 * 返回两个表中最后修改时间最小的值
	 */
	public Date getLastModifyTime() {
		Date headLastModifyTime = headDao.getLastModifyTime();
		Date detailLastModifyTime = detailDao.getLastModifyTime();
		return headLastModifyTime.before(detailLastModifyTime) 
				? detailLastModifyTime : headLastModifyTime;
	}

	/**
	 * 返回最新的数据字典数据
	 */
	public Map<String, Head> get() {
		Map<String, Head> map = new HashMap<String, Head>();
		List<Head> heads = headDao.getAllHeadDetail();
		for (Head head : heads) {
			map.put(head.getCodeType(), head);
		}
		return map;
	}
	
	public Head get(String codeType){
		return headDao.getHeadByCodeType(codeType);
	}
	
	
	

	public IHeadDao getHeadDao() {
		return headDao;
	}

	public void setHeadDao(IHeadDao headDao) {
		this.headDao = headDao;
	}

	public IDetailDao getDetailDao() {
		return detailDao;
	}

	public void setDetailDao(IDetailDao detailDao) {
		this.detailDao = detailDao;
	}

}
