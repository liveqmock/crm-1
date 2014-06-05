package com.deppon.crm.module.bps.shared.domain;

import com.deppon.bpms.module.shared.domain.WorkFlowInfo;

/**
 * 
 * <p>
 * Description:带加密信息的工作流对象<br />
 * </p>
 * @title WorkFlowInfoEnc.java
 * @package com.deppon.crm.module.workflow.shared.domain 
 * @author liuHuan
 * @version 0.1 2013-9-7
 */
public class WorkFlowInfoEnc extends WorkFlowInfo {
	

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String processinstidEnc;

	private String workItemIdEnc;
	private String busiNoEnc;

	public String getProcessinstidEnc() {
		return processinstidEnc;
	}

	public void setProcessinstidEnc(String processinstidEnc) {
		this.processinstidEnc = processinstidEnc;
	}

	public String getWorkItemIdEnc() {
		return workItemIdEnc;
	}

	public void setWorkItemIdEnc(String workItemIdEnc) {
		this.workItemIdEnc = workItemIdEnc;
	}

	public String getBusiNoEnc() {
		return busiNoEnc;
	}

	public void setBusiNoEnc(String busiNoEnc) {
		this.busiNoEnc = busiNoEnc;
	}
	
	
}
