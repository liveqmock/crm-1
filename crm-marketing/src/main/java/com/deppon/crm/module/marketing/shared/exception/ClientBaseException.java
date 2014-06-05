package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;


public class ClientBaseException extends BusinessException {

	/**   
	 * <p>
	 * Description:客户群异常类<br />
	 * </p>
	 * @title ClientBaseException.java
	 * @package com.deppon.crm.module.marketing.shared.exception 
	 * @author lvchognxin
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientBaseException(ClientBaseExceptionType errorType){
		
		super();
		
		this.errCode=errorType.getErrCode();
	}
	
	
}
