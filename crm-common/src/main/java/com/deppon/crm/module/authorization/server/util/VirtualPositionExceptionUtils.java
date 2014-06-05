package com.deppon.crm.module.authorization.server.util;

import com.deppon.crm.module.authorization.shared.exception.VirtualPositionException;
import com.deppon.crm.module.authorization.shared.exception.VirtualPositionExceptionType;
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
public class VirtualPositionExceptionUtils {
	public static void createMailException(VirtualPositionExceptionType type,Object...params){
		VirtualPositionException e = new VirtualPositionException(type);
		throw new GeneralException(e.getErrorCode(),e.getMessage(),e,params) {
			private static final long serialVersionUID = 3391420953866456902L;
		};
	}
}
