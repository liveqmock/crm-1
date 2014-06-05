/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ToDoPojo.java
 * @package com.deppon.crm.module.marketing.server.action 
 * @author ZhuPJ
 * @version 0.1 2012-5-29
 */
package com.deppon.crm.module.marketing.server.action;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description: 待办事宜简单对象<br />
 * </p>
 * @title ToDoPojo.java
 * @package com.deppon.crm.module.marketing.server.action 
 * @author ZhuPJ
 * @version 0.1 2012-5-29
 */

public class ToDoPojo extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1974647053342926890L;
	// 用户ID
	private Integer userid;
	// 部门ID(通知到部门，则用户ID必须为空)
	private Integer deptId;
	// 任务类型
	private String tasktype;
	// 消息内容
	private String ishaveinfo;
	// 消息数量
	private Integer taskcount;
	/**
	 * @return userid : return the property userid.
	 */
	public Integer getUserid() {
		return userid;
	}
	/**
	 * @param userid : set the property userid.
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	/**
	 * @return deptId : return the property deptId.
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId : set the property deptId.
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return tasktype : return the property tasktype.
	 */
	public String getTasktype() {
		return tasktype;
	}
	/**
	 * @param tasktype : set the property tasktype.
	 */
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	/**
	 * @return ishaveinfo : return the property ishaveinfo.
	 */
	public String getIshaveinfo() {
		return ishaveinfo;
	}
	/**
	 * @param ishaveinfo : set the property ishaveinfo.
	 */
	public void setIshaveinfo(String ishaveinfo) {
		this.ishaveinfo = ishaveinfo;
	}
	/**
	 * @return taskcount : return the property taskcount.
	 */
	public Integer getTaskcount() {
		return taskcount;
	}
	/**
	 * @param taskcount : set the property taskcount.
	 */
	public void setTaskcount(Integer taskcount) {
		this.taskcount = taskcount;
	}
	
	
}
