package com.deppon.crm.module.client.sync.domain.dto;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;

public class TCustContractdeptOperation extends TCustContractdept {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2937692564171107471L;

	public OperationFlg getOperationFlg() {
		return operationFlg;
	}

	public void setOperationFlg(OperationFlg operationFlg) {
		this.operationFlg = operationFlg;
	}

	private OperationFlg operationFlg;
}
