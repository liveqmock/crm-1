package com.deppon.crm.module.common.server.manager;

import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;

/**
 * 
 * <p>
 * Description:点部与营业部映射关系<br />
 * </p>
 * 
 * @title ExpressPointBusinessDeptImpl.java
 * @package com.deppon.crm.module.common.server.manager
 * @author roy
 * @version 0.1 2013-7-31
 */
public interface IExpressPointBusinessDeptManager {
	/**
	 * 
	 * <p>
	 * Description:插入点部与营业部映射关系<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-31
	 * @param dept
	 * @return          void
	 */
	void insertExpressPointBusinessDept(ExpressPointBusinessDept dept);

	/**
	 * 
	 * <p>
	 * Description:根据营业部标杆编码更新对应的点部<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-31
	 * @param dept
	 * @return           void
	 */
	void updateExpressPointBusinessDept(ExpressPointBusinessDept dept);

	/**
	 * Description:根据营业部标杆编码查询对应的点部<br />
	 * @author roy
	 * @version 0.1 2013-7-31
	 * @param deptCode
	 * @return           ExpressPointBusinessDept
	 */
	ExpressPointBusinessDept getExpressPointBusinessDeptByDeptCode(
			String deptCode);
	/**
	 * 
	 * <p>
	 * Description:根据营业部标杆编码删除对应的映射关系<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-8-12
	 * @param code
	 * @return
	 * boolean
	 */
	void deleteExpressPointBusinessDeptByDeptCode(String deptCode,String pionCode); 

}
