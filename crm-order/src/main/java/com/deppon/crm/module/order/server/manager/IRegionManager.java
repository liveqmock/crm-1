/**   
 * @title IRegionManager.java
 * @package com.deppon.crm.module.order.server.manager
 * @description what to do
 * @author 潘光均
 * @update 2012-3-15 下午3:39:30
 * @version V1.0   
 */
package com.deppon.crm.module.order.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Region;

/**
 * @description fuction  
 * @author 潘光均
 * @version 0.1 2012-3-15
 *@date 2012-3-15
 */

public interface IRegionManager {

	/**
	 * @description function.  
	 * @author 潘光均
	 * @version 0.1 2012-3-15
	 * @param b true or false   
	 *@date 2012-3-15
	 * @return none
	 * @update 2012-3-15 下午3:41:09
	 */
	List<Region> getProvinces();

	/**
	 * @description function.  
	 * @author 潘光均
	 * @version 0.1 2012-3-15
	 * @param b true or false   
	 *@date 2012-3-15
	 * @return none
	 * @update 2012-3-15 下午3:41:25
	 */
	List<Region> searchRegionByParentCode(String parentCode);

}
