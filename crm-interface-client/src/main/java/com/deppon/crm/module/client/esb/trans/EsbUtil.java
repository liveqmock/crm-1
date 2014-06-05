package com.deppon.crm.module.client.esb.trans;

import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.Holder;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.deppon.crm.module.client.common.util.ClientContext;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.DataFormatUtil;
import com.deppon.crm.module.client.esb.domain.ClientHeader;
import com.deppon.crm.module.client.esb.domain.ESBClientHeader;
import com.deppon.crm.module.client.esb.domain.ServerHeader;
import com.deppon.crm.module.client.esb.domain.StatusInfo;

/**
 * @作者：罗典
 * @时间：2012-11-2
 * @描述：ESB数据转换工具类
 * */
public class EsbUtil {
	/**
	 * header
	 */
	private final static String HEADER = "esbHeader";
	/**
	 * header 命名空间
	 */
	private final static String HEADER_NS = "http://www.deppon.com/esb/header";
	/**
	 * 版本号
	 */
	private final static String VERSION = "version";
	/**
	 * BusinessId业务id，BusinessDesc1、BusinessDesc2、BusinessDesc3业务辅助字段
	 */
	private final static String BUSINESS_ID = "businessId";
	private final static String BUSINESS_DESC1 = "businessDesc1";
	private final static String BUSINESS_DESC2 = "businessDesc2";
	private final static String BUSINESS_DESC3 = "businessDesc3";
	/**
	 * 请求id，
	 */
	private final static String REQUEST_ID = "requestId";
	/**
	 * 响应码
	 */
	public final static String RESPONSE_ID = "responseId";

	/**
	 * 客户端系统编码
	 */
	private final static String SOURCE_SYSTEM = "sourceSystem";
	/**
	 * 服务端系统编码
	 */
	private final static String TARGET_SYSTEM = "targetSystem";

	/**
	 * 服务编码
	 */
	public final static String ESB_SERIVCE_CODE = "esbServiceCode";
	/**
	 * 后端服务编码
	 */
	public final static String BACK_SERVICE_CODE = "backServiceCode";
	/**
	 * 消息格式：SOAP、XML、JSON
	 */
	private final static String MESSAGE_FORMAT = "messageFormat";
	/**
	 * 交互模式
	 */
	private final static String EXCHANGE_PATTERN = "exchangePattern";
	/**
	 * 用户、密码，用于权限认证/授权
	 */
	private final static String USERNAME = "userName";
	private final static String PASSWORD = "password";

	/**
	 * 结果状态嘛，表示调用成功或者失败
	 */
	public final static String RESULT_CODE = "resultCode";

	public final static int RESULT_CODE_STATUS_FAIL = 0;
	public final static int RESULT_CODE_STATUS_SUCCESS = 1;

	/**
	 * @作者：罗典
	 * @描述：转换header消息进入Message
	 * @时间：2012-11-22
	 * */
	public static void buildRequestJMSProperties(Message msg,
			ESBClientHeader header) throws JMSException {
		setValueToMessage(msg, VERSION, header.getVersion());
		setValueToMessage(msg, BUSINESS_ID, header.getBusinessId());
		setValueToMessage(msg, BUSINESS_DESC1, header.getBusinessDesc1());
		setValueToMessage(msg, BUSINESS_DESC2, header.getBusinessDesc2());
		setValueToMessage(msg, BUSINESS_DESC3, header.getBusinessDesc3());
		setValueToMessage(msg, REQUEST_ID, header.getRequestId());
		setValueToMessage(msg, SOURCE_SYSTEM, header.getSourceSystem());
		setValueToMessage(msg, TARGET_SYSTEM, header.getTargetSystem());
		setValueToMessage(msg, RESULT_CODE, header.getResultCode());
		setValueToMessage(msg, ESB_SERIVCE_CODE, header.getEsbServiceCode());
		setValueToMessage(msg, BACK_SERVICE_CODE, header.getBackServiceCode());
		setValueToMessage(msg, MESSAGE_FORMAT, header.getMessageFormat());
		setValueToMessage(msg, EXCHANGE_PATTERN, header.getExchangePattern());
		setValueToMessage(msg, USERNAME, header.getUserName());
		setValueToMessage(msg, PASSWORD, header.getPassword());
		for (StatusInfo info : header.getStatusList()) {
			setValueToMessage(msg, info.getStateKey(), info.getStateTime());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：转换header消息进入Message
	 * @时间：2012-11-22
	 * @throws JMSException
	 * */
	@SuppressWarnings("rawtypes")
	public static Message convertToNewMessage(Message message,Session session,Map<String,Object> map) throws JMSException{
		Message newMessage = session.createTextMessage();
		Enumeration enumeration =message.getPropertyNames();
		while(enumeration.hasMoreElements()){
			String propertyName = (String)enumeration.nextElement();
			newMessage.setObjectProperty(propertyName, message.getObjectProperty(propertyName));
		}
		if(map != null && map.keySet() != null){
			Iterator iterator = map.keySet().iterator();
			while(iterator.hasNext()){
				String key = (String) iterator.next();
				newMessage.setObjectProperty(key,map.get(key));
			}
		}
		return newMessage;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-11-5
	 * @描述：初始化同步客户端发出请求ESBHeader
	 * */
	/*public static void initWSDLHeader(ClientHeader esbHeader) {
		ClientContext.setClientHeader(esbHeader);
	}
*/
	/**
	 * @作者：罗典
	 * @时间：2012-11-5
	 * @描述：创建客户端发出请求out header
	 */
	public static Header createClientOutHeader() {
		if (ClientContext.getClientHeader() == null) {
			throw new RuntimeException("clientHeaders is null");
		}
		// 获取客户端headConfig
		ClientHeader config = ClientContext.getClientHeader();
		// 生成header
		QName qName = new QName(HEADER);
		Document doc = DOMUtils.createDocument();
		Element root = doc.createElementNS(HEADER_NS, HEADER);
		validateNotNull(config.getEsbServiceCode());
		validateNotNull(config.getMessageFormat());
		validateNotNull(config.getVersion());
		validateNotNull(config.getSourceSystem());
		validateNotNull(config.getVersion());
		validateNotNull(config.getMessageFormat());
		root.appendChild(createElement(VERSION, config.getVersion(), doc));
		if (isNull(config.getBusinessId())) {
			root.appendChild(createElement(BUSINESS_ID, config.getBusinessId(),
					doc));
		}
		if (isNull(config.getBusinessDesc1())) {
			root.appendChild(createElement(BUSINESS_DESC1,
					config.getBusinessDesc1(), doc));
		}
		if (isNull(config.getBusinessDesc2())) {
			root.appendChild(createElement(BUSINESS_DESC2,
					config.getBusinessDesc3(), doc));
		}
		if (isNull(config.getBusinessDesc3())) {
			root.appendChild(createElement(BUSINESS_DESC3,
					config.getBusinessDesc3(), doc));
		}
		root.appendChild(createElement(REQUEST_ID,
				UUID.randomUUID().toString(), doc));
		root.appendChild(createElement(SOURCE_SYSTEM, config.getSourceSystem(),
				doc));
		root.appendChild(createElement(ESB_SERIVCE_CODE,
				config.getEsbServiceCode(), doc));
		root.appendChild(createElement(MESSAGE_FORMAT,
				config.getMessageFormat(), doc));
		root.appendChild(createElement(EXCHANGE_PATTERN,
				config.getExchangePattern() + "", doc));
		if (config.getUserName() != null && !"".equals(config.getUserName())
				&& config.getPassword() != null
				&& !"".equals(config.getPassword())) {
			root.appendChild(createElement(USERNAME, config.getUserName(), doc));
			root.appendChild(createElement(PASSWORD, config.getPassword(), doc));
		}
		SoapHeader header = new SoapHeader(qName, root);
		ClientContext.removeClientHeader();
		return header;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-11-5
	 * @描述：处理服务器端接收请求in Header
	 */
	public static void processServerInHeader(SOAPHeader head) {
		// 创建ESBHeader
		Node node = (Node) head;
		ServerHeader esbHeader = null;
		if (node.hasChildNodes()) {
			NodeList list = node.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node child = list.item(i);
				// 判断是否有“eabHeader”节点，若存在则遍历esbHeader，构造ESBHeader对象
				if (HEADER.equals(child.getNodeName())) {
					esbHeader = new ServerHeader();
					NodeList esbHeaderList = child.getChildNodes();
					for (int j = 0; j < esbHeaderList.getLength(); j++) {
						Node header = esbHeaderList.item(j);
						if (VERSION.equals(header.getNodeName())) {
							esbHeader.setVersion(header.getTextContent());
						} else if (BUSINESS_ID.equals(header.getNodeName())) {
							esbHeader.setBusinessId(header.getTextContent());
						} else if (BUSINESS_DESC1.equals(header.getNodeName())) {
							esbHeader.setBusinessDesc1(header.getTextContent());
						} else if (BUSINESS_DESC2.equals(header.getNodeName())) {
							esbHeader.setBusinessDesc2(header.getTextContent());
						} else if (BUSINESS_DESC3.equals(header.getNodeName())) {
							esbHeader.setBusinessDesc3(header.getTextContent());
						} else if (REQUEST_ID.equals(header.getNodeName())) {
							esbHeader.setRequestId(header.getTextContent());
						} else if (SOURCE_SYSTEM.equals(header.getNodeName())) {
							esbHeader.setSourceSystem(header.getTextContent());
						} else if (TARGET_SYSTEM.equals(header.getNodeName())) {
							esbHeader.setTargetSystem(header.getTextContent());
						} else if (ESB_SERIVCE_CODE
								.equals(header.getNodeName())) {
							esbHeader
									.setEsbServiceCode(header.getTextContent());
						} else if (BACK_SERVICE_CODE.equals(header
								.getNodeName())) {
							esbHeader.setBackServiceCode(header
									.getTextContent());
						} else if (MESSAGE_FORMAT.equals(header.getNodeName())) {
							esbHeader.setMessageFormat(header.getTextContent());
						} else if (EXCHANGE_PATTERN
								.equals(header.getNodeName())) {
							esbHeader.setExchangePattern(header
									.getTextContent());
						} else if (USERNAME.equals(header.getNodeName())) {
							esbHeader.setUserName(header.getTextContent());
						} else if (PASSWORD.equals(header.getNodeName())) {
							esbHeader.setPassword(header.getTextContent());
						}
					}
				}
			}
		}
		if (esbHeader == null) {
			throw new RuntimeException(
					"CRMServer check fail : can not convert to ESBHeader or "
							+ "ESBHeader is null");
		}
		// 存放在接口上下文中
		ClientContext.setServerHeader(esbHeader);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-11-5
	 * @描述：创建服务端响应out header
	 *
	 */
	public static Header createServerOutHeader() {
		if (ClientContext.getServerHeader() == null) {
			throw new RuntimeException("ESBHeader is null");
		}
		// 获取服务端ESBHeader对象
		ServerHeader esbHeader = ClientContext.getServerHeader();
		// 生成header信息
		// 生成header
		QName qName = new QName(HEADER);
		Document doc = DOMUtils.createDocument();
		Element root = doc.createElementNS(HEADER_NS, HEADER);

		root.appendChild(createElement(VERSION, esbHeader.getVersion(), doc));
		root.appendChild(createElement(REQUEST_ID, esbHeader.getRequestId(),
				doc));
		root.appendChild(createElement(RESPONSE_ID, UUID.randomUUID()
				.toString(), doc));
		root.appendChild(createElement(SOURCE_SYSTEM,
				esbHeader.getSourceSystem(), doc));
		root.appendChild(createElement(TARGET_SYSTEM,
				esbHeader.getTargetSystem(), doc));
		root.appendChild(createElement(ESB_SERIVCE_CODE,
				esbHeader.getEsbServiceCode(), doc));
		root.appendChild(createElement(BACK_SERVICE_CODE,
				esbHeader.getBackServiceCode(), doc));
		root.appendChild(createElement(MESSAGE_FORMAT,
				esbHeader.getMessageFormat(), doc));
		root.appendChild(createElement(RESULT_CODE, esbHeader.getResultCode(),
				doc));
		root.appendChild(createElement(EXCHANGE_PATTERN,
				esbHeader.getExchangePattern() + "", doc));
		root.appendChild(createElement(RESULT_CODE, esbHeader.getResultCode()
				+ "", doc));
		if (null != esbHeader.getBusinessId()) {
			root.appendChild(createElement(BUSINESS_ID,
					esbHeader.getBusinessId() + "", doc));
		}
		if (null != esbHeader.getBusinessDesc1()) {
			root.appendChild(createElement(BUSINESS_DESC1,
					esbHeader.getBusinessDesc1() + "", doc));
		}
		if (null != esbHeader.getBusinessDesc2()) {
			root.appendChild(createElement(BUSINESS_DESC2,
					esbHeader.getBusinessDesc2() + "", doc));
		}
		if (null != esbHeader.getBusinessDesc3()) {
			root.appendChild(createElement(BUSINESS_DESC3,
					esbHeader.getBusinessDesc3() + "", doc));
		}
		if (esbHeader.getUserName() != null
				&& !"".equals(esbHeader.getUserName())) {
			root.appendChild(createElement(USERNAME, esbHeader.getUserName(),
					doc));
			root.appendChild(createElement(PASSWORD, esbHeader.getPassword(),
					doc));
		}
		SoapHeader header = new SoapHeader(qName, root);
		ClientContext.removeServerHeader();
		return header;
	}

	/**
	 * @作者：罗典
	 * @描述：创建运单同步客户端out ESBHeader
	 * @时间：2012-01-06
	 * */
	public static Holder<com.deppon.foss.crm.ESBHeader> createCustomerWaybillHeader(String esbServiceCode,
			String businessId,String businessDesc1){
		com.deppon.foss.crm.ESBHeader esbHeader =new com.deppon.foss.crm.ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(esbServiceCode);
		esbHeader.setBusinessId(businessId);
		esbHeader.setBusinessDesc1(businessDesc1);
		esbHeader.setBusinessDesc2(DataFormatUtil.formatDate(new Date()));
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<com.deppon.foss.crm.ESBHeader> holder =
				new Holder<com.deppon.foss.crm.ESBHeader>(esbHeader);
		return holder;
	}

	/**
	 * @作者：罗典
	 * @描述：创建运单同步客户端out ESBHeader
	 * @时间：2012-01-06
	 * */
	public static Holder<com.deppon.foss.waybill.ESBHeader> createWaybillHeader(String esbServiceCode,
			String businessId,String businessDesc1){
		com.deppon.foss.waybill.ESBHeader esbHeader =new com.deppon.foss.waybill.ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(esbServiceCode);
		esbHeader.setBusinessId(businessId);
		esbHeader.setBusinessDesc1(businessDesc1);
		esbHeader.setBusinessDesc2(DataFormatUtil.formatDate(new Date()));
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<com.deppon.foss.waybill.ESBHeader> holder =
				new Holder<com.deppon.foss.waybill.ESBHeader>(esbHeader);
		return holder;
	}


	/**
	 * @作者：罗典
	 * @描述：创建异步客户端out ESBHeader
	 * @时间：2012-12-15
	 * */
	public static ESBClientHeader createAsyncOutHeader(String esbServiceCode,String businessId,
			String businessDesc1,String businessDesc2){
		ESBClientHeader header = new ESBClientHeader();
		header.setEsbServiceCode(esbServiceCode);
		header.setBusinessId(businessId);
		header.setBusinessDesc1(businessDesc1);
		header.setBusinessDesc2(businessDesc2);
		StatusInfo statusInfo = new StatusInfo();
		statusInfo.setStateKey(Constant.STATUS_CLIENT_SENT);
		statusInfo.setStateTime(new Date().getTime());
		header.getStatusList().add(statusInfo);
		return header;
	}
	public static ESBClientHeader createAsyncOutHeader(String esbServiceCode,String businessId){
		return createAsyncOutHeader(esbServiceCode,businessId,null,null);
	}
	public static ESBClientHeader createAsyncOutHeader(String esbServiceCode,String businessId,
			String businessDesc1){
		return createAsyncOutHeader(esbServiceCode,businessId,businessDesc1,null);
	}
	/**
	 * @作者：罗典
	 * @时间：2012-11-5
	 * @描述：判断是否空值，若为空值，则抛异常。
	 */
	private static void validateNotNull(String arg) {
		if ("".equals(arg) || arg == null) {
			throw new RuntimeException(" headerConfig value is null");
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-11-5
	 * @描述：判断是否空值，若为空值，则抛异常。
	 * @param arg
	 * @throws JMSException
	 */
	private static void setValueToMessage(Message message, String key,
			Object value) throws JMSException {
		if (value != null) {
			if (value instanceof String && !value.toString().equals("")) {
				message.setStringProperty(key, value.toString());
			} else if (value instanceof Integer) {
				message.setIntProperty(key, ((Integer) value).intValue());
			} else if (value instanceof Long) {
				message.setLongProperty(key, ((Long) value).longValue());
			}
		}
	}

	/**
	 * 判断是否空值，若为空值，则返回false，若不为空值则返回true。
	 *
	 * @param arg
	 * @return
	 */
	private static boolean isNull(String arg) {
		boolean b = false;
		if (!"".equals(arg) && arg != null) {
			b = true;
		}
		return b;
	}

	private static Element createElement(String key, String value, Document doc) {
		Element element = doc.createElementNS("", key);
		element.setTextContent(value);
		return element;
	}
}
