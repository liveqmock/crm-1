/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DictionayManagerTest.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author pgj
 * @version 0.1 2013-11-2
 */
package com.deppon.crm.module.customer.server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.deppon.crm.module.common.server.dao.IDetailDao;
import com.deppon.crm.module.common.server.dao.IHeadDao;
import com.deppon.crm.module.common.server.manager.IDetailManager;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.customer.server.manager.impl.DictionaryManager;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.shared.exception.DictionaryException;
import com.deppon.crm.module.customer.shared.exception.DictionnaryExcetionType;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCache;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.IRemoteCacheStore;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;

/**
 * <p>
 * Description:数据字典manager测试类<br />
 * </p>
 * 
 * @title DictionayManagerTest.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author pgj
 * @version 0.1 2013-11-2
 */

public class DictionaryManagerTest extends TestCase {
	DictionaryManager dictionaryManager = new DictionaryManager();
	
		
		

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		CacheManager.getInstance().shutdown();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		CacheManager.getInstance().shutdown();

		Mockery mock = new Mockery();
		DataDictionaryCache cache = new DataDictionaryCache();
		DataDictionaryCacheProvider cacheProvider = new DataDictionaryCacheProvider();
		final IHeadDao headDao = mock.mock(IHeadDao.class);
		final IDetailDao detailDao = mock.mock(IDetailDao.class);
		final IRemoteCacheStore<String, Head> store = mock
				.mock(IRemoteCacheStore.class);
		mock.checking(new Expectations() {
			{
				allowing(headDao).getLastModifyTime();
				will(returnValue(new Date()));

				allowing(detailDao).getLastModifyTime();
				will(returnValue(new Date()));

				List<Detail> details = new ArrayList<Detail>();
				Detail detail = new Detail();
				detail.setCode("SXX");
				detail.setCodeDesc("芙蓉王");
				details.add(detail);
				allowing(store).get(DataHeadTypeEnum.SECOND_TRADE.toString());
				will(returnValue(details));
			}
		});

		cacheProvider.setHeadDao(headDao);
		cacheProvider.setDetailDao(detailDao);
		cache.setCacheProvider(cacheProvider);
		cache.setCacheStorage(new RedisCacheStorage<String, Head>());
		CacheManager.getInstance().registerCacheProvider(cache);
		UserUtil.setUserToAdmin();
		Mockery detailMock = new Mockery();
		final IDetailManager detailManager = detailMock
				.mock(IDetailManager.class);
		dictionaryManager.setDetailManager(detailManager);

		final Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
		Map<String, String> details = new HashMap<String, String>();
		details.put("t恤", "t恤1");
		result.put("t恤", details);
		Map<String, String> details1 = new HashMap<String, String>();
		details1.put("裤子", "裤子1");
		result.put("裤子", details1);
		Map<String, String> details2 = new HashMap<String, String>();
		details2.put("鞋子", "鞋子1");
		result.put("鞋子", details2);

		final Map<String, Map<String, String>> result2 = new HashMap<String, Map<String, String>>();
		result2.put("t恤", details);

		detailMock.checking(new Expectations() {
			{
				allowing(detailManager).queryMapCodeDesc(
						DataHeadTypeEnum.SECOND_TRADE.toString(), "'裤子'");
				will(returnValue(null));
				allowing(detailManager).queryMapCodeDesc(
						DataHeadTypeEnum.SECOND_TRADE.toString(), "'t恤'");
				will(returnValue(null));
				allowing(detailManager).queryMapCodeDesc(
						DataHeadTypeEnum.SECOND_TRADE.toString(), "'t恤','裤子'");
				will(returnValue(result2));
				allowing(detailManager).queryMapCodeDesc(
						DataHeadTypeEnum.SECOND_TRADE.toString(),
						"'t恤','裤子','鞋子'");
				will(returnValue(result));

				allowing(detailManager).queryMapCodeDesc(
						DataHeadTypeEnum.SECOND_TRADE.toString(),
						"'t恤','裤子','鞋子'");
				will(returnValue(result));

				allowing(detailManager).queryMapCodeDesc(
						DataHeadTypeEnum.SECOND_TRADE.toString(), "'袜子','裤子'");
				will(returnValue(new HashMap<String, Map<String, String>>()));

				allowing(detailManager).queryMapCodeDesc(
						DataHeadTypeEnum.SECOND_TRADE.toString(), "'袜子','长裤子'");
				Map<String, String> details4 = new HashMap<String, String>();
				details4.put("长裤子", "长裤子1");
				final Map<String, Map<String, String>> result3 = new HashMap<String, Map<String, String>>();
				result3.put("长裤子", details4);
				will(returnValue(result3));

				allowing(detailManager).insertDetail(with(any(Detail.class)));
				allowing(detailManager).delDetail(with(any(Detail.class)));

				List<Detail> details2 = new ArrayList<Detail>();
				Detail detail2 = new Detail();
				detail2.setCode("xxx");
				detail2.setCodeDesc("芙蓉王");
				details2.add(detail2);
				allowing(detailManager).queryDetail(with(any(Detail.class)));
				will(returnValue(details2));
				
				List<Detail> details = new ArrayList<Detail>();
				Detail detail = new Detail();
				detail.setCode("SXX");
				detail.setCodeDesc("芙蓉王");
				detail.setParentId("xxd2");
				details.add(detail);

				allowing(detailManager).queryDetail(with(any(Detail.class)));
				will(returnValue(details));


			}
		});
	}

	/**
	 * 
	 * <p>
	 * Description:修改方法测试<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-2 void
	 */
	@Test
	public void testUpdateSecondTrade() {
		List<Detail> saveDetailLists = new ArrayList<Detail>();
		Detail save1 = new Detail();
		save1.setCode("t-shirt");
		// save1.setCodeDesc("t恤");
		save1.setCodeType("CLOTH");
		saveDetailLists.add(save1);

		List<Detail> modifyDetailLists = new ArrayList<Detail>();
		Detail modify1 = new Detail();
		modify1.setCode("KUZI");
		modify1.setCodeDesc("裤子");
		modify1.setCodeType("CLOTH");
		modifyDetailLists.add(modify1);

		List<Detail> deleteDetailLists = new ArrayList<Detail>();
		Detail delete1 = new Detail();
		delete1.setCode("XIEZI");
		delete1.setCodeDesc("鞋子");
		delete1.setCodeType("CLOTH");
		deleteDetailLists.add(delete1); 
		// 新增codeDesc为空
		try {
			dictionaryManager.updateSecondTrade(saveDetailLists,
					modifyDetailLists, deleteDetailLists);
		} catch (DictionaryException e) {
			assertEquals(e.getErrorCode(),
					DictionnaryExcetionType.CODE_DESC_IS_NULL.getErrCode());
		}

		// 修改codeDesc为空
		save1.setCodeDesc("t恤");
		modify1.setCodeDesc(null);
		try {
			dictionaryManager.updateSecondTrade(saveDetailLists,
					modifyDetailLists, deleteDetailLists);
		} catch (DictionaryException e) {
			assertEquals(e.getErrorCode(),
					DictionnaryExcetionType.CODE_DESC_IS_NULL.getErrCode());
		}

		// 新增的已经存在
		modify1.setCodeDesc("裤子");
		try {
			dictionaryManager.updateSecondTrade(saveDetailLists,
					modifyDetailLists, deleteDetailLists);
		} catch (DictionaryException e) {
			assertEquals(e.getErrorCode(),
					DictionnaryExcetionType.CODE_DESC_EXITS.getErrCode());
		}

		// 修改的存在
		save1.setCodeDesc("袜子");
		save1.setCode("WAZI");
		modify1.setCodeDesc("长裤子");
		try {
			dictionaryManager.updateSecondTrade(saveDetailLists,
					modifyDetailLists, deleteDetailLists);
		} catch (DictionaryException e) {
			assertEquals(e.getErrorCode(),
					DictionnaryExcetionType.CODE_DESC_EXITS.getErrCode());
		}

		// 正常
		modify1.setCodeDesc("裤子");
//		dictionaryManager.updateSecondTrade(saveDetailLists, modifyDetailLists,
//				deleteDetailLists);
	}

	/**
	 * 
	 * <p>
	 * Description:根据一级行业查询二级行业测试<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-2 void
	 */
	@Test
	public void testSearchSecondTradeByParentId() {
		// 一级行业为空
		try {
			dictionaryManager.searchSecondTradeByParentId("", true);
		} catch (DictionaryException e) {
			assertEquals(
					DictionnaryExcetionType.CODE_DESC_IS_NULL.getErrCode(),
					e.getErrorCode());
		}

		try {
			dictionaryManager.searchSecondTradeByParentId("", false);
		} catch (DictionaryException e) {
			assertEquals(
					DictionnaryExcetionType.CODE_DESC_IS_NULL.getErrCode(),
					e.getErrorCode());
		}

		dictionaryManager.searchSecondTradeByParentId("xxd", true);

//		 dictionaryManager.searchSecondTradeByParentId("xxd", false);
	}

	/**
	 * 
	 * <p>
	 * Description:根据一级行业查询二级行业测试<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-2 void
	 */
	@Test
	public void testSearchDictionary() {
		try {
			dictionaryManager.searchDictionary("");
		} catch (DictionaryException e) {
			assertEquals(
					DictionnaryExcetionType.CODE_DESC_IS_NULL.getErrCode(),
					e.getErrorCode());
		}

		try {
			dictionaryManager.searchDictionary("xxx");
		} catch (DictionaryException e) {
			assertEquals(
					DictionnaryExcetionType.CODE_DESC_IS_NULL.getErrCode(),
					e.getErrorCode());
		}
	}
	/**
	 * 
	 * <p>
	 * Description:根据一级行业查询二级行业测试<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-2 void
	 */
	@Test
	public void testSearchMonthValidSecondTrade() {
		try {
			dictionaryManager.searchMonthValidSecondTrade("");
		} catch (DictionaryException e) {
			assertEquals(
					DictionnaryExcetionType.CODE_DESC_IS_NULL.getErrCode(),
					e.getErrorCode());
		}
	}
}
