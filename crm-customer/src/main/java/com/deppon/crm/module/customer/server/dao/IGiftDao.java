package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.Gift;
/**
 * 
 * <p>
 * 礼物dao<br />
 * </p>
 * @title IGiftDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author bxj
 * @version 0.2 2012-4-24
 */
public interface IGiftDao {

	/**
	 * <p>
	 * Description:保存积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param gift
	 * void
	 */
	public void insertGift(Gift gift);
	
	/**
	 * <p>
	 * Description:根据ID修改积分的信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param gift
	 * void
	 */
	public void updateGift(Gift gift);	

	/**
	 * <p>
	 * Description:通过ID得到相应的积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @return
	 * Gift
	 */
	public Gift getGiftById(String id);

	/**
	 * <p>
	 * Description:通过积分实体条件 分页查询 对应的积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param gift
	 * @param start
	 * @param limit
	 * @return
	 * List<Gift>
	 */
	public List<Gift> getGifts(Gift gift,int start,int limit);
	
	/**
	 * <p>
	 * Description:根据积分实体条件  统计其对应积分信息的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param gift
	 * @return
	 * long
	 */
	public long countGetGifts(Gift gift);
	
	/**
	 * <p>
	 * Description:通过ID删除对应的积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * void
	 */
	public void deleteGift(String id);
}
