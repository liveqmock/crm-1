package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IWaybillIntegralManager;
import com.deppon.crm.module.customer.server.service.IContactIntegralService;
import com.deppon.crm.module.customer.server.service.IMemberIntegralService;
import com.deppon.crm.module.customer.server.service.IWaybillIntegralService;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;

public class WaybillIntegralManager implements IWaybillIntegralManager{
	
	private IWaybillIntegralService waybillIntegralService;
	private IMemberIntegralService memberIntegralService;
	private IContactIntegralService contactIntegralService;
	
	public void setMemberIntegralService(
			IMemberIntegralService memberIntegralService) {
		this.memberIntegralService = memberIntegralService;
	}

	public void setContactIntegralService(
			IContactIntegralService contactIntegralService) {
		this.contactIntegralService = contactIntegralService;
	}

	public void setWaybillIntegralService(
			IWaybillIntegralService waybillIntegralService) {
		this.waybillIntegralService = waybillIntegralService;
	}
	/**
	 * 
	 * <p>
	 * Description:获取需要失效的运单积分<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @return
	 * List<WaybillIntegral>
	 */
	@Override
	public List<WaybillIntegral> getNeedDisPoseWaybills() {
		return waybillIntegralService.getNeedDisPoseWaybills();
	}
	/**
	 * 
	 * <p>
	 * Description:批量修改作废运单积分<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @param waybillIntegralList
	 * void
	 */
	@Override
	//TODO 性能可优化
	public void disPoseWaybillIntegral(List<WaybillIntegral> waybillIntegralList) {
		for (WaybillIntegral waybillIntegral : waybillIntegralList) {
			String memberId = waybillIntegral.getContact().getMember().getId();
			MemberIntegral memberIntegral = memberIntegralService.getMemberIntegralByMemberId(memberId);
			String contactId = waybillIntegral.getContact().getId();
			ContactIntegral contactIntegral = contactIntegralService.getContactIntegralByContactId(contactId);
			
			//业务规则
			memberIntegral.setCurrentUsableScore(memberIntegral.getCurrentToUsesScore()+waybillIntegral.getScore());
			memberIntegral.setCurrentTotalScore(memberIntegral.getCurrentTotalScore()+waybillIntegral.getScore());
			memberIntegral.setTotalScore(memberIntegral.getTotalScore()+waybillIntegral.getScore());
			memberIntegral.setLastDateScore(new Date());
			
			contactIntegral.setCurrentUsableScore(contactIntegral.getCurrentToUsesScore()+waybillIntegral.getScore());
			contactIntegral.setCurrentTotalScore(contactIntegral.getCurrentTotalScore()+waybillIntegral.getScore());
			contactIntegral.setTotalScore(contactIntegral.getTotalScore()+waybillIntegral.getScore());
			memberIntegral.setLastDateScore(new Date());
			
			memberIntegralService.updateMemberIntegral(memberIntegral);
			contactIntegralService.updateContactIntegral(contactIntegral);
		}
	}
	
	
	
}
