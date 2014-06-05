package com.deppon.crm.module.client.workflow.domain;


/**
 * 多陪参数对象
 * @author davidcun
 * @2012-3-14
 * description
 */
public class MuchRecompenseInfo {
	
	//申请人姓名 
	private String applyPersonName;
	//申请工号  
	private String applyPersonCode;
	//多陪单号 
	private String transportOrErrorCode;
	//多赔金额    
	private double recompensiesMoney;
	//合计多陪总金额(多赔金额+理赔处理金额) 
	private double amountinTotal;
	
	//应收账款是否收回    
	private boolean hasRepayDebt;
	//部门会计(名称)
	private String deptAccountant;
	//申请事由    
	private String applyReason;
	//所属事业部(需要标杆编码)
	private String enterpriseDept;
	public String getApplyPersonName() {
		return applyPersonName;
	}
	public void setApplyPersonName(String applyPersonName) {
		this.applyPersonName = applyPersonName;
	}
	public String getApplyPersonCode() {
		return applyPersonCode;
	}
	public void setApplyPersonCode(String applyPersonCode) {
		this.applyPersonCode = applyPersonCode;
	}
	public String getTransportOrErrorCode() {
		return transportOrErrorCode;
	}
	public void setTransportOrErrorCode(String transportOrErrorCode) {
		this.transportOrErrorCode = transportOrErrorCode;
	}
	public double getRecompensiesMoney() {
		return recompensiesMoney;
	}
	public void setRecompensiesMoney(double recompensiesMoney) {
		this.recompensiesMoney = recompensiesMoney;
	}
	public double getAmountinTotal() {
		return amountinTotal;
	}
	public void setAmountinTotal(double amountinTotal) {
		this.amountinTotal = amountinTotal;
	}
	public boolean isHasRepayDebt() {
		return hasRepayDebt;
	}
	public void setHasRepayDebt(boolean hasRepayDebt) {
		this.hasRepayDebt = hasRepayDebt;
	}
	public String getDeptAccountant() {
		return deptAccountant;
	}
	public void setDeptAccountant(String deptAccountant) {
		this.deptAccountant = deptAccountant;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public String getEnterpriseDept() {
		return enterpriseDept;
	}
	public void setEnterpriseDept(String enterpriseDept) {
		this.enterpriseDept = enterpriseDept;
	}
	
}
