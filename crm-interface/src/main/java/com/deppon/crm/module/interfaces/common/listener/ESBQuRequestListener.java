package com.deppon.crm.module.interfaces.common.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.deppon.crm.module.client.common.exception.CommonExceptionInfo;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.ClientContext;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.common.util.XmlJaxbMapper;
import com.deppon.crm.module.client.esb.trans.CrmToEsbExceptionSender;
import com.deppon.crm.module.client.esb.trans.CrmToEsbResponseSender;
import com.deppon.crm.module.client.esb.trans.CrmToEsbStatusSender;
import com.deppon.crm.module.client.esb.trans.EsbUtil;
import com.deppon.crm.module.client.sync.domain.OrderRightRequest;
import com.deppon.crm.module.interfaces.bank.IBankInfoSyncService;
import com.deppon.crm.module.interfaces.bank.domain.BankInfoNotificationRequest;
import com.deppon.crm.module.interfaces.bank.domain.BankInfoNotificationResponse;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfoNotificationRequest;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfoNotificationResponse;
import com.deppon.crm.module.interfaces.common.ESBStatusSendThread;
import com.deppon.crm.module.interfaces.foss.EsbToCrmService;
import com.deppon.crm.module.interfaces.foss.domain.ReceiveCreditUsedRequest;
import com.deppon.crm.module.interfaces.foss.domain.ReceiveCreditUsedResponse;
import com.deppon.crm.module.interfaces.foss.domain.ReturnVoucherPaymentResultRequest;
import com.deppon.crm.module.interfaces.foss.domain.UpdateOrderRequest;
import com.deppon.crm.module.interfaces.foss.domain.UpdateOrderResponse;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.ObjectFactory;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.SyncMotorcadeRequest;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.SyncMotorcadeResponse;
import com.deppon.crm.module.interfaces.foss.domain.scatter.CreateScatterRequest;
import com.deppon.crm.module.interfaces.foss.domain.scatter.CreateScatterResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncDistrictRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncDistrictResponse;
import com.deppon.foss.express.SyncExpressCityRequest;
import com.deppon.foss.express.SyncExpressCityResponse;
import com.deppon.foss.express.SyncExpressDeptRelationRequest;
import com.deppon.foss.express.SyncExpressDeptRelationResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncOrganizationRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncOrganizationResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncSalesDepartmentRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncSalesDepartmentResponse;
import com.deppon.crm.module.interfaces.ows.IOwsJmsToCrmService;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerRequest;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerResponse;
import com.deppon.crm.module.interfaces.uums.UumsToCrmSyncService;
import com.deppon.crm.module.interfaces.uums.jms.SendAdminOrgRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendAdminOrgResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendEmployeeRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendEmployeeResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendUserInfoDeptAllRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendUserInfoDeptAllResponse;

/**
 * @作者：罗典
 * @描述：FOSS消息监听
 * @时间：2012-11-13
 * */
public class ESBQuRequestListener extends MessageListenerAdapter {
	private static Log log = LogFactory.getLog(ESBQuRequestListener.class);
	// 状态发送工具
	private CrmToEsbStatusSender statusSender;
	private CrmToEsbResponseSender responseSender;
	private IBankInfoSyncService baseInfoSyncService;
	private CrmToEsbExceptionSender exceptionSender;
	private EsbToCrmService esbToCrmService;
	//uums权限信息同步
	private UumsToCrmSyncService uumsToCrmSyncService;
	//官网客户信息同步
	private IOwsJmsToCrmService owsJmsToCrmService;
	/**
	 * @作者：罗典
	 * @描述：重写方法，用于FOSS请求消息处理
	 * @时间：2012-11-22
	 * */
	@Override
	public void onMessage(Message message, Session session) {
		try {
			// 用于向message赋入新参数值
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(Constant.STATUS_SERVER_RECEIVED, new Date().getTime());
			// 文本message
			TextMessage textMessage = (TextMessage) message;
//			log.info(textMessage.getText());
			// 发送已接受状态
			sendStatusToEsb(session,message,Constant.STATUS_SERVER_RECEIVED);
			// 服务编码
			String serviceCode = message.getStringProperty(EsbUtil.BACK_SERVICE_CODE);
			NullOrEmptyValidator.checkEmpty(serviceCode, EsbUtil.BACK_SERVICE_CODE);
			logger.info("接收数据--->"+textMessage.getText());
			if(serviceCode.equals(Constant.SYNC_EXPRESS_DEPT)){
				SyncExpressDeptRelationRequest request=(SyncExpressDeptRelationRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SyncExpressDeptRelationRequest.class);
				SyncExpressDeptRelationResponse response=esbToCrmService.sysExpressDeptRelation(request);
				String info = XmlJaxbMapper.writeValue(response,Constant._SyncExpressDeptResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			// 同步FOSS试点城市、落地配城市
			else if(serviceCode.equals(Constant.SYNC_EXPRESS_CITY)){
				SyncExpressCityRequest request = (SyncExpressCityRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SyncExpressCityRequest.class);
				SyncExpressCityResponse response = esbToCrmService.syncExpressCity(request);
				String info = XmlJaxbMapper.writeValue(response,Constant._SyncExpressCityResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			else if (serviceCode.equals(Constant.RECEIVE_BANK)) {
				BankInfoNotificationRequest request = (BankInfoNotificationRequest)XmlJaxbMapper.
						readValue(textMessage.getText(), BankInfoNotificationRequest.class);
				// 发送开始处理状态
				sendStatusToEsb(session,message,Constant.STATUS_SERVER_PROCESS);
				BankInfoNotificationResponse response = baseInfoSyncService.receiveBank(request);
				map.put(EsbUtil.RESULT_CODE, EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				String info = XmlJaxbMapper.writeValue(response,Constant._BankInfoNotificationResponse_QNAME);
				Message tempMessage = EsbUtil.convertToNewMessage(message, session,map);
				TextMessage tempTextMessage = (TextMessage)tempMessage;
				// 发送响应状态
				sendStatusToEsb(session,message,Constant.STATUS_SERVER_SENT);
				responseSender.send(info, tempTextMessage);
				return ;
			}
			// 同步银企省份城市(请求/回调)
			else if(serviceCode.equals(Constant.RECEIVE_PROVINCECITY)){
				ProvinceCityInfoNotificationRequest request = (ProvinceCityInfoNotificationRequest)XmlJaxbMapper.
						readValue(textMessage.getText(), ProvinceCityInfoNotificationRequest.class);
				// 发送开始处理状态
				sendStatusToEsb(session,message,Constant.STATUS_SERVER_PROCESS);
				ProvinceCityInfoNotificationResponse response = baseInfoSyncService.receiveProvinceCity(request);
				map.put(EsbUtil.RESULT_CODE, EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				String info = XmlJaxbMapper.writeValue(response,Constant._ProvinceCityInfoNotificationRequest_QNAME);
				Message tempMessage = EsbUtil.convertToNewMessage(message, session,map);
				TextMessage tempTextMessage = (TextMessage)tempMessage;
				// 发送响应状态
				sendStatusToEsb(session,message,Constant.STATUS_SERVER_SENT);
				responseSender.send(info, tempTextMessage);
				return ;
			}// 修改订单状态(请求/回调)
			if (serviceCode.equals(Constant.MODIFYORDER)) {
				UpdateOrderRequest request = (UpdateOrderRequest) XmlJaxbMapper
						.readValue(textMessage.getText(),
								UpdateOrderRequest.class);
				UpdateOrderResponse response = esbToCrmService
						.updateOrderStatus(request);
				String info = XmlJaxbMapper.writeValue(response,
						Constant._UpdateOrderResponse_QNAME);
				if (response.isResult()) {
					map.put(EsbUtil.RESULT_CODE,
							EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				} else {
					map.put(EsbUtil.RESULT_CODE,
							EsbUtil.RESULT_CODE_STATUS_FAIL);
				}
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			// 通知理赔支付状态(单向)
			else if (serviceCode.equals(Constant.NOTIFY_CLAIMS_STATE)) {
				ReturnVoucherPaymentResultRequest request = (ReturnVoucherPaymentResultRequest) XmlJaxbMapper
						.readValue(textMessage.getText(),
								ReturnVoucherPaymentResultRequest.class);
				esbToCrmService.claimsState(request);
			}
			/*// 网点同步接口(单向)
			else if (serviceCode.equals(Constant.RECEIVE_SITE)) {
				SiteReceiveRequest request = (SiteReceiveRequest) XmlJaxbMapper
						.readValue(textMessage.getText(),
								SiteReceiveRequest.class);
				esbToCrmService.receiveSiteSync(request);
			}*/
			// 开单组权限同步接口(单向)
			else if (serviceCode.equals(Constant.ORDER_RIGHT)) {
				OrderRightRequest request = (OrderRightRequest) XmlJaxbMapper
						.readValue(textMessage.getText(),
								OrderRightRequest.class);
				esbToCrmService.orderRightSync(request);
			}
			// 合同月结天数同步接口(请求/回调)
			else if (Constant.RECEIVE_CREDITUSED.equals(serviceCode)) {
				ReceiveCreditUsedRequest request = (ReceiveCreditUsedRequest) XmlJaxbMapper
						.readValue(textMessage.getText(),ReceiveCreditUsedRequest.class);
				ReceiveCreditUsedResponse response = esbToCrmService.receiveCreditUsed(request);
			}
			// 组织信息同步
			else if(Constant.SYNC_ORGANIZAITION.equals(serviceCode)) {
				SyncOrganizationRequest request = (SyncOrganizationRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SyncOrganizationRequest.class);
				SyncOrganizationResponse response = esbToCrmService.syncOrganizea(request);
				String info = XmlJaxbMapper.writeValue(response,Constant._SyncOrganizationResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			// 营业部信息同步
			else if(Constant.SYNC_SALES_DEPARTMENT.equals(serviceCode)) {
				SyncSalesDepartmentRequest request = (SyncSalesDepartmentRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SyncSalesDepartmentRequest.class);
				SyncSalesDepartmentResponse response = esbToCrmService.syncStaArrDept(request);
				String info = XmlJaxbMapper.writeValue(response,Constant._SyncSalesDepartmentResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			// 车队信息同步
			}else if(Constant.SYNC_MOTORCADEINFO.equals(serviceCode)){
				SyncMotorcadeRequest request = (SyncMotorcadeRequest) XmlJaxbMapper.readValue(textMessage.getText(), SyncMotorcadeRequest.class);

				SyncMotorcadeResponse response = esbToCrmService.syncMotorcadeInfo(request);
				String info = XmlJaxbMapper.writeValue(response,ObjectFactory._SyncMotorcadeResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				logger.info("响应-->"+info);

				responseSender.send(info, tempTextMessage);
				return;
			}
			// FOSS行政区域同步
			else if(Constant.SYNC_DISTRICT.equals(serviceCode)) {
				SyncDistrictRequest request =(SyncDistrictRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SyncDistrictRequest.class);
				SyncDistrictResponse  response = esbToCrmService.syncAdminArea(request);
				String info = XmlJaxbMapper.writeValue(response,Constant._SyncDistrictResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			//FOSS散客同步
			else if(Constant.SCATTER_CREATE.equals(serviceCode)){
				CreateScatterRequest request=(CreateScatterRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),CreateScatterRequest.class);
				CreateScatterResponse response=esbToCrmService.createScatter(request);
				String info=XmlJaxbMapper.writeValue(response,Constant._CreateScatterResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			//官网同步客户给CRM
			else if(Constant.CUST_OWS_CREATE.equals(serviceCode)){
				CreateOwsCustomerRequest request=(CreateOwsCustomerRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),CreateOwsCustomerRequest.class);
				CreateOwsCustomerResponse response=owsJmsToCrmService.createOwsCustomer(request);
				String info=XmlJaxbMapper.writeValue(response,Constant._CreateOwsCustomerResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			//UUMS权限同步 职位信息
			else if (Constant.POSITION_TO_CRM.equals(serviceCode)) {
				SendPositionRequest request = (SendPositionRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SendPositionRequest.class);
				SendPositionResponse response = uumsToCrmSyncService.syncPositionInfo(request);
				String info = XmlJaxbMapper.writeValue(response, Constant._SendPositionResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			//UUMS权限同步 职员信息
			else if (Constant.EMPLOYEE_TO_CRM.equals(serviceCode)) {
				SendEmployeeRequest request = (SendEmployeeRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SendEmployeeRequest.class);
				SendEmployeeResponse response = uumsToCrmSyncService.syncEmployeeInfo(request);
				String info = XmlJaxbMapper.writeValue(response, Constant._SendEmployeeResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			//UUMS权限同步 用户信息
			else if (Constant.USERINFO_TO_CRM.equals(serviceCode)) {
				SendUserInfoDeptAllRequest request = (SendUserInfoDeptAllRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SendUserInfoDeptAllRequest.class);
				SendUserInfoDeptAllResponse response = uumsToCrmSyncService.syncUserInfoDeptAll(request);
				String info = XmlJaxbMapper.writeValue(response, Constant._SendUserInfoDeptAllResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			//UUMS权限同步 行政组织信息
			else if (Constant.ADMINORG_TO_CRM.equals(serviceCode)) {
				SendAdminOrgRequest request = (SendAdminOrgRequest)XmlJaxbMapper
						.readValue(textMessage.getText(),SendAdminOrgRequest.class);
				SendAdminOrgResponse response = uumsToCrmSyncService.syncAdminOrgInfo(request);
				String info = XmlJaxbMapper.writeValue(response, Constant._SendAdminOrgResponse_QNAME);
				map.put(EsbUtil.RESULT_CODE,EsbUtil.RESULT_CODE_STATUS_SUCCESS);
				map.put(EsbUtil.RESPONSE_ID, UUID.randomUUID().toString());
				map.put(Constant.STATUS_SERVER_SENT, (new Date()).getTime());
				Message tempMessage = EsbUtil.convertToNewMessage(message,
						session, map);
				TextMessage tempTextMessage = (TextMessage) tempMessage;
				responseSender.send(info, tempTextMessage);
				return;
			}
			else {
				CommonExceptionInfo info = new CommonExceptionInfo();
				info.setMessage("FossRequestListener process fail,BACK_SERVICE_CODE is not match to "
						+ serviceCode);
				info.setExceptioncode("ESB000001");
				info.setExceptiontype("SystemException");
				info.setCreatedTime(new Date());
				String exceptionMsg = XmlJaxbMapper.writeValue(info,
						Constant._CommException_QNAME);
				exceptionSender.send(exceptionMsg, message);
				return ;
			}
			ESBStatusSendThread statusThread2 = new ESBStatusSendThread(
					statusSender, textMessage, Constant.STATUS_COMPLETED);
			ClientContext.getPool().execute(statusThread2);
		} catch (Exception e) {
			CommonExceptionInfo info = new CommonExceptionInfo();
			info.setMessage(e.getMessage());
			info.setExceptioncode("ESB000001");
			info.setExceptiontype("SystemException");
			info.setCreatedTime(new Date());
			try {
				String exceptionMsg = XmlJaxbMapper.writeValue(info,
						Constant._CommException_QNAME);
				log.info("系统信息报错",e);
				exceptionSender.send(exceptionMsg, message);
			} catch (CrmBusinessException e1) {
				log.info("发送ESB信息报错",e);
				exceptionSender.send(e1.getMessage(), message);
			}
		}
	}
	/**
	 * @作者：罗典
	 * @时间：2013-2-25
	 * @描述：发送状态消息至ESB
	 * */
	private void sendStatusToEsb(Session session,Message message,String status) throws Exception{
		TextMessage newMessage = (TextMessage)EsbUtil.convertToNewMessage(message, session, null);
		ESBStatusSendThread statusThread =
				new ESBStatusSendThread(statusSender,newMessage,status);
		ClientContext.getPool().execute(statusThread);
	}


	public CrmToEsbStatusSender getStatusSender() {
		return statusSender;
	}

	public void setStatusSender(CrmToEsbStatusSender statusSender) {
		this.statusSender = statusSender;
	}

	public CrmToEsbResponseSender getResponseSender() {
		return responseSender;
	}

	public void setResponseSender(CrmToEsbResponseSender responseSender) {
		this.responseSender = responseSender;
	}

	public IBankInfoSyncService getBaseInfoSyncService() {
		return baseInfoSyncService;
	}

	public void setBaseInfoSyncService(IBankInfoSyncService baseInfoSyncService) {
		this.baseInfoSyncService = baseInfoSyncService;
	}

	public CrmToEsbExceptionSender getExceptionSender() {
		return exceptionSender;
	}

	public void setExceptionSender(CrmToEsbExceptionSender exceptionSender) {
		this.exceptionSender = exceptionSender;
	}
	public EsbToCrmService getEsbToCrmService() {
		return esbToCrmService;
	}
	public void setEsbToCrmService(EsbToCrmService esbToCrmService) {
		this.esbToCrmService = esbToCrmService;
	}
	/**
	 * Description:uumsToCrmSyncService<br />
	 * @author CoCo
	 * @version 0.1 2013-11-25
	 */
	public UumsToCrmSyncService getUumsToCrmSyncService() {
		return uumsToCrmSyncService;
	}
	/**
	 * Description:uumsToCrmSyncService<br />
	 * @author CoCo
	 * @version 0.1 2013-11-25
	 */
	public void setUumsToCrmSyncService(UumsToCrmSyncService uumsToCrmSyncService) {
		this.uumsToCrmSyncService = uumsToCrmSyncService;
	}
	/**
	 * @return owsJmsToCrmService : return the property owsJmsToCrmService.
	 */
	public IOwsJmsToCrmService getOwsJmsToCrmService() {
		return owsJmsToCrmService;
	}
	/**
	 * @param owsJmsToCrmService : set the property owsJmsToCrmService.
	 */
	public void setOwsJmsToCrmService(IOwsJmsToCrmService owsJmsToCrmService) {
		this.owsJmsToCrmService = owsJmsToCrmService;
	}


}
