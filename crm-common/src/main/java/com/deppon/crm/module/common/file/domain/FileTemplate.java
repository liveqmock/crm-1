package com.deppon.crm.module.common.file.domain;

/**
 * <p>EXCEL模板</p>
 * @author Administrator
 * @version 1.0
 * */
public enum FileTemplate {

	Contract_Template("Contract"), Cust360_Analysis_Tempalte("Cust360_Analysis");
	
	/**模板名称*/
	private String templateName;
	
	FileTemplate (String templateName){
		this.templateName=templateName;
	}
	
	public String templateName(){
		return templateName;
	}
	
	
}
