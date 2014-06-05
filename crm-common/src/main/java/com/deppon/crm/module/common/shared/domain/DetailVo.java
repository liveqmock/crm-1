package com.deppon.crm.module.common.shared.domain;

/**
 * 
 * 作 者：fbl
 * 修改时间：2014年4月15日 
 * 描 述： 用来显示数据字典维护的前台显示类
 */
public class DetailVo extends Detail{
	
	
	private static final long serialVersionUID = 986704510923603587L;
	

	//编码类型描述
	private String codeTypeDesc;
	
	//最后修改人工号
	private String empcode;
		
		//最后修改人姓名
	private String empname;


	public String getCodeTypeDesc() {
		return codeTypeDesc;
	}


	public void setCodeTypeDesc(String codeTypeDesc) {
		this.codeTypeDesc = codeTypeDesc;
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
