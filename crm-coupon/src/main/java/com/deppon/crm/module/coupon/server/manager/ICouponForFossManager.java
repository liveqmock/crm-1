package com.deppon.crm.module.coupon.server.manager;

import java.util.List;

import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;

/**
 * <p>
 * Description: 
 * 		优惠券使用规则校验
 * 		优惠券修改（状态变更）：未发送、已发送、已使用、已过期<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-13
 */
public interface ICouponForFossManager {
	/**
	 * <p>
	 * Description: 将优惠券状态批量置为：已过期<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNum	 优惠券编码（列表）
	 * @return String
	 */
	String changeListCouponOverdue(List<String> couponNums); 
	
	/**
	 * <p>
	 * Description: 运单作废： 1.如果是手动发券：优惠券状态变为未发送；<br />
	 * 			 2.如果是自动发券：优惠券状态变为已发送；<br />
	 *           3.过期的优惠券，状态为已过期。<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNum	 优惠券编码（列表）
	 * @return String
	 */
	String changeCouponInvalidWB(String couponNum); 
	
	/**
	 * <p>
	 * Description: 使用优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-20
	 * @param couponNum
	 * @param wbInfo
	 * @return String[]
	 */
	String[] changeCouponStatusUsing(String couponNum, WaybillInfo wbInfo);
	
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
}
