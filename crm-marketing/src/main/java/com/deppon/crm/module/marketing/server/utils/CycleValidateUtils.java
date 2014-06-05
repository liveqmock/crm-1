/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author Administrator
 * @version 0.1 2012-4-14
 */
package com.deppon.crm.module.marketing.server.utils;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title CycleValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author 苏玉军
 * @version 0.1 2012-4-14
 */

public class CycleValidateUtils {
	/**
	 * 
	 * <p>
	 * 判断分组条件是否为空<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-25
	 * @param detail
	 * @return
	 * boolean
	 */
	public static boolean isConditonNull(CustomerGroupDetail detail) {
		//分组明细为空
		return detail == null
				|| StringUtils.isEmpty(detail.getDeptId()) ||(
						detail.getQueryDate() == null
						//客户名是否为空
						&& StringUtils.isEmpty(detail.getCustNumber())
						//分组是否为空
						&& StringUtils.isEmpty(detail.getGroupId())
						//维护人是否为空
						&& StringUtils.isEmpty(detail.getEmpName()));
	}
}
