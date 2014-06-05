package com.deppon.crm.module.customer.server.manager;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVaryExaminView;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;

public interface IIntegralManager {
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-23
	 * @param wkid 工作流ID
	 * @param wkStatus 状态
	 * @param wkman 审批人
	 * @param wktime 审批时间
	 * @return
	 * boolean
	 */
	public boolean giftIntegralCreateApprove(String wkid,boolean wkStatus,String wkman,Date wktime);
	/**
	 * 
	 * <p>根据会员ID获得旗下的联系人列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-26
	 * @param memberId
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> getContactIntegralListByMemId(String memberId);
	
	/**
	 * 
	 * <p>根据联系人ID，得到所在的会员积分对象<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-26
	 * @param contactId
	 * @return
	 * List<MemberIntegral>
	 */
	public MemberIntegral getMemberIntegralByContactId(String contactId);
	
	/**
	 * 
	 * <p>根据联系人列表，得到所在的会员列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-26
	 * @param slist
	 * @return
	 * List<MemberIntegral>
	 */
	public List<MemberIntegral> getMemberIntegralListByContact(List<String> slist);
	
	/**
	 * 
	 * <p>根据会员iD获得会员积分对象<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param mid
	 * @return
	 * MemberIntegral
	 */
	public MemberIntegral getMemberIntegralListByMemberId(String mid);

	
	/**
	 * 
	 * <p>根据会员积分条件，得到会员积分对象列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param dept
	 * @return
	 * List<MemberIntegral>
	 */
	public List<MemberIntegral> searchMemberIntegralsForCondition(IntegralSearchCondition memberIntegCondition,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:据会员积分条件，得到会员积分对象条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberIntegCondition
	 * @return
	 * long
	 */
	public long countSearchMemberIntegralsForCondition(IntegralSearchCondition memberIntegCondition);
	
	/**
	 * 
	 * <p>根据联系人ID列表，得到联系人积分列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param contactIdList
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> searchContactIntegralsForContactId(List<String> contactIdList,int start,int limit);
	
	/**
	 * 
	 * <p>根据联系人ID列表，得到礼品申请记录<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param contactIdList
	 * @return
	 * List<IntegralConvertGift>
	 */
	public List<IntegralConvertGift> searchIntegralConvertGiftForContactId(List<String> contactIdList,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchIntegralConvertGiftForContactId(List<String> contactIdList);

	
	/**
	 * 
	 * <p>获得以积分运单列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param contactIdList 联系人列表
	 * @param startDate 开单开始日期
	 * @param endDate 开单结束日期
	 * @param start
	 * @param limit 
	 * @return
	 * List<WaybillIntegral>
	 */
	public List<WaybillIntegral> searchWaybillIntegralForContactId(List<String> contactIdList,int start,int limit);
	
	public long countSearchWaybillIntegralForContactId(List<String> contactIdList);

	/**
	 * 
	 * <p>获得奖励积分列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return
	 * List<HandRewardIntegral>
	 */
	public List<HandRewardIntegral> searchHandRewardIntegralForContactId(List<String> contactIdList,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchHandRewardIntegralForContactId(List<String> contactIdList);

	/**
	 * 
	 * <p>获得调整积分列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return
	 * List<AdjustIntegral>
	 */
	public List<AdjustIntegral> searchAdjustIntegralForContactId(List<String> contactIdList,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchAdjustIntegralForContactId(List<String> contactIdList);

	/**
	 * 
	 * <p>
	 * 联系人变更<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param contactNumber
	 * @param memberNumber
	 * @return workflowId	
	 */
	public long contactVary(ContactVary contactVary,List<FileInfo> fileList);
	/**
	 * 
	 * <p>
	 * 根据审批结果处理联系人变更<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param contactVaryId
	 * @param examinresult
	 * void
	 */
	public void disposeContactVaryByExamineResult(String contactVaryId,boolean examinresult);
	
	/**
	 * 
	 * <p>
	 * <br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param workFlowId
	 * @return
	 * ContactVary
	 */
	public ContactVary getContactVaryByWorkFlowId(long workFlowId);
	
	/**
	 * 
	 * <p>
	 * 创建联系人变更审批视图<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param varyId
	 * @param workflowId
	 * @return
	 * ContactVaryExaminView
	 */
	public ContactVaryExaminView createContactVaryExaminView(String varyId,long workflowId);
	/**
	 * 
	 * <p>
	 * 手动积分奖励<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-2
	 * @param integralBasicNumber
	 * @param rewardId
	 * @param score
	 * @param contactId
	 * void
	 */
	public void createHandRewardIntegrals(Integer integralBasicNumber,
			RewardIntegRule rewardRule, List<String> contactId);
	
	/**
	 * 
	 * <p>
	 * 根据会员编码查询会员积分对象<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param memberNum
	 * @return
	 * MemberIntegral
	 */
	public MemberIntegral getMemberIntegralByMemberNum(String memberNum); 
	/**
	 * 
	 * <p>
	 * Description:根据客户编码获得客户积分信息 360专用<br />
	 * </p>
	 * @author 106143
	 * @version 0.1 2014年4月11日
	 * @param memberNum
	 * @return
	 * MemberIntegral
	 */
	public MemberIntegral getMemberIntegralByMemberNumFor360(String memberNum);
	/**
	 * 
	 * <p>
	 * 根据联系人编码查询会员积分对象<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param contactNum
	 * @return
	 * MemberIntegral
	 */
	public MemberIntegral getMemberIntegralByContactNum(String contactNum);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integral
	 * @param start
	 * @param limit
	 * @return
	 * List<AdjustIntegral>
	 */
	public List<AdjustIntegral> searchAdjustIntegrals(AdjustIntegral integral,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integral
	 * @return
	 * long
	 */
	public long countSearchAdjustIntegrals(AdjustIntegral integral);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integral
	 * @param start
	 * @param limit
	 * @return
	 * List<HandRewardIntegral>
	 */
	public List<HandRewardIntegral> searchHandRewardIntegrals(HandRewardIntegral integral,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integral
	 * @return
	 * long
	 */
	public long countSearchHandRewardIntegrals(HandRewardIntegral integral);
	
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
	 * <p>礼品兑换审批工作流<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-3
	 * @param flg（同意为true，拒绝为false）
	 * @param wkid
	 * void
	 */
	public void approvalIntegralConvertGift(boolean flg,String wkid);
	/**
	 * 
	 * <p>
	 * 组合查询积分兑换<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param condition
	 * @return
	 * List<IntegralConvertGift>
	 */
	public List<IntegralConvertGift> searchIntegralConvertGift(IntegralConvertGiftCondition condition,int start,int limit);
	/**
	 * 
	 * <p>
	 * 组合查询积分兑换<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-3
	 * @param condition
	 * @return
	 * long
	 */
	public long countSearchIntegralConvertGift(IntegralConvertGiftCondition condition);
	
	/**
	 * 
	 * <p>确认礼品发放<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-4
	 * @param idlist
	 * void
	 */
	public void updateIntegralConvertGiftSend(List<String> idlist); 
	/**
	 * 
	 * <p>确认礼品恢复<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-4
	 * @param idlist
	 * void
	 */
	public void updateIntegralConvertGiftRecover(List<String> idlist);
	/**
	 * 
	 * <p>
	 * 暂存积分兑换清单<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-4
	 * @param integralList
	 * void
	 */
	public void saveIntegralConvertGift(List<IntegralConvertGift> integralList);
	
	public List<IntegralConvertGift> getMyIntegralConvertGift();
	/**
	 * 
	 * <p>
	 * 根据联系人编码查询联系人积分<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-8
	 * @param contactNumber
	 * @return
	 * ContactIntegral
	 */
	public ContactIntegral getContactIntegralByContactNumber(
			String contactNumber);
	
	/**
	 * 
	 * <p>
	 * 根据客户Id查询对应的联系人积分对象，主联系人积分对象是第一个元素
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-5-8
	 * @param custId
	 * @return
	 * List<ContactIntegral>
	 */
	public List<ContactIntegral> get3ContactIntegral(String memberId);
	
	/**
	 * 
	 * <p>
	 * 根据客户客户联系人手机号获取酷虎360对象
	 * </p>
	 * @author 张斌
	 * @version 0.1 2012-5-25
	 * @param mobilePhone   手机号码
	 * @return
	 *      MemberIntegral
	 */
	public MemberIntegral getMemberIntegralByContactMobile(String mobilePhone);
	/**
	 * @description 查询暂存礼品清单..  
	 * @author 潘光均
	 * @version 0.1 2012-5-29
	 * @param 
	 *@date 2012-5-29
	 * @return List<IntegralConvertGift>
	 * @update 2012-5-29 下午3:42:56
	 */
	public List<IntegralConvertGift> searchTemporaryGiftBill();
	/**
	 * 
	 * <p>
	 * 保存积分兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-23
	 * @param integral
	 * @param contactId 联系人ID
	 * void
	 */
	List<String> insertIntegralConvertGift(List<IntegralConvertGift> integrals);
}
