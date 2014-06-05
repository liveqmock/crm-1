/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ISyncEmployeeDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author Administrator
 * @version 0.1 2012-7-5
 */
package com.deppon.crm.module.scheduler.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity;

/**   
 * <p>
 * 人员同步接口<br />
 * </p>
 * @title ISyncEmployeeDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author 苏玉军
 * @version 0.1 2012-7-5
 */

public interface ISyncEmployeeDao {
	public List<OaEmployeeEntity> queryEmployeeByDate(Date date);
	
	public int countEmpByEmpCode(String empCode);

	/**
	 * <p>
	 * 新增Employee信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @param entity
	 * @return
	 * boolean
	 */
	public boolean insertEmployee(OaEmployeeEntity entity);

	/**
	 * <p>
	 * 更新Employee信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @param entity
	 * @return
	 * boolean
	 */
	public boolean updateEmployee(OaEmployeeEntity entity);

	/**
	 * <p>
	 * 删除测试数据<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @param empCode
	 * @return
	 * boolean
	 */
	public boolean deleteEmployee(String empCode);

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
