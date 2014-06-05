package com.deppon.crm.module.common.shared.domain;
/**
 * author:张振伟
 * 工单消息映射对象
 * userid：客户id
 * messageCount：消息数量
 * messageType：消息类型
 * */
public class MessageMap {
	//消息类型
	public static final String RECOMPENSE_TODO_SUBMITED="RECOMPENSE_TODO_SUBMITED";
	public static final String COMPLAINT_RT_REPORT_RETURN="COMPLAINT_RT_REPORT_RETURN";
	public static final String RECOMPENSE_MESSAGE="RECOMPENSE_MESSAGE";
	public static final String DUTY_MESSAGE= "DUTY_MESSAGE";
	public static final String QUALITY_INSPECTOR_RETURNBACK= "QUALITY_INSPECTOR_RETURNBACK";
	public static final String STATISTICIANS_RETURNBACK= "STATISTICIANS_RETURNBACK";
	public static final String CUSTMER_MESSAGE= "CUSTMER_MESSAGE";
	public static final String NEW_PLAN="NEW_PLAN";
	public static final String OVERDUE_SCHEDULE="OVERDUE_SCHEDULE";
	public static final String NEW_SCHEDULE="NEW_SCHEDULE";
	public static final String ORDER_MESSAGE="ORDER_MESSAGE";
	public static final String SUPER_MESSAGE="SUPER_MESSAGE";	
	private String linkId;
	private Integer messageCount;
	private String messageType;
	public MessageMap(){}
	public MessageMap(String userid,Integer messageCount,String messageType){
		this.messageCount=messageCount;
		this.linkId=userid;
		this.messageType=messageType;
	}
	public Integer getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	
	
}
