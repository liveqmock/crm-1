/**
 * @Title: SynchoLogService.java
 * @Package com.deppon.crm.module.logmoniting.server.service.impl
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-16 下午4:15:57
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.service.impl;

import java.util.List;

import com.deppon.crm.module.logmoniting.server.dao.ISynchroLogDao;
import com.deppon.crm.module.logmoniting.server.service.ISynchroLogService;
import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;

/**
 * <p>
 * TODO<br />
 * </p>
 * @title SynchoLogService.java
 * @package com.deppon.crm.module.logmoniting.server.service.impl
 * @author 唐亮
 * @version 0.1 2013-9-16
 */
public class SynchroLogService implements ISynchroLogService{

	private ISynchroLogDao synchroLogDao;
	
	/**
	 * 
	 * Description:synchroLogDao<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-16
	 */
	public void setSynchroLogDao(ISynchroLogDao synchroLogDao) {
		this.synchroLogDao = synchroLogDao;
	}


	/* (非 Javadoc)
	* <p>Title: modifyDataSynchro</p>
	* <p>Description: </p>
	* @param synchronizeLogInfo
	* @see com.deppon.crm.module.logmoniting.server.service.ISynchroLogService#modifyDataSynchro(com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo)
	*/
	@Override
	public void modifyDataSynchro(SynchronizeLogInfo synchronizeLogInfo) {
		synchroLogDao.updateDataSynchroCondition(synchronizeLogInfo);
	}

	
	/* (非 Javadoc)
	* <p>Title: searchDataSynchro</p>
	* <p>Description: </p>
	* @param start
	* @param limit
	* @param synchronizeLogInfo
	* @return
	* @see com.deppon.crm.module.logmoniting.server.service.ISynchroLogService#searchDataSynchro(int, int, com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo)
	*/
	@Override
	public List<SynchronizeLogInfo> searchDataSynchro(int start, int limit,
			SynchronizeLogInfo synchronizeLogInfo) {
		return synchroLogDao.searchDataSynchroList(start, limit, synchronizeLogInfo);
	}


	
	/* (非 Javadoc)
	* <p>Title: countSynchroData</p>
	* <p>Description: </p>
	* @param synchronizeLogInfo
	* @return
	* @see com.deppon.crm.module.logmoniting.server.service.ISynchroLogService#countSynchroData(com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo)
	*/
	@Override
	public int countSynchroData(SynchronizeLogInfo synchronizeLogInfo) {
		return synchroLogDao.countSynchroData(synchronizeLogInfo);
	}

}
