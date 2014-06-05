package com.deppon.crm.module.order.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class OrderNumberRule extends BaseEntity {
	private static final long serialVersionUID = -6214794304575513247L;
	private String id;
	private String name;
	private String resource;
	private String transportMode;
	private String sign;
	private String remark;
	private Integer status;
	private String createUserId;
	private String createUserEmpName;
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserEmpName() {
		return createUserEmpName;
	}

	public void setCreateUserEmpName(String createUserEmpName) {
		this.createUserEmpName = createUserEmpName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
