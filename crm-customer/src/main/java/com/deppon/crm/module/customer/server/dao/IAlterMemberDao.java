package com.deppon.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
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

/**
 * @作者：罗典
 * @时间：2012-3-23
 * @描述：修改会员DAO层接口
 * */
public interface IAlterMemberDao {
	/**
	 * @作者：罗典
	 * @时间：2012-10-11
	 * @描述：根据联系人编码查询联系人信息
	 * @参数：number 联系人编码
	 * @返回值：Contact 联系人信息
	 * */
	public Contact getContactByNum(String number);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-19
	 * @描述：根据联系人ID查询会员信息,及是否为合同客户(存入是否允许联系人兑换积分字段中返回)
	 * @参数：linkmanId 联系人Id,部门ID
	 * @返回值：Member 包含联系人信息的会员信息
	 * */
	public Member getMemberBylinkmanId(String linkmanId,String deptId);
	/**
	 * @作者：罗典
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	public Member getMemberAllById(String id);

	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：保存审批数据信息
	 * @参数：ApproveDataList 审批数据集.
	 * @返回值：boolean 是否保存成功
	 * */
	public void saveApproveData(List<ApproveData> approveDataList);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据类名和字段名查询审批类型
	 * @参数：className 类名; fieldName 字段名
	 * @返回值：String 审批工作流类型
	 * */
	public String getApproveType(String className, String fieldName);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：工作流ID查询审批数据
	 * @参数：workFlowId:工作流ID
	 * @返回值：List<ApproveData> 此工作流对应的审批数据
	 * */
	public List<ApproveData> searchApproveData(String workFlowId);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改会员基本信息
	 * @参数：member 会员基本信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateMember(Member member);
	
	/**
	 * <p>
	 * Description:修改固定客户所有的信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param member
	 * @void
	 */
	public void updateMemberAllInfo(Member member);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人信息
	 * @参数 contact 联系人信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateContact(Contact contact);
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人信息  修改标注
	 * @参数 contact 联系人信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateContactForCustCoordinates(Contact contact);
	/**
	 * <p>
	 * Description:修改联系人的信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contact
	 * void
	 */
	public void updateContactAllInfo(Contact contact);
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改账号信息
	 * @参数 account 账号信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateAccount(Account account);
	
	/**
	 * <p>
	 * Description:修改固定客户的账号信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param account
	 * void
	 */
	public void updateAccountAllInfo(Account account);


	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改偏好地址信息
	 * @参数 preferenceAddress 偏好地址信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updatePreferenceAddress(PreferenceAddress preferenceAddress);
	
	/**
	 * <p>
	 * Description:修改固定客户的联系人偏好地址<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param preferenceAddress
	 * void
	 */
	public void updatePreferenceAddressAllInfo(PreferenceAddress preferenceAddress);


	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改接送货地址信息
	 * @参数 shuttleAddress 接送货地址信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateShuttleAddress(ShuttleAddress shuttleAddress);
	
	/**
	 * <p>
	 * Description:修改固定客户联系人的接送货地址<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param shuttleAddress
	 * void
	 */
	public void updateShuttleAddressAllInfo(ShuttleAddress shuttleAddress);


	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改会员状态
	 * @修改人：李学兴
	 * @参数 id ID,status 会员状态
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean alterMemberStatus(String id, String status);
	
	/**
	 * <p>
	 * Description:根据ID 和 版本号 修改会员的状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @param status
	 * @param versionNumber
	 * @return
	 * boolean
	 */
	public boolean alterMemberStatus(String id, String status, Integer versionNumber);
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改接送货地址状态
	 * @修改人：李学兴
	 * @参数 id ID,status 接送货地址状态，versionNumber版本号
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean alterShuttleAddressStatus(String id, String status);

	/**
	 * <p>
	 * Description:根据ID 和版本号 修改接送货地址状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @param status
	 * @param versionNumber
	 * @return
	 * boolean
	 */
	public boolean alterShuttleAddressStatus(String id, String status, Integer versionNumber);
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人偏好地址状态
	 * @修改人：李学兴
	 * @参数 id ID,status 联系人偏好地址状态，versionNumber版本号
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean alterPreferenceAddressStatus(String id, String status);

	/**
	 * <p>
	 * Description:根据ID 和 版本号 修改联系人偏好地址状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @param status
	 * @param versionNumber
	 * @return
	 * boolean
	 */
	public boolean alterPreferenceAddressStatus(String id, String status, Integer versionNumber);
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改账号信息状态
	 * @修改人：李学兴
	 * @参数 id ID,status 账号信息状态，versionNumber版本号
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean alterAccountStatus(String id, String status);
	
	/**
	 * <p>
	 * Description:根据ID 和版本号 修改账号信息状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @param status
	 * @param versionNumber
	 * @return
	 * boolean
	 */
	public boolean alterAccountStatus(String id, String status, Integer versionNumber);
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人状态
	 * @修改人：李学兴
	 * @参数 id ID,status 联系人状态
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean alterContactStatus(String id, String status);
	
	/**
	 * <p>
	 * Description:根据ID 和 版本号 修改联系人状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @param status
	 * @param versionNumber
	 * @return
	 * boolean
	 */
	public boolean alterContactStatus(String id, String status, Integer versionNumber);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据条件查询会员信息
	 * @参数 id:实体ID
	 * @返回值 会员集合
	 * */
	public List<MemberResult> searchMember(MemberCondition condition);
	
	/**
	 * @作者：李学兴
	 * @时间：2012-10-18
	 * @功能描述：根据条件查询会员信息 存在客户编码则可以查询出作废客户信息
	 * @参数 id:实体ID
	 * @返回值 会员集合
	 * */
	public List<MemberResult> searchMemberListFor360(MemberCondition condition);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据会员ID查询会员信息
	 * @参数 id:实体ID
	 * @返回值 会员信息
	 * */
	public Member getMember(String id);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据联系人ID查询联系人信息
	 * @参数 id:实体ID
	 * @返回值 联系人信息
	 * */
	public Contact getContact(String id);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据接送货地址ID查询接送货地址信息
	 * @参数 id:实体ID
	 * @返回值 接送货地址信息
	 * */
	public ShuttleAddress getShuttleAddress(String id);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据联系人偏好地址ID查询联系人偏好地址信息
	 * @参数 id:实体ID
	 * @返回值 联系人偏好地址信息
	 * */
	public PreferenceAddress getPreferenceAddress(String id);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据账号信息ID查询账号信息
	 * @参数 id:实体ID
	 * @返回值 账号信息
	 * */
	public Account getAccount(String id);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：根据会员ID查询接送货地址信息
	 * @参数：会员ID
	 * @返回值：接送货地址信息集合
	 * */
	public List<ShuttleAddress> searchShuttleAddressByMemberId(String memberId);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：根据会员ID查询联系人基本信息
	 * @参数：会员ID
	 * @返回值：联系人信息集合
	 * */
	public List<Contact> searchContactByMemberId(String memberId);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息
	 * */
	public  List<TodoWorkflow> searchWorkflowByCondition(WorkFlowCondition toDoWorkflowCondition,int start,int limit);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息总数
	 * */
	public  int countWorkflowByCondition(WorkFlowCondition toDoWorkflowCondition);
	
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据条件查询会员信息总数
	 * @参数: 会员查询条件
	 * @返回值：满足条件的总数
	 * */
	public int countMemberByCondition(MemberCondition condition);
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param condition
	 * @return
	 * List<Member>
	 */
	public List<Member> searchMemberByCondition(MemberCondition condition);
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param id
	 * @return
	 * Contact
	 */
	public Contact getContactDetailInfoById(String id);
	
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
	 * */
	public List<Account> getAccountsByMemberId(String memberId);
	
	/**
	 * 
	 * <p>
	 * 根据联系人删除偏好地址<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-2
	 * @param contactId
	 * void
	 */
	public void losePreferenceAddressByContactId(String contactId);
	
	/**
	 * @description 根据联系人id查询偏好地址.  
	 * @author 潘光均
	 * @version 0.1 2012-6-16
	 * @param 
	 *@date 2012-6-16
	 * @return void
	 * @update 2012-6-16 上午11:38:47
	 */
	public List<PreferenceAddress> getPreferAddByContactId(String contactId,String addressType);
	
	/**
	 * @description 查询我要处理的工作流.  
	 * @author 潘光均
	 * @version 0.1 2012-7-14
	 * @param 
	 *@date 2012-7-14
	 * @return List<MyToDoWorkFlow>
	 * @update 2012-7-14 上午11:36:02
	 */
	public List<MyToDoWorkFlow> searchMyWorkflowByCondition(
			WorkFlowCondition workflowCondition, int start, int limit);
	
	/**
	 * 
	 * <p>
	 * 保存会员操作记录<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param log
	 * void
	 */
	public void insertMemberOperationLog(MemberOperationLog log);
	
	/**
	 * 
	 * <p>
	 * 修改会员操作记录<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param log
	 * void
	 */
	public void updateMemberOperationLog(MemberOperationLog log);
	
	/**
	 * 
	 * <p>
	 * 根据会员修改日志id查询详细修改信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param logId
	 * @return
	 * List<ApproveData>
	 */
	public List<ApproveData> searchApproveDataByLogId(String logId);
	
	/**
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：通过客户ID，和工作流id 保存客户最近一次的工作流记录
	 * @参数：
	 * @返回值：void
	 */
	public void insertCustWorkflow(String custId,String workflowId);
	
	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：
	 * @参数：linkmanCode 联系人编码，部门ID
	 * @返回值：boolean 为true则表示修改成功
	 */
	public boolean updateCustWorkflow(String custId,String workflowId); 
	
	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据客户id查找工作流id
	 * @参数：custId 客户id
	 * @返回值：List 工作流id
	 */
	public String getCustWorkflow(String custId);
	public String getCustWorkFlowbyWorkFlow(String workFlow);
	/**
	 * @description 查询数据权限客户信息.  
	 * @author 潘光均
	 * @version 0.1 2012-9-7
	 * @param 
	 *@date 2012-9-7
	 * @return List<MemberResult>
	 * @update 2012-9-7 下午7:21:48
	 */
	List<MemberResult> searchMemberWithAuth(MemberCondition condition);
	/**
	 * @作者：李学兴
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询作废会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	public Member getInvalidMemberAllById(String memberId);
	
	/**
	 * <p>
	 * Description:通过客户ID查询客户的所有信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * @return
	 * Member4All
	 */
	public Member4All queryMember4AllById(String memberId);
	
	/**
	 * 
	 * @作者：李盛
	 * @时间：2012-9-19
	 * @描述：维护联系人对应的所属客户的客户类型
	 * @参数：member 客户
	 */
	public void updateLinkmanCustType(Member member);
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
	public List<PreferenceAddress> queryPreferenceAddressByContactId(String contactId);
	
	/**
	 * <p>
	 * Description:查询无效的固定客户编码<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.1 2013-1-30
	 * @param phone
	 * @return
	 * List<String>
	 */
	List<String> queryInvalidCust();
	/**
	 * <p>
	 * Description:作废客户财务状态<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.1 2013-5-7
	 * @param updateCustIds 
	 * @return
	 * List<String>
	 */
	public void invalidMemberFinStatus(List<String> updateCustStatus,List<String> updateCustIds, boolean status);
	/**
	 * <p>
	 * Description:通过客户id查询客户所有信息（包括作废客户）<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.1 2013-5-7
	 * @return
	 * List<String>
	 */
	Member queryMember4WorkflowById(String memberId);	
	
	/**
	 * <p>
	 * Description:根据客户编码查询客户id<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.1 2013-5-7
	 * @return
	 * List<String>
	 */
	public List<String> queryCustIdByNumber(List<String> updateCustStatus);
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
	public List<MemberResult> queryBasicMemberByCustNum(String custNum);
	/**
	 * 
	 * <p>
	 * Description:根据客户ID查询接送货地址<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param custId
	 * @return
	 *
	 */
	public List<ShuttleAddress> queryShuttleAddressForDetial(String custId) ;

	/**
	 * <p>
	 * Description:根据客户id删除客户扩展表信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param id
	 * void
	 */
	public void deleteMemberExtend(String custId);

	/**
	 * <p>
	 * Description:根据联系人id删除偏好地址<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param id
	 * void
	 */
	public void deletePreferenceAddress(String contactId);

	/**
	 * <p>
	 * Description:根据客户id删除联系人<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param id
	 * void
	 */
	public void deleteContact(String custId);

	/**
	 * <p>
	 * Description:根据客户id删除接送货地址<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param id
	 * void
	 */
	public void deleteShuttleAddress(String custId);

	/**
	 * <p>
	 * Description:根据客户id删除账号信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param id
	 * void
	 */
	public void deleteAccount(String custId);

	/**
	 * <p>
	 * Description:根据客户id删除客户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param id
	 * void
	 */
	public void deleteMember(String id);

	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午3:46:35
	 * @param custId
	 * @return
	 * List<MemberOperationLog>
	 * @update 2014-4-1下午3:46:35
	 */
	public List<MemberOperationLog> searchMemberOperationLogList(String custId);

	/**
	 * <p>
	 * Description:修改固定天数无修改的散客部门为“电销管理小组”<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @param day
	 * void
	 */
	public void upadteScatterMarktingDeptByDay(int day,String deptId);
	
	/**
	 * 
	* @Title: isExtistUnCloseBusiness
	* @Description: 判断是否存在未关闭的商机状态
	* @author chenaichun 
	* @param @param memberId
	* @param @return    设定文件
	* @date 2014年4月21日 下午6:18:50
	* @return boolean    返回类型
	* @throws
	* @update 2014年4月21日 下午6:18:50
	 */
	public boolean isExtistUnCloseBusiness(String memberId);

	/**
	 * 
	* @Title: getMemberExtendInfo
	* @Description: 根据客户ID查找扩展信息
	* @author chenaichun 
	* @param @param id
	* @param @return    设定文件
	* @date 2014年4月28日 下午8:16:38
	* @return MemberExtend    返回类型
	* @throws
	* @update 2014年4月28日 下午8:16:38
	 */
	public MemberExtend getMemberExtendInfo(String id);

	/**
	 * 
	* @Title: queryMember4AllByIdNEW
	* @Description: 新的查询客户信息的方法
	* @author chenaichun 
	* @param @param memberId
	* @param @return    设定文件
	* @date 2014年4月29日 下午3:23:41
	* @return Member4All    返回类型
	* @throws
	* @update 2014年4月29日 下午3:23:41
	 */
	public Member4All queryMember4AllByIdNEW(String memberId);

	/**
	 * 
	* @Title: queryContactAndPrefByMemberId
	* @Description: 根据会员ID查询联系人基本信息(里面包括联系人偏好地址信息、客户信息等)
	* @author chenaichun 
	* @param @param memberId
	* @param @return    设定文件
	* @date 2014年4月29日 下午4:40:07
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014年4月29日 下午4:40:07
	 */
	public List<Contact> queryContactAndPrefByMemberId(String memberId);

	/**
	 * <p>
	 * Description:查询纯到达散客<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-5-6
	 * @param condition
	 * @return
	 * List<MemberResult>
	 */
	List<MemberResult> queryArriveScatterMember(MemberCondition condition);
	
	/**
	 * 
	* @Title: upadteUnCloseBustiness
	* @Description: 根据客户ID修改未关闭客户的商机
	* @author chenaichun 
	* @param @param memberId
	* @param @return    设定文件
	* @date 2014年5月9日 上午10:20:54
	* @return boolean    返回类型
	* @throws
	* @update 2014年5月9日 上午10:20:54
	 */
	public boolean upadteUnCloseBustiness(String newMemberId, String beforeMemberId);
	/**
	 * 
	* @Title: getMemberAllByIdNEW
	* @Description: 根据会员ID查询会员相关所有信息(包括作废的客户)
	* @author chenaichun 
	* @param @param id
	* @param @return    设定文件
	* @date 2014年5月14日 上午11:17:59
	* @return Member    返回类型
	* @throws
	* @update 2014年5月14日 上午11:17:59
	 */
	public Member getMemberAllByIdNEW(String id);

	public MemberOperationLog searchMemberOperationLogByWorkflowNum(String num);
}
