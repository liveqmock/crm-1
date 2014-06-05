package com.deppon.crm.module.logmoniting.server.manager;

import java.util.List;

import com.deppon.crm.module.logmoniting.shared.domain.ErrorCodeCondition;
import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;

/**   
 * @Description:异常编码的管理类<br />
 * @title IExceptionErrorCodeManager.java
 * @package com.deppon.crm.module.logmoniting.server.manager 
 * @author CoCo
 * @version 0.1 2013-7-29
 */
public interface IExceptionErrorCodeManager {

	/**
	 * @Description:异常编码保存<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 * @param errorCode
	 * void
	 */
	public void saveExceptionErrorCode(ExceptionErrorCode errorCode);
	
	/**
	 * @Description:异常编码的修改<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 * @param errorCode
	 * void
	 */
	public void updateExceptionErrorCode(ExceptionErrorCode errorCode);
	
	
	/**
	 * @Description:根据ID删除异常编码<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 * @param id
	 * void
	 */
	public void deleteExceptionErrorCodeById(String id);
	
	/**
	 * @Description:根据条件查询异常编码<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 * @param errorCodeCondition
	 * @return List<ExceptionErrorCode>
	 */
	public List<ExceptionErrorCode> searchExceptionErrorCodesByCondition(ErrorCodeCondition errorCodeCondition,String type);
}
