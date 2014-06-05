/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DevelopExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author Administrator
 * @version 0.1 2012-3-23
 */
package com.deppon.crm.module.marketing.shared.exception;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title MapExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception
 * @author zhujunyong
 * @version 0.1 2012-3-30
 */

public enum MapExceptionType {
	// 查询条件为空--示例
	markInfoInvalid("i18n.fiveKilometreMap.markInfoInvalid");
	//异常代码
	private String errCode;
	/**
	 * @return errCode : return the property errCode.
	 */
	public String getErrCode() {
		return errCode;
	}
	/**
	 * @param errCode : set the property errCode.
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	/**
	 * 
	 * constructer
	 * @param errorCode
	 */
	private MapExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
