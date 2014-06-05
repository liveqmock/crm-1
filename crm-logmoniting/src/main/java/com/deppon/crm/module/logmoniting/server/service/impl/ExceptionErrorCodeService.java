package com.deppon.crm.module.logmoniting.server.service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.logmoniting.server.dao.IExceptionErrorCodeDao;
import com.deppon.crm.module.logmoniting.server.service.IExceptionErrorCodeService;
import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;

public class ExceptionErrorCodeService implements IExceptionErrorCodeService {

	
	private IExceptionErrorCodeDao exceptionErrorCodeDao;

	/**
	 * Description:exceptionErrorCodeDao<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public IExceptionErrorCodeDao getExceptionErrorCodeDao() {
		return exceptionErrorCodeDao;
	}

	/**
	 * Description:exceptionErrorCodeDao<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setExceptionErrorCodeDao(
			IExceptionErrorCodeDao exceptionErrorCodeDao) {
		this.exceptionErrorCodeDao = exceptionErrorCodeDao;
	}
	
	
	@Override
	public void saveExceptionErrorCode(ExceptionErrorCode errorCode) {
		exceptionErrorCodeDao.saveExceptionErrorCode(errorCode);
	}
	
	@Override
	public void updateExceptionErrorCode(ExceptionErrorCode errorCode) {
		exceptionErrorCodeDao.updateExceptionErrorCode(errorCode);
	}

	@Override
	public void removeExceptionErrorCode(ExceptionErrorCode errorCode) {
		exceptionErrorCodeDao.removeExceptionErrorCode(errorCode);
	}

	@Override
	public List<ExceptionErrorCode> getAllExceptionErrorCodes() {
		return exceptionErrorCodeDao.getAllExceptionErrorCodes();
	}

	@Override
	public List<ExceptionErrorCode> getExceptionErrorCodesByQuery(Query query,
			Class<ExceptionErrorCode> errorClass) {
		return exceptionErrorCodeDao.getExceptionErrorCodesByQuery(query, errorClass);
	}

}
