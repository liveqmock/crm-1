package com.deppon.crm.module.workflow.shared.domain;

import java.util.Date;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:业务编码：6位数组<br />
 * </p>
 * @title ClaimNoSuffixEntity.java
 * @package com.deppon.crm.module.workflow.shared.domain 
 * @author andy
 * @version 0.1 2013-8-5
 */
public class ClaimNoSuffixEntity extends BaseEntity{
	
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String nextSuffix;
	private Date suffixDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNextSuffix() {
		return nextSuffix;
	}
	public void setNextSuffix(String nextSuffix) {
		this.nextSuffix = nextSuffix;
	}
	public Date getSuffixDate() {
		return suffixDate;
	}
	public void setSuffixDate(Date suffixDate) {
		this.suffixDate = suffixDate;
	}
	
	

}
