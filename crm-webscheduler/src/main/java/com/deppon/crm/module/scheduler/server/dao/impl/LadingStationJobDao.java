/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LadingStationJobDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-6-1
 */
package com.deppon.crm.module.scheduler.server.dao.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao;
import com.deppon.crm.module.scheduler.shared.domain.LadingStation;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LadingStationJobDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-6-1
 */

public class LadingStationJobDao extends iBatis3DaoImpl implements ILadingStationJobDao {

	private final String NAMESPACE_LADINGSTATIONGROUP="com.deppon.crm.module.scheduler.shared.domain.LadingStation.";
	// 根据ERP ID查询网点
	private final String QUERY_CRM_LADINGSTATIONBYERPID = "queryCRMLadingStationByErpID";
	// 新增CRM网点-自营
	private final String INSERT_CRM_LADINGSTATION_SEL = "insertLadingStation_Self";
	// 新增CRM网点-代理
	private final String INSERT_CRM_LADINGSTATION_OUT = "insertLadingStation_OUT";
	// 更新CRM网点
	private final String UPDATW_CRM_LADINGSTATION = "updateLadingStation";
	// 根据日期获取ERP网点信息
	private final String QUERY_ERP_LADINGSTATION = "queryERPLadingStation";
	// 根据日期获取ERP部门信息
	private final String QUERY_ERP_BUSINESSDEPT = "queryERPBusinessDept";
	// 根据日期获取ERP部门信息
	private final String QUERY_ERP_BUSINESSDEPT_CAR = "queryERPBusinessDept_Car";
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao#queryCRMLadingStationByErpID(java.lang.String)
	 */
	@Override
	public LadingStation queryCRMLadingStationByErpID(String id) {
		return (LadingStation) this.getSqlSession().selectOne(NAMESPACE_LADINGSTATIONGROUP+QUERY_CRM_LADINGSTATIONBYERPID,id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao#createLadingStation(com.deppon.crm.module.scheduler.shared.domain.LadingStation)
	 */
	@Override
	public boolean createLadingStation(LadingStation ls) {		
		// 保存网点数据
		// 自营网点主键：同FORGID
		// 				车队数据：系统自增
		// 代理网点主键：系统自增
		if (ls.getIsOutward().equals("0")){
			if (ls.getIsTeam().equals("1")){
				return this.getSqlSession().insert(NAMESPACE_LADINGSTATIONGROUP+INSERT_CRM_LADINGSTATION_OUT, ls) > 0;
			}
			ls.setId(ls.getOrgId());
			return this.getSqlSession().insert(NAMESPACE_LADINGSTATIONGROUP+INSERT_CRM_LADINGSTATION_SEL, ls) > 0;
		}else{
			return this.getSqlSession().insert(NAMESPACE_LADINGSTATIONGROUP+INSERT_CRM_LADINGSTATION_OUT, ls) > 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao#updateLadingStation(com.deppon.crm.module.scheduler.shared.domain.LadingStation)
	 */
	@Override
	public boolean updateLadingStation(LadingStation ls) {
		return this.getSqlSession().insert(NAMESPACE_LADINGSTATIONGROUP+UPDATW_CRM_LADINGSTATION, ls) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao#queryERPLadingStationByDate(java.util.Date)
	 */
	@Override
	public List<LadingStation>  queryERPLadingStationByDate(Date date) {
		return this.getSqlSession().selectList(NAMESPACE_LADINGSTATIONGROUP+QUERY_ERP_LADINGSTATION, date);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao#queryERPBusinessDeptByDate(java.util.Date)
	 */
	@Override
	public List<LadingStation>  queryERPBusinessDeptByDate(Date date) {
		return this.getSqlSession().selectList(NAMESPACE_LADINGSTATIONGROUP+QUERY_ERP_BUSINESSDEPT, date);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao#queryERPBusinessDeptCarByDate(java.util.Date)
	 */
	@Override
	public List<LadingStation> queryERPBusinessDeptCarByDate(Date date) {
		return this.getSqlSession().selectList(NAMESPACE_LADINGSTATIONGROUP+QUERY_ERP_BUSINESSDEPT_CAR, date);
	}

	
}
