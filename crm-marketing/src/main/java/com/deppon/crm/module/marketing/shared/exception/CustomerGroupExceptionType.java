/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.shared.exception;

/**   
 * <p>
 * Description:客户分组异常类型<br />
 * </p>
 * @title CustomerGroupException.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
public enum CustomerGroupExceptionType {
	//组名已存在异常
	groupNameAlreadyExistedInDept("i18n.customerGroup.groupNameAlreadyExistedInDept"),
	//分组客户超过最大数异常
	groupCustomerMaxLimit("i18n.customerGroup.groupCustomerMaxLimit"),
	//分组名称最大长度异常
	groupNameMaxLimit("i18n.customerGroup.groupNameMaxLimit"),
	//分组必须选择
	groupMustSelectDept("i18n.customerGroup.mustSelectDept");
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
	private CustomerGroupExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
