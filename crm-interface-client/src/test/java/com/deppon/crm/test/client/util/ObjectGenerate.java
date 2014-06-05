/*package com.deppon.crm.test.client.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectGenerate {

	
	public static void resultMapSql(Class clazz, String alis)
			throws IOException {
		String fullName = clazz.getName();
		String className = fullName.substring(fullName.lastIndexOf(".") + 1,
				fullName.length());
		// ResultMap
		StringBuffer rmsb = new StringBuffer();
		rmsb.append("<resultMap id=\"" + className + "RM\" type=\"" + fullName
				+ "\">");
		// SQL片段
		StringBuffer sb = new StringBuffer();
		sb.append("<sql id=\"" + className + "_column\">\n\t\t<![CDATA[");
		fieldFor(clazz.getDeclaredFields(), alis, sb, rmsb);
		fieldFor(clazz.getSuperclass().getDeclaredFields(), alis, sb, rmsb);
		sb.replace(sb.lastIndexOf(","), sb.length(), "");
		sb.append("\n\t\t]]>\n</sql>");
		rmsb.append("\n</resultMap>");
		System.out.println(rmsb.toString());
		System.out.println(sb.toString());
	}

	public static void fieldFor(Field[] fieldss, String alis, StringBuffer sb,
			StringBuffer rmsb) {
		for (Field field : fieldss) {
			if (field.getName().equals("serialVersionUID")
					|| field.getType().getName().equals("java.util.List")) {
				continue;
			}
			rmsb.append("\n<result property=\"" + field.getName()
					+ "\" column=\"" + alis + "_F"
					+ field.getName().toUpperCase() + "\"/>");
			sb.append("\n\t\t\t" + alis + ".F" + field.getName().toUpperCase()
					+ " " + alis + "_F" + field.getName().toUpperCase() + ",");
		}
	}

	// 自动生成实体赋值
	public static void pojoInstallValue(Object obj) {
		Class clazz = obj.getClass();
		String clazzName = clazz.getSimpleName();
		String lowName = changeUpperOrLower(clazzName, 1);
		Field[] fields = objectFields(clazz);
		StringBuffer sb = new StringBuffer();
		sb.append(clazzName + " " + lowName + " = new " + clazzName + "();\n");
		for (Field field : fields) {
			String fieldName = changeUpperOrLower(field.getName(), 2);
			String value = getValueByType(field);
			if (fieldName.equals("SerialVersionUID")) {
				continue;
			}
			sb.append(lowName + ".set" + fieldName + "(" + value + ");\n");
		}
		System.out.println(sb.toString());
	}

	// 首字母大写或小写 1小写，2大写
	private static String changeUpperOrLower(String fieldName, int type) {
		if (type == 1) {
			fieldName = fieldName.substring(0, 1).toLowerCase()
					+ fieldName.substring(1, fieldName.length());
		}
		if (type == 2) {
			fieldName = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1, fieldName.length());
		}
		return fieldName;
	}

	// 根据类型返回不同的值
	private static String getValueByType(Field field) {
		String type = field.getType().toString();
		if (type.lastIndexOf(".") > 0)
			type = type.substring(type.lastIndexOf(".") + 1, type.length());
		if (type.equals("long")) {
			type = "Long.valueOf(\"1\")";
		} else if (type.equals("String")) {
			type = "\"1\"";
		} else if (type.equals("Boolean") || type.equals("boolean")) {
			type = "true";
		} else if (type.equals("Double") || type.equals("double")) {
			type = "Double.valueOf(\"1\")";
		} else if (type.equals("int")) {
			type = "1";
		} else if (type.equals("Integer")) {
			type = "Integer.valueOf(\"1\")";
		} else if (type.equals("Date")) {
			type = "new Date()";
		}
		return type;
	}

	// 获得对象的所有字段
	private static Field[] objectFields(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Field[] superFields = clazz.getSuperclass().getDeclaredFields();
		Field[] allFields = new Field[fields.length + superFields.length];
		for (int i = 0; i < superFields.length; i++) {
			allFields[i] = superFields[i];
		}
		for (int i = 0; i < fields.length; i++) {
			allFields[i + superFields.length] = fields[i];
		}
		return allFields;
	}

	// 生成数据字典脚本 1为CODE在前，0为CODE在后
	private static void oracleTxt(String txt, int id, String status,
			int handleType) {
		String sql = "insert into T_CODE_DETAIL(fid,Fparentid,fcodetype,fcode,fcodedesc,fstatus,Fcodeseq,Flanguage,Fcreatetime,Fcreateuserid,Flastupdatetime,Flastmodifyuserid) values(\n";
		StringBuffer sb = new StringBuffer();
		txt.replace(" ", "");
		txt.replace("	", "");
		String[] codes = txt.split("/");
		List<String> list = Arrays.asList(codes);
		for (int i = 1; i <= codes.length;) {
			sb.append(sql + ((i + 1) / 2 + id) + ",0,'" + status + "','");
			if (handleType == 1) {
				sb.append(list.get(i - 1) + "','" + list.get(i) + "','");
			} else {
				sb.append(list.get(i) + "','" + list.get(i - 1) + "','");
			}
			sb.append("A'," + (i + 2) / 2 + ",'zh_CN',sysdate,1,sysdate,1);\n");
			i = i + 2;
		}
		System.out.println(sb.toString());
	}

	// 实体转换成Map
	private static void pojoToMap(Class clazz) {
		Field[] fields = objectFields(clazz);
		StringBuffer sb = new StringBuffer();
		String className = changeUpperOrLower(clazz.getSimpleName(), 1);
		sb.append("Map<String,Object> map = new HashMap<String,Object>();\n");
		for (Field field : fields) {
			Map<String, Object> map = new HashMap<String, Object>();

			sb.append("map.put(\"" + field.getName() + "\"," + className
					+ ".get" + changeUpperOrLower(field.getName(), 2)
					+ "());\n");
		}
		System.out.println(sb.toString());
	}
}
*/