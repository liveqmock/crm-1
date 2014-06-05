/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.crm.module.login.shared.exception.LoginExceptionType;
import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */

@SuppressWarnings("serial")
public class ReturnVisitException extends BusinessException {
	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public ReturnVisitException(ReturnVisitExceptionType errorType){
		//父类构造器
		super();
		//变量初始化
		this.errCode=errorType.getErrCode();
	}
	/**
	 * 
	 * constructer
	 * @param errorType
	 * @param args
	 */
	public ReturnVisitException(ReturnVisitExceptionType errorType,Object... args){
		this(errorType);
		this.setErrorArguments(args);
	}
}
