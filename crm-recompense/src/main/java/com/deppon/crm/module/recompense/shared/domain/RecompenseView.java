package com.deppon.crm.module.recompense.shared.domain;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * <p>
 * Description:理赔视图<br />
 * </p>
 * @title RecompenseView.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class RecompenseView {

	private RecompenseApplication app = new RecompenseApplication();
	// 出险信息
	private List<IssueItem> issueItemDelList = new ArrayList<IssueItem>();
	//增加
	private List<IssueItem> issueItemAddList = new ArrayList<IssueItem>();
	//更新
	private List<IssueItem> issueItemUpdList = new ArrayList<IssueItem>();
	// 货物交易
	private List<GoodsTrans> goodsTransDelList = new ArrayList<GoodsTrans>();
	//增加
	private List<GoodsTrans> goodsTransAddList = new ArrayList<GoodsTrans>();
	//更新
	private List<GoodsTrans> goodsTransUpdList = new ArrayList<GoodsTrans>();
	// 附件清单
	private List<RecompenseAttachment> attachmentDelList = new ArrayList<RecompenseAttachment>();
	//增加
	private List<RecompenseAttachment> attachmentAddList = new ArrayList<RecompenseAttachment>();
	//更新
	private List<RecompenseAttachment> attachmentUpdList = new ArrayList<RecompenseAttachment>();
	// 2012-3-28添加
	/**
	 * 入部门费用
	 */
	// 新增集合
	private List<DeptCharge> deptChargeAddList = new ArrayList<DeptCharge>();
	// 修改集合
	private List<DeptCharge> deptChargeUpdList = new ArrayList<DeptCharge>();
	// 删除集合
	private List<DeptCharge> deptChargeDelList = new ArrayList<DeptCharge>();

	/**
	 * 责任部门
	 */
	// 新增集合
	private List<ResponsibleDept> dutyDeptAddList = new ArrayList<ResponsibleDept>();
	// 修改集合
	private List<ResponsibleDept> dutyDeptUpdList = new ArrayList<ResponsibleDept>();
	// 删除集合
	private List<ResponsibleDept> dutyDeptDelList = new ArrayList<ResponsibleDept>();

	/**
	 * 消息提醒
	 */
	// 新增集合
	private List<MessageReminder> msgReminderAddList = new ArrayList<MessageReminder>();
	// 修改集合
	private List<MessageReminder> msgReminderUpdList = new ArrayList<MessageReminder>();
	// 删除集合
	private List<MessageReminder> msgReminderDelList = new ArrayList<MessageReminder>();

	/**
	 * 奖罚明细
	 */
	// 新增集合
	private List<AwardItem> awardItemAddList = new ArrayList<AwardItem>();
	// 修改集合
	private List<AwardItem> awardItemUpdList = new ArrayList<AwardItem>();
	// 删除集合
	private List<AwardItem> awardItemDelList = new ArrayList<AwardItem>();

	// 当前可进行操作
	private int[] actions;
	// 当前步骤
	private int[] steps;
	/**
	 * <p>
	 * Description:app<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public RecompenseApplication getApp() {
		return app;
	}
	/**
	 * <p>
	 * Description:app<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setApp(RecompenseApplication app) {
		this.app = app;
	}
	/**
	 * <p>
	 * Description:issueItemDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<IssueItem> getIssueItemDelList() {
		return issueItemDelList;
	}
	/**
	 * <p>
	 * Description:issueItemDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setIssueItemDelList(List<IssueItem> issueItemDelList) {
		this.issueItemDelList = issueItemDelList;
	}
	/**
	 * <p>
	 * Description:issueItemAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<IssueItem> getIssueItemAddList() {
		return issueItemAddList;
	}
	/**
	 * <p>
	 * Description:issueItemAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setIssueItemAddList(List<IssueItem> issueItemAddList) {
		this.issueItemAddList = issueItemAddList;
	}
	/**
	 * <p>
	 * Description:issueItemUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<IssueItem> getIssueItemUpdList() {
		return issueItemUpdList;
	}
	/**
	 * <p>
	 * Description:issueItemUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setIssueItemUpdList(List<IssueItem> issueItemUpdList) {
		this.issueItemUpdList = issueItemUpdList;
	}
	/**
	 * <p>
	 * Description:goodsTransDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<GoodsTrans> getGoodsTransDelList() {
		return goodsTransDelList;
	}
	/**
	 * <p>
	 * Description:goodsTransDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setGoodsTransDelList(List<GoodsTrans> goodsTransDelList) {
		this.goodsTransDelList = goodsTransDelList;
	}
	/**
	 * <p>
	 * Description:goodsTransAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<GoodsTrans> getGoodsTransAddList() {
		return goodsTransAddList;
	}
	/**
	 * <p>
	 * Description:goodsTransAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setGoodsTransAddList(List<GoodsTrans> goodsTransAddList) {
		this.goodsTransAddList = goodsTransAddList;
	}
	/**
	 * <p>
	 * Description:goodsTransUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<GoodsTrans> getGoodsTransUpdList() {
		return goodsTransUpdList;
	}
	/**
	 * <p>
	 * Description:goodsTransUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setGoodsTransUpdList(List<GoodsTrans> goodsTransUpdList) {
		this.goodsTransUpdList = goodsTransUpdList;
	}
	/**
	 * <p>
	 * Description:attachmentDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<RecompenseAttachment> getAttachmentDelList() {
		return attachmentDelList;
	}
	/**
	 * <p>
	 * Description:attachmentDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAttachmentDelList(List<RecompenseAttachment> attachmentDelList) {
		this.attachmentDelList = attachmentDelList;
	}
	/**
	 * <p>
	 * Description:attachmentAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<RecompenseAttachment> getAttachmentAddList() {
		return attachmentAddList;
	}
	/**
	 * <p>
	 * Description:attachmentAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAttachmentAddList(List<RecompenseAttachment> attachmentAddList) {
		this.attachmentAddList = attachmentAddList;
	}
	/**
	 * <p>
	 * Description:attachmentUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<RecompenseAttachment> getAttachmentUpdList() {
		return attachmentUpdList;
	}
	/**
	 * <p>
	 * Description:attachmentUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAttachmentUpdList(List<RecompenseAttachment> attachmentUpdList) {
		this.attachmentUpdList = attachmentUpdList;
	}
	/**
	 * <p>
	 * Description:deptChargeAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<DeptCharge> getDeptChargeAddList() {
		return deptChargeAddList;
	}
	/**
	 * <p>
	 * Description:deptChargeAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptChargeAddList(List<DeptCharge> deptChargeAddList) {
		this.deptChargeAddList = deptChargeAddList;
	}
	/**
	 * <p>
	 * Description:deptChargeUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<DeptCharge> getDeptChargeUpdList() {
		return deptChargeUpdList;
	}
	/**
	 * <p>
	 * Description:deptChargeUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptChargeUpdList(List<DeptCharge> deptChargeUpdList) {
		this.deptChargeUpdList = deptChargeUpdList;
	}
	/**
	 * <p>
	 * Description:deptChargeDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<DeptCharge> getDeptChargeDelList() {
		return deptChargeDelList;
	}
	/**
	 * <p>
	 * Description:deptChargeDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptChargeDelList(List<DeptCharge> deptChargeDelList) {
		this.deptChargeDelList = deptChargeDelList;
	}
	/**
	 * <p>
	 * Description:dutyDeptAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<ResponsibleDept> getDutyDeptAddList() {
		return dutyDeptAddList;
	}
	/**
	 * <p>
	 * Description:dutyDeptAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDutyDeptAddList(List<ResponsibleDept> dutyDeptAddList) {
		this.dutyDeptAddList = dutyDeptAddList;
	}
	/**
	 * <p>
	 * Description:dutyDeptUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<ResponsibleDept> getDutyDeptUpdList() {
		return dutyDeptUpdList;
	}
	/**
	 * <p>
	 * Description:dutyDeptUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDutyDeptUpdList(List<ResponsibleDept> dutyDeptUpdList) {
		this.dutyDeptUpdList = dutyDeptUpdList;
	}
	/**
	 * <p>
	 * Description:dutyDeptDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<ResponsibleDept> getDutyDeptDelList() {
		return dutyDeptDelList;
	}
	/**
	 * <p>
	 * Description:dutyDeptDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDutyDeptDelList(List<ResponsibleDept> dutyDeptDelList) {
		this.dutyDeptDelList = dutyDeptDelList;
	}
	/**
	 * <p>
	 * Description:msgReminderAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<MessageReminder> getMsgReminderAddList() {
		return msgReminderAddList;
	}
	/**
	 * <p>
	 * Description:msgReminderAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setMsgReminderAddList(List<MessageReminder> msgReminderAddList) {
		this.msgReminderAddList = msgReminderAddList;
	}
	/**
	 * <p>
	 * Description:msgReminderUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<MessageReminder> getMsgReminderUpdList() {
		return msgReminderUpdList;
	}
	/**
	 * <p>
	 * Description:msgReminderUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setMsgReminderUpdList(List<MessageReminder> msgReminderUpdList) {
		this.msgReminderUpdList = msgReminderUpdList;
	}
	/**
	 * <p>
	 * Description:msgReminderDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<MessageReminder> getMsgReminderDelList() {
		return msgReminderDelList;
	}
	/**
	 * <p>
	 * Description:msgReminderDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setMsgReminderDelList(List<MessageReminder> msgReminderDelList) {
		this.msgReminderDelList = msgReminderDelList;
	}
	/**
	 * <p>
	 * Description:awardItemAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<AwardItem> getAwardItemAddList() {
		return awardItemAddList;
	}
	/**
	 * <p>
	 * Description:awardItemAddList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAwardItemAddList(List<AwardItem> awardItemAddList) {
		this.awardItemAddList = awardItemAddList;
	}
	/**
	 * <p>
	 * Description:awardItemUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<AwardItem> getAwardItemUpdList() {
		return awardItemUpdList;
	}
	/**
	 * <p>
	 * Description:awardItemUpdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAwardItemUpdList(List<AwardItem> awardItemUpdList) {
		this.awardItemUpdList = awardItemUpdList;
	}
	/**
	 * <p>
	 * Description:awardItemDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<AwardItem> getAwardItemDelList() {
		return awardItemDelList;
	}
	/**
	 * <p>
	 * Description:awardItemDelList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAwardItemDelList(List<AwardItem> awardItemDelList) {
		this.awardItemDelList = awardItemDelList;
	}
	/**
	 * <p>
	 * Description:actions<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public int[] getActions() {
		return actions;
	}
	/**
	 * <p>
	 * Description:actions<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setActions(int[] actions) {
		this.actions = actions;
	}
	/**
	 * <p>
	 * Description:steps<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public int[] getSteps() {
		return steps;
	}
	/**
	 * <p>
	 * Description:steps<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setSteps(int[] steps) {
		this.steps = steps;
	}



}
