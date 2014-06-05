package com.deppon.crm.module.interfaces.channel;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.interfaces.channel.domain.AuthInfo;
import com.deppon.crm.module.interfaces.channel.domain.CommonException;
import com.deppon.crm.module.interfaces.channel.domain.ESBHeader;
import com.deppon.crm.module.interfaces.channel.domain.StatusInfo;
import com.deppon.crm.module.interfaces.channel.domain.StatusList;
import com.deppon.crm.module.interfaces.channel.domain.UpdateWayBillNumRequest;
import com.deppon.crm.module.interfaces.channel.domain.UpdateWayBillNumResponse;

public class ChaneelUpdateOrderWaybillTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChannelUpdateWayBillNum() {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://10.224.64.23:8088/crm/ws/channelService");
		ChannelService channelService = factoryBean.create(ChannelService.class);
		UpdateWayBillNumRequest request = new UpdateWayBillNumRequest();
		request.setChannelNumber("9090");
		request.setWaybilNumber("0909");
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("1");
		esbHeader.setTargetSystem("1");
		StatusInfo statusInfo = new StatusInfo();
		statusInfo.setStatusId("1");
		statusInfo.setTimeStamp(System.currentTimeMillis());
		List<StatusInfo> liststatusInfos = new ArrayList<StatusInfo>();
		StatusList statusList = new StatusList();
		statusList.getStatusInfo().addAll(liststatusInfos);
		esbHeader.setStatusList(statusList);
		esbHeader.setSourceSystem("1");
		esbHeader.setSentSequence(1);
		esbHeader.setResultCode(1);
		esbHeader.setResponseId("1");
		esbHeader.setRequestId("1");
		esbHeader.setMessageFormat("1");
		esbHeader.setExchangePattern(1);
		esbHeader.setEsbServiceCode("1");
		esbHeader.setBusinessId("1");
		esbHeader.setBusinessDesc3("2");
		esbHeader.setBusinessDesc2("3");
		esbHeader.setBusinessDesc1("33");
		esbHeader.setBackServiceCode("xiaowen");
		AuthInfo authInfo = new AuthInfo();
		authInfo.setPassword("dd");
		authInfo.setUsername("dd");
		esbHeader.setAuthentication(authInfo);
		Holder<ESBHeader> esbHeaders = new Holder<ESBHeader>(esbHeader);
			try {
				UpdateWayBillNumResponse updateWayBillNumResponse
					= channelService.channelUpdateWayBillNum(request, esbHeaders);
				System.out.println("result: " + updateWayBillNumResponse.isResult());
			} catch (CommonException e) {
				e.printStackTrace();
			}
	}

}
