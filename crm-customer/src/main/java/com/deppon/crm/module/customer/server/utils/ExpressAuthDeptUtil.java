/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ExpressAuthDeptUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author pgj
 * @version 0.1 2013-9-20
 */
package com.deppon.crm.module.customer.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:快递经理获取对应营业部权限工具类<br />
 * </p>
 * @title ExpressAuthDeptUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author pgj
 * @version 0.1 2013-9-20
 */

public class ExpressAuthDeptUtil {
	public  static List<String> getAuthDept(List<Department> departs){
		List<String> deptIds = new ArrayList<String>();
		if (isPointManager(ContextUtil.getCurrentUserRoleIds())) {
			if (!CollectionUtils.isEmpty(departs)) {
				for (Department dept : departs) {
					deptIds.add(dept.getId());
				}
			}
		}
		return deptIds;
	}
	
	public static boolean isPointManager(Set<String> roleIds){
		return roleIds.contains("10");
	}
	
	public static boolean isDeptManager(Set<String> roleIds){
		return roleIds.contains("1004");
	}
	
}	
