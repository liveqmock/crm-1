package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * <p>
 * 会员积分管理action<br />
 * </p>
 * 
 * @title IntegralRecordAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 潘光均
 * @version 0.1 2012-3-29
 */

public class IntegralRecordAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 5351474961107526782L;
	private IContactManager contactManager;
	// 积分查询manger
	private IIntegralManager integralManager;
	// 会员积分查询集合
	private List<MemberIntegral> ingegralList;
	// 联系人查询集合
	private List<ContactIntegral> contactRecordList;
	// 礼品申请查询集合
	private List<IntegralConvertGift> presentApplyList;
	// 奖励积分查询集合
	private List<HandRewardIntegral> awardRecordList;
	// 调整积分查询集合
	private List<AdjustIntegral> adjustRecordList;
	// 调整积分查询集合
	private List<WaybillIntegral> waybillRecordList;
	// 搜索条件
	private IntegralSearchCondition condition;
	// 分页条数
	private int limit;
	// 分页开始条数
	private int start;
	// 总条数
	private Long totalCount;

	public List<ContactIntegral> getContactRecordList() {
		return contactRecordList;
	}

	public List<IntegralConvertGift> getPresentApplyList() {
		return presentApplyList;
	}

	public List<HandRewardIntegral> getAwardRecordList() {
		return awardRecordList;
	}

	public List<AdjustIntegral> getAdjustRecordList() {
		return adjustRecordList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setCondition(IntegralSearchCondition condition) {
		this.condition = condition;
	}

	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public void setIntegralManager(IIntegralManager integralManager) {
		this.integralManager = integralManager;
	}

	public List<MemberIntegral> getIngegralList() {
		return ingegralList;
	}

	public List<WaybillIntegral> getWaybillRecordList() {
		return waybillRecordList;
	}

	public IntegralSearchCondition getCondition() {
		return condition;
	}

	/**
	 * 
	 * @description 查询会员积分.
	 * @author 潘光均
	 * @version 0.1 2012-4-21
	 * @date 2012-4-21
	 * @return String
	 * @update 2012-4-21 下午2:46:02
	 */
	public String searchRecordList() {
		totalCount = integralManager
				.countSearchMemberIntegralsForCondition(condition);
		ingegralList = integralManager.searchMemberIntegralsForCondition(
				condition, start, limit);

		return SUCCESS;
	}

	/**
	 * 
	 * @description 查询调整积分.
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:11:55
	 */
	public String searchAdjustRecordList() {
		// TODO：最好放在manager
		List<String> contactIdList = new ArrayList<String>();
		if (condition == null) {
			return null;
		}
		if (condition.getContactId() != null
				&& !condition.getContactId().equals("")) {
			contactIdList.add(contactManager
					.getContactIdByContactNumber(condition.getContactId()));
		} else {
			contactIdList = contactManager.getContactIdListByMemberId(condition
					.getMemberId());
		}
		totalCount = integralManager
				.countSearchAdjustIntegralForContactId(contactIdList);
		adjustRecordList = integralManager.searchAdjustIntegralForContactId(
				contactIdList, start, limit);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 查询奖励积分
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:12:17
	 */
	public String searchAwardRecordList() {
		if (condition == null) {
			return null;
		}
		List<String> contactIdList = new ArrayList<String>();
		if (condition.getContactId() != null
				&& !condition.getContactId().equals("")) {
			contactIdList.add(contactManager
					.getContactIdByContactNumber(condition.getContactId()));
		} else {
			contactIdList = contactManager.getContactIdListByMemberId(condition
					.getMemberId());
		}
		totalCount = integralManager
				.countSearchHandRewardIntegralForContactId(contactIdList);

		awardRecordList = integralManager.searchHandRewardIntegralForContactId(
				contactIdList, start, limit);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 查询礼品申请记录.
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:12:31
	 */
	public String searchPresentApplyList() {
		if (condition == null) {
			return null;
		}
		List<String> contactIdList = new ArrayList<String>();
		if (condition.getContactId() != null
				&& !condition.getContactId().equals("")) {
			contactIdList.add(contactManager
					.getContactIdByContactNumber(condition.getContactId()));
		} else {
			contactIdList = contactManager.getContactIdListByMemberId(condition
					.getMemberId());
		}
		totalCount = integralManager
				.countSearchIntegralConvertGiftForContactId(contactIdList);
		presentApplyList = integralManager
				.searchIntegralConvertGiftForContactId(contactIdList, start,
						limit);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 查询联系人积分
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:12:52
	 */
	public String searchContactRecordList() {
		if (condition == null) {
			return null;
		}
		List<String> contactIdList = new ArrayList<String>();
		if (condition.getContactId() != null
				&& !condition.getContactId().equals("")) {
			contactIdList.add(contactManager
					.getContactIdByContactNumber(condition.getContactId()));

		} else {
			contactIdList = contactManager.getContactIdListByMemberId(condition
					.getMemberId());
		}
		contactRecordList = integralManager.searchContactIntegralsForContactId(
				contactIdList, start, limit);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据联系人的id查询出改联系人的积分信息，<br />
	 * 带出所挂靠的客户的积分信息
	 * </p>
	 * ---
	 * 
	 * @author Administrator
	 * @version 0.1 2012-5-3
	 * @return String
	 */
	public String searchContactRecordInfo() {

		String contactNumber = condition.getContactId();

		contactRecordList = new ArrayList<ContactIntegral>();
		List<String> contactIdList = new ArrayList<String>();
		contactIdList.add(condition.getContactId());
		ContactIntegral contactIntegral = integralManager
				.getContactIntegralByContactNumber(contactNumber);

		if (ValidateUtil.objectIsEmpty(contactIntegral)) {
			throw new IntegException(IntegExceptionType.ContactIntegralNotExist);
		} else {
			contactRecordList.add(contactIntegral);
		}

		ingegralList = new ArrayList<MemberIntegral>();
		MemberIntegral memberIntegral = integralManager
				.getMemberIntegralByContactNum(contactNumber);
		if (ValidateUtil.objectIsEmpty(memberIntegral)) {
			throw new IntegException(IntegExceptionType.MemberIntegralNotExist);
		} else {
			ingegralList.add(memberIntegral);
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @description 查询以积分运单
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:27:11
	 */
	public String searchWaybillRecordList() {
		if (condition == null) {
			return null;
		}
		if ((condition.getContactId() == null || condition.getContactId()
				.equals(""))
				&& (condition.getMemberId() == null || condition.getMemberId()
						.equals(""))) {
			return null;
		}
		List<String> contactIdList = new ArrayList<String>();
		if (condition.getContactId() != null
				&& !condition.getContactId().equals("")) {
			contactIdList.add(contactManager
					.getContactIdByContactNumber(condition.getContactId()));
		} else {
			contactIdList = contactManager.getContactIdListByMemberId(condition
					.getMemberId());
		}
		totalCount = integralManager
				.countSearchWaybillIntegralForContactId(contactIdList);
		waybillRecordList = integralManager.searchWaybillIntegralForContactId(
				contactIdList, start, limit);
		return SUCCESS;
	}
}
