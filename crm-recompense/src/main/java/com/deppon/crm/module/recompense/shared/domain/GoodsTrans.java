package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:货物托运清单<br />
 * </p>
 * @title GoodsTrans.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class GoodsTrans {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4277372714713378628L;
	// 编号
	private String id;

	// 理赔单关联Id
	private String recompenseId;

	// 货物名称
	private String goodsName;

	// 单价
	private Double price;

	// 数量
	private Integer quality;

	// 实际价值
	private Double realPrice;
	// 出险类型
	private String insurType;

	// 信息描述
	private String description;

	// 理赔数量
	private int number;

	// 理赔金额
	private Double amount;
	// 是否需要理赔
	private Boolean isClaims;
	// 货运托运清单关联理赔清单的标志位
	private long relationFlag;
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
	 * Description:price<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * <p>
	 * Description:price<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPrice(Double price) {
		this.price = price;
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
	 * Description:realPrice<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getRealPrice() {
		return realPrice;
	}
	/**
	 * <p>
	 * Description:realPrice<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
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
	 * Description:number<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * <p>
	 * Description:number<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setNumber(int number) {
		this.number = number;
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
	/**
	 * <p>
	 * Description:isClaims<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Boolean getIsClaims() {
		return isClaims;
	}
	/**
	 * <p>
	 * Description:isClaims<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setIsClaims(Boolean isClaims) {
		this.isClaims = isClaims;
	}
	/**
	 * <p>
	 * Description:relationFlag<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public long getRelationFlag() {
		return relationFlag;
	}
	/**
	 * <p>
	 * Description:relationFlag<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRelationFlag(long relationFlag) {
		this.relationFlag = relationFlag;
	}

	
}
