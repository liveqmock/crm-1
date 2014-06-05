package com.deppon.crm.module.marketing.shared.domain;
/**
 * 维护效果评估查询结果实体
 * @author xiaohongye
 *
 */
public class AssessMaintainEffect {
	//部门名称
	private String deptName;
	//部门Id
	private String deptId;
	//上月投诉理赔客户数
	private int recompenseCustNum;
	//上月投诉理赔本月回访数
	private int nowVisitNum;
	//上月投诉理赔回访率
	private float nowVisitRate;
	//上月流失客户数
	private int lostCustNum;
	//上月客户流失率
	private float lostCustRate;
	//计划内回访率
	private float planVisitRate;
	//流失回访率
	private float lostVisitRate;
	//挽回客户数
	private int saveCustNum;
	// 客户挽回率
	private int saveCustRate;
	//上上月固定客户发货数
	private float memberDeleviryNum;
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
	public int getRecompenseCustNum() {
		return recompenseCustNum;
	}
	public void setRecompenseCustNum(int recompenseCustNum) {
		this.recompenseCustNum = recompenseCustNum;
	}
	public int getNowVisitNum() {
		return nowVisitNum;
	}
	public void setNowVisitNum(int nowVisitNum) {
		this.nowVisitNum = nowVisitNum;
	}
	public float getNowVisitRate() {
		return nowVisitRate;
	}
	public void setNowVisitRate(float nowVisitRate) {
		this.nowVisitRate = nowVisitRate;
	}
	public int getLostCustNum() {
		return lostCustNum;
	}
	public void setLostCustNum(int lostCustNum) {
		this.lostCustNum = lostCustNum;
	}
	public float getLostCustRate() {
		return lostCustRate;
	}
	public void setLostCustRate(float lostCustRate) {
		this.lostCustRate = lostCustRate;
	}
	public float getPlanVisitRate() {
		return planVisitRate;
	}
	public void setPlanVisitRate(float planVisitRate) {
		this.planVisitRate = planVisitRate;
	}
	public float getLostVisitRate() {
		return lostVisitRate;
	}
	public void setLostVisitRate(float lostVisitRate) {
		this.lostVisitRate = lostVisitRate;
	}
	public int getSaveCustNum() {
		return saveCustNum;
	}
	public void setSaveCustNum(int saveCustNum) {
		this.saveCustNum = saveCustNum;
	}
	public int getSaveCustRate() {
		return saveCustRate;
	}
	public void setSaveCustRate(int saveCustRate) {
		this.saveCustRate = saveCustRate;
	}
	public float getMemberDeleviryNum() {
		return memberDeleviryNum;
	}
	public void setMemberDeleviryNum(float memberDeleviryNum) {
		this.memberDeleviryNum = memberDeleviryNum;
	}
}
