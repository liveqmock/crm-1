package com.deppon.crm.module.workflow.server.util;

import java.math.BigDecimal;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 *<pre>
 *功能:审批权限金额配置实体类
 *作者：andy
 *日期：2013-8-12 下午16:18:40
 *</pre>
 */
public class AmountConfigEntity  extends BaseEntity{

	private static final long serialVersionUID = -4008343902231700805L;
	
	//工作流名称
	private String mcName;
	
	//工作定义名称
	private String mcDefiniTionName;
	
	//当前审批环节名称
	private String currentApproStepName;
	
	//当前审批环节编号
	private String currentApproStepNo;
	
	//目标审批环节名称
	private String targetApproStepName;
	
	//目标审批环节编号
	private String targetApproStepNo;
	
	//审批金额上限
	private BigDecimal minAmount;
	
	//审批金额下限（含）
	private BigDecimal maxAmount;

	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getMcDefiniTionName() {
		return mcDefiniTionName;
	}

	public void setMcDefiniTionName(String mcDefiniTionName) {
		this.mcDefiniTionName = mcDefiniTionName;
	}

	public String getCurrentApproStepName() {
		return currentApproStepName;
	}

	public void setCurrentApproStepName(String currentApproStepName) {
		this.currentApproStepName = currentApproStepName;
	}

	public String getCurrentApproStepNo() {
		return currentApproStepNo;
	}

	public void setCurrentApproStepNo(String currentApproStepNo) {
		this.currentApproStepNo = currentApproStepNo;
	}

	public String getTargetApproStepName() {
		return targetApproStepName;
	}

	public void setTargetApproStepName(String targetApproStepName) {
		this.targetApproStepName = targetApproStepName;
	}

	public String getTargetApproStepNo() {
		return targetApproStepNo;
	}

	public void setTargetApproStepNo(String targetApproStepNo) {
		this.targetApproStepNo = targetApproStepNo;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	
	
}
