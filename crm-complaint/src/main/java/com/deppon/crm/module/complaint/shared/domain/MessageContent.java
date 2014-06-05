package com.deppon.crm.module.complaint.shared.domain;

import java.io.Serializable;

/**
 * 工单短信发送内容 数据
 * @author 侯斌飞
 *
 */
public class MessageContent implements Serializable{
	private static final long serialVersionUID = 4745309112322922430L;
	
	//用户名称
	private String userName; 
	//工单编号
	private String dealbill;
	//单号-凭证号
	private String fbill; 
	//业务项
	private String busItemName; 
	//业务范围
	private String busScopeName; 
	//业务类型
	private String busTypeName; 
    //业务场景名称
    private String busSceneName;
    //场景原因名称
    private String sceneRessonName;
	//来电人
	private String customer; 
	//反馈时限
	private String feedback; 
	//处理时限
	private String solve;
	//任务部门
	private String taskDeptName;
	//投诉类型
	private String reportTypeName;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the dealbill
	 */
	public String getDealbill() {
		return dealbill;
	}
	/**
	 * @param dealbill the dealbill to set
	 */
	public void setDealbill(String dealbill) {
		this.dealbill = dealbill;
	}
	/**
	 * @return the fbill
	 */
	public String getFbill() {
		return fbill;
	}
	/**
	 * @param fbill the fbill to set
	 */
	public void setFbill(String fbill) {
		this.fbill = fbill;
	}
	/**
	 * @return the busItemName
	 */
	public String getBusItemName() {
		return busItemName;
	}
	/**
	 * @param busItemName the busItemName to set
	 */
	public void setBusItemName(String busItemName) {
		this.busItemName = busItemName;
	}
	/**
	 * @return the busScopeName
	 */
	public String getBusScopeName() {
		return busScopeName;
	}
	/**
	 * @param busScopeName the busScopeName to set
	 */
	public void setBusScopeName(String busScopeName) {
		this.busScopeName = busScopeName;
	}
	/**
	 * @return the busTypeName
	 */
	public String getBusTypeName() {
		return busTypeName;
	}
	/**
	 * @param busTypeName the busTypeName to set
	 */
	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}
	/**
	 * @return the busSceneName
	 */
	public String getBusSceneName() {
		return busSceneName;
	}
	/**
	 * @param busSceneName the busSceneName to set
	 */
	public void setBusSceneName(String busSceneName) {
		this.busSceneName = busSceneName;
	}
	/**
	 * @return the sceneRessonName
	 */
	public String getSceneRessonName() {
		return sceneRessonName;
	}
	/**
	 * @param sceneRessonName the sceneRessonName to set
	 */
	public void setSceneRessonName(String sceneRessonName) {
		this.sceneRessonName = sceneRessonName;
	}
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}
	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	/**
	 * @return the solve
	 */
	public String getSolve() {
		return solve;
	}
	/**
	 * @param solve the solve to set
	 */
	public void setSolve(String solve) {
		this.solve = solve;
	}
	/**
	 * @return the taskDeptName
	 */
	public String getTaskDeptName() {
		return taskDeptName;
	}
	/**
	 * @param taskDeptName the taskDeptName to set
	 */
	public void setTaskDeptName(String taskDeptName) {
		this.taskDeptName = taskDeptName;
	}
	/**
	 * @return the reportTypeName
	 */
	public String getReportTypeName() {
		return reportTypeName;
	}
	/**
	 * @param reportTypeName the reportTypeName to set
	 */
	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}
}
