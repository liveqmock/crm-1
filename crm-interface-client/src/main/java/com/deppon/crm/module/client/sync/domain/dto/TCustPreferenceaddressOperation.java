package com.deppon.crm.module.client.sync.domain.dto;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;

public class TCustPreferenceaddressOperation extends TCustPreferenceaddress {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1371329412450425640L;

	public OperationFlg getOperationFlg() {
		return operationFlg;
	}

	public void setOperationFlg(OperationFlg operationFlg) {
		this.operationFlg = operationFlg;
	}

	private OperationFlg operationFlg;
}
