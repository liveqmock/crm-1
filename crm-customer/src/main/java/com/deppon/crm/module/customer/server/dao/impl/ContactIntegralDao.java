/**
 * @description
 * @author 赵斌
 * @2012-4-23
 * @return
 */
package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IContactIntegralDao;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @author 赵斌
 *
 */
public class ContactIntegralDao extends iBatis3DaoImpl implements IContactIntegralDao
{
	private static final String NAMESPACE_CONTACTINTEGRAL = "com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral.";
	// 新增联系人积分
	private static final String INSERT_CONTACTINTEGRAL = "insertContactIntegral";
	// 同意绑定合同附属部门
	private static final String UPDATE_CONTACTINTEGRAL = "updateContactIntegral";
	// 根据ID查询合同基本信息
	private static final String QUERY_CONTACTINTEGRAL_ID = "queryContactIntegralById";
	
	
	
	/**
	 * 
	 * @联系人积分创建
	 * @author 赵斌
	 * @2012-4-23
	 * @return ContactIntegral 联系人积分
	 * @param contactIntegral 联系人积分
	 */
	@Override
	public ContactIntegral createContactIntegral(ContactIntegral contactIntegral) 
	{
		 this.getSqlSession().insert(NAMESPACE_CONTACTINTEGRAL + INSERT_CONTACTINTEGRAL,
				contactIntegral);
		 return contactIntegral;
	}

	
	/**
	 * 
	 * @联系人积分修改
	 * @author 赵斌
	 * @2012-4-23
	 * @return boolean
	 * @param contactIntegral 联系人积分
	 */
	@Override
	public boolean updateContactIntegral(ContactIntegral contactIntegral) 
	{
		return this.getSqlSession().update(
			NAMESPACE_CONTACTINTEGRAL + UPDATE_CONTACTINTEGRAL, contactIntegral) > 0 ? true : false;
	}


	/**
	 * 
	 * @联系人积分查询
	 * @author 赵斌
	 * @2012-4-23
	 * @return ContactIntegral
	 * @param id 联系人积分id
	 */
	@Override
	public ContactIntegral getContactIntegral(String id) 
	{
		return (ContactIntegral)this.getSqlSession().selectOne(NAMESPACE_CONTACTINTEGRAL + QUERY_CONTACTINTEGRAL_ID, id);
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
	public List<ContactIntegral> searchContactIntegralsByMemberId(String memberId){
		return this.getSqlSession().selectList(NAMESPACE_CONTACTINTEGRAL+"searchContactIntegralsByMemberId",memberId);
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
	public List<ContactIntegral> searchContactIntegrals(ContactIntegral integral) {
		return this.getSqlSession().selectList(NAMESPACE_CONTACTINTEGRAL+"searchContactIntegrals", integral);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ContactIntegral> searchContactIntegralsBycontactIdList(
			List<String> contactIdList,int start,int limit) {
		RowBounds row = new RowBounds(start,limit);
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return this.getSqlSession().selectList(NAMESPACE_CONTACTINTEGRAL+"searchContactIntegralsBycontactIdList",map,row);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long countSearchContactIntegralsBycontactIdList(
			List<String> contactIdList) {
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return  (Long) this.getSqlSession().selectOne(NAMESPACE_CONTACTINTEGRAL+"countSearchContactIntegralsBycontactIdList",map);
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
	public void deleteContactIntegralByMemberId(
			String memberId) {
		getSqlSession()
				.update(NAMESPACE_CONTACTINTEGRAL + "deleteContactIntegralByMemberId", memberId);
		
	}
	

}
