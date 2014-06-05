package com.deppon.crm.module.customer.server.testutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
	
	public static final String path ="\\src\\test\\java";
	public static final String sql_postfix = ".txt";
	//TODO 先暂时 耍流氓
	public static final String class_postfix ="Test.java";
	
	
	public static List<SqlEntity> read(Class clazz) throws IOException{
		List<SqlEntity> list = new ArrayList<SqlEntity>();
		
		File file = getFile(clazz,sql_postfix);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String context = null;
		while((context = br.readLine()) != null){
			if(!"".equals(context)){
				SqlEntity sqlEntity = createSqlEntity(context);
				list.add(sqlEntity);
			}
		}
		return list;
	}
	
	public static void write(Class clazz,List<SqlEntity> list) throws IOException{
		File file =getFile(clazz,sql_postfix);
		PrintWriter pw = new PrintWriter(file);
		for (SqlEntity sqlEntity : list) {
			pw.println(sqlEntity);
		}
		pw.close();
	}
	
	public static void write(Class clazz,String context) throws IOException{
		File file =getFile(clazz,class_postfix);
		PrintWriter pw = new PrintWriter(file);
		pw.println(context);
		pw.close();
	}
	
	private static SqlEntity createSqlEntity(String context) {
		SqlEntity sqlEntity = new SqlEntity();
		String[] datas = context.split(",");
		sqlEntity.setClassField(datas[0]);
		sqlEntity.setTableField(datas[1]);
		sqlEntity.setJdbcType(datas[2]);
		return sqlEntity;
	}


	
	public static File getFile(Class clazz,String postfix){
		String absolutePath = new File("").getAbsolutePath();
		String relativePath =clazz.getName().replaceAll("\\.", "\\\\");
		String realPath = absolutePath+path+"\\"+relativePath;
		File newFile = new File(realPath+postfix);
		return newFile;
	}
}
