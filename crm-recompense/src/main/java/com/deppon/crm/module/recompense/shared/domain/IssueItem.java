package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:出险信息<br />
 * </p>
 * @title IssueItem.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class IssueItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4798067702566666251L;
	//编号
	private String id;

	// 理赔单关联Id
	private String recompenseId;

	// 出险类型
	private String insurType;

	// 出险数量
	private Integer quality;

	// 描述
	private String description;

	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseId() {
		return recompenseId;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	/**
	 * <p>
	 * Description:insurType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getInsurType() {
		return insurType;
	}

	/**
	 * <p>
	 * Description:insurType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurType(String insurType) {
		this.insurType = insurType;
	}

	/**
	 * <p>
	 * Description:quality<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Integer getQuality() {
		return quality;
	}

	/**
	 * <p>
	 * Description:quality<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	/**
	 * <p>
	 * Description:description<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <p>
	 * Description:description<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
