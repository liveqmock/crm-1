package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.customer.server.dao.IContractDao;
import com.deppon.crm.module.customer.server.dao.IPrefrentialDealDao;
import com.deppon.crm.module.customer.server.service.IPrefrentialDealService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;
/**
 * 
 * <p>
 * 月发月送新优惠方案<br />
 * </p>
 * @title IPrefrentialDealService.java
 * @package com.deppon.crm.module.customer.server.service
 * @author 唐亮
 * @version 0.1 2013-3-9
 */
public class PrefrentialDealService implements IPrefrentialDealService {
	
	private IPrefrentialDealDao prefrentialDealDao;
	private IContractDao contractDao;
	
	
	public void setPrefrentialDealDao(IPrefrentialDealDao prefrentialDealDao) {
		this.prefrentialDealDao = prefrentialDealDao;
	}
	
	public void setContractDao(IContractDao contractDao) {
		this.contractDao = contractDao;
	}

	/**
	 * 
	 * @Title: createPrefrentialDeal
	 *  <p>
	 * @Description: 创建折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void createPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		prefrentialDealDao.savePrefrentialDeal(prefrentialDeal);
		List<PrefrentialDealItem> itemList = prefrentialDeal.getDealItems();
		for (PrefrentialDealItem dealItem : itemList) {
			dealItem.setDealId(prefrentialDeal.getId());
			dealItem.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			prefrentialDealDao.addPrefrentialDealItem(dealItem);
		}
	}

	/**
	 * 
	 * @Title: updatePrefrentialDeal
	 *  <p>
	 * @Description: 修改折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void updatePrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		prefrentialDealDao.modifyPrefrentialDeal(prefrentialDeal);
		List<PrefrentialDealItem> itemList = prefrentialDeal.getDealItems();
		for(PrefrentialDealItem dealItem : itemList){
			dealItem.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			prefrentialDealDao.modifyPrefrentialDealItem(dealItem);
		}
	}
	/**
	 * 
	 * @Title: activePrefrentialDeal
	 *  <p>
	 * @Description: 激活折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void activePrefrentialDeal(PrefrentialDeal deal) {
		prefrentialDealDao.activePrefrentialDeal(deal);
	}
	/**
	 * 
	 * @Title: searchAllPrefrentialDeal
	 *  <p>
	 * @Description: 初始化折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return List<PrefrentialDeal>
	 * @throws
	 */
	@Override
	public List<PrefrentialDeal> searchAllPrefrentialDeal(PrefrentialDeal prefrentialDeal,int start, int limit) {
		return prefrentialDealDao.searchAllDeals(prefrentialDeal,start,limit);
	
	}
	/**
	 * 
	 * @Title: searchPrefrentialDealById
	 *  <p>
	 * @Description: 通过Id查找折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return PrefrentialDeal
	 * @throws
	 */
	@Override
	public PrefrentialDeal searchPrefrentialDealById(String id) {
		return prefrentialDealDao.queryPrefrentialDealById(id);
	}
	/**
	 * 
	 * @Title: endPrefrentialDeal
	 *  <p>
	 * @Description: 折扣方案失效<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void endPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		prefrentialDealDao.endPrefrential(prefrentialDeal);
	}
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 删除折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void deletePrefrentialDeal(String id) {
		prefrentialDealDao.deletePrefrentialDeal(id);
		prefrentialDealDao.deletePrefrentialDealItem(id);
	}

	@Override
	public String getDealId() {
		return contractDao.getSeqIdByTableName("T_CUST_DEALS");
		
	}
	/**
	 * 
	 * @Title: createPrefrentialDeal
	 *  <p>
	 * @Description: 根据条件查询月发月送方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return  List<PrefrentialDeal>
	 * @throws
	 */
	@Override
	public List<PrefrentialDeal> getEffectPrefrentialDeal(PrefrentialDeal deal) {
		return prefrentialDealDao.getEffectPrefrentialDeal(deal);
	}
	/**
	 * 
	 * @Title: countPrefrentialDeal
	 *  <p>
	 * @Description: 查询月发月送新优惠方案的总条数<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-14
	 * @return int
	 * @throws
	 */
	@Override
	public int countPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		return prefrentialDealDao.countPrefrentialDeal(prefrentialDeal);
	}
	/**
	 * 
	 * @Title: endEffectiveDeal
	 *  <p>
	 * @Description: 终止当前有效的基础资料方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-14
	 * @return void
	 * @throws
	 */
	@Override
	public void endEffectiveDeal(PrefrentialDeal deal) {
		prefrentialDealDao.endEffectiveDeal(deal);
	}
}
