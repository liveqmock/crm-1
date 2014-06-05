//package com.deppon.crm.module.client.common;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.namespace.QName;
//import javax.xml.stream.XMLOutputFactory;
//import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.XMLStreamWriter;
//
//import com.deppon.crm.module.client.common.domain.InterfaceConfig;
//import com.deppon.crm.module.client.common.domain.MethodConfig;
//import com.deppon.crm.module.client.common.util.Constant;
//
///**
// * 文件序列化接口详细信息
// * 
// * @author davidcun @2012-4-10
// */
//public class FileInterfaceSerialize extends ConfigFileLoad implements InterfaceSerialize {
//
//	private static String INTERFACES_PATH = Constant.INTERFACE_DEFAULT_FILE_PATH;
//
//	public FileInterfaceSerialize() {
//		super(INTERFACES_PATH);
//	}
//
//	public FileInterfaceSerialize(String fileClassPath) {
//		super(fileClassPath);
//		INTERFACES_PATH = fileClassPath;
//	}
//
//	@Override
//	public void serialize(List<InterfaceConfig> interfaces) {
//		try {
//			JAXBContext jaxbContext = JAXBContext
//					.newInstance(InterfaceConfig.class);
//
//			Marshaller marshaller = jaxbContext.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
//					Boolean.TRUE);
//			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
//
//			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
//			xmlOutputFactory.setProperty(
//					XMLOutputFactory.IS_REPAIRING_NAMESPACES, false);
//
//			FileOutputStream fileOutputStream = null;
//
//			fileOutputStream = new FileOutputStream(config);
//			XMLStreamWriter xmlStreamWriter = xmlOutputFactory
//					.createXMLStreamWriter(fileOutputStream);
//			xmlStreamWriter.writeStartDocument();
//			xmlStreamWriter
//					.writeStartElement(InterfaceConfig.DOUCUMENT_ROOT_ELEMENT_NAME);
//
//			for (int i = 0; i < interfaces.size(); i++) {
//				InterfaceConfig interfaceConfig = interfaces.get(i);
//				QName qName = new QName(InterfaceConfig.INTERFACE_ROOT_ELEMENT_NAME);
//
//				JAXBElement<InterfaceConfig> element = new JAXBElement<InterfaceConfig>(
//						qName, InterfaceConfig.class, interfaceConfig);
//				marshaller.marshal(element, xmlStreamWriter);
//				xmlStreamWriter.flush();
//			}
//			xmlStreamWriter.writeEndElement();
//			xmlStreamWriter.writeEndDocument();
//			xmlStreamWriter.flush();
//
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (XMLStreamException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		FileInterfaceSerialize impl = new FileInterfaceSerialize();
//		List<InterfaceConfig> list = new ArrayList<InterfaceConfig>();
//
//		for (int i = 0; i < 10; i++) {
//
//			InterfaceConfig detail = new InterfaceConfig();
//			List<MethodConfig> methods = new ArrayList<MethodConfig>();
//			for (int j = 0; j < 5; j++) {
//				MethodConfig methodConfig = new MethodConfig();
//				methodConfig.setName("test");
//				methodConfig.setRecordPerformanceLog(true);
//				methodConfig.setRecordExceptionLog(true);
////				methodDetail.setTimeOut(60000);
//				methods.add(methodConfig);
//			}
//			detail.setClassName(ServiceAccessProxyFactory.class.getName());
//			detail.setOutwardService(false);
////			detail.setServiceAccessWay(InterfaceDetail.WEB_SERVICE);
//			detail.setMethodConfigs(methods);
//			list.add(detail);
//
//		}
//		long start = System.currentTimeMillis();
//		impl.serialize(list);
//		System.out.println((System.currentTimeMillis() - start));
//	}
//}
