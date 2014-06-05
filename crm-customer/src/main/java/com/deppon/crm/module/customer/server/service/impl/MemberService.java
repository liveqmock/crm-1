package com.deppon.crm.module.customer.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.deppon.crm.module.customer.server.dao.IAlterMemberDao;
import com.deppon.crm.module.customer.server.dao.IContactIntegralDao;
import com.deppon.crm.module.customer.server.dao.IMemberDao;
import com.deppon.crm.module.customer.server.dao.IMemberIntegralDao;
import com.deppon.crm.module.customer.server.manager.CustomerRule;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.BoOperationLog;
import com.deppon.crm.module.customer.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept;
import com.deppon.crm.module.customer.shared.domain.DevelopmentLog;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;

/**
 * 
 * <p>
 * Description:客户service<br />
 * </p>
 * @title MemberService.java
 * @package com.deppon.crm.module.customer.server.service.impl 
 * @author 106138
 * @version 0.1 2014年4月18日
 */
public class MemberService implements IMemberService{
	//日志
	private Logger log = Logger.getLogger(MemberService.class);
	//memberDao
	private IMemberDao memberDao ;
	//会员修改dao
	private IAlterMemberDao alterMemberDao ;
	
	//会员积分
	private IMemberIntegralDao memberIntegralDao;
	//联系人积分
	private IContactIntegralDao contactIntegralDao;
	

	/**
	 * <p>
	 * Description:memberDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public IMemberDao getMemberDao() {
		return memberDao;
	}
	/**
	 * <p>
	 * Description:memberDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public void setMemberDao(IMemberDao memberDao) {
		this.memberDao = memberDao;
	}
	/**
	 * <p>
	 * Description:alterMemberDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public IAlterMemberDao getAlterMemberDao() {
		return alterMemberDao;
	}
	/**
	 * <p>
	 * Description:alterMemberDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public void setAlterMemberDao(IAlterMemberDao alterMemberDao) {
		this.alterMemberDao = alterMemberDao;
	}
	/**
	 * <p>
	 * Description:memberIntegralDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public IMemberIntegralDao getMemberIntegralDao() {
		return memberIntegralDao;
	}
	/**
	 * <p>
	 * Description:memberIntegralDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public void setMemberIntegralDao(IMemberIntegralDao memberIntegralDao) {
		this.memberIntegralDao = memberIntegralDao;
	}
	/**
	 * <p>
	 * Description:contactIntegralDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public IContactIntegralDao getContactIntegralDao() {
		return contactIntegralDao;
	}
	/**
	 * <p>
	 * Description:contactIntegralDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 */
	public void setContactIntegralDao(IContactIntegralDao contactIntegralDao) {
		this.contactIntegralDao = contactIntegralDao;
	}
	/**
	 * 
	 * <p>
	 * Description:创建会员<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param member
	 *
	 */
	@Override
	public void createMember(Member member) {
		
		String createUser = member.getCreateUser();
		String memberId = member.getId();
		MemberExtend memberExtend = member.getMemberExtend();
		//保存会员基本信息
		memberDao.insertMemberBaseInfo(member);
		if(!StringUtils.isEmpty(member.getCustPotentialType())
				&&ValidateUtil.objectIsEmpty(memberExtend)){
				memberExtend = new MemberExtend();
				memberExtend.setVolumePotential(member.getCustPotentialType());
				member.setMemberExtend(memberExtend);
		}
		if(!ValidateUtil.objectIsEmpty(memberExtend)){
			//保存客户扩展信息
			member.getMemberExtend().setCustId(memberId);
			this.createMemberExtendInfo(member.getMemberExtend());
		}
		
		// 保存接送货地址接货
		
		List<ShuttleAddress> shuttleAddressList = member.getShuttleAddressList();
		memberDao.insertShuttleAddressList(shuttleAddressList,memberId,createUser);
		
		// 保存联系人集合
		List<Contact> contactList =  member.getContactList();
		/**
		 * @author 李盛
		 * @since 2012-9-19
		 * @修改描述：维护联系人的所属客户的客户类型
		 */
		for(Contact con : contactList){
			con.setBelongCustType(member.getCustType());
		}
		memberDao.insertContactList(contactList,memberId,createUser);

		// 保存会员基础信息
		Contact mainContact = CustomerRule.getMainContact(contactList);
		if(mainContact == null){
			log.warn("会员"+member+"找不到关联的主联系人");
			throw new MemberException(MemberExceptionType.NoMainContactFind);
		}
		// 反写主联系人id
		member.setContactId(mainContact.getId());
		//保存会员基本信息
//		memberDao.insertMemberBaseInfo(member);
		alterMemberDao.updateMember(member);
		
		// 保存银行账号信息
		List<Account> accountList = member.getAccountList();
		if(accountList !=null&&accountList.size()>0){
			for (Account account : accountList) {
				Contact contact = CustomerRule.getContact(contactList,account);
				if(contact == null){
					log.warn("财务账号"+account+"找不到关联的财务联系人");
					throw new MemberException(MemberExceptionType.NoAccountFind,new Object[]{account.getBankAccount()});
				}
				String contactId = contact.getId();
				account.setCreateUser(createUser);
				memberDao.insertAccount(account,memberId,contactId);
			}
		}
		
		for (Contact contact : contactList) {
			// 保存偏好地址
			List<PreferenceAddress> preferenceAddressList = contact.getPreferenceAddressList();
			String contactId = contact.getId();
			for (PreferenceAddress preferenceAddress : preferenceAddressList) {
				// 找到偏好地址关联的接送货地址的id
				ShuttleAddress relevanceShuttleAddress = CustomerRule.getShuttelAddress(shuttleAddressList,preferenceAddress);
				if(relevanceShuttleAddress == null){
					log.warn("偏好地址"+preferenceAddress+"找不到关联的接送货地址");
					throw new MemberException(MemberExceptionType.NoShuttleAddressFind,new Object[]{preferenceAddress.getAddress()});
				}
				String shuttelId = relevanceShuttleAddress.getId();
				preferenceAddress.setCreateUser(createUser);
				memberDao.insertPreferenceAddress(preferenceAddress,contactId,shuttelId);
			}
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:根据税务登记号查询会员信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param taxregNumber
	 * @return
	 *
	 */
	@Override
	public MemberExtend createMemberExtendInfo(MemberExtend memberExtend) {
		return memberDao.insertMemberExtendInfo(memberExtend);
	}
	@Override
	public int getMemberCountByTaxregNumber(String taxregNumber) {
		return memberDao.getMemberCountByTaxregNumber(taxregNumber);
	}
	/**
	 * 
	 * <p>
	 * Description:获得会员id系列<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @return
	 *
	 */
	@Override
	public String getMemberIdUseSeq() {
		return memberDao.getMemberIdUseSeq();
	}
	/**
	 * 
	 * <p>
	 * Description:根据id得到客户信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public Member getMemberById(String memberId) {
		return alterMemberDao.getMember(memberId);
	}
	/**
	 * 
	 * <p>
	 * Description:更新客户状态<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param status
	 * @param memberId
	 * @param modifyUser
	 *
	 */
	@Override
	public void updateMemberStauts(String status, String memberId,String modifyUser) {
		//过滤掉一部分状态，状态为2（作废）的不更新
		String notUpdateStatus = "2"; 
		memberDao.updateMemberStautsByMemberId(status,memberId,modifyUser);
		memberDao.updateContactStatusByMemberId(status,memberId,modifyUser,notUpdateStatus);
		memberDao.updateAccountStatusByMemberId(status,memberId,modifyUser,notUpdateStatus);
		memberDao.updateShuttleAddressStatusByMemberId(status, memberId, modifyUser,notUpdateStatus);
		memberDao.updatePreferenceAddressStatusByMemberId(status, memberId, modifyUser,notUpdateStatus);
	}
	/**
	 * 
	 * <p>
	 * Description:更新升级列表备注<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param upGradeCustomerId
	 * @param remark
	 * @param modifyUser
	 *
	 */
	@Override
	public void addUpGradeCustomerRemark(String upGradeCustomerId, String remark,String modifyUser) {
		memberDao.updateUpGradeCustomerById(upGradeCustomerId,remark,modifyUser);
	}
	/**
	 * 
	 * <p>
	 * Description:根据id查询散客升级列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public UpGradeCustomer getUpGradeCustomerById(String id) {
		return memberDao.getUpGradeCustomerById(id);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 *
	 */
	@Override
	public List<UpGradeCustomer> searchUpGradeCustomerList(
			UpGradeCustomerCondition condition,int start,int limit) {
		return memberDao.searchUpGradeCustomerList(condition,start,limit);
	}
	/**
	 * 
	 * <p>
	 * Description:根据条件统计散客升级条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param condition
	 * @return
	 *
	 */
	@Override
	public int getCountUpGradeCustomerByUpGradeCustomerCondition(
			UpGradeCustomerCondition condition) {
		return memberDao.getCountUpGradeCustomerByUpGradeCustomerCondition(condition);
	}
	/**
	 * 
	 * <p>
	 * Description:得到客户目标等级<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param upGrade
	 * @return
	 *
	 */
	@Override
	public List<String> getMemberTargetLevel(UpGradeCustomer upGrade) {
		String phone = upGrade.getContactPhone();
		String tel = upGrade.getContactTel();
		String contactName = upGrade.getContactName();
		
		if(ValidateUtil.objectIsEmpty(phone) && 
			(ValidateUtil.objectIsEmpty(tel)||
			ValidateUtil.objectIsEmpty(contactName))){
			throw new RuntimeException("调用参数不合法");
		}
		List<String> levelList = null;
		if(!ValidateUtil.objectIsEmpty(phone)){
			levelList = memberDao.getAllTargetLevelByPhone(phone);
		}else{
			levelList = memberDao.getAllTargetLevelByTelAndName(tel, contactName);
		}
		return levelList;
	}
	/**
	 * 
	 * <p>
	 * Description:删除散客升级列表数据<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param phone
	 *
	 */
	@Override
	public void deleteUpGradeCustomerByNumber(String phone) {
		memberDao.deleteUpGradeCustomerByNumber(phone);
	}
	/**
	 * 
	 * <p>
	 * Description:逻辑删除客户信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 *
	 */
	@Override
	public void deleteMemberById(String memberId) {
		//失效偏好地址
		losePreferenceAddressByMemberId(memberId);
		//失效接送货地址
		loseShuttleAddressByMemberId(memberId);
		//失效账号信息
		loseAccountByMemberId(memberId);
		//失效联系人积分信息
		loseContactIntegralByMemberId(memberId);
		//失效联系人信息
		loseContactByMemberId(memberId);
		//失效会员积分信息
		loseMemberIntegralByMemberId(memberId);
		//失效会员信息
		loseMemberInfo(memberId);
	}
	/**
	 * 
	 * <p>
	 * Description:失效客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 * void
	 */
	private void loseMemberInfo(String memberId) {
		memberDao.updateMemberStautsByMemberId(Constant.Status_NoEfficacy, memberId, ContextUtil.getCreateOrModifyUserId());
	}
	/**
	 * 
	 * <p>
	 * Description:失效会员积分信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 * void
	 */
	private void loseMemberIntegralByMemberId(String memberId) {
		memberIntegralDao.deleteMemberIntegralByMemberId(memberId);
		
	}
	/**
	 * 
	 * <p>
	 * Description:失效联系人<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 * void
	 */
	private void loseContactByMemberId(String memberId) {
		//过滤掉一部分状态，状态为2（作废）的不更新
		String notUpdateStatus = "2"; 
		memberDao.updateContactStatusByMemberId(Constant.Status_NoEfficacy, memberId, ContextUtil.getCreateOrModifyUserId(),notUpdateStatus);
	}
	/**
	 * 
	 * <p>
	 * Description:失效联系人积分<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 * void
	 */
	private void loseContactIntegralByMemberId(String memberId) {
		contactIntegralDao.deleteContactIntegralByMemberId(memberId);
	}
	/**
	 * 
	 * <p>
	 * Description:失效账号<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 * void
	 */
	private void loseAccountByMemberId(String memberId) {
		//过滤掉一部分状态，状态为2（作废）的不更新
		String notUpdateStatus = "2";
		memberDao.updateAccountStatusByMemberId(Constant.Status_NoEfficacy, memberId, ContextUtil.getCreateOrModifyUserId(),notUpdateStatus);
	}
	/**
	 * 
	 * <p>
	 * Description:失效接送货地址<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param memberId
	 * void
	 */
	private void loseShuttleAddressByMemberId(String memberId) {
		//过滤掉一部分状态，状态为2（作废）的不更新
		String notUpdateStatus = "2";
		memberDao.updateShuttleAddressStatusByMemberId(Constant.Status_NoEfficacy, memberId, ContextUtil.getCreateOrModifyUserId(),notUpdateStatus);
	}

	private void losePreferenceAddressByMemberId(String memberId) {
		//过滤掉一部分状态，状态为2（作废）的不更新
		String notUpdateStatus = "2";
		memberDao.updatePreferenceAddressStatusByMemberId(Constant.Status_NoEfficacy, memberId, ContextUtil.getCreateOrModifyUserId(),notUpdateStatus);
	}
	/**
	 * 
	 * <p>
	 * Description:根据客户编码更新客户等级<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param targetLevel
	 * @param memberNumber
	 * @param modifyUser
	 *
	 */
	@Override
	public void updateMemberLevelByMemberNumber(String targetLevel,
			String memberNumber,String modifyUser) {
		memberDao.updateMemberLevelByMemberNumber(targetLevel,memberNumber,modifyUser);
	}
	/**
	 * 
	 * <p>
	 * Description:查询会员升级列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 *
	 */
	@Override
	public List<MemberUpgradeList> searchMemberUpgradeList(
			UpGradeCustomerCondition condition, int start, int limit) {
		return memberDao.searchMemberUpgradeList(condition,start,limit);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @param statisticsTime
	 * @return
	 *
	 */
	@Override
	public List<MemberUpgradeList> getMemberUpgraeByStatisticsTime(
			String statisticsTime) {
		return memberDao.getMemberUpgraeByStatisticsTime(statisticsTime);
	}

	@Override
	public List<MemberDemotionList> getDemotionMemberByStatisticsTime(
			String statisticsTime) {
		return memberDao.getMemberDemotionByStatisticsTime(statisticsTime);
	}

	@Override
	public List<MemberDemotionList> searchMemberDemotionList(
			UpGradeCustomerCondition condition, int start, int limit) {
		return memberDao.searchMemberDemotionList(condition,start,limit);
	}

	@Override
	public int getCountMemberUpgradeListByCondition(
			UpGradeCustomerCondition condition) {
		return memberDao.getCountMemberUpgradeListByCondition(condition);
	}

	@Override
	public int getCountMemberDemotionListByCondition(
			UpGradeCustomerCondition condition) {
		return memberDao.getCountMemberDemotionListByCondition(condition);
	}

	@Override
	public Double getMothlyStatementBymemberId(String memberId) {
		return memberDao.getMothlyStatementBymemberId(memberId);
	}

	@Override
	public void updateMothlyStatementByMemberId(String memberId, double monthlyStatement,String modifyUser) {
		memberDao.updateMothlyStatementByMemberId(memberId,monthlyStatement,modifyUser);
	}

	@Override
	public void insertMothlyStatement(String memberId, double monthlyStatement) {
		memberDao.insertMothlyStatement(memberId,monthlyStatement);

	}

	@Override
	public int countContactByCondition(String cardTypeCon,String idCard, String custType,
			boolean isMain) {
		return memberDao.countContactByCondition(cardTypeCon,idCard,custType,isMain);
	}

	
	
	@Override
	public int countContactByLinkManNumber(String linkManNumber) {
		return memberDao.countContactByLinkManNumber(linkManNumber);
	}

	@Override
	public List<Map<String, String>> getMemberListByTaxregNumber(
			String taxregnumber) {
		return memberDao.getMemberListByTaxregNumber(taxregnumber);
	}

	@Override
	public List<Map<String, String>> getMemberListByPersonalcardNumber(
			String cardTypeCon, String idCard, String custType, boolean isMain) {
		return memberDao.getMemberListByPersonalcardNumber(cardTypeCon, idCard, custType, isMain);
	}
	@Override
	public List<Map<String, String>> getMemberListByCustnameAndContact(
			String custName, String telephone) {
		return memberDao.getMemberListByCustnameAndContact(custName,telephone);
	}
	
	@Override
	public List<Map<String, String>> getMemberListByCellphone(String telephone) {
		return memberDao.getMemberListByCellphone(telephone);
	}

	@Override
	public List<Map<String, String>> getMemberListByLinkManNumber(
			String linkManNumber) {
		return memberDao.getMemberListByLinkManNumber(linkManNumber);
	}

	@Override
	public void deleteScatterUpgradeById(String upGradeCustId) {
		memberDao.deleteScatterUpgradeById(upGradeCustId);
	}

	@Override
	public String getCustWorkFlow(String workFlow) {
		return alterMemberDao.getCustWorkFlowbyWorkFlow(workFlow);
	}

	
	/* (非 Javadoc)
	* <p>Title: queryMemberByCustNumber</p>
	* <p>Description: </p>
	* @param deptId
	* @param custNumber
	* @return
	* @see com.deppon.crm.module.customer.server.service.IMemberService#queryMemberByCustNumber(java.lang.String, java.lang.String)
	*/
	@Override
	public boolean queryMemberByCustNumber(String custNumber) {
		return memberDao.memberExistOrNot(custNumber);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#searchNeedUpdateCustomer()
	 */
	@Override
	public List<ChannelCustomer> searchNeedWorkflowUpdateCustomer() {
		return memberDao.queryNeedWorkflowCustomer();
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#deleteUpdatedWorkflowCustomer(java.util.List)
	 */
	@Override
	public void deleteUpdatedWorkflowCustomer(List<String> needDeleteIds) {
		memberDao.deleteChannelCustomerByIds(needDeleteIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#searchCustomerByUserName(java.lang.String)
	 */
	@Override
	public List<ChannelCustomer> searchChannelCustomerByUserName(String custName) {
		return memberDao.queryChannelCustomerByUserName(custName) ;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#updateChannelCustomer(com.deppon.crm.module.customer.shared.domain.ChannelCustomer)
	 */
	@Override
	public void updateChannelCustomer(ChannelCustomer cust) {
		memberDao.updateChannelCustomer(cust);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#searchMemberByUserName(java.lang.String)
	 */
	@Override
	public Member searchMemberByUserName(String custName) {
		return memberDao.queryMemberByUserName(custName);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#saveChannelCustomer(com.deppon.crm.module.customer.shared.domain.ChannelCustomer)
	 */
	@Override
	public void saveChannelCustomer(ChannelCustomer cust) {
		memberDao.createChannelCustomer(cust);
	}

	
	@Override
	public List<Member> getNotDevpPlanAndSysmakelsLoseList(Date date,
			String dept,String custType) {
		return memberDao.getNotDevpPlanAndSysmakelsLoseList(date, dept,custType);
	}

	@Override
	public List<String> getNotDevpPlanAndSysmakelsLoseDeptList(Date date,String custType) {
		return memberDao.getNotDevpPlanAndSysmakelsLoseDeptList(date,custType);
	}
	/**
	 * @Descript 得到部门中失效一年的散客
	 * @author 李学兴
	 * @version 0.1 2012-5-25
	 * @param 无
	 * @return 散客集合
	 */
	@Override
	public List<Member> getScatterCustLoselsAndOneYear(Date date,String deptId,String custType) {
		return memberDao.getScatterCustLoselsAndOneYear(date,deptId,custType);
	}

	/**
	 * @Descript 得到部门中失效一年的潜客 部门
	 * @author 李学兴
	 * @version 0.1 2012-5-25
	 * @param 无
	 * @return 散客部门集合
	 */
	@Override
	public List<String> getScatterCustDeptLoselsAndOneYear(Date date,String custType) {
		return memberDao.getScatterCustDeptLoselsAndOneYear(date,custType);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#updateMemberExtend(com.deppon.crm.module.customer.shared.domain.MemberExtend)
	 */
	@Override
	public void updateMemberExtend(MemberExtend extend) {
		memberDao.updateMemberExtend(extend);
	}
	/**
	 * 
	* @Title: searchMemberExtendByID
	* @Description:  根据客户ID查询客户扩展信息
	* @author chenaichun 
	* @param @param custId
	* @param @return    设定文件
	* @date 2014年3月27日 下午2:31:15
	* @return MemberExtend    返回类型
	* @throws
	* @update 2014年3月27日 下午2:31:15
	 */
	@Override
	public MemberExtend searchMemberExtendByID(String custId) {
		return memberDao.searchMemberExtendByID(custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#searchCustomerMarktingDeptByCustId(java.lang.String)
	 */
	@Override
	public List<CustomerMarktingDept> searchCustomerMarktingDeptByCustId(
			String custId) {
		return memberDao.queryCustomerMarktingDeptByCustId(custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#createMemberMarktingDept(com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept)
	 */
	@Override
	public void createMemberMarktingDept(CustomerMarktingDept dept) {
		dept.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		dept.setCreateDate(new Date());
		memberDao.createMemberMarktingDept(dept);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#modifyMemberMarktingDept(com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept)
	 */
	@Override
	public void modifyMemberMarktingDept(CustomerMarktingDept dept) {
		dept.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		dept.setModifyDate(new Date());
		memberDao.updateMemberMarktingDept(dept);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IMemberService#searchCustMarktingDeptByFirstShip()
	 */
	@Override
	public List<CustomerMarktingDept> searchCustMarktingDeptByFirstShip() {
		return memberDao.queryCustMarktingDeptByFirstShip();
	}
	
	/* (非 Javadoc)
	* <p>Title: updateDevelopmentLog</p>
	* <p>Description: </p>
	* @param developmentLog
	* @see com.deppon.crm.module.customer.server.service.IAlterMemberService#updateDevelopmentLog(com.deppon.crm.module.customer.shared.domain.DevelopmentLog)
	*/
	@Override
	public void updateDevelopmentLog(DevelopmentLog developmentLog) {
		memberDao.updateDevelopmentLog(developmentLog);
	}
	
	/* (非 Javadoc)
	* <p>Title: queryDevelopmentLog</p>
	* <p>Description: </p>
	* @param custId
	* @see com.deppon.crm.module.customer.server.service.IAlterMemberService#queryDevelopmentLog(java.lang.String)
	*/
	@Override
	public List<Map<String, String>> queryDevelopmentLog(String custId) {
		return memberDao.queryDevelopmentLogs(custId);
	}
	@Override
	public List<Member> searchMemberByConstactWay(String mobilePhone,
			String tel, String name) {
		
		return memberDao.searchMemberByConstactWay(mobilePhone,tel,name);
	}
	
	/* (非 Javadoc)
	* <p>Title: queryBusinessOpportunityById</p>
	* <p>Description: </p>
	* @param custId
	* @return
	* @see com.deppon.crm.module.customer.server.service.IMemberService#queryBusinessOpportunityById(java.lang.String)
	*/
	@Override
	public BusinessOpportunity queryBusinessOpportunityById(String custId) {
		return memberDao.queryBusinessOpportunityById(custId);
	}
	
	/* (非 Javadoc)
	* <p>Title: saveBusinessOpportunityStep</p>
	* <p>Description: </p>
	* @param bo
	* @see com.deppon.crm.module.customer.server.service.IMemberService#saveBusinessOpportunityStep(com.deppon.crm.module.customer.shared.domain.BusinessOpportunity)
	*/
	@Override
	public void saveBusinessOpportunityStep(BusinessOpportunity bo) {
		memberDao.saveBusinessOpportunityStep(bo);
	}
	
	/* (非 Javadoc)
	* <p>Title: addBusinessOpportunityLog</p>
	* <p>Description: </p>
	* @param logList
	* @see com.deppon.crm.module.customer.server.service.IMemberService#addBusinessOpportunityLog(java.util.List)
	*/
	@Override
	public void addBusinessOpportunityLog(List<BoOperationLog> logList) {
		memberDao.addBusinessOpportunityLog(logList);
	}
}
