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
 * Description:区域实体<br />
 * </p>
 * @title Area.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author ouyq
 * @version 0.1 2012-3-15
 */

public class AccountBank extends BaseEntity {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	//id
	private int fId;
	//创建时间
	private Date createTime;
	//最后休改时间
	private Date lastUpdateTime;
	//最后修改用户id
	private Integer lastModifyUserId;
	//银行名字
	private String name;
	//银行编码
	private String code;
	//创建人id
	private Integer createUserId;

	/**
	 * @description:配合银企添加属性。对应myBatis文件没有添加相应的配置信息
	 * @author 李国文
	 * @date 2012-12-04
	 */
	//是否禁用。1不被禁用，0禁用
	private int cancel ;

	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
}
