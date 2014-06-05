/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitOpinion.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */
package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:回访信息-客户意见<br />
 * </p>
 * @title ReturnVisitOpinion.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */

public class ReturnVisitOpinion extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1456999715504364736L;

	// 回访ID
	private String returnVisitId;
	
	// 意见类型
	private String opinionType;
	
	// 问题
	private String problem;
	
	// 解决方法
	private String solution;

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
	 * @return opinionType : return the property opinionType.
	 */
	public String getOpinionType() {
		return opinionType;
	}

	/**
	 * @param opinionType : set the property opinionType.
	 */
	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}

	/**
	 * @return problem : return the property problem.
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @param problem : set the property problem.
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	/**
	 * @return solution : return the property solution.
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @param solution : set the property solution.
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
		
}
