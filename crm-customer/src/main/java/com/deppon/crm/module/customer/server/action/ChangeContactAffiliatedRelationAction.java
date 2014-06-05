package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 积分 变更联系人挂靠关系action<br />
 * </p>
 * @title ChangeContactAffiliatedRelationAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-21
 */

public class ChangeContactAffiliatedRelationAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 6863602850936549250L;
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private IIntegralManager integralManager;//积分manager
	//客户编码
	private String custNum;
	//联系人编码
	private String contactNum;
	//会员积分
	private MemberIntegral memberIntegral;
	//工作流号
	private String workFlowNum;
	//新增附件信息
	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
	private ContactVary contactVary;
	/**
	 * 
	 * <p>
	 * 查询客户信息数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-26
	 * @return String
	 */
	public String searchMember(){
		String number ="";
		if(custNum != null && !"".equals(custNum)){
			number = custNum;
			memberIntegral = integralManager.getMemberIntegralByMemberNum(number);
		}else if(contactNum != null && !"".equals(contactNum)){
			number = contactNum;
			memberIntegral = integralManager.getMemberIntegralByContactNum(number);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 查询客户信息数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-26
	 * @return String
	 */
	public String mergeContact(){
		workFlowNum = String.valueOf(integralManager.contactVary(contactVary,fileInfoList));
		return SUCCESS;
	}
	
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getWorkFlowNum() {
		return workFlowNum;
	}
	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
	public MemberIntegral getMemberIntegral() {
		return memberIntegral;
	}
	public ContactVary getContactVary() {
		return contactVary;
	}
	public void setContactVary(ContactVary contactVary) {
		this.contactVary = contactVary;
	}
	public void setIntegralManager(IIntegralManager integralManager) {
		this.integralManager = integralManager;
	}
}
