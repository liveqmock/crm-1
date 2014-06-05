package com.deppon.crm.module.report.shared.exception;

/**
 * 大客户异常枚举
 * @author yw
 *
 */
public enum ReportExceptionType {
	NULL_CUSTNUM("i18n.keyCustReport.custNumNotAllowEmpty"),//客户编码不能为空
	MEMBER_NULL("i18n.keyCustReport.PleaseEntyTrueCustNum"),//请输入有效的客户编码
	DEPTID_NOT_SAME("i18n.keyCustReport.dept.not.match"),//只能查询本部门的客户
	NOT_KEY_CUST("i18n.keyCustReport.not.keyCust"),//该客户无大客户标记，无法查询走货报告
	UNKNOW_ERROR("i18n.keyCustReport.unknow.error"),//未知异常
	CREATE_FILE_ERROR("i18n.keyCustReport.createFile.error"),//创建文件失败
	CREATE_FILE_MODEL_ERROR("i18n.keyCustReport.createFileModel.error");//抽筋啊文件模板失败
	public static String getValue(ReportExceptionType type){
		switch (type) {
		case NULL_CUSTNUM:
			return NULL_CUSTNUM.getErrorCode();
		case MEMBER_NULL:
			return MEMBER_NULL.getErrorCode();
		case DEPTID_NOT_SAME:
			return DEPTID_NOT_SAME.getErrorCode();
		case NOT_KEY_CUST:
			return NOT_KEY_CUST.getErrorCode();
		case CREATE_FILE_ERROR:
			return CREATE_FILE_ERROR.getErrorCode();
		case CREATE_FILE_MODEL_ERROR:
			CREATE_FILE_MODEL_ERROR.getErrorCode();
		default:
			return UNKNOW_ERROR.getErrorCode();
		}
	}
	private String errorCode;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	private ReportExceptionType(String errorCode){
		this.errorCode=errorCode;
	}
}
