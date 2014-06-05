/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:客户分组异常类<br />
 * </p>
 * @title CustomerGroupException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */

public class CustomerGroupException extends BusinessException{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -5109258386372939981L;
	/**
	 * constructer
	 * @param errorType
	 */
	public CustomerGroupException(CustomerGroupExceptionType errorType){
		//父类构造器
		super();
		//变量初始化
		this.errCode=errorType.getErrCode();
	}


}
