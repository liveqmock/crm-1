package com.deppon.crm.module.coupon.server.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;
import com.deppon.crm.module.coupon.shared.exception.ExceptionUtils;
/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponMsgUtil.java
 * @package com.deppon.crm.module.coupon.server.util 
 * @author ZhouYuan
 * @version 0.1 2012-11-22
 */
public class CouponMsgUtil {
	/**
	 * <p>
	 * Description: 用手机号和优惠券信息拼装成短信发送优惠券信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @param sendCouponVO phone couponNumber empCode
	 * @return
	 * CouponCellphoneMsgInfo 手机发送优惠券实体类
	 */
	public static CouponCellphoneMsgInfo createCouponMsg(SendCouponVO sendCouponVO ,CouponCellphone phone,String couponNumber,String empCode,String deptCode){
		
		CouponCellphoneMsgInfo ccmi = new CouponCellphoneMsgInfo();
		//设置优惠券号
		ccmi.setCouponNumber(couponNumber);
		//设置发送手机号
		ccmi.setPhoneNumber(phone.getCellphone());
		//设置短息信息
		ccmi.setMsgContent(createMessage(sendCouponVO.getSms(),sendCouponVO.getCouponValue(), 
				couponNumber ,sendCouponVO.getEndTime()));
		//设置创建时间
		ccmi.setCreateTime(new Date());
		//设置发送标识为未发送(0)
		ccmi.setSended(MarketingCouponConstance.COUPON_MSG_SENDED_UNSEND);
		//设置发送人编码
		ccmi.setSenderEmpCode(empCode);
		//设置发送部门编码
		ccmi.setSendStandardDeptCode(deptCode);

		return ccmi;
	}
	/**
	 * <p>
	 * Description: 根据运单信息创建短信发送信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @return
	 * CouponCellphoneMsgInfo 手机发送优惠券实体类
	 */
	public static CouponCellphoneMsgInfo createCouponMsg(WaybillCoupon wbCoupon,String couponNumber,String empCode,String deptCode){
		CouponCellphoneMsgInfo ccmi = new CouponCellphoneMsgInfo();
		//设置优惠券号
		ccmi.setCouponNumber(couponNumber);
		//设置发送手机号
		ccmi.setPhoneNumber(wbCoupon.getSendTelPhone());
		//设置短息信息
		ccmi.setMsgContent(createMessage(wbCoupon.getMsgContent(),wbCoupon.getValue(), 
				couponNumber,wbCoupon.getEndTime()));
		//设置创建时间
		ccmi.setCreateTime(new Date());
		//设置发送标识为未发送(0)
		ccmi.setSended(MarketingCouponConstance.COUPON_MSG_SENDED_UNSEND);
		//设置发送人编码
		ccmi.setSenderEmpCode(empCode);
		//设置发送部门编码 
		ccmi.setSendStandardDeptCode(deptCode);

		return ccmi;
	}
	/**
	 * <p>
	 * Description: 根据运单信息批量创建短信发送信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @return
	 * CouponCellphoneMsgInfo 手机发送优惠券实体类
	 */
	public static List<CouponCellphoneMsgInfo> createCouponMsg(List<WaybillCoupon> wbCoupons,
			List<Coupon> coupons,String empCode,String deptCode){
		//创建短信发送实体集合
		List<CouponCellphoneMsgInfo> ccmis = new ArrayList<CouponCellphoneMsgInfo>();
		//判断优惠券运单数据是否等于现有优惠券数目
		if(wbCoupons.size()==coupons.size()){
			//遍历优惠券运单数据
			for( int i = 0; i<wbCoupons.size();i++ ){
				//设置需要发送的手机短信信息
				CouponCellphoneMsgInfo ccmi = createCouponMsg( wbCoupons.get(i),
						coupons.get(i).getCouponNumber(),empCode,deptCode);
				//将实体添加到集合中
				ccmis.add(ccmi);
			}
		}else{
			ExceptionUtils.createCouponException(CouponExceptionType.waybillAndCouponNumberNotMatch);
		}
		return ccmis;
	}
	/**
	 * <p>
	 * Description: 设置短信发送信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-27
	 * @param coupon
	 * @param phone
	 * @param couponNumber
	 * @param empCode
	 * @return CouponCellphoneMsgInfo
	 */
	public static CouponCellphoneMsgInfo createCouponMsg(Coupon coupon,
			String smsMsg, Date endTime, 
			String empCode) {
		CouponCellphoneMsgInfo ccmi = new CouponCellphoneMsgInfo();
		//设置优惠券号
		ccmi.setCouponNumber(coupon.getCouponNumber());
		//设置发送手机号
		ccmi.setPhoneNumber(coupon.getSendtelPhone());
		//设置短息信息
		SendCouponVO sendCouponVO = new SendCouponVO();
		//设置发送短信信息
		
		 // 短信内容
		sendCouponVO.setSms(smsMsg);
		// 结束时间
		sendCouponVO.setEndTime(endTime);		
		// 优惠券金额
		sendCouponVO.setCouponValue(coupon.getUseCouponValue()); 
		ccmi.setMsgContent(createMessage(sendCouponVO.getSms(),sendCouponVO.getCouponValue(), coupon.getCouponNumber(),sendCouponVO.getEndTime()));
		//设置创建时间
		ccmi.setCreateTime(new Date());
		//设置发送标识为未发送(0)
		ccmi.setSended(MarketingCouponConstance.COUPON_MSG_SENDED_UNSEND);
		//设置发送人编码
		ccmi.setSenderEmpCode(empCode);
		//设置发送部门编码
		ccmi.setSendStandardDeptCode(MarketingCouponConstance.COUPON_MSG_SEND_DEPT);

		return ccmi;
		
	}
	/**
	 * <p>
	 * Description: 生成短信发送内容<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @return
	 * String
	 */
	public static String createMessage(String handMsg,String couponValue,String couponNumber,Date endTime){
		/*
		 * 短信固定格式
		 * ***元优惠券，编码：********，有效期至**年**月**日，用券详情请咨询当地营业部！
		 */
		StringBuffer sb = new StringBuffer();
		//添加用户自定义部分
		if( handMsg !=null ){
			sb.append( handMsg );
		}
		//添加固定格式部分
		if( couponValue!=null ){
			sb.append(couponValue);
		}else{
			sb.append(0);
		}
		sb.append("元优惠券,编码: ");
		sb.append(couponNumber);
		sb.append(" , 有效期至");
		if( endTime != null){
			sb.append(formatDate( endTime,MarketingCouponConstance.COUPON_MSG_DATE_FORMAT));
		}
		sb.append(", 用券详情请咨询当地营业部!");
		return sb.toString();
	}
	/**
	 * <p>
	 * Description: 根据transfer的内容转换Date类型的输出格式<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @return
	 * String
	 */
	public static String formatDate(Date date,String transfer){
		SimpleDateFormat sdf = new SimpleDateFormat(transfer);
		return sdf.format(date);
	}
}
