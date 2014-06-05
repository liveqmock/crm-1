package com.deppon.crm.test.client.common;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.deppon.crm.module.client.esb.domain.OrigCustSyncRequest;
import com.deppon.crm.module.client.esb.domain.ScatterCustBankInfo;
import com.deppon.crm.module.client.esb.domain.ScatterCustInfo;
import com.deppon.crm.test.client.esb.domain.ObjectFactory;

public class OrigCustSyncRequestTrans {

	private static Log log = LogFactory.getLog(OrigCustSyncRequestTrans.class);

	private static final Class<OrigCustSyncRequest> clzz = OrigCustSyncRequest.class;
	private static JAXBContext context = initContext();

	public static OrigCustSyncRequest toMessage(String string) throws Exception {
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(
					string.getBytes());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<OrigCustSyncRequest> element = unmarshaller.unmarshal(
					new StreamSource(stream), clzz);
			return element.getValue();
		} catch (JAXBException e) {
			throw new Exception("反序列化" + clzz.getName() + "时失败！", e);
		}
	}

	public static String fromMessage(OrigCustSyncRequest value)
			throws Exception {
		if (value == null) {
			return null;
		}
		try {
			StringWriter stringWriter = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING,
					"UTF-8");
			JAXBElement<OrigCustSyncRequest> element = new ObjectFactory()
					.createOrigCustSyncRequest(value);
			marshaller.marshal(element, stringWriter);
			stringWriter.flush();
			return stringWriter.toString();
		} catch (PropertyException e) {
			throw new Exception("序列化" + value.getClass().getName() + "时失败！", e);
		} catch (FactoryConfigurationError e) {
			throw new Exception("序列化" + value.getClass().getName() + "时失败！", e);
		} catch (JAXBException e) {
			throw new Exception("序列化" + value.getClass().getName() + "时失败！", e);
		}
	}

	private static JAXBContext initContext() {
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(clzz);
		} catch (JAXBException e) {
			log.error(e.getMessage(), e);
		}
		return context;
	}
	
	@Test
	public void test() throws Exception{
		OrigCustSyncRequest origCustSyncRequest = new OrigCustSyncRequest();
		ScatterCustInfo scatterCustInfo = new ScatterCustInfo();
		scatterCustInfo.setFscatterid("1");
		scatterCustInfo.setFscattertype("1");
		scatterCustInfo.setFprocredit(new BigDecimal(1));
		scatterCustInfo.setOperateType(1);
		scatterCustInfo.setCustName("1");
		scatterCustInfo.setCustCode("1");
		scatterCustInfo.setCustNumber("1");
		scatterCustInfo.setContactName("1");
		scatterCustInfo.setMobilePhone("1");
		scatterCustInfo.setTelephone("1");
		scatterCustInfo.setContactAddress("1");
		ScatterCustBankInfo scatterCustBankInfo = new ScatterCustBankInfo();
		scatterCustBankInfo.setFsubbankname("1");
		scatterCustBankInfo.setFbankaccount("1");
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
		scatterCustBankInfo.setSubbankName("1");
		scatterCustBankInfo.setFdesciption("1");
		scatterCustBankInfo.setFisdefaultaccount(true);

		scatterCustInfo.getFbank().add(scatterCustBankInfo);
		scatterCustInfo.setCustStatus(1);
		origCustSyncRequest.setScatterCustInfo(scatterCustInfo);
		String result = OrigCustSyncRequestTrans.fromMessage(origCustSyncRequest);
		log.info(result);
	}
}
