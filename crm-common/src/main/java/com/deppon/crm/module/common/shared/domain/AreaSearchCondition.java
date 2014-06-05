/**
 * Filename:	AreaSearchCondition.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-5-10 上午10:38:26
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-5-10	    
 */

package com.deppon.crm.module.common.shared.domain;


public class AreaSearchCondition {
	
	private Integer start;
	
	private Integer limit;
	
	private String name;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
