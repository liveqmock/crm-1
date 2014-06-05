/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LadingStationDaoTest.java
 * @package com.deppon.crm.module.scheduler.dao 
 * @author ZhuPJ
 * @version 0.1 2012-6-1
 */
package com.deppon.crm.module.scheduler.dao;

import java.util.Date;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.job.BeanContextHelper;
import com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao;
import com.deppon.crm.module.scheduler.shared.domain.LadingStation;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LadingStationDaoTest.java
 * @package com.deppon.crm.module.scheduler.dao 
 * @author ZhuPJ
 * @version 0.1 2012-6-1
 */

public class LadingStationDaoTest {
	
	private ILadingStationJobDao ladingStationJobDao;
	private String createId;
//	private JdbcTemplate jdbc;

	@Before
	public void setUp() throws Exception {
//		jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);
//		jdbc.execute("delete from T_CRM_LADINGSTATION_ZPJ where fid >= 99999000");
		
		ladingStationJobDao = (ILadingStationJobDao) BeanContextHelper.getApplicationContext().getBean(ILadingStationJobDao.class);
		System.out.println(111);
	}

	@After
	public void setDown() throws Exception{
	}
	@Test
	public void testQueryCRMLadingStationByErpID(){
		String id = "Jwa3LAEuEADgJ0fmwKgCZcznrtQ=";
		LadingStation ls = ladingStationJobDao.queryCRMLadingStationByErpID(id);
		System.out.println(ls);
	}

	@Test
	public void testCreateLadingStation(){
		LadingStation ls = new LadingStation();
//		this.initInsLSObj(ls);
//		boolean rs = ladingStationJobDao.createLadingStation(ls);
//		System.out.println(rs);

		this.initUpdLSObj(ls);
		boolean rs1 =ladingStationJobDao.updateLadingStation(ls);
		System.out.println(rs1);
	}

	@Test
	public void testQueryERPLadingStationByDate(){
		Date d = DateUtils.setMonths(new Date(), 2);
		List<LadingStation> ls = ladingStationJobDao.queryERPLadingStationByDate(d);
		
		List<LadingStation> ls2 = ladingStationJobDao.queryERPBusinessDeptByDate(d);
		System.out.println(ls.size());
		System.out.println(ls2.size());
		
	}
	
	
	
	
	// 构造insert LS数据
	private void initInsLSObj(LadingStation ls){
		createId = String.valueOf(new Date().getTime());
//		ls.setId(createId);
		ls.setArea("1");
		ls.setAreaId("11");
		ls.setCityId("22");
		ls.setContact("我爱你中国inst");
		ls.setCreateUser("11469");
		ls.setDeptName("神秘部门X222");
		ls.setDetpAddress("神秘地区地质222");
		ls.setErpId("ERPID111222");
		ls.setIsArrive("1");
		ls.setIsEnable("0");
		ls.setIsHavePDA("0");
		ls.setIsHomeDelivery("1");
		ls.setIsLeave("0");
		ls.setIsOpen("1");
		ls.setIsOutfield("0");
		ls.setIsOutward("0");
		ls.setIsSelfDelivery("1");
		ls.setIsTeam("0");
		ls.setIsTransit("1");
		ls.setModifyUser("11469");
		ls.setOrgId("33");
		ls.setProvinceId("44");
		ls.setRemark(null);
	}

	// 构造upd LS数据
	private void initUpdLSObj(LadingStation ls){
		createId = String.valueOf(new Date().getTime());
		
//		ls.setId(createId);
		ls.setArea(null);
		ls.setAreaId(null);
		ls.setCityId(null);
		ls.setContact("我爱你中国upd");
		ls.setCreateUser("11469");
		ls.setDeptName("神秘部门X222");
		ls.setDetpAddress(null);
		ls.setErpId("qzAKPwEdEADgISZowKgCzMznrtQ=");
		ls.setIsArrive(null);
		ls.setIsEnable(null);
		ls.setIsHavePDA("0");
		ls.setIsHomeDelivery("0");
		ls.setIsLeave(null);
		ls.setIsOpen(null);
		ls.setIsOutfield(null);
		ls.setIsOutward("0");
		ls.setIsSelfDelivery("1");
		ls.setIsTeam(null);
		ls.setIsTransit(null);
		ls.setModifyUser("11469");
		ls.setOrgId("10859");
		ls.setProvinceId(null);
		ls.setRemark("remark222");
	}
	
	
}
