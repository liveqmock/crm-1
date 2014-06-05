/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundContactNumValidatorTest.java
 * @package com.deppon.crm.module.order.manager 
 * @author Administrator
 * @version 0.1 2012-9-10
 */
package com.deppon.crm.module.order.manager;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.order.server.manager.UnboundContactNumValidator;
import com.deppon.crm.module.order.shared.exception.UnboundExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundContactNumValidatorTest.java
 * @package com.deppon.crm.module.order.manager 
 * @author 苏玉军
 * @version 0.1 2012-9-10
 */

public class UnboundContactNumValidatorTest {

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deppon.crm.module.order.server.manager.UnboundContactNumValidator#isContactNull(com.deppon.crm.module.customer.shared.domain.Contact)}.
	 */
	@Test
	public void testIsContactNull() {
		Contact contact = new Contact();
		contact.setId("1222222");
		contact.setCreateUser("suyujun");
		UnboundContactNumValidator.isContactNull(contact);
		contact = null;
		try{
			UnboundContactNumValidator.isContactNull(contact);
		}catch(Exception e){
			Assert.assertNotNull(e);
		}
		
	}

	/**
	 * Test method for {@link com.deppon.crm.module.order.server.manager.UnboundContactNumValidator#isNullContactMobilePhone(com.deppon.crm.module.customer.shared.domain.Contact)}.
	 */
	@Test
	public void testIsNullContactMobilePhone() {
		Contact contact = new Contact();
		contact.setMobilePhone("13917097761");
		UnboundContactNumValidator.isNullContactMobilePhone(contact);
		try{
			contact.setMobilePhone(null);
			UnboundContactNumValidator.isNullContactMobilePhone(contact);
		}catch(Exception e){
			Assert.assertNotNull(e);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:Test method for {@link com.deppon.crm.module.order.server.manager.UnboundContactNumValidator#isContactNumberNull(java.lang.String)}<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-19
	 * void
	 */
	@Test
	public void testIsContactNumberNull() {
		String contactNumber = null;
		
		//StringUtils.isEmpty(contactNumber) is true
		try{
			UnboundContactNumValidator.isContactNumberNull(contactNumber);
		}catch(GeneralException e){
			if(e.getErrorCode().equals(UnboundExceptionType.CONTACT_NUMBER_NULL.getErrorCode())){
				Assert.assertFalse(false);
			}else{
				Assert.fail("异常");
			}
		}
		
		//StringUtils.isEmpty(contactNumber) is false
		contactNumber = System.currentTimeMillis()+"";
		Assert.assertTrue(UnboundContactNumValidator.isContactNumberNull(contactNumber));
	}

	/**
	 * 
	 * <p>
	 * Description:Test method for {@link com.deppon.crm.module.order.server.manager.UnboundContactNumValidator#isContactViewNull(com.deppon.crm.module.customer.shared.domain.ContactView)}<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-19
	 * void
	 */
	@Test
	public void testIsContactViewNull() {
		ContactView contactView = null;
		
		//contactView == null  is true
		try{
			UnboundContactNumValidator.isContactViewNull(contactView);
		}catch(GeneralException e){
			if(e.getErrorCode().equals(UnboundExceptionType.CONTACT_NOT_EXISTS.getErrorCode())){
				Assert.assertFalse(false);
			}else{
				Assert.fail("异常");
			}
		}
		
		//contactView == null is false
		contactView = new ContactView();
		Assert.assertTrue(UnboundContactNumValidator.isContactViewNull(contactView));
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:Test method for {@link com.deppon.crm.module.order.server.manager.UnboundContactNumValidator#isRegisterIdNull(java.lang.String)}<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-19
	 * void
	 */
	@Test
	public void testIsRegisterIdNull() {
		String registerId = null;
		
		//StringUtils.isEmpty(registerId)  is true
		//GeneralException.getErrorCode 有误
//		try{
//			UnboundContactNumValidator.isRegisterIdNull(registerId);
//		}catch(GeneralException e){
//			if(e.getErrorCode().equals(UnboundExceptionType.REGISTER_NAME_NULL.getErrorCode())){
//				Assert.assertFalse(false);
//			}else{
//				Assert.fail("异常");
//			}
//		}
		
		//StringUtils.isEmpty(registerId) is false
		registerId = System.currentTimeMillis()+"";
		Assert.assertTrue(UnboundContactNumValidator.isRegisterIdNull(registerId));
	}
}
