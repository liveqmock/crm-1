/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProvinceDaoTest.java
 * @package com.deppon.crm.module.common.dao 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.ProvinceDao;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.Province;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProvinceDaoTest.java
 * @package com.deppon.crm.module.common.dao 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */

public class ProvinceDaoTest extends TestCase{

	private static ProvinceDao provinceDao;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"DaoBeanTest.xml"};
	
	static {
		try{
		if(ctx ==null){
			ctx = new ClassPathXmlApplicationContext(xmls);
		}
		
		provinceDao = ctx.getBean(ProvinceDao.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	protected void setUp() throws Exception {

	}
	
	public void testGetAllProvince(){
		Assert.assertTrue(provinceDao.getAllProvince().size()>0);
	}
	
	public void testInsertProvince(){
		Province province =new Province();
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
		provinceDao.insertProvince(province);
		assertNotNull(province.getFid());
		provinceDao.deleteProvince(province);
		/*try{
		
		}catch(Exception e){
			e.printStackTrace();
		}
		assertNotNull(province.getFid());*/
	}
	
	public void testDeleteProvince(){
		Province province =new Province();
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
		provinceDao.insertProvince(province);
		assertNotNull(province.getFid());
		provinceDao.deleteProvince(province);
	}
	
	public void testGetProvinceById(){
		Province province =new Province();
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
		provinceDao.insertProvince(province);
		Province queryProvince=provinceDao.getProvinceById(province.getFid());
		assertNotNull(queryProvince.getFid());
		provinceDao.deleteProvince(province);
	}
	
	public void testUpdateProvince(){
		Province province =new Province();
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
		provinceDao.insertProvince(province);
		province.setName("aabbccddee");
		provinceDao.updateProvince(province);
		Province queryProvince=provinceDao.getProvinceById(province.getFid());
		assertEquals(queryProvince.getName(),"aabbccddee");
		provinceDao.deleteProvince(province);
	}
	
	public void testSearchProvincesByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("北京");
		List<Province> list=provinceDao.searchProvincesByName(condition);
		assertNotNull(list);
		condition.setLimit(0);
		condition.setStart(0);
		provinceDao.searchProvincesByName(condition);
		condition.setLimit(null);
		condition.setStart(0);
		condition.setName("北京");
		provinceDao.searchProvincesByName(condition);
	}
	
	public void testSearchProvincesCountByName(){
		AreaSearchCondition condition=new AreaSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setName("北京");
		Integer count=provinceDao.searchProvincesCountByName(condition);
		assertEquals(count>0,true);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-4
	 * void
	 */
	public void testGetProvincesByCityId(){
		Province p=provinceDao.getProvincesByCityId(1);
		assertNotNull(p);
	}
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的省份信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：省份信息
	 * */
	public void testQueryVaildProvinceByNum(){
		Province province = provinceDao.queryVaildProvinceByNum("810000");
		assertNotNull (province);
	}
}
