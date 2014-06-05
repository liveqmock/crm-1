/**
 * 
 */
package com.deppon.crm.module.customer.server.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;

/**
 * @author Administrator
 *
 */
public class BaseDataServiceTest extends BeanUtil{
	
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetBankCityByBankProvinceId(){
		Assert.assertNotNull(baseDataService.getBankCityByBankProvinceId("213546"));
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetBankProvince(){
		Assert.assertNotNull(baseDataService.getBankProvince());
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetAccountBank(){
		Assert.assertNotNull(baseDataService.getAccountBank());
	}
}
