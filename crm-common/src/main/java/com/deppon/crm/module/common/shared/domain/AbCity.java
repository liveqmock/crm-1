/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Area.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.shared.domain;


import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AbCity.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author ouy
 * @version 0.1 2012-5-8
 */

public class AbCity extends BaseEntity {
	//id
	private int fId;
	//创建时间
	private Date createTime;
	//最后休改时间
	private Date lastUpdateTime;
	//最后修改用户id
	private Integer lastModifyUserId;
	//创建人id
	private Integer createUserId;
	//修改人姓名
	private String lastModifyUserName;
	//创建人姓名
	private String createUserName;
	//城市名称
	private String name;
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(Integer lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public String getLastModifyUserName() {
		return lastModifyUserName;
	}
	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
}
