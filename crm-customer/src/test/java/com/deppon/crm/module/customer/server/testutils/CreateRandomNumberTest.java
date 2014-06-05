/**
 * 
 */
package com.deppon.crm.module.customer.server.testutils;

import com.deppon.crm.module.customer.server.utils.CreateRandomNumber;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author 唐亮
 *
 */
public class CreateRandomNumberTest extends TestCase{
	CreateRandomNumber ran = new CreateRandomNumber();
	@SuppressWarnings("static-access")
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-25
	 @return void
	 */
	public void testGetRandomNumber(){
		String str1;
		for(int i=0;i<1000;i++){
			str1=  ran.getRandomNumber();
		   Assert.assertNotNull(str1);
		}
		
	}
}
