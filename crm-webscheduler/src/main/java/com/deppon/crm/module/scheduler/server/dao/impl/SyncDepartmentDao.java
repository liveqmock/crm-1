/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SyncDepartmentDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-7-3
 */
package com.deppon.crm.module.scheduler.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao;
import com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SyncDepartmentDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author 苏玉军
 * @version 0.1 2012-7-3
 */

public class SyncDepartmentDao extends iBatis3DaoImpl implements ISyncDepartmentDao {
	private static final String NAMESPACE="com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity.";
	private static final String QUERY_LIST_BYUPDATETIME = "queryListByUpdateTime";
	private static final String INSERT_DEPT = "insertDept";
	private static final String UPDATE_DEPT="updateDept";
	private static final String COUNT_DEPT_BYSEQ = "countDepartmentByDeptSeq";
	private static final String COUNT_RECORD="countRecord";
	private static final String DELETE_BYCREATEUSER="deleteByCreateUser";
	private static final String QUERY_DEPT_BYID="queryDeptById";
	private static final String GET_SYSDATE="getSysDate";
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#queryListByUpdateTime()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OaDepartmentEntity> queryUpdateListByTime(Date lastUpdateTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", lastUpdateTime);
		return this.getSqlSession().selectList(NAMESPACE+QUERY_LIST_BYUPDATETIME, map);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#countRecord(java.util.Date)
	 */
	@Override
	public int countRecord(Date upadteTime) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE+COUNT_RECORD, upadteTime);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#insertDept(com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity)
	 */
	@Override
	public boolean insertDept(OaDepartmentEntity entity) {
		return this.getSqlSession().insert(NAMESPACE+INSERT_DEPT, entity) > 0 ? true : false;
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#queryDepartmentById(java.lang.String)
	 */
	@Override
	public int countDepartmentByDeptSeq(String id) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE+COUNT_DEPT_BYSEQ, id);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#updateDept(com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity)
	 */
	@Override
	public boolean updateDept(OaDepartmentEntity entity) {
		return this.getSqlSession().update(NAMESPACE+UPDATE_DEPT,entity) >0 ? true : false;
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#deleteByCreateUser(java.lang.String)
	 */
	@Override
	public boolean deleteByCreateUser(String createUser) {
		return this.getSqlSession().delete(NAMESPACE+DELETE_BYCREATEUSER, createUser) > 0 ? true : false;
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#queryDeptById(int)
	 */
	@Override
	public OaDepartmentEntity queryDeptById(int orgId) {
		return (OaDepartmentEntity) this.getSqlSession().selectOne(NAMESPACE+QUERY_DEPT_BYID, orgId);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao#getSysDate()
	 */
	@Override
	public Date getSysDate() {
		return (Date) this.getSqlSession().selectOne(NAMESPACE+GET_SYSDATE);
	}
}
