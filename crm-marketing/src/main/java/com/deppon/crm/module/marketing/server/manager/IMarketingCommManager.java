/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IMarketingComm.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-7-3
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description: 营销模块-公共接口（供前台下拉框使用）<br />
 * </p>
 * @title IMarketingComm.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-7-3
 */

public interface IMarketingCommManager {
	/**
	 * <p>
	 * Description: 根据分组维护人取ID<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-22
	 * @param depId
	 * @param userId
	 * @return
	 * List<String>
	 */
	 List<String> getCustomerGroupByPrehuman(String depId, String userId);

	/**
	 * <p>
	 * Description: 根据分组取维护人ID<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-22
	 * @param depId
	 * @param groupId
	 * @return
	 * List<String>
	 */
	 List<String> getPrehumanByGroupId(String depId, String groupId);
	
	/**
	 * <p>
	 * Description: 根据部门ID获取相应计划创建人<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-3
	 * @param user
	 * @param deptId
	 * @param planType
	 * @return
	 * List<Employee>
	 */
	 List<Employee> getPlanCreatorList(User user, String deptId, String planType);
}
