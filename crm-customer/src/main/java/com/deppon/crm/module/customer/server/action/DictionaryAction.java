/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DictionaryAction.java
 * @package com.deppon.crm.module.customer.server.action 
 * @author pgj
 * @version 0.1 2013-7-4
 */
package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.customer.server.manager.IDictionaryManager;
import com.deppon.crm.module.customer.shared.domain.TradeView;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**   
 * <p>
 * Description:数据字段维护Action<br />
 * </p>
 * @title DictionaryAction.java
 * @package com.deppon.crm.module.customer.server.action 
 * @author pgj
 * @version 0.1 2013-7-4
 */

public class DictionaryAction extends AbstractAction{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 9031352729150629814L;
	//查询条件
	private Detail condition = new Detail();;
	//查询结果
	private List<Detail> results = new ArrayList<Detail>();
	//新增的二级行业
	private List<Detail> addSecondTrade = new ArrayList<Detail>();
	//删除的二级行业
	private List<Detail> deleteSecondTrade = new ArrayList<Detail>();
	//修改的二级行业
	private List<Detail> updateSecondTrade = new ArrayList<Detail>();
	
	private List<TradeView> tradeView = new ArrayList<TradeView>();
	
	private IDictionaryManager dictionaryManager;
	
	private Logger logger = Logger.getLogger(DictionaryAction.class);
	/**
	 * <p>
	 * Description:根据条件查询数据字典<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-7-4
	 * void
	 */
	public String searchDictionary() {
		tradeView = dictionaryManager.searchDictionary(condition.getCodeDesc());
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:保存二级行业<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-7-4
	 * void
	 * @throws CrmBusinessException 
	 */
	public String saveSecondTrade() {
		logger.info(addSecondTrade.size());
		dictionaryManager.updateSecondTrade(addSecondTrade,updateSecondTrade, deleteSecondTrade);
		return SUCCESS;
	}

	
	/**
	 * <p>
	 * Description:根据父Code返回子级别数据字典(有效的)<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-7-4
	 * void
	 * @throws CrmBusinessException 
	 */
	public String searchSecondTradeByParentId(){
		logger.info("二级行业ID:"+condition.getParentId()+"----"+condition.getStatus());
		results = dictionaryManager.searchSecondTradeByParentId(condition.getParentId(),condition.getStatus()!=null?condition.getStatus():false);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:根据父Code返回子级别数据字典<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-7-4
	 * void
	 * @throws CrmBusinessException 
	 */
	public String searchMonthValidSecondTrade(){
		logger.info("二级行业ID:"+condition.getParentId());
		results = dictionaryManager.searchMonthValidSecondTrade(condition.getParentId());
		return SUCCESS;
	}
	
	
	/**
	 *@user pgj
	 *2013-7-4上午11:48:00
	 * @return results : return the property results.
	 */
	public List<Detail> getResults() {
		return results;
	}


	/**
	 * @param condition : set the property condition.
	 */
	public void setCondition(Detail condition) {
		this.condition = condition;
	}
	/**
	 * @param addSecondTrade : set the property addSecondTrade.
	 */
	public void setAddSecondTrade(List<Detail> addSecondTrade) {
		this.addSecondTrade = addSecondTrade;
	}
	/**
	 * @param deleteSecondTrade : set the property deleteSecondTrade.
	 */
	public void setDeleteSecondTrade(List<Detail> deleteSecondTrade) {
		this.deleteSecondTrade = deleteSecondTrade;
	}
	/**
	 * @param updateSecondTrade : set the property updateSecondTrade.
	 */
	public void setUpdateSecondTrade(List<Detail> updateSecondTrade) {
		this.updateSecondTrade = updateSecondTrade;
	}
	public void setDictionaryManager(IDictionaryManager dictionaryManager) {
		this.dictionaryManager = dictionaryManager;
	}
	public List<TradeView> getTradeView() {
		return tradeView;
	}
	public void setTradeView(List<TradeView> tradeView) {
		this.tradeView = tradeView;
	}
	
}
