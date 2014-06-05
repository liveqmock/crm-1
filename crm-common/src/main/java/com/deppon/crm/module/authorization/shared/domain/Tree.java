/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Area.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.authorization.shared.domain;


import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Tree.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author ouy
 * @version 0.1 2012-5-7
 */

public class Tree extends BaseEntity {
	//主键
	private Integer fId;
	//创建时间
	private Date createTime;
	//创建用户id
	private Integer createUserId;
	//最后修改时间
	private Date lastUpdateTime;
	//最后修改人
	private Integer lastModifyUserId;
	//节点id
	private Integer noteid;
	//节点action
	private String noteurl;
	//节点名称
	private String notename;
	//所属用户
	private Integer userid;
	//功能编码
	private String functionCode;
	public Integer getfId() {
		return fId;
	}
	public void setfId(Integer fId) {
		this.fId = fId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(Integer lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public String getNoteurl() {
		return noteurl;
	}
	public void setNoteurl(String noteurl) {
		this.noteurl = noteurl;
	}
	public String getNotename() {
		return notename;
	}
	public void setNotename(String notename) {
		this.notename = notename;
	}
	public Integer getNoteid() {
		return noteid;
	}
	public void setNoteid(Integer noteid) {
		this.noteid = noteid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
}
