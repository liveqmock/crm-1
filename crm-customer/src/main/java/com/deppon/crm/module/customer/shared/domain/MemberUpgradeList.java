package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 会员升级信息<br />
 * </p>
 * @title MemberUpgrade.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-4-5
 */
//数据对于字段 flifttype = 1 
public class MemberUpgradeList extends BaseEntity{
	//客户编号
	private String custNumber;
	//客户名称
	private String custName;
	//主联系人姓名
	private String linkName;
	//主联系人手机
	private String linkPhone;
	//主联系人电话
	private String linkTel;
	//当前等级
	private String currentLevel;
	//目标等级
	private String targetLevel;
	//所属部门id
	private String belongDeptId;
	//所属部门name
	private String belongDeptName;
	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * <p>
	 * Description:linkName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkName() {
		return linkName;
	}
	/**
	 * <p>
	 * Description:linkName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	/**
	 * <p>
	 * Description:linkPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkPhone() {
		return linkPhone;
	}
	/**
	 * <p>
	 * Description:linkPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	/**
	 * <p>
	 * Description:linkTel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkTel() {
		return linkTel;
	}
	/**
	 * <p>
	 * Description:linkTel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	/**
	 * <p>
	 * Description:currentLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}
	/**
	 * <p>
	 * Description:currentLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	/**
	 * <p>
	 * Description:targetLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTargetLevel() {
		return targetLevel;
	}
	/**
	 * <p>
	 * Description:targetLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTargetLevel(String targetLevel) {
		this.targetLevel = targetLevel;
	}
	/**
	 * <p>
	 * Description:belongDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBelongDeptId() {
		return belongDeptId;
	}
	/**
	 * <p>
	 * Description:belongDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBelongDeptId(String belongDeptId) {
		this.belongDeptId = belongDeptId;
	}
	/**
	 * <p>
	 * Description:belongDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBelongDeptName() {
		return belongDeptName;
	}
	/**
	 * <p>
	 * Description:belongDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBelongDeptName(String belongDeptName) {
		this.belongDeptName = belongDeptName;
	}
}
