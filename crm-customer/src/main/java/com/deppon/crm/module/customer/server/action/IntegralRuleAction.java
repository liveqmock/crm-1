package com.deppon.crm.module.customer.server.action;

import java.util.List;

import com.deppon.crm.module.customer.server.manager.impl.IntegRuleManager;
import com.deppon.crm.module.customer.shared.domain.integral.ChannelIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.ProductIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WiringIntegRule;
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

public class IntegralRuleAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 8910880992379268238L;
	//积分规则管理manager
	private IntegRuleManager integRuleManager;
	//产品规则list
	private List<ProductIntegRule> productRuleList;
	//渠道规则list
	private List<ChannelIntegRule> channelRuleList;
	//路线规则list
	private List<WiringIntegRule> wayRuleList;
	//总条数
	private Long totalCount;
	//规则id
	private String ruleId;
	//产品规则 --前台传过来需要新增或修改的对象
	private ProductIntegRule productRule;
	//渠道规则 --前台传过来需要新增或修改的对象
	private ChannelIntegRule channelRule;
	//线路规则 --前台传过来需要新增或修改的对象
	private WiringIntegRule wiringRule;
	//条数限制
	private int limit;
	//开始条数
	private int start;

	public void setProductRule(ProductIntegRule productRule) {
		this.productRule = productRule;
	}

	public void setChannelRule(ChannelIntegRule channelRule) {
		this.channelRule = channelRule;
	}

	public void setWiringRule(WiringIntegRule wiringRule) {
		this.wiringRule = wiringRule;
	}

	public void setIntegRuleManager(IntegRuleManager integRuleManager) {
		this.integRuleManager = integRuleManager;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<ProductIntegRule> getProductRuleList() {
		return productRuleList;
	}

	public List<ChannelIntegRule> getChannelRuleList() {
		return channelRuleList;
	}

	public List<WiringIntegRule> getWayRuleList() {
		return wayRuleList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleId() {
		return ruleId;
	}
	/**
	 * 
	 * @description 查询产品规则  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 *@date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:34:02
	 */
	public String searchProductRuleList() {
		totalCount = integRuleManager.countGetProductIntegRules();
		productRuleList = integRuleManager.getProductIntegRules(start, limit);
		return SUCCESS;
	}
	/**
	 * 
	 * @description 渠道规则.  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 *@date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:34:21
	 */
	public String searchChannelRuleList() {
		totalCount = integRuleManager.countGetChannelIntegRules();
		channelRuleList = integRuleManager.getChannelIntegRules(start, limit);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @description 路线规则查询  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 *@date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:34:31
	 */
	public String searchWayRuleList() {
		totalCount = integRuleManager.countGetWiringIntegRules();
		wayRuleList = integRuleManager.getWiringIntegRules(start, limit);

		return SUCCESS;
	}
	/**
	 * 
	 * @description 删除规则  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 *@date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:34:49
	 */
	public String deleteRecordRuleById() {
		integRuleManager.deleteWiringIntegRule(ruleId);
		return SUCCESS;
	}
	/**
	 * 
	 * @description 根据规则编号查询规则.  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 *@date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:35:03
	 */
	public String searchRecordRuleByNumber() {
		ruleId = integRuleManager.searchIntegRule(ruleId);
		return SUCCESS;
	}
	/**
	 * 
	 * @description 创建规则.  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 *@date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:35:22
	 */
	public String createRecordRule() {
		if (productRule != null) {
			integRuleManager.saveProductIntegRule(productRule);
		} else if (channelRule != null) {
			integRuleManager.saveChannelIntegRule(channelRule);
		} else if (wiringRule != null) {
			integRuleManager.saveWiringIntegRule(wiringRule);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @description 修改规则  
	 * @author 潘光均
	 * @version 0.1 2012-5-4
	 * @param 
	 *@date 2012-5-4
	 * @return String
	 * @update 2012-5-4 上午9:35:44
	 */
	public String modifyRecordRule() {
		if (productRule != null) {
			integRuleManager.updateProductIntegRule(productRule);
		} else if (channelRule != null) {
			integRuleManager.updateChannelIntegRule(channelRule);
		} else if (wiringRule != null) {
			integRuleManager.updateWiringIntegRule(wiringRule);
		}
		return SUCCESS;
	}
}
