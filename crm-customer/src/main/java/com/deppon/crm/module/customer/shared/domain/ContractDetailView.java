package com.deppon.crm.module.customer.shared.domain;


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
/**
 * <p>
 * TODO<br />
 * </p>
 * @title ContractDetailView.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author 唐亮
 * @version 0.1 2013-9-21
 */
public class ContractDetailView extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 零担结款方式 
	private String payWay;
	//快递结款方式
	private String exPayWay;
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
	// 客户ID
	private String custId;
	// 部门ID
	private String deptId;
	// 部门名称
	private String deptName;
	// 是否折扣
	private Boolean iddisCount;
	// 合同状态  1生效 2失效
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
	//快递优惠类型
	private String exPreferentialType;
	//合同部门对应关系
	private List<ContractDeptView> contractDepartList;
	//合同工作流
	private List<ContractWorkflowView> contractWorkflowList;
	//合同附件
	private List<FileInfo> fileInfoList;
	//零担合同优惠信息
	private Preferential preferential;
	//快递合同优惠信息
	private Preferential exPreferential;
	//零担合同近三个月发货金额
	private String arrAmount;
	//快递合同近三个月发货金额
	private String exArrAmount;
	// 最后修改部门
	private Department lastModifyDept = new Department();

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
	//催款部门名称
	private String dunningDeptName; 
	/*2013-05-07  唐亮增加字段完毕*/
	//价格版本时间
	private Date priceVersionDate;
	//快递价格版本时间
	private Date exPriceVersionDate;
	//合同类型
	private String contractType;
	
	/**
	 * Description:税务标记添加字段<br />
	 * @author 陈爱春
	 */
	//签署合同公司
	private String signCompany;
	//发票标记
	private String invoiceType;
	
	/**
	 *@author chenaichun
	 * @date 2013-11-19 下午5:49:20 
	 *@return the signCompany
	 */
	public String getSignCompany() {
		return signCompany;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-19 下午5:49:20 
	 * @param signCompany the signCompany to set
	 */
	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-19 下午5:49:20 
	 *@return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-19 下午5:49:20 
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 
	 * Description:contractType<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-22
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * 
	 * Description:contractType<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-22
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * 
	 * Description:exPriceVersionDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public Date getExPriceVersionDate() {
		return exPriceVersionDate;
	}
	/**
	 * 
	 * Description:exPriceVersionDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public void setExPriceVersionDate(Date exPriceVersionDate) {
		this.exPriceVersionDate = exPriceVersionDate;
	}
	/**
	 * 
	 * Description:exPreferentialType<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public String getExPreferentialType() {
		return exPreferentialType;
	}
	/**
	 * 
	 * Description:exPreferentialType<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public void setExPreferentialType(String exPreferentialType) {
		this.exPreferentialType = exPreferentialType;
	}
	/**
	 * 
	 * Description:exPayWay<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public String getExPayWay() {
		return exPayWay;
	}
	/**
	 * 
	 * Description:exPayWay<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public void setExPayWay(String exPayWay) {
		this.exPayWay = exPayWay;
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
	 * 
	 * Description:exPreferential<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public Preferential getExPreferential() {
		return exPreferential;
	}
	/**
	 * 
	 * Description:exPreferential<br />
	 * @author 唐亮
	 * @version 0.1 2013-9-21
	 */
	public void setExPreferential(Preferential exPreferential) {
		this.exPreferential = exPreferential;
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
	 * <p>
	 * Description:debtDays<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public int getDebtDays() {
		return debtDays;
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
	 * Description:debtDays<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setDebtDays(int debtDays) {
		this.debtDays = debtDays;
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
	 * Description:contractDepartList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ContractDeptView> getContractDepartList() {
		return contractDepartList;
	}


	/**
	 * <p>
	 * Description:contractDepartList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractDepartList(List<ContractDeptView> contractDepartList) {
		this.contractDepartList = contractDepartList;
	}


	/**
	 * <p>
	 * Description:contractWorkflowList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ContractWorkflowView> getContractWorkflowList() {
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
			List<ContractWorkflowView> contractWorkflowList) {
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
	 * Description:lastModifyDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getLastModifyDept() {
		return lastModifyDept;
	}

	/**
	 * <p>
	 * Description:lastModifyDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLastModifyDept(Department lastModifyDept) {
		this.lastModifyDept = lastModifyDept;
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

	@Override
	public String toString() {
		return "ContracDetailViewt [payWay=" + payWay + ", arrearaMount="
				+ arrearaMount + ", linkManName=" + linkManName
				+ ", linkManPhone=" + linkManPhone + ", linkManMobile="
				+ linkManMobile + ", linkManAddress=" + linkManAddress
				+ ", reconDate=" + reconDate + ", invoicDate=" + invoicDate
				+ ", resultDate=" + resultDate + ", contractBeginDate="
				+ contractBeginDate + ", contractendDate=" + contractendDate
				+ ", application=" + application + ", custId=" + custId
				+ ", deptId=" + deptId + ", deptName=" + deptName
				+ ", iddisCount=" + iddisCount + ", contractStatus="
				+ contractStatus + ", contractSubject=" + contractSubject
				+ ", regicapital=" + regicapital + ", beforeContractNum="
				+ beforeContractNum + ", contractNum=" + contractNum
				+ ", goodsName=" + goodsName + ", custCompany=" + custCompany
				+ ", contactId=" + contactId + ", preferentialType="
				+ preferentialType + ", contractDepartList="
				+ contractDepartList + ", contractWorkflowList="
				+ contractWorkflowList + ", fileInfoList=" + fileInfoList
				+ ", preferential=" + preferential + "]";
	}

}
