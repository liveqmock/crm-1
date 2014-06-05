package com.deppon.crm.module.client.workflow.impl;

import java.rmi.RemoteException;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.common.util.XmlMapperUtil;
import com.deppon.crm.module.client.workflow.IGiftApplyOperate;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo;
import com.deppon.crm.module.client.workflow.domain.LMSServiceLocator;
import com.deppon.crm.module.client.workflow.domain.OtherBill;
import com.deppon.lms.workflow.WSInvokeException;

public class GiftApplyOperateImpl implements IGiftApplyOperate {
	// 地址服务类
	private LMSServiceLocator lmsService;

	/**
	 * @作者：罗典
	 * @时间：2012-9-13
	 * @描述：积分兑换申请
	 * */
	@Override
	public String giftApply(GiftApplyInfo giftApplyInfo)
			throws CrmBusinessException {
		String[] result = null;
		try {
			OtherBill bill = ClientTool.changeGigtToBill(giftApplyInfo);
			String xmlStr = XmlMapperUtil.writeValue(bill);
			result = lmsService.getWSLMSGiftApplyFacade().applyGift(xmlStr);
		} catch (WSInvokeException e) {
			throw new CrmBusinessException("1005", e);
		} catch (RemoteException e) {
			throw new CrmBusinessException("1005", e);
		} catch (Exception e) {
			throw new CrmBusinessException("1005", e);
		}
		if (result!=null && result.length > 2 && result[0].equals("T")) {
			return result[1];
		} else {
			throw new CrmBusinessException("1005", result[2]);
		}
	}

	public LMSServiceLocator getLmsService() {
		return lmsService;
	}

	public void setLmsService(LMSServiceLocator lmsService) {
		this.lmsService = lmsService;
	}


}
