package com.deppon.crm.module.common.file.domain.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * @description 文件exception
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-5
 */

public class FileException extends BusinessException{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 8927081270074039087L;
	/**
	 * constructer
	 */
	public FileException(FileExceptionType type) {
		this.errCode=FileExceptionType.getValue(type);
	}
}
