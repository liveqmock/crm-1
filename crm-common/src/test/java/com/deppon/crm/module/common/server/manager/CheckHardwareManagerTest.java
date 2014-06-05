/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CheckHardwareManagerTest.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-26
 */
package com.deppon.crm.module.common.server.manager;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.manager.impl.CheckHardwareManager;
import com.deppon.crm.module.common.server.manager.impl.CheckHardwareManagerMock;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CheckHardwareManagerTest.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-26
 */

public class CheckHardwareManagerTest extends TestCase {
	private static ICheckHardwareManager checkHardwareManager;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"CommonBeanTest.xml"};
	static String token = "025a625882bd173efa44af7c6570ae09";
	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			checkHardwareManager = ctx.getBean(CheckHardwareManager.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckHardwareInfo(){
		HardWareInfo info = new HardWareInfo();
		String v = "v";
		String s = "s";
		String version = CheckHardwareManager.VERSION; // 默认使用当前版本
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(null, null, null, version), "300_split_ERROR_Code:300");
		// hostname is null
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, null, null, version), "300_split_ERROR_Code:300");
		// hostname is ""
		info.setHostName("");
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, null, null, version), "300_split_ERROR_Code:300");
		// s is null
		info.setHostName("crm024.");
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, null, null, version), "300_split_ERROR_Code:300");
		// s is ""
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, "", null, version), "300_split_ERROR_Code:300");
		// v is null
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, s, null, version), "300_split_ERROR_Code:300");
		// v is ""
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, s, "", version), "300_split_ERROR_Code:300");
		// v is null
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, s, v, version), "300_split_ERROR_Code:999");
		v="æ";
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, s, v, version), "202_split_");
		// 版本号不同
		Assert.assertEquals(checkHardwareManager.checkHardwareInfo(info, s, v, version+"1"), "203_split_");
		// falg is FALSE
		info.setHostName("DP01-155618");
		Assert.assertEquals(
				checkHardwareManager.checkHardwareInfo(info, s, v, version),
				"201_split_");
		// 成功
		info.setHostName("DLZD004");
		String msg = checkHardwareManager.checkHardwareInfo(info, s, v, version);
		token = msg.split("_")[2]; // 返回格式：200_split_xxxxxx，其中xxxx为token
	}
	
	@Test
	public void testCheckHardWareToken(){
		// token is null
		checkHardwareManager.checkHardWareToken(null);
		// token is ""
		checkHardwareManager.checkHardWareToken("");
		// hwToken is null
		String tokenTmp = "123";
		checkHardwareManager.checkHardWareToken(tokenTmp);
		// isUsed is False
		tokenTmp = "3674228884ea63acdcb6b65397ccd4dd";
		checkHardwareManager.checkHardWareToken(tokenTmp);
		// isUsed is True
		tokenTmp = "025a625882bd173efa44af7c6570ae09";
		checkHardwareManager.checkHardWareToken(tokenTmp);
		// 成功
		checkHardwareManager.checkHardWareToken(token);
	}
	
	@Test
	public void testGetFunction(){
		CheckHardwareManager chm = new CheckHardwareManager();
		chm.getiCheckHardWareService();
		chm.getiHardWareTokenService();
	}
	
	@Test
	public void testCheckHardwareManagerMock(){
		HardWareInfo info = new HardWareInfo();
		String v = "v";
		String s = "s";
		String version = CheckHardwareManager.VERSION; // 默认使用当前版本
		
		// 测试CheckHardwareManagerMock，由于没有使用spring注入，因此使用new
		CheckHardwareManagerMock chm = new CheckHardwareManagerMock();
		chm.checkHardwareInfo(info, s, v, version);
		chm.checkHardWareToken(null);
	}
}
