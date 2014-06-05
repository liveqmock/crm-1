package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;
import java.util.List;

/**   
 * <p>
 * Description:客户分组-客户列表详情-查询条件实体/>
 * </p>
 * @title CustGroupDayDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhouYuan
 * @version 2013.11.26
 */
public class CustGroupDayDetailCondition {
	//客户编码集合
	private List<String> custNumbers;
	//发到货性质
	private String fcAnalyseType;
	//查询日期
	private Date searchDate;
	//发货类别
	private String sendGoodsType;
	/**
	 * @param custNumbers the custNumbers to get
	 */
	public List<String> getCustNumbers() {
		return custNumbers;
	}
	/**
	 * @param custNumbers the custNumbers to set
	 */
	public void setCustNumbers(List<String> custNumbers) {
		this.custNumbers = custNumbers;
	}
	public String getFcAnalyseType() {
		return fcAnalyseType;
	}
	public void setFcAnalyseType(String fcAnalyseType) {
		this.fcAnalyseType = fcAnalyseType;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public String getSendGoodsType() {
		return sendGoodsType;
	}
	public void setSendGoodsType(String sendGoodsType) {
		this.sendGoodsType = sendGoodsType;
	}
	
}
