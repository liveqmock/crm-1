/**
 * @description
 * @author 赵斌
 * @2012-4-24
 * @return
 */
package com.deppon.crm.module.customer.server.manager.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.service.IContactService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * @author 赵斌
 * 
 */
@Transactional
public class ContactManager implements IContactManager {
	private final static String msmContent = 
			"尊敬的德邦客户，您好！" +
			"您的网上用户名【{0}】与联系人编码【{1}】，" +
			"在{2}进行了绑定，请核实！" +
			"若有疑问请致电营业部【{3}】，电话号码【{4}】。";
	private IContactService contactService;
	private ICustomerOperate customerOperate;
	public void setCustomerOperate(ICustomerOperate customerOperate) {
		this.customerOperate = customerOperate;
	}

	public IContactService getContactService() {
		return contactService;
	}

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}
	private ISmsInfoSender iSmssender;
	
	/**
	 * <p>
	 * Description:iSmssender<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-22
	 */
	public ISmsInfoSender getiSmssender() {
		return iSmssender;
	}

	/**
	 * <p>
	 * Description:iSmssender<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-22
	 */
	public void setiSmssender(ISmsInfoSender iSmssender) {
		this.iSmssender = iSmssender;
	}

	/**
	 * 
	 * @根据联系人编码获得联系人信息
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@Override
	public ContactView getContactByNumber(String number) {
		return contactService.getContactByNumber(number);
	}
	
	/**
	 * @description 解绑联系人
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param linkmanId:联系人id
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	@Override
	public void unboundContact(RegisterInfo registerInfo) {
		if (null != registerInfo
				&& !StringUtils.isEmpty(registerInfo.getUserName())
				&& !StringUtils.isEmpty(registerInfo.getCustsource())) {
			contactService.unboundContactOnlineNum(registerInfo);
		}
	}
	
	/**
	 * @description 根据联系人id查询官网用户名
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param userName:官网用户名
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	@Override
	public List<RegisterInfo> queryRegisterInfo(RegisterInfo registerInfo) {
		return contactService.searchRegisterInfo(registerInfo);
	}
	
	@Override
	public void boundContactForOnline(RegisterInfo registerInfo, String operateType) {
		if (Constant.BOUNDING_CONTACT.equals(operateType)) {
			Contact contact = new Contact();
			contact.setModifyUser(registerInfo.getModifyUser());
			contact.setId(registerInfo.getCusCode());
			contactService.boundContact(contact,registerInfo);
		} else if (Constant.UNBOUNDING_CONTACT.equals(operateType)) {
			contactService.unboundContactOnlineNum(registerInfo);
		}
	}

	/**
	 * 
	 * @绑定联系人
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 * @update 2012-10-11 苏玉军修改，联系人编码绑定原代码修改
	 */
	@Override
	@Transactional
	public boolean boundContactForWeb(String orderSource, String channelCustId,String contactNumber,String mobile) {

		try {
			ContactView contactView = contactService.getContactByNumber(contactNumber);
			Contact contact = contactView.getContact();
			if(mobile == null || "".equals(mobile) || contact.getMobilePhone() == null || "".equals(contact.getMobilePhone())){
				throw new ContractException(ContractExceptionType.MOBILEPHONENOTSAME);
			}
			if(!contact.getMobilePhone().trim().equals(mobile.trim())){
				throw new ContractException(ContractExceptionType.MOBILEPHONENOTSAME);
			}
			
			
			RegisterInfo queryInfo = new RegisterInfo();
			queryInfo.setCusCode(contact.getId());
			
			//获取该联系人的所有绑定信息
			List<RegisterInfo> infos = contactService.searchRegisterInfo(queryInfo);
			List<String> resList = new ArrayList<String>();
			//获得该联系人的所有绑定来源
			for(RegisterInfo reg:infos){
				resList.add(reg.getCustsource());
			}
			//如果绑定新来源包含在已绑定的列表中，不容许绑定
			if(resList.contains(orderSource)){
				if(orderSource.equals(Constant.ORDER_SOURCE_ONLINE)){
					throw new ContractException(ContractExceptionType.ONLINEHAVEBOUND);
				}else if(orderSource.equals(Constant.ORDER_SOURCE_ALIBABA)){
					throw new ContractException(ContractExceptionType.ALIBABAHAVEBOUND);
				}else if(orderSource.equals(Constant.ORDER_SOURCE_SHANGCHENG)){
					throw new ContractException(ContractExceptionType.SHANGCHENGHAVEBOUND);
				}else if(orderSource.equals(Constant.ORDER_SOURCE_TAOBAO)){
					throw new ContractException(ContractExceptionType.TAOBAOHAVEBOUND);
				}else if(orderSource.equals(Constant.ORDER_SOURCE_YOUSHANG)){
					throw new ContractException(ContractExceptionType.YOUSHANGHAVEBOUND);
				}
				
			}
			
			/**
			 * 订单界面绑定，更新联系人绑定表中，联系人id
			 */
			RegisterInfo info = new RegisterInfo();
			info.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			info.setUserName(channelCustId);
			info.setCusCode(contact.getId());
			info.setCustsource(orderSource);
			
			contactService.boundContact(contact,info);
			
			String contactId = contact.getId();
			if(orderSource.equals(Constant.ORDER_SOURCE_ONLINE)){
				customerOperate.bindToContact(contactId,channelCustId);
			}
			User user = (User) UserContext.getCurrentUser();
			sendSmsToContact(contact, user, channelCustId);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage()) {
				private static final long serialVersionUID = 4430254689512850620L;
			};
		}
		return true;
	}

	/**
	 * 
	 * 
	 * @descption 绑定成功发送短信
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param mobilePhone
	 * void
	 * @throws CrmBusinessException 
	 */
	@Override
	public void sendSmsToContact(Contact contact,User user,String registerId) throws CrmBusinessException{
		String formatString = "yyyy年MM月dd日HH:mm";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		Date date = new Date();
		String formatDateStr = dateFormat.format(date);
		
		String deptName = user.getEmpCode().getDeptId().getDeptName();
		String contactNum = contact.getNumber();
		String deptPhone = user.getEmpCode().getDeptId().getPhone();
		String content = MessageFormat.format(msmContent, new Object[]{registerId,contactNum,formatDateStr,deptName,deptPhone});
		
		String mobile = contact.getMobilePhone();
		if(StringUtils.isEmpty(mobile)){
			throw new ContractException(ContractExceptionType.MOBILECONNOTNULL);
		}
		SmsInformation smsInfo = new SmsInformation();
		String deptStandardCode = user.getEmpCode().getDeptId().getStandardCode();
		String empCode = user.getEmpCode().getEmpCode();
		smsInfo.setMobile(contact.getMobilePhone());
		smsInfo.setMsgContent(content);
		smsInfo.setSendDept(deptStandardCode);
		smsInfo.setSender(empCode);
		smsInfo.setMsgType(com.deppon.crm.module.client.common.util.Constant.SMS_CONTACT_CODE);
		//发送短信
		iSmssender.sendSms(smsInfo);
	}


	@Override
	public String getContactMemberId(String contactId) {
		Contact contact = new Contact();
		contact.setId(contactId);
		List<Contact> list = contactService.searchContacts(contact);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0).getCustId();
		}
	}

	@Override
	public List<Contact> getContactByMemberId(String memberId) {
		Contact contact = new Contact();
		contact.setCustId(memberId);
		return contactService.searchContacts(contact);
	}

	@Override
	public void varyContact(Contact fromContact, Member member) {
		Contact updateContact = new Contact();
		updateContact.setId(fromContact.getId());
		updateContact.setCustId(member.getId());
		contactService.updateContact(updateContact);
	}

	@Override
	public List<String> getContactIdListByMemberId(String memberId) {
		List<Contact> list = getContactByMemberId(memberId);
		List<String> ids = new ArrayList<String>();
		for (Contact contact : list) {
			ids.add(contact.getId());
		}
		return ids;
	}

	@Override
	public List<Contact> searchContactByCondition(ContactCondition condition) {
		return contactService.searchContactByCondition(condition);
	}

	@Override
	public String getContactIdByContactNumber(String number) {
		ContactView view = getContactByNumber(number);
		if (ValidateUtil.objectIsEmpty(view)
				|| ValidateUtil.objectIsEmpty(view.getContact())) {
			return null;
		}
		return view.getContact().getId();
	}

	@Override
	public boolean sendValidatorMsg(String contactNumber,
			String orderContactMobile) {
		return false;
	}
	/**
	 * 
	* @Title: searchContactList
	* @Description: 根据联系方式查询非2(即有效和审批中)的联系人列表
	* @author chenaichun 
	* @param @param phone
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014-3-4 下午3:29:47
	* @return boolean    返回类型
	* @throws
	* @update 2014-3-4 下午3:29:47
	 */
	public List<Contact> searchContactList(String phone, String tel, String name) {
		List<Contact> contactList = null;
		if (ValidateUtil.objectIsEmpty(phone)
				&& (ValidateUtil.objectIsEmpty(tel) || ValidateUtil
						.objectIsEmpty(name))) {
			throw new IllegalArgumentException("参数不合法");
		}
		//手机能查到就不会查电话和联系人姓名
		if (!ValidateUtil.objectIsEmpty(phone)) {
			contactList = searchContactByPhone(phone);
			if(!CollectionUtils.isEmpty(contactList)){
				return contactList;
			}
		}
		if (!ValidateUtil.objectIsEmpty(tel)
				&& !ValidateUtil.objectIsEmpty(name)) {
			contactList = searchContactByTelName(tel, name);
		}
		return contactList;
	}
	/**
	 * 
	* @Title: searchContactByPhone
	* @Description: 根据手机号查找有效和审批中的联系人
	* @author chenaichun 
	* @param @param phone
	* @param @return    设定文件
	* @date 2014-3-4 下午5:00:28
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014-3-4 下午5:00:28
	 */
	public List<Contact> searchContactByPhone(String phone) {
		Contact contact = new Contact();
		contact.setMobilePhone(phone);
		return contactService.searchContactList(contact);
		
	}
	/**
	 * 
	* @Title: searchContactByTelName
	* @Description: 根据固定电话和姓名查找有效和审批中的联系人
	* @author chenaichun 
	* @param @param tel
	* @param @param name
	* @param @return    设定文件
	* @date 2014-3-4 下午5:01:12
	* @return List<Contact>    返回类型
	* @throws
	* @update 2014-3-4 下午5:01:12
	 */
	public List<Contact> searchContactByTelName(String tel, String name) {
		Contact contact = new Contact();
		contact.setTelPhone(tel);
		contact.setName(name);
		return contactService.searchContactList(contact);
	}
}
