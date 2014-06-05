package com.deppon.crm.module.recompense.server.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.BankAccount;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
import com.deppon.crm.module.recompense.shared.domain.PaymentBill;
import com.deppon.crm.module.recompense.shared.domain.RecalledCompensation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

public class TestDataCreator {

	public static RecompenseApplication createBase() {
		Waybill waybill = creatWaybill();
		RecompenseApplication app = new RecompenseApplication();

		Member cust = new Member();
		cust.setId("2");
		app.setCustomer(cust);

		// app.setWorkflowId(11111111l);
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		app.setRecompenseType(Constants.LOST_GOODS);
		app.setWaybill(waybill);
		app.setRecompenseNum("9090095");
		app.setRealAmount(30000d);
		app.setNormalAmount(300000d);
		app.setInsurType("2");
		app.setInsurQuantity(43);
		app.setReportDept("10277");
		app.setInsurDate(new Date(2011, 9, 25, 8, 25, 32));
		app.setReportMan("23722");
		app.setReportDate(new Date(2011, 9, 26, 8, 25, 32));
		app.setDeptId("157788");
		app.setDeptName("北京南部大区");
		app.setRecompenseAmount(5464564.23);
		// app.setAgreeRecompense(false);
		app.setClaimParty("1");
		app.setCreateUser("123");
		app.setCreateDate(new Date());
		app.setCompanyFax("AX-9989-9090950");
		app.setCompanyPhone("9090950");
		app.setCompanyName("人民大会堂");
		app.setStatus(Constants.STATUS_SUBMITED);

		return app;
	}

	public static RecompenseApplication createReportApp() {
		RecompenseApplication app = createBase();
		List<GoodsTrans> goodsList = new ArrayList<GoodsTrans>();
		goodsList.add(createGoodsTrans());
		goodsList.add(createGoodsTrans());
		app.setGoodsTransList(goodsList);

		List<IssueItem> issueList = new ArrayList<IssueItem>();
		issueList.add(createIssueItem());
		issueList.add(createIssueItem());
		app.setIssueItemList(issueList);

		List<RecompenseAttachment> recomAttList = new ArrayList<RecompenseAttachment>();
		recomAttList.add(createRecompenseAttachment());
		recomAttList.add(createRecompenseAttachment());
		app.setAttachmentList(recomAttList);

		return app;
	}

	public static List<GoodsTrans> createGoodsTransList() {
		List<GoodsTrans> goodsList = new ArrayList<GoodsTrans>();
		goodsList.add(createGoodsTrans());
		goodsList.add(createGoodsTrans());
		return goodsList;
	}

	public static List<IssueItem> createIssueItemList() {
		List<IssueItem> issueList = new ArrayList<IssueItem>();
		issueList.add(createIssueItem());
		issueList.add(createIssueItem());
		return issueList;

	}

	public static List<RecompenseAttachment> createRecompenseAttachmentList() {
		List<RecompenseAttachment> recomAttList = new ArrayList<RecompenseAttachment>();
		recomAttList.add(createRecompenseAttachment());
		recomAttList.add(createRecompenseAttachment());
		return recomAttList;
	}

	public static List<AwardItem> createAwardItemList() {
		List<AwardItem> awardList = new ArrayList<AwardItem>();
		awardList.add(createAwardItem());
		awardList.add(createAwardItem());
		return awardList;
	}

	public static List<DeptCharge> createDeptChargeList() {
		List<DeptCharge> deptList = new ArrayList<DeptCharge>();
		deptList.add(createDeptCharge());
		deptList.add(createDeptCharge());
		return deptList;
	}

	public static List<ResponsibleDept> createResponsibleDeptList() {
		List<ResponsibleDept> redeptList = new ArrayList<ResponsibleDept>();
		redeptList.add(createResponsibleDept());
		redeptList.add(createResponsibleDept());
		return redeptList;
	}

	public static List<MessageReminder> createMessageReminderList() {
		List<MessageReminder> messageList = new ArrayList<MessageReminder>();
		messageList.add(createMessageReminder());
		messageList.add(createMessageReminder());
		return messageList;
	}

	public static RecompenseApplication createDisposeApp(
			RecompenseApplication app) {

		List<MessageReminder> messageList = new ArrayList<MessageReminder>();
		messageList.add(createMessageReminder());
		messageList.add(createMessageReminder());

		List<AwardItem> awardList = new ArrayList<AwardItem>();
		awardList.add(createAwardItem());
		awardList.add(createAwardItem());

		List<DeptCharge> deptList = new ArrayList<DeptCharge>();
		deptList.add(createDeptCharge());
		deptList.add(createDeptCharge());

		List<ResponsibleDept> redeptList = new ArrayList<ResponsibleDept>();
		redeptList.add(createResponsibleDept());
		redeptList.add(createResponsibleDept());
		app.setRecalledCom(createRecalledCompensation());
		app.getAttachmentList().add(createRecompenseAttachment());

		app.setDeptChargeList(deptList);
		app.setAwardItemList(awardList);
		app.setMessageReminderList(messageList);
		app.setResponsibleDeptList(redeptList);
		return app;
	}

	public static Waybill creatWaybill() {
		Date now = new Date();
		Waybill waybill = new Waybill();
		waybill.setWaybillNumber(Long.toString(now.getTime()));
		waybill.setId(Long.toString(now.getTime()));
		waybill.setPackaging("纸盒");
		waybill.setTransType("3");
		waybill.setInsured("王木子");
		waybill.setTelephone("139909950");
		waybill.setStartStation("北京");
		waybill.setEndStation("上海");
		waybill.setGoodsName("炸药");
		waybill.setPwv("45/234/22");
		waybill.setSendDate(now);
		waybill.setInsurAmount(34453.12);
		waybill.setReceiveDept("10277");
		return waybill;
	}

	public static GoodsTrans createGoodsTrans() {
		GoodsTrans trans = new GoodsTrans();
		trans.setGoodsName("倚天剑");
		trans.setPrice(10283.3);
		trans.setQuality(2);
		trans.setRealPrice(2234.4);
		return trans;
	}

	public static IssueItem createIssueItem() {
		IssueItem iItem = new IssueItem();
		iItem.setInsurType("1");
		iItem.setQuality(34);
		iItem.setDescription("打湿完了,没法放啊");
		return iItem;
	}

	public static RecompenseAttachment createRecompenseAttachment() {
		RecompenseAttachment attachment = new RecompenseAttachment();
		attachment.setAttachAddress("e:es/e/es");
		attachment.setAttachName("倚天剑秘籍");
		return attachment;
	}

	public static MessageReminder createMessageReminder() {
		MessageReminder messageReminder = new MessageReminder();
		// messageReminder.setReminderMan("张三丰");
		messageReminder.setReminderMethod("3");
		return messageReminder;

	}

	public static RecalledCompensation createRecalledCompensation() {
		RecalledCompensation comp = new RecalledCompensation();
		comp.setCompensateBackAmount(43534.22);
		comp.setCompensateBackTime(new Date(
				System.currentTimeMillis() - 8342435));
		comp.setDeptName("灭绝师太门");
		comp.setDeptId("2");
		comp.setRecoveryTime(new Date(System.currentTimeMillis() - 6342435));
		comp.setReturnDeductions(32.33);
		comp.setSuspendedAmount(343.44);
		return comp;
	}

	public static DeptCharge createDeptCharge() {
		DeptCharge charge = new DeptCharge();
		charge.setDeptId("1");
		charge.setDeptName("烧腊店");
		charge.setAmount(3232.2);
		return charge;
	}

	public static ResponsibleDept createResponsibleDept() {
		ResponsibleDept dept = new ResponsibleDept();
		dept.setDeptId("1");
		dept.setDeptName("东方求败派");
		return dept;
	}

	public static AwardItem createAwardItem() {
		AwardItem aItem = new AwardItem("1", "1", "1", "", "", "", 0D, "");
		return aItem;
	}

	public static PaymentBill createPaymentBill() {
		PaymentBill payment = new PaymentBill();
		payment.setFreightCollectAmount(24324.3);
		payment.setReceivableAmount(43534.43);
		payment.setPaymentType("2");
		Department dept1 = new Department();
		dept1.setId("1");
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccount("1");
		bankAccount.setBankName("1");
		bankAccount.setBranchName("1");
		bankAccount.setCity("1");
		bankAccount.setMobile("1");
		bankAccount.setOpenName("1");
		bankAccount.setProvince("1");
		payment.setBankAccount(bankAccount);
		// payment.setBelongfinance(dept1);
		return payment;
	}

	public static Message createMessage() {
		Message message = new Message();
		message.setUserId("1");
		message.setUserName("张三丰");
		message.setCreateTime(new Date());
		message.setContent("你的火炮儿全湿来，没法放了");
		return message;
	}
}
