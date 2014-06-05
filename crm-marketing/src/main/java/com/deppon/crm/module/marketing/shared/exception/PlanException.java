
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
public class PlanException extends BusinessException {
	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public PlanException(PlanExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}
	
	/**
	 * 
	 * constructer
	 * @param errorType
	 * @param args
	 */
	public PlanException(PlanExceptionType errorType, Object...args){
		//父类构造器
		super();
		//变量初始化
		this.errCode=errorType.getErrCode();
		//错误参数
		this.setErrorArguments(args);
	}
}
