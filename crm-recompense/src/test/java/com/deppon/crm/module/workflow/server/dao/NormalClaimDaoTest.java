package com.deppon.crm.module.workflow.server.dao;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.workflow.server.dao.impl.NormalClaimDaoImpl;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;

/**
 * 
 * <p>
 * Description:理赔工作流DAO测试<br />
 * </p>
 * @title NormalClaimDaoTest.java
 * @package com.deppon.crm.module.workflow.server.dao 
 * @author liuHuan
 * @version 0.1 2013-7-29
 */
public class NormalClaimDaoTest extends TestCase {

	private static NormalClaimDaoImpl  normalClaimDao;
	static {
		normalClaimDao= TestUtil.normalClaimDao;
	}

/*	@Test
	public void testSave() {
		NormalClaim nc = new NormalClaim();
		nc.setProcessinstId("121277");
		nc.setApplyPersonCode("15014");
		nc.setClueUserId("12323");
		nc.setTransportOrErrorCode("3432423434");
		nc.setInsuredUnits("张三");
		nc.setContactPhone("027156846");
		nc.setHaulType("长途运输");
		nc.setReceivingDept("收货部门");
		nc.setStartingStation("武汉");
		nc.setGoodsName("冰箱");
		nc.setGoodsAttribute("件");
		nc.setInsuredAmount(1000);
		nc.setTargetDept("客服部");
		nc.setSendingDate(new Date());
		nc.setDangerDate(new Date());
		nc.setArea("武汉");
		nc.setClaimsType("理赔类型");
		nc.setOffsetType("冲账类型");
		nc.setCaseReporter("李四");
		nc.setReportDept("报案部门");
		nc.setReportDate(new Date());
		nc.setHandler("王五");
		nc.setHandleDate(new Date());
		nc.setOtherCost("其它原因");
		nc.setClaimAmount(10000);
		nc.setResponsibileDept("责任部门");
		nc.setNormalAmount(10000);
		nc.setActualClaimsAmount(8000);
		nc.setToCompanyCost(500);
		nc.setAreaCode("0120");
		normalClaimDao.save(nc);
	}
	
*/	

	@Test
	public void testGetNormalClaim(){
		System.out.println(normalClaimDao.getNormalClaim(24269l));
		
	}
	
	@Test
	public void  testGetNextSuffix(){
		System.out.println(normalClaimDao.getNextSuffix());
	}
	
	@Test
	public void testUpdateClaimNoSuffix(){
		normalClaimDao.updateClaimNoSuffix(100);
	}
	
	@Test
	public void testInsertClaimNoSuffix(){
		int n=normalClaimDao.getNextSuffix();
		normalClaimDao.insertClaimNoSuffix(n);
	}
	
	@Test
	public void testInsertNormalClaim(){
		NormalClaim nc = new NormalClaim();
		nc.setProcessinstId("121277");
		nc.setApplyPersonCode("15014");
		nc.setClueUserId("12323");
		nc.setTransportOrErrorCode("3432423434");
		nc.setInsuredUnits("张三");
		nc.setContactPhone("027156846");
		nc.setHaulType("长途运输");
		nc.setReceivingDept("收货部门");
		nc.setStartingStation("武汉");
		nc.setGoodsName("冰箱");
		nc.setGoodsAttribute("件");
		nc.setInsuredAmount(1000);
		nc.setTargetDept("客服部");
		nc.setSendingDate(new Date());
		nc.setDangerDate(new Date());
		nc.setArea("武汉");
		nc.setClaimsType("理赔类型");
		nc.setOffsetType("冲账类型");
		nc.setCaseReporter("李四");
		nc.setReportDept("报案部门");
		nc.setReportDate(new Date());
		nc.setHandler("王五");
		nc.setHandleDate(new Date());
		nc.setOtherCost("其它原因");
		nc.setClaimAmount(10000);
		nc.setResponsibileDept("责任部门");
		nc.setNormalAmount(10000);
		nc.setActualClaimsAmount(8000);
		nc.setToCompanyCost(500);
		nc.setAreaCode("0120");
		normalClaimDao.insertNormalClaim(nc);
	}
	
	@Test
	public void  testGetNormalClaimByWorkflowNo(){
		NormalClaim nc = normalClaimDao.getNormalClaimByWorkflowNo("ICRM201310290000130");
		System.out.println(nc.getWorkflowNo());
	}
	
	@Test
	public void  testGetDeptChargeByProcessinstId(){
		List<DeptCharge> list= normalClaimDao.getDeptChargeByProcessinstId(121277l);
		System.out.println(list.size());
	}
	
	@Test
	public void testGetIssueItemByProcessinstId(){
		List<IssueItem> list =normalClaimDao.getIssueItemByProcessinstId(121277l);
		System.out.println(list.size());
	}
	
	@Test
	public void testGetAwardItemByProcessinstId(){
		List<AwardItem> list =normalClaimDao.getAwardItemByProcessinstId(121277l);
		System.out.println(list.size());
	}
	@Test
	public void testGetResponsibleDeptByProcessinstId(){
		List<ResponsibleDept> list =normalClaimDao.getResponsibleDeptByProcessinstId(121277l);
		System.out.println(list.size());
	}
	
	@Test
	public void testUpdateNormalClaim(){
		NormalClaim nc = new NormalClaim();
		nc.setWorkflowNo("ICRM201310290000130");
		nc.setClaimAmount(100);
		normalClaimDao.updateNormalClaim(nc);
	}
	
	@Test
	public void  testGetRoleIdByUserId(){
		List<String> list =normalClaimDao.getRoleIdByUserId("22385");
		for(String s : list){
			System.out.println(s);
		}
		
	}
	
	
}
