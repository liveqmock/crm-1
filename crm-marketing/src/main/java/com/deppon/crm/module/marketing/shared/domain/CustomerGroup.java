/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroup.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:客户分组管理<br />
 * </p>
 * @title CustomerGroup.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */

public class CustomerGroup extends BaseEntity {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 4287525551882755879L;
	
	// 组名
	private String groupName;
	// 部门id
	private String deptId;
	// 客户计数
	private int count;
	// 维护人
	private String empId;
	
	/**
	 * @return empId : return the property empId.
	 */
	public String getEmpId() {
		return empId;
	}


	/**
	 * @param empId : set the property empId.
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}


	/**
	 * 
	 * <p>
	 * Description:判断是否存在重名<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @param groups
	 * @return
	 * boolean
	 */
	public boolean isExistedInDept(List<CustomerGroup> groups) {
		// validate parameter
		if (CollectionUtils.isEmpty(groups)) {
			//否
			return false;
		}
		// loop check
		for (CustomerGroup group : groups) {
			//遍历判断
			if (StringUtils.equals(groupName, group.getGroupName())) {
				//是
				return true;
			}
		}
		//否
		return false;
	}
	
	
	/**
	 * @return groupName : return the property groupName.
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName : set the property groupName.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return deptId : return the property deptId.
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId : set the property deptId.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return count : return the property count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count : set the property count.
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
