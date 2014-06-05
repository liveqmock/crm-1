package com.deppon.crm.module.client.workflow.domain;


/**
 * @作者：罗典
 * @时间：2012-3-21
 * @描述：合同附件信息
 * */
public class ContractNounInfo{

	// 合同名称
	private String contractName;
	// 保存路径
	private String savePath;
	// 文件名称
	private String fileName;
	// 合同ID
	private String contractId;
	
	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
}
