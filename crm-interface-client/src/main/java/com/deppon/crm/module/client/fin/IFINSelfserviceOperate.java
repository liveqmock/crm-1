package com.deppon.crm.module.client.fin;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.fin.domain.BillInfo;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.fin.selfservice.GenerateClaimsapbillResponse;
import com.deppon.fin.selfservice.QueryCashieraccountRequest;
import java.util.List;
public interface IFINSelfserviceOperate {

	/**
	 * 
	 * <p>
	 * Description:生成报账报销单接口,在CRM中申请未开单理赔工作流，当工作流审批通过后，需要在报账中生成报销单<br />
	 * </p>
	 * 
	 * @interfaceCode FSSC_GENERATE_CLAIMSAPBILL
	 * @author wugenbin_吴根斌
	 * @version 0.1 2013-4-15
	 * @param generateClaimsapbillRequest
	 * @return GenerateClaimsapbillResponse
	 * @throws CrmBusinessException
	 */
	public GenerateClaimsapbillResponse generateClaimsapbill(
			BillInfo billInfo)
			throws CrmBusinessException;

	/**
	 * 
	 * <p>
	 * Description:查询收银员账号接口,CRM在申请理赔报销单时，当客户领款方式为“现金”或“冲账后付现”或“全额冲账”，
	 * 则自动从报账中获取理赔上报部门收银员在报账中的账号信息。<br />
	 * </p>
	 * 
	 * @author wugenbin_吴根斌
	 * @version 0.1 2013-4-15
	 * @param queryCashieraccountRequest
	 * @return QueryCashieraccountResponse
	 * @throws CrmBusinessException
	 */
	public List<CashierAccountInfo> queryCashieraccount(
			QueryCashieraccountRequest queryCashieraccountRequest)
			throws CrmBusinessException;
}
