/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author SYJ
 * @version 0.1 2012-11-7
 */
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author 苏玉军
 * @version 0.1 2012-11-7
 */

public class CustomerCallInException extends BusinessException{
	private static final long serialVersionUID = -6199009725546645865L;
	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public CustomerCallInException(CustomerCallInExceptionType errorType){
		//父类构造器
		super();
		//变量初始化
		this.errCode=errorType.getErrCode();
	}
}
