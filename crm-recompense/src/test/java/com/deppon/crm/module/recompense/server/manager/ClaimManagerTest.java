package com.deppon.crm.module.recompense.server.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.expr.Instanceof;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.dao.impl.ClaimDao;
import com.deppon.crm.module.recompense.server.manager.impl.ClaimManager;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.utils.ClaimConstants;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimMessage;
import com.deppon.crm.module.recompense.shared.domain.ClaimOperationLog;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.crm.module.recompense.shared.exception.ClaimExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.AppContext;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:索赔manager测试类<br />
 * </p>
 * 
 * @title ClaimManagerTest.java
 * @package com.deppon.crm.module.recompense.server.manager
 * @author roy
 * @version 0.1 2013-3-8
 */
public class ClaimManagerTest extends TestCase {
	public static ClaimManager claimManager = null;
	public static User user = null;
	public static Claim claim = null;
	public static ClaimDao claimDao = null;
	static {
		claimManager = TestUtil.claimManager;
		claimDao = TestUtil.claimDao;

		user = new User();
		Employee employee = new Employee();
		Department deparment = new Department();
		deparment.setId("11216");
		deparment.setDeptName("广州白云区东平营业部");
		employee.setId("111");
		employee.setDeptId(deparment);
		user.setEmpCode(employee);
		// claimManager.getWayBillAndType("235689741" +
		// "",Constants.ABNORMAL_SIGN,user );

		claim = new Claim();
		claim.setArrivedDept("11216");
		claim.setReceiveDept("11216");

		claim.setClaimAmount(5000.0);
		claim.setClaimer(ClaimConstants.CONSIGNEE);
		claim.setClaimPerson("鲍鱼");
		claim.setClaimRequire("啊啊 啊啊啊");
		claim.setConsignee("11111");
		claim.setConsigneePhone("13515478946");
		claim.setInsuranceValue(6000.0);
		claim.setWaybillNumber("55551018");
		claim.setRecompenseMethod(Constants.ABNORMAL_SIGN);

	}

	

	@After
	protected void tearDown() throws Exception {
		if (null != claim && null != claim.getId()) {
			claimDao.deleteClaimById(claim.getId());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:查询运单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-8 void
	 */
	@Test
	public void testGetWayBillAndType() {
		String voucherNo = null;
		String recompenseMethod = null;
		try {
			claimManager.getWayBillAndType(voucherNo, recompenseMethod, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					ClaimExceptionType.NULL_VOUCHERNO.getErrorCode())) {
				assertTrue(true);
				System.out.println(e.getErrorCode());
			}
		}
		voucherNo = "55551018";
		recompenseMethod = Constants.UNBILLED;
		try {
			claimManager.getWayBillAndType(voucherNo, recompenseMethod, user);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					ClaimExceptionType.ERROR_PROCESS_DEPT.getErrorCode())) {
				assertTrue(true);
				System.out.println(e.getErrorCode());
			}
		}
		voucherNo = "2013051886017";
		recompenseMethod = "Constants.ABNORMAL_SIGN";
		try {
			claimManager.getWayBillAndType(voucherNo, recompenseMethod, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:保存测试<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-8 void
	 */
	@Test
	public void testSaveClaim() {
		try {
			claimManager.saveClaim(claim, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:同意受理<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-8 void
	 */
	@Test
	public void testAcceptClaim() {
			Claim newClaim = new Claim();
			newClaim.setArrivedDept("11103");
			newClaim.setProcessDept("11103");
			newClaim.setCreateDate(new Date());
			newClaim.setCreateUser("211526");
			newClaim.setModifyDate(new Date());
			newClaim.setModifyUser("211526");
			newClaim.setWaybillNumber("123456789");
			newClaim.setClaimer("SHIPPER");
			newClaim.setLinkMan("11");
			newClaim.setLinkMobile("1");
			newClaim.setInsuranceValue(1000D);
			newClaim.setClaimPerson("1");
			newClaim.setClaimAmount(1D);
			newClaim.setTranferCount(0);
			newClaim.setClaimStatus("HANDLED");
			newClaim.setReportDept("11103");
			newClaim.setReportDept("211526");
			newClaim.setClaimRequire("1");
			newClaim.setStatusSeq("3");
			newClaim.setReporter("211526");
			newClaim.setRecompenseMethod("unbilled");
			newClaim.setReceiveDept("11103");
			newClaim.setShipper("11");
			newClaim.setShipperPhone("11");
			newClaim.setFirstRefuseTime(new Date());
			newClaim.setConsignee("11");
			newClaim.setClaimTime(new Date());
		try {
			claimDao.saveClaim(newClaim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 user.getEmpCode().getDeptId().setId("11103");
		 try{
		 claimManager.acceptClaim(newClaim, user);
		 }catch(Exception e){
		 e.printStackTrace();
		 }
	}

	/**
	 * 
	 * <p>
	 * Description:免赔解冻<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-8 void
	 */
	@Test
	public void testCancelRemitClaim() {
		user.getEmpCode().getDeptId().setId("11103");
		user.getEmpCode().getDeptId()
				.setStandardCode(ClaimConstants.RECOMPENSEGROUPCODE);
		try {
			claimManager.remitClaimByClaimId("301", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			claimManager.cancelRemitClaim("301", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:增加跟进消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-8 void
	 */
	@Test
	public void testAddFollowClaimMessage() {
		ClaimMessage claimMessage = new ClaimMessage();
		claimMessage.setClaimId("1");
		claimMessage.setCreateDate(new Date());
		claimMessage.setCreateUser(user.getEmpCode().getId());
		claimMessage.setFollowDept(user.getEmpCode().getDeptId().getId());
		claimMessage.setCreateTime(new Date());
		claimMessage.setFollowUser(user.getEmpCode().getEmpName());
		claimMessage.setFollowUserId(user.getEmpCode().getId());
		claimMessage.setModifyDate(new Date());
		claimMessage.setModifyUser(user.getEmpCode().getId());
		claimMessage.setContent("邹明说：我是人， 你就是人");
		claimManager.addFollowClaimMessage(claimMessage, user);
		claimMessage.setContent(null);
		try {
			claimManager.addFollowClaimMessage(claimMessage, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:生成运单<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-5-24 void
	 */
	@Test
	public void testCreateWaybill() {
		String voucherNo = null;
		String recompenseMethod = null;
		FossWaybillInfo waybillInfo = new FossWaybillInfo();
		waybillInfo.setInsuranceValue(new BigDecimal(400.0));
		OaAccidentInfo unbilledOaAccident = new OaAccidentInfo();
		claimManager.createWaybill(voucherNo, recompenseMethod, waybillInfo,
				unbilledOaAccident);
		waybillInfo.setSenderMobile("110");
		claimManager.createWaybill(voucherNo, recompenseMethod, waybillInfo,
				unbilledOaAccident);
		waybillInfo.setReceiveDeptNumber("DP01332");
		claimManager.createWaybill(voucherNo, recompenseMethod, waybillInfo,
				unbilledOaAccident);
		recompenseMethod = Constants.UNBILLED;
		claimManager.createWaybill(voucherNo, recompenseMethod, waybillInfo,
				unbilledOaAccident);
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询索赔<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-5-24 void
	 */
	@Test
	public void testSearchClaimsByCondition() {
		ClaimSearchCondition claimSearchCondition = new ClaimSearchCondition();
		List<String> processDepts = new ArrayList<String>();
		processDepts.add("10278");
		claimSearchCondition.setWaybillNumber("72313142");
		claimSearchCondition.setProcessDepts(processDepts);
		claimSearchCondition.setStartTime(new Date(System.currentTimeMillis()
				- 24 * 60 * 60 * 1000));
		claimSearchCondition.setEndTime(new Date());
		Set<String> roleids = new HashSet<String>();
		roleids.add(Constants.ROLE_ADMIN);
		user.setRoleids(roleids);
		user.getEmpCode().getDeptId().setStandardCode("DP00402");
		user.getEmpCode().getDeptId().setId("10278");
		user.setId("26276");
		try {
			claimManager.searchClaimsByCondition(claimSearchCondition, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询索赔<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-5-24 void
	 */
	@Test
	public void testGetClaimById() {
		String claimId = "207";
		claimManager.getClaimById(claimId);
		claimId = "2";
		claimManager.getClaimById(claimId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据凭证号查询操作日志<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-5-24 void
	 */
	@Test
	public void testGetOperationLogListByClaimId() {
		claimManager.getOperationLogListByClaimId("1");
	}

	/**
	 * 
	 * <p>
	 * Description:发送到对方部门<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-5-24 void
	 */
	@Test
	public void testSendToAnotherDept() {
		String claimId = "301";
		String reason = "原因";
		user.getEmpCode().getDeptId().setId("11103");
		try {
			claimManager.sendToAnotherDept(claimId, reason, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		claimId = "911";
		try {
			claimManager.sendToAnotherDept(claimId, reason, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:增加操作日志<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2013-5-24 void
	 */
	@Test
	public void testAddOperationLog() {
		String content = "不知道";
		String claimId = "1";
		claimManager.addOperationLog(content, claimId, user);
	}

	@Test
	public void testGetClaimMessageListByClaimId() {
		claimManager.getClaimMessageListByClaimId("207");
	}
}
