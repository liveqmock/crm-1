package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 积分 手动积分奖励action<br />
 * </p>
 * @title ManualRewardIntegralManagerAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-21
 */

public class ManualRewardIntegralManagerAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -3618461275681888928L;
	private IIntegralManager integralManager;//积分manager
	private IContactManager contactManager;//联系人manager
	//手动奖励积分 查询结果说
	private List<HandRewardIntegral> handRewardIntegralList = new ArrayList<HandRewardIntegral>();
	//查询条件
	private ContactCondition searchCondition;
	//奖励规则
	private RewardIntegRule rewardIntegral;
	//奖励 基数
	private String integralBasicNumber;
	//待奖励 联系人 id数组
	private List<String> contactIds;
    //会员联系人信息列表 
	private List<Contact> contactList = new ArrayList<Contact>();
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
	/**
	 * 
	 * <p>
	 * 手动奖励积分 查询结果<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String searchHandRewardIntegralList(){
		handRewardIntegralList = integralManager.searchHandRewardIntegrals(null,start,limit);
		totalCount = integralManager.countSearchHandRewardIntegrals(null);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 奖励对象查询<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String searchRewardContact(){
		contactList = contactManager.searchContactByCondition(searchCondition);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 添加奖励<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-28
	 * @return String
	 */
	public String createHandRewardIntegrals(){
		try{
			if(this.integralBasicNumber != null && !"".equals(this.integralBasicNumber)){
				int integralBasicNumber =  Integer.parseInt(this.integralBasicNumber);
				integralManager.createHandRewardIntegrals(integralBasicNumber, rewardIntegral, contactIds);
				return SUCCESS;
			}
			return ERROR;
		}catch (NumberFormatException e) {

			return ERROR;
		}
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
	public List<HandRewardIntegral> getHandRewardIntegralList() {
		return handRewardIntegralList;
	}
	public void setIntegralManager(IIntegralManager integralManager) {
		this.integralManager = integralManager;
	}
	public void setRewardIntegral(RewardIntegRule rewardIntegral) {
		this.rewardIntegral = rewardIntegral;
	}
	public void setContactIds(List<String> contactIds) {
		this.contactIds = contactIds;
	}
	public void setIntegralBasicNumber(String integralBasicNumber) {
		this.integralBasicNumber = integralBasicNumber;
	}
	public ContactCondition getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(ContactCondition searchCondition) {
		this.searchCondition = searchCondition;
	}
	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}
	public List<Contact> getContactList() {
		return contactList;
	}
}
