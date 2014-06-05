package com.deppon.crm.module.bpsworkflow.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;
/**
 * 
* @ClassName: NoWorkFlowException 
* @Description: 工作流异常类
* @author LiuY
* @date 2014年4月16日 下午6:12:58 
*
 */
public class NoWorkFlowException extends GeneralException{

	/**
	 * 构造方法
	 */
	public NoWorkFlowException(){
		super();
	}
	/**
	 * 构造方法
	 */
	public NoWorkFlowException(String errCode, String message,
			Throwable cause, Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public NoWorkFlowException(String errCode,Object... arguments) {
		this.errCode = errCode;
		super.setErrorArguments(arguments);
	}
	/**
	 * 构造方法
	 */
	public NoWorkFlowException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public NoWorkFlowException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public NoWorkFlowException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
