package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:索赔操作信息表<br />
 * </p>
 * @title ClaimOperationLog.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimOperationLog extends BaseEntity {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	//操作日志内容
	private String operatorContent;
	//操作时间
	private Date operatorTime;
	//操作人
	private String operator;
	//操作人名字
	private String operatorName;
	//操作部门
	private String operatorDept;
	//操作部门
	private String operatorDeptName;
	//关联的索赔Id
	private String claimId;

	/**
	 * <p>
	 * Description:operatorContent<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getOperatorContent() {
		return operatorContent;
	}
	/**
	 * <p>
	 * Description:operatorContent<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setOperatorContent(String operatorContent) {
		this.operatorContent = operatorContent;
	}
	/**
	 * <p>
	 * Description:operatorTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public Date getOperatorTime() {
		return operatorTime;
	}
	/**
	 * <p>
	 * Description:operatorTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	/**
	 * <p>
	 * Description:operator<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * <p>
	 * Description:operator<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * <p>
	 * Description:operatorDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getOperatorDept() {
		return operatorDept;
	}
	/**
	 * <p>
	 * Description:operatorDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setOperatorDept(String operatorDept) {
		this.operatorDept = operatorDept;
	}
	/**
	 * <p>
	 * Description:claimId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public String getClaimId() {
		return claimId;
	}
	/**
	 * <p>
	 * Description:claimId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	/**
	 * <p>
	 * Description:operatorDeptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-12
	 */
	public String getOperatorDeptName() {
		return operatorDeptName;
	}
	/**
	 * <p>
	 * Description:operatorDeptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-12
	 */
	public void setOperatorDeptName(String operatorDeptName) {
		this.operatorDeptName = operatorDeptName;
	}
	/**
	 * <p>
	 * Description:operatorName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-12
	 */
	public String getOperatorName() {
		return operatorName;
	}
	/**
	 * <p>
	 * Description:operatorName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-12
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	


}
