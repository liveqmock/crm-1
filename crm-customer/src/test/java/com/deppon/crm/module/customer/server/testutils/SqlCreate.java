package com.deppon.crm.module.customer.server.testutils;

import java.io.IOException;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;

public class SqlCreate {
	
	public static boolean isTest = false;
	public static boolean isCreatFial = false;
	
	public static void main(String[] args) throws IOException {
//		createSql(Account.class, "t_cust_account");
//		createSql(Member.class, "t_cust_custbasedata");
//		createSql(ShuttleAddress.class, "t_cust_shuttleaddress");
//		createSql(Contact.class, "t_cust_custlinkman");
//		createSql(PreferenceAddress.class, "t_cust_PreferenceAddress");
//		
//		createFile(ChangeMemberDept.class);
////		createFile(WiringIntegRule.class);
////		createFile(ChannelIntegRule.class);
//		
//		
////		createSelectSqlLabel(MemberDemotionList.class);
////		createSql(ProductIntegRule.class, "t_cust_integrule");
////		createIfTable(WorkFlowCondition.class,"123");
//		createResultMap(ProductIntegRule.class);
//		createResultMap(WiringIntegRule.class);
//		createResultMap(ChannelIntegRule.class);
//		createResultMap(AdjustIntegral.class);
//		createResultMap(IntegralConvertGift.class);
//		createResultMap(HandRewardIntegral.class);
//		createSelectSqlLabel(AdjustIntegral.class,"t_cust_integ");
//		createSelectSqlLabel(IntegralConvertGift.class,"t_cust_integ");
//		createSelectSqlLabel(HandRewardIntegral.class,"t_cust_integ");
		
		
//		createAll(IntegralConvertGift.class,"t_cust_integ");
//		createAll(Integral.class,"t_cust_integ");
//		createAll(AdjustIntegral.class,"t_cust_integ");
		createAll(ChangeMemberDept.class,"t_cust_changeMemberdept");

//		createSelectSqlLabel(ProductIntegRule.class);
//		createUpdateSql(ProductIntegRule.class, "t_cust_integrule");
	}
	
	public static void createAll(Class clazz,String tableName) throws IOException{
		createFile(clazz);
		createInsertSql(clazz,tableName);
		System.out.println();
		createResultMap(clazz);
		System.out.println();
		createSelectLabel(clazz,tableName);
		System.out.println();
		createUpdateSql(clazz,tableName);
		System.out.println();
		createSelectSqlLabel(clazz);
		System.out.println();
		createDelete(clazz,tableName);
		System.out.println();
		createSelectLabelById(clazz,tableName);
	}
	
	
	public static void createDelete(Class clazz, String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<delete id=\"delete"+clazz.getSimpleName()+"\" parameterType=\"String\">");
		sb.append("\n  delete "+tableName+" where fid = #{id}");
		sb.append("\n</delete>");
		System.out.println(sb.toString());
	}

	public static void createUpdateSql(Class clazz,String tableName) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<update id=\"update"+clazz.getSimpleName()+"\" parameterType=\""+clazz.getName()+"\">\n");
		SqlFocatry sqlFocatry = new SqlFocatry(tableName);
		List<SqlEntity> list = FileHelper.read(clazz);
		sb.append(sqlFocatry.createUpdateSql(list));
		sb.append("\n</update>");
		System.out.println(sb.toString());
	}

	public static void createInsertSql(Class clazz ,String tableName) throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("<insert id=\"insert"+clazz.getSimpleName()+"\" parameterType=\""+clazz.getName()+"\" >\n");
		sb.append("<selectKey keyProperty=\"id\" order=\"BEFORE\" resultType=\"String\">\n");
		sb.append("select SEQ_ID_"+tableName.replaceAll("[^_]*_[^_]*_","")+".nextval as id from dual\n");
		sb.append("</selectKey>\n");
		SqlFocatry sqlFocatry = new SqlFocatry(tableName);
		List<SqlEntity> list = FileHelper.read(clazz);
		sb.append(sqlFocatry.cerateInsertSql(list,isTest));
		sb.append("\n</insert>");
		System.out.println(sb.toString());
	}
	
	public static void createIfTable(Class clazz,String tableName) throws IOException{
		SqlFocatry sqlFocatry = new SqlFocatry(tableName);
		List<SqlEntity> list = FileHelper.read(clazz);
		System.out.println(sqlFocatry.getIfLable(list));
	}
	
	public static void createFile(Class clazz) throws IOException{
		if(isCreatFial){
			TypeUtil.createFileByClass(clazz);
		}
	}
	
	public static void createResultMap(Class clazz) throws IOException{
		SqlFocatry sqlFocatry = new SqlFocatry(null);
		List<SqlEntity> list = FileHelper.read(clazz);
		System.out.println(sqlFocatry.createResultMap(clazz,list));
	}
	
	public static void createSelectLabel(Class clazz,String tableName) throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("<select id=\"search"+clazz.getSimpleName()+"s\" parameterType=\""+clazz.getName()+"\" resultMap=\""+clazz.getSimpleName()+"RM\">\n");
		SqlFocatry sqlFocatry = new SqlFocatry(tableName);
		List<SqlEntity> list = FileHelper.read(clazz);
		sb.append(sqlFocatry.createSelectLabel(list,clazz));
		sb.append("\n</select>");
		System.out.println(sb.toString());
	}
	
	public static void createSelectSqlLabel(Class clazz) throws IOException{
		SqlFocatry sqlFocatry = new SqlFocatry(null);
		List<SqlEntity> list = FileHelper.read(clazz);
		System.out.println(sqlFocatry.createSelectSqlLabel(clazz, list));
	}
	
	public static void createSelectLabelById(Class clazz,String tableName) throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("<select id=\"get"+clazz.getSimpleName()+"ById\" parameterType=\"string\" resultMap=\""+clazz.getSimpleName()+"RM\">\n");
		SqlFocatry sqlFocatry = new SqlFocatry(tableName);
		List<SqlEntity> list = FileHelper.read(clazz);
		sb.append(sqlFocatry.createSelectLabelById(list,clazz));
		sb.append("\n</select>");
		System.out.println(sb.toString());
	}
	
}
