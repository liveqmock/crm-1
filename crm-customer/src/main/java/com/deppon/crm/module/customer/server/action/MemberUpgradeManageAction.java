package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 会员升级管理action<br />
 * </p>
 * @title MemberUpgradeManageAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-3-23
 */

public class MemberUpgradeManageAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -3335322982986176695L;
	private IMemberManager memberManager;//会员manage
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
    //会员信息列表
	private List<MemberUpgradeList> memberUpgradeList = new ArrayList<MemberUpgradeList>();
	//会员升级查询条件
	private UpGradeCustomerCondition memberUpGradeCondition = new UpGradeCustomerCondition();
	/**
	 * 
	 * <p>
	 * Description:查询升级会员信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-5
	 * @return
	 * String
	 */
	@SuppressWarnings("static-access")
	public String searchUpMemberList(){
		if(!new ValidateUtil().objectIsEmpty(memberUpGradeCondition.getStatisticsTime())){
			memberUpGradeCondition.setStatisticsTime(memberUpGradeCondition.getStatisticsTime().substring(0, 7));
		}
		memberUpgradeList = memberManager.searchMemberUpgradeList(memberUpGradeCondition, start, limit);
		totalCount = Long.valueOf(memberManager.getCountMemberUpgradeListByCondition(memberUpGradeCondition));
		return SUCCESS;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public List<MemberUpgradeList> getMemberUpgradeList() {
		return memberUpgradeList;
	}
	public UpGradeCustomerCondition getMemberUpGradeCondition() {
		return memberUpGradeCondition;
	}
	public void setMemberUpGradeCondition(
			UpGradeCustomerCondition memberUpGradeCondition) {
		this.memberUpGradeCondition = memberUpGradeCondition;
	}
}
