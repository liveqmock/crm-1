package com.deppon.crm.module.sysmail.server.utils;

import com.deppon.crm.module.sysmail.shared.exception.MailException;
import com.deppon.crm.module.sysmail.shared.exception.MailExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * 异常处理工具类<br />
 * </p>
 * @title MailExceptionUtils.java
 * @package com.deppon.crm.module.sysmail.server.utils 
 * @author suyujun
 * @version 0.1 2013-9-18
 */
public class MailExceptionUtils {
	public static void createMailException(MailExceptionType type,Object...params){
		MailException e = new MailException(type);
		throw new GeneralException(e.getErrorCode(),e.getMessage(),e,params) {
			private static final long serialVersionUID = 635018213570751248L;
		};
	}
}
