package com.deppon.crm.module.sysmail.shared.exception;

public enum MailExceptionType {
	//邮件对象不能为空
	MailAccountIsNull("i18n.sysMail.MailAccountObjectIsNull"),
	//接收人名称不能为空
	MailReceiverNameIsNull("i18n.sysMail.mailReceiverNameIsNull"),
	//邮件地址不能为空
	MailAddressIsNull("i18n.sysMail.mailAddressIsNull"),
	//邮件地址格式不正确
	MailAddressIsInvalid("i18n.sysMail.mailAddressIsInvalid"),
	//邮件组不能为空
	MailGroupIsNull("i18n.sysMail.MailGroupObjectIsNull"), 
	//邮件组名称不能为空
	MailGroupNameIsNull("i18n.sysMail.MailGroupNameIsNull"),
	//邮件组编码不能为空
	MailGroupCodeIsNull("i18n.sysMail.MailGroupCodeIsNull"),
	//重复的邮件地址
	SameMailAddressException("i18n.sysMail.SameMailAddress"), 
	//包含特殊字符
	IncludeSpecialChar("i18n.sysMail.IncludeSpecialChar"), 
	//重复的组名
	SameMailGroupName("i18n.sysMail.SameMailGroupName"), 
	//重复的组编码
	SameMailGroupCode("i18n.sysMail.SameMailGroupCode");
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private MailExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
