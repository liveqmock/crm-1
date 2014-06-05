/**   
 * Description:这里写描述<br />
 * @title AreaAddressManagerTest.java
 * @package com.deppon.crm.module.common.manager 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.cache.CityCache;
import com.deppon.crm.module.common.server.manager.impl.AreaAddressManager;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;

/**   
 * Description:这里写描述<br />
 * @title AreaAddressManagerTest.java
 * @package com.deppon.crm.module.common.manager 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public class AreaAddressManagerTest extends TestCase{
//extends TestCase
	private static  AreaAddressManager areaManager;
	private static  CityCache cityCache;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"DaoBeanTest.xml"};

	static BigDecimal provinceId = new BigDecimal(0);
	static BigDecimal citysId = new BigDecimal(0);
	static BigDecimal areasId = new BigDecimal(0);
	
	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			areaManager = ctx.getBean(AreaAddressManager.class);
//			cityCache = ctx.getBean(CityCache.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	protected void setUp() throws Exception {
	}

	@Test
	public void testSearchProvincesByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		//condition.setName("北京");
		Map map=areaManager.searchProvincesByName(condition);
		//assertEquals(list.get(0).getName(),"北京");
	}

	@Test
	public void testSearchCitysByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("攀枝花市");
		Map map=areaManager.searchCitysByName(condition);
		//assertEquals(list.get(0).getName(),"攀枝花市");
	}

	@Test
	public void testSearchAreasByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("宝清县");
		Map map=areaManager.searchAreasByName(condition);
		//assertEquals(list.get(0).getName(),"宝清县");
	}
	/**
	 * 测试获取常用城市类
	 */
	public void testGetCommonCities() {
		Assert.assertNotNull(areaManager.getCommonCities());
	}
	/**
	 * 测试对应省份的城市
	 */
	public void testGetCityByProvince() {
		String provinceId = "2973";
		Assert.assertNotNull(areaManager.getCityByProvince(provinceId));

		provinceId = "";
		Assert.assertNull(areaManager.getCityByProvince(provinceId));
		provinceId = null;
		Assert.assertNull(areaManager.getCityByProvince(provinceId));
		
	}
	/**
	 * 根据城市获取对应区域
	 */
	public void testGetAreaByCity() {
		String cityId = "9";//"2974"
		Assert.assertNotNull(areaManager.getAreaByCity(cityId));

		cityId = "";
		Assert.assertNull(areaManager.getAreaByCity(cityId));

		cityId = null;
		Assert.assertNull(areaManager.getAreaByCity(cityId));
	}
	
	/**
	 * 获取所有省份
	 */
	public void testGetAllProvinceList() {
		Assert.assertNotNull(areaManager.getAllProvinceList().size());
	}
	/**
	 * 根据名称查询
	 */
	public void testSearchCityByName() {
		String param = "xa";
		Assert.assertNotNull(areaManager.searchCityByName(param));

		param = "西安";
		Assert.assertNotNull(areaManager.searchCityByName(param));
		
		param = "";
		Assert.assertNull(areaManager.searchCityByName(param));

		param = null;
		Assert.assertNull(areaManager.searchCityByName(param));
	}
	
	@Test
	public void testSearchProvinceById(){
		String id = "";
		Assert.assertNull(areaManager.searchProvinceById(id));
		id = null;
		Assert.assertNull(areaManager.searchProvinceById(id));
		id = "111";
		Assert.assertNotNull(areaManager.searchProvinceById(id));
	}

	@Test
	public void testSearchCityById(){
		String id = "";
		Assert.assertNull(areaManager.searchCityById(id));
		id = null;
		Assert.assertNull(areaManager.searchCityById(id));
		id = "111";
		Assert.assertNotNull(areaManager.searchCityById(id));
	}

	@Test
	public void testSearchAreaById(){
		String id = "";
		Assert.assertNull(areaManager.searchAreaById(id));
		id = null;
		Assert.assertNull(areaManager.searchAreaById(id));
		id = "111";
		Assert.assertNotNull(areaManager.searchAreaById(id));
	}

	@Test
	public void testSearchProvincesByCityId(){
		Integer id = 11;
		areaManager.searchProvincesByCityId(id);
	}

	@Test
	public void testGetCityByProvinceId(){
		Integer id = 11;
		areaManager.getCityByProvinceId(id);
	}
	
	@Test
	public void testGetAllProvince(){
		areaManager.getAllProvince();
	}
	@Test
	public void testSaveOrUpdateProvince(){
		// 建议和testDeleteProvinces配合执行
		Province p = new Province ();
		// 新增省份
		p.setCreateUser("383579");
		p.setModifyUser("383579");
		p.setStatus("1");
		p.setName("大明王朝");
		p.setPinyin("daming");
		p.setPyjm("DMWC");
		p.setNumber("9999999");

		areaManager.saveOrUpdateProvince(null);
		areaManager.saveOrUpdateProvince(p);
		
		// 修改省份
		p.setModifyUser("383580");
		areaManager.saveOrUpdateProvince(p);
		// 保存新增省份ID
		provinceId = p.getFid();
	}

	@Test
	public void testDeleteProvinces(){
		// 建议和testSaveOrUpdateProvince配合执行
		Province p = new Province ();
		// 删除省份
		List<Province> list = new ArrayList<Province>();
		areaManager.deleteProvinces(null);
		areaManager.deleteProvinces(list);
		List<City> cityList = new ArrayList<City>();
		p.setCityList(null);
		p.setFid(provinceId);
		
		list.add(p);
		areaManager.deleteProvinces(list);
	}
	
	public void testSaveOrUpdateCity(){
		// 建议和testDeleteCitys配合执行
		City c = new City();
		areaManager.saveOrUpdateCity(null);
		areaManager.saveOrUpdateCity(c);
		c.setCreateUser("383579");
		c.setModifyUser("383579");
		c.setStatus("1");
		c.setName("大明王朝市");
		Date date = new Date();
		c.setPinyin("d"+date.getTime());
		c.setPyjm("D"+date.getTime());
		c.setNumber("9999999");
		// 新增市
		areaManager.saveOrUpdateCity(c);
		// 修改市
		c.setModifyUser("383578");
		areaManager.saveOrUpdateCity(c);
		// 保存新增市
		citysId = c.getFid();
		
		areaManager.saveOrUpdateCity(c);
	}
	
	@Test
	public void testDeleteCitys(){
		// 建议和testSaveOrUpdateCity配合执行
		List<City> citys = new ArrayList<City>();
		areaManager.deleteCitys(null);
		areaManager.deleteCitys(citys);
		City c = new City();
		c.setFid(citysId);
		citys.add(c);
		try{
			areaManager.deleteCitys(citys);
		}catch(Exception e){}
	}
	
	@Test
	public void testSaveOrUpdateArea(){
		// 建议和testDeleteAreas配合执行
		Area a = new Area();
		areaManager.saveOrUpdateArea(null);
		a.setCreateUser("383579");
		a.setModifyUser("383579");
		a.setStatus("1");
		a.setName("大明王朝地盘");
		a.setNumber("9999999");
		a.setCityId(0);

		// 新增区域
		areaManager.saveOrUpdateArea(a);
		// 修改市
		a.setModifyUser("383578");
		areaManager.saveOrUpdateArea(a);
		// 保存新增市
		areasId = a.getFid();
	}

	@Test
	public void testDeleteAreas(){
		// 建议和testSaveOrUpdateArea配合执行
		List<Area> areas = new ArrayList<Area>();
		areaManager.deleteAreas(null);
		areaManager.deleteAreas(areas);
		Area a = new Area();
		a.setFid(areasId);
		areas.add(a);
		areaManager.deleteAreas(areas);
	}
}
