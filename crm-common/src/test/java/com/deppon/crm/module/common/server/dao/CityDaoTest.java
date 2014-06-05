/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityDaoTest.java
 * @package com.deppon.crm.module.common.dao 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.AreaDao;
import com.deppon.crm.module.common.server.dao.impl.CityDao;
import com.deppon.crm.module.common.server.dao.impl.ProblemDao;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityDaoTest.java
 * @package com.deppon.crm.module.common.dao 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */

public class CityDaoTest extends TestCase{
	private CityDao cityDao;
	private ProblemDao problemDao;
	private AreaDao areaDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	
	@Override
	protected void setUp() throws Exception {
		try{
			if(ctx==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			areaDao = ctx.getBean(AreaDao.class);
			cityDao = ctx.getBean(CityDao.class);
			problemDao = ctx.getBean(ProblemDao.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试获取对应城市的区域<br />
	 * </p>
	 * @author 毛建强
	 * @version 0.1 2012-3-15
	 * @param 
	 * @return
	 */
	public void testGetCityByProvince(){
		//传入西安市的id查询其区域
		String provinceId = "1";
		Assert.assertTrue((cityDao.getCityByProvince(provinceId)).size()>0);
		for(City a:cityDao.getCityByProvince(provinceId)){
			System.out.println(a.getProvince().getName()+"——"+a.getName());
		}
	}
	/**
	 * 
	 * <p>
	 * Description:获取所有城市信息<br />
	 * </p>
	 * @author 毛建强
	 * @version 0.1 2012-3-15
	 * @param 
	 * @return
	 */
	public void testGetAllCity(){
		Assert.assertTrue((cityDao.getAllCity().size())>0);
	}
	/**
	 * 
	 * <p>
	 * Description:获取最后一次修改时间<br />
	 * </p>
	 * @author 毛建强
	 * @version 0.1 2012-3-15
	 * @param 
	 * @return
	 */
	public void testGetLastModifyTime(){
		Assert.assertTrue(cityDao.getLastModifyTime()!=null);
	}
	
	public void testInserCity(){
		City city=new City();
		city.setCreateDate(new Date());
		city.setCreateUser("1");
		city.setModifyDate(new Date());
		city.setModifyUser("2");
		city.setName("aa");
		city.setNumber("bb");
		city.setPinyin("ccc");
		city.setPyjm("ddd");
		city.setCityNumber("2");
		city.setStatus("1");
		city.setIsDirCity(new Integer(0));
		city.setCityID(new BigDecimal("1111"));
		Province province =new Province();
		province.setFid(new BigDecimal("1234"));
		province.setCreateUser("1");
		province.setCreateDate(new Date());
		province.setModifyDate(new Date());
		province.setModifyUser("2");
		province.setName("aa");
		province.setNumber("bb");
		province.setPinyin("cc");
		province.setPyjm("dd");
		province.setStatus("1");
		province.setProvinceID(new BigDecimal("1111"));
		city.setProvince(province);
		cityDao.insertCity(city);
		assertNotNull(city.getFid());
		assertNotNull(city.getCityID());
		cityDao.deleteCity(city);
	}
	
	public void testDeleteCity(){
		City city=new City();
		city.setCreateDate(new Date());
		city.setCreateUser("1");
		city.setModifyDate(new Date());
		city.setModifyUser("2");
		city.setName("aa");
		city.setNumber("bb");
		city.setPinyin("cc");
		city.setPyjm("dd");
		city.setCityNumber("2");
		city.setStatus("1");
		city.setIsDirCity(0);
		city.setCityID(new BigDecimal("1111"));
		Province province =new Province();
		province.setFid(new BigDecimal("1234"));
		province.setCreateUser("1");
		province.setCreateDate(new Date());
		province.setModifyDate(new Date());
		province.setModifyUser("2");
		province.setName("aa");
		province.setNumber("bb");
		province.setPinyin("cc");
		province.setPyjm("dd");
		province.setStatus("1");
		province.setProvinceID(new BigDecimal("1111"));
		city.setProvince(province);
		cityDao.insertCity(city);
		assertNotNull(city.getFid());
		cityDao.deleteCity(city);
	}
	
	public void testGetCityById(){
		City city=new City();
		city.setCreateDate(new Date());
		city.setCreateUser("1");
		city.setModifyDate(new Date());
		city.setModifyUser("2");
		city.setName("aa");
		city.setNumber("bb");
		city.setPinyin("cc");
		city.setPyjm("dd");
		city.setCityNumber("2");
		city.setStatus("1");
		city.setIsDirCity(0);
		city.setCityID(new BigDecimal("1111"));
		Province province =new Province();
		province.setFid(new BigDecimal("1234"));
		province.setCreateUser("1");
		province.setCreateDate(new Date());
		province.setModifyDate(new Date());
		province.setModifyUser("2");
		province.setName("aa");
		province.setNumber("bb");
		province.setPinyin("cc");
		province.setPyjm("dd");
		province.setStatus("1");
		province.setProvinceID(new BigDecimal("1111"));
		city.setProvince(province);
		cityDao.insertCity(city);
		City queryCity=cityDao.getCityById(city.getFid());
		assertNotNull(queryCity.getFid());
		cityDao.deleteCity(city);
		
	}
	
	public void testUpdateCity(){
		City city=new City();
		city.setCreateDate(new Date());
		city.setCreateUser("1");
		city.setModifyDate(new Date());
		city.setModifyUser("2");
		city.setName("aa");
		city.setNumber("bb");
		city.setPinyin("cc");
		city.setPyjm("dd");
		city.setCityNumber("2");
		city.setStatus("1");
		city.setCityID(new BigDecimal("1111"));
		city.setIsDirCity(0);
		/**
		Province province =new Province();
		province.setFid(new BigDecimal("1234"));
		province.setCreateUser("1");
		province.setCreateDate(new Date());
		province.setModifyDate(new Date());
		province.setModifyUser("2");
		province.setName("aa");
		province.setNumber("bb");
		province.setPinyin("cc");
		province.setPyjm("dd");
		province.setStatus("1");
		province.setProvinceID(new BigDecimal("1111"));
		**/
		city.setProvinceId(2323);
		cityDao.insertCity(city);
		city.setName("aabbccdd");
		city.setIsDirCity(1);
		cityDao.updateCity(city);
		City queryCity=cityDao.getCityById(city.getFid());
		assertEquals(queryCity.getName(),"aabbccdd");
		cityDao.deleteCity(city);
	}

	public void testSearchCitysByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("攀枝花");
		List<City> list=cityDao.searchCitysByName(condition);
		condition.setLimit(null);
		condition.setStart(0);
		condition.setName("攀枝花");
		list=cityDao.searchCitysByName(condition);
		condition.setLimit(0);
		condition.setStart(0);
		condition.setName("攀枝花");
		list=cityDao.searchCitysByName(condition);
	}
	
	public void testSearchCitysCountByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("攀枝花");
		Integer count=cityDao.searchCitysCountByName(condition);
		assertEquals(count>0,true);
	}
	
	public void testGetCityByProvinceId(){
		List<City> cityList=cityDao.getCityByProvinceId(1002);
		assertNotNull(cityList);
	}
	public void testProblemDao(){
		problemDao.queryAllFeedBack();
	}
	
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的城市信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：城市信息
	 * */
	public void testQueryVaildCityByNum(){
		cityDao.queryVaildCityByNum("dddd");
	}
	
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的区域信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：区域信息
	 * */
	public void testQueryVaildAreaByNum(){
		Area area = new Area();
		area.setId("id");
		area.setCreateDate(new Date());
		area.setCreateUser("11");
		area.setModifyDate(new Date());
		area.setModifyUser("11");
		area.setFid(new BigDecimal(1));
		area.setAreaID(new BigDecimal(1));
		area.setIsCityLevel("1");
		area.setLastModifyName("lastModifyName");
		area.setCityName("cityName");
		area.setCityId(Integer.valueOf("1"));
		area.setProvinceId(Integer.valueOf("1"));
		area.setName("name");
		area.setNumber("number");
		area.setStatus("1");
		areaDao.insertArea(area);
//		area.setCity(City);
		areaDao.queryVaildAreaByNum("dd");
		areaDao.deleteArea(area);
	}
}
