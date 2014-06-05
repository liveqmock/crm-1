package com.deppon.crm.module.customer.server.testutils;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class NoEqluesMatcher<T> extends BaseMatcher<T>{
	
	private String value ;
	
	public NoEqluesMatcher(String value) {
		this.value = value;
	}
	
	@Override
	public boolean matches(Object item) {
		String value = (String) item;  
        if (value == null)  
            return true;  
        return !value.equals(this.value);
	}
	@Override
	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		
	}

}
