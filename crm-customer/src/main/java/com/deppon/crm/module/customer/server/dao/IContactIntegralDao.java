/**
 * @description
 * @author 赵斌
 * @2012-4-23
 * @return
 */
package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;

/**   
 * <p>
 * Description:联系人积分dao接口<br />
 * </p>
 * @title IContactIntegralDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 李国文
 * @version 0.1 2013-2-26
 */

public interface IContactIntegralDao 
{
	/**
	 * 
	 * @联系人积分创建
	 * @author 赵斌
	 * @2012-4-23
	 * @return ContactIntegral 联系人积分
	 * @param contactIntegral 联系人积分
	 */
	public ContactIntegral createContactIntegral(ContactIntegral contactIntegral);

	/**
	 * 
	 * @联系人积分修改
	 * @author 赵斌
	 * @2012-4-23
	 * @return boolean
	 * @param contactIntegral 联系人积分
	 */
	public boolean updateContactIntegral(ContactIntegral contactIntegral);
	
	/**
	 * 
	 * @联系人积分查询
	 * @author 赵斌
	 * @2012-4-23
	 * @return ContactIntegral
	 * @param id 联系人积分id
	 */
	public ContactIntegral getContactIntegral(String id);
	
	/**
	 * <p>
	 * Description:根据联系人积分条件 查询积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param integral
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> searchContactIntegrals(ContactIntegral integral);
	
	/**
	 * 
	 * <p>
	 * 根据联系人ids查询联系人积分<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-2
	 * @param contactIdList
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> searchContactIntegralsBycontactIdList(List<String> contactIdList,int start,int limit);
	
	/**
	 * <p>
	 * Description:根据联系人ID 对应的联系人积分总数两<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchContactIntegralsBycontactIdList(List<String> contactIdList);
	
	/**
	 * <p>
	 * Description:根据会员ID查询联系人积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> searchContactIntegralsByMemberId(
			String memberId);

	/**
	 * <p>
	 * Description:根据会员ID 删除联系人积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param memberId
	 * void
	 */
	public void deleteContactIntegralByMemberId(
			String memberId);

}
