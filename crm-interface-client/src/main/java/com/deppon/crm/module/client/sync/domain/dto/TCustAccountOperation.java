package com.deppon.crm.module.client.sync.domain.dto;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;

public class TCustAccountOperation extends TCustAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2935062701782880028L;

	public OperationFlg getOperationFlg() {
		return operationFlg;
	}

	public void setOperationFlg(OperationFlg operationFlg) {
		this.operationFlg = operationFlg;
	}

	private OperationFlg operationFlg;
}
