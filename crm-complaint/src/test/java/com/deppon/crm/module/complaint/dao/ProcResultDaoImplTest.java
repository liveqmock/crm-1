package com.deppon.crm.module.complaint.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.BasciLevelDaoImpl;
import com.deppon.crm.module.complaint.server.dao.impl.ProcResultDaoImpl;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;

public class ProcResultDaoImplTest extends TestCase{
	private ProcResultDaoImpl  procResultDao;
	private BasciLevelDaoImpl basciLevelDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			procResultDao = ctx.getBean(ProcResultDaoImpl.class);
			basciLevelDao = ctx.getBean(BasciLevelDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveProcResult() {
		ProcResult procResult=new ProcResult();
		procResult.setCreateuserid(new BigDecimal(456));
		procResult.setDeallan("ENGELISH");
		procResult.setFeedbacklimit("1000");
		procResult.setLastmodifyuserid(new BigDecimal(456));
		procResult.setLevelid(new BigDecimal(702));
		procResult.setProclimit("250");
		int id=procResultDao.saveProcResult(procResult);
		assertEquals(!procResult.getFid().equals(0), true);
		procResultDao.deleteProcResultById(new BigDecimal(id));
	}
	
	@Test
	public void testGetProcResultByLevelId(){
		ProcResult procResult=new ProcResult();
		procResult.setCreateuserid(new BigDecimal(456));
		procResult.setDeallan("ENGELISH");
		procResult.setFeedbacklimit("1000");
		procResult.setLastmodifyuserid(new BigDecimal(456));
		procResult.setLevelid(new BigDecimal(702));
		procResult.setProclimit("250");
		int id=procResultDao.saveProcResult(procResult);
		List<ProcResult> prlist=procResultDao.getProcResultByLevelId(new BigDecimal(270));
//		assertEquals(prlist.size()>0, true);
		procResultDao.deleteProcResultById(new BigDecimal(id));
	}
	
	@Test
	public void testUpdateProcResult() {
		ProcResult procResult=new ProcResult();
		procResult.setCreateuserid(new BigDecimal(456));
		procResult.setDeallan("ENGELISH");
		procResult.setFeedbacklimit("1000");
		procResult.setLastmodifyuserid(new BigDecimal(456));
		procResult.setLevelid(new BigDecimal(702));
		procResult.setProclimit("250");
		int id=procResultDao.saveProcResult(procResult);
		
		procResult.setProclimit("240");
		procResultDao.updateProcResult(procResult);
		ProcResult pr=procResultDao.getProcResultById(procResult.getFid());
		
		assertEquals(procResult.getProclimit().equals("240"), true);
		procResultDao.deleteProcResultById(new BigDecimal(id));
	}
	
	@Test
	public void testDeleteProcResultById() {
		ProcResult procResult=new ProcResult();
		procResult.setCreateuserid(new BigDecimal(456));
		procResult.setDeallan("ENGELISH");
		procResult.setFeedbacklimit("1000");
		procResult.setLastmodifyuserid(new BigDecimal(456));
		procResult.setLevelid(new BigDecimal(702));
		procResult.setProclimit("250");
		procResultDao.saveProcResult(procResult);
		BigDecimal id=procResult.getFid();
		assertEquals(!procResult.getFid().equals(0), true);
		procResultDao.deleteProcResultById(id);
		ProcResult pr=procResultDao.getProcResultById(id);
		assertEquals(pr == null, true );
	}

	@Test
	public void testGetProcResultById() {
		ProcResult procResult=new ProcResult();
		procResult.setCreateuserid(new BigDecimal(456));
		procResult.setDeallan("ENGELISH");
		procResult.setFeedbacklimit("1000");
		procResult.setLastmodifyuserid(new BigDecimal(456));
		procResult.setLevelid(new BigDecimal(702));
		procResult.setProclimit("250");
		procResultDao.saveProcResult(procResult);
		BigDecimal id=procResult.getFid();
		ProcResult pr=procResultDao.getProcResultById(id);
		assertEquals(pr.getFid().equals(id), true);
		procResultDao.deleteProcResultById(id);
	}
	@Test
	public void testDeleteProcResultByParentId(){
		BigDecimal id = new BigDecimal(1);
		procResultDao.deleteProcResultByParentId(id);
	}
	
	@Test 
	public void testSaveBusType(){
			
		BasicBusType bbt = new BasicBusType();
		bbt.setLevelId("123456");
		bbt.setBusType("测试业务类型");
		bbt.setCreateTime(new Date());
		bbt.setCreateUserId("100");
		bbt.setLastModifyUserId("100");
		bbt.setLastUpdateTime(new Date());
		bbt.setDealLanguage("测试测试测试测试测试测试测试测试测试");
		bbt.setFeedbackLimit("24");
		bbt.setProcLimit("60");
		
		procResultDao.saveBusType(bbt);
		
		
	}
	@Test
	public void testUpdateBusType(){
	
		BasicBusType bbt = new BasicBusType();
		bbt.setId("9999999");
		bbt.setLevelId("123456");
		bbt.setBusType("测试业务类型1");
		bbt.setCreateTime(new Date());
		bbt.setCreateUserId("100");
		bbt.setLastModifyUserId("100");
		bbt.setLastUpdateTime(new Date());
		bbt.setDealLanguage("测试测试测试测试测试测试测试测试测试");
		bbt.setFeedbackLimit("24");
		bbt.setProcLimit("60");
		
		procResultDao.updateBusType(bbt);
	}
	@Test
	public void testDeleteBusTypeByBusScope(){
		List<String> ids = new ArrayList<String>();
		ids.add("1234");
		procResultDao.deleteBusTypeByBusScope(ids);
	}
	@Test
	public void testDeleteBusTypeById(){
		List<String> ids = new ArrayList<String>();
		ids.add("999999999");
		procResultDao.deleteBusTypeById(ids);
	}
	@Test
	public void testSearchBusTypeByBusScope(){
		procResultDao.searchBusTypeByBusScope("1234");
	}
	@Test
	public void testDeleteBusTypeByBusItem(){
		List<String> busItemIds = new ArrayList<String>();
		busItemIds.add("11111111");
		procResultDao.deleteBusTypeByBusItem(busItemIds);
	}
}
