package com.deppon.crm.module.client.workflow;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo;

/**
 * 礼品工作流审批接口
 * @author davidcun @2012-3-23
 */
public interface IGiftApplyOperate {

	/**
	 * <p>需求：LMS-1</p>
	 * 礼品申请
	 * @author davidcun 2012-3-23
	 * @param   giftApplyInfo {@link GiftApplyInfo} 礼品申请信息    
	 * @return   工作流编号   
	 * @Throws 
	 *
	 */
	public String giftApply(GiftApplyInfo giftApplyInfo) throws CrmBusinessException;
}
