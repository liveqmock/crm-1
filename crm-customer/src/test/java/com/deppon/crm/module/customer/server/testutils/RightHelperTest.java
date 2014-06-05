/**
 * 
 */
package com.deppon.crm.module.customer.server.testutils;

import com.deppon.crm.module.customer.server.utils.RightsHelper;

import junit.framework.TestCase;

/**
 * @author Administrator
 *
 */
public class RightHelperTest extends TestCase{
	RightsHelper helper = new RightsHelper();
	/**
	 * 
	 @Discript 该方法未完成，所以暂时这样测试了
	 @author  唐亮
	 @date 2012-12-25
	 @return void
	 */
	@SuppressWarnings("static-access")
	public void testGetRightByKey(){
		//输入"潘光均" 输出 null
		String str = "潘光均";
		helper.getRightsByKey(str);
		//输入null 输出null
		str = null;
		helper.getRightsByKey(str);
	}
}
