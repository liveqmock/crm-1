package com.deppon.crm.module.customer.server.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;




import org.apache.commons.collections.CollectionUtils;

import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Certification;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * 会员业务规则<br />
 * </p>
 * 
 * @title CustomerValidator.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author bxj
 * @version 0.1 2012-3-12
 */
public class CustomerValidator {

	// ******************************************会员验证信息**********************************************************************

	// ****************************************会员表单验证*****************************************
	public static boolean validateMemberForm(Member member) {
		// 客户基本信息必填字段验证
		if (!validateMemberInfoForm(member)) {
			return false;
		}

		// 接送货地址验证
		List<ShuttleAddress> shuttleAddresses = member.getShuttleAddressList();
		if (!validateMemberShuttleAddress(shuttleAddresses)) {
			return false;
		}

		// 联系人验证
		List<Contact> contacts = member.getContactList();
		if (!validateMemberContactForm(contacts)) {
			return false;
		}

		// 账号验证
		List<Account> accounts = member.getAccountList();
		if (!validateMemberAccountForm(accounts)) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * <p>
	 * 接送货地址验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param shuttleAddresses
	 * @return boolean
	 */
	protected static boolean validateMemberShuttleAddress(
			List<ShuttleAddress> shuttleAddresses) {
		// 接送货地址表单验证
		return validateShuttleAddressForm(shuttleAddresses)
		// 接送货地址不能重复
				|| isShuttleAddressRepeat(shuttleAddresses);
	}

	/**
	 * 
	 * <p>
	 * 接送货地址表单验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param shuttleAddresses
	 * @return boolean
	 */
	protected static boolean validateShuttleAddressForm(
			List<ShuttleAddress> shuttleAddresses) {
		// 接送货地址可以没有，返回true
		if (ValidateUtil.objectIsEmpty(shuttleAddresses)) {
			return true;
		}

		for (ShuttleAddress shuttleAddress : shuttleAddresses) {
			if (!validateShuttleAddressForm(shuttleAddress)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 接送货地址是否重复<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param shuttleAddresses
	 * @return boolean
	 */
	protected static boolean isShuttleAddressRepeat(
			List<ShuttleAddress> shuttleAddresses) {
		for (int i = 0; i < shuttleAddresses.size(); i++) {
			ShuttleAddress shuttleAddress = shuttleAddresses.get(i);
			for (int j = i + 1; j < shuttleAddresses.size(); j++) {
				ShuttleAddress otherShuttleAddress = shuttleAddresses.get(j);
				if (isShuttleAddressSame(shuttleAddress, otherShuttleAddress)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 接送货地址是否一样<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param shuttleAddress
	 * @param otherShuttleAddress
	 * @return boolean
	 */
	protected static boolean isShuttleAddressSame(ShuttleAddress shuttleAddress,
			ShuttleAddress otherShuttleAddress) {
		String address = shuttleAddress.getAddress();
		String addressType = shuttleAddress.getAddressType();

		String otherAaddress = otherShuttleAddress.getAddress();
		String otherAddressType = otherShuttleAddress.getAddressType();

		return ValidateUtil.isSame(address, otherAaddress)
				&& ValidateUtil.isSame(addressType, otherAddressType);
	}

	protected static boolean validateMemberAccountForm(List<Account> accounts) {
		// 账号可以为空
		if (ValidateUtil.objectIsEmpty(accounts)) {
			return true;
		}

		// 验证每个账号必填项是否足够
		for (Account account : accounts) {
			if (!validateAccount(account)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 验证会员的联系人<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contacts
	 * @return boolean
	 */
	protected static boolean validateMemberContactForm(List<Contact> contacts) {
		// 联系人至少存在一个
		if (contacts.size() == 0) {
			throw new MemberException(MemberExceptionType.ContactMustExistOne);
		}

		// 每个联系人必填项是否足够
		for (Contact contact : contacts) {
			// 验证联系人的偏好地址
			List<PreferenceAddress> preAddressList = contact
					.getPreferenceAddressList();
			if (!validatePreferenceAddressForm(preAddressList)) {
				return false;
			}
			if (!validateContactForm(contact)) {
				return false;
			}
		}
		// 是否有且仅有一个主联系人
		if (!isMainContactOnlyOne(contacts)) {
			throw new MemberException(
					MemberExceptionType.MainContactCanExistOnlyOne);
		}

		// 验证联系人数据是否重复，是否没有
		if (isMemberContactHasRepeat(contacts)) {
			throw new MemberException(MemberExceptionType.ContactsHasRepeat);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 是否只有一个主联系人<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contacts
	 * @return boolean
	 */
	protected static boolean isMainContactOnlyOne(List<Contact> contacts) {
		int mainContactCount = 0;
		for (Contact contact : contacts) {
			if (contact.getIsMainLinkMan() == Constant.MainLinkMan) {
				mainContactCount++;
			}
		}
		if (mainContactCount != 1) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 偏好地址表单验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param preAddressList
	 * @return boolean
	 */
	public static boolean validatePreferenceAddressForm(
			List<PreferenceAddress> preAddressList) {
		// 偏好地址可以为空
		if (ValidateUtil.objectIsEmpty(preAddressList)) {
			return true;
		}

		// 表单验证
		for (PreferenceAddress preferenceAddress : preAddressList) {
			if (!validatePreferenceAddressForm(preferenceAddress)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 会员联系人是否重复<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contacts
	 * @return boolean
	 */
	protected static boolean isMemberContactHasRepeat(List<Contact> contacts) {
		for (int i = 0; i < contacts.size(); i++) {
			Contact contact = contacts.get(i);
			for (int j = i + 1; j < contacts.size(); j++) {
				Contact otherContact = contacts.get(j);
				if (isContactSame(contact, otherContact)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 联系人是否相同<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contact
	 * @param otherContact
	 * @return boolean
	 */
	protected static boolean isContactSame(Contact contact, Contact otherContact) {
		Assert.notNull(contact, "contact must not null");
		Assert.notNull(otherContact, "otherContact must not null");

		String phone = contact.getMobilePhone();
		String otherPhone = otherContact.getMobilePhone();

		String idCard = contact.getIdCard();
		String otherIdCard = otherContact.getIdCard();
		
		String cardTypeCon = contact.getCardTypeCon();
		String otherCardTypeCon = otherContact.getCardTypeCon();
		
		String tel = contact.getTelPhone();
		String name = contact.getName();
		String otherTel = otherContact.getTelPhone();
		String otherName = otherContact.getName();

		// !ValidateUtil.objectIsEmpty(phone)
		return (!ValidateUtil.objectIsEmpty(phone)
				&& !ValidateUtil.objectIsEmpty(otherPhone) && ValidateUtil
					.isSame(phone, otherPhone))
				|| (!ValidateUtil.objectIsEmpty(name)
						&& !ValidateUtil.objectIsEmpty(otherName)
						&& ValidateUtil.isSame(name, otherName)
						&& !ValidateUtil.objectIsEmpty(tel)
						&& !ValidateUtil.objectIsEmpty(otherTel) && ValidateUtil
							.isSame(tel, otherTel))
				|| (!ValidateUtil.objectIsEmpty(idCard)
						&& !ValidateUtil.objectIsEmpty(otherIdCard)
						&& !ValidateUtil.objectIsEmpty(cardTypeCon)
						&& !ValidateUtil.objectIsEmpty(otherCardTypeCon)
						&& ValidateUtil
							.isSame(idCard, otherIdCard)
						&& ValidateUtil.isSame(cardTypeCon, otherCardTypeCon)	
						);
	}

	/**
	 * 
	 * <p>
	 * 联系人表单验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param contact
	 * @return boolean
	 */
	public static boolean validateContactForm(Contact contact) {
		Assert.notNull(contact, " contact must  not null");

		MemberExceptionType reson = null;
		// 联系人编码必填
		if (ValidateUtil.objectIsEmpty(contact.getNumber())) {
			reson = MemberExceptionType.NumberNull;
		} else if (ValidateUtil.objectIsEmpty(contact.getName())) {
			reson = MemberExceptionType.NameNull;
		} else if (ValidateUtil.objectIsEmpty(contact.getLinkmanType())) {
			reson = MemberExceptionType.LinkmanTypeNull;
		} else if (ValidateUtil.objectIsEmpty(contact.getIsMainLinkMan())) {
			reson = MemberExceptionType.IsMainLinkManNull;
		} else if (ValidateUtil.objectIsEmpty(contact.getMobilePhone())
				&& ValidateUtil.objectIsEmpty(contact.getTelPhone())) {
			// 手机 电话 同时为空
			reson = MemberExceptionType.ContactWayNull;
		} else if (!ValidateUtil.objectIsEmpty(contact.getMobilePhone())
				&& !contact.getMobilePhone().matches("^1\\d{10}||\\d{8}$")) {
			reson = MemberExceptionType.ContactMobileError;
		} else if (!ValidateUtil.objectIsEmpty(contact.getTelPhone())
				&& !contact.getTelPhone().matches("^\\d{3}[\\d\\-/]{4,37}$")) {
			reson = MemberExceptionType.ContactTelePhoneError;
		}

		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * 账号验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param account
	 * @return boolean
	 */
	public static boolean validateAccount(Account account) {
		Assert.notNull(account, " account must  not null");

		MemberExceptionType reson = null;

		if (ValidateUtil.objectIsEmpty(account.getBank())) {
			reson = MemberExceptionType.BankNull;
		} else if (ValidateUtil.objectIsEmpty(account.getSubBankname())) {
			reson = MemberExceptionType.SubBanknameNull;
		} else if (ValidateUtil.objectIsEmpty(account.getIsdefaultaccount())) {
			reson = MemberExceptionType.IsdefaultaccountNull;
		} else if (ValidateUtil.objectIsEmpty(account.getBankAccount())) {
			reson = MemberExceptionType.BankAccountNull;
		} else if (ValidateUtil.objectIsEmpty(account.getCountName())) {
			reson = MemberExceptionType.CountNameNull;
		} else if (ValidateUtil.objectIsEmpty(account.getRelation())) {
			reson = MemberExceptionType.RelationNull;
		} else if (ValidateUtil.objectIsEmpty(account.getBankProvinceId())) {
			reson = MemberExceptionType.BankProvinceIdNull;
		} else if (ValidateUtil.objectIsEmpty(account.getBankCityId())) {
			reson = MemberExceptionType.BankCityIdNull;
		} else if (ValidateUtil.objectIsEmpty(account.getAccountNature())) {
			reson = MemberExceptionType.AccountNatureNull;
		} else if (ValidateUtil.objectIsEmpty(account.getAccountUse())) {
			reson = MemberExceptionType.AccountUseNull;
		} else if (ValidateUtil.objectIsEmpty(account.getFinanceLinkman())) {
			reson = MemberExceptionType.FinanceLinkmanNull;
		} else if (ValidateUtil.objectIsEmpty(account.getLinkManMobile())
				&& ValidateUtil.objectIsEmpty(account.getLinkManPhone())) {
			reson = MemberExceptionType.ContactWayNull;
		}

		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}

		return true;
	}

	/**
	 * 
	 * <p>
	 * 偏好地址表单验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param preferenceAddress
	 * @return boolean
	 */
	protected static boolean validatePreferenceAddressForm(
			PreferenceAddress preferenceAddress) {
		Assert.notNull(preferenceAddress, " preferenceAddress must  not null");

		MemberExceptionType reson = null;

		if (ValidateUtil.objectIsEmpty(preferenceAddress.getAddressType())) {
			reson = MemberExceptionType.AddressTypeNull;
		} else if (ValidateUtil.objectIsEmpty(preferenceAddress.getAddress())) {
			reson = MemberExceptionType.AddressNull;
		} else if (ValidateUtil.objectIsEmpty(preferenceAddress
				.getIsMainAddress())) {
			reson = MemberExceptionType.IsMainAddressNull;
		}
		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}

		return true;
	}

	/**
	 * 
	 * <p>
	 * 会员表单验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param member
	 * @return boolean
	 */
	protected static boolean validateMemberInfoForm(Member member) {
		Assert.notNull(member, "member must  not null");
		String custGroup = member.getCustGroup();
		//客户是否为潜客
		boolean isPCCustomer = false;
		//是否为固定客户
		boolean isRCCustomer = false;
		MemberExceptionType reson = null;
		if(ValidateUtil.objectIsEmpty(custGroup)){
			throw new MemberException(MemberExceptionType.CustGroupNull);
		}
		
		//客户是否为潜客
		isPCCustomer = custGroup.equals(Constant.CUST_GROUP_PC_CUSTOMER);
		isRCCustomer = custGroup.equals(Constant.CUST_GROUP_RC_CUSTOMER);
		if (isRCCustomer && ValidateUtil.objectIsEmpty(member.getDegree())) {
			reson = MemberExceptionType.DegreeNull;
		} else if (ValidateUtil.objectIsEmpty(member.getCustName())) {
			reson = MemberExceptionType.CustNameNull;
		} else if (ValidateUtil.objectIsEmpty(member.getCustType())) {
			reson = MemberExceptionType.CustTypeNull;
		} else if (ValidateUtil.objectIsEmpty(member.getCustNature())) {
			reson = MemberExceptionType.CustNatureNull;
		} else if (ValidateUtil.objectIsEmpty(member.getProvinceId())) {
			reson = MemberExceptionType.ProvinceNull;
		} else if (ValidateUtil.objectIsEmpty(member.getCityId())) {
			reson = MemberExceptionType.CityNull;
		} else if (ValidateUtil.objectIsEmpty(member.getRegistAddress())) {
			reson = MemberExceptionType.RegistAddressNull;
		}else if (ValidateUtil.objectIsEmpty(member.getIsSpecial())) {
			reson = MemberExceptionType.IsSpecialNull;
		} else if (ValidateUtil.objectIsEmpty(member.getIsImportCustor())) {
			reson = MemberExceptionType.IsImportCustorNull;
		}else if(ValidateUtil.objectIsEmpty(member.getIsKeyCustomer())){
			reson = MemberExceptionType.IsKeyCustorNull;
		}else if (ValidateUtil.objectIsEmpty(member.getIsRedeempoints())) {
			reson = MemberExceptionType.IsRedeempointsNull;
		} else if (ValidateUtil.objectIsEmpty(member.getIsParentCompany())) {
			reson = MemberExceptionType.IsParentCompanyNull;
		} else if (ValidateUtil.objectIsEmpty(member.getIsFocusPay())) {
			reson = MemberExceptionType.IsFocusPayNull;
		} else if (!member.getIsParentCompany()
				&& ValidateUtil.objectIsEmpty(member.getParentCompanyId())) {
			reson = MemberExceptionType.ParentCompanyNull;
		} else if (member.getIsFocusPay()
				&& ValidateUtil.objectIsEmpty(member.getFocusDeptId())) {
			reson = MemberExceptionType.FocusDeptNull;
		} 
//		else if (!isPCCustomer && ValidateUtil.objectIsEmpty(member.getProcRedit())) {
//			reson = MemberExceptionType.ProcReditNull;
//		}//潜客来源渠道不能为空
		else if(isPCCustomer && ValidateUtil.objectIsEmpty(member.getChannelSource())){
			reson = MemberExceptionType.PotentialChannelSourceNull;
		}//客户业务类别（零担、快递）不能为空
		if(ValidateUtil.objectIsEmpty(member.getCustCategory())){
			reson = MemberExceptionType.BussTypeNull;
		}else if(ValidateUtil.objectIsEmpty(member.getDeptId())){
			reson = MemberExceptionType.DeptNull;
		}
		
		/**
		 * 税务标记号校验在manager层
		 * checkEnterpriseTaxregNumber
		 */
		
		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}
		return true;
	}
	
	public static boolean validatePCCustomer(Member member) {
		if(member.getCustName().length()>80){
			throw new MemberException(MemberExceptionType.CUSTNAMETOLONG);
		}
		if(CollectionUtils.isNotEmpty(member.getContactList())&&
				member.getContactList().get(0).getName().length()>80){
			throw new MemberException(MemberExceptionType.CONTACTNAMETOLONG);
		}
		if(StringUtil.isNotBlank(member.getRegistAddress())&&
				member.getRegistAddress().length()>100){
			throw new MemberException(MemberExceptionType.ADDRESSTOLONG);

		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 接送货地址表单验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-23
	 * @param shuttleAddress
	 * @return boolean
	 */
	protected static boolean validateShuttleAddressForm(ShuttleAddress shuttleAddress) {
		Assert.notNull(shuttleAddress, "shuttleAddress must  not null");

		MemberExceptionType reson = null;

		if (ValidateUtil.objectIsEmpty(shuttleAddress.getAddress())) {
			reson = MemberExceptionType.AddressNull;
		} else if (ValidateUtil.objectIsEmpty(shuttleAddress.getAddressType())) {
			reson = MemberExceptionType.AddressTypeNull;
		} else if (ValidateUtil.objectIsEmpty(shuttleAddress.getProvince())) {
			reson = MemberExceptionType.ProvinceNull;
		} else if (ValidateUtil.objectIsEmpty(shuttleAddress.getCity())) {
			reson = MemberExceptionType.CityNull;
		} else if (ValidateUtil.objectIsEmpty(shuttleAddress.getArea())) {
			reson = MemberExceptionType.AreaNull;
		}

		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}

		return true;
	}

	public static boolean validateUpGradeCustomerCondition(
			UpGradeCustomerCondition condition) {
		Assert.notNull(condition, "upGradeCustomerCondition must  not null");

		MemberExceptionType reson = null;

		if (ValidateUtil.objectIsEmpty(condition.getStatisticsTime())) {
			reson = MemberExceptionType.StatisticsTimeNull;
		} else if (!condition.getStatisticsTime().matches("^[\\d]{4}-[01]\\d$")) {
			reson = MemberExceptionType.StatisticsTimeFormError;
		} else if (!isRightfulTime(condition.getStatisticsTime())) {
			reson = MemberExceptionType.TimeNotRight;
		}
		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 检查是否是合法时间，系统时间到前六个月之间<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-3-31
	 * @param statisticsTime
	 * @return boolean
	 */
	private  static boolean isRightfulTime(String statisticsTime) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM");
		Calendar targetCalendar = Calendar.getInstance();
		try {
			targetCalendar.setTime(formate.parse(statisticsTime));
		} catch (ParseException e) {
			throw new MemberException(
					MemberExceptionType.StatisticsTimeFormError);
		}
		Calendar nowCalendar = Calendar.getInstance();
		Calendar beforeCalendar = Calendar.getInstance();
		beforeCalendar.set(Calendar.MONTH,
				beforeCalendar.get(Calendar.MONTH) - 6);
		beforeCalendar.set(Calendar.DAY_OF_MONTH, 1);
		if (targetCalendar.after(nowCalendar)
				|| targetCalendar.before(beforeCalendar)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 会员升级列表查询条件验证<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-4-6
	 * @param condition
	 * @return boolean
	 */
	public static boolean validateMemberUpgradeListCondition(
			UpGradeCustomerCondition condition) {
		Assert.notNull(condition, "upGradeCustomerCondition must  not null");

		MemberExceptionType reson = null;

		if (!ValidateUtil.objectIsEmpty(condition.getStatisticsTime())
				&& !condition.getStatisticsTime().matches("^[\\d]{4}-[01]\\d$")) {
			reson = MemberExceptionType.StatisticsTimeFormError;
		}
		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}
		return true;
	}
	
	/**
	 * 
	* @Title: checkTaxregNumberCanUseForSpecialMember
	* @Description: 校验税务号
	* @author chenaichun 
	* @param @param location
	* @param @param taxregNumber
	* @param @return    设定文件
	* @date 2014-3-24 下午5:21:40
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 下午5:21:40
	 */
	 public static boolean checkTaxregNumberCanUseForSpecialMember(String location,String taxregNumber) {
		if (ValidateUtil.objectIsEmpty(location) || ValidateUtil.objectIsEmpty(taxregNumber)) {
			throw new MemberException(MemberExceptionType.DeptNull);
		}
		//香港登记号
		if (Constant.ISHONGKONG_STRING.equals(location)) {
			if (!taxregNumber.matches("^[\\d]{11}$")) {
				throw new MemberException(MemberExceptionType.HongKong_TaxregNumber_Error);
			}
		} else{
			//验证企业税务登记号是否可用
			if(!MemberEffectValidate.validate(taxregNumber,Certification.TAX_CARD)){
				throw new MemberException(MemberExceptionType.EnterpriseMember_TaxregNumberError);
			}
		}
		return true;
	}
	/**
	 * 
	* @Title: validateMemberDemotionListCondition
	* @Description: 校验客户降级查询条件
	* @author chenaichun 
	* @param @param condition
	* @param @return    设定文件
	* @date 2014-3-24 下午3:27:46
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-24 下午3:27:46
	 */
	public  static boolean validateMemberDemotionListCondition(
			UpGradeCustomerCondition condition) {
		Assert.notNull(condition, "upGradeCustomerCondition must  not null");

		MemberExceptionType reson = null;

		if (ValidateUtil.objectIsEmpty(condition.getStatisticsTime())) {
			reson = MemberExceptionType.StatisticsTimeNull;
		} else if (!condition.getStatisticsTime().matches("^[\\d]{4}$")) {
			reson = MemberExceptionType.StatisticsTimeFormError;
		}
		if (!ValidateUtil.objectIsEmpty(reson)) {
			throw new MemberException(reson);
		}
		return true;
	}
	
	/**
	 * <p>
	 * Description:香港开点，新增的手机验证方法<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-16
	 * @param mobile
	 * @return
	 * boolean
	 */
	public static boolean checkMoblieFormat(String mobile) {
		return (mobile.matches("^1\\d{10}$")||mobile.matches("^[\\d]{8}$"));
	}
}
