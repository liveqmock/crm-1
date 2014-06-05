package com.deppon.crm.module.custview.server.manager.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.deppon.crm.module.authorization.server.service.impl.UserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.CustomerWaybill;
import com.deppon.crm.module.client.order.domain.CustomerWaybillInfo;
import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.common.server.manager.IAreaAddressManager;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.server.manager.impl.IntegralManager;
import com.deppon.crm.module.customer.server.manager.impl.MemberManager;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.custview.server.manager.IMember360ViewManager;
import com.deppon.crm.module.custview.server.service.IStatisticsService;
import com.deppon.crm.module.custview.shared.domain.AccountView;
import com.deppon.crm.module.custview.shared.domain.ComplaintRecompenseView;
import com.deppon.crm.module.custview.shared.domain.ContactView;
import com.deppon.crm.module.custview.shared.domain.ContractView;
import com.deppon.crm.module.custview.shared.domain.CrossMapping;
import com.deppon.crm.module.custview.shared.domain.IntegratedCustDevView;
import com.deppon.crm.module.custview.shared.domain.LatelyTrade;
import com.deppon.crm.module.custview.shared.domain.MarketingActivity;
import com.deppon.crm.module.custview.shared.domain.MemberIntegratedInfoView;
import com.deppon.crm.module.custview.shared.domain.MemberPointsView;
import com.deppon.crm.module.custview.shared.domain.MemberView;
import com.deppon.crm.module.custview.shared.domain.OperationAnalysis;
import com.deppon.crm.module.custview.shared.domain.SearchFastCondition;
import com.deppon.crm.module.custview.shared.domain.TwelveMonth;
import com.deppon.crm.module.custview.shared.domain.WaybillInfo;
import com.deppon.crm.module.custview.shared.util.ExcelExporter;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.IProviderFor360Manager;
import com.deppon.crm.module.marketing.server.manager.impl.WarnLostCustManagerImp;
import com.deppon.crm.module.marketing.shared.WarnLostInfoFor360;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.impl.EmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * @description 会员360度视图manager实现.
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */

public class Member360ViewManager implements IMember360ViewManager {
	/*
	 * 文件名称
	 */
	private static final String EXCELNAME = "360excel.xlsx";
	/*
	 * 快递360文件名称
	 */
	private static final String EXCELNAME_EXP = "360express.xlsx";
	/*
	 * 按照客户编号查询
	 */
	private static final String CUSTNUM = "customerCode";
	/*
	 * 按照联系人编号查询
	 */
	private static final String CONNECTNUM = "contactCoding";
	/*
	 * 按照手机号查询
	 */
	private static final String MOBILE = "mobileNumber";
	
	private static final String CUSTTYPE_LTT = "LTT";
	
	private static final String CUSTTYPE_EXPRESS = "EXPRESS";
	/*
	 * 用户
	 */
	private UserService userService;
	/*
	 * 职员
	 */
	private EmployeeService employeeService;
	/*
	 * 区域
	 */
	private IAreaAddressManager areaAddressManager;
	/*
	 * 部门
	 */
	private IDepartmentService departmentService;
	/*
	 * 会员
	 */
	private IAlterMemberManager alterMemberManager;
	/*
	 * 会员
	 */
	private MemberManager memberManager;
	/*
	 * 客户积分
	 */
	private IntegralManager integralManager;
	/*
	 * 理赔
	 */
	private RecompenseManager recompenseManager;
	/*
	 * 投诉
	 */
	private IComplaintManager complaintManager;
	/*
	 * 订单
	 */
	private IOrderManager orderManager;
	/*
	 * 合同
	 */
	private IContractManager contractManager;
	/*
	 * 客户开发
	 */
	private IProviderFor360Manager providerFor360Manager;
	/*
	 * 360视图分析
	 */
	private IStatisticsService statisticsService;
	//客户信用manager
	private ICustCreditManager custCreditManager;
	//流失预警manager
	private WarnLostCustManagerImp warnLostCustManager;
	//运单接口
	private IWaybillOperate waybillOperateImpl;
	//商机接口
	private IBusinessOpportunityManager boManager;

	/**
	 * @param userService : set the property userService.
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param employeeService : set the property employeeService.
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @param areaAddressManager : set the property areaAddressManager.
	 */
	public void setAreaAddressManager(IAreaAddressManager areaAddressManager) {
		this.areaAddressManager = areaAddressManager;
	}

	/**
	 * @param departmentService : set the property departmentService.
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * @param alterMemberManager : set the property alterMemberManager.
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	/**
	 * @param memberManager : set the property memberManager.
	 */
	public void setMemberManager(MemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * @param integralManager : set the property integralManager.
	 */
	public void setIntegralManager(IntegralManager integralManager) {
		this.integralManager = integralManager;
	}

	/**
	 * @param recompenseManager : set the property recompenseManager.
	 */
	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	/**
	 * @param complaintManager : set the property complaintManager.
	 */
	public void setComplaintManager(IComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}

	/**
	 * @param orderManager : set the property orderManager.
	 */
	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	/**
	 * @param contractManager : set the property contractManager.
	 */
	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}

	/**
	 * @param providerFor360Manager : set the property providerFor360Manager.
	 */
	public void setProviderFor360Manager(
			IProviderFor360Manager providerFor360Manager) {
		this.providerFor360Manager = providerFor360Manager;
	}

	/**
	 * @param statisticsService : set the property statisticsService.
	 */
	public void setStatisticsService(IStatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	
	/**
	 * @param custCreditManager : set the property custCreditManager.
	 */
	public void setCustCreditManager(ICustCreditManager custCreditManager) {
		this.custCreditManager = custCreditManager;
	}

	/**
	 * @param warnLostCustManager : set the property warnLostCustManager.
	 */
	public void setWarnLostCustManager(WarnLostCustManagerImp warnLostCustManager) {
		this.warnLostCustManager = warnLostCustManager;
	}
	
	/**
	 * @param waybillOperateImpl : set the property waybillOperateImpl.
	 */
	public void setWaybillOperateImpl(IWaybillOperate waybillOperateImpl) {
		this.waybillOperateImpl = waybillOperateImpl;
	}

	/**
	 * @param boManager : set the property boManager.
	 */
	public void setBoManager(IBusinessOpportunityManager boManager) {
		this.boManager = boManager;
	}

	/**
	 * 
	 * <p>
	 * Description:非高级查询
	 * 	1.如果没有填写客户编码，根据其他信息查询出客户编码再根据客户编码获取客户综合信息.
	 *  2.如果填写了客户编码，直接根据客户编码查询出客户综合信息
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-26
	 * @param searchFastCondition
	 * @return
	 * MemberIntegratedInfoView
	 */
	public MemberIntegratedInfoView searchMemberInfoFast(
			SearchFastCondition searchFastCondition) {
		String custNo = null;
		MemberCondition condition = new MemberCondition();
		
		if (searchFastCondition.getCustNumber() != null
				&& !"".equals(searchFastCondition.getCustNumber())) {
			/*
			 * 得到客户编码
			 */
			custNo = searchFastCondition.getCustNumber();
		} else if (searchFastCondition.getLinkManNumber() != null  
				&& !"".equals(searchFastCondition.getLinkManNumber())) {
			/*
			 * 得到并设置联系人编码
			 */
			condition.setLinkManNumber(searchFastCondition.getLinkManNumber());
		} else if (searchFastCondition.getMobile() != null
				&& !"".equals(searchFastCondition.getMobile())) {
			/*
			 * 得到并设置手机号码
			 */
			condition.setMobile(searchFastCondition.getMobile());
		} else if (searchFastCondition.getTaxregNumber() != null
				&& !"".equals(searchFastCondition.getTaxregNumber())) {
			/*
			 * 得到并设置税务登记号
			 */
			condition.setTaxregNumber(searchFastCondition.getTaxregNumber());
		} else if (searchFastCondition.getIdCard() != null
				&& !"".equals(searchFastCondition.getIdCard())) {
			/*
			 * 得到并设置证件号码
			 */
			condition.setIdCard(searchFastCondition.getIdCard());
		} else {
			return null;
		}
		if (custNo != null && !"".equals(custNo)) {
			/*
			 * 返回客户360度信息对象
			 */
			return getMemberIntegratedInfo(custNo, CUSTNUM);
		} else {
			List<MemberResult> mrlist = alterMemberManager
					.searchMember(condition);
			if (mrlist.size() > 0) {
				if(Constant.CUST_GROUP_PC_CUSTOMER.equals(mrlist.get(0).getCustGroup()) ||
						Constant.CUST_GROUP_SC_CUSTOMER.equals(mrlist.get(0).getCustGroup())){
					/*
					 * 潜散客不能查询360
					 */
					CustomerException fe = new CustomerException(
							CustomerExceptionType.CAN_NOT_SEARCH360);
					throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
							new Object[] {}) {
					};
				}
				custNo = mrlist.get(0).getCustNum();
				/*
				 * 返回客户360度信息对象
				 */
				return getMemberIntegratedInfo(custNo, CUSTNUM);
			} else {
				return null;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#
	 * getMemberIntegratedInfo(java.lang.String)
	 */
	@SuppressWarnings({ "unused", "deprecation"})
	@Override
	public MemberIntegratedInfoView getMemberIntegratedInfo(String custNo,
			String comboVal) {
		/*
		 * 客户360度综合信息
		 */
		MemberIntegratedInfoView miiview = new MemberIntegratedInfoView();
		MemberIntegral memberIntegral = null;
		if (custNo != null && comboVal.equals(CUSTNUM)) {
			/*
			 * 1、根据客户编码获得客户积分信息
			 */
			memberIntegral = this.integralManager
					.getMemberIntegralByMemberNumFor360(custNo);
		} else if (custNo != null && comboVal.equals(CONNECTNUM)) {
			/*
			 * 2、根据联系人编码获得客户积分信息
			 */
			memberIntegral = this.integralManager
					.getMemberIntegralByContactNum(custNo);
		} else if (custNo != null && comboVal.equals(MOBILE)) {
			/*
			 * 3、根据联系人手机号获得客户积分信息
			 */
			memberIntegral = this.integralManager
					.getMemberIntegralByContactMobile(custNo);
		} else {
			return null;
		}
		/*
		 * CRM-3129 删除custNo != null判断  author By 许华龙
		 */
		if ( memberIntegral != null) {
			Member member = memberIntegral.getMember();
			if(Constant.CUST_GROUP_PC_CUSTOMER.equals(member.getCustGroup()) ||
					Constant.CUST_GROUP_SC_CUSTOMER.equals(member.getCustGroup())){
				/*
				 * 潜散客不能查询360
				 */
				CustomerException fe = new CustomerException(
						CustomerExceptionType.CAN_NOT_SEARCH360);
				throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
						new Object[] {}) {
				};
			}
			
			if (member != null && member.getId() != null) {
				String custId = member.getId();
				miiview.setMemberIntegral(memberIntegral);

				/*
				 * 2、根据客户ID获取获取客户3个联系人积分信息，其中第一个为一个主联系人
				 */
				miiview.setContactIntegralList(this.integralManager
						.get3ContactIntegral(custId));

				/*
				 *  3、根据客户ID获取获取历史投诉统计数据对象
				 */
				miiview.setComplaintStatistics(this.statisticsService
						.getComplaintStatisticsByCustId(custId));

				/*
				 * 4、根据客户ID获取获取历史理赔统计数据对象
				 */
				miiview.setRecStatisticsList(this.statisticsService
						.getRecStatisticsListByCustId(custId));

				/*
				 * 5、根据客户ID获取近6个月份的发货量金额、到货量金额（分别存放于不同List中）
				 */
				Date endDate = new Date();
				Date startDate = new Date(endDate.getYear(),
						endDate.getMonth() - 6, endDate.getDay());
				//零担运营数据
				List<OperationAnalysis> oaList = statisticsService
						.getLAMoneyByCondtion(custId, startDate,endDate);
				//快递运营数据
				List<OperationAnalysis> oaListExp = statisticsService
						.getLAMoneyExpByCondtion(custId, startDate,endDate);
				miiview.setTwelveMonthList(this.getSummaryPlusListByCustId(oaList,oaListExp));
				miiview.setOperationAnalysisList(oaList);
				miiview.setOperationAnalysisExpList(oaListExp);

				/*
				 * 6、根据客户Id,获取最近一次交易统计分析数据以得到最近一次交易日期
				 */
				miiview.setLatelyTrade(this.getCustLatelyTrade(custId));

				/*
				 * 7、根据客户ID获取该客户最近一次投诉记录
				 */
				miiview.setComplaint(this.complaintManager
						.getMostRecentlyComplaintByCustCode(custId));
				/*
				 * 8、根据客户ID获取该客户最近一次理赔记录
				 */
				List<RecompenseApplication> recompenseList = this.recompenseManager
						.getRecompenseListByCustId(custId);
				if (recompenseList != null && recompenseList.size() > 0) {
					miiview.setRecompense(recompenseList.get(0));
				}

				/*
				 * 9、根据客户ID获取该客户最近一次的合同信息
				 */
				miiview.setContract(this.contractManager
						.getLastestContract(custId));

				/*
				 * 10、根据客户ID获取该客户最近一次的维护信息列表（回访记录）
				 */
				miiview.setReturnVisit(this.providerFor360Manager
						.getLastestMaintainInfo(custId));

				return miiview;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#
	 * getMemberBasicinfo(java.lang.String)
	 */
	@Override
	public MemberView getMemberBasicInfoByCustId(String custId) {
		/*
		 * 客户基本信息视图
		 */
		MemberView memberView = new MemberView();

		/*
		 * 1、根据客户ID获得客户基本信息
		 */
		Member member = this.alterMemberManager.getMemberById(custId);
		if (member != null) {
			/*
			 * 设置省份
			 */
			member.setProvince(this.getProvinceNameByProvinceId(member
					.getProvinceId()));
			/*
			 * 设置城市
			 */
			member.setCity(this.getCityNameByCityId(member.getCityId()));

			memberView.setMember(member);

			/*
			 * 2、根据ID找其对应名称
			 * 集中结算部门
			 */
			String focusDeptId = member.getFocusDeptId();
			/*
			 * 所属区域
			 */
			String areaId = member.getAreaId();
			/*
			 * 所属部门
			 */
			String deptId = member.getDeptId();
			String createUserId = member.getCreateUser();
			String updateUserId = member.getModifyUser();
			/*
			 * 设置集中结算部门
			 */
			memberView.setFocusDeptName(this.getDeptNameByDeptId(focusDeptId));
			/*
			 * 设置所属区域名称
			 */
			memberView.setAreaName(this.getAreaNameByAreaId(areaId));
			/*
			 * 设置所属部门
			 */
			memberView.setDeptName(this.getDeptNameByDeptId(deptId));
			/*
			 * 设置创建人名称
			 */
			memberView.setCreaterName(this.getEmpNameByEmpId(createUserId));
			/*
			 * 设置创建人所属部门
			 */
			memberView.setCreateDeptName(this.getDeptNameByEmpId(createUserId));
			/*
			 * 设置 更新人名称
			 */
			memberView.setUpdaterName(this.getEmpNameByEmpId(updateUserId));
			/*
			 * 设置更新人名称所属部门
			 */
			memberView.setUpdateDeptName(this.getDeptNameByEmpId(updateUserId));
		}
		/*
		 * 返回用户信息
		 */
		return memberView;
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getAllContactInfoByCustId(java.lang.String)
	 */
	@Override
	public List<Contact> getAllContactInfoByCustId(String custId) {
		/*
		 * 返回 获得客户的所有联系人信息
		 */
		return this.memberManager.getContactsByMemberId(custId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#
	 * getContactInfoByID(java.lang.String)
	 */
	@SuppressWarnings("unused")
	@Override
	public ContactView getContactInfoById(String contactId) {
		ContactView contactView = new ContactView();

		/*
		 * 1、根据联系人ID获得该联系人详细信息（包含接货地址信息列表）
		 */
		Contact contact = this.memberManager
				.getContactDetailInfoById(contactId);
		if (contact != null) {
			contactView.setContact(contact);

			/*
			 * 2、根据ID找其对应名称
			 */
			String createUserId = contact.getCreateUser();
			String updateUserId = contact.getModifyUser();
			/*
			 * 设置创建人名称
			 */
			contactView.setCreaterName(this.getEmpNameByEmpId(createUserId));
			/*
			 * 设置创建人部门
			 */
			contactView.setCreateDeptName(this.getDeptNameByEmpId(createUserId));
			/*
			 * 设置更新人名称
			 */
			contactView.setUpdaterName(this.getEmpNameByEmpId(updateUserId));
			/*
			 * 设置更新人部门
			 */
			contactView.setUpdateDeptName(this.getDeptNameByEmpId(updateUserId));
		}
		/*
		 * 返回 联系人信息
		 */
		return contactView;
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getAccountListByCustId(java.lang.String)
	 */
	@Override
	public List<Account> getAccountListByCustId(String custId) {
		/*
		 * return 根据会员Id查想会员相关银行账号信息
		 */
		return this.alterMemberManager.getAccountsByMemberId(custId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getAccountInfoById(java.lang.String)
	 */
	@SuppressWarnings("unused")
	@Override
	public AccountView getAccountInfoById(String accountId) {
		AccountView accountView = new AccountView();
		/*
		 * 1、 根据ID获得详情
		 */
		Account account = this.alterMemberManager.getAccountById(accountId);
		if (account != null) {
			accountView.setAccount(account);

			/*
			 * 2、根据ID找其对应名称
			 */
			String createUserId = account.getCreateUser();
			String updateUserId = account.getModifyUser();
			/*
			 * 设置创建人名称
			 */
			accountView.setCreaterName(this.getEmpNameByEmpId(createUserId));
			/*
			 * 设置创建人部门
			 */
			accountView.setCreateDeptName(this.getDeptNameByEmpId(createUserId));
			/*
			 * 设置更新人名称
			 */
			accountView.setUpdaterName(this.getEmpNameByEmpId(updateUserId));
			/*
			 * 设置更新人部门
			 */
			accountView.setUpdateDeptName(this.getDeptNameByEmpId(updateUserId));
		}
		/*
		 * return 财务信息
		 */
		return accountView;
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getContractListByCustId(java.lang.String)
	 */
	@Override
	public List<ContractDetailView> getContractListByCustId(String custId) {
		/*
		 * return 根据会员Id查找出相关的所有合同信息
		 */
		return this.contractManager.getContractsByMemberId(custId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getContractInfoById(java.lang.String)
	 */
	@SuppressWarnings("unused")
	@Override
	public ContractView getContractInfoById(String contractId) {
		ContractView contractView = new ContractView();

		/*
		 * 1、根据合同ID获得合同详细信息（包含附件信息）
		 */
		ContractDetailView contract = this.contractManager
				.getContractDetailById(contractId);
		if (contract != null) {
			contractView.setContract(contract);

			/*
			 * 2、根据ID找其对应名称
			 */
			String createUserId = contract.getCreateUser();
			contractView.setCreaterName(this.getEmpNameByEmpId(createUserId));
			
			contractView.setModifyUser(this.getEmpNameByEmpId(contract.getModifyUser()));
			
			contractView.setCreateDeptName(this
					.getDeptNameByEmpId(createUserId));
		}
		return contractView;
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getIntegratedCustDevByCustId(java.lang.String)
	 */
	@Override
	public IntegratedCustDevView getIntegratedCustDevByCustId(String custId) {
		IntegratedCustDevView icdview = new IntegratedCustDevView();

		/*
		 * 1、根据客户ID获得客户开发计划/日程综合信息列表
		 */
		icdview.setPlanScheduleList(this.providerFor360Manager
				.getPlanScheduleDetail(custId));
		/*
		 * 2、根据客户ID获得客户维护（回访）综合信息列表
		 */
		icdview.setVisitRecordList(this.providerFor360Manager
				.getVisitRecords(custId));
		return icdview;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getOrderListByCustId(java.lang.String, java.util.Date, java.util.Date, int, int)
	 */
	@Override
	public List<Order> getOrderListByCustId(String custId, Date startDate,
			Date endDate, int start, int limit) {
		/*
		 * return 订单集合
		 */
		return this.orderManager.getOrderListByCustId(custId, startDate,
				endDate, start, limit);
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getOrderCountByCustId(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public Long getOrderCountByCustId(String custId, Date startDate,
			Date endDate) {
		/*
		 * return 订单记录数
		 */
		return this.orderManager.getOrderCountByCustId(custId, startDate,
				endDate);
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getMemberPointByCustId(java.lang.String)
	 */
	@Override
	public MemberPointsView getMemberPointByCustId(String custId) {
		MemberPointsView mpv = new MemberPointsView();
		/*
		 * 1、获得客户总积分
		 */
		mpv.setMemberIntegral(this.integralManager
				.getMemberIntegralListByMemberId(custId));
		/*
		 * 2、获得客户联系人积分集合
		 */
		mpv.setContactIntegralList(this.integralManager
				.getContactIntegralListByMemId(custId));
		return mpv;
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getComplaintRecompense(java.lang.String, java.lang.String)
	 */
	@Override
	public ComplaintRecompenseView getComplaintRecompense(String custId,
			String custNo) {
		ComplaintRecompenseView crview = new ComplaintRecompenseView();

		/*
		 * 1、根据客户编码获取该客户所有投诉信息列表（按投诉时间倒序排列）
		 */
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setComplainid(custId);
		Map<String, Object> map = this.complaintManager
				.searchComplaintIncludeSatisfy(condition);
		if (map != null) {
			crview.setComplaintList((List<Complaint>) map.get("complaintList"));
			map.get("count");
		}

		/*
		 * 2、根据客户ID获取该客户所有理赔信息列表（按理赔时间倒序排列）
		 */
		crview.setRecompenseList(this.recompenseManager
				.getRecompenseListByCustId(custId));
		return crview;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#
	 * getCustLatelyTrade(java.lang.String)
	 */
	@Override
	public LatelyTrade getCustLatelyTrade(String custId) {
		/*
		 * 1、构造对象
		 */
		return this.statisticsService
				.getCustLatelyTradeDateByCustId(custId);
	}

	/**
	 * 
	 * @description 根据用户ID获得用户所在部门名称.
	 * @author 安小虎
	 * @version 0.1 2012-4-26
	 * @param 用户ID
	 * @date 2012-4-26
	 * @return 部门名称
	 * @update 2012-4-26 上午11:43:01
	 */
	@SuppressWarnings("unused")
	private String getDeptNameByUserId(String userId) {
		String result = null;
		if (userId != null && !"".equals(userId)) {
			User user = userService.getUserById(userId);
			if (user != null) {
				Employee employee = new Employee();
				employee.setEmpCode(user.getLoginName());
				/*
				 * 根据登录名查询员工集合信息
				 */
				List<Employee> emps = employeeService.queryAll(employee);
				Employee emp = emps.get(0);
				/*
				 * If语句必须使用括号 
				 * 侯斌飞
				 */
				if (emp != null && emp.getDeptId() != null){
					result = emp.getDeptId().getDeptName();
				}
			}
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getOperationAnalysisDetailListByCustId(java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<OperationAnalysis> getOperationAnalysisDetailListByCustId(String custType,
			String custNum,String custId, Date startDate, Date endDate) {
		List<OperationAnalysis> oaList = new ArrayList<OperationAnalysis>();
		/*
		 * 返回营运分析数据集合
		 */
		if(CUSTTYPE_LTT.equals(custType)){
			oaList = statisticsService
					.getOperationAnalysisListByCustId(custType,custId, startDate, endDate);
			for (int i = 0; i < oaList.size(); i++) {
				/*
				 * 设置到达货量
				 */
				oaList.get(i).setArriveWeight(
						Double.toString(Double.parseDouble(oaList.get(i)
								.getArriveWeight()) / 1000));
				/*
				 * 设置到达货量
				 */
				oaList.get(i).setLeaveWeight(
						Double.toString(Double.parseDouble(oaList.get(i)
								.getLeaveWeight()) / 1000));
				/*
				 * 设置代收货款 
				 */
				oaList.get(i).setAgentreceivePay(
						Double.toString(Double.parseDouble(oaList.get(i)
								.getAgentreceivePay()) / 1000));
			}
		}else{
			oaList = statisticsService
					.getOperationAnalysisListExpByCustNo(custNum, startDate, endDate);
			for (int i = 0; i < oaList.size(); i++) {
				/*
				 * 设置代收货款 
				 */
				oaList.get(i).setAgentreceivePay(
						Double.toString(Double.parseDouble(oaList.get(i)
								.getAgentreceivePay()) / 1000));
			}
		}
		return oaList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getOperationAnalysisListByCustId(java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<TwelveMonth> getOperationAnalysisListByCustId(String custType,String custId,
			Date startDate, Date endDate) {
		List<TwelveMonth> twelveMonthList = new ArrayList<TwelveMonth>();
		/*
		 * 年月
		 */
		TwelveMonth yearMonth = new TwelveMonth();
		/*
		 * 出发货量
		 */
		TwelveMonth leaveWeight = new TwelveMonth();
		/*
		 * 到达货量
		 */
		TwelveMonth arriveWeight = new TwelveMonth();
		/*
		 * 出发票数
		 */
		TwelveMonth leaveBill = new TwelveMonth();
		/*
		 * 到达票数
		 */
		TwelveMonth arriveBill = new TwelveMonth();
		/*
		 * 优势业务占比
		 */
		TwelveMonth advantageBusiRate = new TwelveMonth();
		/*
		 * 汽运占比
		 */
		TwelveMonth motorRate = new TwelveMonth();
		/*
		 * 发货总金额
		 */
		TwelveMonth leaveMoney = new TwelveMonth();
		/*
		 * 保险费
		 */
		TwelveMonth insurance = new TwelveMonth();
		/*
		 * 保营比
		 */
		TwelveMonth insuranceRate = new TwelveMonth();
		/*
		 * 理赔金额
		 */
		TwelveMonth claimsMoney = new TwelveMonth();
		/*
		 * 包装费
		 */
		TwelveMonth fpackage = new TwelveMonth();
		/*
		 * 包营比
		 */
		TwelveMonth packageRate = new TwelveMonth();
		/*
		 * 代收货款
		 */
		TwelveMonth agentreceivePay = new TwelveMonth();
		/*
		 * 代收比
		 */
		TwelveMonth agentreceivepayRate = new TwelveMonth();
		/*
		 * 网上营业厅票数占比
		 */
		TwelveMonth wtvoteRate = new TwelveMonth();
		/*
		 * 呼叫中心票数占比
		 */
		TwelveMonth callvoteRate = new TwelveMonth();
		/*
		 * 营业厅票数占比
		 */
		TwelveMonth yytvoteRate = new TwelveMonth();
		/*
		 * 第三方渠道票数占比
		 */
		TwelveMonth othervoteRate = new TwelveMonth();
		/*
		 * 到货金额
		 */
		TwelveMonth arriveMoney = new TwelveMonth();
		/*
		 * 返回营运分析数据集合
		 */
		List<OperationAnalysis> oaList = statisticsService
				.getOperationAnalysisListByCustId(custType,custId, startDate, endDate);
		try {
			Class clazz = Class.forName(TwelveMonth.class.getName());
		
			Method meth;
			for (int i = 0; i < oaList.size(); i++) {
				OperationAnalysis operationAnalysis = oaList.get(i);
				String istr = Integer.toString(i + 1);
				meth = clazz.getDeclaredMethod("setMonth" + istr, String.class);

				/*
				 * 年月
				 */
				meth.invoke(yearMonth, operationAnalysis.getYearMonth());
				if(CUSTTYPE_LTT.equals(custType)){
					/*
					 * 出发货量
					 */
					meth.invoke(leaveWeight,
							Double.toString(Double.parseDouble(operationAnalysis
									.getLeaveWeight()) / 1000));
					/*
					 * 到达货量
					 */
					meth.invoke(arriveWeight,
							Double.toString(Double.parseDouble(operationAnalysis
									.getArriveWeight()) / 1000));
				}else if(CUSTTYPE_EXPRESS.equals(custType)){
					/*
					 * 出发货量
					 */
					meth.invoke(leaveWeight,
							Double.toString(Double.parseDouble(operationAnalysis
									.getLeaveWeight()) ));
					/*
					 * 到达货量
					 */
					meth.invoke(arriveWeight,
							Double.toString(Double.parseDouble(operationAnalysis
									.getArriveWeight()) ));
				}
				/*
				 * 出发票数
				 */
				meth.invoke(leaveBill, operationAnalysis.getLeaveBill());
				/*
				 * 到达票数
				 */
				meth.invoke(arriveBill, operationAnalysis.getArriveBill());
				/*
				 * 优势业务占比
				 */
				meth.invoke(advantageBusiRate,
						operationAnalysis.getAdvantageBusiRate());
				/*
				 * 汽运占比
				 */
				meth.invoke(motorRate, operationAnalysis.getMotorRate());
				/*
				 * 发货总金额
				 */
				meth.invoke(leaveMoney, operationAnalysis.getLeaveMoney());
				/*
				 * 保险费
				 */
				meth.invoke(insurance, operationAnalysis.getInsurance());
				/*
				 * 保营比
				 */
				meth.invoke(insuranceRate, operationAnalysis.getInsuranceRate());
				/*
				 * 理赔金额
				 */
				meth.invoke(claimsMoney, operationAnalysis.getClaimsMoney());
				/*
				 * 包装费
				 */
				meth.invoke(fpackage, operationAnalysis.getFpackage());
				/*
				 * 包营比
				 */
				meth.invoke(packageRate, operationAnalysis.getPackageRate());
				/*
				 * 代收货款
				 */
				meth.invoke(agentreceivePay,
						Double.toString(Double.parseDouble(operationAnalysis
								.getAgentreceivePay()) / 1000));
				/*
				 * 代收比
				 */
				meth.invoke(agentreceivepayRate,
						operationAnalysis.getAgentreceivepayRate());
				/*
				 * 网上营业厅票数占比
				 */
				meth.invoke(wtvoteRate, operationAnalysis.getWtvoteRate());
				/*
				 * 呼叫中心票数占比
				 */
				meth.invoke(callvoteRate, operationAnalysis.getCallvoteRate());
				/*
				 * 营业厅票数占比
				 */
				meth.invoke(yytvoteRate, operationAnalysis.getYytvoteRate());
				/*
				 * 第三方渠道票数占比
				 */
				meth.invoke(othervoteRate, operationAnalysis.getOthervoteRate());
				/*
				 * 到货金额
				 */
				meth.invoke(arriveMoney, operationAnalysis.getArriveMoney());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * 年月
		 */
		twelveMonthList.add(yearMonth);
		/*
		 * 出发货量
		 */
		twelveMonthList.add(leaveWeight);
		/*
		 * 出发票数
		 */
		twelveMonthList.add(leaveBill);
		/*
		 * 到达货量
		 */
		twelveMonthList.add(arriveWeight);
		/*
		 * 到达票数
		 */
		twelveMonthList.add(arriveBill);
		
		if(CUSTTYPE_LTT.equals(custType)){
			/*
			 * 优势业务占比
			 */
			twelveMonthList.add(advantageBusiRate);
			/*
			 * 汽运占比
			 */
			twelveMonthList.add(motorRate);
		}

		/*
		 * 保险费
		 */
		twelveMonthList.add(insurance);
		/*
		 * 保营比
		 */
		twelveMonthList.add(insuranceRate);
		/*
		 * 理赔金额
		 */
		twelveMonthList.add(claimsMoney);
		/*
		 * 包装费
		 */
		twelveMonthList.add(fpackage);
		/*
		 * 包营比
		 */
		twelveMonthList.add(packageRate);
		/*
		 * 代收货款
		 */
		twelveMonthList.add(agentreceivePay);
		/*
		 * 代收比
		 */
		twelveMonthList.add(agentreceivepayRate);
		/*
		 * 网上营业厅票数占比
		 */
		twelveMonthList.add(wtvoteRate);
		/*
		 * 呼叫中心票数占比
		 */
		twelveMonthList.add(callvoteRate);
		/*
		 * 营业厅票数占比
		 */
		twelveMonthList.add(yytvoteRate);
		/*
		 * 第三方渠道票数占比
		 */
		twelveMonthList.add(othervoteRate);
		
		return twelveMonthList;
	}
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getSummaryPlusListByCustId(java.lang.String, java.util.Date, java.util.Date)
	 */
//	public List<TwelveMonth> getSummaryPlusListByCustId(String custType,String custNo,String custId,
//			Date startDate, Date endDate) {
	public List<TwelveMonth> getSummaryPlusListByCustId(List<OperationAnalysis>oaList,List<OperationAnalysis>oaListExp) {
		List<TwelveMonth> twelveMonthList = new ArrayList<TwelveMonth>();
		/*
		 * 年月
		 */
		TwelveMonth yearMonth = new TwelveMonth();
		/*
		 * 发货总金额
		 */
		TwelveMonth leaveMoney = new TwelveMonth();
		/*
		 * 到货金额
		 */
		TwelveMonth arriveMoney = new TwelveMonth();
		
		/*
		 * 发货总金额
		 */
		TwelveMonth leaveMoneyExp = new TwelveMonth();
		/*
		 * 到货金额
		 */
		TwelveMonth arriveMoneyExp = new TwelveMonth();
		try {
			Class clazz = Class.forName(TwelveMonth.class.getName());
			Method meth;
			for (int i = 0; i < oaList.size() && i<12; i++) {
				/*
				 * 营运分析
				 */
				OperationAnalysis operationAnalysis = oaList.get(i);
				String istr = Integer.toString(i + 1);
				meth = clazz.getDeclaredMethod("setMonth" + istr, String.class);
				/*
				 * 年月
				 */
				meth.invoke(yearMonth, operationAnalysis.getYearMonth());
				/*
				 * 发货总金额
				 */
				meth.invoke(leaveMoney, operationAnalysis.getLeaveMoney());
				/*
				 * 到货金额
				 */
				meth.invoke(arriveMoney, operationAnalysis.getArriveMoney());
			}
			for (int i = 0; i < oaListExp.size() &&i<12; i++) {
				/*
				 * 营运分析
				 */
				OperationAnalysis operationAnalysis = oaListExp.get(i);
				String istr = Integer.toString(i + 1);
				meth = clazz.getDeclaredMethod("setMonth" + istr, String.class);
                
				/*
				 * 发货总金额
				 */
				meth.invoke(leaveMoneyExp, operationAnalysis.getLeaveMoney());
				/*
				 * 到货金额
				 */
				meth.invoke(arriveMoneyExp, operationAnalysis.getArriveMoney());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * 年月
		 */
		twelveMonthList.add(yearMonth);
		/*
		 * 发货总金额
		 */
		twelveMonthList.add(leaveMoney);
		/*
		 * 到货金额
		 */
		twelveMonthList.add(arriveMoney);
		/*
		 * 快递发货总金额
		 */
		twelveMonthList.add(leaveMoneyExp);
		/*
		 * 快递到货金额
		 */
		twelveMonthList.add(arriveMoneyExp);
		
		return twelveMonthList;
	}

	/**
	 * 
	 * @description 根据用户ID获得用户名称.
	 * @author 安小虎
	 * @version 0.1 2012-4-26
	 * @param 用户ID
	 * @date 2012-4-27
	 * @return 用户名称
	 * @update 2012-4-26 上午11:43:01
	 */
	@SuppressWarnings("unused")
	private String getUserNameByUserId(String userId) {
		String result = null;
		if (userId != null && !"".equals(userId)) {
			User user = userService.getUserById(userId);
			if (user != null) {
				Employee employee = new Employee();
				employee.setEmpCode(user.getLoginName());
				/*
				 * 根据登录名查询员工信息集合
				 */
				List<Employee> emps = employeeService.queryAll(employee);
				Employee emp = emps.get(0);
				/*
				 * If语句必须使用括号 
				 * 侯斌飞
				 */
				if (emp != null){
					result = emp.getEmpName();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @description 根据员工ID获得员工名称.
	 * @author 安小虎
	 * @version 0.1
	 * @param 员工ID
	 * @date 2012-5-22
	 * @return 员工名称
	 */
	@SuppressWarnings("unused")
	private String getEmpNameByEmpId(String empId) {
		String result = null;
		if (empId != null && !"".equals(empId)) {
			/*
			 * 返回员工信息
			 */
			Employee emp = this.employeeService.getEmpById(empId);
			/*
			 * If语句必须使用括号 
			 * 侯斌飞
			 */
			if (emp != null){
				result = emp.getEmpName();
			}
		}
		return result;
	}

	/**
	 * 
	 * @description 根据员工ID获得员工所在部门名称.
	 * @author 安小虎
	 * @version 0.1
	 * @param 员工ID
	 * @date 2012-5-22
	 * @return 部门名称
	 */
	@SuppressWarnings("unused")
	private String getDeptNameByEmpId(String empId) {
		String result = null;
		if (empId != null && !"".equals(empId)) {
			/*
			 * 返回员工信息
			 */
			Employee emp = this.employeeService.getEmpById(empId);
			/*
			 * If语句必须使用括号 
			 * 侯斌飞
			 */
			if (emp != null && emp.getDeptId() != null){
				result = emp.getDeptId().getDeptName();
			}
		}
		return result;
	}

	/**
	 * 
	 * @description 根据区域ID获得区域名称.
	 * @author 安小虎
	 * @version 0.1 2012-4-27
	 * @param 区域ID
	 * @date 2012-4-27
	 * @return 区域名称
	 * @update 2012-4-27 上午11:43:01
	 */
	@SuppressWarnings("unused")
	private String getAreaNameByAreaId(String areaId) {
		String result = null;
		if (areaId != null && !"".equals(areaId)) {
			/*
			 * 返回区县集合
			 */
			List<Area> list = areaAddressManager.searchAreaById(areaId);
			if (list != null && list.size() > 0) {
				result = list.get(0).getName();
			}
		}
		return result;
	}

	/**
	 * 
	 * @description 根据省份ID获得省份名称.
	 * @author 安小虎
	 * @version 0.1 2012-4-27
	 * @param 省份ID
	 * @date 2012-4-27
	 * @return 省份名称
	 * @update 2012-4-27 上午11:43:01
	 */
	@SuppressWarnings("unused")
	private String getProvinceNameByProvinceId(String provinceId) {
		String result = null;
		if (provinceId != null && !"".equals(provinceId)) {
			/*
			 * 返回省份集合
			 */
			List<Province> list = areaAddressManager
					.searchProvinceById(provinceId);
			if (list != null && list.size() > 0) {
				result = list.get(0).getName();
			}
		}
		return result;
	}

	/**
	 * 
	 * @description 根据城市ID获得城市I名称.
	 * @author 安小虎
	 * @version 0.1 2012-4-27
	 * @param 城市ID
	 * @date 2012-4-27
	 * @return 城市名称
	 * @update 2012-4-27 上午11:43:01
	 */
	@SuppressWarnings("unused")
	private String getCityNameByCityId(String cityId) {
		String result = null;
		if (cityId != null && !"".equals(cityId)) {
			/*
			 * 返回城市集合
			 */
			List<City> list = areaAddressManager.searchCityById(cityId);
			if (list != null && list.size() > 0) {
				result = list.get(0).getName();
			}
		}
		return result;
	}

	/**
	 * 
	 * @description 根据部门ID获得用户所在部门名称.
	 * @author 安小虎
	 * @version 0.1 2012-4-26
	 * @param 部门ID
	 * @date 2012-4-26
	 * @return 部门名称
	 * @update 2012-4-27 上午11:43:01
	 */
	@SuppressWarnings("unused")
	private String getDeptNameByDeptId(String deptId) {
		String result = null;
		if (deptId != null && !"".equals(deptId)) {
			/*
			 * 返回部门信息
			 */
			Department dept = departmentService.queryById(deptId);
			if (dept != null) {
				result = dept.getDeptName();
			}
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getExcrlName(java.util.Date, java.util.Date)
	 */
	@Override
	public String getExcrlName(Date searchOrderDateFrom, Date searchOrderDateTo) {
		/*
		 * 格式化日期
		 */
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String from = sf.format(searchOrderDateFrom);
		String to = sf.format(searchOrderDateTo);
		String downFileName = from + "~" + to + "运营决策分析.xlsx";
		try {
			/*
			 * 字符编码转换
			 */
			downFileName = new String(downFileName.getBytes(), "ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downFileName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#createExcel(java.util.List, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public InputStream createExcel(String custType,List<TwelveMonth> twelveMonthList,
			Date searchOrderDateFrom, Date searchOrderDateTo, String custName) {
		String path = PropertiesUtil.getInstance()
				.getProperty("excelExportTemplatePath").trim();
		String filePath = null;
		if(CUSTTYPE_LTT.equals(custType)){
			filePath= path + "/" + EXCELNAME;/* 组织文件路径 */
		}else{
			filePath = path + "/" + EXCELNAME_EXP;/* 组织文件路径 */
		}
		ExcelExporter exporter = new ExcelExporter();
		XSSFWorkbook wb = exporter.getExcel07Wb(filePath);/* 获得工作薄 */
		try {
			custName = new String(custName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		wb = exporter.setExcel07WbValue(wb, 0, 1, custName);
		/* 组织数据日期 */
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
		String fromDate = sf.format(searchOrderDateFrom);
		String toDate = sf.format(searchOrderDateTo);
		wb = exporter.setExcel07WbValue(wb, 2, 1, fromDate);
		wb = exporter.setExcel07WbValue(wb, 2, 3, toDate);
		/* 组织并设置日期数组 */ 
		List<List<Object>> listListYearMonth = new ArrayList();
		/*
		 * 添加日期
		 */
		listListYearMonth.add(getTwelveMonthListAt(twelveMonthList, 0));
		wb = exporter.setExcel07List(wb, 4, 3, listListYearMonth);
		wb = exporter.setExcel07List(wb, 13, 3, listListYearMonth);
		wb = exporter.setExcel07List(wb, 28, 3, listListYearMonth);
		wb = exporter.setExcel07List(wb, 35, 3, listListYearMonth);
		
		/* 货量结构数据 */ 
		List<List<Object>> listListVolumeOfStructure = new ArrayList<List<Object>>();
		for (int i = 1; i < 5; i++) {
			listListVolumeOfStructure.add(getTwelveMonthListAt(twelveMonthList,
					i));
		}
		wb = exporter.setExcel07List(wb, 5, 3, listListVolumeOfStructure);
		
		//如果是零担类型，包含使用产品分析；否则不包含
		if(CUSTTYPE_LTT.equals(custType)){
			wb = exporter.setExcel07List(wb, 20, 3, listListYearMonth);
			wb = exporter.setExcel07List(wb, 42, 3, listListYearMonth);
			/* 使用产品 */
			List<List<Object>> listListUseOfTheProduct = new ArrayList<List<Object>>();
			for (int i = 5; i < 7; i++) {
				listListUseOfTheProduct
						.add(getTwelveMonthListAt(twelveMonthList, i));
			}
			wb = exporter.setExcel07List(wb, 14, 3, listListUseOfTheProduct);
			/* 保营比 */
			List<List<Object>> listListPaulCampRatio = new ArrayList<List<Object>>();
			for (int i = 7; i < 10; i++) {
				listListPaulCampRatio.add(getTwelveMonthListAt(twelveMonthList, i));
			}
			wb = exporter.setExcel07List(wb, 21, 3, listListPaulCampRatio);
			/* 包营比 */ 
			List<List<Object>> listListPackageBattalionRatio = new ArrayList<List<Object>>();
			for (int i = 10; i < 12; i++) {
				listListPackageBattalionRatio.add(getTwelveMonthListAt(
						twelveMonthList, i));
			}
			wb = exporter.setExcel07List(wb, 29, 3, listListPackageBattalionRatio);
			/* 代收货款 */ 
			List<List<Object>> listListTheAmountOfPayment = new ArrayList<List<Object>>();
			for (int i = 12; i < 14; i++) {
				listListTheAmountOfPayment.add(getTwelveMonthListAt(
						twelveMonthList, i));
			}
			wb = exporter.setExcel07List(wb, 36, 3, listListTheAmountOfPayment);
			/* 代收货款 */
			List<List<Object>> listListChannelPreference = new ArrayList<List<Object>>();
			for (int i = 14; i < 18; i++) {
				/*
				 * 添加代收货款
				 */
				listListChannelPreference.add(getTwelveMonthListAt(twelveMonthList,
						i));
			}
			wb = exporter.setExcel07List(wb, 43, 3, listListChannelPreference);
		}else{
			wb = exporter.setExcel07List(wb, 21, 3, listListYearMonth);
			/* 保营比 */
			List<List<Object>> listListPaulCampRatio = new ArrayList<List<Object>>();
			for (int i = 5; i < 8; i++) {
				listListPaulCampRatio.add(getTwelveMonthListAt(twelveMonthList, i));
			}
			wb = exporter.setExcel07List(wb, 14, 3, listListPaulCampRatio);
			/* 包营比 */ 
			List<List<Object>> listListPackageBattalionRatio = new ArrayList<List<Object>>();
			for (int i = 8; i < 10; i++) {
				listListPackageBattalionRatio.add(getTwelveMonthListAt(
						twelveMonthList, i));
			}
			wb = exporter.setExcel07List(wb, 22, 3, listListPackageBattalionRatio);
			/* 代收货款 */ 
			List<List<Object>> listListTheAmountOfPayment = new ArrayList<List<Object>>();
			for (int i = 10; i < 12; i++) {
				listListTheAmountOfPayment.add(getTwelveMonthListAt(
						twelveMonthList, i));
			}
			wb = exporter.setExcel07List(wb, 29, 3, listListTheAmountOfPayment);
			/* 渠道偏好 */
			List<List<Object>> listListChannelPreference = new ArrayList<List<Object>>();
			for (int i = 12; i < 16; i++) {
				/*
				 * 渠道偏好
				 */
				listListChannelPreference.add(getTwelveMonthListAt(twelveMonthList,
						i));
			}
			wb = exporter.setExcel07List(wb, 36, 3, listListChannelPreference);
		}
		String createPath = PropertiesUtil.getInstance()
				.getProperty("excelExportFilePath").trim();
		String fileName = FileUtil.createFileName(".xlsx");
		String realPath = exporter.createExcel(wb, createPath, fileName);
		InputStream inputStream = null;
		try {
			/*
			 * 创建文件输入流
			 */
			inputStream = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			/*
			 * 跑出文件异常
			 */
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		return inputStream;
	}
	/**
	 * 
	 * <p>
	 * Description:解析月份集合<br />
	 * </p>
	 * @author hpf
	 * @version 0.1 2013-2-1
	 * @param twelveMonthList
	 * @param index
	 * @return
	 * List<Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getTwelveMonthListAt(
			List<TwelveMonth> twelveMonthList, int index) {
		List list = new ArrayList();
		/*
		 * 设置 一月至12月 信息
		 */
		list.add(twelveMonthList.get(index).getMonth1());
		list.add(twelveMonthList.get(index).getMonth2());
		list.add(twelveMonthList.get(index).getMonth3());
		list.add(twelveMonthList.get(index).getMonth4());
		list.add(twelveMonthList.get(index).getMonth5());
		list.add(twelveMonthList.get(index).getMonth6());
		list.add(twelveMonthList.get(index).getMonth7());
		list.add(twelveMonthList.get(index).getMonth8());
		list.add(twelveMonthList.get(index).getMonth9());
		list.add(twelveMonthList.get(index).getMonth10());
		list.add(twelveMonthList.get(index).getMonth11());
		list.add(twelveMonthList.get(index).getMonth12());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#
	 * getComplaintStatisticsByCustId(java.lang.String)
	 */
	@Override
	public List getComplaintStatisticsByCustId(String custId) {
		/*
		 * return 投诉分析数据
		 */
		return this.statisticsService.getComplaintStatisticsByCustId(custId);
	}

	/**
	 * <p>
	 * Description:简版360客户金额分析<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-8-29
	 * @param searchFastCondition
	 * @return
	 * MemberIntegratedInfoView
	 */
	@SuppressWarnings("unchecked")
	public MemberIntegratedInfoView searchMemberntegrateInfo(
			SearchFastCondition searchFastCondition) {
		/*
		 * 客户360度综合信息
		 */
		MemberIntegratedInfoView miiview = new MemberIntegratedInfoView();
		MemberIntegral memberIntegral = null;
		/*
		 * 1、根据客户编码获得客户积分信息
		 */
		memberIntegral = this.integralManager
				.getMemberIntegralByMemberNumFor360(searchFastCondition.getCustNumber());
		/*
		 * CRM-3129 删除custNo != null判断  author By 许华龙
		 */
		if ( memberIntegral != null) {
			Member member = memberIntegral.getMember();
			if (member != null && member.getId() != null) {
				String custId = member.getId();
				miiview.setMemberIntegral(memberIntegral);

				/*
				 * 5、根据客户ID获取近6个月份的发货量金额、到货量金额（分别存放于不同List中）
				 */
				Date endDate = new Date();
				@SuppressWarnings("deprecation")
				Date startDate = new Date(endDate.getYear(),
						endDate.getMonth() - 6, endDate.getDay());
				//零担运营数据
				List<OperationAnalysis> oaList = statisticsService
						.getLAMoneyByCondtion(custId, startDate,endDate);
				//快递运营数据
				List<OperationAnalysis> oaListExp = statisticsService
						.getLAMoneyExpByCondtion(custId, startDate,endDate);
				
				miiview.setOperationAnalysisList(oaList);
				miiview.setOperationAnalysisExpList(oaListExp);
				
				Map map= this.getSummaryPlusList(oaList,oaListExp);
				miiview.setTwelveMonthList((List<TwelveMonth>)map.get("listLtt"));
				miiview.setTwelveMonthListExp((List<TwelveMonth>)map.get("listExp"));
			}
		}
		return miiview;
	}

	/**
	 * <p>
	 * Description:简版营运数据分析表格
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-22上午10:27:06
	 * @param oaList
	 * @param oaListExp
	 * @return
	 * List<List<TwelveMonth>>
	 * @update 2014-4-22上午10:27:06
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getSummaryPlusList(
			List<OperationAnalysis> oaList, List<OperationAnalysis> oaListExp) {
		
		Map twelveMonthListlist = new HashMap();
		List<TwelveMonth>listLtt = new ArrayList<TwelveMonth>();
		List<TwelveMonth>listExp = new ArrayList<TwelveMonth>();
		/*
		 * 年月
		 */
		TwelveMonth yearMonth = new TwelveMonth();
		/*
		 * 发货总金额
		 */
		TwelveMonth leaveMoney = new TwelveMonth();
		/*
		 * 到货金额
		 */
		TwelveMonth arriveMoney = new TwelveMonth();
		/*
		 * 到货票数
		 */
		TwelveMonth arriveBill = new TwelveMonth();
		/*
		 * 发货票数
		 */
		TwelveMonth leaveBill = new TwelveMonth();
		
		/*
		 * 年月
		 */
		TwelveMonth yearMonthExp = new TwelveMonth();
		/*
		 * 发货总金额
		 */
		TwelveMonth leaveMoneyExp = new TwelveMonth();
		/*
		 * 到货金额
		 */
		TwelveMonth arriveMoneyExp = new TwelveMonth();
		/*
		 * 到货票数
		 */
		TwelveMonth arriveBillExp = new TwelveMonth();
		/*
		 * 发货票数
		 */
		TwelveMonth leaveBillExp = new TwelveMonth();
		
		try {
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(TwelveMonth.class.getName());
			Method meth;
			for (int i = 0; i < oaList.size() && i<12; i++) {
				/*
				 * 营运分析
				 */
				OperationAnalysis operationAnalysis = oaList.get(i);
				String istr = Integer.toString(i + 1);
				meth = clazz.getDeclaredMethod("setMonth" + istr, String.class);
				/*
				 * 年月
				 */
				meth.invoke(yearMonth, operationAnalysis.getYearMonth());
				/*
				 * 发货总金额
				 */
				meth.invoke(leaveMoney, operationAnalysis.getLeaveMoney());
				/*
				 * 到货金额
				 */
				meth.invoke(arriveMoney, operationAnalysis.getArriveMoney());
				/*
				 * 到货票数
				 */
				meth.invoke(arriveBill, operationAnalysis.getArriveBill());
				/*
				 * 发货票数
				 */
				meth.invoke(leaveBill, operationAnalysis.getLeaveBill());

			}
			for (int i = 0; i < oaListExp.size() &&i<12; i++) {
				/*
				 * 营运分析
				 */
				OperationAnalysis operationAnalysisExp = oaListExp.get(i);
				String istr = Integer.toString(i + 1);
				meth = clazz.getDeclaredMethod("setMonth" + istr, String.class);
				
				/*
				 * 年月
				 */
				meth.invoke(yearMonthExp, operationAnalysisExp.getYearMonth());
				/*
				 * 发货总金额
				 */
				meth.invoke(leaveMoneyExp, operationAnalysisExp.getLeaveMoney());
				/*
				 * 到货金额
				 */
				meth.invoke(arriveMoneyExp, operationAnalysisExp.getArriveMoney());
				/*
				 * 到货票数
				 */
				meth.invoke(arriveBillExp, operationAnalysisExp.getArriveBill());
				/*
				 * 发货票数
				 */
				meth.invoke(leaveBillExp, operationAnalysisExp.getLeaveBill());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		listLtt.add(yearMonth);//年月
		listLtt.add(leaveMoney);//发货金额
		listLtt.add(arriveMoney);//到货金额
		listLtt.add(leaveBill);//发货票数
		listLtt.add(arriveBill);//到货票数
		
		listExp.add(yearMonthExp);//年月
		listExp.add(leaveMoneyExp);//发货金额
		listExp.add(arriveMoneyExp);//到货金额
		listExp.add(leaveBillExp);//发货票数
		listExp.add(arriveBillExp);//到货票数
		
		twelveMonthListlist.put("listLtt",listLtt);
		twelveMonthListlist.put("listExp",listExp);
		
		return twelveMonthListlist;
	}

	/**
	 * <p>
	 * Description:根据客户ID查询客户变更历史信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#searchMemberOperationLogList(java.lang.String)
	 * @version 0.1 2014-4-1下午3:20:59
	 * @param custId
	 * @return
	 * @update 	2014-4-1下午3:20:59
	 */
	@Override
	public List<MemberOperationLog> searchMemberOperationLogList(String custId) {
		if(custId.isEmpty()){
			return null;
		}else{
			List<MemberOperationLog>list = new ArrayList<MemberOperationLog>();
			List<MemberOperationLog>list1 = alterMemberManager.searchMemberOperationLogList(custId);
			for (int i = 0; i < list1.size() && i<10; i++) {
				list.add(list1.get(i));
			}
			return list;
		}
	}

	/**
	 * <p>
	 * Description:根据日志ID查询客户变更详细信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#searchApproveDataList(java.lang.String)
	 * @version 0.1 2014-4-1下午3:20:59
	 * @param custId
	 * @return
	 * @update 	2014-4-1下午3:20:59
	 */
	@Override
	public List<ApproveData> searchApproveDataList(String logId) {
		if(logId.isEmpty()){
			return null;
		}else{
			return alterMemberManager.searchApproveDataByLogId(logId);
		}
	}

	/**
	 * <p>
	 * Description:获取客户信用信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getCustCreditByCustId(java.lang.String)
	 * @version 0.1 2014-4-4下午4:12:04
	 * @param custId
	 * @return
	 * @update 	2014-4-4下午4:12:04
	 */
	@Override
	public CustCredit getCustCreditByCustId(String custId) {
		return custCreditManager.getCustCreditByCustId(custId);
	}

	/**
	 * <p>
	 * Description:查询流失预警信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#searchWarnLostCust(java.lang.String)
	 * @version 0.1 2014-4-4下午7:12:08
	 * @param custNum
	 * @return
	 * @update 	2014-4-4下午7:12:08
	 */
	@Override
	public List<WarnLostInfoFor360> searchWarnLostCust(String custNum) {
		String str = null;
		Date endDate = new Date();
		Date startDate = new Date(endDate.getYear(),endDate.getMonth()-3,endDate.getDate());
		return warnLostCustManager.searchWarnLostCustFor360View(null, custNum, str,startDate,endDate);
	}

	/**
	 * <p>
	 * Description:查询客户某时间段内运单信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#searchWaybillInfoList(java.lang.String, java.util.Date, java.util.Date, int, int)
	 * @version 0.1 2014-4-9上午9:40:24
	 * @param custId
	 * @param searchOrderDateFrom
	 * @param searchOrderDateTo
	 * @param start
	 * @param limit
	 * @return
	 * @update 	2014-4-9上午9:40:24
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Map searchWaybillInfoList(String custNum,
			Date searchOrderDateFrom, Date searchOrderDateTo, int page,
			int limit) {
		if(searchOrderDateTo !=null){
			searchOrderDateTo = new Date(searchOrderDateTo.getYear(),searchOrderDateTo.getMonth(),searchOrderDateTo.getDate()+1);
		}
		if((searchOrderDateTo.getDate() - searchOrderDateFrom.getDate())>11){
			throw new GeneralException("查询时间超过10天") {
			};
		}
		
		CustomerWaybillInfo c = new CustomerWaybillInfo();
		try {
			 c = waybillOperateImpl.queryCustomerWaybillInfo(custNum, searchOrderDateFrom, searchOrderDateTo, page, limit);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
//		
		List<WaybillInfo>list = new ArrayList<WaybillInfo>();
		List<MarketingActivity>mkActivityList = new ArrayList<MarketingActivity>();
//		WaybillInfo w = new WaybillInfo();
//		w.setWaybillNum("213213");
//		w.setConsignee("54345");
//		w.setConsigneePhone("12342");
//		w.setConsigneeMobile("12342");
//		w.setConsigneeAddress("12342");
//		w.setTranProperty("12342");
//		w.setLadingStationName("12342");
//		w.setSendTime(new Date());
//		w.setConsigneeNumber("245212");
//		list.add(w);
//		c.setTotalNum(1);
//		
//		
//		MarketingActivity mka = new MarketingActivity();
//		mka.setActivityCode("12121");
//		mka.setActivityName("1折大促销");
//		mka.setActivityType("NATIONWIDE");
//		mka.setBillingDate(new Date());
//		mka.setEndDate(new Date());
//		mka.setPreferMethod("COUPON");
//		mka.setStartDate(new Date());
//		mka.setWaybillNo("888829022");
//		mkActivityList.add(mka);
		
		for (CustomerWaybill cw : c.getCustomerWaybill()) {
			//运单信息
			WaybillInfo w = new WaybillInfo();
			w.setWaybillNum(cw.getWaybillNo());
			w.setConsignee(cw.getReceiveCustomerContact());
			w.setConsigneePhone(cw.getReceiveCustomerPhone());
			w.setConsigneeMobile(cw.getReceiveCustomerMobilephone());
			w.setConsigneeAddress(cw.getReceiveCustomerAddress());
			w.setTranProperty(cw.getProductCode());
			w.setLadingStationName(cw.getCustomerPickupOrgCode());
			w.setSendTime(cw.getBillTime());
			w.setConsigneeNumber(cw.getReceiveCustomerName());
			list.add(w);
			if(!StringUtil.isEmpty(cw.getActiveName())){
				//商机活动
				MarketingActivity mka = new MarketingActivity();
				mka.setActivityCode(cw.getActiveCode());
				mka.setActivityName(cw.getActiveName());
				mka.setActivityType(cw.getActiveType());
				mka.setBillingDate(cw.getBillTime());
				mka.setEndDate(cw.getActiveEndTime());
				mka.setPreferMethod(cw.getDiscountType());
				mka.setStartDate(cw.getActiveStartTime());
				mka.setWaybillNo(cw.getWaybillNo());
				mkActivityList.add(mka);
			}
		}
		Map map = new HashMap();
		map.put("waybillInfoList", list);
		map.put("totalCount", ((long)c.getTotalNum()==0)?1:(long)c.getTotalNum());
		map.put("mkActivityList", mkActivityList);
		return map;
	}

	/**
	 * <p>
	 * Description:查询最近三次商机信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#searchBusinessOpportunityList(java.lang.String)
	 * @version 0.1 2014-4-21下午3:13:41
	 * @param custId
	 * @return
	 * @update 	2014-4-21下午3:13:41
	 */
	@Override
	public List<BusinessOpportunity> searchBusinessOpportunityList(String custId) {
		List<BusinessOpportunity> boList = boManager.queryBusinessOpportunityActiveByCustId(custId);
		List<BusinessOpportunity> boList1 = new ArrayList<BusinessOpportunity>();
		//取前三条记录，后台为倒序查询
		int max = boList.size()>3?3:boList.size();
		for (int i = 0; i < max; i++) {
			boList1.add(boList.get(i));
		}
		return boList1;
	}

	/**
	 * <p>
	 * Description:根据外部系统ID（客户里面的erpId）获得交叉映射信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#getCrossMappingByContactId(java.lang.String)
	 * @version 0.1 2014-4-23下午2:53:48
	 * @param contactId
	 * @return
	 * @update 	2014-4-23下午2:53:48
	 */
	@Override
	public List<CrossMapping> getCrossMappingByErpId(String erpId) {
		return statisticsService.getCrossMappingByErpId(erpId);
	}

}
