/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICouponService.java
 * @package com.deppon.crm.module.coupon.server.service 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.CouponResultVO;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.HandMarketPlan;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICouponService.java
 * @package com.deppon.crm.module.coupon.server.service 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public interface ICouponService {
	/**   
	 * <p>
	 * Description:改变优惠券状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	boolean updateCouponStatus(String couponCode, List<String> couponNums);
	
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	List<Coupon> searchCouponsByNums(List<String> couponNums);

	/**   
	 * <p>
	 * Description:根据 优惠券编码查询优惠券<br />
	 * </p>
	 * @author 朱培军
	 * @version 0.1 2012-11-13
	 * @param couponNum 优惠券编码
	 * @return boolean
	 */
	Coupon getCouponByNum(String couponNum);
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-13
	 * @param couponNums
	 * @return CouponRule
	 */
	CouponRule getCouponRuleByNumber(String couponNums);
	
	/**
	 * <p>
	 * Description: 根据营销计划查询规则<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-13
	 * @param couponNums
	 * @return CouponRule
	 */
	CouponRule getCouponRuleByMarketPlanId(String marketPlanId);
	/**   
	 * <p>
	 * Description:根据条件，改变单条优惠券状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */

	boolean updateOneCouponStatus(String couponCode, String couponNum);
	/**   
	 * <p>
	 * Description:新增手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponHand 手动发券实体类
	 * @return boolean
	 */
	boolean addMarketplanVOByRuleCouponHand(RuleCouponHand ruleCouponHand);
	/**   
	 * <p>
	 * Description:新增自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponAuto 自动发券实体类
	 * @return boolean
	 */
	boolean addMarketplanVOByRuleCouponAuto(RuleCouponAuto ruleCouponAuto);
	/**   
	 * <p>
	 * Description:新增营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param marketplan 营销计划实体类
	 * @return boolean
	 */
	boolean addMarketplanVOByMarketplan(Marketplan marketplan);
	/**   
	 * <p>
	 * Description:新增优惠券使用条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param couponRule 优惠券使用条件实体类
	 * @return boolean
	 */
	boolean addMarketplanVOByRuleCouponRule(CouponRule couponRule);
	/**   
	 * <p>
	 * Description:查询营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketPlane 新增营销计划-发券规则
	 * @param start
	 * @param limit
	 * @return List<Marketplan>
	 */
	List<Marketplan> searchMarketPlan(Marketplan marketplan,int start,int limit);
	/**   
	 * <p>
	 * Description:查询营销计划 条数<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-22
	 * @param marketPlane 新增营销计划-发券规则
	 * @return String
	 */
	String searchMarketPlanTotalCount(Marketplan marketplan);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询营销计划详情<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return Marketplan
	 */
	Marketplan searchMarketPlanDetail(String marketplanId);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponHand
	 */
	RuleCouponHand searchRuleCouponHand(String marketplanId);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponAuto
	 */
	RuleCouponAuto searchOnlyRuleCouponAuto(String marketplanId);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponAuto
	 */
	RuleCouponAuto searchRuleCouponAuto(String marketplanId);
	/**
	 * <p>
	 * Description: 批量创建优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-19
	 * @param coupon
	 * @return boolean
	 */
	int createCoupon(Coupon coupon);
	/**
	 * <p>
	 * Description: 更新优惠券信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-21
	 * @param coupon
	 * @return boolean
	 */
	boolean updateCoupon(Coupon coupon);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询返券类型<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marktPlanNumber 营销计划编码
	 * @return Marketplan
	 */
	Marketplan searchCouponTypeByMarketPlanId(String marktPlanNumber);
	/**   
	 * <p>
	 * Description:更新营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketplan 营销计划
	 * @return boolean
	 */
	boolean updateMarketPlan(Marketplan marketplan);
	/**   
	 * <p>
	 * Description:更新手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketplan 营销计划
	 * @return boolean
	 */
	boolean updateCouponHand(RuleCouponHand ruleCouponHand);
	/**   
	 * <p>
	 * Description:更新自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketplan 营销计划
	 * @return boolean
	 */
	boolean updateCouponAuto(RuleCouponAuto ruleCouponAuto);
	/**   
	 * <p>
	 * Description:更新优惠券使用规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param couponRule 使用规则划
	 * @return boolean
	 */
	boolean updateCouponRule(CouponRule couponRule);
	/**   
	 * <p>
	 * Description:查询手机发券前台显示<br/>
	 * </p>
	 * @author ZhouYuan 
	 * @version 0.1 2012-11-20
	 * @param marketPlanId 营销计划ID
	 * @return SendCouponVO 手机发券前台显示
	 */
	SendCouponVO searchSendCouponVO(Map<String,String> searchCondition);
	/**
	 * <p>
	 * Description: 插入优惠券短信发送表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param couponPhoneMsg 优惠券短信发送实体
	 * @return boolean
	 */
	boolean createCouponPhoneMsg(CouponCellphoneMsgInfo couponPhoneMsg);
	/**
	 * <p>
	 * Description: 根据营销计划编码 优惠券类型查询优惠券<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param coupon start limit
	 * @return Coupon
	 */
	List<Coupon> selectCouponByMarketPlanIdAndStatus(Coupon coupon,int start,int limit);
	/**
	 * <p>
	 * Description: 查询优化券前台显示<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-23
	 * @param cp
	 * @return CouponReslutVO
	 */
	List<CouponResultVO> searchCouponResultVOByMutiCondition(CouponSearchCondition condition);
	/**
	 * <p>
	 * Description: 根据条件查询优惠券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param condition
	 * @return int
	 */
	int countCouponByMutiCondition(CouponSearchCondition condition);
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-22
	 * @param condition
	 * @return Map
	 */
	Map<String, Object> searchCouponByCondition(CouponSearchCondition condition,Department dept,
			int start, int limit);
	/**
	 * <p>
	 * Description: 根据优惠券类型和优惠券类型ID查询手动发券营销计划<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param couponType
	 * @param typeId
	 * @return HandMarketPlan>手动发券营销计划 
	 */
	HandMarketPlan searchHandMarketPlanByCouponTypeAndTypeId(String couponType,
			String typeId);
	/**
	 * <p>
	 * Description:删除营销计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-23
	 * @param marketPlanId
	 * @return boolean
	 */
	boolean deleteMarketPlan(String marketPlanId);
	/**
	 * <p>
	 * Description:删除手动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId，id
	 * @return boolean
	 */
	boolean deleteCouponHand(String marketPlanId, String id);
	/**
	 * <p>
	 * Description:删除自动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId，id
	 * @return boolean
	 */
	boolean deleteCouponAuto(String marketPlanId, String id);
	/**
	 * <p>
	 * Description:删除使用条件<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId，id
	 * @return boolean
	 */
	boolean deleteCouponRule(String marketPlanId, String id);
	
	/**
	 * <p>
	 * Description: 根据发送标识查询未发送的短信<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param sended
	 * @return List<CouponCellphoneMsgInfo> 未发送短信实体 
	 */
	List<CouponCellphoneMsgInfo> searchSendCouponMsg(String sended,int start,int limit);
	/**
	 * <p>
	 * Description: 根据id更新手机发送表中数据的发送状态<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param ccpmi
	 * @return boolean
	 */
	boolean updateSendCouponMsg(CouponCellphoneMsgInfo ccpmi);
	/**   
	 * <p>
	 * Description:查询所有已启用的营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-27
	 * @param 
	 * @return boolean
	 */
	boolean updateMarketPlanForSchedual();
	/**
	 * <p>
	 * Description: 每天检查更新所有状态（不包括已过期）优惠券 状态是否过期，<br/>
	 *  过期的把状态置为：已过期<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-29
	 */
	boolean updateCouponStatesByOverdueForSchedual();

	/**
	 * <p>
	 * Description: 根据使用标识查询未使用的运单中间表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param status
	 * @return List<WaybillCoupon> 运单中间表
	 */
	List<WaybillCoupon> searchWaybillCouponByStatus(String status,int start,int limit);

	/**
	 * <p>
	 * Description: 根据ID更新运单中间表 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param ccpmi
	 * @return boolean
	 */
	boolean updateWaybillCouponByStatus(WaybillCoupon wbCoupon);
	/**
	 * <p>
	 * Description: 存储过程调用创建优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param couponType
	 * @return String
	 */
	String callableCreateCouponHand(String couponType);
	/**
	 * <p>
	 * Description: 存储过程调用自动券短信发送 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param empCode
	 * @param deptCode
	 * @return String
	 */
	String callableSendMsgAuto(Date startdate,Date enddate,String empCode,String deptCode);
	/**
	 * <p>
	 * Description: 存储过程调用创建手动优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param empCode
	 * @param deptCode
	 * @return String
	 */
	String callableCreateHandCouponImm(int size, String marketPlanId ,
			String useRuleId,String couponValue);
	/**
	 * <p>
	 * Description: 查询优惠券-部门查询 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 */
	List<Department> queryDeptListByName(String userId, String deptName,
			String shiyebuName, String isMarketingDept, int start, int limit);
	/**
	 * <p>
	 * Description: 查询优惠券-部门查询-条数 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 */
	int counrQueryDeptListByName(String userId, String deptName,
			String shiyebuName, String isMarketingDept, int start, int limit);
	/**
	 * <p>
	 * Description:根据部门标杆编码和活动类型查询已启用的手动券营销计划<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-9-4
	 * @param CouponForInterface
	 * @return int
	 */
	public Coupon searchMarketPlanByDeptStandardCodeAndActivityType(CouponForInterface cfi);
	/**
	 * <p>
	 * Description:根据部门标杆编码和活动类型查询手动券营销计划名称<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-9-4
	 * @param CouponForInterface
	 * @return String
	 */
	public String searchMPNameMarketPlanByDeptStandardCodeAndActivityType(CouponForInterface cfi);
}
