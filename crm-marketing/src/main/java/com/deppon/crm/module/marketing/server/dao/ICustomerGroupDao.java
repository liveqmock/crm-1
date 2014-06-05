/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICustomerGroupDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetail;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetailCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDay;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupMonth;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title ICustomerGroupDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */

public interface ICustomerGroupDao {

	/**
	 * 
	 * <p>
	 * Description:客户分组,取名称列表<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 5, 2012
	 * @param deptId
	 * @return
	 * List<CustomerGroup>
	 */
	 List<CustomerGroup> getCustomerGroupNameList(String deptId);

	/**
	 * 
	 * <p>
	 * Description:客户分组,创建分组<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 5, 2012
	 * @param customerGroup
	 * @return
	 * boolean
	 */
	 boolean createCustomerNameGroup(CustomerGroup customerGroup);
	
	/**
	 * 
	 * <p>
	 * Description:客户分组，修改分组组名<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 5, 2012
	 * @param customerGroup
	 * @return
	 * boolean
	 */
	 boolean updateCustomerNameGroup(CustomerGroup customerGroup);
	
	/**
	 * 
	 * <p>
	 * Description:客户分组，删除分组<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 5, 2012
	 * @param id
	 * @return
	 * boolean
	 */
	 boolean deleteCustomerGroup(String id, String deptId);
	
	/**
	 * 
	 * <p>
	 * Description:客户分组，删除该分组下的客户关系<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 7, 2012
	 * @param groupId
	 * @param deptId
	 * @return
	 * boolean
	 */
	 boolean deleteCustomerGroupDetail(String groupId, String deptId);

	/**
	 * 
	 * <p>
	 * Description:客户分组，取每个分组下的客户数<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @param deptId
	 * @return
	 * List<CustomerGroup>
	 */
	 List<CustomerGroup> getCustomerGroupCountList(String deptId);	
	
	/**
	 * 
	 * <p>
	 * Description:客户分组详情，根据分组id号取详情<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @param customerGroupId
	 * @return
	 * List<CustomerGroupDetail>
	 */
	 List<CustomerGroupDetail> getCustomerGroupDetailList(String customerGroupId,Date queryDate,String fcAnalyseType);

	/**
	 * 
	 * <p>
	 * Description:保存客户分组，删除客户组关系<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 7, 2012
	 * @param customerGroupDetail
	 * @return
	 * boolean
	 */
	 boolean deleteCustomerGroupCustomer(CustomerGroupDetail customerGroupDetail);
	
	/**
	 * 
	 * <p>
	 * Description:保存客户分组，更新维护人<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 7, 2012
	 * @param customerGroupDetail
	 * @return
	 * boolean
	 */
	 boolean updateCustomerGroupCustomer(CustomerGroupDetail customerGroupDetail);
	
	/**
	 * 
	 * <p>
	 * Description:保存客户分组，添加客户组关系<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 7, 2012
	 * @param customerGroupDetail
	 * @return
	 * boolean
	 */
	 boolean createCustomerGroupCustomer(CustomerGroupDetail customerGroupDetail);
	
	/**
	 * 
	 * <p>
	 * Description:客户分组-客户列表-按月分组发货金额,当月,上月和上两月<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 12, 2012
	 * @return
	 * List<CustomerGroupMonth>
	 */
	 List<CustomerGroupMonth> getCustomerGroupDetailMonthAmount(Map<String, Object> condition);

	/**
	 * 
	 * <p>
	 * Description:客户分组-客户列表-按日分组发货金额,从1号到当天<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 12, 2012
	 * @return
	 * List<CustomerGroupDay>
	 */
	 List<CustomerGroupDay> getCustomerGroupDetailDayAmount(Map<String, Object> condition);
	

	/**
	 * 
	 * <p>
	 * Description:客户分组管理， 根据用户录入条件查询符合的客户记录<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 14, 2012
	 * @param detail
	 * @return
	 * List<CustomerGroupDetail>
	 */
	 List<CustomerGroupDetail> getCustomerGroupDetailListByCondition(CustomerGroupDetail detail, int start, int limit);

	/**
	 * 
	 * <p>
	 * Description:客户分组管理， 根据用户录入条件查询符合的客户记录总数<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 14, 2012
	 * @param detail
	 * @return
	 * int
	 */
	 int getCustomerGroupDetailListByConditionCount(CustomerGroupDetail detail);

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
	 * <p>
	 * Description: 根据分组取维护人ID<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-22
	 * @param depId
	 * @param userId
	 * @return
	 * List<String>
	 */
	 List<String> getCustomerGroupByPrehuman(CustomerGroup cg);

	/**
	 * <p>
	 * Description: 根据维护人取分组ID<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-22
	 * @param depId
	 * @param groupId
	 * @return
	 * List<String>
	 */
	 List<String> getPrehumanByGroupId(CustomerGroup cg);
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
	public List<CustGroupDayDetail> searchCustomerGroupDetailDayAmount(CustGroupDayDetailCondition condition);
}
