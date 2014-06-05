package com.deppon.crm.module.logmoniting.shared.domain;

/**   
 * @Description:异常编码实体类<br />
 * @title ExceptionErrorCode.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-7-29
 */
public class ExceptionErrorCode {

	//主键ID
	private String id;
	//创建人
	private String createUser;
	//创建时间
	private long createTime;
	//修改人
	private String updateUser;
	//修改时间
	private long updateTime;
	//模块名
	private String moduleName;
	//异常编码
	private String errorCode;
	//异常信息
	private String exceptionInfo;

	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public String getId() {
		return id;
	}
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Description:createUser<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * Description:createUser<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * Description:createTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public long getCreateTime() {
		return createTime;
	}
	/**
	 * Description:createTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	/**
	 * Description:updateUser<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * Description:updateUser<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * Description:updateTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public long getUpdateTime() {
		return updateTime;
	}
	/**
	 * Description:updateTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * Description:moduleName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * Description:moduleName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * Description:errorCode<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * Description:errorCode<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * Description:exceptionInfo<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public String getExceptionInfo() {
		return exceptionInfo;
	}
	/**
	 * Description:exceptionInfo<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}
}
