package com.deppon.crm.module.recompense.server.dao;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.recompense.server.dao.impl.ClaimDao;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.foss.framework.server.context.AppContext;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:索赔DAO测试类<br />
 * </p>
 * 
 * @title ClaimDaoTest.java
 * @package com.deppon.crm.module.recompense.server.dao
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimDaoTest extends TestCase {

	public static ClaimDao claimDao = null;
	public static Claim claim = null;
	public static ClaimOperationLog log = null;
	public static ClaimMessage msg = null;
	static {
		claimDao = TestUtil.claimDao;

	}

	@Before
	protected void setUp() throws Exception {
		claim = new Claim();
		claim.setClaimAmount(500.0);
		claim.setClaimer("sender");
		claim.setClaimPerson("邹明");
		claim.setClaimRequire("我要100万");
		claim.setClaimStatus("WAIT_ACCEPT");
		claim.setCreateDate(new Date());
		claim.setCreateUser("26274");
		claim.setFirstRefuseTime(new Date());
		claim.setInsuranceValue(4000.0);
		claim.setLinkMan("邹明");
		claim.setLinkMobile("13515895617/021-325655112");
		claim.setModifyDate(new Date());
		claim.setModifyUser("25994");
		claim.setProcessor("26001");
		claim.setProcessDept("100006");
		claim.setRecompenseMethod("unbilled");
		claim.setReportDept("100006");
		claim.setReporter("26001");
		claim.setStatusSeq("1");
		claim.setTranferCount(3);
		claim.setWaybillNumber("198804546");
		claim.setClaimTime(new Date());

		log = new ClaimOperationLog();
		log.setCreateDate(new Date());
		log.setCreateUser("25994");
		log.setModifyDate(new Date());
		log.setModifyUser("25994");
		log.setOperator("25994");
		log.setOperatorContent("邹明拿走了100万");
		log.setOperatorDept("100006");
		log.setOperatorTime(new Date());

		msg = new ClaimMessage();
		msg.setContent("邹明说只要一毛钱");
		msg.setCreateTime(new Date());
		msg.setCreateUser("25994");
		msg.setFollowDept("100006");
		msg.setFollowUser("唐亮");
		msg.setFollowUserId("25994");
	}

	@After
	protected void tearDown() throws Exception {
		if(null!=claim&&null!=claim.getId()){
			claimDao.deleteClaimById(claim.getId());

		}
	}

	/**
	 * 
	 * <p>
	 * Description:保存索赔信息测试<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testSaveClaim() {
		claimDao.saveClaim(null);
		Claim newclaim = claimDao.saveClaim(claim);
		System.err.println(newclaim);
	}

	/**
	 * 
	 * <p>
	 * Description:根据id获取索赔信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testGetClaimById() {
		Claim newclaim = claimDao.saveClaim(claim);

		claimDao.getClaimById(null);
		Claim newclaim1 = claimDao.getClaimById(newclaim.getId());
		claimDao.getClaimById(null);
		claimDao.getClaimById("");
		System.err.println(newclaim1);

	}
	/**
	 * 
	 * <p>
	 * Description:根据运单号查询索赔<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 * void
	 */
	@Test
	public void testGetClaimByWaybillNumber() {
		Claim newclaim = claimDao.saveClaim(claim);
		
		Claim newclaim2 = claimDao.getClaimByWaybillNumber(null);
		Claim newclaim1 = claimDao.getClaimByWaybillNumber(newclaim.getWaybillNumber());
		System.err.println(newclaim1);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testDeleteClaimById() {
		Claim newclaim = claimDao.saveClaim(claim);

		claimDao.deleteClaimById(newclaim.getId());
		

	}

	/**
	 * 
	 * <p>
	 * Description:更新<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testUpdateClaim() {
		Claim newclaim = claimDao.saveClaim(claim);

		Claim claim1 = new Claim();
		claim1.setId(newclaim.getId());
		claim1.setClaimAmount(5001.0);
		claim1.setClaimer("sender1");
		claim1.setClaimPerson("邹明1");
		claim1.setClaimRequire("我要1000万");
		claim1.setClaimStatus("WAIT_ACCEPTed");
		claim1.setCreateDate(new Date());
		claim1.setCreateUser("262741");
		claim1.setFirstRefuseTime(new Date());
		claim.setInsuranceValue(4000.0);
		claim1.setLinkMan("邹明1");
		claim1.setLinkMobile("13515895617/021-3256551121");
		claim1.setModifyDate(new Date());
		claim1.setModifyUser("259941");
		claim1.setProcessor("260011");
		claim1.setProcessDept("1000061");
		claim1.setRecompenseMethod("unbilled1");
		claim1.setReportDept("1000061");
		claim1.setReporter("260011");
		claim1.setStatusSeq("11");
		claim.setTranferCount(3);
		claim.setWaybillNumber("1988045461");
		Claim claim2 = claimDao.updateClaim(null);
		 claim2 = claimDao.updateClaim(claim1);
		 claimDao.updateClaim(null);

		System.out.println(claim2);

	}

	/**
	 * 
	 * <p>
	 * Description:查询<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testSearchClaim() {
		ClaimSearchCondition condition = new ClaimSearchCondition();
		List<Claim> claims = claimDao.searchClaimsByCondition(condition);
		System.out.println(claims);

	}

	/**
	 * 
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testCountClaim() {
		ClaimSearchCondition condition = new ClaimSearchCondition();
		int a = claimDao.countClaimsByCondition(condition);
		System.out.println(a);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testAddOperationLog() {
		Claim newclaim = claimDao.saveClaim(claim);

		log.setClaimId(newclaim.getId());
		claimDao.addOperationLog(log);
		claimDao.addOperationLog(null);


	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testAddClaimMessage() {
		Claim newclaim = claimDao.saveClaim(claim);
		msg.setClaimId(newclaim.getId());
		claimDao.addFollowClaimMessage(msg);
		claimDao.addFollowClaimMessage(null);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5 void
	 */
	@Test
	public void testSearchLog() {
		Claim newclaim = claimDao.saveClaim(claim);

		claimDao.getOperationLogListByClaimId(newclaim.getId());
		claimDao.getOperationLogListByClaimId(null);

	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 * void
	 */

	@Test
	public void testSearchMsg() {
		claimDao.getClaimMessageListByClaimId(null);
		Claim newclaim = claimDao.saveClaim(claim);
		List<ClaimMessage> cms=claimDao.getClaimMessageListByClaimId(newclaim.getId());
		System.out.println(cms);
	};

}
