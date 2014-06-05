package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;
import com.deppon.foss.framework.shared.util.string.StringUtil;


public class GoodTradeMappingDaoTest extends BeanUtil{
	private GoodTradeMappingSearchCondition conition;
	@Before
	public void setUp(){
		conition = new GoodTradeMappingSearchCondition();
		conition.setStart(0);
		conition.setLimit(10);
	}
	@Test
	public void testSearchGoodTradeMappingByCondition(){
		List list = goodTradeMappingDao.searchGoodTradeMappingByCondition(conition);
		Assert.assertFalse(listIsNull(list));
		Integer count = goodTradeMappingDao.countGoodTradeMappingByCondition(conition);
		Assert.assertNotNull(count);
	}
	@Test
	public void testUpdateGoodTradeMapping(){
		List<GoodTradeMapping> list = goodTradeMappingDao.searchGoodTradeMappingByCondition(conition);
		if(StringUtil.isEmpty(list.get(0).getModifyUser())){
			list.get(0).setModifyUser("1");
		}
		goodTradeMappingDao.updateGoodTradeMapping(list.get(0));
	}
	private Boolean listIsNull(List list){
		if(list==null||list.size()==0){
			return true;
		}
		return false;
	}

}
