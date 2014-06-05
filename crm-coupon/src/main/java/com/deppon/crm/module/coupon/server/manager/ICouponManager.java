/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICouponManager.java
 * @package com.deppon.crm.module.coupon.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.manager;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.CouponTypeVo;
import com.deppon.crm.module.coupon.shared.domain.HandMarketPlan;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICouponManager.java
 * @package com.deppon.crm.module.coupon.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
public interface ICouponManager {
	/**
	 * <p>
	 * Description: 优惠券规则验证<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-12
	 * @param couponNum
	 * @param wbInfo
	 * @return
	 * String[] 返回数组，
	 * 		下标0：“true”/“false”
	 * 		下表1：true时代表金额，false时代表错误编码
	 */
	String[] checkCouponRule(String couponNum, WaybillInfo wbInfo);

	/**
	 * <p>
	 * Description: 批量更新优惠券状态<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-12
	 * @param couponCode	优惠券状态编码
	 * @param couponNum		优惠券编码（列表）
	 * @return
	 * boolean
	 */
	boolean updateCouponStatus(String couponCode, List<String> couponNums);
	
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询优惠券集合<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return List<Coupon>
	 */
	List<Coupon> searchCouponsByNums(List<String> couponNums);
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询 单条优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return Coupon
	 */
	Coupon getCouponByNum(String couponNum);
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
	boolean updateOneCouponStatu(String couponCode,String couponNum);
	
	/**   
	 * <p>
	 * Description:新增营销计划-发券规则-保存营销计划规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-14
	 * @param marketPlaneVO 新增营销计划-发券规则VO,user
	 * @return boolean
	 */
	boolean createMarketPlanCoupon(MarketPlaneVO marketPlaneVO,User user);
	/**   
	 * <p>
	 * Description:新增营销计划-发券规则-启用营销计划规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-14
	 * @param marketPlaneVO 新增营销计划-发券规则VO，user
	 * @return boolean
	 */
	boolean startMarketPlanCoupon(MarketPlaneVO marketPlaneVO,User user);
	/**   
	 * <p>
	 * Description:查询营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplan 营销计划实体
	 * @param start
	 * @param limit
	 * @return Map<String,Object>
	 */
	Map<String,Object> searchMarketPlan(User user,Marketplan marketplan,int start,int limit);
	/**   
	 * <p>
	 * Description:查询营销计划详情<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return MarketPlaneVO
	 */
	 MarketPlaneVO searchMarketPlanDetail(String marketplanId);
	 /**   
		 * <p>
		 * Description:根据营销计划id查询使用规则<br/>
		 * </p>
		 * @author 钟琼
		 * @version 0.1 2012-11-18
		 * @param marketplanId 营销计划ID
		 * @return CouponRule
		 */
	CouponRule getCouponRuleByMarketPlanId(String marketPlanId);
	/**   
	 * <p>
	 * Description:查看详情界面-启用营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId 营销计划id,user
	 * @return boolean
	 */
	boolean startMarketPlanByDetail(MarketPlaneVO marketPlaneVO,User user);
	/**   
	 * <p>
	 * Description:终止营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId 营销计划id,user
	 * @return boolean
	 */
	boolean stopMarketPlan(String marketPlanId,User user);
	/**   
	 * <p>
	 * Description:删除营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId 营销计划id
	 * @return boolean
	 */
	boolean deleteMarketPlan(String marketPlanId);
	/**
	 * <p>
	 * Description: 创建优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-19
	 * @param coupon
	 * @return Coupon
	 */
	int createCoupon(Coupon coupon);

	/**
	 * <p>
	 * Description: 创建手动优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-20
	 * @param size
	 * @param marketPlanId
	 * @param useRuleId
	 * @param couponValue
	 * @return boolean
	 */
	boolean createHandCoupon(int size, String marketPlanId,
			String useRuleId, String couponValue);
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
	 * Description:查看详情界面-更新营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketPlaneVO 营销计划 页面 VO,user
	 * @return boolean
	 */
	boolean updateMarketPlan(MarketPlaneVO marketPlaneVO,User user);
	/**   
	 * <p>
	 * Description:根据营销计划查询优惠券数量,剩余数量,活动结束时间,活动开始时间,短信内容,营销计划编码,营销计划名称提供前台显示<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param marketPlanId
	 * @return SendCouponVO 短信发券前台显示
	 */
	SendCouponVO searchSendCouponVOByMPI(String planNumber);
	/**   
	 * <p>
	 * Description:保存营销计划时，封装前台使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-22
	 * @param couponTypeVos
	 * @return Map<String,List<ConditionType>> 
	 */
	Map<String,List<ConditionType>> matchMarketPlanByConditionType(CouponTypeVo couponTypeVos);
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-22
	 * @param condition
	 * @return Map
	 */
	Map<String, Object> searchCouponByCondition(CouponSearchCondition condition,
			int start, int limit, User user);
	/**   
	 * <p>
	 * Description:将需要发送的信息存入到优惠劵手机发送表中<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param planNumber phones user
	 * @return SendCouponVO 短信发券前台显示
	 */
	boolean sendCouponByCellphones(String planNumber ,List<CouponCellphone> phones,User user);
	/**
	 * <p>
	 * Description: 批量导入手机号码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param filePath
	 * @return
	 * List<CouponCellphone> 返回列表,包括合法和非法的手机号码
	 */
	List<CouponCellphone> batchImportCellphones(File file,String fileName);
	/**
	 * <p>
	 * Description: 批量删除无效手机号码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param phones
	 * @return
	 * List<CouponCellphone> 返回列表,合法的手机号码
	 */
	List<CouponCellphone> cleanInvalidCellphones(List<CouponCellphone> phones);
	/**
	 * <p>
	 * Description: 批量删除重复手机号码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param phones
	 * @return
	 * List<CouponCellphone> 返回列表,不重复的手机号码
	 */
	List<CouponCellphone> deleteRepeatCellphones(List<CouponCellphone> phones);
	/**   
	 * <p>
	 * Description:根据 营销计划编码 更新营销计划状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param marketplanCode 营销计划状态码，marketplanNums 营销计划编码
	 * @return boolean
	 */
	boolean updateMarketPlanStatus(String marketplanCode, String marketplanNums);
	/**
	 * <p>
	 * Description: 导出查询优惠券 excel报表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-23
	 * @param title
	 * @param coupons
	 * @param fileName
	 * @param filePath
	 * @return
	 * boolean
	 */
	boolean creatCouponReportExcel(CouponSearchCondition condition, String fileName, String filePath,User user);
	/**
	 * <p>
	 * Description: 短信发券将需要发送的任务插入到短信发券表中<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-23
	 * @param sendCouponVO
	 * @param phones
	 * @param coupons
	 * @param user
	 * @return
	 * boolean
	 */
	boolean sendCouponMsg(SendCouponVO sendCouponVO,
			List<CouponCellphone> phones,List<Coupon> coupons,User user);
	/**
	 * <p>
	 * Description: 短信发券将需要发送的任务插入到短信发券表中<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param ccmi
	 * @param user
	 * @return
	 * boolean
	 */
	boolean sendCouponMsgOne(CouponCellphoneMsgInfo ccmi);
	/**   
	 * <p>
	 * Description:查询营销计划时，解析前台使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-2６
	 * @param Map<String,List<ConditionType>>
	 * @return couponTypeVo
	 */
	CouponTypeVo matchMarketPlanByConditionType(
			List<ConditionType> conditionTypeCreateList,
			List<ConditionType> conditionTypeUseList);
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
	 * Description: 重发优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-27
	 * @param String couponNumber
	 * @return boolean
	 */
	
	boolean reSendCouponMsg(String couponNumber, User user);
	
	/**
	 * <p>
	 * Description: 查询未发送的短信信息<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-28
	 * @param send
	 * @param start
	 * @param limit
	 * @return List<CouponCellphoneMsgInfo>
	 */
	List<CouponCellphoneMsgInfo> searchSendCouponMsg(String send,int start, int limit);
	/**
	 * <p>
	 * Description: 更新已发送短信在短信发送表中的状态<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-28
	 * @param msgs
	 * @param start
	 * @param limit
	 * @return List<CouponCellphoneMsgInfo>
	 */
	boolean updateSendCouponMsg(List<CouponCellphoneMsgInfo> msgs,String send);

	/**
	 * <p>
	 * Description: 根据部门名称模糊查询部门<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-28
	 * @param deptIds
	 * @return
	 * List<Department>
	 */
	Map<String, Object> queryDeptListByDeptName(User user, String deptName,
			int start, int limit);
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
	 * Description:查询使用规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-29
	 * @param marketplanId 营销计划ID
	 * @return MarketPlaneVO
	 */

	MarketPlaneVO searchCouponRuleByMarketPlanIdDetail(String marketplanId);
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
	 * Description: 根据优惠券类型和优惠券类型ID查询手动发券营销计划<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param couponType
	 * @param typeId
	 * @return HandMarketPlan 手动发券营销计划 
	 */
	HandMarketPlan searchHandMarketPlanByCouponTypeAndTypeId(String couponType,String typeId);
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
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有外场
	 * @参数：deptName 部门名字
	 * @返回值：Map<String,Object> 部门集合
	 * */
	Map<String,Object> queryDeptListByName(String deptName,int start,int limit);
	/**   
	 * <p>
	 * Description:更新手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponHand 自动发券
	 * @return boolean
	 */
	boolean updateCouponHand(RuleCouponHand ruleCouponHand);
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
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有外场
	 * @参数：deptName 部门名字
	 * @返回值：Map<String,Object> 部门集合
	 * */
	Map<String, Object> queryOutDeptListByName(String deptName, int start,
			int limit);
	/**
	 * <p>
	 * Description: 生产新点优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 * @param CouponForInterface
	 * @return String
	 */
	public String createCouponForInterface(CouponForInterface cfi);
}
