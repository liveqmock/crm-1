package com.deppon.crm.module.recompense.server.action;

/**.
 * 
 *作 者：张斌 
 *最后修改时间：2012年6月10日 
 *描 述：理赔管理ACTION层
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.OnlineApplyCondition;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.RecSmsInformation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.RecompenseView;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.deppon.foss.framework.shared.util.string.StringUtil;

public class RecompenseAction extends AbstractAction {

	// -----------------只需要get的数据（JSON
	// 通过get方法将ACTION中的数据转换为JSON传给前台）---------------------------------------------
	// 大区设置实体LIST
	private List<UserRoleDeptRelation> userRoleDeptRelationList;

	public List<UserRoleDeptRelation> getUserRoleDeptRelationList() {
		return userRoleDeptRelationList;
	}

	// 运单对象
	private Waybill waybill;
	// 客户对象
	private Member member;
	// 大区
	private Department dept;
	// 提示信息
	private String message;
	// 理赔数据
	private RecompenseApplication app;
	// 所属事业部
	private List<Department> bizDeptList;
	// 部门
	private List<Department> deptList;
	// 总页数
	private Long totalCount;
	// 职员列表
	private List<User> users;
	// 工作流列表
	private List<OAWorkflow> oaWorkflowList;
	// 差错信息列表
	private List<OaAccidentInfo> oaAccidentInfoList;
	// 跟进信息列表
	private List<Message> messageList;
	// 按钮IDs
	private int[] actionIds;
	// BEAN获得国际化对象
	private IMessageBundle messageBundle;
	// manager对象
	private RecompenseManager recompenseManager;
	// 客户ID
	private String custNumber;
	// 理赔上报对象
	private RecompenseView recompenseView;
	// 起始时间
	private Date endTime;
	// 理赔类型
	private String recompenseType;
	// 运单号
	private String wayBillNum;
	// 查询实体
	private RecompenseSearchCondition recomSearchCondition;
	// 获取理赔打印对象
	private OnlineApply onlineApplyPrintData;
	// 工号
	private String empCode;
	// 一条跟进信息
	private Message msg;
	// 部门/da名称
	private String deptName;
	// 起始时间
	private Date startTime;
	// 客户ID
	private String customerId;
	//理赔状态
	private String status;
	//在线理赔查询条件
	private OnlineApplyCondition onlineApplyCondition;
	//在线理赔集合
	private List<OnlineApply> onlineApplyList;
	//受理部门ID 
	private String acceptDept;
	//运单号集合
	private List<String> waybillNumList;
	public List<String> getWaybillNumList() {
		return waybillNumList;
	}
	public void setWaybillNumList(List<String> waybillNumList) {
		this.waybillNumList = waybillNumList;
	}
	public String getAcceptDept() {
		return acceptDept;
	}
	public void setAcceptDept(String acceptDept) {
		this.acceptDept = acceptDept;
	}
	public List<OnlineApply> getOnlineApplyList() {
		return onlineApplyList;
	}
	public void setOnlineApplyList(List<OnlineApply> onlineApplyList) {
		this.onlineApplyList = onlineApplyList;
	}
	public OnlineApplyCondition getOnlineApplyCondition() {
		return onlineApplyCondition;
	}
	public void setOnlineApplyCondition(OnlineApplyCondition onlineApplyCondition) {
		this.onlineApplyCondition = onlineApplyCondition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//是否获取短信模板
	private boolean toGetShortMessageTemplate;
	public boolean isToGetShortMessageTemplate() {
		return toGetShortMessageTemplate;
	}
	public void setToGetShortMessageTemplate(boolean toGetShortMessageTemplate) {
		this.toGetShortMessageTemplate = toGetShortMessageTemplate;
	}

	//理赔监控短信发送对象
	private String noticeTypes;
	public String getNoticeTypes() {
		return noticeTypes;
	}
	public void setNoticeTypes(String noticeTypes) {
		this.noticeTypes = noticeTypes;
	}
	private List<CellphoneMessageInfo> smsInfoList;
	
	public List<CellphoneMessageInfo> getSmsInfoList() {
		return smsInfoList;
	}
	public void setSmsInfoList(List<CellphoneMessageInfo> smsInfoList) {
		this.smsInfoList = smsInfoList;
	}

	// -----------------只需要set的数据(JSON
	// 通过set方法将前台传来的值设置到ACTION中)---------------------------------------------
	private int limit;
	private int start;
	private String position;
	public String getPosition() {
		return position;
	}
	public void setPosition(String position){
		this.position=position;
	}
	//理赔监控短信发送对象集合
	private List<RecSmsInformation> recSmsInformationList;
	public List<RecSmsInformation> getRecSmsInformationList() {
		return recSmsInformationList;
	}
	public void setRecSmsInformationList(
			List<RecSmsInformation> recSmsInformationList) {
		this.recSmsInformationList = recSmsInformationList;
	}
	//理赔监控短信发送内容
	private String msgContent;
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	//理赔监控短信发送模板
	private String shortMessageTemplate;
	public String getShortMessageTemplate() {
		return shortMessageTemplate;
	}
	public void setShortMessageTemplate(String shortMessageTemplate){
		this.shortMessageTemplate=shortMessageTemplate;
	}
	public Waybill getWaybill() {
		return waybill;
	}

	public Member getMember() {
		return member;
	}

	public Department getDept() {
		return dept;
	}

	public String getMessage() {
		return message;
	}

	public RecompenseApplication getApp() {
		return app;
	}
	public void setApp(RecompenseApplication app){
		this.app = app;
	}
	public List<Department> getBizDeptList() {
		return bizDeptList;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	private List<RecompenseApplication> recompenseAppList;

	public List<RecompenseApplication> getRecompenseAppList() {
		return recompenseAppList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<OAWorkflow> getOaWorkflowList() {
		return oaWorkflowList;
	}

	public List<OaAccidentInfo> getOaAccidentInfoList() {
		return oaAccidentInfoList;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public int[] getActionIds() {
		return actionIds;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}

	private int actionId;

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	
	private List<Payment> payRecordList;
	
	public List<Payment> getPayRecordList() {
		return payRecordList;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public void setRecompenseView(RecompenseView recompenseView) {
		this.recompenseView = recompenseView;
	}

	//
	private String recompenseId;

	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}
	//
	private List<String> recompenseIdList;
	
	public List<String> getRecompenseIdList() {
		return recompenseIdList;
	}
	public void setRecompenseIdList(List<String> recompenseIdList) {
		this.recompenseIdList = recompenseIdList;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}

	public void setWayBillNum(String wayBillNum) {
		this.wayBillNum = wayBillNum;
	}

	// -----------------需要get和set的数据---------------------------------------------

	public void setRecomSearchCondition(
			RecompenseSearchCondition recomSearchCondition) {
		this.recomSearchCondition = recomSearchCondition;
	}

	public RecompenseSearchCondition getRecomSearchCondition() {
		return recomSearchCondition;
	}
	
	//付款信息
	private Payment payment;
	
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
		
	public OnlineApply getOnlineApplyPrintData() {
		return onlineApplyPrintData;
	}

	/**
	 * @author Rock
	 * @description 使用recompenseId获取onlineApplyPrint 对象
	 */
	public String onlineApplyPrintDt() {
		onlineApplyPrintData = recompenseManager
				.getOnlineApplyByRecompenseId(recompenseId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过在线理赔的ID获取理赔对象
	 * @author 张斌
	 * @version 0.1 2012-4-19
	 * @date 2012-4-19
	 * @return none
	 * @update 2012-4-19下午8:51:06
	 */
	public String handleOnlineApply() {
		User user=(User) UserContext.getCurrentUser();
		app = recompenseManager.handleOnlineApply(recompenseId,user);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据运单号/或者差错编号查询<br/>
	 * 方法名：findWayBillByNumAndType
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-1
	 * @since JDK1.6
	 */
	@JSON
	public String findWayBillByNumAndType() {
		waybill = recompenseManager.getWaybillInfoByNo(
				recomSearchCondition.getWaybillNum(),
				recomSearchCondition.getRecompenseType());
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 通过客户编号查询客户信息<br/>
	 * 方法名：findCustById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-5
	 * @since JDK1.6
	 */
	@JSON
	public String findCustById() {
		member = recompenseManager.getCustomerByNum(custNumber);
		// waybill =
		// recompenseManager.getWayBillByNum(recomSearchCondition.getWaybillNum(),recomSearchCondition.getRecompenseType());
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 通过客户编号查询客户信息<br/>
	 * 方法名：findCustById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-5
	 * @since JDK1.6
	 */
	@JSON
	public String initArea() {
		User user = (User) UserContext.getCurrentUser();
		dept = recompenseManager.getBigAreaByDeptId(user.getEmpCode()
				.getDeptId().getStandardCode());
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 通过登录用户信息查询登录用户所属事业部<br/>
	 * 方法名：findCustById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-5
	 * @since JDK1.6
	 */
	@JSON
	public String initBus() {
		User user = (User) UserContext.getCurrentUser();
		dept = recompenseManager.getBizDeptByDeptId(user.getEmpCode()
				.getDeptId().getId());
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 通过查询大区<br/>
	 * 方法名：findCustById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-5
	 * @since JDK1.6
	 */
	@JSON
	public String searchAreas() {
		deptList = recompenseManager.getBigAreaList();
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 通过工号查询职员<br/>
	 * 方法名：searchEmployeesByEmpCode
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-15
	 * @since JDK1.6
	 */
	@JSON
	public String searchEmployeesByEmpCode() {
		users = recompenseManager.getUserListByEmpCode(empCode, 20);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 发送跟进信息<br/>
	 * 方法名：sendRecompenseMessage
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-16
	 * @since JDK1.6
	 */
	@JSON
	public String sendRecompenseMessage() {
		User user = (User) UserContext.getCurrentUser();
		messageList = recompenseManager.addMessageForRecompense(recompenseView
				.getApp().getReportDept(), recompenseView.getApp().getDeptId(),
				msg, user);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.flowUpSuccess");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据条件查询理赔信息<br/>
	 * 方法名：searchRecompense
	 * </p>
	 * 
	 * @author 潘光均
	 * @时间 2012-4-16
	 * @since JDK1.6
	 */
	public String searchRecompense() {
		User user = (User) UserContext.getCurrentUser();
		recomSearchCondition.setLimit(limit);
		recomSearchCondition.setStart(start);
		totalCount = Long.valueOf(recompenseManager.getRecompenseCountByCondition(
				recomSearchCondition, user));
		if (totalCount == 0) {
			totalCount = (long) 1;
		}
		recompenseAppList = recompenseManager.searchRecompenseByCondition(
				recomSearchCondition, user);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据条件查询客户历史理赔信息<br/>
	 * 方法名：searchHistroyRecompense
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-5-7
	 * @since JDK1.6
	 */
	public String searchHistroyRecompense() {
		recompenseAppList = recompenseManager
				.getRecompenseApplicationByCustomer(customerId, recompenseId,
						startTime, endTime);
		app = recompenseManager
				.getRecompenseApplicationStatByCustomer(recompenseAppList);// 统计信息在APP中
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据条件查询客户历史理赔统计信息<br/>
	 * 方法名：searchHistroyRecompenseAmount
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-5-11
	 * @since JDK1.6
	 */
	public String searchHistroyRecompenseAmount() {
		app = recompenseManager
				.getRecompenseApplicationStatByCustomer(recompenseAppList);// 统计信息在APP中
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据条件查询理赔监控的理赔信息<br/>
	 * 方法名：searchMonitorRecompense
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-5-07
	 * @since JDK1.6
	 */
	public String searchMonitorRecompense() {
		recomSearchCondition.setLimit(limit);
		recomSearchCondition.setStart(start);
		totalCount = Long.valueOf(recompenseManager
				.getRecompenseCountForMonitor(recomSearchCondition));
		if (totalCount == 0) {
			totalCount = (long) 1;
		}
		recompenseAppList = recompenseManager
				.getRecompenseForMonitoring(recomSearchCondition);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据ID查询该理赔所有相关系信息<br/>
	 * 方法名：searchRecompenseById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-14
	 * @since JDK1.6
	 */
	@JSON
	public String searchRecompenseById() {
		User user = (User) UserContext.getCurrentUser();
		Map map = recompenseManager.getRecompenseApplicationById(
				recompenseId,user);
		app = (RecompenseApplication) map.get(Constants.RECOMPENSE_APPLICATION);
		actionIds = (int[]) map.get("actions");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据ID查询该理赔工作流相关系信息<br/>
	 * 方法名：searchRecompenseWorkflowById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-7-11
	 * @since JDK1.6
	 */
	@JSON
	public String searchRecompenseWorkflowById() {
		oaWorkflowList = recompenseManager
				.getOaWorkflowByRecompenseId(recompenseId);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 查询理赔单的差错信息<br/>
	 * 方法名：searchRecompenseOaAccidentInfo
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-06-29
	 * @since JDK1.6
	 */
	@JSON
	public String searchRecompenseOaAccidentInfo() {
		oaAccidentInfoList = recompenseManager.getAccidentByNo(wayBillNum,
				recompenseType);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 通过部门名称查询部门.
	 * @author 潘光均
	 * @version 0.1 2012-4-10
	 * @date 2012-4-10
	 * @return none
	 * @update 2012-4-10 下午8:51:06
	 */
	public String searchDeptByName() {
		deptList = recompenseManager.getDeptListByDeptName(deptName, 10);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 理赔催办.
	 * @author 潘光均
	 * @version 0.1 2012-4-16
	 * @date 2012-4-16
	 * @return String
	 * @update 2012-4-16 下午3:35:35
	 */
	public String urgeProcess() {
		recompenseManager.reminderForRecompenseMonitor(recompenseId);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.urgeProcessSuccess");
		return SUCCESS;
	}

	/**
	 * 
	 * @description查询当前用户所拥有的大区权限
	 * @author 张斌
	 * @version 0.1 2012-6-16
	 * @date 2012-4-16
	 * @return String
	 */
	public String searchUserBlongArea() {
		User user = (User) UserContext.getCurrentUser();
		userRoleDeptRelationList = recompenseManager
				.getUserRoleDeptRelationByUserId(user.getId());
		return SUCCESS;
	}

	// --------------------------------------------工作流--------------------------------------
	/**
	 * .
	 * <p>
	 * 修改理赔<br/>
	 * 方法名：updateRecompense
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-5
	 * @since JDK1.6
	 */
	@JSON
	public String updateRecompense() {
		User user = (User) UserContext.getCurrentUser();
		Map recompenseMap = new HashMap();
		RecompenseApplication app = recompenseView.getApp();
		recompenseMap.put(Constants.RECOMPENSE_APPLICATION, app);
		Map attachmentMap = new HashMap();
		attachmentMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getAttachmentAddList());
		attachmentMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getAttachmentAddList());
		attachmentMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getAttachmentUpdList());
		Map issueItemMap = new HashMap();
		issueItemMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getIssueItemAddList());
		issueItemMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getIssueItemDelList());
		issueItemMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getIssueItemUpdList());
		Map goodsTransMap = new HashMap();
		goodsTransMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getGoodsTransAddList());
		goodsTransMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getGoodsTransDelList());
		goodsTransMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getGoodsTransUpdList());
		recompenseMap.put(Constants.RECOMPENSE_ATTACHMENT_MAP, attachmentMap);
		recompenseMap.put(Constants.RECOMPENSE_ISSUEITEM_MAP, issueItemMap);
		recompenseMap.put(Constants.RECOMPENSE_GOODSTRANS_MAP, goodsTransMap);
		recompenseMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		recompenseManager.performAction(recompenseMap, 220, user.getId());
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.updateRecompenseSuccess");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 理赔上报<br/>
	 * 方法名：findCustById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-5
	 * @since JDK1.6
	 */
	@JSON
	public String createRecompense() {
		User user = (User) UserContext.getCurrentUser();
		Map recompenseMap = new HashMap();
		RecompenseApplication app = recompenseView.getApp();
		app.setAttachmentList(recompenseView.getAttachmentAddList());
		app.setIssueItemList(recompenseView.getIssueItemAddList());
		app.setGoodsTransList(recompenseView.getGoodsTransAddList());
		recompenseMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		recompenseManager.performAction(recompenseMap, 210, user.getId());
		if (app.getRecompenseMethod().equals("online")) {
			message = messageBundle.getMessage(getLocale(),
					"i18n.recompense.proccessRecompenseSuccess");
		} else {
			message = messageBundle.getMessage(getLocale(),
					"i18n.recompense.createRecompenseSuccess");
		}

		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 初步处理保存<br/>
	 * 方法名：saveRecompenseProcessInfo
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-5
	 * @since JDK1.6
	 */
	@JSON
	public String saveRecompenseProcessInfo() {
		User user = (User) UserContext.getCurrentUser();
		Map recompenseMap = new HashMap();
		RecompenseApplication app = recompenseView.getApp();
		recompenseMap.put(Constants.RECOMPENSE_APPLICATION, app);
		Map deptChargeMap = new HashMap();
		deptChargeMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getDeptChargeAddList());
		deptChargeMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getDeptChargeDelList());
		deptChargeMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getDeptChargeUpdList());
		Map responsibleDeptMap = new HashMap();
		responsibleDeptMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getDutyDeptAddList());
		responsibleDeptMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getDutyDeptDelList());
		responsibleDeptMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getDutyDeptUpdList());
		Map messageReminderMap = new HashMap();
		messageReminderMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getMsgReminderAddList());
		messageReminderMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getMsgReminderDelList());
		messageReminderMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getMsgReminderUpdList());
		Map awardItemMap = new HashMap();
		awardItemMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getAwardItemAddList());
		awardItemMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getAwardItemDelList());
		awardItemMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getAwardItemUpdList());
		recompenseMap.put(Constants.RECOMPENSE_DEPTCHARGE_MAP, deptChargeMap);
		recompenseMap.put(Constants.RECOMPENSE_RESPONSIBLEDEPT_MAP,
				responsibleDeptMap);
		recompenseMap.put(Constants.RECOMPENSE_MESSAGEREMINDER_MAP,
				messageReminderMap);
		recompenseMap.put(Constants.RECOMPENSE_AWARDITEM_MAP, awardItemMap);
		recompenseMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		recompenseManager.saveRecompenseProcessInfo(recompenseMap);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.success");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 工作流操作（那些只需要转一个ID就OK的）<br/>
	 * 方法名：performAction
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-17
	 * @since JDK1.6
	 */
	@JSON
	public String performAction() {
		User user = (User) UserContext.getCurrentUser();
		Map recompenseMap = new HashMap();
		RecompenseApplication app = recompenseView.getApp();
		recompenseMap.put(Constants.RECOMPENSE_APPLICATION, app);
		Map deptChargeMap = new HashMap();
		deptChargeMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getDeptChargeAddList());
		deptChargeMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getDeptChargeDelList());
		deptChargeMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getDeptChargeUpdList());
		Map responsibleDeptMap = new HashMap();
		responsibleDeptMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getDutyDeptAddList());
		responsibleDeptMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getDutyDeptDelList());
		responsibleDeptMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getDutyDeptUpdList());
		Map messageReminderMap = new HashMap();
		messageReminderMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getMsgReminderAddList());
		messageReminderMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getMsgReminderDelList());
		messageReminderMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getMsgReminderUpdList());
		Map awardItemMap = new HashMap();
		awardItemMap.put(Constants.LIST_TYPE_ADD,
				recompenseView.getAwardItemAddList());
		awardItemMap.put(Constants.LIST_TYPE_DELETE,
				recompenseView.getAwardItemDelList());
		awardItemMap.put(Constants.LIST_TYPE_UPDATE,
				recompenseView.getAwardItemUpdList());
		recompenseMap.put(Constants.RECOMPENSE_DEPTCHARGE_MAP, deptChargeMap);
		recompenseMap.put(Constants.RECOMPENSE_RESPONSIBLEDEPT_MAP,
				responsibleDeptMap);
		recompenseMap.put(Constants.RECOMPENSE_MESSAGEREMINDER_MAP,
				messageReminderMap);
		recompenseMap.put(Constants.RECOMPENSE_AWARDITEM_MAP, awardItemMap);
		recompenseMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		recompenseManager.performAction(recompenseMap, actionId, user.getId());
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.success");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 金额确认<br/>
	 * 方法名：amountRecognized
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-19
	 * @since JDK1.6
	 */
	@JSON
	public String amountRecognized() {
		User user = (User) UserContext.getCurrentUser();
		recompenseManager.confirmRecompenseAmountInfo(recompenseId, user);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.success");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 取消初步处理<br/>
	 * 方法名：cancelProcess
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-19
	 * @since JDK1.6
	 */
	@JSON
	public String cancelProcess() {
		User user = (User) UserContext.getCurrentUser();
		recompenseManager.cancelRecompenseProcessInfo(recompenseId, user);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.success");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 取消初步处理<br/>
	 * 方法名：cancelProcess
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-4-19
	 * @since JDK1.6
	 */
	public String queryAllBusiness() {
		bizDeptList = recompenseManager.getAllBizDeptList();
		return SUCCESS;
	}
	/**.
	 * <p>
	 * 查询付款记录集合<br/>
	 * 方法名：searchPayRecordList
	 * </p>
	 * @author 侯斌飞
	 * @时间 2012-12-31
	 */
	 public String searchPayRecordList(){
		 payRecordList = recompenseManager.searchPaymentHistoryByRecompenseId(recompenseId);
		 return SUCCESS;
	 }
	 /**
	  * 
	  * <p>
	  * Description:根据收银员工号查账号信息<br />
	  * </p>
	  * @author 华龙
	  * @version 0.1 2013-1-4
	  * @return
	  * String
	  */
	 
	public String queryPaymentByEmployeeCode(){
		payRecordList = recompenseManager.queryPaymentByEmployeeNum(empCode);
		return SUCCESS;
	}
	 /**
	  * 
	  * <p>
	  * Description:理赔监控得到短信模板员工姓名和工号<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-7-5
	  * @return
	  * String
	  */
	 
	public String searchEmpAndShortMessageTemplate(){
		if(toGetShortMessageTemplate){
			shortMessageTemplate=recompenseManager.getSMSTemplate(
				status,wayBillNum, deptName, recompenseType, noticeTypes==""?"1":noticeTypes);
		}
		recSmsInformationList=recompenseManager.getMessageReceiver(
				recompenseIdList, noticeTypes);
		return SUCCESS;
	}
	/**
	  * 
	  * <p>
	  * Description:理赔监控单条短信发送<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-7-5
	  * @return
	  * String
	  */
	 
	public String oneShortMessageSend(){
		User user =(User) UserContext.getCurrentUser();
		String standardCode=user.getEmpCode().getDeptId().getStandardCode();
		if(msgContent==null||msgContent==""){
			msgContent = recompenseManager.getSMSTemplate(
					status, wayBillNum, deptName, recompenseType,"1");
		}
		for(int i=0;i<smsInfoList.size();i++){
			smsInfoList.get(i).setMsgContent(msgContent);
			smsInfoList.get(i).setMsgType(Constant.SMS_RECOMPENSE_CODE);
			smsInfoList.get(i).setSender(user.getEmpCode().getEmpName());
			smsInfoList.get(i).setWaybillNo(wayBillNum);
			smsInfoList.get(i).setSendDept(standardCode);
		}
		recompenseManager.sendSmsInfo(smsInfoList);
		return SUCCESS;
	}
	/**
	  * 
	  * <p>
	  * Description:理赔监控多条短信发送<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-7-5
	  * @return
	  * String
	  */
	 
	public String manyShortMessageSend(){
		User user =(User) UserContext.getCurrentUser();
		String standardCode=user.getEmpCode().getDeptId().getStandardCode();
		List<RecSmsInformation> rsiList=recompenseManager.getMessageReceiver(recompenseIdList, noticeTypes);
		smsInfoList=new ArrayList<CellphoneMessageInfo>();
		for(int i=0;i<rsiList.size();i++){
			CellphoneMessageInfo sif=new CellphoneMessageInfo();
			RecSmsInformation info = (RecSmsInformation)rsiList.get(i);
			sif.setMsgContent(msgContent);
			sif.setMsgType(Constant.SMS_RECOMPENSE_CODE);
			sif.setSender(user.getEmpCode().getEmpName());
			sif.setSendDept(standardCode);
			sif.setMobile(info.getMobileNumber());
			sif.setRecompenseType(info.getRecompenseType());
			sif.setSendDeptName(info.getDeptName());
			sif.setWaybillNo(info.getRecompenseNum());
			smsInfoList.add(sif);
		}
		recompenseManager.sendSmsInfoMore(smsInfoList, status,noticeTypes);
		return SUCCESS;
	}
	/**
	  * 
	  * <p>
	  * Description:理赔监控多条短信发送模板<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-7-5
	  * @return
	  * String
	  */
	 
	public String searchSMSTemplateMore(){
		msgContent=recompenseManager.getSMSTemplateMore(status, noticeTypes);
		return SUCCESS;
	}
	/**
	  * 
	  * <p>
	  * Description:在线理赔监控查询<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-11-1
	  * @return
	  */
	 @JSON
	public String searchOnlineApply(){
		Map<String,Object> map = recompenseManager.searchOnlineApply(onlineApplyCondition, limit, start);
		onlineApplyList = (List<OnlineApply>) map.get("onlineApplyList");
		totalCount = (Long) map.get("totalCount");
		return SUCCESS;
	}
	 /**
	  * 
	  * <p>
	  * Description:在线理赔监控获取短信发送人<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-11-1
	  * @return recSmsInformationList
	  */
	 @JSON
	public String searchOnlineApplyPerson(){
		waybillNumList = new ArrayList<String>();
		waybillNumList.add(wayBillNum);
		recSmsInformationList = recompenseManager.searchOnlineApplyPerson(waybillNumList, noticeTypes);
		return SUCCESS;
	}
	 /**
	  * 
	  * <p>
	  * Description:在线理赔监控单条短信发送<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-11-1
	  * @return 
	  */
	 @JSON
	public String oneShortMessageSendForOnline(){
		User user =(User) UserContext.getCurrentUser();
		String standardCode=user.getEmpCode().getDeptId().getStandardCode();
		for(int i=0;i<smsInfoList.size();i++){
			smsInfoList.get(i).setMsgContent(msgContent);
			smsInfoList.get(i).setMsgType(Constant.SMS_RECOMPENSE_CODE);
			smsInfoList.get(i).setSender(user.getEmpCode().getEmpName());
			smsInfoList.get(i).setWaybillNo(wayBillNum);
			smsInfoList.get(i).setSendDept(standardCode);
		}
		recompenseManager.sendSmsInfo(smsInfoList);
		return SUCCESS;
	}
	 /**
	  * 
	  * <p>
	  * Description:在线理赔监控多条短信发送<br />
	  * </p>
	  * @author 杨伟
	  * @version 0.1 2013-11-1
	  * @return 
	  */
	 @JSON
	public String manyShortMessageSendForOnline(){
		User user =(User) UserContext.getCurrentUser();
		String standardCode=user.getEmpCode().getDeptId().getStandardCode();
		recSmsInformationList=recompenseManager.searchOnlineApplyPerson(waybillNumList, noticeTypes);
		smsInfoList=new ArrayList<CellphoneMessageInfo>();
		for(int i=0;i<recSmsInformationList.size();i++){
			CellphoneMessageInfo sif=new CellphoneMessageInfo();
			RecSmsInformation info = (RecSmsInformation)recSmsInformationList.get(i);
			sif.setMsgContent(msgContent);
			sif.setMsgType(Constant.SMS_RECOMPENSE_CODE);
			sif.setSender(user.getEmpCode().getEmpName());
			sif.setSendDept(standardCode);
			sif.setMobile(info.getMobileNumber());
			sif.setRecompenseType(info.getRecompenseType());
			sif.setWaybillNo(info.getRecompenseNum());
			smsInfoList.add(sif);
		}
		recompenseManager.sendSmsInfoMore(smsInfoList, null,null);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:在线理赔监控催促办理
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-11-13上午9:20:44
	 * @return
	 * String
	 * @update 2013-11-13上午9:20:44
	 */
	public String pressDoForOnline(){
		recompenseManager.pressDoForOnline(recompenseId);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.urgeProcessSuccess");
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:在线理赔监控查看详情
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-11-16下午4:50:32
	 * @return
	 * String
	 * @update 2013-11-16下午4:50:32
	 */
	public String lookUpOnlineApplyDetail(){
		User user=(User) UserContext.getCurrentUser();
		app = recompenseManager.lookUpOnlineApplyDetail(recompenseId,user);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:审批完成的理赔退回按钮功能
	 * </p>
	 * @author 杨伟
	 * @version 0.1 
	 * @return String
	 * @updateTime 2013-12-17
	 */
	public String recompenseBack(){
		User user=(User) UserContext.getCurrentUser();
		Map recompenseMap = recompenseManager.getRecompenseApplicationById(app.getId(), user);
		recompenseManager.performAction(recompenseMap,440, user.getId());
		return SUCCESS;
	}
}
