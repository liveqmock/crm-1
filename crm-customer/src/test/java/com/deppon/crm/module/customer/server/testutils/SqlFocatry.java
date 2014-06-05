package com.deppon.crm.module.customer.server.testutils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
//TODO 生成ResultMap
//TODO 生成全部的update语句
public class SqlFocatry {
	
	private String tableName;
	//过滤字段
	private static List<String> updatefilters = new ArrayList<String>();
	
	private static List<String> insertfilters = new ArrayList<String>();
	
	static{
		updatefilters.add("modifyUser");
		updatefilters.add("createDate");
		updatefilters.add("createUser");
		updatefilters.add("modifyDate");
		
		insertfilters.add("modifyDate");
		insertfilters.add("modifyUser");

	}
	
	public SqlFocatry(String tableName) {
		this.tableName = tableName;
	}
	
	public String cerateInsertSql(List<SqlEntity> list,boolean isTest){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into "+tableName+"(\n");
		sb.append(getParameter(list));
		sb.append(")\n values(\n");
		if(isTest){
			sb.append(getTestValues(list));
		}else{
			sb.append(getInsertValues(list));
		}
		sb.append("\n)");
		return sb.toString();
	}

	public String createSelectSqlLabel(Class clazz,List<SqlEntity> list){
		String id = getSqlId(clazz);
		StringBuffer sb = new StringBuffer();
		sb.append("<sql id=\""+id+"\">\n");
		for (int i = 0; i < list.size(); i++) {
			SqlEntity sqlEntity = list.get(i);
			if(i == list.size() -1){
				sb.append(sqlEntity.getTableField()+"\n");
			}else{
				sb.append(sqlEntity.getTableField()+",\n");
			}
		}
		sb.append("</sql>");
		return sb.toString();
	}
	
	
	public String cerateInsertSql(List<SqlEntity> list){
		return cerateInsertSql(list, false);
	}

	private String getParameter(List<SqlEntity> list) {
		StringBuffer sb = new StringBuffer();
		int i=1;
		for (SqlEntity sqlEn : list) {
			if(!insertfilters.contains(sqlEn.getClassField())){
				if(i%4 == 0) sb.append("\n");
				sb.append(sqlEn.getTableField()+",");
				i++;
			}
		}
		return sb.substring(0, sb.length()-1);
	}
	
	private String getInsertValues(List<SqlEntity> list){
		StringBuffer sb = new StringBuffer();
		int i=1;
		for (SqlEntity sqlEn : list) {
			if(!insertfilters.contains(sqlEn.getClassField())){
				if(i%2 == 0) sb.append("\n");
//				String classField =sqlEn.getClassField();
//				String jdbcType = sqlEn.getJdbcType();
				String value = sqlEn.getMybatisValue();
				//CRM个性需求
				if(sqlEn.getClassField().equals("createDate")){
					sb.append("sysdate,");
				}else{
					sb.append(value+",");
				}
				
//				sb.append(value+"#{"+classField+",jdbcType="+jdbcType+"},");
				i++;
			}
		}
		return sb.substring(0, sb.length()-1);
	}
	
	private Object getTestValues(List<SqlEntity> list) {
		StringBuffer sb = new StringBuffer();
		for (SqlEntity sqlEn : list) {
			if(!insertfilters.contains(sqlEn.getClassField())){
				String jdbcType = sqlEn.getJdbcType();
				if(jdbcType.equals("DATE")){
					sb.append("sysdate,");
				}else{
					sb.append("1,");
				}
			}
		}
		return sb.substring(0, sb.length()-1);
	}
	
	public String getIfLable(List<SqlEntity> list){
		StringBuffer sb = new StringBuffer();
		for (SqlEntity sqlEntity : list) {
			if(!updatefilters.contains(sqlEntity.getClassField())){
				sb.append(sqlEntity.getIfLabel()+"\n");
			}
		}
		return sb.toString();
	}
	
	public String createUpdateSql(List<SqlEntity> list){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE");
		sb.append("\n    "+getTableName());
		//CRM 个性代码
		sb.append("\nSET FLASTUPDATETIME = sysdate ");
		sb.append("\n,FLASTMODIFYUSERID=#{modifyUser,jdbcType=VARCHAR}");
		//结束
		sb.append("\n"+getIfLable(list));
		sb.append("WHERE FID = #{id}");
		return sb.toString();
	}
	
	public String createSelectLabel(List<SqlEntity> list,Class clazz){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append("\n<include refid=\""+getSqlId(clazz)+"\"/>");
		sb.append("\nFROM "+getTableName());
		sb.append("\n<where>\n"+getSelectIfLable(list));
		sb.append("</where>\n");
		return sb.toString();
	}
	
	private String getSqlId(Class clazz){
		return clazz.getSimpleName()+"_column";
	}
	
	private String getSelectIfLable(List<SqlEntity> list) {
		StringBuffer sb = new StringBuffer();
		for (SqlEntity sqlEntity : list) {
			sb.append(sqlEntity.getSelectIfLabel()+"\n");
		}
		return sb.toString();
	}

	public String getTableName(){
		return StringUtils.upperCase(tableName);
	}

	public String createResultMap(Class clazz, List<SqlEntity> list) {
		String id = clazz.getSimpleName()+"RM";
		String objName = clazz.getName();
		StringBuffer sb = new StringBuffer();
		sb.append("<resultMap id=\""+id+"\"");
		sb.append("\n type=\""+objName+"\">\n");
		for (SqlEntity sqlEntity : list) {
			sb.append(sqlEntity.getResult());
		}
		sb.append("</resultMap>");
		return sb.toString();
	}

	public String createSelectLabelById(List<SqlEntity> list, Class clazz) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append("\n<include refid=\""+getSqlId(clazz)+"\"/>");
		sb.append("\nFROM "+getTableName());
		sb.append("\nWHERE FID = #{id}");
		return sb.toString();
	}
	
}
