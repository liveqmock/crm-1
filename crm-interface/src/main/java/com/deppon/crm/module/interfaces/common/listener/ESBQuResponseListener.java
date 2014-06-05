package com.deppon.crm.module.interfaces.common.listener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.common.util.XmlJaxbMapper;
import com.deppon.crm.module.client.esb.domain.MarketingActivityResponse;
import com.deppon.crm.module.client.esb.domain.OrigCustSyncRequestProcessDetail;
import com.deppon.crm.module.client.esb.domain.OrigCustSyncResponse;
import com.deppon.crm.module.client.esb.trans.CrmToEsbStatusSender;
import com.deppon.crm.module.client.esb.trans.EsbUtil;
import com.deppon.crm.module.client.log.InterfaceInvokeLogSender;
import com.deppon.crm.module.client.log.domain.InterfaceInvokeLog;
import com.deppon.crm.module.client.sync.wdgh.ResultDetal;
import com.deppon.crm.module.client.sync.wdgh.SyncCustInfoResponse;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.customer.ISyncCallbackService;

public class ESBQuResponseListener extends MessageListenerAdapter {
	private static Log log = LogFactory.getLog(ESBQuResponseListener.class);
	private InterfaceInvokeLogSender interfaceLogSender;
	private ISyncCallbackService syncCallBackService;

	// 状态发送工具
	private CrmToEsbStatusSender statusSender;
	@Override
	public void onMessage(Message message, Session session) {
		try {
			// 用于向message赋入新参数值
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(Constant.STATUS_CLIENT_RECEIVED, new Date().getTime());
			// 文本message
			TextMessage textMessage = (TextMessage) message;
			log.info(textMessage.getText());
			statusSender.send(Constant.STATUS_SERVER_RECEIVED, textMessage);
			log.info("--接收响应消息-->"+textMessage.getText());
			// 服务编码
			String serviceCode = message.getStringProperty(EsbUtil.BACK_SERVICE_CODE);
			List<Object> logList =new ArrayList<Object>();
			if(IntefacesTool.SCCUST_RESPONSE.equals(serviceCode)){
				OrigCustSyncResponse response = (OrigCustSyncResponse)XmlJaxbMapper.
						readValue(textMessage.getText(), OrigCustSyncResponse.class);
				if(response !=null && response.getDetail() !=null &&
						response.getDetail().size()>0){
					for(OrigCustSyncRequestProcessDetail detail : response.getDetail() ){
						InterfaceInvokeLog log = new InterfaceInvokeLog();
						if(detail != null){
							log.setRequestMsg(JsonMapperUtil.writeValue(detail));
						}
						log.setClassName(ESBQuResponseListener.class.getName());
						log.setInvokeTime(new Date());
						log.setException(detail.isResult()==true?false:true);
						log.setExceptionMsg(detail.getReason());
						log.setMethod("NONFIXEDCUSTOMER");
						log.setUseTime(10l);
						logList.add(log);
					}
					interfaceLogSender.send(logList);
				}
			}else if(IntefacesTool.SUCCESS_WDGH_RESPONSE.equals(serviceCode)){
				SyncCustInfoResponse response = (SyncCustInfoResponse) XmlJaxbMapper.readValue(textMessage.getText(), SyncCustInfoResponse.class);
				if(response!=null){
					for (ResultDetal resultDetal : response.getResultDetal()) {
						if(!resultDetal.isResultCode()){
							syncCallBackService.handle(resultDetal.getTransactionCode(), "WDGH", 0553, resultDetal.getReason());
						}
					}
				}
			}
			else if(IntefacesTool.MARKETING_ACTIVITY_RESPONSE.equals(serviceCode)){
				MarketingActivityResponse response=(MarketingActivityResponse)XmlJaxbMapper.readValue(textMessage.getText(), MarketingActivityResponse.class);
				if(null!=response){
					InterfaceInvokeLog log = new InterfaceInvokeLog();
					if(null!=response.getErrorMsg()&&!"".equals(response.getErrorMsg())){
						log.setRequestMsg(response.getFid()+","+response.isResult()+","+response.getErrorMsg());
					}
					log.setClassName(ESBQuResponseListener.class.getName());
					log.setInvokeTime(new Date());
					log.setException(response.isResult()==true?false:true);
					log.setExceptionMsg(response.getErrorMsg());
					log.setMethod("sysnMA");
					log.setUseTime(10l);
					logList.add(log);
					interfaceLogSender.send(logList);
				}
			}
			 //发送完成状态(请求/回调)
			statusSender.send(Constant.STATUS_COMPLETED, textMessage);
		} catch (JMSException e1) {
			e1.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public CrmToEsbStatusSender getStatusSender() {
		return statusSender;
	}
	public void setStatusSender(CrmToEsbStatusSender statusSender) {
		this.statusSender = statusSender;
	}
	public InterfaceInvokeLogSender getInterfaceLogSender() {
		return interfaceLogSender;
	}
	public void setInterfaceLogSender(InterfaceInvokeLogSender interfaceLogSender) {
		this.interfaceLogSender = interfaceLogSender;
	}
	public ISyncCallbackService getSyncCallBackService() {
		return syncCallBackService;
	}
	public void setSyncCallBackService(ISyncCallbackService syncCallBackService) {
		this.syncCallBackService = syncCallBackService;
	}

}
