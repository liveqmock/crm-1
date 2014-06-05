package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;
import java.util.List;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ClaimSearchCondition.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimSearchCondition {
	//凭证号
	private String waybillNumber;
	//处理部门
	private List<String> processDepts;
	//索赔状态
	private String claimStatus;
	//每页限制
	private int limit;
	//开始
	private int start;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getWaybillNumber() {
		return waybillNumber;
	}
	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	/**
	 * <p>
	 * Description:claimStatus<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getClaimStatus() {
		return claimStatus;
	}
	/**
	 * <p>
	 * Description:claimStatus<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public int getStart() {
		return start;
	}
	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * <p>
	 * Description:startTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * <p>
	 * Description:startTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * <p>
	 * Description:processDepts<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public List<String> getProcessDepts() {
		return processDepts;
	}
	/**
	 * <p>
	 * Description:processDepts<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setProcessDepts(List<String> processDepts) {
		this.processDepts = processDepts;
	}
	
		
}
