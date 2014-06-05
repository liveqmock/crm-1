package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.GiftConvertHoldRecode;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;

/**
 * 
 * <p>
 * 积分持久化对象<br />
 * </p>
 * @title IIntegDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author bxj
 * @version 0.2 2012-4-21
 */
public interface IIntegralDao {
	
	/**
	 * 
	 * <p>
	 * 保存调整积分对象<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-21
	 * @param integ
	 * void
	 */
	public void insertAdjustIntegral(AdjustIntegral integral);
	
	/**
	 * 
	 * <p>
	 * 保存手动设置积分<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public void insertHandRewardIntegral(HandRewardIntegral integral);
	/**
	 * 
	 * <p>
	 * 保存积分兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public void insertIntegralConvertGift(IntegralConvertGift integral);
	/**
	 * 
	 * <p>
	 * 更新积分兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public void updateIntegralConvertGift(IntegralConvertGift integral);
	/**
	 * 
	 * <p>
	 * 更新手动设置积分<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public void updateHandRewardIntegral(HandRewardIntegral integral);
	/**
	 * 
	 * <p>
	 * 更新调整积分对象<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public void updateAdjustIntegral(AdjustIntegral integral);
	
	/**
	 * <p>
	 * Description:根据ID得到兑换积分<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @return
	 * IntegralConvertGift
	 */
	public IntegralConvertGift getIntegralConvertGiftById(String id);
	
	/**
	 * <p>
	 * Description:根据ID得到 手动奖励积分<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @return
	 * HandRewardIntegral
	 */
	public HandRewardIntegral getHandRewardIntegralById(String id);
	
	/**
	 * <p>
	 * Description:根据ID得到 调整积分<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param id
	 * @return
	 * AdjustIntegral
	 */
	public AdjustIntegral getAdjustIntegralById(String id);

	
	/**
	 * 
	 * <p>
	 * 分页查询 积分兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public List<IntegralConvertGift> searchIntegralConvertGifts(IntegralConvertGift integral,int start,int limit);
	
	/**
	 * <p>
	 * Description:统计 积分兑换礼品的 数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param integral
	 * @return
	 * long
	 */
	public long countSearchIntegralConvertGifts(IntegralConvertGift integral);
	
	/**
	 * <p>
	 * Description:根据联系人id  分页查询 积分兑换礼品<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return
	 * List<IntegralConvertGift>
	 */
	public List<IntegralConvertGift> searchIntegralConvertGiftForContactId(List<String> contactIdList, int start, int limit);

	/**
	 * <p>
	 * Description:根据联系人id 统计 积分兑换礼品的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchIntegralConvertGiftForContactId(List<String> contactIdList);

	/**
	 * 
	 * <p>
	 * 查询手动设置积分<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public List<HandRewardIntegral> searchHandRewardIntegrals(HandRewardIntegral integral,int start,int limit);
	
	/**
	 * <p>
	 * Description:根据手动奖励积分的实体信息中条件 统计手动奖励积分信息 的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param integral
	 * @return
	 * long
	 */
	public long countSearchHandRewardIntegrals(HandRewardIntegral integral);
	
	/**
	 * <p>
	 * Description:根据联系人ID 分页查询 手动奖励积分<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return
	 * List<HandRewardIntegral>
	 */
	public List<HandRewardIntegral> searchHandRewardIntegralForContactId(List<String> contactIdList, int start, int limit);

	/**
	 * <p>
	 * Description:通过联系人ID 统计 手动奖励积分的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchHandRewardIntegralForContactId(List<String> contactIdList);

	/**
	 * 
	 * <p>
	 * 查询调整积分对象<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public List<AdjustIntegral> searchAdjustIntegrals(AdjustIntegral integral,int start,int limit);
	
	/**
	 * <p>
	 * Description:根据调整积分实体条件 查询其对应的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param integral
	 * @return
	 * long
	 */
	public long countSearchAdjustIntegrals(AdjustIntegral integral);
	
	/**
	 * <p>
	 * Description:根据联系人ID 分页查询 调整积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return
	 * List<AdjustIntegral>
	 */
	public List<AdjustIntegral> searchAdjustIntegralForContactId(List<String> contactIdList, int start, int limit);

	/**
	 * <p>
	 * Description:根据联系人id 统计调整积分 的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchAdjustIntegralForContactId(List<String> contactIdList);
	
	/**
	 * <p>
	 * Description:根据兑换积分的查询条件  分页查 兑换积分信息 <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<IntegralConvertGift>
	 */
	public List<IntegralConvertGift> searchIntegralConvertGift(
			IntegralConvertGiftCondition condition,int start,int limit);
	
	/**
	 * <p>
	 * Description:根据兑换积分的查询条件 统计其数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * long
	 */
	public long countSearchIntegralConvertGift(
			IntegralConvertGiftCondition condition);
	/**
	 * @description 通过创建者id查询暂存的礼品清单.  
	 * @author 潘光均
	 * @version 0.1 2012-5-29
	 * @param String
	 *@date 2012-5-29
	 * @return List<IntegralConvertGift>
	 * @update 2012-5-29 下午3:45:50
	 */
	public List<GiftConvertHoldRecode> getTemporaryGiftBill(String deptId);
	
	/**
	 * @description 根据用户id批量删除暂存的礼品兑换对象.  
	 * @author 潘光均
	 * @version 0.1 2012-5-29
	 * @param String
	 *@date 2012-5-29
	 * @return void
	 * @update 2012-5-29 下午5:28:57
	 */
	public void batchDeleteTemporaryGiftBill(String deptId);
	
	/**
	 * @description 批量删除暂存的礼品兑换对象.  .  
	 * @author 潘光均
	 * @version 0.1 2012-5-31
	 * @param List<String> 
	 *@date 2012-5-31
	 * @return void
	 * @update 2012-5-31 下午12:57:18
	 */
	public void batchDeleteTemporaryGiftBill(List<String> ids);
	
	/**
	 * @description 保存礼品申请暂存记录.  
	 * @author 潘光均
	 * @version 0.1 2012-6-8
	 * @param 
	 *@date 2012-6-8
	 * @return void
	 * @update 2012-6-8 下午2:57:30
	 */
	public void addTemporySaveGift(GiftConvertHoldRecode giftConvertHoldRecode);

}
