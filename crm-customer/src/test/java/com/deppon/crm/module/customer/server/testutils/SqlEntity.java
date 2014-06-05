package com.deppon.crm.module.customer.server.testutils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>
 * 数据库映射实体<br />
 * </p>
 * @title SqlEntity.java
 * @package com.deppon.crm.module.customer.server.testutils 
 * @author bxj
 * @version 0.2 2012-3-24
 */
public class SqlEntity {
	//类字段
	private String classField;
	//表映射字段
	private String tableField;
	//字段对应的jdbcType
	private String jdbcType;
	
	public String getClassField() {
		return classField;
	}
	public void setClassField(String classFiled) {
		this.classField = classFiled;
	}
	public String getTableField() {
		return StringUtils.upperCase(tableField);
	}
	public void setTableField(String tableField) {
		this.tableField = tableField;
	}
	public String getJdbcType() {
		return StringUtils.upperCase(jdbcType);
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	
	public String getMybatisValue(){
		return "#{"+getClassField()+",jdbcType="+getJdbcType()+"}";
	}
	
	public String getIfLabel(){
		StringBuffer sb = new StringBuffer();
		sb.append("<if test=\" "+getClassField()+" != null ");
		sb.append(" and ");
		sb.append(getClassField()+" != '' ");
		sb.append(" \">");
		sb.append("\n<![CDATA[,"+getTableField()+"="+getMybatisValue()+"]]>\n");
		sb.append("</if>");
		return sb.toString();
	}
	
	public String getSelectIfLabel(){
		StringBuffer sb = new StringBuffer();
		sb.append("<if test=\" "+getClassField()+" != null ");
		sb.append(" and ");
		sb.append(getClassField()+" != '' ");
		sb.append(" \">");
		sb.append("\n<![CDATA[and "+getTableField()+"="+getMybatisValue()+"]]>\n");
		sb.append("</if>");
		return sb.toString();
	}
	
	public String getResult(){
		StringBuffer sb = new StringBuffer();
		sb.append("<result property=\""+getClassField()+"\" column=\""+getTableField()+"\" jdbcType = \""+getJdbcType()+"\" />\n");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return classField+","+tableField+","+jdbcType;
	}
}
