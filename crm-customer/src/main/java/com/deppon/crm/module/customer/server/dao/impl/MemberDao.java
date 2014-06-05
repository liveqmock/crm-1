package com.deppon.crm.module.customer.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.CustomerTableName;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.customer.server.dao.IMemberDao;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.CustomerLogUtil;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.BoOperationLog;
import com.deppon.crm.module.customer.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
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
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @Descript 会员操作DAO层接口
 * @title PotentialCustomerDao.java
 * @package com.deppon.crm.module.customer.server.dao
 * @author 罗典
 * @version 0.1 2012-3-19
 */
public class MemberDao extends iBatis3DaoImpl implements IMemberDao {

	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.Member.";
	private static final String NAME_SPACE_ALTER = "com.deppon.crm.module.customer.shared.domain.AlterMember.";

	//插入开发阶段日志
	private final String INSERT_DEVELOPMENT_LOG = "insertDevelopmentLog";
	//查询开发阶段日志
	private final String QUERY_DEVELOPMENT_LOG = "queryDevelopmentLog";
	//查询客户商机
	private final String QUERY_BY_CUSTID = "queryBusinessOpportunityByCustId";
	//修改商机阶段
	private static final String UPDATE_BY_ID = "updateBusinessOpportunityById";
	//保存商机阶段操作日志
	private static final String ADD_OPERATION_LOG = "insertOperationLog";
	
	
	public void insertAccount(Account account, String memberId, String contactId) {
		account.setFinanceLinkmanId(contactId);
		account.setBelongcustom(memberId);
		this.saveAccount(account);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.I,account.getId());
		writeCustomerLog(list);
		
	}

	@Override
	public void insertShuttleAddressList(
			List<ShuttleAddress> shuttleAddressList, String memberId,String createUser) {
		if(shuttleAddressList!=null&&shuttleAddressList.size()>0){
			for (ShuttleAddress shuttleAddress : shuttleAddressList) {
				shuttleAddress.setCreateUser(createUser);
				insertShuttleAddress(shuttleAddress, memberId);
			}
		}
	}

	protected void insertShuttleAddress(ShuttleAddress shuttleAddress,
			String memberId) {
		shuttleAddress.setMemberId(memberId);
		this.saveShuttleAddress(shuttleAddress);
	}

	@Override
	public void insertContactList(List<Contact> contactList, String memberId,String createUser) {
		for (Contact contact : contactList) {
			contact.setCreateUser(createUser);
			insertContact(contact, memberId);
		}
	}

	protected void insertContact(Contact contact, String memberId) {
		contact.setCustId(memberId);
		this.saveContact(contact);
	}

	@Override
	public void insertPreferenceAddress(PreferenceAddress preferenceAddress,
			String contactId, String shuttelId) {
		preferenceAddress.setLinkManId(contactId);
		preferenceAddress.setShuttleAddressId(shuttelId);
		this.savePreferenceAddress(preferenceAddress);
	}

	@Override
	public void insertMemberBaseInfo(Member member) {
		
		this.getSqlSession()
				.insert(NAME_SPACE + "insertMemberBaseInfo", member);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.I,member.getId());
		writeCustomerLog(list);
		
		MemberIntegral memberIntegral = new MemberIntegral();
		memberIntegral.setMember(member);

		this.getSqlSession()
		.insert("com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral.insertMemberIntegral", memberIntegral);
		

		
		
	}

	@Override
	public int getMemberCountByTaxregNumber(String taxregNumber) {
		return (Integer) this.getSqlSession().selectOne(
				NAME_SPACE + "getMemberCountByTaxregNumber", taxregNumber);
	}

	@Override
	public String getMemberIdUseSeq() {
		return (String) getSqlSession().selectOne(
				NAME_SPACE + "getMemberIdUseSeq");
	}

	@Override
	public void updateUpGradeCustomerById(String upGradeCustomerId,
			String remark,String modifyUser) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", upGradeCustomerId);
		map.put("remark", remark);
		map.put("modifyUser", modifyUser);
		getSqlSession().update(NAME_SPACE + "updateUpGradeCustomerById", map);
	}

	@Override
	public void deleteScatterUpgradeById(String upGradeCustId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("upGradeCustId", upGradeCustId);
		getSqlSession().update(NAME_SPACE + "deleteScatterUpgradeById", map);
	}
	
	@Override
	public UpGradeCustomer getUpGradeCustomerById(String id) {
		return (UpGradeCustomer) getSqlSession().selectOne(
				NAME_SPACE + "getUpGradeCustomerById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UpGradeCustomer> searchUpGradeCustomerList(
			UpGradeCustomerCondition condition, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		condition.setCustName(SqlUtil.getLikeField(condition.getCustName()));
		condition.setContactName(SqlUtil.getLikeField(condition.getContactName()));
		return getSqlSession().selectList(
				NAME_SPACE + "searchUpGradeCustomerList", condition, rowBounds);
	}

	@Override
	public int getCountUpGradeCustomerByUpGradeCustomerCondition(
			UpGradeCustomerCondition condition) {
		return (Integer) getSqlSession().selectOne(
				NAME_SPACE
						+ "getCountUpGradeCustomerByUpGradeCustomerCondition",
				condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllTargetLevelByPhone(String phone) {
		return getSqlSession().selectList(
				NAME_SPACE + "getAllTargetLevelByPhone", phone);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllTargetLevelByTelAndName(String tel,
			String contactName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tel", tel);
		map.put("contactName", contactName);
		return getSqlSession().selectList(
				NAME_SPACE + "getAllTargetLevelByTelAndName", map);

	}

	@Override
	public void deleteUpGradeCustomerByNumber(String phone) {
		getSqlSession().delete(NAME_SPACE + "deleteUpGradeCustomerByNumber",
				phone);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAccountByMemberId(String memberId) {
		getSqlSession()
				.delete(NAME_SPACE + "deleteAccountByMemberId", memberId);
		
		List<String> idList = this.getSqlSession().selectList(NAME_SPACE+"getAccountIdByMemberId",memberId);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.D,idList);
		writeCustomerLog(list);
		
	}

	@Override
	public void deleteMemberInfoById(String memberId) {
		getSqlSession().delete(NAME_SPACE + "deleteMemberInfoById", memberId);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.D,memberId);
		writeCustomerLog(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteShuttleAddressByMemberId(String memberId) {
		getSqlSession().delete(NAME_SPACE + "deleteShuttleAddressByMemberId",
				memberId);
		
		List<String> idList = this.getSqlSession().selectList(NAME_SPACE+"getShuttleAddressIdByMemberId",memberId);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_SHUTTLEADDRESS,OperationFlg.D,idList);
		writeCustomerLog(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deletePreferenceAddressByMemberId(String memberId) {
		getSqlSession().delete(
				NAME_SPACE + "deletePreferenceAddressByMemberId", memberId);
		
		List<String> idList = this.getSqlSession().selectList(NAME_SPACE+"getPreferenceAddressIdByMemberId",memberId);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_PREFERENCEADDRESS,OperationFlg.D,idList);
		writeCustomerLog(list);
		
	}

	@Override
	public void updateMemberStautsByMemberId(String status, String memberId,String modifyUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("memberId", memberId);
		map.put("modifyUser", modifyUser);
		getSqlSession()
				.update(NAME_SPACE + "updateMemberStautsByMemberId", map);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.U,memberId);
		writeCustomerLog(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateContactStatusByMemberId(String status, String memberId,String modifyUser,String notUpdateStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("memberId", memberId);
		map.put("modifyUser", modifyUser);
		map.put("notUpdateStatus", notUpdateStatus);
		getSqlSession().update(NAME_SPACE + "updateContactStatusByMemberId",
				map);
		
		List<String> idList = this.getSqlSession().selectList("com.deppon.crm.module.customer.shared.domain.Contact.getContactIdByMemberId",memberId);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,OperationFlg.U,idList);
		writeCustomerLog(list);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateAccountStatusByMemberId(String status, String memberId,String modifyUser,String notUpdateStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("memberId", memberId);
		map.put("modifyUser", modifyUser);
		map.put("notUpdateStatus", notUpdateStatus);
		getSqlSession().update(NAME_SPACE + "updateAccountStatusByMemberId",
				map);
		
		List<String> idList = this.getSqlSession().selectList(NAME_SPACE+"getAccountIdByMemberId",memberId);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.U,idList);
		writeCustomerLog(list);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存联系人信息
	 * @参数：contact 联系人信息
	 * @返回值：无
	 * */
	public void saveContact(Contact contact) {
		contact.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		this.getSqlSession().insert(NAME_SPACE + "insertContact", contact);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,OperationFlg.I,contact.getId());
		writeCustomerLog(list);
		
		
		ContactIntegral contactIntegral = new ContactIntegral();
		contactIntegral.setContact(contact);
		this.getSqlSession().insert("com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral.insertContactIntegral", contactIntegral);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存接送货地址信息
	 * @参数：shuttleAddress 接送货地址信息
	 * @返回值：无
	 * */
	public void saveShuttleAddress(ShuttleAddress shuttleAddress) {
		shuttleAddress.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		this.getSqlSession().insert(NAME_SPACE + "insertShuttleAddress",
				shuttleAddress);
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_SHUTTLEADDRESS,OperationFlg.I,shuttleAddress.getId());
		writeCustomerLog(list);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存偏好地址信息
	 * @参数：preferenceAddress 偏好地址信息
	 * @返回值：无
	 * */
	public void savePreferenceAddress(PreferenceAddress preferenceAddress) {
		preferenceAddress.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		this.getSqlSession().insert(NAME_SPACE + "insertPreferenceAddress",
				preferenceAddress);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_PREFERENCEADDRESS,OperationFlg.I,preferenceAddress.getId());
		writeCustomerLog(list);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存账号信息
	 * @参数：account 账号信息
	 * @返回值：无
	 * */
	public void saveAccount(Account account) {
		account.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		this.getSqlSession().insert(NAME_SPACE + "insertAccount", account);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.I,account.getId());
		writeCustomerLog(list);
	}

	@Override
	public void updateMemberLevelByMemberNumber(String targetLevel,
			String memberNumber,String modifyUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("targetLevel",targetLevel);
		map.put("memberNumber",memberNumber);
		map.put("modifyUser", modifyUser);
		this.getSqlSession().update(NAME_SPACE + "updateMemberLevelByMemberNumber",map);
		
		String memberId = (String) this.getSqlSession().selectOne(NAME_SPACE +"getMemberIdByMemberNumber",memberNumber);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.I,memberId);
		writeCustomerLog(list);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MemberUpgradeList> getMemberUpgraeByStatisticsTime(
			String statisticsTime) {
		return this.getSqlSession().selectList(NAME_SPACE+"getMemberUpgraeByStatisticsTime",statisticsTime);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberDemotionList> getMemberDemotionByStatisticsTime(
			String statisticsTime) {
		return this.getSqlSession().selectList(NAME_SPACE+"getMemberDemotionByStatisticsTime",statisticsTime);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberUpgradeList> searchMemberUpgradeList(
			UpGradeCustomerCondition condition, int start, int limit) {
		Assert.notNull(condition,"condition must not null");
		
		condition.setCustName(SqlUtil.getLikeField(condition.getCustName()));
		condition.setContactName(SqlUtil.getLikeField(condition.getContactName()));
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("lifttype", 1);
		RowBounds rowBounds = new RowBounds(start, limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchMemberList",map,rowBounds);
	}

	@Override
	public int getCountMemberUpgradeListByCondition(
			UpGradeCustomerCondition condition) {
		Assert.notNull(condition,"condition must not null");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("lifttype", 1);
		return (Integer) this.getSqlSession().selectOne(NAME_SPACE+"getCountMemberListByCondition",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberDemotionList> searchMemberDemotionList(
			UpGradeCustomerCondition condition, int start, int limit) {
		Assert.notNull(condition,"condition must not null");
		Map<String,Object> map = new HashMap<String, Object>();
		if(condition.getContactName()!=null){
			condition.setCustName("%"+condition.getCustName()+"%");
		}
		if (condition.getContactName()!=null) {
			condition.setContactName("%"+condition.getContactName()+"%");
		}
		map.put("condition", condition);
		map.put("lifttype", 2);
		RowBounds rowBounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchMemberList",map,rowBounds);
	}
	
	@Override
	public int getCountMemberDemotionListByCondition(
			UpGradeCustomerCondition condition) {
		Assert.notNull(condition,"condition must not null");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("lifttype", 2);
		return (Integer) this.getSqlSession().selectOne(NAME_SPACE+"getCountMemberListByCondition",map);
	}

	@Override
	public Double getMothlyStatementBymemberId(String memberId) {
		return (Double) this.getSqlSession().selectOne(NAME_SPACE+"getMothlyStatementBymemberId",memberId);
	}

	@Override
	public void updateMothlyStatementByMemberId(String memberId,
			double monthlyStatement,String modifyUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("monthlyStatement", monthlyStatement);
		map.put("modifyUser", modifyUser);
		this.getSqlSession().update(NAME_SPACE+"updateMothlyStatementByMemberId",map);
	}

	@Override
	public void insertMothlyStatement(String memberId, double monthlyStatement) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("monthlyStatement", monthlyStatement);
		this.getSqlSession().insert(NAME_SPACE+"insertMothlyStatement",map);
	}

	@Override
	public int countContactByCondition(String cardTypeCon,String idCard, String custType,
			boolean isMain) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idCard", idCard);
		map.put("custType", custType);
		map.put("isMain", isMain);
		map.put("cardTypeCon", cardTypeCon);
		return (Integer) this.getSqlSession().selectOne(NAME_SPACE+"countContactByCondition",map);
	}
	
	
	
	private void writeCustomerLog(List<CustTransationOperation> list){
		CustomerLogUtil.writeCustomerLog(list);
	}

	@Override
	public int countContactByLinkManNumber(String linkManNumber) {
		return (Integer) this.getSqlSession().selectOne(NAME_SPACE+"countContactByLinkManNumber",linkManNumber);
	}

	@Override
	public void updateShuttleAddressStatusByMemberId(String status,
			String memberId, String modifyUser,String notUpdateStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("memberId", memberId);
		map.put("modifyUser", modifyUser);
		map.put("notUpdateStatus", notUpdateStatus);
		getSqlSession().update(NAME_SPACE + "updateShuttleAddressStatusByMemberId", map);
	}

	@Override
	public void updatePreferenceAddressStatusByMemberId(
			String status, String memberId,
			String modifyUser,String notUpdateStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("memberId", memberId);
		map.put("modifyUser", modifyUser);
		map.put("notUpdateStatus", notUpdateStatus);
		getSqlSession().update(NAME_SPACE + "updatePreferenceAddressStatusByMemberId", map);
	}

	@Override
	public int countApproveDataByCondition(ApproveData date) {
		return (Integer) getSqlSession().selectOne(NAME_SPACE+"countApproveDataByCondition",date);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getMemberListByTaxregNumber(
			String taxregnumber) {
		return this.getSqlSession().selectList(NAME_SPACE+"getMemberListByTaxregNumber",taxregnumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getMemberListByPersonalcardNumber(
			String cardTypeCon, String idCard, String custType, boolean isMain) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idCard", idCard);
		map.put("custType", custType);
		map.put("isMain", isMain);
		map.put("cardTypeCon", cardTypeCon);
	    return this.getSqlSession().selectList(NAME_SPACE+"getMemberListByPersonalcardNumber",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getMemberListByCustnameAndContact(
			String custName, String telephone) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("custName", custName);
		map.put("telephone", telephone);
		return this.getSqlSession().selectList(NAME_SPACE+"getMemberListByCustnameAndContact",map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getMemberListByLinkManNumber(
			String linkManNumber) {
		List<Map<String, String>> maps = this.getSqlSession().selectList(NAME_SPACE+"getMemberListByLinkManNumber",linkManNumber);
		if(maps.size() > 0 && maps.get(0) != null){			
			return maps;
		}else{
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getMemberListByCellphone(String telephone) {
		List<Map<String, String>> maps = this.getSqlSession().selectList(NAME_SPACE+"getMemberListByCellphone",telephone);
		if(maps.size() >0 && maps.get(0) != null){			
			return maps;
		}else{
			return null;
		}
	
	}

	
	/* (非 Javadoc)
	* <p>Title: memberExistOrNot</p>
	* <p>Description: </p>
	* @param custNumber
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IMemberDao#memberExistOrNot(java.lang.String)
	*/
	@Override
	public boolean memberExistOrNot(String custNumber) {
		int count = (Integer) getSqlSession().selectOne(NAME_SPACE+"countMemberByCustNumber", custNumber);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#createChannelCustomer(com.deppon.crm.module.customer.shared.domain.ChannelCustomer)
	 */
	@Override
	public void createChannelCustomer(ChannelCustomer cust) {
		getSqlSession().insert(NAME_SPACE+"insertCreateChannelCustomer", cust);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#MemberByUserName(java.lang.String)
	 */
	@Override
	public Member queryMemberByUserName(String custName) {
		return (Member) getSqlSession().selectList(NAME_SPACE_ALTER+"queryMemberByUserName", custName).get(0);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#updateChannelCustomer(com.deppon.crm.module.customer.shared.domain.ChannelCustomer)
	 */
	@Override
	public void updateChannelCustomer(ChannelCustomer cust) {
		getSqlSession().insert(NAME_SPACE+"updateChannelCustomer", cust);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#queryChannelCustomerByUserName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelCustomer> queryChannelCustomerByUserName(String custName) {
		return getSqlSession().selectList(NAME_SPACE+"queryChannelCustomerByUserName", custName);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#queryNeedWorkflowCustomer()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelCustomer> queryNeedWorkflowCustomer() {
		return getSqlSession().selectList(NAME_SPACE+"queryNeedWorkflowCustomer");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#deleteChannelCustomerByIds(java.util.List)
	 */
	@Override
	public void deleteChannelCustomerByIds(List<String> needDeleteIds) {
		getSqlSession().delete(NAME_SPACE+"deleteChannelCustomerByIds", needDeleteIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#insertMemberExtendInfo(com.deppon.crm.module.customer.shared.domain.Member)
	 */
	@Override
	public MemberExtend insertMemberExtendInfo(MemberExtend extend) {
		int i = getSqlSession().insert(NAME_SPACE+"insertMemberExtendInfo", extend);
		return i==1?extend:null;
	}
	/**
	 * 获得一年没有开发的潜散客列表
	 */
	@SuppressWarnings("unchecked")
	public List<Member> getNotDevpPlanAndSysmakelsLoseList(Date date,String dept,String custType) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date",date);
		map.put("deptId", dept);
		map.put("custType", custType);
		return (List<Member>)this.getSqlSession()
				.selectList(NAME_SPACE_ALTER + "getScatterCustomerLoseYear",map);
	}
	
	/**
	 * <p>
	 * Description:得到一年没开发过的散客部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-19
	 * @param date
	 * @param deptId
	 * @return
	 * List<ScatterCustomer>
	 */
	@SuppressWarnings("unchecked")
	public List<String> getNotDevpPlanAndSysmakelsLoseDeptList(Date date,String custType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date",date);
		map.put("custType", custType);
		return this.getSqlSession()
				.selectList(NAME_SPACE + "getScatterCustomerDeptLoseYear",map);
	}
	/**
	 * <p>
	 * Description:得到部门中失效一年的散客<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-19
	 * @param date
	 * @param deptId
	 * @return
	 * List<ScatterCustomer>
	 */
	@SuppressWarnings("unchecked")
	public List<Member> getScatterCustLoselsAndOneYear(Date date,
			String deptId,String custType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date",date);
		map.put("deptId", deptId);
		map.put("custType", custType);
		return this.getSqlSession()
				.selectList(NAME_SPACE_ALTER + "queryPSCustLoseYear",map);
	}

	/**
	 * <p>
	 * Description:得到部门中失效一年的潜客 部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-19
	 * @param date
	 * @return
	 * List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> getScatterCustDeptLoselsAndOneYear(Date date,String custType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date",date);
		map.put("custType",custType);
		return this.getSqlSession()
				.selectList(NAME_SPACE + "queryPSCustDeptLoseYear",map);
	}
	

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#updateMemberExtend(com.deppon.crm.module.customer.shared.domain.MemberExtend)
	 */
	@Override
	public void updateMemberExtend(MemberExtend extend) {
		getSqlSession().update(NAME_SPACE+"updateMemberExtendInfo", extend);
	}
	/**
	 * (非 Javadoc)
	* <p>Title: searchMemberExtendByID</p>
	* <p>Description: 根据客户ID查询客户扩展信息
	* @author chenaichun 
	* @param custId
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IMemberDao#searchMemberExtendByID(java.lang.String)
	 */
	@Override
	public MemberExtend searchMemberExtendByID(String custId) {
		return (MemberExtend) this.getSqlSession().selectOne(
				NAME_SPACE_ALTER + "queryMemberExtendByCustId", custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#queryCustomerMarktingDeptByCustId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerMarktingDept> queryCustomerMarktingDeptByCustId(
			String custId) {
		return getSqlSession().selectList(NAME_SPACE+"queryCustomerMarktingDeptByCustId", custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#createMemberMarktingDept(com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept)
	 */
	@Override
	public void createMemberMarktingDept(CustomerMarktingDept dept) {
		getSqlSession().insert(NAME_SPACE+"createMemberMarktingDept", dept);
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.U,dept.getCustId());
		writeCustomerLog(list);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#updateMemberMarktingDept(com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept)
	 */
	@Override
	public void updateMemberMarktingDept(CustomerMarktingDept dept) {
		getSqlSession().insert(NAME_SPACE+"updateMemberMarktingDept", dept);
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.U,dept.getCustId());
		writeCustomerLog(list);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IMemberDao#queryCustMarktingDeptByFirstShip()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerMarktingDept> queryCustMarktingDeptByFirstShip() {
		return getSqlSession().selectList(NAME_SPACE+"queryCustMarktingDeptByFirstShip");
	}
	
	/* (非 Javadoc)
	* <p>Title: updateDevelopmentLog</p>
	* <p>Description: </p>
	* @param developmentLog
	* @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#updateDevelopmentLog(com.deppon.crm.module.customer.shared.domain.DevelopmentLog)
	*/
	@Override
	public void updateDevelopmentLog(DevelopmentLog developmentLog) {
		getSqlSession().insert(NAME_SPACE + INSERT_DEVELOPMENT_LOG,developmentLog);
	}

	
	/* (非 Javadoc)
	* <p>Title: queryDevelopmentLogs</p>
	* <p>Description: </p>
	* @param custId
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#queryDevelopmentLogs(java.lang.String)
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> queryDevelopmentLogs(String custId) {
		return getSqlSession().selectList(NAME_SPACE + QUERY_DEVELOPMENT_LOG, custId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> searchMemberByConstactWay(String mobilePhone,
			String tel, String name) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mobilePhone",mobilePhone);
		map.put("telPhone", tel);
		map.put("name", name);
		return (List<Member>)this.getSqlSession()
				.selectList(NAME_SPACE_ALTER + "queryMemberByContactWay",map);
	}
	
	/* (非 Javadoc)
	* <p>Title: queryBusinessOpportunityById</p>
	* <p>Description: </p>
	* @param id
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IMemberDao#queryBusinessOpportunityById(java.lang.String)
	*/
	@Override
	public BusinessOpportunity queryBusinessOpportunityById(String custId) {
		return (BusinessOpportunity) getSqlSession().selectOne(NAME_SPACE+QUERY_BY_CUSTID, custId);
	}

	
	/* (非 Javadoc)
	* <p>Title: saveBusinessOpportunityStep</p>
	* <p>Description: </p>
	* @param bo
	* @see com.deppon.crm.module.customer.server.dao.IMemberDao#saveBusinessOpportunityStep(com.deppon.crm.module.customer.shared.domain.BusinessOpportunity)
	*/
	@Override
	public void saveBusinessOpportunityStep(BusinessOpportunity bo) {
		getSqlSession().insert(NAME_SPACE+UPDATE_BY_ID,bo);
	}

	
	/* (非 Javadoc)
	* <p>Title: addBusinessOpportunityLog</p>
	* <p>Description: </p>
	* @param logList
	* @see com.deppon.crm.module.customer.server.dao.IMemberDao#addBusinessOpportunityLog(java.util.List)
	*/
	@Override
	public void addBusinessOpportunityLog(List<BoOperationLog> logList) {
		getSqlSession().insert(NAME_SPACE+ADD_OPERATION_LOG,logList);
	}
}
