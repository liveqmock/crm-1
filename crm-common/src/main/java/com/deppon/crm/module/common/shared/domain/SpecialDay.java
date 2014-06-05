package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

public class SpecialDay {
	private String id;
	private Date specialDate;
	private String dateType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSpecialDate() {
		return specialDate;
	}

	public void setSpecialDate(Date specialDate) {
		this.specialDate = specialDate;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

}
