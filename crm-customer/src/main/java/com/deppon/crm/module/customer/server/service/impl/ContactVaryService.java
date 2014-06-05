package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.customer.server.dao.IContactVaryDao;
import com.deppon.crm.module.customer.server.service.IContactVaryService;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
/**
 * 
 * <p>
 * Description:联系人变更service<br />
 * </p>
 * @title ContactVaryService.java
 * @package com.deppon.crm.module.customer.server.service.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ContactVaryService implements IContactVaryService{
	/**
	 * 联系人变更dao
	 */
	private IContactVaryDao contactVaryDao;
	/**
	 * 
	 * <p>
	 * Description:contactVaryDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param contactVaryDao
	 * void
	 */
	public void setContactVaryDao(IContactVaryDao contactVaryDao) {
		this.contactVaryDao = contactVaryDao;
	}
	/**
	 * 
	 * <p>
	 * Description:插入联系人变更信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param contactVary
	 *
	 */
	@Override
	public void insertContactVary(ContactVary contactVary) {
		//插入联系人变更信息
		contactVaryDao.insertContactVary(contactVary);
	}
	/**
	 * 
	 * <p>
	 * Description:根据id获得联系人变更信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public ContactVary getContactVaryById(String id) {
		//根据id获得联系人变更信息
		return contactVaryDao.getContactVaryById(id);
	}
	/**
	 * 
	 * <p>
	 * Description:根据工作流id查询联系人变更信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param workFlowId
	 * @return
	 *
	 */
	@Override
	public ContactVary getContactVaryByWorkFlowId(long workFlowId) {
		//封装条件
		ContactVary contactVary = new ContactVary();
		//设值
		contactVary.setWorkflowId(workFlowId);
		//根据工作流id查询联系人变更信息
		List<ContactVary> list = contactVaryDao.searchContactVarys(contactVary);
		//集合为空
		if(list.isEmpty()){
			return null;
		}else{
			//返回结果
			return list.get(0);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:根据会员id查询联系人变更信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public ContactVary isContactVary2Member(String memberId) {
		//根据会员id查询联系人变更信息
		return contactVaryDao.isContactVary2Member(memberId);
	}
	/**
	 * 
	 * <p>
	 * Description:获得联系人变更id<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 *
	 */
	@Override
	public String getContactVaryId() {
		//获得联系人变更id
		return contactVaryDao.getContactVaryId();
	}

}
