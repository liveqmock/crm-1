/**   
 * Description:这里写描述<br />
 * @title AreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.manager.IAbCityManager;
import com.deppon.crm.module.common.server.service.IAbCityService;
import com.deppon.crm.module.common.shared.domain.AbCity;
import com.deppon.crm.module.common.shared.domain.AbCitySearchCondition;
/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AbCityManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author ouy
 * @version 0.1 2012-5-8
 */
public class AbCityManager implements IAbCityManager {
	private IAbCityService abCityService;
	@Override
	public Map<String, Object> selectAbCity(AbCitySearchCondition condition) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		List<AbCity> abCityList=abCityService.getAbCity(condition);
		map.put("abCityList", abCityList);
		Long abCityCount=abCityService.getAbCityCount(condition);
		map.put("abCityCount", abCityCount);
		return map;
	}
	public IAbCityService getAbCityService() {
		return abCityService;
	}
	public void setAbCityService(IAbCityService abCityService) {
		this.abCityService = abCityService;
	}
	
}