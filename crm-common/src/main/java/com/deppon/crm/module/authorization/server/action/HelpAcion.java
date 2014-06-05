package com.deppon.crm.module.authorization.server.action;

import com.deppon.foss.framework.server.components.security.SecurityNonCheckRequired;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class HelpAcion  extends AbstractAction {
	private String htmlName;
	
	public String getHtmlName() {
		return htmlName;
	}

	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}
	
	
	public String getHelpIndex(){
		return SUCCESS;
	}
	
	
	/**
	 * 浏览器版本低，提示更新浏览器版本
	 * @return
	 */
	@SecurityNonCheckRequired
	public String updateBrowser(){
		return "success";
	}
}
