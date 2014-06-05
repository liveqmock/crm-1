package com.deppon.crm.module.interfaces.foss;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.Test;
import org.w3c.dom.Document;

import com.deppon.crm.module.interfaces.foss.impl.FossToCrmServiceImpl;

public class ServerTest {
	public static void main(String[] args) {
		FossToCrmService hw = new FossToCrmServiceImpl();
		JaxWsServerFactoryBean jsf = new JaxWsServerFactoryBean();
		jsf.setServiceClass(FossToCrmService.class);
		jsf.setAddress("http://localhost:8080/jaxwebservice");
		jsf.setServiceBean(hw);
		jsf.create();
	}
	
	@Test
	public void test()throws Exception{
		String url = "http://192.168.17.141:18080/esb2/ws/foss2crm/fossToCrmService?wsdl";
		String portname_="fossToCrmServicePort";
		String serviceName_="FossToCrmServiceService";
		String targetNS ="http://www.deppon.com/crm/interface/CrmService";
		QName portName = new QName(targetNS,portname_);
		QName serviceName = new QName(targetNS,serviceName_);
		URL crmURL = new URL(url);
		Service service = Service.create(crmURL, serviceName);
		  Dispatch<Source> dispath = service.createDispatch(portName, Source.class,Service.Mode.PAYLOAD);
		    Source source = new DOMSource();
		    Document doc = DOMUtils.createDocument();
		    //doc.createElement("");
		    Source response = dispath.invoke(source);
	}
}
