package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

/**
 * 
 * @作者: 罗典
 * @时间：2012-12-24下午2:14:37
 * @描述：银行城市
 */
public class BankCity {
	// ID
	private String id;
	// 编码
	private String code;
	// 名称
	private String name;
	// 所属省份
	private String provinceId;
	// 是否作废(1 ：作废 0：有效)
	private String cancel;
	// 创建日期
	private Date createTime;
	// 创建人ID
	private String createUserId;
	// 更新时间
	private Date lastUpdateTime;
	// 修改人ID
	private String lastModifyUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastModifyUserId() {
		return lastModifyUserId;
	}

	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

}
