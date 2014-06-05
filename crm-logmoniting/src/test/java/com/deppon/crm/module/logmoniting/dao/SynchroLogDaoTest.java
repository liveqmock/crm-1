/**
 * @Title: SynchroLogDaoTest.java
 * @Package com.deppon.crm.module.logmoniting.dao
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-17 上午8:43:06
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testutils.DataTestUtil;

import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;
import com.deppon.crm.module.logmoniting.utils.BeanUtils;

/**
 * <p>
 * TODO<br />
 * </p>
 * @title SynchroLogDaoTest.java
 * @package com.deppon.crm.module.logmoniting.dao
 * @author 唐亮
 * @version 0.1 2013-9-17
 */
public class SynchroLogDaoTest extends BeanUtils{
	/*
	 * 初始化测试数据
	 */
	@Before
	public void setUp()throws Exception{
		DataTestUtil.cleanCustLabelData();
		DataTestUtil.initSynchronizeData();
	}
	/*
	 * 清理测试数据
	 */
	@After
	public void tearDown() throws Exception{
		DataTestUtil.cleanCustLabelData();
	}
	/**
	 * 
	 * @Title: testSearchDataSynchroList
	 *  <p>
	 * @Description: 测试客户同步数据查询方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-17
	 * @return void
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testSearchDataSynchroList(){
		SynchronizeLogInfo synchronizeLogInfo = new SynchronizeLogInfo();
		Date startTime = new Date();
		Date finishTime = new Date();
		synchronizeLogInfo.setSynFinishTime(finishTime);
		startTime.setYear(0);
		synchronizeLogInfo.setSynStartTime(startTime);
		synchronizeLogInfo.setTableName("T_TL_TEST");
		synchronizeLogInfo.setKeyWord("-1");
		List<SynchronizeLogInfo> dataList = synchroLogDao.searchDataSynchroList(0, 20, synchronizeLogInfo);
		Assert.assertNotNull(dataList);
	}
	
	/**
	 * 
	 * @Title: testUpdateDataSynchroCondition
	 *  <p>
	 * @Description: 测试客户同步数据修改方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-9-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testUpdateDataSynchroCondition(){
		SynchronizeLogInfo synchronizeLogInfo = new SynchronizeLogInfo();
		Date date = new Date();
		synchronizeLogInfo.setHandleType("N");
		synchronizeLogInfo.setSynStatus("U");
		synchronizeLogInfo.setId("-1");
		synchronizeLogInfo.setModifyUser("105888");
		synchronizeLogInfo.setModifyDate(date);
		
		synchroLogDao.updateDataSynchroCondition(synchronizeLogInfo);
		System.out.println();
	}
}
