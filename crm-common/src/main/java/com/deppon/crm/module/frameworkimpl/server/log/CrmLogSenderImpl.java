package com.deppon.crm.module.frameworkimpl.server.log;

import java.io.Serializable;
import java.util.List;

import com.deppon.crm.module.frameworkimpl.server.service.ILogService;
import com.deppon.foss.framework.server.components.logger.ILogSender;



/**
*******************************************
* Copyright deppon.com.
* All rights reserved.
* Description:   日志发送数据库实现
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-6-26            ztjie      新增
********************************************
 */
public class CrmLogSenderImpl implements ILogSender {

    private ILogService logServer;


    public void setLogServer(ILogService logServer) {
		this.logServer = logServer;
	}


	@Override
	public void send(List<Object> msg) {
		logServer.save((List<?>)msg);
	}
}
