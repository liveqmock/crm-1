/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author SYJ
 * @version 0.1 2012-11-7
 */
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title RegionPartitionException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhouYuan
 * @version 0.1 2012-11-7
 */

public class MarketActivityException extends BusinessException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7805058861943725288L;

	public MarketActivityException(MarketActivityExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}
}
