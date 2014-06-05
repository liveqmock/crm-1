package com.deppon.cc;

import org.junit.Test;

import com.deppon.cc.complaint.ReceiveWorkOrderIdService;
import com.deppon.cc.complaint.ReceiveWorkOrderIdServicePortType;

public class ReceiveWorkOrderIdServicePortTypeTest {

	@Test
	public void test1(){
		ReceiveWorkOrderIdServicePortType port = new ReceiveWorkOrderIdService().getReceiveWorkOrderIdServiceHttpPort();
		port.receiveWorkOrderNumber(":", ":");
	}
}
