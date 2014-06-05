package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;



import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @修改：赵斌
 * @功能：优惠信息
 * @时间：2012-04-10
 * */
public class Preferential extends BaseEntity {
	private static final long serialVersionUID = 5346883696343779751L;
	//对应合同ID
	private String contractId;
	//运费折扣费率
	private Double chargeRebate;
	//代收货款费率
	private Double agentgathRate;
	//保价费率
	private Double insuredPriceRate;
	//接货费率
	private Double receivePriceRate;
	//送货费率
	private Double deliveryPriceRate;
		// 状态
	private String status;
	// 折扣生效时间
	private Date beginTime;
	// 折扣失效时间
	private Date endTime;
	//优惠类型
	private String preferType ;
	/**
	 * <p>
	 * Description:beginTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * <p>
	 * Description:beginTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * <p>
	 * Description:contractId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * <p>
	 * Description:contractId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * <p>
	 * Description:chargeRebate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getChargeRebate() {
		return chargeRebate;
	}
	/**
	 * <p>
	 * Description:chargeRebate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChargeRebate(Double chargeRebate) {
		this.chargeRebate = chargeRebate;
	}
	/**
	 * <p>
	 * Description:agentgathRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getAgentgathRate() {
		return agentgathRate;
	}
	/**
	 * <p>
	 * Description:agentgathRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAgentgathRate(Double agentgathRate) {
		this.agentgathRate = agentgathRate;
	}
	/**
	 * <p>
	 * Description:insuredPriceRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getInsuredPriceRate() {
		return insuredPriceRate;
	}
	/**
	 * <p>
	 * Description:insuredPriceRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setInsuredPriceRate(Double insuredPriceRate) {
		this.insuredPriceRate = insuredPriceRate;
	}
	/**
	 * <p>
	 * Description:receivePriceRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getReceivePriceRate() {
		return receivePriceRate;
	}
	/**
	 * <p>
	 * Description:receivePriceRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceivePriceRate(Double receivePriceRate) {
		this.receivePriceRate = receivePriceRate;
	}
	/**
	 * <p>
	 * Description:deliveryPriceRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getDeliveryPriceRate() {
		return deliveryPriceRate;
	}
	/**
	 * <p>
	 * Description:deliveryPriceRate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeliveryPriceRate(Double deliveryPriceRate) {
		this.deliveryPriceRate = deliveryPriceRate;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 *@user pgj
	 *2013-9-20下午2:19:26
	 * @return preferType : return the property preferType.
	 */
	public String getPreferType() {
		return preferType;
	}
	/**
	 * @param preferType : set the property preferType.
	 */
	public void setPreferType(String preferType) {
		this.preferType = preferType;
	}
	@Override
	public Preferential clone(){
		Preferential preferentialBak = new  Preferential();
		preferentialBak.setAgentgathRate(this.getAgentgathRate());
		preferentialBak.setBeginTime(this.getBeginTime());
		preferentialBak.setChargeRebate(this.getChargeRebate());
		preferentialBak.setContractId(this.getContractId());
		preferentialBak.setCreateDate(this.getCreateDate());
		preferentialBak.setCreateUser(this.getCreateUser());
		preferentialBak.setDeliveryPriceRate(this.getDeliveryPriceRate());
		preferentialBak.setEndTime(this.getEndTime());
		preferentialBak.setId(this.getId());
		preferentialBak.setInsuredPriceRate(this.getInsuredPriceRate());
		preferentialBak.setModifyDate(this.getModifyDate());
		preferentialBak.setModifyUser(this.getModifyUser());
		preferentialBak.setReceivePriceRate(this.getReceivePriceRate());
		preferentialBak.setStatus(this.getStatus());
		preferentialBak.setPreferType(this.getPreferType());
		return preferentialBak;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((agentgathRate == null) ? 0 : agentgathRate.hashCode());
		result = prime * result + ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + ((chargeRebate == null) ? 0 : chargeRebate.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((deliveryPriceRate == null) ? 0 : deliveryPriceRate.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((insuredPriceRate == null) ? 0 : insuredPriceRate.hashCode());
		result = prime * result + ((preferType == null) ? 0 : preferType.hashCode());
		result = prime * result + ((receivePriceRate == null) ? 0 : receivePriceRate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preferential other = (Preferential) obj;
		if (agentgathRate == null) {
			if (other.agentgathRate != null)
				return false;
		} else if (!agentgathRate.equals(other.agentgathRate))
			return false;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (chargeRebate == null) {
			if (other.chargeRebate != null)
				return false;
		} else if (!chargeRebate.equals(other.chargeRebate))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (deliveryPriceRate == null) {
			if (other.deliveryPriceRate != null)
				return false;
		} else if (!deliveryPriceRate.equals(other.deliveryPriceRate))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (insuredPriceRate == null) {
			if (other.insuredPriceRate != null)
				return false;
		} else if (!insuredPriceRate.equals(other.insuredPriceRate))
			return false;
		if (preferType == null) {
			if (other.preferType != null)
				return false;
		} else if (!preferType.equals(other.preferType))
			return false;
		if (receivePriceRate == null) {
			if (other.receivePriceRate != null)
				return false;
		} else if (!receivePriceRate.equals(other.receivePriceRate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	public boolean monthSendEquals(Object obj) {
		if (null == this && null == obj) {
			return true;
		}
		if (null == this && null != obj) {
			Preferential compPre = (Preferential) obj;
			return !(compPre.getChargeRebate() == 1d
					&& compPre.getAgentgathRate() == 1d
					&& compPre.getDeliveryPriceRate() == 1d
					&& compPre.getInsuredPriceRate() == 1d 
					&& compPre.getReceivePriceRate() == null);
		} else if (null != this && null == obj) {
			return this.getChargeRebate() == 1d
					&& this.getAgentgathRate() == 1d
					&& this.getDeliveryPriceRate() == 1d
					&& this.getInsuredPriceRate() == 1d
					&& this.getReceivePriceRate() == null;
		} else {
			Preferential compPre = (Preferential) obj;
			return this.getAgentgathRate().equals(compPre.getAgentgathRate())
					&& this.getDeliveryPriceRate().equals(compPre.getDeliveryPriceRate())
					&& this.getInsuredPriceRate().equals(compPre.getInsuredPriceRate()) 
					&& ContractUtil.isTwoObjEqual(this.getReceivePriceRate(),compPre.getReceivePriceRate());
		}
	}
}
