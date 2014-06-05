
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DevelopException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author 苏玉军
 * @version 0.1 2012-3-23
 */
public class ScheduleException extends BusinessException {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public ScheduleException(ScheduleExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}

	/**
	 * 
	 * constructer
	 * @param errorType
	 * @param args
	 */
	public ScheduleException(ScheduleExceptionType errorType, Object...args){
		super();
		this.errCode=errorType.getErrCode();
		//异常参数
		this.setErrorArguments(args);
	}
}
