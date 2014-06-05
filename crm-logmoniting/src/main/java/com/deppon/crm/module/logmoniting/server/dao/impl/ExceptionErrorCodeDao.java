package com.deppon.crm.module.logmoniting.server.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.deppon.crm.module.logmoniting.server.dao.IExceptionErrorCodeDao;
import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;

public class ExceptionErrorCodeDao implements IExceptionErrorCodeDao {
	private MongoOperations mongoTemplate;

	/**
	 * Description:mongoTemplate<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public MongoOperations getMongoTemplate() {
		return mongoTemplate;
	}

	/**
	 * Description:mongoTemplate<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setMongoTemplate(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void saveExceptionErrorCode(ExceptionErrorCode exceptionErrorCode) {
		mongoTemplate.save(exceptionErrorCode);
	}

	@Override
	public void updateExceptionErrorCode(ExceptionErrorCode exceptionErrorCode) {
		Update update = Update.update("moduleName", exceptionErrorCode.getModuleName());
		update.set("errorCode", exceptionErrorCode.getErrorCode());
		update.set("updateUser",exceptionErrorCode.getUpdateUser());
		update.set("updateTime",exceptionErrorCode.getUpdateTime());
		update.set("exceptionInfo", exceptionErrorCode.getExceptionInfo());
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(exceptionErrorCode.getId())), update,ExceptionErrorCode.class);
	}

	@Override
	public void removeExceptionErrorCode(ExceptionErrorCode errorCode) {
		mongoTemplate.remove(errorCode);
	}

	@Override
	public List<ExceptionErrorCode> getAllExceptionErrorCodes() {
		return mongoTemplate.findAll(ExceptionErrorCode.class);
	}

	@Override
	public List<ExceptionErrorCode> getExceptionErrorCodesByQuery(Query query,
			Class<ExceptionErrorCode> errorCodeClass) {
		return mongoTemplate.find(query, errorCodeClass);
	}
}
