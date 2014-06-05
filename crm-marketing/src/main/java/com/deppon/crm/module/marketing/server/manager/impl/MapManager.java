/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.marketing.server.manager.IMapManager;
import com.deppon.crm.module.marketing.server.service.IMapService;
import com.deppon.crm.module.marketing.server.utils.MapValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.MapView;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * Description:五公里地图<br />
 * </p>
 * 
 * @title MapManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */

public class MapManager implements IMapManager {
	/**
	 * 地图service方法封装
	 */
	private IMapService mapService;

	/**
	 * @param mapService
	 *            : set the property mapService.
	 */
	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.marketing.server.manager.IMapManager#getCustomerList
	 * (com.deppon.crm.module.marketing.shared.domain.CustomerQueryCondition,
	 * int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getCustomerList(MapQueryCondition condition,
			int start, int limit, User user) {
		// set deptId
		if (StringUtils.isBlank(condition.getDeptId())) {
			// User user = (User) UserContext.getCurrentUser();
			condition.setDeptId(user.getEmpCode().getDeptId().getId());
		}
		// approximate customerName & linkmanName
		if (StringUtils.isNotBlank(condition.getCustomerName())) {
			condition.setCustomerName("%" + condition.getCustomerName() + "%");
		}
		//构造模糊查询字符串
		if (StringUtils.isNotBlank(condition.getLinkmanName())) {
			condition.setLinkmanName("%" + condition.getLinkmanName() + "%");
		}
		//数据字典cache
		ICache<String, Head> dataDictionaryCache = CacheManager.getInstance()
				.getCache(Head.class.getName());
		//缓存Key
		Head keyHead = dataDictionaryCache.get("TRADE");
		//缓存内容
		List<Detail> detailList = keyHead.getDetailList();
		//封装用户视图
		List<MapView> mapViewList = mapService.searchUsers(condition, start,
				limit);
		//封装查询结果到页面展示
		for (MapView mapView : mapViewList) {
			for (Detail detail : detailList) {
				if (StringUtils.equals(mapView.getIndustry(), detail.getCode())) {
					//描述
					mapView.setIndustryDesc(detail.getCodeDesc());
				}
			}
		}
		//查询总数
		int count = mapService.searchUsersCount(condition);
		//封装结果
		Map<String, Object> result = new HashMap<String, Object>();
		//封装结果
		result.put("data", mapViewList);
		result.put("count", count);

		return result;
	}

	/**
	 * 批量标记客户位置
	 * @see com.deppon.crm.module.marketing.server.manager.IMapManager#
	 * markCustomerLocation
	 * (com.deppon.crm.module.marketing.shared.domain.CustomerLocation)
	 */
	@SuppressWarnings("serial")
	@Override
	@Transactional
	public List<Boolean> markCustomerLocation(
			List<CustomerLocation> customerLocationList,User user) {
		try {
			List<Boolean> result = new ArrayList<Boolean>();
			//验证list元素是否合法
			if (MapValidateUtils.canSaveLocationInfo(customerLocationList)){
				//循环list
				for (CustomerLocation c : customerLocationList) {
					boolean bool;
					//判断标记是否存在
					if (mapService.isCustomerLocationExist(c)) {
						c.setModifyUser(user.getEmpCode().getId());
						c.setModifyDate(new Date());
						//更新标记
						bool = mapService.updateCustomerLocation(c);
					} else {
						//创建日期
						c.setCreateDate(new Date());
						//创建人
						c.setCreateUser(user.getEmpCode().getId());
						//修改人
						c.setModifyUser(user.getEmpCode().getId());
						//修改日期
						c.setModifyDate(new Date());
						// 新增标记
						bool = mapService.insertCustomerLocation(c);
					}
					//添加结果
					result.add(bool);
				}
				
			}
			//返回结果
			return result;
		} catch (BusinessException e) {
			//异常处理
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}
	
}

