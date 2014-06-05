package com.deppon.crm.module.client.sms.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import com.deppon.crm.module.client.common.InterfaceAddressConfigProvider;
import com.deppon.crm.module.client.common.domain.InterfaceAddressConfig;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Check;
import com.deppon.crm.module.client.common.util.EncryptUtil;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
/**
 * @作者：罗典
 * @描述：发送短信接口
 * @时间：2012-11-1
 * */
public class SmsInfoSenderImpl implements ISmsInfoSender {

	private String postAddress = "http://192.168.14.246:8086/dpVoiceSms/ws/sms/batch";
	private String userName    = "crmsms";
	private String password    = "20120504";
	private InterfaceAddressConfigProvider provider;
	private InterfaceAddressConfig config;
	private String serviceCode;

	/**
	 * @作者：罗典
	 * @描述：发送短信集合
	 * @时间：2012-11-1
	 * @参数：短信集合
	 * @返回值：是否成功
	 * */
	@Override
	public boolean sendSms(List<SmsInformation> list)
			throws CrmBusinessException {
		if(config == null){
			config = provider.getAddressMap(serviceCode);
		}
		this.postAddress = config.getAddress();
		this.userName = config.getUserName();
		this.password = config.getPassword();
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(postAddress);

		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
		try {
			mapper.writeValue(stringWriter, list);
			
			String dataDigest = "data_digest="+URLEncoder.encode(EncryptUtil.encrypt(userName + password),"UTF-8");
			String crmsms = "smsInfo="+URLEncoder.encode(stringWriter.getBuffer().toString(),"UTF-8");
			String content = dataDigest+"&"+crmsms;
			StringEntity entity = new StringEntity(content,"UTF-8");
			entity.setContentType("application/json; charset=UTF-8");
			
			post.setEntity(entity);
			
			HttpResponse response = httpClient.execute(post);
			if (response.getStatusLine().getStatusCode()!=200) {
				InputStream stream = response.getEntity().getContent();
				byte[] bt = new byte[1024];
				int len = 0;
				
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				
				while((len=stream.read(bt))!=-1){
					outStream.write(bt, 0, len);
				}
				outStream.flush();
				
				String error = new String(outStream.toByteArray());
				throw new CrmBusinessException("1005",error);
			}
			return true;
		} 
		catch(CrmBusinessException e){
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1005",e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：发送单条短信
	 * @时间：2012-11-1
	 * @参数：单条短信消息
	 * @返回值：是否成功
	 * */
	@Override
	public boolean sendSms(SmsInformation smsInfo) throws CrmBusinessException {
		Check.notNull(smsInfo, "sender message to mobile ,but the method arguments is null");
		Check.notNullOrEmpty(smsInfo.getMobile(), "mobile can not be null");
		List<SmsInformation> si = new ArrayList<SmsInformation>();
		si.add(smsInfo);
		return sendSms(si);
	}

	public InterfaceAddressConfigProvider getProvider() {
		return provider;
	}

	public void setProvider(InterfaceAddressConfigProvider provider) {
		this.provider = provider;
	}

	public InterfaceAddressConfig getConfig() {
		return config;
	}

	public void setConfig(InterfaceAddressConfig config) {
		this.config = config;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

}
