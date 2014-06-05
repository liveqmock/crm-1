package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;


/**
 * 
 * <p>
 * 审批记录<br />
 * </p>
 * @title ExamineRecord.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-4-12
 */
public class ExamineRecord extends BaseEntity{
	
	//审批意见
	private String opinion;
	//处理结果
	private Boolean result;
	//权责
	private String rights;
	//处理人 
	private String disposeUserId;
	//工作流id
	private long workFlowId;
	/**
	 * <p>
	 * Description:opinion<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOpinion() {
		return opinion;
	}
	/**
	 * <p>
	 * Description:opinion<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	/**
	 * <p>
	 * Description:result<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getResult() {
		return result;
	}
	/**
	 * <p>
	 * Description:result<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
	/**
	 * <p>
	 * Description:rights<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRights() {
		return rights;
	}
	/**
	 * <p>
	 * Description:rights<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}
	/**
	 * <p>
	 * Description:disposeUserId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDisposeUserId() {
		return disposeUserId;
	}
	/**
	 * <p>
	 * Description:disposeUserId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDisposeUserId(String disposeUserId) {
		this.disposeUserId = disposeUserId;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public long getWorkFlowId() {
		return workFlowId;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkFlowId(long workFlowId) {
		this.workFlowId = workFlowId;
	}
}
