package com.deppon.crm.module.client.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;

public class JsonMapperUtil {

	public static ObjectMapper mapper = new ObjectMapper();
	
	public static String writeValue(Object obj) throws CrmBusinessException{
//		Check.notNull(obj, "object to json wrong because obj is null");
		if(obj == null){
			return "null";
		}
		try {
			String value = mapper.writeValueAsString(obj);
			return value;
			
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_JSON, String.format("JsonGenerationExcetion because of the object of %s",obj.getClass()));
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_JSON, String.format("JsonMappingException because of the object of %s",obj.getClass()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_JSON, String.format("IOException because of the object of %s",obj.getClass()));
		}
	}
	public static <T> T readValue(String content,Class<T> valueType) throws CrmBusinessException{
		try {
			return mapper.readValue(content, valueType);
		}catch (JsonGenerationException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_JSON, String.format("JsonGenerationExcetion because of the object of %s",valueType));
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_JSON, String.format("JsonMappingException because of the object of %s",valueType));
		} catch (IOException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_JSON, String.format("IOException because of the object of %s",valueType));
		}
	}
	
	public static List<Object> jsonStrToList(String jsonString, Class pojoClass)
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		List list = new ArrayList();
		Map<String, Class> m = new HashMap<String, Class>();
		m.put("items", pojoClass);
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			Object fb = JSONObject.toBean(jsonObject, pojoClass, m);
			list.add(fb);
		}
		return list;
	}

}
