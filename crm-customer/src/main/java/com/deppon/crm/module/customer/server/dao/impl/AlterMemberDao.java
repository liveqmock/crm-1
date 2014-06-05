package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.CustomerTableName;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.server.dao.IAlterMemberDao;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.CustomerLogUtil;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.DevelopmentLog;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Member4All;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MyToDoWorkFlow;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @作者：罗典
 * @时间：2012-3-23
 * @描述：修改会员DAO层
 * */
@SuppressWarnings("unchecked")
public class AlterMemberDao extends iBatis3DaoImpl implements IAlterMemberDao {
	// 修改会员功能模块命名空间
	private static final String ALTERMEMBER_NAMESPACE = "com.deppon.crm.module.customer.shared.domain.AlterMember.";
	// 保存审批数据
	private static final String INSERT_APPROVEDATA = "insertApproveData";
	// 根据工作流ID查询审批数据
	private static final String SEARCH_APPROVEDATA_ID = "searchADByWorkflowId";
	// 根据类名，字段名获取审批类型
	private static final String GETTYPE_FIELD = "queryTypeByField";
	// 根据ID修改账号信息
	private static final String UPDATE_ACCOUNT_ID = "updateAccountById";
	// 根据ID修改会员信息
	private static final String UPDATE_MEMBER_ID = "updateMemberById";
	// 根据ID修改接送货地址
	private static final String UPDATE_SHUTTLEADDRESS_ID = "updateShuttleAddressById";
	// 根据ID修改联系人信息
	private static final String UPDATE_CONTACT_ID = "updateContactById";
	// 根据ID修改偏好地址信息
	private static final String UPDATE_PREFERADDRESS_ID = "updatePreferenceAddressById";
	// 修改会员状态
	private static final String UPDATE_MEMBERSTATUS = "updateMemberStatus";
	// 根据条件查询会员信息
	private static final String QUERY_MEMBER_CONDITION = "queryMemberByCondition";
	// 根据条件查询会员信息总数
	private static final String QUERY_MEMBER_CONDITION_COUNT = "countMemberByCondition";
	// 根据ID查询会员基本信息
	private static final String QUERY_MEMBER_ID = "queryMemberById";
	// 根据ID查询会员所有信息
	private static final String QUERY_MEMBER_ALL_ID = "queryAllMemberById";
	// 根据ID查询账号信息
	private static final String QUERY_ACCOUNT_ID = "queryAccountById";
	// 根据ID查询联系人信息
	private static final String QUERY_CONTACT_ID = "queryContactById";
	// 根据会员ID查询联系人信息
	private static final String QUERY_CONTACT_MEMBERID = "queryContactByMemberId";
	// 根据ID查询接送货地址信息
	private static final String QUERY_SHUTTLEADDRESS_ID = "queryShuttleAddressById";
	// 根据ID查询接送货地址信息
	private static final String QUERY_SHUTTLEADDRESS_MEMBERID = "queryShuttleAddressByMemberId";
	// 根据ID查询接偏好地址信息
	private static final String QUERY_PREFERENCEADDRESS_ID = "queryPreferenceAddressById";
	// 根据ID修改联系人状态
	private static final String UPDATE_CONTACT_STATUS = "updateContactStatus";
	// 根据ID修改账号信息状态
	private static final String UPDATE_ACCOUNT_STATUS = "updateAccountStatus";
	// 根据ID修改联系人偏好地址状态
	private static final String UPDATE_PREFERENCEADDRESS_STATUS = "updatePreferenceAddressStatus";
	// 根据ID修改接送货地址状态
	private static final String UPDATE_SHUTTLEADDRESS_STATUS = "updateShuttleAddressStatus";
	// 根据工作流条件查询工作流信息
	private static final String QUERY_WORKFLOW_CONDITION = "queryWorkflowByCondition";
	// 根据工作流条件查询工作流信息
	private static final String QUERY_COUNTWORKFLOW_CONDITION = "countWorkflowByCondition";
	// 根据联系人编码查询会员信息
	private static final String QUERY_MEMBER_LINKMANID = "getMemberByLinkmanId";
	// 查询会员是否为临担的月结合同客户
	private static final String QUERY_MEMBER_ISMETHODCUST = "queryMemberIsMethodCust";
	// 查询会员是否为快递的月结合同客户
	private static final String QUERY_MEMBER_ISEXPPAYWAYCUST = "queryMemberIsExpPayWayCust";
	//根据联系人id查询联系人详细信息
	private static final String QUERY_CONTACT_DETAILINFO = "getContactDetailInfoById";
	//根据客户Id查询客户账号信息
	private static final String QUERY_ACCOUNTBYMEMBERID="getAccountsByMemberId";
	// 根据联系人编码查询联系人信息
	private static final String QUERY_CONTACT_NUMBER = "queryContactNoMemberByNumber";
	//根据客户ID查询客户所有相关信息（包括客户作废状态）
	private static final String QUERY_MEMBER_ALL_ID_NEW = "queryAllMemberByIdNEW";
	//根据联系人Id查询联系人偏好地址信息
	private final String  QUERY_PreferenceAddress_By_ContactId="queryPreferenceAddressByContactId";
	
	/**
	 * @作者：罗典
	 * @时间：2012-10-11
	 * @描述：根据联系人编码查询联系人信息
	 * @参数：number 联系人编码
	 * @返回值：Contact 联系人信息
	 * */
	@Override
	public Contact getContactByNum(String number){
		return (Contact)this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE+QUERY_CONTACT_NUMBER,number);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：保存审批数据信息
	 * @参数：approveDataList 审批数据集合
	 * @返回值：boolean 是否保存成功
	 * */
	@Override
	public void saveApproveData(List<ApproveData> approveDataList) {
		for (ApproveData approveData : approveDataList) {
			this.getSqlSession().insert(
					ALTERMEMBER_NAMESPACE + INSERT_APPROVEDATA, approveData);
		}

	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据类名和字段名查询审批类型
	 * @参数：className:类名 fieldName:字段名
	 * @返回值：审批类型
	 * */
	@Override
	public String getApproveType(String className, String fieldName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("className", className);
		map.put("fieldName", fieldName);
		return (String) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + GETTYPE_FIELD, map);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：工作流号查询审批数据
	 * @参数：workFlowId 工作流ID
	 * @返回值：审批类型数据集合
	 * */
	@Override
	public List<ApproveData> searchApproveData(String workFlowId) {
		return (List<ApproveData>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + SEARCH_APPROVEDATA_ID, workFlowId);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改会员基本信息
	 * @参数：member 会员基本信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateMember(Member member) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.U,member.getId());
		writeCustomerLog(list);
		
		member.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_MEMBER_ID, member) > 0 ? true
				: false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人信息
	 * @参数 contact 联系人信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateContact(Contact contact) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,OperationFlg.U,contact.getId());
		writeCustomerLog(list);
		
		contact.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_CONTACT_ID, contact) > 0 ? true
				: false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改账号信息
	 * @参数 account 账号信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateAccount(Account account) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.U,account.getId());
		writeCustomerLog(list);
		
		account.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_ACCOUNT_ID, account) > 0 ? true
				: false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改偏好地址信息
	 * @参数 preferenceAddress 偏好地址信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updatePreferenceAddress(PreferenceAddress preferenceAddress) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_PREFERENCEADDRESS,OperationFlg.U,preferenceAddress.getId());
		writeCustomerLog(list);
		
		preferenceAddress.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_PREFERADDRESS_ID,
				preferenceAddress) > 0 ? true : false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改接送货地址信息
	 * @参数 shuttleAddress 接送货地址信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateShuttleAddress(ShuttleAddress shuttleAddress) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_SHUTTLEADDRESS,OperationFlg.U,shuttleAddress.getId());
		writeCustomerLog(list);
		
		shuttleAddress.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_SHUTTLEADDRESS_ID,
				shuttleAddress) > 0 ? true : false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据会员ID修改会员状态
	 * @参数 id 会员ID,status 会员状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Override
	public boolean alterMemberStatus(String id, String status) {
		return alterMemberStatus(id,status,-1);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public boolean alterMemberStatus(String id, String status,Integer versionNumber ) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.U,id);
		writeCustomerLog(list);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("status", status);
		map.put("modifyUser", ContextUtil.getCreateOrModifyUserId());
		if (versionNumber != null && -1 != versionNumber){map.put("versionNumber", String.valueOf(versionNumber));}
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_MEMBERSTATUS, map) > 0 ? true
				: false;
	}
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：查询会员信息
	 * @参数 无
	 * @返回值 会员集合
	 * */
	@Override
	public List<MemberResult> searchMember(MemberCondition condition) {
		condition.setCustName(SqlUtil.getLikeField(condition.getCustName()));
		condition.setLinkManName(SqlUtil.getLikeField(condition.getLinkManName()));
		
		// limit为-1 时不分页
		if (condition.getLimit() != -1) {
			return (List<MemberResult>) this.getSqlSession().selectList(
					ALTERMEMBER_NAMESPACE + QUERY_MEMBER_CONDITION, condition);
		}
		return (List<MemberResult>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + "queryMemberByConditionNopage", condition);
	}
	/**
	 * @作者：潘光均
	 * @时间：2014-5-6
	 * @功能描述：查询会员信息
	 * @参数 无
	 * @返回值 会员集合
	 * */
	@Override
	public List<MemberResult> queryArriveScatterMember(MemberCondition condition) {
		return (List<MemberResult>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + "queryArriveScatterMemberByCondition", condition);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public List<MemberResult> searchMemberListFor360(MemberCondition condition) {
		condition.setCustName(SqlUtil.getLikeField(condition.getCustName()));
		condition.setLinkManName(SqlUtil.getLikeField(condition.getLinkManName()));
		return (List<MemberResult>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + "queryMemberByConditionNopageFor360", condition);
	}
	/**
	 * 
	 * @description 查询数据权限会员信息.  
	 * @author 潘光均
	 * @version 0.1 2012-9-7
	 * @param 
	 *@date 2012-9-7
	 * @return List<MemberResult>
	 * @update 2012-9-7 下午7:21:17
	 */
	@Override
	public List<MemberResult> searchMemberWithAuth(MemberCondition condition) {
		condition.setCustName(SqlUtil.getLikeField(condition.getCustName()));
		condition.setLinkManName(SqlUtil.getLikeField(condition.getLinkManName()));
		return (List<MemberResult>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + "queryMemberByConditionForContract", condition);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据会员ID查询会员信息
	 * @参数 id:实体ID
	 * @返回值 会员信息
	 * */
	public Member getMember(String id) {
		return (Member) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_MEMBER_ID, id);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据联系人ID查询联系人信息
	 * @参数 id:实体ID
	 * @返回值 联系人信息
	 * */
	public Contact getContact(String id) {
		return (Contact) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_CONTACT_ID, id);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据接送货地址ID查询接送货地址信息
	 * @参数 id:实体ID
	 * @返回值 接送货地址信息
	 * */
	public ShuttleAddress getShuttleAddress(String id) {
		return (ShuttleAddress) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_SHUTTLEADDRESS_ID, id);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据联系人偏好地址ID查询联系人偏好地址信息
	 * @参数 id:实体ID
	 * @返回值 联系人偏好地址信息
	 * */
	public PreferenceAddress getPreferenceAddress(String id) {
		return (PreferenceAddress) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_PREFERENCEADDRESS_ID, id);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据账号信息ID查询账号信息
	 * @参数 id:实体ID
	 * @返回值 账号信息
	 * */
	public Account getAccount(String id) {
		return (Account) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_ACCOUNT_ID, id);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：根据会员ID查询接送货地址信息
	 * @参数：会员ID
	 * @返回值：接送货地址信息集合
	 * */
	public List<ShuttleAddress> searchShuttleAddressByMemberId(String memberId) {
		return (List<ShuttleAddress>) this.getSqlSession()
				.selectList(
						ALTERMEMBER_NAMESPACE + QUERY_SHUTTLEADDRESS_MEMBERID,
						memberId);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：根据会员ID查询联系人基本信息(里面不包括包括联系人偏好地址信息，但是包括客户信息等)
	 * @参数：会员ID
	 * @返回值：联系人信息集合
	 * */
	public List<Contact> searchContactByMemberId(String memberId) {
		return (List<Contact>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + QUERY_CONTACT_MEMBERID, memberId);
	}
	/**
	 * 
	* @Title: queryContactAndPrefByMemberId
	* @Description: 根据会员ID查询联系人基本信息(里面包括联系人偏好地址信息、客户信息等)
	* @author chenaichun 
	* @param @param memberId
	* @param @return    设定文件
	* @date 2014年4月29日 下午4:38:06
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014年4月29日 下午4:38:06
	 */
	@Override
	public List<Contact> queryContactAndPrefByMemberId(String memberId) {
		return (List<Contact>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + "getNEWContactDetailInfoById", memberId);
	}
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改接送货地址状态
	 * @参数 id ID,status 接送货地址状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Override
	public boolean alterShuttleAddressStatus(String id, String status) {
		return alterShuttleAddressStatus(id,status,-1);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public boolean alterShuttleAddressStatus(String id, String status,Integer versionNumber ) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_SHUTTLEADDRESS,OperationFlg.U,id);
		writeCustomerLog(list);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("status", status);
		if (versionNumber != null && -1 != versionNumber){map.put("versionNumber", String.valueOf(versionNumber));}
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_SHUTTLEADDRESS_STATUS, map) > 0 ? true
				: false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人偏好地址状态
	 * @参数 id ID,status 联系人偏好地址状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Override
	public boolean alterPreferenceAddressStatus(String id, String status) {
		return alterPreferenceAddressStatus(id,status,-1);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public boolean alterPreferenceAddressStatus(String id, String status,Integer versionNumber ) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_PREFERENCEADDRESS,OperationFlg.U,id);
		writeCustomerLog(list);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("status", status);
		if (versionNumber != null && -1 != versionNumber){map.put("versionNumber", String.valueOf(versionNumber));}
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_PREFERENCEADDRESS_STATUS, map) > 0 ? true
				: false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改账号信息状态
	 * @参数 id ID,status 账号信息状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Override
	public boolean alterAccountStatus(String id, String status) {
		return alterAccountStatus(id,status,-1);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public boolean alterAccountStatus(String id, String status,Integer versionNumber ) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.U,id);
		writeCustomerLog(list);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("status", status);
		if (versionNumber != null && -1 != versionNumber){map.put("versionNumber", String.valueOf(versionNumber));}
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_ACCOUNT_STATUS, map) > 0 ? true
				: false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人状态
	 * @参数 id ID,status 联系人状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Override
	public boolean alterContactStatus(String id, String status) {
		return alterContactStatus(id,status,-1);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public boolean alterContactStatus(String id, String status,Integer versionNumber ) {
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,OperationFlg.U,id);
		writeCustomerLog(list);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("status", status);
		if (versionNumber != null && -1 != versionNumber){map.put("versionNumber", String.valueOf(versionNumber));}
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_CONTACT_STATUS, map) > 0 ? true
				: false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员相关所有信息(剔除了作废的客户)
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	@Override
	public Member getMemberAllById(String id) {
		return (Member) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_MEMBER_ALL_ID, id);
	}
	/**
	 * 
	* @Title: getMemberAllByIdNEW
	* @Description:根据会员ID查询会员相关所有信息(包括作废的客户)
	* @author chenaichun 
	* @param @param id
	* @param @return    设定文件
	* @date 2014年5月14日 上午11:17:06
	* @return Member    返回类型
	* @throws
	* @update 2014年5月14日 上午11:17:06
	 */
	@Override
	public Member getMemberAllByIdNEW(String id) {
		return (Member) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_MEMBER_ALL_ID_NEW, id);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public Member getInvalidMemberAllById(String memberId) {
		return (Member) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + "queryAllInvalidMemberById", memberId);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public Member4All queryMember4AllById(String memberId) {
		return (Member4All) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + "queryMember4AllById", memberId);
	}
	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息
	 * */
	public List<TodoWorkflow> searchWorkflowByCondition(
			WorkFlowCondition toDoWorkflowCondition, int start, int limit) {
		RowBounds rb = new RowBounds(start, limit);
		return (List<TodoWorkflow>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + QUERY_WORKFLOW_CONDITION,
				toDoWorkflowCondition, rb);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据条件查询会员信息总数
	 * @参数: 会员查询条件
	 * @返回值：满足条件的总数
	 * */
	@Override
	public int countMemberByCondition(MemberCondition condition) {
		return Integer.parseInt(
				String.valueOf(
				this.getSqlSession()
				.selectOne(ALTERMEMBER_NAMESPACE + QUERY_MEMBER_CONDITION_COUNT,condition)));

	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息总数
	 * */
	@Override
	public int countWorkflowByCondition(WorkFlowCondition toDoWorkflowCondition) {
		return Integer.parseInt(String.valueOf(this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_COUNTWORKFLOW_CONDITION,
				toDoWorkflowCondition)));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-19
	 * @描述：根据联系人编码查询会员信息
	 * @参数：linkmanCode 联系人编码
	 * @返回值：Member 包含联系人信息的会员信息
	 * */
	@Override
	public Member getMemberBylinkmanId(String linkmanId, String deptId) {
		Member member = (Member) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + QUERY_MEMBER_LINKMANID, linkmanId);
		// 是否月结客户
		boolean isMethodCust = false;
		if (member != null && deptId != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("custId", member.getId());
			map.put("deptId", deptId);
			isMethodCust = (Integer) this.getSqlSession().selectOne(ALTERMEMBER_NAMESPACE + QUERY_MEMBER_ISMETHODCUST, map) > 0 ? true: false;
			// 将是否零担月结客户信息存入是否允许联系人兑换积分字段中返回；
			member.setIsRedeempoints(isMethodCust);
			//是否快递月结--ps:不需要用部门ID条件
			map.remove("deptId");//key
			isMethodCust = (Integer) this.getSqlSession().selectOne(ALTERMEMBER_NAMESPACE + QUERY_MEMBER_ISEXPPAYWAYCUST, map) > 0 ? true: false;
			member.setIsExPayWay(isMethodCust);
		}
		return member;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public List<Member> searchMemberByCondition(MemberCondition condition) {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"searchMemberByCondition",condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#getContactDetailInfoById(java.lang.String)
	 */
	@Override
	public Contact getContactDetailInfoById(String id) {
		return (Contact) this.getSqlSession().selectOne(ALTERMEMBER_NAMESPACE+QUERY_CONTACT_DETAILINFO,id);
	}

	/**
	 * 
	 * <p>
	 * 根据会员Id查想会员相关银行账号信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId 客户Id
	 * @return
	 * List<Account>
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#getAccountsByMemberId(java.lang.String)
	 */
	@Override
	public List<Account> getAccountsByMemberId(String memberId) {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+QUERY_ACCOUNTBYMEMBERID, memberId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void losePreferenceAddressByContactId(String contactId) {
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"losePreferenceAddressByContactId",contactId);
		
		List<String> idList = this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"getPreferenceAddressIdByContactId",contactId);
		
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,OperationFlg.U,idList);
		writeCustomerLog(list);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	private void writeCustomerLog(List<CustTransationOperation> list){
		CustomerLogUtil.writeCustomerLog(list);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#getPreferAddByContactId(java.lang.String)
	 */
	@Override
	public List<PreferenceAddress> getPreferAddByContactId(String contactId,String addressType) {
		Map<String, String > map = new HashMap<String, String>();
		map.put("contactId", contactId);
		map.put("addressType", addressType);
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"getPreferenceAddressByContactId", map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#searchMyWorkflowByCondition(com.deppon.crm.module.customer.shared.domain.WorkFlowCondition, int, int)
	 */
	@Override
	public List<MyToDoWorkFlow> searchMyWorkflowByCondition(
			WorkFlowCondition workflowCondition, int start, int limit) {
		RowBounds rb = new RowBounds(start, limit);
		return (List<MyToDoWorkFlow>) this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + "queryMyWorkflowByCondition",
				workflowCondition, rb);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void insertMemberOperationLog(MemberOperationLog log) {
		this.getSqlSession().insert(ALTERMEMBER_NAMESPACE+"insertMemberOperationLog", log);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateMemberOperationLog(MemberOperationLog log) {
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updateMemberOperationLog",log);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public List<ApproveData> searchApproveDataByLogId(String logId) {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"searchApproveDataByLogId",logId);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void insertCustWorkflow(String custId, String workflowId) {
		HashMap map = new HashMap();
		map.put("custId", custId);
		map.put("workflowId", workflowId);
		this.getSqlSession().insert(ALTERMEMBER_NAMESPACE + "insertCW", map);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean updateCustWorkflow(String custId, String workflowId) {
		HashMap map = new HashMap();
		map.put("custId", custId);
		map.put("workflowId", workflowId);
		return this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updateCW",map)>0?true:false;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public String getCustWorkflow(String custId){
		List<String> cwList = this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"getCW",custId);
		if(cwList.isEmpty()){
			return null;
		}else{
			return cwList.get(0).toString();
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateMemberAllInfo(Member member) {
		Assert.notNull(member, "member must not null");
		Assert.notNull(member.getId(), "member id must not null");
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,OperationFlg.U,member.getId());
		writeCustomerLog(list);
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updateMemberAllInfo", member);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateContactAllInfo(Contact contact) {
		Assert.notNull(contact, "contact must not null");
		Assert.notNull(contact.getId(), "contact id must not null");
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,OperationFlg.U,contact.getId());
		writeCustomerLog(list);
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updateContactAllInfo", contact);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateAccountAllInfo(Account account) {
		Assert.notNull(account, "account must not null");
		Assert.notNull(account.getId(), "account id must not null");
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_ACCOUNT,OperationFlg.U,account.getId());
		writeCustomerLog(list);
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updateAccountAllInfo", account);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updatePreferenceAddressAllInfo(
			PreferenceAddress preferenceAddress) {
		Assert.notNull(preferenceAddress, "preferenceAddress must not null");
		Assert.notNull(preferenceAddress.getId(), "preferenceAddress id must not null");
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_PREFERENCEADDRESS,OperationFlg.U,preferenceAddress.getId());
		writeCustomerLog(list);
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updatePreferenceAddressAllInfo", preferenceAddress);
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateShuttleAddressAllInfo(ShuttleAddress shuttleAddress) {
		Assert.notNull(shuttleAddress, "shuttleAddress must not null");
		Assert.notNull(shuttleAddress.getId(), "shuttleAddress id must not null");
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_SHUTTLEADDRESS,OperationFlg.U,shuttleAddress.getId());
		writeCustomerLog(list);
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updateShuttleAddressAllInfo", shuttleAddress);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateLinkmanCustType(Member member) {
		Assert.notNull(member, "member can't be null");
		this.getSqlSession().update(ALTERMEMBER_NAMESPACE+"updateLinkmanCustType", member);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public String getCustWorkFlowbyWorkFlow(String workFlow) {
			List<String> cwList = this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"getCust",workFlow);
			if(cwList.isEmpty()){
				return null;
			}else{
				return cwList.get(0).toString();
			}
		}
	/**
	 * 
	 * @Title: queryPrefrenceAddressByContactId
	 *  <p>
	 * @Description: 根据联系人Id查询联系人偏好地址	
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return PreferenceAddress
	 * @throws
	 */
	@Override
	public List<PreferenceAddress> queryPreferenceAddressByContactId(String contactId) {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+QUERY_PreferenceAddress_By_ContactId, contactId);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public boolean updateContactForCustCoordinates(Contact contact) {
		contact.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		return this.getSqlSession().update(
				ALTERMEMBER_NAMESPACE + UPDATE_CONTACT_ID, contact) > 0 ? true
				: false;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public List<String> queryInvalidCust() {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"getInvalidCustNumber");
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void invalidMemberFinStatus(List<String> updateCustStatus,List<String> updateCustIds,boolean status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("updateCustStatus", updateCustStatus);
		map.put("status", status);
		if (!CollectionUtils.isEmpty(updateCustIds)) {
			List<CustTransationOperation> list = CustomerLogUtil
					.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA,
							OperationFlg.U, updateCustIds);
			writeCustomerLog(list);
		}
		this.getSqlSession().selectList(
				ALTERMEMBER_NAMESPACE + "invalidCustNumber", map);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public Member queryMember4WorkflowById(String memberId) {
		return (Member) getSqlSession().selectOne(ALTERMEMBER_NAMESPACE+"queryMember4WorkflowById",memberId);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param updateCustStatus
	 * @return
	 *
	 */
	@Override
	public List<String> queryCustIdByNumber(List<String> updateCustStatus) {
		if (CollectionUtils.isEmpty(updateCustStatus)) {
			return null;
		}
		return this.getSqlSession()
				.selectList(ALTERMEMBER_NAMESPACE + "queryCustIdByNumber",
						updateCustStatus);
	}
	/**
	 * 
	 * <p>
	 * Description:根据客户编码查询会员基本信息<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-2
	 * @param custNum
	 * @return
	 * MemberResult
	 */
	public List<MemberResult> queryBasicMemberByCustNum(String custNum) {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"queryMemberByCustomer", custNum);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param custId
	 * @return
	 *
	 */
	@Override
	public List<ShuttleAddress> queryShuttleAddressForDetial(String custId) {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"queryShuttleAddressForDetial", custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#deleteMemberExtend(java.lang.String)
	 */
	@Override
	public void deleteMemberExtend(String custId) {
		this.getSqlSession().delete(ALTERMEMBER_NAMESPACE+"deleteMemberExtend");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#deletePreferenceAddress(java.lang.String)
	 */
	@Override
	public void deletePreferenceAddress(String contactId) {
		this.getSqlSession().delete(ALTERMEMBER_NAMESPACE+"deletePreferenceAddress");
		
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#deleteContact(java.lang.String)
	 */
	@Override
	public void deleteContact(String custId) {
		this.getSqlSession().delete(ALTERMEMBER_NAMESPACE+"deleteContact");
		
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#deleteShuttleAddress(java.lang.String)
	 */
	@Override
	public void deleteShuttleAddress(String custId) {
		this.getSqlSession().delete(ALTERMEMBER_NAMESPACE+"deleteShuttleAddress");
		
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#deleteAccount(java.lang.String)
	 */
	@Override
	public void deleteAccount(String custId) {
		this.getSqlSession().delete(ALTERMEMBER_NAMESPACE+"deleteAccount");
		
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#deleteMember(java.lang.String)
	 */
	@Override
	public void deleteMember(String id) {
		this.getSqlSession().delete(ALTERMEMBER_NAMESPACE+"deleteMember");
		
	}

	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#searchMemberOperationLogList(java.lang.String)
	 * @version 0.1 2014-4-1下午3:46:49
	 * @param custId
	 * @return
	 * @update 	2014-4-1下午3:46:49
	 */
	@Override
	public List<MemberOperationLog> searchMemberOperationLogList(String custId) {
		return this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"queryMemberOperationLogForDetial",custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IAlterMemberDao#upadteScatterMarktingDeptByDay(int)
	 */
	@Override
	public void upadteScatterMarktingDeptByDay(int day,String deptId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("day", day);
		map.put("deptId", deptId);
		map.put("modifyUser", ContextUtil.getCreateOrModifyUserId());
		getSqlSession().update(ALTERMEMBER_NAMESPACE+"upadteScatterMarktingDeptByDay", map);
	}

	@Override
	public boolean isExtistUnCloseBusiness(String memberId) {
		List<String> list = this.getSqlSession().selectList(ALTERMEMBER_NAMESPACE+"queryUnCloseBusiness", memberId);
		return list !=null&&list.size()>0?true:false;
	}
	@Override
	public MemberExtend getMemberExtendInfo(String id) {
		return (MemberExtend) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + "queryMemberExtendByCustId", id);
	}

	@Override
	public Member4All queryMember4AllByIdNEW(String memberId) {
		return (Member4All) this.getSqlSession().selectOne(
				ALTERMEMBER_NAMESPACE + "queryMember4AllByIdNEW", memberId);
	}
	
	@Override
	public boolean upadteUnCloseBustiness(String newMemberId,String beforeMemberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newMemberId", newMemberId);
		map.put("beforeMemberId", beforeMemberId);
		map.put("modifyUser", ContextUtil.getCreateOrModifyUserId());
		return getSqlSession().update(ALTERMEMBER_NAMESPACE+"upadteUnCloseBustiness", map)>0?true:false;
	}

	@Override
	public MemberOperationLog searchMemberOperationLogByWorkflowNum(String num) {
		return (MemberOperationLog) this.getSqlSession().selectOne(ALTERMEMBER_NAMESPACE+"queryMemberOperationLogByNum",num);
	}
}
