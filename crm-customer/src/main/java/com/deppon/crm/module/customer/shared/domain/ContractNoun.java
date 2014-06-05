package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-21
 * @描述：合同附件信息
 * */
public class ContractNoun extends BaseEntity{

	private static final long serialVersionUID = 8729332567100029937L;
	// 合同名称
	private String contractName;
	// 保存路径
	private String savePath;
	// 文件名称
	private String fileName;
	// 合同ID
	private String contractId;
	//合同部门关系ID
	private String contractDeptId;
	/**
	 * <p>
	 * Description:contractName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * <p>
	 * Description:contractName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * <p>
	 * Description:savePath<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getSavePath() {
		return savePath;
	}
	/**
	 * <p>
	 * Description:savePath<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	/**
	 * <p>
	 * Description:fileName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * <p>
	 * Description:fileName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * <p>
	 * Description:contractId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * <p>
	 * Description:contractId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * <p>
	 * Description:contractDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractDeptId() {
		return contractDeptId;
	}
	/**
	 * <p>
	 * Description:contractDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractDeptId(String contractDeptId) {
		this.contractDeptId = contractDeptId;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
