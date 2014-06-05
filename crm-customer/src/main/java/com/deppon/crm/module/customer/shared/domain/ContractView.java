/**
 * @description
 * @author 赵斌
 * @2012-4-11
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

/**
 * @author 赵斌
 *
 */
public class ContractView {

	//合同信息
	private ContractDetailView contract;
	//客户名称
	private String custName;
	//客户编码
	private String custNum;
	//行业
	private String trade;
	//二级行业
	private String secondTrade;
	//客户等级
	private String custGrade;
	//联系人姓名
	private String contactName;
	//联系人手机
	private String mobileNum;
	//联系人电话
	private String telNum;
	//客户类型
	private String custType;
	//税务登记号
	private String taxregNum;
	//身份证号
	private String idCard;
	//城市Id
	private String cityId;
	//城市name
	private String cityName;
	//地址
	private String address;
	/*2013-05-07  唐亮开始增加字段*/
	//是否异地调货
	private Boolean ifForeignGoods;
	//催款部门标杆编码
	private String dunningDeptCode;
	//催款部门名称
	private String dunningDeptName; 
	/*2013-05-07  唐亮增加字段完毕*/
	
	//价格版本时间 
	private Date priceVersionDate;

	/**
	 * <p>
	 * Description:contract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public ContractDetailView getContract() {
		return contract;
	}
	/**
	 * 
	 * Description:ifForeignGoods<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public Boolean getIfForeignGoods() {
		return ifForeignGoods;
	}
	/**
	 * 
	 * Description:ifForeignGoods<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setIfForeignGoods(Boolean ifForeignGoods) {
		this.ifForeignGoods = ifForeignGoods;
	}
	/**
	 * 
	 * Description:dunningDeptCode<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getDunningDeptCode() {
		return dunningDeptCode;
	}
	/**
	 * 
	 * Description:dunningDeptCode<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setDunningDeptCode(String dunningDeptCode) {
		this.dunningDeptCode = dunningDeptCode;
	}
	/**
	 * 
	 * Description:dunningDeptName<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getDunningDeptName() {
		return dunningDeptName;
	}
	/**
	 * 
	 * Description:dunningDeptName<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setDunningDeptName(String dunningDeptName) {
		this.dunningDeptName = dunningDeptName;
	}
	/**
	 * <p>
	 * Description:contract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContract(ContractDetailView contract) {
		this.contract = contract;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustNum() {
		return custNum;
	}
	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * <p>
	 * Description:trade<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTrade() {
		return trade;
	}
	/**
	 * <p>
	 * Description:trade<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}
	/**
	 * <p>
	 * Description:custGrade<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustGrade() {
		return custGrade;
	}
	/**
	 * <p>
	 * Description:custGrade<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustGrade(String custGrade) {
		this.custGrade = custGrade;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:mobileNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMobileNum() {
		return mobileNum;
	}
	/**
	 * <p>
	 * Description:mobileNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	/**
	 * <p>
	 * Description:telNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTelNum() {
		return telNum;
	}
	/**
	 * <p>
	 * Description:telNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	/**
	 * <p>
	 * Description:custType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * <p>
	 * Description:custType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * <p>
	 * Description:taxregNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTaxregNum() {
		return taxregNum;
	}
	/**
	 * <p>
	 * Description:taxregNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTaxregNum(String taxregNum) {
		this.taxregNum = taxregNum;
	}
	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * <p>
	 * Description:cityId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * <p>
	 * Description:cityId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * <p>
	 * Description:cityName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * <p>
	 * Description:cityName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * <p>
	 * Description:address<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * <p>
	 * Description:address<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 *@user pgj
	 *2013-7-19下午8:42:59
	 * @return secondTrade : return the property secondTrade.
	 */
	public String getSecondTrade() {
		return secondTrade;
	}
	/**
	 * @param secondTrade : set the property secondTrade.
	 */
	public void setSecondTrade(String secondTrade) {
		this.secondTrade = secondTrade;
	}
	/**
	 * Description:priceVersionDate<br />
	 * @author CoCo
	 * @version 0.1 2013-8-5
	 */
	public Date getPriceVersionDate() {
		return priceVersionDate;
	}
	/**
	 * Description:priceVersionDate<br />
	 * @author CoCo
	 * @version 0.1 2013-8-5
	 */
	public void setPriceVersionDate(Date priceVersionDate) {
		this.priceVersionDate = priceVersionDate;
	}
}
