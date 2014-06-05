package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.IGiftApplyOperate;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.manager.IIntegRuleManager;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.server.manager.IIntegralRule;
import com.deppon.crm.module.customer.server.manager.IMemberWorkFlowManager;
import com.deppon.crm.module.customer.server.manager.RuleFactory;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IContactIntegralService;
import com.deppon.crm.module.customer.server.service.IContactVaryService;
import com.deppon.crm.module.customer.server.service.IExamineRecordService;
import com.deppon.crm.module.customer.server.service.IIntegralService;
import com.deppon.crm.module.customer.server.service.IMemberIntegralService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.service.IWaybillIntegralService;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactMemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVaryExaminView;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.GiftConvertHoldRecode;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.shared.util.string.StringUtil;


@Transactional
public class IntegralManager implements IIntegralManager {
	// 联系人manager
	private IContactManager contactManager;
	// 积分service
	private IIntegralService integralService;
	// 会员积分service
	private IMemberIntegralService memberIntegralService;
	private IExamineRecordService examineRecordService;
	private IMemberWorkFlowManager memberWorkFlowManager;
	private IMemberService memberService;
	private IContactVaryService contactVaryService;
	private IContactIntegralService contactIntegralService;
	private IAlterMemberService alterMemberService;
	private IWaybillIntegralService waybillIntegralService;
	private IAlterMemberManager alterMemberManager;
	private IFileManager fileManager;
	private IIntegRuleManager integRuleManager;
	private IGiftApplyOperate giftApplyOperate;
	private IBaseDataManager baseDataManager;
	private IRepeatedCustManager repeatedCustManager;

	/**
	 *@author chenaichun
	 * @date 2014年4月15日 上午9:32:26 
	 * @param repeatedCustManager the repeatedCustManager to set
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}


	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}

	public void setGiftApplyOperate(IGiftApplyOperate giftApplyOperate) {
		this.giftApplyOperate = giftApplyOperate;
	}

	public void setIntegRuleManager(IIntegRuleManager integRuleManager) {
		this.integRuleManager = integRuleManager;
	}

	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public void setMemberWorkFlowManager(
			IMemberWorkFlowManager memberWorkFlowManager) {
		this.memberWorkFlowManager = memberWorkFlowManager;
	}

	public void setContactIntegralService(
			IContactIntegralService contactIntegralService) {
		this.contactIntegralService = contactIntegralService;
	}

	public void setWaybillIntegralService(
			IWaybillIntegralService waybillIntegralService) {
		this.waybillIntegralService = waybillIntegralService;
	}

	public void setContactVaryService(IContactVaryService contactVaryService) {
		this.contactVaryService = contactVaryService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public void setExamineRecordService(
			IExamineRecordService examineRecordService) {
		this.examineRecordService = examineRecordService;
	}

	public void setMemberIntegralService(
			IMemberIntegralService memberIntegralService) {
		this.memberIntegralService = memberIntegralService;
	}

	public void setIntegralService(IIntegralService integralService) {
		this.integralService = integralService;
	}

	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}

	/**
	 * @param alterMemberService : set the property alterMemberService.
	 */
	public void setAlterMemberService(IAlterMemberService alterMemberService) {
		this.alterMemberService = alterMemberService;
	}

	@Override
	public boolean giftIntegralCreateApprove(String wkid, boolean wkStatus,
			String wkman, Date wktime) {
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContactIntegral> getContactIntegralListByMemId(String memberId) {
		List<Contact> contactList = contactManager
				.getContactByMemberId(memberId);
		List<ContactIntegral> contactIntegList = new ArrayList<ContactIntegral>();

		for (Contact contact : contactList) {
			ContactIntegral contactInteg = integralService
					.getContactIntegralByContact(contact.getId());
			if (!ValidateUtil.objectIsEmpty(contactInteg)) {
				contactIntegList.add(contactInteg);
			}
		}
		return contactIntegList;
	}

	public List<ContactIntegral> get360ContactIntegralListByMemId(
			String memberId) {
		List<ContactIntegral> contactList = contactIntegralService
				.searchContactIntegralsByMemberId(memberId);
		return contactList;
	}

	@Override
	@Transactional(readOnly = true)
	public MemberIntegral getMemberIntegralByContactId(String contactId) {
		String memberId = contactManager.getContactMemberId(contactId);
		return memberIntegralService.getMemberIntegralByMemberId(memberId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MemberIntegral> getMemberIntegralListByContact(
			List<String> slist) {
		List<MemberIntegral> memberIntegralList = new ArrayList<MemberIntegral>();
		for (String contactId : slist) {
			MemberIntegral memberIntegral = getMemberIntegralByContactId(contactId);
			if (memberIntegral != null) {
				memberIntegralList.add(memberIntegral);
			}
		}
		return memberIntegralList;
	}

	@Override
	@Transactional(readOnly = true)
	public MemberIntegral getMemberIntegralListByMemberId(String mid) {
		return memberIntegralService.getMemberIntegralByMemberId(mid);
	}

	/**
	 * 
	 * <p>
	 * 根据条件得到会员积分对象列表<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param memberIntegCondition
	 * @param start
	 * @param limit
	 * @return List<MemberIntegral>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MemberIntegral> searchMemberIntegralsForCondition(
			IntegralSearchCondition memberIntegCondition, int start, int limit) {
		Assert.notNull(memberIntegCondition,
				"memberIntegCondition must not null");

		// 当查询条件全部为空时，才有数据权限验证。
		if ((ValidateUtil.objectIsEmpty(memberIntegCondition.getContactId())
				&& ValidateUtil.objectIsEmpty(memberIntegCondition
						.getContactName())
				&& ValidateUtil.objectIsEmpty(memberIntegCondition
						.getMemberName()) && ValidateUtil
					.objectIsEmpty(memberIntegCondition.getMemberNum()))
				|| !ValidateUtil.objectIsEmpty(memberIntegCondition
						.getContactName())
				|| !ValidateUtil.objectIsEmpty(memberIntegCondition
						.getMemberName())) {
			String deptId = memberIntegCondition.getDeptId();
			List<String> deptIds = ContextUtil.getDataAuthorityDepts(deptId);
			memberIntegCondition.setDeptIds(deptIds);
		}

		return memberIntegralService.searchMemberIntegralsForCondition(
				memberIntegCondition, start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchMemberIntegralsForCondition(
			IntegralSearchCondition memberIntegCondition) {
		Assert.notNull(memberIntegCondition,
				"memberIntegCondition must not null");

		// 当查询条件全部为空时，才有数据权限验证。
		if ((ValidateUtil.objectIsEmpty(memberIntegCondition.getContactId())
					&& ValidateUtil.objectIsEmpty(memberIntegCondition
							.getContactName())
					&& ValidateUtil.objectIsEmpty(memberIntegCondition
							.getMemberName()) && ValidateUtil
							.objectIsEmpty(memberIntegCondition.getMemberNum()))
					|| !ValidateUtil.objectIsEmpty(memberIntegCondition
							.getContactName())
					|| !ValidateUtil.objectIsEmpty(memberIntegCondition
							.getMemberName())) {
				String deptId = memberIntegCondition.getDeptId();
				List<String> deptIds = ContextUtil.getDataAuthorityDepts(deptId);
				memberIntegCondition.setDeptIds(deptIds);
		}
		return memberIntegralService
				.countSearchMemberIntegralsForCondition(memberIntegCondition);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContactIntegral> searchContactIntegralsForContactId(
			List<String> contactIdList, int start, int limit) {
		return contactIntegralService.searchContactIntegralsBycontactIdList(
				contactIdList, start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IntegralConvertGift> searchIntegralConvertGiftForContactId(
			List<String> contactIdList, int start, int limit) {
		return integralService.searchIntegralConvertGiftForContactId(
				contactIdList, start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WaybillIntegral> searchWaybillIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		List<WaybillIntegral> waybillList = waybillIntegralService
				.searchWaybillIntegralForContactId(contactIdList, start, limit);
		// 切换付款方式类型
		changeOrderPayType(waybillList);
		return waybillList;
	}

	/**
	 * @description 切换订单付款方式--积分运单存的数字转换为数据字典.
	 * @author 潘光均
	 * @version 0.1 2012-7-5
	 * @param
	 * @date 2012-7-5
	 * @return void
	 * @update 2012-7-5 下午5:21:31
	 */
	private void changeOrderPayType(List<WaybillIntegral> waybillList) {
		WaybillIntegral integral = null;
		for (int i = 0; i < waybillList.size(); i++) {
			integral = waybillList.get(i);
			if (integral.getPayMent().equals(Constant.ORDER_PAYMENT_CASH_INTEG)) {
				integral.setPayMent(Constant.ORDER_PAYMENT_CASH);
			} else if (integral.getPayMent().equals(
					Constant.ORDER_PAYMENT_PAY_ARIIVE_INTEG)) {
				integral.setPayMent(Constant.ORDER_PAYMENT_PAY_ARIIVE);
			} else if (integral.getPayMent().equals(
					Constant.ORDER_PAYMENT_MONTH_PAY_INTEG)) {
				integral.setPayMent(Constant.ORDER_PAYMENT_MONTH_PAY);
			} else if (integral.getPayMent().equals(
					Constant.ORDER_PAYMENT_PAY_ONLINE_INTEG)) {
				integral.setPayMent(Constant.ORDER_PAYMENT_PAY_ONLINE);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<HandRewardIntegral> searchHandRewardIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		return integralService.searchHandRewardIntegralForContactId(
				contactIdList, start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdjustIntegral> searchAdjustIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		return integralService.searchAdjustIntegralForContactId(contactIdList,
				start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public long contactVary(ContactVary contactVary, List<FileInfo> fileList) {
		// 增加代码健壮性
		if (null == contactVary) {
			return 0;
		}
		if (null == contactVary.getFromMemberIntegral()
				|| null == contactVary.getToMemberIntegral()
				|| null == contactVary.getToMemberIntegral().getMember()
				|| null == contactVary.getFromMemberIntegral().getMember()) {
			return 0;
		}
		// 同一个会员不能变更
		if(contactVary.getFromMemberIntegral().getId().equals(
				contactVary.getToMemberIntegral().getId())){
			throw new IntegException(
					IntegExceptionType.SameMemberCanNotContact);
		}
		//审批中工作流
		if (contactVary.getToMemberIntegral().getMember() != null
				&& "1".equals(contactVary.getToMemberIntegral().getMember()
						.getCustStatus())) {
			String workflowId = alterMemberService.getCustWorkflow(contactVary
					.getToMemberIntegral().getMember().getId());
			if (!ValidateUtil.objectIsEmpty(workflowId)) {
				throw new CustomerException(
						CustomerExceptionType.MemberIsExamin,
						new Object[] { workflowId });
			}
			workflowId = repeatedCustManager.searchWorkFlowNoByCustId(contactVary.getToMemberIntegral().getMember().getCustNumber());
			if (!ValidateUtil.objectIsEmpty(workflowId)) {
				throw new CustomerException(
						CustomerExceptionType.MemberIsExamin,
						new Object[] { workflowId });
			}
			throw new CustomerException(CustomerExceptionType.custIsExamin);

		}
		if (contactVary.getFromMemberIntegral().getMember() != null
				&& Constant.Cust_STATUS_WORKFLOW.equals(contactVary.getFromMemberIntegral().getMember()
						.getCustStatus())) {
			String formWorkflowId = alterMemberService
					.getCustWorkflow(contactVary.getFromMemberIntegral()
							.getMember().getId());
			if (!ValidateUtil.objectIsEmpty(formWorkflowId)) {
				throw new CustomerException(
						CustomerExceptionType.MemberIsExamin,
						new Object[] { formWorkflowId });
			}
			formWorkflowId = repeatedCustManager.searchWorkFlowNoByCustId(contactVary.getFromMemberIntegral().getMember().getCustNumber());
			if (!ValidateUtil.objectIsEmpty(formWorkflowId)) {
				throw new CustomerException(
						CustomerExceptionType.MemberIsExamin,
						new Object[] { formWorkflowId });
			}
			throw new CustomerException(CustomerExceptionType.custIsExamin);
		}
		Member member = alterMemberService.getMemberById(contactVary.getToMemberIntegral().getMember().getId());
		if (Constant.CUST_GROUP_PC_CUSTOMER.equals(member.getCustGroup())) {
			throw new CustomerException(CustomerExceptionType.PotentialCustomerCannotContactVary);
		}
		Member fromMember = alterMemberService.getMemberById(contactVary.getFromMemberIntegral().getMember().getId());
		if (Constant.CUST_GROUP_PC_CUSTOMER.equals(fromMember.getCustGroup())) {
			throw new CustomerException(CustomerExceptionType.PotentialCustomerCannotContactVary);
		}
		
		if (!member.getCustGroup().equals(fromMember.getCustGroup())) {
			throw new CustomerException(CustomerExceptionType.NotSameCustGroupCannotContactVary);
		}
		//疑似重复列表
		String fDeptName = repeatedCustManager.searchMainCustDeptName(contactVary.getFromMemberIntegral().getMember().getCustNumber());
		if (!StringUtils.isEmpty(fDeptName)) {
			throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{fDeptName});
		}
		//疑似重复列表
		String deptName = repeatedCustManager.searchMainCustDeptName(contactVary.getToMemberIntegral().getMember().getCustNumber());
		if (!StringUtils.isEmpty(deptName)) {
			throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{deptName});
		}
		// 判断是否为主联系人，主联系人不容许变更关系
		isMainContact(contactVary.getContactNumber());
		// 获得contactVary序列的Id
		String contactId = contactVaryService.getContactVaryId();
		contactVary.setId(contactId);

		String modifyUser = ContextUtil.getCurrentUserEmpId();
		// 启动联系人变更工作流
		long workFlowId = memberWorkFlowManager.createContactVaryWorkFlow(
				contactVary.getId(),contactVary.getFromMemberIntegral()
				.getMember().getId(),contactVary.getFromMemberIntegral()
						.getMember().getDeptId(), contactVary
						.getToMemberIntegral().getMember().getDeptId());
		contactVary.setWorkflowId(workFlowId);
		// 保存ContactVary
		contactVaryService.insertContactVary(contactVary);
		// 设置被移动的会员为审批状态
		memberService.updateMemberStauts(Constant.Status_Examine, contactVary
				.getFromMemberIntegral().getMember().getId(), modifyUser);
		// 保存附件对象
		for (FileInfo fileInfo : fileList) {
			fileInfo.setSourceId(contactVary.getId());
			fileInfo.setSourceType(Constant.ContactVaryFileInfo);
		}
		fileManager.saveFileInfo(fileList);
		return workFlowId;
	}

	@Override
	@Transactional(readOnly = true)
	public void disposeContactVaryByExamineResult(String contactVaryId,
			boolean examinresult) {
		
		String modifyUser = ContextUtil.getCurrentUserEmpId();

		ContactVary contactVary = contactVaryService
				.getContactVaryById(contactVaryId);
		
		if (null == contactVary) {
			return;
		}
		//客户如果已经作废，挂靠什么的就没意义了，所以不处理
		Member memb = alterMemberService.getMemberById(contactVary.getFromMemberIntegral().getMember().getId());
		if(!ValidateUtil.objectIsEmpty(memb)
				&&Constant.Cust_STATUS_UNEFFECT.equals(memb.getCustStatus())){
			return;
		}
		// 设置被移动的会员为正常状态
		memberService.updateMemberStauts(Constant.Status_Normal, contactVary
				.getFromMemberIntegral().getMember().getId(), modifyUser);
		
		if (examinresult) {
			// 审批成功
			// 构建参数
			MemberIntegral moveMembeIntegral = null;
			
			moveMembeIntegral = contactVary.getFromMemberIntegral();
			// 变更发起会员的主联系人
			Contact toContact = moveMembeIntegral.getMember().getMainContact();
			// 变更的联系人 查询联系人接口有改动 里面加入客户信息 2012-5-2
			ContactView contactView = contactManager
					.getContactByNumber(contactVary.getContactNumber());
			Contact fromContact = contactView.getContact();

			// 联系人积分调换
			varyContactIntegral(toContact, fromContact);

			// 变更联系人的目标会员
			Member member = contactVary.getToMemberIntegral().getMember();
			// 变更联系人
			contactManager.varyContact(fromContact, member);
			// 删除变更联系人对应的偏好地址
			alterMemberManager.cleanContactPreferenceAddress(fromContact
					.getId());

		} else {
			// 审批失败 没有特殊操作
		}
	}

	/**
	 * 
	 * <p>
	 * 联系人积分变更<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param toContact
	 * @param fromContact
	 *            void
	 */
	private void varyContactIntegral(Contact toContact, Contact fromContact) {
		ContactIntegral toContactInteg = contactIntegralService
				.getContactIntegralByContactId(toContact.getId());
		ContactIntegral fromContactInteg = contactIntegralService
				.getContactIntegralByContactId(fromContact.getId());

		IIntegralRule integralRule = RuleFactory
				.getRule(RuleFactory.ADJUST_INTEGRAL, toContactInteg,
						new ContactIntegral[] { fromContactInteg }, null, null,
						0, null);
		IntegralOperation integralOperation = integralRule
				.getBizIntegralOperation();
		// 当被移动的联系人积分为0时,不进行保存操作。
		if (integralOperation != null) {
			integralService.saveIntegralOperation(integralOperation);
		}

	}

	/**
	 * 
	 * <p>
	 * 判断是否为主联系人，如是主联系人，抛出异常<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-5-21
	 * @param contactNumber
	 *            void
	 */
	private void isMainContact(String contactNumber) { 
		ContactView contactView = contactManager
				.getContactByNumber(contactNumber);
		if (contactView.getContact().getIsMainLinkMan()) {
			throw new IntegException(
					IntegExceptionType.MainLinkManNotChangeMember);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ContactVary getContactVaryByWorkFlowId(long workFlowId) {
		return contactVaryService.getContactVaryByWorkFlowId(workFlowId);
	}

	@Override
	@Transactional
	public ContactVaryExaminView createContactVaryExaminView(String varyId,
			long workflowId) {
		ContactVaryExaminView view = new ContactVaryExaminView();
		// 查询 ContactVary
		ContactVary contactVary = getContactVaryByWorkFlowId(workflowId);
		view.setContactVary(contactVary);
		// 得到附件
		FileInfo fileInfo = new FileInfo();
		fileInfo.setSourceType(Constant.ContactVaryFileInfo);
		fileInfo.setSourceId(varyId);
		List<FileInfo> fileInfoList = fileManager.searchFileInfoByCondition(
				fileInfo, 0, 100000);
		view.setFileInfoList(fileInfoList);
		// 查询审批记录
		List<ExamineRecord> examineRecordList = examineRecordService
				.getExamineRecordByWorkflowId(workflowId);
		view.setExamineRecordList(examineRecordList);
		view.setCurrentExaminer(examineRecordService.getCurrentPeople(workflowId));

		return view;
	}
	
	@Override
	@Transactional
	public void createHandRewardIntegrals(Integer integralBasicNumber,
			RewardIntegRule rewardRule, List<String> contactIds) {
		Set<String> memberIds = new HashSet<String>();
		for (String contactId : contactIds) {
			String memberId = contactManager.getContactMemberId(contactId);
			memberIds.add(memberId);
		}
		// 得到联系人积分
		List<ContactIntegral> contactIntegrals = contactIntegralService
				.searchContactIntegralsBycontactIdList(contactIds, 0, 100000);
		// 得到会员积分
		List<MemberIntegral> memberIntegrals = memberIntegralService
				.getMemberIntegralByMemberIds(memberIds);
		ContactIntegral[] contactIntegralArr = new ContactIntegral[contactIntegrals
				.size()];
		MemberIntegral[] memberIntegralArr = new MemberIntegral[memberIntegrals
				.size()];

		// TODO 待优化
		for (int i = 0; i < contactIntegralArr.length; i++) {
			contactIntegralArr[i] = contactIntegrals.get(i);
		}

		// TODO 待优化
		for (int i = 0; i < memberIntegralArr.length; i++) {
			memberIntegralArr[i] = memberIntegrals.get(i);
		}

		IIntegralRule integralRule = RuleFactory.getRule(
				RuleFactory.REWARD_INTEGRAL, null, contactIntegralArr,
				memberIntegralArr, rewardRule, integralBasicNumber, null);
		IntegralOperation operation = integralRule.getBizIntegralOperation();

		integralService.saveIntegralOperation(operation);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberIntegral getMemberIntegralByMemberNum(String memberNum) {
		String memberId = alterMemberManager.getMemberIdByMemberNum(memberNum);
		if(StringUtil.isBlank(memberId)){
			throw new CustomerException(CustomerExceptionType.CUSTCODE_NOTFOUND);
		}
		MemberIntegral memberInt =  memberIntegralService.getMemberIntegralByMemberId(memberId);
		if (Constant.CUST_GROUP_PC_CUSTOMER.equals(memberInt.getMember().getCustGroup())) {
			throw new CustomerException(CustomerExceptionType.PotentialCustomerCannotContactVary);
		}
		return memberInt;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据客户编码获得客户积分信息 360专用
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.customer.server.manager.IIntegralManager#getMemberIntegralByMemberNumFor360(java.lang.String)
	 * @version 0.1 2014-04-11上午10:59:53
	 * @param memberNum
	 * @return
	 * @update 	2014-04-11上午10:59:53
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberIntegral getMemberIntegralByMemberNumFor360(String memberNum) {
		//最终需求确定 客户360不用查出作废客户的信息
		String memberId = alterMemberManager.getMemberIdByMemberNum(memberNum);
		return memberIntegralService.getMemberIntegralByMemberId(memberId);
	}
	@Override
	@Transactional(readOnly = true)
	public MemberIntegral getMemberIntegralByContactNum(String contactNum) {
		String memberId = alterMemberManager
				.getMemberIdByContactNum(contactNum);
		if(StringUtil.isBlank(memberId)){
			throw new CustomerException(CustomerExceptionType.LINKMANECODE_NOTFOUND);
		}
		MemberIntegral memberInt = memberIntegralService.getMemberIntegralByMemberId(memberId);
		if (Constant.CUST_GROUP_PC_CUSTOMER.equals(memberInt.getMember().getCustGroup())) {
			throw new CustomerException(CustomerExceptionType.PotentialCustomerCannotContactVary);
		}
		return memberInt;
	}

	@Override
	@Transactional(readOnly = true)
	public MemberIntegral getMemberIntegralByContactMobile(String mobilePhone) {
		String memberId = alterMemberManager
				.getMemberIdByContactMobile(mobilePhone);
		return memberIntegralService.getMemberIntegralByMemberId(memberId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdjustIntegral> searchAdjustIntegrals(AdjustIntegral integral,
			int start, int limit) {
		return integralService.searchAdjustIntegrals(integral, start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchAdjustIntegrals(AdjustIntegral integral) {
		return integralService.countSearchAdjustIntegrals(integral);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HandRewardIntegral> searchHandRewardIntegrals(
			HandRewardIntegral integral, int start, int limit) {
		return integralService
				.searchHandRewardIntegrals(integral, start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchHandRewardIntegrals(HandRewardIntegral integral) {
		return integralService.countSearchHandRewardIntegrals(integral);
	}

	@Override
	@Transactional
	public void updateIntegralConvertGift(IntegralConvertGift integral) {
		integralService.updateIntegralConvertGift(integral);

	}

	@Override
	@Transactional(readOnly = true)
	public List<IntegralConvertGift> searchIntegralConvertGift(
			IntegralConvertGiftCondition condition, int start, int limit) {
		return integralService.searchIntegralConvertGift(condition, start,
				limit);
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchIntegralConvertGift(
			IntegralConvertGiftCondition condition) {
		return integralService.countSearchIntegralConvertGift(condition);
	}

	@Override
	@Transactional
	public List<String> insertIntegralConvertGift(
			List<IntegralConvertGift> integrals) {
		// 数据完善，对前台传过来的数据进行完善
		List<IntegralConvertGift> newintegrals = IntegralConvertGiftData(integrals);
		// 定义返回不能兑换的礼品或者联系人列表
		List<String> elist;
		// 校验 是否能被兑换（包括礼品时间限制、区域限制、未启用）
		Set<String> slist = checkConvertGiftRule(newintegrals);
		if (!slist.isEmpty()) {
			elist = new ArrayList<String>(slist);
			// return elist; TODO 把不合格的礼品返回
			// 1,消息为，礼品不合格
			elist.add(0, "gift");
			return elist;
		}
		// 数据校验,获得积分不够的数据List
		elist = checkContactGiftIntegral(newintegrals);
		if (!elist.isEmpty()) {
			// return elist; TODO 把不合格的联系人返回
			elist.add(0, "integral");
			return elist;
		}
		// 对需要审批的数据进行封装,并生成工作流
		String wkid = getGiftApplyInfoForWKid(newintegrals);

		// 积分兑换--积分扣减
		userRuleIntegralDB(wkid, newintegrals);

		// 删除已兑换的礼品清单--暂存数据--根据部门ID删除
		deleteGiftConvertHoldRecode(ContextUtil.getCurrentUserDeptId());

		return null;
	}

	private List<IntegralConvertGift> IntegralConvertGiftData(
			List<IntegralConvertGift> integrals) {
		List<IntegralConvertGift> newdata = new ArrayList<IntegralConvertGift>();
		for (IntegralConvertGift integralConvertGift : integrals) {
			// 完善联系人对象信息
			if (null != integralConvertGift.getContact()) {
				integralConvertGift.setContact(alterMemberManager
						.getContact(integralConvertGift.getContact().getId()));
			}
			// 完善礼品对象信息
			if (null != integralConvertGift.getGift()) {
				integralConvertGift.setGift(integRuleManager
						.getGiftById(integralConvertGift.getGift().getId()));
				newdata.add(integralConvertGift);
			}
		}
		return newdata;
	}

	/**
	 * 
	 * <p>
	 * 检查兑换中使用的礼品，是否满足要求，返回不满足要求的联系人编码<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-7
	 * @param integrals
	 * @return List<String>
	 */
	private Set<String> checkConvertGiftRule(List<IntegralConvertGift> integrals) {
		// 兑换中不满足要求的礼品List
		Set<String> giftList = new HashSet<String>();
		// 礼品
		Gift gift;
		// 自身部门
		Department dept = ContextUtil.getCurrentUserDept();
		// 自身部门对应事业部门
		Department bussDept = baseDataManager.getCauseDepartment(dept.getId());
		// 对应部门没有事业部
		if (null == bussDept) {
			return giftList;
		}

		// 礼品兑换部门
		Department deptGift;
		// 礼品兑换是否启用
		boolean isStart;
		for (IntegralConvertGift integralConvertGift : integrals) {
			// 获得该单条数据中GIFT
			gift = integralConvertGift.getGift();
			// 礼品不能被当前营业部兑换（包括礼品时间限制、区域限制、未启用）
			// 兑换部门
			deptGift = gift.getDepartment();
			// 是否启用
			isStart = gift.getIsStart();

			if (!((gift.getBeginTime().before(new Date()) && gift.getEndTime()
					.after(new Date())) // 时间
					&& (deptGift == null || "".equals(deptGift.getId()) || bussDept
							.getId().equals(deptGift.getId())) // 兑换区域
			&& isStart)) { // 启动
				giftList.add(gift.getGiftNumber());
			}
		}
		return giftList;
	}

	/**
	 * 
	 * <p>
	 * 检查兑换记录，把积分不够的联系人编码返回<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-5
	 * @param integrals
	 *            void
	 */
	private List<String> checkContactGiftIntegral(
			List<IntegralConvertGift> integrals) {
		// 联系人对应的积分情况
		Map<Contact, ContactIntegral> cmap = new HashMap<Contact, ContactIntegral>();
		// 积分不够的联系人List
		List<String> integList = new ArrayList<String>();
		// 联系人
		Contact contact;
		// 联系人积分
		ContactIntegral contactIntegral;
		// 礼品
		Gift gift;
		// 兑换数量
		int convertNumber;
		for (IntegralConvertGift integralConvertGift : integrals) {
			// 获得单条数据中的联系人
			contact = integralConvertGift.getContact();
			// 获得该联系人对应的积分数据--数据库的值
			contactIntegral = getContactIntegral(contact.getId());
			// 获得该单条数据中GIFT
			gift = integralConvertGift.getGift();
			// 获得兑换数量
			convertNumber = integralConvertGift.getConvertNumber();
			// 如果是首次遇到该联系人
			if (cmap.get(contact) == null) {
				// 得到该联系人可以积分--来自数据库
				getContactIntegralDB(contactIntegral);
				cmap.put(contact, contactIntegral);
			}
			// 兑换礼品后得到的积分
			contactIntegral.setTempScore(contactIntegral.getTempScore()
					- gift.getNeedPoints() * convertNumber);
			// 从新设置联系人积分
			cmap.put(contact, contactIntegral);
		}
		// 对联系人进行循环，判断是否有积分不足的联系人，并把积分不够的联系人编码放入LIST
		for (Entry<Contact, ContactIntegral> cmapEntry : cmap.entrySet()) {
			contact = cmapEntry.getKey();
			contactIntegral = cmapEntry.getValue();
			if (contactIntegral.getTempScore() < 0) {
				integList.add(contact.getId());
			}
		}
		return integList;
	}

	/**
	 * 
	 * <p>
	 * 从数据库获得该联系人可以积分<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-7
	 * @param contactIntegral
	 *            void
	 */
	private void getContactIntegralDB(ContactIntegral contactIntegral) {
		// 获得联系人
		Contact contact = contactIntegral.getContact();
		// 判断该联系人是否是主联系人
		if (contact.getIsMainLinkMan()) {
			// 获得改联系人对应的会员积分信息
			MemberIntegral memberIntegral = getMemberIntegral(contact.getId());
			// 获得该会员积分对象的会员信息
			Member member = memberIntegral.getMember();
			// 判断是否仅限主联系人兑换积分
			if (member.getIsRedeempoints()) {
				// 剩余可用积分=会员可以积分
				contactIntegral.setTempScore(memberIntegral
						.getCurrentUsableScore());
			} else {
				contactIntegral.setTempScore(contactIntegral
						.getCurrentUsableScore());
			}
			// 不是主联系人，当前剩余积分为可以积分
		} else {
			contactIntegral.setTempScore(contactIntegral
					.getCurrentUsableScore());
		}
	}

	/**
	 * 
	 * <p>
	 * 封装需要审批的数据,并调用工作流，返回工作流ID<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-7
	 * @param integrals
	 *            void
	 */
	private String getGiftApplyInfoForWKid(List<IntegralConvertGift> integrals) {
		// 需要审批的
		GiftApplyInfo giftApplyInfo = new GiftApplyInfo();
		// 申请人工号
		String applyPersonNumber = ContextUtil.getCurrentUser().getEmpCode()
				.getEmpCode();
		// 到货部门
		String receiveDept = ContextUtil.getCurrentUserDept().getStandardCode();
		// 申请原因
		String applyReason = "客户积分礼品申请兑换";
		// 到货地址
		String receiveAddress = ContextUtil.getCurrentUserDept().getAddress();
		// 联系方式，手机电话或者固定电话
		String phone = ContextUtil.getCurrentUserDept().getPhone();

		giftApplyInfo.setApplyPersonNumber(applyPersonNumber);
		giftApplyInfo.setReceiveAddress(receiveAddress);
		giftApplyInfo.setApplyReason(applyReason);
		giftApplyInfo.setReceiveDept(receiveDept);
		giftApplyInfo.setPhone(phone);

		// 联系人
		Contact contact;
		// 礼品
		Gift gift;
		// 礼品列表
		List<com.deppon.crm.module.client.workflow.domain.GiftApplyInfo.Gift> gfs = new ArrayList<com.deppon.crm.module.client.workflow.domain.GiftApplyInfo.Gift>();
		com.deppon.crm.module.client.workflow.domain.GiftApplyInfo.Gift infacegift;
		for (IntegralConvertGift integralConvertGift : integrals) {
			infacegift = new com.deppon.crm.module.client.workflow.domain.GiftApplyInfo.Gift();
			// 联系人
			contact = integralConvertGift.getContact();
			// 礼品
			gift = integralConvertGift.getGift();
			// 礼品编码设置
			infacegift.setGiftNumber(gift.getGiftNumber());
			// 兑换人身份证
			infacegift.setExchangePersonNumber(contact.getIdCard());
			// 兑换人会员ID
			infacegift.setMemberNumber(getMemberIntegral(contact.getId())
					.getMember().getCustNumber());
			// 兑换数量
			infacegift.setGiftCount(integralConvertGift.getConvertNumber());
			// 礼品编码
			infacegift.setGiftNumber(gift.getGiftNumber());
			gfs.add(infacegift);
		}
		// 礼品列表
		giftApplyInfo.setGifts(gfs);

		String workFlowId = null;
		// 工作流ID
		try {
			workFlowId = giftApplyOperate.giftApply(giftApplyInfo);
		} catch (CrmBusinessException e) {
			
			throw new IntegException(
					IntegExceptionType.IntegGiftChangeInterFaces,
					e.getMessage());
		}
		return workFlowId;
	}

	/**
	 * 
	 * <p>
	 * 通过联系人获得联系人对应的联系人积分对象和会员积分对象<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-5
	 * @param contact
	 * @return ContactMemberIntegral
	 */
	@SuppressWarnings("unused")
	private ContactMemberIntegral getContactMemberIntegral(Contact contact) {
		return new ContactMemberIntegral(getMemberIntegral(contact.getId()),
				getContactIntegral(contact.getId()));

	}

	/**
	 * 
	 * <p>
	 * 对礼品申请的明细进行操作，并保持到数据库<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-7
	 * @param workFlowId
	 * @param integrals
	 *            void
	 */
	private void userRuleIntegralDB(String workFlowId,
			List<IntegralConvertGift> integrals) {

		// 把礼品数据进行加工，使得一个联系人兑换多个礼品
		Map<Contact, List<IntegralConvertGift>> map = new HashMap<Contact, List<IntegralConvertGift>>();
		// 联系人
		Contact contact;
		// 同一个联系人申请的礼品清单
		List<IntegralConvertGift> glist;
		// 对每个申请进行操作
		for (IntegralConvertGift integralConvertGift : integrals) {
			// 联系人
			contact = integralConvertGift.getContact();
			// 如果是首次遇到该联系人
			if (map.get(contact) == null) {
				glist = new ArrayList<IntegralConvertGift>();
				map.put(contact, glist);
			}
			// 获得相同联系人对应的礼品清单
			glist = map.get(contact);
			// 把当前的礼品追加到清单中
			glist.add(integralConvertGift);
			// 把联系人和清单绑定
			map.put(contact, glist);
		}
		// 礼品清单数组
		IntegralConvertGift[] integralConvertGifts;
		// 数组下标
		int i = 0;
		// 联系人积分对象
		ContactIntegral contactIntegral;
		// 会员积分对象
		MemberIntegral memberIntegral;
		// 非主联系人积分列表
		List<ContactIntegral> notMainContactIntegralList;
		// 非主联系人积分对象List
		ContactIntegral[] contactIntegrals = null;
		// 对规则进行循环调用
		for (Entry<Contact, List<IntegralConvertGift>> mapEntry : map
				.entrySet()) {
			// 获得map中的联系人
			contact = mapEntry.getKey();
			// 获得联系人对应的礼品清单
			glist = mapEntry.getValue();
			// 把礼品清单LIST转化成数组
			integralConvertGifts = new IntegralConvertGift[glist.size()];
			i = 0;
			for (IntegralConvertGift integralConvertGift : glist) {
				integralConvertGift
						.setSendStatus(Constant.INTEG_CHANGE_APPROVALING);
				integralConvertGift.setWorkFlowId(workFlowId);
				integralConvertGift.setConvertIdCard(integralConvertGift
						.getContact().getIdCard());
				integralConvertGifts[i++] = integralConvertGift;
			}
			// 联系人积分对象
			contactIntegral = getContactIntegral(contact.getId());
			// 会员积分对象
			memberIntegral = getMemberIntegral(contact.getId());
			// 非主联系人积分列表
			notMainContactIntegralList = getNotMainContact(memberIntegral
					.getMember().getId());
			i = 0;
			// 对非主联系人List转化为数组
			if (!notMainContactIntegralList.isEmpty()) {
				contactIntegrals = new ContactIntegral[notMainContactIntegralList
						.size()];
				for (ContactIntegral contactIntegral2 : notMainContactIntegralList) {
					contactIntegrals[i++] = contactIntegral2;
				}
			}
			IIntegralRule integralRule = RuleFactory.getRule(
					RuleFactory.CONVERTGIFT_INTEGRAL, contactIntegral,
					contactIntegrals, new MemberIntegral[] { memberIntegral },
					null, 0, integralConvertGifts);

			IntegralOperation integralOperation = integralRule
					.getBizIntegralOperation();

			if (integralOperation != null) {
				integralService.saveIntegralOperation(integralOperation);
				updateGiftNum(integrals);
			}
		}
	}

	@Override
	@Transactional
	public void approvalIntegralConvertGift(boolean flg, String wkid)
			throws IntegException {
		// 根据wkid更新数据
		IntegralConvertGift integralConvertGift = new IntegralConvertGift();
		integralConvertGift.setWorkFlowId(wkid);
		List<IntegralConvertGift> list = integralService
				.searchIntegralConvertGifts(integralConvertGift, 0, 100);
		if (list.isEmpty()){
			throw new IntegException(IntegExceptionType.IntegWKidNoData);
		}
		String appStat;
		// 审批通过
		if (flg) {
			appStat = Constant.INTEG_CHANGE_PASS;
			// 审批不同意
		} else {
			// 恢复库存数量
			List<Gift> glist = new ArrayList<Gift>();
			// 审批中的礼品
			Gift giftApp;
			// 库存中的礼品
			Gift giftSrc;
			for (IntegralConvertGift integralConvertGift2 : list) {
				giftApp = integralConvertGift2.getGift();
				giftSrc = integRuleManager.getGiftById(giftApp.getId());
				// 恢复库存数量
				giftSrc.setInventNumber(giftSrc.getInventNumber()
						+ integralConvertGift2.getConvertNumber());
				glist.add(giftSrc);
			}
			// 更新库存
			for (Gift gift : glist) {
				integRuleManager.updateGiftApproval(gift);
			}
			// 恢复积分
			updateContactIntegralNum(list);

			appStat = Constant.INTEG_CHANGE_REJECT;
		}
		for (IntegralConvertGift integralConvertGift2 : list) {
			integralConvertGift2.setSendStatus(appStat);
			integralService.updateIntegralConvertGift(integralConvertGift2);
		}
	}

	/**
	 * 
	 * <p>
	 * 在审批拒绝时，恢复联系人积分和会员积分<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-5-24
	 * @param list
	 *            void
	 */
	@Transactional
	private void updateContactIntegralNum(List<IntegralConvertGift> list) {
		// 礼品兑换数量
		int num = 0;
		// 礼品所需积分
		int scroe = 0;
		// 联系人积分
		ContactIntegral contactIntegral;
		// 联系人
		Contact contact = null;
		// 会员积分
		MemberIntegral memberIntegral;
		// 礼品
		Gift gift;
		for (IntegralConvertGift integralConvertGift : list) {
			gift = integralConvertGift.getGift();
			num = integralConvertGift.getConvertNumber();
			scroe = scroe + num * gift.getNeedPoints();
			contact = integralConvertGift.getContact();
		}
		// 获得联系人积分对象
		contactIntegral = integralService.getContactIntegralByContact(contact
				.getId());
		// 获得会员积分对象
		memberIntegral = getMemberIntegral(contact.getId());

		// 更新数据
		// 本期可用积分
		contactIntegral.setCurrentUsableScore(contactIntegral
				.getCurrentUsableScore() + scroe);
		// 本期已用积分
		contactIntegral.setCurrentToUsesScore(contactIntegral
				.getCurrentToUsesScore() - scroe);
		// 累计已用积分
		contactIntegral.setTotalToUsesScore(contactIntegral
				.getTotalToUsesScore() - scroe);

		// 设置会员积分
		memberIntegral.setCurrentUsableScore(memberIntegral
				.getCurrentUsableScore() + scroe);// 本期可用积分
		memberIntegral.setCurrentToUsesScore(memberIntegral
				.getCurrentToUsesScore() - scroe);// 本期已用积分
		memberIntegral.setTotalToUsesScore(memberIntegral.getTotalToUsesScore()
				- scroe);// 累计已用积分
		// 更新联系人积分
		integralService.updateContactIntegral(contactIntegral);
		// 更新会员积分
		integralService.updateMemberIntegral(memberIntegral);
	}

	/**
	 * 
	 * <p>
	 * 生成清单时，立马减少库存<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-5-24
	 * @param integrals
	 *            void
	 */

	@Transactional
	private void updateGiftNum(List<IntegralConvertGift> integrals) {
		// 更新库存数量
		List<Gift> glist = new ArrayList<Gift>();
		// 兑换的礼品
		Gift giftApp;
		// 库存中的礼品
		Gift giftSrc;
		for (IntegralConvertGift integralConvertGift2 : integrals) {
			giftApp = integralConvertGift2.getGift();
			giftSrc = integRuleManager.getGiftById(giftApp.getId());
			// 更新库存数量
			giftSrc.setInventNumber(giftSrc.getInventNumber()
					- integralConvertGift2.getConvertNumber());
			glist.add(giftSrc);
		}
		// 更新库存
		for (Gift gift : glist) {
			integRuleManager.updateGift(gift);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchIntegralConvertGiftForContactId(
			List<String> contactIdList) {
		return integralService
				.countSearchIntegralConvertGiftForContactId(contactIdList);
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchWaybillIntegralForContactId(
			List<String> contactIdList) {
		return waybillIntegralService
				.countSearchWaybillIntegralForContactId(contactIdList);
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchHandRewardIntegralForContactId(
			List<String> contactIdList) {
		return integralService
				.countSearchHandRewardIntegralForContactId(contactIdList);
	}

	@Override
	@Transactional(readOnly = true)
	public long countSearchAdjustIntegralForContactId(List<String> contactIdList) {
		return integralService
				.countSearchAdjustIntegralForContactId(contactIdList);
	}

	/**
	 * 
	 * <p>
	 * 根据客户ID，获得该客户的非主联系人积分对象列表<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-5-4
	 * @param memberId
	 * @return List<ContactIntegral>
	 */
	@Transactional(readOnly = true)
	public List<ContactIntegral> getNotMainContact(String memberId) {
		List<ContactIntegral> contactList = getContactIntegralListByMemId(memberId);
		Iterator<ContactIntegral> it = contactList.iterator();
		while (it.hasNext()) {
			ContactIntegral contactIntegral = it.next();
			if (contactIntegral.getContact().getIsMainLinkMan()) {
				it.remove();
			}
		}
		return contactList;
	}

	/**
	 * 
	 * <p>
	 * 根据联系人ID，获得该联系人对应的会员积分对象<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-5-4
	 * @param contactId
	 * @return MemberIntegral
	 */
	@Transactional(readOnly = true)
	public MemberIntegral getMemberIntegral(String contactId) {
		return getMemberIntegralByContactId(contactId);
	}

	/**
	 * 
	 * <p>
	 * 根据联系人ID，获得该联系人的积分对象<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-5-4
	 * @param contactId
	 * @return ContactIntegral
	 */
	@Transactional(readOnly = true)
	public ContactIntegral getContactIntegral(String contactId) {
		return integralService.getContactIntegralByContact(contactId);
	}

	@Override
	@Transactional
	public void updateIntegralConvertGiftSend(List<String> idlist) {
		for (String id : idlist) {
			IntegralConvertGift integral = new IntegralConvertGift();
			integral.setId(id);
			integral.setSendStatus(Constant.INTEG_CHANGE_TRANSMITTED);
			integral.setSendTime(new Date());
			integralService.updateIntegralConvertGift(integral);
		}
	}

	@Override
	@Transactional
	public void updateIntegralConvertGiftRecover(List<String> idlist) {
		updateIntegralConvertGiftStatus(idlist, Constant.INTEG_CHANGE_PASS);
	}

	private void updateIntegralConvertGiftStatus(List<String> idlist,
			String sendStatus) {
		for (String id : idlist) {
			IntegralConvertGift integral = new IntegralConvertGift();
			integral.setId(id);
			integral.setSendStatus(sendStatus);
			integralService.updateIntegralConvertGift(integral);
		}
	}

	@Override
	@Transactional
	public void saveIntegralConvertGift(List<IntegralConvertGift> integralList) {
		// 插入新增加的礼品暂存数据
		GiftConvertHoldRecode giftConvertHoldRecode;// 插入数据对象
		List<GiftConvertHoldRecode> giftConvertHoldRecodes = new ArrayList<GiftConvertHoldRecode>();// 插入数据记录
		List<String> ids = new ArrayList<String>();// 得到上次暂存数据ID
		for (IntegralConvertGift integralConvertGift : integralList) {
			if (integralConvertGift.getId() != null) {
				giftConvertHoldRecode = new GiftConvertHoldRecode();
				// 联系人
				giftConvertHoldRecode.setContact(integralConvertGift
						.getContact());
				// 礼品
				giftConvertHoldRecode.setGift(integralConvertGift.getGift());
				// 数量
				giftConvertHoldRecode.setConvertNumber(integralConvertGift
						.getConvertNumber());
				// 添加时间
				giftConvertHoldRecode.setCreateDate(new Date());
				// 添加人
				giftConvertHoldRecode.setCreateUser(ContextUtil
						.getCurrentUser().getId());
				// 创建部门
				giftConvertHoldRecode.setCreateDept(ContextUtil
						.getCurrentUserDept());
				// 添加到保存集合中
				giftConvertHoldRecodes.add(giftConvertHoldRecode);
			} else {
				ids.add(integralConvertGift.getId());
			}
		}
		// 删除被移除的数据
		deleteGiftConvertHoldRecode(ContextUtil.getCurrentUserDept().getId());
		// 保存数据
		integralService.saveTemporyGiftList(giftConvertHoldRecodes);
	}

	/**
	 * 
	 * <p>
	 * 暂存数据删除<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-5
	 * @param ids
	 *            void
	 */
	private void deleteGiftConvertHoldRecode(String deptId) {
		// 使用SQL语句，不存在的ID，数据被删除
		// delete GiftConvertHoldRecode where FDEPTID = "" and FGIFTID is not
		// in(ids)
		integralService.deleteTemporaryGiftBill(deptId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IntegralConvertGift> getMyIntegralConvertGift() {
		IntegralConvertGift integral = new IntegralConvertGift();
		integral.setSendStatus(Constant.INTEG_CHANGE_NOAPPROVE);
		integral.setCreateUser(ContextUtil.getCurrentUserEmpId());
		return integralService.searchIntegralConvertGifts(integral, 0, 1000);
	}

	@Override
	@Transactional(readOnly = true)
	public ContactIntegral getContactIntegralByContactNumber(
			String contactNumber) {
		List<String> contactIds = new ArrayList<String>();
		String contactId = contactManager
				.getContactIdByContactNumber(contactNumber);
		// 不存在 这个联系人编码
		if (ValidateUtil.objectIsEmpty(contactId)) {
			return null;
		}
		contactIds.add(contactId);
		List<ContactIntegral> list = searchContactIntegralsForContactId(
				contactIds, 0, 1);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 
	 * <p>
	 * 根据客户Id查询对应的联系人积分对象，主联系人积分对象是第一个元素
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-5-8
	 * @param custId
	 * @return List<ContactIntegral>
	 * @see com.deppon.crm.module.customer.server.manager.IIntegralManager#get3ContactIntegral(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ContactIntegral> get3ContactIntegral(String memberId) {
		// 返回联系人积分列表
		List<ContactIntegral> contactList = new ArrayList<ContactIntegral>();

		// 放入联系人积分对象个数
		int i = 0;
		// 主联系人积分对象
		ContactIntegral mainContactIntegral = null;
		// 是否找到主联系人
		boolean ifMain = false;
		// 根据客户Id获得所有联系人的积分对象
		List<ContactIntegral> allContactList = this
				.get360ContactIntegralListByMemId(memberId);
		for (ContactIntegral contactIntegral : allContactList) {
			// 在联系人积分对象列表中查找主联系人积分对象
			if (contactIntegral.getContact().getIsMainLinkMan()) {
				mainContactIntegral = contactIntegral;
				ifMain = true;
			} else {
				if (i > 2 && ifMain){
					break;
				}
				if (i < 2){
					contactList.add(contactIntegral);
				}
				i++;
			}
		}
		// 追加非主联系人积分对象
		contactList.add(0, mainContactIntegral);

		return contactList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.manager.IIntegralManager#
	 * searchTemporaryGiftBill()
	 */
	@Override
	public List<IntegralConvertGift> searchTemporaryGiftBill() {
		List<GiftConvertHoldRecode> tempGiftBill = integralService
				.getTemporaryGiftBill(ContextUtil.getCurrentUserDept().getId());
		List<IntegralConvertGift> integs = new ArrayList<IntegralConvertGift>();
		if (tempGiftBill.size() > 0) {
			for (int i = 0; i < tempGiftBill.size(); i++) {
				IntegralConvertGift conGif = new IntegralConvertGift();
				conGif.setId(tempGiftBill.get(i).getId());
				conGif.setContact(tempGiftBill.get(i).getContact());
				conGif.setGift(tempGiftBill.get(i).getGift());
				conGif.setScore(tempGiftBill.get(i).getGift().getNeedPoints()
						* tempGiftBill.get(i).getConvertNumber());
				conGif.setConvertNumber(tempGiftBill.get(i).getConvertNumber());
				integs.add(conGif);
			}
		}
		// 按照创建数据的部门，获得数据集
		return integs;
	}
}
