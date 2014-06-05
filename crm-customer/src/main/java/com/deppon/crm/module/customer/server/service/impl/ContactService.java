package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.server.dao.IContactDao;
import com.deppon.crm.module.customer.server.service.IContactService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Order;
import com.deppon.crm.module.customer.shared.domain.RandomNumber;

public class ContactService implements IContactService {

	private IContactDao contactDao;

	@Override
	public List<Contact> getContact(String phone, String tel, String name) {
		return null;
	}

	@Override
	public int getContactCountByIdCard(String idCard) {
		if (ValidateUtil.objectIsEmpty(idCard)) {
			throw new RuntimeException("身份证号码不能为空,参数调用不合法");
		}
		return contactDao.getContactCountByIdCard(idCard);
	}

	public IContactDao getContactDao() {
		return contactDao;
	}

	public void setContactDao(IContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@Override
	public int getContactCountByTelAndName(String tel, String name) {
		return contactDao.getContactCountByTelAndName(tel, name);
	}

	@Override
	public int getContactCountByPhone(String phone) {
		return contactDao.getContactCountByPhone(phone);
	}

	@Override
	public List<Contact> getContactsByMemberId(String memberId) {
		Contact contact = new Contact();
		contact.setCustId(memberId);
		return contactDao.searchContacts(contact);
	}

	@Override
	public List<Contact> getContactsByMemberNumber(String memberNumber) {
		ContactCondition condition = new ContactCondition();
		condition.setMemberNum(memberNumber);
		return contactDao.searchContactByCondition(condition);
	}

	/**
	 * @根据联系人编码获得联系人信息
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@Override
	public ContactView getContactByNumber(String number) {
		return contactDao.getContactByNumber(number);
	}

	/**
	 * 
	 * @保存随机编码
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 * @param phone
	 *            手机号
	 * @param number
	 *            随机码
	 */
	@Override
	public void saveRandomPhone(String phone, String number) {
		contactDao.saveRandomPhone(phone, number);
	}

	/**
	 * @description
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@Override
	public List<RandomNumber> getRandomNumberByPhone(String phone) {
		return contactDao.getRandomNumberByPhone(phone);
	}

	/**
	 * 
	 * @保存联系人-绑定联系人
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@Override
	public boolean saveContact(Contact contact) {
		return contactDao.saveContact(contact);
	}

	/**
	 * 
	 * @绑定联系人
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@Override
	public boolean boundContact(Contact contact,RegisterInfo info) {
		RegisterInfo queryInfo = new RegisterInfo();
		queryInfo.setUserName(info.getUserName());
		queryInfo.setCustsource(info.getCustsource());
		
		List<RegisterInfo> registsers = contactDao.getRegisterInfo(queryInfo);
		boolean flag = false;
		if (null != registsers && 0 < registsers.size()) {
			contactDao.updateContactOnlineNum(info);
			flag =  contactDao.boundContact(contact);
		} else {
			info.setCreateUser(ContextUtil.getCreateOrModifyUserId());
			contactDao.insertContactOnlineNum(info);
		}
		return flag;
	}

	/**
	 * 
	 * @根据渠道订单号查询订单信息
	 * @author 赵斌
	 * @2012-4-25
	 * @return
	 */
	@Override
	public Order queryOrderByChannelNumber(String channelNumber) {
		return contactDao.queryOrderByChannelNumber(channelNumber);
	}

	/**
	 * 
	 * @根据德邦订单号查询订单信息
	 * @author 赵斌
	 * @2012-4-25
	 * @return
	 */
	@Override
	public Order getOrderByNumber(String number) {
		return contactDao.getOrderByNumber(number);
	}

	@Override
	public List<Contact> searchContacts(Contact contact) {
		return contactDao.searchContacts(contact);
	}

	@Override
	public void updateContact(Contact updateContact) {
		contactDao.updateContact(updateContact);
	}

	@Override
	public List<Contact> searchContactByCondition(ContactCondition condition) {
		return contactDao.searchContactByCondition(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.service.IContactService#
	 * deleteRandomCodeByMobile(java.lang.String)
	 */
	@Override
	public void deleteRandomCodeByMobile(String conPhone) {
		contactDao.deleteRandomCodeByMobile(conPhone);
	}
	
	/**
	 * @description 解绑联系人
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param registerInfo:注册信息， 主要用户用户客户渠道和渠道用户名
	 *@date 2012-6-29
	 * @return void
	 * @update 2013-6-19 上午8:49:16
	 */
	@Override
	public void unboundContactOnlineNum(RegisterInfo registerInfo) {
		//TODO changeContactOnlineNum(registerInfo)  这个方法没有必要，因为去掉了阿里、淘宝等字段，没有什么可更新的内容了
		contactDao.changeContactOnlineNum(registerInfo);
		contactDao.unboundContactOnlineNum(registerInfo);
	}

	/**
	 * @description 根据联系人id查询官网用户名
	 * @author 潘光均
	 * @version 0.1 2013-6-19
	 * @param userName：官网用户名
	 *@date 2012-6-29
	 * @return 联系人id
	 * @update 2013-6-19 上午8:49:16
	 */
	@Override
	public String queryLinkmanIdByOnlineNum(String userName) {
		return contactDao.getOnlineLinkmanId(userName);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContactService#searchRegisterInfo(com.deppon.crm.module.client.customer.domain.RegisterInfo)
	 */
	@Override
	public List<RegisterInfo> searchRegisterInfo(RegisterInfo registerInfo) {
		return contactDao.getRegisterInfo(registerInfo);
	}

	/**(非 Javadoc)
	* <p>Title: searchContactList</p>
	* <p>Description: 根据联系方式查询联系人列表（有效和审批中）
	* @author chenaichun 
	* @param contact
	* @return
	* @see com.deppon.crm.module.customer.server.service.IContactService#searchContactList(com.deppon.crm.module.customer.shared.domain.Contact)
	*/
	@Override
	public List<Contact> searchContactList(Contact contact) {
		
		return contactDao.searchContactList(contact);
	}

}
