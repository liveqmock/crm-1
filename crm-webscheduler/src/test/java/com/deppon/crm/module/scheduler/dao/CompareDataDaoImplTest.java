package com.deppon.crm.module.scheduler.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.server.dao.ICompareDataDao;
import com.deppon.crm.module.scheduler.service.testUtil.SpringTestHelper;
import com.deppon.crm.module.scheduler.shared.domain.CompareMember;

public class CompareDataDaoImplTest {
	ICompareDataDao compareDataDao;

	@Before
	public void init() {
		compareDataDao = SpringTestHelper.get().getBean(ICompareDataDao.class);
	}

	@Test
	public void testQueryMember() {
		Map<String,CompareMember> list = compareDataDao.queryMember();
		System.out.println(list.size());
	}

	@Test
	public void testQueryMemberByAccount() {
		Map<String,CompareMember> list = compareDataDao.queryMemberByAccount();
		System.out.println(list.size());
	}

	@Test
	public void testQueryMemberByContract() {
		Map<String,CompareMember> list = compareDataDao.queryMemberByContract();
		System.out.println(list.size());
	}

	@Test
	public void testQueryMemberByLinkman() {
		Map<String,CompareMember>  list = compareDataDao.queryMemberByLinkman();
		Iterator ss = list.keySet().iterator();
		while(ss.hasNext()){
			System.out.println(ss.next());
		}
		System.out.println(list.keySet().iterator());
	}
	
	@Test
	public void testQueryWorkflow(){
		 compareDataDao.queryContractWorkflow();
		 compareDataDao.queryNormalRecomWorkflow();
		 compareDataDao.queryMuchRecomWorkflow();
	}
}
