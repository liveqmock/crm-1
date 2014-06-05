package com.deppon.crm.module.common.shared.domain;

public class MessageReflection {
	private String[] fileds;
	private int     MessageC;
	private Class<?>[] filedType;
	private String[] methodStrs;
	public String[] getFileds() {
		return fileds;
	}
	public void setFileds(String[] fileds) {
		this.fileds = fileds;
	}
	public int getMessageC() {
		return MessageC;
	}
	public void setMessageC(int messageC) {
		MessageC = messageC;
	}
	public Class<?>[] getFiledType() {
		return filedType;
	}
	public void setFiledType(Class<?>[] filedType) {
		this.filedType = filedType;
	}
	public String[] getMethodStrs() {
		return methodStrs;
	}
	public void setMethodStrs(String[] methodStrs) {
		this.methodStrs = methodStrs;
	}
	public String toString(){
		System.out.println(MessageC);
		for(int i=1;i<fileds.length;i++){
			System.out.println(fileds[i]);
			System.out.println(filedType[i]);
			System.out.println(methodStrs[i]);
		}
		return null;
		
	}
}
