/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author Administrator
 * @version 0.1 2012-4-14
 */
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author 苏玉军
 * @version 0.1 2012-4-14
 */

public class CycleException extends BusinessException{
	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public CycleException(CycleExceptionType errorType){
		//父类构造器
		super();
		//变量初始化
		this.errCode=errorType.getErrCode();
	}
}
