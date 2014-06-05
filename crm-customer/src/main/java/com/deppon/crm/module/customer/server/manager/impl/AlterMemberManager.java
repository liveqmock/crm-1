/**
 * 
 */
package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.server.manager.CustomerRule;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberWorkFlowManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IApprovingWokflowDataService;
import com.deppon.crm.module.customer.server.service.IContactVaryService;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ExpressAuthDeptUtil;
import com.deppon.crm.module.customer.server.utils.MemberUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.CustAddress;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.CustomerLocation;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Member4All;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MyToDoWorkFlow;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * @作者：罗典
 * @时间：2012-3-23
 * @描述：修改会员功能
 * */
@Transactional
public class AlterMemberManager implements IAlterMemberManager {
	// 修改会员功能service层
	private IAlterMemberService alterMemberService;

	private IMemberWorkFlowManager memberWorkFlowManager;

	private IApprovingWokflowDataService approvingWokflowDataService;

	private IMemberManager memberManager;
	// 合同service
	public IContractService contractService;
	// 变更联系人service
	public IContactVaryService contactVaryService;
	// 客户标签manager
	private ICustLabelManager custLabelManager;
	//疑重manager
	private IRepeatedCustManager repeatedCustManager;
	//消息manager
	private IMessageManager messageManager;
	
	private IUserService userService;
	
	/**
	 *@author chenaichun
	 * @date 2014年4月21日 下午6:07:52 
	 * @param repeatedCustManager the repeatedCustManager to set
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

	public void setCustLabelManager(ICustLabelManager custLabelManager) {
		this.custLabelManager = custLabelManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-10-11
	 * @描述：根据联系人编码查询联系人信息
	 * @参数：number 联系人编码
	 * @返回值：Contact 联系人信息
	 * */
	public Contact getContactByNum(String number) {
		return alterMemberService.getContactByNum(number);
	}
	
	/**
	 * 
	 * @Title: custLabelIsChanged
	 *  <p>
	 * @Description: 校验前后两个CustLabel是否一致
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-17
	 * @return boolean
	 * @throws
	 */
	public boolean custLabelIsChanged(List<CustLabel> oldCustLabels,List<CustLabel> newCustLabels ){
		int j = 0;
		if (!ValidateUtil.objectIsEmpty(newCustLabels)) {
			for(int k =0;k < newCustLabels.size();k++){
				for (int l = 0; l < oldCustLabels.size(); l++) {
					if (newCustLabels.get(k).getLabel().getId()
							==oldCustLabels.get(l).getLabel().getId()) {
						j++;
					}
				}
			}
		}
		//未修改
		if (j==newCustLabels.size() && j==oldCustLabels.size()) {
			return false;
		}
		//有修改
		return true;
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @描述：修改会员及相关信息
	 * @参数：approveDataList 需修改的审批数据集合，addPojoMap 需新增的数据实体集合， deleteDataList
	 *                     需删除的审批数据集合，memberId,会员ID
	 * @返回值：是否修改成功
	 * */
	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public String alterMember(List<ApproveData> updateDataAllList,
			Map<String, List> addPojoMap, List<ApproveData> deleteDataList,
			String memberId, Member member) throws MemberException {
		//数据库会员对象
		MemberOperationLog log = null;
		//这个dataBaseMember 并不会查出所有的客户信息，只查出了客户基本信息、联系人扩展信息和主联系人信息
		Member dataBaseMember  = alterMemberService.getMemberAllByIdNEW(memberId);
		//校验客户信息是否有修改，没有修改任何信息则不允许保存并且提示
		if (!custLabelIsChanged(dataBaseMember.getCustLabels(), member.getCustLabels())
				&&CollectionUtils.isEmpty(updateDataAllList)
				&& CollectionUtils.isEmpty(addPojoMap.get("ShuttleAddress"))
				&& CollectionUtils.isEmpty(addPojoMap.get("Contact"))
				&& CollectionUtils.isEmpty(addPojoMap.get("Account"))
				&& CollectionUtils.isEmpty(addPojoMap.get("PreferenceAddress"))
				&& CollectionUtils.isEmpty(deleteDataList)) {
			throw new CustomerException(
					CustomerExceptionType.CUST_INFO_NOTMODIFY);
		}
		
		if (isScatterUpgrade(member, dataBaseMember)) {
			addApproveData(updateDataAllList, member, dataBaseMember);
			log = createLog(memberId, Constant.CUST_OPERATETYPE_SCATTERUPGRADE);
		}else {
			log = createLog(memberId,Constant.OPERATOR_TYPE_UPDATE);
		}
		// 有修改数据时，存入旧值，并存入审批数据集合
		if (updateDataAllList != null && updateDataAllList.size() > 0) {
			// 不进行任何存的操作，只将字段原来的值设置到approveData里
			//member都是新的值 所以不应该传member
			alterMemberService.saveOldValueToApproveData(updateDataAllList,dataBaseMember);
		} else {
			updateDataAllList = new ArrayList<ApproveData>();
		}

		// 有新增数据时保存需保存的实体数据，并存入审批数据集合
		if (addPojoMap != null && addPojoMap.size() > 0) {
			alterMemberService.savePojoInfo(addPojoMap, updateDataAllList,
					member, memberId);
		}
		// 有删除数据的审批数据，并存入审批数据集合
		if (deleteDataList != null && deleteDataList.size() > 0) {
			alterMemberService.changeCancelDataToApproveList(deleteDataList,
					updateDataAllList);
		}
		Set<String> dataTypes = new HashSet<String>();

		// 获取需启动工作流的类型,并启动工作流
		for (ApproveData approveData : updateDataAllList) {
			// 通过类名和字段名获取工作流类型
			String workFlowType = alterMemberService.getApproveType(
					approveData.getClassName(), approveData.getFieldName());
			if (!ValidateUtil.objectIsEmpty(workFlowType)) {
				dataTypes.add(workFlowType);
			}
		}
		/**
		 * 1、潜客修改关键信息不用走归属部门经理审批的工作流</br>
		 * 2、固定客户、散客非归属部门修改了关键信息（且合同客户关键信息），但是非临欠额度和新增账号，归属部门营业部经理审批</br>
		 * 3、固定客户、散客修改了非归属部门仅仅修改临欠额度和新增账号，修改部门营业部经理审批
		 * 4、若为潜客，修改关键信息不用工作流审批
		 */
		boolean isBelongModify = member.getDeptId().equals(
				ContextUtil.getCurrentUserDeptId());
		//非归属部门修改，产生工作流（潜客、散客、固定客户）
		if (dataTypes.contains(Constant.BaseDateType)&&!isBelongModify) {
			dataTypes.add(Constant.AccountDateType);
		}
		//若营业部经理修改了潜客关键信息，不需要营业部经理审批
		if ( !MemberUtil.isModifyAccount(updateDataAllList)
				&& Constant.CUST_GROUP_PC_CUSTOMER
						.equals(member.getCustGroup())) {
			dataTypes.remove(Constant.AccountDateType);
		}
		//若营业部经理仅仅修改了散客临欠额度，不需要营业部经理审批
		if (!ExpressAuthDeptUtil.isDeptManager(ContextUtil.getRoles())
				&& MemberUtil.isModifyProcRedit(updateDataAllList)
				&& Constant.CUST_GROUP_PC_CUSTOMER
				.equals(member.getCustGroup())&&!isBelongModify) {
			dataTypes.add(Constant.AccountDateType);
		}
		
		//若营业部经理仅仅修改了散客临欠额度，不需要营业部经理审批
		if (ExpressAuthDeptUtil.isDeptManager(ContextUtil.getRoles())
				&& MemberUtil.isOnlyModifyProcRedit(updateDataAllList)
				&& Constant.CUST_GROUP_SC_CUSTOMER
				.equals(member.getCustGroup())) {
			dataTypes.remove(Constant.AccountDateType);
		}
		
		String result = "";
		// 非合同客户--(查询是否有有效和审批中的合同)
		if (!memberManager.isContractMember(memberId)) {
			for (String dataType : dataTypes) {
				if (dataType.equals(Constant.BaseDateType)) {
					dataTypes.remove(dataType);
				}
			}
		}
		//有合同的客户修改关键信息，由经理先审批，然后客户管理组审批
		if (!ExpressAuthDeptUtil.isDeptManager(ContextUtil.getRoles())
				&& dataTypes.contains(Constant.BaseDateType)
				&& Constant.CUST_GROUP_RC_CUSTOMER
				.equals(member.getCustGroup())) {
			dataTypes.add(Constant.AccountDateType);
		}
		
		if (dataTypes.size() != 0) {
			long workFlowId = memberWorkFlowManager
					.createNewModifyMemberWorkFlow(dataTypes, memberId,updateDataAllList);
			for (ApproveData approveData : updateDataAllList) {
				approveData.setWorkFlowId(String.valueOf(workFlowId));
			}
			
			Set<ApprovingWorkflowData> contactWorkflowDatas = CustomerRule.lockApproveData(
					updateDataAllList, memberId, workFlowId, member,dataBaseMember.getContactList());
			// 保存审批中中不可用的联系人信息
			approvingWokflowDataService
					.batchCreateContactWorkflowData(contactWorkflowDatas);
			// 启动工作流后保存审批数据，修改会员状态为[审批中]
			alterMemberService.alterMemberStatus(memberId, "1");
			result = String.valueOf(workFlowId);
			// 审批中的修改记录 先为失效数据
			log.setWorkflowId(result);
			log.setEffect(false);
		} else {
			// 未启动工作流则直接修改
			alterMemberService.updateMemberInfo(updateDataAllList, true);
			//修改后的member和extend信息
			Member mem = alterMemberService.getMemberById(memberId);
			memberManager.updateExtendAndLog(mem, mem.getMemberExtend(), Constant.OPERATOR_TYPE_UPDATE);
			memberManager.saveBusinessOpportunity(mem);
			result = "";
			// 不审批的修改记录 理解生效
			log.setEffect(true);
			if(isScatterUpgrade(member, dataBaseMember)){
				memberManager.deleteUpGradeCustomerData(member.getCustNumber());
			}
			//插入代办
			if (!isBelongModify) {
				Message todoMessage = new Message();
				todoMessage.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
				todoMessage.setTaskcount(1);
//			todoMessage.setUserid(Integer.parseInt(user.getId()));
				todoMessage.setDeptId(Integer.parseInt(member.getDeptId()));
				todoMessage.setIshaveinfo(ContextUtil.getCurrentUserDeptName()+"修改了客户编码为："+member.getCustNumber()+"，请到客户详情界面查看！");
				messageManager.addMessage(todoMessage);
			}
		}

		// 持久化 会员修改日志
		alterMemberService.saveMemberOperationLog(log);
		String logId = log.getId();
		alterMemberService.saveApproveData(updateDataAllList, logId);
		if (member.getCustLabels() != null && member.getCustLabels().size() > 0) {
			CustLabel custLabel = member.getCustLabels().get(0);
			custLabelManager.updateCustLabel(member.getCustLabels(),
					custLabel.getCustId(), Constant.MEMBERTYPE_IN_CUSTLABEL,
					custLabel.getLabel().getDeptId());
		} else {
			custLabelManager.updateCustLabel(null, member.getId(),
					Constant.MEMBERTYPE_IN_CUSTLABEL, member.getDeptId());
		}
		return result;
	}

	private boolean isScatterUpgrade(Member member, Member dataBaseMember) {
		return Constant.CUST_GROUP_SC_CUSTOMER.equals(dataBaseMember
				.getCustGroup())
				&& Constant.CUST_GROUP_RC_CUSTOMER
						.equals(member.getCustGroup());
	}

	/**
	 * 
	 * <p>
	 * Description:散客升级加入客户等级和客户性质变化数据<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-8
	 * @param updateDataAllList
	 * @param member
	 * @param dataBaseMember
	 * void
	 */
	private void addApproveData(List<ApproveData> updateDataAllList,
			Member member, Member dataBaseMember) {
		ApproveData approveData = new ApproveData();
		approveData.setClassId(member.getId());
		approveData.setClassName(Member.class.getSimpleName());
		approveData.setOldValue(dataBaseMember.getCustGroup());
		approveData.setNewValue(member.getCustGroup());
		approveData.setFieldName("custGroup");
		approveData.setHandleType(Constant.APPROVEDATA_UPDATE);
		updateDataAllList.add(approveData);
		
		approveData = new ApproveData();
		approveData.setClassId(member.getId());
		approveData.setClassName(Member.class.getSimpleName());
		approveData.setOldValue(dataBaseMember.getDegree());
		approveData.setNewValue(member.getDegree());
		approveData.setFieldName("degree");
		approveData.setHandleType(Constant.APPROVEDATA_UPDATE);
		updateDataAllList.add(approveData);
	}
	/**
	 * 
	 * <p>
	 * Description:记录会员操作日志<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param memberId
	 * @param type
	 * @return
	 * MemberOperationLog
	 */
	private MemberOperationLog createLog(String memberId,String type) {
		MemberOperationLog log = new MemberOperationLog();
		Member member = new Member();
		member.setId(memberId);
		log.setMember(member);
		log.setModifyUserId(ContextUtil.getCreateOrModifyUserId());
		log.setModifyUserName(ContextUtil.getCurrentUser().getEmpCode()
				.getEmpName());
		log.setDepartment(ContextUtil.getCurrentUserDept());
		log.setModifyDate(new Date());
		log.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		log.setOperateType(type);
		return log;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-19
	 * @描述：根据联系人ID查询会员信息,及是否为（月结）合同客户(存入是否允许联系人兑换积分字段中返回)
	 * :提供官网查询使用
	 * @参数：linkmanId 联系人Id,部门ID
	 * @返回值：Member 包含联系人信息的会员信息
	 * */
	@Transactional(readOnly = true)
	public Member getMemberBylinkmanId(String linkmanId, String deptId) {
		return alterMemberService.getMemberBylinkmanId(linkmanId, deptId);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-30
	 * @描述：根据会员查询条件查询会员信息
	 * @参数：condition 会员查询条件
	 * @返回值：只包含联系人集合及会员基本信息的会员信息
	 * */
	@Transactional(readOnly = true)
	@Override
	public List<MemberResult> searchMember(MemberCondition condition) {
		Assert.notNull(condition, "condition must not null");
		return alterMemberService.searchMember(condition);
	}

	public List<MemberResult> searchMemberListFor360(MemberCondition condition) {
		// 查询条件为空，抛出异常
		if (ValidateUtil.objectIsEmpty(condition)
				|| StringUtils.isEmpty(condition.getMemberId())
				&& StringUtils.isEmpty(condition.getDeptId())
				&& StringUtils.isEmpty(condition.getCustName())
				&& StringUtils.isEmpty(condition.getCustNumber())
				&& StringUtils.isEmpty(condition.getLinkManName())
				&& StringUtils.isEmpty(condition.getLinkManNumber())
				&& StringUtils.isEmpty(condition.getCustGrad())
				&& StringUtils.isEmpty(condition.getMobile())
				&& StringUtils.isEmpty(condition.getTelePhone())
				&& StringUtils.isEmpty(condition.getIdCard())
				&& ValidateUtil.objectIsEmpty(condition.getStart())
				&& ValidateUtil.objectIsEmpty(condition.getDeptIds())
				&& StringUtils.isEmpty(condition.getTaxregNumber())
				&& ValidateUtil.objectIsEmpty(condition.getVersionNumber())) {
			throw new CustomerException(
					CustomerExceptionType.ConditionCanNotNull);
		}
		return alterMemberService.searchMemberListFor360(condition);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-30
	 * @描述：根据会员查询条件查询会员信息
	 * @参数：condition 会员查询条件
	 * @返回值：只包含联系人集合及会员基本信息的会员信息
	 * */
	@Transactional(readOnly = true)
	@Override
	public List<MemberResult> searchMemberWithAuth(MemberCondition condition) {
		Assert.notNull(condition, "condition must not null");
		return alterMemberService.searchMemberWithAuth(condition);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	@Transactional(readOnly = true)
	@Override
	public Member getMemberAllById(MemberCondition searchCustCondition) {
		Member mem = alterMemberService.getMemberAllById(searchCustCondition
				.getMemberId());
		if(!ValidateUtil.objectIsEmpty(mem.getMemberExtend())
				&&!StringUtils.isEmpty(mem.getMemberExtend().getVolumePotential())){
			mem.setCustPotentialType(mem.getMemberExtend().getVolumePotential());
		}
		return mem;
	}

	/**
	 * @作者：李学兴
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询作废会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	@SuppressWarnings("serial")
	@Override
	public Member getInvalidMemberAllById(MemberCondition searchCustCondition) {
		try {
			Member member = alterMemberService
					.getInvalidMemberAllById(searchCustCondition.getMemberId());
			if (null == member) {
				throw new CustomerException(CustomerExceptionType.DataError);
			} else if (Constant.Lose
					.equals(searchCustCondition.getCustStatus())
					&& member.isFinOver()) {
				throw new CustomerException(CustomerExceptionType.CUST_FIN_OVER);
			} else {
				return member;
			}
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-7
	 * @功能描述：根据工作流ID查询相关审批数据
	 * @参数：workFlowId 工作流ID
	 * @返回值：List<ApproveData> 审批数据集合
	 * */
	@Transactional(readOnly = true)
	@Override
	public List<ApproveData> searchApproveData(String workFlowId) {
		return alterMemberService.searchApproveData(workFlowId);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据条件查询会员信息总数
	 * @参数: 会员查询条件
	 * @返回值：满足条件的总数
	 * */
	@Transactional(readOnly = true)
	public int countMemberByCondition(MemberCondition condition) {
		return alterMemberService.countMemberByCondition(condition);
	}

	public IAlterMemberService getAlterMemberService() {
		return alterMemberService;
	}

	public void setAlterMemberService(IAlterMemberService alterMemberService) {
		this.alterMemberService = alterMemberService;
	}

	public void setMemberWorkFlowManager(
			IMemberWorkFlowManager memberWorkFlowManager) {
		this.memberWorkFlowManager = memberWorkFlowManager;
	}

	/**
	 * @param messageManager : set the property messageManager.
	 */
	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-11
	 * @描述：完成工作流审批
	 * @参数: workFlowId 工作流ID ;isSuccess 是否成功 true为成功，false为失败
	 * @返回值：无
	 * */
	@Transactional
	@Override
	public void disposeWorkflow(String workFlowId, boolean isSuccess) {
		// 获取审批数据
		List<ApproveData> adList = this.alterMemberService
				.searchApproveData(workFlowId);
//		boolean changeBaseInfo = false;
//		boolean changeAccountInfo = false;
//		String memberId = "";
//		Member memb = null;
//		for (ApproveData ad : adList) {
//			// 会员信息封装
//			if (ad.getClassName().equals("Member")) {
//				changeBaseInfo = true;
//				memberId = ad.getClassId();
//				//修改账号信息如果客户是作废的则保持原状态（迁移数据数据字典不照即使不改基本信息也会走该路线）
//				memb = alterMemberService.getMemberById(memberId);
//				break;
//			}
//			// 联系人信息封装
//			else if (ad.getClassName().equals("Contact")) {
//				changeBaseInfo = true;
//				Contact contact = alterMemberService.getContact(ad.getClassId());
//				memberId = contact.getCustId();
//				break;
//			}// 账号信息封装
//			else if (ad.getClassName().equals("Account")) {
//				changeAccountInfo = true;
//				Account account = alterMemberService.getAccountById(ad.getClassId());
//				memberId = account.getBelongcustom();
//				//修改账号信息如果客户是作废的则保持原状态
//				memb = alterMemberService.getMemberById(memberId);
//				continue;
//			}// 接送货地址信息封装
//			else if (ad.getClassName().equals("ShuttleAddress")) {
//				changeBaseInfo = true;
//				ShuttleAddress shuttleAddress = alterMemberService.getShuttleAddress(ad
//						.getClassId());
//				memberId = shuttleAddress.getMemberId();
//				break;
//			}// 偏好地址信息封装
//			else if (ad.getClassName().equals("PreferenceAddress")) {
//				changeBaseInfo = true;
//				PreferenceAddress pa = alterMemberService.getPreferenceAddress(ad
//						.getClassId());
//				Contact contact = alterMemberService.getContact(pa.getLinkManId());
//				memberId = contact.getCustId();
//				break;
//			}else if(ad.getClassName().equals("MemberExtend")){
//				changeBaseInfo = true;
//				memberId = ad.getClassId();
//				break;
//			}
//			//既修改了客户信息，又修改了账号信息 跳出循环
//			if(changeBaseInfo&&changeAccountInfo){
//				break;
//			}
//		}
//		//只要改了客户信息（不包括账号的）并且客户还是作废的，那么工作流直接结束
//		if(changeBaseInfo&&changeAccountInfo){
//			return;
//		}
		MemberOperationLog log = createUpdateLog(isSuccess, adList);
		// 如果同意，进行日志更新操作
		if (null != log) {
			alterMemberService.updateMemberOperationLog(log);
		}
		// 执行更改操作
		this.alterMemberService.updateMemberInfo(adList, isSuccess);
		// 将会员ID修改成正常状态
		this.alterMemberService.changeMemberStatus(adList);
		// 将审批中的联系人信息解锁
		this.approvingWokflowDataService.updateByWorkflowId(workFlowId, false);
		MemberOperationLog mLog= alterMemberService.searchMemberOperationLogByWorkflowNum(workFlowId);
		//是否归属部门修改
		boolean isBelongModify=false;
		boolean isSCORPC=false;
		Member m=null;
		if(null!=mLog){
			 m=alterMemberService.getMemberById(mLog.getMember().getId());
		 isBelongModify = m.getDeptId().equals(
				ContextUtil.getCurrentUserDeptId());
		//是否潜客或者散客
		 isSCORPC=m.getCustGroup().equals(Constant.CUST_GROUP_SC_CUSTOMER)||
				m.getCustGroup().equals(Constant.CUST_GROUP_PC_CUSTOMER); 
		}
		//同意才会往下走
		if(isSuccess&&!CollectionUtils.isEmpty(adList)){
			Member mem = null;
			//是否修改行业，货量潜力，
			boolean isUpdateDev = false;
			boolean isaddAccount=false;
			for (ApproveData approveData : adList) {
				if (approveData.getClassName().equals(Member.class.getSimpleName())){
					//如果修改了客户性质，从散客改为了固定客户，则需要删除散客升级列表的数据
					if(Constant.CUST_GROUP_RC_CUSTOMER.equals(approveData
									.getNewValue())
							&& Constant.CUST_GROUP_SC_CUSTOMER.equals(approveData
									.getOldValue())) {
						if(ValidateUtil.objectIsEmpty(mem)){
							mem = alterMemberService.getMemberById(approveData.getClassId());
						}
						memberManager.deleteUpGradeCustomerData(mem.getCustNumber());
						continue;
					}
					if(approveData.getFieldName().equals("tradeId")){
						if(ValidateUtil.objectIsEmpty(mem)){
							 mem = alterMemberService.getMemberById(approveData.getClassId());
						}
						isUpdateDev = true;
						continue;
					}
					if(approveData.getFieldName().equals("custPotentialType")){
						if(ValidateUtil.objectIsEmpty(mem)){
							 mem = alterMemberService.getMemberById(approveData.getClassId());
						}
						isUpdateDev = true;
						continue;
					}
				}
				
				//如果是非归属部门
				if(isSCORPC&&!isBelongModify){
					//如果修改了散客或者潜客账号
					if (!isaddAccount&&approveData.getClassName().equals(Account.class.getSimpleName())){
						//如果是新增账号,则银行id不为空
						if(approveData.getFieldName().equals("bankId")){
							if(!ValidateUtil.objectIsEmpty(approveData.getNewValue())&&
									ValidateUtil.objectIsEmpty(approveData.getOldValue())){
								isaddAccount=true;
							}
						}
					}
				}
			}
			if(isaddAccount){
				List<User> users=null;
				if(null!=m){
				 users = userService.queryAllByDeptId(m.getDeptId(), 999, 0);
				}else{
						users=new ArrayList<User>();
				}
				for (User user : users) {
					//插入代办
					Message todoMessage = new Message();
					todoMessage.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
					todoMessage.setTaskcount(1);
					todoMessage.setUserid(Integer.parseInt(user.getId()));
					todoMessage.setDeptId(Integer.parseInt(m.getDeptId()));
					todoMessage.setIshaveinfo("客户"+m.getCustNumber()+"已经新增一个银行账号");
					messageManager.addMessage(todoMessage);
				}
			}
			if(isUpdateDev){
			 memberManager.updateExtendAndLog(mem, mem.getMemberExtend(), Constant.OPERATOR_TYPE_UPDATE);
			 //处理商机阶段
			 memberManager.saveBusinessOpportunity(mem);
			}
		}
		
	}

	private MemberOperationLog createUpdateLog(boolean isSuccess,
			List<ApproveData> adList) {
		Assert.notEmpty(adList, "adList must not null");
		MemberOperationLog log = new MemberOperationLog();
		// 会员修改记录保存
		if (isSuccess) {
			// 获得操作记录Id
			String logId = adList.get(0).getMemberOperationLogId();
			// 修改状态为生效
			log.setId(logId);
			log.setEffect(true);
			return log;
		} else {
			return null;
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息
	 * */
	@Transactional(readOnly = true)
	@Override
	public List<TodoWorkflow> searchWorkflowByCondition(
			WorkFlowCondition toDoWorkflowCondition, int start, int limit) {
		toDoWorkflowCondition
				.setOwnerDeptId(ContextUtil.getCurrentUserDeptId());
		toDoWorkflowCondition.setOwnerRoleIds(ContextUtil
				.getCurrentUserRoleIds());

		return this.alterMemberService.searchWorkflowByCondition(
				toDoWorkflowCondition, start, limit);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员基本信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员基本信息
	 * */
	@Transactional(readOnly = true)
	@Override
	public Member getMemberById(String id) {
		return this.alterMemberService.getMemberById(id);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息总数
	 * */
	@Transactional(readOnly = true)
	@Override
	public int countWorkflowByCondition(WorkFlowCondition toDoWorkflowCondition) {
		return this.alterMemberService
				.countWorkflowByCondition(toDoWorkflowCondition);
	}

	/**
	 * 
	 * <p>
	 * 根据会员Id查想会员相关银行账号信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 *            客户Id
	 * @return List<Account>
	 * @see com.deppon.crm.module.customer.server.manager.IAlterMemberManager#getAccountsByMemberId(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Account> getAccountsByMemberId(String memberId) {
		List<Account> list = null;
		if (!StringUtils.isEmpty(memberId)) {
			list = alterMemberService.getAccountsByMemberId(memberId);
		}
		return list;
	}

	/**
	 * 
	 * <p>
	 * 根据账户id查询账户信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param id
	 * @return Account
	 * @see com.deppon.crm.module.customer.server.manager.IAlterMemberManager#getAccountById(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public Account getAccountById(String id) {
		Account account = null;
		if (!StringUtils.isEmpty(id)) {
			account = alterMemberService.getAccountById(id);
		}
		return account;
	}

	@Transactional(readOnly = true)
	@Override
	public String getMemberIdByMemberNum(String memberNum) {
		MemberCondition condition = new MemberCondition();
		condition.setCustNumber(memberNum);
		List<MemberResult> result = searchMember(condition);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0).getCustId();
	}

	@Transactional(readOnly = true)
	@Override
	public String getMemberIdByMemberNumFor360(String memberNum) {
		MemberCondition condition = new MemberCondition();
		condition.setCustNumber(memberNum);
		List<MemberResult> result = searchMemberListFor360(condition);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0).getCustId();
	}

	@Transactional(readOnly = true)
	@Override
	public String getMemberIdByContactNum(String contactNum) {
		MemberCondition condition = new MemberCondition();
		condition.setLinkManNumber(contactNum);
		List<MemberResult> result = searchMember(condition);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0).getCustId();

	}

	@Transactional
	@Override
	public void addContactType(String contactId, int addContactType) {
		Contact contact = alterMemberService.getContact(contactId);
		if (null == contact) {
			return;
		}
		contact.setLinkmanType(CustomerRule.addContactType(
				contact.getLinkmanType(), addContactType));
		alterMemberService.updateContact(contact);
	}

	@Transactional(readOnly = true)
	@Override
	public String getMemberIdByContactMobile(String mobilePhone) {
		MemberCondition condition = new MemberCondition();
		condition.setMobile(mobilePhone);
		List<MemberResult> result = searchMember(condition);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0).getCustId();

	}

	@Transactional
	@Override
	public void cleanContactPreferenceAddress(String contactId) {
		alterMemberService.losePreferenceAddressByContactId(contactId);
	}

	@Transactional(readOnly = true)
	@Override
	public Contact getContact(String id) {
		return alterMemberService.getContact(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.manager.IAlterMemberManager#
	 * searchPpreferenceAddressByContactId(java.lang.String)
	 */
	@Override
	public List<PreferenceAddress> searchPreferenceAddressByContactId(
			String contactId, String addressType) {
		return alterMemberService.searchPreferAddByContactId(contactId,
				addressType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.manager.IAlterMemberManager#
	 * searchMyWorkflowByCondition
	 * (com.deppon.crm.module.customer.shared.domain.WorkFlowCondition, int,
	 * int)
	 */
	@Override
	public List<MyToDoWorkFlow> searchMyWorkflowByCondition(
			WorkFlowCondition workflowCondition, int start, int limit) {
		workflowCondition.setOwnerRoleIds(ContextUtil.getCurrentUserRoleIds());
		workflowCondition.setOwnerDeptId(ContextUtil.getCurrentUserDeptId());
		return alterMemberService.searchMyWorkflowByCondition(
				workflowCondition, start, limit);
	}

	@Override
	public List<ApproveData> searchApproveDataByLogId(String logId) {
		return alterMemberService.searchApproveDataByLogId(logId);
	}

	public IApprovingWokflowDataService getApprovingWokflowDataService() {
		return approvingWokflowDataService;
	}

	public void setApprovingWokflowDataService(
			IApprovingWokflowDataService approvingWokflowDataService) {
		this.approvingWokflowDataService = approvingWokflowDataService;
	}

	public IMemberWorkFlowManager getMemberWorkFlowManager() {
		return memberWorkFlowManager;
	}

	@SuppressWarnings("serial")
	@Override
	public Member queryAlterMemberById(String memberId) {
		try {
			Member member = alterMemberService.getMemberAllById(memberId);
			if (null==member) {
				return null;
			}
			if ("1".equals(member.getCustStatus())) {
				//审批中
				String workFlowId = this.getMemberLastWorkFlowId(memberId);
				if (!ValidateUtil.objectIsEmpty(workFlowId)) {
					throw new CustomerException(CustomerExceptionType.MemberIsExamin,
							new Object[] { workFlowId });
				}
				workFlowId = repeatedCustManager.searchWorkFlowNoByCustId(member.getCustNumber());
				if (!ValidateUtil.objectIsEmpty(workFlowId)) {
					throw new CustomerException(CustomerExceptionType.MemberIsExamin,
							new Object[] { workFlowId });
				}
				throw new CustomerException(CustomerExceptionType.custIsExamin);
			} else {
				//疑似重复列表
				String deptName = repeatedCustManager.searchMainCustDeptName(member.getCustNumber());
				if (!StringUtils.isEmpty(deptName)) {
					throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{deptName});
				}else{
					return member;
				}
			}
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	// 会员删除（作废）操作（）界面删除潜散客，作废固定客户
	@SuppressWarnings("serial")
	@Override
	public void deleteMember(Member operateMember) {
		try {
			// 进行删除操作 作废会员信息
			alterMemberService
					.deleteMember(vilidateDeleteMember(operateMember));
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 功能：会员删除（作废）操作前校验
	 * 
	 * @param operateMember
	 * @return
	 */
	private Member vilidateDeleteMember(Member operateMember) {
		String memberId = operateMember.getId();
		Integer versionNumber = operateMember.getVersionNumber();
		Member4All member4All = this.alterMemberService
				.queryMember4AllByIdNEW(memberId);
		if (null == member4All
				|| (null != versionNumber
						&& null != member4All.getVersionNumber() && !versionNumber
							.equals(member4All.getVersionNumber()))) {
			// 会员操作时数据已经改动
			throw new CustomerException(CustomerExceptionType.OperateDateChange);
		}
		member4All.setVersionNumber(operateMember.getVersionNumber());
		// 得到这个客户的全部合同
		List<Contract> contracts = member4All.getContractList();
		// 联系人变更
		ContactVary conVary = member4All.getContactVary();
		if (!ContextUtil.getCurrentUserDeptId().equals(member4All.getDeptId())) {
			// 只有归属部门才可以执行作废操作
			throw new CustomerException(
					CustomerExceptionType.OnlyOwnDeptCanInvalid);
		} else if (conVary != null && conVary.getId() != null
				&& !"".equals(conVary.getId())) {
			// 变更联系人操作进行中
			String workFlowId = String.valueOf(conVary.getWorkflowId());
			if (ValidateUtil.objectIsEmpty(workFlowId)) {
				throw new CustomerException(
						CustomerExceptionType.ConVaryDataError);
			} else {
				throw new CustomerException(
						CustomerExceptionType.ConVaryIsExamin,
						new Object[] { workFlowId });
			}
		} else if (member4All != null
				&& Constant.Status_Examine.equals(member4All.getCustStatus())) {
			// 会员审批中
			String workFlowId = this.getMemberLastWorkFlowId(memberId);
			if (!ValidateUtil.objectIsEmpty(workFlowId)) {
				throw new CustomerException(CustomerExceptionType.MemberIsExamin,
						new Object[] { workFlowId });
			}
			workFlowId = repeatedCustManager.searchWorkFlowNoByCustId(member4All.getCustNumber());
			if (!ValidateUtil.objectIsEmpty(workFlowId)) {
				throw new CustomerException(CustomerExceptionType.MemberIsExamin,
						new Object[] { workFlowId });
			}
			throw new CustomerException(CustomerExceptionType.custIsExamin);
		}	// 判断会员存在有效或审批中的合同
		else if (null != contracts && contracts.size() > 0) {
				for (Contract con : contracts) {
					if (Constant.CONTRACT_STATUS_EFFECT.equals(con
							.getContractStatus())) {
						throw new CustomerException(
								CustomerExceptionType.ContactEffect);
					} else if (Constant.CONTRACT_STATUS_INPROCESS.equals(con
							.getContractStatus())) {
						throw new CustomerException(
								CustomerExceptionType.ContactIsExamin);
					}
				}
			//判断客户是否在存在未关闭的商机状态
		}else if(isExtistUnCloseBusiness(memberId)){
			throw new CustomerException(CustomerExceptionType.isExtistsUnCloseBussiness);
		}else{//判断客户是否在疑重中
			String deptName = repeatedCustManager.searchMainCustDeptName(member4All.getCustNumber());
			if (!StringUtils.isEmpty(deptName)) {
				throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{deptName});
			}
		}
		return (Member) member4All;
	}
	//判断客户是否存在未关闭的商机
	private boolean isExtistUnCloseBusiness(String memberId) {
		return alterMemberService.isExtistUnCloseBusiness(memberId);
	}

	@Override
	public void updateMemberLastWorkFlowId(String memberId, String workFlowId) {
		boolean isUpdate = alterMemberService.updateCustWorkflow(memberId,
				workFlowId);
		if (!isUpdate) {
			alterMemberService.insertCustWorkflow(memberId, workFlowId);
		}
	}

	@Override
	public String getMemberLastWorkFlowId(String memberId) {
		return alterMemberService.getCustWorkflow(memberId);
	}

	public IContractService getContractService() {
		return contractService;
	}

	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}

	public IContactVaryService getContactVaryService() {
		return contactVaryService;
	}

	public void setContactVaryService(IContactVaryService contactVaryService) {
		this.contactVaryService = contactVaryService;
	}

	/**
	 * 
	 * @Title: alterMemberAddress
	 *         <p>
	 * @Description: 修改固定客户、潜散客地址
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return void
	 * @throws
	 */
	@Override
	public void alterCustAddress(CustAddress custAddress) {
		// 如果传过来的对象为空，则提示异常信息
		if (ValidateUtil.objectIsEmpty(custAddress)) {
			throw new MemberException(MemberExceptionType.Cust_Data_Error);
		}
		// 判断传过来的必须数据不完整，则提示异常信息
		if (StringUtils.isEmpty(custAddress.getCustId())
				|| StringUtils.isEmpty(custAddress.getAddress())
				|| StringUtils.isEmpty(custAddress.getCustType())
				|| StringUtils.isEmpty(custAddress.getCityId())) {
			throw new MemberException(MemberExceptionType.Cust_Data_Error);
		}
		Date date = new Date();
			// 否则就是固定客户，则区域ID不能为空
			if (StringUtils.isEmpty(custAddress.getAreaId())
					|| StringUtils.isEmpty(custAddress.getProvinceId())
					|| StringUtils.isEmpty(custAddress.getContactId())) {
				throw new MemberException(MemberExceptionType.Cust_Data_Error);
			}
			// 通过联系人id查询出联系人偏好地址
			List<PreferenceAddress> addressList = alterMemberService
					.queryPreferenceAddrByContactId(custAddress.getContactId());
			// 如果该客户不存在联系人偏好地址
			if (addressList == null || addressList.size() == 0) {
				// 根据固定客户id查询出对应的接送货地址列表
				List<ShuttleAddress> shuttleAddresses = alterMemberService
						.queryShuttleAddressForDetial(custAddress.getCustId());
				boolean isInsert = true;
				if (shuttleAddresses != null && shuttleAddresses.size() > 0) {
					for (ShuttleAddress s : shuttleAddresses) {
						if (s.getProvince().equals(custAddress.getProvinceId())
								&& s.getCity().equals(custAddress.getCityId())
								&& s.getArea().equals(custAddress.getAreaId())
								&& s.getAddress().equals(
										custAddress.getAddress())) {
							isInsert = false;
							
						}
					}
				}
				ShuttleAddress shuttleAddress = geneShuttleAddress(custAddress,
						date);
				if (isInsert) {
					// 则先先添加接送货地址
					alterMemberService.saveShuttleAddress(shuttleAddress);
				}
				// 然后添加偏好地址
				PreferenceAddress preferenceAddress = genePreferenceAddress(
						custAddress, date);
				preferenceAddress.setShuttleAddressId(shuttleAddress.getId());
				alterMemberService.savePreferenceAddress(preferenceAddress);

			} else {
				for (PreferenceAddress preferenceAddress : addressList) {
					// 拿到联系人主偏好信息
					if (preferenceAddress.getIsMainAddress()) {
						// 将联系人主偏好信息修改
						genePreferenceAddress(custAddress, date);
						// 修改客户接送货地址信息
						ShuttleAddress shuttleAddress = geneShuttleAddress(
								custAddress, date);
						// 根据固定客户id查询出对应的接送货地址列表
						List<ShuttleAddress> shuttleAddresses = alterMemberService
								.queryShuttleAddressForDetial(custAddress.getCustId());
						boolean isInsert = true;
						if (shuttleAddresses != null && shuttleAddresses.size() > 0) {
							for (ShuttleAddress s : shuttleAddresses) {
								if (s.getProvince().equals(custAddress.getProvinceId())
										&& s.getCity().equals(custAddress.getCityId())
										&& s.getArea().equals(custAddress.getAreaId())
										&& s.getAddress().equals(
												custAddress.getAddress())) {
									isInsert = false;
									
								}
							}
						}
						
						shuttleAddress.setId(preferenceAddress
								.getShuttleAddressId());
						if(isInsert){
						alterMemberService.modifyMemberShuttleAddress(shuttleAddress);
						}
						PreferenceAddress address = genePreferenceAddress(
								custAddress, date);
						address.setId(preferenceAddress.getId());
						// 修改客户偏好地址
						alterMemberService.modifyPreferenceAddress(address);
					}
				}
			}
		
	}

	/**
	 * @Title: genePotentialCustomer
	 *         <p>
	 * @Description: 初始化潜散客信息
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return PotentialCustomer
	 * @throws
	 */
	@SuppressWarnings("unused")
	private PotentialCustomer genePotentialCustomer(CustAddress custAddress,
			Date date) {
		PotentialCustomer pc = new PotentialCustomer();
		pc.setId(custAddress.getCustId());
		pc.setAddress(custAddress.getAddress());
		pc.setModifyDate(date);
		pc.setCity(custAddress.getCityId());
		pc.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return pc;
	}

	/**
	 * 
	 * @Title: genePreferenceAddress
	 *         <p>
	 * @Description: 初始化偏好地址信息
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return PreferenceAddress
	 * @throws
	 */
	private PreferenceAddress genePreferenceAddress(CustAddress custAddress,
			Date date) {
		PreferenceAddress preferenceAddress = new PreferenceAddress();
		preferenceAddress.setAddress(custAddress.getAddress());
		preferenceAddress.setAddressType(Constant.RECEIVE_GOODS);
		preferenceAddress.setCreateDate(date);
		preferenceAddress.setModifyDate(date);
		preferenceAddress.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		preferenceAddress.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		preferenceAddress.setIsMainAddress(true);
		preferenceAddress.setLinkManId(custAddress.getContactId());
		preferenceAddress.setStatus(Constant.Status_Normal);
		preferenceAddress.setHasStopCost(false);
		preferenceAddress.setIsSendToFloor(false);
		return preferenceAddress;
	}

	/**
	 * 
	 * @Title: geneShuttleAddress
	 *         <p>
	 * @Description: 初始化接送货地址信息
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return ShuttleAddress
	 * @throws
	 */
	private ShuttleAddress geneShuttleAddress(CustAddress custAddress, Date date) {
		ShuttleAddress shuttleAddress = new ShuttleAddress();
		shuttleAddress.setAddress(custAddress.getAddress());
		shuttleAddress.setArea(custAddress.getAreaId());
		shuttleAddress.setMemberId(custAddress.getCustId());
		shuttleAddress.setCity(custAddress.getCityId());
		shuttleAddress.setAddressType(Constant.RECEIVE_GOODS);
		shuttleAddress.setProvince(custAddress.getProvinceId());
		shuttleAddress.setCreateDate(date);
		shuttleAddress.setStatus(Constant.Status_Normal);
		shuttleAddress.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		shuttleAddress.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return shuttleAddress;
	}

	@Override
	public boolean updateCustCoordinates(CustomerLocation customerLocation) {
		boolean flag = false;
		// 验证参数
		if (validateCustCoordinatesParameters(customerLocation)) {
			throw new CustomerException(
					CustomerExceptionType.CustomerLocation_Is_Null);
		} else {
				Contact contact = new Contact();
				contact.setId(customerLocation.getLinkmanId());
				contact.setLng(customerLocation.getLng());
				contact.setLat(customerLocation.getLat());
				flag = alterMemberService
						.updateContactForCustCoordinates(contact);
		}
		return flag;
	}

	/**
	 * @Description:客户标注 验证参数<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 * @return boolean
	 */
	private boolean validateCustCoordinatesParameters(
			CustomerLocation customerLocation) {
		boolean flag = false;
		if (ValidateUtil.objectIsEmpty(customerLocation)) {
			flag = true;
		} else if (StringUtils.isEmpty(customerLocation.getCustomerId())
				|| StringUtils.isEmpty(customerLocation.getCustomerType())
				|| StringUtils.isEmpty(customerLocation.getLat())
				|| StringUtils.isEmpty(customerLocation.getLng())) {
			flag = true;
		} else if (Constant.MEMBERTYPE_IN_CUSTLABEL.equals(customerLocation
				.getCustomerType())) {
			// 固定客户 联系人ID不能为空
			if (StringUtils.isEmpty(customerLocation.getLinkmanId())) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户编码查询会员基本信息<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-2
	 * @param custNum
	 * @return MemberResult
	 */
	@Override
	public MemberResult queryBasicMemberByCustNum(String custNum) {
		List<MemberResult> memberResultList =alterMemberService.queryBasicMemberByCustNum(custNum);
		if(null!=memberResultList&&memberResultList.size()>0){
			return memberResultList.get(0);
			
		}else {
			return null;
		}
	}
	/**
	 * <p>
	 * Description:物理删除客户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param member
	 * void
	 */
	@Override
	public void physicalDeleteMember(Member member) {
		alterMemberService.physicalDeleteMember(member);
	}

	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.customer.server.manager.IAlterMemberManager#searchMemberOperationLogList(java.lang.String)
	 * @version 0.1 2014-4-1下午3:35:06
	 * @param custId
	 * @update 	2014-4-1下午3:35:06
	 */
	@Override
	public List<MemberOperationLog> searchMemberOperationLogList(String custId) {
		return alterMemberService.searchMemberOperationLogList(custId);
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
