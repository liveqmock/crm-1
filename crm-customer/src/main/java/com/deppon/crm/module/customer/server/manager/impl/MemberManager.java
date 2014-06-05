package com.deppon.crm.module.customer.server.manager.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.customer.domain.CustomerCancel;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.common.file.util.ExcelReader;
import com.deppon.crm.module.common.file.util.IExcelReader;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.server.manager.CustomerRule;
import com.deppon.crm.module.customer.server.manager.CustomerValidator;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IChangeMemberDeptManager;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberWorkFlowManager;
import com.deppon.crm.module.customer.server.manager.MemberEffectValidate;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IApprovingWokflowDataService;
import com.deppon.crm.module.customer.server.service.IContactService;
import com.deppon.crm.module.customer.server.service.IExamineRecordService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.MemberUtil;
import com.deppon.crm.module.customer.server.utils.PontentialCustToMemberAdpater;
import com.deppon.crm.module.customer.server.utils.PotentialCustomerExcelHelper;
import com.deppon.crm.module.customer.server.utils.ScatterCustomerUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.crm.module.customer.shared.domain.BoOperationLog;
import com.deppon.crm.module.customer.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.customer.shared.domain.Certification;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDeptView;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.CustomerMarktingDept;
import com.deppon.crm.module.customer.shared.domain.DevelopmentLog;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.ImplementMemberView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.MemberExaminView;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.QualificationView;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.crm.module.customer.shared.domain.WayBillInfo;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.customer.shared.exception.ScatterException;
import com.deppon.crm.module.customer.shared.exception.ScatterExceptionType;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * <p>
 * Description:客户管理<br />
 * </p>
 * @title MemberManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl 
 * @author 106138
 * @version 0.1 2014年4月14日
 */
@Transactional
public class MemberManager implements IMemberManager {
	//日志
	private Logger log = Logger.getLogger(MemberManager.class);
	//客户service
	private IMemberService memberService;
	//会员修改service
	private IAlterMemberService alterMemberService;
	//联系人service
	private IContactService contactService;
	//审批serivce
	private IExamineRecordService examineRecordService;
	//会员工作流管理
	private IMemberWorkFlowManager memberWorkFlowManager;
	//合同管理
	private IContractManager contractManager;
	//归属部门变更管理
	private IChangeMemberDeptManager changeMemberDeptManager;
	//基础数据
	private IBaseDataManager baseDataManager;
	//审批工作流数据
	private IApprovingWokflowDataService approvingWokflowDataService;
	//客户标签
	private ICustLabelManager custLabelManager;
	//网点管理
	private ILadingstationDepartmentManager departmentManager;
	//客户操作
	private ICustomerOperate customerOperate;
	//联系人管理
	private IContactManager contactManager;
	//疑重manager
	private IRepeatedCustManager repeatedCustManager;
	//部门管理
	private IDepartmentService departmentService;
	//国际化
	private IMessageBundle messageBundle;
	// foss运单接口
	private IWaybillOperate waybillOperate;
	
	/**
	 * 
	 * <p>
	 * Description:根据手机号码查询客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param phone
	 * @return
	 *
	 */
	@Override
	public Member getMemberByPhone(String phone) {
		//封装查询条件
		MemberCondition condition = new MemberCondition();
		//设置查询条件
		condition.setMobile(phone);
		//进行查询
		List<Member> list = alterMemberService
				.searchMemberByCondition(condition);
		//为空返回空
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:根据电话查询客户列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param tel
	 * @return
	 *
	 */
	@Override
	public List<Member> searchMemberByTel(String tel) {
		//封装条件
		MemberCondition condition = new MemberCondition();
		//电话
		condition.setTelePhone(tel);
		//直接返回列表
		return alterMemberService.searchMemberByCondition(condition);
	}
	/**
	 * 
	 * <p>
	 * Description:根据客户名称查询客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param name
	 * @return
	 *
	 */
	@Override
	public List<Member> searchMemberByName(String name) {
		//封装条件
		MemberCondition condition = new MemberCondition();
		condition.setCustName(name);
		//直接返回列表
		return alterMemberService.searchMemberByCondition(condition);
	}
	/**
	 * 
	 * <p>
	 * Description:删除手机对应的散客升级列表数据<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param custNumber
	 *
	 */
	@Override
	public void deleteUpGradeCustomerData(String custNumber) {
		Assert.notNull(custNumber, "upGradeCustomer must not null");
			// 删除手机对应的散客升级列表数据
			memberService.deleteUpGradeCustomerByNumber(custNumber);
	}

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
	@Override
	public boolean createMember(Member member) {
		// 系统自动生成客户编码
		// 获得会员序列的下一个id值
		String memberId = memberService.getMemberIdUseSeq();
		member.setId(memberId);
		// 根据会员序列id，生成会员编号
		String custNumber = CustomerRule.createCustNumberByMembrId(memberId);
		member.setCustNumber(custNumber);
		// 表单验证--- 会员各种表单验证
		// 业务验证--- 会员，联系人验证
		// 会员持久化
		long time1 = System.nanoTime();
		if (CustomerValidator.validateMemberForm(member)
				&& this.validateCreateMember(member)&&
				this.checkEnterpriseTaxregNumber(member)) {
			long time2 = System.nanoTime();
			log.info("validator member cost" + (time2 - time1) / 1000000.0
					+ "ms");
			// 设置账号关联的联系人为财务联系人
			addFinanceContact(member);
			String createUser = ContextUtil.getCurrentUserEmpId();
			member.setCreateUser(createUser);
			// 设置会员状态为正常
			CustomerRule.setMemberStatus(member, Constant.Status_Normal);
			memberService.createMember(member);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void createMember(Member member, UpGradeCustomer upGradeCustomer) {
		// 保存会员
		if (createMember(member)) {
			// 保存客户标签
			//校验存在客户标签列表
			if (CollectionUtils.isNotEmpty(member.getCustLabels())) {
				//获得第一条数据
				CustLabel custLabel = member.getCustLabels().get(0);
				//保存客户标签
				custLabelManager.saveCustLabel(ScatterCustomerUtil
						.setCustLabelData(member.getCustLabels(), member
								.getId(), Constant.MEMBERTYPE_IN_CUSTLABEL,
								custLabel.getLabel().getDeptId()));
			}
		}
	}
	/**
	 * 
	 * <p>
	 * Description:设置账号关联的人为财务联系人<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param member
	 * void
	 */
	public void addFinanceContact(Member member) {
		//获得联系人列表
		List<Contact> contactList = member.getContactList();
		// 获得银行账号信息
		List<Account> accountList = member.getAccountList();
		if(CollectionUtils.isNotEmpty(accountList)){
		for (Account account : accountList) {
			Contact contact = CustomerRule.getContact(contactList, account);
			if (contact == null) {
				log.warn("财务账号" + account + "找不到关联的财务联系人");
				throw new MemberException(MemberExceptionType.NoAccountFind,
						new Object[] { account.getBankAccount() });
			}
			// 账号选择的联系人为财务联系人
			contact.setLinkmanType(CustomerRule.addContactType(
					contact.getLinkmanType(), Constant.ContactType_Finance));
		}
		}
	}


	/**
	 * 
	 * <p>
	 * Description:初始化会员信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param upGrade
	 * @return
	 *
	 */
	@Override
	public Member initMember(UpGradeCustomer upGrade) {
		// 如果没有找出客户等级 ---可能此散客已经被别人抢去
		if (ValidateUtil.objectIsEmpty(upGrade)||ValidateUtil.objectIsEmpty(upGrade.getCustNumber())) {
			throw new MemberException(MemberExceptionType.TargetLevelNotExist);
		}
		//封装条件
		MemberCondition condition = new MemberCondition();
		condition.setCustNumber(upGrade.getCustNumber());
		//校验不为空
		Member member=null;
		List<Member> memberList = alterMemberService.searchMemberByCondition(condition);
		//不为空校验 避免空
		if(CollectionUtils.isNotEmpty(memberList)){
			member=memberList.get(0);
		}
		//如果客户类型不为散客,就是人品有问题
		if (null==member||!Constant.CUST_GROUP_SC_CUSTOMER.equals(member.getCustGroup())) {
			throw new MemberException(MemberExceptionType.TargetLevelNotExist);
		}
		if(StringUtils.equals(member.getCustStatus(), Constant.Cust_STATUS_WORKFLOW)){
			throw new MemberException(MemberExceptionType.MemberisExaming);
		}
		//疑似重复列表
		String deptName = repeatedCustManager.searchMainCustDeptName(member.getCustNumber());
		if (!StringUtils.isEmpty(deptName)) {
			throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{deptName});
		}
		//设置等级
		member.setDegree(upGrade.getTargetLevel());
		//设置客户分组为固定客户
		member.setCustGroup(Constant.CUST_GROUP_RC_CUSTOMER);
		return member;
	}
	/**
	 * 
	 * <p>
	 * Description:初始化特殊创建客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @return
	 *
	 */
	@Override
	public Member initSepcailMember() {
		String deptId = ContextUtil.getCurrentUserDeptId();
		return CustomerRule.initSepcatilMember(deptId);
	}
	/**
	 * 
	 * <p>
	 * Description:实时创建校验<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param phone
	 * @param tel
	 * @param name
	 * @return
	 * @throws CrmBusinessException
	 *
	 */
	@Override
	public ImplementMemberView initImplementMember(String phone, String tel,
			String name) throws CrmBusinessException {
		Member member = this.checkIsExistMember(phone, tel, name);
		// 验证联系人不存在
		if (!ValidateUtil.objectIsEmpty(member)&&!StringUtils.isEmpty(member.getId())) {
			ImplementMemberView view = new ImplementMemberView();
			//TODO 调用外部接口
			List<WayBillInfo> waybillList = getWayBillInfoList(phone, tel, name);
			//调试使用数据
//			List<WayBillInfo> waybillList = new ArrayList<WayBillInfo>();
//			WayBillInfo wayBillinfo = new WayBillInfo();
//			wayBillinfo.setMoney(1000);
//			wayBillinfo.setRatio(1);
//			wayBillinfo.setWayBillNumber("1231241");
//			waybillList.add(wayBillinfo);
			// 生成资格验证view
			QualificationView qualificationView = getQualificationView(waybillList);
			view.setQualificationView(qualificationView);

			// 是否初始化会员
			if (qualificationView.isQualified()) {
//				String deptId = ContextUtil.getCurrentUserDeptId();
//				String deptName = ContextUtil.getCurrentUserDeptName();
//				// 初始化会员信息
//				Member member = CustomerRule.initImplementMember(phone, tel,
//						name,"", deptId, deptName); 
				member = alterMemberService.getMemberAllByIdNEW(member.getId());
				member.setCustGroup(Constant.CUST_GROUP_RC_CUSTOMER);
				member.setDegree(qualificationView.getCustLevel());
				view.setMember(member);
			}
			return view;
		} else {
			return null;
		}

	}
	/**
	 * 
	 * <p>
	 * Description:生成资格验证<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param waybillList
	 * @return
	 * QualificationView
	 */
	private QualificationView getQualificationView(List<WayBillInfo> waybillList) {
		// 计算金额
		double totalMoney = 0;
		//集合不为空
		if(CollectionUtils.isNotEmpty(waybillList)){
		for (WayBillInfo wayBillInfo : waybillList) {
			totalMoney += wayBillInfo.getMoney() * wayBillInfo.getRatio();
			}
		}
		//资格验证
		QualificationView qualificationView = new QualificationView();
		qualificationView.setWayBillList(waybillList);
		qualificationView.setTotalMoney(totalMoney);
		//等级计算
		String custLevel = CustomerRule.calculateCustLevel(totalMoney);
		qualificationView.setCustLevel(custLevel);
		//不能升级为会员
		if (Constant.CustLevel_Fail.equals(custLevel)) {
			qualificationView.setQualified(false);
		} else {
			qualificationView.setQualified(true);
		}
		return qualificationView;
	}

	/**
	 * 
	 * <p>
	 * 调用外部接口，并且适配器转换<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-29
	 * @param phone
	 *            客户手机
	 * @param tel
	 *            客户电话
	 * @param name
	 *            客户姓名
	 * @return List<WayBillInfo>
	 * @throws CrmBusinessException
	 */
	private List<WayBillInfo> getWayBillInfoList(String phone, String tel,
			String name) throws CrmBusinessException {
		// 访问接口
		// 正式环境调接口组接口
		List<WaybillInfo> interfacleWaybillList = waybillOperate
				.queryWaybillMoney(phone, name, tel);
//		List<WaybillInfo> interfacleWaybillList = new ArrayList<WaybillInfo>();
		if (interfacleWaybillList.isEmpty()) {
			throw new MemberException(MemberExceptionType.ContactNotFindError);
		}
		// 适配器
		List<WayBillInfo> customerWayBillList = CustomerRule
				.adapterWayBillList(interfacleWaybillList);
		return customerWayBillList;
	}

	
	
	/**
	 * 
	* @Title: createPotentialCustomer
	* @Description: 创建潜客（界面手动创建）
	* @author chenaichun 
	* @param @param member
	* @param @return    设定文件
	* @date 2014-3-11 下午6:54:26
	* @return String    返回类型
	* @throws
	* @update 2014-3-11 下午6:54:26
	 */
	@Override
	public void createPotentialCustomer(Member member){
		//获得客户的联系人列表
		List<Contact> contacts = member.getContactList();
		//创建潜客-所有的联系人都不存在
		if(!CollectionUtils.isEmpty(contacts)&&isContactsAllNotExist(contacts)){
			//不存在相应客户则新增一条潜客
			if(CustomerValidator.validateMemberForm(member)
					&& this.validateCreateMember(member)
					&& this.checkEnterpriseTaxregNumber(member)){
					//设置客户为有效
					CustomerRule.setMemberStatus(member, Constant.Status_Normal);
					// 设置客户创建人为当前用户
					String createUser = ContextUtil.getCurrentUserEmpId();
					member.setCreateUser(createUser);
					// 会员持久化
					if(createMember(member)){
						//开发阶段的处理
						createMemberExtendAndDevelopment(member);	
					}
					// 保存客户标签
					if (CollectionUtils.isNotEmpty(member.getCustLabels())) {
						custLabelManager.saveCustLabel(ScatterCustomerUtil
								.setCustLabelData(member.getCustLabels(),
										member.getId(),
										Constant.MEMBERTYPE_IN_CUSTLABEL,
										member.getDeptId()));
					}
					// 记录操作日志
					MemberOperationLog log = CustomerRule.createLog(member
							.getId(),Constant.OPERATOR_TYPE_INSERT);
//					log.setOperateType(Constant.OPERATOR_TYPE_INSERT);
					log.setEffect(true);
					alterMemberService.saveMemberOperationLog(log);
			}
		}else{
			//无法创建
			throw new CustomerException(CustomerExceptionType.contactExitsCustomer);
		}
	}
	/**
	 * 
	* @Title: createScatterCustomer
	* @Description: foss创建散客（根据散客编码创建）
	* @author chenaichun 
	* @param @param member
	* @param @return    return null  就说明成功   不成功就抛异常
	* @date 2014-3-11 下午6:54:43
	* @return String    返回类型
	* @throws
	* @update 2014-3-11 下午6:54:43
	 */
	@Override
	public String createScatterCustomer(ChannelCustomer cust){
		//根据散客编码查询
		//校验编码不为空,部门标杆编码不为空,客户类别不恩能够为空（零担快递）
		if(ValidateUtil.objectIsEmpty(cust.getCustNumber())
				||ValidateUtil.objectIsEmpty(cust.getDeptStandCode())
				||ValidateUtil.objectIsEmpty(cust.getCustCategory())
				||ValidateUtil.objectIsEmpty(cust.getCustNature())){
			throw new MemberException(MemberExceptionType.Cust_Data_Error);
		}
		//根据联系方式去查(有效和审批中的联系人)
		List<Contact> contactLists = contactManager.searchContactList(
				cust.getMobilePhone(), cust.getTelPhone(),
				cust.getLinkManName());
		//TODO没有相同联系方式的联系人，直接创建散客 “开发阶段”
		if(CollectionUtils.isEmpty(contactLists)){
			if(ValidateUtil.objectIsEmpty(cust.getErpId())){
				throw new MemberException(MemberExceptionType.FOSSIDNULL);
			}
			Member member = this.produceMember(cust, Constant.CUST_SOURCE_Hall);
			if(!ValidateUtil.objectIsEmpty(member)){
				//开发阶段的处理
				createMemberExtendAndDevelopment(member);
				CustomerInfoLog.commit();
				//处理商机阶段
				this.saveBusinessOpportunity(member);
				return member.getId();
			}
		}//有相同联系方式的联系人（有效和审批中）
		else if(contactLists.size()>1){
			throw new CustomerException(CustomerExceptionType.MORETHANONECONTACT);	
		}else{
			//根据联系人查找客户
			if(StringUtils.isEmpty(contactLists.get(0).getCustId())){
				throw new MemberException(MemberExceptionType.Data_Miss_Error);
			}
			//extisMember 包含扩展信息
			Member existMember = alterMemberService.getMemberById(contactLists.get(0).getCustId());
			if(ValidateUtil.objectIsEmpty(existMember)){
				throw new CustomerException(CustomerExceptionType.CANNOTFINDMEMBER);
			}
			//客户性质
			String custGroup = existMember.getCustGroup();
			if(ValidateUtil.objectIsEmpty(custGroup)){
				throw new CustomerException(CustomerExceptionType.CUSTGROUPISNULL);
			}
			//如果查出来的是潜客：分两种：
			//有效潜客：foss客户编码和CRM编码相同：直接升级CRM潜客；不同，作废CRM 潜客
			//审批中潜客:直接作废潜客，并将工作流不同意
			if(Constant.CRM_POTENTIAL.equals(custGroup)){
				//有效潜客：foss客户编码和CRM编码相同：直接升级CRM潜客；
				if(existMember.getCustNumber().equals(cust.getCustNumber())
						&&Constant.Cust_STATUS_EFFECT.equals(existMember.getCustStatus())){
					//潜客-》散客
					//将状态改为散客 并记录日志 所有的开发阶段等处理都在升级方法里面
					if(this.makePotentialToScatter(existMember,cust)){
						return existMember.getId();
					}
				}else{
					//商机状态为未关闭，FOSS开单创建散客删除该潜客，则将新的客户ID覆盖商机中原有的客户ID
					//创建散客
					if(ValidateUtil.objectIsEmpty(cust.getErpId())){
						throw new MemberException(MemberExceptionType.FOSSIDNULL);
					}
					Member member = this.produceMember(cust, Constant.CUST_SOURCE_Hall);
					if(!ValidateUtil.objectIsEmpty(member)){
						//开发阶段的处理
						createMemberExtendAndDevelopment(member);
						//商机处理
						this.deletePotentialCustomer(existMember,member.getId());
						//处理商机阶段
						this.saveBusinessOpportunity(member);
						CustomerInfoLog.commit();
						return member.getId();
					}
				}
				
			//如果查出来的为散客或者固定客户
			}else{
				//散客来源都是foss,所以客户编码肯定相同
				//固定客户（创建特殊固定客户在审批中，可能编码不同，散客不创建）
				//客户编码相同，更新客户的客户属性，客户类别等字段
				if(existMember.getCustNumber().equals(cust.getCustNumber())){
					//原来客户的客户属性
					String oldCustNature = existMember.getCustNature();
					//判断原来的客户是否为纯到达散客，如果属性改变 那么需要更新开发阶段 
					boolean isChangeNature = false;
					//修改的信息
					Member updateMemberInfo = new Member();
					updateMemberInfo.setId(existMember.getId());
					if(!StringUtils.equals(existMember.getCustNature(), cust.getCustNature())){
						updateMemberInfo.setCustNature(Constant.LEAVE_ARRIVE_CUSTOMER);
						existMember.setCustNature(Constant.LEAVE_ARRIVE_CUSTOMER);
						isChangeNature = true;
					}
					if(!StringUtils.equals(existMember.getCustCategory(), cust.getCustCategory())){
						updateMemberInfo.setCustCategory(Constant.CUST_BUSTYPE_EXANDLD);
						existMember.setCustCategory(Constant.CUST_BUSTYPE_EXANDLD);
					}
					//非纯到达散客、固定客户不需要更新开发阶段（member,memberExtend)
					if(alterMemberService.updateMember(updateMemberInfo)){
						//纯到达散客变为出发到达 就需要改变开发阶段
						if(isChangeNature&&StringUtils.equals(oldCustNature, Constant.ArriveCustomer)){
							//开发阶段的处理
							createMemberExtendAndDevelopment(existMember);
						}
						CustomerInfoLog.commit();
						return existMember.getId();
					}
				}
			}
		}
		throw new MemberException(MemberExceptionType.createScatterFail);
	}
	/**
	 * 
	* @Title: deletePotentialCustomer
	* @Description: 创建散客发现有潜客（编码不同，联系方式相同），需要作废潜客
	* @author chenaichun 
	* @param @param existMember    设定文件
	* @date 2014年5月8日 下午3:33:49
	* @return void    返回类型
	* @throws
	* @update 2014年5月8日 下午3:33:49
	 */
	private boolean deletePotentialCustomer(Member member,String newMemberId) {
		//判断该客户是否有审批中工作流,确定：有没有工作流都直接作废，有工作流的在工作流审批的时候去做处理
		//判断该客户商机状态是否未关闭,如果未关闭，则将新的客户ID更新到商机中去
		if(alterMemberService.isExtistUnCloseBusiness(member.getId())){
			alterMemberService.upadteUnCloseBustiness(newMemberId, member.getId());
		}
		alterMemberService.deleteMember(member);
		return true;
	}
	/**
	 * 
	* @Title: makePotentialToScatter
	* @Description: 将潜客变为散客，
	* 1、	潜客第一次发货时，若在潜客的归属部门发货，则该客户的归属部门字段不做变更；
      2、	潜客第一次发货时，若不在潜客的归属部门发货，则变更该客户的归属部门字段为发货的营业部；
	* @author chenaichun 
	* @param @param potentialMember
	* @param @param member    设定文件
	* @date 2014-3-12 下午2:41:59
	* @return void    返回类型
	* @throws
	* @update 2014-3-12 下午2:41:59
	 */
	private boolean makePotentialToScatter(Member poMember,ChannelCustomer cust) {
		boolean isUpdate = false;
		//获取foss传过来的部门ID,潜客变为散客时，将潜客的归属部门变为第一次发货的部门
		Department dept = departmentService.getDeptByStandardCode(cust.getDeptStandCode());
		// 设置修改人为当前用户或者超级管理员
		String modifyUser = ContextUtil.getCreateOrModifyUserId();
		List<ApproveData> updateDataAllList = new ArrayList<ApproveData>();
		//部门是否一致
		if(!StringUtils.equals(poMember.getDeptId(),dept.getId())){
			//在审批数据增加部门
			updateDataAllList.add(MemberUtil.proApproveData(poMember.getDeptId(),
					dept.getId(), poMember.getId(),
					Member.class.getSimpleName(), "deptId"));
		}
		//部门名字是否一致
		if(!StringUtils.equals(poMember.getDeptName(),dept.getDeptName())){
			updateDataAllList.add(MemberUtil.proApproveData(poMember.getDeptName(),
					dept.getDeptName(), poMember.getId(),
					Member.class.getSimpleName(), "deptName"));
		}
		//是否为散客
		if(!StringUtils.equals(poMember.getCustGroup(),Constant.CUST_GROUP_SC_CUSTOMER)){
			updateDataAllList.add(MemberUtil.proApproveData(poMember.getCustGroup(),
					Constant.CUST_GROUP_SC_CUSTOMER, poMember.getId(),
					Member.class.getSimpleName(), "custGroup"));
		}
		//变更客户的归属部门
		poMember.setDeptId(dept.getId());
		poMember.setDeptName(dept.getDeptName());
		//将客户性质改为散客
		poMember.setCustGroup(Constant.CUST_GROUP_SC_CUSTOMER);
//		 “开发阶段”的东西，查潜客的memberExtend,更新
		//保存修改信息
		Member updateMemberInfo = new Member();
		updateMemberInfo.setId(poMember.getId());
		//变更客户的归属部门
		updateMemberInfo.setDeptId(dept.getId());
		updateMemberInfo.setDeptName(dept.getDeptName());
		//将客户性质改为散客
		updateMemberInfo.setCustGroup(Constant.CUST_GROUP_SC_CUSTOMER);
		updateMemberInfo.setModifyUser(modifyUser);
		if(!StringUtils.equals(poMember.getCustNature(), cust.getCustNature())){
			updateMemberInfo.setCustNature(Constant.LEAVE_ARRIVE_CUSTOMER);
			updateDataAllList.add(MemberUtil.proApproveData(poMember.getCustNature(),
					cust.getCustNature(), poMember.getId(),
					Member.class.getSimpleName(), "custNature"));
			poMember.setCustNature(Constant.LEAVE_ARRIVE_CUSTOMER);
			
		}
		if(!StringUtils.equals(poMember.getCustCategory(), cust.getCustCategory())){
			updateMemberInfo.setCustCategory(Constant.CUST_BUSTYPE_EXANDLD);
			updateDataAllList.add(MemberUtil.proApproveData(poMember.getCustCategory(),
					cust.getCustCategory(), poMember.getId(),
					Member.class.getSimpleName(), "custCategory"));
			poMember.setCustCategory(Constant.CUST_BUSTYPE_EXANDLD);
		}
		isUpdate = alterMemberService.updateMember(updateMemberInfo);
		if(isUpdate){
			//开发阶段的处理
			createMemberExtendAndDevelopment(poMember);
			//处理商机阶段
			saveBusinessOpportunity(poMember);
			// 记录的操作日志  
			MemberOperationLog log = CustomerRule.createLog(poMember.getId(),Constant.OPERATOR_TYPE_POTOSC);
//			log.setOperateType(Constant.OPERATOR_TYPE_POTOSC);
			log.setEffect(true);
			alterMemberService.saveMemberOperationLog(log);
			// 持久化approveData
			String logId = log.getId();
			alterMemberService.saveApproveData(updateDataAllList, logId);
		}
		CustomerInfoLog.commit();
		return isUpdate;
	}

	/**
	 * 
	 * <p>
	 * Description:创建特殊固定客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param member
	 * @return
	 *
	 */
	@Override
	public String createSpecialMember(Member member) {
		// 系统自动生成客户编码
		// 获得会员序列的下一个id值
		String memberId = memberService.getMemberIdUseSeq();
		member.setId(memberId);
		// 根据会员序列id，生成会员编号
		String custNumber = CustomerRule.createCustNumberByMembrId(memberId);
		member.setCustNumber(custNumber);
		//TODO　重复设值　ｌｉｎｅ　６７５
		// 设置会员状态为审批中
		member.setCustStatus(Constant.Status_Examine);

		// 表单验证--- 会员各种表单验证
		// 业务验证--- 会员，联系人验证
		if (CustomerValidator.validateMemberForm(member)
				&& this.validateCreateMember(member)
				&& this.checkEnterpriseTaxregNumber(member)) {
			// 设置会员状态为 审批中
			CustomerRule.setMemberStatus(member, Constant.Status_Examine);
			// 设置会员创建人为当前用户
			String createUser = ContextUtil.getCurrentUserEmpId();
			member.setCreateUser(createUser);
			// 会员持久化
			memberService.createMember(member);
			////处理开发阶段
			this.createMemberExtendAndDevelopment(member);
			// 保存客户标签
			if (CollectionUtils.isNotEmpty(member.getCustLabels())) {
				custLabelManager.saveCustLabel(ScatterCustomerUtil
						.setCustLabelData(member.getCustLabels(),
								member.getId(),
								Constant.MEMBERTYPE_IN_CUSTLABEL,
								member.getDeptId()));
			}
			// 调用工作流接口
			long workflowId = memberWorkFlowManager
					.createSepCreateMemberWorkFlow(memberId);
			// 存放需要锁定的处于工作流状态的联系人信息
			Set<ApprovingWorkflowData> workflowDatas = new HashSet<ApprovingWorkflowData>();
			// 获得会员的联系人集合
			List<Contact> contacts = member.getContactList();
			// 需要锁定的处于工作流状态的联系人信息
			ApprovingWorkflowData workflowData;
			// 遍历联系人信息，产生锁定的处于工作流状态的联系人信息
			if (CollectionUtils.isNotEmpty(contacts)) {
				workflowData = new ApprovingWorkflowData();
				for (int i = 0; i < contacts.size(); i++) {
					Contact contact = contacts.get(i);
					workflowData.setWorkflowId(String.valueOf(workflowId));
					workflowData.setContactId(contacts.get(i));
					// 如果是一个个人客户，而且联系人为主联系人
					if (Constant.Person_Member.equals(member.getCustType())
							&& contact.getIsMainLinkMan()) {
						// 如果主联系人身份证不为空
						if (StringUtils.isEmpty(contact.getIdCard())) {
							// 证件号
							workflowData.setIdCardNo(contact.getIdCard());
							// 证件类型
							workflowData.setCredentialsType(contact
									.getCardTypeCon());
							workflowDatas.add(workflowData);
						}
					}
					// 根据联系人信息产生需要锁定的联系人信息，放入workflowDatas中
					produceWorkflowData(workflowId, workflowDatas, contact);
				}
			}
			// 保存根据联系人信息产生需要锁定的联系人信息
			approvingWokflowDataService
					.batchCreateContactWorkflowData(workflowDatas);
			// 返回工作流号
			return String.valueOf(workflowId);
		}
		return null;
	}

	/**
	 * @description 根据联系人信息生成需要锁定的联系人信息.
	 * @author 潘光均
	 * @version 0.1 2012-7-17
	 * @param
	 * @date 2012-7-17
	 * @return ContactWorkflowData
	 * @update 2012-7-17 下午2:30:57
	 */
	private void produceWorkflowData(long workflowId,
			Set<ApprovingWorkflowData> workflowDatas, Contact contact) {
		ApprovingWorkflowData workflowData = null;
		// 联系人手机不为空
		if (StringUtils.isEmpty(contact.getMobilePhone())) {
			workflowData = new ApprovingWorkflowData();
			workflowDatas.add(workflowData);
		}
		// 联系人编码不为空
		if (StringUtils.isEmpty(contact.getNumber())) {
			workflowData = new ApprovingWorkflowData();
			workflowData.setContactNum(contact.getNumber());
			workflowDatas.add(workflowData);
		}
		// 联系人姓名和固话不为空
		if (StringUtils.isEmpty(contact.getMobilePhone())
				&& StringUtils.isEmpty(contact.getName())) {
			workflowData = new ApprovingWorkflowData();
			workflowData.setContactName(contact.getName());
			workflowData.setContactMobile(contact.getMobilePhone());
			workflowDatas.add(workflowData);
		}
		// 分别设置每一条信息的工作流号和联系人信息
		for (ApprovingWorkflowData contactWorkflowData : workflowDatas) {
			contactWorkflowData.setContactId(contact);
			contactWorkflowData.setWorkflowId(String.valueOf(workflowId));
		}
	}
	/**
	 * 
	 * <p>
	 * Description:审批之后调用,同意更新为有效,不同意删除客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberId
	 * @param examineResult
	 *
	 */

	@Override
	public void disposeSpecialMemberByExamineResult(String memberId,
			boolean examineResult) {
		// 查询出会员信息
		String modifyUser = ContextUtil.getCurrentUserEmpId();
		//审批工作流的时候校验下 该客户是否已经作废，如果已经作废，就不做任何处理
		Member memb = alterMemberService.getMemberById(memberId);
		if(!ValidateUtil.objectIsEmpty(memb)
				&&Constant.Cust_STATUS_UNEFFECT.equals(memb.getCustStatus())){
			return;
		}
		if (examineResult) {
			String status = Constant.Status_Normal;
			// 修改会员状态 有效
			memberService.updateMemberStauts(status, memberId, modifyUser);
		} else {
			//删除客户
			memberService.deleteMemberById(memberId);
		}

	}
	/**
	 * 
	 * <p>
	 * Description:散客升级后增加备注信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param upGradeCustomerId
	 * @param remark
	 *
	 */
	@Override
	public void addUpGradeCustomerRemark(String upGradeCustomerId, String remark) {
		String modifyUser = ContextUtil.getCurrentUserEmpId();
		//散客升级后增加备注信息
		memberService.addUpGradeCustomerRemark(upGradeCustomerId, remark,
				modifyUser);
	}
	/**
	 * 
	 * <p>
	 * Description:根据id获得散客升级信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public UpGradeCustomer getUpGradeCustomerById(String id) {
		return memberService.getUpGradeCustomerById(id);
	}
	/**
	 * 
	 * <p>
	 * Description:查询部门中的会员id<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param deptId
	 * @return
	 *
	 */
	@Override
	public List<String> getMemberIdListBydeptId(String deptId) {
		MemberCondition condition = new MemberCondition();
		condition.setDeptId(deptId);
		List<Member> list = alterMemberService
				.searchMemberByCondition(condition);
		List<String> ids = new ArrayList<String>();
		//空校验
		if(CollectionUtils.isNotEmpty(list)){
		for (Member member : list) {
			ids.add(member.getId());
		}
		}
		return ids;
	}
	/**
	 * 
	 * <p>
	 * Description:根据条件查询散客升级列表条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param condition
	 * @return
	 *
	 */
	@Override
	public int getCountUpGradeCustomerByUpGradeCustomerCondition(
			UpGradeCustomerCondition condition) {
		List<String> depts = null;
		String dept = condition.getDept();
		// 得到数据权限部门 里面包含数据权限验证
		depts = baseDataManager.getDataAuthorityDepts(dept);
		condition.setDepts(depts);
		//校验查询条件
		if (CustomerValidator.validateUpGradeCustomerCondition(condition)) {
			return memberService
					.getCountUpGradeCustomerByUpGradeCustomerCondition(condition);
		}
		return 0;
	}
	/**
	 * 
	 * <p>
	 * Description:根据条件查询散客升级列表信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param condition
	 * @return
	 *int 
	 */
	@Override
	public List<UpGradeCustomer> searchUpGradeCustomerList(
			UpGradeCustomerCondition condition, int start, int limit) {
		List<String> depts = null;
		String dept = condition.getDept();

		List<UpGradeCustomer> upList = new ArrayList<UpGradeCustomer>();
		// 得到数据权限部门 里面包含数据权限验证
		depts = baseDataManager.getDataAuthorityDepts(dept);
		condition.setDepts(depts);
		condition.setDepts(depts);
		if (CustomerValidator.validateUpGradeCustomerCondition(condition)) {
			upList = memberService.searchUpGradeCustomerList(condition, start,
					limit);
		}
		return upList;
	}
	/**
	 * 
	 * <p>
	 * Description:实时创建会员信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param member
	 *
	 */
	@Override
	public void createImplementMember(Member member) {
		// 创建会员
		createMember(member);
	}
	/**
	 * 
	 * <p>
	 * Description:根据手机,电话,姓名校验是否存在对应的客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param phone
	 * @param tel
	 * @param name
	 * @return
	 * 二期改造该方法：只查找联系人表，看是否存在对应的有效的散客，如果有，允许创建固定客户
	 * true 允许创建，false 不允许创建
	 */
	@Override
	public Member checkIsExistMember(String phone, String tel, String name) {
		if (ValidateUtil.objectIsEmpty(phone)
				&& (
					ValidateUtil.objectIsEmpty(name) || ValidateUtil
					.objectIsEmpty(tel) 
				)) {
			throw new IllegalArgumentException("手机，姓名，电话值不全,参数调用不合法");
		}
		if (!ValidateUtil.objectIsEmpty(phone)) {
			List<Member> memberList = memberService.searchMemberByConstactWay(phone,null,null);
			return validateRealTimeCreateMember(memberList);
			
		}
		if (!ValidateUtil.objectIsEmpty(name)
				&& !ValidateUtil.objectIsEmpty(tel)) {
			List<Member> memberList = memberService.searchMemberByConstactWay(null,tel,name);
			return validateRealTimeCreateMember(memberList);
		}
		return null;
	}
	/**
	 * 
	* @Title: validateRealTimeCreateMember
	* @Description: 校验是否是有效的散客，是：true
	* @author chenaichun 
	* @param @param memberList
	* @param @return    设定文件
	* @date 2014年5月16日 上午10:15:50
	* @return boolean    返回类型
	* @throws
	* @update 2014年5月16日 上午10:15:50
	 */
	public Member validateRealTimeCreateMember(List<Member> memberList) {
		if(CollectionUtils.isEmpty(memberList)){
			throw new CustomerException(CustomerExceptionType.CANNOTFINDMEMBER);
		}
		if(memberList.size()>1){
			throw new CustomerException(CustomerExceptionType.MORETHANONECONTACT);
		}
		Member member = memberList.get(0);
		if(ValidateUtil.objectIsEmpty(member)){
			throw new CustomerException(CustomerExceptionType.CANNOTFINDMEMBER);
		}
		if(!StringUtils.equals(Constant.CUST_GROUP_SC_CUSTOMER, member.getCustGroup())){
			throw new CustomerException(CustomerExceptionType.NOTSCATTERNOTAllOWED,
					new Object[] { member.getCustNumber(),DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.CUST_TYPE,member.getCustGroup())});
		}
		//存在审批中工作流
		if(Constant.Cust_STATUS_WORKFLOW.equals(member.getCustStatus())){
			String workflowId = alterMemberService.getCustWorkflow(member.getId());
			if (!ValidateUtil.objectIsEmpty(workflowId)) {
				throw new CustomerException(CustomerExceptionType.MemberIsExamin,
						new Object[] { workflowId });
			}
			workflowId = repeatedCustManager.searchWorkFlowNoByCustId(member.getCustNumber());
			if (!ValidateUtil.objectIsEmpty(workflowId)) {
				throw new CustomerException(CustomerExceptionType.MemberIsExamin,
						new Object[] { workflowId });
			}
			throw new CustomerException(CustomerExceptionType.custIsExamin);
		}
		//疑重列表中
		//疑似重复列表
		String deptName = repeatedCustManager.searchMainCustDeptName(member.getCustNumber());
		if (!StringUtils.isEmpty(deptName)) {
			throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{deptName});
		}
		return member;
	}
	/**
	 * 
	 * <p>
	 * Description:查询会员升级信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param statisticsTime
	 * @return
	 *
	 */
	@Override
	public List<MemberUpgradeList> getMemberUpgrage(String statisticsTime) {
		return memberService.getMemberUpgraeByStatisticsTime(statisticsTime);
	}
	/**
	 * 
	 * <p>
	 * Description:固定客户升级<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberUpgrade
	 *
	 */
	@Override
	public void upgrageMember(MemberUpgradeList memberUpgrade) {
		//TODO 最好抛出异常
		Assert.notNull(memberUpgrade, "memberUpgrade must not null");
		String modifyUser = ContextUtil.getCurrentUserEmpId();
		// 会员编号
		String memberNumber = memberUpgrade.getCustNumber();
		// 目标等级
		String targetLevel = memberUpgrade.getTargetLevel();
		// 修改会员等级
		memberService.updateMemberLevelByMemberNumber(targetLevel,
				memberNumber, modifyUser);

	}
	/**
	 * 
	 * <p>
	 * Description:查询会员升级信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 *
	 */
	@Override
	public List<MemberUpgradeList> searchMemberUpgradeList(
			UpGradeCustomerCondition condition, int start, int limit) {
		List<String> depts = null;
		String dept = condition.getDept();
		List<MemberUpgradeList> upList = new ArrayList<MemberUpgradeList>();
		// 得到数据权限部门 里面包含数据权限验证
		depts = baseDataManager.getDataAuthorityDepts(dept);
		condition.setDepts(depts);
		if (CustomerValidator.validateMemberUpgradeListCondition(condition)) {
			upList = memberService.searchMemberUpgradeList(condition, start,
					limit);
		}
		return upList;
	}
	/**
	 * 
	 * <p>
	 * Description:批量升级<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param upgradeList
	 *
	 */
	@Override
	public void upgrageMember(List<MemberUpgradeList> upgradeList) {
		for (MemberUpgradeList memberUpgradeList : upgradeList) {
			upgrageMember(memberUpgradeList);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:会员降级列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param demotionList
	 *
	 */
	@Override
	public void demotionMember(List<MemberDemotionList> demotionList) {
		for (MemberDemotionList memberDemotionList : demotionList) {
			demotionMember(memberDemotionList);
		}
	}

	/**
	 * 
	 * <p>
	 * 会员降级操作<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param memberDemotionList
	 *            void
	 */
	private void demotionMember(MemberDemotionList memberDemotion) {
		Assert.notNull(memberDemotion, "memberDemotionList must not null");
		String modifyUser = ContextUtil.getCurrentUserEmpId();
		// 会员编号
		String memberNumber = memberDemotion.getCustNumber();
		// 目标等级
		String targetLevel = memberDemotion.getTargetLevel();

		// 修改会员等级
		memberService.updateMemberLevelByMemberNumber(targetLevel,
				memberNumber, modifyUser);

		// 是否是 已降级会员
		if (Constant.CustLevel_Demotion.equals(targetLevel)) {
//			// 查询所有的联系人
//			List<Contact> contacts = contactService
//					.getContactsByMemberNumber(memberNumber);
//			// 潜散客生效
//			for (Contact contact : contacts) {
//				String phone = contact.getMobilePhone();
//				String tel = contact.getTelPhone();
//				String name = contact.getName();
			//TODO
//				scatterCustomerManager.takeEffectCustomer(phone, tel, name);
//			}
		}

	}
	/**
	 * 
	 * <p>
	 * Description:根据统计时间查询会员降级列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param statisticsTime
	 * @return
	 *
	 */
	@Override
	public List<MemberDemotionList> getDemotionMemberByStatisticsTime(
			String statisticsTime) {
		return memberService.getDemotionMemberByStatisticsTime(statisticsTime);
	}
	/**
	 * 
	 * <p>
	 * Description:查询会员降级列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 *
	 */
	@Override
	public List<MemberDemotionList> searchMemberDemotionList(
			UpGradeCustomerCondition condition, int start, int limit) {
		List<String> depts = null;
		String dept = condition.getDept();

		List<MemberDemotionList> upList = new ArrayList<MemberDemotionList>();
		// 得到数据权限部门 里面包含数据权限验证
		depts = baseDataManager.getDataAuthorityDepts(dept);
		condition.setDepts(depts);
		depts = ContextUtil.getDataAuthorityDepts(dept);
		condition.setDepts(depts);
		if (CustomerValidator.validateMemberDemotionListCondition(condition)) {
			upList = memberService.searchMemberDemotionList(condition, start,
					limit);
		}
		return upList;
	}
	/**
	 * 
	 * <p>
	 * Description:根据查询条件查询出总页数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param condition
	 * @return
	 *
	 */
	@Override
	public int getCountMemberUpgradeListByCondition(
			UpGradeCustomerCondition condition) {
		return memberService.getCountMemberUpgradeListByCondition(condition);
	}
	/**
	 * 
	 * <p>
	 * Description:根据查询条件查询出总页数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param condition
	 * @return
	 *
	 */
	@Override
	public int getCountMemberDemotionListByCondition(
			UpGradeCustomerCondition condition) {
		return memberService.getCountMemberDemotionListByCondition(condition);
	}
	/**
	 * 
	 * <p>
	 * Description:校验电话号码是否有效<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param mobilePhone
	 * @return
	 *
	 */
	@Override
	public boolean checkMobilePhoneCanUse(String mobilePhone) {
		return !checkPhone(mobilePhone);
	}
	/**
	 * 
	 * <p>
	 * Description:校验电话和姓名<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param tel
	 * @param name
	 * @return
	 *
	 */
	@Override
	public boolean checkTelAndNameCanUse(String tel, String name) {
		return checkTelAndName(tel, name);
	}
	/**
	 * 
	 * <p>
	 * Description:校验税务登记号可用<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param taxregNumber
	 * @return
	 *
	 */
	@Override
	public boolean checkTaxregNumberCanUse(String taxregNumber) {
		//香港登记号
		if (baseDataManager.isHongKongOrMainLand()) {
			if (!taxregNumber.matches("^[\\d]{11}$")) {
				throw new MemberException(MemberExceptionType.HongKong_TaxregNumber_Error);
			}
		} else{
			//验证企业税务登记号是否可用
			if(!MemberEffectValidate.validate(taxregNumber,Certification.TAX_CARD)){
				throw new MemberException(MemberExceptionType.EnterpriseMember_TaxregNumberError);
			}
		}
		
		return checkTaxregNumber(taxregNumber);
	}
	/**
	 * 
	 * <p>
	 * Description:校验证件号<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param idCard
	 * @param custType
	 * @param isMain
	 * @param cardTypeCon
	 * @return
	 *
	 */
	@SuppressWarnings("serial")
	@Override
	public boolean checkIdCardCanUse(String idCard, String custType,
			boolean isMain, String cardTypeCon) {
		try {
			Assert.notNull(idCard, "Card NO Can't Null !");
			Assert.notNull(custType, "CustType  Can't Null !");
			Assert.notNull(isMain, "IsMain  Can't Null !");
			Assert.notNull(cardTypeCon, "Card Type  Can't Null !");
			if (Constant.Person_Member.equals(custType) && isMain) {
				return this.idCardCanUse(idCard, cardTypeCon);
			} else {
				return true;
			}
		} catch (MemberException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}
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
	@Override
	public void addMonthlyStatement(String memberId, double monthlyStatement) {
		String modifyUser = ContextUtil.getCurrentUserEmpId();
		// 得到申请月结额度
		double maxMothlyStatement = 100000;
		// 添加月结额度 验证月结额度 是否会超过最大值
		Double oldMonthlyStatement = memberService
				.getMothlyStatementBymemberId(memberId);
		double newMonthlyStatement = oldMonthlyStatement + monthlyStatement;
		if (newMonthlyStatement > maxMothlyStatement) {
			throw new MemberException(MemberExceptionType.MonthlyStatementOver,
					new Object[] { oldMonthlyStatement, newMonthlyStatement });
		}
		memberService.updateMothlyStatementByMemberId(memberId,
				newMonthlyStatement, modifyUser);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberId
	 * @param monthlyStatement
	 *
	 */
	@Override
	public void reduceMonthlyStatement(String memberId, double monthlyStatement) {
		String modifyUser = ContextUtil.getCurrentUserEmpId();
		// 验证月结额度 是否能被减
		// 得到申请月结额度
		// 添加月结额度 验证月结额度 是否会超过最大值
		Double oldMonthlyStatement = memberService
				.getMothlyStatementBymemberId(memberId);
		double newMonthlyStatement = oldMonthlyStatement - monthlyStatement;
		if (newMonthlyStatement < 0) {
			throw new RuntimeException("月结额度不能小于0");
		}
		memberService.updateMothlyStatementByMemberId(memberId,
				newMonthlyStatement, modifyUser);
	}
	/**
	 * 
	 * <p>
	 * Description:是否合同客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public boolean isContractMember(String memberId) {
		List<ContractDetailView> list = contractManager
				.getContractsByMemberId(memberId);
		return list.size() > 0;
	}
	/**
	 *
	 * <p>
	 * Description:根据客户编码和客户id校验是否为合同客户<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberId
	 * @param custNumber
	 * @return
	 *
	 */
	@Override
	public boolean isContractMemberByIdOrCustNumber(String memberId,
			String custNumber) {
		//如果客户id不为空
		if (!StringUtils.isEmpty(memberId)) {
			return this.isContractMember(memberId);
		}
		//客户编码不为空
		if (!StringUtils.isEmpty(custNumber)) {
			List<Contract> list = contractManager
					.getContractsByCustNumber(custNumber);
			if (CollectionUtils.isNotEmpty(list)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:保存审批信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param record
	 *
	 */
	@Override
	public void saveExamineRecord(ExamineRecord record) {
		String createUser = ContextUtil.getCurrentUserEmpId();
		record.setCreateUser(createUser);
		//保存
		examineRecordService.saveExamineRecord(record);
	}

	@Override
	public MemberExaminView createModifyMemberExaminView(String memberId,
			long workflowId) {
		Member member = alterMemberService.searchMember4WorkflowById(memberId);

		List<ApproveData> appList = alterMemberService.searchApproveData(String
				.valueOf(workflowId));
		List<ExamineRecord> examineRecordList = examineRecordService
				.getExamineRecordByWorkflowId(workflowId);
		// 增加修改对象名称
		addChangerName(memberId, member, appList);
		MemberExaminView view = new MemberExaminView();
		view.setMember(member);
		view.setAppList(appList);
		view.setExamineRecordList(examineRecordList);
		view.setCurrentExaminer(examineRecordService
				.getCurrentPeople(workflowId));

		return view;
	}

	/**
	 * @description 增加会员修改信息对象名称.
	 * @author 潘光均
	 * @version 0.1 2012-7-19
	 * @param
	 * @date 2012-7-19
	 * @return void
	 * @update 2012-7-19 下午3:56:51
	 */
	private void addChangerName(String memberId, Member member,
			List<ApproveData> appList) {
		// 会员联系人
		List<Contact> contacts = member.getContactList();
		// 会员账户
		List<Account> accounts = member.getAccountList();
		// 接送货地址
		List<ShuttleAddress> shutAddresses = member.getShuttleAddressList();
		// 联系人偏好地址
		List<PreferenceAddress> preAddresses = new ArrayList<PreferenceAddress>();
		// 构造联系人偏好地址集合
		for (Contact cont : contacts) {
			preAddresses.addAll(cont.getPreferenceAddressList());
		}
		for (ApproveData approveData : appList) {
			if ("Member".equals(approveData.getClassName())
					&& memberId.equals(approveData.getClassId())) {
				approveData.setChangerName("客户:" + member.getCustName());
			} else if ("Contact".equals(approveData.getClassName())) {
				addContactChangerName(contacts, approveData);
			} else if ("PreferenceAddress".equals(approveData.getClassName())) {
				addPreAddChangerName(contacts, approveData);
			} else if ("Account".equals(approveData.getClassName())) {
				addAccountChangerName(member, accounts, approveData);
			} else if ("ShuttleAddress".equals(approveData.getClassName())) {
				addShutAddChangerName(member, shutAddresses, approveData);
			}

		}
	}

	/**
	 * @description 增加联系人修改对象名称.
	 * @author 潘光均
	 * @version 0.1 2012-7-19
	 * @param
	 * @date 2012-7-19
	 * @return void
	 * @update 2012-7-19 下午3:59:36
	 */
	private void addContactChangerName(List<Contact> contacts,
			ApproveData approveData) {
		for (Contact contact : contacts) {
			if (approveData.getClassId().equals(contact.getId())) {
				approveData.setChangerName("联系人:" + contact.getName());
			}
		}
		if (StringUtils.isEmpty(approveData.getChangerName())) {
			approveData.setChangerName("新增联系人信息");
		}
	}

	/**
	 * @description 增加偏好地址修改对象名称.
	 * @author 潘光均
	 * @version 0.1 2012-7-19
	 * @param
	 * @date 2012-7-19
	 * @return void
	 * @update 2012-7-19 下午3:59:08
	 */
	private void addPreAddChangerName(List<Contact> contacts,
			ApproveData approveData) {
		for (Contact contact : contacts) {
			for (PreferenceAddress preferenceAddress : contact
					.getPreferenceAddressList()) {
				if (approveData.getClassId().equals(preferenceAddress.getId())) {
					approveData.setChangerName("联系人:" + contact.getName());
				}
			}
		}
		if (StringUtils.isEmpty(approveData.getChangerName())) {
			approveData.setChangerName("新增联系人偏好地址");
		}
	}

	/**
	 * @description 增加账户修改对象名称.
	 * @author 潘光均
	 * @version 0.1 2012-7-19
	 * @param
	 * @date 2012-7-19
	 * @return void
	 * @update 2012-7-19 下午3:58:49
	 */
	private void addAccountChangerName(Member member, List<Account> accounts,
			ApproveData approveData) {
		for (Account account : accounts) {
			if (approveData.getClassId().equals(account.getId())) {
				approveData.setChangerName("客户:" + member.getCustName()
						+ ":财务联系人:" + account.getFinanceLinkman());
			}
		}
		if (StringUtils.isEmpty(approveData.getChangerName())) {
			approveData.setChangerName("新增账户信息");
		}
	}

	/**
	 * @description 增加接送货地址的修改对象名称.
	 * @author 潘光均
	 * @version 0.1 2012-7-19
	 * @param
	 * @date 2012-7-19
	 * @return void
	 * @update 2012-7-19 下午3:57:59
	 */
	private void addShutAddChangerName(Member member,
			List<ShuttleAddress> shutAddresses, ApproveData approveData) {
		for (ShuttleAddress shuttleAddress : shutAddresses) {
			if (approveData.getClassId().equals(shuttleAddress.getId())) {
				if ("provinceName".equals(approveData.getFieldName())) {
					approveData.setChangerName("客户:"
							+ member.getCustName()
							+ ":接送货地址:省份"
							+ (shuttleAddress.getProvinceName() == null ? ""
									: ":" + shuttleAddress.getProvinceName()));
					break;
				} else if ("cityName".equals(approveData.getFieldName())) {
					approveData.setChangerName("客户:"
							+ member.getCustName()
							+ ":接送货地址:城市"
							+ (shuttleAddress.getCityName() == null ? "" : ":"
									+ shuttleAddress.getCityName()));
					break;
				} else if ("areaName".equals(approveData.getFieldName())) {
					approveData.setChangerName("客户:"
							+ member.getCustName()
							+ ":接送货地址:区县"
							+ (shuttleAddress.getAreaName() == null ? "" : ":"
									+ shuttleAddress.getAreaName()));
					break;
				} else if ("address".equals(approveData.getFieldName())) {
					approveData.setChangerName("客户:"
							+ member.getCustName()
							+ ":接送货地址:详细地址"
							+ (shuttleAddress.getAddress() == null ? "" : ":"
									+ shuttleAddress.getAddress()));
					break;
				}
			}
		}
		if (StringUtils.isEmpty(approveData.getChangerName())) {
			approveData.setChangerName("新增接送货地址");
		}
	}
	/**
	 * 
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberId
	 * @param workflowId
	 * @return
	 *
	 */
	@Override
	public MemberExaminView createSepMemberExaminView(String memberId,
			long workflowId) {
		Member member = alterMemberService.getMemberAllById(memberId);

		List<ExamineRecord> examineRecordList = examineRecordService
				.getExamineRecordByWorkflowId(workflowId);
		MemberExaminView view = new MemberExaminView();
		view.setMember(member);
		view.setExamineRecordList(examineRecordList);
		view.setCurrentExaminer(examineRecordService
				.getCurrentPeople(workflowId));

		return view;
	}
	/**
	 * 
	 * <p>
	 * Description:根据客户编码查询客户信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param custNumber
	 * @return
	 *
	 */
	@Override
	public Member getMemberByCustNumber(String custNumber) {
		if (ValidateUtil.objectIsEmpty(custNumber)) {
			return null;
		}
	
		MemberCondition condition = new MemberCondition();
		condition.setCustNumber(custNumber);
		List<Member> list = alterMemberService
				.searchMemberByCondition(condition);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:根据部门id列表查询客户列表<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param deptIds
	 * @return
	 *
	 */
	@Override
	public List<Member> getMemberIdListBydeptIds(String[] deptIds) {
		List<Member> memberList = new ArrayList<Member>();
		for (String deptId : deptIds) {
			MemberCondition condition = new MemberCondition();
			condition.setDeptId(deptId);
			memberList.addAll(alterMemberService
					.searchMemberByCondition(condition));
		}
		return memberList;
	}

	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}

	/**
	 * 
	 * <p>
	 * Description:根据会员Id查询三个联系人，主联系人第一<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public List<Contact> get3ContactByOrder(String memberId) {
		List<Contact> list = new ArrayList<Contact>();
		List<Contact> orderedList = new ArrayList<Contact>();
		if (!StringUtils.isEmpty(memberId)) {
			list = alterMemberService.searchContactByMemberId(memberId);
		}
		if (list.size() < 3) {
			orderedList = list;
		} else {
			for (int i = 0; i < 3; i++) {
				orderedList.add(list.get(i));
			}
		}
		return orderedList;
	}

	/**
	 * 
	 * <p>
	 * Description:获得客户的所有联系人信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public List<Contact> getContactsByMemberId(String memberId) {
		List<Contact> list = new ArrayList<Contact>();
		if (!StringUtils.isEmpty(memberId)) {
			list = alterMemberService.searchContactByMemberId(memberId);
		}
		return list;
	}

	/**
	 * 	@Override
	 * <p>
	 * Description:根据联系人Id查询联系人的详细信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param contactId
	 * @return
	 *
	 */
	public Contact getContactDetailInfoById(String contactId) {
		Contact contact = new Contact();
		if (!StringUtils.isEmpty(contactId)) {
			contact = alterMemberService.getContactDetailInfoById(contactId);
		}
		return contact;
	}
	/**
	 * 
	 * <p>
	 * Description:归属变更<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param changeMemberDept
	 * @return
	 *
	 */
	@Override
	public long changeMemberDept(ChangeMemberDept changeMemberDept) {
		Assert.notNull(changeMemberDept, "changeMemberDept must not null");
		Assert.notNull(changeMemberDept.getMemberId(), "memberId must not null");
		Assert.notNull(changeMemberDept.getToDeptId(), "todeptId must not null");
		//校验规则
		//潜客不能变更
		Member member = alterMemberService.getMemberById(changeMemberDept.getMemberId());
		if (Constant.CUST_GROUP_PC_CUSTOMER.equals(member.getCustGroup())) {
			throw new CustomerException(CustomerExceptionType.PotentialCustomerCannotChangeDept);
		}
		//疑似重复列表
		String deptName = repeatedCustManager.searchMainCustDeptName(member.getCustNumber());
		if (!StringUtils.isEmpty(deptName)) {
			throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{deptName});
		}
		//审批中工作流
		if(StringUtils.equals(member.getCustStatus(), Constant.Cust_STATUS_WORKFLOW)){
			String workflowId = alterMemberService.getCustWorkflow(changeMemberDept.getMemberId());
			if (!ValidateUtil.objectIsEmpty(workflowId)) {
				throw new CustomerException(CustomerExceptionType.MemberIsExamin,
						new Object[] { workflowId });
			}
			workflowId = repeatedCustManager.searchWorkFlowNoByCustId(member.getCustNumber());
			if (!ValidateUtil.objectIsEmpty(workflowId)) {
				throw new CustomerException(CustomerExceptionType.MemberIsExamin,
						new Object[] { workflowId });
			}
			throw new CustomerException(CustomerExceptionType.custIsExamin);
		}
		
		// 调用工作流
		long workFlowId = memberWorkFlowManager.createChangeMemberDeptWorkFlow(
				changeMemberDept.getMemberId(),
				changeMemberDept.getFromDeptId());

		changeMemberDept.setWorkFlowId(workFlowId);

		// 持久化 changeMemberDept
		changeMemberDeptManager.saveChangeMemberDept(changeMemberDept);
		// 设置状态为审批中
		alterMemberService.alterMemberStatus(changeMemberDept.getMemberId(),
				Constant.Status_Examine);

		return workFlowId;
	}

	@Override
	public void disposeChangeMemberDeptByExamineResult(String memberId,
			String workFLowId, boolean examineResult) {
		//审批工作流的时候校验下 该客户是否已经作废，如果已经作废，就不做任何处理
		Member memb = alterMemberService.getMemberById(memberId);
		if(!ValidateUtil.objectIsEmpty(memb)
				&&Constant.Cust_STATUS_UNEFFECT.equals(memb.getCustStatus())){
			return;
		}
		if (examineResult) {
			// 修改所属部门
			ChangeMemberDept dept = changeMemberDeptManager
					.getChangeMemberDeptByWorkFlowId(workFLowId);
			String changeDept = dept.getToDeptId();
			Member member = new Member();
			member.setId(dept.getMemberId());
			member.setDeptId(changeDept);
			alterMemberService.updateMember(member);
		}
		// 修改会员状态为正常
		alterMemberService.alterMemberStatus(memberId, Constant.Status_Normal);

	}
	/**
	 * 
	 * <p>
	 * Description:查询归属部门变更工作流信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param workFlowId
	 * @return
	 *
	 */
	@Override
	public ChangeMemberDeptView getChangeMemberDeptView(long workFlowId) {
		ChangeMemberDeptView view = new ChangeMemberDeptView();
		view.setChangeMemberDept(changeMemberDeptManager
				.getChangeMemberDeptByWorkFlowId(String.valueOf(workFlowId)));
		view.setExamineRecordList(examineRecordService
				.getExamineRecordByWorkflowId(workFlowId));
		view.setCurrentExaminer(examineRecordService
				.getCurrentPeople(workFlowId));
		return view;

	}
	/**
	 * 
	 * <p>
	 * Description:校验联系人编码是否存在<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param linkManNumber
	 * @return
	 *
	 */
	@Override
	public boolean checkLinkManNumberCanUse(String linkManNumber) {
		return !this.isExistLinkManMuber(linkManNumber);
	}
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
	@Override
	public boolean checkIdCardIsLegal(String idCard, String cardTypeCon) {
		try {
			return MemberEffectValidate.validate(idCard,
					Certification.getCertification(cardTypeCon));
		} catch (MemberException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
				private static final long serialVersionUID = 1L;
			};
		}
	}
	/**
	 * 
	 * <p>
	 * Description:根据id删除散客升级<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param upGradeCustId
	 *
	 */
	@Override
	public void deleteScatterUpgradeById(String upGradeCustId) {
		this.memberService.deleteScatterUpgradeById(upGradeCustId);
	}
	/**
	 * 
	 * <p>
	 * Description:根据电话和姓名查询客户信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param tel
	 * @param name
	 * @return
	 *
	 */
	@Override
	public Member getMemberInfoByTelAndName(String tel, String name) {
		MemberCondition condition = new MemberCondition();
		condition.setTelePhone(tel);
		condition.setLinkManName(name);
		List<Member> list = alterMemberService
				.searchMemberByCondition(condition);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:校验税务登记号<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月14日
	 * @param location
	 * @param enterpriseMember
	 * @return
	 *
	 */
	@Override
	public boolean checkTaxregNumberCanUseForSpecialMember(String location,
			String enterpriseMember) {
		if( CustomerValidator.checkTaxregNumberCanUseForSpecialMember(
				location, enterpriseMember)){
			this.checkTaxregNumber(enterpriseMember);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新散客和固定客户的财务冻结字段<br />
	 * </p>
	 * 
	 * @author 潘光均
	 * @version 0.1 2013-5-7
	 * @return Member
	 */
	@Override
	public void changeCustFinStatus() {
		// 固定客户
		List<String> custNumber = alterMemberService.findInvalidCustNumber();
		// 50个一组发送到FOSS获取客户财务状态集合
		List<String> sendNumber = null;
		// 需要更新客户状态的客户状态编码集合
		List<String> updateCustStatus = null;
		if (CollectionUtils.isNotEmpty(custNumber)) {
			sendNumber = new ArrayList<String>();
			for (int i = 0; i < custNumber.size(); i++) {
				sendNumber.add(custNumber.get(i));
				// 满足50个或者是集合的最大值则请求借口
				if ((i + 1) % 50 == 0 || i + 1 == custNumber.size()) {
					List<CustomerCancel> custStatus = null;
					try {
						custStatus = customerOperate
								.queryCustomerCancelOrNot(sendNumber);
					} catch (CrmBusinessException e) {
						log.error(e.getMessage(), e);
					}
					if (CollectionUtils.isNotEmpty(custStatus)) {
						updateCustStatus = new ArrayList<String>();
						for (int j = 0; j < custStatus.size(); j++) {
							// 如果财务状态为已作废则添加到需要更新客户财务冻结状态
							if (custStatus.get(j).isCustomerBlankOut()) {
								updateCustStatus.add(sendNumber.get(j));
							}
						}
						// 情况发送集合，方便收集下次数据
						sendNumber.clear();
						// 根据客户财务状态
						alterMemberService.updateMemberFinStatus(
								updateCustStatus, true);
					}
				}
			}
		}
		
	}
	
	/**
	 * @Descript 得到部门中失效一年的散客
	 * @author 李学兴
	 * @version 0.1 2012-5-25
	 * @param 无
	 * @return 散客集合
	 */
	@Override
	public List<Member> getNotDevpPlanAndSysmakelsLoseList(Date date,
			String dept,String custType) {
		return memberService.getNotDevpPlanAndSysmakelsLoseList(date,dept,custType);
	}

	/**
	 * @Descript 得到部门中一年没有开发过的潜散客部门列表
	 * @author 李学兴
	 * @version 0.1 2012-5-25
	 * @param 无
	 * @return 散客集合
	 */
	@Override
	public List<String> getNotDevpPlanAndSysmakelsLoseDeptList(Date date,String custType) {
		return memberService.getNotDevpPlanAndSysmakelsLoseDeptList(date,custType);
	}
	/**
	 * @Descript 得到部门中失效一年的潜客
	 * @author 李学兴
	 * @version 0.1 2012-5-25
	 * @param 无
	 * @return 散客集合
	 */
	@Override
	public List<Member> getScatterCustLoselsAndOneYear(Date date,String deptId,String custType) {
		return memberService.getScatterCustLoselsAndOneYear(date,deptId,custType);
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
		return memberService.getScatterCustDeptLoselsAndOneYear(date,custType);
	}

	/**
	 * 
	 * <p>
	 * Description:创建渠道用户<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @param cust
	 *            :渠道客户信息 void
	 */
	@Override
	public Map<String,String> createChannelCutomer(ChannelCustomer cust) {
		// 渠道客户信息不为空，渠道来源字段不为空
		if (ValidateUtil.objectIsEmpty(cust)
				|| ValidateUtil.objectIsEmpty(cust.getChannelSource())) {
			throw new CustomerException(
					CustomerExceptionType.ChannelCustomer_DATANULL);
		}
		if (Constant.CUST_SOURCE_OWS.equals(cust.getChannelSource())) {
			return this.createOwsCustomer(cust);
		} else if (Constant.CUST_SOURCE_CC.equals(cust.getChannelSource())) {
			return this.createCCCustomer(cust);
		} else {
			throw new CustomerException(
					CustomerExceptionType.ChannelCustomer_DATANULL);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:修改渠道客户<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @param cust
	 *            :渠道客户信息 void
	 */
	@Override
	public String updateChannelCustomer(ChannelCustomer cust) {
		if (ValidateUtil.objectIsEmpty(cust)
				|| ValidateUtil.objectIsEmpty(cust.getChannelSource())) {
			throw new CustomerException(
					CustomerExceptionType.ChannelCustomer_DATANULL);
		}
		if (Constant.CUST_SOURCE_OWS.equals(cust.getChannelSource())) {
			return this.updateOwsCustomer(cust);
		} else if (Constant.CUST_SOURCE_CC.equals(cust.getChannelSource())) {
			return this.updateCCCustomer(cust);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:创建官网客户信息<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-3
	 * @param cust
	 *            :官网用户实体 void
	 */
	private Map<String,String> createOwsCustomer(ChannelCustomer cust) {
		//返回结果
		Map<String,String> owsMap = new HashMap<String, String>();
		if (ValidateUtil.objectIsEmpty(cust.getMobilePhone())
				&& (
					ValidateUtil.objectIsEmpty(cust.getLinkManName()) || ValidateUtil
					.objectIsEmpty(cust.getTelPhone()) 
				)) {
			throw new IllegalArgumentException("手机，姓名，电话，值不全,参数调用不合法");
		}
		// 1、根据手机号到联系人表中查询该用户是否是CRM客户
		// 2、若未查询到，再根据联系人名称+姓名 在联系人表中查询是否为CRM客户
		List<Contact> contactLists = contactManager.searchContactList(
				cust.getMobilePhone(), cust.getTelPhone(),
				cust.getLinkManName());
		// 3、若是CRM客户，根据官网用户名+渠道（官网）/官网用户名+固话+渠道（官网） ，
		// 查询是否在联系人绑定表中已经绑定，若已经绑定则此次业务流程结束
		// 4、若不是CRM客户，则根据用户信息构造客户信息，保存
		// 5、联系人名称为客户名称，客户性质为“潜客”，客户属性为”出发客户“，
		// 客户归属部门为“电销管理小组”，客户编码系统自动生成，省市为传递省市县，地址为传递地址
		// 客户类别为：“零担”，是否特殊客户为“否”，潜客来源为“官网”
		// 6、接送货地址为传递的省市县和地址 地址类型为“接货地址”，
		// 7、联系人包括 联系人名称，固话，手机，官网id，偏好地址为新增的接送货地址，是否为主地址为“是”，其他字段默认为空
		// 8、为改客户记录一条创建日志
		// 根据联系方式拿到联系人列表
		//isMobileInApprove处于审批中的t_cust_approvingworkflowdata的信息进行校验
		//处于审批中的客户信息不进行插入，由用户手动在CRM界面进行新增
		// 匹配不成功,新增一条客户信息（潜客）
		if(CollectionUtils.isEmpty(contactLists)
					&&!isMobileInApprove(cust.getMobilePhone())
					&&!isTelInApprove(cust.getLinkManName(),cust.getTelPhone())){
			//新增客户信息
			//TODO  已经解绑的仍然存在于绑定表中，如果手机号相同 是去重新绑定还是新增一条。。
			//TODO??? 保存官网用户的时候需不需要去绑定下联系人（因为官网手动绑定已经去掉了）AND 创建成功绑定失败呢？
			 Member member = this.produceMember(cust, Constant.CUST_SOURCE_OWS);
			 if(!ValidateUtil.objectIsEmpty(member)){
				 owsMap.put("userName", cust.getCustName());
				 String contactId = null;
				 if(!CollectionUtils.isEmpty(member.getContactList())
						 &&!ValidateUtil.objectIsEmpty(member.getContactList().get(0))){
					 Contact contact = member.getContactList().get(0);
					 contactId = contact.getId();
					//绑定官网用户联系人
					this.createOWSBind(cust, contact);
					//创建扩展信息，保存开发阶段
					createMemberExtendAndDevelopment(member);
					//处理商机阶段
					saveBusinessOpportunity(member);
				 }
				 CustomerInfoLog.commit();
				 owsMap.put("contactId",contactId);
				 owsMap.put("custNumber", member.getCustNumber());
			 }else{
				 throw new MemberException(MemberExceptionType.createMemberFail);
			 }
		}
		else {
			//如果在审批中，系统不进行自动绑定，由用户手动绑定,也不需要返回联系人ID
			//有效的才返回联系人ID和用户名
			if (!CollectionUtils.isEmpty(contactLists)
					&& Constant.Cust_STATUS_EFFECT.equals(contactLists.get(0)
							.getMember().getCustStatus())) {
				Contact contact = contactLists.get(0);
				// 绑定官网用户联系人
				this.createOWSBind(cust, contact);
				owsMap.put("userName", cust.getCustName());
				owsMap.put("contactId", contact.getId());
			}
			else{
				throw new MemberException(MemberExceptionType.createMemberFail);
			}
		}
		return owsMap;
	}
	/**
	 * 
	* @Title: createMemberExtendAndDevelopment
	* @Description: 创建修改客户时 开发阶段处理方法
	* @author chenaichun 
	* @param @param member    设定文件
	* @date 2014年5月10日 上午10:10:17
	* @return void    返回类型
	* @throws
	* @update 2014年5月10日 上午10:10:17
	 */
	@Override
	public void createMemberExtendAndDevelopment(Member member) {
		MemberExtend memberExtend = member.getMemberExtend();
		if(ValidateUtil.objectIsEmpty(memberExtend)){
			memberExtend = new MemberExtend();
			memberExtend.setCustId(member.getId());
			this.updateExtendAndLog(member, memberExtend, Constant.OPERATOR_TYPE_INSERT);
		}else{
			memberExtend.setCustId(member.getId());
			this.updateExtendAndLog(member, memberExtend, Constant.OPERATOR_TYPE_UPDATE);
		}
	}
	/**
	 * 
	* @Title: createOWSBind
	* @Description: 绑定官网用户联系人
	* @author chenaichun 
	* @param @param cust
	* @param @param contact    设定文件
	* @date 2014年5月6日 下午6:26:13
	* @return void    返回类型
	* @throws
	* @update 2014年5月6日 下午6:26:13
	 */
	public void createOWSBind(ChannelCustomer cust, Contact contact) {
		RegisterInfo registerinfo = new RegisterInfo();
		BeanUtils.copyProperties(contact, registerinfo);
		registerinfo.setCusCode(contact.getId());
		registerinfo.setFixedPhone(contact.getTelPhone());
		registerinfo.setTelephone(contact.getMobilePhone());
		registerinfo.setAddress(cust.getAddress());
		registerinfo.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		//官网用户名
		registerinfo.setUserName(cust.getCustName());
		contactManager.boundContactForOnline(registerinfo,
				Constant.BOUNDING_CONTACT);
	}

	/**
	 * 
	 * <p>
	 * Description:创建CC用户<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @param cust
	 *            :cc客户 void
	 */
	private Map<String,String> createCCCustomer(ChannelCustomer cust) {
		//返回结果
		Map<String,String> owsMap = new HashMap<String, String>();
		// 1、根据手机号到联系人表中查询该用户是否是CRM客户
		// 2、若未查询到，再根据联系人名称+姓名 在联系人表中查询是否为CRM客户
		List<Contact> contactList = cust.getContactList();
		
		// 4、若不是CRM客户，则根据用户信息构造客户信息，保存
		// 5、联系人名称为客户名称，客户等级为“普通客户”，客户性质为“潜客”，客户属性为”出发客户“，
		// 客户归属部门为“电销管理小组”，客户编码系统自动生成，省市为传递省市县，地址为传递地址
		// 客户类别为：“零担”，是否特殊客户为“否”，来源渠道为“网上营业厅”
		// 6、接送货地址为传递的省市县和地址 地址类型为“接货地址”，
		// 7、联系人包括 联系人名称，固话，手机，官网id，偏好地址为新增的接送货地址，是否为主地址为“是”，其他字段默认为空
		// 8、为改客户记录一条创建日志
		if(!CollectionUtils.isEmpty(contactList)&&isContactsAllNotExist(contactList)){
			//获取电销管理小组的部门ID和部门名称
			Department dept = departmentService.getDeptByStandardCode(Constant.CALL_SALE_STANDCODE);
			// 初始化客户和联系人
			//设置客户默认值
			Member member = CustomerRule.getDefaultMember();
			member.setDeptId(dept.getId());
			member.setDeptName(dept.getDeptName());
			contactList.get(0).setIsMainLinkMan(true);
			member.setContactList(contactList);
			// 设置CC同步过来的数据以及默认值
			member = this.makeDefaultMemberPro(member, cust,
					Constant.CUST_SOURCE_CC);
			if(CustomerValidator.validateMemberForm(member)
					&& this.validateCreateMember(member)
					&& this.checkEnterpriseTaxregNumber(member)){
				memberService.createMember(member);
				//记录操作日志
				MemberOperationLog log = CustomerRule.createLog(member
						.getId(),Constant.OPERATOR_TYPE_INSERT);
//				log.setOperateType(Constant.OPERATOR_TYPE_INSERT);
				log.setEffect(true);
				alterMemberService.saveMemberOperationLog(log);
				//创建扩展信息，保存开发阶段
				createMemberExtendAndDevelopment(member);
				CustomerInfoLog.commit();
				owsMap.put("message", "success");
				owsMap.put("custNumber", member.getCustNumber());
			}
			else{
				owsMap.put("message", "fail");
			}
		}//联系人存在，保存失败
		else{
			owsMap.put("message", "fail");
			log.info("CC customer is aready crm customer, save it fail!");
		}
		return owsMap;
	}

	/**
	 * 
	* @Title: produceMember
	* @Description: 创建官网客户（潜客）,foss散客
	* @author chenaichun 
	* @param @param cust
	* @param @param channalSource    设定文件
	* @date 2014-3-10 上午11:27:47
	* @return void    返回类型
	* @throws
	* @update 2014-3-10 上午11:27:47
	 */
	private Member produceMember(ChannelCustomer cust,
			String channalSource){
		//官网创建时，部门为空，默认设置电销管理小组，foss必传
		if(ValidateUtil.objectIsEmpty(cust.getDeptStandCode())){
			cust.setDeptStandCode(Constant.CALL_SALE_STANDCODE);
		}
		Department dept = departmentService.getDeptByStandardCode(cust.getDeptStandCode());
		// 初始化客户和联系人
		Member member = CustomerRule.initImplementMember(
				cust.getMobilePhone(), cust.getTelPhone(),
				cust.getLinkManName(),cust.getEmail(), dept.getId(), dept.getDeptName());
		// 设置一些官网同步过来的数据以及默认值（还有foss）
		member = this.makeDefaultMemberPro(member, cust,
				channalSource);
		if(CustomerValidator.validateMemberForm(member)
				&& this.validateCreateMember(member)
				&& this.checkEnterpriseTaxregNumber(member)){
			memberService.createMember(member);
			//记录操作日志
			MemberOperationLog log = CustomerRule.createLog(member
					.getId(),Constant.OPERATOR_TYPE_INSERT);
//			log.setOperateType(Constant.OPERATOR_TYPE_INSERT);
			log.setEffect(true);
			alterMemberService.saveMemberOperationLog(log);
			return member;
		}
		return null;
	}
	/**
	 * 
	 * @Title: makeDefaultMemberPro
	 * @Description: 用来初始化和创建官网和CC同步过来的客户数据(默认潜客),foss默认散客
	 * @author chenaichun
	 * @param @param member
	 * @param @param cust
	 * @param @param channalSource
	 * @param @return 设定文件
	 * @date 2014-3-7 下午4:13:48
	 * @return Member 返回类型
	 * @throws
	 * @update 2014-3-7 下午4:13:48
	 */
	private Member makeDefaultMemberPro(Member member, ChannelCustomer cust,
			String channalSource) {
		// 系统自动生成客户编码
		// 获得会员序列的下一个id值
		
		String memberId = member.getId();
		if(StringUtils.isEmpty(memberId)){
			memberId = memberService.getMemberIdUseSeq();
		}
		member.setId(memberId);
		// 根据会员序列id，生成会员编号 foss 会有客户编码
		if(ValidateUtil.objectIsEmpty(cust.getCustNumber())){
			cust.setCustNumber(CustomerRule.createCustNumberByMembrId(memberId));
		}
		member.setCustNumber(cust.getCustNumber());
		// 客户的省市城(官网可以为空）
		if(StringUtils.isEmpty(cust.getProvince())||StringUtils.isEmpty(cust.getCity())
				||StringUtils.isEmpty(cust.getProvinceName())||StringUtils.isEmpty(cust.getCityName())){
			BussinessDept busDept = departmentManager.getBusDeptById(member.getDeptId());
			cust.setProvince(busDept.getProvince().getId());
			cust.setProvinceName(busDept.getProvince().getName());
			cust.setCity(busDept.getCity().getId());
			cust.setCityName(busDept.getCity().getName());
		}
		member.setProvinceId(cust.getProvince());
		member.setProvince(cust.getProvinceName());
		member.setCityId(cust.getCity());
		member.setCity(cust.getCityName());
//		member.setAreaId(cust.getArea());
		//foss不为空
		member.setErpId(cust.getErpId());
		// 行业，二级行业
		member.setTradeId(cust.getTradeId());
		member.setSecondTrade(cust.getSecondTrade());
		// 偏好渠道
		member.setChannel(cust.getChannel());
		// 偏好服务
		member.setPreferenceService(cust.getPreferenceService());
		// 来源渠道(CC 和官网、foss)(即客户来源)
		member.setChannelSource(channalSource);
		//客户类型(CC会传，官网和foss不传 默认为个人)
		if (StringUtils.isEmpty(cust.getCustType())) {
			// 客户类型(官网和foss默认为个人）
			cust.setCustType(Constant.Person_Member);
		}
		member.setCustType(cust.getCustType());
		// 客户类别(即官网客户业务类型 默认为零担，CC和Foss都会传客户类别)
		if(StringUtils.isEmpty(cust.getCustCategory())){
			cust.setCustCategory(Constant.CUST_BUSTYPE_EXANDLD);
		}
		member.setCustCategory(cust.getCustCategory());
		// 客户属性(官网和CC默认出发客户，foss会传)
		if(StringUtils.isEmpty(cust.getCustNature())){
			cust.setCustNature(Constant.LEAVE_ARRIVE_CUSTOMER);
		}
		member.setCustNature(cust.getCustNature());
		// 是否财务作废（否）
		member.setFinOver(false);
		List<Contact> contactList = member.getContactList();
		//CC多条(cust.contactlist )，官网和foss只有一个(邮箱和ffax等单独设置)
		for(Contact conta :contactList){
			// CC联系人名称 为空则默认匿名联系人
			if (StringUtils.equals(cust.getChannelSource(), Constant.CUST_SOURCE_CC)){
				if(StringUtils.isEmpty(conta.getName())) {
					conta.setName(Constant.CONTACT_NONAME);
				}
			}//官网和foss 使用的是linkmanName字段
			else {
				if(StringUtils.isEmpty(cust.getLinkManName())){
					conta.setName(Constant.CONTACT_NONAME);
				}else{
					conta.setName(cust.getLinkManName());
				}
				
			}
			//生成联系人编码
			MemberUtil.produceContactNum(conta);
			//设置联系人的类型默认为业务联系人
			conta.setLinkmanType(CustomerRule.addContactType(Constant.defaultLinkManType,
					Constant.ContactType_Business));
			//将CC非主联系人的的类型设置为否
			if(ValidateUtil.objectIsEmpty(conta.getIsMainLinkMan())){
				conta.setIsMainLinkMan(false);
			}
		}
		//主联系人
		Contact con = CustomerRule.getMainContact(contactList);
		// 官网同步的custname里面是官网注册的用户名,所以使用联系人名称
		//foss过来的客户名称不会为空,为空的话就将客户名称设置为主联系人的名称
		//CC会传客户名称，如果为空 也是用联系人名称
		if (Constant.CUST_SOURCE_OWS.equals(channalSource)||StringUtils.isEmpty(cust.getCustName())) {
			// 客户名称
			member.setCustName(con.getName());
		}else{
			member.setCustName(cust.getCustName());
		}
		//客户地址
		if(StringUtils.isEmpty(cust.getAddress())){
			cust.setAddress(cust.getProvinceName()+"-"+cust.getCityName()+"-");
		}
		//客户详细地址
		member.setRegistAddress(cust.getAddress());
		// 接送货地址
		member.setJshAddress(cust.getAddress());
		member.setContactList(contactList);
		String createUser = ContextUtil.getCreateOrModifyUserId();
		member.setCreateUser(createUser);
		//foss创建为散客，官网和CC创建客户性质为潜客
		if(Constant.CUST_SOURCE_Hall.equals(channalSource)){
			if(StringUtils.equals(cust.getCustNature(), Constant.ArriveCustomer)){
				member.setChannelSource(Constant.ARRIVE_SCATTER);
			}
			member.setCustGroup(Constant.CUST_GROUP_SC_CUSTOMER);
		}else{
			member.setCustGroup(Constant.CUST_GROUP_PC_CUSTOMER);
		}
		/**
		 * 设置会员状态为正常（包括联系人，地址等等的状态）
		 */
		CustomerRule.setMemberStatus(member, Constant.Status_Normal);
		return member;
	}

	/**
	 * 
	 * <p>
	 * Description:修改官网用户<br />
	 * </p>
	 * 官网修改不会修改二级行业 潜力类型等字段，因此不会更改客户的开发阶段字段，所以不需要调用开发阶段方法
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @param cust
	 *            :官网用户 void
	 */
	private String updateOwsCustomer(ChannelCustomer cust) {
		// 1、根据官网"用户名"到官网修改暂存表中进行查询，如果存在暂存表中，用此次修改的信息，覆盖前一次修改的信息
		// 此次修改结束
		if(StringUtils.isEmpty(cust.getCustName())){
			log.info("官网用户名为空");
			return "官网用户名为空";
		}
		List<ChannelCustomer> customers = memberService
				.searchChannelCustomerByUserName(cust.getCustName());
		if (null != customers && 0 < customers.size()) {
			if (2 <= customers.size()) {
				log.info("Channel customer more than 2!");
				return "Channel customer more than 2!";
			}
			cust.setId(customers.get(0).getId());
			memberService.updateChannelCustomer(cust);
			return null;
		}
		//根据注册信息（用户名+渠道来源 确定唯一的联系人绑定信息）
		RegisterInfo queryInfo = new RegisterInfo();
		queryInfo.setUserName(cust.getCustName());
		queryInfo.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		List<RegisterInfo> infoList = contactService.searchRegisterInfo(queryInfo);
		if (CollectionUtils.isEmpty(infoList)) {
			log.info("Member manager----con't find registerInfo!");
			return "Member manager----con't find registerInfo!";
		}
		if(infoList.size()>1){
			log.info("more than one registerInfo");
			return "more than one registerInfo";
		}
		RegisterInfo info = infoList.get(0);
		//【重要】 如果官网过来的联系人ID为空（官网用户联系人已经解绑的情况），查出来的member就为空，这个时候就不是修改，而是新增客户了
		//接口做了判断 会根据用户名去绑定表查，如果查到的联系人ID不为空就是修改否则就是新增
		// 2、若不存在暂存表，根据官网用户名到联系人绑定表中进行查询，匹配出该用户对于的客户信息 “sql里面加了来源为官网的限制”
		Member member = memberService
				.searchMemberByUserName(cust.getCustName());
		//根据官网用户名找不到客户信息
		if(ValidateUtil.objectIsEmpty(member)){
			log.info("member can't find");
			return "member can't find";
		}
		// 绑定联系人
		Contact contact = null;
		for (int i = 0; i < member.getContactList().size(); i++) {
			if (member.getContactList().get(i).getId()
					.equals(info.getCusCode())) {
				contact = member.getContactList().get(i);
			}
		}
		if (null == contact) {
			log.info("Member manager----can't find contact info!");
			return "Member manager----can't find contact info!";
		}
		// // 若修改的关键信息被锁定或者客户处于审批中，则将此次修改信息存入暂存表，客户处于审批中
		if (null != member
				&& Constant.Cust_STATUS_WORKFLOW.equals(member.getCustStatus())) {
			memberService.saveChannelCustomer(cust);
			return null;
		}
		// 客户处于疑似重复列表，则放到暂存表中
		//如果修改了疑似重复数据里面的关键信息则清掉客户的疑重标记（在service层最后更新Member时统一处理）
		if (repeatedCustManager.isCustExistsRepeat(member.getId())) {
			memberService.saveChannelCustomer(cust);
			return null;
		}
		// 4、若修改信息为关键信息，则根据修改的信息去审批锁定记录表中查询，是否修改的关键信息被锁定，
		//    是的话就保存到暂存表中
		// 手机号被锁定
//		if (!StringUtils.isEmpty(cust.getMobilePhone())) {
			if (isMobileInApprove(cust.getMobilePhone())) {
				memberService.saveChannelCustomer(cust);
				return null;
			}
//		}
		// 固定电话+姓名被锁定
//		if (!StringUtils.isEmpty(cust.getTelPhone())
//				&& !StringUtils.isEmpty(cust.getLinkManName())) {
			if (isTelInApprove(cust.getLinkManName(),cust.getTelPhone())) {
				memberService.saveChannelCustomer(cust);
				return null;
			}
//		}
		List<ApproveData> updateDataAllList = produceApproveData(cust, member,contact);
		// 是否修改了关键信息
		boolean isModifyKeyInfo = isModifyKeyInfo(member, cust, contact,
				Constant.CUST_SOURCE_OWS);
		/* 校验要修改的手机是否已经存在 */
		List<Contact> contactLists = contactManager.searchContactList(
				cust.getMobilePhone(), cust.getTelPhone(),
				cust.getLinkManName());
		// 修改的联系方式是否存在，若存在，此次修改结束  加了个空判断
		if(!CollectionUtils.isEmpty(contactLists)){
			for (int i = 0; i < contactLists.size(); i++) {
				if (!contact.getId().equals(contactLists.get(i).getId())) {
					return "联系方式相同，不允许修改";
				}
			}
		}
		
		MemberOperationLog oprerateLog = CustomerRule.createLog(member
				.getId(),Constant.OPERATOR_TYPE_UPDATE);
		// 潜客修改关键信息不产生工作流 
		// 如果没有修改关键信息，则修改地址时，需要修改联系人偏好地址，接送或地址 ，此次修改结束
		if (!isModifyKeyInfo||StringUtils.equals(Constant.CUST_GROUP_PC_CUSTOMER, member.getCustGroup())) {
			// 3、检验修改信息是否为客户关键信息，若不为关键信息，则直接修改客户信息，
			//未启动工作流则直接修改
			alterMemberService.updateMemberInfo(updateDataAllList, true);
			CustomerInfoLog.commit();
		//修改了关键信息
		} else {
			// 5、若没有被锁定，客户状态也不是审批中，则启动客户修改工作流
			// 4、调用worflowManager启动工作流
			Set<String> dataTypes = new HashSet<String>();
			// 获取需启动工作流的类型,并启动工作流
			for (ApproveData approveData : updateDataAllList) {
				// 通过类名和字段名获取工作流类型
				String workFlowType = alterMemberService.getApproveType(
						approveData.getClassName(), approveData.getFieldName());
				if (!ValidateUtil.objectIsEmpty(workFlowType)) {
					dataTypes.add(workFlowType);
				}
			}
			//官网修改，肯定经过营业部经理审批
			addDataTypes(member, dataTypes);
			long workFlowId = memberWorkFlowManager
					.createNewModifyMemberWorkFlow(dataTypes, member.getId(),updateDataAllList);
			for (ApproveData approveData : updateDataAllList) {
				approveData.setWorkFlowId(String.valueOf(workFlowId));
			}
			// 2、将修改的联系人关键信息构造为ApprovingWorkflowData 锁定将要修改需要启动工作流的关键信息，并保存
			Set<ApprovingWorkflowData> contactWorkflowDatas = CustomerRule
					.lockApproveData(updateDataAllList, member.getId(),
							workFlowId, member, member.getContactList());
			// 保存审批中中不可用的联系人信息
			approvingWokflowDataService
			.batchCreateContactWorkflowData(contactWorkflowDatas);
			// 启动工作流后保存审批数据，修改会员状态为[审批中]
			alterMemberService.alterMemberStatus(member.getId(), Constant.Cust_STATUS_WORKFLOW);
			// 审批中的修改记录 先为失效数据
			oprerateLog.setWorkflowId(String.valueOf(workFlowId));
			oprerateLog.setEffect(false);
		}
		// 5、记录客户修改日志信息 构造MemberOperationLog对象，并保存
		alterMemberService.saveMemberOperationLog(oprerateLog);
		// 持久化approveData
		String logId = oprerateLog.getId();
		alterMemberService.saveApproveData(updateDataAllList, logId);
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:封装修改前后数据<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param cust
	 * @param member
	 * @param mainPreferAddress
	 * @param shuttleAddress
	 * @return List<ApproveData>
	 */
	private List<ApproveData> produceApproveData(ChannelCustomer cust,
			Member member,Contact contact) {
		// 1、将修改的信息构造为ApproveData 对象，并保存
		List<ApproveData> updateDataAllList = new ArrayList<ApproveData>();

		if (!StringUtils.equals(contact.getEmail(), cust.getEmail())) {
			updateDataAllList.add(MemberUtil.proApproveData(contact.getEmail(),
					cust.getEmail(), contact.getId(),
					Contact.class.getSimpleName(),"email"));
		}
		if (!StringUtils.equals(contact.getMobilePhone(), cust.getMobilePhone())) {
			updateDataAllList.add(MemberUtil.proApproveData(contact.getMobilePhone(),
					cust.getMobilePhone(), contact.getId(),
					Contact.class.getSimpleName(),"mobilePhone"));
		}
		
		if (!StringUtils.equals(contact.getTelPhone(), cust.getTelPhone())) {
			updateDataAllList.add(MemberUtil.proApproveData(contact.getTelPhone(),
					cust.getTelPhone(), contact.getId(),
					Contact.class.getSimpleName(),"telPhone"));
		}
		if (!StringUtils.equals(contact.getName(), cust.getLinkManName())) {
			updateDataAllList.add(MemberUtil.proApproveData(contact.getName(),
					cust.getLinkManName(), contact.getId(),
					Contact.class.getSimpleName(),"name"));
		}
		if (!StringUtils.equals(member.getRegistAddress(), cust.getAddress())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getRegistAddress(),
					cust.getAddress(), member.getId(),
					Member.class.getSimpleName(),"registAddress"));
		}
		if (!StringUtils.equals(member.getProvinceId(),cust.getProvince())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getProvinceId(),
					cust.getProvince(), member.getId(),
					Member.class.getSimpleName(),"provinceId"));
		}
		if (!StringUtils.equals(member.getProvince(),cust.getProvinceName())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getProvince(),
					cust.getProvinceName(), member.getId(),
					Member.class.getSimpleName(),"province"));
		}
		if (!StringUtils.equals(member.getCity(),cust.getCityName())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getCity(),
					cust.getCityName(), member.getId(),
					Member.class.getSimpleName(),"city"));
		}
		if (!StringUtils.equals(member.getCityId(), cust.getCity())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getCityId(),
					cust.getCity(), member.getId(),
					Member.class.getSimpleName(),"cityId"));
		}
		return updateDataAllList;
	}
	/**
	 * 
	* @Title: produceCCApproveData
	* @Description: 保存CC修改信息
	* @author chenaichun 
	* @param @param cust
	* @param @param member
	* @param @param mainPreferAddress
	* @param @param shuttleAddress
	* @param @return    设定文件
	* @date 2014年4月4日 上午10:40:09
	* @return List<ApproveData>    返回类型
	* @throws
	* @update 2014年4月4日 上午10:40:09
	 */
	private List<ApproveData> produceCCApproveData(ChannelCustomer cust,
			Member member) {
		// 1、将修改的信息构造为ApproveData 对象，并保存
		List<ApproveData> updateDataAllList = new ArrayList<ApproveData>();
		if (!StringUtils.equals(member.getErpId(), cust.getErpId())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getErpId(),
					cust.getErpId(), member.getId(),
					Member.class.getSimpleName(),"erpId"));
		}
		if (!StringUtils.equals(member.getCustName(), cust.getCustName())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getCustName(),
					cust.getCustName(), member.getId(),
					Member.class.getSimpleName(),"custName"));
		}
		if (!StringUtils.equals(member.getChannel(), cust.getChannel())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getChannel(),
					cust.getChannel(), member.getId(),
					Member.class.getSimpleName(),"channel"));
		}
		if (!StringUtils.equals(member.getPreferenceService(),
				cust.getPreferenceService())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getPreferenceService(),
					cust.getPreferenceService(), member.getId(),
					Member.class.getSimpleName(),"preferenceService"));
		}
		if (!StringUtils.equals(member.getCustType(), cust.getCustType())) {
			updateDataAllList.add(MemberUtil.proApproveData(member.getCustType(),
					cust.getCustType(), member.getId(),
					Member.class.getSimpleName(),"custType"));
		}
		//获得联系人修改信息（只有修改的，新增和删除的都没有）
		List<ApproveData> alist = MemberUtil.createContactListApproveData(member.getContactList(), cust.getContactList());
		updateDataAllList.addAll(alist);
		return updateDataAllList;
	}

	/**
	 * <p>
	 * Description:是否修改关键信息<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-7
	 * @param member
	 * @param cust
	 * @param string
	 * @return boolean
	 */
	private boolean isModifyKeyInfo(Member member, ChannelCustomer cust,
			Contact contact, String source) {
		// 官网用户绑定联系人
		if (cust == null && member == null) {
			return false;
		} else if (null == cust && null != member) {
			return true;
		} else if (null != cust && null == member) {
			return true;
			//cust、 member都不为空时
			//官网 比较关键信息是否修改
		} else if(Constant.CUST_SOURCE_OWS.equals(source)){
				if (!StringUtils.equals(contact.getName(),cust.getLinkManName())) {
					return true;
				}
				if (!StringUtils.equals(contact.getMobilePhone(),cust.getMobilePhone())) {
					return true;
				}
				if (!StringUtils.equals(contact.getTelPhone(),cust.getTelPhone())) {
					return true;
				}
		}else{//CC比较关键信息是否修改
			// 在这里应该之比较关键信息和ID
				//联系人信息
				if(!MemberUtil.isCCContactListEquals(member.getContactList(),cust.getContactList())){
					return true;
				}
				if (!StringUtils.isEmpty(member.getCustName())
						&& !member.getCustName().equals(cust.getCustName())) {
					return true;
				}
				if (!StringUtils.isEmpty(member.getCustType())
						&& !member.getCustType().equals(cust.getCustType())) {
					return true;
				}
				
			}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:修改CC客户<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-4
	 * @param cust
	 *            :cc客户 void
	 */
	@SuppressWarnings("rawtypes")
	private String updateCCCustomer(ChannelCustomer cust) {
		// 根据CC传来的客户编码查询客户信息 
		Member member = this.getMemberByCustNumber(cust.getCustNumber());
		// 若修改的关键信息被锁定或者客户处于审批中，则将此次修改信息存入暂存表，客户处于审批中
		if (null != member
				&& Constant.Cust_STATUS_WORKFLOW.equals(member.getCustStatus())) {
			return "客户正在审批中！";
		}
		// 客户处于疑似重复列表
		//注：如果最后修改了疑似重复数据里面的关键信息则清掉客户的疑重标记（在service层最后更新Member时统一处理）
		String deptName = repeatedCustManager.searchMainCustDeptName(member.getCustNumber());
		if (!StringUtils.isEmpty(deptName)) {
			memberService.saveChannelCustomer(cust);
			return "客户正处理疑似重复列表！在部门"+deptName+"的疑重列表中";
		}
		// 4、若修改信息为关键信息，则根据修改的信息去审批锁定记录表中查询，是否修改的关键信息被锁定，
		// 手机号被锁定
		List<Contact> contactList = cust.getContactList();
		Map<String,List> addPojoMap = new HashMap<String,List>();
		List<Contact> addContactList = new ArrayList<Contact>();
		if(CollectionUtils.isEmpty(contactList)){
			return "CC 传过来的联系人列表为空！";
		}
		for (Contact con : contactList) {
			//遍历联系人列表
				if ( isMobileInApprove(con.getMobilePhone())) {
					return "修改的手机正被其他客户使用！";
				}
			// 固定电话+姓名被锁定
				if (isTelInApprove(con.getName(),con.getTelPhone())) {
					return "修改的固话+姓名正被其他客户使用！";
				}
			//@构造新增联系人的列表,如果传过来的联系人的ID为空，说明就是新增的联系人,然后设置联系人类型
			if(StringUtils.isEmpty(con.getId())){
				//设置联系人默认值
				//生成联系人编码
				MemberUtil.produceContactNum(con);
				//设置联系人的类型默认为业务联系人
				con.setLinkmanType(CustomerRule.addContactType(Constant.defaultLinkManType,
						Constant.ContactType_Business));
				//将新加的联系人的的类型设置为否
				con.setIsMainLinkMan(false);
				addContactList.add(con);
			}
		}
		
		//构造新增联系人的列表
		addPojoMap.put(Contact.class.getSimpleName(), addContactList);
		//创建修改信息列表
		List<ApproveData> updateDataAllList = produceCCApproveData(cust, member);
		if(CollectionUtils.isEmpty(addContactList)&&
				CollectionUtils.isEmpty(updateDataAllList)){
			//CC可能删除联系人，然后我们这边相当于没有修改，直接提示成功
			return null;
		}
		alterMemberService.savePojoInfo(addPojoMap, updateDataAllList, member, member.getId());
		// 是否修改关键信息 CC比较的是联系人列表
		boolean isModifyKeyInfo = isModifyKeyInfo(member, cust,
				null, Constant.CUST_SOURCE_CC);
		/**
		 * 校验要修改的手机是否已经存在于其他客户中
		 * 本客户下联系人列表手机号也不允许重复，这个在CC做的控制
		 */
		for(Contact con :contactList){
			List<Contact> existContactLists = contactManager.searchContactList(
					con.getMobilePhone(), con.getTelPhone(),
					con.getName());
			if(!CollectionUtils.isEmpty(existContactLists)){
				for(Contact contact :existContactLists){
					if(!StringUtils.equals(member.getId(),contact.getCustId())){
						return "联系方式与其他客户重复！";
					}
				}
			}
		}
		MemberOperationLog oprerateLog = CustomerRule.createLog(member
				.getId(),Constant.OPERATOR_TYPE_UPDATE);
		// 修改地址时，需要修改联系人偏好地址，接送或地址 ，此次修改结束
		if (!isModifyKeyInfo||StringUtils.equals(member.getCustGroup(), Constant.CUST_GROUP_PC_CUSTOMER)) {
//			// 3、检验修改信息是否为客户关键信息，若不为关键信息，则直接修改客户信息，
//			member.getMainContact().setName(cust.getLinkManName());
//			member.getMainContact().setEmail(cust.getEmail());
			//添加修改方法 @潘光均 检查下
			// 未启动工作流则直接修改
			alterMemberService.updateMemberInfo(updateDataAllList, true);
			CustomerInfoLog.commit();
		} else {
			
			// 5、若没有被锁定，客户状态也不是审批中，则启动客户修改工作流
			// 4、调用worflowManager启动工作流

			Set<String> dataTypes = new HashSet<String>();
			// 获取需启动工作流的类型,并启动工作流
			for (ApproveData approveData : updateDataAllList) {
				// 通过类名和字段名获取工作流类型
				String workFlowType = alterMemberService.getApproveType(
						approveData.getClassName(), approveData.getFieldName());
				if (!ValidateUtil.objectIsEmpty(workFlowType)) {
					dataTypes.add(workFlowType);
				}
			}
			addDataTypes(member, dataTypes);
			if (dataTypes.size() != 0) {
				long workFlowId = memberWorkFlowManager
						.createNewModifyMemberWorkFlow(dataTypes,
								member.getId(),updateDataAllList);
				for (ApproveData approveData : updateDataAllList) {
					approveData.setWorkFlowId(String.valueOf(workFlowId));
				}

				saveOperateLog(member, updateDataAllList, oprerateLog,
						workFlowId);
			}
		}
		// 5、记录客户修改日志信息 构造MemberOperationLog对象，并保存
		alterMemberService.saveMemberOperationLog(oprerateLog);
		// 持久化approveData
		String logId = oprerateLog.getId();
		alterMemberService.saveApproveData(updateDataAllList, logId);
		return null;
	}
	
	/**
	 * 
	 * <p>
	 * Description：保存审批中关键信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param member
	 * @param updateDataAllList
	 * @param oprerateLog
	 * @param workFlowId
	 * void
	 */
	
	private void saveOperateLog(Member member,
			List<ApproveData> updateDataAllList,
			MemberOperationLog oprerateLog, long workFlowId) {
		String result;
		// 2、将修改的联系人关键信息构造为ApprovingWorkflowData 锁定将要修改需要启动工作流的关键信息，并保存
		Set<ApprovingWorkflowData> contactWorkflowDatas = CustomerRule
				.lockApproveData(updateDataAllList, member.getId(),
						workFlowId, member, member.getContactList());
		// 保存审批中中不可用的联系人信息
		approvingWokflowDataService
				.batchCreateContactWorkflowData(contactWorkflowDatas);
		// 启动工作流后保存审批数据，修改会员状态为[审批中]
		alterMemberService.alterMemberStatus(member.getId(), "1");
		result = String.valueOf(workFlowId);
		// 审批中的修改记录 先为失效数据
		oprerateLog.setWorkflowId(result);
		oprerateLog.setEffect(false);
	}

//	/**
//	 * 
//	 * <p>
//	 * Description:偏好地址设置默认值<br />
//	 * </p>
//	 * 
//	 * @author pgjo
//	 * @version 0.1 2014-3-8
//	 * @param cust
//	 * @param sAddress
//	 * @return PreferenceAddress
//	 */
//	private PreferenceAddress producePreferAddress(
//			ShuttleAddress sAddress) {
//		PreferenceAddress pAddress = new PreferenceAddress();
//		pAddress.setAddress(sAddress.getAddress());
//		pAddress.setShuttleAddressId(sAddress.getId());
//		pAddress.setAddressType(sAddress.getAddressType());
//		// 是否有停车费
//		pAddress.setHasStopCost(false);
//		// 是否主联系偏好地址
//		pAddress.setIsMainAddress(true);
//		// 是否送货上楼
//		pAddress.setIsSendToFloor(false);
//		pAddress.setStatus(Constant.Status_Normal);
//		return pAddress;
//	}

	/**
	 * 
	 * <p>
	 * Description:启动官网用户修改工作流<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-4 void
	 */
	@Override
	public void geneCustomerUpdateWorkflow() {
		// 查询需要产生工作流的OWS用户信息
		List<ChannelCustomer> custs = memberService
				.searchNeedWorkflowUpdateCustomer();
		// 构造已经启动了工作流的需要启动工作流信息的OWS用户信息
		List<String> needDeleteIds = new ArrayList<String>();
		for (int i = 0; i < custs.size(); i++) {
			ChannelCustomer cust = custs.get(i);
			
			RegisterInfo queryInfo = new RegisterInfo();
			queryInfo.setUserName(cust.getCustName());
			queryInfo.setCustsource(Constant.ORDER_SOURCE_ONLINE);
			List<RegisterInfo> infoList = contactService.searchRegisterInfo(queryInfo);
			if (CollectionUtils.isEmpty(infoList)) {
				log.info("Member manager----con't find registerInfo!");
				continue;
			}
			if(infoList.size()>1){
				log.info("more than one registerInfo");
				continue;
			}
			RegisterInfo info = infoList.get(0);
			if (ValidateUtil.objectIsEmpty(info)) {
				log.info("Member manager----con't find registerInfo!");
				continue;
			}
			// 2、若不存在暂存表，根据官网用户名到联系人绑定表中进行查询，匹配出该用户对于的客户信息
			Member member = memberService.searchMemberByUserName(cust
					.getCustName());
			//根据官网用户名找不到客户信息
			if(ValidateUtil.objectIsEmpty(member)){
				log.info("member can't find");
				continue;
			}
			// 绑定联系人
			Contact contact = null;
			for (int j = 0; j < member.getContactList().size(); j++) {
				if (member.getContactList().get(j).getId()
						.equals(info.getCusCode())) {
					contact = member.getContactList().get(j);
				}
			}
			if (ValidateUtil.objectIsEmpty(contact)) {
				log.info("Member manager----can't find contact info!");
				continue;
			}
			// // 若修改的关键信息被锁定或者客户处于审批中，则将此次修改信息存入暂存表，客户处于审批中
			if (null != member
					&& Constant.Cust_STATUS_WORKFLOW.equals(member
							.getCustStatus())) {
				continue;
			}
			// 客户处于疑似重复列表
			if (repeatedCustManager.isCustExistsRepeat(member.getId())) {
				continue;
			}
			// 4、若修改信息为关键信息，则根据修改的信息去审批锁定记录表中查询，是否修改的关键信息被锁定，
			// 手机号被锁定
//			if (!StringUtils.isEmpty(cust.getMobilePhone())) {
				if ( isMobileInApprove(cust.getMobilePhone())) {
					continue;
				}
//			}
			// 固定电话+姓名被锁定
//			if (!StringUtils.isEmpty(cust.getTelPhone())
//					&& !StringUtils.isEmpty(cust.getLinkManName())) {
				if (isTelInApprove(cust.getLinkManName(),cust.getTelPhone())) {
					continue;
				}
//			}
			//构建修改休息
			List<ApproveData> updateDataAllList = produceApproveData(cust,
					member,contact);
			/* 校验要修改的手机是否已经存在 */
			List<Contact> contactLists = contactManager.searchContactList(
					cust.getMobilePhone(), cust.getTelPhone(),
					cust.getLinkManName());
			// 修改的联系方式是否存在，若存在，此次修改结束 
			//TODO 这里不应该是return吧
			boolean isContinue=false;
			for (int j = 0; j < contactLists.size(); j++) {
				if (!contact.getId().equals(contactLists.get(j).getId())) {
					isContinue=true;
					break;
				}
			}
			if (isContinue) {
				continue;
			}
			// 5、若没有被锁定，客户状态也不是审批中，则启动客户修改工作流
			Set<String> dataTypes = new HashSet<String>();
			// 获取需启动工作流的类型,并启动工作流
			for (ApproveData approveData : updateDataAllList) {
				// 通过类名和字段名获取工作流类型
				String workFlowType = alterMemberService.getApproveType(
						approveData.getClassName(), approveData.getFieldName());
				if (!ValidateUtil.objectIsEmpty(workFlowType)) {
					dataTypes.add(workFlowType);
				}
			}
			addDataTypes(member, dataTypes);
			MemberOperationLog oprerateLog = CustomerRule.createLog(member
					.getId(),Constant.OPERATOR_TYPE_UPDATE);
			addDataTypes(member, dataTypes);
			if (dataTypes.size() != 0) {
				long workFlowId = memberWorkFlowManager
						.createNewModifyMemberWorkFlow(dataTypes,
								member.getId(),updateDataAllList);
				for (ApproveData approveData : updateDataAllList) {
					approveData.setWorkFlowId(String.valueOf(workFlowId));
				}

				saveOperateLog(member, updateDataAllList, oprerateLog,
						workFlowId);
			}
			//更新客户状态
			alterMemberService.alterMemberStatus(member.getId(), Constant.Cust_STATUS_WORKFLOW);
			// 5、记录客户修改日志信息 构造MemberOperationLog对象，并保存
			alterMemberService.saveMemberOperationLog(oprerateLog);
			// 持久化approveData
			String logId = oprerateLog.getId();
			alterMemberService.saveApproveData(updateDataAllList, logId);
			// 加入待删除列表
			needDeleteIds.add(cust.getId());
		}
		if (!CollectionUtils.isEmpty(needDeleteIds)) {
			// 启动工作流完毕，删除记录在暂存表中的启动客户修改工作流的信息
			memberService.deleteUpdatedWorkflowCustomer(needDeleteIds);
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据业务增删工作流审批类型<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-11
	 * @param member
	 * @param dataTypes
	 * void
	 */
	private void addDataTypes(Member member, Set<String> dataTypes) {
		//TODO:电销管理小组Id
//	 	if ("电销管理小组Id".equals(member.getDeptId())) {
			dataTypes.add(Constant.AccountDateType);
//		}
		// 非合同客户--账号信息
		if (!isContractMember(member.getId())) {
			dataTypes.remove(Constant.BaseDateType);
		}
	}

//	/**
//	 * 
//	 * <p>
//	 * Description:获取主偏好地址<br />
//	 * </p>
//	 * @author pgj
//	 * @version 0.1 2014-3-11
//	 * @param addresses
//	 * @return
//	 * PreferenceAddress
//	 */
//	private PreferenceAddress getPreferAddress(List<PreferenceAddress> addresses ) {
//		for (int j = 0; j < addresses.size(); j++) {
//			if (addresses.get(j).getIsMainAddress()) {
//				return addresses.get(j);
//			}
//		}
//		return null;
//	}
	
//	/**
//	 * 
//	 * <p>
//	 * Description:根据接送货地址Id获取接送货地址<br />
//	 * </p>
//	 * @author pgj
//	 * @version 0.1 2014-3-11
//	 * @param member
//	 * @param shuttAddId
//	 * @return
//	 * ShuttleAddress
//	 */
//	private ShuttleAddress getShuttleAddress(Member member, String shuttAddId) {
//		if (null != shuttAddId && null != member.getShuttleAddressList()
//				&& 0 <= member.getShuttleAddressList().size()) {
//			for (int j = 0; j < member.getShuttleAddressList().size(); j++) {
//				if (shuttAddId.equals(member.getShuttleAddressList().get(j)
//						.getId())) {
//					return member.getShuttleAddressList().get(j);
//				}
//			}
//		}
//		return null;
//	}

//	/**
//	 * 
//	 * <p>
//	 * Description:保存接送货地址和偏好地址<br />
//	 * </p>
//	 * @author pgj
//	 * @version 0.1 2014-3-11
//	 * @param cust
//	 * void
//	 */
//	private void saveAddress(ChannelCustomer cust) {
//		ShuttleAddress sAddress = new ShuttleAddress();
//		BeanUtils.copyProperties(cust, sAddress);
//		sAddress.setStatus(Constant.Status_NoEfficacy);
//		alterMemberService.saveShuttleAddress(sAddress);
////		PreferenceAddress pAddress = producePreferAddress(cust,
////				sAddress);
//		PreferenceAddress pAddress = producePreferAddress(sAddress);
//		pAddress.setStatus(Constant.Status_NoEfficacy);
//		alterMemberService.savePreferenceAddress(pAddress);
//	}
//	
	/**
	 * 
	 * <p>
	 * Description:校验手机号码是否在审批中<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-11
	 * @param cust
	 * @return
	 * boolean
	 */
	private boolean isMobileInApprove(String mobilePhone) {
		if(StringUtils.isEmpty(mobilePhone)){
			return false;
		}
		List<ApprovingWorkflowData> datas = queryWorkFlowDataByPhone(mobilePhone);
		return null != datas && 0 < datas.size();
	}
	/**
	 * 
	 * <p>
	 * Description:校验电话号码+姓名是否在审批中<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-11
	 * @param cust
	 * @return
	 * boolean
	 */
	private  boolean  isTelInApprove(String linkManName,String telPhone) {
		if(StringUtils.isEmpty(linkManName)||StringUtils.isEmpty(telPhone)){
			return false;
		}
		List<ApprovingWorkflowData> datas = queryWorkFlowDataByTelAndName(telPhone, linkManName);
		return null != datas && 0 < datas.size();
	}
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
	public void updateMemberExtend(MemberExtend extend){
		memberService.updateMemberExtend(extend);
	}
	/**
	 * 
	* @Title: checkPhone
	* @Description: 校验手机号是否存在
	* @author chenaichun 
	* @param @param phone
	* @param @return    设定文件
	* @date 2014-3-24 上午10:51:16
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 上午10:51:16
	 */
	@SuppressWarnings("rawtypes")
	private boolean checkPhone(String phone) {
		ArrayList list = (ArrayList) memberService.getMemberListByCellphone(phone);
		if (!CollectionUtils.isEmpty(list)) {
			String cust = (String) ((HashMap) list.get(0)).get("CUST");
			String depart = (String) ((HashMap) list.get(0)).get("DEPART");
			throw new MemberException(
					MemberExceptionType.PhoneMemberExist, new Object[] {
							phone,depart, cust });
		}
		List<ApprovingWorkflowData> datas = queryWorkFlowDataByPhone(phone);
		if(!CollectionUtils.isEmpty(datas)){
			for (ApprovingWorkflowData data : datas) {
				throw new MemberException(
						MemberExceptionType.PhoneMemberExamin, new Object[]{phone,data.getWorkflowId()});
			}
		}
		return true;
	}
	/**
	 * 
	* @Title: queryWorkFlowDataByPhone
	* @Description: 根据手机号查询审批中的数据，看是否存在
	* @author chenaichun 
	* @param @param phone
	* @param @return    设定文件
	* @date 2014年4月12日 下午1:49:08
	* @return List<ApprovingWorkflowData>    返回类型
	* @throws
	* @update 2014年4月12日 下午1:49:08
	 */
	private List<ApprovingWorkflowData> queryWorkFlowDataByPhone(String phone) {
		ApprovingWorkflowData workflowData = new ApprovingWorkflowData();
		workflowData.setContactMobile(phone);
		//加审核中的存在的手机号数据校验
		List<ApprovingWorkflowData> datas = approvingWokflowDataService.queryConWorkflowData(workflowData);
		return datas;
	}
	/**
	 * 
	* @Title: checkTelAndName
	* @Description: 校验客户名称+固定电话是否存在： true为不存在
	* @author chenaichun 
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014-3-24 上午10:53:21
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 上午10:53:21
	 */
	@SuppressWarnings("rawtypes")
	private boolean checkTelAndName(String tel, String name) {
		List<ApprovingWorkflowData> datas = queryWorkFlowDataByTelAndName(tel, name);
		if(!CollectionUtils.isEmpty(datas)){
			for (ApprovingWorkflowData data : datas) {
				throw new MemberException(
						MemberExceptionType.TelAndNameMemberExamin, new Object[]{tel,name,data.getWorkflowId()});
			}
		}
		ArrayList list = (ArrayList) memberService
				.getMemberListByCustnameAndContact(name, tel);
		if (!CollectionUtils.isEmpty(list)) {
			String cust = (String) ((HashMap) list.get(0)).get("CUST");
			String depart = (String) ((HashMap) list.get(0)).get("DEPART");
			throw new MemberException(
					MemberExceptionType.TelAndNameMemberExist, new Object[] {
							tel,name,depart, cust });
		}
		return true;
	}
	/**
	 * 
	* @Title: queryAppDataByTelAndName
	* @Description: 根据固定电话+姓名查询审批中的数据，看是否存在
	* @author chenaichun 
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014年4月12日 下午2:09:20
	* @return List<ApprovingWorkflowData>    返回类型
	* @throws
	* @update 2014年4月12日 下午2:09:20
	 */
	private List<ApprovingWorkflowData> queryWorkFlowDataByTelAndName(String tel,
			String name) {
		ApprovingWorkflowData workflowData = new ApprovingWorkflowData();
		workflowData.setContactTel(tel);
		workflowData.setContactName(name);
		List<ApprovingWorkflowData> datas = approvingWokflowDataService.queryConWorkflowData(workflowData);
		return datas;
	}
	/**
	 * 
	* @Title: checkTaxregNumber
	* @Description: 查询税务号是否能用 true 能用
	* @author chenaichun 
	* @param @param taxregNumber
	* @param @return    设定文件
	* @date 2014-3-24 上午11:16:01
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 上午11:16:01
	 */
	@SuppressWarnings("rawtypes")
	private boolean checkTaxregNumber(String taxregNumber) {
		ArrayList list = (ArrayList) memberService
				.getMemberListByTaxregNumber(taxregNumber);
		if (!CollectionUtils.isEmpty(list)) {
			String cust = (String) ((HashMap) list.get(0)).get("CUST");
			String depart = (String) ((HashMap) list.get(0)).get("DEPART");
			//香港登记证
			if (Constant.registration_defaultLength == taxregNumber.length()) {
				throw new MemberException(MemberExceptionType.RegistrationMemberExist,
						new Object[] { taxregNumber, depart, cust });
			} else{
			throw new MemberException(
					MemberExceptionType.TaxregnumerMemberExist, new Object[] {
							taxregNumber,depart, cust });
			}
		}
		ApprovingWorkflowData workflowData = new ApprovingWorkflowData();
		workflowData.setTaxregNumber(taxregNumber);
		List<ApprovingWorkflowData> datas = approvingWokflowDataService.queryConWorkflowData(workflowData);
		if(!CollectionUtils.isEmpty(datas)){
			for (ApprovingWorkflowData data : datas) {
				//香港登记证
				if (Constant.registration_defaultLength == taxregNumber.length()) {
					throw new MemberException(
							MemberExceptionType.RegistrationNumerMemberExamin,
							new Object[] { taxregNumber, data.getWorkflowId() });
				} else {
					throw new MemberException(
							MemberExceptionType.TaxregnumerMemberExamin,
							new Object[] { taxregNumber, data.getWorkflowId() });
				}
			}
		}
		
		return true;
	}
	/**
	 * 
	 * <p>
	 * 会员创建业务逻辑验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-21
	 * @param member
	 *            会员 void
	 */
	@Override
	public boolean validateCreateMember(Member member) {
		Assert.notNull(member, "member must not be null");

		String custType = member.getCustType();
			// 企业用户验证
			if (Constant.Enterprise_Member.equals(custType)) {
				//存在数据的验证
				String taxregNumber = member.getTaxregNumber();
				if(!ValidateUtil.objectIsEmpty(taxregNumber)){
					//检查税务登记号是否可用
					checkTaxregNumber(taxregNumber);
					//固定客户 为企业类型时不允许为空
				}else if(Constant.CUST_GROUP_RC_CUSTOMER.equals(member.getCustGroup())){
					throw new MemberException(MemberExceptionType.taxregNumberNull);
				}
				//联系人类型为个人时
			}else if(Constant.Person_Member.equals(custType)){
				//验证个人客户的主联系人身份证是否重复
				List<Contact> contacts = member.getContactList();
				String idCard = "";
				String cardTypeCon ="";
				//得到主联系人 idCard
				for (Contact contact : contacts) {
					if(contact.getIsMainLinkMan()){
						idCard = contact.getIdCard();
						cardTypeCon = contact.getCardTypeCon();
					}
				}
				//固定客户 为个人时证件号必填
				if (Constant.CUST_GROUP_RC_CUSTOMER.equals(member.getCustGroup())) {
					//个人客户主联系人 身份证号码不能为空
					if (ValidateUtil.objectIsEmpty(idCard) || ValidateUtil.objectIsEmpty(cardTypeCon)) {
						throw new MemberException(MemberExceptionType.IdCardNull);
					}
				}
				//个人客户主联系人 身份证是否重复验证
				if (!StringUtils.isEmpty(idCard)&&!idCardCanUse(idCard,cardTypeCon)) {
					throw new MemberException(MemberExceptionType.ContactExist);
				}
			}
		
		// 联系人验证
		List<Contact> contacts = member.getContactList();
		return isContactsAllNotExist(contacts);

	}
	/**
	 * 
	* @Title: idCardCanUse
	* @Description: 验证身份证是否可以用
	* @author chenaichun 
	* @param @param idCard
	* @param @param cardTypeCon
	* @param @return    设定文件
	* @date 2014-3-24 下午2:22:03
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 下午2:22:03
	 */
	@SuppressWarnings("rawtypes")
	public boolean idCardCanUse(String idCard,String cardTypeCon) {
		//检查证件是否有效
		if(!MemberEffectValidate.validate(idCard,Certification.getCertification(cardTypeCon))){
			return false;
		}	
		//个人客户的主联系人 之间 才检查 身份证重复
		List list = memberService.getMemberListByPersonalcardNumber(cardTypeCon, idCard, Constant.Person_Member, true);
		if (!CollectionUtils.isEmpty(list)) {
			String cust = (String) ((HashMap) list.get(0)).get("CUST");
			String depart = (String) ((HashMap) list.get(0)).get("DEPART");
			throw new MemberException(
					MemberExceptionType.CardMemberExist, new Object[] {
							DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.CARDTYPECON,cardTypeCon),idCard,depart, cust });
		}
		ApprovingWorkflowData workflowData = new ApprovingWorkflowData();
		workflowData.setIdCardNo(idCard);
		workflowData.setCredentialsType(cardTypeCon);
		List<ApprovingWorkflowData> datas = approvingWokflowDataService.queryConWorkflowData(workflowData);
		if (!CollectionUtils.isEmpty(datas)) {
			for (ApprovingWorkflowData data : datas) {
				throw new MemberException(
						MemberExceptionType.CardMemberExamin, new Object[]{DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.CARDTYPECON, cardTypeCon),idCard,data.getWorkflowId()});
			}
		}
		
		return true;
	}

	/**
	 * 
	 * <p>
	 * 联系人是否存在<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-21
	 * @param contact
	 * @return boolean
	 */
	public boolean isContactExist(Contact contact) {
		String phone = contact.getMobilePhone();
		String tel = contact.getTelPhone();
		String name = contact.getName();
		String number = contact.getNumber();
		
		
		if (ValidateUtil.objectIsEmpty(phone)
				&& (
					ValidateUtil.objectIsEmpty(name) || ValidateUtil
					.objectIsEmpty(tel) 
				)
				&& ValidateUtil.objectIsEmpty(number) 
				) {
			throw new IllegalArgumentException("手机，姓名，电话，联系人编码,值不全,参数调用不合法");
		}

		if (!ValidateUtil.objectIsEmpty(phone)) {
			if (!checkPhone(phone)) {
				return true;
			}
		}
		if (!ValidateUtil.objectIsEmpty(name)
				&& !ValidateUtil.objectIsEmpty(tel)) {
			if (!checkTelAndName(tel, name)) {
				return true;
			}
		}
		
		if(!ValidateUtil.objectIsEmpty(number)){
			if(isExistLinkManMuber(number)){
				return true;
			}
		}
	
		return false;
	}
	
	/**
	 * 
	 * <p>
	 * Description:联系人是否都不存在<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contacts
	 * @return boolean
	 */
	protected boolean isContactsAllNotExist(List<Contact> contacts) {
		Assert.notNull(contacts, "contacts must not be null");

		for (Contact contact : contacts) {
			if (isContactExist(contact)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	* @Title: isExistLinkManMuber
	* @Description: 是否存在联系人编码 false 为不存在
	* @author chenaichun 
	* @param @param linkManNumber
	* @param @return    设定文件
	* @date 2014-3-24 下午3:01:06
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 下午3:01:06
	 */
	@SuppressWarnings("rawtypes")
	public boolean isExistLinkManMuber(String linkManNumber) {
		List list = memberService.getMemberListByLinkManNumber(linkManNumber);
		if (!CollectionUtils.isEmpty(list)) {
			String cust = (String) ((HashMap) list.get(0)).get("CUST");
			String depart = (String) ((HashMap) list.get(0)).get("DEPART");
			throw new MemberException(
					MemberExceptionType.LinkManMuberMemberExist, new Object[] {
							linkManNumber,depart, cust });
		}
		ApprovingWorkflowData workflowData = new ApprovingWorkflowData();
		workflowData.setContactNum(linkManNumber);
		List<ApprovingWorkflowData> datas = approvingWokflowDataService.queryConWorkflowData(workflowData);
		if (!CollectionUtils.isEmpty(datas)) {
			for (ApprovingWorkflowData data : datas) {
				throw new MemberException(
						MemberExceptionType.LinkManMuberMemberExamin, new Object[]{linkManNumber,data.getWorkflowId()});
			}
		}
		
		return false;
	}
	/**
	 * 
	* @Title: checkEnterpriseTaxregNumber
	* @Description: 企业用户 税务登记号 必填
	* @author chenaichun 
	* @param @param member
	* @param @return    设定文件
	* @date 2014-3-24 下午4:57:08
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 下午4:57:08
	 */
	@Override
	public boolean checkEnterpriseTaxregNumber(Member member){
		// 企业用户 税务登记号 必填
		if (Constant.Enterprise_Member.equals(member.getCustType())) {
			//潜散客 税务登记号为空的时候 校验直接通过
			if(!Constant.CUST_GROUP_RC_CUSTOMER.equals(member.getCustGroup())
					&&StringUtils.isEmpty(member.getTaxregNumber())){
				return true;
			}
			String location = baseDataManager.getDeptCityLocation(member.getDeptId());
			CustomerValidator.checkTaxregNumberCanUseForSpecialMember(location,member.getTaxregNumber());
		}
		return true;
	}
	/**
	 * 
	* <p>Title: searchMemberExtendByID</p>
	* <p>Description: 根据客户ID查询客户扩展信息
	* @author chenaichun 
	* @param custId
	* @return
	* @see com.deppon.crm.module.customer.server.manager.IMemberManager#searchMemberExtendByID(java.lang.String)
	 */
	@Override
	public MemberExtend searchMemberExtendByID(String custId) {
		if(ValidateUtil.objectIsEmpty(custId)){
			throw new MemberException(MemberExceptionType.CustId_Miss_Error);
		}
		return memberService.searchMemberExtendByID(custId);
	}
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
	@Override
	public List<Member> searchMemberByCondition(MemberCondition memberCondition){
		return alterMemberService.searchMemberByCondition(memberCondition);
	}
	/**
	 * 
	* @Title: makeDefaultMember
	* @Description: 创建潜客默认值，提供给“陌生来电和订单揽货失败” 使用（member.getChannalSource()字段必须有值）
	* @author chenaichun 
	* @param @param member
	* @param @return    设定文件
	* @date 2014年4月2日 上午11:52:28
	* @return Member    返回类型
	* @throws
	* @update 2014年4月2日 上午11:52:28
	*/
	private Member makeDefaultMember(Member member){
		// 系统自动生成客户编码
		// 获得会员序列的下一个id值
		String memberId = memberService.getMemberIdUseSeq();
		member.setId(memberId);
		// 根据会员序列id，生成会员编号
		String custNumber = CustomerRule.createCustNumberByMembrId(memberId);
		member.setCustNumber(custNumber);
		//联系人信息
		List<Contact> contactList = member.getContactList();
		if(CollectionUtils.isEmpty(contactList)||ValidateUtil.objectIsEmpty(contactList.get(0))){
			throw new MemberException(MemberExceptionType.ContactNotFindError);
		}
		Contact contact = contactList.get(0);
		//获得客户的部门ID
		String deptId = member.getDeptId();
		if(StringUtils.isEmpty(deptId)){
			throw new MemberException(MemberExceptionType.DeptNull);
		}
		//根据部门ID获得对应的省市存入客户的省市字段
		if(StringUtils.isEmpty(member.getProvince())||StringUtils.isEmpty(member.getCity())
				||StringUtils.isEmpty(member.getProvinceId())||StringUtils.isEmpty(member.getCityId())){
			BussinessDept busDept = departmentManager.getBusDeptById(member.getDeptId());
			if(busDept == null || busDept.getCity() == null){
				throw new MemberException(MemberExceptionType.DeptNull);
			}
			member.setCityId(busDept.getCity().getId());
			member.setCity(busDept.getCityName());
			member.setProvinceId(busDept.getProvince().getId());
			member.setProvince(busDept.getProvinceName());
		}
		//根据电话号生成联系人编码
		MemberUtil.produceContactNum(contact);
		//设置主联系人
		contact.setIsMainLinkMan(true);
		member.setMainContact(contact);
		// 客户详细地址
		if(StringUtils.isEmpty(member.getRegistAddress())){
			member.setRegistAddress(member.getProvince()+"-"+member.getCity()+"-");
		}
		member.setJshAddress(member.getRegistAddress());
		// 客户类型(默认为个人）
		member.setCustType(Constant.Person_Member);
		// 客户属性(订：出发客户 ，陌生来电：出发到达)
		if(Constant.CUST_SOURCE_STRANGER.equals(member.getChannelSource())){
			member.setCustNature(Constant.LEAVE_ARRIVE_CUSTOMER);
		}else if(Constant.ORDER_CUSTOMER.equals(member.getChannelSource())){
			member.setCustNature(Constant.LeaveCustomer);
		}
		//默认 大客户 为否
		member.setIsKeyCustomer(false);
		//默认  重要客户为否
		member.setIsImportCustor(false);
		//默认 特殊会员为否 
		member.setIsSpecial(false);
		//默认 集中结算 为  否
		member.setIsFocusPay(false);
		//默认 是否母公司为 是
		member.setIsParentCompany(true);
		//默认 临欠额度为空
		member.setProcRedit(null);
		//默认 仅允许联系人兑换积分值为否
		member.setIsRedeempoints(false);
		// 是否财务作废（否）
		member.setFinOver(false);
		member.setContactList(contactList);
		String createUser = ContextUtil.getCreateOrModifyUserId();
		member.setCreateUser(createUser);
		member.setCustGroup(Constant.CRM_POTENTIAL);
		/**
		 * 设置会员状态为正常（包括联系人，地址等等的状态）
		 */
		CustomerRule.setMemberStatus(member, Constant.Status_Normal);
		return member;
	}
	/**
	 * 
	* @Title: createPotentialMember
	* @Description: 创建潜客，提供给“陌生来电和订单揽货失败” 使用（member.getChannalSource()字段必须有值）
	* @author chenaichun 
	* @param @param member    设定文件
	* @date 2014年4月3日 上午10:28:23
	* @return void    返回类型
	* @throws
	* @update 2014年4月3日 上午10:28:23
	 */
	@Transactional
	@Override
	public void createPotentialMember(Member member){
		Contact con = null;
		if(!CollectionUtils.isEmpty(member.getContactList())){
			con = member.getContactList().get(0);
		}
		// 匹配不成功,新增一条客户信息（潜客）
		if(!ValidateUtil.objectIsEmpty(con)&&!this.isContactExist(con)){
			
				// 设置一些客户的数据以及默认值
				member = this.makeDefaultMember(member);
				memberService.createMember(member);
				//处理开发阶段
				this.createMemberExtendAndDevelopment(member);
				//记录操作日志
				MemberOperationLog log = CustomerRule.createLog(member
						.getId(),Constant.OPERATOR_TYPE_INSERT);
//				log.setOperateType(Constant.OPERATOR_TYPE_INSERT);
				log.setEffect(true);
				alterMemberService.saveMemberOperationLog(log);
			
		}else{
			throw new MemberException(MemberExceptionType.createPotentialMemberFail);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IMemberManager#upgradeScatter(com.deppon.crm.module.customer.shared.domain.Member)
	 */
	@Override
	public void upgradeScatter(Member member) {
		Member newMember = new Member();
		newMember.setId(member.getId());
		newMember.setDegree(member.getDegree());
		newMember.setCustGroup(member.getCustGroup());
		alterMemberService.updateMember(newMember);
		
		MemberOperationLog log = createMemberLog(member,Constant.CUST_OPERATETYPE_SCATTERUPGRADE,null,null);
		alterMemberService.saveMemberOperationLog(log);
		
		deleteUpGradeCustomerData(member.getCustNumber());
//		throw new RuntimeException();
	}

	/**
	 * 
	 * <p>
	 * Description:创建操作日志<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-3
	 * @param member
	 * @param type
	 * @param degree
	 * @param beforeDegree
	 * @return
	 * MemberOperationLog
	 */
	private MemberOperationLog createMemberLog(Member member,String type,String degree,String beforeDegree) {
		MemberOperationLog log = new MemberOperationLog();
		log.setCreateDate(new Date());
		log.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		log.setMember(member);
		log.setDepartment(ContextUtil.getCurrentUserDept());
		log.setEffect(true);
		log.setModifyUserId(ContextUtil.getCreateOrModifyUserId());
		log.setModifyUserName(ContextUtil.getCreateOrModifyUserName());
		log.setOperateType(type);
		return log;
	}
	
	








	@Override
	public String importPotentialCustomer(File excel) {
		IExcelReader reader = new ExcelReader(excel);
		String[] header = reader.getHeader();
		
		String deptId = ContextUtil.getCurrentUserDeptId();
		String userId = ContextUtil.getCurrentUserEmpId();
		//获得当前部门所在城市
		
		//验证模板
		if(!PotentialCustomerExcelHelper.headValidate(header)){
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		/**
		 * @description:新增潜客导入条数不能超过100条 @需求RCRM2013071801潜客导入模板优化需求
		 * @date 2013-07-31
		 * @author pgj
		 */
		if (reader.getTotalRows()>101) {
			return 	sb.append("导入潜客数超过100条，请分批导入").toString();
		}
		
		
		int rowNum = 0;
		int errorNum = 0;
		while (reader.hasNextRow()) {
			rowNum++;
			
			Object[] row = reader.getNextRow();
			//转换 PotentialCustomer 对象
			PotentialCustomer ps;
			
			try {
				ps = PotentialCustomerExcelHelper.getPotenCustomerByPotenRule(row);
				
				//设置部门
				ps.setDeptId(deptId);
				//设置创建人
				ps.setCreateUser(userId);
				//设置创建时间
				ps.setCreateDate(new Date());
				//设置城市
				BussinessDept bussDept = departmentManager.getBusDeptById(deptId);
				String cityId = "";
				if(bussDept != null && bussDept.getCity() != null){
					cityId = bussDept.getCity().getId();
				}
				ps.setCityId(cityId);
				ps.setCity(bussDept.getCityName());
				ps.setProvinceId(bussDept.getProvince().getId());
				ps.setProvince(bussDept.getProvinceName());
				String memberId = memberService.getMemberIdUseSeq();
				ps.setId(memberId);
				ps.setAreaId(bussDept.getRegion().getId());
				ps.setArea(bussDept.getRegionName());
				//业务逻辑持久化
				Member member = PontentialCustToMemberAdpater.toMember(ps);
				if (CustomerValidator.validateMemberForm(member)
				&& this.validateCreateMember(member)
				&&this.checkEnterpriseTaxregNumber(member)&&
				CustomerValidator.validatePCCustomer(member)) {
					memberService.createMember(member);
				}
				//处理开发阶段
				this.createMemberExtendAndDevelopment(member);
				//处理商机阶段
				this.saveBusinessOpportunity(member);
			} catch (GeneralException e){
				errorNum++;
				sb.append("第"+rowNum+"条数据导入失败,失败原因为 "+getMessageBundle().getMessage(getLocale(),e.getErrorCode(),e.getErrorArguments())+"\n");
			}
		}
		String context = "";
		if(errorNum == 0){
			context = "恭喜，导入成功。";
		}else{
			context = "一共导入数据"+rowNum+"条，其中成功"+(rowNum - errorNum)+"条,失败"+errorNum+"条，\n失败原因如下:\n";
		}
		
		return context+sb.toString();
	}
	
	public Locale getLocale() {
        ActionContext ctx = ActionContext.getContext();
        if (ctx != null) {
            return ctx.getLocale();
        } else {
            return null;
        }
    }

	public IMessageBundle getMessageBundle() {
		return messageBundle;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}
	@Override
	public String getNumberForRecompense(String mobile, String phone,
			String linkManName, String deptId, String custNumber,FossWaybillInfo waybillInfo,String leaveOrArrive) {
		// 部门ID和联系人名称不能为空，固话和手机至少存在一个
		if (StringUtils.isEmpty(mobile) && StringUtils.isEmpty(phone)
				|| StringUtils.isEmpty(linkManName)
				|| StringUtils.isEmpty(deptId)) {
			throw new ScatterException(ScatterExceptionType.DATAERROR);
		}
		if (!StringUtils.isEmpty(custNumber) && !StringUtils.isEmpty(deptId)) {
			boolean flag = memberService.queryMemberByCustNumber(custNumber);
			// 若存在，则返回客户编码
			if (flag) {
				List<String> custNumbers = new ArrayList<String>();
				custNumbers.add(custNumber);
				alterMemberService.updateMemberFinStatus(custNumbers,false);
				CustomerInfoLog.commit();
				return custNumber;
			} 
		}
		String number = null;
		// 固定会员
		number = checkCustomerByMobileOrPhone(mobile, phone, linkManName);
		if (!StringUtils.isEmpty(number)) {
			List<String> custNumbers = new ArrayList<String>();
			custNumbers.add(number);
			alterMemberService.updateMemberFinStatus(custNumbers,false);
			return number;
		}
		ChannelCustomer c=new ChannelCustomer();
		//TODO 功能未实现  散客
		this.createScatterCustomer(c);
		return null;
		
	}

	/**
	 * <p>
	 * Description:本部门验证<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-3-18
	 * @param mobile
	 * @param phone
	 * @param linkManName
	 * @return
	 * String
	 */
	@SuppressWarnings("rawtypes")
	private String checkCustomerByMobileOrPhone(String mobile, String phone,String linkManName) {
		String number = null;
		ArrayList memberList = null;
		//手机校验
		if (!StringUtils.isEmpty(mobile)) {
			memberList = (ArrayList) memberService.getMemberListByCellphone(mobile);
			if (memberList != null && memberList.size() > 0) {
				return (String) ((HashMap) memberList.get(0)).get("CUST");
			}
			List<ApprovingWorkflowData> onedatas = queryWorkFlowDataByPhone(mobile);
			//如果审批中的实体存在,则返回原来的客户编码
			if (onedatas != null && onedatas.size() > 0) {
				String custId = memberService.getCustWorkFlow(onedatas.get(0).getWorkflowId());
				return memberService.getMemberById(custId).getCustNumber();
			}
		}  
		//固话和姓名
		if (!StringUtils.isEmpty(phone) && !StringUtils.isEmpty(linkManName)){
			 memberList = (ArrayList) memberService.getMemberListByCustnameAndContact(linkManName, phone);
			if (memberList != null && memberList.size() > 0) {
					return (String) ((HashMap) memberList.get(0)).get("CUST");
			}				
			List<ApprovingWorkflowData> twodatas = queryWorkFlowDataByTelAndName(
					phone, linkManName);
			//如果审批中的实体存在,则返回原来的客户编码
			if (twodatas != null && twodatas.size() > 0) {
				String custId = memberService.getCustWorkFlow(twodatas.get(0).getWorkflowId());
				return memberService.getMemberById(custId).getCustNumber();
			}
		}
		return number;
	}

	 /* (non-Javadoc)
	  * @see com.deppon.crm.module.customer.server.manager.IMemberManager#searchArrivalScatterCustByDept(java.lang.String)
	  */
	 @Override
	 public List<MemberResult> searchArrivalScatterCustByDept(String deptId) {
	  MemberCondition condition = new MemberCondition();
	  condition.setCustGroup(Constant.CUST_GROUP_SC_CUSTOMER);
	  condition.setDeptId(deptId);
	  condition.setCustNature(Constant.ArriveCustomer);
	  Calendar ca = Calendar.getInstance(Locale.CHINA);
	  ca.set(Calendar.MONTH, ca.get(Calendar.MONDAY)-6);
	  condition.setCreateDate(ca.getTime());
	  return alterMemberService.searchArriveScatterMember(condition);
	 }
	 /* (non-Javadoc)
	  * @see com.deppon.crm.module.customer.server.manager.IMemberManager#updateCustomerMarktingDept(java.lang.String, java.lang.String)
	  */
	 @Override
	 public void updateCustomerMarktingDept(CustomerMarktingDept dept) {
	  List<CustomerMarktingDept> depts = memberService.searchCustomerMarktingDeptByCustId(dept.getCustId());
	  if (CollectionUtils.isEmpty(depts)) {
	   memberService.createMemberMarktingDept(dept);
	  }else{
	   memberService.modifyMemberMarktingDept(dept);
	  }
	 }

	 /* (non-Javadoc)
	  * @see com.deppon.crm.module.customer.server.manager.IMemberManager#recoverScatterMarktingDeptByDay(int)
	  */
	 @Override
	 public void recoverScatterMarktingDeptByDay(int day) {
	  alterMemberService.modifyScatterMarktingDeptByDay(day,Constant.CALL_SALE_ID);
	 }

	 /* (non-Javadoc)
	  * @see com.deppon.crm.module.customer.server.manager.IMemberManager#dispartureSatterMarktingDept()
	  */
	 @Override
	 public void dispartureSatterMarktingDept() {
	  List<CustomerMarktingDept> depts = memberService.searchCustMarktingDeptByFirstShip();
	  for (CustomerMarktingDept customerMarktingDept : depts) {
	   //更新客户归属部门
	   Member member = new Member();
	   member.setId(customerMarktingDept.getCustId());
	   member.setDeptId(customerMarktingDept.getMarktingDept());
	   alterMemberService.updateMember(member);
	   //更新客户营销权限部门
	   customerMarktingDept.setMarktingDept(customerMarktingDept.getBelongDept());
	    if (StringUtils.isEmpty(customerMarktingDept.getId())) {
	     memberService.createMemberMarktingDept(customerMarktingDept);
	    }else{
	     memberService.modifyMemberMarktingDept(customerMarktingDept);
	    }
	  }
	 }
	 /**
		 * <p>
		 * Description:memberService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IMemberService getMemberService() {
			return memberService;
		}

		/**
		 * <p>
		 * Description:memberService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setMemberService(IMemberService memberService) {
			this.memberService = memberService;
		}

		/**
		 * <p>
		 * Description:alterMemberService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IAlterMemberService getAlterMemberService() {
			return alterMemberService;
		}

		/**
		 * <p>
		 * Description:alterMemberService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setAlterMemberService(IAlterMemberService alterMemberService) {
			this.alterMemberService = alterMemberService;
		}

		/**
		 * <p>
		 * Description:examineRecordService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IExamineRecordService getExamineRecordService() {
			return examineRecordService;
		}

		/**
		 * <p>
		 * Description:examineRecordService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setExamineRecordService(IExamineRecordService examineRecordService) {
			this.examineRecordService = examineRecordService;
		}

		/**
		 * <p>
		 * Description:memberWorkFlowManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IMemberWorkFlowManager getMemberWorkFlowManager() {
			return memberWorkFlowManager;
		}

		/**
		 * <p>
		 * Description:memberWorkFlowManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setMemberWorkFlowManager(IMemberWorkFlowManager memberWorkFlowManager) {
			this.memberWorkFlowManager = memberWorkFlowManager;
		}

		/**
		 * <p>
		 * Description:contractManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IContractManager getContractManager() {
			return contractManager;
		}

		/**
		 * <p>
		 * Description:contractManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setContractManager(IContractManager contractManager) {
			this.contractManager = contractManager;
		}

		/**
		 * <p>
		 * Description:changeMemberDeptManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IChangeMemberDeptManager getChangeMemberDeptManager() {
			return changeMemberDeptManager;
		}

		/**
		 * <p>
		 * Description:changeMemberDeptManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setChangeMemberDeptManager(IChangeMemberDeptManager changeMemberDeptManager) {
			this.changeMemberDeptManager = changeMemberDeptManager;
		}

		/**
		 * <p>
		 * Description:baseDataManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IBaseDataManager getBaseDataManager() {
			return baseDataManager;
		}

		/**
		 * <p>
		 * Description:baseDataManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setBaseDataManager(IBaseDataManager baseDataManager) {
			this.baseDataManager = baseDataManager;
		}

		/**
		 * <p>
		 * Description:approvingWokflowDataService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IApprovingWokflowDataService getApprovingWokflowDataService() {
			return approvingWokflowDataService;
		}

		/**
		 * <p>
		 * Description:approvingWokflowDataService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setApprovingWokflowDataService(IApprovingWokflowDataService approvingWokflowDataService) {
			this.approvingWokflowDataService = approvingWokflowDataService;
		}

		/**
		 * <p>
		 * Description:custLabelManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public ICustLabelManager getCustLabelManager() {
			return custLabelManager;
		}

		/**
		 * <p>
		 * Description:custLabelManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setCustLabelManager(ICustLabelManager custLabelManager) {
			this.custLabelManager = custLabelManager;
		}

		/**
		 * <p>
		 * Description:departmentManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public ILadingstationDepartmentManager getDepartmentManager() {
			return departmentManager;
		}

		/**
		 * <p>
		 * Description:departmentManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setDepartmentManager(ILadingstationDepartmentManager departmentManager) {
			this.departmentManager = departmentManager;
		}

		/**
		 * <p>
		 * Description:customerOperate<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public ICustomerOperate getCustomerOperate() {
			return customerOperate;
		}

		/**
		 * <p>
		 * Description:customerOperate<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setCustomerOperate(ICustomerOperate customerOperate) {
			this.customerOperate = customerOperate;
		}

		/**
		 * <p>
		 * Description:contactManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IContactManager getContactManager() {
			return contactManager;
		}

		/**
		 * <p>
		 * Description:contactManager<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setContactManager(IContactManager contactManager) {
			this.contactManager = contactManager;
		}

		/**
		 * <p>
		 * Description:departmentService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IDepartmentService getDepartmentService() {
			return departmentService;
		}

		/**
		 * <p>
		 * Description:departmentService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public void setDepartmentService(IDepartmentService departmentService) {
			this.departmentService = departmentService;
		}

		/**
		 * <p>
		 * Description:contactService<br />
		 * </p>
		 * @author 106138
		 * @version 0.1 2014年4月14日
		 */
		public IContactService getContactService() {
			return contactService;
		}
		/**
		 *@author chenaichun
		 * @date 2014年4月15日 上午9:33:07 
		 * @param repeatedCustManager the repeatedCustManager to set
		 */
		public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
			this.repeatedCustManager = repeatedCustManager;
		}
		@Transactional
		@Override
		public MemberExtend createMemberExtendInfo(MemberExtend memberExtend) {
			return memberService.createMemberExtendInfo(memberExtend);
		}
		
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
		@Override
		public void updateDevelopmentLog(DevelopmentLog developmentLog) {
			//校验必须字段非空
			if(StringUtils.isEmpty(developmentLog.getCurrentStage())
					||StringUtils.isEmpty(developmentLog.getNextStage())
					||StringUtils.isEmpty(developmentLog.getCustId())){
				throw new MemberException(MemberExceptionType.DEVELOPDATANULL);
			}
			
			developmentLog.setCreateDate(new Date());
			developmentLog.setCreateUser(ContextUtil.getCreateOrModifyUserId());
			memberService.updateDevelopmentLog(developmentLog);
		}
		
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
		/**
		 * 需求确认：	IDENTIFY_NEEDS
		 * 意向洽谈：	INTENTION_DISCUSS
		 * 持续培养：	CONTINUING_TRAINING
		 * 开始发货：	BEGAN_SHIPPING
		 */
		public void updateExtendAndLog(Member member,MemberExtend extend,String operationType){
			//校验传过来的参数完整性，extend和membe不能同时为空
			if(ValidateUtil.objectIsEmpty(extend)||StringUtils.isEmpty(extend.getCustId())){
				throw new MemberException(MemberExceptionType.DEVELOPDATANULL);
			}
			//如果传入的member为空，则通过ID到后台去查询该客户的信息
			if(ValidateUtil.objectIsEmpty(member)){
				member = alterMemberService.getMemberAllById(extend.getCustId());
			}
			//开发阶段日志
			DevelopmentLog developmentLog = new DevelopmentLog();
			//结果列表
			List<Map<String, String>> logList = new ArrayList<Map<String,String>>();
			
			//如果是非纯到达散客或者固定客户，则直接到发货阶段
			if ("SC_CUSTOMER".equals(member.getCustGroup())
					&&!Constant.ArriveCustomer.equals(member.getCustNature())
					||"RC_CUSTOMER".equals(member.getCustGroup())) {
				//拿到该客户的开发阶段日志
				logList =memberService.queryDevelopmentLog(extend.getCustId());
				//加入custId
				developmentLog.setCustId(extend.getCustId());
				//校验客户当前开发阶段是否为发货阶段，若不是则客户扩展信息之开发阶段直接设为 "发货阶段"
				if (Constant.OPERATOR_TYPE_INSERT.equals(operationType) ||
						!Constant.BEGAN_SHIPPING.equals(extend.getDevelopmentPhase())) {
					extend.setDevelopmentPhase(Constant.BEGAN_SHIPPING);
				}
				saveMemberExtend(extend,operationType);
				/*
				 *由于散客客户属性可以修改，可能在作为纯到达散客的时候，已经存在一些操作日志
				 *所以该散客是需要校验Ta的日志，不能直接硬性插入三条数据
				 *此功能不影响固定客户操作日志插入,不再另外分情况，因为100%满足下面三个条件，会直接插入三条。
				 */
				//日志条数为零，则插入第一条操作记录
				if (logList.size()<1) {
					saveDiscussStageLog(developmentLog);
				}
				//日志条数为小于贰，则插入第二条操作记录
				if (logList.size()<2) {
					saveTrainingStageLog(developmentLog);
				}
				//日志条数小于叁，则插入第三条操作记录
				if (logList.size()<3) {
					saveShippingStageLog(developmentLog);
				}
			}else if(canBeDiscussStage(member, extend)){
				//否则就是潜客或者纯到达散客，而这类客户最多只能到达“持续培养”阶段
				
				/*满足“持续培养”阶段的条件：
				 * ①	客户行业（只限一级行业）；
				 * ②	适合我司承运（选择是）；
		         * ③	货量潜力
		         * ④	合作意向字段（选择高）
				 */
				//拿到该客户的开发阶段日志
				logList =memberService.queryDevelopmentLog(extend.getCustId());
				developmentLog.setCustId(extend.getCustId());
				if (Constant.HIGN.equals(extend.getCoopIntention())) {
					//校验客户开发阶段是否为 持续培养阶段，若不是，则设为持续培养
					if (!Constant.CONTINUING_TRAINING.equals(extend.getDevelopmentPhase())) {
						extend.setDevelopmentPhase(Constant.CONTINUING_TRAINING);
					}
					saveMemberExtend(extend,operationType);
					//校验该客户是否有开发阶段日志，若没有则插入升级为意向洽谈、持续培养阶段的日志，若没有，则插入
					if (logList.size()<1) {
						saveDiscussStageLog(developmentLog);
						saveTrainingStageLog(developmentLog);
					}else if(logList.size()==1){//如果该客户已有升级为意向洽谈阶段日志，则只需插入持续培养阶段日志
						saveTrainingStageLog(developmentLog);
					}
				}else {//若不满足“持续培养”阶段，则校验是否满足意向洽谈阶段
					
					/* 满足 “意向洽谈”阶段的条件：
					 * ①	客户行业（只限一级行业）不为空;
					 * ②	适合我司承运（选择是）；
					 * ③	货量潜力不为空;
					 */
					
						//校验客户开发阶段是否为意向洽谈阶段，若不是，则设为意向洽谈
						if (!Constant.INTENTION_DISCUSS.equals(extend.getDevelopmentPhase())) {
							extend.setDevelopmentPhase(Constant.INTENTION_DISCUSS);
						}
						saveMemberExtend(extend,operationType);
						//校验客户升级为意向洽谈阶段的日志是否已存在，如果不存在，则插入
						if (logList.size() < 1) {
							saveDiscussStageLog(developmentLog);
						}
				}
			}else {//否则就不达到客户开发阶段变更的条件，则直接只保存客户扩展信息
				//其他不符合就为需求确认阶段
				if(StringUtils.isEmpty(extend.getDevelopmentPhase())){
					extend.setDevelopmentPhase(Constant.IDENTIFY_NEEDS);
				}
				saveMemberExtend(extend,operationType);
			}
		}
		
		/**
		 * 
		 * @Title: canBeDiscussStage
		 *  <p>
		 * @Description: 校验是否满足进入意向洽谈阶段条件
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-9
		 * @return boolean
		 * @throws
		 */
		public boolean canBeDiscussStage(Member member,MemberExtend extend){
			return !ValidateUtil.objectIsEmpty(member)
					&&!StringUtils.isEmpty(member.getTradeId())
					&&(!StringUtils.isEmpty(extend.getIsAccord())
							&&"1".equals(extend.getIsAccord()))
							&&!StringUtils.isEmpty(extend.getVolumePotential());
		}
		
		/**
		 * 
		 * @Title: saveShippingStageLog
		 *  <p>
		 * @Description: 插入开发阶段--洽谈阶段日志
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-8
		 * @return void
		 * @throws
		 */
		public void saveDiscussStageLog(DevelopmentLog developmentLog){
			developmentLog.setCurrentStage(Constant.IDENTIFY_NEEDS);
			developmentLog.setNextStage(Constant.INTENTION_DISCUSS);
			updateDevelopmentLog(developmentLog);
		}
		
		/**
		 * 
		 * @Title: saveShippingStageLog
		 *  <p>
		 * @Description: 插入开发阶段--培养阶段日志
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-8
		 * @return void
		 * @throws
		 */
		public void saveTrainingStageLog(DevelopmentLog developmentLog){
			developmentLog.setCurrentStage(Constant.INTENTION_DISCUSS);
			developmentLog.setNextStage(Constant.CONTINUING_TRAINING);
			updateDevelopmentLog(developmentLog);
		}
		
		/**
		 * 
		 * @Title: saveShippingStageLog
		 *  <p>
		 * @Description: 插入开发阶段--发货阶段日志
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-8
		 * @return void
		 * @throws
		 */
		public void saveShippingStageLog(DevelopmentLog developmentLog){
			developmentLog.setCurrentStage(Constant.CONTINUING_TRAINING);
			developmentLog.setNextStage(Constant.BEGAN_SHIPPING);
			updateDevelopmentLog(developmentLog);
		}
		
		/**
		 * 
		 * @Title: updateMemberExtend
		 *  <p>
		 * @Description: 保存客户扩展信息
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-9
		 * @return void
		 * @throws
		 */
		public void saveMemberExtend(MemberExtend extend,String operationType){
			if(Constant.OPERATOR_TYPE_INSERT.equalsIgnoreCase(operationType)){
				createMemberExtendInfo(extend);
			}else if(Constant.OPERATOR_TYPE_UPDATE.equalsIgnoreCase(operationType)){
				updateMemberExtend(extend);
			}
		}
		@Override
		public boolean canRealTimeCreateMember(String phone, String tel,
				String name) {
			Member member = this.checkIsExistMember(phone, tel, name);
			if(!ValidateUtil.objectIsEmpty(member)&&!StringUtils.isEmpty(member.getId())){
				return true;
			}
			return false;
		}
		
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
		@Override
		public void saveBusinessOpportunity(Member member) {
			if (!StringUtils.isEmpty(member.getId())) {
				// 获取修改前商机
				BusinessOpportunity bo = memberService
						.queryBusinessOpportunityById(member.getId());
				if (!ValidateUtil.objectIsEmpty(bo)) {
					// 获取客户行业
					bo.getCustomer().setCustFirstType(member.getTradeId());
					bo.getCustomer().setCustSecondType(member.getSecondTrade());
					bo.setCustomer(bo.getCustomer());
					// 与当前商机进行信息比较
					if (checkBusinessOpportunityInfo(bo.getBoStep())) {
						// 通过商机信息获取商机状态
						String newStep = getBusinessOpportunityStep(bo);
						bo.setBoStep(newStep);
						Date current = new Date();
						bo.setModifyTime(current);
						// 修改保存商机信息
						memberService.saveBusinessOpportunityStep(bo);
						// 生成商机阶段操作日志
						List<BoOperationLog> logList = getBusinessOpportunityLog(
								bo.getId(), bo.getBoStep(), newStep, bo.getModifier()
										.getId());
						// 记录商机阶段操作日志
						if (!logList.isEmpty()) {
							memberService.addBusinessOpportunityLog(logList);
						}
					}
				}
			}
		}
		/**
		 * 
		 * @Title: checkBusinessOpportunityInfo
		 *  <p>
		 * @Description: 检查商机阶段的内容是否完整
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-20
		 * @return boolean
		 * @throws
		 */
		public static boolean checkBusinessOpportunityInfo(String lastStep){
			if (StringUtils.equals(lastStep,
					Constant.BO_STEP_CONTACT)) {
				// 初步接触商机阶段判断
				return true;
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_ANALYZE)) {
				// 需求分析商机阶段判断
				return true;
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_SCHEME)) {
				// 制定方案商机阶段判断
				return true;
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_OFFER)) {
				// 报价/竞标商机阶段判断
				return true;
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_DELIVER)) {
				// 持续发货商机阶段判断
				return true;
			}
			return false;
		}
		
		/**
		 * 
		 * @Title: getBusinessOpportunityStep
		 *  <p>
		 * @Description: 获取新的商机状态
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-20
		 * @return String
		 * @throws
		 */
		public static String getBusinessOpportunityStep(BusinessOpportunity bo) {
			String newStep = Constant.BO_STEP_CONTACT;
			if (StringUtils.isNotEmpty(bo.getCustomer().getCustFirstType())
					&& StringUtils.isNotEmpty(bo.getCustomer().getCustSecondType())
					&& StringUtils.isNotEmpty(bo.getIsBidProject())
					&& StringUtils.isNotEmpty(bo.getIsBOConfirm())
					&& StringUtils.equals(bo.getIsBOConfirm(), "1")) {
				newStep = Constant.BO_STEP_ANALYZE;
				if (StringUtils.isNotEmpty(bo.getCustomerDemandDesc())) {
					newStep = Constant.BO_STEP_SCHEME;
					if (StringUtils.isNotEmpty(bo.getSolutionDesc())) {
						newStep = Constant.BO_STEP_OFFER;
						if (StringUtils.isNotEmpty(bo.getCompetitiveInfo())) {
							if (StringUtils.equals(bo.getBoStep(),
									Constant.BO_STEP_DELIVER)) {
								newStep = Constant.BO_STEP_DELIVER;
							} else {
								newStep = Constant.BO_STEP_OFFER;
							}
						}
					}
				}
			}
			return newStep;
		}
		
		/**
		 * 
		 * @Title: getBusinessOpportunityLog
		 *  <p>
		 * @Description: 获取阶段转换的变更日志
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-20
		 * @return List<BoOperationLog>
		 * @throws
		 */
		public static List<BoOperationLog> getBusinessOpportunityLog(String boId,
				String lastStep, String newStep, String operator) {
			Date current = new Date();
			List<BoOperationLog> logList = new ArrayList<BoOperationLog>();
			// 创建商机阶段日志
			BoOperationLog createLog = getBoOperationLog(boId, null,
					Constant.BO_STEP_CONTACT, operator, current);
			// 需求分析阶段日志
			BoOperationLog analyzeLog = getBoOperationLog(boId,
					Constant.BO_STEP_CONTACT,
					Constant.BO_STEP_ANALYZE, operator, current);
			// 制定计划阶段日志
			BoOperationLog schemeLog = getBoOperationLog(boId,
					Constant.BO_STEP_ANALYZE,
					Constant.BO_STEP_SCHEME, operator, current);
			// 竞标投标阶段日志
			BoOperationLog offerLog = getBoOperationLog(boId,
					Constant.BO_STEP_SCHEME,
					Constant.BO_STEP_OFFER, operator, current);
			// 持续发货阶段日志
			BoOperationLog deliverLog = getBoOperationLog(boId,
					Constant.BO_STEP_OFFER,
					Constant.BO_STEP_DELIVER, operator, current);
			if (StringUtils.isEmpty(lastStep)) {
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_CONTACT)) {
					// 如原商机阶段为空，并且新商机阶段为初步联系
					logList.add(createLog);
				}
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_CONTACT)) {
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_ANALYZE)) {
					// 如原商机阶段为初步联系，并且新商机阶段为需求分析
					logList.add(analyzeLog);
				}
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_SCHEME)) {
					// 如原商机阶段为初步联系，并且新商机阶段为制定计划
					logList.add(analyzeLog);
					logList.add(schemeLog);
				}
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_OFFER)) {
					// 如原商机阶段为初步联系，并且新商机阶段为竞标投标
					logList.add(analyzeLog);
					logList.add(schemeLog);
					logList.add(offerLog);
				}
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_DELIVER)) {
					// 如原商机阶段为初步联系，并且新商机阶段为持续发货
					logList.add(analyzeLog);
					logList.add(schemeLog);
					logList.add(offerLog);
					logList.add(deliverLog);
				}
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_ANALYZE)) {
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_SCHEME)) {
					// 如原商机阶段为需求分析，并且新商机阶段为制定计划
					logList.add(schemeLog);
				}
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_OFFER)) {
					// 如原商机阶段为需求分析，并且新商机阶段为竞标投标
					logList.add(schemeLog);
					logList.add(offerLog);
				}
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_DELIVER)) {
					// 如原商机阶段为需求分析，并且新商机阶段为持续发货
					logList.add(schemeLog);
					logList.add(offerLog);
					logList.add(deliverLog);
				}
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_SCHEME)) {
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_OFFER)) {
					// 如原商机阶段为制定计划，并且新商机阶段为竞标投标
					logList.add(offerLog);
				}
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_DELIVER)) {
					// 如原商机阶段为制定计划，并且新商机阶段为持续发货
					logList.add(offerLog);
					logList.add(deliverLog);
				}
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_OFFER)) {
				if (StringUtils.equals(newStep,
						Constant.BO_STEP_DELIVER)) {
					// 如原商机阶段为竞标投标，并且新商机阶段为持续发货
					logList.add(deliverLog);
				}
			} else if (StringUtils.equals(lastStep,
					Constant.BO_STEP_DELIVER)) {
				// 如原商机阶段为持续发货
			}
			return logList;
		}
		
		/**
		 * 
		 * @Title: getBoOperationLog
		 *  <p>
		 * @Description: 生成指定的商机阶段变更日志
		 * </p>
		 * @author 唐亮
		 * @version 0.1 2014-5-20
		 * @return BoOperationLog
		 * @throws
		 */
		public static BoOperationLog getBoOperationLog(String boId,
				String lastStep, String newStep, String operator, Date doneTime) {
			BoOperationLog log = new BoOperationLog();
			log.setBoId(boId);
			log.setPrevStep(lastStep);
			log.setCurrentStep(newStep);
			log.setOperator(operator);
			log.setOperationTime(doneTime);
			return log;
		}
}
