package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.customer.server.dao.IContactVaryDao;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ContactVaryDao extends iBatis3DaoImpl implements IContactVaryDao{
	
	private final static String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.integral.ContactVary.";
	
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
	public void insertContactVary(ContactVary contactVary) {
		this.getSqlSession().insert(NAME_SPACE+"insertContactVary",contactVary);
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
	public ContactVary getContactVaryById(String id) {
		return (ContactVary) this.getSqlSession().selectOne(NAME_SPACE+"getContactVaryById",id);
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
	public List<ContactVary> searchContactVarys(ContactVary contactVary) {
		return this.getSqlSession().selectList(NAME_SPACE+"searchContactVarys",contactVary);
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
	public String getContactVaryId() {
		return (String) this.getSqlSession().selectOne(NAME_SPACE+"getContactVaryId");
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
	private List<ContactVary> isContactVary2Member(ContactVary contactVary) {
		return this.getSqlSession().selectList(NAME_SPACE+"isContactVary2Member",contactVary);
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
	public ContactVary isContactVary2Member(String memberId) {
		ContactVary contactVary = new ContactVary();
		MemberIntegral toMemberIntegral = new MemberIntegral();
		Member mem = new Member();
		mem.setId(memberId);
		toMemberIntegral.setMember(mem);
		contactVary.setToMemberIntegral(toMemberIntegral);
		List<ContactVary> conList = isContactVary2Member(contactVary);
		return conList.size()>0?conList.get(0):null;
	}
	
}
