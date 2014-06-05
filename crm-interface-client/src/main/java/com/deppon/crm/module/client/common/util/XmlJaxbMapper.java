package com.deppon.crm.module.client.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;

public class XmlJaxbMapper {
	private static Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String writeValue(Object obj, QName qName)
			throws CrmBusinessException {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = getJaxbContext(obj.getClass());
			JAXBElement element = new JAXBElement(qName, obj.getClass(), null,
					obj);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(element, writer);
			writer.flush();
			return writer.getBuffer().toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new CrmBusinessException(e.getMessage());
		}
	}

	public static Object readValue(String xmlString, Class clazz)
			throws CrmBusinessException, UnsupportedEncodingException {
		StringReader reader = new StringReader(xmlString);
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(
					xmlString.getBytes("UTF-8"));
			JAXBContext jaxbContext = getJaxbContext(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement element = jaxbUnmarshaller.unmarshal(new StreamSource(
					stream), clazz);
//			Object obj = jaxbUnmarshaller.unmarshal(reader);
			return element.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new CrmBusinessException(e.getMessage());
		}
	}

	private static JAXBContext getJaxbContext(Class clazz) throws JAXBException {
		String className = clazz.getSimpleName();
		JAXBContext jaxbContext = null;
		if (jaxbContextMap.containsKey(className)) {
			return jaxbContextMap.get(className);
		} else {
			jaxbContext = JAXBContext.newInstance(clazz);
			jaxbContextMap.put(className, jaxbContext);
		}
		return jaxbContext;
	}
}
