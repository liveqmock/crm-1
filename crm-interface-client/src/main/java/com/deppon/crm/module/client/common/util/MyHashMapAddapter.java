package com.deppon.crm.module.client.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

@SuppressWarnings("rawtypes")
public class MyHashMapAddapter extends XmlAdapter<MyHashMap, HashMap> {

	@Override
	public HashMap unmarshal(MyHashMap v) throws Exception {
		List<MyHashMap.MyHashMapEntry> myEntry = v.getMyHashMapEntries();
		
		HashMap<String, String> hashMap = new HashMap<String,String>();
		
		for (MyHashMap.MyHashMapEntry myHashMapEntry : myEntry) {
			hashMap.put(myHashMapEntry.getKey(),myHashMapEntry.getValue());
		}
		return hashMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MyHashMap marshal(HashMap v) throws Exception {
		MyHashMap myHashMap = new MyHashMap();
		
		Set<Entry<String, String>> set = v.entrySet();
		for (Entry<String, String> entry : set) {
			entry.getKey();
			MyHashMap.MyHashMapEntry myEntry = 
				new MyHashMap.MyHashMapEntry(entry.getKey(), entry.getValue());
			myHashMap.getMyHashMapEntries().add(myEntry);
		}
		return myHashMap;
	}

}
