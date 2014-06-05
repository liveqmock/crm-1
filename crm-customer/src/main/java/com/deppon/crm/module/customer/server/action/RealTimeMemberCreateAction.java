package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.ImplementMemberView;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 创建实时会员管理action<br />
 * </p>
 * @title RealTimeMemberCreateAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-3-29
 */

public class RealTimeMemberCreateAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -6488512843225107198L;
	private IMemberManager memberManager;//会员manage
	//会员信息弹出框查询条件
	private MemberCondition searchCustCondition = new MemberCondition();
	//实时创建会员 信息
	private ImplementMemberView implementMemberView = new ImplementMemberView();
    //会员信息列表
	private List<MemberResult> memberResultList = new ArrayList<MemberResult>();
	//校验会员是否存在
	private boolean ischecked;
	private String linkWay;
	private String linkWayNum;
	private String contactName;
	/**
	 * 
	 * <p>
	 * 检查会员是否存在<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-3-31
	 * void
	 * 
	 * 二期改造：校验是否存在有效的散客，如果存在才允许创建固定客户，其他一律不允许创建
	 */
	public String checkIsExistMember(){
		if("mobile".equals(linkWay)){
			ischecked = memberManager.canRealTimeCreateMember(linkWayNum,null,contactName);
			return SUCCESS;
		}
		if("tel".equals(linkWay)){
			ischecked = memberManager.canRealTimeCreateMember(null,linkWayNum,contactName);
			return SUCCESS;
		}
		return ERROR;
	}
	/**
	 * 
	 * <p>
	 * 实时创建会员检查是否通过校验<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-3-31
	 * void
	 */
	public String acquireImplementMemberView(){
		if("mobile".equals(linkWay)){
			try {
				implementMemberView = memberManager.initImplementMember(linkWayNum,null,contactName);
			} catch (CrmBusinessException e) {
				return ERROR;
			}
			return SUCCESS;
		}
		if("tel".equals(linkWay)){
			try {
				implementMemberView = memberManager.initImplementMember(null,linkWayNum,contactName);
			} catch (CrmBusinessException e) {
				return ERROR;
			}
			return SUCCESS;
		}
		return ERROR;
	}
	public MemberCondition getSearchCustCondition() {
		return searchCustCondition;
	}
	public void setSearchCustCondition(MemberCondition searchCustCondition) {
		this.searchCustCondition = searchCustCondition;
	}
	public ImplementMemberView getImplementMemberView() {
		return implementMemberView;
	}
	public List<MemberResult> getMemberResultList() {
		return memberResultList;
	}
	public boolean isIschecked() {
		return ischecked;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}
	public void setLinkWayNum(String linkWayNum) {
		this.linkWayNum = linkWayNum;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
}
