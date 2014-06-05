package com.deppon.crm.module.customer.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Member4All;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MyToDoWorkFlow;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.shared.exception.MemberException;

/**
 * @作者：罗典
 * @时间：2012-3-24
 * @描述：修改会员功能service层
 * */
public interface IAlterMemberService {

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
	public Member getMemberBylinkmanId(String linkmanId, String deptId);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：保存审批数据信息
	 * @参数：ApproveDataList 审批数据集合
	 * @返回值：无
	 * */
	public void saveApproveData(List<ApproveData> approveDataList, String logId);

	/**
	 * @作者：罗典
	 * @时间：2012-3-31
	 * @功能描述：保存需新增的数据并返回ID封装到审批数据集合中
	 * @参数：addPojoMap 需保存的数据集合，approveDataList将保存后的数据封装到审批数据中,memberId,会员ID
	 * @返回值：无
	 * */
	@SuppressWarnings("rawtypes")
	public void savePojoInfo(Map<String, List> addPojoMap,
			List<ApproveData> approveDataList, Member member, String memberId)
			throws MemberException;

	/**
	 * @作者：罗典
	 * @时间：2012-3-31
	 * @功能描述：保存需修改或删除的数据并返回ID封装到审批数据集合中
	 * @参数：approveDataList审批数据集合，deleteDataList需修改或删除的数据, handleType操作类型操作类型
	 *                                                    1为新增，2为修改,3为删除
	 * @返回值：无
	 * */
	public void changeCancelDataToApproveList(List<ApproveData> deleteDataList,
			List<ApproveData> approveDataList) throws MemberException;

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @功能描述：将需修改的审批数据的的旧值存入审批数据集合中
	 * @参数：updateApproveDataList需修改数据 handleType操作类型操作类型 1为新增，2为修改,3为删除
	 * @返回值：无
	 * */
	public void saveOldValueToApproveData(
			List<ApproveData> updateApproveDataList,Member member) throws MemberException;

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据类名和字段名查询审批类型
	 * @参数：className 类名 fieldName 字段名
	 * @返回值： String 工作流审批类型
	 * */
	public String getApproveType(String className, String fieldName);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：工作流号查询审批数据
	 * @参数：workFlowId 工作流ID
	 * @返回值： List<ApproveData> 审批数据集合
	 * */
	public List<ApproveData> searchApproveData(String workFlowId);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据审批数据修改会员相关信息
	 * @参数： approveDataList 审批数据集合 ;isNeedUpdate 是否修改数据 true 需修改，false为还原
	 * @返回值：是否修改成功
	 * */
	public boolean updateMemberInfo(List<ApproveData> approveDataList,
			boolean isNeedUpdate) throws MemberException;

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据会员ID修改会员状态
	 * @参数 id 会员ID,status 会员状态
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean alterMemberStatus(String id, String status);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：查询会员信息
	 * @参数 无
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
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	public Member getMemberAllById(String id);

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据条件查询会员信息总数
	 * @参数: 会员查询条件
	 * @返回值：满足条件的总数
	 * */
	public int countMemberByCondition(MemberCondition condition);

	/**
	 * @作者：罗典
	 * @时间：2012-4-6
	 * @功能描述：根据会员ID查询会员基本信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员基本信息
	 * */
	public Member getMemberById(String id);

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据审批数据将会员状态修改为正常
	 * @参数: 审批数据
	 * @返回值：无
	 * */
	public void changeMemberStatus(List<ApproveData> adList);

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息
	 * */
	public List<TodoWorkflow> searchWorkflowByCondition(
			WorkFlowCondition toDoWorkflowCondition, int start, int limit);

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息总数
	 * */
	public int countWorkflowByCondition(WorkFlowCondition toDoWorkflowCondition);

	/**
	 * <p>
	 * 根据条件查询会员全部信息<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param condition
	 * @return List<Member>
	 */
	public List<Member> searchMemberByCondition(MemberCondition condition);

	/**
	 * <p>
	 * 根据会员ID查询联系人基本信息<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param memberId
	 *            会员id
	 * @return List<Contact> 联系人信息集合
	 */
	public List<Contact> searchContactByMemberId(String memberId);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param contactId
	 * @return Contact
	 */
	public Contact getContactDetailInfoById(String contactId);

	/**
	 * <p>
	 * 根据会员Id查想会员相关银行账号信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 *            客户Id
	 * @return List<Account>
	 */
	public List<Account> getAccountsByMemberId(String memberId);

	/**
	 * <p>
	 * 根据账户id查询账户信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param id
	 *            账户Id
	 * @return Account
	 */
	public Account getAccountById(String id);

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
	 * @功能描述：根据ID修改联系人信息
	 * @参数 contact 联系人信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateContact(Contact contact);

	/**
	 * <p>
	 * 根据联系人id删除偏好地址<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-6-2
	 * @param contactId
	 *            void
	 */
	public void losePreferenceAddressByContactId(String contactId);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改会员基本信息
	 * @参数：member 会员基本信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateMember(Member member);

	/**
	 * @description 根据联系人id查询偏好地址.
	 * @author 潘光均
	 * @version 0.1 2012-6-16
	 * @param
	 * @date 2012-6-16
	 * @return void
	 * @update 2012-6-16 上午11:36:33
	 */
	public List<PreferenceAddress> searchPreferAddByContactId(String contactId,
			String addressType);

	/**
	 * @description 查询我要处理的工作流.
	 * @author 潘光均
	 * @version 0.1 2012-7-14
	 * @param
	 * @date 2012-7-14
	 * @return List<MyToDoWorkFlow>
	 * @update 2012-7-14 上午11:34:17
	 */
	public List<MyToDoWorkFlow> searchMyWorkflowByCondition(
			WorkFlowCondition workflowCondition, int start, int limit);

	/**
	 * <p>
	 * 保存会员修改日志<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param log
	 *            void
	 */
	public void saveMemberOperationLog(MemberOperationLog log);

	/**
	 * <p>
	 * 修改会员修改日志<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param log
	 *            void
	 */
	public void updateMemberOperationLog(MemberOperationLog log);

	/**
	 * <p>
	 * 根据会员修改日志id查询修改详情<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param logId
	 * @return List<ApproveData>
	 */
	public List<ApproveData> searchApproveDataByLogId(String logId);

	/**
	 * <p>
	 * Description:通过客户ID，和工作流id 保存客户最近一次的工作流记录<br />
	 * </p>
	 * 
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param custId
	 * @param workflowId
	 *            void
	 */
	public void insertCustWorkflow(String custId, String workflowId);

	/**
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：
	 * @参数：linkmanCode 联系人编码，部门ID
	 * @返回值：boolean 为true则表示修改成功
	 */
	public boolean updateCustWorkflow(String custId, String workflowId);

	/**
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据客户id查找工作流id
	 * @参数：custId 客户id
	 * @返回值：List 工作流id
	 */
	public String getCustWorkflow(String custId);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据条件查询会员信息
	 * @参数 无
	 * @返回值 会员集合
	 * */
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
	 * @作者：李学兴
	 * @时间：2012-4-6
	 * @功能描述：删除（作废）会员信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	public void deleteMember(Member member);

	/**
	 * <p>
	 * Description:通过客户ID查询客户的所有信息<br />
	 * </p>
	 * 
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return Member4All
	 */
	public Member4All queryMember4AllById(String id);

	/**
	 * 
	 * @Title: modifyPrefrenceAddress
	 *         <p>
	 * @Description: 修改联系人接送货主偏好地址
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return void
	 * @throws
	 */
	public void modifyPreferenceAddress(PreferenceAddress preferenceAddress);

	/**
	 * 
	 * @Title: modifyMemberShuttleAddress
	 *         <p>
	 * @Description: 修改固定客户接送货地址
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return void
	 * @throws
	 */
	public void modifyMemberShuttleAddress(ShuttleAddress shuttleAddress);

	/**
	 * 
	 * @Title: queryPreferenceAddrByContactId
	 *         <p>
	 * @Description: 通过联系人Id查询联系人偏好地址
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return void
	 * @throws
	 */
	public List<PreferenceAddress> queryPreferenceAddrByContactId(
			String contactId);

	/**
	 * 
	 * @Title: savePreferenceAddress
	 *         <p>
	 * @Description: 保存联系人偏好地址
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return void
	 * @throws
	 */
	public void savePreferenceAddress(PreferenceAddress preferenceAddress);

	/**
	 * 
	 * @Title: saveShuttleAddress
	 *         <p>
	 * @Description: 保存接送货地址
	 *               </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-23
	 * @return void
	 * @throws
	 */
	public void saveShuttleAddress(ShuttleAddress shuttleAddress);

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID修改联系人信息 修改标注
	 * @参数 contact 联系人信息
	 * @返回值 boolean 是否修改成功
	 * */
	public boolean updateContactForCustCoordinates(Contact contact);

	/**
	 * @作者：潘光均
	 * @时间：2013-5-7
	 * @功能描述：查询删除（作废）会员编码
	 * @参数：id 会员ID
	 * @返回值：（作废）会员编码
	 * */
	public List<String> findInvalidCustNumber();

	/**
	 * @作者：潘光均
	 * @时间：2013-5-7
	 * @功能描述：删除（作废）会员信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	public void updateMemberFinStatus(List<String> updateCustStatus,
			boolean status);

	/**
	 * @作者：潘光均
	 * @时间：2013-5-16
	 * @功能描述：查询包括（作废）会员信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	Member searchMember4WorkflowById(String memberId);

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
	public List<MemberResult> queryBasicMemberByCustNum(String custNum);

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询到接送货地址信息<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param custId
	 * @return List<ShuttleAddress>
	 */
	public List<ShuttleAddress> queryShuttleAddressForDetial(String custId);

	/**
	 * <p>
	 * Description:物理删除客户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param member
	 * void
	 */
	public void physicalDeleteMember(Member member);

	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午3:45:20
	 * @param custId
	 * @return
	 * List<MemberOperationLog>
	 * @update 2014-4-1下午3:45:20
	 */
	public List<MemberOperationLog> searchMemberOperationLogList(String custId);
	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午3:45:20
	 * @param custId
	 * @return
	 * List<MemberOperationLog>
	 * @update 2014-4-1下午3:45:20
	 */
	public MemberOperationLog searchMemberOperationLogByWorkflowNum(String num);

	/**
	 * <p>
	 * Description:更新60无修改操作的散客营销权限部门为“电销管理小组”<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @param day
	 * void
	 */
	public void modifyScatterMarktingDeptByDay(int day,String deptId);
	
	/**
	 * 
	* @Title: isExtistUnCloseBusiness
	* @Description: 判断是否存在未关闭的商机
	* @author chenaichun 
	* @param @param memberId    设定文件
	* @date 2014年4月21日 下午6:16:42
	* @return void    返回类型
	* @throws
	* @update 2014年4月21日 下午6:16:42
	 */
	public boolean isExtistUnCloseBusiness(String memberId);

	/**
	 * 
	* @Title: newQueryMember4AllById
	* @Description:新的查询客户信息的方法
	* @author chenaichun 
	* @param @param memberId
	* @param @return    设定文件
	* @date 2014年4月29日 下午3:21:27
	* @return Member4All    返回类型
	* @throws
	* @update 2014年4月29日 下午3:21:27
	 */
	public Member4All queryMember4AllByIdNEW(String memberId);

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
	List<MemberResult> searchArriveScatterMember(MemberCondition condition);
	
	/**
	 * 
	* @Title: upadteUnCloseBustiness
	* @Description: 根据客户ID修改商机状态为未关闭的客户
	* @author chenaichun 
	* @param @param newMemberId
	* @param @param beforeMemberId
	* @param @return    设定文件
	* @date 2014年5月9日 上午10:31:17
	* @return boolean    返回类型
	* @throws
	* @update 2014年5月9日 上午10:31:17
	 */
	boolean upadteUnCloseBustiness(String newMemberId, String beforeMemberId);

	/**
	 * 
	* @Title: getMemberAllByIdNEW
	* @Description:根据会员ID查询会员相关所有信息(包括作废的客户)
	* @author chenaichun 
	* @param @param memberId
	* @param @return    设定文件
	* @date 2014年5月14日 上午11:18:52
	* @return Member    返回类型
	* @throws
	* @update 2014年5月14日 上午11:18:52
	 */
	public Member getMemberAllByIdNEW(String memberId);
	
	/**
	 * 
	* @Title: getShuttleAddress
	* @Description: 根据接送货Id查找接送货地址
	* @author chenaichun 
	* @param @param classId
	* @param @return    设定文件
	* @date 2014年5月14日 下午4:07:53
	* @return ShuttleAddress    返回类型
	* @throws
	* @update 2014年5月14日 下午4:07:53
	 */
	public ShuttleAddress getShuttleAddress(String classId);
	/**
	 * 
	* @Title: getPreferenceAddress
	* @Description: 根据偏好地址id查找对应的地址
	* @author chenaichun 
	* @param @param classId
	* @param @return    设定文件
	* @date 2014年5月14日 下午4:08:39
	* @return PreferenceAddress    返回类型
	* @throws
	* @update 2014年5月14日 下午4:08:39
	 */
	public PreferenceAddress getPreferenceAddress(String classId);
}
