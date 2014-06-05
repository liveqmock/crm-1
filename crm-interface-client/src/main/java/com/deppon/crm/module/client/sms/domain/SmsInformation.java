package com.deppon.crm.module.client.sms.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @作者：罗典
 * @描述：发送短信实体
 * @时间：2012-11-1
 * */
public class SmsInformation {
	// 发送号码
	private String mobile;
	// 服务类型
	private String serviceType = "1";
	// 发送内容
	private String msgContent;
	// 发送时间
	private Timestamp sendTime = new Timestamp(new Date().getTime());
	// 最大延迟发送时间
	private Date latestSendTime;
	// 业务类型
	private String msgType;
	// 系统来源
	private String msgSource = "XTLY20121105084226";
	// 发送人
	private String sender;
	// 发送部门
	private String sendDept;
	// 单号
	private String waybillNo;
	// 唯一标识
	private String unionId;

	public SmsInformation() {
		// 初始化唯一标示
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		unionId = format.format(new Date()) + UUID.randomUUID().toString();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getSendTime() {
		String dateString = "";
		 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 dateString = sd.format(sendTime);
		 System.out.println(dateString);
		return dateString;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Date getLatestSendTime() {
		return latestSendTime;
	}

	public void setLatestSendTime(Date latestSendTime) {
		this.latestSendTime = latestSendTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgSource() {
		return msgSource;
	}

	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendDept() {
		return sendDept;
	}

	public void setSendDept(String sendDept) {
		this.sendDept = sendDept;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
