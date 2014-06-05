/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundExceptionType.java
 * @package com.deppon.crm.module.order.shared.exception 
 * @author Administrator
 * @version 0.1 2012-9-8
 */
package com.deppon.crm.module.order.shared.exception;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundExceptionType.java
 * @package com.deppon.crm.module.order.shared.exception 
 * @author 苏玉军
 * @version 0.1 2012-9-8
 */

public enum UnboundExceptionType {
	//联系人编码不能为空
	CONTACT_NUMBER_NULL("i18n.order.contactNumberNull"),
	//联系人不存在
	CONTACT_NOT_EXISTS("i18n.order.contactNotExists"),
	//联系人手机号码不能为空
	CONTACT_MOBILEPHONE_ISNULL("i18n.order.contactMobileIsNull"),
	//联系人对象为空
	CONTACT_OBJECT_ISNULL("i18n.order.contactObjectIsNull"),
	//对端接口调用失败
	UNBOUND_FAIL("i18n.order.unboundFail"),
	//注册用户名不能为空
	REGISTER_NAME_NULL("i18n.order.registerIdIsNull"),
	//手机号码不能为空
	CONTACT_MOBILE_NULL("i18n.order.contactMobileNull");
	//错误编码
	private String errorCode;
	private UnboundExceptionType(String errorCode){
		this.errorCode 	=  errorCode;
	}
		
	/**
	 *@return  errorCode;
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode : set the property errorCode.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @description 获取异常消息
	 * @author 苏玉军
	 * @version 0.1 2012-9-8
	 * @param type
	 * @return
	 * String
	 */
	public static String getValue(UnboundExceptionType type){
		switch(type){
		case CONTACT_NUMBER_NULL:
			return CONTACT_NUMBER_NULL.getErrorCode();
		case CONTACT_NOT_EXISTS:
			return CONTACT_NOT_EXISTS.getErrorCode();
		case CONTACT_MOBILEPHONE_ISNULL:
			return CONTACT_MOBILEPHONE_ISNULL.getErrorCode();
		case CONTACT_OBJECT_ISNULL:
			return CONTACT_OBJECT_ISNULL.getErrorCode();
		case UNBOUND_FAIL:
			return UNBOUND_FAIL.getErrorCode();
		case CONTACT_MOBILE_NULL:
			CONTACT_MOBILE_NULL.getErrorCode();
		default:
			return "";
		}
	}
}
