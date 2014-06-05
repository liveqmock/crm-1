/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SyncEmployeeDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-7-5
 */
package com.deppon.crm.module.scheduler.server.dao.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao;
import com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * 人员同步接口<br />
 * </p>
 * @title SyncEmployeeDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author 苏玉军
 * @version 0.1 2012-7-5
 */

public class SyncEmployeeDao extends iBatis3DaoImpl implements ISyncEmployeeDao {
	private static final String NAMESPACE="com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity.";
	private static final String QUERY_EMPLOYEE_BYDATE="queryEmployeeByDate";
	private static final String COUNT_EMPLOYEE_BYEMPCODE="countEmpByEmpCode";
	private static final String INSERTEMPLOYEE="insertEmployee";
	private static final String UPDATEEMPLOYEE="updateEmployee";
	private static final String DELETEEMPLOYEE="deleteEmployee";
	private static final String GET_SYSDATE="getSysDate";
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao#queryEmployeeByDate(java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OaEmployeeEntity> queryEmployeeByDate(Date date) {
		return this.getSqlSession().selectList(NAMESPACE+QUERY_EMPLOYEE_BYDATE, date);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao#countEmpByEmpCode(java.lang.String)
	 */
	@Override
	public int countEmpByEmpCode(String empCode) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE+COUNT_EMPLOYEE_BYEMPCODE, empCode);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao#insertEmployee(com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity)
	 */
	@Override
	public boolean insertEmployee(OaEmployeeEntity entity) {
		return this.getSqlSession().insert(NAMESPACE+INSERTEMPLOYEE, entity) > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao#updateEmployee(com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity)
	 */
	@Override
	public boolean updateEmployee(OaEmployeeEntity entity) {
		return this.getSqlSession().update(NAMESPACE+UPDATEEMPLOYEE, entity) > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao#deleteEmployee(java.lang.String)
	 */
	@Override
	public boolean deleteEmployee(String empCode) {
		return this.getSqlSession().delete(NAMESPACE+DELETEEMPLOYEE, empCode) >0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao#getSysDate()
	 */
	@Override
	public Date getSysDate() {
		return (Date) this.getSqlSession().selectOne(NAMESPACE+GET_SYSDATE);
	}

}
