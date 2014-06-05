package com.deppon.crm.module.recompense.server.ws;

public class OAClientFactory {
	public static OAClient oa = new OAClientMock();
	
	
	public static OAClient getOAClient(){
		return oa;
	}
}
