package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title VisualMarketException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhouYuan
 * @version 0.1 2012-11-7
 */
public class VisualMarketException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public VisualMarketException(VisualMarketExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}
}
