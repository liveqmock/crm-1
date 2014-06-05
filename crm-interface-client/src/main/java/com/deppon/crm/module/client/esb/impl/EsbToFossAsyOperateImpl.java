package com.deppon.crm.module.client.esb.impl;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.common.util.XmlJaxbMapper;
import com.deppon.crm.module.client.esb.IEsbToFossAsyOperate;
import com.deppon.crm.module.client.esb.domain.ClaimsPayBillGenerateRequest;
import com.deppon.crm.module.client.esb.domain.ESBClientHeader;
import com.deppon.crm.module.client.esb.domain.MarketingActivityRequest;
import com.deppon.crm.module.client.esb.domain.OrigCustSyncRequest;
import com.deppon.crm.module.client.esb.trans.CrmToEsbRequestSender;
import com.deppon.crm.module.client.esb.trans.EsbUtil;

/**
 * @作者：罗典
 * @描述：与FOSS交互的异步接口
 * @时间：2012-9-28
 * */
public class EsbToFossAsyOperateImpl implements IEsbToFossAsyOperate {
	private CrmToEsbRequestSender sender;

	/**
	 * @作者：罗典
	 * @描述：FOSS应付单申请接口
	 * @时间：2012-9-28
	 * */
	@Override
	@Deprecated
	public void claimsApbill(ClaimsPayBillGenerateRequest request)
			throws CrmBusinessException {
		NullOrEmptyValidator.checkNull(request,"OrigCustSyncRequest");
		NullOrEmptyValidator.checkNull(request.getBillNo(),"request.getBillNo()");
		String xmlStr = XmlJaxbMapper.writeValue(request,Constant._ClaimsPayBill_QNAME);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.CLAIMSAPBILL,
				request.getBillNo());
		sender.send(xmlStr, esbHeader);
	}

	/**
	 * @作者：吴根斌
	 * @时间：2014-3-18
	 * @描述：把推广活动同步给FOSS
	 * @参数：推广活动
	 * */
	public void synMarketingActivity(MarketingActivityRequest request)throws CrmBusinessException{
		NullOrEmptyValidator.checkNull(request, "MarketingActivityRequest");
		String xmlStr = XmlJaxbMapper.writeValue(request,Constant._MarketingActivityRequest_QNAME);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.MARKETING_ACTIVITY,
				request.getActivityCode());
		esbHeader.setExchangePattern(2);
		sender.send(xmlStr, esbHeader);
	}

	/**
	 * @作者：罗典
	 * @描述：临欠散客同步接口
	 * @时间：2012-9-28
	 * */
	@Override
	public void customerInfoEtc(OrigCustSyncRequest request)
			throws CrmBusinessException {
		NullOrEmptyValidator.checkNull(request, "OrigCustSyncRequest");
		NullOrEmptyValidator.checkNull(request.getScatterCustInfo(), "request.getScatterCustInfo()");
		NullOrEmptyValidator.checkNull(request.getScatterCustInfo().getFscatterid(),
				"request.getScatterCustInfo().getFscatterid()");
		String xmlStr = XmlJaxbMapper.writeValue(request,Constant._OrigCustSyncRequest_QNAME);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.NONFIXEDCUSTOMER,
				request.getScatterCustInfo().getFscatterid());
		esbHeader.setExchangePattern(2);
		sender.send(xmlStr, esbHeader);
	}

	public CrmToEsbRequestSender getSender() {
		return sender;
	}

	public void setSender(CrmToEsbRequestSender sender) {
		this.sender = sender;
	}


}
