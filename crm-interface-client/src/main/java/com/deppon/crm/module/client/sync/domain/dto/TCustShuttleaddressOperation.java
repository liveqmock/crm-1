package com.deppon.crm.module.client.sync.domain.dto;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;

public class TCustShuttleaddressOperation extends TCustShuttleaddress {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4023258934636805426L;

	public OperationFlg getOperationFlg() {
		return operationFlg;
	}

	public void setOperationFlg(OperationFlg operationFlg) {
		this.operationFlg = operationFlg;
	}

	private OperationFlg operationFlg;
}
