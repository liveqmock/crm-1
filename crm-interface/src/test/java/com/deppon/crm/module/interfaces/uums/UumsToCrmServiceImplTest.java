package com.deppon.crm.module.interfaces.uums;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.deppon.crm.module.interfaces.uums.ws.CompanyInfo;
import com.deppon.crm.module.interfaces.uums.ws.ESBHeader;
import com.deppon.crm.module.interfaces.uums.ws.SendCompanyInfoRequest;
import com.deppon.crm.module.interfaces.uums.ws.SendCompanyInfoResponse;
import com.deppon.crm.module.interfaces.uums.ws.SyncCompanyInfosFault;

public class UumsToCrmServiceImplTest {

	@Test
	public void testSyncCompanyInfos() {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://10.224.64.23:8088/crm/ws/uumsToCrmService");
		UumsToCrmService uumsToCrmService = factoryBean.create(UumsToCrmService.class);
		SendCompanyInfoRequest syncCompanyInfosRequest = new SendCompanyInfoRequest();
		List<CompanyInfo> companyInfos = new ArrayList<CompanyInfo>();
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompanyCode("小文");
		companyInfos.add(companyInfo);
		syncCompanyInfosRequest.getCompanyInfo().addAll(companyInfos);
		
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("小文");
		Holder<ESBHeader> esbHeaders = new Holder<ESBHeader>(esbHeader);
		
		try {
			SendCompanyInfoResponse sendCompanyInfoResponse
				= uumsToCrmService.syncCompanyInfos(syncCompanyInfosRequest, esbHeaders);
		} catch (SyncCompanyInfosFault e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testT(){
		System.out.println(1 + 'a' + "a" + 'a');
	}
}
