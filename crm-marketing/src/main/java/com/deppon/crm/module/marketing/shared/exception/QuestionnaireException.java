/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireException.java
 * @package com.deppon.crm.module.marketing.shared.exception
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * <p>
 * Description:问卷管理异常定义<br />
 * </p>
 * @title QuestionnaireException.java
 * @package com.deppon.crm.module.marketing.shared.exception
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
public class QuestionnaireException extends BusinessException{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public QuestionnaireException(QuestionnaireExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}

	/**
	 * 
	 * constructer
	 * @param errorType
	 * @param args
	 */
	public QuestionnaireException(QuestionnaireExceptionType errorType, Object...args){
		super();
		this.errCode=errorType.getErrCode();
		//异常参数
		this.setErrorArguments(args);
	}
}
