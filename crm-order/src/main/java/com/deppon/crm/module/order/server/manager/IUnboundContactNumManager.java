package com.deppon.crm.module.order.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;

/**   
 * 
 * @description 联系人解绑操作接口定义<br />
 * @title IUnboundContactNumManager.java
 * @package com.deppon.crm.module.order.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-9-8
 * 
 */

public interface IUnboundContactNumManager {
	/**
	 * 
	 * @description 根据联系人编码查询联系人及联系人编码绑定信息
	 * @author 苏玉军
	 * @version 0.1 2012-9-8
	 * @param contactNumber 联系人编码
	 * @return
	 *  Map<String,Object> 封装查询结果 给Action
	 * 
	 */
	Map<String, Object> queryContactAndBoundInfo(String contactNumber);
	
	/**
	 * 
	 * @description 解绑操作封装
	 * @author 苏玉军
	 * @version 0.1 2012-9-8
	 * @param contact 联系人对象
	 * @param channelId 渠道用户Id
	 * @param source  订单来源，渠道Id来源
	 * @return boolean 解绑结果
	 * 
	 */
	boolean unboundContactNumber(String contactNumber,String registerId,String source);
	
	/**
	 * 
	 * @descption 解绑成功发送短信
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @param mobilePhone
	 * void
	 * @throws CrmBusinessException 
	 */
	void sendSmsToContact(Contact contact,User user,String registerId) throws CrmBusinessException;
	
	
	/**
	 * 
	 * @description  根据联系人编码查询联系人信息
	 * @author 苏玉军
	 * @version 0.1 2012-9-11
	 * @param contactNum
	 * @return
	 * ContactView
	 */
	ContactView queryContactViewByNum(String contactNum);
	
	/**
	 * 
	 * @Description:这里写描述<br />
	 * @author 苏玉军
	 * @version 0.1 2012-9-11
	 * @return
	 * List<RegisterInfo>
	 */
	List<RegisterInfo> boundInfos(String contactId);
}
