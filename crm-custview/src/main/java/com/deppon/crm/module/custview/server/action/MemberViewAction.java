package com.deppon.crm.module.custview.server.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.custview.server.manager.IMember360ViewManager;
import com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager;
import com.deppon.crm.module.custview.shared.domain.AccountView;
import com.deppon.crm.module.custview.shared.domain.ComplaintRecompenseView;
import com.deppon.crm.module.custview.shared.domain.ContactView;
import com.deppon.crm.module.custview.shared.domain.ContractView;
import com.deppon.crm.module.custview.shared.domain.CrossMapping;
import com.deppon.crm.module.custview.shared.domain.IntegratedCustDevView;
import com.deppon.crm.module.custview.shared.domain.MarketingActivity;
import com.deppon.crm.module.custview.shared.domain.MemberIntegratedInfoView;
import com.deppon.crm.module.custview.shared.domain.MemberPointsView;
import com.deppon.crm.module.custview.shared.domain.MemberView;
import com.deppon.crm.module.custview.shared.domain.OperationAnalysis;
import com.deppon.crm.module.custview.shared.domain.SearchFastCondition;
import com.deppon.crm.module.custview.shared.domain.TwelveMonth;
import com.deppon.crm.module.custview.shared.domain.WaybillInfo;
import com.deppon.crm.module.marketing.shared.WarnLostInfoFor360;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class MemberViewAction extends AbstractAction {
	/* 
	 * 通过
	 * 综合信息
	 */ 
	private MemberIntegratedInfoView memberIntegratedInfoView;
	/*
	 * 基本信息
	 */
	private MemberView memberView;
	/*
	 * 联系人信息
	 */
	private ContactView contactView;
	/*
	 * 联系人LIST
	 */
	private List<Contact> contactList;
	/*
	 * 合同信息
	 */
	private ContractView contractView;
	/*
	 * 合同基本信息LIST
	 */
	private List<ContractDetailView> contractList;
	/*
	 * 维护信息
	 */
	private IntegratedCustDevView integratedCustDevView;
	/*
	 * 客户积分
	 */
	private MemberPointsView memberPointsView;
	/*
	 * 投诉理赔
	 */
	private ComplaintRecompenseView complaintRecompenseView;
	/*
	 * 12个月的数据
	 */
	private List<TwelveMonth> twelveMonthList;
	/* 
	 * 通过set方法将前台传来的值设置到ACTION中)
	 * 客户编号
	 */ 
	private SearchFastCondition searchFastCondition = new SearchFastCondition();
	/*
	 * 财务信息
	 */
	private AccountView accountView;
	/*
	 * 财务信息LIST
	 */
	private List<Account> accountList;
	/*
	 * 订单list
	 */
	private List<Order> orderList;
	/*
	 * comboVal
	 */
	private String comboVal;
	/*
	 * 客户编号
	 */
	private String custId;
	/*
	 * 合同ID
	 */
	private String contractId;
	/*
	 * 联系人ID
	 */
	private String contactId;
	/*
	 * manager对象
	 */
	private Member360ViewManager member360ViewManager;
	
	private IMember360ViewManager imember360ViewManager;
	/*
	 * 查询订单时间FROM
	 */
	private Date searchOrderDateFrom;
	/*
	 * 查询订单时间TO
	 */
	private Date searchOrderDateTo;
	/*
	 * 财务信息ID
	 */
	private String accountId;
	/*
	 * 营运分析集合
	 */
	private List<OperationAnalysis> operationAnalysisList;
	/*
	 * 客户编号
	 */
	private String custNum;
	
	//会员操作记录
	private List<MemberOperationLog> mOperationLogList;
	
	//会员修改的详细信息
	private List<ApproveData> approveDataList; 
	//工作流号
	private String logId;
	//用户信用
	private CustCredit custCredit;
	//流失预警
	private List<WarnLostInfoFor360> warnLostCustList;
	//运单信息
	private List<WaybillInfo> waybillInfoList;
	//业务类别
	private String activityCategory;
	//营销活动
	private List<MarketingActivity>mkActivityList;
	//商机信息
	private List<BusinessOpportunity> businessOpportunityList;
	//CrossMapping
	private List<CrossMapping>crossMappingList;
	//erpId
	private String erpId;
	//运单查询  
	private int page;
	/**
	 * @param imember360ViewManager : set the property imember360ViewManager.
	 */
//	public void setImember360ViewManager(IMember360ViewManager imember360ViewManager) {
//		this.imember360ViewManager = imember360ViewManager;
//	}
	/**
	 *@return  mOperationLogList;
	 */
	public List<MemberOperationLog> getMOperationLogList() {
		return mOperationLogList;
	}
	/**
	 * @param mOperationLogList : set the property mOperationLogList.
	 */
	public void setMOperationLogList(List<MemberOperationLog> mOperationLogList) {
		this.mOperationLogList = mOperationLogList;
	}
	/**
	 *@return  approveDataList;
	 */
	public List<ApproveData> getApproveDataList() {
		return approveDataList;
	}
	/**
	 * @param approveDataList : set the property approveDataList.
	 */
	public void setApproveDataList(List<ApproveData> approveDataList) {
		this.approveDataList = approveDataList;
	}
	/**
	 * @param logId : set the property logId.
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}
	/**
	 * @return memberIntegratedInfoView : return the property memberIntegratedInfoView.
	 */
	public MemberIntegratedInfoView getMemberIntegratedInfoView() {
		return memberIntegratedInfoView;
	}
	/**
	 * @return memberView : return the property memberView.
	 */
	public MemberView getMemberView() {
		return memberView;
	}
	/**
	 * @return contactView : return the property contactView.
	 */
	public ContactView getContactView() {
		return contactView;
	}
	/**
	 * @return contactList : return the property contactList.
	 */
	public List<Contact> getContactList() {
		return contactList;
	}
	/**
	 * @return contractView : return the property contractView.
	 */
	public ContractView getContractView() {
		return contractView;
	}
	/**
	 * @return contractList : return the property contractList.
	 */
	public List<ContractDetailView> getContractList() {
		return contractList;
	}
	/**
	 * @return integratedCustDevView : return the property integratedCustDevView.
	 */
	public IntegratedCustDevView getIntegratedCustDevView() {
		return integratedCustDevView;
	}
	/**
	 * @return memberPointsView : return the property memberPointsView.
	 */
	public MemberPointsView getMemberPointsView() {
		return memberPointsView;
	}
	/**
	 * @return complaintRecompenseView : return the property complaintRecompenseView.
	 */
	public ComplaintRecompenseView getComplaintRecompenseView() {
		return complaintRecompenseView;
	}
	/**
	 * @return twelveMonthList : return the property twelveMonthList.
	 */
	public List<TwelveMonth> getTwelveMonthList() {
		return twelveMonthList;
	}
	/**
	 * @return searchFastCondition : return the property searchFastCondition.
	 */
	public SearchFastCondition getSearchFastCondition() {
		return searchFastCondition;
	}
	/**
	 * @return accountView : return the property accountView.
	 */
	public AccountView getAccountView() {
		return accountView;
	}
	/**
	 * @return accountList : return the property accountList.
	 */
	public List<Account> getAccountList() {
		return accountList;
	}
	/**
	 * @return orderList : return the property orderList.
	 */
	public List<Order> getOrderList() {
		return orderList;
	}
	/**
	 * @param searchFastCondition : set the property searchFastCondition.
	 */
	public void setSearchFastCondition(SearchFastCondition searchFastCondition) {
		this.searchFastCondition = searchFastCondition;
	}
	/**
	 * @param comboVal : set the property comboVal.
	 */
	public void setComboVal(String comboVal) {
		this.comboVal = comboVal;
	}
	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @param contractId : set the property contractId.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @param contactId : set the property contactId.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @param member360ViewManager : set the property member360ViewManager.
	 */
	public void setMember360ViewManager(Member360ViewManager member360ViewManager) {
		this.member360ViewManager = member360ViewManager;
	}
	/**
	 * @param searchOrderDateFrom : set the property searchOrderDateFrom.
	 */
	public void setSearchOrderDateFrom(Date searchOrderDateFrom) {
		this.searchOrderDateFrom = searchOrderDateFrom;
	}
	/**
	 * @param searchOrderDateTo : set the property searchOrderDateTo.
	 */
	public void setSearchOrderDateTo(Date searchOrderDateTo) {
		this.searchOrderDateTo = searchOrderDateTo;
	}
	/**
	 * @param accountId : set the property accountId.
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @param limit : set the property limit.
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 * @param start : set the property start.
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @param custNum : set the property custNum.
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * @return operationAnalysisList : return the property operationAnalysisList.
	 */
	public List<OperationAnalysis> getOperationAnalysisList() {
		return operationAnalysisList;
	}
	
	/**
	 *@return  custCredit;
	 */
	public CustCredit getCustCredit() {
		return custCredit;
	}
	/**
	 * @param custCredit : set the property custCredit.
	 */
	public void setCustCredit(CustCredit custCredit) {
		this.custCredit = custCredit;
	}

	/**
	 * @return warnLostCustList : return the property warnLostCustList.  
	 */
	public List<WarnLostInfoFor360> getWarnLostCustList() {
		return warnLostCustList;
	}
	/**
	 * @param warnLostCustList : set the property warnLostCustList. 
	 */
	public void setWarnLostCustList(List<WarnLostInfoFor360> warnLostCustList) {
		this.warnLostCustList = warnLostCustList;
	}
	/**
	 *@return  waybillInfoList;
	 */
	public List<WaybillInfo> getWaybillInfoList() {
		return waybillInfoList;
	}
	/**
	 * @param waybillInfoList : set the property waybillInfoList.
	 */
	public void setWaybillInfoList(List<WaybillInfo> waybillInfoList) {
		this.waybillInfoList = waybillInfoList;
	}
	/**
	 * @param activityCategory : set the property activityCategory.
	 */
	public void setActivityCategory(String activityCategory) {
		this.activityCategory = activityCategory;
	}
	/**
	 *@return  mkActivityList;
	 */
	public List<MarketingActivity> getMkActivityList() {
		return mkActivityList;
	}
	/**
	 * @param mkActivityList : set the property mkActivityList.
	 */
	public void setMkActivityList(List<MarketingActivity> mkActivityList) {
		this.mkActivityList = mkActivityList;
	}
	
	/**
	 *@return  businessOpportunityList;
	 */
	public List<BusinessOpportunity> getBusinessOpportunityList() {
		return businessOpportunityList;
	}
	/**
	 * @param businessOpportunityList : set the property businessOpportunityList.
	 */
	public void setBusinessOpportunityList(
			List<BusinessOpportunity> businessOpportunityList) {
		this.businessOpportunityList = businessOpportunityList;
	}
	/**
	 *@return  crossMappingList;
	 */
	public List<CrossMapping> getCrossMappingList() {
		return crossMappingList;
	}
	/**
	 * @param crossMappingList : set the property crossMappingList.
	 */
	public void setCrossMappingList(List<CrossMapping> crossMappingList) {
		this.crossMappingList = crossMappingList;
	}
	
	/**
	 * @param erpId : set the property erpId.
	 */
	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
	
	/**
	 * @param page : set the property page.
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 
	 * @description 高级查询 ，即先通过弹窗查询客户信息获取一个客户编码，再通过客户编码获取客户综合信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchMemberIntegratedInfoViewByCustNum() {
		/*
		 * 客户360度信息对象
		 */
		memberIntegratedInfoView = member360ViewManager
				.getMemberIntegratedInfo(custNum, comboVal);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:非高级查询
	 * 	1.如果没有填写客户编码，根据其他信息查询出客户编码再根据客户编码获取客户综合信息.
	 *  2.如果填写了客户编码，直接根据客户编码查询出客户综合信息
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-26
	 * @return
	 * String
	 */
	public String findMemberInfoFast() {
		/*
		 * 客户360度信息对象
		 */
		memberIntegratedInfoView = member360ViewManager
				.searchMemberInfoFast(searchFastCondition);	
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:非高级查询
	 * 	1.如果没有填写客户编码，根据其他信息查询出客户编码再根据客户编码获取客户综合信息.
	 *  2.如果填写了客户编码，直接根据客户编码查询出客户综合信息
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-26
	 * @return
	 * String
	 */
	public String searchMemberntegrateInfo() {
		/*
		 * 客户360度信息对象
		 */
		memberIntegratedInfoView = member360ViewManager
				.searchMemberntegrateInfo(searchFastCondition);	
		return SUCCESS;
	}
	
	/**
	 * 
	 * @description 简版360默认加载基本信息
	 * @author pgj
	 * @version 0.1 2013-08-26
	 * @date 2013-08-26
	 * @return String
	 * @update 2012-4-25
	 */
	public String simpleMemberViewIndex() {
		/*
		 * 根据客户ID获取客户基本信息.
		 */
		memberView = member360ViewManager.getMemberBasicInfoByCustId(custId);
		return SUCCESS;
	}
	/**
	 * 
	 * @description 通过客户编码获取客户基本信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchMemberViewByCustNum() {
		/*
		 * 根据客户ID获取客户基本信息.
		 */
		memberView = member360ViewManager.getMemberBasicInfoByCustId(custId);
		mOperationLogList = member360ViewManager.searchMemberOperationLogList(custId);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午5:16:07
	 * @return
	 * String
	 * @update 2014-4-1下午5:16:07
	 */
	public String searchMemberOperationLogList(){
		mOperationLogList = imember360ViewManager.searchMemberOperationLogList(custId);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:根据工作流号查询客户变更详细信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午5:17:01
	 * @return
	 * String
	 * @update 2014-4-1下午5:17:01
	 */
	public String searchApproveDataList(){
		approveDataList = member360ViewManager.searchApproveDataList(logId);
		return SUCCESS;
	}
	/**
	 * 
	 * @description 通过客户编码获取联系人
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchContactViewByCustNum() {
		/*
		 * 根据客户ID获取该客户所有联系人信息列表.
		 */
		contactList = member360ViewManager.getAllContactInfoByCustId(custId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过联系人ID获取联系人
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchContactByContactId() {
		/*
		 * 根据联系人ID获得该联系人详细信息（包含接货地址信息列表）.
		 */
		contactView = member360ViewManager.getContactInfoById(contactId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过客户编码获取合同信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchContractViewByCustNum() {
		/*
		 * 根据客户ID获取该客户的所有合同信息列表.
		 */
		contractList = member360ViewManager.getContractListByCustId(custId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过合同ID合同信息
	 * @author 张斌
	 * @version 0.1 2012-4-27
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-27
	 */
	public String searchContractByContractId() {
		/*
		 * 根据合同ID获得合同详细信息（包含附件信息）.
		 */
		contractView = member360ViewManager.getContractInfoById(contractId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过客户编码获取维护信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchIntegratedCustDevViewByCustNum() {
		/*
		 * 根据客户ID获得客户开发/日程综合信息集合集.
		 */
		integratedCustDevView = member360ViewManager
				.getIntegratedCustDevByCustId(custId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过客户编码获取客户积分信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchMemberPointsViewByCustNum() {
		/*
		 * 根据客户ID获取该客户所有联系人的积分情况列表;
		 */
		memberPointsView = member360ViewManager.getMemberPointByCustId(custId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过客户编码获取投诉理赔信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchComplaintRecompenseViewByCustNum() {
		/*
		 * 投诉理赔集合集
		 */
		complaintRecompenseView = member360ViewManager.getComplaintRecompense(
				custId, custNum);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过客户编码获取财务信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchAccountViewByCustNum() {
		/*
		 * 根据客户ID获取该客户所有帐户信息列表.
		 */
		accountList = member360ViewManager.getAccountListByCustId(custId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过财务信息ID获取财务信息
	 * @author 张斌
	 * @version 0.1 2012-5-2
	 * @date 2012-5-2
	 * @return none
	 * @update 2012-5-2
	 */
	public String searchAccountViewById() {
		/*
		 * 根据帐户ID获取该帐户详细信息.
		 */
		accountView = member360ViewManager.getAccountInfoById(accountId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过客户编码获取订单信息
	 * @author 张斌
	 * @version 0.1 2012-4-25
	 * @date 2012-4-25
	 * @return none
	 * @update 2012-4-25
	 */
	public String searchOrderListByCustNum() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(searchOrderDateTo);
		/*
		 * 把日期往后增加一天.整数往后推,负数往前移动
		 */
		calendar.add(calendar.DATE, 1);
		/*
		 * 这个时间就是日期往后推一天的结果
		 */
		searchOrderDateTo = calendar.getTime();
		/*
		 * 根据客户ID、起始时间获取订单信息列表(只获取订单有绑定联系人的记录列表).
		 */
		orderList = member360ViewManager.getOrderListByCustId(custId,
				searchOrderDateFrom, searchOrderDateTo, start, limit);
		/*
		 * 根据客户ID、起始时间获取订单信息列表总数(只获取订单有绑定联系人的记录列表).
		 */
		totalCount = member360ViewManager.getOrderCountByCustId(custId,
				searchOrderDateFrom, searchOrderDateTo);
		/*
		 * CRM-3127 修改判断的前后顺序 author By 许华龙
		 */
		if ( totalCount == null ||totalCount == 0) {
			totalCount = (long) 1;
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @description 获取运营决策数据
	 * @author 张斌
	 * @version 0.1 2012-4-28
	 * @date 2012-4-28
	 * @return none
	 * @update 2012-4-28
	 */
	public String searchOperatingDecision() {
		/*
		 * 统计分析结果结构转换
		 */
		twelveMonthList = member360ViewManager
				.getOperationAnalysisListByCustId(activityCategory,custId, searchOrderDateFrom,
						searchOrderDateTo);
		/*
		 * 统计明細
		 */
		operationAnalysisList = member360ViewManager
				.getOperationAnalysisDetailListByCustId(activityCategory,custNum,custId,
						searchOrderDateFrom, searchOrderDateTo);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:获取客户信用信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-4下午4:10:57
	 * @return
	 * String
	 * @update 2014-4-4下午4:10:57
	 */
	public String searchCustCredit(){
		custCredit = member360ViewManager.getCustCreditByCustId(custId);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:查询流失预警信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-4下午7:10:46
	 * @return
	 * String
	 * @update 2014-4-4下午7:10:46
	 */
	public String searchWarnLostCustList(){
		warnLostCustList = member360ViewManager.searchWarnLostCust(custNum);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:查询客户某时间段内运单信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-4下午7:10:46
	 * @return
	 * String
	 * @update 2014-4-4下午7:10:46
	 */
	@SuppressWarnings("unchecked")
	public String searchWaybillListByCustNum(){
		@SuppressWarnings("rawtypes")
		Map map = member360ViewManager.searchWaybillInfoList(custNum,
				searchOrderDateFrom, searchOrderDateTo, page, limit);
		waybillInfoList = (List<WaybillInfo>) map.get("waybillInfoList");
		totalCount =(Long) map.get("totalCount");
		mkActivityList = (List<MarketingActivity>) map.get("mkActivityList");
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询最近三次商机信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-21下午3:13:10
	 * @return
	 * String
	 * @update 2014-4-21下午3:13:10
	 */
	public String searchBusinessOpportunityList(){
		businessOpportunityList = member360ViewManager.searchBusinessOpportunityList(custId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据联系人ID获得交叉映射信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-23下午2:53:15
	 * @return
	 * String
	 * @update 2014-4-23下午2:53:15
	 */
	public String searchCMappingByErpId() {
		/*
		 * 根据联系人ID获得交叉映射信息
		 */
		crossMappingList = member360ViewManager.getCrossMappingByErpId(custId);
		return SUCCESS;
	}
}
