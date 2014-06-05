/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager;
import com.deppon.crm.module.marketing.server.service.ICustomerGroupService;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetail;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetailCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDay;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.exception.CustomerGroupException;
import com.deppon.crm.module.marketing.shared.exception.CustomerGroupExceptionType;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title CustomerGroupManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */

public class CustomerGroupManager implements ICustomerGroupManager {
	//分组操作service bean
	private ICustomerGroupService customerGroupService;

	/**
	 * @param customerGroupService : set the property customerGroupService.
	 */
	public void setCustomerGroupService(ICustomerGroupService customerGroupService) {
		this.customerGroupService = customerGroupService;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#createCustomerGroup(java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Override
	@Transactional
	public boolean createCustomerGroup(String groupName, User user) {
		// check parameter
		if (StringUtils.isBlank(groupName)) {
			return false;
		}
		
		// get customerGroup list in user's dept
		String deptId = user.getEmpCode().getDeptId().getId();
		//查询分组
		List<CustomerGroup> groups = customerGroupService.getCustomerGroupNameList(deptId);

		// create CustomerGroup Object
		Date now = new Date();
		//声明对象
		CustomerGroup group = new CustomerGroup();
		//设置分组名称
		group.setGroupName(groupName);
		//设置部门ID
		group.setDeptId(deptId);
		//设置创建人
		group.setCreateUser(user.getEmpCode().getId());
		//设置创建日期
		group.setCreateDate(now);
		//修改人
		group.setModifyUser(user.getEmpCode().getId());
		//修改时间
		group.setModifyDate(now);
		
		// check groupName
		if (group.isExistedInDept(groups)) {
			//客户分组名存在异常
			CustomerGroupException e = new CustomerGroupException(CustomerGroupExceptionType.groupNameAlreadyExistedInDept);
			//抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,	e.getErrorArguments()) {};
		}

		// create group
		return customerGroupService.createCustomerGroupName(group);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#updateCustomerGroup(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Override
	@Transactional
	public boolean updateCustomerGroup(String groupName, String id, User user) {
		// check parameter
		if (StringUtils.isBlank(groupName) || StringUtils.isBlank(id)) {
			return false;
		}
		// 组名不能超过20
		if (groupName.length() > 20) {
			//组名称太长
			CustomerGroupException e = new CustomerGroupException(CustomerGroupExceptionType.groupNameMaxLimit);
			//异常转换
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,	e.getErrorArguments()) {};
		}
		

		// get customerGroup list in user's dept
		String deptId = user.getEmpCode().getDeptId().getId();
		//查询客户分组
		List<CustomerGroup> groups = customerGroupService.getCustomerGroupNameList(deptId);

		// create CustomerGroup Object
		Date now = new Date();
		//新建分组对象
		CustomerGroup group = new CustomerGroup();
		//组名
		group.setGroupName(groupName);
		//部门id
		group.setDeptId(deptId);
		//修改人
		group.setModifyUser(user.getEmpCode().getId());
		//修改时间
		group.setModifyDate(now);
		//id
		group.setId(id);
		
		// check groupName
		if (group.isExistedInDept(groups)) {
			//组名已存在
			CustomerGroupException e = new CustomerGroupException(CustomerGroupExceptionType.groupNameAlreadyExistedInDept);
			//异常转换，抛出
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,	e.getErrorArguments()) {};
		}
		
		// update group
		return customerGroupService.updateCustomerGroupName(group);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#deleteCustomerGroup(java.lang.String)
	 */
	@Override
	@Transactional
	public boolean deleteCustomerGroup(String id, User user) {
		// check parameter
		if (StringUtils.isBlank(id)) {
			return false;
		}
		// get deptId
		String deptId = user.getEmpCode().getDeptId().getId();

		// delete customerGroup
		customerGroupService.deleteCustomerGroupDetail(id, deptId);
		// return execute result
		return customerGroupService.deteteCustomerGroup(id, deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#getCustomerGroupList()
	 */
	@Override
	public List<CustomerGroup> getCustomerGroupList(String deptId) {
		// get group name & count list
		List<CustomerGroup> names = customerGroupService.getCustomerGroupNameList(deptId);
		//count
		List<CustomerGroup> counts = customerGroupService.getCustomerGroupCountList(deptId);

		// set counts
		for (CustomerGroup group : names) {
			//循环处理
			for (CustomerGroup temp : counts) {
				//id相等
				if (StringUtils.equals(group.getId(), temp.getId())) {
					//设置
					group.setCount(temp.getCount());
				}
			}
		}
		
		return names;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#saveCustomerGroup(java.lang.String, java.util.List, java.util.List, java.util.List)
	 */
	@SuppressWarnings("serial")
	@Override
	@Transactional
	public boolean saveCustomerGroup(String groupId,
			List<CustomerGroupDetail> deletedList,
			List<CustomerGroupDetail> updatedList,
			List<CustomerGroupDetail> createdList, User user) {
		// check parameter
		if (StringUtils.isBlank(groupId)) {
			return false;
		}
		// init data
		String operatorId = user.getEmpCode().getId();
		// dept id
		String deptId = user.getEmpCode().getDeptId().getId();
		//当前时间
		Date now = new Date();
		//执行结果
		boolean response = true;
		
		// 计算该分组原有的用户数
		int count = 0;
		//查询分组
		List<CustomerGroup> counts = customerGroupService.getCustomerGroupCountList(deptId);
		for (CustomerGroup c : counts) {
			//分组id与查询结果相同
			if (StringUtils.equals(groupId, c.getId())) {
				//设置组内客户数
				count = c.getCount();
			}
		}
		//删除list长度
		int deletedListCount = deletedList == null ? 0 : deletedList.size();
		//创建长度
		int createdListCount = createdList == null ? 0 : createdList.size();
		// 如果原有数据+新增数据-删除数据 超过 50，则抛出异常
		if (count + createdListCount - deletedListCount > MarketingConstance.GROUPCUSTOMER_MAX) {
			//超过最大长度
			CustomerGroupException e = new CustomerGroupException(CustomerGroupExceptionType.groupCustomerMaxLimit);
			//抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,	e.getErrorArguments()) {};
		}
		
		
		// delete data
		if (CollectionUtils.isNotEmpty(deletedList)) {
			//循环删除
			for (CustomerGroupDetail detail : deletedList) {
				//分组id
				detail.setGroupId(groupId);
				//部门id
				detail.setDeptId(deptId);
				//删除客户分组
				boolean result = customerGroupService.deleteCustomerGroupCustomer(detail);
				//返回结果
				response = result && response;
			}
		}
		
		// update data
		if (CollectionUtils.isNotEmpty(updatedList)) {
			//循环更新
			for (CustomerGroupDetail detail : updatedList) {
				//分组id
				detail.setGroupId(groupId);
				//部门ID
				detail.setDeptId(deptId);
				//修改时间
				detail.setModifyDate(now);
				//修改人
				detail.setModifyUser(operatorId);
				//执行结果
				boolean result = customerGroupService.updateCustomerGroupCustomer(detail);
				//结果
				response = result && response;
			}
		}
		
		// create data
		if (CollectionUtils.isNotEmpty(createdList)) {
			//创建 分组
			for (CustomerGroupDetail detail : createdList) {
				//分组ID
				detail.setGroupId(groupId);
				//部门ID
				detail.setDeptId(deptId);
				//创建日期
				detail.setCreateDate(now);
				//创建人
				detail.setCreateUser(operatorId);
				//修改时间
				detail.setModifyDate(now);
				//修改人
				detail.setModifyUser(operatorId);
				// 确保一个用户只属于一个组，因此在新增用户和组关系前，先执行删除操作
				customerGroupService.deleteCustomerGroupCustomer(detail);
				// 执行新增用户和组关系操作
				boolean result = customerGroupService.createCustomerGroupCustomer(detail);
				response = result && response;
			}
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#getCustomerGroupDetailList(java.lang.String)
	 */
	@Override
	public List<CustomerGroupDetail> getCustomerGroupDetailList(String groupId, Date queryDate, String fcAnalyseType) {
		// 默认查询发货
		if (StringUtils.isBlank(fcAnalyseType)) {
			//发货参数
			fcAnalyseType = "1";
		}
		//查询发货
		return customerGroupService.getCustomerGroupDetailList(groupId,queryDate,fcAnalyseType);
//		//数据转换
//		return fillCustomerGroupDetailAmount(details, queryDate, fcAnalyseType);
	}
	/**
	 * 
	 * <p>
	 * 填充空的金额<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-31
	 * @param date
	 * @return
	 * List<String>
	 */
	private List<String> fillEmptyDayAmount(int date) {
		//日金额数组
		List<String> dayAmounts = new ArrayList<String> ();
		//循环日期
		for (int i = 1; i <= date; i++) {
			//填充0
			dayAmounts.add("0");
		}
		//返回金额数组
		return dayAmounts;
	}
	/**
	 * <p>
	 * Description: 设置天数信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-9-26
	 * @param details
	 * @param date
	 * @return
	 * List<CustomerGroupDetail>
	 */
	private List<CustomerGroupDetail> fillEmptyDayAmountBatch(List<CustomerGroupDetail> details, int date) {
		//循环设置
		for (CustomerGroupDetail detail : details) {
			//日金额为空
			if (CollectionUtils.isEmpty(detail.getDayAmount())) {
				//填充
				detail.setDayAmount(fillEmptyDayAmount(date));
			}
		}
		return details;
	}
	
	/**
	 * 
	 * <p>
	 * Description:客户分组, 填充CustomerGroupDetail里的月和日金额信息<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 14, 2012
	 * @param details
	 * @return
	 * List<CustomerGroupDetail>
	 */
	private List<CustomerGroupDetail> fillCustomerGroupDetailAmount(List<CustomerGroupDetail> details, Date queryDate, String fcAnalyseType) {
		// 取时间参数，当前月，上个月，上上个月和日期等
		// 取当前年，月，日
		Calendar calendar = Calendar.getInstance();
		if (queryDate != null) {
			calendar.setTime(queryDate);
		}
		//年
		int year = calendar.get(Calendar.YEAR);
		//月
		int month = calendar.get(Calendar.MONTH) + 1;
		//日
		int date = calendar.get(Calendar.DATE);
		//判断list是否为空
		if (CollectionUtils.isEmpty(details)) {
			//批量填充日金额
			return fillEmptyDayAmountBatch(details, date);
		}
		// 组装customerNumber列表
		List<String> customerNumbers = new ArrayList<String>();
		for (CustomerGroupDetail d : details) {
			//客户编码不为空
			if (StringUtils.isNotBlank(d.getCustNumber())) {
				//设置
				customerNumbers.add(d.getCustNumber());
			}
		}
		if (CollectionUtils.isEmpty(customerNumbers)) {
			return fillEmptyDayAmountBatch(details, date);
		}

		// 取上个月的年，月
		calendar.add(Calendar.MONTH, -1);
		//上一年
		int year1 = calendar.get(Calendar.YEAR);
		//上一月
		int month1 = calendar.get(Calendar.MONTH) + 1;

//		// 取上上个月的年，月
		calendar.add(Calendar.MONTH, -1);
		//上上年
		int year2 = calendar.get(Calendar.YEAR);
		//上上月
		int month2 = calendar.get(Calendar.MONTH) + 1;
		
/*	//构建查询月分组条件
		Map<String, Object> monthCondition = new HashMap<String, Object>();
		monthCondition.put("year", year);
		monthCondition.put("year1", year1);
		monthCondition.put("year2", year2);
		monthCondition.put("month", month);
		monthCondition.put("month1", month1);
		monthCondition.put("month2", month2);
		monthCondition.put("day", date);
		monthCondition.put("customerNumbers", customerNumbers);
		monthCondition.put("fcAnalyseType", fcAnalyseType);
		// 取月分组数据
		List<CustomerGroupMonth> months = customerGroupService.getCustomerGroupDetailMonthAmount(monthCondition);
*/
		//构建查询日分组条件
		Map<String, Object> dayCondition = new HashMap<String, Object>();
		//年
		dayCondition.put("year", year);
		//月
		dayCondition.put("month", month);
		//日
		dayCondition.put("day", date);
		//客户编码
		dayCondition.put("customerNumbers", customerNumbers);
		//发到货类型
		dayCondition.put("fcAnalyseType", fcAnalyseType);
		
		// 取日分组数据
		List<CustomerGroupDay> days = customerGroupService.getCustomerGroupDetailDayAmount(dayCondition);
		//日期数组
		String[] dateArray = new String[]{String.valueOf(year), String.valueOf(month), String.valueOf(date), String.valueOf(year1), String.valueOf(month1), String.valueOf(year2), String.valueOf(month2)};
		
		// 合并3个查询的结果，组装成前台需要的数据类型
		return mergeCustomerGroupData(details, days, dateArray);
	}

	
	/**
	 * 
	 * <p>
	 * Description:客户分组, 合并3个查询的结果，组装成前台需要的数据类型<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 14, 2012
	 * @param details
	 * @param months
	 * @param days
	 * @param customerNumbers
	 * @param dateArray
	 * @return
	 * List<CustomerGroupDetail>
	 */
	private List<CustomerGroupDetail> mergeCustomerGroupData(List<CustomerGroupDetail> details, List<CustomerGroupDay> days, String[] dateArray) {
		String date = dateArray[2];
/*		String year = dateArray[0];
		String month = dateArray[1];
		String year1 = dateArray[3];
		String month1 = dateArray[4];
		String year2 = dateArray[5];
		String month2 = dateArray[6];*/
		for (CustomerGroupDetail detail : details) {
			// 设置月金额
/*			for (CustomerGroupMonth m : months) {
				// 设置本月金额
				if (StringUtils.equals(detail.getCustNumber(), m.getCustomerNumber()) && StringUtils.equals(year, m.getYear()) && StringUtils.equals(month, m.getMonth())) {
					detail.setMonthAmount(m.getAmount());
				} else // 设置上月金额
				if (StringUtils.equals(detail.getCustNumber(), m.getCustomerNumber()) && StringUtils.equals(year1, m.getYear()) && StringUtils.equals(month1, m.getMonth())) {
					detail.setMonth1Amount(m.getAmount());
					detail.setSendGoodCount(Integer.parseInt(m.getTimes()));
				} else // 设置上上月金额
				if (StringUtils.equals(detail.getCustNumber(), m.getCustomerNumber()) && StringUtils.equals(year2, m.getYear()) && StringUtils.equals(month2, m.getMonth())) {
					detail.setMonth2Amount(m.getAmount());
				}
			}*/
			// 按顺序设置日金额
			detail.setDayAmount(new ArrayList<String>());
			for (int i = 1; i <= Integer.parseInt(date); i++) {
				for (CustomerGroupDay d : days) {
					// 设置日金额
					if (StringUtils.equals(detail.getCustNumber(), d.getCustomerNumber()) && StringUtils.equals(String.valueOf(i), d.getDay())) {
						//添加到日金额中
						detail.getDayAmount().add(d.getAmount());
					}
				}
				// 如果其中有一天的发货金额在数据库中没有记录，则设置金额为0
				if (detail.getDayAmount().size() < i) {
					//设置为0
					detail.getDayAmount().add("0");
				}
			}
			
		}
		// 设置找不到customerNumber的用户日金额为0
		for (CustomerGroupDetail detail : details) {
			if (CollectionUtils.isEmpty(detail.getDayAmount())) {
				detail.setDayAmount(fillEmptyDayAmount(Integer.parseInt(date)));
			}
		}
		
		return details;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#getCustomerGroupDetailListByCondition(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail, int, int)
	 */
	@Override
	public Map<String, Object> getCustomerGroupDetailListByCondition(CustomerGroupDetail detail, int start, int limit) {
		//分组明细为空
		if(StringUtils.isBlank(detail.getDeptId())){
			//未选择分组异常
			throw new CustomerGroupException(CustomerGroupExceptionType.groupMustSelectDept);
		}
		//客户名称
		if (StringUtils.isNotBlank(detail.getCustName())) {
			//模糊查询
			detail.setCustName("%" + detail.getCustName().trim() + "%");
		}
		// 默认查询发货
		if (StringUtils.isBlank(detail.getFcAnalyseType())) {
			//设置类型值
			detail.setFcAnalyseType("1");
		}
		//分页查询分组明细
		List<CustomerGroupDetail> details = customerGroupService.getCustomerGroupDetailListByCondition(detail, start, limit);
		int count = customerGroupService.getCustomerGroupDetailListByConditionCount(detail);
		//数据填充
		//details = fillCustomerGroupDetailAmount(details, detail.getQueryDate(), detail.getFcAnalyseType());
		//总数
		//结果Map
		Map<String, Object> result = new HashMap<String, Object> ();
		//封装结果
		result.put("data", details);
		result.put("count", count);
		return result;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#getCustomerGroupByPrehuman(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getCustomerGroupByPrehuman(String depId, String userId) {
		//分组对象
		CustomerGroup cg = new CustomerGroup();
		//部门id
		cg.setDeptId(depId);
		//员工id
		cg.setEmpId(userId);
		//返回查询结果
		return customerGroupService.getCustomerGroupByPrehuman(cg);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#getPrehumanByGroupId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getPrehumanByGroupId(String depId, String groupId) {
		//客户分组
		CustomerGroup cg = new CustomerGroup();
		//部门id
		cg.setDeptId(depId);
		//分组id
		cg.setId(groupId);
		//查询
		return customerGroupService.getPrehumanByGroupId(cg);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager#getPrehumanByDeptId(java.lang.String)
	 */
	@Override
	public List<Employee> getPrehumanByDeptId(String deptId) {
		//根据部门ID查询维护人
		return customerGroupService.getPrehumanByDeptId(deptId);
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
	 * @return List<CustomerGroupDetail>
	 */
	@Override	
	public List<CustGroupDayDetail> searchCustomerGroupDetailDayAmount(CustGroupDayDetailCondition condition){
		return customerGroupService.searchCustomerGroupDetailDayAmount(condition);
	}
}
