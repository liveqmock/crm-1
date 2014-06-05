package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IGiftDao;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description:礼品dao<br />
 * </p>
 * @title GiftDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class GiftDao extends iBatis3DaoImpl implements IGiftDao{
	/**
	 * 命名空间
	 * 
	 */
	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.integral.Gift.";
	/**
	 * 
	 * <p>
	 * Description:插入礼品<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 *
	 */
	@Override
	public void insertGift(Gift gift) {
		/**
		 * 插入
		 */
		this.getSqlSession().insert(NAME_SPACE+"insertGift", gift);
	}
	/**
	 * 
	 * <p>
	 * Description:更新礼品<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 *
	 */
	@Override
	public void updateGift(Gift gift) {
		/**
		 * 更新
		 */
		this.getSqlSession().update(NAME_SPACE+"updateGift", gift);
	}
	/**
	 * 
	 * <p>
	 * Description:根据id获得礼品<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public Gift getGiftById(String id) {
		//新建礼品
		Gift gift = new Gift();
		//id
		gift.setId(id);
		//通过条件查询
		List<Gift> list = getGifts(gift,0,1) ;
		//校验查询条数是否为空
		if(ValidateUtil.objectIsEmpty(list)){
			return null;
		}
		//返回
		return list.get(0);
	}
	/**
	 * 
	 * <p>
	 * Description:根据条件查询礼品<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 * @param start
	 * @param limit
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gift> getGifts(Gift gift,int start,int limit) {
		//设置分页
		RowBounds rowBounds = new RowBounds(start,limit);
		//或者模糊查询条件
		gift.setGiftName(SqlUtil.getLikeField(gift.getGiftName()));
		return this.getSqlSession().selectList(NAME_SPACE+"getGifts",gift,rowBounds);
	}
	/**
	 * 
	 * <p>
	 * Description:删除礼品<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 *
	 */
	@Override
	public void deleteGift(String id) {
		/**
		 * 删除礼品
		 */
		this.getSqlSession().delete(NAME_SPACE+"deleteGift",id);
	}
	/**
	 * 
	 * <p>
	 * Description:统计<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 * @return
	 *
	 */
	@Override
	public long countGetGifts(Gift gift) {
		//设置查询条件
		gift.setGiftName(SqlUtil.getLikeField(gift.getGiftName()));
		//统计
		return (Long) this.getSqlSession().selectOne(NAME_SPACE+"countGetGifts",gift);
	}

}
