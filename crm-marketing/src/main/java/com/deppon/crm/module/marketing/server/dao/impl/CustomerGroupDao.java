/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetail;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetailCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDay;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupMonth;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title CustomerGroupDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */

public class CustomerGroupDao extends iBatis3DaoImpl implements ICustomerGroupDao {
	//namespace
	private static final String NAMESPACE_CUSTOMERGROUP="com.deppon.crm.module.marketing.shared.domain.CustomerGroup.";
    //查询分组名
	private static final String GETCUSTOMERGROUPNAMELIST="getCustomerGroupNameList";
	//查询分组总数
	private static final String GETCUSTOMERGROUPCOUNTLIST="getCustomerGroupCountList";
	//创建分组名称
	private static final String CREATECUSTOMERGROUPNAME = "createCustomerGroupName";
	//更新分组名
	private static final String UPDATECUSTOMERGROUPNAME = "updateCustomerGroupName";
	//删除分组
	private static final String DELETECUSTOMERGROUP = "deleteCustomerGroup";
	//删除分组明细
	private static final String DELETECUSTOMERGROUPDETAIL = "deleteCustomerGroupDetail";
	//查询分组明细
	private static final String GETCUSTOMERGROUPDETAILLIST="getCustomerGroupDetailList";
	//通过条件查询分组明细
	private static final String GETCUSTOMERGROUPDETAILLISTBYCONDITION="getCustomerGroupDetailListByCondition";
	//条件计数
	private static final String GETCUSTOMERGROUPDETAILLISTBYCONDITIONCOUNT="getCustomerGroupDetailListByConditionCount";

	//查询分组明细月数量
	private static final String GETCUSTOMERGROUPDETAILMONTHAMOUNT="getCustomerGroupDetailMonthAmount";
	//发货周期
	private static final String GETCUSTOMERGROUPDETAILDAYAMOUNT="getCustomerGroupDetailDayAmount";
	//创建分组客户
	private static final String CREATECUSTOMERGROUPCUSTOMER = "createCustomerGroupCustomer";
	//更新分组客户
	private static final String UPDATECUSTOMERGROUPCUSTOMER = "updateCustomerGroupCustomer";
	//删除分组客户
	private static final String DELETECUSTOMERGROUPCUSTOMER = "deleteCustomerGroupCustomer";
	//获取维护人部门id
	private static final String GETPREHUMANBYDEPTID = "getPrehumanByDeptId";
	//通过维护人查找客户分组
	private static final String GETCUSTOMERGROUPBYPREHUMAN = "getCustomerGroupByPrehuman";
	//通过维护人查找客户ID
	private static final String GETPREHUMANBYGROUPID = "getPrehumanByGroupId";
	//查询客户分组详情
	private static final String SEARCHCUSTOMERGROUPDETAILDAYAMOUNT= "searchCustomerGroupDetailDayAmount";
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroup> getCustomerGroupNameList(String deptId) {
		//查询
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPNAMELIST, deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#createCustomerGroup(com.deppon.crm.module.marketing.shared.domain.CustomerGroup)
	 */
	@Override
	public boolean createCustomerNameGroup(CustomerGroup customerGroup) {
		//查询
		return getSqlSession().insert(NAMESPACE_CUSTOMERGROUP + CREATECUSTOMERGROUPNAME, customerGroup) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#updateCustomerGroup(com.deppon.crm.module.marketing.shared.domain.CustomerGroup)
	 */
	@Override
	public boolean updateCustomerNameGroup(CustomerGroup customerGroup) {
		//查询
		return getSqlSession().update(NAMESPACE_CUSTOMERGROUP + UPDATECUSTOMERGROUPNAME, customerGroup) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#deleteCustomerGroup(java.lang.String)
	 */
	@Override
	public boolean deleteCustomerGroup(String id, String deptId) {
		//查询参数
		Map<String, String> map = new HashMap<String, String>();
		//id
		map.put("id", id);
		//deptid
		map.put("deptId", deptId);
		//删除
		return getSqlSession().delete(NAMESPACE_CUSTOMERGROUP + DELETECUSTOMERGROUP, map) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupCountList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroup> getCustomerGroupCountList(String deptId) {
		//查询
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPCOUNTLIST, deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupDetailList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroupDetail> getCustomerGroupDetailList(
			String customerGroupId,Date queryDate,String fcAnalyseType) {
		//查询参数
		Map<String,Object> map = new HashMap<String, Object>();
		//分组ID
		map.put("customerGroupId", customerGroupId);
		//查询日期
		map.put("queryDate", queryDate);
		//分析类型
		map.put("fcAnalyseType", fcAnalyseType);
		//查询
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPDETAILLIST, map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#deleteCustomerGroupDetail(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteCustomerGroupDetail(String groupId, String deptId) {
		//查询参数
		Map<String, String> map = new HashMap<String, String>();
		//分组ID
		map.put("groupId", groupId);
		//部门ID
		map.put("deptId", deptId);
		//查询
		return getSqlSession().delete(NAMESPACE_CUSTOMERGROUP + DELETECUSTOMERGROUPDETAIL, map) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#deleteCustomerGroupCustomer(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public boolean deleteCustomerGroupCustomer(
			CustomerGroupDetail customerGroupDetail) {
		//查询
		return getSqlSession().delete(NAMESPACE_CUSTOMERGROUP + DELETECUSTOMERGROUPCUSTOMER, customerGroupDetail) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#updateCustomerGroupCustomer(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public boolean updateCustomerGroupCustomer(
			CustomerGroupDetail customerGroupDetail) {
		//更新
		return getSqlSession().update(NAMESPACE_CUSTOMERGROUP + UPDATECUSTOMERGROUPCUSTOMER, customerGroupDetail) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#createCustomerGroupCustomer(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public boolean createCustomerGroupCustomer(
			CustomerGroupDetail customerGroupDetail) {
		//新增
		return getSqlSession().insert(NAMESPACE_CUSTOMERGROUP + CREATECUSTOMERGROUPCUSTOMER, customerGroupDetail) > 0; 
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupDetailMonthAmount(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroupMonth> getCustomerGroupDetailMonthAmount(
			Map<String, Object> condition) {
		//查询
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPDETAILMONTHAMOUNT, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupDetailDayAmount(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroupDay> getCustomerGroupDetailDayAmount(
			Map<String, Object> condition) {
		//查询
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPDETAILDAYAMOUNT, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupDetailListByCondition(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroupDetail> getCustomerGroupDetailListByCondition(
			CustomerGroupDetail detail, int start, int limit) {
		//分页
		RowBounds rowBounds = new RowBounds(start, limit);
		//查询
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPDETAILLISTBYCONDITION, detail, rowBounds); 
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupDetailListByConditionCount(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)
	 */
	@Override
	public int getCustomerGroupDetailListByConditionCount(
			CustomerGroupDetail detail) {
		//查询
		return (Integer) getSqlSession().selectOne(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPDETAILLISTBYCONDITIONCOUNT, detail);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getPrehumanByDeptId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getPrehumanByDeptId(String deptId) {
		// 根据部门获取部门下所有维护人
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETPREHUMANBYDEPTID, deptId); 
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getCustomerGroupByPrehuman(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCustomerGroupByPrehuman(CustomerGroup cg) {
		// 根据维护人获取部门下分组
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETCUSTOMERGROUPBYPREHUMAN, cg); 
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao#getPrehumanByGroupId(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPrehumanByGroupId(CustomerGroup cg) {
		// 根据分组获取部门下分组
		return getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP + GETPREHUMANBYGROUPID, cg); 
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
		return (List<CustGroupDayDetail>)getSqlSession().selectList(NAMESPACE_CUSTOMERGROUP+SEARCHCUSTOMERGROUPDETAILDAYAMOUNT,condition);
	}
}
