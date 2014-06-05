package com.deppon.crm.module.customer.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

/**
 * @Descript 会员操作DAO层接口
 * @title PotentialCustomerDao.java
 * @package com.deppon.crm.module.customer.server.dao
 * @author 罗典
 * @version 0.1 2012-3-19
 */

public interface IMemberDao {

	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存联系人信息
	 * @参数：contact 联系人信息
	 * @返回值：无
	 * */
	public void saveContact(Contact contact);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存接送货地址信息
	 * @参数：shuttleAddress 接送货地址信息
	 * @返回值：无
	 * */
	public void saveShuttleAddress(ShuttleAddress shuttleAddress);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存偏好地址信息
	 * @参数：preferenceAddress 偏好地址信息
	 * @返回值：无
	 * */
	public void savePreferenceAddress(PreferenceAddress preferenceAddress);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-1
	 * @描述：保存账号信息
	 * @参数：account 账号信息
	 * @返回值：无
	 * */
	public void saveAccount(Account account);
	
	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-8-1
	 * @描述：根据税务登记号查出客户编码以及客户所在的部门
	 * @参数：taxregnumber 税务登记号
	 * @返回值：List 包含客户编号以及客户所在部门
	 */
	public List<Map<String, String>> getMemberListByTaxregNumber(String taxregnumber);
	
	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据联系人个人证件号、证件类型、是否是会员来获取用户的部门和编码
	 * @参数：cardTypeCon 证件类型；idCard 证件号码；custType 客户类型；isMain 是否是主联系人
	 * @返回值：List 包含客户编号以及客户所在部门
	 */
	public List<Map<String,String>> getMemberListByPersonalcardNumber(String cardTypeCon,String idCard, String custType,
			boolean isMain);
	
	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据客户名称和电话号码查找客户部门以及客户编码
	 * @参数：custName 客户名称；telephone 客户电话
	 * @返回值：List 客户部门以及客户编码列表
	 */
	public List<Map<String,String>> getMemberListByCustnameAndContact(String custName,String telephone);
	
	/**
	 * 
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据手机号码查找客户所在部门以及客户编码
	 * @参数：telephone 手机号码
	 * @返回值：List 客户部门以及客户编码列表
	 */
	public List<Map<String,String>> getMemberListByCellphone(String telephone);
	
	/**
	 * 
	 * @作者：李学兴
	 * @时间：2012-9-13
	 * @描述：通过id删除（作废）散客升级列表散客信息，支持批量作废
	 * @参数：upGradeCustId 散客id,modifyUser 操作人
	 * @返回值：void
	 */
	public void deleteScatterUpgradeById(String upGradeCustId);

	/**
	 * <p>
	 * Description:根据会员ID，操作人，保存接送货地址信息 <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param shuttleAddressList
	 * @param memberId
	 * @param createUser
	 * void
	 */
	public void insertShuttleAddressList(
			List<ShuttleAddress> shuttleAddressList, String memberId, String createUser);

	/**
	 * <p>
	 * Description:根据会员id，操作人 保存联系人信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactList
	 * @param memberId
	 * @param createUser
	 * void
	 */
	public void insertContactList(List<Contact> contactList, String memberId, String createUser);

	/**
	 * <p>
	 * Description:根据会员id，操作人 保存联系人偏好地址 信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param preferenceAddress
	 * @param contactId
	 * @param shuttelId
	 * void
	 */
	public void insertPreferenceAddress(PreferenceAddress preferenceAddress,
			String contactId, String shuttelId);

	/**
	 * <p>
	 * Description:保存会员基本信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param member
	 * void
	 */
	public void insertMemberBaseInfo(Member member);

	/**
	 * <p>
	 * Description:根据会员id，操作人 保存会员账号 信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param account
	 * @param memberId
	 * @param contactId
	 * void
	 */
	public void insertAccount(Account account, String memberId, String contactId);

	/**
	 * <p>
	 * Description:根据税务登记证统计会员的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param taxregNumber
	 * @return
	 * int
	 */
	public int getMemberCountByTaxregNumber(String taxregNumber);

	/**
	 * <p>
	 * Description:根据会员序列的下一个序列值<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @return
	 * String
	 */
	public String getMemberIdUseSeq();

	/**
	 * <p>
	 * Description:根据ID，当前操作人 修改散客升级列表的备注<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param upGradeCustomerId
	 * @param remark
	 * @param modifyUser
	 * void
	 */
	public void updateUpGradeCustomerById(String upGradeCustomerId,
			String remark,String modifyUser);

	/**
	 * <p>
	 * Description:通过ID得到散客升级列表信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @return
	 * UpGradeCustomer
	 */
	public UpGradeCustomer getUpGradeCustomerById(String id);

	/**
	 * <p>
	 * Description:通过散客升级查询条件 分页查询 散客升级列表信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<UpGradeCustomer>
	 */
	public List<UpGradeCustomer> searchUpGradeCustomerList(
			UpGradeCustomerCondition condition,int start,int limit);

	/**
	 * <p>
	 * Description:通过散客升级查询条件 统计其数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * int
	 */
	public int getCountUpGradeCustomerByUpGradeCustomerCondition(
			UpGradeCustomerCondition condition);
	
	/**
	 * <p>
	 * Description:根据手机 得到散客升级列表里面的 级别值<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param phone
	 * @return
	 * List<String>
	 */
	List<String> getAllTargetLevelByPhone(String phone);

	/**
	 * <p>
	 * Description:根据固话和姓名 得到散客升级列表里面的 级别值<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param tel
	 * @param contactName
	 * @return
	 * List<String>
	 */
	List<String> getAllTargetLevelByTelAndName(String tel, String contactName);

	/**
	 * <p>
	 * Description:根据手机号码 删除散客列表对应的值<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param phone
	 * void
	 */
	public void deleteUpGradeCustomerByNumber(String phone);

	/**
	 * <p>
	 * Description:根据会员id删除会员账号信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * void
	 */
	public void deleteAccountByMemberId(String memberId);

	/**
	 * <p>
	 * Description:根据会员ID 删除 会员信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * void
	 */
	public void deleteMemberInfoById(String memberId);

	/**
	 * <p>
	 * Description:删除会员ID 删除接送货地址信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * void
	 */
	public void deleteShuttleAddressByMemberId(String memberId);

	/**
	 * <p>
	 * Description:根据会员ID 删除联系人偏好地址信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * void
	 */
	public void deletePreferenceAddressByMemberId(String memberId);

	/**
	 * <p>
	 * Description:根据客户id，当前操作人 修改会员状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param status
	 * @param memberId
	 * @param modifyUser
	 * void
	 */
	public void updateMemberStautsByMemberId(String status, String memberId,String modifyUser);

	/**
	 * <p>
	 * Description:根据客户id，当前操作人 修改联系人状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param status
	 * @param memberId
	 * @param modifyUser
	 * void
	 * @param notUpdateStatus 
	 */
	public void updateContactStatusByMemberId(String status, String memberId,String modifyUser, String notUpdateStatus);

	/**
	 * <p>
	 * Description:根据客户id，当前操作人 修改会员账号状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param status
	 * @param memberId
	 * @param modifyUser
	 * void
	 * @param notUpdateStatus 
	 */
	public void updateAccountStatusByMemberId(String status, String memberId,String modifyUser, String notUpdateStatus);

	/**
	 * <p>
	 * Description:根据客户编码，当前操作人 修改会员级别<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param targetLevel
	 * @param memberNumber
	 * @param modifyUser
	 * void
	 */
	public void updateMemberLevelByMemberNumber(String targetLevel,
			String memberNumber,String modifyUser);

	/**
	 * <p>
	 * Description:会员升级实体查询条件 分页查询 会员升级列表信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<MemberUpgradeList>
	 */
	public List<MemberUpgradeList> searchMemberUpgradeList(
			UpGradeCustomerCondition condition, int start, int limit);

	/**
	 * <p>
	 * Description:根据时间 查询 会员升级列表信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param statisticsTime
	 * @return
	 * List<MemberUpgradeList>
	 */
	public List<MemberUpgradeList> getMemberUpgraeByStatisticsTime(
			String statisticsTime);

	/**
	 * <p>
	 * Description:通过时间 查询 会员降级列表 信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param statisticsTime
	 * @return
	 * List<MemberDemotionList>
	 */
	public List<MemberDemotionList> getMemberDemotionByStatisticsTime(
			String statisticsTime);

	/**
	 * <p>
	 * Description:通过会员升级查询条件  分页查询 会员降级列表 信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<MemberDemotionList>
	 */
	public List<MemberDemotionList> searchMemberDemotionList(
			UpGradeCustomerCondition condition, int start, int limit);

	/**
	 * <p>
	 * Description:根据会员升级查询条件 统计升级的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * int
	 */
	public int getCountMemberUpgradeListByCondition(
			UpGradeCustomerCondition condition);

	/**
	 * <p>
	 * Description:根据会员升级查询条件 统计降级的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * int
	 */
	public int getCountMemberDemotionListByCondition(
			UpGradeCustomerCondition condition);

	/**
	 * <p>
	 * Description:根据会员ID。。。<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * @return
	 * Double
	 */
	public Double getMothlyStatementBymemberId(String memberId);

	/**
	 * <p>
	 * Description:根据ID修改。。。。。<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * @param monthlyStatement
	 * @param modifyUser
	 * void
	 */
	public void updateMothlyStatementByMemberId(String memberId,
			double monthlyStatement,String modifyUser);

	/**
	 * <p>
	 * Description:未知用途表。正式上此表没有数据<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * @param monthlyStatement
	 * void
	 */
	public void insertMothlyStatement(String memberId, double monthlyStatement);

	/**
	 * <p>
	 * Description:根据证件类型,证件号码，客户类型，是否是主联系人 四个条件 查询联系人的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param cardTypeCon
	 * @param idCard
	 * @param custType
	 * @param isMain
	 * @return
	 * int
	 */
	public int countContactByCondition(String cardTypeCon,String idCard, String custType,
			boolean isMain);

	/**
	 * <p>
	 * Description:根据联系人编码 统计联系人的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param linkManNumber
	 * @return
	 * int
	 */
	public int countContactByLinkManNumber(String linkManNumber);

	/**
	 * <p>
	 * Description:根据会员id和当前用户ID 修改接送货地址的状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param statusNoefficacy
	 * @param memberId
	 * @param createOrModifyUserId
	 * void
	 * @param notUpdateStatus 
	 */
	public void updateShuttleAddressStatusByMemberId(String statusNoefficacy,
			String memberId, String createOrModifyUserId, String notUpdateStatus);

	/**
	 * <p>
	 * Description:根据会员id和当前用户ID 修改联系人偏好地址的状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param statusNoefficacy
	 * @param memberId
	 * @param createOrModifyUserId
	 * void
	 * @param notUpdateStatus 
	 */
	public void updatePreferenceAddressStatusByMemberId(
			String statusNoefficacy, String memberId,
			String createOrModifyUserId, String notUpdateStatus);
	
	/**
	 * <p>
	 * Description:根据审批中实体条件 统计其对应的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param date
	 * @return
	 * int
	 */
	public int countApproveDataByCondition(ApproveData date);

	/**
	 * <p>
	 * Description:根据联系人编码 得到客户的部门名字和客户编码
	 * department.fdeptname depart,customer.fcustnumber cust
	 * <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param linkManNumber
	 * @return
	 * List<Map<String,String>>
	 */
	List<Map<String, String>> getMemberListByLinkManNumber(String linkManNumber);
	/**
	 * 
	 * @Title: memberExistOrNot
	 *  <p>
	 * @Description: 通过客户编码查询是否存在有效固定客户
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-6-2
	 * @return boolean
	 * @throws
	 */
	public boolean memberExistOrNot(String custNumber);

	/**
	 * <p>
	 * Description:保存暂存官网用户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param cust
	 * void
	 */
	public void createChannelCustomer(ChannelCustomer cust);

	/**
	 * <p>
	 * Description:根据官网用户名查询客户基本信息、联系人信息、接送货地址信息、账号信息、偏好地址信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param custName
	 * @return
	 * Member
	 */
	public Member queryMemberByUserName(String custName);

	/**
	 * <p>
	 * Description:更新暂存的官网用户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param cust
	 * void
	 */
	public void updateChannelCustomer(ChannelCustomer cust);

	/**
	 * <p>
	 * Description:根据官网用户名查询暂存的官网用户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param custName
	 * @return
	 * List<ChannelCustomer>
	 */
	public List<ChannelCustomer> queryChannelCustomerByUserName(String custName);

	/**
	 * <p>
	 * Description:查询要启动修改审批工作流的渠道客户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @return
	 * List<ChannelCustomer>
	 */
	public List<ChannelCustomer> queryNeedWorkflowCustomer();

	/**
	 * <p>
	 * Description:删除已经启动修改工作流的渠道客户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param needDeleteIds
	 * void
	 */
	public void deleteChannelCustomerByIds(List<String> needDeleteIds);

	/**
	 * <p>
	 * Description:保存客户扩展信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-12
	 * @param member
	 * void
	 * @return 
	 */
	public MemberExtend insertMemberExtendInfo(MemberExtend extend);
	/**
	 * 
	 * <p>获得该部门一年没有开发计划的散客<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-22
	 * @param date
	 * @return
	 * List<ScatterCustomer>
	 */
	public List<Member> getNotDevpPlanAndSysmakelsLoseList(Date date,String dept,String custType);
	
	/**
	 * 
	 * <p>
	 * 获得一年没有开发计划散客的部门<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-5-26
	 * @param date
	 * @return
	 * List<String>
	 */
	public List<String> getNotDevpPlanAndSysmakelsLoseDeptList(Date date,String custType);

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
	public List<Member> getScatterCustLoselsAndOneYear(Date date,
			String deptId,String custType);

	/**
	 * <p>
	 * Description:得到部门中失效一年的散客 部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-19
	 * @param date
	 * @return
	 * List<String>
	 */
	public List<String> getScatterCustDeptLoselsAndOneYear(Date date,String custType);


	/**
	 * 
	 * <p>
	 * Description:更新客户扩展属性<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-22
	 * @param extend
	 * void
	 */
	public void updateMemberExtend(MemberExtend extend);
	/**
	 * 
	* @Title: searchMemberExtendByID
	* @Description: 
	* @author chenaichun 
	* @param @param custId
	* @param @return    设定文件
	* @date 2014年3月27日 下午2:29:51
	* @return MemberExtend    返回类型
	* @throws
	* @update 2014年3月27日 下午2:29:51
	 */
	public MemberExtend searchMemberExtendByID(String custId);

	/**
	 * <p>
	 * Description:根据客户id查询客户营销权限部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @param custId
	 * @return
	 * List<CustomerMarktingDept>
	 */
	public List<CustomerMarktingDept> queryCustomerMarktingDeptByCustId(
			String custId);

	/**
	 * <p>
	 * Description:新增客户营销权限部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @param dept
	 * void
	 */
	public void createMemberMarktingDept(CustomerMarktingDept dept);

	/**
	 * <p>
	 * Description:修改客户营销权限部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @param dept
	 * void
	 */
	public void updateMemberMarktingDept(CustomerMarktingDept dept);

	/**
	 * <p>
	 * Description:查询客户第一次发货营销权限部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @return
	 * List<CustomerMarktingDept>
	 */
	public List<CustomerMarktingDept> queryCustMarktingDeptByFirstShip();
	
	/**
	 * 
	 * @Title: updateDevelopmentLog
	 *  <p>
	 * @Description: 开发阶段日志保存
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 * @return void
	 * @throws
	 */
	public void updateDevelopmentLog(DevelopmentLog developmentLog);
	
	/**
	 * 
	 * @Title: queryDevelopmentLogs
	 *  <p>
	 * @Description: 通过客户ID查询开发阶段日志
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-8
	 * @return List<DevelopmentLog>
	 * @throws
	 */
	public List<Map<String, String>> queryDevelopmentLogs(String custId);

	/**
	 * 
	* @Title: searchMemberByConstactWay
	* @Description: 根据联系方式查询客户基本信息（有效和审批中）
	* @author chenaichun 
	* @param @param mobilePhone
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014年5月16日 上午8:16:30
	* @return List<Member>    返回类型
	* @throws
	* @update 2014年5月16日 上午8:16:30
	 */
	public List<Member> searchMemberByConstactWay(String mobilePhone,
			String tel, String name);
	
	/**
	 * 
	 * @Title: queryBusinessOpportunityById
	 *  <p>
	 * @Description: 获取指定的商机信息
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-20
	 * @return BusinessOpportunity
	 * @throws
	 */
	BusinessOpportunity queryBusinessOpportunityById(String custId);
	
	/**
	 * 
	 * @Title: saveBusinessOpportunityStep
	 *  <p>
	 * @Description: 保存商机阶段
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-20
	 * @return void
	 * @throws
	 */
	void saveBusinessOpportunityStep(BusinessOpportunity bo);

	/**
	 * 
	 * @Title: addBusinessOpportunityLog
	 *  <p>
	 * @Description: 保存商机阶段变更日志
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-20
	 * @return void
	 * @throws
	 */
	void addBusinessOpportunityLog(List<BoOperationLog> logList);
}
