package com.deppon.crm.module.frameworkimpl.server.service;

import java.util.List;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.foss.framework.service.IService;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:日志服务类</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
*  1       2011-6-26            ztjie      新增
* </div>  
********************************************
 */
public interface ILogService extends IService{

	/**
	 * 保存日志对象
	 * @param id
	 * @param log
	 */
	public void save(LogInfo msg);
	/**
	 * 保存日志对象
	 * @param msgs
	 */
	public void save(List<?> msgs);
}
