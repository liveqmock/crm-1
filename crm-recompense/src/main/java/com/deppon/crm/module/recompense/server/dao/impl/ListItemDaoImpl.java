/**   
 * @title ListItemDaoJdbcImpl.java
 * @package com.deppon.crm.recompense.dao.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-2-10 上午11:00:52
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.recompense.server.dao.ListItemDao;
import com.deppon.crm.module.recompense.server.utils.NullAndEmptyValidator;
import com.deppon.crm.module.recompense.shared.domain.OnlineApplyCondition;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.Balance;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.PaymentBill;
import com.deppon.crm.module.recompense.shared.domain.RecalledCompensation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * @description 实现xxx
 * @author 潘光均
 * @version 0.1 2012-2-10
 * @date 2012-2-10
 */

public class ListItemDaoImpl extends iBatis3DaoImpl implements ListItemDao {
	// 命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.recompense.shared.domain.ListItem.";
	// 保存多赔
	private static final String INSERT_OVERPAY = "insertOverpay";
	// 修改多赔
	private static final String UPDATE_OVERPAY = "updateOverpay";
	// 根据理赔id获得多赔
	private static final String GETOVERPAY_BYRECOMPENSEID = "getOverpayByRecompenseId";
	// 插入在线理赔
	private static final String INSERT_ONLINEAPPLY = "insertOnlineApply";
	// 更新在线理赔
	private static final String UPDATE_ONLINEAPPLY = "updateOnlineApply";
	// 根据理赔id在线理赔状态
	private static final String UPDATE_ONLINEAPPLY_BYRECOMPENSEID = "updateOnlineApplyStatusByRecompenseId";
	// 根据理赔id在线理赔信息
	private static final String UPDATE_ONLINEAPPLY_INFO_BYRECOMPENSEID = "updateOnlineApplyInfoByRecompenseId";
	// 根据id获得在线理赔
	private static final String GET_ONLINEAPPLY_BYID = "getOnlineApplyById";
	// 根据运单号或者在线理赔
	private static final String GET_ONLINEAPPLY_BYWAYBILLNUM = "getOnlineApplyByWaybillNum";
	// 根据条件查询在线理赔
	private static final String SEARCH_ONLINEAPPLY_BYCONDITION = "searchOnlineApplyByCondition";
	// 保存冲账金额
	private static final String INSERT_BALANCE = "insertBalance";
	// 根据条件查询冲账金额
	private static final String SEARCH_BALANCE_BYCONDITION = "searchBalanceByCondition";
	// 根据理赔查询冲账金额
	private static final String GET_BALANCEAMOUNTCOUNT_BYRECOMPENSEID = "getBalanceAmountCountByRecompenseId";
	// 根据条件查询在线理赔数
	private static final String GET_ONLINEAPPLYCOUNT_BYCONDITION = "getOnlineApplyCountByCondition";
	// 根据id查询在线理赔
	private static final String GET_ONLINEAPPLY_BYRECOMPENSEID = "getOnlineApplyByRecompenseId";
	private static final String SAVE_PAYMENT = "insertPayment";
	private static final String UPDATE_PAYMENT = "updatePaymentStatus";
	private static final String SEARCH_PAYMENTHISTORY = "searchPaymentHistory";
	private static final String FIND_PAYMENT = "findPaymentById";
	//查询在线理赔
	private static final String SEARCH_ONLINEAPPLY="searchOnlineApply";
	//查询在线理赔总数
	private static final String SEARCH_ONLINEAPPLY_COUNT="searchOnlineApplyCount";
	
	//多赔详情	
	private static final String GET_DETAILOVERPAY_BYWORKNUMBER="getDetailOverpayByWorkNumber";

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
	@Override
	public GoodsTrans insertGoodsTrans(GoodsTrans goodsTrans) {
		NullAndEmptyValidator.objectNullPreform(goodsTrans);
		getSqlSession().insert(NAMESPACE + "insertGoodsTrans", goodsTrans);
		return goodsTrans;
	}

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
	@Override
	public GoodsTrans getGoodsTransById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (GoodsTrans) getSqlSession().selectOne(
				NAMESPACE + "queryGoodsTransById", id);
	}

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

	@SuppressWarnings("unchecked")
	@Override
	public List<AwardItem> getAwardItemByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<AwardItem>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryAwardItemByRecompenseId", recompenseId);
	}

	/**
	 * @description 保存奖罚明细
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param recompenseId
	 *            :理赔的id
	 * @date 2012-2-3
	 * @return boolean
	 * @update 2012-2-3 下午4:48:00
	 */
	@Override
	public AwardItem insertAwardItem(AwardItem awardItem) {
		NullAndEmptyValidator.objectNullPreform(awardItem);
		NullAndEmptyValidator.stringNullPreform(awardItem.getId());
		getSqlSession().insert(NAMESPACE + "insertAwardItem", awardItem);
		return awardItem;
	}

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
	@Override
	public AwardItem getAwardItemById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (AwardItem) getSqlSession().selectOne(
				NAMESPACE + "queryAwardItemById", id);
	}

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
	@Override
	public DeptCharge getDeptChargeById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (DeptCharge) getSqlSession().selectOne(
				NAMESPACE + "queryDeptChargeById", id);
	}

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
	@Override
	public DeptCharge insertDeptCharge(DeptCharge deptCharge) {
		NullAndEmptyValidator.objectNullPreform(deptCharge);
		NullAndEmptyValidator.stringNullPreform(deptCharge.getId());
		getSqlSession().insert(NAMESPACE + "insertDeptCharge", deptCharge);
		return deptCharge;
	}

	/**
	 * @description 查询所有部门费用项
	 * @author 潘光均
	 * @version 0.1 2012-2-3
	 * @param false
	 * @date 2012-2-3
	 * @return List<DeptCharge>
	 * @update 2012-2-3 下午4:49:36
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptCharge> getDeptChargeByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<DeptCharge>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryDeptChargeByRecompenseId", recompenseId);
	}
	/**
	 * 
	 * <p>
	 * Description:查询所有出险项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompeneId
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IssueItem> getIssueItemByRecompenseId(String recompeneId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompeneId)) {
			return new ArrayList<IssueItem>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryIssueItemByRecompenseId", recompeneId);
	}

	/**
	 * 
	 * <p>
	 * Description:实现出险信息的保存<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param issueItem
	 * @return
	 *
	 */
	@Override
	public IssueItem insertIssueItem(IssueItem issueItem) {
		NullAndEmptyValidator.objectNullPreform(issueItem);
		NullAndEmptyValidator.stringNullPreform(issueItem.getId());
		getSqlSession().insert(NAMESPACE + "insertIssueItem", issueItem);
		return issueItem;
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID出险出险信息.<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 *
	 */
	@Override
	public IssueItem getIssueItemById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (IssueItem) getSqlSession().selectOne(
				NAMESPACE + "queryIssueItemById", id);
	}

	/**
	 * 
	 * <p>
	 * Description: 新增跟进信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param message
	 * @return
	 *
	 */
	@Override
	public Message insertMessage(Message message) {
		NullAndEmptyValidator.objectNullPreform(message);
		NullAndEmptyValidator.stringNullPreform(message.getId());
		getSqlSession().insert(NAMESPACE + "insertMessage", message);
		return message;
	}

	/**
	 * 
	 * <p>
	 * Description:查询所有消息项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public List<Message> getMessageByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<Message>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryMessageByRecompenseId", recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:新增消息提醒<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param messageReminder
	 * @return
	 *
	 */
	@Override
	public MessageReminder insertMessageReminder(MessageReminder messageReminder) {
		NullAndEmptyValidator.objectNullPreform(messageReminder);
		NullAndEmptyValidator.stringNullPreform(messageReminder.getId());
		getSqlSession().insert(NAMESPACE + "insertMessageReminder",
				messageReminder);
		return messageReminder;
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID查询消息提醒信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 *
	 */
	@Override
	public MessageReminder getMessageReminderById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (MessageReminder) getSqlSession().selectOne(
				NAMESPACE + "queryMessageReminderById", id);
	}

	/**
	 * 
	 * <p>
	 * Description:查询所有消息提醒项.<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public List<MessageReminder> getMessageReminderByRecompenseId(
			String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<MessageReminder>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryMessageReminderByRecompenseId", recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:保存付款单信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param paymentBill
	 * @return
	 *
	 */
	@Override
	public PaymentBill insertPaymentBill(PaymentBill paymentBill) {
		NullAndEmptyValidator.objectNullPreform(paymentBill);
		NullAndEmptyValidator.stringNullPreform(paymentBill.getId());
		getSqlSession().insert(NAMESPACE + "insertPaymentBill", paymentBill);
		return paymentBill;
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID查询付款单<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 *
	 */
	@Override
	public PaymentBill getPaymentBillById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (PaymentBill) getSqlSession().selectOne(
				NAMESPACE + "queryPaymentBillById", id);
	}

	/**
	 * 
	 * <p>
	 * Description:查询所有付款单项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public List<PaymentBill> getPaymentBillByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<PaymentBill>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryPaymentBillByRecompenseId", recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:保存附件清单.<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseAttachment
	 * @return
	 *
	 */
	@Override
	public RecompenseAttachment insertRecompenseAttachment(
			RecompenseAttachment recompenseAttachment) {
		NullAndEmptyValidator.objectNullPreform(recompenseAttachment);
		NullAndEmptyValidator.stringNullPreform(recompenseAttachment.getId());
		getSqlSession().insert(NAMESPACE + "insertAttachment",
				recompenseAttachment);
		return recompenseAttachment;
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID查询附件清单<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 *
	 */
	@Override
	public RecompenseAttachment getRecompenseAttachmentById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (RecompenseAttachment) getSqlSession().selectOne(
				NAMESPACE + "queryAttachmentById", id);
	}

	/**
	 * 
	 * <p>
	 * Description:追偿信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recalledCompensation
	 * @return
	 *
	 */
	@Override
	public RecalledCompensation insertRecalledCompensation(
			RecalledCompensation recalledCompensation) {
		NullAndEmptyValidator.objectNullPreform(recalledCompensation);
		NullAndEmptyValidator.stringNullPreform(recalledCompensation.getId());
		getSqlSession().insert(NAMESPACE + "insertRecalledComp",
				recalledCompensation);
		return recalledCompensation;
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID查询追偿信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 *
	 */
	@Override
	public RecalledCompensation getRecalledCompensationById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (RecalledCompensation) getSqlSession().selectOne(
				NAMESPACE + "queryRecalledCompById", id);
	}

	/**
	 * 
	 * <p>
	 * Description:查询所有追回项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public List<RecalledCompensation> getRecalledCompensationByRecompenseId(
			String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<RecalledCompensation>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryRecalledCompByRecompenseId", recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:查询所有货物运输项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public List<GoodsTrans> getGoodsTransByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<GoodsTrans>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryGoodsTransByRecompenseId", recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:实现货物托运清单列表的保存.<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param appId
	 * @param goodsTranss
	 * @return
	 *
	 */
	@Override
	public boolean insertGoodsTrans(String appId, List<GoodsTrans> goodsTranss) {
		for (int i = 0; i < goodsTranss.size(); i++) {
			GoodsTrans gt = goodsTranss.get(i);
			gt.setRecompenseId(appId);
			insertGoodsTrans(gt);
		}
		return true;
	}


	/**
	 * 
	 * <p>
	 * Description:根据理赔单id删除所有货物运输项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public boolean deleteGoodsTransByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteGoodsTransByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔单id所有奖励项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public boolean deleteAwardItemByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteAwardItemByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

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
	@Override
	public boolean insertAwardItem(String appId, List<AwardItem> awardItems) {
		for (int i = 0; i < awardItems.size(); i++) {
			AwardItem ai = awardItems.get(i);
			ai.setRecompenseId(appId);
			insertAwardItem(ai);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:新增入部门费用<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param appId
	 * @param deptCharges
	 * @return
	 *
	 */
	@Override
	public boolean insertDeptCharge(String appId, List<DeptCharge> deptCharges) {
		for (int i = 0; i < deptCharges.size(); i++) {
			DeptCharge dc = deptCharges.get(i);
			dc.setRecompenseId(appId);
			insertDeptCharge(dc);
		}
		return true;
	}

	/**
	 * @see com.deppon.crm.recompense.dao.ListItemDao#deleteDeptChargeByRecompenseId(java.lang.String)
	 */
	@Override
	public boolean deleteDeptChargeByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteDeptChargeByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @see com.deppon.crm.recompense.dao.ListItemDao#deleteIssueItemByRecompenseId(java.lang.String)
	 */
	@Override
	public boolean deleteIssueItemByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteIssueItemByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @see com.deppon.crm.recompense.dao.ListItemDao#insertIssueItem(String,
	 *      java.util.List)
	 */
	@Override
	public boolean insertIssueItem(String appId, List<IssueItem> issueItems) {
		for (int i = 0; i < issueItems.size(); i++) {
			IssueItem ii = issueItems.get(i);
			ii.setRecompenseId(appId);
			insertIssueItem(ii);
		}
		return true;
	}

	/**
	 * @see com.deppon.crm.recompense.dao.ListItemDao#insertMessage(java.util.List)
	 */
	@Override
	public boolean insertMessage(List<Message> messages) {
		for (int i = 0; i < messages.size(); i++) {
			insertMessage(messages.get(i));
		}
		return true;
	}

	/**
	 * @see com.deppon.crm.recompense.dao.ListItemDao#deleteMessageByRecompenseId(java.lang.String)
	 */
	@Override
	public boolean deleteMessageByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteMessageByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @see com.deppon.crm.recompense.dao.ListItemDao#insertMessageReminder(String,
	 *      java.util.List)
	 */
	@Override
	public boolean insertMessageReminder(String appId,
			List<MessageReminder> messageReminders) {
		for (int i = 0; i < messageReminders.size(); i++) {
			MessageReminder mr = messageReminders.get(i);
			mr.setRecompenseId(appId);
			insertMessageReminder(mr);
		}
		return true;
	}

	/**
	 * @see com.deppon.crm.recompense.dao.ListItemDao#insertRecompenseAttachment(String,
	 *      java.util.List)
	 */
	@Override
	public boolean insertRecompenseAttachment(String appId,
			List<RecompenseAttachment> recompenseAttachments) {
		for (int i = 0; i < recompenseAttachments.size(); i++) {
			RecompenseAttachment ra = recompenseAttachments.get(i);
			ra.setRecompenseId(appId);
			insertRecompenseAttachment(ra);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔单删除所有附件项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public boolean deleteAttachmentByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteAttachmentByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * Description:删除理赔附件项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseAttachments
	 * @return
	 *
	 */
	@Override
	public boolean deleteRecompenseAttachment(
			List<RecompenseAttachment> recompenseAttachments) {
		for (int i = 0; i < recompenseAttachments.size(); i++) {
			deleteAttachmentByRecompenseId(recompenseAttachments.get(i).getId());
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:新增责任部门项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param dept
	 * @return
	 *
	 */
	@Override
	public ResponsibleDept insertResponsibleDept(ResponsibleDept dept) {
		NullAndEmptyValidator.objectNullPreform(dept);
		NullAndEmptyValidator.stringNullPreform(dept.getId());
		getSqlSession().insert(NAMESPACE + "insertResponsibleDept", dept);
		return dept;
	}

	/**
	 * 
	 * <p>
	 * Description:批量新增责任部门项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param appId
	 * @param depts
	 * @return
	 *
	 */
	@Override
	public boolean insertResponsibleDept(String appId,
			List<ResponsibleDept> depts) {
		if (depts == null || 0 == depts.size()) {
			return false;
		}
		for (int i = 0; i < depts.size(); i++) {
			ResponsibleDept rd = depts.get(i);
			rd.setRecompenseId(appId);
			insertResponsibleDept(rd);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔单id查询责任部门项.<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public List<ResponsibleDept> getResponsibleDeptByRecompenseId(
			String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<ResponsibleDept>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryResponsibleDeptByRecompenseId", recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔单id删除责任部门项<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public boolean deleteResponsibleDeptByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession()
				.delete(NAMESPACE + "deleteResponsibleDeptByRecompenseId",
						recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description: 根据理赔单id删除所有消息提醒<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public boolean deleteMessageReminderByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession()
				.delete(NAMESPACE + "deleteMessageReminderByRecompenseId",
						recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新部门费用<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param deptCharge
	 * @return
	 *
	 */
	@Override
	public boolean updateDeptChargeById(DeptCharge deptCharge) {
		/** 原来代码逻辑错误，进行重构。@author roy */
		if (null != deptCharge && null != deptCharge.getId()) {

			int row = getSqlSession().update(
					NAMESPACE + "updateDeptChargeById", deptCharge);
			if (0 >= row) {
				return false;
			}
		} else {
			return false;

		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:批量更新部门费用<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param deptCharges
	 * @return
	 *
	 */
	@Override
	public boolean updateDeptChargesById(List<DeptCharge> deptCharges) {
		/** 原来代码逻辑错误，进行重构。@author roy */
		if (null != deptCharges && 0 <= deptCharges.size()) {

			for (int i = 0; i < deptCharges.size(); i++) {
				updateDeptChargeById(deptCharges.get(i));
			}
		} else {
			return false;

		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:保存工作流.<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param flow
	 * @return
	 *
	 */
	@Override
	public OAWorkflow insertWorkflow(OAWorkflow flow) {
		NullAndEmptyValidator.objectNullPreform(flow);
		NullAndEmptyValidator.stringNullPreform(flow.getId());
		getSqlSession().insert(NAMESPACE + "insertOAWorkflow", flow);
		return flow;
	}

	/**
	 * 
	 * <p>
	 * Description:通过凭证号工作流.<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 *
	 */
	@Override
	public List<OAWorkflow> getWorkflowByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<OAWorkflow>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryOAWorkflowByRecompenseId", recompenseId);
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:23:01
	 */
	@Override
	public boolean deleteAwardItemById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(NAMESPACE + "deleteAwardItemById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	
	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:24:12
	 */
	@Override
	public boolean deleteDeptChargeById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession()
				.delete(NAMESPACE + "deleteDeptChargeById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:24:51
	 */
	@Override
	public boolean deleteGoodsTransById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession()
				.delete(NAMESPACE + "deleteGoodsTransById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteGoodsTrans(List<GoodsTrans> goodsTranss) {

		for (int i = 0; i < goodsTranss.size(); i++) {
			deleteGoodsTransById(goodsTranss.get(i).getId());
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return boolean
	 * @update 2012-2-16 下午5:25:24
	 */
	@Override
	public boolean deleteIssueItemById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(NAMESPACE + "deleteIssueItemById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * Description:删除问题<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param issueItems
	 * @return
	 *
	 */
	@Override
	public boolean deleteIssueItem(List<IssueItem> issueItems) {
		for (int i = 0; i < issueItems.size(); i++) {
			deleteIssueItemById(issueItems.get(i).getId());
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return boolean
	 * @update 2012-2-16 下午5:25:58
	 */
	@Override
	public boolean deleteMessageRemainderById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteMessageReminderById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:26:12
	 */
	@Override
	public boolean deleteMessageById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(NAMESPACE + "deleteMessageById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:26:35
	 */
	@Override
	public boolean deleteOAWorkflowById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession()
				.delete(NAMESPACE + "deleteOAWorkflowById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔id删除OA工作流<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 * 
	 */
	@Override
	public boolean deleteOAWorkflowByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteOAWorkflowByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:27:11
	 */
	@Override
	public boolean deleteRecallCompById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(NAMESPACE + "deleteRecalledCompById",
				id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:27:35
	 */
	@Override
	public boolean deleteRecompAttById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession()
				.delete(NAMESPACE + "deleteAttachmentById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-16
	 * @param b
	 *            true or false
	 * @date 2012-2-16
	 * @return void
	 * @update 2012-2-16 下午5:27:40
	 */
	@Override
	public boolean deleteResponsibleDeptById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteResponsibleDeptById", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-23
	 * @param b
	 *            true or false
	 * @date 2012-2-23
	 * @return Object
	 * @update 2012-2-23 上午10:17:46
	 */
	@Override
	public Message getMessageById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return null;
		}
		return (Message) getSqlSession().selectOne(
				NAMESPACE + "queryMessageById", id);
	}

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-2-23
	 * @param b
	 *            true or false
	 * @date 2012-2-23
	 * @return List<RecompenseItem>
	 * @update 2012-2-23 上午11:29:22
	 */
	@Override
	public List<RecompenseAttachment> getAttachmentByRecompenseId(
			String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return new ArrayList<RecompenseAttachment>();
		}
		return getSqlSession().selectList(
				NAMESPACE + "queryAttachmentByRecompenseId", recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID更新货物运输项.<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param trans
	 * @return
	 * 
	 */
	@Override
	public boolean updateGoodsTransById(GoodsTrans trans) {
		/** 原来代码逻辑错误，进行重构。@author roy */

		if (null != trans
				&& !NullAndEmptyValidator
						.checkStringNullAndEmpty(trans.getId())) {

			int row = getSqlSession().update(
					NAMESPACE + "updateGoodsTransById", trans);
			if (0 >= row) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新货物运输项<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param goodsTranss
	 * @return
	 * 
	 */
	@Override
	public boolean updateGoodsTrans(List<GoodsTrans> goodsTranss) {
		for (int i = 0; i < goodsTranss.size(); i++) {
			updateGoodsTransById(goodsTranss.get(i));
		}
		return true;
	}

	@Override
	public boolean updateAwardItemById(AwardItem item) {
		/** 原来代码逻辑错误，进行重构。@author roy */

		if (null != item
				&& !NullAndEmptyValidator.checkStringNullAndEmpty(item.getId())) {

			int row = getSqlSession().update(NAMESPACE + "updateAwardItemById",
					item);
			if (0 >= row) {
				return false;
			}
		} else {
			return false;

		}
		return true;
	}

	@Override
	public boolean updateIssueItemById(IssueItem item) {
		/** 原来代码逻辑错误，进行重构。@author roy */

		if (null != item
				&& !NullAndEmptyValidator.checkStringNullAndEmpty(item.getId())) {
			int row = getSqlSession().update(NAMESPACE + "updateIssueItemById",
					item);
			if (0 >= row) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新问题描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param issueItems
	 * @return
	 * 
	 */
	@Override
	public boolean updateIssueItem(List<IssueItem> issueItems) {
		for (int i = 0; i < issueItems.size(); i++) {
			updateIssueItemById(issueItems.get(i));
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新跟进信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param message
	 * @return
	 * 
	 */
	@Override
	public boolean updateMessage(Message message) {
		/** 原来代码逻辑错误，进行重构。@author roy */

		if (null != message
				&& !NullAndEmptyValidator.checkStringNullAndEmpty(message
						.getId())) {
			int row = getSqlSession().update(NAMESPACE + "updateMessageById",
					message);
			if (0 >= row) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description: 更新消息提醒<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param messageReminder
	 * @return
	 * 
	 */
	@Override
	public boolean updateMessageReminder(MessageReminder messageReminder) {
		/** 原来代码逻辑错误，进行重构。@author roy */

		if (null != messageReminder
				&& !NullAndEmptyValidator
						.checkStringNullAndEmpty(messageReminder.getId())) {
			int row = getSqlSession().update(
					NAMESPACE + "updateMessageReminderById", messageReminder);
			if (0 >= row) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新付款单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param paymentBill
	 * @return
	 * 
	 */
	@Override
	public boolean updatePaymentBill(PaymentBill paymentBill) {
		/** 原来代码逻辑错误，进行重构。@author roy */

		if (null != paymentBill && null != paymentBill.getId()
				&& !"".equals(paymentBill.getId())) {

			int row = getSqlSession().update(
					NAMESPACE + "updatePaymentBillById", paymentBill);
			if (0 >= row) {
				return false;
			}
		} else {

			return false;
		}

		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔id删除付款单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 * 
	 */
	@Override
	public boolean deletePaymentByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deletePaymentBillByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新理赔附件信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseAttachment
	 * @return
	 * 
	 */
	@Override
	public boolean updateRecompenseAttachment(
			RecompenseAttachment recompenseAttachment) {
		/** 原来代码逻辑错误，进行重构。@author roy CRM-3115 */

		if (null != recompenseAttachment
				&& null != recompenseAttachment.getId()
				&& !"".equals(recompenseAttachment.getId())) {

			int row = getSqlSession().update(
					NAMESPACE + "updateAttachmentReminderById",
					recompenseAttachment);
			if (0 >= row) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:更新责任部门<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param dept
	 * @return
	 * 
	 */
	@Override
	public boolean updateResponsibleDept(ResponsibleDept dept) {
		/** 原来代码逻辑错误，进行重构。@author roy CRM-3117 */

		if (null != dept && null != dept.getId() && !"".equals(dept.getId())) {
			int row = getSqlSession().update(
					NAMESPACE + "updateResponsibleDeptById", dept);
			if (0 >= row) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:genjid删除付款单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public boolean deletePaymentBillById(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(NAMESPACE + "deletePaymentBillById",
				id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔id删除RecallComp<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public boolean deleteRecallCompByRecompenseId(String id) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(id)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteRecalledCompByRecompenseId", id);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:updateRecalledCompensation<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recalledCompensation
	 * @return
	 * 
	 */
	@Override
	public int updateRecalledCompensation(
			RecalledCompensation recalledCompensation) {
		return this.getSqlSession().update(
				NAMESPACE + "updateRecalledCompById", recalledCompensation);
	}

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
	@Override
	public boolean updateAwardItem(List<AwardItem> awardItemList) {
		for (AwardItem awardItem : awardItemList) {
			this.updateAwardItemById(awardItem);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param messageReminderList
	 * @return
	 * 
	 */
	@Override
	public boolean updateMessageReminder(
			List<MessageReminder> messageReminderList) {
		for (MessageReminder messageReminder : messageReminderList) {
			this.updateMessageReminder(messageReminder);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新责任部门<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param responsibleDeptList
	 * @return
	 * 
	 */
	@Override
	public boolean updateResponsibleDept(
			List<ResponsibleDept> responsibleDeptList) {
		for (ResponsibleDept responsibleDept : responsibleDeptList) {
			this.updateResponsibleDept(responsibleDept);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:删除消息提醒<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param messageReminderList
	 * @return
	 * 
	 */
	@Override
	public boolean deleteMessageRemainder(
			List<MessageReminder> messageReminderList) {
		for (MessageReminder messageReminder : messageReminderList) {
			this.deleteMessageRemainderById(messageReminder.getId());
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:删除责任部门<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param responsibleDeptList
	 * @return
	 * 
	 */
	@Override
	public boolean deleteResponsibleDept(
			List<ResponsibleDept> responsibleDeptList) {
		for (ResponsibleDept responsibleDept : responsibleDeptList) {
			this.deleteResponsibleDeptById(responsibleDept.getId());
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:删除入部门费用<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param deptChargeList
	 * @return
	 * 
	 */
	@Override
	public boolean deleteDeptCharge(List<DeptCharge> deptChargeList) {
		for (DeptCharge deptCharge : deptChargeList) {
			this.deleteDeptChargeById(deptCharge.getId());
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:删除奖励<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param awardItemList
	 * @return
	 * 
	 */
	@Override
	public boolean deleteAwardItem(List<AwardItem> awardItemList) {
		for (AwardItem awardItem : awardItemList) {
			this.deleteAwardItemById(awardItem.getId());
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:保存多赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param overpay
	 * @return
	 * 
	 */
	@Override
	public boolean insertOverpay(Overpay overpay) {
		int result = this.getSqlSession().insert(NAMESPACE + INSERT_OVERPAY,
				overpay);
		return result > 0 ? true : false;
	}
	
	public void updateOverpay(Overpay overpay) {
		this.getSqlSession().update(NAMESPACE + UPDATE_OVERPAY,
				overpay);
	}

	public boolean deleteOverpayByRecompenseId(String recompenseId) {
		if (NullAndEmptyValidator.checkStringNullAndEmpty(recompenseId)) {
			return false;
		}
		int row = getSqlSession().delete(
				NAMESPACE + "deleteOverpayByRecompenseId", recompenseId);
		if (0 >= row) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔id获得多赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Overpay> getOverpayByRecompenseId(String recompenseId) {
		return this.getSqlSession().selectList(
				NAMESPACE + GETOVERPAY_BYRECOMPENSEID, recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据工作流号获得多赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param workflowNum
	 * @param recompenseId
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Overpay getOverpayByWorkflowNum(String workflowNum,
			String recompenseId) {
		Map map = new HashMap();
		map.put("workflowNum", workflowNum);
		map.put("recompenseId", recompenseId);
		return (Overpay) this.getSqlSession().selectOne(
				NAMESPACE + "getOverpayByWorkflowNum", map);
	}

	/**
	 * 
	 * <p>
	 * Description:保存在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param onlineApply
	 * @return
	 * 
	 */
	@Override
	public boolean insertOnlineApply(OnlineApply onlineApply) {
		int result = this.getSqlSession().insert(
				NAMESPACE + INSERT_ONLINEAPPLY, onlineApply);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * <p>
	 * Description:更新在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param onlineApply
	 * @return
	 * 
	 */
	@Override
	public boolean updateOnlineApply(OnlineApply onlineApply) {
		int result = this.getSqlSession().update(
				NAMESPACE + UPDATE_ONLINEAPPLY, onlineApply);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * <p>
	 * Description:根据id获得在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public OnlineApply getOnlineApplyById(String id) {
		return (OnlineApply) this.getSqlSession().selectOne(
				NAMESPACE + GET_ONLINEAPPLY_BYID, id);
	}

	/**
	 * 
	 * <p>
	 * Description:根据运单号查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param waybillNum
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OnlineApply> getOnlineApplyByWaybillNum(String waybillNum) {
		return this.getSqlSession().selectList(
				NAMESPACE + GET_ONLINEAPPLY_BYWAYBILLNUM, waybillNum);
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param deptId
	 * @param statusList
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OnlineApply> searchOnlineApplyByCondition(String deptId,
			List<String> statusList, int start, int limit) {
		Map map = new HashMap();
		map.put("deptId", deptId);
		map.put("statusList", statusList);
		map.put("start", start + 1);
		map.put("end", start + limit);

		return this.getSqlSession().selectList(
				NAMESPACE + SEARCH_ONLINEAPPLY_BYCONDITION, map);
	}

	/**
	 * 
	 * <p>
	 * Description:获得在线理赔数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param deptId
	 * @param statusList
	 * @return
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Long getOnlineApplyCountByCondition(String deptId,
			List<String> statusList) {
		Map map = new HashMap();
		map.put("deptId", deptId);
		map.put("statusList", statusList);
		return (Long) this.getSqlSession().selectOne(
				NAMESPACE + GET_ONLINEAPPLYCOUNT_BYCONDITION, map);
	}

	/**
	 * 
	 * <p>
	 * Description:保存冲账<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param balance
	 * @return
	 * 
	 */
	@Override
	public boolean insertBalance(Balance balance) {
		int result = this.getSqlSession().insert(NAMESPACE + INSERT_BALANCE,
				balance);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * <p>
	 * Description:保存冲账<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param balanceList
	 * @return
	 * 
	 */
	@Override
	public boolean insertBalance(List<Balance> balanceList) {
		for (Balance balance : balanceList) {
			this.insertBalance(balance);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:查询冲账<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param map
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Balance> searchBalanceByCondition(Map<String, String> map) {
		RowBounds bounds = new RowBounds(Integer.parseInt(map.get("start")),
				Integer.parseInt(map.get("limit")));
		return this.getSqlSession().selectList(
				NAMESPACE + SEARCH_BALANCE_BYCONDITION, map, bounds);
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔id获得冲账数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 * 
	 */
	@Override
	public Double getBalanceAmountCountByRecompenseId(String recompenseId) {
		return (Double) this.getSqlSession()
				.selectOne(NAMESPACE + GET_BALANCEAMOUNTCOUNT_BYRECOMPENSEID,
						recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据工作流获得工作流<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param workflowNum
	 * @return
	 * 
	 */
	@Override
	public OAWorkflow getWorkflowByWorkflowNum(String workflowNum) {
		return (OAWorkflow) getSqlSession().selectOne(
				NAMESPACE + "getWorkflowByWorkflowNum", workflowNum);
	}

	/**
	 * 
	 * <p>
	 * Description:获得工作流<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param appId
	 * @param type
	 * @return
	 * 
	 */
	@Override
	public OAWorkflow getWorkflowByAppIdAndType(String appId, String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		map.put("type", type);
		return (OAWorkflow) this.getSqlSession().selectOne(
				NAMESPACE + "getWorkflowByAppIdAndType", map);
	}

	/**
	 * 
	 * <p>
	 * Description:更新在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param oaWorkflow
	 * 
	 */
	@Override
	public void updateWorkflow(OAWorkflow oaWorkflow) {
		getSqlSession().update(NAMESPACE + "updateOAWorkflow", oaWorkflow);
	}

	/**
	 * 
	 * <p>
	 * Description:更新在线理赔状态<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @param status
	 * @param rejectReason
	 * 
	 */
	@Override
	public void updateOnlineApplyStatusByRecompenseId(String recompenseId,
			String status, String rejectReason) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("recompenseId", recompenseId);
		map.put("status", status);
		map.put("rejectReason", rejectReason);
		int result;
		if (rejectReason == null || rejectReason.equals("")) {
			result = this.getSqlSession().update(
					NAMESPACE + UPDATE_ONLINEAPPLY_BYRECOMPENSEID, map);
		} else {
			result = this.getSqlSession().update(
					NAMESPACE + UPDATE_ONLINEAPPLY_INFO_BYRECOMPENSEID, map);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:通过理赔id获得在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @return
	 * 
	 */
	@Override
	public OnlineApply getOnlineApplyByRecompenseId(String recompenseId) {
		return (OnlineApply) this.getSqlSession().selectOne(
				NAMESPACE + GET_ONLINEAPPLY_BYRECOMPENSEID, recompenseId);
	}

	/**
	 * 
	 * <p>
	 * Description:通过官网永华查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param onlineUser
	 * @param waybillNum
	 * @return
	 * 
	 */
	@Override
	public List<OnlineApply> getOnlineApplyByOnlineUser(String onlineUser,
			String waybillNum) {
		Map map = new HashMap();
		map.put("onlineUser", onlineUser);
		map.put("waybillNum", waybillNum);
		return this.getSqlSession().selectList(
				NAMESPACE + "getOnlineApplyByOnlineUser", map);
	}

	/**
	 * 
	 * <p>
	 * Description:获得在线理赔list<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param onlineUser
	 * @param waybillNum
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Override
	public List<OnlineApply> getOnlineApplyByInterCondition(String onlineUser,
			String waybillNum, Date startTime, Date endTime, int start,
			int limit) {
		// 封装属性
		Map map = new HashMap();
		map.put("onlineUser", onlineUser);
		map.put("waybillNum", waybillNum);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("start", start);
		map.put("limit", start + limit - 1);
		return this.getSqlSession().selectList(
				NAMESPACE + "getOnlineApplyByInterCondition", map);
	}

	/**
	 * 
	 * <p>
	 * Description:获得在线理赔数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param onlineUser
	 * @param waybillNum
	 * @param startTime
	 * @param endTime
	 * @return
	 * 
	 */
	@Override
	public int getOnlineApplyByInterConditionCount(String onlineUser,
			String waybillNum, Date startTime, Date endTime) {
		// 封装属性
		Map map = new HashMap();
		map.put("onlineUser", onlineUser);
		map.put("waybillNum", waybillNum);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + "getOnlineApplyByInterConditionCount", map);
	}

	/**
	 * 
	 * <p>
	 * Description：保存账号信息<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-31 void
	 */
	@Override
	public Payment savePayment(Payment payment) {
		if (null != payment) {
			this.getSqlSession().insert(NAMESPACE + SAVE_PAYMENT, payment);
			return payment;
		}
		return null;
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
	@Override
	public Payment updatePayment(Payment payment) {
		if (null != payment) {
			this.getSqlSession().update(NAMESPACE + UPDATE_PAYMENT, payment);
			return payment;
		}
		return null;
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
	@Override
	public List<Payment> searchPaymentByRecompenseId(String recompenseId) {
		if (null != recompenseId && !"".equals(recompenseId)) {

			return this.getSqlSession().selectList(
					NAMESPACE + SEARCH_PAYMENTHISTORY, recompenseId);
		}
		return null;
	}

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
	@Override
	public Payment findPaymentByRecompenseIdAndStatus(String recompenseId,
			String paymentStatus) {
		Map map=new HashMap();
		map.put("recompenseId", recompenseId);
		map.put("paymentStatus", paymentStatus);
		return (Payment) this.getSqlSession().selectOne(NAMESPACE+FIND_PAYMENT, map);
	}

	public List<String> findCreateCustList(String type) {
//		Map map = new HashMap();
//		map.put("type", type);
//		return (List<String>) this.getSqlSession().selectList(
//				NAMESPACE + "findCreateCustList", map);
		return new ArrayList<String>();
	}

	public boolean insertCreateCust(String waybillnum, String type) {
//		Map map = new HashMap();
//		map.put("waybillnum", waybillnum);
//		map.put("type", type);
//		getSqlSession().insert(NAMESPACE + "insertCreateCust", map);
		return true;
	}

	public boolean updateCreateCust(String waybillnum, String type) {
//		Map map = new HashMap();
//		map.put("waybillnum", waybillnum);
//		map.put("type", type);
//		int row = getSqlSession().update(NAMESPACE + "updateCreateCust", map);
		return true;
	}

	@Override
	public Map<String,Object> searchOnlineApply(OnlineApplyCondition condition,
			int limit, int start) {
		Map<String,Object> map=new HashMap<String,Object>();
		List<OnlineApply> list;
		Long totalCount;
		if(!StringUtil.isEmpty(condition.getWaybillNumber())){
			list = this.getSqlSession().selectList(NAMESPACE+SEARCH_ONLINEAPPLY,condition);
		}else{
			RowBounds rowBounds=new RowBounds(start, limit);
			list = this.getSqlSession().selectList(NAMESPACE+SEARCH_ONLINEAPPLY,condition, rowBounds);
		}
		totalCount = (Long) this.getSqlSession().selectOne(NAMESPACE+SEARCH_ONLINEAPPLY_COUNT, condition);
		map.put("totalCount", totalCount);
		map.put("onlineApplyList", list);
		return map;
	}
	
	/**
	 * 
	 * @description 根据流程号查询多赔信息
	 * @author andy
	 * @version 0.1 2014-1-10
	 * @param 流程号 ICRM+年月日+7位数字
	 * @date 2014-1-10
	 * @return Overpay
	 * @update 2014-1-10
	 */
	@Override
	public Overpay getOverPay(String workflowNo) {
		return (Overpay) this.getSqlSession().selectOne(
				NAMESPACE + "getOverpayByWorkflowNo", workflowNo);
	}

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
	@Override
	public Overpay getDetailOverpayByWorkNumber(String workNumber) {
		return (Overpay) this.getSqlSession().selectOne(
				NAMESPACE + GET_DETAILOVERPAY_BYWORKNUMBER, workNumber);
	}

}
