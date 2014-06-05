package com.deppon.crm.module.customer.shared.domain;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.Detail;

public class TradeView extends Detail {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	//二级行业
	private List<Detail> secondTrade = new ArrayList<Detail>();

	/**
	 *@user pgj
	 *2014-4-11上午8:14:18
	 * @return secondTrade : return the property secondTrade.
	 */
	public List<Detail> getSecondTrade() {
		return secondTrade;
	}

	/**
	 * @param secondTrade : set the property secondTrade.
	 */
	public void setSecondTrade(List<Detail> secondTrade) {
		this.secondTrade = secondTrade;
	}


}
