package com.deppon.crm.module.marketing.shared.domain;

public class BoReportInfo {

	// 部门
	private String deptId;
	private String deptName;
	// 经营本部
	private String divisionId;
	private String divisionName;
	// 事业部
	private String bizId;
	private String bizName;
	// 新建商机数量
	private int createNum;
	// 当前进行中商机数
	private int ongoingNum;
	// 当前超期进行中商机数
	private int extendedNum;
	// 当前已休眠商机数
	private int dormantNum;
	// 商机预计发货总金额
	private double expectDeliveryAmount;
	// 当前初步接触阶段商机数
	private int contactNum;
	// 本月初步接触阶段商机回访次数
	private int contactReturnVisitNum;
	// 本月初步接触阶段转化商机数
	private int contactTransNum;
	// 初步接触阶段转化率
	private double contactTransRate;
	// 当前需求分析阶段商机数
	private int analyzeNum;
	// 本月需求分析阶段商机回访次数
	private int analyzeReturnVisitNum;
	// 本月需求分析阶段转化商机数
	private int analyzeTransNum;
	// 需求分析阶段转化率
	private double analyzeTransRate;
	// 当前制定方案阶段商机数
	private int schemeNum;
	// 本月制定方案阶段商机回访次数
	private int schemeReturnVisitNum;
	// 本月制定方案阶段转化商机数
	private int schemeTransNum;
	// 制定方案阶段转化率
	private double schemeTransRate;
	// 当前报价/竞标阶段商机数
	private int offerNum;
	// 本月报价/竞标阶段商机回访次数
	private int offerReturnVisitNum;
	// 本月报价/竞标阶段转化商机数
	private int offerTransNum;
	// 报价/竞标阶段转化率
	private double offerTransRate;
	// 当前持续发货阶段商机数
	private int deliverNum;
	// 本月持续发货阶段商机回访次数
	private int deliverReturnVisitNum;
	// 本月持续发货阶段转化商机数
	private int deliverTransNum;
	// 持续发货阶段转化率
	private double deliverTransRate;
	// 失败关闭商机数
	private int failureNum;
	// 成功关闭商机数
	private int successNum;
	// 商机成功率
	private double successRate;
	// 开发成功商机对应客户发货量
	private double deliverAmount;
	// 本月应执行的商机日程数量
	private int scheduleNum;
	// 本月内实际执行的商机日程数量
	private int performNum;
	// 本月商机日程完成率
	private double performRate;

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public int getCreateNum() {
		return createNum;
	}

	public void setCreateNum(int createNum) {
		this.createNum = createNum;
	}

	public int getOngoingNum() {
		return ongoingNum;
	}

	public void setOngoingNum(int ongoingNum) {
		this.ongoingNum = ongoingNum;
	}

	public int getExtendedNum() {
		return extendedNum;
	}

	public void setExtendedNum(int extendedNum) {
		this.extendedNum = extendedNum;
	}

	public int getDormantNum() {
		return dormantNum;
	}

	public void setDormantNum(int dormantNum) {
		this.dormantNum = dormantNum;
	}

	public double getExpectDeliveryAmount() {
		return expectDeliveryAmount;
	}

	public void setExpectDeliveryAmount(double expectDeliveryAmount) {
		this.expectDeliveryAmount = expectDeliveryAmount;
	}

	public int getContactNum() {
		return contactNum;
	}

	public void setContactNum(int contactNum) {
		this.contactNum = contactNum;
	}

	public int getContactReturnVisitNum() {
		return contactReturnVisitNum;
	}

	public void setContactReturnVisitNum(int contactReturnVisitNum) {
		this.contactReturnVisitNum = contactReturnVisitNum;
	}

	public int getContactTransNum() {
		return contactTransNum;
	}

	public void setContactTransNum(int contactTransNum) {
		this.contactTransNum = contactTransNum;
	}

	public double getContactTransRate() {
		return contactTransRate;
	}

	public void setContactTransRate(double contactTransRate) {
		this.contactTransRate = contactTransRate;
	}

	public int getAnalyzeNum() {
		return analyzeNum;
	}

	public void setAnalyzeNum(int analyzeNum) {
		this.analyzeNum = analyzeNum;
	}

	public int getAnalyzeReturnVisitNum() {
		return analyzeReturnVisitNum;
	}

	public void setAnalyzeReturnVisitNum(int analyzeReturnVisitNum) {
		this.analyzeReturnVisitNum = analyzeReturnVisitNum;
	}

	public int getAnalyzeTransNum() {
		return analyzeTransNum;
	}

	public void setAnalyzeTransNum(int analyzeTransNum) {
		this.analyzeTransNum = analyzeTransNum;
	}

	public double getAnalyzeTransRate() {
		return analyzeTransRate;
	}

	public void setAnalyzeTransRate(double analyzeTransRate) {
		this.analyzeTransRate = analyzeTransRate;
	}

	public int getSchemeNum() {
		return schemeNum;
	}

	public void setSchemeNum(int schemeNum) {
		this.schemeNum = schemeNum;
	}

	public int getSchemeReturnVisitNum() {
		return schemeReturnVisitNum;
	}

	public void setSchemeReturnVisitNum(int schemeReturnVisitNum) {
		this.schemeReturnVisitNum = schemeReturnVisitNum;
	}

	public int getSchemeTransNum() {
		return schemeTransNum;
	}

	public void setSchemeTransNum(int schemeTransNum) {
		this.schemeTransNum = schemeTransNum;
	}

	public double getSchemeTransRate() {
		return schemeTransRate;
	}

	public void setSchemeTransRate(double schemeTransRate) {
		this.schemeTransRate = schemeTransRate;
	}

	public int getOfferNum() {
		return offerNum;
	}

	public void setOfferNum(int offerNum) {
		this.offerNum = offerNum;
	}

	public int getOfferReturnVisitNum() {
		return offerReturnVisitNum;
	}

	public void setOfferReturnVisitNum(int offerReturnVisitNum) {
		this.offerReturnVisitNum = offerReturnVisitNum;
	}

	public int getOfferTransNum() {
		return offerTransNum;
	}

	public void setOfferTransNum(int offerTransNum) {
		this.offerTransNum = offerTransNum;
	}

	public double getOfferTransRate() {
		return offerTransRate;
	}

	public void setOfferTransRate(double offerTransRate) {
		this.offerTransRate = offerTransRate;
	}

	public int getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(int deliverNum) {
		this.deliverNum = deliverNum;
	}

	public int getDeliverReturnVisitNum() {
		return deliverReturnVisitNum;
	}

	public void setDeliverReturnVisitNum(int deliverReturnVisitNum) {
		this.deliverReturnVisitNum = deliverReturnVisitNum;
	}

	public int getDeliverTransNum() {
		return deliverTransNum;
	}

	public void setDeliverTransNum(int deliverTransNum) {
		this.deliverTransNum = deliverTransNum;
	}

	public double getDeliverTransRate() {
		return deliverTransRate;
	}

	public void setDeliverTransRate(double deliverTransRate) {
		this.deliverTransRate = deliverTransRate;
	}

	public int getFailureNum() {
		return failureNum;
	}

	public void setFailureNum(int failureNum) {
		this.failureNum = failureNum;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

	public double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

	public double getDeliverAmount() {
		return deliverAmount;
	}

	public void setDeliverAmount(double deliverAmount) {
		this.deliverAmount = deliverAmount;
	}

	public int getScheduleNum() {
		return scheduleNum;
	}

	public void setScheduleNum(int scheduleNum) {
		this.scheduleNum = scheduleNum;
	}

	public int getPerformNum() {
		return performNum;
	}

	public void setPerformNum(int performNum) {
		this.performNum = performNum;
	}

	public double getPerformRate() {
		return performRate;
	}

	public void setPerformRate(double performRate) {
		this.performRate = performRate;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
