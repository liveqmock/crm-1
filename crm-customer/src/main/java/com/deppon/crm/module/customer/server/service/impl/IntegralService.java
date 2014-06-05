package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.customer.server.dao.IContactIntegralDao;
import com.deppon.crm.module.customer.server.dao.IIntegralDao;
import com.deppon.crm.module.customer.server.dao.IMemberIntegralDao;
import com.deppon.crm.module.customer.server.service.IIntegralService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.GiftConvertHoldRecode;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.Integral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;

public class IntegralService implements IIntegralService{
	
	private IMemberIntegralDao memberIntegralDao;
	private IIntegralDao integralDao;
	private IContactIntegralDao contactIntegralDao;
	
	public void setMemberIntegralDao(IMemberIntegralDao memberIntegralDao) {
		this.memberIntegralDao = memberIntegralDao;
	}

	public void setIntegralDao(IIntegralDao integralDao) {
		this.integralDao = integralDao;
	}


	public void setContactIntegralDao(IContactIntegralDao contactIntegralDao) {
		this.contactIntegralDao = contactIntegralDao;
	}

	/**
	 * 
	 * <p>插入积分明细<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-19
	 * @param integral
	 * void
	 */
	public void createIntegral(Integral integral){
		if(integral instanceof AdjustIntegral){
			integralDao.insertAdjustIntegral((AdjustIntegral)integral);
		}else if(integral instanceof HandRewardIntegral){
			integralDao.insertHandRewardIntegral((HandRewardIntegral)integral);
		}else if(integral instanceof IntegralConvertGift){
			integralDao.insertIntegralConvertGift((IntegralConvertGift)integral);
		}else{
			throw new RuntimeException("扩展了对象，这里也要添加代码的，亲");
		}
	}
	
	/**
	 * 
	 * <p>更新联系人的积分<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-19
	 * @param contactIntegral
	 * void
	 */
	public void updateContactIntegral(ContactIntegral contactIntegral){
		if(ValidateUtil.objectIsEmpty(contactIntegral.getId())){
			contactIntegralDao.createContactIntegral(contactIntegral);
		}else{
			contactIntegralDao.updateContactIntegral(contactIntegral);
		}
	}
	/**
	 * 
	 * <p>
	 * 更新积分联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param memberIntegral
	 * void
	 */
	public void updateMemberIntegral(MemberIntegral memberIntegral){
		if(ValidateUtil.objectIsEmpty(memberIntegral.getId())){
			memberIntegralDao.insertMemberIntegral(memberIntegral);
		}else{
			memberIntegralDao.updateMemberIntegral(memberIntegral);
		}
	}
	
	/**
	 * 
	 * <p>通过联系人ID获得联系人积分对象<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-19
	 * @param id
	 * @return
	 * ContactIntegral
	 */
	public ContactIntegral getContactIntegralByContact(String contactId){
		ContactIntegral contactIntegral = new ContactIntegral();
		Contact contact = new Contact();
		contact.setId(contactId);
		contactIntegral.setContact(contact);
		
		List<ContactIntegral> list = contactIntegralDao.searchContactIntegrals(contactIntegral);
		
		if(list.size() <= 0){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public void saveIntegralOperation(IntegralOperation operation) {
		List<ContactIntegral> contactIntegralList = operation.getContactIntegral();
		List<Integral> integralList = operation.getIntegral();
		List<MemberIntegral> memberIntegralList = operation.getMemberIntegral();
		
		for (ContactIntegral contactIntegral : contactIntegralList) {
			contactIntegral.setModifyUser(ContextUtil.getCurrentUserEmpId());
			updateContactIntegral(contactIntegral);
		}
		for (Integral integral : integralList) {
			integral.setCreateUser(ContextUtil.getCurrentUserEmpId());
			createIntegral(integral);
		}
		for (MemberIntegral memberIntegral : memberIntegralList) {
			memberIntegral.setModifyUser(ContextUtil.getCurrentUserEmpId());
			updateMemberIntegral(memberIntegral);
		}
		
	}

	@Override
	public List<AdjustIntegral> searchAdjustIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		return integralDao.searchAdjustIntegralForContactId(contactIdList, start, limit);
	}

	@Override
	public long countSearchAdjustIntegralForContactId(List<String> contactIdList) {
		return integralDao.countSearchAdjustIntegralForContactId(contactIdList);
	}

	@Override
	public List<HandRewardIntegral> searchHandRewardIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		return integralDao.searchHandRewardIntegralForContactId(contactIdList, start, limit);
	}

	@Override
	public long countSearchHandRewardIntegralForContactId(
			List<String> contactIdList) {
		return integralDao.countSearchHandRewardIntegralForContactId(contactIdList);
	}

	@Override
	public List<IntegralConvertGift> searchIntegralConvertGiftForContactId(
			List<String> contactIdList, int start, int limit) {
		return integralDao.searchIntegralConvertGiftForContactId(contactIdList, start, limit);
	}

	@Override
	public long countSearchIntegralConvertGiftForContactId(
			List<String> contactIdList) {
		return integralDao.countSearchIntegralConvertGiftForContactId(contactIdList);
	}

	@Override
	public List<AdjustIntegral> searchAdjustIntegrals(AdjustIntegral integral,
			int start, int limit) {
		return integralDao.searchAdjustIntegrals(integral, start, limit);
	}

	@Override
	public long countSearchAdjustIntegrals(AdjustIntegral integral) {
		return integralDao.countSearchAdjustIntegrals(integral);
	}

	@Override
	public List<HandRewardIntegral> searchHandRewardIntegrals(
			HandRewardIntegral integral, int start, int limit) {
		return integralDao.searchHandRewardIntegrals(integral, start, limit);
	}

	@Override
	public long countSearchHandRewardIntegrals(HandRewardIntegral integral) {
		return integralDao.countSearchHandRewardIntegrals(integral);
	}

	/**
	 * 
	 * <p>
	 * 保存积分兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public void insertIntegralConvertGift(IntegralConvertGift integral){
		integralDao.insertIntegralConvertGift(integral);
	}
	/**
	 * 
	 * <p>
	 * 更新积分兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public void updateIntegralConvertGift(IntegralConvertGift integral){
		integralDao.updateIntegralConvertGift(integral);
	}
	@Override
	public List<IntegralConvertGift> searchIntegralConvertGifts(
			IntegralConvertGift integral, int start, int limit) {
		
		return integralDao.searchIntegralConvertGifts(integral, start, limit);
	}
	@Override
	public List<IntegralConvertGift> searchIntegralConvertGift(
			IntegralConvertGiftCondition condition,int start,int limit) {
		return integralDao.searchIntegralConvertGift(condition,start,limit);
	}

	@Override
	public long countSearchIntegralConvertGift(
			IntegralConvertGiftCondition condition) {
		return integralDao.countSearchIntegralConvertGift(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IIntegralService#getTemporaryGiftBill(java.lang.String)
	 */
	@Override
	public List<GiftConvertHoldRecode> getTemporaryGiftBill(String deptId) {
		return integralDao.getTemporaryGiftBill(deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IIntegralService#deleteTemporaryGiftBill(java.util.List)
	 */
	@Override
	public void deleteTemporaryGiftBill(String deptId) {
			integralDao.batchDeleteTemporaryGiftBill(deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IIntegralService#deleteTemporaryGiftBill(java.util.List)
	 */
	@Override
	public void deleteTemporaryGiftBill(List<String> ids) {
		integralDao.batchDeleteTemporaryGiftBill(ids);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IIntegralService#saveTemporyGiftList(java.util.List)
	 */
	@Override
	public void saveTemporyGiftList(
			List<GiftConvertHoldRecode> giftConvertHoldRecodes) {
		for (int i = 0; i < giftConvertHoldRecodes.size(); i++) {
			integralDao.addTemporySaveGift(giftConvertHoldRecodes.get(i));
		}
	}
}
