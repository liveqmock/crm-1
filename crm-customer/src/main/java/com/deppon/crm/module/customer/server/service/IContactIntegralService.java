/**
 * @description
 * @author 赵斌
 * @2012-4-24
 * @return
 */
package com.deppon.crm.module.customer.server.service;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;

/**
 * @author 赵斌
 *
 */
public interface IContactIntegralService 
{
	/**
	 * @联系人积分创建
	 * @author 赵斌
	 * @2012-4-24
	 * @return ContactIntegral 联系人积分
	 * @param contactIntegral 联系人积分
	 */
	public ContactIntegral createContactIntegral(ContactIntegral contactIntegral);

	/**
	 * @联系人积分修改
	 * @author 赵斌
	 * @2012-4-24
	 * @return boolean
	 * @param contactIntegral 联系人积分
	 */
	public boolean updateContactIntegral(ContactIntegral contactIntegral);
	
	/**
	 * @联系人积分查询
	 * @author 赵斌
	 * @2012-4-24
	 * @return ContactIntegral
	 * @param id 联系人积分id
	 */
	public ContactIntegral getContactIntegral(String id);
	
	/**
	 * <p>
	 * Description:通过联系人ID得到联系人积分信息<br/>
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-27
	 * @param contactId
	 * @return
	 * ContactIntegral
	 */
	public ContactIntegral getContactIntegralByContactId(String contactId);
	
	
	/**
	 * <p>
	 * Description:通过联系人ID 分页查询  联系人积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> searchContactIntegralsBycontactIdList(List<String> contactIdList,int start,int limit);
	
	/**
	 * <p>
	 * Description:通过联系人ID统计联系人对应积分信息的 总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchContactIntegralsBycontactIdList(List<String> contactIdList);

	/**
	 * <p>
	 * Description:通过会员ID查询联系人积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param memberId
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> searchContactIntegralsByMemberId(
			String memberId);

}
