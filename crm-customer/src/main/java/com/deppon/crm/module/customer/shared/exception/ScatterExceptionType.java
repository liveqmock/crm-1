package com.deppon.crm.module.customer.shared.exception;
/**
 * 
 * <p>
 * Description:散客异常类型<br />
 * </p>
 * @title ScatterExceptionType.java
 * @package com.deppon.crm.module.customer.shared.exception 
 * @author 106138
 * @version 0.1 2014年4月18日
 */
public enum ScatterExceptionType {
	
	//该散客有审批中的工作流不可以修改
	SCATTEREXISITSWORKFLOWCANTMODIFY("i18n.scatter.scatterExisitsWorkFlowCantModify"),
	//该散客已被修改过，请刷新页面重新操作
	SCATTERMODIFYBYOTHERTRYAGAIN("i18n.scatter.scatterModifyByOtherTryAgain"),
	//通过反射设置散客信息出现异常
	JAVAREFLECTERROR("i18n.scatter.javaReflectError"),
	//客户名字和联系人姓名不能为空
	CUSTNAMEANDLINKNAMENOTNULL("i18n.scatter.custNameAndLinkNameNotNull"),
	//散客部门信息不能为空
	SCATTERDEPTNOTNULL("i18n.scatter.scatterDeptNotNull"),
	//手机号码格式不正确
	PHONEFORMERROR("i18n.scatter.phoneFormError"),
	//临欠额度不能大于3000
	VELOCITYAMOUNT("i18n.scatter.velocityAmount"),
	//散客查询条件不能为空
	SCATTERCONDITIONNOTNULL("i18n.scatter.scatterConditionNotNull"),
	//查询的起止时间都不能为空
	CONDITIONTIMENOTNULL("i18n.scatter.conditionTimeNotNull"),
	//查询起始时间不能超过一年
	CONDITIONTIMENOTRIGHT("i18n.scatter.conditionTimeNotRight"),
	//散客账户信息不完整
	SCATTERACCOUNTNOTFULL("i18n.scatter.scatterAccountNotFull"),
	//同一个散客银行账号不能相同
	SCATTERACCOUNTCANNOTEQUAL("i18n.scatter.scatterAccountCanNotEqual"),
	//不符删除散客的条件
	SCATTERCANNOTDELETE("i18n.scatter.scatterCanNotDelete"),
	//客户属性为空
	CUSTNATUREISNULL("i18n.scatter.custNatureIsNull"),
	//该联系方式在本部门存在对应散客信息
	EXITSCATTERINCURRENTDEPT("i18n.scatter.exitScatterInCurrentDept"),
	//该联系方式对应存在会员信息
	EXITMEMBER("i18n.scatter.ExitMember"),
	//联系方式XXXX与XXX营业部的固定客户编码XXXX重复，不能保存为散客 
	MOBILEEXIT("i18n.scatter.mobileExit"),
	//联系方式XXXX与XXX营业部的固定客户编码XXXX重复，不能保存为散客 
	EXITNAMEANDPHONE("i18n.scatter.exitNameAndPhone"),
	//联系人电话和姓名审批中
	EXITNAMEANDPHONEING("i18n.scatter.exitNameAndPhoneIng"),
	//手机号码审批中
	EXITMOBILEING("i18n.scatter.exitMobileIng"),
	//联系方式不能为空
	CONTACTWAYISNULL("i18n.scatter.contactWayIsNull"),
	//查找修改账号的原值发生错误
	CANNOTFINDACCOUNTORIGINALVALUE("i18n.scatter.canNotFindAccountOriginalValue"),
	//数据错误
	DATAERROR("i18n.scatter.DataError"),
	//散客账户的联系方式不能全部为空
	ACCOUNTCONTACTWAYCANNOTNULL("i18n.scatter.accountContactWayCanNotNull"),
	//联系方式XXXX与散客客户编码XXXX重复
	CONTACTWAYINCURRENTDEPTNOTEQUAIL("i18n.scatter.contactWayInCurrentDeptNotEquail"),
	//散客信息已经被删除
	SCATTERCUSTOMERISNULL("i18n.scatter.scatterCustomerIsNull"),
	//该散客在审批中，工作流号xxxxxxx，审批未完成不能删除散客信息！
	SCATTEREXAMINATION("i18n.scatter.scatterExamination"),
	//该散客已是无效,无需删除
	SCATTERISCANCELL("i18n.scatter.scatterIsCancel"),
	//该散客已经为临代散客，不需要做转化
	SCATTERISFOSSSCATTER("i18n.scatter.scatterIsFossScatter"),
	//该散客状态为无效，不能转为临代散客
	SCATTERCUSTOMERISLOSEEFFECT("i18n.scatter.scatterCustomerIsLoseEffect"),
	//保存新增账号失败
	SAVEACCOUNTERROR("i18n.scatter.saveAccountError"),
	//电话号码格式不正确
	TELFORMERROR("i18n.scatter.telFormError"),
	//香港登记证号不合法
	EnterpriseSCTTERCUSTOMER_TaxregNumberError("i18n.scatter.EnterpriseSCTTERCUSTOMER_TaxregNumberError"),
	//部门已存在联系方式为XXXX的散客，不能重复保存
	CONTACTWAYHASEXISTSINSCATTER("i18n.scatter.ContactWayHasExistsInScatter");
	
	private String errCode;

	private ScatterExceptionType(String errCode) {
		this.errCode = errCode;
	}
	/**
	 * 
	 * <p>
	 * Description:getErrCode<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
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
	 * @version 0.1 2014年4月18日
	 * @param errCode
	 * void
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
