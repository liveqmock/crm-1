/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhuPJ
 * @version 0.1 2012-6-30
 */
package com.deppon.crm.module.marketing.server.utils;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.exception.MapException;
import com.deppon.crm.module.marketing.shared.exception.MapExceptionType;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhuPJ
 * @version 0.1 2012-6-30
 */

public class MapValidateUtils {

	/**
	 * 
	 * <p>
	 * 批量标注客户<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param customerLocationList
	 * @return
	 * boolean
	 */
	// 验证客户信息是否
	public static boolean canSaveLocationInfo(List<CustomerLocation> customerLocationList) {
		//循环校验客户列表
		for (CustomerLocation c : customerLocationList) {
			//验证合法性
			if (c == null || !c.validate()) {
				//不合法异常
				throw new MapException(MapExceptionType.markInfoInvalid);
			}
		}
		return true;
	}
}
