package com.deppon.crm.module.duty.server.util;

//TODO 添加容器类
public enum FieldType {
	INT("int","java.lang.Integer"),
	DOUBLE("double","java.lang.Double"),
	CHAR("char","java.lang.Character"),
	STRING("java.lang.String"),
	SHORT("short","java.lang.Short"),
	LONG("long","java.lang.Long"),
	BYTE("byte","java.lang.Byte"),
	FLOAT("float","java.lang.Float"),
	BOOLEAN("boolean","java.lang.Boolean"),
	DATE("java.util.Date"),
	OTHER();
	
	
	private String typeName;
	private String packName;
	
	private FieldType(){
		
	}
	
	private FieldType(String packName) {
		this.packName = packName;
	}
	private FieldType(String typeName,String packName) {
		this.typeName = typeName;
		this.packName = packName;
	}
	public static FieldType getFieldType(String className){
		if(null == className || "".intern() == className.trim().intern()) return null;
		if(className.intern() == INT.typeName.intern() || className.intern() == INT.packName.intern()) return INT;
		if(className.intern() == DOUBLE.typeName.intern() || className.intern() == DOUBLE.packName.intern()) return DOUBLE;
		if(className.intern() == CHAR.typeName.intern() || className.intern() == CHAR.packName.intern()) return CHAR;
		if(className.intern() == STRING.packName.intern()) return STRING;
		if(className.intern() == DATE.packName.intern()) return DATE;
		if(className.intern() == SHORT.typeName.intern() || className.intern() == SHORT.packName.intern()) return SHORT;
		if(className.intern() == LONG.typeName.intern() || className.intern() == LONG.packName.intern()) return LONG;
		if(className.intern() == BYTE.typeName.intern() || className.intern() == BYTE.packName.intern()) return BYTE;
		if(className.intern() == FLOAT.typeName.intern() || className.intern() == FLOAT.packName.intern()) return FLOAT;
		if(className.intern() == BOOLEAN.typeName.intern() || className.intern() == BOOLEAN.packName.intern()) return BOOLEAN;
		return OTHER;
	}
	
}
