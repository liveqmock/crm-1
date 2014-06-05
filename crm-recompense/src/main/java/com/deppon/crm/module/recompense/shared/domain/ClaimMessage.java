package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:索赔跟进信息<br />
 * </p>
 * 
 * @title ClaimMessage.java
 * @package com.deppon.crm.module.recompense.shared.domain
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimMessage extends BaseEntity {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	// 跟进人id
	private String followUserId;
	// 跟进人
	private String followUser;
	// 跟进部门
	private String followDept;
	//跟进部门名字
	private String followDeptName;
	// 跟进内容
	private String content;
	// 跟进时间
	private Date createTime;
	// 所属的索赔关联单的Id
	private String claimId;

	/**
	 * <p>
	 * Description:followUserId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getFollowUserId() {
		return followUserId;
	}

	/**
	 * <p>
	 * Description:followUserId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setFollowUserId(String followUserId) {
		this.followUserId = followUserId;
	}

	/**
	 * <p>
	 * Description:followUser<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getFollowUser() {
		return followUser;
	}

	/**
	 * <p>
	 * Description:followUser<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setFollowUser(String followUser) {
		this.followUser = followUser;
	}

	/**
	 * <p>
	 * Description:followDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getFollowDept() {
		return followDept;
	}

	/**
	 * <p>
	 * Description:followDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setFollowDept(String followDept) {
		this.followDept = followDept;
	}

	/**
	 * <p>
	 * Description:content<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <p>
	 * Description:content<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * <p>
	 * Description:createTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * <p>
	 * Description:createTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * <p>
	 * Description:claimId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getClaimId() {
		return claimId;
	}

	/**
	 * <p>
	 * Description:claimId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	/**
	 * <p>
	 * Description:followDeptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-12
	 */
	public String getFollowDeptName() {
		return followDeptName;
	}

	/**
	 * <p>
	 * Description:followDeptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-12
	 */
	public void setFollowDeptName(String followDeptName) {
		this.followDeptName = followDeptName;
	}

}
