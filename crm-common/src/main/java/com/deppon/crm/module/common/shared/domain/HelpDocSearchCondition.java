package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

/**
 * 描 述： 帮助
 */
public class HelpDocSearchCondition {

	//
	private String helpTitle;
	private String belongModule;
	private String belongMenu;
	private String windowNum;
	private Boolean active;
	private Date startDate;
	private Date endDate;
	private int start;
	private int limit;

	public String getHelpTitle() {
		return helpTitle;
	}

	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String getBelongModule() {
		return belongModule;
	}

	public void setBelongModule(String belongModule) {
		this.belongModule = belongModule;
	}

	public String getBelongMenu() {
		return belongMenu;
	}

	public void setBelongMenu(String belongMenu) {
		this.belongMenu = belongMenu;
	}

	public String getWindowNum() {
		return windowNum;
	}

	public void setWindowNum(String windowNum) {
		this.windowNum = windowNum;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
