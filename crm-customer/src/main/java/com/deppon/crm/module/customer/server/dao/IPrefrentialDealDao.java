package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;
/**
 * 
 * <p>
 * 月发月送运费折扣
 * </p>
 * @title IPrefrentialDealDao.java
 * @package com.deppon.crm.module.customer.server.dao
 * @author 唐亮
 * @version 0.1 2013-3-8
 */

public interface IPrefrentialDealDao {
	
	/**
	 * 
	 * @Title: addPrefrentialDeal
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料新增<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return PrefrentialDeal
	 * @throws
	 */
	
	public void savePrefrentialDeal(PrefrentialDeal prefrentialDeal);
	/**
	 * 
	 * @Title: addPrefrentialDealItem
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料新增<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	public void addPrefrentialDealItem(PrefrentialDealItem item);
	/**
	 * 
	 * @Title: modifyPrefrentialDeal
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料修改<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return PrefrentialDeal
	 * @throws
	 */
	public void modifyPrefrentialDeal(PrefrentialDeal prefrentialDeal);
	/**
	 * 
	 * @Title: modifyPrefrentialDealItem
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料修改<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	public void modifyPrefrentialDealItem(PrefrentialDealItem dealItem);
	/**
	 * 
	 * @Title: endPrefrential
	 *  <p>
	 * @Description: 优惠方案失效<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	
	public void endPrefrential(PrefrentialDeal prefrentialDeal);
	
	/**
	 * 
	 * @Title: activePrefrentialDeal
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料激活<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	public void activePrefrentialDeal(PrefrentialDeal deal);
	
	/**
	 * @param prefrentialDeal 
	 * 
	 * @Title: searchAllDeals
	 *  <p>
	 * @Description: 初始化所有月发月送运费折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	public List<PrefrentialDeal> searchAllDeals(PrefrentialDeal prefrentialDeal, int start, int limit);
	
	/**
	 * 
	 * @Title: queryPrefrentialDealById
	 *  <p>
	 * @Description: 通过id查询优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	public PrefrentialDeal queryPrefrentialDealById(String dealId);
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料删除<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	public void deletePrefrentialDeal(String id);
	/**
	 * 
	 * @Title: deletePrefrentialDealItem
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料删除<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-12
	 * @return void
	 * @throws
	 */
	public void deletePrefrentialDealItem(String id);
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
	public List<PrefrentialDeal> getEffectPrefrentialDeal(PrefrentialDeal deal);
	
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
	public int countPrefrentialDeal(PrefrentialDeal prefrentialDeal);
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
	public void endEffectiveDeal(PrefrentialDeal deal);
}
