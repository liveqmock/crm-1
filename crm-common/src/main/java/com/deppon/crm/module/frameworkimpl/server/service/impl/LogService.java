package com.deppon.crm.module.frameworkimpl.server.service.impl;

import java.util.List;

import com.deppon.crm.module.frameworkimpl.server.dao.ILogDao;
import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.service.ILogService;

public class LogService implements ILogService {

	private ILogDao logDao;

	@Override
	public void save(LogInfo logInfo) {
		logDao.insert(logInfo);
	}
	
	public void setLogDao(ILogDao logDao){
		this.logDao=logDao;
	}

	@Override
	public void save(List<?> logInfoList) {
		logDao.insert(logInfoList);
	}
}
