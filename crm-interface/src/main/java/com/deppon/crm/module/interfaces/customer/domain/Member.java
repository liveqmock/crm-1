package com.deppon.crm.module.interfaces.customer.domain;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-21
 * @描述：会员实体,发生变更时需要通知官网，以免WS出现解析错误
 * */
public class Member extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 客户编码
	private String custNumber;
	// 客户等级
	private String degree;
	// 客户姓名
	private String custName;
	// 所属行业
	private String tradeId;
	// 客户类型
	private String custType;
	// 税务登记号
	private String taxregNumber;
	// 公司性质
	private String companyProperty;
	// 客户属性
	private String custNature;
	// 所在省份
	private String province;
	// 所在省份ID
	private String provinceId;
	// 所在城市
	private String city;
	// 所在城市ID
	private String cityId;
	// 客户注册地址
	private String registAddress;
	// 是否特殊客户 1（true）为特殊客户，0为非特殊客户
	private Boolean isSpecial;
	//仅限主联系人兑换积分
	private Boolean isRedeempoints;
	// 是否部门重要客户1为是，2为否
	private Boolean isImportCustor;
	// 客户潜力类型
	private String custPotentialType;
	// 是否接受德邦营销信息
	private Boolean isAcceptMarket;
	// 品牌价值
	private String brandWorth;
	// 来源渠道
	private String channelSource;
	// 偏好渠道
	private String channel;
	// 偏好服务
	private String preferenceService;
	// 上一年公司规模
	private String companyScop;
	// 上一年公司利润
	private Double lastYearProfit;
	// 上一年财务公司收入
	private Double lastYearIncome;
	// 是否集中结算1为是，2为否
	private Boolean isFocusPay;
	// 集中结算部门
	private String focusDeptId;
	// 集中结算部门名称
	private String focusDeptName;
	// 发票抬头
	private String billTitle;
	// 是否母公司1为是，2为否
	private Boolean isParentCompany;
	// 客户所属母公司名称
	private String parentNumber;
	// 客户所属母公司ID
	private String parentCompanyId;
	// 注册资金
	private Double registerFund;
	// 临欠额度
	private Double procRedit;
	// 推荐人
	private String recommendCust;
	// 备注
	private String remark;
	// 主联系人
	private Contact mainContact;
	// 联系人集合
	private List<Contact> contactList;
	// 接送货地址接货
	//private List<ShuttleAddress> shuttleAddressList;
	// 银行账号
	private List<Account> accountList;
	// 会员操作日志
	//private List<MemberOperationLog> logs;
	// 接送货主地址
	private String jshAddress;
	// 客户简称
	private String simpleName;
	// 客户业务类型
	private String bussType;
	// 是否异地调货 1为是，2为否
	private Boolean isTranGoods;
	// 所属区域
	private String areaId;
	// 所属部门
	private String deptId;
	//所属部门名称
	private String deptName;
	// 客户ID
	private String custId;
	// 主要联系人ID
	private String contactId;
	// 升级来源
	private String upgradeSource;
	// 客户状态      正常：0；  审批中：1  ；无效 ：2；
	private String custStatus;
	// 成为会员时间
	private Date becomeMemTime;
	// 责任人(维护人)ID
	private String responsibillity;
	// 版本号
	private Integer versionNumber;
	// 客户上一等级
	private String nextLevel;
	// 上次升降级时间
	private Date lastChangTime;
	// 可使用月结额度
	private Double surplusMonthlyStatementMoney; 
	//是否财务完结
	private boolean finOver;
	//用处是:得到客户的所在省份城市 可以判别是否是香港
    private BussinessDept bussinessDept;
	//客户二级行业
    private String secondTrade;
    /*2013-04-11唐亮开始新增*/
    //客户标签
  	//private List<CustLabel> custLabels;
    /*2013-04-11唐亮新增完毕*/
	//潜客渠道来源
	private String potenSource;
	//客户类别
	private String custCategory;
	//是否是快递月结客户，提供给官网
	private Boolean isExPayWay;
	
	/**
	 * @return the isExPayWay
	 */
	public Boolean getIsExPayWay() {
		return isExPayWay;
	}

	/**
	 * @param isExPayWay the isExPayWay to set
	 */
	public void setIsExPayWay(Boolean isExPayWay) {
		this.isExPayWay = isExPayWay;
	}

	/**
	 * <p>
	 * Description:bussinessDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-5
	 */
	public BussinessDept getBussinessDept() {
		return bussinessDept;
	}

	/**
	 * <p>
	 * Description:bussinessDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-5
	 */
	public void setBussinessDept(BussinessDept bussinessDept) {
		this.bussinessDept = bussinessDept;
	}


	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * <p>
	 * Description:degree<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * <p>
	 * Description:degree<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDegree(String degree) {
		this.degree = degree;
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
	 * Description:tradeId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTradeId() {
		return tradeId;
	}
	/**
	 * <p>
	 * Description:tradeId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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
	 * Description:taxregNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTaxregNumber() {
		return taxregNumber;
	}
	/**
	 * <p>
	 * Description:taxregNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTaxregNumber(String taxregNumber) {
		this.taxregNumber = taxregNumber;
	}
	/**
	 * <p>
	 * Description:companyProperty<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCompanyProperty() {
		return companyProperty;
	}
	/**
	 * <p>
	 * Description:companyProperty<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCompanyProperty(String companyProperty) {
		this.companyProperty = companyProperty;
	}
	/**
	 * <p>
	 * Description:custNature<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustNature() {
		return custNature;
	}
	/**
	 * <p>
	 * Description:custNature<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustNature(String custNature) {
		this.custNature = custNature;
	}
	/**
	 * <p>
	 * Description:province<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * <p>
	 * Description:province<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * <p>
	 * Description:provinceId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getProvinceId() {
		return provinceId;
	}
	/**
	 * <p>
	 * Description:provinceId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * <p>
	 * Description:city<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCity() {
		return city;
	}
	/**
	 * <p>
	 * Description:city<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * Description:registAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRegistAddress() {
		return registAddress;
	}
	/**
	 * <p>
	 * Description:registAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}
	/**
	 * <p>
	 * Description:isSpecial<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsSpecial() {
		return isSpecial;
	}
	/**
	 * <p>
	 * Description:isSpecial<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	/**
	 * <p>
	 * Description:isRedeempoints<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsRedeempoints() {
		return isRedeempoints;
	}
	/**
	 * <p>
	 * Description:isRedeempoints<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsRedeempoints(Boolean isRedeempoints) {
		this.isRedeempoints = isRedeempoints;
	}
	/**
	 * <p>
	 * Description:isImportCustor<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsImportCustor() {
		return isImportCustor;
	}
	/**
	 * <p>
	 * Description:isImportCustor<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsImportCustor(Boolean isImportCustor) {
		this.isImportCustor = isImportCustor;
	}
	/**
	 * <p>
	 * Description:custPotentialType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustPotentialType() {
		return custPotentialType;
	}
	/**
	 * <p>
	 * Description:custPotentialType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustPotentialType(String custPotentialType) {
		this.custPotentialType = custPotentialType;
	}
	/**
	 * <p>
	 * Description:isAcceptMarket<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsAcceptMarket() {
		return isAcceptMarket;
	}
	/**
	 * <p>
	 * Description:isAcceptMarket<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsAcceptMarket(Boolean isAcceptMarket) {
		this.isAcceptMarket = isAcceptMarket;
	}
	/**
	 * <p>
	 * Description:brandWorth<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBrandWorth() {
		return brandWorth;
	}
	/**
	 * <p>
	 * Description:brandWorth<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBrandWorth(String brandWorth) {
		this.brandWorth = brandWorth;
	}
	/**
	 * <p>
	 * Description:channelSource<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getChannelSource() {
		return channelSource;
	}
	/**
	 * <p>
	 * Description:channelSource<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}
	/**
	 * <p>
	 * Description:channel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * <p>
	 * Description:channel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * <p>
	 * Description:preferenceService<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPreferenceService() {
		return preferenceService;
	}
	/**
	 * <p>
	 * Description:preferenceService<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPreferenceService(String preferenceService) {
		this.preferenceService = preferenceService;
	}
	/**
	 * <p>
	 * Description:companyScop<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCompanyScop() {
		return companyScop;
	}
	/**
	 * <p>
	 * Description:companyScop<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCompanyScop(String companyScop) {
		this.companyScop = companyScop;
	}
	/**
	 * <p>
	 * Description:lastYearProfit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getLastYearProfit() {
		return lastYearProfit;
	}
	/**
	 * <p>
	 * Description:lastYearProfit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLastYearProfit(Double lastYearProfit) {
		this.lastYearProfit = lastYearProfit;
	}
	/**
	 * <p>
	 * Description:lastYearIncome<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getLastYearIncome() {
		return lastYearIncome;
	}
	/**
	 * <p>
	 * Description:lastYearIncome<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLastYearIncome(Double lastYearIncome) {
		this.lastYearIncome = lastYearIncome;
	}
	/**
	 * <p>
	 * Description:isFocusPay<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsFocusPay() {
		return isFocusPay;
	}
	/**
	 * <p>
	 * Description:isFocusPay<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsFocusPay(Boolean isFocusPay) {
		this.isFocusPay = isFocusPay;
	}
	/**
	 * <p>
	 * Description:focusDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFocusDeptId() {
		return focusDeptId;
	}
	/**
	 * <p>
	 * Description:focusDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFocusDeptId(String focusDeptId) {
		this.focusDeptId = focusDeptId;
	}
	/**
	 * <p>
	 * Description:focusDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFocusDeptName() {
		return focusDeptName;
	}
	/**
	 * <p>
	 * Description:focusDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFocusDeptName(String focusDeptName) {
		this.focusDeptName = focusDeptName;
	}
	/**
	 * <p>
	 * Description:billTitle<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBillTitle() {
		return billTitle;
	}
	/**
	 * <p>
	 * Description:billTitle<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}
	/**
	 * <p>
	 * Description:isParentCompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsParentCompany() {
		return isParentCompany;
	}
	/**
	 * <p>
	 * Description:isParentCompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsParentCompany(Boolean isParentCompany) {
		this.isParentCompany = isParentCompany;
	}
	/**
	 * <p>
	 * Description:parentNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getParentNumber() {
		return parentNumber;
	}
	/**
	 * <p>
	 * Description:parentNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}
	/**
	 * <p>
	 * Description:parentCompanyId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getParentCompanyId() {
		return parentCompanyId;
	}
	/**
	 * <p>
	 * Description:parentCompanyId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setParentCompanyId(String parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
	}
	/**
	 * <p>
	 * Description:registerFund<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getRegisterFund() {
		return registerFund;
	}
	/**
	 * <p>
	 * Description:registerFund<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRegisterFund(Double registerFund) {
		this.registerFund = registerFund;
	}
	/**
	 * <p>
	 * Description:procRedit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getProcRedit() {
		return procRedit;
	}
	/**
	 * <p>
	 * Description:procRedit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setProcRedit(Double procRedit) {
		this.procRedit = procRedit;
	}
	/**
	 * <p>
	 * Description:recommendCust<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRecommendCust() {
		return recommendCust;
	}
	/**
	 * <p>
	 * Description:recommendCust<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRecommendCust(String recommendCust) {
		this.recommendCust = recommendCust;
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
	 * Description:mainContact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contact getMainContact() {
		return mainContact;
	}
	/**
	 * <p>
	 * Description:mainContact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMainContact(Contact mainContact) {
		this.mainContact = mainContact;
	}
	
	/**
	 * <p>
	 * Description:accountList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<Account> getAccountList() {
		return accountList;
	}
	/**
	 * <p>
	 * Description:accountList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	
	/**
	 * <p>
	 * Description:jshAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getJshAddress() {
		return jshAddress;
	}
	/**
	 * <p>
	 * Description:jshAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setJshAddress(String jshAddress) {
		this.jshAddress = jshAddress;
	}
	/**
	 * <p>
	 * Description:simpleName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getSimpleName() {
		return simpleName;
	}
	/**
	 * <p>
	 * Description:simpleName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	/**
	 * <p>
	 * Description:bussType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBussType() {
		return bussType;
	}
	/**
	 * <p>
	 * Description:bussType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBussType(String bussType) {
		this.bussType = bussType;
	}
	/**
	 * <p>
	 * Description:isTranGoods<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsTranGoods() {
		return isTranGoods;
	}
	/**
	 * <p>
	 * Description:isTranGoods<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsTranGoods(Boolean isTranGoods) {
		this.isTranGoods = isTranGoods;
	}
	/**
	 * <p>
	 * Description:areaId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * <p>
	 * Description:areaId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
	 * Description:upgradeSource<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getUpgradeSource() {
		return upgradeSource;
	}
	/**
	 * <p>
	 * Description:upgradeSource<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setUpgradeSource(String upgradeSource) {
		this.upgradeSource = upgradeSource;
	}
	/**
	 * <p>
	 * Description:custStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustStatus() {
		return custStatus;
	}
	/**
	 * <p>
	 * Description:custStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	/**
	 * <p>
	 * Description:becomeMemTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getBecomeMemTime() {
		return becomeMemTime;
	}
	/**
	 * <p>
	 * Description:becomeMemTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBecomeMemTime(Date becomeMemTime) {
		this.becomeMemTime = becomeMemTime;
	}
	/**
	 * <p>
	 * Description:responsibillity<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getResponsibillity() {
		return responsibillity;
	}
	/**
	 * <p>
	 * Description:responsibillity<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setResponsibillity(String responsibillity) {
		this.responsibillity = responsibillity;
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
	 * <p>
	 * Description:nextLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getNextLevel() {
		return nextLevel;
	}
	/**
	 * <p>
	 * Description:nextLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNextLevel(String nextLevel) {
		this.nextLevel = nextLevel;
	}
	/**
	 * <p>
	 * Description:lastChangTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getLastChangTime() {
		return lastChangTime;
	}
	/**
	 * <p>
	 * Description:lastChangTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLastChangTime(Date lastChangTime) {
		this.lastChangTime = lastChangTime;
	}
	/**
	 * <p>
	 * Description:surplusMonthlyStatementMoney<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getSurplusMonthlyStatementMoney() {
		return surplusMonthlyStatementMoney;
	}
	/**
	 * <p>
	 * Description:surplusMonthlyStatementMoney<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSurplusMonthlyStatementMoney(Double surplusMonthlyStatementMoney) {
		this.surplusMonthlyStatementMoney = surplusMonthlyStatementMoney;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isFinOver() {
		return finOver;
	}

	public void setFinOver(boolean finOver) {
		this.finOver = finOver;
	}
	
	/**
	 *@user pgj
	 *2013-7-18上午9:28:07
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
	 *@user pgj
	 *2013-7-31下午5:36:22
	 * @return potenSource : return the property potenSource.
	 */
	public String getPotenSource() {
		return potenSource;
	}

	/**
	 * @param potenSource : set the property potenSource.
	 */
	public void setPotenSource(String potenSource) {
		this.potenSource = potenSource;
	}

	/**
	 *@user pgj
	 *2013-9-23下午8:23:41
	 * @return custCategory : return the property custCategory.
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

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
}
