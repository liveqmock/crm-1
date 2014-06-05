package com.deppon.crm.module.complaint.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.FeedBackReasionDaoImpl;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;

public class FeedBackReasionDaoImplTest extends TestCase{

	private FeedBackReasionDaoImpl feedBackReasionDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			feedBackReasionDao = ctx.getBean(FeedBackReasionDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void searchFeedBackReasionByCompIdTest() {
		BigDecimal bd=new BigDecimal(232);
		feedBackReasionDao.searchFeedBackReasionByCompId(bd);
	}
	
	@Test
	public void testSaveFeedBackReasion(){
		FeedBackReasion feedback=new FeedBackReasion();
		feedback.setComplaintid(new BigDecimal("1"));
		feedback.setWailbillnunber("1");
		feedback.setWailbillcontent("1");
		feedback.setRecordman("1");
		feedback.setRecordpartermentid(new BigDecimal("1"));
		feedback.setRecordpartmentname("1");
		feedback.setRecordtime(new Date());
		feedback.setRecordtype(Constants.COMPLAINT_RECORD_TYPE_FEEDBACK);
		feedback.setCreateDate(new Date());
		feedback.setCreateUser("1");
		feedback.setModifyDate(new Date());
		feedback.setModifyUser("1");
		try{
		feedBackReasionDao.saveFeedBackReasion(feedback);
		}catch(Exception e){
			e.printStackTrace();
		}
		assertNotNull(feedback);
	}
	
	public void testSearchFeedBackReasionByCompId(){
		List<FeedBackReasion> fbr= feedBackReasionDao.searchFeedBackReasionByCompId(new BigDecimal(1224));
		assertEquals(fbr != null, true);
	}
	@Test
	public void testupdateFeedBackReasion(){
		FeedBackReasion feedBackReasion =new FeedBackReasion();
		feedBackReasion.setWailbillcontent("106139");
		feedBackReasion.setFid(new BigDecimal("106139"));
//		feedBackReasionDao.updateFeedBackReasion(feedBackReasion);
	}
}
