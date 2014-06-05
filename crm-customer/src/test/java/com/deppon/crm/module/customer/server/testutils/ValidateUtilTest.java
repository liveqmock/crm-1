package com.deppon.crm.module.customer.server.testutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Member;

public class ValidateUtilTest {
	
	@Test
	public void test_isSame(){
		
		Assert.assertTrue(ValidateUtil.isSame(null, null));
		
		Assert.assertFalse(ValidateUtil.isSame("", "1234"));
		
		Assert.assertTrue(ValidateUtil.isSame("1234", "1234"));
		
		Assert.assertFalse(ValidateUtil.isSame("1234", ""));
		
	}
	
	@Test
	public void test_objectIsEmpty(){
		
		Assert.assertTrue(ValidateUtil.objectIsEmpty(null));
		
		Assert.assertTrue(ValidateUtil.objectIsEmpty(""));
		
		Assert.assertTrue(ValidateUtil.objectIsEmpty(new HashMap<String,String>()));
	
		Assert.assertTrue(ValidateUtil.objectIsEmpty(new ArrayList<String>()));
	
		Assert.assertTrue(ValidateUtil.objectIsEmpty(new TreeSet<String>()));
	
		Assert.assertFalse(ValidateUtil.objectIsEmpty(new Member()));
	
	}

}
