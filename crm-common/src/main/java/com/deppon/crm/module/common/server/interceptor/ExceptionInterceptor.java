/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ExceptionInterceptor.java
 * @package com.deppon.crm.module.common.server.interceptor 
 * @author ZhuPJ
 * @version 0.1 2013-12-4
 */
package com.deppon.crm.module.common.server.interceptor;

import org.apache.commons.lang3.StringUtils;

import com.deppon.ar.bamp.client.interceptor.struts2.RequestErrorInterceptor;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

/**   
 * <p>
 * Description: 自定义拦截器，用于监控平台，<br />
 * </p>
 * @title ExceptionInterceptor.java
 * @package com.deppon.crm.module.common.server.interceptor 
 * @author ZhuPJ
 * @version 0.1 2013-12-4
 */

public class ExceptionInterceptor extends RequestErrorInterceptor {
	private static final long serialVersionUID = 1L;
	// 默认操作人工号（-1）
	final static String DEFAULT_EMPCODE = "-1";
	
	@Override
	public String getEmpCode() {
		// 获得当前操作人工号，获得不到则返回-1
		User currentUser = (User)(UserContext.getCurrentUser());
		if (currentUser == null){
			// User 为空，范围DEFAULT_EMPCODE
			return DEFAULT_EMPCODE;
		}
		Employee e = currentUser.getEmpCode();
		if (e == null){
			// Employee 为空，范围DEFAULT_EMPCODE
			return DEFAULT_EMPCODE;
		}
		String empCode = e.getEmpCode();
		if (StringUtils.isNotEmpty(empCode)){
			return empCode;
		}else{
			// empCode 为空，范围DEFAULT_EMPCODE
			return DEFAULT_EMPCODE;
		}
	}

}
