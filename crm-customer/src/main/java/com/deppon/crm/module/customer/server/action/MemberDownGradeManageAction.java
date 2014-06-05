package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
/**
 * <p>
 * 会员降级管理action<br />
 * </p>
 * @title MemberDownGradeManageAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-3-29
 */

public class MemberDownGradeManageAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 6793167909493663364L;
	private IMemberManager memberManager;//会员manage
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
    //会员降级信息列表
	private List<MemberDemotionList> memberDemotionList = new ArrayList<MemberDemotionList>();
	//会员降级查询条件
	private UpGradeCustomerCondition memberDownGradeCondition = new UpGradeCustomerCondition();
	/**
	 * 
	 * <p>
	 * Description:查询降级会员信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-5
	 * @return
	 * String
	 */
	public String searchDownMemberList(){
		if(!"".equals(memberDownGradeCondition.getStatisticsTime())){
			memberDownGradeCondition.setStatisticsTime(memberDownGradeCondition.getStatisticsTime().substring(0, 4));
		}
		memberDemotionList = memberManager.searchMemberDemotionList(memberDownGradeCondition, start, limit);
		totalCount = Long.valueOf(memberManager.getCountMemberDemotionListByCondition(memberDownGradeCondition));
		return SUCCESS;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public List<MemberDemotionList> getMemberDemotionList() {
		return memberDemotionList;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public UpGradeCustomerCondition getMemberDownGradeCondition() {
		return memberDownGradeCondition;
	}
	public void setMemberDownGradeCondition(
			UpGradeCustomerCondition memberDownGradeCondition) {
		this.memberDownGradeCondition = memberDownGradeCondition;
	}
}
