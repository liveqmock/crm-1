package com.deppon.crm.module.customer.server.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WayBillInfo;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
/**
 *
 * <p>
 * Description:客户规则<br />
 * </p>
 * @title CustomerRule.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月12日
 */
public class CustomerRule {
	
	/**
	 *  
	 * <p>
	 * 得到需要修改的数据<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-21
	 * @param sc
	 * @param scatterList 
	 * @return
	 * List<ScatterCustomer>
	 */
	public static List<ScatterCustomer> getNeedUpdateScatterCustomer(String custNature, List<ScatterCustomer> scatterList) {
		Assert.notNull(scatterList,"scatterList must not null");
		
		List<ScatterCustomer> scatterCustomerList = new ArrayList<ScatterCustomer>();
		
		for (ScatterCustomer scatterCustomer : scatterList) {
			String oldCustNature = scatterCustomer.getCustNature();
			// 不是出发达到客户，并且客户类型不一样
			if (!Constant.LEAVE_ARRIVE_CUSTOMER
					.equals(oldCustNature) && !oldCustNature.equals(custNature)) {
				scatterCustomerList.add(scatterCustomer);
			}
		}
		return scatterCustomerList;
	}
	/**
	 * 
	 * <p>
	 * 会员编码生成规则<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param memberId
	 * @return
	 * String
	 */
	public static String createCustNumberByMembrId(String memberId) {
		return StringUtils.leftPad(memberId, 6, "0");
	}
	/**
	 * 
	 * <p>
	 * 得到对应的联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contacts
	 * @param account
	 * @return
	 * Contact
	 */
	public static Contact getContact(List<Contact> contacts,Account account){
		Assert.notNull(contacts, "contacts must not null");
		Assert.notNull(account, "contact must not null");

		for (Contact contact : contacts) {
			if(isContactSameAccount(contact,account)){
				return contact;
			}
		}
		return null;
	}
	/**
	 * 
	 * <p>
	 * 这个账号是否关联的这个联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contact
	 * @param account
	 * @return
	 * boolean
	 */
	public static boolean isContactSameAccount(Contact contact, Account account) {
		Assert.notNull(contact, "contact must not null");
		Assert.notNull(account, "account must not null");
		
		String coPhone = contact.getMobilePhone();
		String coName = contact.getName();
		
		String acPhone = account.getLinkManMobile();
		String acName = account.getFinanceLinkman();
		
		return ValidateUtil.isSame(coPhone,acPhone) 
				&& ValidateUtil.isSame(coName,acName);
	}
	/**
	 * 
	 * <p>
	 * 得到偏好地址对于的接送货地址<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param shuttleAddressList
	 * @param preferenceAddress
	 * @return
	 * ShuttleAddress
	 */
	public static ShuttleAddress getShuttelAddress(List<ShuttleAddress> shuttleAddressList,PreferenceAddress preferenceAddress){
		for (ShuttleAddress shuttleAddress : shuttleAddressList) {
			if(isShuttleAddressSamePreferenceAddress(shuttleAddress,preferenceAddress)){
				return shuttleAddress;
			}
		}
		return null;
	}
	/**
	 * 
	 * <p>
	 * 偏送货地址是否关联接送货地址<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param shuttleAddress
	 * @param preferenceAddress
	 * @return
	 * boolean
	 */
	public static boolean isShuttleAddressSamePreferenceAddress(ShuttleAddress shuttleAddress,PreferenceAddress preferenceAddress){
		//详细地址
		String shAddress = shuttleAddress.getAddress();
		//地址类型
		String shAddressType = shuttleAddress.getAddressType();
		
		String preAddress = preferenceAddress.getAddress();
		String preAddressType = preferenceAddress.getAddressType();
		return ValidateUtil.isSame(shAddress,preAddress) 
				&& ValidateUtil.isSame(shAddressType,preAddressType);
	}
	/**
	 * 
	 * <p>
	 * 会员等级公式计算<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-29
	 * @param totalMoney 总金额
	 * @return
	 * String 会员等级
	 */
	public static String calculateCustLevel(double totalMoney) {
		if(totalMoney < Constant.Normol_Money){
			return Constant.CustLevel_Fail;
		}else if(totalMoney < Constant.Gold_Money){
			return Constant.CustLevel_Normol;
		}else if(totalMoney < Constant.Platinum_Money){
			return Constant.CustLevel_Gold;
		}else if(totalMoney < Constant.Diamond_Money){
			return Constant.CustLevel_Platinum;
		}else{
			return Constant.CustLevel_Diamond;
		}
	}
	/**
	 * 
	 * <p>
	 * Description:初始化客户基础数据<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月12日
	 * @param phone
	 * @param tel
	 * @param name
	 * @param deptId
	 * @param deptName
	 * @return
	 * Member
	 */
	public static Member initImplementMember(String phone, String tel,
			String name,String email,String deptId,String deptName) {
		Member  member = getDefaultMember();
		List<Contact> contacts = initContacts(tel,phone,name,email);
		//设置联系人
		member.setContactList(contacts);
		//设置会员所属部门
		member.setDeptId(deptId);
		member.setDeptName(deptName);
		return member;
	}
	/**
	 * 
	 * <p>
	 * Description:初始化散客升级客户的基本信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月12日
	 * @param custName
	 * @param tel
	 * @param phone
	 * @param linkName
	 * @param deptId
	 * @param deptName
	 * @return
	 * Member
	 */
	public static Member initUpGradeCustomerMember(String custName, String tel,
			String phone, String linkName,String deptId,String deptName) {
		Member member = getDefaultMember();
		/*会员基本信息设置*/
		//客户姓名
		member.setCustName(custName);
		/*默认联系人创建*/
		List<Contact> contacts = initContacts(tel,phone,linkName,"");
		//设置联系人
		member.setContactList(contacts);
		//设置会员所属部门
		member.setDeptId(deptId);
		member.setDeptName(deptName);
		return member;
	}
	/**
	 * 
	 * <p>
	 * Description:客户初始化<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月12日
	 * @return
	 * Member
	 */
	public static Member getDefaultMember() {
		Member member = new Member();
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
		return member;
	}
	/**
	 * 
	 * <p>
	 * Description:初始化联系人信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月12日
	 * @param tel
	 * @param phone
	 * @param linkName
	 * @return
	 * List<Contact>
	 */
	private static List<Contact> initContacts(String tel,
			String phone, String linkName,String email) {
		List<Contact> contacts = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setName(linkName);
		
		if(!ValidateUtil.objectIsEmpty(phone)){
			//联系人手机
			contact.setMobilePhone(phone);
		} 
		if(!ValidateUtil.objectIsEmpty(tel)){
			//联系人固话
			contact.setTelPhone(tel);
		}
		if(!ValidateUtil.objectIsEmpty(email)){
			//联系人固话
			contact.setEmail(email);
		}
		//主联系人
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		return contacts;
	}
	/**
	 * 
	 * <p>
	 * Description:初始化特殊会员信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月12日
	 * @param deptId
	 * @return
	 * Member
	 */
	public static Member initSepcatilMember(String deptId) {
		Member member = getDefaultMember();
		//设置为特殊客户
		member.setIsSpecial(true);
		//设置会员所属部门
		member.setDeptId(deptId);
		return member;
	}

	public static List<WayBillInfo> adapterWayBillList(
			List<WaybillInfo> interfacleWaybillList) {
		if(interfacleWaybillList == null){
			return null;
		}
		List<WayBillInfo> wayBillList = new ArrayList<WayBillInfo>();
		for (WaybillInfo waybillInfo : interfacleWaybillList) {
			wayBillList.add(adapterWayBill(waybillInfo));
		}
		return wayBillList;
	}
	
	protected static WayBillInfo adapterWayBill(WaybillInfo waybillInfo){
		WayBillInfo waybill = new WayBillInfo();
		//计算金额
		double money = waybillInfo.getPrepaid() + waybillInfo.getFreightCollect() - waybillInfo.getServicefee() - waybillInfo.getCollectionCharges();
		//获得系数
		//发货客户
		if(waybillInfo.isShipperment()){
			waybillInfo.setCustomerType(Constant.LeaveCustomer);
		//收货客户
		} else {
			waybillInfo.setCustomerType(Constant.ArriveCustomer);
		}
		double ratio = CustomerRule.getRatio(waybillInfo.getCustomerType());
		waybill.setMoney(money);
		waybill.setRatio(ratio);
		waybill.setWayBillNumber(waybillInfo.getWaybillNumber());
		return waybill;
	}
	
	/**
	 * 
	 * <p>
	 * 得到客户类型的系数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param customerType
	 * @return
	 * double
	 */
	protected static double getRatio(String customerType) {
		if(Constant.LeaveCustomer.equals(customerType)){
			return Constant.LeaveCustomerRatio;
		}else if(Constant.ArriveCustomer.equals(customerType)){
			return Constant.ArriveCustomerRatio;
		}
		throw new MemberException(MemberExceptionType.DataDictionaryError,new Object[]{"客户类型",customerType});
	}
	
	public static int getCustLevel(String custLevelType){
		if(Constant.CustLevel_Normol.equals(custLevelType)){
			return Constant.CustLevel_Normol_Level;
		}else if(Constant.CustLevel_Gold.equals(custLevelType)){
			return Constant.CustLevel_Gold_Level;
		}else if(Constant.CustLevel_Platinum.equals(custLevelType)){
			return Constant.CustLevel_Platinum_Level;
		}else if(Constant.CustLevel_Diamond.equals(custLevelType)){
			return Constant.CustLevel_Diamond_Level;
		}
		throw new MemberException(MemberExceptionType.DataDictionaryError,new Object[]{"客户等级",custLevelType});
	}
	
	/**
	 * 
	 * <p>
	 * 得到最大的客户等级<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param targetLevelList
	 * @return
	 * String
	 */
	public static String getMaxTargetLevel(List<String> targetLevelList) {
		Assert.notEmpty(targetLevelList, "targetLevelList must not empty");
		
		String maxTargetLevel = targetLevelList.get(0); 
		for (String targetLevel : targetLevelList) {
			if(getCustLevel(maxTargetLevel) < getCustLevel(targetLevel)){
				maxTargetLevel = targetLevel;
			}
		}
		return maxTargetLevel;
	}
	/**
	 * 
	 * <p>
	 * 设置会员状态<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-30
	 * @param member
	 * void
	 */
	public static void setMemberStatus(Member member,String status) {
		Assert.notNull(member, "member must not null");
		
		//设置会员状态
		member.setCustStatus(status);
		//设置联系人状态
		List<Contact> contacts = member.getContactList();
		for (Contact contact : contacts) {
			contact.setStatus(status);
			List<PreferenceAddress> list = contact.getPreferenceAddressList();
			for (PreferenceAddress preferenceAddress : list) {
				preferenceAddress.setStatus(status);
			}
		}
		//设置账户信息状态
		List<Account> accounts = member.getAccountList();
		if(accounts !=null &&accounts.size()>0){
			for (Account account : accounts) {
				account.setStatus(status);
			}
		}
		
		//设置偏好地址
		List<ShuttleAddress> list =	member.getShuttleAddressList();
		if(list!=null&&list.size()>0){
		for (ShuttleAddress shuttleAddress : list) {
			shuttleAddress.setStatus(status);
		}
		}
	}
	
	public static Contact getMainContact(List<Contact> contactList) {
		for (Contact contact : contactList) {
			if(contact.getIsMainLinkMan()){
				return contact;
			}
		}
		throw new RuntimeException("联系人集合有问题");
	}
	/**
	 * 
	 * <p>
	 * 添加联系人类型<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-24
	 * @param contactType 联系人类型的值
	 * @param addContactType 添加的类型
	 * @return
	 * String
	 */
	public static String addContactType(String contactType,int addContactType){
		String[] type = contactType.split(",");
		if(type.length != 5){
			throw new MemberException(MemberExceptionType.ContactTypeError);
		}
		type[addContactType] = Constant.contactType_True;
		return StringUtils.join(type, ",");
	}

	/**
	 * <p>
	 * Description:生成散客编码<br />
	 * </p>
	 * 散客编码规则：大写字母S+创建日期+8位随机码（例：S20120921-25896374）
	 * @author 李国文
	 * @version 0.1 2012-11-3
	 * @return
	 * String
	 */
	public static String getScatterNum(String id) {
		StringBuilder sb = new StringBuilder();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateString = sdf.format(date);
		sb.append("S").append(dateString);
		sb.append("-");
		//如果小于八位
		if (id.length() < 8) {
			sb.append(StringUtils.leftPad(id,8, '0'));
		} else {
			sb.append(id.substring(id.length()-8, id.length()));
		}
		return sb.toString();
	}

	/**
	 * 
	 * <p>
	 * Description:构造客户修改日志<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @param memberId
	 * @return
	 * MemberOperationLog
	 */
	public static MemberOperationLog createLog(String memberId,String operateType) {
		//会员操作日志
		MemberOperationLog log = new MemberOperationLog();
		//会员休息
		Member member = new Member();
		member.setId(memberId);
		log.setMember(member);
		log.setModifyUserId(ContextUtil.getCreateOrModifyUserId());
		log.setModifyUserName(ContextUtil.getCreateOrModifyUserName());
		log.setDepartment(ContextUtil.getCurrentUserDept());
		log.setModifyDate(new Date());
		log.setOperateType(operateType);
		log.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		return log;
	}
	/**
	 * 
	 * <p>
	 * 锁定审批数据<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-7-19
	 * @param updateDataAllList
	 * @param memberId
	 * @param workFlowId
	 * @return Set<ApprovingWorkflowData>
	 */
	public static Set<ApprovingWorkflowData> lockApproveData(
			List<ApproveData> updateDataAllList, String memberId,
			long workFlowId, Member member,List<Contact> databaseContacts) {
		// 需要保存的正在审批中的会员信息集合
		Set<ApprovingWorkflowData> contactWorkflowDatas = new HashSet<ApprovingWorkflowData>();

		// 需要保存的正在审批中的会员信息
		ApprovingWorkflowData workflowData;
		// 会员信息
		Contact contact;
		// 构造会员需要 保存申请工作流锁定信息
		List<ApproveData> contactApproveDatas = new ArrayList<ApproveData>();
		for (ApproveData approveData : updateDataAllList) {
			//为修改客户信息和联系人信息时
			if (approveData.getHandleType().equals(2)) {
					if (approveData.getClassName().equals("Contact")) {
						contactApproveDatas.add(approveData);
				} else if (approveData.getClassName().equals("Member")
						&& approveData.getFieldName().equals("taxregNumber")
						&& !StringUtils.isEmpty(approveData.getNewValue())) {
						workflowData = new ApprovingWorkflowData();
						contact = new Contact();
						contact.setId(memberId);
						// 设置税务登记号
						workflowData.setTaxregNumber(approveData.getNewValue());
						// 设置会员信息
						workflowData.setContactId(contact);
						// 设置工作流号
						workflowData.setWorkflowId(String.valueOf(workFlowId));
						// 设置状态
						workflowData.setStatus(true);
						contactWorkflowDatas.add(workflowData);
					}
			} else if (approveData.getHandleType().equals(1)//新增联系人信息
					&& approveData.getClassName().equals("Contact")) {
				contactApproveDatas.add(approveData);
			}
		}
		// 对会员修改会员信息遍历
		for (ApproveData approveData : contactApproveDatas) {
			// 初始化信息
			workflowData = new ApprovingWorkflowData();
			contact = new Contact();
			contact.setId(approveData.getClassId());
			// 设置会员信息
			workflowData.setContactId(contact);
			// 设置工作流号
			workflowData.setWorkflowId(String.valueOf(workFlowId));
			// 设置状态
			workflowData.setStatus(true);

			// 如果修改了联系人编码
			if (approveData.getFieldName().equals("number")
					&& !StringUtils.isEmpty(approveData.getNewValue())) {
				workflowData.setContactNum(approveData.getNewValue());
				contactWorkflowDatas.add(workflowData);
			}

			// 如果修改了手机
			if (approveData.getFieldName().equals("mobilePhone")
					&& !StringUtils.isEmpty(approveData.getNewValue())) {
				workflowData.setContactMobile(approveData.getNewValue());
				contactWorkflowDatas.add(workflowData);
				// 如果修改的是电话
			} else if (approveData.getFieldName().equals("telPhone")
					&& !StringUtils.isEmpty(approveData.getNewValue())) {
				// 设置电话号码
				workflowData.setContactTel(approveData.getNewValue());
				// 如果同时修改了联系人姓名，则需要找到联系人姓名
				for (ApproveData appData : updateDataAllList) {
					if (appData.getFieldName().equals("name")
							&& approveData.getClassId().equals(
									appData.getClassId())) {
						workflowData.setContactName(appData.getNewValue());
					}
				}
				// 如果没有修改联系人姓名，则需要从数据查询出改联系人姓名
				if (workflowData.getContactName() == null
						|| workflowData.getContactName().equals("")) {
					if (null == databaseContacts || 0>=databaseContacts.size()) {
						continue;
					}
					for (int i = 0; i < databaseContacts.size(); i++) {
						if (databaseContacts.get(i).getId().equals(approveData.getClassId())) {
							workflowData.setContactName(databaseContacts.get(i).getName());
						}
					}
				}
				// 如果集合中没改条数据才增加，进去（联系人姓名和固话，产生重复）
				if (!isApproveDataExist(contactWorkflowDatas, workflowData)) {
					contactWorkflowDatas.add(workflowData);
				}
				// 如果修改了联系人姓名
			} else if (approveData.getFieldName().equals("name")
					&& !StringUtils.isEmpty(approveData.getNewValue())) {
				// 设置联系人修改后的姓名
				workflowData.setContactName(approveData.getNewValue());
				for (ApproveData appData : updateDataAllList) {
					if (appData.getFieldName().equals("telPhone")
							&& approveData.getClassId().equals(
									appData.getClassId())) {
						workflowData.setContactTel(appData.getNewValue());
					}
				}
				// 如果联系人姓名未修改
				if (workflowData.getContactTel() == null
						|| workflowData.getContactTel().equals("")) {
					// 如果没有
					if (null == databaseContacts || 0>=databaseContacts.size()) {
						continue;
					}
					for (int i = 0; i < databaseContacts.size(); i++) {
						if (databaseContacts.get(i).getId().equals(approveData.getClassId())) {
							workflowData.setContactName(databaseContacts.get(i).getTelPhone());
						}
					}
				}
				// 如果集合中没改条数据才增加进去（联系人姓名和固话，产生重复）
				if (!isApproveDataExist(contactWorkflowDatas, workflowData)) {
					contactWorkflowDatas.add(workflowData);
				}
			} else if (approveData.getFieldName().equals("idCard")
					&& !StringUtils.isEmpty(approveData.getNewValue())) {
				// 会员类型
				String memberType = member.getCustType();
				// 主联系人id
				String mainContactId = getMainContractId(member);
				// 如果该证件号的不是主联系人，就不进行锁定
				if (StringUtils.isEmpty(memberId)
						|| !approveData.getClassId().equals(mainContactId)) {
					continue;
				}
				// 若果会员类型为空
				if (StringUtils.isEmpty(memberType)) {
					throw new RuntimeException("member type is null !");
				}
				// 不是个人客户也不需要锁定
				if (!Constant.Person_Member.equals(memberType)) {
					continue;
				}
				// 联系人证件类型
				String cardTypeCon = null;
				// 找出会员客户类型

				cardTypeCon = getContactCardType(member, approveData);
				//TODO 潜散客证件类型允许为空
				// 证件类型为空，主联系人id为空，会员类型为空都是非正常数据
				if (StringUtils.isEmpty(cardTypeCon)
						|| StringUtils.isEmpty(mainContactId)) {
					CustomerException ce = new CustomerException(
							CustomerExceptionType.CardInfomationIncomplete);
					throw new GeneralException(ce.getErrorCode(),
							ce.getMessage(), ce, new Object[] {}) {
						private static final long serialVersionUID = 1L;
					};
				}

				// 如果会员的主联系人与修改的联系人id相同，会员类型为个人客户
				if (approveData.getClassId().equals(mainContactId)) {
					// 设置联系人证件信息
					workflowData.setIdCardNo(approveData.getNewValue());
					// 设置证件类型
					workflowData.setCredentialsType(cardTypeCon);
					contactWorkflowDatas.add(workflowData);
				}
			}
		}
		return contactWorkflowDatas;
	}
	/**
	 * @description 校验一个对象是否在一个set中.
	 * @author 潘光均
	 * @version 0.1 2012-7-18
	 * @param
	 * @date 2012-7-18
	 * @return void
	 * @update 2012-7-18 下午2:38:47
	 */
	private static boolean isApproveDataExist(
			Set<ApprovingWorkflowData> contactWorkflowDatas,
			ApprovingWorkflowData workflowData) {
		for (ApprovingWorkflowData approvingData : contactWorkflowDatas) {
			if (approvingData.equals(workflowData)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @description 获得主联系人id
	 * @author 潘光均
	 * @version 0.1 2012-7-26
	 * @param
	 * @date 2012-7-26
	 * @return String
	 * @update 2012-7-26 下午4:49:32
	 */
	private static String getMainContractId(Member member) {
		String mainContactId = null;
		//会员不为空
		if (!ValidateUtil.objectIsEmpty(member)) {
			//得到联系人列表
			List<Contact> contacts = member.getContactList();
			//数据存在
			if (null != member && !CollectionUtils.isEmpty(contacts)) {
				for (Contact cont : contacts) {
					if (null != cont.getIsMainLinkMan()
							&& cont.getIsMainLinkMan()) {
						mainContactId = cont.getId();
					}
				}
			}
		}
		return mainContactId;
	}
	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-7-26
	 * @param
	 * @date 2012-7-26
	 * @return String
	 * @update 2012-7-26 下午9:11:56
	 */
	private static String getContactCardType(Member member, ApproveData approveData) {
		String cardTypeCon = null;
		if (!CollectionUtils.isEmpty(member.getContactList())
				) {
			for (Contact cont : member.getContactList()) {
				if (cont.getId().equals(approveData.getClassId())) {
					cardTypeCon = cont.getCardTypeCon();
				}
			}
		}
		return cardTypeCon;
	}

}
