package com.deppon.crm.module.authorization.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;
import com.deppon.foss.framework.entity.IFunction;
import com.deppon.foss.framework.entity.IModule;

/**
 * 功能对象实体
 * @author Administrator
 *
 */
public class Function extends BaseEntity implements IFunction {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 8204215052602820708L;

	//功能编码
	private String functionCode;

	//功能名称
	private String functionName;

	//功能入口URI
	private String uri;

	//功能层次
	private Integer functionLevel;

	//父功能
	private Function parentCode;

	//是否有效
	private Boolean validFlag;

	//失效时间
	private Date invalidDate;

	//生效时间
	private Date validDate;

	//显示顺序
	private Integer displayOrder;

	//是否权限检查
	private Boolean check;

	//功能类型
	private Byte functionType;

	//功能描述
	private String functionDesc;

	//功能路径序列
	private String functionSeq;
	
	//页面功能列表
	private List<Function> functionPageElementList;

	public String getFunctionCode() {
		return this.functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getFunctionLevel() {
		return this.functionLevel;
	}

	public void setFunctionLevel(Integer functionLevel) {
		this.functionLevel = functionLevel;
	}

	public Function getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(Function parentCode) {
		this.parentCode = parentCode;
	}

	public Date getInvalidDate() {
		return this.invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Integer getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Boolean getCheck() {
		return this.check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public Byte getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(Byte functionType) {
		this.functionType = functionType;
	}

	public String getFunctionDesc() {
		return this.functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getFunctionSeq() {
		return this.functionSeq;
	}

	public void setFunctionSeq(String functionSeq) {
		this.functionSeq = functionSeq;
	}

	public String getKey() {
		return uri;
	}

	public IModule getModule() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean getValidFlag() {
		return this.validFlag;
	}
	
	public void setValidFlag(Boolean validFlag) {
		this.validFlag = validFlag;
	}

	public List<Function> getFunctionPageElementList() {
		return functionPageElementList;
	}

	public void setFunctionPageElementList(List<Function> functionPageElementList) {
		this.functionPageElementList = functionPageElementList;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
