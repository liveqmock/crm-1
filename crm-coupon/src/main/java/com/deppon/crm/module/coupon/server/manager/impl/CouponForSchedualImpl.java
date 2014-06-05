package com.deppon.crm.module.coupon.server.manager.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.coupon.server.manager.ICouponForSchedual;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.utils.CouponCellphoneUtils;
import com.deppon.crm.module.coupon.server.utils.CouponValidator;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;
import com.deppon.crm.module.coupon.shared.exception.ExceptionUtils;

/**
 * <p>
 * Description: 优惠券状态修改-定时器<br/>
 * </p> 
 * @author 钟琼
 * @version 0.1 2012-11-26
 */
public class CouponForSchedualImpl  implements ICouponForSchedual{
	//优惠券Manager接口
	private ICouponManager couponManagerImpl;	
	//短信发送接口调用
	private ISmsInfoSender smsSender;
	
	public void setCouponManagerImpl(ICouponManager couponManagerImpl) {
		this.couponManagerImpl = couponManagerImpl;
	}
	public void setSmsSender(ISmsInfoSender smsSender) {
		this.smsSender = smsSender;
	}
	/**
	 * <p>
	 * Description: 每天检查更新已启用的营销计划 状态是否过期，<br/>
	 * 	过期的把状态置为：已结束<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @return boolean
	 */
	@Override
	public boolean updateMarketPlanStatesByOverdue() {
		return couponManagerImpl.updateMarketPlanForSchedual();
	}
	/**
	 * <p>
	 * Description: 每天检查更新所有状态（不包括已过期）优惠券 状态是否过期，<br/>
	 *  过期的把状态置为：已过期<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 */
	@Override
	public boolean updateCouponStatesByOverdue() {
		return couponManagerImpl.updateCouponStatesByOverdueForSchedual();		
	}
	/**
	 * <p>
	 * Description: 向短信网关发送优惠券信息<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-27
	 * @return boolean
	 */
	@Override
	public boolean sendCouponMsg(){
		List<CouponCellphoneMsgInfo> msgs = couponManagerImpl.searchSendCouponMsg(
				MarketingCouponConstance.COUPON_MSG_SENDED_UNSEND, 0, 
				MarketingCouponConstance.SEND_COUPON_FETCHFROMMSG);
		//将短信发送实体转换成短信接口要求的实体类型
		List<SmsInformation> smsInfo = CouponCellphoneUtils.convertCouponMsgToSmsInfo(msgs);
		//发送短信成功标识
		boolean sendResult = false;
		//每次发送循环次数
		int maxCycle = msgs.size()/
				MarketingCouponConstance.SEND_COUPON_LIMITNUMBER;
		//如果短信实体取余不等于0则循环数加1
		if( (msgs.size()%
				MarketingCouponConstance.SEND_COUPON_LIMITNUMBER) >0 ){
			maxCycle++;
		}
		//调用发送短信接口发送短信
			for(int i = 0 ; i < maxCycle ; i++){
				List<SmsInformation> sendSmSInfo = null; 
				//如果短信发送不是最后一组则取每组要求的最大个数
				if( i != maxCycle-1 ){
					sendSmSInfo = smsInfo.subList(
						i*MarketingCouponConstance.SEND_COUPON_LIMITNUMBER ,
						(i+1)*MarketingCouponConstance.SEND_COUPON_LIMITNUMBER);
				}else{
					//如果短信发送是最后一组则取最大值减去前三组的剩余数量
					sendSmSInfo = smsInfo.subList(
							i*MarketingCouponConstance.SEND_COUPON_LIMITNUMBER, 
							msgs.size());
				}
				try {
					//调用发送短信接口
					sendResult = smsSender.sendSms(sendSmSInfo);
				} catch (CrmBusinessException e) {
					ExceptionUtils.createCouponException(CouponExceptionType.messageSendError);
				}
				//短信发送成功更新短信发送表中该批数据状态
				if( sendResult ){
					couponManagerImpl.updateSendCouponMsg( msgs,
							MarketingCouponConstance.PHONE_SEND_STATUS_SENDSCE );
				}
			}
		return true;
	}
	public boolean autoSendCoupon(Date startdate,Date enddate){
		//调用自动券,创建自动券以及根据自动券生成短信信息插入到短信表中的存储过程
		String flag = couponManagerImpl.callableSendMsgAuto(
			startdate,//查询开始日期
			enddate,//查询结束时间
			MarketingCouponConstance.AUTO_SEND_MSG_EMPCODE, 
		    MarketingCouponConstance.AUTO_SEND_MSG_DEPTCODE);
		//调用检查该处理标志的校验方法并返回相应结果
		//返回值为1处理成功 ,0处理失败
		return CouponValidator.checkCallableFlag(flag);
	}

	public boolean createCouponHandBackground(){
		//调用手动券创建优惠券存储过程
		String flag = couponManagerImpl.callableCreateCouponHand(MarketingCouponConstance.COUPON_SENDHAND);
		//调用检查该处理标志的校验方法并返回相应结果
		//返回值为1处理成功 ,0处理失败
		return CouponValidator.checkCallableFlag(flag);		
	}
}
