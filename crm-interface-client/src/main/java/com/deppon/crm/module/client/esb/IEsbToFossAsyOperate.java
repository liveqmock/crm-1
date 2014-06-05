package com.deppon.crm.module.client.esb;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.esb.domain.ClaimsPayBillGenerateRequest;
import com.deppon.crm.module.client.esb.domain.MarketingActivityRequest;
import com.deppon.crm.module.client.esb.domain.OrigCustSyncRequest;

/**
 * @作者：罗典
 * @描述：与FOSS对接异步接口
 * @时间：2012-9-28
 * */
public interface IEsbToFossAsyOperate {
	/**
	 * @作者：罗典
	 * @描述：理赔付款单申请--异步接口
	 * @时间：2012-9-28
	 * @修改人:王明明
	 * @修改时间:2013-6-5
	 * @see com.deppon.crm.module.client.customer.CustomerOperateImpl claimsApbill
	 * 异步申请理赔付款单
	 * 此接口已由 CustomerOperateImpl下claimsApbill 同步调用方法替代
	 * */
	@Deprecated
	public void claimsApbill(ClaimsPayBillGenerateRequest request)throws CrmBusinessException;

	/**
	 * @作者：罗典 
	 * @描述：临欠散客同步接口
	 * @时间：2012-9-28
	 * */
	public void customerInfoEtc(OrigCustSyncRequest request)throws CrmBusinessException;

	/**
	 * @作者：吴根斌
	 * @时间：2014-3-18
	 * @描述：把推广活动同步给FOSS
	 * @参数：推广活动
	 * */
	public void synMarketingActivity(MarketingActivityRequest request)throws CrmBusinessException;
}
