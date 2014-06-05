package com.deppon.crm.module.customer.server.service;

import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;

public interface IContactVaryService {

	public void insertContactVary(ContactVary contactVary);
			
	/**
	 * <p>
	 * Description:保存联系人变跟实体<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return
	 * ContactVary
	 */
	public ContactVary getContactVaryById(String id);
	
	/**
	 * <p>
	 * Description:通过工作流号查询联系人变跟实体<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param workFlowId
	 * @return
	 * ContactVary
	 */
	public ContactVary getContactVaryByWorkFlowId(long workFlowId);

	/**
	 * <p>
	 * Description:得到SEQ_ID_contactvary序列的下一个值<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @return
	 * String
	 */
	public String getContactVaryId();
	
	/**
	 * @作者：李学兴
	 * @时间：2012-9-17
	 * @功能描述：通过会员id判断变更联系人的目标会员 工作流是否审批中
	 * @参数：memberId 会员ID
	 * @返回值：boolean true：审批中，false不是审批中
	 * */
	public ContactVary isContactVary2Member(String memberId);
}
