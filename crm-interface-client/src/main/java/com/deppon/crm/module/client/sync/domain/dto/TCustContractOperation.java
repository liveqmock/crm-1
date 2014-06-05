package com.deppon.crm.module.client.sync.domain.dto;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;

public class TCustContractOperation extends TCustContract {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1143126816664146808L;
	private OperationFlg operationFlg;

	public OperationFlg getOperationFlg() {
		return operationFlg;
	}

	public void setOperationFlg(OperationFlg operationFlg) {
		this.operationFlg = operationFlg;
	}
}
