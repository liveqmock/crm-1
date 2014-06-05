/**   
 * @title LadingstationDepartment.java
 * @package com.deppon.crm.module.common.shared.domain
 * @description what to do
 * @author 安小虎
 * @update 2012-3-22 下午2:54:45
 * @version V1.0   
 */
package com.deppon.crm.module.common.shared.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @description 始发网点受理部门关系实体
 * @author 安小虎
 * @version 0.1 2012-3-22
 * @date 2012-3-22
 */
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})
public class LadingstationDepartment extends BaseEntity implements Serializable{

	/**
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = -5106473932218814298L;

	/**
	 * 实体字段
	 */
	
	// 是否接货
	private String ifReceive;
	//订单来源
	private String resource;
	//始发部门(废弃字段)
	private BussinessDept beginLading;
	//受理部门(废弃字段)
	private BussinessDept acceptDept;

	//始发部门
	private Department beginLadingDeptN;
	
	//受理部门
	private Department acceptDeptN;
	
	//字段标示该关系是否有效
	private Integer isValid;	

	
	/**
	 * 非实体字段
	 */
	
	//始发部门名称（非实体关联字段，只是用于查找和显示）
	private String beginDepName;

	//受理部门名称（非实体关联字段，只是用于查找和显示）
	private String acceptDepName;	
	
	//始发部门标杆编码（非实体关联字段，只是用于查找和显示）
	private String beginDeptStandardCode;
	
	//受理部门标杆编码（非实体关联字段，只是用于查找和显示）
	private String acceptDeptStandCode;
	
	//最后修改人名字(非实体关联字段，只是用于查找和显示)
	private String modifyUserName;
	
	//创建人名字(非实体关联字段，只是用于查找和显示)
	private String createUserName;
	
	//创建时间（从何时开始）(非实体关联字段，只是用于查找和显示)
	private Date createDateBegin;
	
	//创建时间（到何时为止）(非实体关联字段，只是用于查找和显示)
	private Date createDateEnd;
	
	public String getBeginDepName() {
		return beginDepName;
	}

	public void setBeginDepName(String beginDepName) {
		this.beginDepName = beginDepName;
	}

	public String getAcceptDepName() {
		return acceptDepName;
	}

	public void setAcceptDepName(String acceptDepName) {
		this.acceptDepName = acceptDepName;
	}

	public String getIfReceive() {
		return ifReceive;
	}

	public void setIfReceive(String ifReceive) {
		this.ifReceive = ifReceive;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public BussinessDept getBeginLading() {
		return beginLading;
	}

	public void setBeginLading(BussinessDept beginLading) {
		this.beginLading = beginLading;
	}

	public BussinessDept getAcceptDept() {
		return acceptDept;
	}

	public void setAcceptDept(BussinessDept acceptDept) {
		this.acceptDept = acceptDept;
	}

	public Department getBeginLadingDeptN() {
		return beginLadingDeptN;
	}

	public void setBeginLadingDeptN(Department beginLadingDeptN) {
		this.beginLadingDeptN = beginLadingDeptN;
	}

	public Department getAcceptDeptN() {
		return acceptDeptN;
	}

	public void setAcceptDeptN(Department acceptDeptN) {
		this.acceptDeptN = acceptDeptN;
	}

	public String getBeginDeptStandardCode() {
		return beginDeptStandardCode;
	}

	public void setBeginDeptStandardCode(String beginDeptStandardCode) {
		this.beginDeptStandardCode = beginDeptStandardCode;
	}

	public String getAcceptDeptStandCode() {
		return acceptDeptStandCode;
	}

	public void setAcceptDeptStandCode(String acceptDeptStandCode) {
		this.acceptDeptStandCode = acceptDeptStandCode;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	
	
}
