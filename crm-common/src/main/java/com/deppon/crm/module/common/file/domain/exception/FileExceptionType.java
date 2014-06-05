package com.deppon.crm.module.common.file.domain.exception;

/**
 * @description 文件异常类型
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-5
 */

public enum FileExceptionType {

	/*
	 * 上传下载
	 */
	// 文件操作失败！
	FILE_EXCEPTION("i18n.file.error"),
	// 文件大小超过设置值！
	FILE_EXCEPTION_MAXSIZE("i18n.file.error.maxSize"),
	// 此类型文件不允许上传！
	FILE_EXCEPTION_ALLOWEDTYPES("i18n.file.error.allowedTypes"),
	// 此文件不存在！
	FILE_EXCEPTION_FILENOTEXISTS("i18n.file.error.fileNotExists"),
	/*
	 * EXCEL模块
	 */
	// 配置文件中没有对应模块的键值对！
	FILE_EXCEPTION_PROPKEYVALNOTEXISTS("i18n.file.error.propKeyValNotExists"),
	/*
	 * EXCEL导入导出
	 */
	// 此文件不是Excel文件!
	FILE_EXCEPTION_FILENOTEXCEL("i18n.file.error.fileNotExcel"),
	// 此文件无法读取,请确认是否Excel文件!
	FILE_EXCEPTION_FILENOTEXPORT("i18n.file.error.fileNotExport"),
	// 导出文件失败,请重新导出!
	FILE_EXCEPTION_FILECANNOTREAD("i18n.file.error.fileCannotRead"),
	// 文件名长度超过100个字符抛出异常
	FILE_EXCEPTION_FILENAME_TOOLONG("i18n.file.error.fileNameTooLong"),
	// 没有列头，请确认
	FILE_EXCEPTION_FILENOHEADER("i18n.file.error.fileNoHeader"),
	//Ognl解析错误,Map对应标题与实体不符
	FILE_EXCEPTION_OGNLEXPRESSERROR("i18n.file.error.ognlExpressError");
	private String errorCode;

	private FileExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @description 获取异常的异常信息.
	 * @author 安小虎
	 * @version 0.1
	 * @param FileExceptionType
	 * @date 2012-5-5
	 * @return String
	 */
	public static String getValue(FileExceptionType type) {
		switch (type) {
		case FILE_EXCEPTION_MAXSIZE:
			return FILE_EXCEPTION_MAXSIZE.getErrorCode();
		case FILE_EXCEPTION_ALLOWEDTYPES:
			return FILE_EXCEPTION_ALLOWEDTYPES.getErrorCode();
		case FILE_EXCEPTION_FILENOTEXISTS:
			return FILE_EXCEPTION_FILENOTEXISTS.getErrorCode();
		case FILE_EXCEPTION_PROPKEYVALNOTEXISTS:
			return FILE_EXCEPTION_PROPKEYVALNOTEXISTS.getErrorCode();
		case FILE_EXCEPTION_FILENOTEXCEL:
			return FILE_EXCEPTION_FILENOTEXCEL.getErrorCode();
		case FILE_EXCEPTION_FILENOTEXPORT:
			return FILE_EXCEPTION_FILENOTEXPORT.getErrorCode();
		case FILE_EXCEPTION_FILECANNOTREAD:
			return FILE_EXCEPTION_FILECANNOTREAD.getErrorCode();
		case FILE_EXCEPTION_FILENOHEADER:
			return FILE_EXCEPTION_FILENOHEADER.getErrorCode();
		case FILE_EXCEPTION_FILENAME_TOOLONG:
			return FILE_EXCEPTION_FILENAME_TOOLONG.getErrorCode();
		default:
			return FILE_EXCEPTION.getErrorCode();
		}
	}
}