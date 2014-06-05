package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.marketing.server.manager.ICustomerCallManager;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager;
import com.deppon.crm.module.marketing.server.manager.IReturnVisitManager;
import com.deppon.crm.module.marketing.server.service.IReturnVisitService;
import com.deppon.crm.module.marketing.server.utils.CustomerCallInValidateUtils;
import com.deppon.crm.module.marketing.server.utils.UserInfoUtils;
import com.deppon.crm.module.marketing.shared.domain.CCPushMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCPushPlanInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfoDetail;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingRecord;
import com.deppon.crm.module.marketing.shared.domain.CustomerCallVO;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * 客户来电录入操作实现类<br />
 * </p>
 * @title CustomerCallManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author 苏玉军
 * @version 0.1 2013-1-29
 */
public class CustomerCallManager implements ICustomerCallManager {

	// Description:固定客户的manager
	private IMemberManager memberManager;

	// 部门manager
	private ILadingstationDepartmentManager ladDeptManager;

	//营销manager
	private IReturnVisitManager returnVisitManager;
	//部门管理service
	private IDepartmentService departmentService;
	//IEmployeeService
	private IEmployeeService employeeService;
	//Userser
	private IUserService userService;
	//returnvisit service
	private IReturnVisitService returnVisitService;
	//问题管理Manager
	private IQuestionnaireManager questionnaireManager;
	//计划管理
	private IPlanManager planManager;
	
	/**
	 * @param questionnaireManager : set the property questionnaireManager. 
	 */
	public void setQuestionnaireManager(IQuestionnaireManager questionnaireManager) {
		this.questionnaireManager = questionnaireManager;
	}

	/**
	 * @return memberManager : return the property memberManager.
	 */
	public IMemberManager getMemberManager() {
		return memberManager;
	}

	/**
	 * @param memberManager : set the property memberManager.
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * @return ladDeptManager : return the property ladDeptManager.
	 */
	public ILadingstationDepartmentManager getLadDeptManager() {
		return ladDeptManager;
	}

	/**
	 * @param ladDeptManager : set the property ladDeptManager.
	 */
	public void setLadDeptManager(ILadingstationDepartmentManager ladDeptManager) {
		this.ladDeptManager = ladDeptManager;
	}

	/**
	 * @return returnVisitManager : return the property returnVisitManager.
	 */
	public IReturnVisitManager getReturnVisitManager() {
		return returnVisitManager;
	}

	/**
	 * @param returnVisitManager : set the property returnVisitManager.
	 */
	public void setReturnVisitManager(IReturnVisitManager returnVisitManager) {
		this.returnVisitManager = returnVisitManager;
	}

	/**
	 * @param departmentService : set the property departmentService. 
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * @param employeeService : set the property employeeService. 
	 */
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @param 
 : set the property userService. 
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @param returnVisitService : set the property returnVisitService. 
	 */
	public void setReturnVisitService(IReturnVisitService returnVisitService) {
		this.returnVisitService = returnVisitService;
	}

	/**
	 * @param planManager : set the property planManager. 
	 */
	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}

	/**
	 * @描述 根据手机号码和部門id去查潜散客和固定客户
	 * @作者 钟琼
	 * @时间 2012-11-05
	 * @参数 手机号码、部门id
	 * @返回值 潜散客实体
	 */
	@Override
	public CustomerCallVO queryCustomerByMobileAndDeptId(String mobile,
			String deptId) {
		// 固定客户实体
		Member member = null;
		// 转换为前台显示的实体
		CustomerCallVO customerCallVO = null;
		// 用户输入手机号码后，先查询存不存在固定客户
		if (StringUtils.isNotEmpty(mobile)) {
			//查询固定客户
			member = memberManager.getMemberByPhone(mobile);
		}
		if (member != null) {
			//固定客户不为空，装换为前台视图对象
			customerCallVO = changeMemberView(member);
			//返回数据
			return customerCallVO;
		}
		//手机号码、部门ID不为空
		if (StringUtils.isNotEmpty(mobile) && StringUtils.isNotEmpty(deptId)) {
			MemberCondition condition = new MemberCondition();
			condition.setMobile(mobile);
			condition.setDeptId(deptId);
			//查询潜散客信息
			List<Member> results = memberManager.searchMemberByCondition(condition);
			if(!results.isEmpty()){
				member = results.get(0);
			}
		}
		if (null != member) {
			// 如果存在此潜散客，就将客户实体进行转换，以便前台显示
			customerCallVO = changeMemberView(member);
			//返回
			return customerCallVO;
		}
		//都为查询到，返回NULL
		return null;

	}

	/**
	 * @描述 根据电话号码、联系人名字和部门id去查潜散客和固定客户
	 * @作者 钟琼
	 * @时间 2012-11-05
	 * @参数 电话号码、联系人名字、部门id
	 * @返回值 查潜散客实体
	 */
	@Override
	public CustomerCallVO queryCustomerByPhoneDeptIdName(String phone,
			String linkName, String deptId) {
		// Description:固定客户实体
		Member member = null;
		// Description:转换为前台显示的实体
		CustomerCallVO customerCallVO = null;
		/*
		 * 用户输入手机号码后，经过查询不存在此客户 然后输入固话和联系人名字，再查询是否存在此固定用户
		 */
		if (StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(linkName)) {
			//联系人姓名 +　联系电话查询固定客户
			member = memberManager.getMemberInfoByTelAndName(phone, linkName);
		}
		//固定客户
		if (member != null) {
			//转换为前台显示
			customerCallVO = changeMemberView(member);
			//返回
			return customerCallVO;
		}
		//联系人姓名 + 电话 + 部门ID查询潜客 散客
		if (StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(linkName)
				&& StringUtils.isNotEmpty(deptId)) {
			//查询潜散客信息
			MemberCondition condition = new MemberCondition();
			condition.setTelePhone(phone);
			condition.setLinkManName(linkName);
			condition.setDeptId(deptId);
			List<Member> results = memberManager.searchMemberByCondition(condition);
			if(!results.isEmpty()){
				member = results.get(0);
			}
		}
		if (null != member) {
			// 如果存在此潜散客，就将客户实体进行转换，以便前台显示
			customerCallVO = changeMemberView(member);
			//返回潜散客信息
			return customerCallVO;
		}
		//均为查询到，返回null
		return null;

	}

	/**
	 * <p>
	 * 根据当前登录用户查找部门所在城市<br/>
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-7
	 * @param user
	 *            当前登录用户
	 * @return Department 部门对象
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#queryDepartmentWithCurrentUser(com.deppon.crm.module.authorization.shared.domain.User)
	 */
	@Override
	public BussinessDept queryDepartmentWithCurrentUser(User user) {
		//部门ID
		String deptId = user.getEmpCode().getDeptId().getId();
		//部门对象
		BussinessDept dept = ladDeptManager.getBusDeptByDeptId(deptId);
		//不为空并且是叶子节点，返回
		if (dept != null && dept.getIfLeave()) {
			//返回结果
			return dept;
		}
		//否则返回null
		return null;
	}

	/**
	 * <p>
	 * Description:将固定客户实体进行封装<br />
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2012-11-6
	 * @param member
	 * @return CustomerCallVO
	 */
	private CustomerCallVO changeMemberView(Member member) {
		//声明结果
		CustomerCallVO customerCallVO = new CustomerCallVO();
		//客户id
		customerCallVO.setId(member.getId());
		//创建人
		customerCallVO.setCreateUser(member.getCreateUser());
		//联系手机
		customerCallVO.setContactMobile(((Contact) member.getContactList().get(0)).getMobilePhone());
		//联系电话
		customerCallVO.setContactPhone(((Contact) member.getContactList().get(0)).getTelPhone());
		//联系人姓名
		customerCallVO.setCustLinkManName(((Contact) member.getContactList().get(0)).getName());
		//客户姓名
		customerCallVO.setCustName(member.getCustName());
		//联系人职务
		customerCallVO.setContactJob(((Contact) member.getContactList().get(0)).getDuty());
		//联系人行业
		customerCallVO.setContactTrade(member.getTradeId());
		//二级行业
		customerCallVO.setSecondTrade(member.getSecondTrade());
		//业务状态
		customerCallVO.setBusStatus(null);
		//联系城市
		customerCallVO.setContactCity(member.getCity());
		//联系地址
		customerCallVO.setContactAddress(member.getRegistAddress());
		//联系类型
		customerCallVO.setContactType(member.getCustGroup());
		//联系人ID
		customerCallVO.setCustLinkManId(((Contact) member.getContactList().get(0)).getId());
		//返回结果
		return customerCallVO;
	}


	/**
	 * 
	 * <p>
	 * 点击保存后进行营销数据处理<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-7
	 * @param vo
	 *            页面数据封装
	 * @param optionList
	 *            客户意见列表
	 * @param volPotentialList
	 * @param user
	 * @return boolean：成功 false：失败
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#processMarketData(com.deppon.crm.module.marketing.shared.domain.CustomerCallVO,
	 *      java.util.List, java.util.List,
	 *      com.deppon.crm.module.authorization.shared.domain.User)
	 */
	@Override
	@Transactional
	public boolean processMarketData(CustomerCallVO vo,
			List<ReturnVisitOpinion> optionList,
			List<ReturnVisitVolumePotential> volPotentialList, User user) {
		// 1、客户端数据校验
		CustomerCallInValidateUtils.validateVo(vo);
		// 2、客户意见校验
		CustomerCallInValidateUtils.validateCustOptions(optionList);
		// 3、根据客户Id判断客户是否存在
		if (StringUtils.isEmpty(vo.getId())) {
			// 4、客户不存在，创建潜客
			Member member = this.generatePotentialCust(vo,user);
			//创建潜客
			memberManager.createPotentialMember(member);
			// 5、设置id
			vo.setId(member.getId());
			//6、设置客户类型为潜客
			vo.setContactType(MarketingConstance.POTENTIAL_CUSTOMER);
			//7、营销方式
			vo.setMarketingMothod(MarketingConstance.MARKETING_WAY_STRANGER);
			vo.setCustLinkManId(member.getContactList().get(0).getId());
		}
		//6、存在客户，直接保存营销记录
		ReturnVisit returnVisit = this.generateReturnVisit(vo, user);
		//7、保存营销记录
		returnVisitManager.createReturnvisit(returnVisit, optionList, volPotentialList,null,null, user);
		//返回执行结果
		return true;
	}

	/**
	 * 
	 * <p>
	 * 根据页面传过来的数据构造潜客信息
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-8
	 * @param vo
	 * @return PotentialCustomer 潜客
	 */
	private Member generatePotentialCust(CustomerCallVO vo, User user) {
		//潜客对象
		Member member = new Member();
		Contact contact = new Contact();
		MemberExtend extend = new MemberExtend();
		List<Contact> list = new ArrayList<Contact>();
		BussinessDept bizDept = this.queryDepartmentWithCurrentUser(user);
		// 联系人名称
		contact.setName(vo.getCustLinkManName());
		contact.setTelPhone(vo.getContactPhone());
		contact.setMobilePhone(vo.getContactMobile());
		contact.setIsMainLinkMan(true);
		contact.setLinkmanType("0,1,0,0,0");
		// 客户名称
		member.setCustName(vo.getCustName());
		contact.setDuty(vo.getContactJob());
		// 行业
		member.setTradeId(vo.getContactTrade());
		//二级行业
		member.setSecondTrade(vo.getSecondTrade());
		// 商机状态
		extend.setDevelopmentPhase(vo.getBusStatus());
		// 客户来源--陌生来电
		member.setChannelSource(Constant.CUST_SOURCE_STRANGER);
		//潜客部门
		member.setDeptId(UserInfoUtils.getCurrentDeptId(user));
		//创建人
		member.setCreateUser(UserInfoUtils.getCurrentUserId(user));
		//业务类别
		member.setCustCategory(vo.getBusinessType());
		//省份
		member.setProvinceId(bizDept.getProvince().getId());
		member.setProvince(bizDept.getProvince().getName());
		//城市
		member.setCityId(bizDept.getCity().getId());
		member.setCity(bizDept.getCity().getName());
		member.setChannelSource(Constant.CUST_SOURCE_STRANGER);
		//地址
		member.setRegistAddress(vo.getContactAddress());
		//返回
		list.add(contact);
		member.setContactList(list);
		member.setMemberExtend(extend);
		return member;
	}

	private ReturnVisit generateReturnVisit(CustomerCallVO vo, User user) {
		ReturnVisit returnVisit = new ReturnVisit();
		// 客户类型为潜客 散客
		if (MarketingConstance.POTENTIAL_CUSTOMER.equals(vo.getContactType())
				|| MarketingConstance.SCATTER_CUSTOMER.equals(vo.getContactType())) {
			//设置潜散客ID
//			returnVisit.setPsCustomerId(vo.getId());
			//设置回访类型
			returnVisit.setScheType(MarketingConstance.DEVELOP_TYPE);
		} else {
			//设置回访类型
			returnVisit.setScheType(MarketingConstance.MAINTAIN_TYPE);
		}
		// 客户类型为会员
		returnVisit.setMemberId(vo.getId());
		//设置联系人id
		returnVisit.setLinkManId(vo.getCustLinkManId());
		//联系人名称
		returnVisit.setLinkName(vo.getCustLinkManName());
		//联系人手机
		returnVisit.setLinkManMobile(vo.getContactMobile());
		//联系人电话
		returnVisit.setLinkManPhone(vo.getContactPhone());
		//营销方式为空
		if(StringUtils.isEmpty(vo.getMarketingMothod())){
			// 营销方式 -- 已有客户来电
			returnVisit.setWay(MarketingConstance.MARKETING_WAY_EXISTCUST);			
		}else{
			//设置vo中的方式
			returnVisit.setWay(vo.getMarketingMothod());
		}
		//设置营销时间
		returnVisit.setExecutorTime(new Date());
		//跟踪日程时间
		returnVisit.setSchedule(vo.getScheduleDate());
		//营销人
		returnVisit.setCreateUser(UserInfoUtils.getCurrentUserId(user));
		//修改人
		returnVisit.setModifyUser(UserInfoUtils.getCurrentUserId(user));
		//执行人
		returnVisit.setExecutorId(UserInfoUtils.getCurrentUserId(user));
		//执行部门ID 
		returnVisit.setExecuteDeptId(UserInfoUtils.getCurrentDeptId(user));
		//返回
		return returnVisit;
	}

	/**
	 * 
	 * <p>
	 * 营销历史查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param vo 前台页面封装
	 * @return Map<String,Object>  联系人对应客户下的所有联系人营销记录
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#queryMarketingHistory(com.deppon.crm.module.marketing.shared.domain.CustomerCallVO)
	 */
	@Override
	public List<String> queryMarketingHistory(CustomerCallVO vo,User user) {
		//1、验证vo
		CustomerCallInValidateUtils.validateCustIdAndType(vo);
		//2、取得客户Id 客户类型 联系人Id
		String custId = vo.getId();
		//客户类型
//		String custType = vo.getContactType();
		//查询结果
		Map<String,String> map = new HashMap<String, String>();
		//客户ID
		map.put("custId", custId);
		//客户类型
//		map.put("custType", custType);
		//3、根据客户Id获得所有联系人营销Id集合
		List<String> list = new ArrayList<String>();
		//查询营销历史
		list = returnVisitManager.queryRvListByCustId(map);
		//返回结果
		return list;
	}

	/**
	 * 
	 * <p>
	 * 分页查询客户意见<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return
	 * List<ReturnVisitOpinion>
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#queryRvOptionByMarketingIds(java.util.List, int, int)
	 */
	@Override
	public Map<String,Object> queryRvOptionByMarketingIds(CustomerCallVO vo,User user, int start, int limit) {
		//获得联系人对应客户下的所有营销id
		List<String> marketingIds = this.queryMarketingHistory(vo, user);
		//结果声明
		Map<String,Object> result = new HashMap<String, Object>();
		//查询列表定义
		List<ReturnVisitOpinionVo> list = new ArrayList<ReturnVisitOpinionVo>();
		//结果总数
		int count = 0;
		//结果列表不为空
		if(marketingIds != null && marketingIds.size() > 0){
			//根据营销id查询所有客户意见
			list = returnVisitManager.queryRvOptionByMarketingIds(marketingIds,start,limit);
			//计算总数
			count = returnVisitManager.countRvOptionByMarketingIds(marketingIds);
		}
		//封装列表
		result.put("data", list);
		//封装总数
		result.put("count", count);
		//返回结果
		return result;
	}

	/**
	 * 
	 * <p>
	 * 分页查询货量潜力<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return
	 * List<ReturnVisitVolumePotential>
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#queryRvPotentialByMarketingIds(java.util.List, int, int)
	 */
	@Override
	public Map<String,Object> queryRvPotentialByMarketingIds(CustomerCallVO vo,User user, int start, int limit) {
		//获得联系人对应客户下的所有营销id
		List<String> marketingIds = this.queryMarketingHistory(vo, user);
		//结果Map
		Map<String,Object> result = new HashMap<String, Object>();
		//查询列表
		List<ReturnVisitVolumePotentialVo> list = new ArrayList<ReturnVisitVolumePotentialVo>();
		//总数
		int count = 0;
		//营销ID不为空
		if(CollectionUtils.isNotEmpty(marketingIds)){
			////根据营销id查询所有所有的货量潜力
			list = returnVisitManager.queryRvPotentialByMarketingIds(marketingIds,start,limit);
			//查询总数
			count = returnVisitManager.countRvPotentialByMarketingIds(marketingIds);
		}
		//查询结果
		result.put("data", list);
		//查询总数
		result.put("count", count);
		//返回结果
		return result;
	}

	 /**
	  * 
	  * <p>
	  * CC营销信息推送保存<br />
	  * </p>
	  * @author 043260
	  * @version 0.1 2014年4月6日
	  * @param info
	  * @return
	  * boolean
	  */
	@Override
	public boolean addCCMarketingInfo(CCPushMarketingInfo info) throws GeneralException{
		boolean isSuccess = false;
		//如果推送对象为空，直接返回
		if(info == null){
			return isSuccess;
		}
		//回访主信息
		ReturnVisit returnVisit = new ReturnVisit();
		//回访意见
		ReturnVisitOpinion returnVisitOpinion = new ReturnVisitOpinion();
		//回访意见List
		List<ReturnVisitOpinion> list = new ArrayList<ReturnVisitOpinion>();
		//根据客户编码查询客户信息
		Member member = memberManager.getMemberByCustNumber(info.getCustNumber());
		//营销部门信息
		Department dept = departmentService.getDeptByStandardCode(info.getMarketDept());
		//emp
		Employee emp = employeeService.getEmpByCode(info.getMarketPerson());
		//user
		User user = userService.findByLoginName(info.getMarketPerson());
		/*********************回访信息*****************************/
		//设置回访客户ID
		returnVisit.setMemberId(member.getId());
		//设置联系人
		returnVisit.setLinkManId(member.getMainContact().getId());
		//设置联系人手机
		returnVisit.setLinkManMobile(info.getCellphone());
		//设置联系人固话
		returnVisit.setLinkManPhone(info.getTelephone());
		//set link man name
		returnVisit.setLinkName(member.getMainContact().getName());
		//set execute dept
		returnVisit.setExecuteDeptId(dept.getId());
		//set execute person
		returnVisit.setExecutorId(emp.getId());
		//set returnvisit way
		returnVisit.setWay(MarketingConstance.RETURNVISIT_WAY_PHONE);
		//set marketing time
		returnVisit.setExecutorTime(info.getMarketTime());
		// set create user id
		returnVisit.setCreateUser(emp.getId());
		
		/********************回访意见信息***********************/
		//set execute user id
		returnVisitOpinion.setCreateUser(emp.getId());
		// set need type
		returnVisitOpinion.setOpinionType(info.getNeedType());
		// set problem
		returnVisitOpinion.setProblem(info.getNeedQuestion());
		// set solultion
		returnVisitOpinion.setSolution(info.getSolution());
		// set to list
		list.add(returnVisitOpinion);
		isSuccess = returnVisitManager.createReturnvisit(returnVisit, list, null, null, null, user);
		return isSuccess;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#queryMarketingInfoToCC(java.lang.String, int, int)
	 */
	@Override
	public CCQueryMarketingInfo queryMarketingInfoToCC(String custNumber,
			int start, int limit) throws GeneralException{
		//查询条件
		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
		//返回实体声明
		CCQueryMarketingInfo info = new CCQueryMarketingInfo();
		condition.setCustNumber(custNumber);
		//根据客户编码查询回访记录
		List<ReturnVisit> list = returnVisitService.getReturnVisitList(condition, start, limit);
		//回访记录总数
		int count = returnVisitService.getReturnVisitCount(condition);
		List<CCQueryMarketingRecord> records = new ArrayList<CCQueryMarketingRecord>();
		CCQueryMarketingRecord record = null;
		//回访信息转换为接口使用
		for(ReturnVisit rv : list){
			record = new CCQueryMarketingRecord();
			//客户ID
			record.setCustId(rv.getMemberId());
			//联系人名称
			record.setLinkMan(rv.getLinkName());
			//营销方式,整数类型0、电话回访；1、上门拜访；
			record.setMarketingMode(rv.getWay());				
			//营销人
			record.setMarketingPerson(rv.getUserName());
			//需求类型
			if(StringUtils.isNotEmpty(rv.getNeedType())){
				record.setMarketingType("NO_QUE");				
			}else{
				record.setMarketingType("QUE");
			}
			//计划主题
			record.setPlanningTopic(rv.getTheme());
			//回访ID
			record.setRecallId(rv.getId());
			//营销时间
			record.setMarketingTime(rv.getExecutorTime());
			records.add(record);
		}
		info.setRecords(records);
		info.setCount(count);
		//返回对象
		return info;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#queryMarketingInfoDetailToCC(java.lang.String, java.lang.String)
	 */
	@Override
	public CCQueryMarketingInfoDetail queryMarketingInfoDetailToCC(
			String custId, String returnVisitId) throws GeneralException {
		CCQueryMarketingInfoDetail ccQueryMarketingInfoDetail = new CCQueryMarketingInfoDetail();
		ccQueryMarketingInfoDetail.setDto(questionnaireManager.searchQuestionnaireByVisitId(returnVisitId));
		List<String> marketingIds = new ArrayList<String>();
		marketingIds.add(returnVisitId);
		List<ReturnVisitOpinionVo>  OpinionList = returnVisitManager.queryRvOptionByMarketingIds(marketingIds, 0, 10000);
		List<ReturnVisitVolumePotentialVo>  volumePotentials = returnVisitManager.queryRvPotentialByMarketingIds(marketingIds, 0, 10000);
		ccQueryMarketingInfoDetail.setOpinions(OpinionList);
		ccQueryMarketingInfoDetail.setPotentials(volumePotentials);
		return  ccQueryMarketingInfoDetail;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICustomerCallManager#addCCPlanInfo(com.deppon.crm.module.marketing.shared.CCPushPlanInfo)
	 */
	@Override
	public String addCCPlanInfo(CCPushPlanInfo planInfo)
			throws GeneralException {
		String result = "";
		Plan plan = new Plan();
		Department dept = departmentService.getDeptByStandCode(planInfo.getExecuteDept());
		Member member = memberManager.getMemberByCustNumber(planInfo.getCustNumber());
		User user = userService.findByLoginName(planInfo.getCreatorCode());
		Employee creator = employeeService.getEmpByCode(planInfo.getCreatorCode());
		Employee manager = employeeService.getEmpByCode(dept.getPrincipal());
		user.setEmpCode(creator);
		plan.setBeginTime(planInfo.getPlanStartTime());
		plan.setEndTime(planInfo.getPlanEndTime());
		plan.setTopic(planInfo.getPlanTheme());
		plan.setExecdeptid(dept.getId());
		plan.setExecuserid(manager.getId());
		plan.setProjectType(planInfo.getProjectType());
		if(MarketingConstance.REGULAR_CUSTOMER.equals(member.getCustGroup())){
			plan.setPlanType(MarketingConstance.MAINTAIN_TYPE);
		}else{
			plan.setPlanType(MarketingConstance.DEVELOP_TYPE);
		}
		result  = planManager.createPlan(plan, new String[]{member.getId()}, new String[]{member.getContactId()}, user);
		return result;
	}
}
