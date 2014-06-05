package com.deppon.crm.module.interfaces.ows.impl;

import java.util.Date;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.log4j.Logger;

import com.deppon.crm.module.coupon.server.manager.ICouponForInterfaceManager;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.ows.IOwsToCrmService;
import com.deppon.crm.module.interfaces.ows.domain.CommonExceptionInfo;
import com.deppon.crm.module.interfaces.ows.domain.CommonExceptionInfo_Exception;
import com.deppon.crm.module.interfaces.ows.domain.CreateNewCouponRequest;
import com.deppon.crm.module.interfaces.ows.domain.CreateNewCouponResponse;
import com.deppon.crm.module.interfaces.ows.domain.ESBHeader;
import com.deppon.foss.framework.exception.GeneralException;

public class OwsToCrmService implements IOwsToCrmService {
	
	private ICouponForInterfaceManager couponForInterfaceManager;                            
	
	private final Logger log = Logger.getLogger(OwsToCrmService.class);

	
	/***
	 * 获取优惠券信息方法
	 * @author dpadmin
	 */
	@Override
	public CreateNewCouponResponse createNewCoupon( Holder<ESBHeader> esbHeader,
			CreateNewCouponRequest createNewCouponRequest)throws CommonExceptionInfo_Exception {
		log.info("获取优惠券requestId:"+esbHeader.value.getRequestId());
		try{
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setBackServiceCode("CRM_ESB2CRM_GAIN_COUPON");
		header.setResultCode(1);
		// 转换数据对象为业务对象
		CouponForInterface couponForInterface = IntefacesTool.convertToCouponForInterface(createNewCouponRequest);
		// 调用业务获取  优惠券信息
		String couponCode = couponForInterfaceManager.createCouponForInterface(couponForInterface);
		//封装响应信息类
		CreateNewCouponResponse response = new CreateNewCouponResponse();
		response.setCouponCode(couponCode);
		return response;
		}catch(GeneralException ge){
			CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
			commonExceptionInfo.setCreatedTime(new Date());
			commonExceptionInfo.setExceptioncode("ESB5067");
			commonExceptionInfo.setExceptiontype("BusinessException");
			commonExceptionInfo.setMessage(IntefacesTool.getMessage("coupon", ge.getErrorCode()));
			commonExceptionInfo.setDetailedInfo(ge.getStackTrace().toString());
			throw new CommonExceptionInfo_Exception(IntefacesTool.getMessage("coupon", ge.getErrorCode()),commonExceptionInfo);
		}catch(Exception e){
			log.info("获取优惠券异常",e);
			CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
			commonExceptionInfo.setCreatedTime(new Date());
			commonExceptionInfo.setExceptioncode(e.getMessage());
			commonExceptionInfo.setExceptiontype("SystemException");
			commonExceptionInfo.setMessage(e.getMessage());
			commonExceptionInfo.setDetailedInfo(e.getStackTrace().toString());
			throw new CommonExceptionInfo_Exception("服务运行异常",commonExceptionInfo);
		}
	}

	public ICouponForInterfaceManager getCouponForInterfaceManager() {
		return couponForInterfaceManager;
	}

	public void setCouponForInterfaceManager(
			ICouponForInterfaceManager couponForInterfaceManager) {
		this.couponForInterfaceManager = couponForInterfaceManager;
	}

	
}
