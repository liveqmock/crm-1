/**
 * @description
 * @author 赵斌
 * @2012-4-24
 * @return
 */
package com.deppon.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.dao.IContactIntegralDao;
import com.deppon.crm.module.customer.server.service.IContactIntegralService;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;

/**
 * @author 赵斌
 *
 */
public class ContactIntegralService implements IContactIntegralService 
{
	
	public IContactIntegralDao contactIntegralDao;
	
	public IContactIntegralDao getContactIntegralDao() {
		return contactIntegralDao;
	}
	public void setContactIntegralDao(IContactIntegralDao contactIntegralDao) {
		this.contactIntegralDao = contactIntegralDao;
	}

	/**
	 * 
	 * @联系人积分创建
	 * @author 赵斌
	 * @2012-4-24
	 * @return ContactIntegral 联系人积分
	 * @param contactIntegral 联系人积分
	 */
	@Override
	public ContactIntegral createContactIntegral(ContactIntegral contactIntegral) 
	{
		return contactIntegralDao.createContactIntegral(contactIntegral);
	}

	/**
	 * 
	 * @联系人积分修改
	 * @author 赵斌
	 * @2012-4-24
	 * @return boolean
	 * @param contactIntegral 联系人积分
	 */
	@Override
	public boolean updateContactIntegral(ContactIntegral contactIntegral) 
	{
		return contactIntegralDao.updateContactIntegral(contactIntegral);
	}

	/**
	 * 
	 * @联系人积分查询
	 * @author 赵斌
	 * @2012-4-24
	 * @return ContactIntegral
	 * @param id 联系人积分id
	 */
	@Override
	public ContactIntegral getContactIntegral(String id) 
	{
		return contactIntegralDao.getContactIntegral(id);
	}
	
	@Override
	public ContactIntegral getContactIntegralByContactId(String contactId) {
		if(ValidateUtil.objectIsEmpty(contactId)){
			return null;
		}
		
		ContactIntegral integral = new ContactIntegral();
		integral.getContact().setId(contactId);
		List<ContactIntegral> list =  contactIntegralDao.searchContactIntegrals(integral);
		if(list.isEmpty()){
			contactIntegralDao.createContactIntegral(integral);
			return integral;
		}else{
			return list.get(0);
		}
	}
	@Override
	public List<ContactIntegral> searchContactIntegralsBycontactIdList(
			List<String> contactIdList, int start, int limit) {
		return contactIntegralDao.searchContactIntegralsBycontactIdList(contactIdList, start, limit);
	}
	@Override
	public long countSearchContactIntegralsBycontactIdList(
			List<String> contactIdList) {
		return contactIntegralDao.countSearchContactIntegralsBycontactIdList(contactIdList);
	}
	@Override
	public List<ContactIntegral> searchContactIntegralsByMemberId(
			String memberId) {
		return contactIntegralDao.searchContactIntegralsByMemberId(memberId);
	}

}
