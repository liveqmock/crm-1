package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

/**
 * 
 * @author 苏玉军
 * @date 2012-01-09
 * @version 0.1
 * @description 处理意见 跟进信息
 * 
 */
// message
public class Message {
	//差错编号
	private String id;
	//跟进人id
	private String userId;
	// 跟进人
	private String userName;

	// 跟进内容c
	private String content;

	// 跟进时间
	private Date createTime;

	// 所属的理赔关联单的Id
	private String recompenseId;

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
	 * Description:userId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * <p>
	 * Description:userId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * <p>
	 * Description:userName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * <p>
	 * Description:userName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * <p>
	 * Description:content<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <p>
	 * Description:content<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * <p>
	 * Description:createTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * <p>
	 * Description:createTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseId() {
		return recompenseId;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}



	

}
