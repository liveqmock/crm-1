/**
 * @Title: DataSynchroDao.java
 * @Package com.deppon.crm.module.logmoniting.server.dao.impl
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-12 下午5:12:44
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.logmoniting.server.dao.ISynchroLogDao;
import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * <p>
 * TODO<br />
 * </p>
 * @title DataSynchroDao.java
 * @package com.deppon.crm.module.logmoniting.server.dao.impl
 * @author 唐亮
 * @version 0.1 2013-9-12
 */
public class SynchroLogDao extends iBatis3DaoImpl implements ISynchroLogDao{
	//命名空间
	private static final String NAMESPACE_DATASYNCHRO = "com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo.";
	//通过条件查询客户同步数据
	private static final String QUERY_DATASYNCHRO = "queryDataSynchro";
	//修改客户主数据同步信息
	private static final String UPDATE_DATASYNCHRO = "updateDataSynchro";
	//统计客户同步数据条数
	private static final String COUNT_DATASYNCHRO = "countDataSynchro";
	/* (非 Javadoc)
	* <p>Title: updateDataSynchroCondition</p>
	* <p>Description: </p>
	* @param condition
	* @see com.deppon.crm.module.logmoniting.server.dao.IDataSynchroDao#updateDataSynchroCondition(com.deppon.crm.module.logmoniting.shared.domain.DataSynchroCondition)
	*/
	@Override
	public void updateDataSynchroCondition(SynchronizeLogInfo synchronizeLogInfo) {
		this.getSqlSession().update(NAMESPACE_DATASYNCHRO+UPDATE_DATASYNCHRO,synchronizeLogInfo);
	}

	
	/* (非 Javadoc)
	* <p>Title: searchDataSynchroList</p>
	* <p>Description: </p>
	* @param condition
	* @return
	* @see com.deppon.crm.module.logmoniting.server.dao.IDataSynchroDao#searchDataSynchroList(com.deppon.crm.module.logmoniting.shared.domain.DataSynchroCondition)
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<SynchronizeLogInfo> searchDataSynchroList(
			int start,int limit,SynchronizeLogInfo synchronizeLogInfo) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return this.getSqlSession().selectList(NAMESPACE_DATASYNCHRO+QUERY_DATASYNCHRO, synchronizeLogInfo,rowBounds);
	}
	
	/* (非 Javadoc)
	* <p>Title: countSynchroData</p>
	* <p>Description: </p>
	* @param synchronizeLogInfo
	* @return
	* @see com.deppon.crm.module.logmoniting.server.dao.ISynchroLogDao#countSynchroData(com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo)
	*/
	@Override
	public int countSynchroData(SynchronizeLogInfo synchronizeLogInfo) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_DATASYNCHRO+COUNT_DATASYNCHRO,synchronizeLogInfo);
	}
}
