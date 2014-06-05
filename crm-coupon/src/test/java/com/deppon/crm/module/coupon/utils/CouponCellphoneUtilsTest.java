package com.deppon.crm.module.coupon.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.deppon.crm.module.coupon.server.utils.CouponCellphoneUtils;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;

public class CouponCellphoneUtilsTest {
	@Test
	public void testCleanInvalid(){
		List<CouponCellphone> phones = new ArrayList<CouponCellphone>();
		for(int i=0;i<10;i++){
			CouponCellphone ccp = new CouponCellphone();
			ccp.setCellphone(new Random().nextInt(15)+"");
			ccp.setSendStatus("0");
			ccp.setValidity("0");
			phones.add(ccp);
		}
		CouponCellphoneUtils.cleanInvalid(phones);
	}
	@Test
	public void testDeleteRepeat(){
		List<CouponCellphone> phones1 = new ArrayList<CouponCellphone>();
		CouponCellphoneUtils.deleteRepeat(phones1);
		for(int i=0;i<4;i++){
			CouponCellphone ccp = new CouponCellphone();
			ccp.setCellphone("12311112222"+i);
			ccp.setSendStatus("0");
			ccp.setValidity("0");
			phones1.add(ccp);
		}
		CouponCellphoneUtils.deleteRepeat(phones1);
		List<CouponCellphone> phones2 = new ArrayList<CouponCellphone>();
		for(int i=0;i<4;i++){
			CouponCellphone ccp = new CouponCellphone();
			ccp.setCellphone("12311112222");
			ccp.setSendStatus("0");
			ccp.setValidity("0");
			phones2.add(ccp);
		}
		CouponCellphoneUtils.deleteRepeat(phones2);
	}
	@Test
	public void testConvertCouponMsgToSmsInfo(){
		List<CouponCellphoneMsgInfo> ccmi = new ArrayList<CouponCellphoneMsgInfo>();
		for(int i=0;i<10;i++){
			CouponCellphoneMsgInfo c = new CouponCellphoneMsgInfo();
			c.setCouponNumber(2012111212+""+new Random().nextInt(10));
			c.setSended("0");
			c.setCreateTime(new Date());
			c.setPhoneNumber("12322223333");
			c.setSenderEmpCode("123222");
			c.setSendStandardDeptCode("1234563");
			c.setMsgContent("亲爱的用户,您好!");
			ccmi.add(c);
		}
		CouponCellphoneUtils.convertCouponMsgToSmsInfo(ccmi);
	}
}
