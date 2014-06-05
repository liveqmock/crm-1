package com.deppon.crm.module.customer.shared.exception;
/**
 * 
 * <p>
 * Description:工作流异常类型<br />
 * </p>
 * @title WorkFlowExceptionType.java
 * @package com.deppon.crm.module.customer.shared.exception 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public enum WorkFlowExceptionType {
	//数据类型错误
	DataTypeError("i18n.customer.DataTypeError"),
	//没有权限
	NoAuthor("i18n.customer.NoAuthor"),
	//工作流已经结束
	WorkFlowEnd("i18n.customer.WorkFlowEnd"),
	WorkFlowHasNoSplit("i18n.customer.WorkFlowHasNoSplit"), 
	//找不到角色
	NoRole("i18n.customer.NoRole"), 
	//没有对应的数据权限部门
	NoRoleUserDepartment("i18n.customer.NoRoleUserDepartment"), 
	//没有权限创建工作流
	ErrorAuthCreateWorkFLow("i18n.customer.ErrorAuthCreateWorkFLow"), 
	//工作流生成失败
	ErrorCreateWorkFlow("i18n.customer.ErrorCreateWorkFlow"),
	;
	
	private String errCode;
	/**
	 * 
	 * constructer
	 * @param errCode
	 */
	private WorkFlowExceptionType(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * <p>
	 * Description:errCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * <p>
	 * Description:errCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
