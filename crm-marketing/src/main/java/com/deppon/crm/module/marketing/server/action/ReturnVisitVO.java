/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitView.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.server.action;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;

/**   
 * <p>
 * Description: 回访信息录入<br />
 * </p>
 * @title ReturnVisitView.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */

public class ReturnVisitVO extends ReturnVisit {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4011835888234716977L;

	// 客户意见
	private List<ReturnVisitOpinion> opinionList;
	
	// 客户走货潜力
	private List<ReturnVisitVolumePotential> volumePotentialList;

	/**
	 * @return opinionList : return the property opinionList.
	 */
	public List<ReturnVisitOpinion> getOpinionList() {
		return opinionList;
	}

	/**
	 * @param opinionList : set the property opinionList.
	 */
	public void setOpinionList(List<ReturnVisitOpinion> opinionList) {
		this.opinionList = opinionList;
	}

	/**
	 * @return volumePotentialList : return the property volumePotentialList.
	 */
	public List<ReturnVisitVolumePotential> getVolumePotentialList() {
		return volumePotentialList;
	}

	/**
	 * @param volumePotentialList : set the property volumePotentialList.
	 */
	public void setVolumePotentialList(
			List<ReturnVisitVolumePotential> volumePotentialList) {
		this.volumePotentialList = volumePotentialList;
	}

}
