package com.deppon.crm.module.complaint.server.util;

import java.util.HashMap;
import java.util.Map;

public class BasicInfoConstants {
	/**
	 * 基础数据中查询条件 中的查询类型定义
	 */
	//业务项
	public static final String BASICCONTYPEBUSITEM = "001";
	//业务范围
	public static final String BASICCONTYPEBUSSCOPE = "002";
	//业务类型
	public static final String BASICCONTYPEBUSTYPE = "003";
	/**
	 * 基础资料投诉类型
	 */
	//投诉
	public static final String BASICCOMPTYPECOMPAINT = "COMPLAINT";
	//异常
	public static final String BASICCOMPTYPEUNUSUAL = "UNUSUAL";
	/**
	 * 基础资料Level等级
	 */
	public static final String BASICLEVELBUSSCOPE = "1";
	/**
	 * 基础资料投诉类型字段对应的显示
	 */
	public static final Map<String,String> basicCompTypeMap;
	static{
		basicCompTypeMap = new HashMap<String,String>();
		basicCompTypeMap.put(BASICCOMPTYPECOMPAINT,"投诉");
		basicCompTypeMap.put(BASICCOMPTYPEUNUSUAL, "异常");
	}
}
