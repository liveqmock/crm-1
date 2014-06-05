/**
 * 
 */
package com.deppon.crm.module.customer.server.testutils;

import org.junit.Test;

import junit.framework.TestCase;

import com.deppon.crm.module.customer.server.utils.Assert;

/**
 * @author Administrator
 *
 */
public class AssertTest extends TestCase{
	Assert ass = new Assert();
	/**
	 * 
	 @Discript 为空的方法测试
	 @author  唐亮
	 @date 2012-12-25
	 @return void
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testIsNUll(){
		
		//输入：非空字符串       输出：IllegalArgumentException
		String object = "12";
		String message = "你好";
		try{
			ass.isNull(object, message);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//输入：空字符串     输出：无
		object = null;
		ass.isNull(object, message);
	}
	/**
	 * 
	 @Discript 不为空方法测试
	 @author  唐亮
	 @date 2012-12-25
	 @return void
	 */
	@SuppressWarnings("static-access")
	public  void testNotNull(){
		//输入：非空字符串     输出：无
		String object = "12";
		String message = "你好";
		ass.notNull(object, message);
		//输入：空字符串     输出：
		object = null;
		try{			
			ass.notNull(object, message);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 
	 @Discript notEmpty方法测试
	 @author  唐亮
	 @date 2012-12-25
	 @return void
	 */
	@SuppressWarnings("static-access")
	public void testNotEmpty(){
		//输入：非空字符串    输出：无
		String object = "12";
		String message = "你好";
		ass.notEmpty(object, message);
		//输入：空字符串    输出：IllegalArgumentException
		object = null;
		try {
			ass.notEmpty(object, message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
