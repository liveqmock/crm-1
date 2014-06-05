package com.deppon.crm.module.marketing.utilstest;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.MessageSendUtils;
import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;

public class MessageSendUtilsTest {
	@SuppressWarnings("unchecked")
	@Test
	public void testReadMessagePhoneFile(){
	  int startRow = 1;
	  boolean ifLast = false;
	  List<MessageSendPhone> list = null;
	  Map<String,Object> map = null;
	  boolean firstRead = true;
	  try{
		  while(!ifLast){
			  System.out.println("beginTime"+new Date());
			  map = MessageSendUtils.readMessagePhoneFile("E:"+File.separator+"ssssaaass.xlsx", startRow, true,"1", 3);
			  if(map.isEmpty())
				  firstRead=false;
			  list = (List<MessageSendPhone>)map.get("phoneList");
			  ifLast = (Boolean)map.get("ifLast");
			  startRow = (Integer)map.get("startRow");	
			  for(MessageSendPhone msp : list){
				  System.out.println(msp.getPhone());
				  // System.out.println(msp.getPhone()+"--"+msp.getValid());
			  }
			  System.out.println("EndTime"+new Date());
		  }		  
	  }catch (Exception e){
		  System.out.println(e.getLocalizedMessage());
	  }
	}
}
