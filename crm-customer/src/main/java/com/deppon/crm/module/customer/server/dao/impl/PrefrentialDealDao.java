package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;


import com.deppon.crm.module.customer.server.dao.IPrefrentialDealDao;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * 月发月送新优惠方案
 * </p>
 * @title PrefrentialDealDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl
 * @author 唐亮
 * @version 0.1 2013-3-12
 */
public class PrefrentialDealDao extends iBatis3DaoImpl implements IPrefrentialDealDao{
	//月发月送新优惠方案命名空间
	private static final String PREFRENTIALDEAL_NAMESPACE = "com.deppon.crm.module.customer.shared.domain.Contract.";
	//月发月送新优惠方案基础资料新增
	private static final String ADD_PREFRENTIALDEAL = "addPrefrentialDeal";
	private static final String ADD_PREFRENTIALDEALITEM = "addPrefrentialDealItem";
	//月发月送新优惠方案基础资料修改
	private static final String MODIFY_PREFRENTIALDEAL = "modifyPrefrentialDeal";
	private static final String MODIFY_PREFRENTIALDEALITEM = "modifyPrefrentialDealItem";	
	//优惠方案失效(根据Id)
	private static final String END_PREFRENTIAL = "endPrefrential";
	//直接失效掉当前有效的
	private static final String ENDEFFECTIVEDEAL = "endEffectiveDeal";
	//月发月送新优惠方案基础资料激活
	private static final String ACTIVE_PREFRENTIALDEAL = "activePrefrentialDeal";
	//初始化所有月发月送运费折扣方案
	private static final String SEARCH_PREFRENTIALDEAL = "searchPrefrentialDeal";
	//通过id查询优惠方案
	private static final String QUERY_PREFRENTIALDEAL = "queryPrefrentialDeal";
	//月发月送新优惠方案基础资料删除
	private static final String DELETE_PREFRENTIALDEAL = "deletePrefrentialDeal";
	private static final String DELETE_PREFRENTIALDEALITEM = "deletePrefrentialDealItem";

	private static final String GET_EFFECTPREFRENTIALDEAL = "getEffectPrefrentialDeal";
	//查询出优惠方案的总条目数
	private static final String COUNT_PREFRENTIALDEAL = "countPrefrentialDeal";
	
	
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
	@Override
	public void savePrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		 this.getSqlSession().insert(
				PREFRENTIALDEAL_NAMESPACE + ADD_PREFRENTIALDEAL, prefrentialDeal);							
	}
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
	@Override
	public void addPrefrentialDealItem(PrefrentialDealItem item) {
		this.getSqlSession().insert(
				PREFRENTIALDEAL_NAMESPACE + ADD_PREFRENTIALDEALITEM, item);
	}
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
	
	@Override
	public void modifyPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		this.getSqlSession().update(
				PREFRENTIALDEAL_NAMESPACE + MODIFY_PREFRENTIALDEAL,prefrentialDeal
				);
	}
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
	@Override
	public void endPrefrential(PrefrentialDeal prefrentialDeal) {
		this.getSqlSession().update(
				PREFRENTIALDEAL_NAMESPACE+END_PREFRENTIAL,prefrentialDeal
				);		
	}
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
	@Override
	public void activePrefrentialDeal(PrefrentialDeal deal) {
		this.getSqlSession().update(
				PREFRENTIALDEAL_NAMESPACE+ACTIVE_PREFRENTIALDEAL,deal);
	}
	/**
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
	@SuppressWarnings("unchecked")
	@Override
	public List<PrefrentialDeal> searchAllDeals(PrefrentialDeal prefrentialDeal,int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return (List<PrefrentialDeal>)this.getSqlSession().selectList(
				PREFRENTIALDEAL_NAMESPACE + SEARCH_PREFRENTIALDEAL,prefrentialDeal,rowBounds);	
	}
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
	@Override
	public PrefrentialDeal queryPrefrentialDealById(String id) {
		return (PrefrentialDeal) this.getSqlSession().selectOne(PREFRENTIALDEAL_NAMESPACE+QUERY_PREFRENTIALDEAL,id);		
	}
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
	@Override
	public void deletePrefrentialDeal(String id) {
		this.getSqlSession().delete(
				PREFRENTIALDEAL_NAMESPACE + DELETE_PREFRENTIALDEAL,id);
	}
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 月发月送新优惠方案基础资料修改<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	@Override
	public void modifyPrefrentialDealItem(PrefrentialDealItem dealItem) {
		this.getSqlSession().update(
				PREFRENTIALDEAL_NAMESPACE + MODIFY_PREFRENTIALDEALITEM,dealItem);
	}
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
	@Override
	public void deletePrefrentialDealItem(String dealId) {
		this.getSqlSession().delete(
				PREFRENTIALDEAL_NAMESPACE + DELETE_PREFRENTIALDEALITEM, dealId);
	}
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 根据条件查询月发月送新优惠方案基础资料<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PrefrentialDeal> getEffectPrefrentialDeal(PrefrentialDeal deal) {
		return (List<PrefrentialDeal>)this.getSqlSession().selectList(
				PREFRENTIALDEAL_NAMESPACE + GET_EFFECTPREFRENTIALDEAL,deal);
	}
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 查询月发月送新优惠方案基础资料条目总数<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	@Override
	public int countPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		return Integer.parseInt(String.valueOf(this.getSqlSession().selectOne(
				PREFRENTIALDEAL_NAMESPACE + COUNT_PREFRENTIALDEAL,prefrentialDeal)));
	}
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 终止当前有效的月发月送新优惠方案基础资料<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-8
	 * @return void
	 * @throws
	 */
	@Override
	public void endEffectiveDeal(PrefrentialDeal deal) {
		this.getSqlSession().update(PREFRENTIALDEAL_NAMESPACE + ENDEFFECTIVEDEAL,deal);
	}
}
