package com.deppon.crm.module.custview.shared.domain;

import java.util.Date;

/**
 * @description 投诉统计.
 * @author 安小虎
 * @version 0.1 2012-4-25
 * @date 2012-4-25
 */

public class ComplaintStatistics {
	// ID
	private String id;
	// 客户ID
	private String custId;
	// 日期
	private Date date;
	// 保险理赔
	private Integer insuranceclaims;
	// 代理问题
	private Integer agencyproblem;
	// 非投诉
	private Integer notcomplaint;
	// 费用投诉
	private Integer costcomplaint;
	// 服务
	private Integer service;
	// 开单差错
	private Integer billingerror;
	// 客户催单
	private Integer customerReminder;
	// 客户原因
	private Integer customerReason;
	// 内部投诉
	private Integer innerComplaints;
	// 人品问题
	private Integer personalityProblem;
	// 时效
	private Integer timeLiness;
	// 业务差错
	private Integer businessError;
	// 合计
	private Integer total;
	/**
	 * @return the id
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the custId
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return the date
	 * @author 潘光均
	 * @version v0.1
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the insuranceclaims
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getInsuranceclaims() {
		return insuranceclaims;
	}
	/**
	 * @param insuranceclaims the insuranceclaims to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setInsuranceclaims(Integer insuranceclaims) {
		this.insuranceclaims = insuranceclaims;
	}
	/**
	 * @return the agencyproblem
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getAgencyproblem() {
		return agencyproblem;
	}
	/**
	 * @param agencyproblem the agencyproblem to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setAgencyproblem(Integer agencyproblem) {
		this.agencyproblem = agencyproblem;
	}
	/**
	 * @return the notcomplaint
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getNotcomplaint() {
		return notcomplaint;
	}
	/**
	 * @param notcomplaint the notcomplaint to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setNotcomplaint(Integer notcomplaint) {
		this.notcomplaint = notcomplaint;
	}
	/**
	 * @return the costcomplaint
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getCostcomplaint() {
		return costcomplaint;
	}
	/**
	 * @param costcomplaint the costcomplaint to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCostcomplaint(Integer costcomplaint) {
		this.costcomplaint = costcomplaint;
	}
	/**
	 * @return the service
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setService(Integer service) {
		this.service = service;
	}
	/**
	 * @return the billingerror
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getBillingerror() {
		return billingerror;
	}
	/**
	 * @param billingerror the billingerror to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setBillingerror(Integer billingerror) {
		this.billingerror = billingerror;
	}
	/**
	 * @return the customerReminder
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getCustomerReminder() {
		return customerReminder;
	}
	/**
	 * @param customerReminder the customerReminder to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCustomerReminder(Integer customerReminder) {
		this.customerReminder = customerReminder;
	}
	/**
	 * @return the customerReason
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getCustomerReason() {
		return customerReason;
	}
	/**
	 * @param customerReason the customerReason to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCustomerReason(Integer customerReason) {
		this.customerReason = customerReason;
	}
	/**
	 * @return the innerComplaints
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getInnerComplaints() {
		return innerComplaints;
	}
	/**
	 * @param innerComplaints the innerComplaints to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setInnerComplaints(Integer innerComplaints) {
		this.innerComplaints = innerComplaints;
	}
	/**
	 * @return the personalityProblem
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getPersonalityProblem() {
		return personalityProblem;
	}
	/**
	 * @param personalityProblem the personalityProblem to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setPersonalityProblem(Integer personalityProblem) {
		this.personalityProblem = personalityProblem;
	}
	/**
	 * @return the timeLiness
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getTimeLiness() {
		return timeLiness;
	}
	/**
	 * @param timeLiness the timeLiness to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setTimeLiness(Integer timeLiness) {
		this.timeLiness = timeLiness;
	}
	/**
	 * @return the businessError
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getBusinessError() {
		return businessError;
	}
	/**
	 * @param businessError the businessError to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setBusinessError(Integer businessError) {
		this.businessError = businessError;
	}
	/**
	 * @return the total
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

}
