package com.deppon.crm.module.recompense.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.client.workflow.domain.AccountInfo;
import com.deppon.crm.module.client.workflow.domain.MuchRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.PaymentInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.Balance;
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
import com.deppon.crm.module.recompense.shared.domain.RecSmsInformation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.recompense.shared.domain.TodoReminder;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.erp.payment.DepClaimsBill;

public interface RecompenseService {

	/**
	 * 
	 * <p>
	 * Description:删除当前设置的大区信息<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-16
	 * @param id
	 *            void
	 */
	abstract void deleteUserRoleDeptRelationById(String id);

	/**
	 * 
	 * <p>
	 * Description:查询已有得大区设置信息<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-29
	 * @return List<RoleDeptRalation>
	 */
	abstract List<UserRoleDeptRelation> getAllUserRoleDeptRelation(int start,
			int limit);

	/**
	 * 
	 * <p>
	 * Description:大区设置<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-16
	 * @param userId
	 * @param roleId
	 * @param deptId
	 *            void
	 */
	abstract void insertUserRoleDepartment(String userId, String roleId,
			String deptId);

	/**
	 * 
	 * <p>
	 * Description:大区设置<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-24
	 * @param userId
	 * @param roleId
	 * @param deptIds
	 *            void
	 */
	abstract void insertUserRoleDepartment(String userId, String roleId,
			List<String> deptIds);

	/**
	 * 
	 * <p>
	 * Description:查询当前角色设置大区<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-16
	 * @return Department
	 */
	abstract List<UserRoleDeptRelation> getUserRoleDeptRelationByUserId(
			String userId);

	/**
	 * 
	 * <p>
	 * Description:查询客户历史理赔信息<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-15
	 * @param customerId
	 * @param recompenseId
	 * @return List<RecompenseApplication>
	 */
	abstract List<RecompenseApplication> getCustomerHistoryRecompenseApp(
			String customerId, String recompenseId, Date startTime, Date endTime);

	/**
	 * @Title: getRecompenseApplicationById
	 * @Description: 按理赔单的Id查询理赔单信息
	 * @param @param userId 操作人员
	 * @param @param recompenseId 理赔单Id
	 * @param @return 理赔单信息
	 * @return RecompenseApplication 返回类型
	 */
	abstract RecompenseApplication getRecompenseApplicationById(
			String recompenseId);

	/**
	 * 
	 * <p>
	 * Description:查询我要处理的理赔单<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-13
	 * @param deptId
	 * @return List<RecompenseApplication>
	 */
	abstract List<RecompenseApplication> getUnfinishedRecompenseAppByUser(
			String roleId);

	/**
	 * 
	 * <p>
	 * Description:删除理赔单<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-13
	 * @param recompenseId
	 *            void
	 */
	abstract void deleteRecompenseApplication(String recompenseId);

	/**
	 * <p>
	 * Description:添加跟进信息<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-13
	 * @param message
	 *            void
	 */
	abstract List<Message> insertMessage(Message message);

	/**
	 * @Title: createRecompenseApplication
	 * @Description: 新建一个理赔单
	 * @param @param app 理赔单数据
	 * @return void
	 */
	abstract void createRecompenseApplication(RecompenseApplication app);

	/**
	 * 
	 * <p>
	 * Description:理赔上报<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-10
	 * @param app
	 *            void
	 * @param issueItemModifyMap
	 * @param goodsTransModifyMap
	 */
	abstract void updateRecompenseReportInfo(RecompenseApplication app,
			Map<String, List<IssueItem>> issueItemModifyMap,
			Map<String, List<GoodsTrans>> goodsTransModifyMap,
			Map<String, List<RecompenseAttachment>> attachmentModifyMap);

	/**
	 * 
	 * <p>
	 * Description:初步处理<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-9
	 * @param app
	 *            void
	 */
	abstract void updateRecompenseProcessInfo(RecompenseApplication recompense,
			Map<String, List<DeptCharge>> deptChargeMap,
			Map<String, List<ResponsibleDept>> responsibleDeptMap,
			Map<String, List<MessageReminder>> messageReminderMap,
			Map<String, List<AwardItem>> awardItemMap);

	/**
	 * 
	 * <p>
	 * Description:保存付款信息<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-14
	 * @param paymentBill
	 *            void
	 */
	abstract void updateRecompensePaymentInfo(RecompenseApplication app);

	/**
	 * 
	 * <p>
	 * Description:更新理赔冲账金额<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param app
	 *            void
	 */

	abstract void updateRecompenseBalanceInfo(RecompenseApplication app);

	/**
	 * <p>
	 * Description:免赔处理<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-10
	 * @param app
	 *            void
	 */
	abstract void updateRecompenseExemptInfo(RecompenseApplication app);

	/**
	 * 
	 * <p>
	 * Description:变更理赔状态<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.1 2012-2-9
	 * @param status
	 *            void
	 */
	abstract void updateRecompenseStatusInfo(RecompenseApplication app);

	/**
	 * 根据运单查询责任部门
	 * 
	 * @param number
	 * @return
	 */
	abstract Map<String, Department> getDeptByWayBillNum(String number);

	abstract RecompenseApplication getRecompenseApplicationByVoucherNo(
			String voucherNo);

	/**
	 * 
	 * @description 理赔处理提交审批.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param reportManCode
	 * 
	 * @param applyManCode
	 * 
	 * @param standardCode
	 * 
	 * @param commiter
	 * 
	 * @param 理赔对象
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午11:38:29
	 */
	boolean submitRecompenseOaApproval(RecompenseApplication app,
			User commiter, String reportManCode, String applyManCode, String standardCode);

	/**
	 * 
	 * <p>
	 * Description:提交多赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param app
	 * @param commiter
	 * @param reportManCode
	 * @param reportManName
	 * @return boolean
	 */

	boolean submitRecompenseOverpayApproval(RecompenseApplication app,
			User commiter, String reportManCode, String reportManName);

	/**
	 * 
	 * @description 根据条件查询理赔-如果运单号或客户编号不为空时其他条件失效.
	 * @author 安小虎
	 * @version 0.1 2012-3-31
	 * @param 理赔查询对象
	 * @date 2012-3-31
	 * @return 理赔对对象集合
	 * @update 2012-3-31 上午9:10:16
	 */
	List<RecompenseApplication> searchRecompenseByCondition(
			RecompenseSearchCondition condition);

	/**
	 * 
	 * @description 根据理赔查询条件得到满足条件的记录数.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param 理赔查询对象
	 * @date 2012-4-10
	 * @return int：满足条件的记录数
	 * @update 2012-4-10 下午4:22:27
	 */
	int getRecompenseCountByCondition(RecompenseSearchCondition condition);

	/**
	 * 
	 * <p>
	 * Description:提交理赔付款单审批<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param app
	 * @param paymentDetail
	 * @param deptChargeDetail
	 * @return boolean
	 */
	boolean submitRecompensePaymentApproval(RecompenseApplication app,
			String[] paymentDetail, String[] deptChargeDetail);

	/**
	 * 
	 * @description 在线理赔申请service新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-4-1
	 * @param 在线理赔对象
	 * @date 2012-4-1
	 * @return boolean：成功与否
	 * @update 2012-4-1 上午10:02:39
	 */
	boolean insertOnlineApply(OnlineApply onlineApply);

	/**
	 * 
	 * @description 在线理赔申请service修改接口.
	 * @author 安小虎
	 * @version 0.1 2012-4-9
	 * @param 在线理赔对象
	 * @date 2012-4-9
	 * @return boolean：成功与否
	 * @update 2012-4-9 下午4:22:56
	 */
	boolean updateOnlineApply(OnlineApply onlineApply);

	/**
	 * 
	 * @description 在线理赔根据ID查询.
	 * @author 安小虎
	 * @version 0.1 2012-4-19
	 * @param id
	 * @date 2012-4-9
	 * @return 在线理赔对象
	 * @update 2012-4-9 下午5:42:44
	 */
	OnlineApply getOnlineApplyById(String id);

	/**
	 * 
	 * @description 在线理赔申请根据运单号查询.
	 * @author 安小虎
	 * @version 0.1 2012-4-9
	 * @param 运单号
	 * @date 2012-4-9
	 * @return 在线理赔申请集合
	 * @update 2012-4-9 上午9:21:57
	 */
	List<OnlineApply> searchOnlineApplyByWaybillNum(String waybillNum);

	/**
	 * 
	 * @description 在线理赔根据条件查询.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param map
	 *            ：key和属性一致
	 * @date 2012-4-10
	 * @return 在线理赔对象集合
	 * @update 2012-4-10 上午9:11:27
	 */
	List<OnlineApply> searchOnlineApplyByCondition(String deptId,
			List<String> statusList, int start, int limit);

	/**
	 * 
	 * @description 在线理赔初始加载记录数.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @date 2012-4-10
	 * @return 记录数
	 * @update 2012-4-10 下午5:21:44
	 */
	Long getOnlineApplyCountByCondition(String deptId, List<String> statusList);

	/**
	 * 
	 * @description 冲帐根据条件查询.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param map中key与冲帐对象中属性名一致
	 * @date 2012-4-10
	 * @return 冲帐对象集合
	 * @update 2012-4-10 下午3:42:56
	 */
	List<Balance> searchBalanceByCondition(Map<String, String> map);

	/**
	 * 
	 * <p>
	 * Description:根据工作流号获得工作流实体<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param WorkflowNum
	 * @return OAWorkflow
	 */

	OAWorkflow getWorkflowByWorkflowNum(String WorkflowNum);

	/**
	 * 
	 * <p>
	 * Description:更新工作流实体<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param oaWorkflow
	 *            void
	 */
	void updateWorkflow(OAWorkflow oaWorkflow);

	/**
	 * 
	 * @description 理赔修改.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 理赔对象
	 * @date 2012-4-12
	 * @return boolean：成功与否
	 * @update 2012-4-12 上午10:13:09
	 */
	boolean updateRecompense(RecompenseApplication recompense);

	/**
	 * 
	 * <p>
	 * Description:根据用户id获得部门ids<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param userId
	 * @return List<String>
	 */

	List<String> getDeptIdsByUserId(String userId);

	/**
	 * 
	 * @description 通过运单号查询所有差错信息.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 运单号
	 * @date 2012-4-12
	 * @return 差错信息集合
	 * @update 2012-4-12 上午11:03:21
	 */
	List<OaAccidentInfo> getAccidentByWaybillNum(String waybillNum);

	/**
	 * 
	 * @description 查询运单状态为异常签收状态的运单.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 运单号
	 * @date 2012-4-12
	 * @return 运单信息
	 * @update 2012-4-12 上午11:16:13
	 */
	FossWaybillInfo getWaybillRecompense(String waybillNum);

	/**
	 * 
	 * @description 通过差错编号查询差错信息（未开单的差错），可能返回空.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 差错编号
	 * @date 2012-4-12
	 * @return 差错信息数据
	 * @update 2012-4-12 上午11:39:25
	 */
	OaAccidentInfo getAccidentByAccidentCode(String errorCode);

	/**
	 * 
	 * @description 常规理赔工作流审批申请（理赔专员提交审批）.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 常规理赔工作流需要的参数信息
	 * @date 2012-4-12
	 * @return OA系统生成的工作流编号
	 * @update 2012-4-12 下午3:19:04
	 */
	String applyNormalRecompense(NormalRecompenseInfo normalRecompenseInfo);

	/**
	 * 
	 * @description 多陪工作流审批申请.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 多陪审批信息
	 * @date 2012-4-12
	 * @return OA系统生成的工作流编号
	 * @update 2012-4-12 下午3:23:59
	 */
	String applyMuchRecompense(MuchRecompenseInfo muchRecompenseInfo);

	/**
	 * 
	 * @description 付款工作流提交.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param PaymentInfo对象
	 * @date 2012-4-12
	 * @return 工作流编号
	 * @update 2012-4-12 下午3:37:34
	 */
	String paymentApply(PaymentInfo paymentInfo);

	/**
	 * 
	 * @description 通过工号查询营业员的账户信息，支持现金付款的流程.
	 * @author 安小虎
	 * @version 0.1 2012-4-19
	 * @param 工号
	 * @date 2012-4-19
	 * @return 账户信息
	 * @update 2012-4-19 下午3:14:36
	 */
	AccountInfo queryAccount(String jobNumber);

	/**
	 * 
	 * @description 在线理赔付款工作流提交.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param PaymentInfo对象
	 * @date 2012-4-12
	 * @return 工作流编号
	 * @update 2012-4-12 下午3:49:09
	 */
	String paymentOnlineApply(PaymentInfo paymentInfo);

	/**
	 * 
	 * @description 发送短信.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 以分号隔开的手机号
	 *            ，发送内容
	 * @date 2012-4-12
	 * @update 2012-4-12 下午3:58:37
	 */
	void sendSms(String phones, String content);

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
	void sendSmsInfo(String phones, String content, String sender,
			String deptCode);

	/**
	 * 
	 * 
	 * @description 发送内部邮件.
	 * @author 安小虎
	 * @version 0.1 2012-4-12
	 * @param 以分号隔开的员工号
	 *            ，发送内容
	 * @date 2012-4-12
	 * @return none
	 * @update 2012-4-12 下午4:05:49
	 */
	void sendInwardMail(String empNos, String content);

	/**
	 * 
	 * @Title: getOverpayByWorkflowNum
	 * @Description: 查询多赔申请按多赔工作流号,最后创建时间的一个,没有则返回null
	 * @param @param workflowNum
	 * @param @param recompenseId
	 * @param @return 设定文件
	 * @return Overpay 返回类型
	 * @throws
	 */
	Overpay getOverpayByWorkflowNum(String workflowNum, String recompenseId);

	/**
	 * 
	 * @Title: getWorkflowByAppIdAndType
	 * @Description: 查询理赔单当前类型的最后一个工作流,没有则返回null
	 * @param @param appId
	 * @param @param type
	 * @param @return 设定文件
	 * @return OAWorkflow 返回类型
	 * @throws
	 */
	OAWorkflow getWorkflowByAppIdAndType(String appId, String type);

	/**
	 * @param rejectReason
	 * 
	 * 
	 * @Title: updateOnlineApplyStatusByRecompenseId
	 * @Description: 通过理赔单状态更新在线理赔申请
	 * @param @param recompenseId
	 * @param @param Status 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	void updateOnlineApplyStatusByRecompenseId(String recompenseId,
			String status, String rejectReason);

	/**
	 * 
	 * @description 修改理赔单中多赔ID.
	 * @author 安小虎
	 * @version 0.1 2012-4-20
	 * @param 理赔单ID
	 * @param 多赔单ID
	 * @date 2012-4-20
	 * @return boolean：成功与否
	 * @update 2012-4-20 下午1:47:54
	 */
	boolean updateRecompenseOverpayById(String recompenseId, String overPayId);

	/**
	 * 
	 * @description 根据理赔单ID查询在线理赔.
	 * @author 安小虎
	 * @version 0.1 2012-4-20
	 * @param 理赔单ID
	 * @date 2012-4-20
	 * @return 在线理赔对象
	 * @update 2012-4-20 下午4:31:18
	 */
	OnlineApply getOnlineApplyByRecompenseId(String recompenseId);

	/**
	 * 
	 * <p>
	 * Description:根据部门id获取用户部门角色关系<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param deptId
	 * @return List<UserRoleDeptRelation>
	 */

	List<UserRoleDeptRelation> getUserRoleDeptRelationByDeptId(String deptId);

	/**
	 * 
	 * <p>
	 * Description:根据id或者理赔消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param recompenseId
	 * @return List<Message>
	 */
	List<Message> getMessageByRecompenseId(String recompenseId);

	/**
	 * 
	 * @description 根据客户ID获得客户所有理赔信息（理赔时间倒序）.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @date 2012-4-24
	 * @return 理赔集合
	 * @update 2012-4-24 下午3:27:44
	 */
	List<RecompenseApplication> getRecompenseListByCustId(String custId);

	/**
	 * 
	 * <p>
	 * Description:根据官网账号查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineUser
	 * @param waybillNum
	 * @return List<OnlineApply>
	 */
	List<OnlineApply> getOnlineApplyByOnlineUser(String onlineUser,
			String waybillNum);

	/**
	 * 
	 * <p>
	 * Description:根据条件查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineUser
	 * @param waybillNum
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @return List<OnlineApply>
	 */
	List<OnlineApply> getOnlineApplyByInterCondition(String onlineUser,
			String waybillNum, Date startTime, Date endTime, int start,
			int limit);

	/**
	 * 
	 * <p>
	 * Description:根据条件查询在线理赔数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineUser
	 * @param waybillNum
	 * @param startTime
	 * @param endTime
	 * @return int
	 */
	int getOnlineApplyByInterConditionCount(String onlineUser,
			String waybillNum, Date startTime, Date endTime);

	/**
	 * 
	 * <p>
	 * Description:根据理赔id查询工作流实体<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param recompenseId
	 * @return List<OAWorkflow>
	 */
	List<OAWorkflow> getOaWorkflowByRecompenseId(String recompenseId);

	/**
	 * 
	 * <p>
	 * Description:生成待办<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param status
	 * @return List<TodoReminder>
	 */
	List<TodoReminder> generateTodoReminder(String status);

	/**
	 * 
	 * <p>
	 * Description:部门应付单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param appNumList
	 * @return List<DepClaimsBill>
	 */
	List<DepClaimsBill> getRecompenseInPayment(List<String> appNumList);

	/**
	 * 
	 * <p>
	 * Description:查询DepClaimsBillList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param startDate
	 * @param endDate
	 * @return List<DepClaimsBill>
	 */
	List<DepClaimsBill> getRecompensePaymented(Date startDate, Date endDate);

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
	public void updateOnlineDeptCharge(RecompenseApplication app,
			Map<String, List<DeptCharge>> deptChargeMap);

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
	public List<Payment> queryPaymentByEmployeeNum(String employeeNum);

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
			RecompenseApplication recompenseApplication, User user);

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
			RecompenseApplication recompenseApplication, User user);

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
	public Payment savePayment(Payment payment);

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
	public Payment updatePayment(Payment payment);

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
	public List<Payment> searchPaymentByRecompenseId(String recompenseId);

	/**
	 * 
	 * <p>
	 * Description:根据理赔号更新付款单id<<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param payment
	 * @return Payment
	 */

	public void updatePaymentIdByRecompenseNum(String recompenseNum, String id);

	/**
	 * 
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
	public String findPaymentIdByRecompenseNum(String recompenseNum);

	/**
	 * 
	 * <p>
	 * Description:查询有财务作废及到付客户未完成的理赔单数据<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 * @return List<RecompenseApplication>
	 */

	List<RecompenseApplication> findInvalidCustRecompenseList();

	/**
	 * 
	 * <p>
	 * Description:查询有财务作废及到付客户未完成的理赔单数据<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 * @return List<RecompenseApplication>
	 */

	void updateCustomerInfo(String recompenseId, String leaveCustomerId,
			String arriveCustomerId);

	public List<String> findCreateCustList(String type);

	public boolean insertCreateCust(String waybillnum, String type);

	public boolean updateCreateCust(String waybillnum, String type);

	/**
	 * 理赔监控，获取短信接收人(经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByManager(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(理赔专员)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByCommissioner(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(区域经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByAreaManager(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(大区总经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByGeneralManager(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(事业部办公室主任)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByDirector(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(事业部总裁)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByPresident(List<String> recompenseIdList);
	
	/**
	 * 理赔监控，发送短信
	 * @param smsInfo 短信实体
	 */
	public void sendSmsInfo(List<SmsInformation> smsInfo);
	
	/**
	 * 理赔类型值为“在线理赔”时，时间为当前时间-创建时间（客户官网提交的时间）
	 * 理赔监控短信发送模板，获取处理天数
	 * @param recompenseNum 运单号
	 * @return 返回处理天数
	 */
	public String getRecDurationOnline(String recompenseNum);
	
	/**
	 * 当理赔类型值为非“在线理赔”时，时间为当前时间-索赔时间（索赔录入时间）
	 * 理赔监控短信发送模板，获取处理天数
	 * @param recompenseNum 运单号
	 * @return 返回处理天数
	 */
	public List<RecSmsInformation> getRecDurationNoOnline(String recompenseNum);
	
	void insertWorkflow(OAWorkflow flow);
	
	/**
	 * 获取快递业务管理部负责人信息
	 * @param deptCode 快递业务管理部部门编号
	 * @return
	 */
	CellphoneMessageInfo findExpressDelivery(String deptCode);
	
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
	List<RecompenseForCC> searchRecompenseHistoryList(String phone, int limit,
			int start);
	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-8-19
	 * @param phone
	 * @return
	 * int
	 */
	int countRecompenseHistory(String phone);
	/**
	 * 
	 * <p>
	 * Description:在线理赔查询<br />
	 * </p>
	 * @author 杨伟
	 * @version 0.1 2013-11-5
	 * @param condition
	 * @return List<OnlineApply>
	 */
	Map<String,Object> searchOnlineApply(OnlineApplyCondition condition, int limit,
			int start);
	/**
	 * 
	 * <p>
	 * Description:在线理赔查询<br />
	 * </p>
	 * @author 杨伟
	 * @version 0.1 2013-11-5
	 * @param waybillnumberList noticeTypes
	 * @return List<RecSmsInformation>
	 */
	List<RecSmsInformation> searchOnlineApplyPerson(List<String> waybillnumberList,
			String noticeTypes);
	
	/**
	 * 
	 * <p>
	 * Description:根据部门ID查询部门经理ID
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-11-13下午3:56:51
	 * @param applyDeptId
	 * @return
	 * String
	 * @update 2013-11-13下午3:56:51
	 */
	String getManagerIdByDeptId(String applyDeptId);
	
	/**
	 * 
	 * @description 多赔DAO新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-31
	 * @param 多赔对象
	 * @date 2012-3-31
	 * @return boolean：成功与否
	 * @update 2012-3-31 下午2:19:59
	 */
	void insertOverpay(Overpay overpay);
	
	void updateOverpay(Overpay overpay);
	
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
	RecompenseApplication getRecompense(String workflowNo);
	
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
	Overpay getOverPay(String workflowNo);
	
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
	Overpay getDetailOverpayByWorkNumber(String workNumber);
}