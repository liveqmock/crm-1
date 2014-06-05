/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DepartmentDaoImpl.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */
package com.deppon.crm.module.scheduler.server.dao.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.server.dao.IDepartmentDao;
import com.deppon.crm.module.scheduler.shared.domain.DepartmentExt;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DepartmentDaoImpl.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */

public class DepartmentDaoImpl extends iBatis3DaoImpl implements IDepartmentDao {

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.IDepartmentDao#queryOADepartments()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentExt> queryOADepartments(Date date) {
		return (List<DepartmentExt>) getSqlSession().selectList(getClass().getName() + ".queryOADepartments", date);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.dao.IDepartmentDao#createCRMDepartment(com.deppon.crm.module.scheduler.shared.domain.DepartmentExt)
	 */
	@Override
	public int createCRMDepartment(DepartmentExt department) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static void main(String[] args) {
		System.out.println(DepartmentDaoImpl.class.getName());
	}

}
