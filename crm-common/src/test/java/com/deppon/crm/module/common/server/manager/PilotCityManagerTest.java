package com.deppon.crm.module.common.server.manager;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.crm.module.common.server.manager.impl.PilotCityManager;
import com.deppon.crm.module.common.shared.domain.PilotCity;
import com.deppon.foss.framework.server.context.AppContext;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml")
public class PilotCityManagerTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	static {
		AppContext.initAppContext("application", "","");
	}

	@Autowired
	private PilotCityManager pilotCityManager;

	/**
	 * 插入测试数据
	 */
	@Before
	public void setUp() {
		executeSqlScript("fixtures/init_pilot_city.sql", false);
	}
	
	/**
	 * 检查getPilotCity方法是否正确
	 */
	@Test
	public void getPilotCity() {
		PilotCity pilotCity = new PilotCity();
		pilotCity.setCityCode("N101");
		PilotCity city = pilotCityManager.getPilotCity(pilotCity);
		assertEquals("上海", city.getCityName());
		pilotCity.setCityCode("XXX");
		city = pilotCityManager.getPilotCity(pilotCity);
		assertNull(city);
	}

	/**
	 * 用测试数据检查checkPilotCity方法是否正确
	 */
	@Test
	public void checkPilotCity() {
		assertTrue(pilotCityManager.checkPilotCity("N101"));
		assertTrue(pilotCityManager.checkPilotCity("N102"));
		assertFalse(pilotCityManager.checkPilotCity("N103"));
		assertFalse(pilotCityManager.checkPilotCity("N104"));
		assertFalse(pilotCityManager.checkPilotCity("N111"));
	}
	
	/**
	 * 对于表中不存在的记录调用checkPilotCity方法应该返回false
	 */
	@Test
	public void checkNotExistsPilotCity() {
		assertFalse(pilotCityManager.checkPilotCity("N105"));
	}
	
	@Test
	public void checkPilotCityByName() {
		assertTrue(pilotCityManager.checkPilotCityByName("上海"));
		assertTrue(pilotCityManager.checkPilotCityByName("北京"));
		assertFalse(pilotCityManager.checkPilotCityByName("广州"));
		assertFalse(pilotCityManager.checkPilotCityByName("市上海"));
		assertFalse(pilotCityManager.checkPilotCityByName("上海市市"));
		assertFalse(pilotCityManager.checkPilotCityByName("长沙"));
		assertFalse(pilotCityManager.checkPilotCityByName("武汉"));
		assertFalse(pilotCityManager.checkPilotCityByName("上海市"));
		assertFalse(pilotCityManager.checkPilotCityByName("上"));
		assertFalse(pilotCityManager.checkPilotCityByName("海"));
	}
	
	/**
	 * 检查insertPilotCity方法是否正确
	 */
	@Test
	public void insertPilotCityManager() {
		PilotCity pilotCity = new PilotCity();
		pilotCity.setId("5");
		pilotCity.setCityCode("NX302");
		pilotCity.setCityName("长沙");
		pilotCity.setHasAgent("n");
		pilotCity.setIsPilot("y");
		pilotCity.setCreateDate(new Date());
		pilotCity.setModifyDate(new Date());
		pilotCity.setStatus(true);
		pilotCityManager.insertPilotCity(pilotCity);
		assertTrue(pilotCityManager.checkPilotCity(pilotCity.getCityCode()));
	}

	/**
	 * 插入重复主键的试点城市会报错
	 */
	@Test(expected = DuplicateKeyException.class)
	public void insertDuplicateKeyPilotCity() {
		PilotCity pilotCity = new PilotCity();
		pilotCity.setId("1");
		pilotCity.setCityCode("NX302");
		pilotCity.setCityName("长沙");
		pilotCity.setHasAgent("n");
		pilotCity.setIsPilot("y");
		pilotCity.setCreateDate(new Date());
		pilotCity.setModifyDate(new Date());
		pilotCityManager.insertPilotCity(pilotCity);
	}

	/**
	 * 检查updatePilotCity方法是否正确
	 */
	@Test
	public void updatePilotCity() {
		PilotCity pilotCity = new PilotCity();
		pilotCity.setId("1");
		pilotCity.setCityCode("N101");
		pilotCity.setCityName("上海");
		pilotCity.setIsPilot("n");
		pilotCity.setHasAgent("y");
		pilotCity.setCreateDate(new Date());
		pilotCity.setModifyDate(new Date());
		pilotCityManager.updatePilotCity(pilotCity);
		assertFalse(pilotCityManager.checkPilotCity(pilotCity.getCityCode()));
	}

	/**
	 * 检查deletePilotCity方法是否正确
	 */
	@Test
	public void deletePilotCity() {
		pilotCityManager.deletePilotCity("1");
		assertFalse(pilotCityManager.checkPilotCity("N101"));
	}

}
