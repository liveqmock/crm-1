package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.Date;


/**
 * 
 * <p>兑换积分<br />
 * </p>
 * @title IntegralConvertGift.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author Administrator
 * @version 0.1 2012-4-20w
 */
public class IntegralConvertGift extends Integral{
	private static final long serialVersionUID = 1L;
	//申请礼物
	private Gift gift;
	//兑换数量
	private Integer convertNumber;
	//兑换人身份证
	private String convertIdCard;
	//工作流id
	private String workFlowId;
	//兑换日期
	private Date convertTime;
	//发送日期
	private Date sendTime;
	//是否发送
	private Boolean isSend;
	//发送状态
	private String sendStatus;
	//兑换部门
	private String deptId;
	/**
	 * <p>
	 * Description:gift<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Gift getGift() {
		return gift;
	}
	/**
	 * <p>
	 * Description:gift<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	/**
	 * <p>
	 * Description:convertNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getConvertNumber() {
		return convertNumber;
	}
	/**
	 * <p>
	 * Description:convertNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setConvertNumber(Integer convertNumber) {
		this.convertNumber = convertNumber;
	}
	/**
	 * <p>
	 * Description:convertIdCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getConvertIdCard() {
		return convertIdCard;
	}
	/**
	 * <p>
	 * Description:convertIdCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setConvertIdCard(String convertIdCard) {
		this.convertIdCard = convertIdCard;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWorkFlowId() {
		return workFlowId;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	/**
	 * <p>
	 * Description:convertTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getConvertTime() {
		return convertTime;
	}
	/**
	 * <p>
	 * Description:convertTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setConvertTime(Date convertTime) {
		this.convertTime = convertTime;
	}
	/**
	 * <p>
	 * Description:sendTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * <p>
	 * Description:sendTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * <p>
	 * Description:isSend<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsSend() {
		return isSend;
	}
	/**
	 * <p>
	 * Description:isSend<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}
	/**
	 * <p>
	 * Description:sendStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getSendStatus() {
		return sendStatus;
	}
	/**
	 * <p>
	 * Description:sendStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	
}
