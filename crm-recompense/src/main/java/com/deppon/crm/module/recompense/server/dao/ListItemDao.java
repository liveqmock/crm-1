/**   
 * @title RecompenseItemDaoJdbcImpl.java
 * @package com.deppon.crm.recompense.dao.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-2-9 下午1:34:18
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.Balance;
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
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;

/**
 * @description 实现小列表的持久化
 * @author 潘光均
 * @version 0.1 2012-2-9
 * @date 2012-2-9
 */

public interface ListItemDao {
	/**
	 * @description 实现货物托运清单的保存.
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param goodsTrans
	 *            需保存的货物托运清单
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午16:19
	 */
	GoodsTrans insertGoodsTrans(GoodsTrans goodsTrans);

	/**
	 * @description 实现货物托运清单列表的保存.
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param appId
	 * 
	 * @param goodsTrans
	 *            需保存的货物托运清单列表
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午16:19
	 */
	boolean insertGoodsTrans(String appId, List<GoodsTrans> goodsTrans);

	/**
	 * @description 根据ID查询货物托运清单.
	 * @author 潘光均
	 * @version 0.1
	 * @param id
	 *            货物托运清单ID
	 * @date 2012-1-31
	 * @return GoodsTrans
	 * @update 2012-1-31 下午16:40
	 */
	GoodsTrans getGoodsTransById(String id);

	/**
	 * @description 根据ID更新货物运输项.
	 * @author 潘光均
	 * @version 0.1
	 * @param trans
	 *            货物托运
	 * @date 2012-1-31
	 * @return boolean
	 * @update 2012-1-31 下午16:40
	 */
	boolean updateGoodsTransById(GoodsTrans trans);

	/**
	 * 
	 * <p>
	 * Description:更新货物运输项.<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param goodsTranss
	 * @return boolean
	 */

	boolean updateGoodsTrans(List<GoodsTrans> goodsTranss);

	/**
	 * @description 查询所有货物运输项
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<GoodsTrans>
	 * @update 2012-2-3 下午4:05:02
	 */
	List<GoodsTrans> getGoodsTransByRecompenseId(String recompeneId);

	/**
	 * @description 根据理赔单id删除所有货物运输项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔单ID
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:11:06
	 */
	boolean deleteGoodsTransByRecompenseId(String recompenseId);

	/**
	 * @description 查询所有奖励项
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<AwardItem>
	 * @update 2012-2-3 下午4:43:05
	 */
	List<AwardItem> getAwardItemByRecompenseId(String recompeneId);

	/**
	 * @description 根据理赔单id所有奖励项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔的id
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:48:00
	 */
	boolean deleteAwardItemByRecompenseId(String recompenseId);

	/**
	 * @description 根据id更新奖罚项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔的id
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:48:00
	 */
	boolean updateAwardItemById(AwardItem item);

	/**
	 * 
	 * @description 批量更新奖罚项.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param 奖罚项LIST
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午9:52:49
	 */
	boolean updateAwardItem(List<AwardItem> awardItemList);

	/**
	 * @description 实现奖罚明细的保存.
	 * @author 潘光均
	 * @version 0.1 2012-1-30
	 * @param app
	 *            需保存的奖罚明细
	 * @date 2012-1-30
	 * @return 是否保存成功
	 * @update 2012-1-30 下午15:05
	 */
	AwardItem insertAwardItem(AwardItem awardItem);

	/**
	 * @description 实现奖罚明细的列表保存.
	 * @author 潘光均
	 * @version 0.1 2012-1-30
	 * @param appId
	 * 
	 * @param app
	 *            需保存的奖罚明细列表
	 * @date 2012-1-30
	 * @return 是否保存成功
	 * @update 2012-1-30 下午15:05
	 */
	boolean insertAwardItem(String appId, List<AwardItem> awardItems);

	/**
	 * @description 根据ID查询奖罚明细.
	 * @author 潘光均
	 * @version 0.1 2012-1-30
	 * @param id
	 *            奖罚明细ID
	 * @date 2012-1-30
	 * @return 查询结果
	 * @update 2012-1-30 下午15:25
	 */
	AwardItem getAwardItemById(String id);

	/**
	 * @description 根据ID查询入部门费用信息
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param id
	 *            入部门费用信息id
	 * @date 2012-1-31
	 * @return 入部门费用信息
	 * @update 2012-1-31 下午15:16
	 */
	DeptCharge getDeptChargeById(String id);

	/**
	 * @description 新增入部门费用
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param id
	 *            入部门费用信息
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午15:09
	 */
	DeptCharge insertDeptCharge(DeptCharge deptCharge);

	/**
	 * @description 批量新增入部门费用
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param appId
	 * 
	 * @param deptCharges
	 *            入部门费用信息列表
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午15:09
	 */
	boolean insertDeptCharge(String appId, List<DeptCharge> deptCharges);

	/**
	 * @description 更新部门费用
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param deptCharge
	 *            入部门费用信息
	 * @date 2012-1-31
	 * @return 是否更新成功
	 * @update 2012-1-31 下午15:09
	 */
	boolean updateDeptChargeById(DeptCharge deptCharge);

	/**
	 * @description 批量更新部门费用
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param deptCharges
	 *            入部门费用信息列表
	 * @date 2012-1-31
	 * @return 是否更新成功
	 * @update 2012-1-31 下午15:09
	 */
	boolean updateDeptChargesById(List<DeptCharge> deptCharges);

	/**
	 * @description 查询所有部门费用项
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<DeptCharge>
	 * @update 2012-2-3 下午4:49:36
	 */
	List<DeptCharge> getDeptChargeByRecompenseId(String recompeneId);

	/**
	 * @description 根据理赔单id所有入部门费用.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔的id
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:48:00
	 */
	boolean deleteDeptChargeByRecompenseId(String recompenseId);

	/**
	 * @description 查询所有出险项
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<IssueItem>
	 * @update 2012-2-3 下午4:16:59
	 */
	List<IssueItem> getIssueItemByRecompenseId(String recompenseId);

	/**
	 * @description 根据id更新出险项
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param IssueItem
	 *            item
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:16:59
	 */
	boolean updateIssueItemById(IssueItem item);

	/**
	 * 
	 * <p>
	 * Description:更新问题描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param issueItems
	 * @return boolean
	 */

	boolean updateIssueItem(List<IssueItem> issueItems);

	/**
	 * @description 删除所有出险项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔单ID
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:24:50
	 */
	boolean deleteIssueItemByRecompenseId(String recompenseId);

	/**
	 * @description 实现出险信息的保存.
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param issueItem
	 *            需保存的出险信息
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午17:12
	 */
	IssueItem insertIssueItem(IssueItem issueItem);

	/**
	 * @description 实现出险信息的批量保存.
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param appId
	 * 
	 * @param issueItems
	 *            需保存的出险信息列表
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午17:12
	 */
	boolean insertIssueItem(String appId, List<IssueItem> issueItems);

	/**
	 * @description 根据ID出险出险信息.
	 * @author 潘光均
	 * @version 0.1
	 * @param id
	 *            出险信息ID
	 * @date 2012-1-31
	 * @return 查询结果
	 * @update 2012-1-31 下午17:28
	 */
	IssueItem getIssueItemById(String id);

	/**
	 * @description 新增跟进信息
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param id
	 *            跟进信息
	 * @date 2012-1-31
	 * @return Message
	 * @update 2012-1-31 下午18:11
	 */
	Message insertMessage(Message message);

	/**
	 * @description 更新跟进信息
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param id
	 *            跟进信息
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午18:11
	 */
	boolean updateMessage(Message message);

	/**
	 * @description 批量新增跟进信息
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param messages
	 *            跟进信息列表
	 * @date 2012-1-31
	 * @return 是否保存成功
	 * @update 2012-1-31 下午18:11
	 */
	boolean insertMessage(List<Message> messages);

	/**
	 * @description 查询所有消息项
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<Message>
	 * @update 2012-2-3 下午5:23:07
	 */
	List<Message> getMessageByRecompenseId(String recompenseId);

	/**
	 * @description 根据理赔单id删除所有跟进项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔单id
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午5:23:41
	 */
	boolean deleteMessageByRecompenseId(String recompenseId);

	/**
	 * @description 新增消息提醒
	 * @author 潘光均
	 * @version 0.1 2012-2-1
	 * @param messageReminder
	 *            消息提醒信息
	 * @date 2012-2-01
	 * @return MessageReminder
	 * @update 2012-2-01 上午9:13
	 */
	MessageReminder insertMessageReminder(MessageReminder messageReminder);

	/**
	 * @description 更新消息提醒
	 * @author 潘光均
	 * @version 0.1 2012-2-1
	 * @param messageReminder
	 *            消息提醒信息
	 * @date 2012-2-01
	 * @return boolean
	 * @update 2012-2-01 上午9:13
	 */
	boolean updateMessageReminder(MessageReminder messageReminder);

	/**
	 * 
	 * @description 批量更新消息提醒.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param 消息提醒LIST
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午9:58:50
	 */
	boolean updateMessageReminder(List<MessageReminder> messageReminderList);

	/**
	 * @description 新增消息提醒列表
	 * @author 潘光均
	 * @version 0.1 2012-2-1
	 * @param appId
	 * 
	 * @param messageReminders
	 *            消息提醒信息列表
	 * @date 2012-2-01
	 * @return 是否保存成功
	 * @update 2012-2-01 上午9:13
	 */
	boolean insertMessageReminder(String appId,
			List<MessageReminder> messageReminders);

	/**
	 * @description 根据ID查询消息提醒信息
	 * @author 潘光均
	 * @version 0.1 2012-2-01
	 * @param id
	 *            消息提醒id
	 * @date 2012-2-01
	 * @return 消息提醒信息
	 * @update 2012-2-01 上午
	 */
	MessageReminder getMessageReminderById(String id);

	/**
	 * @description 查询所有消息提醒项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<MessageReminder>
	 * @update 2012-2-3 下午5:35:42
	 */
	List<MessageReminder> getMessageReminderByRecompenseId(String recompenseId);

	/**
	 * @description 根据理赔单id删除所有消息提醒.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔单id
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午5:23:41
	 */
	boolean deleteMessageReminderByRecompenseId(String recompenseId);

	/**
	 * @description 保存付款单信息
	 * @author 潘光均
	 * @version 0.1 2012-2-1
	 * @param paymentBill
	 *            付款单信息
	 * @date 2012-2-1
	 * @return 是否保存成功
	 * @update 2012-2-1 上午11:31
	 */
	PaymentBill insertPaymentBill(PaymentBill paymentBill);

	/**
	 * @description 更新付款单信息
	 * @author 潘光均
	 * @version 0.1 2012-2-1
	 * @param paymentBill
	 *            付款单信息
	 * @date 2012-2-1
	 * @return 是否更新成功
	 * @update 2012-2-1 上午11:31
	 */
	boolean updatePaymentBill(PaymentBill paymentBill);

	/**
	 * @description 根据ID查询付款单
	 * @author 潘光均
	 * @version 0.1 2012-2-01
	 * @param id
	 *            付款单id
	 * @date 2012-2-01
	 * @return 付款单信息
	 * @update 2012-2-01 上午12:01
	 */
	PaymentBill getPaymentBillById(String id);

	/**
	 * @description 查询所有付款单项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<Message>
	 * @update 2012-2-3 下午5:24:49
	 */
	List<PaymentBill> getPaymentBillByRecompenseId(String recompenseId);

	/**
	 * @description 保存附件清单.
	 * @author 潘光均
	 * @version 0.1 2012-2-01
	 * @param recompenseAttachment
	 *            需保存的附件清单
	 * @date 2012-2-01
	 * @return 是否保存成功
	 * @update 2012-2-01 下午14:26
	 */
	RecompenseAttachment insertRecompenseAttachment(
			RecompenseAttachment recompenseAttachment);

	/**
	 * @description 更新附件清单.
	 * @author 潘光均
	 * @version 0.1 2012-2-01
	 * @param recompenseAttachment
	 *            需更新的附件清单
	 * @date 2012-2-01
	 * @return boolean
	 * @update 2012-2-01 下午14:26
	 */
	boolean updateRecompenseAttachment(RecompenseAttachment recompenseAttachment);

	/**
	 * @description 批量保存附件清单.
	 * @author 潘光均
	 * @version 0.1 2012-2-01
	 * @param appId
	 * 
	 * @param recompenseAttachments
	 *            需保存的附件清单列表
	 * @date 2012-2-01
	 * @return 是否保存成功
	 * @update 2012-2-01 下午14:26
	 */
	boolean insertRecompenseAttachment(String appId,
			List<RecompenseAttachment> recompenseAttachments);

	/**
	 * @description 根据ID查询附件清单.
	 * @author 潘光均
	 * @version 0.1
	 * @param id
	 *            附件清单ID
	 * @date 2012-2-01
	 * @return 查询结果
	 * @update 2012-2-01 下午14:28
	 */
	RecompenseAttachment getRecompenseAttachmentById(String id);

	/**
	 * @description 根据理赔单删除所有附件项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔单id
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:31:26
	 */
	boolean deleteAttachmentByRecompenseId(String recompenseId);

	/**
	 * 
	 * <p>
	 * Description:删除理赔附件项<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param recompenseAttachments
	 * @return boolean
	 */

	boolean deleteRecompenseAttachment(
			List<RecompenseAttachment> recompenseAttachments);

	/**
	 * @author 潘光均
	 * @version 0.1 2012-2-1
	 * @param recalledCompensation
	 *            追偿信息
	 * @date 2012-2-1
	 * @return 是否保存成功
	 * @update 2012-2-1 下午15:32
	 */
	RecalledCompensation insertRecalledCompensation(
			RecalledCompensation recalledCompensation);

	/**
	 * 
	 * @description 追偿信息DAO修改接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-28
	 * @param 追偿信息对象
	 * @date 2012-3-28
	 * @return int：1成功，非1失败
	 * @update 2012-3-28 上午10:06:10
	 */
	int updateRecalledCompensation(RecalledCompensation recalledCompensation);

	/**
	 * @description 根据ID查询追偿信息
	 * @author 潘光均
	 * @version 0.1 2012-2-01
	 * @param id
	 *            追偿信息id
	 * @date 2012-2-01
	 * @return 追偿信息
	 * @update 2012-2-01 下午15:05
	 */
	RecalledCompensation getRecalledCompensationById(String id);

	/**
	 * @description 查询所有追回项.
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<RecalledCompensation>
	 * @update 2012-2-3 下午5:32:03
	 */
	List<RecalledCompensation> getRecalledCompensationByRecompenseId(
			String recompenseId);

	/**
	 * @description 新增责任部门项.
	 * @author 潘光均
	 * @version 0.1 2012-2-11
	 * @param dept
	 *            :责任部门
	 * @date 2012-2-11
	 * @return ResponsibleDept
	 * @update 2012-2-11 下午3:05:00
	 */
	ResponsibleDept insertResponsibleDept(ResponsibleDept dept);

	/**
	 * @description 修改责任部门项.
	 * @author 潘光均
	 * @version 0.1 2012-2-11
	 * @param dept
	 *            :责任部门
	 * @date 2012-2-11
	 * @return boolean
	 * @update 2012-2-11 下午3:05:00
	 */
	boolean updateResponsibleDept(ResponsibleDept dept);

	/**
	 * 
	 * @description 批量修改责任部门.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param 责任部门LIST
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午10:01:37
	 */
	boolean updateResponsibleDept(List<ResponsibleDept> responsibleDeptList);

	/**
	 * @description 批量新增责任部门项.
	 * @author 潘光均
	 * @version 0.1 2012-2-11
	 * @param appId
	 * 
	 * @param dept
	 *            :责任部门
	 * @date 2012-2-11
	 * @return ResponsibleDept
	 * @update 2012-2-11 下午3:05:00
	 */
	boolean insertResponsibleDept(String appId, List<ResponsibleDept> dept);

	/**
	 * @description 根据理赔单id查询责任部门项.
	 * @author 潘光均
	 * @version 0.1 2012-2-11
	 * @param recompenseId
	 *            :理赔单id
	 * @date 2012-2-11
	 * @return ResponsibleDept
	 * @update 2012-2-11 下午3:05:00
	 */
	List<ResponsibleDept> getResponsibleDeptByRecompenseId(String recompenseId);

	/**
	 * @description 根据理赔单id删除责任部门项.
	 * @author 潘光均
	 * @version 0.1 2012-2-11
	 * @param recompenseId
	 *            :理赔单id
	 * @date 2012-2-11
	 * @return ResponsibleDept
	 * @update 2012-2-11 下午3:05:00
	 */
	boolean deleteResponsibleDeptByRecompenseId(String recompenseId);

	/**
	 * @description 保存工作流.
	 * @author 潘光均
	 * @version 0.1 2012-2-15
	 * @param flow
	 *            :待保存工作流
	 * @date 2012-2-15
	 * @return Workflow
	 * @update 2012-2-15 上午9:48:38
	 */
	OAWorkflow insertWorkflow(OAWorkflow flow);

	/**
	 * @description 通过凭证号工作流.
	 * @author 潘光均
	 * @version 0.1 2012-2-15
	 * @param recompenseNum
	 *            :凭证号
	 * @date 2012-2-15
	 * @return List<Workflow>
	 * @update 2012-2-15 上午9:48:38
	 */
	List<OAWorkflow> getWorkflowByRecompenseId(String recompenseNum);

	/**
	 * 
	 * <p>
	 * Description:根据工作流号获得工作流对象<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param workflowNum
	 * @return OAWorkflow
	 */
	OAWorkflow getWorkflowByWorkflowNum(String workflowNum);

	/**
	 * 
	 * @description 查询理赔单当前类型的最后一个工作流.
	 * @author 安小虎
	 * @version 0.1 2012-4-20
	 * @param 理赔单ID
	 * @param 类型
	 * @date 2012-4-20
	 * @return OAWorkflow
	 * @update 2012-4-20 上午8:43:59
	 */
	OAWorkflow getWorkflowByAppIdAndType(String appId, String type);

	/**
	 * @description 通过id删除货物运输项.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:31:47
	 */
	boolean deleteGoodsTransById(String id);

	/**
	 * 
	 * <p>
	 * Description:删除货物运输项<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param goodsTranss
	 * @return boolean
	 */

	boolean deleteGoodsTrans(List<GoodsTrans> goodsTranss);

	/**
	 * @description 通过id删除出险项.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:32:12
	 */
	boolean deleteIssueItemById(String id);

	/**
	 * 
	 * <p>
	 * Description:删除问题<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param issueItems
	 * @return boolean
	 */

	boolean deleteIssueItem(List<IssueItem> issueItems);

	/**
	 * @description 通过id上次消息提醒项
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:34:57
	 */
	boolean deleteMessageRemainderById(String id);

	/**
	 * 
	 * @description 批量删除消息提醒项.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param 消息提醒项LIST
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午9:55:47
	 */
	boolean deleteMessageRemainder(List<MessageReminder> messageReminderList);

	/**
	 * @description 通过id删除跟进信息.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:38:33
	 */
	boolean deleteMessageById(String id);

	/**
	 * @description 通过id删除工作流.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:39:04
	 */
	boolean deleteOAWorkflowById(String id);

	/**
	 * 
	 * <p>
	 * Description:根据理赔id删除OA工作流<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param recompenseId
	 * @return boolean
	 */

	boolean deleteOAWorkflowByRecompenseId(String recompenseId);

	/**
	 * @description 通过id删除付款单项.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:39:54
	 */
	boolean deletePaymentBillById(String id);

	/**
	 * @description 通过理赔单id删除付款单项.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:39:54
	 */
	boolean deletePaymentByRecompenseId(String recompenseId);

	/**
	 * @description 通过id删除追回项.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:41:27
	 */
	boolean deleteRecallCompById(String id);

	/**
	 * @description 通过理赔单id删除追回项.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:41:27
	 */
	boolean deleteRecallCompByRecompenseId(String id);

	/**
	 * @description 通过id删除附件项
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:42:05
	 */
	boolean deleteRecompAttById(String id);

	/**
	 * @description 通过id删除责任部门.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午1:42:45
	 */
	boolean deleteResponsibleDeptById(String id);

	/**
	 * 
	 * @description 批量删除责任部门.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param 责任部门LIST
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午10:03:11
	 */
	boolean deleteResponsibleDept(List<ResponsibleDept> responsibleDeptList);

	/**
	 * @description 根据id删除部门费用
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午2:15:11
	 */
	boolean deleteDeptChargeById(String id);

	/**
	 * 
	 * @description 批量删除部门费用.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param 部门费用LIST
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午10:05:42
	 */
	boolean deleteDeptCharge(List<DeptCharge> deptChargeList);

	/**
	 * @description 通过id删除奖励项.
	 * @author 潘光均
	 * @version 0.1 2012-2-17
	 * @param id
	 * @date 2012-2-17
	 * @return boolean
	 * @update 2012-2-17 下午2:40:42
	 */
	boolean deleteAwardItemById(String id);

	/**
	 * 
	 * @description 批量删除奖励项.
	 * @author 安小虎
	 * @version 0.1 2012-3-29
	 * @param 奖励项LIST
	 * @date 2012-3-29
	 * @return boolean：成功与否
	 * @update 2012-3-29 上午9:47:35
	 */
	boolean deleteAwardItem(List<AwardItem> awardItemList);

	/**
	 * @description 通过id删除理赔项
	 * @author 潘光均
	 * @version 0.1 2012-2-23
	 * @param String
	 *            :id
	 * @date 2012-2-23
	 * @return boolean
	 * @update 2012-2-23 上午8:48:48
	 */
	// boolean deleteRecompenseItemById(String id);
	/**
	 * @description 通过id查询根据信息.
	 * @author 潘光均
	 * @version 0.1 2012-2-23
	 * @param String
	 *            id
	 * @date 2012-2-23
	 * @return Message
	 * @update 2012-2-23 上午11:34:19
	 */
	Message getMessageById(String id);

	/**
	 * @description 根据理赔单id查询附件项目.
	 * @author 潘光均
	 * @version 0.1 2012-2-23
	 * @param String
	 *            recompenseId
	 * @date 2012-2-23
	 * @return List<RecompenseItem>
	 * @update 2012-2-23 上午11:34:42
	 */
	List<RecompenseAttachment> getAttachmentByRecompenseId(String recompenseId);

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
	boolean insertOverpay(Overpay overpay);
	
	void updateOverpay(Overpay overpay);

	boolean deleteOverpayByRecompenseId(String recompenseId);

	/**
	 * 
	 * @description 根据理赔单ID查找对应的多赔记录.
	 * @author 安小虎
	 * @version 0.1 2012-3-31
	 * @param 理赔单ID
	 * @date 2012-3-31
	 * @return 多赔集合
	 * @update 2012-3-31 下午5:06:23
	 */
	List<Overpay> getOverpayByRecompenseId(String recompenseId);

	/**
	 * 
	 * @description 在线理赔DAO新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-4-1
	 * @param 在线理赔对象
	 * @date 2012-4-1
	 * @return boolean：成功与否
	 * @update 2012-4-1 上午9:13:40
	 */
	boolean insertOnlineApply(OnlineApply onlineApply);

	/**
	 * 
	 * @description 在线理赔DAO修改接口.
	 * @author 安小虎
	 * @version 0.1 2012-4-1
	 * @param 在线理赔对象
	 * @date 2012-4-1
	 * @return boolean：成功与否
	 * @update 2012-4-1 下午3:37:57
	 */
	boolean updateOnlineApply(OnlineApply onlineApply);

	/**
	 * 
	 * @description 在线理赔根据ID查询接口.
	 * @author 安小虎
	 * @version 0.1 2012-4-1
	 * @param id
	 * @date 2012-4-1
	 * @return 在线理赔对象
	 * @update 2012-4-1 下午5:17:26
	 */
	OnlineApply getOnlineApplyById(String id);

	/**
	 * 
	 * @description 在线理赔根据运单号查询.
	 * @author 安小虎
	 * @version 0.1 2012-4-1
	 * @param 运单号
	 * @date 2012-4-1
	 * @return 在线理赔对象集合
	 * @update 2012-4-1 下午3:38:16
	 */
	List<OnlineApply> getOnlineApplyByWaybillNum(String waybillNum);

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
	 * @description 冲帐新增.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param 冲帐对象
	 * @date 2012-4-10
	 * @return boolean：成功与否
	 * @update 2012-4-10 上午11:33:21
	 */
	boolean insertBalance(Balance balance);

	/**
	 * 
	 * @description 冲帐批量新增.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param 冲帐对象集合
	 * @date 2012-4-10
	 * @return boolean：成功与否
	 * @update 2012-4-10 上午11:33:26
	 */
	boolean insertBalance(List<Balance> balanceList);

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
	 * @description 得到理赔单的已冲帐金额.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param 理赔单ID
	 * @date 2012-4-10
	 * @return 该理赔单的已冲帐金额
	 * @update 2012-4-10 下午3:47:33
	 */
	Double getBalanceAmountCountByRecompenseId(String recompenseId);

	/**
	 * 
	 * <p>
	 * Description:更新工作流<br />
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
	 * <p>
	 * Description:根据工作流号获取多赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param workflowNum
	 * @param recompenseId
	 * @return Overpay
	 */

	Overpay getOverpayByWorkflowNum(String workflowNum, String recompenseId);

	/**
	 * @param rejectReason
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
	 * Description:根据官网用户和运单号查询在线理赔br />
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
	 * Description:根据条件或者在线理赔统计<br />
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
	 * Description：保存账号信息<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-31 void
	 * @return
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
	 * Description:根据理赔id及付款状态查找付款单<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param recompenseId
	 * @param paymentStatus
	 * @return Payment
	 */
	public Payment findPaymentByRecompenseIdAndStatus(String recompenseId,
			String paymentStatus);

	public List<String> findCreateCustList(String type);

	public boolean insertCreateCust(String waybillnum, String type);

	public boolean updateCreateCust(String waybillnum, String type);
	 /**
	 * 
	 * <p>
	 * Description:在线理赔查询<br />
	 * </p>
	 * @author 杨伟
	 * @version 0.1 2013-11-5
	 * @param condition 查询条件
	 * @param limit 限制
	 * @param start 开始业
	 * void
	 */
	Map<String,Object> searchOnlineApply(OnlineApplyCondition condition,int limit,int start);
	
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
	 * Description:理赔详情<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-14
	 * @param workNumber
	 * @return
	 * Overpay
	 */
	Overpay getDetailOverpayByWorkNumber(String workNumber);

}
