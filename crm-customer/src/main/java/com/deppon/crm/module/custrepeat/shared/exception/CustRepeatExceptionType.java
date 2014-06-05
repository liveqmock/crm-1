package com.deppon.crm.module.custrepeat.shared.exception;


public enum CustRepeatExceptionType {
	//部门ID为空
	DEPTID_ISNULL("i18n.custrepeat.system.deptIdIsNull"),
	//疑似重复主客户编号为空
	MAINCUSTID_ISNULL("i18n.custrepeat.system.mainCustIdIsNull"),
	//工作流对象为空
	WORKFLOWINFO_ISNULL("i18n.custrepeat.workflow.infoIsNull"),
	//查询条件不能全为空
	CUSTCODE_AND_CISTMAME_ALLNULL("i18n.custrepeat.system.custCode_isNull"),
	//客户编码不能为空
	CUSTCODE_ISNULL("i18n.custrepeat.system.custCode_isNull"),
	//存在疑似重复列表中
	CUST_ISEXIST_REPEAT("i18n.custrepeat.system.isExistRepeatCust"),
	//存在审批中的客户
	CUST_ISEXIST_APPEOVAL("i18n.custrepeat.system.isExistApprovalCust"),
	//存在日程中的（流失预警，计划中，日程中）
	CUST_ISEXIST_SCHEDULE("i18n.custrepeat.system.isExistScheduleCust"),
	//存在多个大客户/合同客户，无法确定主客户，请核实
	CUST_EXISTMOREKEYCUST("i18n.custrepeat.system.existMoreKeyCust"),
	//列表中无本部门客户，请核实
	CUST_NOTEXISTCUST_OFOURDEPT("i18n.custrepeat.system.notExistCustofOurDept"),
	//本部门客户优先级低于其他部门客户优先级，无法确定主客户，请核实
	CUST_CURRENTDEPT_CUSTLEVEL_LOW("i18n.custrepeat.system.currentDept_custLevel_low"),
	//所辖部门超过800个
	DEPTLISTOVER("i18n.custrepeat.system.deptListOver");
	;
	/**
	 * 异常code
	 */
	private String errCode;
	/**
	 * 构造器
	 * @param errCode
	 */
	private CustRepeatExceptionType(String errCode) {
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
