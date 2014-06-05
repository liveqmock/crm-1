/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IDepartmentDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */
package com.deppon.crm.module.scheduler.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.shared.domain.DepartmentExt;

/**   
 * <p>
 * Description:从OA的部门表导数据到CRM的部门表<br />
 * </p>
 * @title IDepartmentDao.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */

public interface IDepartmentDao {
	
	public List<DepartmentExt> queryOADepartments(Date date);

	public int createCRMDepartment(DepartmentExt department);

	
	
}
