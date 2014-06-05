package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IIntegRuleManager;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 积分 积分兑换规则管理action<br />
 * </p>
 * @title RecordExchangeRuleManagerAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-21
 */

public class RecordExchangeRuleManagerAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 4833090808430231586L;
	private IIntegRuleManager integRuleManager;
	//返回前台查询结果
	private List<Gift> giftList = new ArrayList<Gift>();
	//积分兑换规则
	private Gift gift = new Gift();
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount=0L;
	private String [] exchangeRuleIds;
	
	public void setIntegRuleManager(IIntegRuleManager integRuleManager) {
		this.integRuleManager = integRuleManager;
	}
	/**
	 * 
	 * <p>
	 * 查询积分兑换数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-25
	 * @return String
	 */
	public String searchGiftList(){
		giftList = integRuleManager.getGifts(gift, start, limit);
		totalCount = (Long)integRuleManager.countGetGifts(gift);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 新增 积分兑换数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-25
	 * @return String
	 */
	public String saveRecordExchangeRule(){
		integRuleManager.insertGift(gift);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 修改 积分兑换数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-25
	 * @return String
	 */
	public String updateRecordExchangeRule(){
		integRuleManager.updateGift(gift);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 删除 积分兑换数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-25
	 * @return String
	 */
	public String deleteRecordExchangeRule(){
		for(String id:exchangeRuleIds){
			integRuleManager.deleteGift(id);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 启用兑换设置<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-25
	 * @return String
	 */
	public String startExchangeSet(){
		integRuleManager.updateGift(gift);
		return SUCCESS;
	}
	
	public List<Gift> getGiftList() {
		return giftList;
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
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	public Gift getGift() {
		return gift;
	}
	public void setExchangeRuleIds(String[] exchangeRuleIds) {
		this.exchangeRuleIds = exchangeRuleIds;
	}
	
}
