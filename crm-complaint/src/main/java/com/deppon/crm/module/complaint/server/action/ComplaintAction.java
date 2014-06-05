package com.deppon.crm.module.complaint.server.action;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelView;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintTaskCondtion;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * <p>
 * 客户工单查询action<br />
 * </p>
 * 
 * @title ComplaintQueryAction.java
 * @package com.deppon.crm.module.complaint.server.action
 * @author hpf
 * @version 0.1 2012-4-11
 */

public class ComplaintAction extends AbstractAction {
	// IComplaintManager
	private IComplaintManager complaintManager = null;
	// 工单信息
	private Complaint complaint = null;
	// 客户工单详情 数据模型
	private Map<String, Object> otherComplint = null;
	// 工单Id
	private String fid;
	// 前台传入处理参数
	private Map<String, Object> parameterMap = new HashMap<String,Object>();
	// 部门工单管理查询条件
	private ComplaintTaskCondtion complaintTaskCondtion;
	//部门集合
	private List<Department> departmentList=null;
	//处理结果
	private List<Result> resultList;
	//通知对象集合
	private List<Bulletin> bulletinList=null;
	//退回原因
	private String returnReason;
	//投诉
	private String complaintList;
	//业务类型列表
	private List<BasicBusType> busTypes;
	//上报类型
	private String reportType;
	public void setComplaintList(String complaintList) {
		this.complaintList = complaintList;
	}
	
	//标志位--延时审批按钮是否需要
	private boolean postponeButtonFlag;
	
	//标志位--完成审批按钮是否需要
	private boolean processCompleteBttnFlag; 
	//选中一条基础资料，单击基础资料设置后，加载出此条业务范围下所有的业务类型列表
	private List<BasicBusScopeVO> businessTypeList;
	//业务项、业务范围名称和ID等封装的实体
	private BasicBusScopeVO businessType;
	//基础资料列表
	private List<BasicInfo> basicInfoList;
	
	//异常
	private String exceptionList;

	
	public void setExceptionList(String exceptionList) {
		this.exceptionList = exceptionList;
	}
	// 处理结果列表
	private List<ProcResult> procResultList;
	// 基础等级
	private BasciLevel basciLevel;
	// 处理结果
	private String procResult;
	//同意延时申请的result
	private List<Result> resultsToApprove;	
	//退回给提交人或者审批人的时候所填写的退回原因
	private String feedbackReason=null;	
	//延时申请审批查询得到的所有延时申请列表
	private List<Result> postponeResultList;	
	//result搜索条件
	private ResultSearchCondition resultSearchCondition;	
	//退回原因/反馈记录
	private FeedBackReasion feedBackReasion;
	//服务编码
	private String serviceId;
	//处理结果基础资料
	List<BasciLevelView> basciLevelList=null;
	// 工单列表基础等级
	List<BasciLevel> complaintListBasciLevel=null; 
	// 异常列表基础等级
	List<BasciLevel> exceptionListBasciLevel=null;
	/**
	 * 保存业务范围设置
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String saveBasciLevel(){
		// 点击业务范围设置保存
		//传进来的时候要给basciLevel 设置上报类型 方便我后台保存
		complaintManager.setUpParentBasciLevel(complaintListBasciLevel, exceptionListBasciLevel, (User) UserContext.getCurrentUser());
		// 返回成功
		return SUCCESS;
	}
	/**
	 * 保存处理结果
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String saveFoundation(){
		// 保存修改
		complaintManager.saveOrUpdateFoundationData(procResultList, basciLevel, basciLevel.getParentid(), basciLevel.getType(), (User) UserContext.getCurrentUser());
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:根据id 查询工单基本信息<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-14
	 * @return String
	 */
	public String searchComplaintBasicById() {
		// 通过id查询工单
		this.complaint = complaintManager.getComplaintById(fid);
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:根据id 查询工单基本信息 and other 信息 <br/>
	 * </p>
	 * @author 
	 * @version 0.2 2012-4-14
	 * @return String
	 */
	public String searchComplaintAndOtherById() {
		// 通过id查询工单
		this.complaint = complaintManager.getComplaintById(fid);
		// complaintManager.getOtherCompInfo(complaint);
		this.otherComplint =new HashMap<String, Object>();
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:提交工单上报<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-17
	 * @return String
	 */
	public String submitComplaint() {
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		complaint.setMainFid(fid);
		// 工单上报提交
		complaintManager.submitComplaint(complaint,currentUser);
		// 返回成功
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description: 工单退回后再次上报<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-9-10
	 * @return
	 * String
	 */
	public String submitReturnComplaint(){
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		//为了清空锁定人和锁定时间
		complaint.setLockingUserId(new BigDecimal(-1));
		// 工单上报提交
		complaintManager.submitComplaint(complaint,fid,complaint.getRebindno(),currentUser);
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:工单处理-退回上报人 <br />
	 * parameterMap:{ compId:'工单编号', feedbackreason:'退回原因内容' }
	 * </p>
	 * 
	 * @author hpf
	 * @version 0.2 2012-4-20
	 * @return String
	 */
	public String returnReportor() {
/*		{//测试
			parameterMap.put("sessionUser", getUserObject());
		}*/
		// 用户信息
		parameterMap.put("sessionUser", UserContext.getCurrentUser());
		// 退回工单上报人
		complaintManager.returnReportor(this.parameterMap);
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:工单处理-提交审批 <br />
	 * parameterMap:{ sessionUser:'当前用户', resultList:'处理结果-Result 处理结果实体bean',
	 * bulletin:'map-map key:'id',value:'name' complaint:'投诉对象' }
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-20
	 * @return String
	 */
	public String submitApproval() {
		// 处理
		String processLimit=null;
		// 返回
		String feedbacklimit=null;
		// 处理结果-Result 处理结果实体bean
		if(resultList!=null&&!resultList.isEmpty()){
			// 处理
			processLimit=resultList.get(0).getProcesstimelimit().toString();
			// 返回
			feedbacklimit=resultList.get(0).getFeedtimelimit().toString();
		}
		// 前台传入处理参数
		parameterMap.put("sessionUser", UserContext.getCurrentUser());
		// 工单里诶奥
		parameterMap.put("complaint",this.complaint);
		// 处理列表
		parameterMap.put("resultList",this.resultList);
		parameterMap.put("bulletinList",this.bulletinList);
		// 处理
		parameterMap.put("processLimit", processLimit);
		// 退回
		parameterMap.put("feedbackLimit", feedbacklimit);
		// 提交审批
		complaintManager.submitForApproval(this.parameterMap);
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:工单处理-投诉升级 <br />
	 * parameterMap:{ sessionUser:'当前用户',complaint:'投诉对象' }
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-23
	 * @return String
	 */
	public String complaintUpgrade() {
		// 用户信息
		parameterMap.put("sessionUser", UserContext.getCurrentUser());
		// 工单
		parameterMap.put("complaint",this.complaint);
		// 更新工单
		complaintManager.upgradeComplaint(this.parameterMap);
		// 返回成功
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:批量删除业务类型 <br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-7-5
	 * @return String
	 */
	public String basciLevelTypeDelelteList() {
		// 删除业务类型数据
		complaintManager.deleteBasciLevelType(basciLevelList);
		// 返回成功
		return SUCCESS;
	}
	

	/**
	 * <p>
	 * Description:批量删除业务范围 <br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-7-5
	 * @return String
	 */
	public String basciLevelScopeDelelte() {
		// 删除业务范围数据
		complaintManager.deleteBasciLevel(basciLevel);
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:工单处理-完成处理 <br />
	 * parameterMap:{ sessionUser:'当前用户', resultList:'处理结果-Result 处理结果实体bean'
	 * complaint:'投诉对象' }
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-23
	 * @return String
	 */
	public String finishComplaintProcess() {
		// 处理
		String processLimit=null;
		// 退回
		String feedbacklimit=null;
		// 处理结果-Result 处理结果实体bean
		if(resultList!=null&&!resultList.isEmpty()){
			// 处理
			processLimit=resultList.get(0).getProcesstimelimit().toString();
			// 退回
			feedbacklimit=resultList.get(0).getFeedtimelimit().toString();
		}
		// 前台传入处理参数
		parameterMap.put("sessionUser", UserContext.getCurrentUser());
		// 工单
		parameterMap.put("complaint",this.complaint);
		// 处理结果
		parameterMap.put("resultList",this.resultList);
		//通知对象
		parameterMap.put("bulletinList",this.bulletinList);
		// 处理时限
		parameterMap.put("processLimit", processLimit);
		// 反馈时限
		parameterMap.put("feedbackLimit", feedbacklimit);
		//工单处理完成处理信息保存
		complaintManager.finishedComplaintProccess(this.parameterMap);
		//sendMessage(bulletinList,complaint,feedbacklimit,processLimit,(User)UserContext.getCurrentUser());
		// 判断：当出发部门不为任务部门时
		//(1)生成短信通知出发部门经理和副经理维护客户
		//(2)生成一条待办事宜到出发部门
		complaintManager.dealComplaint(resultList, complaint);
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:待办工单-待处理完成处理 <br />
	 * </p>
	 * @author justin.xu
	 * @return String
	 */
	public String finishProcessWithApprove(){
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 待办工单-待处理完成处理
		complaintManager.finishedComplaintProccess(complaint, null, null, null, currentUser);
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:待办工单-已升级完成处理 <br />
	 * </p>
	 * @author justin.xu
	 * @return String
	 */
	public String finishProcessWithUpgraded(){
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 待办工单-已升级完成处理
		complaintManager.finishedComplaintProccess(complaint, resultList, null, bulletinList, currentUser);
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:待办工单-完成处理 <br />
	 * sessionUser:当前用户
	 * complaint:
	 * resultList
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-28
	 * @return String
	 */
	public String returnedFinishComplaintProcess() {
		// 待办工单-完成处理 
		complaintManager.finishedComplaintProccess(
				complaint,resultList,null,bulletinList,
				(User)UserContext.getCurrentUser());
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:工单处理-经理锁定工单<br />
	 * complaint:
	 * resultList
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-28
	 * @return String
	 */
	@JSON
	public String compProcess_lockById() {
		// 工单处理-经理锁定工单
		complaintManager.managerLockComplaint(complaint,(User)UserContext.getCurrentUser());
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:部门工单管理->退回处理人<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-23
	 * @return String
	 */
	@JSON
	public String returnToProcess() {
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 部门工单管理->退回处理人
		complaintManager.returnToProcess(complaintTaskCondtion.getFid(),
				complaintTaskCondtion.getDeptId(),
				complaintTaskCondtion.getFeedbackContent(), currentUser);
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:部门工单管理->反馈登记<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-4-24
	 * @return String
	 */
	public String passFeedback() {
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 部门工单管理->反馈登记
		complaintManager.passFeedback(complaintTaskCondtion.getFid(),
				complaintTaskCondtion.getDeptId(),
				complaintTaskCondtion.getFeedbackContent(),
				complaintTaskCondtion.getResolveCode(), currentUser);
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:部门工单管理->延时申请<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-24
	 * @return String
	 */
	public String delayApplication() {
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 部门工单管理->延时申请
		complaintManager.delayApplication(complaintTaskCondtion.getFid(),
				complaintTaskCondtion.getDeptId(),
				complaintTaskCondtion.getFeedbackContent(),
				complaintTaskCondtion.getApplyCode(), currentUser);
		// 返回成功
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:工单回访登记<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-24
	 * @return
	 * String
	 */
	public String submitVerfiyComplaint(){
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 工单回访登记
		complaintManager.submitVerfiyComplaint(complaint,feedBackReasion, currentUser);
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * 退回提交人
	 * @return
	 */
	public String returnSubmitor(){
		// 退回提交人
		this.complaintManager.returnSubmitor(complaint, (User) (UserContext.getCurrentUser()),feedbackReason);
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * 根据条件查询请求延时的申请
	 * @return
	 */
	@JSON
	public String searchPostponeResult(){
		// 据条件查询请求延时的申请
		if(resultSearchCondition!=null){
			resultSearchCondition.setDelay(Constants.IF_DELAY_APPLICATION_EFFECTIVE_NO);
			postponeResultList=complaintManager.getResultByCondition(this.resultSearchCondition);
		}
		// 返回成功
		return SUCCESS;
	}
	/**
	 * 同意延时申请
	 * @return
	 */
	public String agreeWithPostponeRequests(){
		// 同意延时申请
		boolean result=complaintManager.applyMoreTimeApproval(complaint, resultsToApprove, Constants.IF_DELAY_APPLICATION_EFFECTIVE_YES,null,null);
		if(result){
			// 返回成功
			return SUCCESS;
		}else{
			// 返回错误
			return ERROR;
		}
	}	
	
	/**
	 * 拒绝延时申请
	 * @return
	 */
	public String refusePostponeRequests(){
		// 申请延时审批，同意或者不同意都调用此方法，在状态中设置对应状态
		boolean result=complaintManager.applyMoreTimeApproval(
				complaint, resultsToApprove, 
				// 2
				Constants.IF_DELAY_APPLICATION_EFFECTIVE_REFUSE,
				// 用户信息
				(User)(UserContext.getCurrentUser()),returnReason
		);
		// 返回成功
		if(result){return SUCCESS;
		// 返回失败
		}else{return ERROR;}
	}	
	
	
	/**
	 * 是否需要延时审批按钮
	 * @return
	 */
	public String isPostponeAppButtonEnable(){
		// 处理结果表查询条件
		ResultSearchCondition resultSearchCondition=new ResultSearchCondition();
		// 工单id
		resultSearchCondition.setComplainid(complaint.getFid());
		// 延时
		resultSearchCondition.setDelay(Constants.IF_DELAY_APPLICATION_EFFECTIVE_NO);
		// 否需要延时审批按钮
		List<Result> results=complaintManager.getResultByCondition(resultSearchCondition);
		// 如果为空
		if(results.isEmpty()){
			// 失败
			postponeButtonFlag=false;
		}else{
			// 成功
			postponeButtonFlag=true;
		}
		// 返回成功
		return SUCCESS;
	}
	
	/**
	 * 判断待办工单中，“完成处理”按钮是否应该存在
	 * 注意：前提是：需要确定被检查的complaint状态是FEEDBACK_UNRESOLVE或者FEEDBACK_RESOLVED
	 * @return
	 */
	public String isProcessCompleteButtonEnable(){
		// 处理结果表查询条件
		ResultSearchCondition resultSearchCondition=new ResultSearchCondition();
		// 工单id
		resultSearchCondition.setComplainid(complaint.getFid());
		// 延时
		resultSearchCondition.setStat(Constants.COMPLAINT_TASK_STATUS_RETURNED);
		// 根据条件查询部门处理结果列表
		List<Result> results=complaintManager.getResultByCondition(resultSearchCondition);
		//要是存在退回的result，则显示“完成处理”按钮
		if(!results.isEmpty()){
			// 成功
			processCompleteBttnFlag=true;
		}else{
			// 失败
			processCompleteBttnFlag=false;
		}
		// 返回成功
		return SUCCESS;		
	}
	// 工单get方法
	/**
	 * 基础资料查询主界面：点击删除按钮，删除选中的基础资料
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String deleteBasicInfo(){
		complaintManager.deleteBusTypeByIds(basicInfoList);
		return SUCCESS;
	}

	/**
	 * 新基础资料--删除业务类型在操作基础资料页面中使用
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String deleteBusTypeByIdsInBusScopeVO(){
		complaintManager.deleteBusTypeByIdsInBusScopeVO(busTypes);
		return SUCCESS;
	}

	/**
	 * 业务项设置界面：点击提交按钮，向数据库中新增或修改投诉和异常的业务项
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String addBusinessItems(){
		User currentUser = (User) (UserContext.getCurrentUser());
		complaintManager.operateBusItem(basicInfoList, currentUser);
		return SUCCESS;
	}

	/**
	 * 处理结果基础资料设置界面：选定投诉或异常的类型后，加载出相应的业务项信息到下拉列表中
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String loadBusinessItems(){
		basicInfoList = complaintManager.searchBusItemByType(reportType);
		return SUCCESS;
	}

	/**
	 * 处理结果基础资料设置界面：选中一条基础资料，单击基础资料设置后，加载出此条业务范围下所有的业务类型到列表中
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String loadBasicInfo(){
		businessTypeList = complaintManager.searchBasicBusScopeVO(businessType);
		if(!CollectionUtils.isEmpty(businessTypeList)){
			//获取工单类型列表
			busTypes = businessTypeList.get(0).getBusTypes();
		}
		return SUCCESS;
	}
	
	/**
	 * 处理结果基础资料设置界面：新基础资料--业务项修改,业务范围新增与修改,业务类型新增修改删除
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String operateBasicBusInfo(){
		User currentUser = (User) (UserContext.getCurrentUser());
		complaintManager.operateBasicBusInfo(businessType, currentUser);
		return SUCCESS;
	}
	
	/**
	 * 业务项设置界面：删除业务项
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String deleteBusItemByIds(){
		complaintManager.deleteBusItemByIds(basicInfoList);
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:查询工单<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-10-22
	 * @return String
	 */
	@JSON
	public String searchComplaint() {
		if(null != fid && !"".equals(fid)){
			complaint = complaintManager.getComplaintById(fid);
		}
		return SUCCESS;
	}
	
	public List<BasicInfo> getBasicInfoList() {
		return basicInfoList;
	}
	public List<BasicBusScopeVO> getBusinessTypeList() {
		return businessTypeList;
	}
	public BasicBusScopeVO getBusinessType() {
		return businessType;
	}
	public void setBusinessType(BasicBusScopeVO businessType) {
		this.businessType = businessType;
	}
	public Complaint getComplaint() {
		return complaint;
	}
	// 工单set方法
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	// 工单manager set方法
	public void setComplaintManager(IComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}
	// fid get方法
	public String getFid() {
		return fid;
	}
	// fid set方法
	public void setFid(String fid) {
		this.fid = fid;
	}
	// OtherComplint get方法
	public Map<String, Object> getOtherComplint() {
		return otherComplint;
	}
	// OtherComplint get方法
	public void setOtherComplint(Map<String, Object> otherComplint) {
		this.otherComplint = otherComplint;
	}
	// ParameterMap get方法
	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}
	// ParameterMap set方法
	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}
	// ComplaintTaskCondtion get方法
	public ComplaintTaskCondtion getComplaintTaskCondtion() {
		return complaintTaskCondtion;
	}
	// ComplaintTaskCondtion set方法
	public void setComplaintTaskCondtion(
			ComplaintTaskCondtion complaintTaskCondtion) {
		this.complaintTaskCondtion = complaintTaskCondtion;
	}
	// DepartmentList get方法
	public List<Department> getDepartmentList() {
		return departmentList;
	}
	// DepartmentList set方法
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	// BulletinList get方法
	public List<Bulletin> getBulletinList() {
		return bulletinList;
	}
	// BulletinList set方法
	public void setBulletinList(List<Bulletin> bulletinList) {
		this.bulletinList = bulletinList;
	}
	// ResultsToApprove get方法
	public List<Result> getResultsToApprove() {
		return resultsToApprove;
	}
	// ResultsToApprove set方法
	public void setResultsToApprove(List<Result> resultsToApprove) {
		this.resultsToApprove = resultsToApprove;
	}
	// feedbackReason get方法
	public String getFeedbackReason() {
		return feedbackReason;
	}
	// feedbackReason set方法
	public void setFeedbackReason(String feedbackReason) {
		this.feedbackReason = feedbackReason;
	}
	// postponeResultList get方法
	public List<Result> getPostponeResultList() {
		return postponeResultList;
	}
	// postponeResultList set方法
	public void setPostponeResultList(List<Result> postponeResultList) {
		this.postponeResultList = postponeResultList;
	}
	// resultSearchCondition get方法
	public ResultSearchCondition getResultSearchCondition() {
		return resultSearchCondition;
	}
	// resultSearchCondition set方法
	public void setResultSearchCondition(ResultSearchCondition resultSearchCondition) {
		this.resultSearchCondition = resultSearchCondition;
	}
	// resultList get方法
	public List<Result> getResultList() {
		return resultList;
	}
	// resultList set方法
	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}
	// feedBackReasion get方法
	public FeedBackReasion getFeedBackReasion() {
		return feedBackReasion;
	}
	// feedBackReasion set方法
	public void setFeedBackReasion(FeedBackReasion feedBackReasion) {
		this.feedBackReasion = feedBackReasion;
	}
	// returnReason get方法
	public String getReturnReason() {
		return returnReason;
	}
	// returnReason set方法
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	// serviceId get方法
	public String getServiceId() {
		return serviceId;
	}
	// serviceId set方法
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	// postponeButtonFlag get方法
	public boolean getPostponeButtonFlag() {
		return postponeButtonFlag;
	}
	// postponeButtonFlag set方法
	public void setPostponeButtonFlag(boolean postponeButtonFlag) {
		this.postponeButtonFlag = postponeButtonFlag;
	}
	// basciLevelList get方法
	public List<BasciLevelView> getBasciLevelList() {
		return basciLevelList;
	}
	// basciLevelList set方法
	public void setBasciLevelList(List<BasciLevelView> basciLevelList) {
		this.basciLevelList = basciLevelList;
	}
	// processCompleteBttnFlag get方法
	public boolean isProcessCompleteBttnFlag() {
		return processCompleteBttnFlag;
	}
	// processCompleteBttnFlag set方法
	public void setProcessCompleteBttnFlag(boolean processCompleteBttnFlag) {
		this.processCompleteBttnFlag = processCompleteBttnFlag;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public List<BasicBusType> getBusTypes() {
		return busTypes;
	}
	public void setBusTypes(List<BasicBusType> busTypes) {
		this.busTypes = busTypes;
	}
	public void setBasicInfoList(List<BasicInfo> basicInfoList) {
		this.basicInfoList = basicInfoList;
	}	
	
	// procResultList get方法
	public List<ProcResult> getProcResultList() {
		return procResultList;
	}
	// procResultList set方法
	public void setProcResultList(List<ProcResult> procResultList) {
		this.procResultList = procResultList;
	}
	// basciLevel get方法
	public BasciLevel getBasciLevel() {
		return basciLevel;
	}
	// basciLevel set方法
	public void setBasciLevel(BasciLevel basciLevel) {
		this.basciLevel = basciLevel;
	}
	// procResult set方法
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	// complaintListBasciLevel get方法
	public List<BasciLevel> getComplaintListBasciLevel() {
		return complaintListBasciLevel;
	}
	// complaintListBasciLevel set方法
	public void setComplaintListBasciLevel(List<BasciLevel> complaintListBasciLevel) {
		this.complaintListBasciLevel = complaintListBasciLevel;
	}
	// exceptionListBasciLevel get方法
	public List<BasciLevel> getExceptionListBasciLevel() {
		return exceptionListBasciLevel;
	}
	// exceptionListBasciLevel set方法
	public void setExceptionListBasciLevel(List<BasciLevel> exceptionListBasciLevel) {
		this.exceptionListBasciLevel = exceptionListBasciLevel;
	}
	// complaintList get方法
	public String getComplaintList() {
		return complaintList;
	}
	// exceptionList get方法
	public String getExceptionList() {
		return exceptionList;
	}
	// exceptionList set方法
	public String getProcResult() {
		return procResult;
	}
}

