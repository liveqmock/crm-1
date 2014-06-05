/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankInfoManagerTest.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-26
 */
package com.deppon.crm.module.common.server.manager;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.manager.impl.AbCityManager;
import com.deppon.crm.module.common.server.manager.impl.BankInfoManager;
import com.deppon.crm.module.common.shared.domain.BankView;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankInfoManagerTest.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-26
 */

public class BankInfoManagerTest extends TestCase {
	private static IBankInfoManager bankInfoManager;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"CommonBeanTest.xml"};

	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			bankInfoManager = ctx.getBean(BankInfoManager.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetBankInfoByBV(){
		// TODO 无参数时全表查询，数据量很大
		BankView bv = new BankView();
		bv.setProvinceId("wwNiEgEuEADgA4WNwKjeATcYh+k=");
		bv.setCityId("wwNiEgEuEADgA4X8wKjeAU1UgE4=");
		bv.setAccountBranch("河北南皮农村合作");
		bankInfoManager.getBankInfoByBV(bv);
	}
	public void testGetFunction(){
		BankInfoManager bm = new BankInfoManager();
		bm.getBankInfoService();
	}
}
