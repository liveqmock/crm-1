package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:硬件Token信息<br />
 * </p>
 * @title HardWareToken.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author Weill
 * @version 0.1 2012-7-8
 */
public class HardWareToken  extends BaseEntity{

	private String token;
	private Date expireTime;
	private boolean  isUsed;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
}
