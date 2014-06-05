package com.deppon.crm.module.frameworkimpl.server.dao;

import java.util.List;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;


/**
 * 日志处理类
 * @author Administrator
 *
 */
public interface ILogDao {

	/**
	 * 保存日志
	 * @param logInfo
	 */
	void insert(Object logInfo);
	/**
	 * 保存日志
	 * @param logInfoList
	 */
	void insert(List<?> logInfoList);
}
