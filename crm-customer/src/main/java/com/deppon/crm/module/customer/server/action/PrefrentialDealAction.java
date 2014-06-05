/**   
 * @title PrefrentialDealAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @description 月发月送新优惠方案Action  
 * @author 潘光均
 * @update 2013-3-11 上午9:51:21
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.server.manager.IPrefrentialDealManager;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * The Class PrefrentialDealAction.
 *
 * @description 月发月送新优惠方案Action
 * @author 潘光均
 * @version 0.1 2013-3-11
 * @date 2013-3-11
 */

public class PrefrentialDealAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 4724644309787279518L;
	// 查询结果
	/** The prefrential deals. */
	private List<PrefrentialDeal> prefrentialDeals = new ArrayList<PrefrentialDeal>();
	// 存放数据对象
	/** The prefrential deal. */
	private PrefrentialDeal prefrentialDeal;
	//manager
	/** The prefrential deal manager. */
	private IPrefrentialDealManager prefrentialDealManager;
	//totalCount
	private Long totalCount;
	//start
	private int start;
	//limit;
	private int limit;
	public Long getTotalCount() {
		return totalCount;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Gets the prefrential deal.
	 *
	 * @return the prefrential deal
	 */
	public PrefrentialDeal getPrefrentialDeal() {
		return prefrentialDeal;
	}

	/**
	 * Sets the prefrential deal manager.
	 *
	 * @param prefrentialDealManager the new prefrential deal manager
	 */
	public void setPrefrentialDealManager(
			IPrefrentialDealManager prefrentialDealManager) {
		this.prefrentialDealManager = prefrentialDealManager;
	}

	/**
	 * Sets the prefrential deal.
	 *
	 * @param prefrentialDeal the new prefrential deal
	 */
	public void setPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		this.prefrentialDeal = prefrentialDeal;
	}

	/**
	 * Gets the prefrential deals.
	 *
	 * @return the prefrential deals
	 */
	/**
	 */
	public List<PrefrentialDeal> getPrefrentialDeals() {
		return prefrentialDeals;
	}

/**
 * Save prefrential deal.
 *
 * @return String
 * @description 保存 新增/修改 的优惠方案.
 * @author 潘光均
 * @version 0.1 2013-3-11
 * @date 2013-3-11
 * @update 2013-3-11 下午2:40:22
 */
	public String savePrefrentialDeal() {
		if (null!=prefrentialDeal&&StringUtils.isEmpty(prefrentialDeal.getId())) {
			prefrentialDealManager.createPrefrentialDeal(prefrentialDeal);
		}else{
			prefrentialDealManager.updatePrefrentialDeal(prefrentialDeal);
		}
		return SUCCESS;
	}

	/**
	 * Delete prefrential deal.
	 *
	 * @return String
	 * @description 删除优惠方案
	 * @author 潘光均
	 * @version 0.1 2013-3-11
	 * @date 2013-3-11
	 * @update 2013-3-11 下午2:41:05
	 */
	public String deletePrefrentialDeal() {
		prefrentialDealManager.deletePrefrentialDeal(prefrentialDeal.getId());
		return SUCCESS;
	}

	/**
	 * Active prefrential deal.
	 *
	 * @return String
	 * @description 激活优惠方案
	 * @author 潘光均
	 * @version 0.1 2013-3-11
	 * @date 2013-3-11
	 * @update 2013-3-11 下午2:41:20
	 */
	public String activePrefrentialDeal() {
		prefrentialDealManager.activePrefrentialDeal(prefrentialDeal);
		return SUCCESS;
	}

	/**
	 * View prefrential deal.
	 *
	 * @return String
	 * @description 查看优惠方案详情
	 * @author 潘光均
	 * @version 0.1 2013-3-11
	 * @date 2013-3-11
	 * @update 2013-3-11 下午2:41:47
	 */
	public String viewPrefrentialDeal() {
		prefrentialDeal = prefrentialDealManager.searchPrefrentialDealById(prefrentialDeal.getId());
		return SUCCESS;
	}

/**
 * Search all prefrential deal.
 *
 * @return String
 * @description 查询所有优惠方案
 * @author 潘光均
 * @version 0.1 2013-3-11
 * @date 2013-3-11
 * @update 2013-3-11 下午2:42:38
 */
	public String searchAllPrefrentialDeal() {
		prefrentialDeals = prefrentialDealManager.searchAllPrefrentialDeal(prefrentialDeal,start,limit);
		totalCount = Long.valueOf(prefrentialDealManager.countPrefrentialDeal(prefrentialDeal));
		return SUCCESS;
	}
}
