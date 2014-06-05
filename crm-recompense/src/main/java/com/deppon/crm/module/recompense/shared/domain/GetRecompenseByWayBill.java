package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.crm.module.customer.shared.domain.Member;

public class GetRecompenseByWayBill {
	private RecompenseApplication recompenseApplication;
	private Member member;
	private Waybill waybill;
	private String lastApprovedOpinion;
	private String deptCharge;
	private String issueDesc;
	private String status;
	private String insurType;

	public GetRecompenseByWayBill(RecompenseApplication recompenseApplication,
			String lastApprovedOpinion,String deptCharge,String issueDesc,String status,String insurType) {
		this.recompenseApplication = recompenseApplication;
		this.waybill = recompenseApplication.getWaybill();
		if(waybill == null){
			waybill = new Waybill();
		}
		this.member = recompenseApplication.getCustomer();
		if(member == null){
			member = new Member();
		}
		this.lastApprovedOpinion = lastApprovedOpinion;
		this.deptCharge = deptCharge;
		this.issueDesc = issueDesc;
		this.status = status;
		this.insurType = insurType;
	}

	public String getCustNumber() {
		return member.getCustNumber();
	}

	public String getCustName() {
		return member.getCustName();
	}

	public String getWaybillNumber() {
		return waybill.getWaybillNumber();
	}

	public String getInsurType() {
		return insurType;
	}

	public Date getInsurDate() {
		return recompenseApplication.getInsurDate();
	}

	public Date getReportDate() {
		return recompenseApplication.getReportDate();
	}

	public Double getRecompenseAmount() {
		return recompenseApplication.getRecompenseAmount();
	}

	public Double getRealAmount() {
		return recompenseApplication.getRealAmount();
	}

	public String getReceiveDept() {
		return waybill.getReceiveDept();
	}
	
	public String getIssueDesc() {
		return issueDesc;
	}

	public String getStatus() {
		return status;
	}

	public Date getHandledTime() {
		return recompenseApplication.getHandledTime();
	}

	public String getHandledMan() {
		return recompenseApplication.getHandledMan();
	}

	public String getLastApprovedMan() {
		return recompenseApplication.getLastApprovedMan();
	}

	public String getLastApprovedOpinion() {
		return lastApprovedOpinion;
	}

	public String getDeptCharge() {
		return deptCharge;
	}

	public String getInCompanyCharge(){
		return null;
	}
	
	public String getOtherCharge(){
		return null;
	}

}
