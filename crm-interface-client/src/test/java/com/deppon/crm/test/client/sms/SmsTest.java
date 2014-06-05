/*package com.deppon.crm.test.client.sms;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.deppon.crm.module.client.common.util.EncryptUtil;
import com.deppon.crm.module.client.sms.domain.SmsInfo;

public class SmsTest {

	@Test
	public void test41() throws Exception{
		SmsInfo info = new SmsInfo();
		info.setMobile("13944444444");
		info.setMsgContent("短信内容1");
		info.setSendDept("发送部门");
		info.setSender("发送人");
		info.setMsgType(5);
		info.setMsgSource(5);
		SmsInfo info1 = new SmsInfo();
		info1.setMobile("13955555555");
		info1.setMsgContent("短信内容2");
		info1.setSendDept("发送部门");
		info1.setSender("发送人");
		info1.setMsgType(5);
		info1.setMsgSource(5);
		List<SmsInfo> list = new ArrayList<SmsInfo>();
//		list.add(info);
//		list.add(info1);
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://192.168.14.246:8086/dpVoiceSms/ws/sms/batch");
//		HttpPost post = new HttpPost("http://localhost:8080/dpVoiceSms/ws/sms/batch");
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
		mapper.writeValue(stringWriter, list);

		System.out.println(EncryptUtil.encrypt("crmsms"+"20120504"));
		
		String dataDigest = "data_digest="+URLEncoder.encode(EncryptUtil.encrypt("crmsms" + "20120504"),"UTF-8");
		String crmsms = "smsInfo="+URLEncoder.encode(stringWriter.getBuffer().toString(),"UTF-8");
		String content = dataDigest+"&"+crmsms;
		
		StringEntity entity = new StringEntity(content,"UTF-8");
		
		entity.setContentType("application/json; charset=UTF-8");
		post.setEntity(entity);
		
		HttpResponse response = httpClient.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		InputStream stream = response.getEntity().getContent();
		byte[] bt = new byte[1024];
		int len = 0;
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		while((len=stream.read(bt))!=-1){
			outStream.write(bt, 0, len);
		}
		outStream.flush();
		
		String error = new String(outStream.toByteArray());
		System.out.println(error);
		
		Header[] hd = response.getAllHeaders();
		for (Header header : hd) {
			System.out.println(header.getName()+":"+header.getValue());
			HeaderElement[] he= header.getElements();
			for (HeaderElement headerElement : he) {
				System.out.println("-----"+headerElement.getName()+":"+headerElement.getValue());
			}
			
		}
	}
	
	@Test
	public void test31(){
//		HttpClient httpClient = new HttpClient();
//		PostMethod postMethod = new PostMethod("http://192.168.14.246:8086/dpVoiceSms/ws/sms/batch");
//		PostMethod postMethod = new PostMethod("http://localhost:8080/dpVoiceSms/ws/sms/batch");
//		SmsInfo info = new SmsInfo();
//		info.setMobile("13944444444");
//		info.setMsgContent("短信内容1");
//		info.setSendDept("发送部门");
//		info.setSender("发送人");
//		info.setMsgType(5);
//		info.setMsgSource(5);
//		SmsInfo info1 = new SmsInfo();
//		info1.setMobile("13955555555");
//		info1.setMsgContent("短信内容2");
//		info1.setSendDept("发送部门");
//		info1.setSender("发送人");
//		info1.setMsgType(5);
//		info1.setMsgSource(5);
//		List<SmsInfo> list = new ArrayList<SmsInfo>();
//		list.add(info);
//		list.add(info1);
//		ObjectMapper mapper = new ObjectMapper();
//		StringWriter stringWriter = new StringWriter();
//		//------------
//		mapper.writeValue(stringWriter, list);
//		String digest = encrypt("crmsms"+"20120504");
//		System.out.println(stringWriter.getBuffer().toString());
//		postMethod.setRequestBody(new NameValuePair[]{new NameValuePair("data_digest", digest), new NameValuePair("smsInfo", stringWriter.getBuffer().toString())});
//		System.out.println(digest);
//		postMethod.getParams().setContentCharset("UTF-8");
//		int status = httpClient.executeMethod(postMethod);
//		System.out.println(postMethod.getResponseBodyAsString()+"---");
//		
//		System.out.println(status);
	}
}
*/