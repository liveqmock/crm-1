package com.deppon.crm.module.recompense.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.shared.domain.BankAccount;
import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.GetRecompenseByWayBill;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.OnlineApplyCondition;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.RecSmsInformation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

public interface RecompenseManager {

	// ***************************************************工作流********************************************************
	/**
	 * @Title: performAction
	 * @Description: 执行理赔新增、修改保存、删除、提交等业务操作
	 * @param @param app 业务数据
	 * @param @param actionId 返回当前执行的事件Id
	 * @param @param userId 当前操作人员
	 * @return void 返回类型
	 */
	void performAction(Map appMap, int actionId, String userId);

	/**
	 * 
	 * @Title: getRecompenseApplicationByUser
	 * @Description: 查询当前用户的理赔
	 * @param @param userId
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	List<RecompenseApplication> getRecompenseApplicationByUser(String userId);

	/**
	 * 
	 * @Title: getRecompenseApplicationByCustomer
	 * @Description: 查询客户已有理赔记录，排除当前理赔单
	 * @param @param customerId
	 * @param @param recompenseId
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	List<RecompenseApplication> getRecompenseApplicationByCustomer(
			String customerId, String recompenseId, Date startTime, Date endTime);

	/**
	 * 
	 * @Title: getRecompenseApplicationStatByCustomer
	 * @Description: 查询客户已有理赔记录统计，排除当前理赔单
	 * @param @param customerId
	 * @param @param recompenseId
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return
	 * @return RecompenseApplication 返回类型
	 * @throws
	 */
	RecompenseApplication getRecompenseApplicationStatByCustomer(
			List<RecompenseApplication> appList);

	/**
	 * @Title: getRecompenseApplicationById
	 * @Description: 按理赔单的Id查询理赔单信息
	 * @param @param userId 操作人员
	 * @param @param recompenseId 理赔单Id
	 * @param @return 理赔单信息
	 * @return RecompenseApplication 返回类型
	 */
	Map getRecompenseApplicationById( String recompenseId,User user);

	/**
	 * 
	 * @Title: getWorkFlowByOwner
	 * @Description: 获取用户工作流
	 * @param @param userId
	 * @param @return
	 * @return List 返回类型
	 * @throws
	 */
	List getWorkFlowByOwner(String userId);

	/**
	 * 
	 * @Title: deleteRecompenseApplication
	 * @Description: 经理删除未提交理赔
	 * @param @param userId
	 * @param @param recompenseId
	 * @param @param workflowId
	 * @return void 返回类型
	 * @throws
	 */
	void deleteRecompenseApplication(String userId, String recompenseId,
			long workflowId);

	/**
	 * 
	 * @Title: addMessageForRecompense
	 * @Description: 增加理赔的跟进消息
	 * @param @param reportDept
	 * @param @param deptId
	 * @param @param message
	 * @param @param user
	 * @param @return
	 * @return List<Message> 返回类型
	 * @throws
	 */
	List<Message> addMessageForRecompense(String reportDept, String deptId,
			Message message, User user);

	/**
	 * 
	 * @Title: getWaybillInfoByNo
	 * @Description: 根据凭证号获得运单
	 * @param @param voucherNo
	 * @param @param recompenseMethod
	 * @param @return
	 * @return Waybill 返回类型
	 * @throws
	 */
	Waybill getWaybillInfoByNo(String voucherNo, String recompenseMethod);

	/**
	 * 
	 * <p>
	 * Description:在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param WaybillNum
	 * @return boolean
	 */

	boolean canCreateOnlineApply(String WaybillNum);

	/**
	 * 
	 * @Title: getAccidentByNo
	 * @Description: 根据凭证号获得差错信息
	 * @param @param voucherNo
	 * @param @param recompenseMethod
	 * @param @return
	 * @return List<OaAccidentInfo> 返回类型
	 * @throws
	 */
	List<OaAccidentInfo> getAccidentByNo(String voucherNo,
			String recompenseMethod);

	/**
	 * 
	 * @Title: getRecompenseForMonitoring
	 * @Description: 查询理赔监控
	 * @param @param condition
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	List<RecompenseApplication> getRecompenseForMonitoring(
			RecompenseSearchCondition condition);

	/**
	 * 
	 * @Title: reminderForRecompenseMonitor
	 * @Description: 理赔监控催办
	 * @param @param id
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	boolean reminderForRecompenseMonitor(String id);

	// *************************************大区设置***************************************

	/**
	 * 
	 * @Title: getUserRoleDeptRelationByUserId
	 * @Description: 查询当前角色已设置的大区
	 * @param @param userId
	 * @param @return
	 * @return List<UserRoleDeptRelation> 返回类型
	 * @throws
	 */
	List<UserRoleDeptRelation> getUserRoleDeptRelationByUserId(String userId);

	/**
	 * 
	 * @Title: getAllUserRoleDeptRelation
	 * @Description: 得到已有得大区设置信息
	 * @param @param start
	 * @param @param limit
	 * @param @return
	 * @return List<UserRoleDeptRelation> 返回类型
	 * @throws
	 */
	List<UserRoleDeptRelation> getAllUserRoleDeptRelation(int start, int limit);

	/**
	 * 
	 * @Title: deleteUserRoleDeptRelationById
	 * @Description: 根据id删除大区设置信息
	 * @param @param id
	 * @return void 返回类型
	 * @throws
	 */
	void deleteUserRoleDeptRelationById(String id);

	/**
	 * 
	 * @Title: insertUserRoleDepartment
	 * @Description: 大区设置
	 * @param @param userId
	 * @param @param roleId
	 * @param @param deptId
	 * @return void 返回类型
	 * @throws
	 */
	void insertUserRoleDepartment(String userId, String roleId, String deptId);

	/**
	 * 
	 * @Title: searchOnlineApplyByCondition
	 * @Description: 在线理赔申请查询
	 * @param @param waybillNum
	 * @param @param deptId
	 * @param @param start
	 * @param @param limit
	 * @param @return
	 * @return List<OnlineApply> 返回类型
	 * @throws
	 */
	List<OnlineApply> searchOnlineApplyByCondition(String waybillNum,
			String deptId, int start, int limit);

	/**
	 * 
	 * @description 在线理赔初始加载记录数.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @date 2012-4-10
	 * @return 记录数
	 * @update 2012-4-10 下午5:21:44
	 */
	Long searchOnlineApplyCountByCondition(String waybillNum, String deptId);

	/**
	 * 
	 * @description 在线理赔申请受理.
	 * @author 安小虎
	 * @version 0.1 2012-4-9
	 * @param 在线理赔申请对象
	 * @date 2012-4-9
	 * @return boolean：成功与否
	 * @update 2012-4-9 上午10:10:17
	 */
	RecompenseApplication handleOnlineApply(String id,User user);

	/**
	 * 
	 * @description 在线理赔申请拒绝.
	 * @author 安小虎
	 * @version 0.1 2012-4-9
	 * @param 在线理赔申请对象
	 * @date 2012-4-9
	 * @return boolean：成功与否
	 * @update 2012-4-9 下午4:21:31
	 */
	boolean refuseOnlineApply(String id,User user);

	/**
	 * 
	 * @description 根据条件查询理赔-如果运单号或客户编号不为空时其他条件失效.
	 * @author 安小虎
	 * @version 0.1 2012-4-5
	 * @param user
	 * @param 理赔查询对象
	 * @date 2012-4-5
	 * @return 理赔对对象集合
	 * @update 2012-4-5 上午9:30:16
	 */
	List<RecompenseApplication> searchRecompenseByCondition(
			RecompenseSearchCondition condition, User user);

	/**
	 * 
	 * @description 根据理赔查询条件得到满足条件的记录数.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param user
	 * @param 理赔查询对象
	 * @date 2012-4-10
	 * @return int：满足条件的记录数
	 * @update 2012-4-10 下午4:22:27
	 */
	int getRecompenseCountByCondition(RecompenseSearchCondition condition,
			User user);

	/**
	 * 
	 * @description 根据大区ID查询大区.
	 * @author 安小虎
	 * @version 0.1 2012-4-11
	 * @param 大区ID
	 * @date 2012-4-11
	 * @return 大区对象
	 * @update 2012-4-11 上午9:28:55
	 */
	Department getAreaById(String id);

	/**
	 * 
	 * @Title: saveRecompenseProcessInfo
	 * @Description: 保存理賠處理
	 * @param @param appMap 理赔数据MAP
	 * @return void 返回类型
	 * @throws
	 */
	void saveRecompenseProcessInfo(Map appMap);

	/**
	 * @param user
	 * 
	 * @Title: confirmRecompenseAmountInfo
	 * @Description: 确认理赔金额
	 * @param @param appId 理赔单ID
	 * @return void 返回类型
	 * @throws
	 */
	void confirmRecompenseAmountInfo(String appId, User user);

	/**
	 * @param user
	 * 
	 * @Title: cancelRecompenseProcessInfo
	 * @Description: 取消理赔金额
	 * @param @param appId 理赔单ID
	 * @return void 返回类型
	 * @throws
	 */
	void cancelRecompenseProcessInfo(String appId, User user);

	/**
	 * 
	 * @Title: oaOverpayRefuse
	 * @Description: 多赔拒绝
	 * @param @param workflowNum 工作流号
	 * @param @param laster 最后操作人ID
	 * @param @param reason 审批原因
	 * @param @param approveDate 审批日期
	 * @return void 返回类型
	 * @throws
	 */
	void oaOverpayRefuse(String workflowNum, User approver, String reason,
			Date approveDate);

	/**
	 * 
	 * @Title: oaOverpayApprove
	 * @Description: 多赔同意
	 * @param @param workflowNum 工作流号
	 * @param @param approver 最后操作人ID
	 * @param @param reason 审批原因
	 * @param @param approveDate 审批日期
	 * @return void 返回类型
	 * @throws
	 */
	void oaOverpayApprove(String workflowNum, User approver, String reason,
			Date approveDate);

	/**
	 * 
	 * @Title: oaNormalRefuse
	 * @Description: oa普通审批拒绝
	 * @param @param workflowNum 工作流号
	 * @param @param approver 最后操作人ID
	 * @param @param reason 审批原因
	 * @param @param approveDate 审批日期
	 * @return void 返回类型
	 * @throws
	 */
	void oaNormalRefuse(String workflowNum, User approver, String reason,
			Date approveDate);

	/**
	 * 
	 * @Title: oaNormalApprove
	 * @Description: oa普通审批同意
	 * @param @param workflowNum 工作流号
	 * @param @param approver 最后操作人ID
	 * @param @param reason 审批原因
	 * @param @param approveDate 审批日期
	 * @return void 返回类型
	 * @throws
	 */
	void oaNormalApprove(String workflowNum, User approver, String reason,
			Date approveDate);

	/**
	 * 
	 * @Title: getDeptIdsByUserId
	 * @Description: 查询专员的大区列表
	 * @param @param userId
	 * @param @return
	 * @return List<String> 返回类型
	 * @throws
	 */
	List<String> getDeptIdsByUserId(String userId);

	/**
	 * 
	 * @Title: getUserDeptIds
	 * @Description: 获取专员的部门列表
	 * @param @param user
	 * @param @return
	 * @return String[] 返回类型
	 * @throws
	 */
	String[] getUserDeptIds(User user);

	/**
	 * 
	 * @Title: getUserById
	 * @Description: 获取用户
	 * @param @param userId
	 * @param @return
	 * @return User 返回类型
	 * @throws
	 */
	User getUserById(String userId);

	/**
	 * 
	 * @Title: getUserRoleId
	 * @Description: 获取当前用户的理赔角色
	 * @param @param user
	 * @param @return
	 * @return String 返回类型
	 * @throws
	 */
	String getUserRoleId(User user);

	/**
	 * 
	 * @Title: getBigAreaByDeptId
	 * @Description: 通过上报部门查询所属大区
	 * @param @param deptId
	 * @param @return
	 * @return Department 返回类型
	 * @throws
	 */
	Department getDepartmentByDeptId(String deptId);

	/**
	 * 
	 * @Title: getBigAreaByDeptId
	 * @Description: 获取大区对象
	 * @param @param standcode
	 * @param @return
	 * @return Department 返回类型
	 * @throws
	 */
	Department getBigAreaByDeptId(String standcode);

	/**
	 * 
	 * @Title: getBizDeptByDeptId
	 * @Description: 获取事业部对象
	 * @param @param deptId
	 * @param @return
	 * @return Department 返回类型
	 * @throws
	 */
	Department getBizDeptByDeptId(String deptId);

	/**
	 * 
	 * @Title: getBigAreaByDeptCode
	 * @Description: 获取大区对象
	 * @param @param deptCode
	 * @param @return
	 * @return Department 返回类型
	 * @throws
	 */
	Department getBigAreaByDeptCode(String deptCode);

	/**
	 * 
	 * @Title: getDeptByStandardCode
	 * @Description: 通过标杆编码获取部门
	 * @param @param deptCode
	 * @param @return
	 * @return Department 返回类型
	 * @throws
	 */
	Department getDeptByStandardCode(String deptCode);

	/**
	 * 
	 * @Title: getBigAreaList
	 * @Description: 查询大区列表
	 * @param @return
	 * @return List<Department> 返回类型
	 * @throws
	 */
	List<Department> getBigAreaList();

	/**
	 * 
	 * @Title: getAllBizDeptList
	 * @Description: 查询事业部列表
	 * @param @return
	 * @return List<Department> 返回类型
	 * @throws
	 */
	List<Department> getAllBizDeptList();

	/**
	 * 
	 * @Title: getBigAreaListByName
	 * @Description: 通过名字查大区
	 * @param @param areaName
	 * @param @return
	 * @return List<Department> 返回类型
	 * @throws
	 */
	List<Department> getBigAreaListByName(String areaName);

	/**
	 * 
	 * @Title: getRecompenseApplicationByVoucherNo
	 * @Description: 通过凭证号查理赔
	 * @param @param voucherNo
	 * @param @return
	 * @return RecompenseApplication 返回类型
	 * @throws
	 */
	RecompenseApplication getRecompenseApplicationByVoucherNo(String voucherNo);

	/**
	 * 
	 * @Title: getUserListByUserName
	 * @Description: 通过名字查用户
	 * @param @param userName
	 * @param @param limit
	 * @param @return
	 * @return List<User> 返回类型
	 * @throws
	 */
	List<User> getUserListByUserName(String userName, int limit);

	/**
	 * 
	 * @Title: getUserListByEmpCode
	 * @Description: 通过员工编号查用户
	 * @param @param empcode
	 * @param @param limit
	 * @param @return
	 * @return List<User> 返回类型
	 * @throws
	 */
	List<User> getUserListByEmpCode(String empcode, int limit);

	/**
	 * 
	 * @Title: getDeptListByDeptName
	 * @Description: 通过名字查部门
	 * @param @param deptName
	 * @param @param limit
	 * @param @return
	 * @return List<Department> 返回类型
	 * @throws
	 */
	List<Department> getDeptListByDeptName(String deptName, int limit);

	/**
	 * 
	 * @Title: getBankAccountByEmpCode
	 * @Description: 查询收银员账号
	 * @param @param empCode
	 * @param @return
	 * @return BankAccount 返回类型
	 * @throws
	 */
	BankAccount getBankAccountByEmpCode(String empCode);

	/**
	 * 
	 * @Title: getMessageUserIds
	 * @Description: 查询角色所属部门
	 * @param @param roleId
	 * @param @param reportDept
	 * @param @param deptId
	 * @param @return
	 * @return List<String> 返回类型
	 * @throws
	 */
	List<String> getMessageUserIds(String roleId, String reportDept,
			String deptId);

	/**
	 * 
	 * @Title: addTodoMessage
	 * @Description: 增加代办消息
	 * @param @param todoMessage
	 * @return void 返回类型
	 * @throws
	 */
	void addTodoMessage(
			com.deppon.crm.module.common.shared.domain.Message todoMessage);

	/**
	 * 
	 * @description 在线理赔申请manager新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-4-1
	 * @param 在线理赔对象
	 * @date 2012-4-1
	 * @return boolean：成功与否
	 * @update 2012-4-1 上午10:00:30
	 */
	boolean createOnlineApply(OnlineApply onlineApply);

	/**
	 * 
	 * @Title: updateOnlineApply
	 * @Description: 更新在线理赔申请
	 * @param @param onlineApply
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	boolean updateOnlineApply(OnlineApply onlineApply);

	/**
	 * 
	 * @Title: cancelOnlineApply
	 * @Description: 取消在线理赔申请
	 * @param @param onlineUser
	 * @param @param waybillNum
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	boolean cancelOnlineApply(String onlineUser, String waybillNum);

	/**
	 * 
	 * @Title: getOnlineApplyStatusByWaybillNum
	 * @Description: 获取在线理赔申请状态
	 * @param @param waybillNum
	 * @param @return
	 * @return String 返回类型
	 * @throws
	 */
	String getOnlineApplyStatusByWaybillNum(String waybillNum);

	/**
	 * 
	 * @Title: getOnlineApplyByOnlineUser
	 * @Description:查询客户的在线理赔申请
	 * @param @param onlineUser
	 * @param @param waybillNum
	 * @param @return
	 * @return List<OnlineApply> 返回类型
	 * @throws
	 */
	List<OnlineApply> getOnlineApplyByOnlineUser(String onlineUser,
			String waybillNum);

	/**
	 * 
	 * @Title: getOnlineApplyByCondition
	 * @Description: 查询在线理赔申请
	 * @param @param onlineUser
	 * @param @param waybillNum
	 * @param @param startTime
	 * @param @param endTime
	 * @param @param start
	 * @param @param limit
	 * @param @return
	 * @return List<OnlineApply> 返回类型
	 * @throws
	 */
	List<OnlineApply> getOnlineApplyByCondition(String onlineUser,
			String waybillNum, Date startTime, Date endTime, int start,
			int limit);

	/**
	 * 
	 * @Title: getOnlineApplyByConditionCount
	 * @Description: 统计查询结果个数
	 * @param @param onlineUser
	 * @param @param waybillNum
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return
	 * @return int 返回类型
	 * @throws
	 */
	int getOnlineApplyByConditionCount(String onlineUser, String waybillNum,
			Date startTime, Date endTime);

	/**
	 * 
	 * @Title: repayOnlineApply
	 * @Description: 在线理赔重新付款
	 * @param @param onlineApply
	 * @return void 返回类型
	 * @throws
	 */
	boolean repayOnlineApply(OnlineApply onlineApply);

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
	 * @Title: getOnlineApplyById
	 * @Description: 获取在线理赔申请
	 * @param @param id
	 * @param @return
	 * @return OnlineApply 返回类型
	 * @throws
	 */
	OnlineApply getOnlineApplyById(String id);

	/**
	 * 
	 * @Title: getOnlineApplyByRecompenseId
	 * @Description: 通过理赔单获取在线理赔申请
	 * @param @param recompenseId
	 * @param @return
	 * @return OnlineApply 返回类型
	 * @throws
	 */
	OnlineApply getOnlineApplyByRecompenseId(String recompenseId);

	/**
	 * 
	 * @Title: searchRecompenseForMonitor
	 * @Description: 查询理赔监控
	 * @param @param condition
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	List<RecompenseApplication> searchRecompenseForMonitor(
			RecompenseSearchCondition condition);

	/**
	 * 
	 * @Title: getRecompenseCountForMonitor
	 * @Description: 理赔监控计数
	 * @param @param condition
	 * @param @return
	 * @return int 返回类型
	 * @throws
	 */
	int getRecompenseCountForMonitor(RecompenseSearchCondition condition);

	/**
	 * 
	 * @Title: generateRecompenseForReport
	 * @Description: 生成报表时间
	 * @param @param userId
	 * @param @param oldStatus
	 * @param @param newStatus
	 * @param @return
	 * @return RecompenseApplication 返回类型
	 * @throws
	 */
	RecompenseApplication generateRecompenseForReport(String userId,
			String oldStatus, String newStatus);

	/**
	 * 
	 * @Title: assignDeptCharge
	 * @Description: 分配部门费用和责任部门
	 * @param @param app
	 * @param @param oldApp
	 * @param @return
	 * @return RecompenseApplication 返回类型
	 * @throws
	 */
	RecompenseApplication assignDeptCharge(RecompenseApplication app,
			RecompenseApplication oldApp);

	/**
	 * 
	 * @Title: getCustomerByNum
	 * @Description: 通过客户编码获取客户
	 * @param @param custNumber
	 * @param @return
	 * @return Member 返回类型
	 * @throws
	 */
	Member getCustomerByNum(String custNumber);

	/**
	 * 
	 * @Title: getOaWorkflowByRecompenseId
	 * @Description: 查询理赔单工作流
	 * @param @param recompenseId
	 * @param @return
	 * @return List<OAWorkflow> 返回类型
	 * @throws
	 */
	List<OAWorkflow> getOaWorkflowByRecompenseId(String recompenseId);

	/**
	 * 
	 * <p>
	 * Description:生成待办<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21 void
	 */

	void generateTodoReminder();

	/**
	 * 
	 * <p>
	 * Description:查询付款中的理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param startDate
	 * @param endDate
	 * @return List<String>
	 */

	List<String> getRecompenseInPayment(Date startDate, Date endDate);

	/**
	 * 
	 * <p>
	 * Description:查询付款完成的理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param startDate
	 * @param endDate
	 * @return List<String>
	 */

	List<String> getRecompensePaymented(Date startDate, Date endDate);

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
	 * Description:查询付款记录<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @return List<Payment>
	 */
	public List<Payment> searchPaymentHistoryByRecompenseId(String recompenseId);
	/**
	 * 
	 * @Title: paymentRefuse
	 * @Description: 付款失败
	 * @param @param voucherNum
	 * @return void 返回类型
	 * @throws
	 */
	public void paymentRefuse(String voucherNum);

	/**
	 * 
	 * @Title: PaymentApprove
	 * @Description: 付款成功
	 * @param @param voucherNum
	 * @return void 返回类型
	 * @throws
	 */
	public void paymentApprove(String voucherNum);
	
	/**
	 * 
	 * @Description: 查询foss运单参数
	 * @author huangzhanming
	 * @param waybillNum
	 * @return
	 * @return FossWaybillInfo
	 * @date 2013-3-19 上午10:44:23
	 * @version V1.0
	 */
	FossWaybillInfo getFossWaybillInfo(String waybillNum);

	/**
	 * @Description: 查询运单理赔信息
	 * @author huaoguo
	 * @param waybillNum
	 * @return GetRecompenseByWayBill
	 * @date 2013-4-22 上午11:09
	 * @version V1.0
	 */
	GetRecompenseByWayBill getRecompenseByWayBill(String waybillNum);

	 List<String> findCreateCustList(String type);

	 boolean insertCreateCust(String waybillnum, String type);

	 boolean updateCreateCust(String waybillnum, String type);
	 
	 
	
	
	
	
	/**
	 * 理赔监控，获取短信接收人
	 * @param recompenseIdList 理赔ID集合
	 * @param noticeTypes 通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiver(List<String> recompenseIdList, String noticeTypes);
	
	/**
	 * 理赔监控，短信发送模板
	 * @param status 处理状态
	 * @param recompenseNum 运单号
	 * @param reportDeptName 营业部名称
	 * @param recompenseType 理赔类型
	 * @param noticeTypes 通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 * @return 返回短信发送模板
	 */
	public String getSMSTemplate(String status, String recompenseNum, String reportDeptName, String recompenseType, String noticeTypes);
	
	/**
	 * 理赔监控，短信发送模板(多条短信发送模板)
	 * @param status 处理状态
	 * @param noticeTypes 通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 * @return
	 */
	public String getSMSTemplateMore(String status, String noticeTypes);
	
	/**
	 * 理赔监控，发送短信
	 * @param messageInfos 短信实体
	 */
	public void sendSmsInfo(List<CellphoneMessageInfo> messageInfos);
	
	/**
	 * 理赔监控，发送短信(多条理赔短信发送)
	 * @param messageInfos 短信实体
	 * @param status 处理状态
	 * @param noticeTypes 通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 */
	public void sendSmsInfoMore(List<CellphoneMessageInfo> messageInfos, String status, String noticeTypes);
	
	/**
	 * 理赔监控短信发送模板，获取处理天数
	 * @param recompenseNum 运单号
	 * @param recompenseType 理赔类型
	 * @return 返回处理天数
	 */
	public String getRecDuration(String recompenseNum, String recompenseType);
	
	/**
	 * 
	 * @description 理赔处理提交审批.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param reportManCode
	 * 
	 * @param commiter
	 * 
	 * @param 理赔对象
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午11:38:29
	 */
	boolean submitRecompenseOaApproval(RecompenseApplication app,
			User commiter, String reportManCode);
	
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
	Map<String, Object> searchRecompenseHistoryList(String phone, int limit,
			int start);
	/**
	 * 		
	 * <p>
	 * Description:在线理赔查询<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-11-5
	 * @param onlineApplyCondition
	 *            在线理理赔查询条件
	 * @param limit
	 *            限制
	 * @param start
	 *            开始
	 * @return map
	 */
	Map<String,Object> searchOnlineApply(OnlineApplyCondition condition,int limit,int start);
	/**
	 * 		
	 * <p>
	 * Description:在线理赔监控单条短信发送获得短信发送人<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-11-5
	 * @param onlineApplyCondition
	 *            在线理理赔查询条件
	 * @param limit
	 *            限制
	 * @param start
	 *            开始
	 * @return map
	 */
	List<RecSmsInformation> searchOnlineApplyPerson(List<String> waybillNumberList,String noticeTypes);
	
	/**
	 * 运输类型转换中文至英文
	 * @author andy
	 * @version 0.1 2013-11-02
	 * @param app
	 * @return RecompenseApplication
	 */
	RecompenseApplication replaceTranType(RecompenseApplication app);
	
	/**
	 * 
	 * <p>
	 * Description:在线理赔监控催办
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-11-13上午9:37:24
	 * @param waybillnumber
	 * void
	 * @update 2013-11-13上午9:37:24
	 */
	void pressDoForOnline(String waybillnumber);

	/**
	 * <p>
	 * Description:在线理赔监控查看详情
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-11-16下午4:53:30
	 * @param recompenseId
	 * @param user
	 * @return
	 * RecompenseApplication
	 * @update 2013-11-16下午4:53:30
	 */
	RecompenseApplication lookUpOnlineApplyDetail(String recompenseId, User user);
	
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
}