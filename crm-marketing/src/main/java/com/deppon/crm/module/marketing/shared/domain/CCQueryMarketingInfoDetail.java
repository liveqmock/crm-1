/**    
 * <p>
 * 返回给CC营销明细查询<br />
 * </p>
 * @title CCQueryMarketingInfoDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireDto;

/**   
 * <p>
 * 返回给CC营销明细查询<br />
 * </p>
 * @title CCQueryMarketingInfoDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */

public class CCQueryMarketingInfoDetail implements Serializable {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 3341684094671010025L;
	//客户意见
	private List<ReturnVisitOpinionVo> opinions=new ArrayList<ReturnVisitOpinionVo>();
	//货量潜力
	private List<ReturnVisitVolumePotentialVo> potentials=new ArrayList<ReturnVisitVolumePotentialVo>();
	//问卷详情
	private List<QuestionnaireDto> dto=new ArrayList<QuestionnaireDto>();

	/**
	 * @return opinions : return the property opinions.  
	 */
	public List<ReturnVisitOpinionVo> getOpinions() {
		return opinions;
	}
	/**
	 * @param opinions : set the property opinions. 
	 */
	public void setOpinions(List<ReturnVisitOpinionVo> opinions) {
		this.opinions = opinions;
	}
	/**
	 * @return potentials : return the property potentials.  
	 */
	public List<ReturnVisitVolumePotentialVo> getPotentials() {
		return potentials;
	}
	/**
	 * @param potentials : set the property potentials. 
	 */
	public void setPotentials(List<ReturnVisitVolumePotentialVo> potentials) {
		this.potentials = potentials;
	}
	/**
	 * @return dto : return the property dto.  
	 */
	public List<QuestionnaireDto> getDto() {
		return dto;
	}
	/**
	 * @param dto : set the property dto. 
	 */
	public void setDto(List<QuestionnaireDto> dto) {
		this.dto = dto;
	}
	
}
