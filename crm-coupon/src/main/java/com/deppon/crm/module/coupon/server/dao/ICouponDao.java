/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICouponDao.java
 * @package com.deppon.crm.module.coupon.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.CouponResultVO;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICouponDao.java
 * @package com.deppon.crm.module.coupon.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public interface ICouponDao {
	/**   
	 * <p>
	 * Description:改变优惠券状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNums 优惠券编码
	 * @return int
	 */
	int updateCouponStatus(String couponCode, List<String> couponNums);

	/**   
	 * <p>
	 * Description:根据 优惠券编码 批量 查询优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	List<Coupon> searchCouponsByNums(List<String> couponNums);	
	
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询 单条优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return boolean
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
	 * @return int
	 */
	int updateOneCouponStatus(String couponCode, String couponNum);
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
	 * Description: 短信发券时查询所需字段<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param marketPlanId
	 * @return SendCouponVO 手机发券前台显示
	 */
	SendCouponVO searchSendCouponVO(Map<String,String> searchCondition);
	/**
	 * <p>
	 * Description: 插入优惠券短信发送表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param marketPlanId
	 * @return int 
	 */
	int createCouponPhoneMsg(CouponCellphoneMsgInfo couponPhoneMsg);
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
	 * 根据优惠券编码更新优惠券状态 优惠券发送手机号码  发送时间
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param coupon
	 * @return int
	 */
	int updateCouponByCouponNumber(Coupon coupon);	
	/**
	 * 根据归属部门 优惠券编码  优惠券状态 营销计划名称 发送时间 手机号 使用时间 查询优惠券信息
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @param condition
	 * @return List<CouponResultVO>
	 */
	List<CouponResultVO> selectCouponByMutiCondition(CouponSearchCondition condition,
			int start, int limit);
	
	/**
	 * <p>
	 * Description: 根据条件查询查询优惠券总数<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-23
	 * @param condition
	 * @return int
	 */
	int countCouponByMutiCondition(CouponSearchCondition condition);
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
	 * Description: 根据ID更新短信发送表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param ccpmi
	 * @return int
	 */
	int updateSendCouponMsg(CouponCellphoneMsgInfo ccpmi);
	/**
	 * <p>
	 * Description: 根据使用标识查询未使用的运单中间表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param status
	 * @return List<WaybillCoupon> 运单中间表
	 */
	List<WaybillCoupon> searchWaybillCouponByStatus(String status,int start,int limit);
	/**
	 * <p>
	 * Description: 根据ID更新运单中间表 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param ccpmi
	 * @return int
	 */
	int updateWaybillCouponByStatus(WaybillCoupon wbCoupon);
	
	/**
	 * <p>
	 * Description: 获取部门Seq <br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-12-6
	 * @param code
	 * @return String
	 */
	String getDeptSeq(String code);
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
	String callableCreateHandCouponImm(int size, String marketPlanId ,String useRuleId,String couponValue);
	/**
	 * <p>
	 * Description: 根据用户编码查询用户id <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 * @param empCode
	 * @return String
	 */
	String searchUserIdByDeptSeq(String principal);
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
}
