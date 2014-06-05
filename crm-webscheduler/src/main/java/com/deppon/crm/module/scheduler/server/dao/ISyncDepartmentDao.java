/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ISyncDepartmentDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author Administrator
 * @version 0.1 2012-7-3
 */
package com.deppon.crm.module.scheduler.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ISyncDepartmentDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author 苏玉军
 * @version 0.1 2012-7-3
 */

public interface ISyncDepartmentDao {
	public List<OaDepartmentEntity> queryUpdateListByTime(Date updateTime);
	
	public int countRecord(Date upadteTime);

	/**
	 * <p>
	 * 新增组织信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-4
	 * @param entity
	 * @return
	 * boolean
	 */
	public boolean insertDept(OaDepartmentEntity entity);
	/**
	 * 根据组织Id查询组织
	 */
	public int countDepartmentByDeptSeq(String id);
	
	/**
	 * 更新组织信息
	 */
	public boolean updateDept(OaDepartmentEntity entity);

	/**
	 * <p>
	 * 通过创建用户删除组织数据(用于测试)<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-4
	 * @param createUser
	 * @return
	 * boolean
	 */
	public boolean deleteByCreateUser(String createUser);

	/**
	 * <p>
	 * 根据Id查询部门<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @param orgId
	 * @return
	 * OaDepartmentEntity
	 */
	public OaDepartmentEntity queryDeptById(int orgId);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-17
	 * @return
	 * Long
	 */
	public Date getSysDate();
}
