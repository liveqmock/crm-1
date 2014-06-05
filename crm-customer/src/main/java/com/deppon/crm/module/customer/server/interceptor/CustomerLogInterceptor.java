package com.deppon.crm.module.customer.server.interceptor;

import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.foss.framework.server.web.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.ActionInvocation;
/**
 * 
 * <p>
 * Description:客户同步拦截器<br />
 * </p>
 * @title CustomerLogInterceptor.java
 * @package com.deppon.crm.module.customer.server.interceptor 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class CustomerLogInterceptor extends AbstractInterceptor{
/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -2524664887038066045L;


	/**
	 * @author 078823
	 * @date 2014-04-11
	 * @decription:提交客户修改同步日志</br>
	 * 主要用于action请求
	 * *(non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//初始化
		CustomerInfoLog.init();
		//invoke
		String result = invocation.invoke();
		//提交
		CustomerInfoLog.commit();
		//返回结果
		return result;
	}
	
}
