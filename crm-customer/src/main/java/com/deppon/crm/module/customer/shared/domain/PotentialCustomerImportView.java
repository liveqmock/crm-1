package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

/**
 * 
 * <p>
 * 潜客导入试图<br />
 * </p>
 * 
 * @title PotentialCustomerView.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author bxj
 * @version 0.2 2012-3-12
 */
public class PotentialCustomerImportView {
	//潜客信息
	private PotentialCustomer potentialCustomer;
	//行数
	private String rowNum;
	//提示信息
	private String message;
	//是否导入成功
	private boolean importSuccess;
	/**
	 * <p>
	 * Description:potentialCustomer<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public PotentialCustomer getPotentialCustomer() {
		return potentialCustomer;
	}
	/**
	 * <p>
	 * Description:potentialCustomer<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPotentialCustomer(PotentialCustomer potentialCustomer) {
		this.potentialCustomer = potentialCustomer;
	}
	/**
	 * <p>
	 * Description:rowNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRowNum() {
		return rowNum;
	}
	/**
	 * <p>
	 * Description:rowNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * <p>
	 * Description:message<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * <p>
	 * Description:message<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * <p>
	 * Description:importSuccess<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public boolean isImportSuccess() {
		return importSuccess;
	}
	/**
	 * <p>
	 * Description:importSuccess<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setImportSuccess(boolean importSuccess) {
		this.importSuccess = importSuccess;
	}
}
