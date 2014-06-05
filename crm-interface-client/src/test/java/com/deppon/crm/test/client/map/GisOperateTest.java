package com.deppon.crm.test.client.map;

import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean; 
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.esb.esbtogis.CommonException;
import com.deppon.esb.esbtogis.ESBHeader;
import com.deppon.esb.esbtogis.GisService;
import com.deppon.esb.esbtogis.QueryDeptCoordinateRequest;
import com.deppon.esb.esbtogis.QueryDeptCoordinateResponse;

public class GisOperateTest extends OperaterTest{

	
	GisService gisService;
	
	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(GisService.class);
		// jcf.setAddress("http://localhost:8080/jaxwebservice");
		jcf.setAddress("http://192.168.17.141:17080/esb2/ws/crm2gis/gisService");
		//jcf.setAddress("http://192.168.17.141:18080/esb2/ws/crm2gis/gisService");
		jcf.getOutInterceptors()
				.add(new com.deppon.crm.module.client.esb.trans.SoapMessageOutInterceptor());
		gisService = (GisService) jcf.create();
		
		
	}
	@Test 
	public void testQueryCoordinate() throws CrmBusinessException{ 
		try { 
			QueryDeptCoordinateRequest request = new QueryDeptCoordinateRequest();
			ESBHeader esbHeader = new ESBHeader();
			esbHeader.setVersion("0.1");
			esbHeader.setRequestId(UUID.randomUUID().toString());
			esbHeader.setSourceSystem("CRM");
			esbHeader.setBusinessId("ddd");
			esbHeader.setExchangePattern(1);
			esbHeader.setMessageFormat("SOAP");
			esbHeader.setEsbServiceCode("ESB_CRM2ESB_QUERY_DPETCOORDINATES");
			request.getDeptCode().add("DP01112");
			request.getDeptCode().add("DP01111");
			Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
			QueryDeptCoordinateResponse response = gisService.queryCoordinate(request,holder);
			System.out.println(response.getCoordinateList().get(0).getAddress());
			Assert.notNull(response);
		} 
		catch(CommonException e){
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getFaultInfo().getDetailedInfo());
		}
		catch (Exception e) {
			e.printStackTrace();   
		}   
	}    
	
//	@Test
//	public void bindComplaintId2ServiceNumberTest(){
//		try {
//			QueryDeptCoordinateRequest request=new QueryDeptCoordinateRequest();
//			request.getDeptCode().add("DP01112");
//			request.getDeptCode().add("DP01111");
//			QueryDeptCoordinateResponse response= (QueryDeptCoordinateResponse) gisOperate.queryCoordinate(request.getDeptCode());
//			int count=response.getCoordinateList().size();
//			System.out.println(count);
//		} catch (CrmBusinessException e) {
//			e.printStackTrace();
//		}
//	}
	
	
}
