/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title HardWareTokenDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-14
 */
package com.deppon.crm.module.common.server.dao;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.shared.domain.HardWareToken;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title HardWareTokenDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-14
 */

public class HardWareTokenDaoTest extends TestCase{
	private IHardWareTokenDao hardWareTokenDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			hardWareTokenDao = ctx.getBean(IHardWareTokenDao.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testSave(){
		HardWareToken token = new HardWareToken();
		token.setId(String.valueOf( System.currentTimeMillis()));
		token.setExpireTime(new Date());
		token.setUsed(false);
		token.setToken(String.valueOf( System.currentTimeMillis()));
		hardWareTokenDao.save(token);
	}
	
	public void testSearch(){
		HardWareToken hwt1 = hardWareTokenDao.getHardWareTokenById("31212");
		HardWareToken hwt2 = hardWareTokenDao.getHardWareTokenByToken("31245");
	}
	
	public void testUpdate(){
		HardWareToken hwt1 = hardWareTokenDao.getHardWareTokenById("31212");
		hwt1.setUsed(!hwt1.isUsed());
		hardWareTokenDao.update(hwt1);
		hwt1.setToken("jone");
		hardWareTokenDao.update(hwt1);
	}
}
