//package com.deppon.crm.module.client.common;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.XMLStreamReader;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.util.Assert;
//
//import com.deppon.crm.module.client.common.domain.InterfaceConfig;
//import com.deppon.crm.module.client.common.util.Constant;
//
///**
// * 从文件获取接口详细信息的实现类
// * @author davidcun @2012-4-10
// */
//public class FileInterfaceProvier extends ConfigFileLoad implements InterfaceProvider {
//
//	private static Log log = LogFactory.getLog(FileInterfaceProvier.class);
//	private Map<String, InterfaceConfig> interfaceMap;
//	private static String INTERFACES_PATH = Constant.INTERFACE_DEFAULT_FILE_PATH;
//	
//	public FileInterfaceProvier(){
//		super(INTERFACES_PATH);
//	}
//	public FileInterfaceProvier(String fileClassPath) {
//		super(fileClassPath);
//		Assert.notNull(fileClassPath);
//		Assert.hasText(fileClassPath);
//	}
//
//	
//	public Map<String, InterfaceConfig> getInterfaceConfigs() {
//
//		if (interfaceMap==null) {
//			synchronized(this){
//				if (interfaceMap==null) {
//					try {
//						JAXBContext jaxbContext = JAXBContext.newInstance(InterfaceConfig.class);
//						Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//						XMLInputFactory inputFactory = XMLInputFactory.newFactory();
//						
//						FileInputStream  is = new FileInputStream(config);
//						interfaceMap = new HashMap<String, InterfaceConfig>();
//						XMLStreamReader xmlStreamReader = inputFactory.createXMLStreamReader(is);
//						while (xmlStreamReader.hasNext()) {
//							
//							if (xmlStreamReader.isStartElement() && 
//									InterfaceConfig.INTERFACE_ROOT_ELEMENT_NAME.equals(xmlStreamReader.getName().getLocalPart())) {
//								JAXBElement<InterfaceConfig> element = 
//									unmarshaller.unmarshal(xmlStreamReader,InterfaceConfig.class);
//								element.getValue().setClassName(element.getValue().getClassName());
//								interfaceMap.put(element.getValue().getClassName().trim(), element.getValue());
//								continue;
//							}
//							xmlStreamReader.next();
//						}
//					} catch (JAXBException e) {
//						e.printStackTrace();
//					}  catch (XMLStreamException e) {
//						e.printStackTrace();
//					} catch (FileNotFoundException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		return interfaceMap;
//	}
//	
//	public static void main(String[] args) {
//		FileInterfaceProvier interfaceProvier = new FileInterfaceProvier();
//		long start = System.currentTimeMillis();
//		interfaceProvier.getInterfaceConfigs();
//		System.out.println(System.currentTimeMillis()-start);
//		
//	}
//}
