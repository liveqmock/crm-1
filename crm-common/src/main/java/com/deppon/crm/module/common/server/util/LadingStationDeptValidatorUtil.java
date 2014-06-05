/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LadingStationDeptValidatorUtil.java
 * @package com.deppon.crm.module.common.server.util 
 * @author ZhuPJ
 * @version 0.1 2012-6-19
 */
package com.deppon.crm.module.common.server.util;

import com.deppon.crm.module.common.shared.exception.LadingStationDepartmentException;
import com.deppon.crm.module.common.shared.exception.LadingStationDepartmentExceptionType;

/**   
 * <p>
 * Description: 受理部门信息验证类<br />
 * </p>
 * @title LadingStationDeptValidatorUtil.java
 * @package com.deppon.crm.module.common.server.util 
 * @author ZhuPJ
 * @version 0.1 2012-6-19
 */

public class LadingStationDeptValidatorUtil {
	public static boolean canSaveLadingstationDepartment(int exists){
		if (exists > 0){
			throw new LadingStationDepartmentException(LadingStationDepartmentExceptionType.LADINGSTATION_EXISTS);
		}
		return true;
	}
}
