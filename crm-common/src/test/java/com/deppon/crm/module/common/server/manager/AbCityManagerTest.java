/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AbCityManagerTest.java
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
import com.deppon.crm.module.common.shared.domain.AbCitySearchCondition;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AbCityManagerTest.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-26
 */

public class AbCityManagerTest extends TestCase {
	private static IAbCityManager abCityManager;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"CommonBeanTest.xml"};

	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			abCityManager = ctx.getBean(AbCityManager.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectAbCity(){
		AbCitySearchCondition condition = new AbCitySearchCondition();
		abCityManager.selectAbCity(condition);
	}
	
	public void testGetFunction(){
		AbCityManager am = new AbCityManager();
		am.getAbCityService();
	}
}
