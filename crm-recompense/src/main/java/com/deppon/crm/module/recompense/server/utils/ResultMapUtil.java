package com.deppon.crm.module.recompense.server.utils;

import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.recompense.shared.domain.ResultMap;


public class ResultMapUtil {
	public static Map<String,Object> createSimpleResultMap(){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ResultMap.CODE, "");
		resultMap.put(ResultMap.DATA, "");
		resultMap.put(ResultMap.MESSAGE,"");
		return resultMap;
	}
	
	public static Map<String,Object> createSuccessResultMap(Object resultData){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ResultMap.CODE, ResultMap.SUCCESS);
		resultMap.put(ResultMap.DATA, resultData);
		resultMap.put(ResultMap.MESSAGE,"");
		return resultMap;
	}
	
	public static Map<String,Object> createFailResultMap(Object message){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ResultMap.CODE, ResultMap.FAIL);
		resultMap.put(ResultMap.DATA, "");
		resultMap.put(ResultMap.MESSAGE,message);
		return resultMap;
	}
}
