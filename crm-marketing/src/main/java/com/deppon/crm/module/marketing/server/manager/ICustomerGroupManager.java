/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICustomerGroupManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetail;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetailCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title ICustomerGroupManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */

public interface ICustomerGroupManager {

	/**
	 * 
	 * <p>
	 * Description:在本部门中创建分组<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @param groupName
	 * @return
	 * boolean
	 */
	 boolean createCustomerGroup(String groupName, User user);
	
	/**
	 * 
	 * <p>
	 * Description:在本部门中更新组名<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @param groupName
	 * @param id
	 * @return
	 * boolean
	 */
	 boolean updateCustomerGroup(String groupName, String id, User user);
	
	/**
	 * 
	 * <p>
	 * Description:在本部门中删除分组<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @param id
	 * @return
	 * boolean
	 */
	 boolean deleteCustomerGroup(String id, User user);
	
	/**
	 * 
	 * <p>
	 * Description:取本部门的分组列表，只取本部门的组名列表（非所有权限列表，3.5和朱培军确认）<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @return
	 * List<CustomerGroup>
	 */
	 List<CustomerGroup> getCustomerGroupList(String deptId);

	/**
	 * <p>
	 * Description: 根据部门ID 取得所有维护人<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-22
	 * @param deptId
	 * @return
	 * List<Employee>
	 */
	 List<Employee> getPrehumanByDeptId(String deptId);
	
	/**
	 * 
	 * <p>
	 * Description:客户分组，保存客户分组关系<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 7, 2012
	 * @param groupId
	 * @param deletedList
	 * @param updatedList
	 * @param createdList
	 * @return
	 * boolean
	 */
	 boolean saveCustomerGroup(String groupId, List<CustomerGroupDetail> deletedList, List<CustomerGroupDetail> updatedList, List<CustomerGroupDetail> createdList, User user);

	/**
	 * 
	 * <p>
	 * Description:客户分组，点击管理某个分组后，展示该分组下的具体客户信息<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 14, 2012
	 * @param groupId 分组Id，不可为null
	 * @param queryDate 可以传null值，null表示查询当前时间
	 * @return
	 * List<CustomerGroupDetail>
	 */
	 List<CustomerGroupDetail> getCustomerGroupDetailList(String groupId, Date queryDate, String fcAnalyseType);

	/**
	 * 
	 * <p>
	 * Description:客户分组，点击管理某个分组后，按用户录入的搜索条件，搜索符合条件的用户信息<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 14, 2012
	 * @param detail
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,Object>
	 */
	 Map<String, Object> getCustomerGroupDetailListByCondition(CustomerGroupDetail detail, int start, int limit);
	
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
	 * 
	 * <p>
	 * 查询发到货周期表的客户详情<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-11-26
	 * @param condition
	 * @return List<CustGroupDayDetail>
	 */
	 List<CustGroupDayDetail> searchCustomerGroupDetailDayAmount(CustGroupDayDetailCondition condition);
}
