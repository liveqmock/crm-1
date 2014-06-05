package com.deppon.crm.module.customer.server.service;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.foss.framework.service.IService;
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
public interface IPrefrentialDealService extends IService{
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
	public void createPrefrentialDeal(PrefrentialDeal prefrentialDeal);
	
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
	public void updatePrefrentialDeal(PrefrentialDeal prefrentialDeal);
	
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
	public void activePrefrentialDeal(PrefrentialDeal deal);
	
	/**
	 * @param prefrentialDeal 
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
	public List<PrefrentialDeal> searchAllPrefrentialDeal(PrefrentialDeal prefrentialDeal, int start, int limit);
	
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
	public PrefrentialDeal searchPrefrentialDealById(String id);
	
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
	public void endPrefrentialDeal(PrefrentialDeal prefrentialDeal);
	
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
	public void deletePrefrentialDeal(String id);
	
	/**
	 * 
	 * @Title: getDealNumber
	 *  <p>
	 * @Description: 获取方案的Id<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-11
	 * @return String
	 * @throws
	 */
	public String getDealId();
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
	 * @Description: 统计月发月送新优惠方案总条目数<br />
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
