
package com.deppon.crm.module.order.server.service.impl;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.server.service.IContactService;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.order.server.service.IUnboundContactNumService;

/**   
 * @description 联系人编码解绑数据组装、接口查询集成
 * @title IUnboundContactNumService.java
 * @package com.deppon.crm.module.order.server.service 
 * @author 苏玉军
 * @version 0.1 2012-9-8
 */

public class UnboundContactNumService implements IUnboundContactNumService {
	//客户操作信息
	private ICustomerOperate customerOperate;
	//合同Service
	private IContactService contactService;
	
	/**
	 *@return  customerOperate;
	 */
	public ICustomerOperate getCustomerOperate() {
		return customerOperate;
	}
	/**
	 * @param customerOperate : set the property customerOperate.
	 */
	public void setCustomerOperate(ICustomerOperate customerOperate) {
		this.customerOperate = customerOperate;
	}
	/**
	 *@return  contactService;
	 */
	public IContactService getContactService() {
		return contactService;
	}
	/**
	 * @param contactService : set the property contactService.
	 */
	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}
	/**
	 * 
	 * @Description 根据联系人Id查询用户注册信息<br />
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param contactId
	 * @return List<RegisterInfo> 用户注册信息
	 * @throws CrmBusinessException 接口异常
	 * @see com.deppon.crm.module.order.server.service.IUnboundContactNumService#getBoundInfoList(java.lang.String)
	 */
	@Override
	public List<RegisterInfo> getBoundInfoList(String contactId) throws CrmBusinessException {
		List<RegisterInfo> regInfoList = customerOperate.queryRegisterInfosByContactId(contactId);
		return regInfoList;
	}
	/**
	 * 
	 * @Description 联系人编码解绑操作
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param contact 联系人对象
	 * @param channelId 注册用户Id
	 * @return boolean
	 * @throws CrmBusinessException 
	 * @see com.deppon.crm.module.order.server.service.IUnboundContactNumService#unboundContactNum(com.deppon.crm.module.customer.shared.domain.Contact)
	 */
	@Override
	public boolean unboundContactNum(Contact contact,String registerId) throws CrmBusinessException {
		boolean b2 = customerOperate.bindToContact("",registerId);
		return b2;
	}
	/**
	 * 
	 * @description 根据联系人编码查询联系人
	 * @author 苏玉军
	 * @version 0.1 2012-9-11
	 * @param contactNum
	 * @return
	 * ContactView
	 * @see com.deppon.crm.module.order.server.service.IUnboundContactNumService#queryContactViewByContactNum(java.lang.String)
	 */
	@Override
	public ContactView queryContactViewByContactNum(String contactNum) {
		return contactService.getContactByNumber(contactNum);
	}

}
