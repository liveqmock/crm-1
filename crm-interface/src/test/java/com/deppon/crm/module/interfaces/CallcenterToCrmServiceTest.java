/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CallcenterToCrmServiceTest.java
 * @package com.deppon.crm.module.interfaces
 * @author 105681
 * @version 0.1 2014-4-16
 */
package com.deppon.crm.module.interfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.cc.ICallcenterToCrmService;
import com.deppon.crm.module.interfaces.cc.domain.BusinessOpportunityIfexistRequest;
import com.deppon.crm.module.interfaces.cc.domain.BusinessOpportunityIfexistResponse;
import com.deppon.crm.module.interfaces.cc.domain.CommException;
import com.deppon.crm.module.interfaces.cc.domain.CreateCallCenterCustomerRequest;
import com.deppon.crm.module.interfaces.cc.domain.CreateCallCenterCustomerResponse;
import com.deppon.crm.module.interfaces.cc.domain.ESBHeader;
import com.deppon.crm.module.interfaces.cc.domain.LinkmanInfoCreate;
import com.deppon.crm.module.interfaces.cc.domain.LinkmanInfoUpdate;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoAddRequest;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoAddResponse;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryDetailRequest;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryDetailResponse;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryRequest;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryResponse;
import com.deppon.crm.module.interfaces.cc.domain.RecallPlanRequest;
import com.deppon.crm.module.interfaces.cc.domain.RecallPlanResponse;
import com.deppon.crm.module.interfaces.cc.domain.UpdateCallCenterCustomerRequest;
import com.deppon.crm.module.interfaces.cc.domain.UpdateCallCenterCustomerResponse;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CallcenterToCrmServiceTest.java
 * @package com.deppon.crm.module.interfaces
 * @author 105681
 * @version 0.1 2014-4-16
 */

public class CallcenterToCrmServiceTest{

	private ICallcenterToCrmService service;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		//jax.setAddress("http://10.224.64.19:8088/crm/ws/callcenterToCrmService?wsdl");
		//jax.setAddress("http://192.168.67.71:8180/esb2/ws/ccs2crm/ccs2CrmService");
		jax.setAddress("http://localhost:8088/crm/ws/callcenterToCrmService?wsdl");
		jax.setServiceClass(ICallcenterToCrmService.class);
		service = (ICallcenterToCrmService) jax.create();

	}

	/**
	 * @throws CommException
	 * @throws TestMethodFault
	 * @作者：罗典
	 * @时间：2012-2-25
	 * @描述：合同审批数据结果返回
	 * */
	@org.junit.Test
	public void test() throws CrmBusinessException, CommException {
		MarketingInfoAddRequest request=new MarketingInfoAddRequest();
		request.setCellphone("13524455216");
		request.setCustName("wu");
		request.setCustNumber("215454");
		request.setMarketDept("DP23562");
		request.setMarketPerson("150256");
		request.setNeedQuestion("dkf");
		request.setNeedType("dkl");
		request.setSolution("dkfkld");
		request.setTelephone("559405");
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_SEND_MARKETINGINFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		MarketingInfoAddResponse response=	service.sendMarketingInfo(holder, request);
        System.out.println("dfdklfjkdl");
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-16
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */

	@org.junit.Test
	public void eyi()
			throws CommException {
		//for (int i = 0; i < 6; i++) {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_QUERY_MARKETINGINFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		MarketingInfoQueryRequest request=new MarketingInfoQueryRequest();
		request.setCustNumber("400462934");
		request.setStart(0);
		request.setLimit(10);

			MarketingInfoQueryResponse response = service.queryMarketingInfo(
					holder, request);
		//}
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-16
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */
	@Test
	public void sendMarketingInfo()
			throws CommException {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_SEND_MARKETINGINFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		MarketingInfoAddRequest request=new MarketingInfoAddRequest();
		request.setCustNumber("40001997");
		request.setCustName("苏玉军");
		request.setCellphone("13524455217");
		request.setNeedType("EFFICIENCY");
		request.setNeedQuestion("贵吗");
		request.setSolution("贵");
		request.setMarketPerson("084544");
		request.setMarketDept("DP02076");
		request.setMarketTime(new Date());
		MarketingInfoAddResponse response=service.sendMarketingInfo(holder, request);

	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-16
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */
	@Test
	public void updateCustomerInfo()
			throws CommException {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_UPDATE_CUSTOMERINFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		UpdateCallCenterCustomerRequest request=new UpdateCallCenterCustomerRequest();
		request.setCustName("拥抱大海");
		request.setCustType("PERSONAL");
		request.setCustNumber("400463897");
		request.setCustCategory(null);
		request.setCustDegree("NORMAL");
		request.setCreateTime(new Date(1397807856000l));
		request.setLastUpdateTime(new Date(1397807856000l));
		request.setIfBigCustomer(false);
		request.setStdeptcode("DP09788");
		request.setIfvalid(true);
		request.setExternalSysID("crm2cc400463897");
		request.setPickAddress(null);
		List<LinkmanInfoUpdate> linkmanInfos=new ArrayList<LinkmanInfoUpdate>();
		LinkmanInfoUpdate link=new LinkmanInfoUpdate();
		link.setName("拥抱大海");
		link.setCellphone("14716016016");
		LinkmanInfoUpdate link2=new LinkmanInfoUpdate();
		link2.setName("谭打开");
		link2.setCellphone("13826836818");
		linkmanInfos.add(link);
		linkmanInfos.add(link2);
		request.getLinkmanInfos().addAll(linkmanInfos);
		UpdateCallCenterCustomerResponse response=service.updateCustomerInfo(holder, request);


	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-16
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 * 40003164问卷   客户需求408788867  发货潜力414498109
	 */
	@Test
	public void queryMarketingDetailInfo() throws CommException {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_QUERY_MARKETINGDETAILINFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		MarketingInfoQueryDetailRequest request=new MarketingInfoQueryDetailRequest();
        //request.setCustId("5425");
        request.setRecallId("40003164");
        MarketingInfoQueryDetailResponse response=service.queryMarketingDetailInfo(holder, request);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-16
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 * 40001994
		*40002025
		*40002028
		*40001995
		*40001996
		*40001997
	 */
	@Test
	public void sendRecallPlan() throws CommException {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_SEND_RECALLPLAN");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		RecallPlanRequest request=new RecallPlanRequest();
		request.setCustNumber("40001997");
		request.setPlanSubject("top1");
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime(new Date());
		end.setTime(new Date());

		start.add(Calendar.DATE, +1);
		end.add(Calendar.DATE, +15);
		request.setBeginTime(start.getTime());
		request.setEndTime(end.getTime());
		request.setExecuteDept("DP02076");
		request.setCreator("084544");
		request.setRemark("dd");
		RecallPlanResponse response=service.sendRecallPlan(holder, request);

	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-16
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */
	@Test
	public void createCustomerInfo()
			throws CommException {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_CREATE_CUSTOMERINFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		CreateCallCenterCustomerRequest request=new CreateCallCenterCustomerRequest();
		request.setCustName("王国宇");
		request.setCustType("PERSONAL");
		request.setIfvalid(true);
		request.setExternalSysID("cc0004730049");
		List<LinkmanInfoCreate> linkmanInfos =new ArrayList<LinkmanInfoCreate>();
		LinkmanInfoCreate linkMan=new LinkmanInfoCreate();
		linkMan.setCellphone("13900000217");
		linkmanInfos.add(linkMan);
		request.getLinkmanInfos().addAll(linkmanInfos);
		CreateCallCenterCustomerResponse response=service.createCustomerInfo(holder, request);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-16
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */

	public BusinessOpportunityIfexistResponse validateBusinessOpportunity(
			Holder<ESBHeader> esbHeader,
			BusinessOpportunityIfexistRequest request) throws CommException {
		// TODO Auto-generated method stub
		return null;
	}

}
