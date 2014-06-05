package com.deppon.crm.module.customer.server.action;

import java.util.List;

import com.deppon.crm.module.customer.server.manager.IIntegRuleManager;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * <p>
 * 积分规则管理action<br />
 * </p>
 * 
 * @title IntegralRuleAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 潘光均
 * @version 0.1 2012-3-29
 */

public class GiftManageAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 6005598635536945263L;
	// 积分查询manger
	private IIntegralManager integralManager;
	//规则管理manager
	private IIntegRuleManager integRuleManager;
	//客户编号
	private String custNo;
	//联系人编号
	private String contactNo;
	//发放状态
	private String sendStatus;
	//礼品名称
	private String giftName;
	//礼品发放查询list
	private List<IntegralConvertGift> custGiftList;
	//选择礼品的id
	private List<String> giftIds;
	//选择礼品的集合
	private List<Gift> giftChooseList;
	// 前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	// 前台传入的条件查询潜客信息的limit参数（用于分页）
	private Long totalCount = 0L;
	private int limit;

	//显示礼品申请的状态
	private List<String> checkGiftList;
	
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public void setIntegralManager(IIntegralManager integralManager) {
		this.integralManager = integralManager;
	}

	public void setIntegRuleManager(IIntegRuleManager integRuleManager) {
		this.integRuleManager = integRuleManager;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public void setGiftIds(List<String> giftIds) {
		this.giftIds = giftIds;
	}

	public void setCustGiftList(List<IntegralConvertGift> custGiftList) {
		this.custGiftList = custGiftList;
	}

	public List<IntegralConvertGift> getCustGiftList() {
		return custGiftList;
	}

	public List<Gift> getGiftChooseList() {
		return giftChooseList;
	}

	public List<String> getCheckGiftList() {
		return checkGiftList;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @description 查询客户礼品 --页面库存管理.
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:04:56
	 */
	public String searchCustGifts() {
		IntegralConvertGiftCondition condition = new IntegralConvertGiftCondition();
		condition.setContactNum(contactNo);
		condition.setMemberNum(custNo);
		condition.setSendStatus(sendStatus);
		condition.setDeptIds(ContextUtil.getCurrentUserDataAuthorityDept());
		totalCount = (Long)integralManager.countSearchIntegralConvertGift(condition);
		custGiftList=integralManager.searchIntegralConvertGift(condition, start, limit);
		return SUCCESS;
	}

	/**
	 * @description 礼品发放 --页面确认发放按钮事件.
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:06:27
	 */
	public String confirmSendGift() {
		integralManager.updateIntegralConvertGiftSend(giftIds);
		return SUCCESS;
	}

	/**
	 * @description 库存恢复 --页面恢复库存按钮事件.  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:07:04
	 */
	public String revertStoreGift() {
		integralManager.updateIntegralConvertGiftRecover(giftIds);
		return SUCCESS;
	}

	/**
	 * @description 查询礼品 --礼品搜索.  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:07:35
	 */
	public String searchGiftChooseList() {
		giftChooseList = integRuleManager.getCanUseGiftsByGiftName(giftName, start, limit);
		totalCount = (Long) integRuleManager.countCanUseGistsByGiftName(giftName);
		return SUCCESS;
	}
	/**
	 * 
	 * @description 暂存礼品清单 --暂存清单按钮.  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:07:59
	 */
	public String temporarySaveGiftBill() {
		integralManager.saveIntegralConvertGift(custGiftList);
		return SUCCESS;
	}

	/**
	 * @description 检查礼品清单是否可以兑换.
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午8:50:18
	 */
	public String checkGiftBillAviable() {
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:保存礼品清单<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-3
	 * @return String
	 */
	public String saveGiftBill() {
		checkGiftList=integralManager.insertIntegralConvertGift(custGiftList);
		return SUCCESS;
	}
	/**
	 * @description 查询暂存礼品清单.  
	 * @author 潘光均
	 * @version 0.1 2012-5-29
	 * @param 
	 *@date 2012-5-29
	 * @return String
	 * @update 2012-5-29 下午3:42:56
	 */
	public String searchTemporaryGiftBill(){
		custGiftList = integralManager.searchTemporaryGiftBill();
		return SUCCESS;
	}
}
