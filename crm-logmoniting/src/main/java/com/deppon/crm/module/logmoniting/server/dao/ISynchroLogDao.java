/**
 * @Title: IDataSynchroDao.java
 * @Package com.deppon.crm.module.logmoniting.server.dao
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-12 下午5:03:05
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.dao;

import java.util.List;

import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;


/**
 * <p>
 * TODO<br />
 * </p>
 * @title IDataSynchroDao.java
 * @package com.deppon.crm.module.logmoniting.server.dao
 * @author 唐亮
 * @version 0.1 2013-9-12
 */
public interface ISynchroLogDao {
	/**
	 * 
	 * @Title: updateDataSynchroCondition
	 *  <p>
	 * @Description: 客户主数据同步信息修改<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-12
	 * @return void
	 * @throws
	 */
	public void updateDataSynchroCondition(SynchronizeLogInfo synchronizeLogInfo);
	
	/**
	 * 
	 * @Title: searchDataSynchroList
	 *  <p>
	 * @Description: 查询客户同步数据
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-12
	 * @return List<DataSynchroCondition>
	 * @throws
	 */
	public List<SynchronizeLogInfo> searchDataSynchroList(int start,int limit,SynchronizeLogInfo synchronizeLogInfo);

	/**
	 * 
	 * @Title: countSynchroData
	 *  <p>
	 * @Description: 统计同步数据条数
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-17
	 * @return int
	 * @throws
	 */
	public int countSynchroData(SynchronizeLogInfo synchronizeLogInfo);
}
