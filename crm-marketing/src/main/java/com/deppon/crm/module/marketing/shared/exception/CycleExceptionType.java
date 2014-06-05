/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author Administrator
 * @version 0.1 2012-4-14
 */
package com.deppon.crm.module.marketing.shared.exception;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author 苏玉军
 * @version 0.1 2012-4-14
 */

public enum CycleExceptionType {
	//必须选择一个条件
	oneConditionIsMust("i18n.cycle.oneConditionIsMust"),
	//未选择客户异常
	noCustIdHaveSelected("i18n.cycle.noCustIdHaveSelected"),
	//接口调用失败异常
	interfaceExecuteFailure("i18n.cycle.interfaceExecuteFailure"),
	//客户编码为空异常
	custNumberIsNull("i18n.cycle.custNumberIsNull"),
	//查询日期为空异常
	queryDateIsNull("i18n.cycle.queryDateIsNull"),
	//出发到达类型为空
	leaveArriveTypeIsNull("i18n.cycle.leaveArriveTypeIsNull");
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
	private CycleExceptionType(String errorCode) {
		this.errCode = errorCode;
	}

	/*public static String getValue(CycleExceptionType errorType) {
		switch (errorType) {
		case oneConditionIsMust:
			return oneConditionIsMust.getErrCode();
		case noCustIdHaveSelected:
			return noCustIdHaveSelected.getErrCode();
		}
		return null;
	}*/
}
