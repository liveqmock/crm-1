package com.deppon.crm.module.logmoniting.shared.domain;

/**   
 * @Description:异常编码信息实体对象，用于发送邮件<br />
 * @title ErrorCodeInfo.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-7-30
 */
public class ErrorCodeInfo {
	//模块名
	private String moduleName;
	//异常编码
	private String errorCode;
	//异常信息
	private String exceptionInfo;
	//总共次数
	private long count;
	/**
	 * Description:moduleName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * Description:moduleName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * Description:errorCode<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * Description:errorCode<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * Description:exceptionInfo<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public String getExceptionInfo() {
		return exceptionInfo;
	}
	/**
	 * Description:exceptionInfo<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}
	/**
	 * Description:count<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public long getCount() {
		return count;
	}
	/**
	 * Description:count<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public void setCount(long count) {
		this.count = count;
	}
}
