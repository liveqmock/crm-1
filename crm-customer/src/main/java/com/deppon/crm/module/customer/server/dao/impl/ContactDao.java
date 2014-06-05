package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.CustomerTableName;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.customer.server.dao.IContactDao;
import com.deppon.crm.module.customer.server.utils.CustomerLogUtil;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Order;
import com.deppon.crm.module.customer.shared.domain.RandomNumber;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ContactDao extends iBatis3DaoImpl implements IContactDao {

	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.Contact.";
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public int getContactCountByIdCard(String idCard) {
		return (Integer) this.getSqlSession().selectOne(
				NAME_SPACE + "getContactByIdCard", idCard);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public List<Contact> getContactByPhone(String phone) {
		return null;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public List<Contact> getContactByTelAndName(String tel, String name) {
		return null;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public int getContactCountByPhone(String phone) {
		if (ValidateUtil.objectIsEmpty(phone)) {
			throw new RuntimeException("手机为空");
		}
		return (Integer) this.getSqlSession().selectOne(
				NAME_SPACE + "getContactCountByPhone", phone);

	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public int getContactCountByTelAndName(String tel, String name) {
		if (ValidateUtil.objectIsEmpty(tel) || ValidateUtil.objectIsEmpty(name)) {
			throw new RuntimeException("手机或姓名为空");
		}
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("tel", tel);
		valueMap.put("name", name);
		return (Integer) this.getSqlSession().selectOne(
				NAME_SPACE + "getContactCountByTelAndName", valueMap);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void deleteContactByMemberId(String memberId) {
		getSqlSession()
				.delete(NAME_SPACE + "deleteContactByMemberId", memberId);

		List<String> idList = this.getSqlSession().selectList(
				NAME_SPACE + "getContactIdByMemberId", memberId);

		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,
						OperationFlg.D, idList);
		writeCustomerLog(list);

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
		return (ContactView) this.getSqlSession().selectOne(
				NAME_SPACE + "getContactByNumber", number);
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
		RandomNumber randomNumber = new RandomNumber();
		randomNumber.setMobile(phone);
		randomNumber.setRadomnumber(number);
		this.getSqlSession().insert(NAME_SPACE + "insertRandomPhone",
				randomNumber);
	}

	/**
	 * @description
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RandomNumber> getRandomNumberByPhone(String phone) {
		return this.getSqlSession().selectList(
				NAME_SPACE + "getRandomNumberByPhone", phone);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> searchContacts(Contact contact) {
		return this.getSqlSession().selectList(NAME_SPACE + "searchContacts",
				contact);
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
		int result = this.getSqlSession().insert(NAME_SPACE + "insertContact",
				contact);

		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,
						OperationFlg.I, contact.getId());
		writeCustomerLog(list);

		return result > 0 ? true : false;
	}

	/**
	 * 
	 * @绑定联系人
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@Override
	public boolean boundContact(Contact contact) {
		int result = this
				.getSqlSession()
				.update("com.deppon.crm.module.customer.shared.domain.AlterMember.updateContactById",
						contact);

		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,
						OperationFlg.U, contact.getId());
		writeCustomerLog(list);

		return result > 0 ? true : false;
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
		return (Order) this.getSqlSession().selectOne(
				NAME_SPACE + "queryOrderByChannelNumber", channelNumber);
	}

	/**
	 * 
	 * @根据渠道订单号查询订单信息
	 * @author 赵斌
	 * @2012-4-25
	 * @return
	 */
	@Override
	public Order getOrderByNumber(String number) {
		return (Order) this.getSqlSession().selectOne(
				NAME_SPACE + "queryOrderByNum", number);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateContact(Contact updateContact) {
		this.getSqlSession()
				.update("com.deppon.crm.module.customer.shared.domain.AlterMember.updateContactById",
						updateContact);

		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CUSTLINKMAN,
						OperationFlg.U, updateContact.getId());
		writeCustomerLog(list);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> searchContactByCondition(ContactCondition condition) {
		condition.setContactName(SqlUtil.getLikeField(condition
				.getContactName()));
		condition
				.setMemberName(SqlUtil.getLikeField(condition.getMemberName()));
		return this.getSqlSession().selectList(
				NAME_SPACE + "searchContactByCondition", condition);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	private void writeCustomerLog(List<CustTransationOperation> list) {
		CustomerLogUtil.writeCustomerLog(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#
	 * deleteRandomCodeByMobile(java.lang.String)
	 */
	@Override
	public void deleteRandomCodeByMobile(String conPhone) {
		this.getSqlSession().delete(NAME_SPACE + "deleteRandomCodeByMobile",
				conPhone);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#
	 * getOnlineBusinessId(java.lang.String)
	 */

	@Override
	public String getOnlineLinkmanId(String linkManId) {
		return (String) this.getSqlSession().selectOne(NAME_SPACE+"getOnlineBusinessId",linkManId);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#
	 * insertContactOnlineNum(java.lang.String,java.lang.String,java.lang.String)
	 */
	@Override
	public void insertContactOnlineNum(RegisterInfo info) {
		this.getSqlSession().insert(NAME_SPACE+"insertContactOnlineNum", info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#
	 * updateContactOnlineNum(java.lang.String,java.lang.String,java.lang.String)
	 */
	@Override
	public void updateContactOnlineNum(RegisterInfo registerInfo) {
		this.getSqlSession().update(NAME_SPACE+"updateContactOnlineNum", registerInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#
	 * unboundContactOnlineNum(java.lang.String)
	 */
	@Override
	public void unboundContactOnlineNum(RegisterInfo registerInfo) {
		this.getSqlSession().update(NAME_SPACE+"unboundContactOnlineNum", registerInfo);		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#
	 * changeContactOnlineNum(java.lang.String)
	 */
	@Override
	public void changeContactOnlineNum(RegisterInfo registerInfo) {
		this.getSqlSession().update(NAME_SPACE+"changeContactOnlineNum", registerInfo);	
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#getRegisterInfo(com.deppon.crm.module.client.customer.domain.RegisterInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RegisterInfo> getRegisterInfo(RegisterInfo registerInfo) {
		return getSqlSession().selectList(NAME_SPACE+"queryRegisterInfo", registerInfo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContactDao#unboundContactOnlineNum(java.lang.String)
	 */
	@Override
	public void unboundContactOnlineNum(String contactId) {
		this.getSqlSession().update(NAME_SPACE+"unboundContactByContactId",contactId);
	}

	/**(非 Javadoc)
	* <p>Title: searchContactList</p>
	* <p>Description: </p>
	* @author chenaichun 
	* @param contact
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IContactDao#searchContactList(com.deppon.crm.module.customer.shared.domain.Contact)
	*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> searchContactList(Contact contact) {
		return getSqlSession().selectList(NAME_SPACE+"queryContactList", contact);
	}
	
	
}
