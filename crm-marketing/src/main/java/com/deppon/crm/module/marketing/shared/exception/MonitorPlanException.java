/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-4-20
 */
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-4-20
 */

public class MonitorPlanException extends BusinessException {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public MonitorPlanException(MonitorPlanExceptionType errorType){
		//父类构造器
		super();
		//变量初始化
		this.errCode=errorType.getErrCode();
	}
}
