package com.deppon.crm.module.client.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MyHashMap {

	@XmlElement(required=true)
	private List<MyHashMapEntry> myHashMapEntries = new ArrayList<MyHashMap.MyHashMapEntry>();
	
	public List<MyHashMapEntry> getMyHashMapEntries() {
		return myHashMapEntries;
	}
	
	public void setMyHashMapEntries(List<MyHashMapEntry> myHashMapEntries) {
		this.myHashMapEntries = myHashMapEntries;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class MyHashMapEntry{
		@XmlAttribute
		private String key;
		@XmlValue
		private String value;
		
		public MyHashMapEntry() {
		}
		public MyHashMapEntry(String key,String value) {
			this.key = key;
			this.value= value;
		}
		
		public String getKey() {
			return key;
		}
		
		public void setKey(String key) {
			this.key = key;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
	
}
