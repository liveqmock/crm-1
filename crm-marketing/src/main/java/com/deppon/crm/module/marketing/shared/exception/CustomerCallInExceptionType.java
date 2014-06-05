/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author SYJ
 * @version 0.1 2012-11-7
 */
package com.deppon.crm.module.marketing.shared.exception;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author 苏玉军
 * @version 0.1 2012-11-7
 */

public enum CustomerCallInExceptionType {
	//客户信息不能为空
	CUSTVO_ISNULL("i18n.customerCallIn.custInfoVoIsNull"),
	//联系人姓名不能为空
	CUSTLINKMAN_NAME_ISNULL("i18n.customerCallIn.custLinkManNameIsNull"),
	//联系人手机、电话至少录入一个
	CUSTLINK_MOBILE_TEL_MUSTHAVEONE("i18n.customerCallIn.custLinkMobileTelMustHaveOne"),
	//请填写客户意见
	CUST_OPTION_ISNULL("i18n.customerCallIn.custOptionIsNull"),
	//客户需求不完整
	CUST_OPTION_INCOMPLETE("i18n.customerCallIn.custOptionInComplete"),
	//请选择客户
	CUST_MUST_SELECT("i18n.customerCallIn.doNotSelectCust"),
	//未知客户类型
	CUST_UNKNOW_TYPE("i18n.customerCallIn.unKnownCustType");
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
	private CustomerCallInExceptionType(String errorCode) {
		this.errCode = errorCode;
	}

}
