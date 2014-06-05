package com.deppon.crm.module.customer.server.testutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * 
 * <p>
 * Description: 此类为测试  国际化手动工具类<br />
 *    由于hudson 的路径问题，一直报空指针异常以及此程序不会在线上运行
 *    故暂时放弃此单元测试用例
 * </p>
 * @title InternationalToolsTest.java
 * @package com.deppon.crm.module.customer.server.testutils 
 * @author 王明明
 * @version 0.1 2012-12-28
 */
public class InternationalToolsTest {
	
	private String targetPath ;
	/**
	 * 
	 * <p>
	 * Description:测试国际化<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	
	public void test_international() throws FileNotFoundException, IOException{

		URL url = InternationalToolsTest.class.getClassLoader().getResource("./com/deppon/crm/module/customer/server/testutils/testInternational.properties");
	    InternationalTools.international(targetPath, "Internal.js", url.getFile());

	}
	
	
	public void setUp() throws IOException{
		String urlPath = InternationalToolsTest.class.getClassLoader().getResource("./test.js").getPath();
		InternationalTools.absolutePath = urlPath.substring(1, urlPath.lastIndexOf("target"));
		 targetPath = InternationalTools.absolutePath+"\\src\\test\\resources\\com\\deppon\\crm\\module\\customer\\server\\META-INF\\";
		InternationalTools.i18n = "\\src\\test\\resources\\com\\deppon\\crm\\module\\customer\\server\\META-INF\\i18n_config.properties";
	    File f = new File(InternationalTools.absolutePath+InternationalTools.i18n);
	    if(f.exists()){
	    	f.delete();
	    }
	    f.createNewFile();
	    
	    InternationalTools.message_zh_CN = "\\src\\test\\resources\\com\\deppon\\crm\\module\\customer\\server\\META-INF\\message_zh_CN.properties";
	    f = new File(InternationalTools.absolutePath+InternationalTools.message_zh_CN);
	    if(f.exists()){
	    	f.delete();
	    }
	    f.createNewFile();
	    
	    f = new File(targetPath+"Internal.js");
	    f.createNewFile();
	    
	}
	
     
	public void tearDown(){

	   File f = new File(InternationalTools.absolutePath+InternationalTools.message_zh_CN);
	    f.delete();
	    
	    f = new File(targetPath+"international_Internal.js");
	    f.delete();
	    
	    f = new File(targetPath+"Internal.js");
	    f.delete();
	   
	    f = new File(InternationalTools.absolutePath+InternationalTools.i18n);
	    f.delete();
	    
	}


}
