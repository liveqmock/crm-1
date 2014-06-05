package com.deppon.crm.module.common.shared.domain;

import java.util.ArrayList;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * 作 者：ztjie 
 * 最后修改时间：2012年3月5日 
 * 描 述： 数据字典头信息
 */
public class Head extends BaseEntity{

	private static final long serialVersionUID = 5620153209542261852L;
	
	// 代码类型
	private String codeType;
	
	// 代码类型描述
	private String codeTypeDesc;
	
	//数据字典详细信息列表
	private List<Detail> detailList = new ArrayList<Detail>();
	
	//最后修改人工号
	private String empcode;
	
	//最后修改人姓名
	private String empname;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeTypeDesc() {
		return codeTypeDesc;
	}

	public void setCodeTypeDesc(String codeTypeDesc) {
		this.codeTypeDesc = codeTypeDesc;
	}

	public List<Detail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<Detail> detailList) {
		this.detailList = detailList;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

}
