package com.deppon.crm.module.client.common.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.CustomerCancel;
import com.deppon.crm.module.client.fin.domain.BillInfo;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.crm.module.client.fin.domain.CostDetail;
import com.deppon.crm.module.client.fin.domain.NoBillingRecompenseInfo;
import com.deppon.crm.module.client.fin.domain.ResponsibilityDeptInfo;
import com.deppon.crm.module.client.map.domain.CoordinateDetail;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.CustomerWaybill;
import com.deppon.crm.module.client.order.domain.CustomerWaybillInfo;
import com.deppon.crm.module.client.order.domain.ErpRemind;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillRecompenseInfo;

import com.deppon.crm.module.client.sync.cc.WaitingSendCcRequest;
import com.deppon.crm.module.client.sync.domain.WaitingSendRequest;
import com.deppon.crm.module.client.sync.foss.WaitingSendFossRequest;
import com.deppon.crm.module.client.workflow.domain.BillHead;
import com.deppon.crm.module.client.workflow.domain.Entry;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo.Gift;
import com.deppon.crm.module.client.workflow.domain.OtherBill;

import com.deppon.erp.payment.Msg;
import com.deppon.esb.esbtogis.CoordinateInfo;
import com.deppon.esb.esbtogis.QueryDeptCoordinateResponse;
import com.deppon.fin.selfservice.CashierAccount;
import com.deppon.fin.selfservice.CostDetailsType;
import com.deppon.fin.selfservice.GenerateClaimsapbillRequest;
import com.deppon.fin.selfservice.QueryCashieraccountResponse;
import com.deppon.foss.crm.AmountInfo;
import com.deppon.foss.crm.GoodsBillReceiveRequest;
import com.deppon.foss.crm.IsCustomerBlankOutList;
import com.deppon.foss.crm.QueryMoneyResponse;
import com.deppon.foss.crm.FossQueryAcctinfoResponse;
import com.deppon.foss.waybill.QueryDetailResponse;
import com.deppon.foss.waybill.WayBillDetail;

/**
 * @作者：罗典
 * @描述：接口客户端工具类
 * @时间：2012-9-13
 * */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClientTool {


	/**
	 * @description
	 *   将客户主数据对象转化为 发送FOSS系统的数据对象
	 * @author WMM
	 * @param waitingSendRequest
	 * @return
	 * @throws CrmBusinessException
	 */
	public static WaitingSendFossRequest converToWaitingSendFossRequest(WaitingSendRequest waitingSendRequest) throws CrmBusinessException{
		WaitingSendFossRequest waitingSendFossRequest = new WaitingSendFossRequest();
		try{
		if(waitingSendRequest!=null){
			com.deppon.crm.module.client.sync.foss.TCustCustbasedataOperation tCustCustbasedata = (com.deppon.crm.module.client.sync.foss.TCustCustbasedataOperation) BeanUtils.copyProperties(waitingSendRequest.gettCustCustbasedata(),com.deppon.crm.module.client.sync.foss.TCustCustbasedataOperation.class);
			waitingSendFossRequest.settCustCustbasedata(tCustCustbasedata);

			List<com.deppon.crm.module.client.sync.foss.TCustAccountOperation> tCustAccount = (List<com.deppon.crm.module.client.sync.foss.TCustAccountOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustAccount(), com.deppon.crm.module.client.sync.foss.TCustAccountOperation.class);
			waitingSendFossRequest.settCustAccount(tCustAccount);

			List<com.deppon.crm.module.client.sync.foss.TCustContractOperation> tCustContract = (List<com.deppon.crm.module.client.sync.foss.TCustContractOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustContract(), com.deppon.crm.module.client.sync.foss.TCustContractOperation.class);
			waitingSendFossRequest.settCustContract(tCustContract);

			List<com.deppon.crm.module.client.sync.foss.TCustContractTaxOperation> tCustContractTax = (List<com.deppon.crm.module.client.sync.foss.TCustContractTaxOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustContractTax(), com.deppon.crm.module.client.sync.foss.TCustContractTaxOperation.class);
			waitingSendFossRequest.settCustContractTax(tCustContractTax);

			List<com.deppon.crm.module.client.sync.foss.TCustCustlinkmanOperation> tCustCustlinkman = (List<com.deppon.crm.module.client.sync.foss.TCustCustlinkmanOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustCustlinkman(), com.deppon.crm.module.client.sync.foss.TCustCustlinkmanOperation.class);
			waitingSendFossRequest.settCustCustlinkman(tCustCustlinkman);

			List<com.deppon.crm.module.client.sync.foss.TCustShuttleaddressOperation> tCustShuttleaddress = (List<com.deppon.crm.module.client.sync.foss.TCustShuttleaddressOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustShuttleaddress(), com.deppon.crm.module.client.sync.foss.TCustShuttleaddressOperation.class);
			waitingSendFossRequest.settCustShuttleaddress(tCustShuttleaddress);

			List<com.deppon.crm.module.client.sync.foss.TCustPreferenceaddressOperation> tCustPreferenceaddress = (List<com.deppon.crm.module.client.sync.foss.TCustPreferenceaddressOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustPreferenceaddress(), com.deppon.crm.module.client.sync.foss.TCustPreferenceaddressOperation.class);
			waitingSendFossRequest.settCustPreferenceaddress(tCustPreferenceaddress);

			List<com.deppon.crm.module.client.sync.foss.TCustContractdeptOperation> tCustContractdept = (List<com.deppon.crm.module.client.sync.foss.TCustContractdeptOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustContractdept(), com.deppon.crm.module.client.sync.foss.TCustContractdeptOperation.class);
			waitingSendFossRequest.settCustContractdept(tCustContractdept);

			List<com.deppon.crm.module.client.sync.foss.TCustPreferentialOperation> tCustPreferential = (List<com.deppon.crm.module.client.sync.foss.TCustPreferentialOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustPreferential(), com.deppon.crm.module.client.sync.foss.TCustPreferentialOperation.class);
			waitingSendFossRequest.settCustPreferential(tCustPreferential);

		    }
		}catch(Exception e){
			throw new CrmBusinessException("1007",e.getMessage());
		}
		return waitingSendFossRequest;
	}

	/**
	 * @description
	 *   将客户主数据对象转化为 发送CC系统的数据对象
	 * @author WMM
	 * @param waitingSendRequest
	 * @return
	 * @throws CrmBusinessException
	 */
	public static WaitingSendCcRequest converToWaitingSendCcRequest(WaitingSendRequest waitingSendRequest) throws CrmBusinessException{
		WaitingSendCcRequest waitingSendCcRequest = new WaitingSendCcRequest();
		try{
		if(waitingSendRequest!=null){
			com.deppon.crm.module.client.sync.cc.TCustCustbasedataOperation tCustCustbasedata = (com.deppon.crm.module.client.sync.cc.TCustCustbasedataOperation) BeanUtils.copyProperties(waitingSendRequest.gettCustCustbasedata(),com.deppon.crm.module.client.sync.cc.TCustCustbasedataOperation.class);
			waitingSendCcRequest.settCustCustbasedata(tCustCustbasedata);

			List<com.deppon.crm.module.client.sync.cc.TCustAccountOperation> tCustAccount = (List<com.deppon.crm.module.client.sync.cc.TCustAccountOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustAccount(), com.deppon.crm.module.client.sync.cc.TCustAccountOperation.class);
			waitingSendCcRequest.settCustAccount(tCustAccount);

			List<com.deppon.crm.module.client.sync.cc.TCustContractOperation> tCustContract = (List<com.deppon.crm.module.client.sync.cc.TCustContractOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustContract(), com.deppon.crm.module.client.sync.cc.TCustContractOperation.class);
			waitingSendCcRequest.settCustContract(tCustContract);

			List<com.deppon.crm.module.client.sync.cc.TCustCustlinkmanOperation> tCustCustlinkman = (List<com.deppon.crm.module.client.sync.cc.TCustCustlinkmanOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustCustlinkman(), com.deppon.crm.module.client.sync.cc.TCustCustlinkmanOperation.class);
			waitingSendCcRequest.settCustCustlinkman(tCustCustlinkman);

			List<com.deppon.crm.module.client.sync.cc.TCustShuttleaddressOperation> tCustShuttleaddress = (List<com.deppon.crm.module.client.sync.cc.TCustShuttleaddressOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustShuttleaddress(), com.deppon.crm.module.client.sync.cc.TCustShuttleaddressOperation.class);
			waitingSendCcRequest.settCustShuttleaddress(tCustShuttleaddress);

			List<com.deppon.crm.module.client.sync.cc.TCustPreferenceaddressOperation> tCustPreferenceaddress = (List<com.deppon.crm.module.client.sync.cc.TCustPreferenceaddressOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustPreferenceaddress(), com.deppon.crm.module.client.sync.cc.TCustPreferenceaddressOperation.class);
			waitingSendCcRequest.settCustPreferenceaddress(tCustPreferenceaddress);

			List<com.deppon.crm.module.client.sync.cc.TCustContractdeptOperation> tCustContractdept = (List<com.deppon.crm.module.client.sync.cc.TCustContractdeptOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustContractdept(), com.deppon.crm.module.client.sync.cc.TCustContractdeptOperation.class);
			waitingSendCcRequest.settCustContractdept(tCustContractdept);

			List<com.deppon.crm.module.client.sync.cc.TCustPreferentialOperation> tCustPreferential = (List<com.deppon.crm.module.client.sync.cc.TCustPreferentialOperation>) BeanUtils.copyProperties(waitingSendRequest.gettCustPreferential(), com.deppon.crm.module.client.sync.cc.TCustPreferentialOperation.class);
			waitingSendCcRequest.settCustPreferential(tCustPreferential);

		    }
		}catch(Exception e){
			throw new CrmBusinessException("1007",e.getMessage());
		}
		return waitingSendCcRequest;
	}

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-15
	 * @param response
	 * @return
	 * @throws CrmBusinessException
	 * CustomerWaybillInfo
	 */
	public static CustomerWaybillInfo convertToCustomerWaybillInfo(
			FossQueryAcctinfoResponse response) throws CrmBusinessException {
		try {
			CustomerWaybillInfo waybill = new CustomerWaybillInfo();
			waybill.setTotalNum(response.getTotalNum());
			List<CustomerWaybill> customerWaybills = (List<CustomerWaybill>) BeanUtils
					.copyProperties(
							response.getFossQueryAcctinfoList(),
							com.deppon.crm.module.client.order.domain.CustomerWaybill.class);
			waybill.getCustomerWaybill().addAll(customerWaybills);
			return waybill;
		} catch (Exception e) {
			throw new CrmBusinessException("1007", e.getMessage());
		}
	}


	public static List<CashierAccountInfo> convertToCashierAccountInfo(
			QueryCashieraccountResponse response) {
		List<CashierAccount> cashierInfo=response.getCashierAccountInformation();
		List<CashierAccountInfo> infos=new ArrayList<CashierAccountInfo>();
		for(int i=0;i<cashierInfo.size();i++){
			CashierAccount cashierAccount=cashierInfo.get(i);
			CashierAccountInfo info=new CashierAccountInfo();
			info.setAccount(cashierAccount.getAccount());
			info.setAccountNature(cashierAccount.getAccountNature());
			info.setBankBranchCode(cashierAccount.getBankBranchCode());
			info.setBankCityCode(cashierAccount.getBankCityCode());
			info.setBankCode(cashierAccount.getBankCode());
			info.setBankProvinceCode(cashierAccount.getBankProvinceCode());
			info.setMobile(cashierAccount.getMobile());
			info.setOpenBankUserName(cashierAccount.getOpenBankUserName());
			infos.add(info);
		}
		return infos;
	}

	/**
	 *
	 * @description
	 * @author wugenbin_吴根斌
	 * @version 2013-5-29
	 * @param
	 * @return
	 */
	public static GenerateClaimsapbillRequest convertBillInfo(BillInfo billInfo) {
		GenerateClaimsapbillRequest request = new GenerateClaimsapbillRequest();
		request.setAccountNature(billInfo.getAccountNature());
		request.setApplyUser(billInfo.getApplyUser());
		request.setBankAccount(billInfo.getBankAccount());
		request.setBankBranchCode(billInfo.getBankBranchCode());
		request.setBankCityCode(billInfo.getBankCityCode());
		request.setBankCode(billInfo.getBankCode());
		request.setBankProvinceCode(billInfo.getBankProvinceCode());
		request.setMobilePhone(billInfo.getMobilePhone());
		request.setReceiverName(billInfo.getUserName());
		request.setRecompenseAmount(billInfo.getRecompenseAmount());
		request.setVoucherNumber(billInfo.getBillNum());
		request.setWorkflowtype(billInfo.getDrawMoneyType());
		List<CostDetailsType> requestDetails = new ArrayList<CostDetailsType>();
		for (int i = 0; i < billInfo.getCostDetails().size(); i++) {
			CostDetailsType requestDetail = new CostDetailsType();
			CostDetail detail = billInfo.getCostDetails().get(i);
			requestDetail.setBillNum(detail.getVoucherNumber());
			requestDetail.setCostDate(detail.getCostDate());
			requestDetail.setRecompenseType(detail.getRecompenseType());
			requestDetail.setReimbursementMoneyDetail(detail
					.getReimbursementMoneyDetail());
			requestDetail.setResponsibilityDeptInfo(detail
					.getResponsibilityDeptInfo());
			requestDetails.add(requestDetail);
		}
		request.getCostDetails().addAll(requestDetails);
		return request;
	}

	/**
	 *
	 * @description
	 * @author wugenbin_吴根斌
	 * @version 2013-5-10
	 * @param
	 * @return
	 */
	public static List<CustomerCancel> convertToCustCancel(
			List<IsCustomerBlankOutList> customerNumberList) {

		List<CustomerCancel> customerCancelList = new ArrayList<CustomerCancel>();
		for (int i = 0; i < customerNumberList.size(); i++) {
			CustomerCancel cc = new CustomerCancel();
			cc.setCustomerCoder(customerNumberList.get(i).getCustomerCode());
			cc.setCustomerBlankOut(customerNumberList.get(i)
					.isIsCustomerBlankOut());
			customerCancelList.add(cc);
		}
		return customerCancelList;
	}

	/**
	 *
	 * @description 生成报账报销单接口请求参数空值校验
	 * @author wugenbin_吴根斌
	 * @version 2013-4-26
	 * @param
	 * @return
	 * @throws CrmBusinessException
	 */
	public static void checkClaimsapbillEmpty(
			GenerateClaimsapbillRequest generateClaimsapbillRequest)
			throws CrmBusinessException {

		NullOrEmptyValidator.checkEmpty(generateClaimsapbillRequest,
				"generateClaimsapbillRequest");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getRecompenseAmount(),
				"recompenseAmount");
		NullOrEmptyValidator
				.checkEmpty(generateClaimsapbillRequest.getAccountNature(),
						"accountNature");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getBankAccount(), "bankAccount");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getBankCode(), "bankCode");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getBankBranchCode(),
				"bankBranchCode");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getBankCityCode(), "bankCityCode");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getBankProvinceCode(),
				"bankProviceCode");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getApplyUser(), "applyUser");
		NullOrEmptyValidator
				.checkEmpty(generateClaimsapbillRequest.getVoucherNumber(),
						"voucherNumber");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getReceiverName(), "receiverName");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getWorkflowtype(), "workflowtype");
		NullOrEmptyValidator.checkEmpty(
				generateClaimsapbillRequest.getCostDetails(), "costDetails");
		List<CostDetailsType> costDetail = generateClaimsapbillRequest
				.getCostDetails();
		for (int i = 0; i < costDetail.size(); i++) {
			NullOrEmptyValidator.checkEmpty(costDetail.get(i).getCostDate(),
					"costDate");
			NullOrEmptyValidator.checkEmpty(costDetail.get(i).getBillNum(),
					"billNum");
			NullOrEmptyValidator.checkEmpty(costDetail.get(i)
					.getRecompenseType(), "recompenseType");
			NullOrEmptyValidator.checkEmpty(costDetail.get(i)
					.getReimbursementMoneyDetail(), "reimbursementMoneyDetail");
			NullOrEmptyValidator.checkEmpty(costDetail.get(i)
					.getResponsibilityDeptInfo(), "responsibilityDeptInfo");
		}
	}

	/**
	 * @作者：吴根斌
	 * @描述：坐标实体转换
	 * @时间：2013-05-3
	 * */
	public static List<CoordinateDetail> convertGistoCoordinate(
			QueryDeptCoordinateResponse response) {
		List<CoordinateInfo> infos = response.getCoordinateList();
		List<CoordinateDetail> details = new ArrayList<CoordinateDetail>();
		for (int i = 0; i < infos.size(); i++) {
			CoordinateInfo info = infos.get(i);
			CoordinateDetail detail = new CoordinateDetail();
			detail.setAddress(info.getAddress());
			detail.setBaiduLat(info.getBaiduLat());
			detail.setBaiduLng(info.getBaiduLng());
			detail.setDeptName(info.getDeptName());
			detail.setDesc(info.getDesc());
			detail.setGoogleLat(info.getGoogleLat());
			detail.setGoogleLng(info.getGoogleLng());
			detail.setType(info.getType());
			details.add(i, detail);
		}
		return details;
	}

	/**
	 * @作者：罗典
	 * @描述：积分实体转换
	 * @时间：2012-9-13
	 * */
	public static OtherBill changeGigtToBill(GiftApplyInfo giftApplyInfo) {
		OtherBill bill = new OtherBill();
		BillHead head = new BillHead();
		head.setApplicant(giftApplyInfo.getApplyPersonNumber());
		head.setApplyOrgUnit(giftApplyInfo.getReceiveDept());
		head.setApplyDate(DateUtils.getCurrentDate());
		head.setAddress(giftApplyInfo.getReceiveAddress());
		head.setPhone(giftApplyInfo.getPhone());
		bill.setBillHead(head);
		List<Gift> gifts = giftApplyInfo.getGifts();
		List<Entry> billEntries = new ArrayList<Entry>();
		for (Gift gift : gifts) {
			Entry entry = new Entry();
			entry.setArticleNum(gift.getGiftNumber());
			entry.setQuantity(gift.getGiftCount());
			entry.setReasonReq(giftApplyInfo.getApplyReason());
			billEntries.add(entry);
		}
		bill.setBillEntries(billEntries);
		return bill;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-10-19
	 * @描述：转换订单来源类型
	 * */
	public static String convertUserSource(String bhoSource) {
		// 空: 官网 1：淘宝网 3：阿里巴巴 4：金蝶友商 5：淘宝商城
		if (bhoSource == null || bhoSource.equals("")) {
			return "ONLINE";
		} else if (bhoSource != null && bhoSource.equals("1")) {
			return "TAOBAO";
		} else if (bhoSource != null && bhoSource.equals("3")) {
			return "ALIBABA";
		} else if (bhoSource != null && bhoSource.equals("4")) {
			return "YOUSHANG";
		} else if (bhoSource != null && bhoSource.equals("5")) {
			return "SHANGCHENG";
		} else if (bhoSource != null && bhoSource.equals("7")) {
			return "QQSUDI";
		} else {
			return "ONLINE";
		}
	}

	/**
	 * @作者：罗典
	 * @描述：ERP消息提醒实体转换
	 * @时间：2012-10-25
	 * */
	public static Msg convertErpRemindToMsg(ErpRemind remind) {
		Msg msg = new Msg();
		msg.setFdescription(remind.getFdescription());
		msg.setFlastupdateDate(remind.getFlastupdateDate());
		msg.setFmsgNum(remind.getFmsgNum());
		msg.setFstandardcode(remind.getFstandardcode());
		msg.setFsubModule(remind.getFsubModule());
		msg.setFsubModuleNum(remind.getFsubModuleNum());
		msg.setFsubSys(remind.getFsubSys());
		return msg;
	}

	/**
	 * @作者：罗典
	 * @描述：转换Header
	 * @时间：2012-11-2
	 * */
	public Map convertObjectToMap(Object obj) {
		Map map = new HashMap();
		try {
			Class clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getType().isAssignableFrom(List.class)) {
					continue;
				}
				Object objValue = field.get(obj);
				if (objValue != null) {
					map.put(field.getName(), objValue);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * @作者：罗典
	 * @描述：客户发到货金额集合转换
	 * @时间：2012-11-6
	 * */
	public static List<WaybillInfo> convertToWaybills(
			QueryMoneyResponse response) {
		List<WaybillInfo> waybillInfos = new ArrayList<WaybillInfo>();
		for (AmountInfo info : response.getAmountInfo()) {
			WaybillInfo billInfo = new WaybillInfo();
			billInfo.setWaybillNumber(info.getWaybillNo());
			billInfo.setShipperment(info.isIsSender());
			billInfo.setCustomerType(info.getCustType());
			billInfo.setPrepaid(validataBigDecimal(info.getPrePayment())
					.doubleValue());
			billInfo.setFreightCollect(validataBigDecimal(
					info.getArrivePayment()).doubleValue());
			billInfo.setServicefee(validataBigDecimal(info.getServiceFee())
					.doubleValue());
			billInfo.setCollectionCharges(validataBigDecimal(info.getRefund())
					.doubleValue());
			waybillInfos.add(billInfo);
		}
		return waybillInfos;
	}

	/**
	 * @作者：罗典
	 * @描述：约车信息转换
	 * @时间：2012-11-6
	 * */
	public static GoodsBillReceiveRequest convertToGoodsBill(
			AppointmentCarInfo appointmentCarInfo) {
		GoodsBillReceiveRequest goodsBillReceiveRequest = new GoodsBillReceiveRequest();
		goodsBillReceiveRequest.setCustName(appointmentCarInfo.getCustName());
		goodsBillReceiveRequest.setCustMobile(appointmentCarInfo
				.getCustMobile());
		goodsBillReceiveRequest.setCustTel(appointmentCarInfo.getCustTel());
		goodsBillReceiveRequest.setReceiveProvince(appointmentCarInfo
				.getCustProvince());
		goodsBillReceiveRequest
				.setReceiveCity(appointmentCarInfo.getCustCity());
		goodsBillReceiveRequest.setReceiveCounty(appointmentCarInfo
				.getCustArea());
		goodsBillReceiveRequest.setReceiveDetailAddress(appointmentCarInfo
				.getCustAddress());
		goodsBillReceiveRequest.setGoodsName(appointmentCarInfo.getGoodsName());
		goodsBillReceiveRequest.setWeight(validataBigDecimal(
				appointmentCarInfo.getWeight()).doubleValue());
		goodsBillReceiveRequest.setCubage(validataBigDecimal(
				appointmentCarInfo.getCubage()).doubleValue());
		goodsBillReceiveRequest.setPacking(appointmentCarInfo.getPacking());
		goodsBillReceiveRequest.setPieces(validateInterger(appointmentCarInfo.getPieces()));
		//收货地址
		goodsBillReceiveRequest.setConsigneeAddress(appointmentCarInfo.getCustArrAddress());
		goodsBillReceiveRequest.setSize(appointmentCarInfo.getSize());
		// 添加 备注
		goodsBillReceiveRequest.setDesc(appointmentCarInfo.getRemark());
		// 数据字典-运输性质
		goodsBillReceiveRequest.setTransProperty(appointmentCarInfo
				.getTransProperty());
		// 数据字段-提货方式
		goodsBillReceiveRequest.setDeliverMode(appointmentCarInfo
				.getDeliverMode());
		goodsBillReceiveRequest.setLadingStationId(appointmentCarInfo
				.getDeptCode());
		// TODO 货物类型
		/**
		 * 货物类型 A是1 B是2 无是null if(appointmentCarInfo.getGoodsType() == null ||
		 * appointmentCarInfo.getGoodsType().equals("")){
		 * appointmentCarInfo.setGoodsType(null); }else
		 * if(appointmentCarInfo.getGoodsType().equals("A")){
		 * appointmentCarInfo.setGoodsType("1"); }else
		 * if(appointmentCarInfo.getGoodsType().equals("B")){
		 * appointmentCarInfo.setGoodsType("2"); }else{
		 * appointmentCarInfo.setGoodsType(null); }
		 */
		goodsBillReceiveRequest.setGoodsType(appointmentCarInfo.getGoodsType());
		goodsBillReceiveRequest.setUsingVehicleDeptNum(appointmentCarInfo
				.getUsingVehicleDeptNum());
		goodsBillReceiveRequest.setUsingVehicleDeptId(appointmentCarInfo
				.getUsingVehicleDeptId());
		goodsBillReceiveRequest.setFirstPickTime(appointmentCarInfo
				.getFirstPickTime());
		goodsBillReceiveRequest.setLastPickTime(appointmentCarInfo
				.getLastPickTime());
		goodsBillReceiveRequest.setVehDeptNum(appointmentCarInfo
				.getDeliveryVehNum());
		goodsBillReceiveRequest.setVehDeptId(appointmentCarInfo
				.getDeliveryVehId());
		goodsBillReceiveRequest.setOrderNumber(appointmentCarInfo
				.getOrderNumber());
		goodsBillReceiveRequest.setOrderedTime(appointmentCarInfo
				.getOrderedTime());
		goodsBillReceiveRequest.setOrderType(appointmentCarInfo.getOrderType());
		goodsBillReceiveRequest.setOrderOwnDeptId(appointmentCarInfo
				.getVehDeptNum());
		goodsBillReceiveRequest.setCreatorNum(appointmentCarInfo
				.getCreatorNum());
		goodsBillReceiveRequest.setCreatorId(appointmentCarInfo.getCreatorId());
		goodsBillReceiveRequest.setMemberType(appointmentCarInfo.getMemberType());
		goodsBillReceiveRequest.setCouponNumber(appointmentCarInfo.getCouponNumber());
		goodsBillReceiveRequest.setWaybillNumber(appointmentCarInfo.getWaybillNumber());
//		goodsBillReceiveRequest.setPaidMethod(appointmentCarInfo.getPaidMethod());
		return goodsBillReceiveRequest;
	}


	/**
	 * @作者：罗典
	 * @描述：运单集合实体转换
	 * @时间：2012-11-7
	 * */
	public static List<FossWaybillInfo> convertToFossWaybill(
			QueryDetailResponse response) {
		if (response == null || response.getWayBillDetailList() == null
				|| response.getWayBillDetailList().size() == 0) {
			return null;
		}
		List<FossWaybillInfo> waybillInfos = new ArrayList<FossWaybillInfo>();
		for (WayBillDetail detail : response.getWayBillDetailList()) {
			FossWaybillInfo fossWaybillInfo = new FossWaybillInfo();
			fossWaybillInfo.setNumber(detail.getNumber());
			fossWaybillInfo.setTranType(detail.getTranType());
			fossWaybillInfo.setSender(detail.getSender());
			fossWaybillInfo.setSenderPhone(detail.getSenderPhone());
			fossWaybillInfo.setSenderMobile(detail.getSenderMobile());
			fossWaybillInfo.setDeparture(detail.getDeparture());
			fossWaybillInfo.setSenderAddress(detail.getSenderAddress());
			fossWaybillInfo.setConsignee(detail.getConsignee());
			fossWaybillInfo.setConsigneePhone(detail.getConsigneePhone());
			fossWaybillInfo.setConsigneeMobile(detail.getConsigneeMobile());
			fossWaybillInfo.setDestination(detail.getDestination());
			fossWaybillInfo.setConsigneeAddress(detail.getConsigneeAddress());
			fossWaybillInfo.setGoodName(detail.getGoodName());
			fossWaybillInfo.setPieces(detail.getPieces());
			fossWaybillInfo.setWeight(detail.getWeight());
			fossWaybillInfo.setCubage(detail.getCubage());
			fossWaybillInfo.setTotalCharge(detail.getTotalCharge());
			fossWaybillInfo.setPayment(detail.getPayment());
			fossWaybillInfo.setPreCharge(detail.getPreCharge());
			fossWaybillInfo.setArriveCharge(detail.getArriveCharge());
			fossWaybillInfo.setRefundType(detail.getRefundType());
			fossWaybillInfo.setRefund(detail.getRefund());
			fossWaybillInfo.setRefundFee(detail.getRefundFee());
			fossWaybillInfo.setDeliveryType(detail.getDeliveryType());
			fossWaybillInfo.setConsignCharge(detail.getConsignCharge());
			fossWaybillInfo.setDeliveryCharge(detail.getDeliveryCharge());
			fossWaybillInfo.setSignBackCharge(detail.getSignBackCharge());
			fossWaybillInfo.setPickCharge(detail.getPickCharge());
			fossWaybillInfo.setLaborRebate(detail.getLaborRebate());
			fossWaybillInfo.setPublishCharge(detail.getPublishCharge());
			fossWaybillInfo.setDepartureDeptName(detail.getDepartureDeptName());
			fossWaybillInfo.setDepartureDeptNumber(detail
					.getDepartureDeptNumber());
			fossWaybillInfo.setDepartureDeptPhone(detail
					.getDepartureDeptPhone());
			fossWaybillInfo.setLadingStationName(detail.getLadingStationName());
			fossWaybillInfo.setLadingStationNumber(detail
					.getLadingStationNumber());
			fossWaybillInfo.setLadingStationPhone(detail
					.getLadingStationPhone());
			fossWaybillInfo.setIsSigned(detail.isIsSigned());
			fossWaybillInfo.setIsNormalSigned(detail.isIsNormalSigned());
			fossWaybillInfo.setSignRecorderId(detail.getSignRecorderId());
			fossWaybillInfo.setSignedDate(detail.getSignedDate());
			fossWaybillInfo.setSignedDesc(detail.getSignedDesc());
			fossWaybillInfo.setOrderNumber(detail.getOrderNumber());
			fossWaybillInfo.setInsuranceValue(detail.getInsuranceValue());
			fossWaybillInfo.setInsurance(detail.getInsurance());
			fossWaybillInfo.setPacking(detail.getPacking());
			fossWaybillInfo.setOrderState(detail.getOrderState());
			fossWaybillInfo.setOtherPayment(detail.getOtherPayment());
			fossWaybillInfo.setTranDesc(detail.getTranDesc());
			fossWaybillInfo.setSenderNumber(detail.getSenderNumber());
			fossWaybillInfo.setConsigneeNumber(detail.getConsigneeNumber());
			fossWaybillInfo.setIsClear(detail.getIsClear());
			fossWaybillInfo.setSignBackType(detail.getSignBackType());
			// fossWaybillInfo.setStowageType(detail.getStowageType());
			fossWaybillInfo.setTransNotice(detail.getTransNotice());
			fossWaybillInfo.setSendTime(detail.getSendTime());
			fossWaybillInfo.setReceiveDeptName(detail.getReceiveDeptName());
			fossWaybillInfo.setReceiveDeptNumber(detail.getReceiveDeptNumber());
			fossWaybillInfo.setStowageDept(detail.getStowageDept());
			fossWaybillInfo.setTranProperty(detail.getTranProperty());
			fossWaybillInfo.setDepartureDeptAddr(detail.getDepartureDeptAddr());
			fossWaybillInfo.setDepartureDeptFax(detail.getDepartureDeptFax());
			fossWaybillInfo.setLadingStationAddr(detail.getLadingStationAddr());
			fossWaybillInfo.setLadingStationFax(detail.getLadingStationFax());
			fossWaybillInfo.setSenderCityCode(detail.getSenderCityCode());
			fossWaybillInfo.setSenderCityName(detail.getSenderCityName());
			fossWaybillInfo.setSenderProvinceCode(detail
					.getSenderProvinceCode());
			fossWaybillInfo.setSenderProvinceName(detail
					.getSenderProvinceName());
			fossWaybillInfo.setConsigneeCityCode(detail.getConsigneeCityCode());
			fossWaybillInfo.setConsigneeCityName(detail.getConsigneeCityName());
			fossWaybillInfo.setConsigneeProvinceCode(detail
					.getConsigneeProvinceCode());
			fossWaybillInfo.setConsigneeProvinceName(detail
					.getConsigneeProvinceName());
			fossWaybillInfo.setIsDoorToDoorPick(detail.isIsDoorToDoorPick());
			fossWaybillInfo.setSmsNoticeResult(detail.getSmsNoticeResult());
			fossWaybillInfo.setSignBillBackWay(detail.getSignBillBackWay());
			// 2013.03.25 添加了第一次签收时间
			fossWaybillInfo.setFirstSignedDate(detail.getFirstSignedDate());
			fossWaybillInfo.setExDepartureCourierName(detail.getExDepartureCourierName());
			fossWaybillInfo.setExDepartureCourierNumber(detail.getExDepartureCourierNumber());
			fossWaybillInfo.setExDepartureDeptName(detail.getExDepartureDeptName());
			fossWaybillInfo.setExDepartureDeptNumber(detail.getExDepartureDeptNumber());
			fossWaybillInfo.setExDepartureDeptStandardNumber(detail.getExDepartureDeptStandardNumber());
			fossWaybillInfo.setExDepartureRegionName(detail.getExDepartureRegionName());
			fossWaybillInfo.setExDepartureRegionNubmer(detail.getExDepartureRegionNubmer());
			fossWaybillInfo.setExDepartureRegionStandardNubmer(detail.getExDepartureRegionStandardNubmer());
			fossWaybillInfo.setExDestinationCourierName(detail.getExDestinationCourierName());
			fossWaybillInfo.setExDestinationCourierNumber(detail.getExDestinationCourierNumber());
			fossWaybillInfo.setExDestinationDeptName(detail.getExDestinationDeptName());
			fossWaybillInfo.setExDestinationDeptNumber(detail.getExDestinationDeptNumber());
			fossWaybillInfo.setExDestinationDeptStandardNumber(detail.getExDestinationDeptStandardNumber());
			fossWaybillInfo.setExDestinationRegionName(detail.getExDestinationRegionName());
			fossWaybillInfo.setExDestinationRegionNubmer(detail.getExDestinationRegionNubmer());
			fossWaybillInfo.setExDestinationRegionStandardNubmer(detail.getExDestinationRegionStandardNubmer());
			waybillInfos.add(fossWaybillInfo);
		}
		return waybillInfos;
	}

	/**
	 * @作者：罗典
	 * @描述：转换理赔上报运单信息
	 * @时间：2013-01-08
	 * */
	public static WaybillInfo convertToWaybillInfo(WayBillDetail detail) {
		WaybillInfo info = new WaybillInfo();
		info.setPackaging(detail.getPacking());
		// TODO 设置为汽运或空运
		info.setTransportType(detail.getTranProperty());
		info.setShipperTelephone(detail.getSenderPhone());
		info.setShipperPhone(detail.getSenderMobile());
		info.setLeaveDept(detail.getDepartureDeptNumber());
		info.setDestinationStation(detail.getDestination());
		info.setBizDate(detail.getSendTime());
		info.setShipmentDate(detail.getSendTime());
		info.setReceiveDept(detail.getLadingStationNumber());
		info.setShipper(detail.getSender());
		info.setGoodsName(detail.getGoodName());
		info.setWeight(validataFloat(detail.getWeight()));
		info.setPiece(validataFloat(detail.getCubage()).intValue());
		info.setVolumn(detail.getCubage());
		info.setInsurance(validataBigDecimal(detail.getInsuranceValue())
				.doubleValue());
		info.setStartStation(detail.getDeparture());
		info.setShipperCustomer(detail.getSenderNumber());
		info.setReceiveCustomer(detail.getConsigneeNumber());
		info.setFailedSignReceive((detail.isIsSigned() && !detail
				.isIsNormalSigned()));
		return info;
	}

	/**
	 * @作者：罗典
	 * @描述：转换在线理赔运单信息
	 * @时间：2013-01-08
	 * */
	public static WaybillRecompenseInfo convertToWaybillRecompenseInfo(
			WayBillDetail detail) {
		WaybillRecompenseInfo info = new WaybillRecompenseInfo();
		info.setShipperMobilePhone(detail.getSenderMobile());
		info.setShipperTelePhone(detail.getSenderPhone());
		info.setReceiveMobilePhone(detail.getConsigneeMobile());
		info.setReceiveTelephone(detail.getConsigneePhone());
		info.setReceiveDept(detail.getLadingStationNumber());
		info.setShipmentsDept(detail.getReceiveDeptNumber());
		info.setInsurance(validataBigDecimal(detail.getInsuranceValue())
				.doubleValue());
		info.setStartStation(detail.getDeparture());
		info.setDestinationStation(detail.getDestination());
		info.setShipmentsTime(detail.getSendTime());
		info.setTransportType(detail.getTranProperty());
		return info;
	}

	/**
	 * @作者：罗典
	 * @描述：转化收银员账号信息
	 * @时间：2013-01-18
	 * */
	public static CashierAccountInfo convertToCashierAccountInfo(
			String[] stringArray) {
		CashierAccountInfo cashierAccountInfo = new CashierAccountInfo();
		cashierAccountInfo.setBankProvinceCode(stringArray[0]);
		cashierAccountInfo.setBankCityCode(stringArray[1]);
		cashierAccountInfo.setBankBranchCode(stringArray[2]);
		cashierAccountInfo.setBankCode(stringArray[3]);
		cashierAccountInfo.setOpenBankUserName(stringArray[4]);
		cashierAccountInfo.setAccount(stringArray[5]);
		cashierAccountInfo.setAccountNature(stringArray[6]);
		cashierAccountInfo.setMobile(stringArray[7]);
		return cashierAccountInfo;
	}

	/**
	 * @作者：罗典
	 * @描述：转换未开单理赔信息为String[]
	 * @时间：2013-01-15
	 * */
	public static String[] convertToStringArray(NoBillingRecompenseInfo info) {
		int arrayCount = 13;
		if (info.getResponsibilityDeptInfos() != null) {
			arrayCount += 3 * info.getResponsibilityDeptInfos().size();
		}
		String[] noBillInfo = new String[arrayCount];
		noBillInfo[0] = info.getBillNum();
		noBillInfo[1] = info.getRecompenseAmount();
		noBillInfo[2] = info.getBankCode();
		noBillInfo[3] = info.getReceiverName();
		noBillInfo[4] = info.getBankAccount();
		noBillInfo[5] = info.getBankBranchCode();
		noBillInfo[6] = info.getBankProvinceCode();
		noBillInfo[7] = info.getBankCityCode();
		noBillInfo[8] = info.getMobilePhone();
		noBillInfo[9] = info.getDrawMoneyType();
		noBillInfo[10] = info.getRecompenseType();
		noBillInfo[11] = info.getApplyUser();
		noBillInfo[12] = info.getAccountNature();
		int i = 12;
		for (ResponsibilityDeptInfo dept : info.getResponsibilityDeptInfos()) {
			noBillInfo[1 + i++] = info.getBillNum();
			noBillInfo[1 + i++] = dept.getResponsibilityDeptCode();
			noBillInfo[1 + i++] = dept.getResponsibilityMoney();
		}
		return noBillInfo;
	}

	/**
	 * @作者：罗典
	 * @描述：过滤BigDecimal为空
	 * @时间：2012-11-6
	 * */
	public static BigDecimal validataBigDecimal(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return new BigDecimal(0);
		}
		return bigDecimal;
	}

	public static Integer validateInterger(Integer interger){
		if(interger==null){
			return new Integer(0);
		}
		return interger;
	}
	/**
	 * @作者：罗典
	 * @描述：过滤Float为空
	 * @时间：2012-11-6
	 * */
	public static Float validataFloat(Float floatt) {
		if (floatt == null) {
			return new Float(0);
		}
		return floatt;
	}

	/**
	 * @作者：罗典
	 * @描述：过滤String为空
	 * @时间：2012-11-6
	 * */
	public static String validataString(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}
}
