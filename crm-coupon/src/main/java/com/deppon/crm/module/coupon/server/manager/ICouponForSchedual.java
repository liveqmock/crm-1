package com.deppon.crm.module.coupon.server.manager;

import java.util.Date;



/**
 * <p>
 * Description: 优惠券状态修改-定时器<br/>
 * </p> 
 * @author 钟琼
 * @version 0.1 2012-11-26
 */
public interface ICouponForSchedual{
	/**
	 * <p>
	 * Description: 每天检查更新已启用的营销计划 状态是否过期，<br/>
	 * 	过期的把状态置为：已结束<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @return boolean
	 */
	boolean updateMarketPlanStatesByOverdue();
	
	/**
	 * <p>
	 * Description: 每天检查更新所有状态（不包括已过期）优惠券 状态是否过期，<br/>
	 *  过期的把状态置为：已过期<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 */
	boolean updateCouponStatesByOverdue();
	/**
	 * <p>
	 * Description: 向短信网关发送优惠券信息<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-27
	 * @return boolean
	 */
	boolean sendCouponMsg();
	/**
	 * <p>
	 * Description: 优惠券自动返券,每天查询前一天符合已启用营销计划的运单,进行优惠券发送<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @return boolean
	 */
	boolean autoSendCoupon(Date startdate,Date enddate);

	/**
	 * <p>
	 * Description:手动发券创建优惠券后台任务<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @return boolean
	 */
	boolean createCouponHandBackground();
	
}
