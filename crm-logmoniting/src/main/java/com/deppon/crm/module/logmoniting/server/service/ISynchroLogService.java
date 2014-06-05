/**
 * @Title: IDataSynchroService.java
 * @Package com.deppon.crm.module.logmoniting.server.service
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-12 下午5:34:40
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.service;

import java.util.List;

import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;

/**
 * <p>
 * TODO<br />
 * </p>
 * @title IDataSynchroService.java
 * @package com.deppon.crm.module.logmoniting.server.service
 * @author 唐亮
 * @version 0.1 2013-9-12
 */
public interface ISynchroLogService {
	/**
	 * 
	 * @Title: modifyDataCharo
	 *  <p>
	 * @Description: 同步日志修改方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-12
	 * @return void
	 * @throws
	 */
	public void modifyDataSynchro(SynchronizeLogInfo synchronizeLogInfo);
	/**
	 * 
	 * @Title: searchDataSynchro
	 *  <p>
	 * @Description: 同步日志查询方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-12
	 * @return List<DataSynchroCondition>
	 * @throws
	 */
	public List<SynchronizeLogInfo> searchDataSynchro(int start,int limit,SynchronizeLogInfo synchronizeLogInfo);
	
	/**
	 * 
	 * @Title: countSynchroData
	 *  <p>
	 * @Description: 统计客户同步数据条数
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-17
	 * @return int
	 * @throws
	 */
	public int countSynchroData(SynchronizeLogInfo synchronizeLogInfo);
}
