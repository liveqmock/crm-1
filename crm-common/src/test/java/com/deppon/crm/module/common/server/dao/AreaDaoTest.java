/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AreaDaoTest.java
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
import com.deppon.crm.module.common.server.dao.impl.DetailDao;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
/**   
 * <p>
 * Description:AreaDao测试类<br />
 * </p>
 * @title AreaDaoTest.java
 * @package com.deppon.crm.module.common.dao 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */

public class AreaDaoTest extends TestCase {
	private AreaDao areaDao;
	private DetailDao detailDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Override
	protected void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			areaDao = ctx.getBean(AreaDao.class);
			detailDao = ctx.getBean(DetailDao.class);
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
	public void testGetAreaByCity(){
		//传入西安市的id查询其区域
		String cityId = "1";//"2974"
		Assert.assertTrue((areaDao.getAreaByCity(cityId)).size()>0);
		for(Area a:areaDao.getAreaByCity(cityId)){
			System.out.println(a.getProvinceId()+"-"+a.getCityName()+"-"+a.getName());
		}
	}
	
	public void testInsertArea(){
		Area area=new Area();
		area.setCreateDate(new Date());
		area.setCreateUser("1");
		area.setModifyDate(new Date());
		area.setModifyUser("2");
		area.setName("11");
		area.setNumber("22");
		area.setStatus("1");
		area.setAreaID(new BigDecimal("1111"));
		area.setIsCityLevel("0");
	/**	City city=new City();
		city.setFid(new BigDecimal("22"));
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
		area.setCity(city); **/
		area.setCityId(1111);
		areaDao.insertArea(area); 
		assertNotNull(area.getFid());
		assertNotNull(area.getAreaID());
		assertNotNull(area.getIsCityLevel());
		areaDao.deleteArea(area);
	}
	
	public void testDeleteArea(){
		Area area=new Area();
		area.setCreateDate(new Date());
		area.setCreateUser("1");
		area.setModifyDate(new Date());
		area.setModifyUser("2");
		area.setName("11");
		area.setNumber("22");
		area.setStatus("1");
		area.setAreaID(new BigDecimal("1111"));
		area.setIsCityLevel("0");
	/**	City city=new City();
		city.setFid(new BigDecimal("22"));
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
		city.setCityID(new BigDecimal("1111")); **/
		area.setCityId(1111);
		areaDao.insertArea(area);
		assertNotNull(area.getFid());
		areaDao.deleteArea(area);
	}
	
	public void testUpdateArea(){
		Area area=new Area();
		area.setCreateDate(new Date());
		area.setCreateUser("1");
		area.setModifyDate(new Date());
		area.setModifyUser("2");
		area.setName("11");
		area.setNumber("22");
		area.setStatus("1");
		area.setAreaID(new BigDecimal("1111"));
		area.setIsCityLevel("0");
		/**
		City city=new City();
		city.setFid(new BigDecimal("22"));
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
		area.setCity(city); **/
		area.setCityId(1111);
		areaDao.insertArea(area);
		area.setName("ccddeeff");
		areaDao.updateArea(area);
		Area queryArea=areaDao.getAreaById(area.getFid());
		assertEquals(queryArea.getName(),"ccddeeff");
		areaDao.deleteArea(area);
	}
	
	public void testSearchAreasByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("宝清县");
		List<Area> list=areaDao.searchAreasByName(condition);
		assertEquals(list.get(0).getName(),"宝清县");
		condition.setLimit(null);
		areaDao.searchAreasByName(condition);
		condition.setLimit(0);
		areaDao.searchAreasByName(condition);
	}
	
	public void testSearchAreasCountByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("宝清县");
		Integer count=areaDao.searchAreasCountByName(condition);
		assertEquals(count>0,true);
		detailDao.getLastModifyTime();
	}
	
/*	public void testZhengZe(){
		String words = "内蒙股";
		String regx = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
		Pattern pattern = Pattern.compile(regx);  
        boolean tf = pattern.matcher(words).matches();   
        System.out.println(tf);  
	}*/
}
