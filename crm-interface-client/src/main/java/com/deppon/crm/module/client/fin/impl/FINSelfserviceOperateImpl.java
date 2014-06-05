package com.deppon.crm.module.client.fin.impl;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.fin.IFINSelfserviceOperate;
import com.deppon.crm.module.client.fin.domain.BillInfo;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.fin.selfservice.CrmService;
import com.deppon.fin.selfservice.GenerateClaimsapbillRequest;
import com.deppon.fin.selfservice.GenerateClaimsapbillResponse;
import com.deppon.fin.selfservice.QueryCashieraccountRequest;
import com.deppon.fin.selfservice.QueryCashieraccountResponse;
import java.util.List;

public class FINSelfserviceOperateImpl implements IFINSelfserviceOperate {

	private CrmService fsscClaimsCash;

	public CrmService getFsscClaimsCash() {
		return fsscClaimsCash;
	}

	public void setFsscClaimsCash(CrmService fsscClaimsCash) {
		this.fsscClaimsCash = fsscClaimsCash;
	}

	/**
	 * @作者：吴根斌
	 * @时间：2013-04-15
	 * @描述：生成报账报销单接口
	 * @接口编号：FSSC_GENERATE_CLAIMSAPBILL
	 * */
	@Override
	public GenerateClaimsapbillResponse generateClaimsapbill(BillInfo billInfo)
			throws CrmBusinessException {
		GenerateClaimsapbillRequest generateClaimsapbillRequest = ClientTool
				.convertBillInfo(billInfo);
		ClientTool.checkClaimsapbillEmpty(generateClaimsapbillRequest);
		String payWay = generateClaimsapbillRequest.getWorkflowtype();
		GenerateClaimsapbillResponse response = new GenerateClaimsapbillResponse();
		if (payWay.equals("10")) {
			generateClaimsapbillRequest.setWorkflowtype("2");
		} else if (payWay.equals("20")) {
			generateClaimsapbillRequest.setWorkflowtype("1");
		}
		try {
			response = fsscClaimsCash
					.generateClaimsapbill(generateClaimsapbillRequest);
		} catch (Exception e) {
			throw new CrmBusinessException("1002", e.getMessage());
		}
		return response;
	}

	/**
	 * @作者：吴根斌
	 * @时间：2013-04-15
	 * @描述：查询收银员账号接口
	 * @接口编号：FSSC_QUERY_CASHIERACCOUNT
	 * */
	@Override
	public List<CashierAccountInfo> queryCashieraccount(
			QueryCashieraccountRequest queryCashieraccountRequest)
			throws CrmBusinessException {
		NullOrEmptyValidator.checkEmpty(queryCashieraccountRequest,
				"cashieraccountRequest");
		NullOrEmptyValidator.checkEmpty(
				queryCashieraccountRequest.getCashierNumber(), "cashierNumber");
		QueryCashieraccountResponse response = new QueryCashieraccountResponse();
		try {
			response = fsscClaimsCash
					.queryCashieraccount(queryCashieraccountRequest);
		} catch (Exception e) {
			throw new CrmBusinessException("1002", e.getMessage());
		}
//		if (!response.isIsSuccess()) {
//			throw new CrmBusinessException("1002", response.getFailedReason());
//		}
		List<CashierAccountInfo> infos = ClientTool
				.convertToCashierAccountInfo(response);
		return infos;
	}
}
