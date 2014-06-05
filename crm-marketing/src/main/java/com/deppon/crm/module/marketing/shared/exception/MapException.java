
package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author zhujunyong
 * @version 0.1 2012-3-30
 */
public class MapException extends BusinessException {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 34185245627832334L;
	/**
	 * 
	 * constructer
	 * @param errorType
	 */
	public MapException(MapExceptionType errorType){
		//父类构造器
		super();
		//变量初始化
		this.errCode=errorType.getErrCode();
	}
}
