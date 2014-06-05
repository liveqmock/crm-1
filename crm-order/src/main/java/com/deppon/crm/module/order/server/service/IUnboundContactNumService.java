package com.deppon.crm.module.order.server.service;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;

/**   
 * @description 联系人编码解绑数据组装、接口查询集成
 * @title IUnboundContactNumService.java
 * @package com.deppon.crm.module.order.server.service 
 * @author 苏玉军
 * @version 0.1 2012-9-8
 */

public interface IUnboundContactNumService {
	/**
	 * 
	 * @Description 根据联系人Id查询用户注册信息<br />
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param contactId
	 * @return
	 * List<?>
	 * @throws CrmBusinessException 接口异常
	 */
	List<RegisterInfo> getBoundInfoList(String contactId) throws CrmBusinessException;
	
	
	/**
	 * 
	 * @Description 联系人编码解绑操作
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param contact 联系人对象
	 * @return
	 * boolean
	 * @throws CrmBusinessException 
	 */
	boolean unboundContactNum(Contact contact,String channelId) throws CrmBusinessException;
	
	/**
	 * 
	 * @description 根据联系人编码查询联系人
	 * @author 苏玉军
	 * @version 0.1 2012-9-11
	 * @param contactNum
	 * @return
	 * ContactView
	 */
	ContactView queryContactViewByContactNum(String contactNum);
}
