package com.deppon.crm.module.customer.server.manager;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDeptView;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept;
import com.deppon.crm.module.customer.shared.domain.DevelopmentLog;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.ImplementMemberView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.MemberExaminView;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
/**
 * 
 * <p>
 * 会员管理，会员对外的接口。<br />
 * </p>
 * @title IMemberManager.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author bxj
 * @version 0.2 2012-3-21
 */
public interface IMemberManager {
	
	/**
	 * 
	 * <p>
	 * 会员创建<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-21
	 * @param member
	 * void
	 */
	public void createMember(Member member,UpGradeCustomer upGradeCustomer);
	/**
	 * 
	 * <p>
	 * Description:根据手机查询会员信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param phone
	 * @return
	 * Member
	 */
	public Member getMemberByPhone(String phone);
	/**
	 * 
	 * <p>
	 * Description:根据电话查询会员信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param tel
	 * @return
	 * List<Member>
	 */
	public List<Member> searchMemberByTel(String tel);
	/**
	 * 
	 * <p>
	 * Description:根据名字查询会员信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param name
	 * @return
	 * List<Member>
	 */
	public List<Member> searchMemberByName(String name);

	/**
	 * 
	 * @description 保存会员.
	 * @author 潘光均
	 * @version 0.1 2013-2-22
	 * @param
	 * @date 2013-2-22
	 * @return boolean
	 * @update 2013-2-22 上午9:23:45
	 */
	public boolean createMember(Member member) ;
	/**
	 * 
	 * <p>
	 * 初始化会员信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-21
	 * @param upGrade
	 * @return
	 * Member
	 */
	public Member initMember(UpGradeCustomer upGrade);
	/**
	 * 
	 * <p>
	 * 查询散客升级列表的总数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-21
	 * @return
	 * List<UpGradeList>
	 */
	public int getCountUpGradeCustomerByUpGradeCustomerCondition(UpGradeCustomerCondition condition);
	/**
	 * 
	 * <p>
	 * 分页查询散客升级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-29
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<UpGradeCustomer>
	 */
	public List<UpGradeCustomer> searchUpGradeCustomerList(UpGradeCustomerCondition condition,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * 添加备注<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-28
	 * @param upGradeCustomerId
	 * @param remark
	 * void
	 */
	public void addUpGradeCustomerRemark(String upGradeCustomerId,String remark);
	/**
	 * 
	 * <p>
	 * 根据id查询散客信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-28
	 * @param id
	 * @return
	 * UpGradeCustomer
	 */
	public UpGradeCustomer getUpGradeCustomerById(String id);
	
	/**
	 * 
	 * <p>
	 * 创建特殊会员<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-28
	 * @return
	 * Member
	 */
	public Member initSepcailMember();
	/**
	 * 
	 * <p>
	 * 创建实施会员<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-28
	 * @return
	 * Member
	 */
	public ImplementMemberView initImplementMember(String phone,String tel,String name)
			throws CrmBusinessException;
	
	/**
	 * 
	 * <p>
	 * 特殊会员创建<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-28
	 * @param member
	 * void
	 * @return 
	 */
	public String createSpecialMember(Member member);
	/**
	 * 
	 * <p>
	 * 通过审批结果，处理特殊会员信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-28
	 * @param memberId
	 * @param examineResult
	 * void
	 */
	public void disposeSpecialMemberByExamineResult(String memberId,boolean examineResult);
	/**
	 * 
	 * <p>
	 * 查询出当前部门下面的所有会员id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-28
	 * @param deptId
	 * @return
	 * List<String>
	 */
	public List<String> getMemberIdListBydeptId(String deptId);
	/**
	 * 
	 * <p>
	 * 创建实施会员<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param member 会员对象
	 * void
	 */
	public void createImplementMember(Member member);
	/**
	 * 
	 * <p>
	 * 检查会员是否存在<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param phone
	 * @param tel
	 * @param name
	 * void
	 */
	public Member checkIsExistMember(String phone,String tel,String name);
	/**
	 * 
	 * <p>
	 * 得到要处理的会员升级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-5
	 * @param statisticsTime 
	 * @return
	 * List<MemberUpgrade>
	 */
	public List<MemberUpgradeList> getMemberUpgrage(String statisticsTime);
	/**
	 * 
	 * <p>
	 * 会员升级<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-5
	 * @param memberUpgrade
	 * void
	 */
	public void upgrageMember(MemberUpgradeList memberUpgrade);
	/**
	 * 
	 * <p>
	 * 查询会员升级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-5
	 * @param condition
	 * void
	 */
	public List<MemberUpgradeList> searchMemberUpgradeList(UpGradeCustomerCondition condition,int start,int limit);
	/**
	 * 
	 * <p>
	 * 根据查询条件查询出总页数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param condition
	 * @return
	 * int
	 */
	public int getCountMemberUpgradeListByCondition(UpGradeCustomerCondition condition);
	/**
	 * 
	 * <p>
	 * 会员升级<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param upgradeList
	 * void
	 */
	public void upgrageMember(List<MemberUpgradeList> upgradeList);
	
	/**
	 * 
	 * <p>
	 * 会员降级<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param demotionList
	 * void
	 */
	public void demotionMember(List<MemberDemotionList> demotionList);
	/**
	 * 
	 * <p>
	 * 根据统计时间查询会员降级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param statisticsTime
	 * void
	 */
	public List<MemberDemotionList> getDemotionMemberByStatisticsTime(String statisticsTime);

	/**
	 * 
	 * <p>
	 * 根据查询条件查询会员降级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<MemberDemotionList>
	 */
	public List<MemberDemotionList> searchMemberDemotionList(UpGradeCustomerCondition condition,int start,int limit);
	/**
	 * 
	 * <p>
	 * 根据查询条件查询出总页数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param condition
	 * @return
	 * int
	 */
	public int getCountMemberDemotionListByCondition(UpGradeCustomerCondition condition);
	/**
	 * 
	 * <p>
	 * 检查手机是否可用<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-7
	 * @param mobilePhone
	 * @return
	 * boolean
	 */
	public boolean checkMobilePhoneCanUse(String mobilePhone);
	/**
	 * 
	 * <p>
	 * 检查固话加联系人姓名是否可用<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-7
	 * @param  tel:固话,  name:联系人姓名
	 * @return
	 * boolean
	 */
	public boolean checkTelAndNameCanUse(String tel, String name);
	/**
	 * 
	 * <p>
	 * 检查企业凭证号是否可用<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-7
	 * @param enterpriseMember
	 * @return
	 * boolean
	 */
	public boolean checkTaxregNumberCanUse(String enterpriseMember);
	/**
	 * 
	 * <p>
	 * 检查身份证是否可用<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-7
	 * @param idCard
	 * @return
	 * boolean
	 */
	public boolean checkIdCardCanUse(String idCard,String custType,boolean isMain,String cardTypeCon);
	
	/**
	 * 
	 * <p>
	 * 验证个人身份证的有效性<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-24
	 * @param idCard
	 * @param cardTypeCon
	 * @return
	 * boolean
	 */
	public boolean checkIdCardIsLegal(String idCard,String cardTypeCon);
	/**
	 * 
	 * <p>
	 * 验证联系人编码是否可用<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-15
	 * @param linkManNumber
	 * @return
	 * boolean
	 */
	public boolean checkLinkManNumberCanUse(String linkManNumber);
	/**
	 * 
	 * <p>
	 * 添加月结额度<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-7
	 * @param memberId
	 * @param monthlyStatement
	 * void
	 */
	public void addMonthlyStatement(String memberId,double monthlyStatement);
	/**
	 * 
	 * <p>
	 * 减少月结额度<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-7
	 * @param memberId
	 * @param monthlyStatement
	 * void
	 */
	public void reduceMonthlyStatement(String memberId,double monthlyStatement);
	/**
	 * 
	 * <p>
	 * 是否是合同客户<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-11
	 * @param memberId
	 * @return
	 * boolean
	 */
	public boolean isContractMember(String memberId);
	
	/**
	 * 是否是合同客户<br />
	 * @author bxj
	 * @version 0.2 2012-4-11
	 * @param memberId custNumber
	 * @return
	 * boolean
	 */
	public boolean isContractMemberByIdOrCustNumber(String memberId,String custNumber);
	/**
	 * 
	 * <p>
	 * 保存审批记录<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-12
	 * @param record
	 * void
	 */
	void saveExamineRecord(ExamineRecord record);
	/**
	 * 
	 * <p>
	 * 创建修改会员流程审批试图<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param record
	 * void
	 * @return 
	 */
	public MemberExaminView createModifyMemberExaminView(String memberId,long workflowId);
	/**
	 * 
	 * <p>
	 * 创建特殊会员流程审批试图<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param todoWorkflow
	 * @return
	 * MemberExaminView
	 */
	public MemberExaminView createSepMemberExaminView(String memberId,long workflowId);
	/**
	 * 
	 * <p>
	 * 根据会员编码查询会员信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param custNumber
	 * @return
	 * Member
	 */
	public Member getMemberByCustNumber(String custNumber);
	/**
	 * 
	 * <p>
	 * 查询部门里面的会员信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-16
	 * @param deptIds
	 * @return
	 * List<Member>
	 */
	public List<Member> getMemberIdListBydeptIds(String[] deptIds);
	
	
	/**
	 * 
	 * <p>
	 * 根据会员id查询三个联系人，主联系人在第一个位置<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 * @return
	 * List<Contact>
	 */
	public List<Contact> get3ContactByOrder(String memberId);
	
	/**
	 * 
	 * <p>
	 * 根据客户id取得所有的联系人列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-27
	 * @param memberId
	 * @return
	 * List<Contact>
	 */
	public List<Contact> getContactsByMemberId(String memberId);
	/**
	 * 
	 * <p>
	 * Description:根据联系人id查询联系人详情<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param contactId
	 * @return
	 * Contact
	 */
	public Contact getContactDetailInfoById(String contactId);
	/**
	 * 
	 * <p>
	 * 会员归属部门变更<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-5
	 * @param changeMemberDept
	 * @return
	 * long
	 */
	public long changeMemberDept(ChangeMemberDept changeMemberDept);
	/**
	 * 
	 * <p>
	 * 根据工作审批结果进行联系人归属部门变更业务逻辑处理<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-5
	 * @param appId 
	 * @param workFLowId 
	 * @param examineResult
	 * void
	 */
	public void disposeChangeMemberDeptByExamineResult(String memberId,String workFLowId,boolean examineResult);
	
	/**
	 * 
	 * <p>
	 * 查询会员变更的工作流审批试图<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-8
	 * @param workFlowId
	 * @return
	 * ChangeMemberDeptView
	 */
	public ChangeMemberDeptView getChangeMemberDeptView(long workFlowId);
	/**
	 * 
	 * <p>
	 * 删除（作废）散客升级列表数据<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-8
	 * @param workFlowId
	 * @return
	 * ChangeMemberDeptView
	 */
	public void deleteScatterUpgradeById(String upGradeCustId);
	
	/**
	 * 
	 * <p>
	 * Description:通过固话+姓名获得固定客户详细信息<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-11-5
	 * @param tel 固定电话
	 * @param name 姓名
	 * @return
	 * Member
	 */
	public Member getMemberInfoByTelAndName(String tel,String name);
	
	/**
	 * <p>
	 * Description:提供给创建特殊会员调用，校验商业登记证或者税务登记证<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-24
	 * @param location
	 * @param enterpriseMember
	 * @return boolean
	 */
	public boolean checkTaxregNumberCanUseForSpecialMember(String location,
			String enterpriseMember);
	
	/**
	 * 
	 * <p>
	 * Description:更改固定客户和散客财务冻结字段<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.1 2012-11-5
	 * @return
	 * Member
	 */
	public void changeCustFinStatus();

	/**
	 * <p>
	 * Description:创建渠道客户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @param cust
	 * void
	 * @return 
	 */
	Map<String, String> createChannelCutomer(ChannelCustomer cust);

	/**
	 * <p>
	 * Description:修改渠道客户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @param cust
	 * void
	 */
	String updateChannelCustomer(ChannelCustomer cust);

	/**
	 * <p>
	 * Description:启动官网客户修改工作流<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * void
	 */
	void geneCustomerUpdateWorkflow();

	/**
	 * <p>
	 * Description:获得该部门一年没有开发计划的散客<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-14
	 * @param date
	 * @param dept
	 * @return
	 * List<ScatterCustomer>
	 */
	List<Member> getNotDevpPlanAndSysmakelsLoseList(Date date,
			String dept,String custType);

	/**
	 * <p>
	 * Description:获得一年没有开发计划散客的部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-14
	 * @param date
	 * @return
	 * List<String>
	 */
	List<String> getNotDevpPlanAndSysmakelsLoseDeptList(Date date,String custType);

	/**
	 * 
	* @Title: createPotentialCustomer
	* @Description: 手动创建客户 默认潜客
	* @author chenaichun 
	* @param @param member
	* @param @return    设定文件
	* @date 2014-3-20 上午11:45:53
	* @return void    返回类型
	* @throws
	* @update 2014-3-20 上午11:45:53
	 */
	void createPotentialCustomer(Member member);
	/**
	 * @Descript 得到失效一年的潜散客 部门
	 * @author 李学兴
	 * @version 0.1 2012-3-1
	 * @param 无
	 * @return 散客部门集合
	 */
	public List<String> getScatterCustDeptLoselsAndOneYear(
			Date date,String custType);
	/**
	 * @Descript 得到部门中失效一年的散客
	 * @author 李学兴
	 * @version 0.1 2012-5-25
	 * @param 无
	 * @return 散客集合
	 */
	public List<Member> getScatterCustLoselsAndOneYear(
			Date date,String deptId,String custType);
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
	* @Title: checkEnterpriseTaxregNumber
	* @Description:  企业用户 税务登记号 必填
	* @author chenaichun 
	* @param @param member
	* @param @return    设定文件
	* @date 2014-3-24 下午5:11:24
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 下午5:11:24
	 */
	boolean checkEnterpriseTaxregNumber(Member member);

	/**
	 * 
	* @Title: validateCreateMember
	* @Description: 校验创建客户业务
	* @author chenaichun 
	* @param @param member
	* @param @return    设定文件
	* @date 2014-3-24 下午5:23:53
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 下午5:23:53
	 */
	boolean validateCreateMember(Member member);

	/**
	 * 
	* @Title: searchMemberExtendByID
	* @Description: 根据客户ID查询客户扩展信息
	* @author chenaichun 
	* @param @param custId
	* @param @return    设定文件
	* @date 2014年3月27日 下午2:32:24
	* @return MemberExtend    返回类型
	* @throws
	* @update 2014年3月27日 下午2:32:24
	 */
	public MemberExtend searchMemberExtendByID(String custId);

	/**
	 * 
	* @Title: searchMemberByCondition
	* @Description: 根据查询条件查询客户信息
	* @author chenaichun 
	* @param @param memberCondition
	* @param @return    设定文件
	* @date 2014年3月31日 上午10:00:10
	* @return List<Member>    返回类型
	* @throws
	* @update 2014年3月31日 上午10:00:10
	 */
	List<Member> searchMemberByCondition(MemberCondition memberCondition);

	/**
	 * <p>
	 * Description:散客升级<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-3
	 * @param member
	 * void
	 */
	public void upgradeScatter(Member member);
	/**
	 * 
	* @Title: createPotentialMember
	* @Description: 创建潜客，提供给“陌生来电和订单揽货失败” 使用（member.getChannalSource()字段必须有值）
	* @author chenaichun 
	* @param @param member    设定文件
	* @date 2014年4月8日 上午9:09:20
	* @return void    返回类型
	* @throws
	* @update 2014年4月8日 上午9:09:20
	 */
	public void createPotentialMember(Member member);
	/**
	 * 
	 * <p>
	 * Description:批量导入潜客<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月10日
	 * @param excel
	 * @return
	 * String
	 */
	String importPotentialCustomer(File excel);
	/**
	 * 
	 * <p>
	 * Description:理赔生成散客<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月10日
	 * @param mobile
	 * @param phone
	 * @param linkManName
	 * @param deptId
	 * @param custNumber
	 * @return
	 * String
	 */
	String getNumberForRecompense(String mobile, String phone, String linkManName, String deptId, String custNumber,FossWaybillInfo info,String leaveOrArrive);

	/**
	 * 
	* @Title: createScatterCustomer
	* @Description: foss创建散客（根据散客编码创建）
	* @author chenaichun 
	* @param @param cust
	* @param @return    设定文件
	* @date 2014年4月10日 下午3:03:33
	* @return Member    返回类型
	* @throws
	* @update 2014年4月10日 下午3:03:33
	 */
	public String createScatterCustomer(ChannelCustomer cust);


	/**
	 * <p>
	 * Description:散客散客升级列表数据<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-9
	 * @param custNumber
	 * void
	 */
	void deleteUpGradeCustomerData(String custNumber);
	/**
	 * <p>
	 * Description:根据部门id获得到达客户<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-9
	 * void
	 */
	List<MemberResult> searchArrivalScatterCustByDept(String deptId);
	/**
	 * <p>
	 * Description:更新客户营销权限部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-9
	 * void
	 */
	void updateCustomerMarktingDept(CustomerMarktingDept dept);
	/**
	 * <p>
	 * Description:回收营销权限<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-9
	 * void
	 */
	void recoverScatterMarktingDeptByDay(int day);
	/**
	 * <p>
	 * Description:到达散客第一次发货，更新其归属部门及营销权限部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * void
	 */
	void dispartureSatterMarktingDept();
	/**
	 * 
	* @Title: createMemberExtendInfo
	* @Description: 插入客户扩展信息 营销使用
	* @author chenaichun 
	* @param @param memberExtend
	* @param @return    设定文件
	* @date 2014年4月18日 下午5:31:00
	* @return MemberExtend    返回类型
	* @throws
	* @update 2014年4月18日 下午5:31:00
	 */
	public MemberExtend createMemberExtendInfo(MemberExtend memberExtend);
	
	/**
	 * 
	 * @Title: updateDevelopmentLog
	 *  <p>
	 * @Description: 保存开发阶段日志
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 * @return void
	 * @throws
	 */
	public void updateDevelopmentLog(DevelopmentLog developmentLog);
	
	/**
	 * 
	 * @Title: updateExtendAndLog
	 *  <p>
	 * @Description: 保存客户扩展信息以及日志
	 * 	说明：根据业务，固定客户修改、非纯到达散客修改都不调用此方法，
	 * 		此方法仅适用于：潜客新增、潜客修改、新增固定客户、新增散客、纯到达散客修改、散客升级、营销模块修改客户扩展信息
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-7
	 * @return void
	 * @throws
	 */
	public void updateExtendAndLog(Member member,MemberExtend memberExtend,String operationType);
	/**
	 * 
	* @Title: createMemberExtendAndDevelopment
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author chenaichun 
	* @param @param member    设定文件
	* @date 2014年5月12日 下午7:26:42
	* @return void    返回类型
	* @throws
	* @update 2014年5月12日 下午7:26:42
	 */
	public void createMemberExtendAndDevelopment(Member member);
	/**
	 * 
	* @Title: canRealTimeCreateMember
	* @Description: 判断是否能实时创建固定客户
	* @author chenaichun 
	* @param @param phone
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014年5月16日 下午4:26:06
	* @return boolean    返回类型
	* @throws
	* @update 2014年5月16日 下午4:26:06
	 */
	public boolean canRealTimeCreateMember(String phone, String tel, String name);
	
	/**
	 * 
	 * @Title: saveBusinessOpportunity
	 *  <p>
	 * @Description:  修改保存商机阶段
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-20
	 * @return void
	 * @throws
	 */
	void saveBusinessOpportunity(Member member);
}
