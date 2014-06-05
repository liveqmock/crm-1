/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao;
import com.deppon.crm.module.marketing.server.service.ICustomerGroupService;
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
 * @title CustomerGroupService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */

public class CustomerGroupService implements ICustomerGroupService {
	//客户分组数据操作封装
	private ICustomerGroupDao customerGroupDao;

	/**
	 * @param customerGroupDao : set the property customerGroupDao.
	 */
	public void setCustomerGroupDao(ICustomerGroupDao customerGroupDao) {
		this.customerGroupDao = customerGroupDao;
	}
	
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupNameList(java.lang.String)
	 */
	@Override
	public List<CustomerGroup> getCustomerGroupNameList(String deptId) {
		//查询客户分组
		return customerGroupDao.getCustomerGroupNameList(deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#createCustomerGroupName(com.deppon.crm.module.marketing.shared.domain.CustomerGroup)
	 */
	@Override
	public boolean createCustomerGroupName(CustomerGroup customerGroup) {
		//创建分组
		return customerGroupDao.createCustomerNameGroup(customerGroup);
	}
	

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#updateCustomerGroupName(com.deppon.crm.module.marketing.shared.domain.CustomerGroup)
	 */
	@Override
	public boolean updateCustomerGroupName(CustomerGroup customerGroup) {
		//更新客户分组
		return customerGroupDao.updateCustomerNameGroup(customerGroup);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#deteteCustomerGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deteteCustomerGroup(String id, String deptId) {
		//删除客户分组
		return customerGroupDao.deleteCustomerGroup(id, deptId);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupCountList(java.lang.String)
	 */
	@Override
	public List<CustomerGroup> getCustomerGroupCountList(String deptId) {
		//查询部门下的客户分组列表
		return customerGroupDao.getCustomerGroupCountList(deptId);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#deleteCustomerGroupDetail(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteCustomerGroupDetail(String groupId, String deptId) {
		//删除客户分组
		return customerGroupDao.deleteCustomerGroupDetail(groupId, deptId);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupDetailList(java.lang.String)
	 */
	@Override
	public List<CustomerGroupDetail> getCustomerGroupDetailList(
			String customerGroupId,Date queryDate,String fcAnalyseType) {
		//查询客户分组明细
		return customerGroupDao.getCustomerGroupDetailList(customerGroupId,queryDate,fcAnalyseType);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#deleteCustomerGroupCustomer(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public boolean deleteCustomerGroupCustomer(
			CustomerGroupDetail customerGroupDetail) {
		//查询客户分组
		return customerGroupDao.deleteCustomerGroupCustomer(customerGroupDetail);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#updateCustomerGroupCustomer(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public boolean updateCustomerGroupCustomer(
			CustomerGroupDetail customerGroupDetail) {
		//更新客户分组
		return customerGroupDao.updateCustomerGroupCustomer(customerGroupDetail);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#createCustomerGroupCustomer(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public boolean createCustomerGroupCustomer(
			CustomerGroupDetail customerGroupDetail) {
		//创建客户分组
		return customerGroupDao.createCustomerGroupCustomer(customerGroupDetail);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupDetailMonthAmount(java.util.Map)
	 */
	@Override
	public List<CustomerGroupMonth> getCustomerGroupDetailMonthAmount(
			Map<String, Object> condition) {
		//综合查询客户分组
		return customerGroupDao.getCustomerGroupDetailMonthAmount(condition);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupDetailDayAmount(java.util.Map)
	 */
	@Override
	public List<CustomerGroupDay> getCustomerGroupDetailDayAmount(
			Map<String, Object> condition) {
		//查询分组
		return customerGroupDao.getCustomerGroupDetailDayAmount(condition);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupDetailListByCondition(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail, int, int)
	 */
	@Override
	public List<CustomerGroupDetail> getCustomerGroupDetailListByCondition(
			CustomerGroupDetail detail, int start, int limit) {
		//分页查询客户分组
		return customerGroupDao.getCustomerGroupDetailListByCondition(detail, start, limit);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupDetailListByConditionCount(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public int getCustomerGroupDetailListByConditionCount(
			CustomerGroupDetail detail) {
		//查询客户分组明细
		return customerGroupDao.getCustomerGroupDetailListByConditionCount(detail);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getPrehumanByDeptId(java.lang.String)
	 */
	@Override
	public List<Employee> getPrehumanByDeptId(String deptId) {
		//查询部门维护人
		return customerGroupDao.getPrehumanByDeptId(deptId);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getCustomerGroupByPrehuman(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getCustomerGroupByPrehuman(CustomerGroup cg) {
		//分组条件查询客户
		return customerGroupDao.getCustomerGroupByPrehuman(cg);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.ICustomerGroupService#getPrehumanByGroupId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getPrehumanByGroupId(CustomerGroup cg) {
		//根据分组ID查询维护人
		return customerGroupDao.getPrehumanByGroupId(cg);
	}
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
	@Override
	public List<CustGroupDayDetail> searchCustomerGroupDetailDayAmount(CustGroupDayDetailCondition condition){
		return customerGroupDao.searchCustomerGroupDetailDayAmount(condition);
	}
}
