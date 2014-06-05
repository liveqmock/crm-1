package com.deppon.crm.module.logmoniting.server.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.logmoniting.server.manager.IExceptionErrorCodeManager;
import com.deppon.crm.module.logmoniting.server.service.IExceptionErrorCodeService;
import com.deppon.crm.module.logmoniting.server.utils.ValidateUtil;
import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoException;
import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoExceptionType;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.shared.domain.ErrorCodeCondition;
import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;
import com.deppon.foss.framework.server.context.UserContext;


public class ExceptionErrorCodeManager implements IExceptionErrorCodeManager{

	private IExceptionErrorCodeService exceptionErrorCodeService;
	
	/**
	 * Description:exceptionErrorCodeService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public IExceptionErrorCodeService getExceptionErrorCodeService() {
		return exceptionErrorCodeService;
	}

	/**
	 * Description:exceptionErrorCodeService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setExceptionErrorCodeService(
			IExceptionErrorCodeService exceptionErrorCodeService) {
		this.exceptionErrorCodeService = exceptionErrorCodeService;
	}
	
	@Override
	public void saveExceptionErrorCode(ExceptionErrorCode errorCode) {
		//验证对象以及传入参数是否正确
		if(this.vadateExceptionErrorCode(errorCode,Constant.ADD)){
			errorCode.setCreateTime(System.currentTimeMillis());
			errorCode.setCreateUser(UserContext.getCurrentUser().getId());
			exceptionErrorCodeService.saveExceptionErrorCode(errorCode);
		};		
	}

	@Override
	public void updateExceptionErrorCode(ExceptionErrorCode errorCode) {
		//验证对象以及传入参数是否正确
		if(this.vadateExceptionErrorCode(errorCode,Constant.UPDATE)){
			errorCode.setUpdateTime(System.currentTimeMillis());
			errorCode.setUpdateUser(UserContext.getCurrentUser().getId());
			exceptionErrorCodeService.updateExceptionErrorCode(errorCode);
		};
	}

	@Override
	public void deleteExceptionErrorCodeById(String id) {
		//验证ID是否为空
		if (StringUtils.isEmpty(id)) {
			throw new LogInfoException(LogInfoExceptionType.ObjectParames_Are_Null);
		}
		ExceptionErrorCode errorCode = new ExceptionErrorCode();
		errorCode.setId(id);
		exceptionErrorCodeService.removeExceptionErrorCode(errorCode);
	}
	
	
	/**
	 * @Description:验证对象以及传入参数是否正确<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 * @param errorCode
	 * @return
	 * boolean
	 */
	private boolean vadateExceptionErrorCode(ExceptionErrorCode errorCode,String type){
		boolean flag = false;
		if (ValidateUtil.objectIsEmpty(errorCode)) {
			//对象为空，抛异常
			throw new LogInfoException(LogInfoExceptionType.Object_is_Null);
		}else {
			//验证errorCode的moduleName（模块名）和errorCode（异常编码）exceptionInfo（异常信息）是否为空
			if(StringUtils.isEmpty(errorCode.getModuleName())
					&&StringUtils.isEmpty(errorCode.getErrorCode())
					&&StringUtils.isEmpty(errorCode.getExceptionInfo())){
				throw new LogInfoException(LogInfoExceptionType.ModuleName_ErrorCode_ExceptionInfo_isNull);
			}
			if (Constant.ADD.equals(type)) {
				Query query = new Query();
				query.addCriteria(Criteria.where("errorCode").is(errorCode.getErrorCode()));
				List<ExceptionErrorCode> list = exceptionErrorCodeService.getExceptionErrorCodesByQuery(query, ExceptionErrorCode.class);
				if (list != null && list.size() > 0 ) {
					throw new LogInfoException(LogInfoExceptionType.ErrorObject_HasAlreadyExsits);
				}
			}
			
			flag = true;
		}
		return flag;
	}
	
	@Override
	public List<ExceptionErrorCode> searchExceptionErrorCodesByCondition(
			ErrorCodeCondition errorCodeConditione,String type) {
		if (ValidateUtil.objectIsEmpty(errorCodeConditione)) {
			//对象为空，抛异常
			throw new LogInfoException(LogInfoExceptionType.Object_is_Null);
		}
		List<ExceptionErrorCode> list = null;
		Query query = null;
		if (StringUtils.isEmpty(errorCodeConditione.getModuleName())
			&&StringUtils.isEmpty(errorCodeConditione.getErrorCode())
			&&StringUtils.isEmpty(errorCodeConditione.getExceptionInfo())) {
			if (!StringUtils.isEmpty(type)&&Constant.WHETHER_PAGING.equals(type)) {
				query = new Query();
				query.skip(errorCodeConditione.getStart()).limit(errorCodeConditione.getLimit());
				list = exceptionErrorCodeService.getExceptionErrorCodesByQuery(query, ExceptionErrorCode.class);
			}else {
				list = exceptionErrorCodeService.getAllExceptionErrorCodes();
			}
		}else {
			query = new Query();
			if (!StringUtils.isEmpty(errorCodeConditione.getErrorCode())) {
				query.addCriteria(Criteria.where("errorCode").is(errorCodeConditione.getErrorCode()));
			}
			if (!StringUtils.isEmpty(errorCodeConditione.getModuleName())) {
				query.addCriteria(Criteria.where("moduleName").is(errorCodeConditione.getModuleName()));
			}
			if (!StringUtils.isEmpty(errorCodeConditione.getExceptionInfo())) {
				query.addCriteria(Criteria.where("exceptionInfo").is(errorCodeConditione.getExceptionInfo()));
			}
			if (!StringUtils.isEmpty(type)&&Constant.WHETHER_PAGING.equals(type)) {
				query.skip(errorCodeConditione.getStart()).limit(errorCodeConditione.getLimit());
			}
			list = exceptionErrorCodeService.getExceptionErrorCodesByQuery(query, ExceptionErrorCode.class);
		}
		return list;
	}
	
}
