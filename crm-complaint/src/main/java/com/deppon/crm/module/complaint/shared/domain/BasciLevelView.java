package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:基础层级<br />
 * </p>
 * @title Bulletin.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public class BasciLevelView {
	// 父id
	private BigDecimal fatherId;
	// 子id
	private Integer childId;
	/**
	 * 业务范围
	 */
	private String fatherBLName;
	/**
	 * 业务类型
	 */
	private String chiledBLName;
	/**
	 * 上报类型
	 */
	private String type;
	private String useYn;
	// fatherId get方法
	public BigDecimal getFatherId() {
		return fatherId;
	}
	// fatherId set方法
	public void setFatherId(BigDecimal fatherId) {
		this.fatherId = fatherId;
	}
	// childId get方法
	public Integer getChildId() {
		return childId;
	}
	// childId set方法
	public void setChildId(Integer childId) {
		this.childId = childId;
	}
	// fatherBLName get方法
	public String getFatherBLName() {
		return fatherBLName;
	}
	// fatherBLName set方法
	public void setFatherBLName(String fatherBLName) {
		this.fatherBLName = fatherBLName;
	}
	// chiledBLName get方法
	public String getChiledBLName() {
		return chiledBLName;
	}
	// chiledBLName set方法
	public void setChiledBLName(String chiledBLName) {
		this.chiledBLName = chiledBLName;
	}
	// type get方法
	public String getType() {
		return type;
	}
	// type set方法
	public void setType(String type) {
		this.type = type;
	}
	// useYn get方法
	public String getUseYn() {
		return useYn;
	}
	// useYn set方法
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
}
