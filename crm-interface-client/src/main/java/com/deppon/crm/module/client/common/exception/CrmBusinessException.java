package com.deppon.crm.module.client.common.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebFault;

/**
 * 继承自{@link Exception} ，表示的是一种业务异常，所有的接口都会抛出此业务异常，
 * 每个抛出的业务异常都会有一个业务异常代码{@link ErrorMessageCode}}，可以通过业
 * 务异常代码查找到相应的业务异常信息
 * @author davidcun
 * @2012-3-10
 * description
 */
@WebFault(name="CrmBusiness",targetNamespace="http://exception.common.client.module.crm.deppon.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrmBusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3050799771143444715L;
	
	@XmlElement
	private String errorCode;
	
	public CrmBusinessException(String errorCode) {
		super(BusinessExceptionMessageProvider.getMessage(errorCode));
		this.errorCode = errorCode;
	}

	public CrmBusinessException(String errorCode,Throwable cause) {
		super(BusinessExceptionMessageProvider.getMessage(errorCode),cause);
		this.errorCode = errorCode;
	}
	public CrmBusinessException(String errorCode,Object... arguments){
		super(BusinessExceptionMessageProvider.getMessage(errorCode, arguments));
		this.errorCode = errorCode;
	}
	public CrmBusinessException(String errorCode,Throwable cause,Object... arguments){
		super(BusinessExceptionMessageProvider.getMessage(errorCode, arguments),cause);
		this.errorCode = errorCode;
	}
		
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
