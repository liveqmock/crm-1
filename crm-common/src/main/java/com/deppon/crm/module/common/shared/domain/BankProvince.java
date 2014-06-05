package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

/**
 * @作者: 罗典
 * @时间：2012-12-24下午2:30:39
 * @描述：银行省份
 */

public class BankProvince {
	// ID
	private String id;
	// 编码
	private String code;
	// 名称
	private String name;
	// 是否作废(1 ：作废 0：不作废)
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

	public String getLastModifyUserId() {
		return lastModifyUserId;
	}

	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
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

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
}
