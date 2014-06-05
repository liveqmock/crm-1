package com.deppon.crm.module.recompense.shared.domain;


/**
 * 
 * <p>
 * Description:奖罚<br />
 * </p>
 * @title AwardItem.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class AwardItem {

	private static final long serialVersionUID = -5603982091936036124L;
	//id
	private String id;
	// 理赔单Id
	private String recompenseId;
	// 奖罚类型
	private String awardType;
	//部门ID
	private String deptId;
	// 部门名称
	private String deptName;
	// 员工ID
	private String userId;
	// 员工工号
	private String userNumber;
	// 奖罚金额
	private Double amount;
	// 奖罚对象类型
	private String awardTargetType;

	/**
	 * constructer
	 */
	public AwardItem() {
		super();
	}

	/**
	 * constructer
	 * 
	 * @param recompenseId
	 * @param awardType
	 * @param deptId
	 * @param deptName
	 * @param userId
	 * @param userNumber
	 * @param amount
	 * @param awardTargetType
	 */
	public AwardItem(String recompenseId, String awardType, String deptId,
			String deptName, String userId, String userNumber, Double amount,
			String awardTargetType) {
		this.recompenseId = recompenseId;
		this.awardType = awardType;
		this.deptId = deptId;
		this.deptName = deptName;
		this.userId = userId;
		this.userNumber = userNumber;
		this.amount = amount;
		this.awardTargetType = awardTargetType;
	}

	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return recompenseId : return the property recompenseId.
	 */
	public String getRecompenseId() {
		return recompenseId;
	}

	/**
	 * @param recompenseId
	 *            : set the property recompenseId.
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	/**
	 * @return awardType : return the property awardType.
	 */
	public String getAwardType() {
		return awardType;
	}

	/**
	 * @param awardType
	 *            : set the property awardType.
	 */
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	/**
	 * @return deptId : return the property deptId.
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            : set the property deptId.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return deptName : return the property deptName.
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            : set the property deptName.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return userId : return the property userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            : set the property userId.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userNumber : return the property userNumber.
	 */
	public String getUserNumber() {
		return userNumber;
	}

	/**
	 * @param userNumber
	 *            : set the property userNumber.
	 */
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	/**
	 * @return amount : return the property amount.
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            : set the property amount.
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return awardTargetType : return the property awardTargetType.
	 */
	public String getAwardTargetType() {
		return awardTargetType;
	}

	/**
	 * @param awardTargetType
	 *            : set the property awardTargetType.
	 */
	public void setAwardTargetType(String awardTargetType) {
		this.awardTargetType = awardTargetType;
	}

}
