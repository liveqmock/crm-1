/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IAreaDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AbCity;
import com.deppon.crm.module.common.shared.domain.AbCitySearchCondition;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IAccountBankDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ouy
 * @version 0.1 2012-3-30
 */

public interface IAbCityDao {
	/**
	 * 
	 * <p>
	 * Description:查询AbCity<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-8
	 * @param conditon
	 * @return
	 * List<AbCity>
	 */
	public List<AbCity> getAbCity(AbCitySearchCondition conditon);
	/**
	 * 
	 * <p>
	 * Description:记录总数<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-8
	 * @param conditon
	 * @return
	 * Integer
	 */
	public Long getAbCityCount(AbCitySearchCondition conditon);
}
