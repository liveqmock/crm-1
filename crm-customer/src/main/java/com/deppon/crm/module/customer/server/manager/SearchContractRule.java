/**
 * @description
 * @author 赵斌
 * @2012-4-18
 * @return
 */
package com.deppon.crm.module.customer.server.manager;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * 
 * <p>
 * Description:合同查询规则<br />
 * </p>
 * @title SearchContractRule.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class SearchContractRule 
{
	
	/**
	 * 
	 * @校验查询条件是否全空
	 * @author 赵斌
	 * @2012-4-18
	 * @return
	 */
	public static boolean checkSearcheConditionIsNull(ContractCondition condition) 
	{
		return condition == null
	   	||(ValidateUtil.objectIsEmpty(condition.getDeptId())
		&& ValidateUtil.objectIsEmpty(condition.getCustNumber())
		&& ValidateUtil.objectIsEmpty(condition.getCustCompany()) 
		&& ValidateUtil.objectIsEmpty(condition.getContractNum())
		&& ValidateUtil.objectIsEmpty(condition.getContactName())
		&& ValidateUtil.objectIsEmpty(condition.getSearchStartTime())
		&& ValidateUtil.objectIsEmpty(condition.getSearchEndTime()));
	}
	
	/**
	 * 
	 * @校验查询时间是否在一定范围之内
	 * @author 赵斌
	 * @2012-4-18
	 * @return
	 */
	public static boolean isSearchBetweenPerioid(
			ContractCondition condition, int perioid) 
	{
		boolean b=false;
		//查询开始日期
		Date start = condition.getSearchStartTime();
		//查询结束日期
		Date end = condition.getSearchEndTime();
		//都不为空进行日期范围的判断
		if(start!=null && end!=null){
			b = getDaysBetweenDates(start, end, perioid);			
		}
		
		if(start==null && end ==null){
			return true;
		}
		return b;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月10日
	 * @param start
	 * @param end
	 * @param perioid
	 * @return
	 * boolean
	 */
	public static boolean getDaysBetweenDates(Date start, Date end, int perioid) {
		//获得开始日期
		Calendar calenderStart = Calendar.getInstance();
		//获得结束日期
		Calendar calenderEnd = Calendar.getInstance();
		//设置开始
		calenderStart.setTime(start);
		//设置结束
		calenderEnd.setTime(end);
		//计算月份
		calenderStart.set(Calendar.MONTH,calenderStart.get(Calendar.MONTH)+perioid);
		//范围大于period 返回true
		if (calenderEnd.before(calenderStart) || calenderEnd.equals(calenderStart)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @只查询本部门合同信息
	 * @author 赵斌
	 * @2012-4-18
	 * @return
	 */
	public static ContractCondition setConditionSelfDept(ContractCondition condition)
	{
		//获得用户对象
		User user = (User) UserContext.getCurrentUser();
		if (user != null) 
		{
			//从user对象中获取部门Id
			String deptId = user.getEmpCode().getDeptId().getId();
			if (!StringUtils.isEmpty(condition.getDeptId()) && !condition.getDeptId().equals(deptId)) 
			{
				//进行部门Id的设置
				condition.setDeptId(deptId);
			}
		}
		return condition;
	}

}
