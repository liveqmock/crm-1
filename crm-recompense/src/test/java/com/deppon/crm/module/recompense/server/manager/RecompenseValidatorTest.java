package com.deppon.crm.module.recompense.server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Balance;
import com.deppon.crm.module.recompense.shared.domain.BankAccount;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.Finance;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.PaymentBill;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class RecompenseValidatorTest extends TestCase {

	@Test
	public void testValidateRecompenseReportCreate() {
		RecompenseApplication app = new RecompenseApplication();
		List<IssueItem> issueItemAddList = new ArrayList<IssueItem>();
		List<GoodsTrans> goodsTransAddList = new ArrayList<GoodsTrans>();
		boolean result = false;
		// 1. 都为空校验
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		app.setRecompenseMethod(null);
		app.setRecompenseType("11");
		Waybill waybill = new Waybill();
		waybill.setWaybillNumber("11");
		app.setWaybill(waybill);
		app.setInsurType("111");
		app.setInsurQuantity(123);
		app.setDeptId("123");
		app.setInsurDate(new Date());
		app.setClaimParty("aa");
		app.setRecompenseAmount(123d);
		Member customer = new Member();
		customer.setId("123");
		app.setCustomer(customer);
		app.setCompanyPhone(null);
		//2. app不为空，app.getRecompenseMethod()为空
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		
		app.setRecompenseMethod(Constants.FAST_TYPE);
		// 快速理賠測試 失败
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		app.setCompanyPhone("12344");
		
		// 快速理賠測試
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			assertTrue(true);
		} catch (GeneralException e) {
			fail("正常数据抛出异常");
		}
		
		//列表都为空
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		
		GoodsTrans goodsTrans = new GoodsTrans();
		goodsTransAddList.add(goodsTrans);
		app.setGoodsTransList(goodsTransAddList);
		// 一个列表为空校验
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		app.setGoodsTransList(null);
		IssueItem issueItem = new IssueItem();
		issueItemAddList.add(issueItem);
		app.setIssueItemList(issueItemAddList);
		// 一个列表不为空校验
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			assertTrue(true);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
					.getErrorCode())) {
			assertTrue(true);
			} else {
			fail("抛出异常不正确");
			}
		}
		
		app.setGoodsTransList(goodsTransAddList);
		// 列表不为空校验
		try {
			result = RecompenseValidator.validateRecompenseReportCreate(app);
			assertTrue(true);
		} catch (GeneralException e) {
			fail("正常数据抛出异常");
		}
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-12-4上午9:07:42
	 * void
	 * @update 2012-12-4上午9:07:42
	 */
	@Test
	public void testValidateRecompenseReportRequire(){
		RecompenseApplication app = new RecompenseApplication();
		//1.method不为空，其他都为空
		app.setRecompenseMethod(Constants.FAST_TYPE);
		RecompenseValidator.validateRecompenseReportRequire(app);
		
		//2.method不为空，其他也不为空
		app.setRecompenseType("11");
		Waybill waybill = new Waybill();
		waybill.setWaybillNumber("11");
		app.setWaybill(waybill);
		app.setInsurType("111");
		app.setInsurQuantity(123);
		app.setDeptId("123");
		app.setInsurDate(new Date());
		app.setClaimParty("aa");
		app.setRecompenseAmount(123d);
		Member customer = new Member();
		customer.setId("123");
		app.setCustomer(customer);
		app.setCompanyPhone("15921594568");
		
		System.err.println(
				RecompenseValidator.validateRecompenseReportRequire(app));
	}
	
	@Test
	public void testIsExistListItem(){
		List<String> originalList = new ArrayList<String>();
		List<String> addList = new ArrayList<String>();
		List<String> deleteList = null;
		List<String> updateList = null;
		//1. addList不为空，size大于0
		addList.add("1");
		System.err.println("1."+RecompenseValidator.
				isExistListItem(originalList, addList, deleteList, updateList));
		
		//2.addList为空，updateList不为空
		addList = null;
		updateList = new ArrayList<String>();
		updateList.add("1");
		System.err.println("2."+RecompenseValidator.
				isExistListItem(originalList, addList, deleteList, updateList));
		
		//3.addList为空，updateList为空，deleteList也为空
		updateList = null;
		System.err.println("3."+RecompenseValidator.
				isExistListItem(originalList, addList, deleteList, updateList));
		
		//4.addList为空，updateList为空，deleteList不为空，size等于0
		deleteList = new ArrayList<String>();
		System.err.println("4."+RecompenseValidator.
				isExistListItem(originalList, addList, deleteList, updateList));
		
		//5.addList为空，updateList为空，deleteList不为空，size大于0
		deleteList.add("1");
		System.err.println("5."+RecompenseValidator.
				isExistListItem(originalList, addList, deleteList, updateList));
		//6.
		originalList.add("1");
		originalList.add("2");
		System.err.println("6."+RecompenseValidator.
				isExistListItem(originalList, addList, deleteList, updateList));
		
		deleteList.add("3");
		deleteList.add("2");
		System.err.println("7."+RecompenseValidator.
				isExistListItem(originalList, addList, deleteList, updateList));
	}
	
	@Test
	public  void testIsObjectNotNull(){
		String str = "";
		Map<Integer, String>map = new HashMap<Integer, String>();
		map.put(12345, "上山打老虎");
		RecompenseValidator.isObjectNotNull(str);
		RecompenseValidator.isObjectNotNull(map);
	}
	@Test
	public void testValidateRecompenseReportUpdate() {
		Map<String, List<IssueItem>> issueItemModifyMap = new HashMap<String, List<IssueItem>>();
		Map<String, List<GoodsTrans>> goodsTransModifyMap = new HashMap<String, List<GoodsTrans>>();
		List<IssueItem> issueOriginal = new ArrayList<IssueItem>();
		List<IssueItem> issueAdd = new ArrayList<IssueItem>();
		List<IssueItem> issueDelete = new ArrayList<IssueItem>();
		List<IssueItem> issueUpdate = new ArrayList<IssueItem>();
		List<GoodsTrans> goodsTransOriginal = new ArrayList<GoodsTrans>();
		List<GoodsTrans> goodsTransAdd = new ArrayList<GoodsTrans>();
		List<GoodsTrans> goodsTransDelete = new ArrayList<GoodsTrans>();
		List<GoodsTrans> goodsTransUpdate = new ArrayList<GoodsTrans>();
		issueItemModifyMap.put(Constants.LIST_TYPE_ORIGINAL, issueOriginal);
		issueItemModifyMap.put(Constants.LIST_TYPE_ADD, issueAdd);
		issueItemModifyMap.put(Constants.LIST_TYPE_DELETE, issueDelete);
		issueItemModifyMap.put(Constants.LIST_TYPE_UPDATE, issueUpdate);
		goodsTransModifyMap.put(Constants.LIST_TYPE_ORIGINAL,
				goodsTransOriginal);
		goodsTransModifyMap.put(Constants.LIST_TYPE_ADD, goodsTransAdd);
		goodsTransModifyMap.put(Constants.LIST_TYPE_DELETE, goodsTransDelete);
		goodsTransModifyMap.put(Constants.LIST_TYPE_UPDATE, goodsTransUpdate);
		RecompenseApplication app = new RecompenseApplication();
		boolean result = false;
		//1. 都为空校验
		try {
			result = RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		
		app.setRecompenseType("11");
		Waybill waybill = new Waybill();
		waybill.setWaybillNumber("11");
		app.setWaybill(waybill);
		app.setInsurType("111");
		app.setInsurQuantity(123);
		app.setDeptId("123");
		app.setInsurDate(new Date());
		app.setClaimParty("aa");
		app.setRecompenseAmount(123d);
		Member customer = new Member();
		customer.setId("123");
		app.setCustomer(customer);
		app.setCompanyPhone("123123");
		//2.method为空
		try {
			result = RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		app.setRecompenseMethod(Constants.FAST_TYPE);
		//4. 快速理賠測試
		try {
			result = RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
			assertTrue(true);
		} catch (GeneralException e) {
			fail("正常数据抛出异常");
		}
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		// 正常列表为空校验
		try {
			result = RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
			assertTrue(true);
			// fail("未抛出异常");
		} catch (GeneralException e) {
			fail("正常数据抛出异常");
			// if (e.getErrorCode().equals(
			// RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
			// .getErrorCode())) {
			// assertTrue(true);
			// } else {
			// fail("抛出异常不正确");
			// }
		}
		issueDelete.add(new IssueItem());
		goodsTransDelete.add(new GoodsTrans());
		// 刪除列表比原來的多
		try {
			result = RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		issueUpdate.add(new IssueItem());
		goodsTransUpdate.add(new GoodsTrans());
		result = RecompenseValidator.validateRecompenseReportUpdate(app,
				issueItemModifyMap, goodsTransModifyMap);
		// 有修改列表
		try {
			result = RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
			assertTrue(true);
		} catch (GeneralException e) {
			fail("正常数据抛出异常");
		}
		issueAdd.add(new IssueItem());
		goodsTransAdd.add(new GoodsTrans());
		result = RecompenseValidator.validateRecompenseReportUpdate(app,
				issueItemModifyMap, goodsTransModifyMap);
		// 有增加列表
		try {
			result = RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
			assertTrue(true);
		} catch (GeneralException e) {
			fail("正常数据抛出异常");
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-12-4上午9:07:22
	 * void
	 * @update 2012-12-4上午9:07:22
	 */
	@Test
	public void testValidateRecompenseProcess(){
		//RecompenseApplication app,Map<String, List<DeptCharge>> deptChargeMap
		RecompenseApplication app = new RecompenseApplication();
		
		Double num = 90d;
		app.setNormalAmount(100d);
		app.setRealAmount(num);
		
		Map<String, List<DeptCharge>> deptChargeMap
			 = new HashMap<String, List<DeptCharge>>();
		List<DeptCharge> deptChargeList = new ArrayList<DeptCharge>();
		
		DeptCharge deptCharge = new DeptCharge();
		deptCharge.setId("ID");
		deptCharge.setRecompenseId("RecompenseId");
		deptCharge.setAmount(num);
		deptCharge.setDeptId("DeptId");
		deptCharge.setDeptName("华东财务部");
		deptChargeList.add(deptCharge);
		deptChargeMap.put("deptChargeList",deptChargeList);
		System.err.println(deptChargeMap.get("deptChargeList").get(0).getAmount());
		System.out.println(app.getNormalAmount());
		System.out.println(app.getRealAmount());
		//1.都大于0
		System.err.println("通过："+
				RecompenseValidator.validateRecompenseProcess(app, deptChargeMap));
		
		//2.realAmount小于0
		app.setRealAmount(-100d);
		System.err.println("RealAmount小于0："+
				RecompenseValidator.validateRecompenseProcess(app, deptChargeMap));
		
		//3.都小于0
		app.setNormalAmount(-100d);
		System.err.println("都小于0："+
				RecompenseValidator.validateRecompenseProcess(app, deptChargeMap));
		
		//4.NormalAmount小于0
		app.setRealAmount(99d);
		System.err.println("NormalAmount小于0："+
				RecompenseValidator.validateRecompenseProcess(app, deptChargeMap));
		
		deptCharge.setAmount(99d);
		app.setNormalAmount(88d);
		System.err.println("不通过03："+
				RecompenseValidator.validateRecompenseProcess(app, deptChargeMap));
		
	}
	
	/**
	 */
	@Test
	public void testValidateRecompenseBalance() {
		Double recompenseAmount = 1000d;
		boolean result = false;
		List<Balance> balanceList = new ArrayList<Balance>();
		// 冲账单为空异常
		try {
			result = RecompenseValidator.validateRecompenseBalance(
					recompenseAmount, balanceList);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_BALANCE_LIST_NULL
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		Balance balance1 = new Balance();
		Balance balance2 = new Balance();
		balanceList.add(balance1);
		balanceList.add(balance2);
		// 数据为空异常
		try {
			result = RecompenseValidator.validateRecompenseBalance(
					recompenseAmount, balanceList);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_BALANCE_ITEM_UNASSGIN
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		balance1.setUsableAmount(100d);
		balance1.setBalanceAmount(110d);
		balance2.setUsableAmount(200d);
		balance2.setBalanceAmount(-300d);
		// 數據記錄負數超出异常
		try {
			result = RecompenseValidator.validateRecompenseBalance(
					recompenseAmount, balanceList);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_BALANCE_ITEM_UNASSGIN
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		balance1.setUsableAmount(100d);
		balance1.setBalanceAmount(100d);
		balance2.setUsableAmount(200d);
		balance2.setBalanceAmount(300d);
		// 数据记录超出异常
		try {
			result = RecompenseValidator.validateRecompenseBalance(
					recompenseAmount, balanceList);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_BALANCE_ITEM_UNASSGIN
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		balance1.setUsableAmount(100d);
		balance1.setBalanceAmount(100d);
		balance2.setUsableAmount(2000d);
		balance2.setBalanceAmount(1000d);
		// 数据超出总金额异常
		try {
			result = RecompenseValidator.validateRecompenseBalance(
					recompenseAmount, balanceList);
			fail("抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_BALANCE_TOTAL_UNASSGIN
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		balance1.setUsableAmount(100d);
		balance1.setBalanceAmount(100d);
		balance2.setUsableAmount(2000d);
		balance2.setBalanceAmount(100d);
		// 数据使用正常
		try {
			result = RecompenseValidator.validateRecompenseBalance(
					recompenseAmount, balanceList);
			assertTrue(true);
		} catch (GeneralException e) {
			fail("正常数据抛出异常");
		}

	}

	
	@Test
	public void testValidateRecompensePayment() {
		boolean result = false;
		PaymentBill paymentBill = null;
		// 理赔单为空
		try {
			result = RecompenseValidator.validateRecompensePayment(paymentBill);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_PAYMENT_NULL_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		paymentBill = new PaymentBill();
		paymentBill.setPaymentType("现金");
		Finance finance = new Finance();
		paymentBill.setBelongfinance(finance);
		BankAccount bankAccount = new BankAccount();
		paymentBill.setBankAccount(bankAccount);

		// 理赔单金额为空
		try {
			result = RecompenseValidator.validateRecompensePayment(paymentBill);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_PAYMENT_AMOUNT_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		
		// PaymentAmount小于0
		paymentBill.setPaymentAmount(-213d);
		try {
			result = RecompenseValidator.validateRecompensePayment(paymentBill);
			fail("未抛出异常");
		} catch (GeneralException e) {
			System.err.println("xxxxx:"+e.getErrorCode());
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_PAYMENT_AMOUNT_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		
		//
		paymentBill.setPaymentAmount(213d);
		// 理赔单数据不全
		try {
			result = RecompenseValidator.validateRecompensePayment(paymentBill);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_PAYMENT_REQUIRE_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		bankAccount.setBankName("11");
		bankAccount.setOpenName("1213");
		bankAccount.setAccount("1231");
		bankAccount.setBranchName("1213");
		bankAccount.setProvince("afa");
		bankAccount.setCity("afasdf");
		bankAccount.setMobile("123132");
		// 数据完整
		try {
			result = RecompenseValidator.validateRecompensePayment(paymentBill);
			assertTrue(true);
		} catch (GeneralException e) {
			fail("数据正常抛出异常");
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-12-4上午9:08:48
	 * void
	 * @update 2012-12-4上午9:08:48
	 */
	@Test
	public void testValidateRecompensePaymentRequire(){
		PaymentBill paymentBill = new PaymentBill();
		BankAccount bankAccount = new BankAccount();
		
		bankAccount.setBankName("德邦银行");
		bankAccount.setOpenName("德邦大爷");
		bankAccount.setAccount("008009001002");
		bankAccount.setProvince("天堂省");
		bankAccount.setCity("极乐城");
		bankAccount.setBranchName("德邦小弟银行");
		bankAccount.setMobile("0214008305555");
		
		paymentBill.setPaymentType("月结");
		paymentBill.setPaymentAmount(100d);
		paymentBill.setBankAccount(bankAccount);
		System.err.println(RecompenseValidator.validateRecompensePayment(paymentBill));
	
		paymentBill.setPaymentAmount(-100d);
//		System.err.println();
		try {
			RecompenseValidator.validateRecompensePayment(paymentBill);
			fail("大人，未抛出异常。");		
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(
					RecompenseExceptionType.
					RECOMPENSE_PAYMENT_AMOUNT_ERROR.getErrorCode())){
				assertTrue(true);
			}else{
				fail("大人，异常不正确");
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-12-4上午9:09:10
	 * void
	 * @update 2012-12-4上午9:09:10
	 */
	@Test
	public void testCanCreateRecompense(){
//		//String recompenseMethod,
//		//WaybillInfo waybillInfo, List<OaAccidentInfo> waybillAccidentList,
//		//OaAccidentInfo unbillAccident, RecompenseApplication old
//		OaAccidentInfo unbillAccident = null;
//		OaAccidentInfo unbillAccident01 = new OaAccidentInfo();
//		List<OaAccidentInfo> waybillAccidentList = null;
//		WaybillInfo waybillInfo = null;
//		String recompenseMethod = null;
//		
//		//1.
//		RecompenseApplication old = new RecompenseApplication();
//		try {
//			RecompenseValidator.canCreateRecompense(
//				recompenseMethod, waybillInfo, 
//						waybillAccidentList, unbillAccident, old);
//			fail("大人，未抛出异常。");		
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.
//					RECOMPENSE_RECORD_EXIST_ERROR.getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常不正确");
//			}
//		}
//		
//		//2.
//		old = null;
//		recompenseMethod = "unbilled";
//		try {
//			RecompenseValidator.canCreateRecompense(
//					recompenseMethod, waybillInfo, 
//						waybillAccidentList, unbillAccident, old);
//			fail("大人，未抛出异常。");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.
//						RECOMPENSE_UNBILLED_MISTAKE_NOEXIST_ERROR.
//						getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常不正确");
//			}
//		}
//		
//		//3.
//		unbillAccident = new OaAccidentInfo();
//		System.err.println(
//				RecompenseValidator.canCreateRecompense(
//						recompenseMethod, waybillInfo, 
//							waybillAccidentList, unbillAccident, old));
//		
//		//4.
//		recompenseMethod = "abnormal";
//		try {
//			RecompenseValidator.canCreateRecompense(
//					recompenseMethod, waybillInfo, 
//						waybillAccidentList, unbillAccident, old);
//			fail("大人，未抛出异常。");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.
//					RECOMPENSE_NOEXIST_WAYBILL_ERROR.
//						getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常不正确");
//			}
//		}
//		
//		//5.
//		waybillInfo = new WaybillInfo();
//		waybillInfo.setFailedSignReceive(true);
//		System.err.println(
//				RecompenseValidator.canCreateRecompense(
//						recompenseMethod, waybillInfo, 
//							waybillAccidentList, unbillAccident, old));
//		
//		//6.
//		waybillInfo.setFailedSignReceive(false);
//		waybillAccidentList = new ArrayList<OaAccidentInfo>();
//		unbillAccident.setAccidentType(2);
//		waybillAccidentList.add(unbillAccident);
//		System.err.println(
//				RecompenseValidator.canCreateRecompense(
//						recompenseMethod, waybillInfo, 
//							waybillAccidentList, unbillAccident, old));
//		
//		
//		//7.
//		unbillAccident.setAccidentType(14);
//		recompenseMethod = "lost_goods";
//		waybillAccidentList.add(unbillAccident);
//		System.err.println(
//				RecompenseValidator.canCreateRecompense(
//						recompenseMethod, waybillInfo, 
//							waybillAccidentList, unbillAccident, old));
//		
//		
//		//8.
//		unbillAccident.setAccidentType(2);
//		waybillAccidentList.add(unbillAccident);
//		try {
//			RecompenseValidator.canCreateRecompense(
//					recompenseMethod, waybillInfo, 
//						waybillAccidentList, unbillAccident, old);
//			fail("大人，未抛出异常。");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.
//					RECOMPENSE_GOODSLOST_MISTAKE_NOEXIST_ERROR.
//						getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常不正确");
//			}
//		}
//		//8.
//		recompenseMethod = "abnormal";
//		waybillAccidentList=null;
//		unbillAccident.setAccidentType(14);
//		try {
//			RecompenseValidator.canCreateRecompense(
//					recompenseMethod, waybillInfo, 
//						waybillAccidentList, unbillAccident, old);
//			fail("大人，未抛出异常。");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.
//					RECOMPENSE_ABNORMAL_MISTAKE_NOEXIST_ERROR.
//						getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常不正确");
//			}
//		}
//		
//		//9.
//		recompenseMethod = "xxxxxx";
//		try {
//			RecompenseValidator.canCreateRecompense(
//					recompenseMethod, waybillInfo, 
//						waybillAccidentList, unbillAccident, old);
//			fail("大人，未抛出异常。");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.
//					RECOMPENSE_GOODSLOST_MISTAKE_NOEXIST_ERROR.
//						getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常不正确");
//			}
//		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-12-4上午9:10:58
	 * void
	 * @update 2012-12-4上午9:10:58
	 */
	@Test
	public void testCanCreateOnlineRecompense(){
//		//WaybillInfo waybillInfo,
//		//List<OaAccidentInfo> waybillAccidentList, RecompenseApplication old
//		WaybillInfo waybillInfo = new WaybillInfo();
//		List<OaAccidentInfo>waybillAccidentList = new ArrayList<OaAccidentInfo>();
//		
//		RecompenseApplication old = new RecompenseApplication();
//		old.setId("德邦008");
//		//1.old不为空
//		try {
//			RecompenseValidator.
//			canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
//			fail("大人，木有异常！");
//		} catch (GeneralException e) {
//			if (e.getErrorCode().equals(
//					RecompenseExceptionType.RECOMPENSE_RECORD_EXIST_ERROR.getErrorCode())) {
//				assertTrue(true);
//			}else{
//				fail("大人，异常异常，肿么办！");
//			}
//		}
//		//2.old为空,waybillInfo为空
//		old = null;
//		waybillInfo=null;
//		try {
//			RecompenseValidator.
//			canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
//			fail("大人，木有异常");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR
//					.getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常异常");
//			}
//		}
//		//3.old为空,waybillInfo不为空，
//		waybillInfo = new WaybillInfo();
//		waybillInfo.setFailedSignReceive(false);
//		try {
//			RecompenseValidator.
//			canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
//			fail("大人，木有异常");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR
//					.getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常异常");
//			}
//		}
//		
//		//4.old为空,waybillInfo不为空，
//				//waybillInfo.isFailedSignReceive()==true
//		waybillInfo.setFailedSignReceive(true);
//		RecompenseValidator.
//		canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
//		
//		//5.old为空,waybillInfo为空,waybillAccidentList也为空
//		waybillInfo = null;
//		waybillAccidentList = null;
//		try {
//			RecompenseValidator.
//			canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
//			fail("大人，木有异常");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR
//					.getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常异常");
//			}
//		}
//		
//		//6.old为空,waybillInfo为空,waybillAccidentList不为空，但是类型匹配不上
//		OaAccidentInfo oa = new OaAccidentInfo();
//		waybillAccidentList = new ArrayList<OaAccidentInfo>();
//		oa.setInsuredAmount(150);
//		oa.setAccidentType(1529);
//		waybillAccidentList.add(oa);
//		try {
//			RecompenseValidator.
//			canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
//			fail("大人，木有异常");
//		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(
//					RecompenseExceptionType.RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR
//					.getErrorCode())){
//				assertTrue(true);
//			}else{
//				fail("大人，异常异常");
//			}
//		}
//		
//		//7.old为空,waybillInfo为空,waybillAccidentList不为空，
//			//但是类型为OA_ACCIDENT_ABNORMAL_SIGN
//		oa.setAccidentType(2);
//		RecompenseValidator.
//		canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
//		
//		//8.old为空,waybillInfo为空,waybillAccidentList不为空，
//		//但是类型为OA_ACCIDENT_LOST_GOODS
//		oa.setAccidentType(14);
//		RecompenseValidator.
//		canCreateOnlineRecompense(waybillInfo, waybillAccidentList, old);
		
	}
	@Test
	public void testIsDoableInfo() {
		String info = "recompense";
		List<String> infoList = new ArrayList<String>();
		infoList.add("hello");
		boolean result = false;
		try {
			result = RecompenseValidator.isDoableInfo(info, infoList);
			assertFalse(result);
		} catch (GeneralException e) {
			fail("数据正常抛出异常");
		}
		infoList.add(info);
		try {
			result = RecompenseValidator.isDoableInfo(info, infoList);
			assertTrue(result);
		} catch (GeneralException e) {
			fail("数据正常抛出异常");
		}

	}

	@Test
	public void testValidateRecompenseNull() {
		RecompenseApplication app = null;
		boolean result = false;
		try {
			result = RecompenseValidator.validateRecompenseNull(app);
			assertTrue(result);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_APPLICATION_NULL
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		app = new RecompenseApplication();
		result = RecompenseValidator.validateRecompenseNull(app);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void testValidateOAWorkflowNull() {
		OAWorkflow oaWorkflow = null;
		boolean result = false;
		try {
			result = RecompenseValidator.validateOAWorkflowNull(oaWorkflow);
			assertTrue(result);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_OA_WORKFLOW_NULL
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		oaWorkflow = new OAWorkflow();
		result = RecompenseValidator.validateOAWorkflowNull(oaWorkflow);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void testValidateRecompenseStatus() {
		String status = "recompense";
		List<String> statusList = new ArrayList<String>();
		statusList.add("hello");
		boolean result = false;
		try {
			result = RecompenseValidator.validateRecompenseStatus(status,
					statusList);
			assertFalse(result);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_STATUS_NOTMATCH_ERROR
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		statusList.add(status);
		result = RecompenseValidator.validateRecompenseStatus(status,
				statusList);
		assertTrue(result);
	}

	@Test
	public void testValidateRecompenseStatusOne() {
		String oldStatus = "recompense";
		String maacthStatus = "recompense1";
		boolean result = false;
		try {
			result = RecompenseValidator.validateRecompenseStatus(oldStatus,
					maacthStatus);
			assertFalse(result);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_STATUS_NOTMATCH_ERROR
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		maacthStatus="recompense";
		result = RecompenseValidator.validateRecompenseStatus(oldStatus,
				maacthStatus);
		assertTrue(result);
	}
	@Test
	public void testValidateRecompenseOverpay(){
		boolean result = false;
		RecompenseApplication app = new RecompenseApplication();
			result = RecompenseValidator.validateRecompenseOverpay(app);
				
	}

}
