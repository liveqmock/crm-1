package com.deppon.crm.module.keycustomer.shared.exception;
 /**
  * 
  * <p>
  * Description:大客户异常类型<br />
  * </p>
  * @title KeyCustomerExceptionType.java
  * @package com.deppon.crm.module.keycustomer.shared.exception 
  * @author 106138
  * @version 0.1 2014年4月11日
  */
public enum KeyCustomerExceptionType {
	NULLSEARCHCONDITION("您好，查询条件不能全部为空！"), 
	ERRORSEARCHCONDITION("模糊匹配必须输入部门"), 
	WORKFLOWTYPEISNULL("您好，工作流申请类型为空！"), 
	SPECIALISNULL("您好，是否特殊申请为空！"),
	INFOISNULl("您好，表单申请元素不全"), 
	NOTDEPTBELONG("您好，非归属部门经理进行申请！"),
	WORKFLOW__IN_APPROVE("该客户存在审批中的大客户工作流，无法进行大客户工作流申请"), 
	NOW_IS_KEYCUSTOMER("该客户已经是大客户，无法进行申请"),
	NOW_IN_KEYCUSTOMERLIST("客户在准入列表或者预退出列表中，请到到相应页面进行申请!"),
	NOW_IS_NOTKEYCUSTOMER("该客户已经被删除大客户标记,无法进行退出处理或者保留申请!");
	private String errCode;
	/**
	 * 
	 * constructer
	 * @param errCode
	 */
	private KeyCustomerExceptionType(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * 
	 * <p>
	 * getErrCode<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @return
	 * String
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * 
	 * <p>
	 * Description:setErrCode<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param errCode
	 * void
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
