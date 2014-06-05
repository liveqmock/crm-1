package com.deppon.crm.module.customer.server.service;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.GiftConvertHoldRecode;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;

public interface IIntegralService {
	
	/**
	 * <p>
	 * 保存积分操作对象<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param operation
	 * void
	 */
	public void saveIntegralOperation(IntegralOperation operation);
	
	/**
	 * <p>更具联系人获得联系人积分对象
	 * <br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param id
	 * @return
	 * ContactIntegral
	 */
	public ContactIntegral getContactIntegralByContact(String contactId);
	
	/**
	 * <p>
	 * Description:根据联系人ID 分页查询 调整积分信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchAdjustIntegralForContactId(List<String> contactIdList);
	
	/**
	 * <p>
	 * Description:根据联系人ID 分页查询 手动奖励积分<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchHandRewardIntegralForContactId(List<String> contactIdList);
	
	/**
	 * <p>
	 * Description:根据联系人id  分页查询 积分兑换礼品<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchIntegralConvertGiftForContactId(List<String> contactIdList);
	
	/**
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
	 * @version 0.1 2013-2-27
	 * @param integral
	 * @return
	 * long
	 */
	public long countSearchAdjustIntegrals(AdjustIntegral integral);
	
	/**
	 * <p>
	 * Description:分页查询手动设置积分<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integral
	 * @param start
	 * @param limit
	 * @return
	 * List<HandRewardIntegral>
	 */
	public List<HandRewardIntegral> searchHandRewardIntegrals(HandRewardIntegral integral,int start,int limit);
	
	/**
	 * <p>
	 * Description:根据手动奖励积分的实体信息中条件 统计手动奖励积分信息 的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integral
	 * @return
	 * long
	 */
	public long countSearchHandRewardIntegrals(HandRewardIntegral integral);
	
	/**
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
	 * <p>
	 * 查询积分兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * void
	 */
	public List<IntegralConvertGift> searchIntegralConvertGifts(IntegralConvertGift integral,int start,int limit);
	
	/**
	 * <p>
	 * Description:根据兑换积分的查询条件 统计其数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
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
	 * @version 0.1 2013-2-27
	 * @param condition
	 * @return
	 * long
	 */
	public long countSearchIntegralConvertGift(
			IntegralConvertGiftCondition condition);
	
	/**
	 * <p>更新联系人的积分<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-19
	 * @param contactIntegral
	 * void
	 */
	public void updateContactIntegral(ContactIntegral contactIntegral);
	
	/**
	 * <p>
	 * 更新积分联系人<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param memberIntegral
	 * void
	 */
	public void updateMemberIntegral(MemberIntegral memberIntegral);
	
	/**
	 * @description 通过创建者id查询暂存的礼品清单.  
	 * @author 潘光均
	 * @version 0.1 2012-5-29
	 * @param String
	 *@date 2012-5-29
	 * @return List<IntegralConvertGift>
	 * @update 2012-5-29 下午3:45:50
	 */
	public List<GiftConvertHoldRecode> getTemporaryGiftBill(String userId);
	
	/**
	 * @description 根据用户id删除暂存的礼品申请记录对象
	 * @author 潘光均
	 * @version 0.1 2012-5-29
	 * @param String
	 *@date 2012-5-29
	 * @return void
	 * @update 2012-5-29 下午3:45:50
	 */
	public void deleteTemporaryGiftBill(String deptId);
	
	/**
	 * @description 批量删除暂存的礼品申请记录对象.  
	 * @author 潘光均
	 * @version 0.1 2012-5-31
	 * @param List<String>
	 *@date 2012-5-31
	 * @return void
	 * @update 2012-5-31 下午12:56:08
	 */
	public void deleteTemporaryGiftBill(List<String> ids);
	
	/**
	 * @description 保存暂存礼品记录.  
	 * @author 潘光均
	 * @version 0.1 2012-6-8
	 * @param 
	 *@date 2012-6-8
	 * @return void
	 * @update 2012-6-8 下午2:56:13
	 */
	public void saveTemporyGiftList(
			List<GiftConvertHoldRecode> giftConvertHoldRecodes);
	
}
	
