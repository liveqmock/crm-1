/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CheckHardWareDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */
package com.deppon.crm.module.common.server.dao;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.shared.domain.HardWareInfo;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CheckHardWareDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */

public class CheckHardWareDaoTest extends TestCase{
	private ICheckHardWareDao checkHardWareDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			checkHardWareDao = ctx.getBean(ICheckHardWareDao.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public ICheckHardWareDao getCheckHardWareDao() {
		return checkHardWareDao;
	}
	public void setCheckHardWareDao(ICheckHardWareDao checkHardWareDao) {
		this.checkHardWareDao = checkHardWareDao;
	}
	
	@Test
	public void testGetHardWareInfo(){
		HardWareInfo i = new HardWareInfo();
		i.setHostName("SZDLS03");
		i.setCPUNumber("BFEBFBFF00010676");
		i.setMACAddress("00:23:AE:99:C7:56");
		HardWareInfo info = checkHardWareDao.getHardWareInfo(i);
		System.out.println(info);
	}
	
	@Test
	public void testGetAllHardWareInfo (){
	    checkHardWareDao.getAllHardWareInfo();
	}
	
	@Test
    public void testGetLastModifyTime(){
        checkHardWareDao.getLastModifyTime();
    }
}
