package com.deppon.crm.module.order.server.manager;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.order.shared.exception.UnboundException;
import com.deppon.crm.module.order.shared.exception.UnboundExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * @description 解绑数据验证类
 * @title UnboundContactNumValidator.java
 * @package com.deppon.crm.module.order.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-9-10
 */

public class UnboundContactNumValidator {
	
	/**
	 * 
	 * @description 验证联系人对象是否为空 
	 * @author 苏玉军
	 * @version 0.1 2012-9-20
	 * @param contact
	 * @return
	 * boolean
	 */
	public static boolean isContactNull(Contact contact){
		if(contact == null){
			UnboundException e = new UnboundException(UnboundExceptionType.CONTACT_OBJECT_ISNULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				private static final long serialVersionUID = 5035356416096950424L;
			};
		}
		return true;
	}
	
	/**
	 * 
	 *@description 验证联系人手机号码是否为空
	 * @author 苏玉军
	 * @version 0.1 2012-9-20
	 * @param contact
	 * @return
	 * boolean
	 */
	public static boolean isNullContactMobilePhone(Contact contact){
		if(StringUtils.isEmpty(contact.getMobilePhone())){
			UnboundException e = new UnboundException(UnboundExceptionType.CONTACT_MOBILEPHONE_ISNULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				private static final long serialVersionUID = 4886563259500201220L;
			};
		}
		return true;
	}
	
	/**
	 * @description 验证联系人是否为空
	 * @author 苏玉军
	 * @version 0.1 2012-9-20
	 * @param contactNumber
	 * @return
	 * boolean
	 */
	public static boolean isContactNumberNull(String contactNumber){
		if(StringUtils.isEmpty(contactNumber)){
			UnboundException e = new UnboundException(UnboundExceptionType.CONTACT_NUMBER_NULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				private static final long serialVersionUID = 8601769755124430898L;
			};
		}
		return true;
	}
	
	/**
	 * @description 验证联系人编码是否为空
	 * @author 苏玉军
	 * @version 0.1 2012-9-20
	 * @param contactView
	 * @return
	 * boolean
	 */
	public static boolean isContactViewNull(ContactView contactView){
		if(contactView == null){
			UnboundException e = new UnboundException(UnboundExceptionType.CONTACT_NOT_EXISTS);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				private static final long serialVersionUID = -1221053857050180261L;
			};
		}
		return true;
	}
	
	/**
	 * @description 验证注册用户Id
	 * @author 苏玉军
	 * @version 0.1 2012-9-20
	 * @param contactNumber
	 * @return
	 * boolean
	 */
	public static boolean isRegisterIdNull(String registerId){
		if(StringUtils.isEmpty(registerId)){
			UnboundException e = new UnboundException(UnboundExceptionType.REGISTER_NAME_NULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				private static final long serialVersionUID = 8367646347730845425L;
			};
		}
		return true;
	}
}
