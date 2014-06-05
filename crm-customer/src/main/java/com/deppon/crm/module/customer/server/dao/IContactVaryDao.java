package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;

/**   
 * <p>
 * Description:联系人变跟dao接口<br />
 * </p>
 * @title IContactVaryDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 李国文
 * @version 0.1 2013-2-26
 */

public interface IContactVaryDao {
	
	/**
	 * <p>
	 * Description:保存联系人变跟实体<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactVary
	 * void
	 */
	public void insertContactVary(ContactVary contactVary);
	
	/**
	 * <p>
	 * Description:根据ID 查询联系人变跟实体<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @return
	 * ContactVary
	 */
	public ContactVary getContactVaryById(String id);
	
	/**
	 * <p>
	 * Description:根据联系人变跟实体的信息 查询对应的实体<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactVary
	 * @return
	 * List<ContactVary>
	 */
	public List<ContactVary> searchContactVarys(ContactVary contactVary);

	/**
	 * <p>
	 * Description:通过序列得到   联系人变跟实体的ID
	 * 	SEQ_ID_contactvary
	 * <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @return
	 * String
	 */
	public String getContactVaryId();

	/**
	 * @作者：李学兴
	 * @时间：2012-9-17
	 * @功能描述：通过会员id判断变更联系人的目标会员 工作流是否审批中
	 * @参数：memberId 会员ID
	 * @返回值：ContactVary 变更联系人信息
	 * */
	public ContactVary isContactVary2Member(String memberId);
}
