package com.deppon.crm.module.recompense.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.Member;

/**
 * 
 * <p>
 * Description:理赔信息<br />
 * </p>
 * 
 * @title RecompenseApplication.java
 * @package com.deppon.crm.module.recompense.shared.domain
 * @author roy
 * @version 0.1 2013-1-21
 */

public class RecompenseApplication {
	// 编号
	private String id;
	// 理赔单号
	private String recompenseNum;
	// 创建日期
	private Date createDate;
	// 创建人
	private String createUser;
	// 修改日期
	private Date modifyDate;
	// 修改人
	private String modifyUser;

	// 在线理赔申请Id
	private String onlineApplyId;

	// 催办次数
	private Integer hastenCount;
	// 最后催办时间
	private Date lastHastenTime;

	// 状态持续时间
	private Date statusDuration;
	// 工作流号
	private Long workflowId;
	// 运单信息
	private Waybill waybill;
	// 理赔类型
	private String recompenseType;
	// 理赔方式 1: 快速理赔 2: 正常理赔
	private String recompenseMethod;
	// 客户ID
	private Member customer;
	// 正常理赔----索赔方 1: 发货方 2: 收货方
	private String claimParty;
	// 公司名称
	private String companyName;
	// 公司传真
	private String companyFax;
	// 联系电话
	private String companyPhone;
	// ********************
	// 理赔金额
	private Double recompenseAmount;
	// 正常理赔金额
	private Double normalAmount;
	// 实际理赔金额
	private Double realAmount;
	// 费用说明
	private String costExplain;
	// ********************
	// 出险类型
	private String insurType;
	// 出险数量
	private Integer insurQuantity;
	// 所属区域Id
	private String deptId;
	// 所属区域名称
	private String deptName;
	// 出险日期
	private Date insurDate;
	// 报案人
	private String reportMan;
	// 报案部门
	private String reportDept;
	// 付款单
	private PaymentBill paymentBill;
	// 报案时间
	private Date reportDate;
	// 状态
	private String status;
	// 当前操作人
	private String operator;
	// 追偿信息
	private RecalledCompensation recalledCom;
	// 出险信息
	private List<IssueItem> issueItemList = new ArrayList<IssueItem>();
	// 货物交易
	private List<GoodsTrans> goodsTransList = new ArrayList<GoodsTrans>();
	// 附件清单
	private List<RecompenseAttachment> attachmentList = new ArrayList<RecompenseAttachment>();
	// 部门费用
	private List<DeptCharge> DeptChargeList = new ArrayList<DeptCharge>();
	// 责任部门
	private List<ResponsibleDept> responsibleDeptList = new ArrayList<ResponsibleDept>();
	// 奖罚明细
	private List<AwardItem> awardItemList = new ArrayList<AwardItem>();
	// 消息提醒
	private List<MessageReminder> messageReminderList = new ArrayList<MessageReminder>();
	// 跟进消息
	private List<Message> messageList = new ArrayList<Message>();
	// 多赔申请记录
	private List<Overpay> overpayList = new ArrayList<Overpay>();
	// 多赔信息
	private Overpay overpay;
	// 多赔标记
	private boolean overpayFlag = false;
	// 财务部门
	private Finance finance;
	// 冲账金额
	private Double balanceAmount;
	// 冲账列表
	private List<Balance> balanceList = new ArrayList<Balance>();
	// 短信发送次数
	private Integer sendMsgTime;

	// ************************************************
	// 初步处理时间：
	private Date handledTime;
	// 初步处理人：
	private String handledMan;
	// 资料确认时间：
	private Date docConfirmedTime;
	// 资料确认人：
	private String docConfirmedMan;
	// 金额确认时间：
	private Date amountConfirmedTime;
	// 金额确认人：
	private String amountConfirmedMan;
	// 提交审批时间：
	private Date normalApproveSubmitTime;
	// 提交审批人：
	private String normalApproveSubmitMan;
	// 最后审批时间：
	private Date lastApprovedTime;
	// 最后审批人：
	private String lastApprovedMan;
	// 提交付款时间：
	private Date paymentSubmitTime;
	// 提交付款人：
	private String paymentSubmitMan;
	// 出纳付款时间：
	private Date amountPaidTime;
	// 付款审批人：
	private String amountPaidMan;
	// 创建人名
	private String createUserName;
	// 修改人名
	private String modifyUserName;
	// 上报部门名
	private String reportDeptName;
	// 上报人名
	private String reportManName;
	// 审批退回原因
	private String rejectReason;
	// 持续时间
	private Integer duration;
	// 付款单
	private Payment payment;
	// 付款历史
	private List<Payment> paymentList = new ArrayList<Payment>();

	// 出发客户ID
	private String leaveCustomerId;
	// 到达客户ID
	private String arriveCustomerId;
	// 收银员所在部门
	private String cashierDept;
	// 金额确认部门
	private String confirmAmountDept;
	
	// 开户名
	private String openName;
	// 账号
	private String account;

	// 银行
	private String bankName;
	// 支银行
	private String branchName;
	
	/**
	 * <p>
	 * Description:leaveCustomerId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 */
	public String getLeaveCustomerId() {
		return leaveCustomerId;
	}

	/**
	 * <p>
	 * Description:leaveCustomerId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 */
	public void setLeaveCustomerId(String leaveCustomerId) {
		this.leaveCustomerId = leaveCustomerId;
	}

	/**
	 * <p>
	 * Description:arriveCustomerId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 */
	public String getArriveCustomerId() {
		return arriveCustomerId;
	}

	/**
	 * <p>
	 * Description:arriveCustomerId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 */
	public void setArriveCustomerId(String arriveCustomerId) {
		this.arriveCustomerId = arriveCustomerId;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * 
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
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Description:recompenseNum<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseNum() {
		return recompenseNum;
	}

	/**
	 * <p>
	 * Description:recompenseNum<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseNum(String recompenseNum) {
		this.recompenseNum = recompenseNum;
	}

	/**
	 * <p>
	 * Description:createDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * <p>
	 * Description:createDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * <p>
	 * Description:createUser<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * <p>
	 * Description:createUser<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * <p>
	 * Description:modifyDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * <p>
	 * Description:modifyDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * <p>
	 * Description:modifyUser<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getModifyUser() {
		return modifyUser;
	}

	/**
	 * <p>
	 * Description:modifyUser<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	/**
	 * <p>
	 * Description:onlineApplyId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getOnlineApplyId() {
		return onlineApplyId;
	}

	/**
	 * <p>
	 * Description:onlineApplyId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOnlineApplyId(String onlineApplyId) {
		this.onlineApplyId = onlineApplyId;
	}

	/**
	 * <p>
	 * Description:hastenCount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Integer getHastenCount() {
		return hastenCount;
	}

	/**
	 * <p>
	 * Description:hastenCount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setHastenCount(Integer hastenCount) {
		this.hastenCount = hastenCount;
	}

	/**
	 * <p>
	 * Description:lastHastenTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getLastHastenTime() {
		return lastHastenTime;
	}

	/**
	 * <p>
	 * Description:lastHastenTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setLastHastenTime(Date lastHastenTime) {
		this.lastHastenTime = lastHastenTime;
	}

	/**
	 * <p>
	 * Description:statusDuration<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getStatusDuration() {
		return statusDuration;
	}

	/**
	 * <p>
	 * Description:statusDuration<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setStatusDuration(Date statusDuration) {
		this.statusDuration = statusDuration;
	}

	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Long getWorkflowId() {
		return workflowId;
	}

	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	/**
	 * <p>
	 * Description:waybill<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Waybill getWaybill() {
		return waybill;
	}

	/**
	 * <p>
	 * Description:waybill<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setWaybill(Waybill waybill) {
		this.waybill = waybill;
	}

	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseType() {
		return recompenseType;
	}

	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}

	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseMethod() {
		return recompenseMethod;
	}

	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseMethod(String recompenseMethod) {
		this.recompenseMethod = recompenseMethod;
	}

	/**
	 * <p>
	 * Description:customer<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Member getCustomer() {
		return customer;
	}

	/**
	 * <p>
	 * Description:customer<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustomer(Member customer) {
		this.customer = customer;
	}

	/**
	 * <p>
	 * Description:claimParty<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getClaimParty() {
		return claimParty;
	}

	/**
	 * <p>
	 * Description:claimParty<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setClaimParty(String claimParty) {
		this.claimParty = claimParty;
	}

	/**
	 * <p>
	 * Description:companyName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * <p>
	 * Description:companyName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * <p>
	 * Description:companyFax<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCompanyFax() {
		return companyFax;
	}

	/**
	 * <p>
	 * Description:companyFax<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	/**
	 * <p>
	 * Description:companyPhone<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCompanyPhone() {
		return companyPhone;
	}

	/**
	 * <p>
	 * Description:companyPhone<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	/**
	 * <p>
	 * Description:recompenseAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getRecompenseAmount() {
		return recompenseAmount;
	}

	/**
	 * <p>
	 * Description:recompenseAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseAmount(Double recompenseAmount) {
		this.recompenseAmount = recompenseAmount;
	}

	/**
	 * <p>
	 * Description:normalAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getNormalAmount() {
		return normalAmount;
	}

	/**
	 * <p>
	 * Description:normalAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setNormalAmount(Double normalAmount) {
		this.normalAmount = normalAmount;
	}

	/**
	 * <p>
	 * Description:realAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getRealAmount() {
		return realAmount;
	}

	/**
	 * <p>
	 * Description:realAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	/**
	 * <p>
	 * Description:costExplain<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCostExplain() {
		return costExplain;
	}

	/**
	 * <p>
	 * Description:costExplain<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCostExplain(String costExplain) {
		this.costExplain = costExplain;
	}

	/**
	 * <p>
	 * Description:insurType<br />
	 * </p>
	 * 
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
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurType(String insurType) {
		this.insurType = insurType;
	}

	/**
	 * <p>
	 * Description:insurQuantity<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Integer getInsurQuantity() {
		return insurQuantity;
	}

	/**
	 * <p>
	 * Description:insurQuantity<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurQuantity(Integer insurQuantity) {
		this.insurQuantity = insurQuantity;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * <p>
	 * Description:insurDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getInsurDate() {
		return insurDate;
	}

	/**
	 * <p>
	 * Description:insurDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurDate(Date insurDate) {
		this.insurDate = insurDate;
	}

	/**
	 * <p>
	 * Description:reportMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getReportMan() {
		return reportMan;
	}

	/**
	 * <p>
	 * Description:reportMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportMan(String reportMan) {
		this.reportMan = reportMan;
	}

	/**
	 * <p>
	 * Description:reportDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getReportDept() {
		return reportDept;
	}

	/**
	 * <p>
	 * Description:reportDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}

	/**
	 * <p>
	 * Description:paymentBill<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public PaymentBill getPaymentBill() {
		return paymentBill;
	}

	/**
	 * <p>
	 * Description:paymentBill<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPaymentBill(PaymentBill paymentBill) {
		this.paymentBill = paymentBill;
	}

	/**
	 * <p>
	 * Description:reportDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * <p>
	 * Description:reportDate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * <p>
	 * Description:operator<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * <p>
	 * Description:operator<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * <p>
	 * Description:recalledCom<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public RecalledCompensation getRecalledCom() {
		return recalledCom;
	}

	/**
	 * <p>
	 * Description:recalledCom<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecalledCom(RecalledCompensation recalledCom) {
		this.recalledCom = recalledCom;
	}

	/**
	 * <p>
	 * Description:issueItemList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<IssueItem> getIssueItemList() {
		return issueItemList;
	}

	/**
	 * <p>
	 * Description:issueItemList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setIssueItemList(List<IssueItem> issueItemList) {
		this.issueItemList = issueItemList;
	}

	/**
	 * <p>
	 * Description:goodsTransList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<GoodsTrans> getGoodsTransList() {
		return goodsTransList;
	}

	/**
	 * <p>
	 * Description:goodsTransList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setGoodsTransList(List<GoodsTrans> goodsTransList) {
		this.goodsTransList = goodsTransList;
	}

	/**
	 * <p>
	 * Description:attachmentList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<RecompenseAttachment> getAttachmentList() {
		return attachmentList;
	}

	/**
	 * <p>
	 * Description:attachmentList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAttachmentList(List<RecompenseAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * <p>
	 * Description:deptChargeList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<DeptCharge> getDeptChargeList() {
		return DeptChargeList;
	}

	/**
	 * <p>
	 * Description:deptChargeList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptChargeList(List<DeptCharge> deptChargeList) {
		DeptChargeList = deptChargeList;
	}

	/**
	 * <p>
	 * Description:responsibleDeptList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<ResponsibleDept> getResponsibleDeptList() {
		return responsibleDeptList;
	}

	/**
	 * <p>
	 * Description:responsibleDeptList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setResponsibleDeptList(List<ResponsibleDept> responsibleDeptList) {
		this.responsibleDeptList = responsibleDeptList;
	}

	/**
	 * <p>
	 * Description:awardItemList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<AwardItem> getAwardItemList() {
		return awardItemList;
	}

	/**
	 * <p>
	 * Description:awardItemList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAwardItemList(List<AwardItem> awardItemList) {
		this.awardItemList = awardItemList;
	}

	/**
	 * <p>
	 * Description:messageReminderList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<MessageReminder> getMessageReminderList() {
		return messageReminderList;
	}

	/**
	 * <p>
	 * Description:messageReminderList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setMessageReminderList(List<MessageReminder> messageReminderList) {
		this.messageReminderList = messageReminderList;
	}

	/**
	 * <p>
	 * Description:messageList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<Message> getMessageList() {
		return messageList;
	}

	/**
	 * <p>
	 * Description:messageList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	/**
	 * <p>
	 * Description:overpayList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<Overpay> getOverpayList() {
		return overpayList;
	}

	/**
	 * <p>
	 * Description:overpayList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOverpayList(List<Overpay> overpayList) {
		this.overpayList = overpayList;
	}

	/**
	 * <p>
	 * Description:overpay<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Overpay getOverpay() {
		return overpay;
	}

	/**
	 * <p>
	 * Description:overpay<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOverpay(Overpay overpay) {
		this.overpay = overpay;
	}

	/**
	 * <p>
	 * Description:overpayFlag<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public boolean isOverpayFlag() {
		return overpayFlag;
	}

	/**
	 * <p>
	 * Description:overpayFlag<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOverpayFlag(boolean overpayFlag) {
		this.overpayFlag = overpayFlag;
	}

	/**
	 * <p>
	 * Description:finance<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Finance getFinance() {
		return finance;
	}

	/**
	 * <p>
	 * Description:finance<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setFinance(Finance finance) {
		this.finance = finance;
	}

	/**
	 * <p>
	 * Description:balanceAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * <p>
	 * Description:balanceAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * <p>
	 * Description:balanceList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<Balance> getBalanceList() {
		return balanceList;
	}

	/**
	 * <p>
	 * Description:balanceList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBalanceList(List<Balance> balanceList) {
		this.balanceList = balanceList;
	}

	/**
	 * <p>
	 * Description:sendMsgTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Integer getSendMsgTime() {
		return sendMsgTime;
	}

	/**
	 * <p>
	 * Description:sendMsgTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setSendMsgTime(Integer sendMsgTime) {
		this.sendMsgTime = sendMsgTime;
	}

	/**
	 * <p>
	 * Description:handledTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getHandledTime() {
		return handledTime;
	}

	/**
	 * <p>
	 * Description:handledTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setHandledTime(Date handledTime) {
		this.handledTime = handledTime;
	}

	/**
	 * <p>
	 * Description:handledMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getHandledMan() {
		return handledMan;
	}

	/**
	 * <p>
	 * Description:handledMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setHandledMan(String handledMan) {
		this.handledMan = handledMan;
	}

	/**
	 * <p>
	 * Description:docConfirmedTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getDocConfirmedTime() {
		return docConfirmedTime;
	}

	/**
	 * <p>
	 * Description:docConfirmedTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDocConfirmedTime(Date docConfirmedTime) {
		this.docConfirmedTime = docConfirmedTime;
	}

	/**
	 * <p>
	 * Description:docConfirmedMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDocConfirmedMan() {
		return docConfirmedMan;
	}

	/**
	 * <p>
	 * Description:docConfirmedMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDocConfirmedMan(String docConfirmedMan) {
		this.docConfirmedMan = docConfirmedMan;
	}

	/**
	 * <p>
	 * Description:amountConfirmedTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getAmountConfirmedTime() {
		return amountConfirmedTime;
	}

	/**
	 * <p>
	 * Description:amountConfirmedTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAmountConfirmedTime(Date amountConfirmedTime) {
		this.amountConfirmedTime = amountConfirmedTime;
	}

	/**
	 * <p>
	 * Description:amountConfirmedMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getAmountConfirmedMan() {
		return amountConfirmedMan;
	}

	/**
	 * <p>
	 * Description:amountConfirmedMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAmountConfirmedMan(String amountConfirmedMan) {
		this.amountConfirmedMan = amountConfirmedMan;
	}

	/**
	 * <p>
	 * Description:normalApproveSubmitTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getNormalApproveSubmitTime() {
		return normalApproveSubmitTime;
	}

	/**
	 * <p>
	 * Description:normalApproveSubmitTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setNormalApproveSubmitTime(Date normalApproveSubmitTime) {
		this.normalApproveSubmitTime = normalApproveSubmitTime;
	}

	/**
	 * <p>
	 * Description:normalApproveSubmitMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getNormalApproveSubmitMan() {
		return normalApproveSubmitMan;
	}

	/**
	 * <p>
	 * Description:normalApproveSubmitMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setNormalApproveSubmitMan(String normalApproveSubmitMan) {
		this.normalApproveSubmitMan = normalApproveSubmitMan;
	}

	/**
	 * <p>
	 * Description:lastApprovedTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getLastApprovedTime() {
		return lastApprovedTime;
	}

	/**
	 * <p>
	 * Description:lastApprovedTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setLastApprovedTime(Date lastApprovedTime) {
		this.lastApprovedTime = lastApprovedTime;
	}

	/**
	 * <p>
	 * Description:lastApprovedMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getLastApprovedMan() {
		return lastApprovedMan;
	}

	/**
	 * <p>
	 * Description:lastApprovedMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setLastApprovedMan(String lastApprovedMan) {
		this.lastApprovedMan = lastApprovedMan;
	}

	/**
	 * <p>
	 * Description:paymentSubmitTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getPaymentSubmitTime() {
		return paymentSubmitTime;
	}

	/**
	 * <p>
	 * Description:paymentSubmitTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPaymentSubmitTime(Date paymentSubmitTime) {
		this.paymentSubmitTime = paymentSubmitTime;
	}

	/**
	 * <p>
	 * Description:paymentSubmitMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPaymentSubmitMan() {
		return paymentSubmitMan;
	}

	/**
	 * <p>
	 * Description:paymentSubmitMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPaymentSubmitMan(String paymentSubmitMan) {
		this.paymentSubmitMan = paymentSubmitMan;
	}

	/**
	 * <p>
	 * Description:amountPaidTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getAmountPaidTime() {
		return amountPaidTime;
	}

	/**
	 * <p>
	 * Description:amountPaidTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAmountPaidTime(Date amountPaidTime) {
		this.amountPaidTime = amountPaidTime;
	}

	/**
	 * <p>
	 * Description:amountPaidMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getAmountPaidMan() {
		return amountPaidMan;
	}

	/**
	 * <p>
	 * Description:amountPaidMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAmountPaidMan(String amountPaidMan) {
		this.amountPaidMan = amountPaidMan;
	}

	/**
	 * <p>
	 * Description:createUserName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * <p>
	 * Description:createUserName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * <p>
	 * Description:modifyUserName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getModifyUserName() {
		return modifyUserName;
	}

	/**
	 * <p>
	 * Description:modifyUserName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	/**
	 * <p>
	 * Description:reportDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getReportDeptName() {
		return reportDeptName;
	}

	/**
	 * <p>
	 * Description:reportDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	/**
	 * <p>
	 * Description:reportManName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getReportManName() {
		return reportManName;
	}

	/**
	 * <p>
	 * Description:reportManName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportManName(String reportManName) {
		this.reportManName = reportManName;
	}

	/**
	 * <p>
	 * Description:rejectReason<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRejectReason() {
		return rejectReason;
	}

	/**
	 * <p>
	 * Description:rejectReason<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	/**
	 * <p>
	 * Description:duration<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * <p>
	 * Description:duration<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getCashierDept() {
		return cashierDept;
	}

	public void setCashierDept(String cashierDept) {
		this.cashierDept = cashierDept;
	}

	public String getConfirmAmountDept() {
		return confirmAmountDept;
	}

	public void setConfirmAmountDept(String confirmAmountDept) {
		this.confirmAmountDept = confirmAmountDept;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}



}
