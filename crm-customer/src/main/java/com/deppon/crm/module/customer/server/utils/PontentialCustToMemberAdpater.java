/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PontenToMemberAdpater.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author pgj
 * @version 0.1 2014-3-12
 */
package com.deppon.crm.module.customer.server.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.server.manager.CustomerRule;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;

/**   
 * <p>
 * Description:潜客转化为固定客户适配器<br />
 * </p>
 * @title PontentialCustToMemberAdpater.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author pgj
 * @version 0.1 2014-3-12
 */

public class PontentialCustToMemberAdpater {
	/**
	 * 
	 * <p>
	 * Description:潜客到会员<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param potenCust
	 * @return
	 * Member
	 */
	public static Member toMember(PotentialCustomer potenCust){
		Member member  = new Member();
		ShuttleAddress shuttleAddress = new ShuttleAddress();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);   
		try {
			BeanUtils.copyProperties(member, potenCust);
			BeanUtils.copyProperties(shuttleAddress, potenCust);
		} catch (Exception e) {
			throw new MemberException(MemberExceptionType.COPYERROR);
		}
		member.setTradeId(potenCust.getTrade());
		member.setChannelSource(potenCust.getCustbase());
		member.setCustGroup(Constant.CUST_GROUP_PC_CUSTOMER);
		String createUser = ContextUtil.getCurrentUserEmpId();
		member.setCreateUser(createUser);
		member.setCustCategory(potenCust.getCustCategory());
		member.setCustType(potenCust.getCustomerType());//客户属性
		member.setCustNature(potenCust.getCustNature());//
		member.setIsFocusPay(false);
		member.setIsSpecial(false);
		member.setIsImportCustor(false);
		member.setIsParentCompany(true);
		member.setIsKeyCustomer(false);
		member.setIsRedeempoints(false);
		member.setLastChangTime(new Date());
		member.setProcRedit(0d);
		member.setId(potenCust.getId());
		// 根据会员序列id，生成会员编号
		String custNumber = CustomerRule.createCustNumberByMembrId(member.getId());
		member.setCustNumber(custNumber);
		MemberExtend extend = new MemberExtend();
		
		extend.setRecentCoop(potenCust.getRecentcoop());
		extend.setCoopIntention(potenCust.getCoopIntention());
		extend.setVolumePotential(potenCust.getVolumePotential());
		extend.setCustNeed(potenCust.getCustNeed());
		member.setRegistAddress(potenCust.getAddress());
		member.setMemberExtend(extend);
		
		Contact contact = new Contact();
		contact.setName(potenCust.getLinkManName());
		contact.setMobilePhone(potenCust.getLinkManMobile());
		contact.setTelPhone(potenCust.getLinkManPhone());
		contact.setIsMainLinkMan(true);
		contact.setSex(potenCust.getSex());
		contact.setLinkmanType("0,1,0,0,0");
		contact.setDuty(potenCust.getPost());
		String linkWay = StringUtils.isEmpty(potenCust.getLinkManMobile()) ? potenCust
				.getLinkManPhone() : potenCust.getLinkManMobile();
		contact.setNumber(StringUtils.lowerCase((new FirstCharGetUtil()).getFirstChar(potenCust.getLinkManName()))+linkWay);
		
		PreferenceAddress preferenceAddress = new PreferenceAddress();
		preferenceAddress.setAddress(potenCust.getAddress());
		preferenceAddress.setHasStopCost(false);
		preferenceAddress.setIsMainAddress(true);
		preferenceAddress.setIsSendToFloor(false);
		preferenceAddress.setAddressType(Constant.RECEIVE_GOODS);
		
		List<PreferenceAddress> preferenceAddressList = new ArrayList<PreferenceAddress>();
		preferenceAddressList.add(preferenceAddress);
		contact.setPreferenceAddressList(preferenceAddressList);
		
		List<ShuttleAddress> shuttleAddressList = new ArrayList<ShuttleAddress>();
		shuttleAddress.setAddressType(Constant.RECEIVE_GOODS);
		shuttleAddressList.add(shuttleAddress);
		shuttleAddress.setProvince(potenCust.getProvinceId());
		shuttleAddress.setProvinceName(potenCust.getProvince());
		shuttleAddress.setCity(potenCust.getCityId());
		shuttleAddress.setCityName(potenCust.getCity());
		shuttleAddress.setArea(potenCust.getAreaId());
		shuttleAddress.setAreaName(potenCust.getArea());
		List<Contact> contactList = new ArrayList<Contact>();
		contactList.add(contact);
		
		member.setContactList(contactList);
		member.setShuttleAddressList(shuttleAddressList);
		// 设置会员状态为正常
		CustomerRule.setMemberStatus(member, Constant.Status_Normal);
		return member;
	}
}
