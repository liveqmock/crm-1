package com.deppon.crm.module.custview.shared.domain;

import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;

/**
 * @description 客户360度信息对象
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */

public class MemberIntegratedInfoView {
	// 会员积分
	private MemberIntegral memberIntegral;
	// 联系人积分列表
	private List<ContactIntegral> contactIntegralList;
	// 历史理赔统计数据对象
	private List<RecStatistics> recStatisticsList;
	// 历史投诉统计数据对象（旧）
	private List<ComplaintStatistics> complaintStatisticsList;
	// 历史投诉统计数据对象（新）
	private List complaintStatistics;
	// 营运分析数据
	private List<TwelveMonth> twelveMonthList;
	//营运分析数据
	private List<TwelveMonth> twelveMonthListExp;
	// 零担营运金额
	private List<OperationAnalysis> operationAnalysisList;
	// 快递营运数据
	private List<OperationAnalysis> operationAnalysisExpList;
	// 交易记录
	private LatelyTrade latelyTrade;
	// 最近一次投诉信息
	private Complaint complaint;
	// 理赔信息
	private RecompenseApplication recompense;
	// 合同信息
	private ContractDetailView contract;
	// 回访记录
	private ReturnVisit returnVisit;
	/**
	 * @return the memberIntegral
	 * @author 潘光均
	 * @version v0.1
	 */
	public MemberIntegral getMemberIntegral() {
		return memberIntegral;
	}
	/**
	 * @param memberIntegral the memberIntegral to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setMemberIntegral(MemberIntegral memberIntegral) {
		this.memberIntegral = memberIntegral;
	}
	/**
	 * @return the contactIntegralList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<ContactIntegral> getContactIntegralList() {
		return contactIntegralList;
	}
	/**
	 * @param contactIntegralList the contactIntegralList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setContactIntegralList(List<ContactIntegral> contactIntegralList) {
		this.contactIntegralList = contactIntegralList;
	}
	/**
	 * @return the recStatisticsList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<RecStatistics> getRecStatisticsList() {
		return recStatisticsList;
	}
	/**
	 * @param recStatisticsList the recStatisticsList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setRecStatisticsList(List<RecStatistics> recStatisticsList) {
		this.recStatisticsList = recStatisticsList;
	}
	/**
	 * @return the complaintStatisticsList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<ComplaintStatistics> getComplaintStatisticsList() {
		return complaintStatisticsList;
	}
	/**
	 * @param complaintStatisticsList the complaintStatisticsList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setComplaintStatisticsList(
			List<ComplaintStatistics> complaintStatisticsList) {
		this.complaintStatisticsList = complaintStatisticsList;
	}
	/**
	 * @return the complaintStatistics
	 * @author 潘光均
	 * @version v0.1
	 */
	public List getComplaintStatistics() {
		return complaintStatistics;
	}
	/**
	 * @param complaintStatistics the complaintStatistics to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setComplaintStatistics(List complaintStatistics) {
		this.complaintStatistics = complaintStatistics;
	}
	/**
	 * @return the twelveMonthList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<TwelveMonth> getTwelveMonthList() {
		return twelveMonthList;
	}
	/**
	 * @param twelveMonthList the twelveMonthList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setTwelveMonthList(List<TwelveMonth> twelveMonthList) {
		this.twelveMonthList = twelveMonthList;
	}
	/**
	 * @return the operationAnalysisList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<OperationAnalysis> getOperationAnalysisList() {
		return operationAnalysisList;
	}
	/**
	 * @param operationAnalysisList the operationAnalysisList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setOperationAnalysisList(
			List<OperationAnalysis> operationAnalysisList) {
		this.operationAnalysisList = operationAnalysisList;
	}
	/**
	 * @return the latelyTrade
	 * @author 潘光均
	 * @version v0.1
	 */
	public LatelyTrade getLatelyTrade() {
		return latelyTrade;
	}
	/**
	 * @param latelyTrade the latelyTrade to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setLatelyTrade(LatelyTrade latelyTrade) {
		this.latelyTrade = latelyTrade;
	}
	/**
	 * @return the complaint
	 * @author 潘光均
	 * @version v0.1
	 */
	public Complaint getComplaint() {
		return complaint;
	}
	/**
	 * @param complaint the complaint to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	/**
	 * @return the recompense
	 * @author 潘光均
	 * @version v0.1
	 */
	public RecompenseApplication getRecompense() {
		return recompense;
	}
	/**
	 * @param recompense the recompense to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setRecompense(RecompenseApplication recompense) {
		this.recompense = recompense;
	}
	/**
	 * @return the contract
	 * @author 潘光均
	 * @version v0.1
	 */
	public ContractDetailView getContract() {
		return contract;
	}
	/**
	 * @param contract the contract to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setContract(ContractDetailView contract) {
		this.contract = contract;
	}
	/**
	 * @return the returnVisit
	 * @author 潘光均
	 * @version v0.1
	 */
	public ReturnVisit getReturnVisit() {
		return returnVisit;
	}
	/**
	 * @param returnVisit the returnVisit to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setReturnVisit(ReturnVisit returnVisit) {
		this.returnVisit = returnVisit;
	}
	/**
	 *@return  operationAnalysisExpList;
	 */
	public List<OperationAnalysis> getOperationAnalysisExpList() {
		return operationAnalysisExpList;
	}
	/**
	 * @param operationAnalysisExpList : set the property operationAnalysisExpList.
	 */
	public void setOperationAnalysisExpList(
			List<OperationAnalysis> operationAnalysisExpList) {
		this.operationAnalysisExpList = operationAnalysisExpList;
	}
	/**
	*@return  twelveMonthListExp;
	*/
	public List<TwelveMonth> getTwelveMonthListExp() {
		return twelveMonthListExp;
	}
	/**
	 * @param twelveMonthListExp : set the property twelveMonthListExp.
	 */
	public void setTwelveMonthListExp(List<TwelveMonth> twelveMonthListExp) {
		this.twelveMonthListExp = twelveMonthListExp;
	}
}
