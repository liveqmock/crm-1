package com.deppon.crm.module.logmoniting.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.utils.BeanUtils;

public class LogInfoServiceTest  extends BeanUtils{

	@Before
	public void setUP() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSearchAllBaseLogInfoByQuery(){
		Query query = new Query();
		query.addCriteria(Criteria.where("blackList").is(Constant.TrueValue));
		List<BasicLoginfo> basicLoginfos = logInfoService.searchAllBaseLogInfoByQuery(BasicLoginfo.class, query);
		for (BasicLoginfo basicLoginfo : basicLoginfos) {
			System.out.println(basicLoginfo.getUrl() + ":----------" + basicLoginfo.getModulename());
		}
	}
}