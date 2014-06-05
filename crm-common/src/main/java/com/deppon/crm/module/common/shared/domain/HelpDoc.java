package com.deppon.crm.module.common.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 描 述： 帮助
 */
public class HelpDoc extends BaseEntity {

	// 序列化
	private static final long serialVersionUID = 1509575619162379325L;
	//
	private String helpTitle;
	private String helpContent;
	private String belongModule;
	private String belongMenu;
	private String windowNum;
	private boolean active;

	public String getHelpTitle() {
		return helpTitle;
	}

	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String getHelpContent() {
		return helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
