package com.deppon.crm.module.recompense.server.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.esb.impl.EsbToFossAsyOperateImpl;
import com.deppon.crm.module.client.fin.IFINSelfserviceOperate;
import com.deppon.crm.module.client.fin.domain.BillInfo;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.crm.module.client.fin.domain.CostDetail;
import com.deppon.crm.module.client.fin.domain.NoBillingRecompenseInfo;
import com.deppon.crm.module.client.fin.domain.ResponsibilityDeptInfo;
import com.deppon.crm.module.client.fin.impl.FINRecompenserOperateImpl;
import com.deppon.crm.module.client.order.IOaAccidentOperate;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.client.workflow.IPaymentApplyOperate;
import com.deppon.crm.module.client.workflow.IRecompenseApplyOperate;
import com.deppon.crm.module.client.workflow.domain.AccountInfo;
import com.deppon.crm.module.client.workflow.domain.MuchRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.AccidentDescription;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.IndeptCharges;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.RewardPunishment;
import com.deppon.crm.module.client.workflow.domain.PaymentInfo;
import com.deppon.crm.module.common.server.service.impl.BankProvinceCityServiceImpl;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.dao.BaseModelDao;
import com.deppon.crm.module.recompense.server.dao.ListItemDao;
import com.deppon.crm.module.recompense.server.dao.RecompenseDao;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.Balance;
import com.deppon.crm.module.recompense.shared.domain.BankAccount;
import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.OnlineApplyCondition;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.PaymentBill;
import com.deppon.crm.module.recompense.shared.domain.RecalledCompensation;
import com.deppon.crm.module.recompense.shared.domain.RecSmsInformation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.recompense.shared.domain.TodoReminder;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.crm.module.recompense.shared.exception.RecompenseException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.crm.module.recompense.shared.exception.RecompenseMonitorException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseMonitorExceptionType;
import com.deppon.crm.module.workflow.server.dao.INormalClaimDao;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.erp.payment.DepClaimsBill;
import com.deppon.fin.selfservice.QueryCashieraccountRequest;
import com.deppon.foss.crm.BankPayInfo;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;
import com.deppon.foss.crm.ResponsibilityInfo;
import com.deppon.foss.framework.exception.GeneralException;
//import com.deppon.crm.module.client.sms.ISmsSender;
import com.deppon.foss.framework.shared.util.string.StringUtil;

//问题记录---- 子表的关联id谁放？
//线程单例 ？
public class RecompenseServiceImpl implements RecompenseService {

	private static RecompenseService service;
	// 调用费控接口
	private FINRecompenserOperateImpl fINRecompenserOperate;

	// F
	private EsbToFossAsyOperateImpl esbToFossAsyOperate;
	private BaseModelDao baseModelDao;
	private ListItemDao listItemDao;
	private RecompenseDao recompenseDao;
	private INormalClaimDao normalClaimDao;
	// private OAClient oaClient;

	// OA相关接口
	private IOaAccidentOperate oaAccidentOperate;
	// 运单相关接口
	private IWaybillOperate waybillOperate;
	// 理赔工作流相关接口
	private IRecompenseApplyOperate recompenseApplyOperate;
	// 付款工作流相关接口
	private IPaymentApplyOperate paymentApplyOperate;
	// 发送短信接口
	private ISmsInfoSender smsInfoSender;
	// 财务共享接口
	private IFINSelfserviceOperate fINSelfserviceOperate;

	private DepartmentService departmentService;

	public ISmsInfoSender getSmsInfoSender() {
		return smsInfoSender;
	}

	public void setSmsInfoSender(ISmsInfoSender smsInfoSender) {
		this.smsInfoSender = smsInfoSender;
	}

	private BankProvinceCityServiceImpl bankProvinceCityService;

	public BankProvinceCityServiceImpl getBankProvinceCityService() {
		return bankProvinceCityService;
	}

	public void setBankProvinceCityService(
			BankProvinceCityServiceImpl bankProvinceCityService) {
		this.bankProvinceCityService = bankProvinceCityService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public FINRecompenserOperateImpl getfINRecompenserOperate() {
		return fINRecompenserOperate;
	}

	public void setfINRecompenserOperate(
			FINRecompenserOperateImpl fINRecompenserOperate) {
		this.fINRecompenserOperate = fINRecompenserOperate;
	}

	public EsbToFossAsyOperateImpl getEsbToFossAsyOperate() {
		return esbToFossAsyOperate;
	}

	public void setEsbToFossAsyOperate(
			EsbToFossAsyOperateImpl esbToFossAsyOperate) {
		this.esbToFossAsyOperate = esbToFossAsyOperate;
	}

	public static RecompenseService get() {
		if (service == null) {
			service = new RecompenseServiceImpl();
		}
		return service;
	}

	/**
	 * @return paymentApplyOperate : return the property paymentApplyOperate.
	 */
	public IPaymentApplyOperate getPaymentApplyOperate() {
		return paymentApplyOperate;
	}

	/**
	 * @param paymentApplyOperate
	 *            : set the property paymentApplyOperate.
	 */
	public void setPaymentApplyOperate(IPaymentApplyOperate paymentApplyOperate) {
		this.paymentApplyOperate = paymentApplyOperate;
	}

	/**
	 * @return recompenseApplyOperate : return the property
	 *         recompenseApplyOperate.
	 */
	public IRecompenseApplyOperate getRecompenseApplyOperate() {
		return recompenseApplyOperate;
	}

	/**
	 * @param recompenseApplyOperate
	 *            : set the property recompenseApplyOperate.
	 */
	public void setRecompenseApplyOperate(
			IRecompenseApplyOperate recompenseApplyOperate) {
		this.recompenseApplyOperate = recompenseApplyOperate;
	}

	/**
	 * @return waybillOperate : return the property waybillOperate.
	 */
	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	/**
	 * @param waybillOperate
	 *            : set the property waybillOperate.
	 */
	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
	}

	/**
	 * @return oaAccidentOperate : return the property oaAccidentOperate.
	 */
	public IOaAccidentOperate getOaAccidentOperate() {
		return oaAccidentOperate;
	}

	/**
	 * @param oaAccidentOperate
	 *            : set the property oaAccidentOperate.
	 */
	public void setOaAccidentOperate(IOaAccidentOperate oaAccidentOperate) {
		this.oaAccidentOperate = oaAccidentOperate;
	}

	private RecompenseServiceImpl() {

	}

	/*
	 * (非 Javadoc) <p>Title: getBaseModelDao</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#getBaseModelDao()
	 */
	public BaseModelDao getBaseModelDao() {
		return baseModelDao;
	}

	/*
	 * (非 Javadoc) <p>Title: setBaseModelDao</p> <p>Description: </p>
	 * 
	 * @param baseModelDao
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#setBaseModelDao(
	 * com.deppon.crm.recompense.dao.BaseModelDao)
	 */
	public void setBaseModelDao(BaseModelDao baseModelDao) {
		this.baseModelDao = baseModelDao;
	}

	/*
	 * (非 Javadoc) <p>Title: getListItemDao</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#getListItemDao()
	 */
	public ListItemDao getListItemDao() {
		return listItemDao;
	}

	/*
	 * (非 Javadoc) <p>Title: setListItemDao</p> <p>Description: </p>
	 * 
	 * @param listItemDao
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#setListItemDao(com
	 * .deppon.crm.recompense.dao.ListItemDao)
	 */
	public void setListItemDao(ListItemDao listItemDao) {
		this.listItemDao = listItemDao;
	}

	/*
	 * (非 Javadoc) <p>Title: setRecompenseDao</p> <p>Description: </p>
	 * 
	 * @param recompenseDao
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#setRecompenseDao
	 * (com.deppon.crm.recompense.dao.RecompenseDao)
	 */
	public void setRecompenseDao(RecompenseDao recompenseDao) {
		this.recompenseDao = recompenseDao;
	}

	/*
	 * (非 Javadoc) <p>Title: getRecompenseDao</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#getRecompenseDao()
	 */
	public RecompenseDao getRecompenseDao() {
		return recompenseDao;
	}

	public INormalClaimDao getNormalClaimDao() {
		return normalClaimDao;
	}

	public void setNormalClaimDao(INormalClaimDao normalClaimDao) {
		this.normalClaimDao = normalClaimDao;
	}

	/**
	 * 
	 * <p>
	 * Description:删除大区设置<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-30
	 * @return RecompenseDao
	 */
	@Override
	public void deleteUserRoleDeptRelationById(String id) {
		baseModelDao.deleteUserRoleDeptRelationById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:获得用户部门<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-30
	 * @return RecompenseDao
	 */
	@Override
	public List<UserRoleDeptRelation> getAllUserRoleDeptRelation(int start,
			int limit) {
		return baseModelDao.getAllUserRoleDeptRelation(start, limit);
	}

	@Override
	public void insertUserRoleDepartment(String userId, String roleId,
			String deptId) {
		List<String> deptIds = new ArrayList<String>();
		deptIds.add(deptId);
		baseModelDao.insertUserRoleDepartment(userId, roleId, deptIds);
	}

	@Override
	public void insertUserRoleDepartment(String userId, String roleId,
			List<String> deptIds) {
		baseModelDao.insertUserRoleDepartment(userId, roleId, deptIds);
	}

	@Override
	public List<UserRoleDeptRelation> getUserRoleDeptRelationByUserId(
			String userId) {
		return baseModelDao.getUserRoleDeptRelationByUserId(userId);
	}

	/*
	 * (非 Javadoc) <p>Title: searchMyRecompenseApp</p> <p>Description: </p>
	 * 
	 * @param roleId
	 * 
	 * @param voucherNo
	 * 
	 * @param custNum
	 * 
	 * @param insurStartTime
	 * 
	 * @param insurEndTime
	 * 
	 * @param recompenseType
	 * 
	 * @param recompenseMethod
	 * 
	 * @param recompenseState
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#searchMyRecompenseApp
	 * (java.lang.String, java.lang.String, java.lang.String, java.util.Date,
	 * java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */

	private List<String> getDeptIdListIdByUserId(String userId) {
		List<UserRoleDeptRelation> relations = baseModelDao
				.getUserRoleDeptRelationByUserId(userId);
		List<String> deptIds = new ArrayList<String>();
		for (UserRoleDeptRelation relation : relations) {
			// regionsId.add(roleDept.getDept().getId());
		}
		return deptIds;
	}

	public List<String> getDeptIdsByUserId(String userId) {
		List<UserRoleDeptRelation> relations = baseModelDao
				.getUserRoleDeptRelationByUserId(userId);
		List<String> deptIds = new ArrayList<String>();
		for (UserRoleDeptRelation relation : relations) {
			deptIds.add(relation.getDept().getId());
		}
		return deptIds;
	}

	/*
	 * (非 Javadoc) <p>Title: searchRecompenseApp</p> <p>Description: </p>
	 * 
	 * @param voucherNo
	 * 
	 * @param custNum
	 * 
	 * @param insurDept
	 * 
	 * @param ownArea
	 * 
	 * @param custName
	 * 
	 * @param modifyStartTime
	 * 
	 * @param modifyEndTime
	 * 
	 * @param insurStartTime
	 * 
	 * @param insurEndTime
	 * 
	 * @param recompenseType
	 * 
	 * @param recompenseMethod
	 * 
	 * @param recompenseState
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#searchRecompenseApp
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.util.Date, java.util.Date, java.util.Date,
	 * java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */

	/*
	 * (非 Javadoc) <p>Title: getCustomerHistoryRecompenseApp</p> <p>Description:
	 * </p>
	 * 
	 * @param customerId
	 * 
	 * @param recompenseId
	 * 
	 * @return
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * getCustomerHistoryRecompenseApp(java.lang.String, java.lang.String)
	 */
	@Override
	public List<RecompenseApplication> getCustomerHistoryRecompenseApp(
			String customerId, String recompenseId, Date startTime, Date endTime) {
		return recompenseDao.getRecompenseApplicationByCustomerId(customerId,
				recompenseId, startTime, endTime);
	}

	/*
	 * (非 Javadoc) <p>Title: getRecompenseApplicationById</p> <p>Description:
	 * </p>
	 * 
	 * @param recompenseId
	 * 
	 * @return
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * getRecompenseApplicationById(java.lang.String)
	 */
	@Override
	public RecompenseApplication getRecompenseApplicationById(
			String recompenseId) {
		return recompenseDao.getRecompenseApplicationById(recompenseId);
	}

	/*
	 * (非 Javadoc) <p>Title: getUnfinishedRecompenseAppByUser</p>
	 * <p>Description: </p>
	 * 
	 * @param roleId
	 * 
	 * @return
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * getUnfinishedRecompenseAppByUser(java.lang.String)
	 */
	@Override
	public List<RecompenseApplication> getUnfinishedRecompenseAppByUser(
			String roleId) {
		// List<String> ownAreas = getDeptIdListIdByUserId(roleId);
		List<String> status = new ArrayList<String>();
		status.add(Constants.STATUS_SUBMITED);
		status.add(Constants.STATUS_DOC_CONFIRMED);
		status.add(Constants.STATUS_HANDLED);
		status.add(Constants.STATUS_AMOUNT_CONFIRMED);
		status.add(Constants.STATUS_OVERPAY_APPROVED);

		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		// condition.setDeptIdList(ownAreas);
		condition.setRecompenseStateList(status);

		return recompenseDao.searchRecompenseByCondition(condition);// getRecompenseAppByStatus(ownAreas,
																	// status);
	}

	/*
	 * (非 Javadoc) <p>Title: deleteRecompenseApplication</p> <p>Description:
	 * </p>
	 * 
	 * @param recompenseId
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * deleteRecompenseApplication(java.lang.String)
	 */
	@Override
	public void deleteRecompenseApplication(String recompenseId) {
		listItemDao.deleteAttachmentByRecompenseId(recompenseId);
		listItemDao.deleteAwardItemByRecompenseId(recompenseId);
		listItemDao.deleteDeptChargeByRecompenseId(recompenseId);
		listItemDao.deleteGoodsTransByRecompenseId(recompenseId);
		listItemDao.deleteIssueItemByRecompenseId(recompenseId);
		listItemDao.deleteMessageByRecompenseId(recompenseId);
		listItemDao.deleteMessageReminderByRecompenseId(recompenseId);
		listItemDao.deleteResponsibleDeptByRecompenseId(recompenseId);
		listItemDao.deletePaymentByRecompenseId(recompenseId);
		listItemDao.deleteRecallCompByRecompenseId(recompenseId);
		listItemDao.deleteOverpayByRecompenseId(recompenseId);
		listItemDao.deleteOAWorkflowByRecompenseId(recompenseId);
		recompenseDao.deleteRecompenseApplicationById(recompenseId);
	}

	/*
	 * (非 Javadoc) <p>Title: insertMessage</p> <p>Description: </p>
	 * 
	 * @param message
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#insertMessage(com
	 * .deppon.crm.recompense.domain.Message)
	 */
	@Override
	public List<Message> insertMessage(Message message) {
		listItemDao.insertMessage(message);
		return listItemDao.getMessageByRecompenseId(message.getRecompenseId());
	}

	/*
	 * (非 Javadoc) <p>Title: createRecompenseApplication</p> <p>Description:
	 * </p>
	 * 
	 * @param app
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * createRecompenseApplication
	 * (com.deppon.crm.recompense.domain.RecompenseApplication)
	 */
	@Override
	public void createRecompenseApplication(RecompenseApplication app) {
		recompenseDao.insertRecompenseApplication(app);
		// setAppIdToChildObjectAndClearId(app);
		listItemDao.insertGoodsTrans(app.getId(), app.getGoodsTransList());
		listItemDao.insertIssueItem(app.getId(), app.getIssueItemList());
		listItemDao.insertRecompenseAttachment(app.getId(),
				app.getAttachmentList());
		listItemDao.insertResponsibleDept(app.getId(),
				app.getResponsibleDeptList());
		listItemDao.insertDeptCharge(app.getId(), app.getDeptChargeList());
	}

	@Override
	public void updateRecompenseReportInfo(RecompenseApplication app,
			Map<String, List<IssueItem>> issueItemModifyMap,
			Map<String, List<GoodsTrans>> goodsTransModifyMap,
			Map<String, List<RecompenseAttachment>> attachmentModifyMap) {
		recompenseDao.updateRecompenseApplication(app);
		listItemDao.deleteResponsibleDeptByRecompenseId(app.getId());
		listItemDao.insertResponsibleDept(app.getId(),
				app.getResponsibleDeptList());
		listItemDao.deleteDeptChargeByRecompenseId(app.getId());
		listItemDao.insertDeptCharge(app.getId(), app.getDeptChargeList());
		// 出险信息
		if (issueItemModifyMap != null) {
			List<IssueItem> issueAdd = issueItemModifyMap
					.get(Constants.LIST_TYPE_ADD);
			if (issueAdd != null && issueAdd.size() > 0) {
				listItemDao.insertIssueItem(app.getId(), issueAdd);
			}
			List<IssueItem> issueDelete = issueItemModifyMap
					.get(Constants.LIST_TYPE_DELETE);
			if (issueDelete != null && issueDelete.size() > 0) {
				listItemDao.deleteIssueItem(issueDelete);
			}
			List<IssueItem> issueUpdate = issueItemModifyMap
					.get(Constants.LIST_TYPE_UPDATE);
			if (issueUpdate != null && issueUpdate.size() > 0) {
				listItemDao.updateIssueItem(issueUpdate);
			}
		}
		// 货物托运清单
		if (goodsTransModifyMap != null) {
			List<GoodsTrans> goodsTransAdd = goodsTransModifyMap
					.get(Constants.LIST_TYPE_ADD);
			if (goodsTransAdd != null && goodsTransAdd.size() > 0) {
				listItemDao.insertGoodsTrans(app.getId(), goodsTransAdd);
			}
			List<GoodsTrans> goodsTransDelete = goodsTransModifyMap
					.get(Constants.LIST_TYPE_DELETE);
			if (goodsTransDelete != null && goodsTransDelete.size() > 0) {
				listItemDao.deleteGoodsTrans(goodsTransDelete);
			}
			List<GoodsTrans> goodsTransUpdate = goodsTransModifyMap
					.get(Constants.LIST_TYPE_UPDATE);
			if (goodsTransUpdate != null && goodsTransUpdate.size() > 0) {
				listItemDao.updateGoodsTrans(goodsTransUpdate);
			}
		}
		// 附件列表
		if (attachmentModifyMap != null) {
			List<RecompenseAttachment> attachmentAdd = attachmentModifyMap
					.get(Constants.LIST_TYPE_ADD);
			if (attachmentAdd != null && attachmentAdd.size() > 0) {
				listItemDao.insertRecompenseAttachment(app.getId(),
						attachmentAdd);
			}
			List<RecompenseAttachment> attachmentDelete = attachmentModifyMap
					.get(Constants.LIST_TYPE_DELETE);
			if (attachmentDelete != null && attachmentDelete.size() > 0) {
				listItemDao.deleteRecompenseAttachment(attachmentDelete);
			}
		}
		// listItemDao.deleteGoodsTransByRecompenseId(view.getId());
		// listItemDao.deleteIssueItemByRecompenseId(view.getId());
		// listItemDao.deleteAttachmentByRecompenseId(view.getId());
		// setAppIdToChildObjectAndClearId(view);
		// listItemDao.insertGoodsTrans(view.getGoodsTransList());
		// listItemDao.insertIssueItem(view.getIssueItemList());
		// listItemDao.insertRecompenseAttachment(view.getAttachmentList());
	}

	@Override
	public void updateRecompenseProcessInfo(RecompenseApplication app,
			Map<String, List<DeptCharge>> deptChargeMap,
			Map<String, List<ResponsibleDept>> responsibleDeptMap,
			Map<String, List<MessageReminder>> messageReminderMap,
			Map<String, List<AwardItem>> awardItemMap) {

		// 2012-3-27之后的代码
		// 0、保存修改的理赔单
		recompenseDao.updateRecompenseApplication(app);

		/**
		 * 1、入部门费用
		 */
		// 新增集合
		if (deptChargeMap != null) {
			List<DeptCharge> deptChargeAddList = deptChargeMap
					.get(Constants.LIST_TYPE_ADD);
			if (deptChargeAddList != null && deptChargeAddList.size() > 0) {
				listItemDao.insertDeptCharge(app.getId(), deptChargeAddList);
			}
			// 修改集合
			List<DeptCharge> deptChargeUpdList = deptChargeMap
					.get(Constants.LIST_TYPE_UPDATE);
			if (deptChargeUpdList != null && deptChargeUpdList.size() > 0) {
				listItemDao.updateDeptChargesById(deptChargeUpdList);
			}
			// 删除集合
			List<DeptCharge> deptChargeDelList = deptChargeMap
					.get(Constants.LIST_TYPE_DELETE);
			if (deptChargeDelList != null && deptChargeDelList.size() > 0) {
				listItemDao.deleteDeptCharge(deptChargeDelList);
			}
		}

		/**
		 * 2、责任部门
		 */
		// 新增集合
		if (responsibleDeptMap != null) {
			List<ResponsibleDept> responsibleDeptAddList = responsibleDeptMap
					.get(Constants.LIST_TYPE_ADD);
			if (responsibleDeptAddList != null
					&& responsibleDeptAddList.size() > 0) {
				listItemDao.insertResponsibleDept(app.getId(),
						responsibleDeptAddList);
			}
			// 修改集合
			List<ResponsibleDept> responsibleDeptUpdList = responsibleDeptMap
					.get(Constants.LIST_TYPE_UPDATE);
			if (responsibleDeptUpdList != null
					&& responsibleDeptUpdList.size() > 0) {
				listItemDao.updateResponsibleDept(responsibleDeptUpdList);
			}
			// 删除集合
			List<ResponsibleDept> responsibleDeptDelList = responsibleDeptMap
					.get(Constants.LIST_TYPE_DELETE);
			if (responsibleDeptDelList != null
					&& responsibleDeptDelList.size() > 0) {
				listItemDao.deleteResponsibleDept(responsibleDeptDelList);
			}
		}

		/**
		 * 3、消息提醒
		 */
		if (messageReminderMap != null) {
			// 新增集合
			List<MessageReminder> messageReminderAddList = messageReminderMap
					.get(Constants.LIST_TYPE_ADD);
			if (messageReminderAddList != null
					&& messageReminderAddList.size() > 0) {
				listItemDao.insertMessageReminder(app.getId(),
						messageReminderAddList);
			}
			// 修改集合
			List<MessageReminder> messageReminderUpdList = messageReminderMap
					.get(Constants.LIST_TYPE_UPDATE);
			if (messageReminderUpdList != null
					&& messageReminderUpdList.size() > 0) {
				listItemDao.updateMessageReminder(messageReminderUpdList);
			}
			// 删除集合
			List<MessageReminder> messageReminderDelList = messageReminderMap
					.get(Constants.LIST_TYPE_DELETE);
			if (messageReminderDelList != null
					&& messageReminderDelList.size() > 0) {
				listItemDao.deleteMessageRemainder(messageReminderDelList);
			}
		}

		/**
		 * 4、奖罚明细
		 */
		if (awardItemMap != null) {
			// 新增集合
			List<AwardItem> awardItemAddList = awardItemMap
					.get(Constants.LIST_TYPE_ADD);
			if (awardItemAddList != null && awardItemAddList.size() > 0) {
				listItemDao.insertAwardItem(app.getId(), awardItemAddList);
			}
			// 修改集合
			List<AwardItem> awardItemUpdList = awardItemMap
					.get(Constants.LIST_TYPE_UPDATE);
			if (awardItemUpdList != null && awardItemUpdList.size() > 0) {
				listItemDao.updateAwardItem(awardItemUpdList);
			}
			// 删除集合
			List<AwardItem> awardItemDelList = awardItemMap
					.get(Constants.LIST_TYPE_DELETE);
			if (awardItemDelList != null && awardItemDelList.size() > 0) {
				listItemDao.deleteAwardItem(awardItemDelList);
			}
		}

		/**
		 * 5、追偿信息
		 */
		RecalledCompensation recalledCom = app.getRecalledCom();
		if (recalledCom != null) {
			recalledCom.setRecompenseId(app.getId());
			List<RecalledCompensation> rcl = listItemDao
					.getRecalledCompensationByRecompenseId(app.getId());
			// String recalledComId = recalledCom.getId();
			if (rcl != null && rcl.size() > 0) {
				recalledCom.setId(rcl.get(0).getId());
				listItemDao.updateRecalledCompensation(recalledCom);
			} else {
				listItemDao.insertRecalledCompensation(recalledCom);
			}
		}

	}

	@Override
	public void updateRecompensePaymentInfo(RecompenseApplication app) {
		PaymentBill paymentBill = app.getPaymentBill();
		paymentBill.setRecompenseId(app.getId());
		List<PaymentBill> payList = listItemDao
				.getPaymentBillByRecompenseId(app.getId());
		if (payList != null && payList.size() > 0) {
			paymentBill.setId(payList.get(0).getId());
			listItemDao.updatePaymentBill(paymentBill);
		} else {
			listItemDao.insertPaymentBill(app.getPaymentBill());
		}
	}

	@Override
	public void updateRecompenseBalanceInfo(RecompenseApplication app) {
		List<Balance> list = app.getBalanceList();
		if (list != null && list.size() > 0) {
			this.listItemDao.insertBalance(list);
		}
	}

	/*
	 * (非 Javadoc) <p>Title: updateRecompenseExemptInfo</p> <p>Description: </p>
	 * 
	 * @param app
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * updateRecompenseExemptInfo
	 * (com.deppon.crm.recompense.domain.RecompenseApplication)
	 */
	@Override
	public void updateRecompenseExemptInfo(RecompenseApplication app) {
		List<DeptCharge> deptCharges = app.getDeptChargeList();
		recompenseDao.updateRecompenseApplication(app);
		listItemDao.updateDeptChargesById(deptCharges);
	}

	/*
	 * (非 Javadoc) <p>Title: updateRecompenseStatusInfo</p> <p>Description: </p>
	 * 
	 * @param appId
	 * 
	 * @param status
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * updateRecompenseStatusInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateRecompenseStatusInfo(RecompenseApplication app) {
		recompenseDao.updateRecompenseStatusInfo(app);
	}

	/*
	 * (非 Javadoc) <p>Title: getDeptByWayBillNum</p> <p>Description: </p>
	 * 
	 * @param number
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#getDeptByWayBillNum
	 * (java.lang.String)
	 */
	@Override
	public Map<String, Department> getDeptByWayBillNum(String number) {
		Map<String, Department> map = new HashMap();
		Department leaveDept = new Department();
		leaveDept.setId("123");
		Department reportDept = new Department();
		reportDept.setId("456");
		map.put("leaveDept", leaveDept);
		map.put("reportDept", reportDept);
		return map;
	}

	/*
	 * (非 Javadoc) <p>Title: getRecompenseApplicationByVoucherNo</p>
	 * <p>Description: </p>
	 * 
	 * @param voucherNo
	 * 
	 * @return
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * getRecompenseApplicationByVoucherNo(java.lang.String)
	 */
	@Override
	public RecompenseApplication getRecompenseApplicationByVoucherNo(
			String voucherNo) {
		List<RecompenseApplication> list = recompenseDao
				.searchRecompenseByWaybillNum(voucherNo);// getRecompenseApplicationByVoucherNo(voucherNo);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * (非 Javadoc) <p>Title: getWaybillByVoucherNoAndRecomType</p>
	 * <p>Description: </p>
	 * 
	 * @param voucherNo
	 * 
	 * @param recompenseMethod
	 * 
	 * @return
	 * 
	 * @throws ValidateException
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * getWaybillByVoucherNoAndRecomType(java.lang.String, java.lang.String)
	 */
	// @Override
	// public Waybill getWaybillByVoucherNoAndRecomType(String voucherNo,
	// String recompenseMethod) {
	// if (Constants.UNBILLED.equals(recompenseMethod)) {
	// return oaClient.getUnbilledWaybill(voucherNo);
	// }
	// return oaClient.getWaybillByNum(voucherNo);
	// }

	// *******************************OAclient接口封装*************************

	/*
	 * (非 Javadoc) <p>Title: getWaybillAbnormalSignState</p> <p>Description:
	 * </p>
	 * 
	 * @param voucherNo
	 * 
	 * @return
	 * 
	 * @see com.deppon.crm.recompense.service.IRecompenseService#
	 * getWaybillAbnormalSignState(java.lang.String)
	 */
	// @Override
	// public boolean getWaybillAbnormalSignState(String voucherNo) {
	// return oaClient.getWaybillAbnormalSignState(voucherNo);
	// }

	/*
	 * (非 Javadoc) <p>Title: getAbnormalSignState</p> <p>Description: </p>
	 * 
	 * @param voucherNo
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#getAbnormalSignState
	 * (java.lang.String)
	 */
	// @Override
	// public boolean getAbnormalSignState(String voucherNo) {
	// return oaClient.getAbnormalSignState(voucherNo);
	// }

	/*
	 * (非 Javadoc) <p>Title: getLostGoodsState</p> <p>Description: </p>
	 * 
	 * @param voucherNo
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#getLostGoodsState
	 * (java.lang.String)
	 */
	// @Override
	// public boolean getLostGoodsState(String voucherNo) {
	// return oaClient.getLostGoodsState(voucherNo);
	// }

	/*
	 * (非 Javadoc) <p>Title: getUnbilledState</p> <p>Description: </p>
	 * 
	 * @param voucherNo
	 * 
	 * @return
	 * 
	 * @see
	 * com.deppon.crm.recompense.service.IRecompenseService#getUnbilledState
	 * (java.lang.String)
	 */
	// @Override
	// public boolean getUnbilledState(String voucherNo) {
	// return oaClient.getUnbilledState(voucherNo);
	// }

	// ******************************工具方法*******************************

	// private void setAppIdToChildObjectAndClearId(RecompenseApplication app) {
	// if (app == null || app.getId() == null)
	// return;
	// String recompenseId = app.getId();
	// setRecompenseIdToListObejct(app.getAttachmentList(), recompenseId);
	// setRecompenseIdToListObejct(app.getAwardItemList(), recompenseId);
	// setRecompenseIdToListObejct(app.getDeptChargeList(), recompenseId);
	// setRecompenseIdToListObejct(app.getGoodsTransList(), recompenseId);
	// setRecompenseIdToListObejct(app.getIssueItemList(), recompenseId);
	// setRecompenseIdToListObejct(app.getMessageList(), recompenseId);
	// setRecompenseIdToListObejct(app.getMessageReminderList(), recompenseId);
	// setRecompenseIdToListObejct(app.getResponsibleDeptList(), recompenseId);
	// setRecompenseIdToObject(app.getPaymentBill(), recompenseId);
	// setRecompenseIdToObject(app.getRecalledCom(), recompenseId);
	//
	// }

	// private void setRecompenseIdToObject(Object object, String recompenseId)
	// {
	// if (object == null)
	// return;
	// if (object instanceof PaymentBill) {
	// PaymentBill pb = (PaymentBill) object;
	// pb.setRecompenseId(recompenseId);
	// pb.setId(null);
	// } else if (object instanceof RecalledCompensation) {
	// RecalledCompensation pb = (RecalledCompensation) object;
	// pb.setRecompenseId(recompenseId);
	// pb.setId(null);
	// }
	// }

	// private void setRecompenseIdToListObejct(List list, String recompenseId)
	// {
	// if (list == null || list.size() == 0)
	// return;
	// for (Object object : list) {
	// if (object instanceof RecompenseAttachment) {
	// RecompenseAttachment ra = (RecompenseAttachment) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof AwardItem) {
	// AwardItem ra = (AwardItem) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof DeptCharge) {
	// DeptCharge ra = (DeptCharge) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof IssueItem) {
	// IssueItem ra = (IssueItem) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof GoodsTrans) {
	// GoodsTrans ra = (GoodsTrans) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof RecompenseItem) {
	// RecompenseItem ra = (RecompenseItem) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof ResponsibleDept) {
	// ResponsibleDept ra = (ResponsibleDept) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof MessageReminder) {
	// MessageReminder ra = (MessageReminder) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// } else if (object instanceof Message) {
	// Message ra = (Message) object;
	// ra.setRecompenseId(recompenseId);
	// ra.setId(null);
	// }
	// }
	// }
	private NormalClaim ConvertRecToClaim(NormalRecompenseInfo nri,
			String workflowNum, String areaCode, String currentStandardCode) {

		NormalClaim normalClaim = new NormalClaim();
		// 工作流编号
		normalClaim.setProcessinstId(workflowNum);
		// 申请人工号
		normalClaim.setApplyPersonCode(nri.getApplyPersonCode());
		// 申请人所在部门标杆编码
		normalClaim.setStandardCode(currentStandardCode);
		// 线索工号,报案人经理工号
		normalClaim.setClueUserId(nri.getClueUserId());
		normalClaim.setClueUserId(nri.getClueUserId());
		// 运单号/差错编号
		normalClaim.setTransportOrErrorCode(nri.getTransportOrErrorCode());
		// 保价人/发货联系人
		normalClaim.setInsuredUnits(nri.getInsuredUnits());
		// 联系电话
		normalClaim.setContactPhone(nri.getContactPhone());
		// 运输类型
		normalClaim.setHaulType(nri.getHaulType());
		// 收货部门（名称）
		normalClaim.setReceivingDept(nri.getReceivingDept());
		// 始发站
		normalClaim.setStartingStation(nri.getStartingStation());
		// 货物名称
		normalClaim.setGoodsName(nri.getGoodsName());
		// 货物属性：件/重/体
		normalClaim.setGoodsAttribute(nri.getGoodsAttribute());
		// 保险金额
		normalClaim.setInsuredAmount(nri.getInsuredAmount());
		// 目标部门，到达部门
		normalClaim.setTargetDept(nri.getTargetDept());
		// 发货日期
		normalClaim.setSendingDate(nri.getSendingDate());
		// 出险日期
		normalClaim.setDangerDate(nri.getDangerDate());
		// 所属区域(名称)
		normalClaim.setArea(nri.getArea());
		// 理赔类型(名称)
		normalClaim.setClaimsType(nri.getClaimsType());
		// 冲账方式
		normalClaim.setOffsetType(nri.getOffsetTypt());
		// 报案人(名称)
		normalClaim.setCaseReporter(nri.getCaseReporter());
		// 报案部门(名称)
		normalClaim.setReportDept(nri.getReportDept());
		// 报案日期
		normalClaim.setReportDate(nri.getReportDate());
		// 处理人(名称)
		normalClaim.setHandler(nri.getHandler());
		// 处理日期
		normalClaim.setHandleDate(nri.getHandleDate());
		// 其他费用说明
		normalClaim.setOtherCost(nri.getOtherCost());
		// 索赔金额
		normalClaim.setClaimAmount(nri.getClaimAmount());
		// 责任部门/大区（如果有多个则进行组装：重庆大区,江门大区）
		normalClaim.setResponsibileDept(nri.getResponsibileDept());
		// 正常理赔金额
		normalClaim.setNormalAmount(nri.getNormalAmount());
		// 实际理赔金额
		normalClaim.setActualClaimsAmount(nri.getActualClaimsAmount());
		// 入公司费用
		normalClaim.setToCompanyCost(nri.getTocompanyCost());
		// 大区标杆编码
		normalClaim.setAreaCode(areaCode);
		return normalClaim;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean submitRecompenseOaApproval(RecompenseApplication recompense,
			User commiter, String reportManCode, String applyManCode,
			String currentStandardCode) {
		try {
			NormalRecompenseInfo nri = transNormalRecompenseInfo(recompense,
					reportManCode, applyManCode);
			String workflowNum;
			workflowNum = recompenseApplyOperate.applyNormalRecompense(nri);
			String standardCode = departmentService.getDepartmentById(
					recompense.getDeptId()).getStandardCode();
			NormalClaim normalClaim = this.ConvertRecToClaim(nri, workflowNum,
					standardCode, currentStandardCode);
			normalClaimDao.insertNormalClaim(normalClaim);
			// String workflowNum = "OA_TEST_" +
			// recompense.getWorkflowId().toString();
			OAWorkflow flow = new OAWorkflow();
			// flow.setAuditDate(new Date());
			// flow.setAuditopinion("Testing");
			flow.setCommitDate(new Date());
			flow.setCommiter(commiter);
			flow.setRecompenseId(recompense.getId());
			flow.setWorkflowType(Constants.WORKFLOW_TYPE_NORMAL);
			flow.setWorkflowStatus(Constants.WORKFLOW_STATUS_SUBMIT);
			flow.setWorkflowNum(workflowNum);
			if (flow != null) {
				listItemDao.insertWorkflow(flow);
			}
			return true;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	private NormalRecompenseInfo transNormalRecompenseInfo(
			RecompenseApplication app, String reportManCode, String applyManCode) {
		Waybill waybill = app.getWaybill();
		NormalRecompenseInfo nri = new NormalRecompenseInfo();
		// 申请人工号
		nri.setApplyPersonCode(applyManCode);
		// 线索工号,报案人经理工号
		nri.setClueUserId(reportManCode);
		// 运单号/差错编号
		nri.setTransportOrErrorCode(waybill.getWaybillNumber());
		// 保价人/发货联系人
		nri.setInsuredUnits(waybill.getInsured());
		// 联系电话
		nri.setContactPhone(waybill.getTelephone());
		// 运输类型
		nri.setHaulType(waybill.getTransType());
		// 收货部门（名称）
		nri.setReceivingDept(waybill.getReceiveDept());
		// 始发站
		nri.setStartingStation(waybill.getStartStation());
		// 货物名称
		nri.setGoodsName(waybill.getGoodsName());
		// 货物属性：件/重/体
		nri.setGoodsAttribute(waybill.getPwv());
		// 保险金额
		nri.setInsuredAmount(waybill.getInsurAmount());
		// 目标部门，到达部门
		nri.setTargetDept(waybill.getEndStation());
		// 发货日期
		nri.setSendingDate(waybill.getSendDate());
		// 出险日期
		nri.setDangerDate(app.getInsurDate());
		// 所属区域(名称)
		nri.setArea(app.getDeptName());
		// 理赔类型(名称)
		String recompenseType = DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.RECOMPENSE_TYPE, app.getRecompenseType());
		nri.setClaimsType(recompenseType);
		// 冲账方式
		nri.setOffsetTypt("");
		// 报案人(名称)
		nri.setCaseReporter(app.getReportManName());
		// 报案部门(名称)
		nri.setReportDept(app.getReportDeptName());
		// 报案日期
		nri.setReportDate(app.getReportDate());
		// 处理人(名称)
		nri.setHandler(app.getModifyUserName());
		// 处理日期
		nri.setHandleDate(app.getModifyDate());
		// 其他费用说明
		nri.setOtherCost(app.getCostExplain());
		// 索赔金额
		nri.setClaimAmount(app.getRecompenseAmount());
		// 责任部门/大区（如果有多个则进行组装：重庆大区,江门大区）
		List<ResponsibleDept> rdLst = app.getResponsibleDeptList();
		String rdStr = "";
		for (ResponsibleDept responsibleDept : rdLst) {
			rdStr = rdStr + responsibleDept.getDeptName() + ",";
		}
		nri.setResponsibileDept(rdStr);
		// 正常理赔金额
		nri.setNormalAmount(app.getNormalAmount());
		// 实际理赔金额
		nri.setActualClaimsAmount(app.getRealAmount());
		// 入公司费用
		nri.setTocompanyCost(0);
		// 出险信息
		List<AccidentDescription> accidentDescriptionInfos = new ArrayList<NormalRecompenseInfo.AccidentDescription>();
		List<IssueItem> iiLst = app.getIssueItemList();
		for (IssueItem issueItem : iiLst) {
			AccidentDescription ad = new AccidentDescription();
			ad.setAccidentAcount(issueItem.getQuality());
			String insurType = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.DANGER_TYPE, issueItem.getInsurType());
			ad.setAccidentType(insurType);
			ad.setDescription(issueItem.getDescription());
			accidentDescriptionInfos.add(ad);
		}
		nri.setAccidentDescriptionInfos(accidentDescriptionInfos);

		// 入部门费用
		List<IndeptCharges> indeptCharges = new ArrayList<NormalRecompenseInfo.IndeptCharges>();
		List<DeptCharge> dcLst = app.getDeptChargeList();
		for (DeptCharge deptCharge : dcLst) {
			IndeptCharges idc = new IndeptCharges();
			idc.setCharges(deptCharge.getAmount());
			idc.setDept(deptCharge.getDeptName());
			indeptCharges.add(idc);
		}
		nri.setIndeptCharges(indeptCharges);

		// 奖罚明细列表
		List<RewardPunishment> rewardPunishments = new ArrayList<NormalRecompenseInfo.RewardPunishment>();
		List<AwardItem> aiLst = app.getAwardItemList();
		for (AwardItem awardItem : aiLst) {
			RewardPunishment rp = new RewardPunishment();
			rp.setDisposeTarget(awardItem.getDeptName());
			rp.setMoney(awardItem.getAmount());
			String awardTargetType = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.AWARD_TARGET_TYPE,
					awardItem.getAwardTargetType());
			rp.setProcessType(awardTargetType);
			String awardType = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.AWARD_TYPE, awardItem.getAwardType());
			rp.setRewardPunishmentType(awardType);
		}
		nri.setRewardPunishments(rewardPunishments);

		return nri;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean submitRecompenseOverpayApproval(
			RecompenseApplication recompense, User commiter,
			String reportManCode, String reportManName) {
		try {
			MuchRecompenseInfo mri = transMuchRecompenseInfo(recompense,
					reportManCode, reportManName);
			String workflowNum = recompenseApplyOperate
					.applyMuchRecompense(mri);
			// 临时调用
			// String workflowNum = "OVERPAY_TEST_"
			// + recompense.getWorkflowId().toString();
			OAWorkflow flow = new OAWorkflow();
			flow.setCommitDate(new Date());
			flow.setCommiter(commiter);
			flow.setRecompenseId(recompense.getId());
			flow.setWorkflowType(Constants.WORKFLOW_TYPE_OVERPAY);
			flow.setWorkflowStatus(Constants.WORKFLOW_STATUS_SUBMIT);
			flow.setWorkflowNum(workflowNum);
			if (flow != null) {
				Overpay overpay = recompense.getOverpay();
				overpay.setRecompenseId(recompense.getId());
				overpay.setWorkNumber(workflowNum);
				listItemDao.insertOverpay(recompense.getOverpay());
				listItemDao.insertWorkflow(flow);
			}
			return true;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

	}

	private MuchRecompenseInfo transMuchRecompenseInfo(
			RecompenseApplication recompense, String reportManCode,
			String reportManName) {
		Overpay overpay = recompense.getOverpay();
		MuchRecompenseInfo mci = new MuchRecompenseInfo();
		// 申请人姓名
		mci.setApplyPersonName(reportManName);
		// 申请工号
		mci.setApplyPersonCode(reportManCode);
		// 多陪单号
		mci.setTransportOrErrorCode(recompense.getWaybill().getWaybillNumber());
		// 多赔金额
		mci.setRecompensiesMoney(overpay.getOverpayAmount());
		// 合计多陪总金额(多赔金额+理赔处理金额)
		mci.setAmountinTotal(overpay.getTotalAmount());

		// 应收账款是否收回
		mci.setHasRepayDebt(overpay.isRecoverYszk());
		// 部门会计(名称)
		mci.setDeptAccountant(overpay.getDeptAccount().getEmpCode()
				.getEmpCode());
		// 申请事由
		mci.setApplyReason(overpay.getOverpayReason());
		// 所属事业部(需要标杆编码)
		mci.setEnterpriseDept(overpay.getOverpayBu().getStandardCode());// ("W0113010301");//
		return mci;
	}

	@Override
	public boolean submitRecompensePaymentApproval(RecompenseApplication app,
			String[] paymentDetail, String[] deptChargeDetail) {
		// 临时调用
		try {
			String info = paymentApplyOperate.submitPaymentInfo(
					Arrays.asList(paymentDetail),
					Arrays.asList(deptChargeDetail));
			if (null != info
					&& !("success".equals(info) || "repeat".equals(info))) {
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.RECOMPENSE_INTERFACE);
				throw new GeneralException(info, info, re, new Object[] {}) {
				};
			}
			// String workflowNum = "";
			// if (app.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
			// OnlineApply onlineApply = listItemDao
			// .getOnlineApplyByRecompenseId(app.getId());
			// PaymentInfo paymentInfo = transPaymentInfo(app, onlineApply,
			// applyManCode);
			// workflowNum = paymentApplyOperate
			// .paymentOnlineApply(paymentInfo);
			// workflowNum = "PAYMENT_TEST_" + app.getWorkflowId().toString();
			// } else {
			// workflowNum = "PAYMENT_TEST_" + app.getWorkflowId().toString();
			// }
			// OAWorkflow flow = new OAWorkflow();
			// flow.setCommitDate(new Date());
			// flow.setCommiter(commiter);
			// flow.setRecompenseId(app.getId());
			// flow.setWorkflowType(Constants.WORKFLOW_TYPE_PAYMENT);
			// flow.setWorkflowStatus(Constants.WORKFLOW_STATUS_SUBMIT);
			// flow.setWorkflowNum(workflowNum);
			// if (flow != null) {
			// listItemDao.insertWorkflow(flow);
			// }
			return true;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	private PaymentInfo transPaymentInfo(RecompenseApplication app,
			OnlineApply onlineApply, String applyManCode) {
		Waybill waybill = app.getWaybill();
		PaymentBill paymentBill = app.getPaymentBill();
		BankAccount bankAccount = paymentBill.getBankAccount();
		PaymentInfo paymentInfo = new PaymentInfo();
		// 申請人工号(如果是在线理赔需要的是经理工号)
		paymentInfo.setApplyPersonNumber(applyManCode);
		// 收款人姓名
		paymentInfo.setPayee(bankAccount.getBankName());
		// 收款人手机号
		paymentInfo.setPayeeMobilePhone(bankAccount.getMobile());
		// 省份标识
		paymentInfo.setProvince(bankAccount.getProvince());
		// 城市标识
		paymentInfo.setCity(bankAccount.getCity());
		// 开户银行名称
		paymentInfo.setBank(bankAccount.getOpenName());
		// 支行名称
		paymentInfo.setSubbranch(bankAccount.getBankName());
		// 账号
		paymentInfo.setAccountNumber(bankAccount.getAccount());
		// 总金金额
		paymentInfo.setAmountMoney(app.getRealAmount());
		// 支付(领款)方式
		paymentInfo.setPayWay(paymentBill.getPaymentType());
		// 单号
		paymentInfo.setWaybillNumber(waybill.getWaybillNumber());
		// 差错编号
		paymentInfo.setErrorNumber("");
		// -----------------在线理赔付款多加的信息
		if (app.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
			// 甲方
			paymentInfo.setPartA(onlineApply.getPartA());
			// 乙方
			paymentInfo.setPartB(onlineApply.getPartB());
			// 发货日期
			paymentInfo.setShipmentsDate(onlineApply.getSendDate());
			// 出发站
			paymentInfo.setStartStation(onlineApply.getStartStation());
			// 目的站
			paymentInfo.setDestination(onlineApply.getEndStation());
			// 赔款金额
			paymentInfo.setRecompenseMoney(onlineApply.getRecompenseAmount());
			// 赔款金额大写
			paymentInfo.setRecompenseMoneyText(onlineApply
					.getRecompenseAmount().toString());
			// 开户名
			paymentInfo.setAccountName(onlineApply.getOpenName());
			// 甲方签字
			paymentInfo.setPartAsign(onlineApply.getPartAsign());
			// 甲方签字日期
			paymentInfo.setPartAsignDate(onlineApply.getPartAsignDate());
			// 乙方签字
			paymentInfo.setPartBAsign(onlineApply.getPartBAsign());
			// 乙方签字日期
			paymentInfo.setPartBAsignDate(onlineApply.getPartBAsignDate());
			// 身份证
			paymentInfo.setIdentityCard(onlineApply.getIdCard());
		}
		return paymentInfo;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * insertOnlineApply
	 * (com.deppon.crm.module.recompense.shared.domain.OnlineApply)
	 */
	@Override
	public boolean insertOnlineApply(OnlineApply onlineApply) {
		return this.listItemDao.insertOnlineApply(onlineApply);
	}

	@Override
	public int getRecompenseCountByCondition(RecompenseSearchCondition condition) {
		int countNum = 0;
		// 运单号
		String waybillNum = condition.getWaybillNum();
		// 客户编号
		String custNum = condition.getCustNum();

		List<RecompenseApplication> resultList = new ArrayList<RecompenseApplication>();
		if (waybillNum != null && !"".equals(waybillNum)) {
			resultList = this.recompenseDao
					.searchRecompenseByWaybillNum(waybillNum);
			countNum = resultList.size();
		} else if (custNum != null && !"".equals(custNum)) {
			countNum = this.recompenseDao
					.getRecompenseCountByCustNum(condition);
		} else {
			countNum = this.recompenseDao
					.getRecompenseCountByCondition(condition);
		}
		return countNum;
	}

	@Override
	public List<RecompenseApplication> searchRecompenseByCondition(
			RecompenseSearchCondition condition) {
		// 运单号
		String waybillNum = condition.getWaybillNum();
		// 客户编号
		String custNum = condition.getCustNum();

		List<RecompenseApplication> resultList = new ArrayList<RecompenseApplication>();
		if (waybillNum != null && !"".equals(waybillNum)) {
			resultList = this.recompenseDao
					.searchRecompenseByWaybillNum(waybillNum);
		} else if (custNum != null && !"".equals(custNum)) {
			resultList = this.recompenseDao
					.searchRecompensePageByCustNum(condition);
		} else {
			resultList = this.recompenseDao
					.searchRecompenseByCondition(condition);
		}
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getMemberById(java.lang.String)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * searchOnlineApplyByWaybillNum(java.lang.String)
	 */
	@Override
	public List<OnlineApply> searchOnlineApplyByWaybillNum(String waybillNum) {
		return this.listItemDao.getOnlineApplyByWaybillNum(waybillNum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * updateOnlineApply
	 * (com.deppon.crm.module.recompense.shared.domain.OnlineApply)
	 */
	@Override
	public boolean updateOnlineApply(OnlineApply onlineApply) {
		return this.listItemDao.updateOnlineApply(onlineApply);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getOnlineApplyById(java.lang.String)
	 */
	@Override
	public OnlineApply getOnlineApplyById(String id) {
		return this.listItemDao.getOnlineApplyById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getOnlineApplyByCondition(java.util.Map)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<OnlineApply> searchOnlineApplyByCondition(String deptId,
			List<String> statusList, int start, int limit) {
		return this.listItemDao.searchOnlineApplyByCondition(deptId,
				statusList, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getOnlineApplyCountByCondition(java.util.Map)
	 */
	@Override
	public Long getOnlineApplyCountByCondition(String deptId,
			List<String> statusList) {
		return this.listItemDao.getOnlineApplyCountByCondition(deptId,
				statusList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * searchBalanceByCondition(java.util.Map)
	 */
	@Override
	public List<Balance> searchBalanceByCondition(Map<String, String> map) {
		String start = map.get("start");
		if (start == null || "".equals(start)) {
			map.put("start", "0");
		}
		String limit = map.get("limit");
		if (limit == null || "".equals(limit) || limit == "0") {
			map.put("limit", "10");
		}
		return this.listItemDao.searchBalanceByCondition(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getBalanceAmountCountByRecompenseId(java.lang.String)
	 */
	// @Override
	// public Double getBalanceAmountCountByRecompenseId(String recompenseId) {
	// return this.getBalanceAmountCountByRecompenseId(recompenseId);
	// }

	@Override
	public OAWorkflow getWorkflowByWorkflowNum(String WorkflowNum) {
		return listItemDao.getWorkflowByWorkflowNum(WorkflowNum);
	}

	@Override
	public void updateWorkflow(OAWorkflow oaWorkflow) {
		listItemDao.updateWorkflow(oaWorkflow);
	}

	@Override
	public boolean updateRecompense(RecompenseApplication recompense) {
		return this.recompenseDao.updateRecompenseApplication(recompense);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * queryAccidentByWaybillNum(java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Override
	public List<OaAccidentInfo> getAccidentByWaybillNum(String waybillNum) {
		try {
			return this.oaAccidentOperate
					.queryAccidentByWaybillCode(waybillNum);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getWaybillByNum(java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Override
	public FossWaybillInfo getWaybillRecompense(String waybillNum) {
		FossWaybillInfo waybillInfo = new FossWaybillInfo();
		try {
			waybillInfo = waybillOperate.queryWaybillInfo(waybillNum);

			return waybillInfo;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getAccidentByAccidentCode(java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Override
	public OaAccidentInfo getAccidentByAccidentCode(String errorCode) {
		try {
			return this.oaAccidentOperate
					.queryAccidentByAccidentCode(errorCode);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * applyNormalRecompense
	 * (com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo)
	 */
	@SuppressWarnings("serial")
	@Override
	public String applyNormalRecompense(
			NormalRecompenseInfo normalRecompenseInfo) {
		try {
			return this.recompenseApplyOperate
					.applyNormalRecompense(normalRecompenseInfo);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * applyMuchRecompense
	 * (com.deppon.crm.module.client.workflow.domain.MuchRecompenseInfo)
	 */
	@SuppressWarnings("serial")
	@Override
	public String applyMuchRecompense(MuchRecompenseInfo muchRecompenseInfo) {
		try {
			return this.recompenseApplyOperate
					.applyMuchRecompense(muchRecompenseInfo);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * paymentApply(com.deppon.crm.module.client.workflow.domain.PaymentInfo)
	 */
	@SuppressWarnings("serial")
	@Override
	public String paymentApply(PaymentInfo paymentInfo) {
		try {
			return this.paymentApplyOperate.paymentApply(paymentInfo);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * queryAccount(java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Override
	public AccountInfo queryAccount(String jobNumber) {
		try {
			return this.paymentApplyOperate.queryAccount(jobNumber);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * paymentOnlineApply
	 * (com.deppon.crm.module.client.workflow.domain.PaymentInfo)
	 */
	@SuppressWarnings("serial")
	@Override
	public String paymentOnlineApply(PaymentInfo paymentInfo) {
		try {
			return this.paymentApplyOperate.paymentOnlineApply(paymentInfo);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.recompense.server.service.RecompenseService#sendMsg
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Override
	public void sendSms(String phones, String content) {
		// if (phones != null) {
		// try {
		// // phones, content, null
		// SmsInfo smsInfo = new SmsInfo();
		// smsInfo.setMobile(phones);
		// smsInfo.setMsgContent(content);
		// this.smsSender.sendSms(smsInfo);
		// SmsInformation smsInfo = new SmsInformation();
		// smsInfo.setMobile(phones);
		// smsInfo.setMsgContent(content);
		// this.getSmsInfoSender().sendSms(smsInfo);
		// } catch (CrmBusinessException e) {
		// throw new GeneralException(e.getMessage(), e.getMessage(), e,
		// new Object[] {}) {
		// };
		// } catch (Exception e) {
		// RecompenseException re = new RecompenseException(
		// RecompenseExceptionType.RECOMPENSE_INTERFACE);
		// throw new GeneralException(re.getErrorCode(), re.getMessage(),
		// re, new Object[] {}) {
		// };
		// }
		// }
	}

	/**
	 * 
	 * @Description: 发送新短信
	 * @author huangzhanming
	 * @param phones
	 * @param content
	 * @param sender
	 * @param deptCode
	 * @return void
	 * @date 2012-11-9 下午1:49:43
	 * @version V1.0
	 */
	@Override
	public void sendSmsInfo(String phones, String content, String sender,
			String deptCode) {
		if (phones != null) {
			try {
				SmsInformation smsInfo = new SmsInformation();
				smsInfo.setMsgType(Constant.SMS_RECOMPENSE_CODE);
				smsInfo.setMobile(phones);
				smsInfo.setMsgContent(content);
				smsInfo.setSender(sender);
				smsInfo.setSendDept(deptCode);
				smsInfoSender.sendSms(smsInfo);
			} catch (CrmBusinessException e) {
				throw new GeneralException(e.getMessage(), e.getMessage(), e,
						new Object[] {}) {
				};
			} catch (Exception e) {
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.RECOMPENSE_INTERFACE);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.recompense.server.service.RecompenseService#sendMail
	 * ()
	 */
	@Override
	public void sendInwardMail(String empNos, String content) {
		// TODO Auto-generated method stub

	}

	@Override
	public Overpay getOverpayByWorkflowNum(String workflowNum,
			String recompenseId) {
		return listItemDao.getOverpayByWorkflowNum(workflowNum, recompenseId);
	}

	@Override
	public OAWorkflow getWorkflowByAppIdAndType(String appId, String type) {
		return this.listItemDao.getWorkflowByAppIdAndType(appId, type);
	}

	@Override
	public void updateOnlineApplyStatusByRecompenseId(String recompenseId,
			String status, String rejectReason) {
		listItemDao.updateOnlineApplyStatusByRecompenseId(recompenseId, status,
				rejectReason);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * updateRecompenseOverpayById(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateRecompenseOverpayById(String recompenseId,
			String overPayId) {
		return this.recompenseDao.updateRecompenseOverpayById(recompenseId,
				overPayId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getOnlineApplyByRecompenseId(java.lang.String)
	 */
	@Override
	public OnlineApply getOnlineApplyByRecompenseId(String recompenseId) {
		return this.listItemDao.getOnlineApplyByRecompenseId(recompenseId);
	}

	@Override
	public List<UserRoleDeptRelation> getUserRoleDeptRelationByDeptId(
			String deptId) {
		return this.baseModelDao.getUserRoleDeptRelationByDeptId(deptId);
	}

	@Override
	public List<Message> getMessageByRecompenseId(String recompenseId) {
		return listItemDao.getMessageByRecompenseId(recompenseId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.recompense.server.service.RecompenseService#
	 * getRecompenseListByCustId(java.lang.String)
	 */
	@Override
	public List<RecompenseApplication> getRecompenseListByCustId(String custId) {
		return this.recompenseDao.getRecompenseListByCustId(custId);
	}

	@Override
	public List<OnlineApply> getOnlineApplyByOnlineUser(String onlineUser,
			String waybillNum) {
		return listItemDao.getOnlineApplyByOnlineUser(onlineUser, waybillNum);
	}

	@Override
	public List<OnlineApply> getOnlineApplyByInterCondition(String onlineUser,
			String waybillNum, Date startTime, Date endTime, int start,
			int limit) {
		return listItemDao.getOnlineApplyByInterCondition(onlineUser,
				waybillNum, startTime, endTime, start, limit);
	}

	@Override
	public int getOnlineApplyByInterConditionCount(String onlineUser,
			String waybillNum, Date startTime, Date endTime) {
		return listItemDao.getOnlineApplyByInterConditionCount(onlineUser,
				waybillNum, startTime, endTime);
	}

	@Override
	public List<OAWorkflow> getOaWorkflowByRecompenseId(String recompenseId) {
		return listItemDao.getWorkflowByRecompenseId(recompenseId);
	}

	@Override
	public List<TodoReminder> generateTodoReminder(String status) {
		return recompenseDao.generateTodoReminder(status);
	}

	public List<DepClaimsBill> getRecompenseInPayment(List<String> appNumList) {
		try {
			List<DepClaimsBill> list = new ArrayList<DepClaimsBill>();
			if (appNumList != null && appNumList.size() > 0) {
				list = paymentApplyOperate.queryDepClaimsByNumList(appNumList);
			}
			return list;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

	}

	public List<DepClaimsBill> getRecompensePaymented(Date startDate,
			Date endDate) {
		try {
			return paymentApplyOperate.queryDepClaimsByApproveDate(startDate,
					endDate);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:更新在线理赔入部门费用<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2012-12-10
	 * @param deptChargeMap
	 * @param app
	 *            void
	 */
	@Override
	public void updateOnlineDeptCharge(RecompenseApplication app,
			Map<String, List<DeptCharge>> deptChargeMap) {
		if (deptChargeMap != null) {
			List<DeptCharge> deptChargeAddList = deptChargeMap
					.get(Constants.LIST_TYPE_ADD);
			if (deptChargeAddList != null && deptChargeAddList.size() > 0) {
				listItemDao.insertDeptCharge(app.getId(), deptChargeAddList);
			}
			// 修改集合
			List<DeptCharge> deptChargeUpdList = deptChargeMap
					.get(Constants.LIST_TYPE_UPDATE);
			if (deptChargeUpdList != null && deptChargeUpdList.size() > 0) {
				listItemDao.updateDeptChargesById(deptChargeUpdList);
			}
			// 删除集合
			List<DeptCharge> deptChargeDelList = deptChargeMap
					.get(Constants.LIST_TYPE_DELETE);
			if (deptChargeDelList != null && deptChargeDelList.size() > 0) {
				listItemDao.deleteDeptCharge(deptChargeDelList);
			}
		}

	}

	/**
	 * 
	 * <p>
	 * Description:根据员工工号调用费控接口查询收银员信息<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param employeeNum
	 * @return Payment
	 */
	@Override
	public List<Payment> queryPaymentByEmployeeNum(String employeeNum) {
		List<Payment> payments = new ArrayList<Payment>();
		try {
			QueryCashieraccountRequest queryCashieraccountRequest = new QueryCashieraccountRequest();
			queryCashieraccountRequest.setCashierNumber(employeeNum);
			List<CashierAccountInfo> infos = fINSelfserviceOperate
					.queryCashieraccount(queryCashieraccountRequest);
			for (CashierAccountInfo info : infos) {
				Payment payment = new Payment();
				if (StringUtil.isEmpty(info.getBankProvinceCode())
						|| StringUtil.isEmpty(info.getBankCityCode())
						|| StringUtil.isEmpty(info.getBankCode())
						|| StringUtil.isEmpty(info.getBankBranchCode())) {
					RecompenseException re = new RecompenseException(
							RecompenseExceptionType.NOTFULLBANKINFO);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};
				}
				;

				payment.setBankProvice((bankProvinceCityService
						.getBankProvinceByCode(info.getBankProvinceCode())));
				payment.setBankCity((bankProvinceCityService
						.getBankCityByCode(info.getBankCityCode())));
				payment.setBank((bankProvinceCityService
						.getAccountBankByCode(info.getBankCode())));
				payment.setBranch((bankProvinceCityService
						.getAccountBranchByCode(info.getBankBranchCode())));
				payment.setAccount(info.getAccount());
				payment.setOpenName(info.getOpenBankUserName());
				payment.setMobile(info.getMobile());
				payments.add(payment);
			}
			return payments;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}

	}

	/**
	 * 
	 * <p>
	 * Description:向foss提交付款单<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param payment
	 * @return boolean
	 */
	public boolean submitPaymentToFOSS(
			RecompenseApplication recompenseApplication, User user) {
		ClaimsPayBillGenerateRequest request = new ClaimsPayBillGenerateRequest();
		// 封装账号信息
		BankPayInfo bankInfo = new BankPayInfo();
		bankInfo.setAccountName(recompenseApplication.getPayment()
				.getOpenName());
		bankInfo.setAccountNumber(recompenseApplication.getPayment()
				.getAccount());
		bankInfo.setBankCityCode(recompenseApplication.getPayment()
				.getBankCity().getCode());
		bankInfo.setBankCityName(recompenseApplication.getPayment()
				.getBankCity().getName());
		bankInfo.setBankCode(recompenseApplication.getPayment().getBank()
				.getCode());
		bankInfo.setBankName(recompenseApplication.getPayment().getBank()
				.getName());
		bankInfo.setBankProvinceCode(recompenseApplication.getPayment()
				.getBankProvice().getCode());
		bankInfo.setBankProvinceName(recompenseApplication.getPayment()
				.getBankProvice().getName());
		bankInfo.setSubbranchCode(recompenseApplication.getPayment()
				.getBranch().getCode());
		bankInfo.setSubbranchName(recompenseApplication.getPayment()
				.getBranch().getName());
		bankInfo.setAccountProperty(recompenseApplication.getPayment()
				.getAccountProp());
		request.setBankPayInfo(bankInfo);
		// 运单号
		request.setBillNo(recompenseApplication.getRecompenseNum());
		// 业务类型
		request.setBusinessType("1");
		// 金额
		request.setClaimMoney(BigDecimal.valueOf(recompenseApplication
				.getRealAmount()));
		// 理赔类型： 1,异常签收理赔、2,丢货理赔,3，无
		if (recompenseApplication.getRecompenseType().equals(
				Constants.ABNORMAL_SIGN)) {

			request.setClaimType("1");
		}
		if (recompenseApplication.getRecompenseType().equals(
				Constants.LOST_GOODS)) {
			request.setClaimType("2");
		}
		// 理赔方式: 1，正常理赔,2，快速理赔,3，在线理赔，4，无
		if (recompenseApplication.getRecompenseMethod().equals(
				Constants.FAST_TYPE)) {
			request.setClaimWay("1");
		}
		if (recompenseApplication.getRecompenseMethod().equals(
				Constants.NORMAL_TYPE)) {
			request.setClaimWay("2");
		}
		if (recompenseApplication.getRecompenseMethod().equals(
				Constants.OVERPAY_TYPE)) {
			request.setClaimWay("3");
		}
		// 申请人工号
		request.setCreatorNo(user.getEmpCode().getEmpCode());
		// 客户编码
		String customerNum = recompenseApplication.getWaybill()
				.getLeaveCustomerId();
		if ("2".equals(recompenseApplication.getClaimParty())) {
			customerNum = recompenseApplication.getWaybill()
					.getArriveCustomerId();
		}
		if (customerNum == null) {
			customerNum = "";
		}
		request.setCustNo(customerNum);
		// 部门标杆编码
		request.setDeptNo(recompenseApplication.getReportDept());
		// request.setPayBillLastTime(payBillLastTime);
		// //支付类别 现金：CH 电汇：TT 核销：W 核销后现金:WCH 核销后电汇:WTT
		String paymentCategories = recompenseApplication.getPayment()
				.getPaymentMode();
		if (null != paymentCategories) {
			if (paymentCategories.equals(Constants.CUST_DROWMONEY_TYPE_CASH)) {
				request.setPaymentCategory("CH");

			}
			if (paymentCategories.equals(Constants.CUST_DROWMONEY_TYPE_REMIT)) {
				request.setPaymentCategory("TT");
			}
			if (paymentCategories
					.equals(Constants.CUST_DROWMONEY_TYPE_CASH_AFTER_STRIKE_A_BALANCE)) {
				request.setPaymentCategory("WCH");
			}
			if (paymentCategories
					.equals(Constants.CUST_DROWMONEY_TYPE_REMIT_AFTER_STRIKE_A_BALANCE)) {
				request.setPaymentCategory("WTT");
			}
			if (paymentCategories
					.equals(Constants.CUST_DROWMONEY_TYPE_ALL_STRIKE_A_BALANCE)) {
				request.setPaymentCategory("W");
			}

		}

		// 费用划分明细
		List<DeptCharge> deptCharges = recompenseApplication
				.getDeptChargeList();
		for (int i = 0; i < deptCharges.size(); i++) {
			DeptCharge deptCharge = deptCharges.get(i);
			ResponsibilityInfo deptinfo = new ResponsibilityInfo();
			deptinfo.setResponsibilityDeptCode(deptCharge.getDeptCode());
			deptinfo.setResponsibilityMoney(BigDecimal.valueOf(deptCharge
					.getAmount()));
			request.getResponsibilityInfos().add(deptinfo);
		}
		try {
			return paymentApplyOperate.claimsApbill(request);

		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}

	}

	/**
	 * 
	 * <p>
	 * Description:向费控提交付款单<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param payment
	 * @return boolean
	 */
	public boolean submitPaymentToFIN(
			RecompenseApplication recompenseApplication, User user) {
		// NoBillingRecompenseInfo info = new NoBillingRecompenseInfo();
		BillInfo info = new BillInfo();
		// 账号性质
		if (Constants.ACCOUNT_PROPERTIES_CASHIER_ACCOUNT
				.equals(recompenseApplication.getPayment().getAccountProp())) {
			info.setAccountNature(2 + "");
		}
		if (Constants.ACCOUNT_PROPERTIES_PUBLIC.equals(recompenseApplication
				.getPayment().getAccountProp())) {
			info.setAccountNature(5 + "");
		}
		if (Constants.ACCOUNT_PROPERTIES_PRIVATE.equals(recompenseApplication
				.getPayment().getAccountProp())) {
			info.setAccountNature(6 + "");
		}

		// 申请人工号

		info.setApplyUser(user.getEmpCode().getEmpCode());
		// 银行账号
		info.setBankAccount(recompenseApplication.getPayment().getAccount());
		// 银行支行
		info.setBankBranchCode(recompenseApplication.getPayment().getBranch()
				.getCode());
		// 银行城市
		info.setBankCityCode(recompenseApplication.getPayment().getBankCity()
				.getCode());
		// 银行省份
		info.setBankProvinceCode(recompenseApplication.getPayment()
				.getBankProvice().getCode());
		// 银行code
		info.setBankCode(recompenseApplication.getPayment().getBank().getCode());
		// 理赔单号
		info.setBillNum(recompenseApplication.getRecompenseNum());
		// 领款方式
		if (Constants.CUST_DROWMONEY_TYPE_REMIT.equals(recompenseApplication
				.getPayment().getPaymentMode())) {
			info.setDrawMoneyType("20");
		}
		if (Constants.CUST_DROWMONEY_TYPE_CASH.equals(recompenseApplication
				.getPayment().getPaymentMode())) {
			info.setDrawMoneyType("10");
		}
		// 手机
		info.setMobilePhone(recompenseApplication.getPayment().getMobile());
		// 开户名
		info.setUserName(recompenseApplication.getPayment().getOpenName());
		// 金额
		info.setRecompenseAmount(new BigDecimal(recompenseApplication
				.getRealAmount()));
		// 转换入部门费用
		List<DeptCharge> deptCharges = recompenseApplication
				.getDeptChargeList();
		List<CostDetail> costDetails = new ArrayList<CostDetail>();
		for (int i = 0; i < deptCharges.size(); i++) {
			DeptCharge deptCharge = deptCharges.get(i);
			CostDetail costDetail = new CostDetail();
			// 费用产生日期
			costDetail.setCostDate(new Date());
			// 理赔类型---无在线理赔 默认传空？
			costDetail.setRecompenseType(recompenseApplication
					.getRecompenseType());
			// 报销金额
			costDetail.setReimbursementMoneyDetail(new BigDecimal(deptCharge
					.getAmount()));
			// 费用产生部门
			costDetail.setResponsibilityDeptInfo(deptCharge.getDeptCode());
			// 凭证号
			costDetail.setVoucherNumber(recompenseApplication
					.getRecompenseNum());
			costDetails.add(costDetail);
		}
		info.setCostDetails(costDetails);
		try {
			// 调用接口生成报销单
			fINSelfserviceOperate.generateClaimsapbill(info);

			return true;
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};

		}

	}

	/**
	 * 
	 * <p>
	 * Description:保存账号信息<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param payment
	 * @return Payment
	 */
	public Payment savePayment(Payment payment) {
		return listItemDao.savePayment(payment);
	}

	/**
	 * 
	 * <p>
	 * Description:根据接口回调状态修改付款记录<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param payment
	 * @return Payment
	 */
	public Payment updatePayment(Payment payment) {
		return listItemDao.updatePayment(payment);
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔Id查询付款记录<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param recompenseId
	 * @return List<Payment>
	 */
	public List<Payment> searchPaymentByRecompenseId(String recompenseId) {
		return listItemDao.searchPaymentByRecompenseId(recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔号更新付款单id<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param payment
	 * @return Payment
	 */
	@Override
	public void updatePaymentIdByRecompenseNum(String recompenseNum, String id) {
		recompenseDao.updatePaymentIdByRecompenseNum(recompenseNum, id);

	}

	/**
	 * *
	 * <p>
	 * Description:根据理赔号查询付款单id<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param recompenseNum
	 * @param id
	 *            void
	 */
	public String findPaymentIdByRecompenseNum(String recompenseNum) {

		return recompenseDao.findPaymentIdByRecompenseNum(recompenseNum);
	}

	/**
	 * <p>
	 * Description:fINSelfserviceOperate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-29
	 */
	public IFINSelfserviceOperate getfINSelfserviceOperate() {
		return fINSelfserviceOperate;
	}

	/**
	 * <p>
	 * Description:fINSelfserviceOperate<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-29
	 */
	public void setfINSelfserviceOperate(
			IFINSelfserviceOperate fINSelfserviceOperate) {
		this.fINSelfserviceOperate = fINSelfserviceOperate;
	}

	/**
	 * 
	 * <p>
	 * Description:查询有财务作废及到付客户未完成的理赔单数据<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 * @return
	 * 
	 */
	@Override
	public List<RecompenseApplication> findInvalidCustRecompenseList() {

		return recompenseDao.findInvalidCustRecompenseList();
	}

	@Override
	public void updateCustomerInfo(String recompenseId, String leaveCustomerId,
			String arriveCustomerId) {

		recompenseDao.updateCustomerInfo(recompenseId, leaveCustomerId,
				arriveCustomerId);

	}

	@Override
	public List<String> findCreateCustList(String type) {
		return listItemDao.findCreateCustList(type);
	}

	@Override
	public boolean insertCreateCust(String waybillnum, String type) {
		return listItemDao.insertCreateCust(waybillnum, type);
	}

	@Override
	public boolean updateCreateCust(String waybillnum, String type) {
		return listItemDao.updateCreateCust(waybillnum, type);
	}

	/**
	 * 理赔监控，获取短信接收人(经理)
	 * 
	 * @param recompenseIdList
	 *            理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByManager(
			List<String> recompenseIdList) {
		return recompenseDao.getMessageReceiverByManager(recompenseIdList);
	}

	/**
	 * 理赔监控，获取短信接收人(理赔专员)
	 * 
	 * @param recompenseIdList
	 *            理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByCommissioner(
			List<String> recompenseIdList) {
		return recompenseDao.getMessageReceiverByCommissioner(recompenseIdList);
	}

	/**
	 * 理赔监控，获取短信接收人(区域经理)
	 * 
	 * @param recompenseIdList
	 *            理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByAreaManager(
			List<String> recompenseIdList) {
		return recompenseDao.getMessageReceiverByAreaManager(recompenseIdList);
	}

	/**
	 * 理赔监控，获取短信接收人(大区总经理)
	 * 
	 * @param recompenseIdList
	 *            理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByGeneralManager(
			List<String> recompenseIdList) {
		return recompenseDao
				.getMessageReceiverByGeneralManager(recompenseIdList);
	}

	/**
	 * 理赔监控，获取短信接收人(事业部办公室主任)
	 * 
	 * @param recompenseIdList
	 *            理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByDirector(
			List<String> recompenseIdList) {
		return recompenseDao.getMessageReceiverByDirector(recompenseIdList);
	}

	/**
	 * 理赔监控，获取短信接收人(事业部总裁)
	 * 
	 * @param recompenseIdList
	 *            理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByPresident(
			List<String> recompenseIdList) {
		return recompenseDao.getMessageReceiverByPresident(recompenseIdList);
	}

	/**
	 * 理赔监控，发送短信
	 * 
	 * @param smsInfo
	 *            短信实体
	 */
	@Override
	public void sendSmsInfo(List<SmsInformation> infos) {
		try {
			smsInfoSender.sendSms(infos);
		} catch (CrmBusinessException e) {
			RecompenseMonitorException re = new RecompenseMonitorException(
					RecompenseMonitorExceptionType.RECOMPENSE_SENDMESSAGE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {

			};
		}
	}

	/**
	 * 理赔类型值为“在线理赔”时，时间为当前时间-创建时间（客户官网提交的时间） 理赔监控短信发送模板，获取处理天数
	 * 
	 * @param recompenseNum
	 *            运单号
	 * @return 返回处理天数
	 */
	@Override
	public String getRecDurationOnline(String recompenseNum) {
		return recompenseDao.getRecDurationOnline(recompenseNum);
	}

	/**
	 * 当理赔类型值为非“在线理赔”时，时间为当前时间-索赔时间（索赔录入时间） 理赔监控短信发送模板，获取处理天数
	 * 
	 * @param recompenseNum
	 *            运单号
	 * @return 返回处理天数
	 */
	@Override
	public List<RecSmsInformation> getRecDurationNoOnline(String recompenseNum) {
		return recompenseDao.getRecDurationNoOnline(recompenseNum);
	}

	@Override
	public void insertWorkflow(OAWorkflow flow) {
		listItemDao.insertWorkflow(flow);
	}

	/**
	 * 获取快递业务管理部负责人信息
	 * 
	 * @param deptCode
	 *            快递业务管理部部门编号
	 * @return
	 */
	@Override
	public CellphoneMessageInfo findExpressDelivery(String deptCode) {
		return recompenseDao.findExpressDelivery(deptCode);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-8-19
	 * @param phone
	 *            电话号码
	 * @param limit
	 *            限制
	 * @param start
	 *            开始
	 * @return List<RecompenseForCC>
	 */
	public List<RecompenseForCC> searchRecompenseHistoryList(String phone,
			int limit, int start) {

		return recompenseDao.searchRecompenseHistoryList(phone, limit, start);
	}

	@Override
	public int countRecompenseHistory(String phone) {
		return recompenseDao.countRecompenseHistory(phone);
	}

	@Override
	public Map<String,Object> searchOnlineApply(OnlineApplyCondition condition,
			int limit, int start) {
		return listItemDao.searchOnlineApply(condition,limit,start);
	}

	@Override
	public List<RecSmsInformation> searchOnlineApplyPerson(
			List<String> waybillnumberList, String noticeTypes) {
		return recompenseDao.getMessageReceiverForOnline(waybillnumberList, noticeTypes);
	}

	/**
	 * <p>
	 * Description:根据部门ID查询部门经理ID
	 * </p>
	 * @author 	zouming
	 * @extends	@see com.deppon.crm.module.recompense.server.service.RecompenseService#getManagerIdByDeptId(java.lang.String)
	 * @version 0.1 2013-11-13下午3:57:17
	 * @param applyDeptId
	 * @return
	 * @update 	2013-11-13下午3:57:17
	 */
	@Override
	public String getManagerIdByDeptId(String applyDeptId) {
		return recompenseDao.getManagerIdByDeptId(applyDeptId);
	}

	@Override
	public void insertOverpay(Overpay overpay) {
		listItemDao.insertOverpay(overpay);
	}
	
	@Override
	public void updateOverpay(Overpay overpay) {
		listItemDao.updateOverpay(overpay);
	}
	
	/**
	 * 
	 * @description 根据流程号查询理赔信息
	 * @author andy
	 * @version 0.1 2014-1-10
	 * @param 流程号
	 * @date 2014-1-10
	 * @return RecompenseApplication
	 * @update 2014-1-10
	 */
	@Override
	public RecompenseApplication getRecompense(String workflowNo) {
		return recompenseDao.getRecompense(workflowNo);
	}
	
	/**
	 * 
	 * @description 根据流程号查询多赔信息
	 * @author andy
	 * @version 0.1 2014-1-10
	 * @param 流程号
	 * @date 2014-1-10
	 * @return Overpay
	 * @update 2014-1-10
	 */
	@Override
	public Overpay getOverPay(String workflowNo) {
		return listItemDao.getOverPay(workflowNo);
	}

	/**
	 * 
	 * <p>
	 * Description:多赔详情<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-14
	 * @param workNumber
	 * @return
	 * Overpay
	 */
	@Override
	public Overpay getDetailOverpayByWorkNumber(String workNumber) {
		 return listItemDao.getDetailOverpayByWorkNumber(workNumber);
	}
}