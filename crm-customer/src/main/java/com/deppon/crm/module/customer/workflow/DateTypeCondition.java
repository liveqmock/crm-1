package com.deppon.crm.module.customer.workflow;

import java.util.Map;
import java.util.Set;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Condition;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:时间类型条件<br />
 * </p>
 * @title DateTypeCondition.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class DateTypeCondition implements Condition{
	
	/**
	 * @author 078823
	 * @date 2014-04-11
	 * @description:获取客户修改类型</br>
	 * 主要用于action请求日志
	 * *(non-Javadoc)
	 * @see com.opensymphony.workflow.Condition.passesCondition(Map, Map, PropertySet) throws WorkflowException
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean passesCondition(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//从属性set获得数据类型
		Set<String> dateTypes =  (Set<String>) ps.getObject("dataTypes");
		//当前
		String currentDateType = (String) args.get("dataType");
		//是否默认
		String isDefault = (String)args.get("isDefault");
		//转为boolean
		boolean isDefaultB = Boolean.valueOf(isDefault);
		//循环
		for (String dateType : dateTypes) {
			if(dateType.equals(currentDateType)){
				return !isDefaultB;
			}
		}
		//返回
		return isDefaultB;
	}
}
