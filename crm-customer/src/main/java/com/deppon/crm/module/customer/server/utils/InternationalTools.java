package com.deppon.crm.module.customer.server.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * <p>
 * 国际化工具类，此来国际化的帮助类，半自动化工具，不能保证百分之百的正确性，和百分之百的自动化，加入了一些人工处理操作。<br />
 * </p>
 * @title InternationalTools.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author bxj
 * @version 0.2 2012-5-22
 * <br/>
 * <h3>使用说明:</h3>
 * 1.
 * 2.
 * 3.
 * <br/>
 */
public class InternationalTools {
	
	public static String absolutePath = new File("").getAbsolutePath();
	
	public static String i18n = "\\src\\main\\resources\\com\\deppon\\crm\\module\\customer\\server\\META-INF\\i18n-config\\i18n_config.properties";
	
	public static String message_zh_CN = "\\src\\main\\resources\\com\\deppon\\crm\\module\\customer\\server\\META-INF\\messages\\message_zh_CN.properties";
	
	public static final String Code = "utf-8";
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		international(absolutePath+"\\src\\main\\resources\\com\\deppon\\crm\\module\\customer\\server\\META-INF\\scripts\\membercust\\view\\","MyWorkFlowDealEditView.js",absolutePath+"\\src\\main\\resources\\guojihua.properties");
	}
	
	/**
	 * 
	 * <p>
	 * 国际化<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-22
	 * @param targetFile 被国际化的文件
	 * @param keyFile 国家化的key,value文件位置。
	 * void
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void international(String targetPath,String targetFile,String keyFile) throws FileNotFoundException, IOException{
		//获得key value.
		Map<String,String> map = getNewInternationKVP(keyFile);
		//对i18n_config.properties文件进行写操作。   ----
		writei18n(map);
		//对message_zh_CN.properties文件进行写操作。
		writeMessage_zh_CN(map);
		//对被国家化的js targetFile进行写操作。
		internationJsFile(targetPath,targetFile,map);
	}
	/**
	 * 
	 * <p>
	 * 国际化js文件<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-22
	 * @param targetFile2 
	 * @param map
	 * void
	 * @throws IOException 
	 */
	private static void internationJsFile(String targetPath,String targetFile, Map<String, String> map) throws IOException {
		// 'value'  replace i18n('key')
		if(map.isEmpty()){
			return ;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(targetPath+targetFile),Code));
		StringBuffer sb = new StringBuffer();
		String context = null;
		while((context =br.readLine()) != null){
			sb.append(context+"\n");
		}
		br.close();
		Set<String> keys = map.keySet();
		String allContext = sb.toString();
		for (String key : keys) {
			String value = map.get(key);
			String regex = "'"+value+"'";
			String replacement = "i18n('"+key+"')";
			allContext = allContext.replaceAll(regex, replacement);
		}
		
		//写国际化后的文件
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(targetPath+"international_"+targetFile),Code));
		pw.println(allContext);
		pw.flush();
		pw.close();
	}
	/**
	 * 
	 * <p>
	 * 写Message_zh_CN文件，并对键值对进行过滤，赛选出重复的key，并记log<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-22
	 * @param map
	 * void
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static void writeMessage_zh_CN(Map<String, String> map) throws FileNotFoundException, IOException {
		if(map.isEmpty()){
			return ;
		}
		Properties prop = new Properties();
		String filePath = absolutePath+message_zh_CN;
		InputStreamReader isr = null;
		try{
		 isr = new InputStreamReader(new FileInputStream(filePath),Code);
		prop.load(isr);
		}finally{
			if(isr!=null){isr.close();}
		}
		Set<String> keys = map.keySet();
		List<String> removeKeys = new ArrayList<String>();
		for (String key : keys) {
			if(prop.getProperty(key) == null){
				prop.setProperty(key, map.get(key));
			}else{
				System.out.println(key+"已经存在，请仔细检查,阁下写的键值对为:   "+key+":"+map.get(key));
				removeKeys.add(key);
			}
		}
		for (String key : removeKeys) {
			map.remove(key);
		}
		OutputStreamWriter osw = null;
		osw = new OutputStreamWriter(new FileOutputStream(filePath),Code);
		try{
		prop.store(osw,"国际化");
		}finally{
			if(osw!=null){
				osw.close();
			}
		}

		
	}
	/**
	 * 
	 * <p>
	 * 写i18n文件，并对键值对进行过滤，赛选出重复的key，并记log<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-22
	 * @param map
	 * void
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static void writei18n(Map<String, String> map) throws FileNotFoundException, IOException {
		if(map.isEmpty()){
			return ;
		}
		Properties prop = new Properties();
		String filePath = absolutePath+i18n;
		InputStreamReader isr = null;
		try{
			isr = new InputStreamReader(new FileInputStream(filePath),Code);
		    prop.load(isr);
		}finally{
			if(isr!=null){isr.close();}
		}
		Set<String> keys = map.keySet();
		List<String> removeKeys = new ArrayList<String>();
		for (String key : keys) {
			if(prop.getProperty(key) == null){
				//TODO 该文件只有 key 待测试  并且 修改
				prop.setProperty(key,"");
			}else{
				System.out.println(key+"已经存在，请仔细检查,阁下写的键值对为:   "+key+":"+map.get(key));
				removeKeys.add(key);
			}
		}
		for (String key : removeKeys) {
			map.remove(key);
		}
		OutputStreamWriter osw = null;
		osw = new OutputStreamWriter(new FileOutputStream(filePath),Code);
		try{
		prop.store(osw,"国际化");
		}finally{
			if(osw!=null){
				osw.close();
			}
		}
		
	}
	/**
	 * 
	 * <p>
	 * 得到新的国家化的键值对<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-22
	 * @param keyFile .properties文件
	 * @return
	 * Map<String,String>
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static Map<String, String> getNewInternationKVP(String keyFile) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		InputStreamReader isr = null;
		try{
			isr = new InputStreamReader(new FileInputStream(keyFile),Code);
		    prop.load(isr);
		}finally{
			if(isr!=null){isr.close();}
		}
		Set<String> keys = prop.stringPropertyNames();
		Map<String,String> kvp = new HashMap<String, String>();
		for (String key : keys) {
			kvp.put(key, prop.getProperty(key));
		}
		return kvp;
	}
	
}
