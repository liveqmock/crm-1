package com.deppon.crm.module.duty.server.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.duty.server.manager.IDutyManager;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.shared.domain.CommitDutyResultVO;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.duty.shared.domain.InformUser;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * <p>
 * Description:工单责任 action 增删改查<br />
 * </p>
 * @title CompltDutyAction.java
 * @package com.deppon.crm.module.complaint.server.action 
 * @author 侯斌飞
 * @version 0.1 2013-2-23
 */
public class DutyAction extends AbstractAction {
	//责任 Manager
	private IDutyManager dutyManager;
	//工单manager
	private IComplaintManager complaintManager;
	// 工单责任Id
	private String dutyId;
	// 工单责任详情
	private Duty dutyDetail;
	// 处理经过
	private List<DutyDealProcess> dutyDealProcess;
	// 工单划分结果
	private List<DutyResult> dutyResult;
	// 工单划分-处理结果新增的
	private List<DutyResult> addResultList;
	// 工单划分-处理结果修改的
	private List<DutyResult> updateResultList;
	// 工单划分-处理结果删除的
	private List<DutyResult> deleteResultList;
	// 工单划分-处理结果
	private List<DutyResult> dutyResultList;
	//查询条件
	private SearchDutyCondition searchDutyCondition;
	//附件集合
	private List<FeedAttach> feedAttachList;
	
	//责任部门
	private DutyResult result;
	
	//通知对象-新增的
	private List<InformUser> addInformUserList;
	//通知对象-修改的
	private List<InformUser> updateInformUserList;
	//通知对象-删除的
	private List<InformUser> deleteInformUserList;
	
	//责任通知对象集合
	private List<InformUser> dutyInformUserList;
	// 通知对象
	private String userNames;
	//责任反馈记录列表
	private List<DutyFeedback> dutyFeedbackList;
	//工单责任反馈记录
	private DutyFeedback dutyFeedback;
	//反馈时限
	private Date feedbackTime;
	
	//处理经过
	private DutyDealProcess dealProcess;
	//工单反馈信息ID
	private String feedbackId;
	//部门名称
	private String deptName;
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the feedbackId
	 */
	public String getFeedbackId() {
		return feedbackId;
	}
	/**
	 * @param feedbackId the feedbackId to set
	 */
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	/**
	 * @return the dealProcess
	 */
	public DutyDealProcess getDealProcess() {
		return dealProcess;
	}
	/**
	 * @param dealProcess the dealProcess to set
	 */
	public void setDealProcess(DutyDealProcess dealProcess) {
		this.dealProcess = dealProcess;
	}

	// 工单id
	private String complaintId;
	//操作记录集合    -  调查建议集合
	private List<WorkOrder> workOrderList;
	//通知对象集合
	private String bulletinStr=null;
	//返回查询list结果
	private List<Result> searchResultList=null;
	// 处理记录
	private  List<FeedBackReasion> searchProtList = null;
	// 工单信息
	private Complaint complaint = null;
	//工单特殊责任部门
	private DutyDept dutyDept;
	//工单特殊部门集合
	private List<DutyDept> dutyDeptList;
	
	

	/**
	 * @param dutyDeptList the dutyDeptList to set
	 */
	public void setDutyDeptList(List<DutyDept> dutyDeptList) {
		this.dutyDeptList = dutyDeptList;
	}
	/**
	 * @param dutyDept the dutyDept to set
	 */
	public void setDutyDept(DutyDept dutyDept) {
		this.dutyDept = dutyDept;
	}
	/**
	 * @return the complaint
	 */
	public Complaint getComplaint() {
		return complaint;
	}
	/**
	 * @param complaint the complaint to set
	 */
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	/**
	 * @return the searchProtList
	 */
	public List<FeedBackReasion> getSearchProtList() {
		return searchProtList;
	}
	/**
	 * @param searchProtList the searchProtList to set
	 */
	public void setSearchProtList(List<FeedBackReasion> searchProtList) {
		this.searchProtList = searchProtList;
	}
	/**
	 * @param complaintManager the complaintManager to set
	 */
	public void setComplaintManager(IComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}
	/**
	 * @return the searchResultList
	 */
	public List<Result> getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList the searchResultList to set
	 */
	public void setSearchResultList(List<Result> searchResultList) {
		this.searchResultList = searchResultList;
	}
	/**
	 * @return the bulletinStr
	 */
	public String getBulletinStr() {
		return bulletinStr;
	}
	/**
	 * @param bulletinStr the bulletinStr to set
	 */
	public void setBulletinStr(String bulletinStr) {
		this.bulletinStr = bulletinStr;
	}
	/**
	 * @return the workOrderList
	 */
	public List<WorkOrder> getWorkOrderList() {
		return workOrderList;
	}
	/**
	 * @param workOrderList the workOrderList to set
	 */
	public void setWorkOrderList(List<WorkOrder> workOrderList) {
		this.workOrderList = workOrderList;
	}
	/**
	 * @return the complaintId
	 */
	public String getComplaintId() {
		return complaintId;
	}
	
	/**
	 * @param complaintId the complaintId to set
	 */
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	public String getUserNames() {
		return userNames;
	}
	/**
	 * @param dutyFeedback the dutyFeedback to get
	 */
	public DutyFeedback getDutyFeedback() {
		return dutyFeedback;
	}


	/**
	 * @param dutyFeedback the dutyFeedback to set
	 */
	public void setDutyFeedback(DutyFeedback dutyFeedback) {
		this.dutyFeedback = dutyFeedback;
	}
	/**
	 * @return addInformUserList : return the property addInformUserList.
	 */
	public List<InformUser> getAddInformUserList() {
		return addInformUserList;
	}


	/**
	 * @param addInformUserList : set the property addInformUserList.
	 */
	public void setAddInformUserList(List<InformUser> addInformUserList) {
		this.addInformUserList = addInformUserList;
	}


	/**
	 * @return updateInformUserList : return the property updateInformUserList.
	 */
	public List<InformUser> getUpdateInformUserList() {
		return updateInformUserList;
	}


	/**
	 * @param updateInformUserList : set the property updateInformUserList.
	 */
	public void setUpdateInformUserList(List<InformUser> updateInformUserList) {
		this.updateInformUserList = updateInformUserList;
	}


	/**
	 * @return deleteInformUserList : return the property deleteInformUserList.
	 */
	public List<InformUser> getDeleteInformUserList() {
		return deleteInformUserList;
	}

	

	/**
	 * @param deleteInformUserList : set the property deleteInformUserList.
	 */
	public void setDeleteInformUserList(List<InformUser> deleteInformUserList) {
		this.deleteInformUserList = deleteInformUserList;
	}


	/**
	 * @return dutyResultList : return the property dutyResultList.
	 */
	public List<DutyResult> getDutyResultList() {
		return dutyResultList;
	}


	/**
	 * @param dutyResultList : set the property dutyResultList.
	 */
	public void setDutyResultList(List<DutyResult> dutyResultList) {
		this.dutyResultList = dutyResultList;
	}

	
	/**
	 * @return addResultList : return the property addResultList.
	 */
	public List<DutyResult> getAddResultList() {
		return addResultList;
	}


	/**
	 * @param addResultList : set the property addResultList.
	 */
	public void setAddResultList(List<DutyResult> addResultList) {
		this.addResultList = addResultList;
	}


	/**
	 * @return updateResultList : return the property updateResultList.
	 */
	public List<DutyResult> getUpdateResultList() {
		return updateResultList;
	}


	/**
	 * @return feedAttachList : return the property feedAttachList.
	 */
	public List<FeedAttach> getFeedAttachList() {
		return feedAttachList;
	}
	/**
	 * @param feedAttachList : set the property feedAttachList.
	 */
	public void setFeedAttachList(List<FeedAttach> feedAttachList) {
		this.feedAttachList = feedAttachList;
	}
	/**
	 * @param updateResultList : set the property updateResultList.
	 */
	public void setUpdateResultList(List<DutyResult> updateResultList) {
		this.updateResultList = updateResultList;
	}


	/**
	 * @return deleteResultList : return the property deleteResultList.
	 */
	public List<DutyResult> getDeleteResultList() {
		return deleteResultList;
	}


	/**
	 * @param deleteResultList : set the property deleteResultList.
	 */
	public void setDeleteResultList(List<DutyResult> deleteResultList) {
		this.deleteResultList = deleteResultList;
	}

	/**
	 * @return the dutyResult
	 */
	public List<DutyResult> getDutyResult() {
		return dutyResult;
	}


	/**
	 * @param dutyId the dutyId to set
	 */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}


	/**
	 * @return the dutyDealProcess
	 */
	public List<DutyDealProcess> getDutyDealProcess() {
		return dutyDealProcess;
	}

	
	/**
	 * @return searchDutyCondition : return the property searchDutyCondition.
	 */
	public SearchDutyCondition getSearchDutyCondition() {
		return searchDutyCondition;
	}


	/**
	 * @param searchDutyCondition : set the property searchDutyCondition.
	 */
	public void setSearchDutyCondition(SearchDutyCondition searchDutyCondition) {
		this.searchDutyCondition = searchDutyCondition;
	}


	/**
	 * @return the dutyDetail
	 */
	public Duty getDutyDetail() {
		return dutyDetail;
	}
	

	/**
	 * @param dutyDetail : set the property dutyDetail.
	 */
	public void setDutyDetail(Duty dutyDetail) {
		this.dutyDetail = dutyDetail;
	}

	
	/**
	 * @return feedbackTime : return the property feedbackTime.
	 */
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	/**
	 * @param feedbackTime : set the property feedbackTime.
	 */
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	/**
	 * @return the dutyFeedbackList
	 */
	public List<DutyFeedback> getDutyFeedbackList() {
		return dutyFeedbackList;
	}


	/**
	 * @return the dutyId
	 */
	public String getDutyId() {
		return dutyId;
	}

	/**
	 * @param dutyManager : set the property dutyManager.
	 */
	public void setDutyManager(IDutyManager dutyManager) {
		this.dutyManager = dutyManager;
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任 -查询-查看详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@SuppressWarnings("unchecked")
	public String searchDutyDetail(){
		Map<String,Object> map = dutyManager.getDutyDetail(dutyId);
		dutyDetail = (Duty) map.get("dutyDetail");
		dutyDealProcess = (List<DutyDealProcess>) map.get("dutyDealProcess");
		dutyResult = (List<DutyResult>) map.get("dutyResult");
		userNames = (String) map.get("userNames");
		dutyFeedbackList = (List<DutyFeedback>)map.get("dutyFeedbackList");
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据工单责任ID查询工单责任划分结果
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-4-27下午2:44:07
	 * @return
	 * String
	 * @update 2013-4-27下午2:44:07
	 */
	public String searchDutyResult(){
		dutyResult = dutyManager.searchDutyResult(dutyId);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任 -查询-查看详情<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-11
	 */
	public String searchDutyById(){
		dutyDetail = dutyManager.searchDutyById(searchDutyCondition.getId());
		return SUCCESS;
	} 
	
	/**
	 * 
	 * <p>
	 * Description:责任划分 - 确认无责<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-15
	 * @return
	 * String
	 */
	public String confirmNotDuty(){
		if(this.dutyDetail==null){return SUCCESS;}
		dutyManager.getDutyById(this.dutyDetail.getId(), false, true, false);
		User currentUser = (User) (UserContext.getCurrentUser());
		dutyManager.sureNoResponsibility(this.dutyDetail,currentUser);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任划分 - 暂存<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-15
	 * @return
	 * String
	 */
	public String temporarySave(){
		if(this.dutyDetail==null){return SUCCESS;}
		dutyManager.getDutyById(this.dutyDetail.getId(), true, false, false);
		//处理经过
		DutyDealProcess dutyDealProcess = new DutyDealProcess();
		dutyDealProcess.setDutyId(this.dutyDetail.getId());
		dutyDealProcess.setTreatProcess(this.dutyDetail.getProcessPass());
		
		CommitDutyResultVO parms = new CommitDutyResultVO();
		parms.setDutyDealProcess(dutyDealProcess);
		
		//处理结果
		parms.setAddDutyResultRecords(this.addResultList);
		parms.setUpdateDutyResultRecords(this.updateResultList);
		parms.setDeleteDutyResultRecords(this.deleteResultList);
		//通知对象
		parms.setAddInformUserRecords(this.addInformUserList);
		parms.setUpdateInformUserRecords(this.updateInformUserList);
		parms.setDeleteInformUserRecords(this.deleteInformUserList);
		
		User currentUser = (User) (UserContext.getCurrentUser());
		dutyManager.temporaryDutyResults(parms, currentUser);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任划分 - 提交<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-15
	 * @return
	 * String
	 */
	public String submitDutyDivide(){
		if(this.dutyDetail==null){return SUCCESS;}
		dutyManager.getDutyById(this.dutyDetail.getId(), false, false, true);
		//处理经过
		DutyDealProcess dutyDealProcess = new DutyDealProcess();
		dutyDealProcess.setDutyId(this.dutyDetail.getId());
		dutyDealProcess.setTreatProcess(this.dutyDetail.getProcessPass());
		
		CommitDutyResultVO parms = new CommitDutyResultVO();
		parms.setDutyDealProcess(dutyDealProcess);
		
		parms.setDutyDetail(this.dutyDetail);
		
		//处理结果
		parms.setAddDutyResultRecords(this.addResultList);
		parms.setUpdateDutyResultRecords(this.updateResultList);
		parms.setDeleteDutyResultRecords(this.deleteResultList);
		//通知对象
		parms.setAddInformUserRecords(this.addInformUserList);
		parms.setUpdateInformUserRecords(this.updateInformUserList);
		parms.setDeleteInformUserRecords(this.deleteInformUserList);
		
		User currentUser = (User) (UserContext.getCurrentUser());
		dutyManager.commitDutyResults(parms, currentUser);
		return SUCCESS;
	}
	
	//审批类型：统计员同意STATAGREE，统计员退回STATDISAGREE，
	//质检员同意CALLERAGREE，质检员退回CALLERDISAGREE
	private String approvalType;
	/**
	 * @param approvalType the approvalType to set
	 */
	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}
	/**
	 * <p>
	 * Description:工单责任审批操作<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@JSON
	public String dutyApprovalOperation(){
		User user = (User) UserContext.getCurrentUser();
		//审批同意 + 重新划分责任
		if(StringUtils.isNotEmpty(approvalType) && DutyConstants.INSPECTOR_AGREE.equals(approvalType.trim())){
			CommitDutyResultVO parms = new CommitDutyResultVO();
			parms.setDutyResultList(dutyResultList);//划分结果
			parms.setDutyInformUserList(dutyInformUserList);//通知对象
			parms.setDutyFeedback(dutyFeedback);// 反馈记录
			parms.setDutyDealProcess(dealProcess);//处理经过
			dutyManager.commitApprovalAgree(parms,user);
		}
		//审批退回
		else if(StringUtils.isNotEmpty(approvalType) && DutyConstants.INSPECTOR_DISAGREE.equals(approvalType.trim())){
			dutyManager.callerDisagreeOfApproval(dutyFeedback, user);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任反馈 - 责任认领<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-27
	 * @return
	 * String
	 */
	public String dutyClaim(){
		User user = (User) UserContext.getCurrentUser();
		dutyManager.dutyClaim(searchDutyCondition.getId(),searchDutyCondition.getDutyResultId(),user);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任反馈 - 责任反馈<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-27
	 * @return
	 * String
	 */
	public String dutyFeedbackProcess(){
		User user = (User) UserContext.getCurrentUser();
		DutyFeedback dutyFeedback = new DutyFeedback();
		//责任反馈内容
		dutyFeedback.setDescribe(searchDutyCondition.getDutyFeedback());
		dutyFeedback.setDetailId(searchDutyCondition.getDutyResultId());//划分结果ID
		dutyManager.dutyFeedBack(searchDutyCondition.getId(),dutyFeedback,user,feedAttachList);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任划分 - 查询最新的处理经过<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-28
	 * @return
	 * String
	 */
	public String searchNewProcessPass(){
		this.dealProcess = dutyManager.selectMaxDutyDealProcess(searchDutyCondition.getId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:工单详情 -查询-查看详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-4-1
	 */
	@SuppressWarnings("unchecked")
	public String searchCompalaintDetail(){

		// 工单详情
		dutyDetail = dutyManager.getDutyDomainDetail(dutyId);
		// 调查建议
		workOrderList = complaintManager.getInvestigateSuggestionByCompId(new BigDecimal(complaintId));
		// 处理记录
		searchProtList = this.complaintManager.searchReturnedRecordListByCompId(new BigDecimal(complaintId));
		// 通知对象
		List<Bulletin> bulletinList = this.complaintManager.searchBulletinByCompId(new BigDecimal(complaintId));
		StringBuffer bulletinStr =  new StringBuffer();
		for(Bulletin str : bulletinList){  
			String bulletinName = str.getBulletinname();
			bulletinStr.append(bulletinName);
			bulletinStr.append(",");
		}
		this.bulletinStr = bulletinStr.toString();
		// 处理结果
		searchResultList = this.complaintManager.searchProcessResultListByCompId(new BigDecimal(complaintId));
		// 通过工单id查询工单
		complaint = complaintManager.getComplaintById(complaintId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:得到反馈时限的默认值<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-4-1
	 * @return
	 * String
	 */
	public String serarchFeedbackTime(){
		String value = searchDutyCondition.getValue();
		if(value == null || "".equals(value.trim())){value="0";}
		feedbackTime = dutyManager.feedBackExtendedTime(new Integer(value.trim()));
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:责任审批-(400 or 事业部)<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 20143-1-11
	 * @return
	 * String
	 */
	public String feedbackApproval(){
		this.dutyResultList.get(0);
		this.dutyInformUserList.get(0);
		this.dutyDetail.getClass();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询工单责任<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-6-8
	 * @return
	 * String
	 */
	public String searchStatDeptName(){
		deptName = dutyManager.searchStatDeptName(feedbackId);
		return SUCCESS;
	}
	/**
	 * @param dutyInformUserList the dutyInformUserList to set
	 */
	public void setDutyInformUserList(List<InformUser> dutyInformUserList) {
		this.dutyInformUserList = dutyInformUserList;
	}
	/**
	 * <p>
	 *	Description: 保存工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @return
	 * String
	 */
	@JSON
	public String saveDutyDept(){
		User user = (User) UserContext.getCurrentUser();
		dutyManager.saveDutyDept(dutyDept, user);
		return SUCCESS;
	}
	/**
	 * <p>
	 *	Description:删除特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-11
	 * @return
	 * String
	 */
	@JSON
	public String deleteDutyDept(){
		dutyManager.deleteDutyDept(dutyDeptList);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 *	Description: 查询多个部门是否为同一个事业部
	 * </p> 
	 * @author hpf
	 * @date 2014-1-16
	 * @return
	 * List<DutyDept>
	 */
	@JSON
	public String isSameDepartmentByDeptIds(){
		User currentUser = (User) (UserContext.getCurrentUser());
		dutyManager.isSameDepartmentByDeptIds(result,currentUser);
		return SUCCESS;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(DutyResult result) {
		this.result = result;
	}
}

