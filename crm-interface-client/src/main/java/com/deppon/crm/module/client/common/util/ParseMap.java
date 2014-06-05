package com.deppon.crm.module.client.common.util;

import java.util.HashMap;

public final class ParseMap {

	
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> parse(MyHashMap myHashMap){
		try {
			return new MyHashMapAddapter().unmarshal(myHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, String>();
	}
	
	public static MyHashMap print(HashMap<String, String> map){
		try {
			return new MyHashMapAddapter().marshal(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new MyHashMap();
	}
}
