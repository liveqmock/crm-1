/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityCacheDealTest.java
 * @package com.deppon.crm.module.common.cache 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.cache;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.cache.CityCacheDeal;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityCacheDealTest.java
 * @package com.deppon.crm.module.common.cache 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public class CityCacheDealTest {
//extends TestCase 
//
//	private static ApplicationContext ctx = null;
//	String[] xmls = new String[]{"DaoBeanTest.xml"};
//	
//	protected void setUp() {
//		try{
//			if(ctx == null )
//			{
//				ctx = new ClassPathXmlApplicationContext(xmls);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 测试获取默认城市
//	 */
//	public void testGetCommonCities(){
//		CityCacheDeal cityCacheDeal = new CityCacheDeal();
//		Assert.assertTrue(cityCacheDeal.getCommonCities().size()==6);
//	}
//	
//	/**
//	 * 测试获取所有省份
//	 */
//	public void testGetAllProvinceList(){
//		CityCacheDeal cityCacheDeal = new CityCacheDeal();
//		Assert.assertTrue(cityCacheDeal.getAllProvinceList().size()==34);
//		for(Province p:cityCacheDeal.getAllProvinceList()){
//			System.out.println(p.getName());
//		}
//	}
//	/**
//	 * 获取对应省份的城市
//	 */
//	public void testGetCityByProvince(){
//		String provinceId = "2973";
//		CityCacheDeal cityCacheDeal = new CityCacheDeal();
//		Assert.assertTrue(cityCacheDeal.getCityByProvince(provinceId).size()>1);
//	}
//	/**
//	 * 获取根据名称查询的城市
//	 */
//	public void testSearchCityByChinese(){
//		String name = "内蒙古";
//		CityCacheDeal cityCacheDeal = new CityCacheDeal();
//		Assert.assertTrue(cityCacheDeal.searchCityByChinese(name).size()==12);
//		for(City c:cityCacheDeal.searchCityByChinese(name)){
//			System.out.println(c.getName());
//		}
//	}
//	/**
//	 * 根据拼音简称和拼音获取城市
//	 */
//	public void testSearchCityByEngLish(){
//		String name = "sz";
//		CityCacheDeal cityCacheDeal = new CityCacheDeal();
//		//Assert.assertTrue(cityCacheDeal.searchCityByEnglish(name).size()==12);
//		for(City c:cityCacheDeal.searchCityByEnglish(name)){
//			System.out.println(c.getName());
//		}
//	}
	
//	public void testSearchCityById(){
//		String id = "2974";
//		CityCacheDeal cityCacheDeal = new CityCacheDeal();
//		Assert.assertTrue(cityCacheDeal.searchCityById(id).size()==1);
//	}
//	
//	public void testSearchProvinceById(){
//		String id = "2610";
//		CityCacheDeal cityCacheDeal = new CityCacheDeal();
//		Assert.assertTrue(cityCacheDeal.searchProvinceById(id).size()==1);
//	}
}
