package com.deppon.crm.module.complaint.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.BasciLevelDaoImpl;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.Result;

public class ListComplaintActionTest {

	private BasciLevelDaoImpl BasciLevelDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			BasciLevelDao = ctx.getBean(BasciLevelDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchBasciLevelById() {
//		BigDecimal bd=new BigDecimal(323);
		BasciLevelSearchCondition cnd=new BasciLevelSearchCondition();
		cnd.setParentid(23);
	//	List<BasciLevel> bl=BasciLevelDao.searchBasciLevelById(cnd);
		//System.out.println(bl.size());
	}
}
