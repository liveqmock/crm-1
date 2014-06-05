package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:理赔清单<br />
 * </p>
 * @title RecompenseItem.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class RecompenseItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4714431128212817540L;
	
	private String id;
	// 理赔单关联Id
	private String recompenseId;

	// 货物名称s
	private String goodsName;

	// 出险类型
	private String insurType;

	// 信息描述
	private String description;

	// 数量
	private int quality;

	// 理赔金额
	private Double amount;

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
	 * Description:goodsName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * <p>
	 * Description:goodsName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	/**
	 * <p>
	 * Description:quality<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public int getQuality() {
		return quality;
	}

	/**
	 * <p>
	 * Description:quality<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setQuality(int quality) {
		this.quality = quality;
	}

	/**
	 * <p>
	 * Description:amount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * <p>
	 * Description:amount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	
}
