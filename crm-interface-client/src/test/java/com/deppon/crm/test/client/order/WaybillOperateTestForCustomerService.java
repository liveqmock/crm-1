package com.deppon.crm.test.client.order;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.foss.crm.CommonException;
import com.deppon.foss.crm.CustomerService;
import com.deppon.foss.crm.ESBHeader;
import com.deppon.foss.crm.FossQueryAcctinfoRequest;
import com.deppon.foss.crm.FossQueryAcctinfoResponse;

public class WaybillOperateTestForCustomerService{

	CustomerService customerService;
	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.setAddress("http://192.168.17.141:17080/esb2/ws/crm2foss/customerService");
		jax.setServiceClass(CustomerService.class);
		customerService = (CustomerService) jax.create();
	}
	/**
	 *
	 * <p>
	 * Description:运单查询接口测试<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-21
	 * @throws Exception
	 * void
	 */
	@Test
	public void fossQueryAcctinfoTest() throws Exception {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CRM2ESB_QUERY_ACCT_INFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		FossQueryAcctinfoRequest request = new FossQueryAcctinfoRequest();
		request.setDeliveryCustomerCode("400463892");
//		Calendar start = Calendar.getInstance();
//		Calendar end = Calendar.getInstance();
		/*start.setTime(new Date());
		end.setTime(new Date());*/
		/*start.add(Calendar.DATE, +1);
		end.add(Calendar.DATE, +15);*/
//		start.set(2014,5, 27);
//		end.set(2014, 5, 29);
		System.out.println(new Date(1400860800000l).toString());
		System.out.println(new Date(1401292800000l).toString());
		request.setStartDate(new Date(1400860800000l));
		request.setEndDate(new Date(1401292800000l));
		request.setCurrentPage(1);
		request.setPageSize(20);
		FossQueryAcctinfoResponse response = customerService.fossQueryAcctinfo(
				holder, request);
	}

}
