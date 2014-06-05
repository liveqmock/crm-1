package com.deppon.crm.module.customer.shared.domain;

import java.util.List;

/**
 * @作者：罗典
 * @时间：2012-4-9
 * @描述：会员查询结果
 * */
public class MemberResult {
	//客户id
    private String  custId;
    //客户编码
    private String custNum;
    //客户名称
    private String custName;
    //客户等级 会员管理 查询结果
    private String custGrade;
    //联系人编码
    private String contactNum;
    //联系人姓名
    private String contactName;
    //手机号码
    private String mobileNum;
    //固定电话
    private String telNum;
    //所属部门 会员管理 查询结果
    private String deptId;
    //所属部门 会员管理 查询结果
    private String deptName;
    //地址
    private String address;
    //备注
    private String remark;
    //行业
    private String trade;
    //二级行业
    private String secondTrade;
    //客户类型
    private String custType;
    //税务登记号
    private String taxregNum;
    //身份证
    private String idCard;
    //城市
    private String cityId;
    //城市
    private String cityName;
    // 是否主联系人1为是，0为否
 	private Boolean isMainLinkMan;
 	// 联系人Id
 	private String contactId;
 	//@注 该字段是前端查询结果grid中用于拼装 客户编码+客户等级+客户名称+……
    private String custGroup;
    //客户类别（零担快递）
    private String custCategory;
    //会员状态
    private String status;
	// 版本号
	private Integer versionNumber;
	//
	private List<CustLabel> custLabels;
	//二期增加
	//是否大客户 1 是 0 不是
	private Boolean isKeyCustomer;
	//客户性质 （潜客 散客 固定客户）
	private String custGroupPSR;
	/**
	 * 
	 * Description:custLabels<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-1
	 */
	public List<CustLabel> getCustLabels() {
		return custLabels;
	}
	/**
	 * 
	 * Description:custLabels<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-1
	 */
	public void setCustLabels(List<CustLabel> custLabels) {
		this.custLabels = custLabels;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustId(String custId) {
		this.custId = custId;
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
	 * Description:contactNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactNum() {
		return contactNum;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
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
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	 * <p>
	 * Description:remark<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * <p>
	 * Description:remark<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * Description:isMainLinkMan<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsMainLinkMan() {
		return isMainLinkMan;
	}
	/**
	 * <p>
	 * Description:isMainLinkMan<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsMainLinkMan(Boolean isMainLinkMan) {
		this.isMainLinkMan = isMainLinkMan;
	}
	/**
	 * <p>
	 * Description:contactId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * <p>
	 * Description:contactId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * <p>
	 * Description:custGroup<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustGroup() {
		return custGroup;
	}
	/**
	 * <p>
	 * Description:custGroup<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
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
	 * <p>
	 * Description:versionNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getVersionNumber() {
		return versionNumber;
	}
	/**
	 * <p>
	 * Description:versionNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}
	/**
	 *@user pgj
	 *2013-7-19下午8:03:40
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
	 *@return  custCategory;
	 */
	public String getCustCategory() {
		return custCategory;
	}
	/**
	 * @param custCategory : set the property custCategory.
	 */
	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}
	/**
	 *@author chenaichun
	 * @date 2014年4月17日 下午4:19:03 
	 *@return the isKeyCustomer
	 */
	public Boolean getIsKeyCustomer() {
		return isKeyCustomer;
	}
	/**
	 *@author chenaichun
	 * @date 2014年4月17日 下午4:19:03 
	 * @param isKeyCustomer the isKeyCustomer to set
	 */
	public void setIsKeyCustomer(Boolean isKeyCustomer) {
		this.isKeyCustomer = isKeyCustomer;
	}
	/**
	 *@author chenaichun
	 * @date 2014年4月17日 下午4:19:03 
	 *@return the custGroupPSR
	 */
	public String getCustGroupPSR() {
		return custGroupPSR;
	}
	/**
	 *@author chenaichun
	 * @date 2014年4月17日 下午4:19:03 
	 * @param custGroupPSR the custGroupPSR to set
	 */
	public void setCustGroupPSR(String custGroupPSR) {
		this.custGroupPSR = custGroupPSR;
	}
}
