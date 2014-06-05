package com.deppon.crm.module.common.server.util;

import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;

/**
 * 为受理网点关系导入功能创建的pojo对象
 * @author 邢悦
 *
 */
public class LadingStationDeptReaderPojo {

	//实体对象
	private LadingstationDepartment ld;

	//行数
	private Integer lineNo;
	
	//错误信息
	private String errorMessage;

	public LadingStationDeptReaderPojo(LadingstationDepartment ld) {
		this(ld, null);
	}
	public LadingStationDeptReaderPojo(LadingstationDepartment ld,
			 String errorMessage) {
		this.ld = ld;
		this.errorMessage = errorMessage;
	}
	
	
	public LadingStationDeptReaderPojo(){
		
	}


	public LadingstationDepartment getLd() {
		return ld;
	}


	public void setLd(LadingstationDepartment ld) {
		this.ld = ld;
	}


	public Integer getLineNo() {
		return lineNo;
	}


	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * override equals方法
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LadingStationDeptReaderPojo other = (LadingStationDeptReaderPojo) obj;
		
		if (ld == null||other.ld==null) {
			return false;
		} 
		
		//始发网点
		if(ld.getBeginDeptStandardCode()==null||other.ld.getBeginDeptStandardCode()==null){
			return false;
		}
		
		if(!ld.getBeginDeptStandardCode().equals(other.ld.getBeginDeptStandardCode())) {
			return false;
		}
		
		//订单来源
		if(ld.getResource()==null||other.ld.getResource()==null){
			return false;
		}
		
		if(!ld.getResource().equals(other.ld.getResource())) {
			return false;
		}
		
		//是否接货
		if(ld.getIfReceive()==null||other.ld.getIfReceive()==null){
			return false;
		}
		
		if(!ld.getIfReceive().equals(other.ld.getIfReceive())) {
			return false;
		}		
		
		
		return true;
	}
	
	
	
	
}
