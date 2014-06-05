package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
/**
 * 
 * <p>
 * Description:月发越送优惠方案manager<br />
 * </p>
 * @title IPrefrentialDealManager.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface IPrefrentialDealManager {
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
	public List<PrefrentialDeal> searchEffectPrefrentialDeal(PrefrentialDeal prefrentialDeal);
	/**
	 * 
	 * @Title: createPrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送优惠方案增加<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	public void createPrefrentialDeal(PrefrentialDeal prefrentialDeal);
	
	/**
	 * 
	 * @Title: updatePrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送折扣方案修改<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	public void updatePrefrentialDeal(PrefrentialDeal prefrentialDeal);
	
	/**
	 * 
	 * @Title: activePrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送折扣方案激活<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	public void activePrefrentialDeal(PrefrentialDeal prefrentialDeal);
	
	/**
	 * 
	 * @Title: searchPrefrentialDealById
	 *  <p>
	 * @Description: 通过Id查找新月发月送折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return PrefrentialDeal
	 * @throws
	 */
	public PrefrentialDeal searchPrefrentialDealById(String id);
	
	/**
	 * 
	 * @Title: endPrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送折扣方案失效<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	public void endPrefrentialDeal(String id);
	
	/**
	 * @param prefrentialDeal 
	 * 
	 * @Title: searchAllPrefrentialDeal
	 *  <p>
	 * @Description: 初始化所有折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return List<PrefrentialDeal>
	 * @throws
	 */
	public List<PrefrentialDeal> searchAllPrefrentialDeal(PrefrentialDeal prefrentialDeal, int start, int limit);
	
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 删除新月发月送折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	public void deletePrefrentialDeal(String id);
	
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
	 * @Title: prefrentialdealAutoWork
	 *  <p>
	 * @Description: 定时器调用
	 * 				失效应该今日失效的优惠方案，
	 * 				生效应该今日生效的优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-15
	 * @return void
	 * @throws
	 */
	public void prefrentialdealAutoWork();
}
