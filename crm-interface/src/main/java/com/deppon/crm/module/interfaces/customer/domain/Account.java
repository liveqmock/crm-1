package com.deppon.crm.module.interfaces.customer.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-21
 * @描述：账号详细
 * */
public class Account extends BaseEntity {
	private static final long serialVersionUID = 7205576077523457775L;
	// 开户行
	private String bank;;
	// 开户行ID
	private String bankId;
	// 支行名称
	private String subBankname;
	// 支行名称Id
	private String subBanknameId;
	// 是否默认账户 1为是，0为否
	private Boolean isdefaultaccount;
	// 银行账号
	private String bankAccount;
	// 开户姓名
	private String countName;
	// 账号与客户关系
	private String relation;
	// 开户省份
	private String bankProvinceId;
	// 开户省份名称
	private String bankProvinceName;
	// 账户性质
	private String accountNature;
	// 账户用途
	private String accountUse;
	// 开户城市
	private String bankCityId;
	// 开户城市名称
	private String bankCityName;
	// 财务联系人姓名
	private String financeLinkman;
	// 财务联系人ID
	private String financeLinkmanId;
	// 联系手机
	private String linkManMobile;
	// 联系人固话
	private String linkManPhone;
	// 联系方式
	private String contactType;
	// 开户区县
	private String bankArea;
	// 最后更新部门ID
	private String lastUpdateDeptId;
	// 创建部门ID
	private String createDeptId;
	// 所属客户
	private String belongcustom;
	// 状态 正常：0；  审批中：1  ；无效 ：2；
	private String status;
	
	
	/**
	 * <p>
	 * Description:bank<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * <p>
	 * Description:bank<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * <p>
	 * Description:bankId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * <p>
	 * Description:bankId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * <p>
	 * Description:subBankname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getSubBankname() {
		return subBankname;
	}

	/**
	 * <p>
	 * Description:subBankname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSubBankname(String subBankname) {
		this.subBankname = subBankname;
	}


	/**
	 * <p>
	 * Description:subBanknameId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getSubBanknameId() {
		return subBanknameId;
	}


	/**
	 * <p>
	 * Description:subBanknameId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSubBanknameId(String subBanknameId) {
		this.subBanknameId = subBanknameId;
	}

	/**
	 * <p>
	 * Description:isdefaultaccount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsdefaultaccount() {
		return isdefaultaccount;
	}


	/**
	 * <p>
	 * Description:isdefaultaccount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsdefaultaccount(Boolean isdefaultaccount) {
		this.isdefaultaccount = isdefaultaccount;
	}


	/**
	 * <p>
	 * Description:bankAccount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBankAccount() {
		return bankAccount;
	}


	/**
	 * <p>
	 * Description:bankAccount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}


	/**
	 * <p>
	 * Description:countName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCountName() {
		return countName;
	}


	/**
	 * <p>
	 * Description:countName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCountName(String countName) {
		this.countName = countName;
	}

	/**
	 * <p>
	 * Description:relation<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRelation() {
		return relation;
	}


	/**
	 * <p>
	 * Description:relation<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}


	/**
	 * <p>
	 * Description:bankProvinceId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBankProvinceId() {
		return bankProvinceId;
	}

	/**
	 * <p>
	 * Description:bankProvinceId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBankProvinceId(String bankProvinceId) {
		this.bankProvinceId = bankProvinceId;
	}

	/**
	 * <p>
	 * Description:bankProvinceName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBankProvinceName() {
		return bankProvinceName;
	}

	/**
	 * <p>
	 * Description:bankProvinceName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBankProvinceName(String bankProvinceName) {
		this.bankProvinceName = bankProvinceName;
	}

	/**
	 * <p>
	 * Description:accountNature<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAccountNature() {
		return accountNature;
	}

	/**
	 * <p>
	 * Description:accountNature<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}

	/**
	 * <p>
	 * Description:accountUse<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAccountUse() {
		return accountUse;
	}

	/**
	 * <p>
	 * Description:accountUse<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAccountUse(String accountUse) {
		this.accountUse = accountUse;
	}

	/**
	 * <p>
	 * Description:bankCityId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBankCityId() {
		return bankCityId;
	}

	/**
	 * <p>
	 * Description:bankCityId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBankCityId(String bankCityId) {
		this.bankCityId = bankCityId;
	}

	/**
	 * <p>
	 * Description:bankCityName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBankCityName() {
		return bankCityName;
	}

	/**
	 * <p>
	 * Description:bankCityName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBankCityName(String bankCityName) {
		this.bankCityName = bankCityName;
	}

	/**
	 * <p>
	 * Description:financeLinkman<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFinanceLinkman() {
		return financeLinkman;
	}

	/**
	 * <p>
	 * Description:financeLinkman<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFinanceLinkman(String financeLinkman) {
		this.financeLinkman = financeLinkman;
	}

	/**
	 * <p>
	 * Description:financeLinkmanId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFinanceLinkmanId() {
		return financeLinkmanId;
	}

	/**
	 * <p>
	 * Description:financeLinkmanId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFinanceLinkmanId(String financeLinkmanId) {
		this.financeLinkmanId = financeLinkmanId;
	}

	/**
	 * <p>
	 * Description:linkManMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManMobile() {
		return linkManMobile;
	}

	/**
	 * <p>
	 * Description:linkManMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}

	/**
	 * <p>
	 * Description:linkManPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManPhone() {
		return linkManPhone;
	}

	/**
	 * <p>
	 * Description:linkManPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}


	/**
	 * <p>
	 * Description:contactType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactType() {
		return contactType;
	}


	/**
	 * <p>
	 * Description:contactType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}


	/**
	 * <p>
	 * Description:bankArea<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBankArea() {
		return bankArea;
	}


	/**
	 * <p>
	 * Description:bankArea<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBankArea(String bankArea) {
		this.bankArea = bankArea;
	}


	/**
	 * <p>
	 * Description:lastUpdateDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLastUpdateDeptId() {
		return lastUpdateDeptId;
	}


	/**
	 * <p>
	 * Description:lastUpdateDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLastUpdateDeptId(String lastUpdateDeptId) {
		this.lastUpdateDeptId = lastUpdateDeptId;
	}


	/**
	 * <p>
	 * Description:createDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCreateDeptId() {
		return createDeptId;
	}


	/**
	 * <p>
	 * Description:createDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCreateDeptId(String createDeptId) {
		this.createDeptId = createDeptId;
	}


	/**
	 * <p>
	 * Description:belongcustom<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBelongcustom() {
		return belongcustom;
	}


	/**
	 * <p>
	 * Description:belongcustom<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBelongcustom(String belongcustom) {
		this.belongcustom = belongcustom;
	}


	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return serialversionuid : return the property serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
