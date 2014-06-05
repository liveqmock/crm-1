/**
 * @Title: ISynchoLogManager.java
 * @Package com.deppon.crm.module.logmoniting.server.manager
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-16 下午4:23:05
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.manager;

import java.util.List;

import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;

/**
 * <p>
 * TODO<br />
 * </p>
 * @title ISynchoLogManager.java
 * @package com.deppon.crm.module.logmoniting.server.manager
 * @author 唐亮
 * @version 0.1 2013-9-16
 */
public interface ISynchroLogManager {
	/**
	 * 
	 * @Title: modifySynchroLog
	 *  <p>
	 * @Description: 同步日志修改
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-16
	 * @return void
	 * @throws
	 */
	public void modifySynchroLog(SynchronizeLogInfo synchronizeLogInfo);
	
	/**
	 * 
	 * @Title: searchSynchroLogInfos
	 *  <p>
	 * @Description: 同步日志查询
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-16
	 * @return List<SynchronizeLogInfo>
	 * @throws
	 */
	public List<SynchronizeLogInfo> searchSynchroLogInfos(int start,int limit,SynchronizeLogInfo synchronizeLogInfo);

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
