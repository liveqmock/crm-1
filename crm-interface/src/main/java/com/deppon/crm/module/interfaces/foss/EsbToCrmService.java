package com.deppon.crm.module.interfaces.foss;

import com.deppon.crm.module.client.sync.domain.OrderRightRequest;
import com.deppon.crm.module.client.sync.domain.SiteReceiveRequest;
import com.deppon.crm.module.interfaces.foss.domain.CommException;
import com.deppon.crm.module.interfaces.foss.domain.ReceiveCreditUsedRequest;
import com.deppon.crm.module.interfaces.foss.domain.ReceiveCreditUsedResponse;
import com.deppon.crm.module.interfaces.foss.domain.ReturnVoucherPaymentResultRequest;
import com.deppon.crm.module.interfaces.foss.domain.UpdateOrderRequest;
import com.deppon.crm.module.interfaces.foss.domain.UpdateOrderResponse;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.SyncMotorcadeRequest;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.SyncMotorcadeResponse;
import com.deppon.crm.module.interfaces.foss.domain.scatter.CreateScatterRequest;
import com.deppon.crm.module.interfaces.foss.domain.scatter.CreateScatterResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncDistrictRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncDistrictResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncOrganizationRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncOrganizationResponse;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncSalesDepartmentRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncSalesDepartmentResponse;
import com.deppon.foss.express.SyncExpressCityRequest;
import com.deppon.foss.express.SyncExpressCityResponse;
import com.deppon.foss.express.SyncExpressDeptRelationRequest;
import com.deppon.foss.express.SyncExpressDeptRelationResponse;

/**  
 * @作者：罗典
 * @描述：与FOSS对接的ESB异步接口
 * @时间：2012-11-8
 * */
public interface EsbToCrmService {

	/**
	 *
	 * <p>
	 * Description:FOSS创建散客<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-14
	 * @param createScatterrequest
	 * @return
	 * CreateScatterResponse
	 */
	public CreateScatterResponse createScatter(CreateScatterRequest createScatterrequest)throws CommException;
	/**
	 * @作者：罗典
	 * @描述：通知理赔付款状态
	 * @时间：2012-11-8
	 * */
	public void claimsState(ReturnVoucherPaymentResultRequest claimsStateRequest)
			throws CommException;

	/**
	 * @作者：罗典
	 * @描述：修改订单状态
	 * @时间：2012-11-8
	 * */
	public UpdateOrderResponse updateOrderStatus(
			UpdateOrderRequest updateOrderRequest) throws CommException;

	/**
	 * @作者：罗典
	 * @描述：同步网点信息
	 * @时间：2012-11-8
	 * *//*
	public void receiveSiteSync(SiteReceiveRequest request)
			throws CommException;*/
	/**
	 * @作者：罗典
	 * @描述：同步FOSS营业组织信息
	 * @时间：2013-1-31
	 * */
	public SyncOrganizationResponse syncOrganizea(SyncOrganizationRequest request) throws CommException;

	/**
	 * @作者：罗典
	 * @描述：同步FOSS是否出发到达营业部信息
	 * @时间：2013-1-31
	 * */
	public SyncSalesDepartmentResponse syncStaArrDept(SyncSalesDepartmentRequest request) throws CommException;
	/**
	 *
	 * @description 同步FOSS试点城市和落地配城市
	 * @author wugenbin_吴根斌
	 * @version 2013-7-23
	 * @param
	 * @return
	 */
	public SyncExpressCityResponse syncExpressCity(SyncExpressCityRequest request)throws CommException;

		/**
	 * @作者：王明明
	 * @描述：同步FOSS是否顶级车队--车队信息
	 * @时间：2013-7-29
	 * */
	public SyncMotorcadeResponse syncMotorcadeInfo(SyncMotorcadeRequest request) throws CommException;

	/**
	 * @作者：吴根斌
	 * @描述：同步营业部-快递点部映射关系
	 * @时间：2013-8-8
	 * */
	public SyncExpressDeptRelationResponse sysExpressDeptRelation(SyncExpressDeptRelationRequest request)throws CommException;
	/**
	 * @作者：罗典
	 * @描述：同步FOSS行政区域信息
	 * @时间：2013-1-31
	 * */
	public SyncDistrictResponse syncAdminArea(SyncDistrictRequest request) throws CommException;
	/**
	 * @作者：罗典
	 * @描述：同步组织权限
	 * @时间：2012-11-8
	 * */
	public void orderRightSync(OrderRightRequest request) throws CommException;

	/**
	 * @作者：罗典
	 * @描述：同步合同月结最长账龄日期
	 * @时间：2012-01-11
	 * */
	public ReceiveCreditUsedResponse receiveCreditUsed(
			ReceiveCreditUsedRequest request) throws CommException;
}
