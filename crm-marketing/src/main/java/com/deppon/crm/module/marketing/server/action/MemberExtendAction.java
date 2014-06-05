package com.deppon.crm.module.marketing.server.action;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class MemberExtendAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IMemberManager memberManager;
    private IAlterMemberManager alterMemberManager;
	//日程manager
	private IScheduleManager scheduleManager;
	/**
	 * @param scheduleManager : set the property scheduleManager
	 */
	public void setScheduleManager(IScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}
	//日程列表
	private List<ScheduleQueryResultObject> scheduleList;
	/**
	 * @return scheduleList : get the property scheduleList
	 */
	public List<ScheduleQueryResultObject> getScheduleList() {
		return scheduleList;
	}
	/**
	 * @param scheduleList : set the property scheduleList
	 */
	public void setScheduleList(List<ScheduleQueryResultObject> scheduleList) {
		this.scheduleList = scheduleList;
	}
	/**
	 * @param memberManager the memberManager to set
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * @param alterMemberManager the alterMemberManager to set
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	// 客户信息
	private Member member;
	// ------
	// 客户ID
	private String custId;
	
	//客户扩展信息
	private MemberExtend memberExtend;
	// ------
	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}


	/**
	 * @param memberExtend the memberExtend to set
	 */
	public void setMemberExtend(MemberExtend memberExtend) {
		this.memberExtend = memberExtend;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	// 查询条件Vo
	private CustomerVo customerVo;
	/**
	 * @return customerVo : get the property customerVo
	 */
	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	/**
	 * @param customerVo : set the property customerVo
	 */
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	
	// 获取当前用户
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
	/**
	 * .
	 * <p>
	 * 修改客户业务信息<br/>
	 * 方法名：updateMemberExtend
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2014-3-27
	 * @since JDK1.6
	 */
	@JSON
	public String updateMemberExtend() {
		MemberExtend mb = memberManager.searchMemberExtendByID(memberExtend.getCustId());
		if(mb == null){
			memberManager.updateExtendAndLog(null,memberExtend,Constant.OPERATOR_TYPE_INSERT);
			//memberManager.createMemberExtendInfo(memberExtend);
		}else{
			memberManager.updateExtendAndLog(null,memberExtend,Constant.OPERATOR_TYPE_UPDATE);
		}
		return SUCCESS;
	}
	/**
	 * .
	 * <p>
	 * 查询客户信息<br/>
	 * 方法名：searchMemberExtend
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2014-3-27
	 * @since JDK1.6
	 */
	@JSON
	public String searchMemberExtend() {
		member = alterMemberManager.getMemberById(custId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 查询客户
	 * </p>
	 * 
	 * @author 张斌
	 * @version 0.1 2014-3-28
	 * @revision 盛诗庆 查询条件增加业务类型
	 */
	@JSON
	public String searchCustomerList() {
		scheduleList = scheduleManager.searchCustomerListForMemberExtend(customerVo, start,
				limit,this.getCurrentUser());
		totalCount=Long.valueOf(scheduleManager.countSearchCustomerList(customerVo));
		return SUCCESS;
	}
}
