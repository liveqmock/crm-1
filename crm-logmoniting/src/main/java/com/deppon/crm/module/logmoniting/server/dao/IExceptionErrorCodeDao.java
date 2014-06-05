package com.deppon.crm.module.logmoniting.server.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;

public interface IExceptionErrorCodeDao {

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
	 * @Description:删除异常编码<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 * @param id
	 * void
	 */
	public void removeExceptionErrorCode(ExceptionErrorCode errorCode);
	
	/**
	 * @Description:拿到所有的异常errorCode<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 * void
	 */
	public List<ExceptionErrorCode> getAllExceptionErrorCodes();
	
	/**
	 * @Description:根据条件查询异常errorCode<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 * @param query
	 * @param errorClass
	 * @return List<ExceptionErrorCode>
	 */
	public List<ExceptionErrorCode> getExceptionErrorCodesByQuery(Query query,Class<ExceptionErrorCode> errorClass);
}
