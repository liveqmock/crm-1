/**   
 * Description:这里写描述<br />
 * @title AreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.service.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IAbCityDao;
import com.deppon.crm.module.common.server.service.IAbCityService;
import com.deppon.crm.module.common.shared.domain.AbCity;
import com.deppon.crm.module.common.shared.domain.AbCitySearchCondition;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AbCityService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author ouy
 * @version 0.1 2012-5-8
 */

public class AbCityService implements IAbCityService {
	private IAbCityDao abCityDao;
	@Override
	public List<AbCity> getAbCity(AbCitySearchCondition condition) {
		// TODO Auto-generated method stub
		return abCityDao.getAbCity(condition);
	}

	@Override
	public Long getAbCityCount(AbCitySearchCondition condition) {
		// TODO Auto-generated method stub
		return abCityDao.getAbCityCount(condition);
	}

	public IAbCityDao getAbCityDao() {
		return abCityDao;
	}

	public void setAbCityDao(IAbCityDao abCityDao) {
		this.abCityDao = abCityDao;
	}

}
