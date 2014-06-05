/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitVolumePotential.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */
package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:回访信息-走货潜力<br />
 * </p>
 * @title ReturnVisitVolumePotential.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */

public class ReturnVisitVolumePotential extends BaseEntity {
	//serialVersionUID
	private static final long serialVersionUID = 8481767724714901524L;

	// 回访ID
	private String returnVisitId;
	
	// 始发城市
	private String comeFromCity;
	
	// 到达城市
	private String comeToCity;
	
	// 货量潜力
	private String volumePotential;
	
	// 合作物流公司
	private String companyId;
	// 备注原因
	private String remark;
	//是否已有线路
	private String alreadyHaveLine;
	//是否适合我司承运 1是 0 否
	private String accord;
	//合作意向
	private String cooperate;
	//适合我司承运备注
	private String accordMark;
	/**
	 * @return alreadyHaveLine : return the property alreadyHaveLine.
	 */
	public String getAlreadyHaveLine() {
		return alreadyHaveLine;
	}

	/**
	 * @param alreadyHaveLine : set the property alreadyHaveLine.
	 */
	public void setAlreadyHaveLine(String alreadyHaveLine) {
		this.alreadyHaveLine = alreadyHaveLine;
	}

	/**
	 * @return returnVisitId : return the property returnVisitId.
	 */
	public String getReturnVisitId() {
		return returnVisitId;
	}

	/**
	 * @param returnVisitId : set the property returnVisitId.
	 */
	public void setReturnVisitId(String returnVisitId) {
		this.returnVisitId = returnVisitId;
	}

	/**
	 * @return comeFromCity : return the property comeFromCity.
	 */
	public String getComeFromCity() {
		return comeFromCity;
	}

	/**
	 * @param comeFromCity : set the property comeFromCity.
	 */
	public void setComeFromCity(String comeFromCity) {
		this.comeFromCity = comeFromCity;
	}

	/**
	 * @return comeToCity : return the property comeToCity.
	 */
	public String getComeToCity() {
		return comeToCity;
	}

	/**
	 * @param comeToCity : set the property comeToCity.
	 */
	public void setComeToCity(String comeToCity) {
		this.comeToCity = comeToCity;
	}

	/**
	 * @return volumePotential : return the property volumePotential.
	 */
	public String getVolumePotential() {
		return volumePotential;
	}

	/**
	 * @param volumePotential : set the property volumePotential.
	 */
	public void setVolumePotential(String volumePotential) {
		this.volumePotential = volumePotential;
	}

	/**
	 * @return companyId : return the property companyId.
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId : set the property companyId.
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return remark : return the property remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark : set the property remark.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAccord() {
		return accord;
	}

	public void setAccord(String accord) {
		this.accord = accord;
	}

	public String getCooperate() {
		return cooperate;
	}

	public void setCooperate(String cooperate) {
		this.cooperate = cooperate;
	}

	public String getAccordMark() {
		return accordMark;
	}

	public void setAccordMark(String accordMark) {
		this.accordMark = accordMark;
	}
	
}
