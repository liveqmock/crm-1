/**   
 * @title RoleDeptRalation.java
 * @package com.deppon.crm.recompense.domain
 * @description what to do
 * @author 潘光均
 * @update 2012-2-23 下午2:25:02
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.shared.domain;

import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * @description 大区设置实体
 * @author 潘光均
 * @version 0.1 2012-2-23
 * @date 2012-2-23
 */

public class UserRoleDeptRelation {
	///编号
	private String id;
	//角色
	private Role role;
	//部门
	private Department dept;
	//用户
	private User user;
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getId() {
		return id;
	}
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * <p>
	 * Description:role<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * <p>
	 * Description:role<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * <p>
	 * Description:dept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Department getDept() {
		return dept;
	}
	/**
	 * <p>
	 * Description:dept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}
	/**
	 * <p>
	 * Description:user<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public User getUser() {
		return user;
	}
	/**
	 * <p>
	 * Description:user<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUser(User user) {
		this.user = user;
	}

	

}
