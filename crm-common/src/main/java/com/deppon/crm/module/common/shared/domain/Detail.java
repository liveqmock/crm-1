package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * 作 者：ztjie 最后
 * 修改时间：2012年3月5日 
 * 描 述： 数据字典详细数据信息
 */
public class Detail extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5487648348482421682L;

	// 父数据ID
	private String parentId;
	
	//代码类型
	private String codeType;
	
	//代码
	private String code;
	
	//代码类型描述
	private String codeDesc;
	
	//状态
	private Boolean status;
	
	//代码序列
	private Integer codeSeq;
	
	//语言
	private String language;
	
	//失效时间
	private Date invaliTime ;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getInvaliTime() {
		return invaliTime;
	}

	public void setInvaliTime(Date invaliTime) {
		this.invaliTime = invaliTime;
	}

	
}
