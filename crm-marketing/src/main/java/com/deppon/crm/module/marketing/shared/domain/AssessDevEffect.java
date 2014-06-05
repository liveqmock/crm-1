package com.deppon.crm.module.marketing.shared.domain;
/**
 * 开发效果评估查询结果实体
 * @author xiaohongye
 *
 */
public class AssessDevEffect {
//	部门名称
	private String deptName;
//	部门ID
	private String deptId;
//	会展
	private int exposend;
//	黄页
	private int yellowPages;
//	派单
	private int dispatchList;
//	阿里巴巴
	private int alibaba;
//	陌生来电
	private int strangerCalls;
//	订单潜客
	private int orderCust;
//	到达散客
	private int arriveCust;
//	计划回访数
	private int planVisitNum;
//	实际回访数
	private int actualVisitNum;
//	计划内回访率
	private float planVisitRate;
//	新增数量
	private int nowPotentialNum;
//	新增潜客回访数
	private int nowPotantialVisitNum;
//	本月新增潜客回访率
	private float nowPotantialVisitRate;
//	近三个月回访潜客数
	private int beforePotentialNum;
//	近三个月回访潜客本月发货数
	private int nowPotentialDeleviryNum;
//	潜客开发成功率
	private float potentialDevSuccessRate;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public int getExposend() {
		return exposend;
	}
	public void setExposend(int exposend) {
		this.exposend = exposend;
	}
	public int getYellowPages() {
		return yellowPages;
	}
	public void setYellowPages(int yellowPages) {
		this.yellowPages = yellowPages;
	}
	public int getDispatchList() {
		return dispatchList;
	}
	public void setDispatchList(int dispatchList) {
		this.dispatchList = dispatchList;
	}
	public int getAlibaba() {
		return alibaba;
	}
	public void setAlibaba(int alibaba) {
		this.alibaba = alibaba;
	}
	public int getStrangerCalls() {
		return strangerCalls;
	}
	public void setStrangerCalls(int strangerCalls) {
		this.strangerCalls = strangerCalls;
	}
	public int getOrderCust() {
		return orderCust;
	}
	public void setOrderCust(int orderCust) {
		this.orderCust = orderCust;
	}
	public int getArriveCust() {
		return arriveCust;
	}
	public void setArriveCust(int arriveCust) {
		this.arriveCust = arriveCust;
	}
	public int getPlanVisitNum() {
		return planVisitNum;
	}
	public void setPlanVisitNum(int planVisitNum) {
		this.planVisitNum = planVisitNum;
	}
	public int getActualVisitNum() {
		return actualVisitNum;
	}
	public void setActualVisitNum(int actualVisitNum) {
		this.actualVisitNum = actualVisitNum;
	}
	public float getPlanVisitRate() {
		return planVisitRate;
	}
	public void setPlanVisitRate(float planVisitRate) {
		this.planVisitRate = planVisitRate;
	}
	public int getNowPotentialNum() {
		return nowPotentialNum;
	}
	public void setNowPotentialNum(int nowPotentialNum) {
		this.nowPotentialNum = nowPotentialNum;
	}
	public int getNowPotantialVisitNum() {
		return nowPotantialVisitNum;
	}
	public void setNowPotantialVisitNum(int nowPotantialVisitNum) {
		this.nowPotantialVisitNum = nowPotantialVisitNum;
	}
	public float getNowPotantialVisitRate() {
		return nowPotantialVisitRate;
	}
	public void setNowPotantialVisitRate(float nowPotantialVisitRate) {
		this.nowPotantialVisitRate = nowPotantialVisitRate;
	}
	public int getBeforePotentialNum() {
		return beforePotentialNum;
	}
	public void setBeforePotentialNum(int beforePotentialNum) {
		this.beforePotentialNum = beforePotentialNum;
	}
	public int getNowPotentialDeleviryNum() {
		return nowPotentialDeleviryNum;
	}
	public void setNowPotentialDeleviryNum(int nowPotentialDeleviryNum) {
		this.nowPotentialDeleviryNum = nowPotentialDeleviryNum;
	}
	public float getPotentialDevSuccessRate() {
		return potentialDevSuccessRate;
	}
	public void setPotentialDevSuccessRate(float potentialDevSuccessRate) {
		this.potentialDevSuccessRate = potentialDevSuccessRate;
	}
	
}
