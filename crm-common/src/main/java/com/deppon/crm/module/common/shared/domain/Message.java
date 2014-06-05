package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * 作 者：赵斌 修改时间：2012年3月15日 描 述： 待办事宜
 */
public class Message extends BaseEntity {

	// 序列化
	private static final long serialVersionUID = 8511499855281738418L;

	// 用户id
	private Integer userid;
	// 部门Id
	private Integer deptId;
	private String deptStandardCode;

	// 待办事宜类别
	private String tasktype;

	// 待办事宜数量
	private Integer taskcount;

	// 详细信息
	private String ishaveinfo;

	// 查询条件
	private String conditions;
	// url
	private String url;

	// 预留字段
	private String expe;

	// 有效日期
	private Date effectiveDate;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public Integer getTaskcount() {
		return taskcount;
	}

	public void setTaskcount(Integer taskcount) {
		this.taskcount = taskcount;
	}

	public String getIshaveinfo() {
		return ishaveinfo;
	}

	public void setIshaveinfo(String ishaveinfo) {
		this.ishaveinfo = ishaveinfo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpe() {
		return expe;
	}

	public void setExpe(String expe) {
		this.expe = expe;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getDeptStandardCode() {
		return deptStandardCode;
	}

	public void setDeptStandardCode(String deptStandardCode) {
		this.deptStandardCode = deptStandardCode;
	}

}
