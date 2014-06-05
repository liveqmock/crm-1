package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:主要用于action请求日志<br />
 * </p>
 * @title CleanPropertySetSetFunction.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class CleanPropertySetSetFunction implements FunctionProvider{
	
	/**
	 * @author 078823
	 * @date 2014-04-11
	 * @description:请求属性</br>
	 * 主要用于action请求日志
	 * *
	 */	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//获得key
		String keys = (String) args.get("cleanKeys");
		//为空就返回
		if(keys == null){
			return ;
		}
		//拆分字符串
		String[] keysArr = keys.split(",");
		//删除PropertySet的key
		for (String key : keysArr) {
			ps.remove(key);
		}
		
	}

}
