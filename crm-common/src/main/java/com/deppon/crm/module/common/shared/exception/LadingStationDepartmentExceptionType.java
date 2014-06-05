package com.deppon.crm.module.common.shared.exception;


public enum LadingStationDepartmentExceptionType {

	/**
	 * 所导入的文件不存在
	 */
	FILE_NOT_FOUND("i18n.file.error.fileNotExists"),

	/**
	 * 导入的文件格式不正确
	 */
	WRONG_FILE_FORMAT("i18n.file.error.notXLSFile"),

	/**
	 * 文件校验错误
	 */
	FILE_CHECK_ERROR("i18n.file.check.error"),
	
	/**
	 * 文件上传出错
	 */
	FILE_UPLOAD_ERROR("i18n.file.upload.error"),
	/**
	 * 相同网点已存在
	 */
	LADINGSTATION_EXISTS("i18n.ladingstation.exists"),
	/**
	 * 不存在对应的始发网点
	 */
	BEGIN_LADING_STATION_DEPT_NOT_EXIST("i18n.ladingstation.beginLDNotExist"),
	
	/**
	 * 不存在对应的受理部门
	 */
	ACCEPT_DEPT_NOT_EXIST("i18n.ladingstation.acceptLDNotExist"),
	
	/**
	 * 逻辑校验错误
	 */
	LOGICAL_CHECK_EXCEPTION("i18n.ladingstation.logicalCheckException"),
	
	LADING_PROVICE_IS_NULL("i18n.ladingstation.ladingProvinceIsNull")
	;
	
	
	private String errorCode;

	private LadingStationDepartmentExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public static String getValue(LadingStationDepartmentExceptionType type) {
		switch (type) {
		case FILE_NOT_FOUND:
			return FILE_NOT_FOUND.getErrorCode();
			
		case WRONG_FILE_FORMAT:
			return WRONG_FILE_FORMAT.getErrorCode();
		
		case FILE_CHECK_ERROR:
			return FILE_CHECK_ERROR.getErrorCode();
		
		case FILE_UPLOAD_ERROR:
			return FILE_UPLOAD_ERROR.getErrorCode();			

		case LADINGSTATION_EXISTS:
			return LADINGSTATION_EXISTS.getErrorCode();	
			
		case BEGIN_LADING_STATION_DEPT_NOT_EXIST:
			return BEGIN_LADING_STATION_DEPT_NOT_EXIST.getErrorCode();
			
		case ACCEPT_DEPT_NOT_EXIST:
			return ACCEPT_DEPT_NOT_EXIST.getErrorCode();
			
		case LOGICAL_CHECK_EXCEPTION:
			return LOGICAL_CHECK_EXCEPTION.getErrorCode();
		
		default :
			return FILE_NOT_FOUND.getErrorCode();
		}
	}	
	
	
}
