/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityCacheTest.java
 * @package com.deppon.crm.module.common.cache 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.cache;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.deppon.crm.module.common.server.cache.CityCache;
import com.deppon.crm.module.common.server.cache.CityCacheProvider;
import com.deppon.crm.module.common.server.dao.impl.AreaDao;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.opensymphony.xwork2.interceptor.annotations.Before;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityCacheTest.java
 * @package com.deppon.crm.module.common.cache 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public class CityCacheTest{
// extends TestCase
//	private static ApplicationContext ctx = null;
//	String[] xmls = new String[]{"DaoBeanTest.xml"};
//	
//	protected void setUp() {
//		try{
//			if(ctx==null){
//				ctx = new ClassPathXmlApplicationContext(xmls);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public void testCityCache(){
//		try{
//			ICache<String, Object> cityCache = CacheManager.getInstance().getCache(CityCacheProvider.class.getName());
//			List<Province> provinceList= (List<Province>) cityCache.get("province");
//			List<City> cityList = (List<City>) cityCache.get("city");
//			List<City> defaultCity = (List<City>) cityCache.get("commonCities");
//			System.out.println(provinceList.size());
//			System.out.println(cityList.size());
//			System.out.println(defaultCity.size());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
