package com.deppon.crm.module.complaint.manager;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.complaint.server.manager.ComplaintValidate;
import com.deppon.crm.module.complaint.shared.domain.Complaint;

public class ComplaintValidateTest extends TestCase{
	private ComplaintValidate cv=null;
	@Before
	public void setUp() throws Exception {
		cv=new ComplaintValidate();
	}

	@Test
	public void testIsSameOwNumber() {
		Complaint complaint=new Complaint();
		complaint.setBill("53621");
		String temp="53621";
		cv.isSameOwNumber(temp,complaint);
		complaint.setBill("53621");
		try{
			cv.isSameOwNumber("323",complaint);
			fail();
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testIsSameComplaitType() {
		Complaint complaint=new Complaint();
	}

}
