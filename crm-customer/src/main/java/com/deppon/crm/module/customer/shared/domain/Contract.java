package com.deppon.crm.module.customer.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-15
 * @功能：合同信息
 * @修改：赵斌
 * @功能：补充合同信息
 * @时间：2012-04-09
 * */
public class Contract extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 结款方式 
	private String payWay;
	// 申请欠款额度
	private Double arrearaMount;
	// 联系人姓名
	private String linkManName;
	// 联系人固话
	private String linkManPhone;
	// 联系人手机
	private String linkManMobile;
	// 联系人详细地址
	private String linkManAddress;
	// 对账日期
	private int reconDate;
	// 开发票日期
	private int invoicDate;
	// 结款日期
	private int resultDate;
	// 合同起始日期
	private Date contractBeginDate;
	// 合同到期日期
	private Date contractendDate;
	// 申请事由
	private String application;
	// 会员
	private Member member = new Member();
	//所属部门
	private Department dept = new Department();
	// 是否折扣
	private Boolean iddisCount;
	// 合同状态  0审批中1生效 2失效3待生效4不同意
	private String contractStatus;
	// 合同主体
	private String contractSubject;
	// 注册资金
	private String regicapital;
	// 原合同编号
	private String beforeContractNum;
	// 合同编号
	private String contractNum;
	// 走货名称
	private String goodsName;
	// 客户全称
	private String custCompany;
	// 联系人ID
	private String contactId;
	// 优惠类型  价格折扣0   协议价1  月发月送2
	private String preferentialType;
	//合同操作记录
	private List<ContractOperatorLog> contractWorkflowList = new ArrayList<ContractOperatorLog>();
	//合同新增时附件
	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
	//合同部门
	private List<ContractDept> contractDepartList= new ArrayList<ContractDept>();
	//合同优惠信息
	private Preferential preferential;
	//原合同信息
	private Contract oldContract;
	//零担近三个月发货金额
	private String arrAmount;
	//快递近三个月发货金额
	private String exArrAmount;
	/**
	 * Description:合同添加字段<br />
	 * @author 李国文
	 */
	//合同月结天数
	private int debtDays;
	//可使用额度
	private double useableArrearAmount;
	/*2013-05-07  唐亮开始增加字段*/
	//是否异地调货
	private Boolean ifForeignGoods;
	//催款部门标杆编码
	private String dunningDeptCode;
	//催款部门
	private String dunningDeptName;
	/*2013-05-07  唐亮增加字段完毕*/
	//零担合同价格版本时间 
	private Date priceVersionDate;
	//快递结款方式
	private String exPayWay;
	//快递优惠方式
	private String exPreferentialType;
	//快递价格版本
	private Date exPriceVersionDate;
	//快递折扣
	private Preferential exPreferential;
	/**
	 * Description:添加税务标记
	 * @author chenaichun
	 */
	//发票标记
	private List<ContractTax> contractTaxList;
	
	/**
	 *@author chenaichun
	 * @date 2013-11-19 上午9:25:34 
	 *@return the contractTaxList
	 */
	public List<ContractTax> getContractTaxList() {
		return contractTaxList;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-19 上午9:25:34 
	 * @param contractTaxList the contractTaxList to set
	 */
	public void setContractTaxList(List<ContractTax> contractTaxList) {
		this.contractTaxList = contractTaxList;
	}
	/**
	 * 
	 * Description:exArrAmount<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public String getExArrAmount() {
		return exArrAmount;
	}
	/**
	 * 
	 * Description:exArrAmount<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public void setExArrAmount(String exArrAmount) {
		this.exArrAmount = exArrAmount;
	}
	/**
	 * <p>
	 * Description:debtDays<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDebtDays(int debtDays) {
		this.debtDays = debtDays;
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
	 * 
	 * Description:debtDays<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public int getDebtDays() {
		return debtDays;
	}
	/**
	 * <p>
	 * Description:useableArrearAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public double getUseableArrearAmount() {
		return useableArrearAmount;
	}
	/**
	 * <p>
	 * Description:useableArrearAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setUseableArrearAmount(double useableArrearAmount) {
		this.useableArrearAmount = useableArrearAmount;
	}


	/**
	 * <p>
	 * Description:payWay<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPayWay() {
		return payWay;
	}
	/**
	 * <p>
	 * Description:payWay<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	/**
	 * <p>
	 * Description:arrearaMount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getArrearaMount() {
		return arrearaMount;
	}
	/**
	 * <p>
	 * Description:arrearaMount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setArrearaMount(Double arrearaMount) {
		this.arrearaMount = arrearaMount;
	}
	/**
	 * <p>
	 * Description:linkManName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManName() {
		return linkManName;
	}
	/**
	 * <p>
	 * Description:linkManName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
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
	 * Description:linkManAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManAddress() {
		return linkManAddress;
	}
	/**
	 * <p>
	 * Description:linkManAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManAddress(String linkManAddress) {
		this.linkManAddress = linkManAddress;
	}
	/**
	 * <p>
	 * Description:reconDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getReconDate() {
		return reconDate;
	}
	/**
	 * <p>
	 * Description:reconDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReconDate(int reconDate) {
		this.reconDate = reconDate;
	}
	/**
	 * <p>
	 * Description:invoicDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getInvoicDate() {
		return invoicDate;
	}
	/**
	 * <p>
	 * Description:invoicDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setInvoicDate(int invoicDate) {
		this.invoicDate = invoicDate;
	}
	/**
	 * <p>
	 * Description:resultDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getResultDate() {
		return resultDate;
	}
	/**
	 * <p>
	 * Description:resultDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setResultDate(int resultDate) {
		this.resultDate = resultDate;
	}
	/**
	 * <p>
	 * Description:contractBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getContractBeginDate() {
		return contractBeginDate;
	}
	/**
	 * <p>
	 * Description:contractBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractBeginDate(Date contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}
	/**
	 * <p>
	 * Description:contractendDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getContractendDate() {
		return contractendDate;
	}
	/**
	 * <p>
	 * Description:contractendDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractendDate(Date contractendDate) {
		this.contractendDate = contractendDate;
	}
	/**
	 * <p>
	 * Description:application<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApplication() {
		return application;
	}
	/**
	 * <p>
	 * Description:application<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApplication(String application) {
		this.application = application;
	}
	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMember(Member member) {
		this.member = member;
	}
	/**
	 * <p>
	 * Description:dept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getDept() {
		return dept;
	}
	/**
	 * <p>
	 * Description:dept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}
	/**
	 * <p>
	 * Description:iddisCount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIddisCount() {
		return iddisCount;
	}
	/**
	 * <p>
	 * Description:iddisCount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIddisCount(Boolean iddisCount) {
		this.iddisCount = iddisCount;
	}
	/**
	 * <p>
	 * Description:contractStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractStatus() {
		return contractStatus;
	}
	/**
	 * <p>
	 * Description:contractStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	/**
	 * <p>
	 * Description:contractSubject<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractSubject() {
		return contractSubject;
	}
	/**
	 * <p>
	 * Description:contractSubject<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractSubject(String contractSubject) {
		this.contractSubject = contractSubject;
	}
	/**
	 * <p>
	 * Description:regicapital<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRegicapital() {
		return regicapital;
	}
	/**
	 * <p>
	 * Description:regicapital<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRegicapital(String regicapital) {
		this.regicapital = regicapital;
	}
	/**
	 * <p>
	 * Description:beforeContractNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBeforeContractNum() {
		return beforeContractNum;
	}
	/**
	 * <p>
	 * Description:beforeContractNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBeforeContractNum(String beforeContractNum) {
		this.beforeContractNum = beforeContractNum;
	}
	/**
	 * <p>
	 * Description:contractNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractNum() {
		return contractNum;
	}
	/**
	 * <p>
	 * Description:contractNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	/**
	 * <p>
	 * Description:goodsName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * <p>
	 * Description:goodsName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * <p>
	 * Description:custCompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustCompany() {
		return custCompany;
	}
	/**
	 * <p>
	 * Description:custCompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustCompany(String custCompany) {
		this.custCompany = custCompany;
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
	 * Description:preferentialType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPreferentialType() {
		return preferentialType;
	}
	/**
	 * <p>
	 * Description:preferentialType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}
	/**
	 * <p>
	 * Description:contractWorkflowList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ContractOperatorLog> getContractWorkflowList() {
		return contractWorkflowList;
	}
	/**
	 * <p>
	 * Description:contractWorkflowList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractWorkflowList(
			List<ContractOperatorLog> contractWorkflowList) {
		this.contractWorkflowList = contractWorkflowList;
	}
	/**
	 * <p>
	 * Description:fileInfoList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}
	/**
	 * <p>
	 * Description:fileInfoList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
	/**
	 * <p>
	 * Description:contractDepartList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ContractDept> getContractDepartList() {
		return contractDepartList;
	}
	/**
	 * <p>
	 * Description:contractDepartList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractDepartList(List<ContractDept> contractDepartList) {
		this.contractDepartList = contractDepartList;
	}
	/**
	 * <p>
	 * Description:preferential<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Preferential getPreferential() {
		return preferential;
	}
	/**
	 * <p>
	 * Description:preferential<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPreferential(Preferential preferential) {
		this.preferential = preferential;
	}
	/**
	 * <p>
	 * Description:oldContract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contract getOldContract() {
		return oldContract;
	}
	/**
	 * <p>
	 * Description:oldContract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOldContract(Contract oldContract) {
		this.oldContract = oldContract;
	}
	/**
	 * <p>
	 * Description:arrAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getArrAmount() {
		return arrAmount;
	}
	/**
	 * <p>
	 * Description:arrAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setArrAmount(String arrAmount) {
		this.arrAmount = arrAmount;
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
	/**
	 *@user pgj
	 *2013-9-20上午11:00:33
	 * @return exPreferentialType : return the property exPreferentialType.
	 */
	public String getExPreferentialType() {
		return exPreferentialType;
	}
	/**
	 *@user pgj
	 *2013-9-20上午11:00:33
	 * @return exPriceVersionDate : return the property exPriceVersionDate.
	 */
	public Date getExPriceVersionDate() {
		return exPriceVersionDate;
	}
	/**
	 *@user pgj
	 *2013-9-20上午11:10:26
	 * @return exPayWay : return the property exPayWay.
	 */
	public String getExPayWay() {
		return exPayWay;
	}
	/**
	 * @param exPayWay : set the property exPayWay.
	 */
	public void setExPayWay(String exPayWay) {
		this.exPayWay = exPayWay;
	}
	/**
	 * @param exPreferentialType : set the property exPreferentialType.
	 */
	public void setExPreferentialType(String exPreferentialType) {
		this.exPreferentialType = exPreferentialType;
	}
	/**
	 * @param exPriceVersionDate : set the property exPriceVersionDate.
	 */
	public void setExPriceVersionDate(Date exPriceVersionDate) {
		this.exPriceVersionDate = exPriceVersionDate;
	}
	/**
	 *@user pgj
	 *2013-9-21上午11:09:12
	 * @return exPreferential : return the property exPreferential.
	 */
	public Preferential getExPreferential() {
		return exPreferential;
	}
	/**
	 * @param exPreferential : set the property exPreferential.
	 */
	public void setExPreferential(Preferential exPreferential) {
		this.exPreferential = exPreferential;
	}
	/**
	 * 
	 * <p>
	 * Description:判断合同信息是否有快递信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-26
	 * @return
	 * boolean
	 */
	public boolean hasExpressInfo() {
		if (null == this || null != this.getExPayWay()
				|| null != this.getExPreferentialType()) {
			return true;
		}
		if (null != this.getExPreferential()) {
			if (1d != this.getExPreferential().getAgentgathRate()
					&& null != this.getExPreferential().getAgentgathRate()
					&& 0d != this.getExPreferential().getAgentgathRate()) {
				return true;
			}
			if (1d != this.getExPreferential().getChargeRebate()
					&& null != this.getExPreferential().getChargeRebate()
					&& 0d != this.getExPreferential().getChargeRebate()) {
				return true;
			}
			if (1d != this.getExPreferential().getInsuredPriceRate()
					&& null != this.getExPreferential().getInsuredPriceRate()
					&& 0d != this.getExPreferential().getInsuredPriceRate()) {
				return true;
			}
			if (1d != this.getExPreferential().getDeliveryPriceRate()
					&& null != this.getExPreferential().getDeliveryPriceRate()
					&& 0d != this.getExPreferential().getDeliveryPriceRate()) {
				return true;
			}
			if (
					 null != this.getExPreferential().getReceivePriceRate()
					) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:判断合同信息是否有零担信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-26
	 * @return
	 * boolean
	 */
	public boolean hasLttInfo(){
		if (null == this || null!=this.getPayWay() || null!=this.getPreferentialType()) {
			return true;
		}
		if (null!=this.getPreferential()) {
			return 1d == this.getPreferential().getAgentgathRate()
					&& 1d == this.getPreferential().getChargeRebate()
					&& 1d == this.getPreferential().getDeliveryPriceRate()
					&& 1d == this.getPreferential().getInsuredPriceRate()
					&& null == this.getPreferential().getReceivePriceRate();
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:获取合同更新的税务信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-11-29
	 * @return
	 * ContractTax
	 */
	public ContractTax getNewContractTax() {
		List<ContractTax> contractTaxList = this.getContractTaxList();
		if (null == contractTaxList || 0 == contractTaxList.size()) {
			return null;
		}
		if (contractTaxList.size() == 1) {
			return contractTaxList.get(0);
		}
		// 当前时间在下月1号前 返回第一条信息（税务信息为旧合同信息）
		if (contractTaxList.get(0).getBeginTime()
				.after(contractTaxList.get(1).getBeginTime())) {
			return contractTaxList.get(0);
		} else {
			return contractTaxList.get(1);
		}
	}
}
