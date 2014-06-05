package com.deppon.crm.module.client.workflow.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 礼品申请审批信息集合封装类
 * @author davidcun @2012-3-23
 */
public class GiftApplyInfo {

	//申请人工号
	private String applyPersonNumber;
	
	//到货部门(标杆编码)
	private String receiveDept;
	
	//申请原因
	private String applyReason;
	//到货地址
	private String receiveAddress;
	
	//联系方式，手机电话或者固定电话
	private String phone;
	
	
	private List<Gift> gifts;

	public String getApplyPersonNumber() {
		return applyPersonNumber;
	}

	public void setApplyPersonNumber(String applyPersonNumber) {
		this.applyPersonNumber = applyPersonNumber;
	}

	public String getReceiveDept() {
		return receiveDept;
	}

	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static class Gift{
		
		//会员卡ID
		private String memberNumber="";
		//兑换人身份证号码
		private String exchangePersonNumber="";
		//礼品编码
		private String giftNumber;
		//礼品数量
		private int giftCount;
		public String getGiftNumber() {
			return giftNumber;
		}
		public void setGiftNumber(String giftNumber) {
			this.giftNumber = giftNumber;
		}
		public int getGiftCount() {
			return giftCount;
		}
		public void setGiftCount(int giftCount) {
			this.giftCount = giftCount;
		}
		public String getMemberNumber() {
			return memberNumber;
		}
		public void setMemberNumber(String memberNumber) {
			this.memberNumber = memberNumber;
		}
		public String getExchangePersonNumber() {
			return exchangePersonNumber;
		}
		public void setExchangePersonNumber(String exchangePersonNumber) {
			this.exchangePersonNumber = exchangePersonNumber;
		}
	}
	public List<Gift> getGifts() {
		if (gifts==null) {
			gifts = new ArrayList<GiftApplyInfo.Gift>();
		}
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}
}
