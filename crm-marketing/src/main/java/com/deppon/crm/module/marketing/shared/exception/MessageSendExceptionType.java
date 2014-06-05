package com.deppon.crm.module.marketing.shared.exception;

public enum MessageSendExceptionType {
	//上传的文件没有找到
	messageFileNotFound("i18n.messageSend.filenotFound"),
	//读取文件失败
	messageFileInputStreamError("i18n.messageSend.inputStreamError"),
	//营销短信发送任务实体为空
	messageSendTaskIsNull("i18n.messageSend.messageSendTaskIsNull"),
	//文件名称不能为空
	messageSendTaskFileNameIsNull("i18n.messageSend.messageSendTaskFileNameIsNull"),
	//文件路径错误
	messageSendTaskFilePathError("i18n.messageSend.messageSendTaskFilePathError"),
	//任务状态异常
	messageSendTaskStatusError("i18n.messageSend.messageSendTaskStatusError"),
	//发送任务员工信息异常
	messageSendTaskUserError("i18n.messageSend.messageSendTaskUserError"),
	//短信内容大于140个字
	messageSendMaxMsgContentError("i18n.messageSend.contextCanNotExceedLimit"),
	//文本类型错误
	messgeFileTypeError("i18n.messageSend.fileTypeError"),
	//手机号码文件为空
	messageFileIsNull("i18n.messageSend.phoneFileIsNull");
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private MessageSendExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
