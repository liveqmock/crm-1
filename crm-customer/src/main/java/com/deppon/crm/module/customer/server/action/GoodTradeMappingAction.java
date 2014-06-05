package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.deppon.crm.module.customer.server.manager.impl.GoodTradeMappingManager;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * 
 * <p>
 * Description:行业与品名映射维护<br />
 * </p>
 * @title GoodTradeMappingAction.java
 * @package com.deppon.crm.module.customer.server.action 
 * @author 106138
 * @version 0.1 2014-3-18
 */
public class GoodTradeMappingAction  extends  AbstractAction{	
	
	/**  
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -8930799273830547269L;
	private List<GoodTradeMapping> list=new ArrayList<GoodTradeMapping>();
	//查询
	private GoodTradeMappingSearchCondition condition;
	private GoodTradeMappingManager goodTradeMappingManager;
	//映射所传参数
	private  GoodTradeMapping goodTradeMapping;
	//总条数
	private long totalCount;
	// 开始条数
	private int start;
	//每月
	private int limit;
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-19
	 * @return
	 * String
	 */
	@JSON
	public String searchGoodTradeMapping(){
		Map<String,Object> map=goodTradeMappingManager.searchGoodTradeMappingByCondition(condition);
		list=(List<GoodTradeMapping>) map.get("list");
		totalCount= Long.parseLong(map.get("total")+""); 
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-19
	 * @return
	 * String
	 */
	@JSON
	public String updateGoodTradeMapping(){
		goodTradeMappingManager.updateGoodTradeMapping(goodTradeMapping);
		return SUCCESS;
	}
	
	
	
	public void setGoodTradeMappingManager(GoodTradeMappingManager goodTradeMappingManager) {
		this.goodTradeMappingManager = goodTradeMappingManager;
	}
	public GoodTradeMappingSearchCondition getCondition() {
		return condition;
	}
	public void setCondition(GoodTradeMappingSearchCondition condition) {
		this.condition = condition;
	}
	public GoodTradeMapping getGoodTradeMapping() {
		return goodTradeMapping;
	}
	public void setGoodTradeMapping(GoodTradeMapping goodTradeMapping) {
		this.goodTradeMapping = goodTradeMapping;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}



	public List<GoodTradeMapping> getList() {
		return list;
	}



	public void setList(List<GoodTradeMapping> list) {
		this.list = list;
	}
}
