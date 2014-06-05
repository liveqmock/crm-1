package com.deppon.crm.module.customer.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.CustAddress;
import com.deppon.crm.module.customer.shared.domain.CustomerLocation;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MyToDoWorkFlow;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.shared.exception.MemberException;

/**
 * @作者：罗典
 * @时间：2012-3-23
 * @描述：修改会员功能接口
 * */
public interface IAlterMemberManager {
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
	 * @时间：2012-3-24
	 * @描述：修改会员及相关信息
	 * @参数：approveDataList 需修改的审批数据集合，addPojoMap 需新增的数据实体集合，
	 * 			deleteDataList 需删除的审批数据集合，memberId,会员ID
	 * @返回值：String (启动了工作流返回工作流id , 未启动工作流返回 "") 
	 * */
	@SuppressWarnings("rawtypes")
	public String alterMember(List<ApproveData> approveDataList,
			Map<String, List> addPojoMap,List<ApproveData> deleteDataList,
			String memberId,Member member)throws MemberException;

	/**
	 * @作者：罗典
	 * @时间：2012-3-30
	 * @描述：根据会员查询条件查询会员信息
	 * @参数：condition 会员查询条件
	 * @返回值：只包含联系人集合及会员基本信息的会员信息
	 * */
	public List<MemberResult> searchMember(MemberCondition condition);/**
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
	public Member getMemberAllById(MemberCondition searchCustCondition);
	
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
	 * @时间：2012-4-7
	 * @功能描述：根据工作流ID查询相关审批数据
	 * @参数：workFlowId 工作流ID
	 * @返回值：List<ApproveData> 审批数据集合
	 * */
	public List<ApproveData> searchApproveData(String workFlowId);
	
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
	 * @时间：2012-4-11
	 * @描述：完成工作流审批
	 * @参数: workFlowId 工作流ID ;isSuccess 是否成功 true为成功，false为失败
	 * @返回值：无
	 * */
	public void disposeWorkflow(String workFlowId,boolean isSuccess);
	
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
	 * 
	 * <p>
	 * 根据会员Id查想会员相关银行账号信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId 客户Id
	 * @return
	 * List<Account>
	 */
	public List<Account> getAccountsByMemberId(String memberId);
	
	
	/**
	 * 
	 * <p>
	 * 根据账户id查询账户信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param id 账户Id
	 * @return
	 * Account
	 */
	public Account getAccountById(String id);
	/**
	 * 
	 * <p>
	 * 根据客户编码得到客户id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param memberNum
	 * @return
	 * String
	 */
	public String getMemberIdByMemberNum(String memberNum);
	public String getMemberIdByMemberNumFor360(String memberNum);
	/**
	 * 
	 * <p>
	 * 根据联系人编码得到客户id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param contactNum
	 * @return
	 * String
	 */
	public String getMemberIdByContactNum(String contactNum);
	/**
	 * 
	 * <p>
	 * 给联系人添加 联系人类型<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-24
	 * @param contactId
	 * @param contactType
	 * void
	 */
	public void addContactType(String contactId,int addContactType);
	/**
	 * 
	 * <p>
	 * 根据联系人手机号得到客户id<br />
	 * </p>
	 * @author 张斌
	 * @version 0.2 2012-5-25
	 * @param mobilePhone
	 * @return
	 * String
	 */
	public String getMemberIdByContactMobile(String mobilePhone);
	/**
	 * 
	 * <p>
	 * 根据联系人id,清空其对应的偏好地址<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-2
	 * @param contactId
	 * void
	 */
	public void cleanContactPreferenceAddress(String contactId);
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据联系人ID查询联系人信息
	 * @参数 id:实体ID
	 * @返回值 联系人信息
	 * */
	public Contact getContact(String id);
	/**
	 * @description 根据联系人id查询联系人偏好地址.  
	 * @author 潘光均
	 * @version 0.1 2012-6-16
	 * @param 
	 *@date 2012-6-16
	 * @return void
	 * @update 2012-6-16 上午11:35:06
	 */
	public List<PreferenceAddress> searchPreferenceAddressByContactId(String contactId,String addressType);
	/**
	 * @description 查询我要处理的工作流.  
	 * @author 潘光均
	 * @version 0.1 2012-7-14
	 * @param 
	 *@date 2012-7-14
	 * @return List<MyToDoWorkFlow>
	 * @update 2012-7-14 上午11:32:41
	 */
	public List<MyToDoWorkFlow> searchMyWorkflowByCondition(
			WorkFlowCondition workflowCondition, int start, int limit);
	
	/**
	 * 
	 * <p>
	 * 根据日志id查询操作记录<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param logId
	 * @return
	 * List<ApproveData>
	 */
	public List<ApproveData> searchApproveDataByLogId(String logId);
	
	/**
	 * 
	 * <p>
	 * 会员审批查询方法<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-6
	 * @param memberId
	 * @return
	 * Member
	 */
	public Member queryAlterMemberById(String memberId);
	/**
	 * 
	 * <p>
	 * 更新会员最后一次工作流id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-6
	 * @param memberId
	 * @param workFlowId
	 * void
	 */
	public void updateMemberLastWorkFlowId(String memberId,String workFlowId);
	/**
	 * 
	 * <p>
	 * 得到会员最后一次工作流id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-6
	 * @param memberId
	 * @return
	 * String
	 */
	public String getMemberLastWorkFlowId(String memberId);
	/**
	 * @作者：罗典
	 * @时间：2012-3-30
	 * @描述：根据会员查询条件查询会员信息
	 * @参数：condition 会员查询条件
	 * @返回值：只包含联系人集合及会员基本信息的会员信息
	 * */
	List<MemberResult> searchMemberWithAuth(MemberCondition condition);
	/**
	 * @作者：李学兴
	 * @时间：2012-9-11
	 * @功能描述：删除（作废）会员信息
	 * @参数 无
	 * @返回值 会员集合
	 * */
	public void deleteMember(Member member);	
	/**
	 * @作者：李学兴
	 * @时间：2012-9-11
	 * @功能描述：根据会员ID查询作废后会员相关所有信息
	 * @参数：searchCustCondition.getMemberId() 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	public Member getInvalidMemberAllById(MemberCondition searchCustCondition);

	/**
	 * 
	 * @Title: alterMemberAddress
	 *  <p>
	 * @Description: 修改固定客户、潜散客地址
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-10
	 * @return void
	 * @throws
	 */
	public void alterCustAddress(CustAddress address);
	
	/**
	 * @Description:修改固定客户、潜散客 标注<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 * @param customerLocation
	 * @return boolean
	 */
	public boolean updateCustCoordinates(CustomerLocation customerLocation);
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
	public MemberResult queryBasicMemberByCustNum(String custNum);
	/**
	 * <p>
	 * Description:物理删除客户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param member
	 * void
	 */
	void physicalDeleteMember(Member member);
	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午3:34:40
	 * @param custId
	 * void
	 * @update 2014-4-1下午3:34:40
	 */
	public List<MemberOperationLog> searchMemberOperationLogList(String custId);
	
}
