/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PropServiceImpl.java
 * @package com.deppon.crm.module.frameworkimpl.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.deppon.crm.module.frameworkimpl.server.dao.IPropInfoDao;
import com.deppon.crm.module.frameworkimpl.server.service.IPropInfoService;
import com.deppon.crm.module.frameworkimpl.shared.domain.PropInfo;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PropServiceImpl.java
 * @package com.deppon.crm.module.frameworkimpl.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public class PropInfoServiceImpl implements IPropInfoService {
	private IPropInfoDao propInfoDao;

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.frameworkimpl.server.service.IPropService#getPropInfo()
	 */
	@Override
	public Map<String, String> getPropInfo() {
		List<PropInfo> piList = propInfoDao.getPropInfo();
		HashMap<String, String> map = new HashMap<String, String>();
		for (PropInfo pi : piList){
			if (StringUtils.isEmpty(pi.getKey())){
				continue;
			}
			// 配置信息为空时，默认为“”
			String value = StringUtils.isEmpty(pi.getValue()) ? "" : pi.getValue();
			map.put(pi.getKey(), value);
		}
		return map;
	}

	/**
	 * @return propDao : return the property propDao.
	 */
	public IPropInfoDao getPropInfoDao() {
		return propInfoDao;
	}

	/**
	 * @param propDao : set the property propDao.
	 */
	public void setPropInfoDao(IPropInfoDao propInfoDao) {
		this.propInfoDao = propInfoDao;
	}

}
