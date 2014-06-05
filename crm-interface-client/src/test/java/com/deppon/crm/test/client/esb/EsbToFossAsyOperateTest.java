package com.deppon.crm.test.client.esb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.esb.IEsbToFossAsyOperate;
import com.deppon.crm.module.client.esb.domain.BankPayInfo;
import com.deppon.crm.module.client.esb.domain.ClaimsPayBillGenerateRequest;
import com.deppon.crm.module.client.esb.domain.DevelopDeptInfos;
import com.deppon.crm.module.client.esb.domain.DiscountInfo;
import com.deppon.crm.module.client.esb.domain.GoodsLine;
import com.deppon.crm.module.client.esb.domain.MarketingActivityRequest;
import com.deppon.crm.module.client.esb.domain.OrigCustSyncRequest;
import com.deppon.crm.module.client.esb.domain.ResponsibilityInfo;
import com.deppon.crm.module.client.esb.domain.ScatterCustBankInfo;
import com.deppon.crm.module.client.esb.domain.ScatterCustInfo;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.crm.test.client.util.SpringHelper;
import com.deppon.foss.crm.CustomerService;

public class EsbToFossAsyOperateTest {

	protected static IEsbToFossAsyOperate esbToFossAsyOperate;
	private static ApplicationContext context;
	@Before
	public void setUp() {
		if (context==null) {
			synchronized (SpringHelper.class) {
				if (context==null) {
					context =
						new ClassPathXmlApplicationContext(
								"com/deppon/crm/module/client/server/META-INF/spring-test.xml");
				}
			}
		}
		esbToFossAsyOperate=(IEsbToFossAsyOperate)context.getBean("esbToFossAsyOperate");
	}

	@Test
	public void synMarketingActivityTest() throws CrmBusinessException{
		MarketingActivityRequest request=new MarketingActivityRequest();
		request.setFid(new BigDecimal(1));
		request.setActivityName("activity");
		request.setActivityCode("code");
		request.setFstatus("Y");
		DiscountInfo disInfo=new DiscountInfo();
		disInfo.setFid(new BigDecimal(1));
		disInfo.setPreferentialProduct("JH");
		disInfo.setPriceDiscount("0.2");
		request.getDiscountInfos().add(disInfo);
		request.setActivityBeginTime(new Date());
		request.setActivityEndTime(new Date(0));
		request.getFirstTrades().add("ITRONIC_FURNITURE");
		request.getSecondTrades().add("ITRONIC_FURNITURE");
		request.setActivityCategory("EXPRESS");
		request.setActivityType("NATIONWIDE");
		request.getProductType().add("FSF");
		request.getGoodsName().add("goods1");
		request.setBillMinMoney("1");
		request.setBillMaxMoney("2");
		request.setGoodsMinWeight("1");
		request.setGoodsMaxWeight("1");
		request.setGoodsMinVolume("1");
		request.setGoodsMaxVolume("1");
		request.getOrderResource().add("ALIBABA");
		DevelopDeptInfos deptInfo=new DevelopDeptInfos();
		deptInfo.setFid(new BigDecimal(1));
		deptInfo.setSponsorDepts("49311");
		request.getDevelopDeptInfos().add(deptInfo);
		GoodsLine goodsLine=new GoodsLine();
		goodsLine.setFid(new BigDecimal(1));
		goodsLine.setArrivalArea("1");
		goodsLine.setArrivalOutfield("1");
		goodsLine.setStartingArea("1");
		goodsLine.setStartingOutfield("1");
		esbToFossAsyOperate.synMarketingActivity(request);
	}

	@Test
	public void customerInfoEtcTest() {
		OrigCustSyncRequest origCustSyncRequest = new OrigCustSyncRequest();
		ScatterCustInfo scatterCustInfo = new ScatterCustInfo();
		scatterCustInfo.setFscatterid("2");
		scatterCustInfo.setFscattertype("2");
		scatterCustInfo.setFprocredit(new BigDecimal(1));
		scatterCustInfo.setOperateType(2);
		scatterCustInfo.setCustName("1");
		scatterCustInfo.setCustCode("1");
		scatterCustInfo.setCustNumber("1");
		scatterCustInfo.setContactName("1");
		scatterCustInfo.setMobilePhone("1");
		scatterCustInfo.setTelephone("1");
		scatterCustInfo.setContactAddress("1");
		scatterCustInfo.setCustStatus(1);
		scatterCustInfo.setLastModifyTime(new Date());
		ScatterCustBankInfo scatterCustBankInfo = new ScatterCustBankInfo();
		scatterCustBankInfo.setFsubbankname("1");
		scatterCustBankInfo.setFbankaccount("1");
		scatterCustBankInfo.setStatus("1");
		scatterCustBankInfo.setFcountname("1");
		scatterCustBankInfo.setBankcityCode("1");
		scatterCustBankInfo.setBankCityName("1");
		scatterCustBankInfo.setBankProvinceCode("1");
		scatterCustBankInfo.setBankProvinceName("1");
		scatterCustBankInfo.setBankCode("1");
		scatterCustBankInfo.setBankName("1");
		scatterCustBankInfo.setFlinkmanmobile("1");
		scatterCustBankInfo.setFrelation("1");
		scatterCustBankInfo.setSubbankCode("1");
		scatterCustBankInfo.setStatus("1");
		scatterCustBankInfo.setSubbankName("1");
		scatterCustBankInfo.setFdesciption("1");
		scatterCustBankInfo.setFisdefaultaccount(true);
		scatterCustBankInfo.setLastModifyTime(new Date());
		scatterCustBankInfo.setFid(new BigInteger("1"));
		scatterCustInfo.getFbank().add(scatterCustBankInfo);
		origCustSyncRequest.setScatterCustInfo(scatterCustInfo);
		try {
			esbToFossAsyOperate.customerInfoEtc(origCustSyncRequest);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
}
