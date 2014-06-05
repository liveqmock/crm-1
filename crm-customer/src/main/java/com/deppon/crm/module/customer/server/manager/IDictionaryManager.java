package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.customer.shared.domain.TradeView;
/**
 * 
 * <p>
 * Description:二级行业manager<br />
 * </p>
 * @title IDictionaryManager.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface IDictionaryManager {

	/**
	 * <p>
	 * Description:保存/修改/删除二级行业信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-17
	 * @return
	 * String
	 */
	public void updateSecondTrade(List<Detail> saveDetailLists,List<Detail> modifyDetailLists,List<Detail> delDetailLists);
	
	/***
	 * 
	 * <p>
	 * Description:根据名称查询二级行业信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @return
	 * List<Detail>
	 */
	public List<TradeView> searchDictionary(String secondTradeName);

	/**
	 * 
	 * <p>
	 * Description:根据一级行业ID查询有效的二级行业信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-17
	 * @return
	 * String
	 */
	public List<Detail> searchSecondTradeByParentId(String parentId,boolean status);


	/**
	 * <p>
	 * Description:根据parentId查询未超过失效时间的二级行业<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-19
	 * @param parentId
	 * @return
	 * List<Detail>
	 */
	public List<Detail> searchMonthValidSecondTrade(String parentId);
	
	
	
}
