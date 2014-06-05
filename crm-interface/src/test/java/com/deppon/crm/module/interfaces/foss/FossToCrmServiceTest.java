package com.deppon.crm.module.interfaces.foss;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.DataFormatUtil;
import com.deppon.crm.module.interfaces.foss.domain.AmountInfo;
import com.deppon.crm.module.interfaces.foss.domain.BackFreightCheckRequest;
import com.deppon.crm.module.interfaces.foss.domain.CommException;
import com.deppon.crm.module.interfaces.foss.domain.CouponWaybillInfo;
import com.deppon.crm.module.interfaces.foss.domain.ESBHeader;
import com.deppon.crm.module.interfaces.foss.domain.QueryClaimbillRequest;
import com.deppon.crm.module.interfaces.foss.domain.QueryClaimbillResponse;
import com.deppon.crm.module.interfaces.foss.domain.QueryOrderListRequest;
import com.deppon.crm.module.interfaces.foss.domain.QueryOrderListResponse;
import com.deppon.crm.module.interfaces.foss.domain.SearchOrderRequest;
import com.deppon.crm.module.interfaces.foss.domain.SearchOrderResponse;
import com.deppon.crm.module.interfaces.foss.domain.ValidateCouponRequest;
import com.deppon.crm.module.interfaces.foss.domain.ValidateCouponResponse;
import com.sun.mail.iap.Response;

public class FossToCrmServiceTest {
	FossToCrmService fossToCrmService;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(FossToCrmService.class);
		jcf.setAddress("http://192.168.67.71:8180/esb2/ws/foss2crm/fossToCrmService");
		jcf.getOutInterceptors()
				.add(new com.deppon.crm.module.client.esb.trans.SoapMessageOutInterceptor());
		fossToCrmService = (FossToCrmService) jcf.create();
	}


	@Test
	public void testQueryClaimbill(){
		try {
			QueryClaimbillRequest request = new QueryClaimbillRequest();
			request.setWaybillNum("54559549");
			ESBHeader esbHeader = new ESBHeader();
			esbHeader.setBusinessId(request.getWaybillNum());
			Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
			QueryClaimbillResponse response = fossToCrmService.queryClaimbill(
					request, holder);
			System.out.println(response.getWaybillNum()+response.getCustName());
		} catch (CommException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchOrder() {
		try {
			SearchOrderRequest request = new SearchOrderRequest();
			request.setOrderNumber("W130928728212");
			ESBHeader esbHeader = new ESBHeader();
			esbHeader.setEsbServiceCode(Constant.IMPORT);
			esbHeader.setBusinessId(request.getOrderNumber());
			Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
			SearchOrderResponse response = fossToCrmService.searchOrder(
					request, holder);
			System.out.println(response.getOrder().getAcceptDept());
		} catch (CommException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryOrder() {
		QueryOrderListRequest queryOrderListRequest = new QueryOrderListRequest();
		/*queryOrderListRequest.setBeginTime(DataFormatUtil.parseDate(
				"2013-08-01", "yyyy-MM-dd"));*/
		//queryOrderListRequest.setEndTime(new Date());
		queryOrderListRequest.setOrderNumber("W130928728212");
		//queryOrderListRequest.setTransType("JZCY");
		// queryOrderListRequest.setPageNum(1);
		// queryOrderListRequest.setPageSize(10);
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessDesc1(queryOrderListRequest.getOrderNumber());
		esbHeader.setBusinessId(queryOrderListRequest.getOrderNumber());
		esbHeader.setEsbServiceCode(Constant.QUERYORDER);
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		try {
			QueryOrderListResponse response = fossToCrmService.queryOrder(
					queryOrderListRequest, holder);
			System.out.println(response.getTotalCount());
		} catch (CommException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBackFreightCheck(){
		BackFreightCheckRequest request = new BackFreightCheckRequest ();
		request.setWaybillNum("111111");
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessDesc1(request.getWaybillNum());
		esbHeader.setBusinessId(request.getWaybillNum());
		esbHeader.setEsbServiceCode(Constant.BACK_FREIGHT);
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		try {
			for (int i = 0; i < 10; i++) {
				fossToCrmService.backFreightCheck(request, holder);
			}
		} catch (CommException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidateCoupon(){
		ValidateCouponRequest request = new ValidateCouponRequest();
		request.setCouponNumber("140430504771217");
		CouponWaybillInfo waybillInfo=new CouponWaybillInfo();
		waybillInfo.setLeaveDept("DP02076");
		waybillInfo.setCustNumber("400077441");
		waybillInfo.setArrivedDept("DP00421");
		waybillInfo.setWaybillNumber("201441002");
		waybillInfo.setTotalAmount(new BigDecimal(70));
		waybillInfo.setLeaveMobile("11111111111");
		List<AmountInfo> amounts=new ArrayList<AmountInfo>();
		AmountInfo amount=new AmountInfo();
		amount.setValuationCode("BF");
		amount.setValuationAmonut(new BigDecimal(11));
		AmountInfo amount1=new AmountInfo();
		amount1.setValuationCode("FRT");
		amount1.setValuationAmonut(new BigDecimal(55));
		AmountInfo amount2=new AmountInfo();
		amount2.setValuationCode("ZHXX");
		amount2.setValuationAmonut(new BigDecimal(11.0));
		AmountInfo amount3=new AmountInfo();
		amount3.setValuationCode("RYFJ");
		amount3.setValuationAmonut(new BigDecimal(11.0));
		amounts.add(amount);
		amounts.add(amount1);
		amounts.add(amount2);
		amounts.add(amount3);
		waybillInfo.getAmountList().addAll(amounts);
		waybillInfo.setLeaveOutDept("DP07290");
		waybillInfo.setProduceType("FLF");
		waybillInfo.setOrderSource("BUSINESS_DEPT");
		waybillInfo.setLeavePhone("85753866");
		waybillInfo.setArrivedOutDept("DP11496");
		waybillInfo.setProduceType("LRF");
		request.setWaybillInfo(waybillInfo);
		request.setIsUsed(false);
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setTargetSystem("CRM");
		esbHeader.setResultCode(1);
		esbHeader.setBusinessId("ORDER");
		//esbHeader.setResponseId("20f67032-9423-4ba1-9878-8b0ff0f01183");
		esbHeader.setSourceSystem("FOSS");
		esbHeader.setEsbServiceCode("ESB_FOSS2ESB_VALIDATE_COUPON");
		//esbHeader.setBackServiceCode("CRM_ESB2CRM_VALIDATE_COUPON");
		esbHeader.setMessageFormat("SOAP");
		esbHeader.setExchangePattern(1);
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		ValidateCouponResponse response=null;
		try {
			 response=fossToCrmService.validateCoupon(request, holder);
		} catch (CommException e) {
			e.printStackTrace();
		}
	}

}
