package com.deppon.crm.module.customer.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.BoOperationLog;
import com.deppon.crm.module.customer.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept;
import com.deppon.crm.module.customer.shared.domain.DevelopmentLog;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.foss.framework.service.IService;

public interface IMemberService extends IService{
	
	/**
	 * <p>
	 * Description:保存会员<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param member
	 * void
	 */
	public void createMember(Member member);

	/**
	 * <p>
	 * Description:根据税务登记证统计会员的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
	 * @return
	 * String
	 */
	public String getMemberIdUseSeq();

	/**
	 * <p>
	 * Description:根据会员ID得到会员信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberId
	 * @return
	 * Member
	 */
	public Member getMemberById(String memberId);

	/**
	 * <p>
	 * Description:根据会员ID,操作人修改会员 联系人 账户状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param status
	 * @param memberId
	 * @param modifyUser
	 * void
	 */
	public void updateMemberStauts(String status, String memberId,String modifyUser);

	/**
	 * <p>
	 * Description:根据ID，当前操作人 修改散客升级列表的备注<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param upGradeCustomerId
	 * @param remark
	 * @param modifyUser
	 * void
	 */
	public void addUpGradeCustomerRemark(String upGradeCustomerId, String remark,String modifyUser);

	/**
	 * <p>
	 * Description:通过ID得到散客升级列表信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
	 * @param condition
	 * @return
	 * int
	 */
	public int getCountUpGradeCustomerByUpGradeCustomerCondition(
			UpGradeCustomerCondition condition);
	/**
	 * <p>
	 * 得到会员实际等级<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param upGrade
	 * @return
	 * String
	 */
	public List<String> getMemberTargetLevel(UpGradeCustomer upGrade);

	/**
	 * <p>
	 * Description:根据手机号码 删除散客列表对应的值<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param phone
	 * void
	 */
	public void deleteUpGradeCustomerByNumber(String phone);

	/**
	 * <p>
	 * 删除会员信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param memberId
	 * void
	 */
	public void deleteMemberById(String memberId);
	
	/**
	 * <p>
	 * 修改会员等级<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-5
	 * @param targetLevel
	 * @param memberNumber
	 * void
	 */
	public void updateMemberLevelByMemberNumber(String targetLevel,
			String memberNumber,String modifyUser);
	/**
	 * <p>
	 * 根据查询条件组合查询会员升级信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-5
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
	 * 根据统计时间得到会员升级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param statisticsTime
	 * @return
	 * List<MemberUpgradeList>
	 */
	public List<MemberUpgradeList> getMemberUpgraeByStatisticsTime(
			String statisticsTime);
	/**
	 * <p>
	 * 根据统计时间得到会员降级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param statisticsTime
	 * @return
	 * List<MemberDemotionList>
	 */
	public List<MemberDemotionList> getDemotionMemberByStatisticsTime(
			String statisticsTime);
	/**
	 * <p>
	 * 分页查询会员降级列表<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
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
	 * 根据查询条件得到会员升级列表的总记录数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param condition
	 * @return
	 * int
	 */
	public int getCountMemberUpgradeListByCondition(
			UpGradeCustomerCondition condition);
	/**
	 * <p>
	 * 根据查询条件得到会员降级列表的总记录数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-6
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
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
	 * @param memberId
	 * @param monthlyStatement
	 * @param modifyUser
	 * void
	 */
	public void updateMothlyStatementByMemberId(String memberId, double monthlyStatement,String modifyUser);

	/**
	 * <p>
	 * Description:未知用途表。正式上此表没有数据<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberId
	 * @param monthlyStatement
	 * void
	 */
	public void insertMothlyStatement(String memberId, double monthlyStatement);
	
	/**
	 * <p>
	 * 根据条件统计联系人的个数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-4
	 * @param idCard 身份证
	 * @param custType 客户类型
	 * @param isMain 是否主联系人
	 * @return
	 * int
	 */
	public int countContactByCondition(String cardTypeCon,String idCard, String custType,
			boolean isMain);
	/**
	 * <p>
	 * 统计联系人编码<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-15
	 * @param linkManNumber
	 * @return
	 * int
	 */
	public int countContactByLinkManNumber(String linkManNumber);
	
	/**
	 * @作者：吴根斌
	 * @时间：2012-8-2
	 * @描述：根据个人证件查出客户编号和客户部门信息
	 * @参数：cardTypeCon 证件类型；idCard 证件号；custType 客户类型；isMain 是否是主要联系人
	 * @返回值：List 客户编号和客户类型
	 */
	public List<Map<String, String>> getMemberListByPersonalcardNumber(String cardTypeCon, String idCard, String custType, boolean isMain);
	
	/**
	 * <p>
	 * Description:根据联系人编码 得到客户的部门名字和客户编码
	 * department.fdeptname depart,customer.fcustnumber cust <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param linkManNumber
	 * @return
	 * List<Map<String,String>>
	 */
	List<Map<String, String>> getMemberListByLinkManNumber(String linkManNumber);

	/**
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据客户名称和电话号码查找客户部门以及客户编码
	 * @参数：custName 客户名称；telephone 客户电话
	 * @返回值：List 客户部门以及客户编码列表
	 */
	public List<Map<String,String>> getMemberListByCustnameAndContact(String custName,String telephone);
	
	/**
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据手机号码查找客户所在部门以及客户编码
	 * @参数：mobilephone 手机号码
	 * @返回值：List 客户部门以及客户编码列表
	 */
	public List<Map<String,String>> getMemberListByCellphone(String mobilephone);
	
	/**
	 * @作者：吴根斌
	 * @时间：2012-8-6
	 * @描述：根据企业客户税务登记号来获取客户所在部门以及客户编码
	 * @参数：taxregnumber 税务登记号
	 * @返回值：List 客户所在部门以及客户编码
	 */
	public List<Map<String, String>> getMemberListByTaxregNumber(
			String taxregnumber);
	/**
	 * @作者：李学兴
	 * @时间：2012-9-13
	 * @描述：通过id删除（作废）散客升级列表散客信息，支持批量作废
	 * @参数：upGradeCustId 散客id
	 * @返回值：void
	 */
	public void deleteScatterUpgradeById(String upGradeCustId);
	public String getCustWorkFlow(String workFlow);
	/**
	 * 
	 * @Title: queryCustByCustNumber
	 *  <p>
	 * @Description: 通过固定客户编码查询客户信息
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-6-2
	 * @return boolean
	 * @throws
	 */
	public boolean queryMemberByCustNumber(String custNumber);

	/**
	 * <p>
	 * Description:1,查询需要产生工作流的官网用户修改信息</br>
	 * 2,查询出来这部分 客户信息不能处于审批中，如果处于审批中，不用查询出来</br>
	 * 3,查询出来的手机号不能处于审批被锁定状态，固定电话+姓名也如此</br>
	 * 4,查询结果直接用于启动修改工作流
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @return
	 * List<OwsCustomer>
	 */
	public List<ChannelCustomer> searchNeedWorkflowUpdateCustomer();

	/**
	 * <p>
	 * Description:删除已经启动过修改工作流的暂存OWS用户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-5
	 * @param needDeleteIds
	 * void
	 */
	public void deleteUpdatedWorkflowCustomer(List<String> needDeleteIds);

	/**
	 * <p>
	 * Description:根据官网用户名到客户工作流修改暂存表中查询集合<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-7
	 * @param custName
	 * @return
	 * List<ChannelCustomer>
	 */
	public List<ChannelCustomer> searchChannelCustomerByUserName(String custName);

	/**
	 * <p>
	 * Description:更新渠道客户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-7
	 * @param cust
	 * void
	 */
	public void updateChannelCustomer(ChannelCustomer cust);

	/**
	 * <p>
	 * Description:根据官网用户名查询客户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-7
	 * @param custName
	 * @return
	 * Member
	 */
	public Member searchMemberByUserName(String custName);

	/**
	 * <p>
	 * Description:暂存OWS用户客户信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param cust
	 * void
	 */
	public void saveChannelCustomer(ChannelCustomer cust);

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
	 * @Descript 得到失效一年的散客 部门
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
	* @Title: searchMemberExtendByID
	* @Description: 根据客户id查询客户扩展信息
	* @author chenaichun 
	* @param @param custId
	* @param @return    设定文件
	* @date 2014年3月27日 下午2:31:27
	* @return MemberExtend    返回类型
	* @throws
	* @update 2014年3月27日 下午2:31:27
	 */
	MemberExtend searchMemberExtendByID(String custId);

	/**
	 * <p>
	 * Description:根据客户id查询营销权限部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @param custId
	 * @return
	 * List<CustomerMarktingDept>
	 */
	public List<CustomerMarktingDept> searchCustomerMarktingDeptByCustId(
			String custId);

	/**
	 * <p>
	 * Description:创建客户营销部门<br />
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
	public void modifyMemberMarktingDept(CustomerMarktingDept dept);

	/**
	 * <p>
	 * Description:查询第一次发货散客<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-10
	 * @return
	 * List<CustomerMarktingDept>
	 */
	public List<CustomerMarktingDept> searchCustMarktingDeptByFirstShip();

	/**
	 * 
	* @Title: createMemberExtendInfo
	* @Description: 创建客户扩展信息
	* @author chenaichun 
	* @param @param memberExtend
	* @param @return    设定文件
	* @date 2014年4月18日 下午5:28:43
	* @return MemberExtend    返回类型
	* @throws
	* @update 2014年4月18日 下午5:28:43
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
	 * @Title: queryDevelopmentLog
	 *  <p>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-5-8
	 * @return void
	 * @throws
	 */
	public List<Map<String, String>> queryDevelopmentLog(String custId);

	/**
	 * 
	* @Title: searchMemberByConstactWay
	* @Description: 根据联系方式查询客户基本信息
	* @author chenaichun 
	* @param @param mobilePhone
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014年5月16日 上午8:14:30
	* @return List<Member>    返回类型
	* @throws
	* @update 2014年5月16日 上午8:14:30
	 */
	public List<Member> searchMemberByConstactWay(String mobilePhone, String tel,
			String name);

	/**
	 * 
	 * @Title: queryBusinessOpportunityById
	 *  <p>
	 * @Description: 获取制定商机信息
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
	 * @Description: 保存商机状态
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
