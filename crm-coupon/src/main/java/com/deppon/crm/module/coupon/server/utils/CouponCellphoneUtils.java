package com.deppon.crm.module.coupon.server.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
/**   
 * <p>
 * Description:手机发券工具类<br />
 * </p>
 * @title CouponCellphoneUtils.java
 * @package com.deppon.crm.module.coupon.server.util 
 * @author ZhouYuan
 * @version 0.1 2012-11-22
 */
public class CouponCellphoneUtils {
	/**
	 * <p>
	 * Description: 清除无效手机号码实现类<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param filePath
	 * @return
	 * List<CouponCellphone> 返回列表,包括合法和非法的手机号码
	 */
	public static List<CouponCellphone> cleanInvalid(List<CouponCellphone> phones) {
		for( int i = phones.size()-1 ; i >= 0; i--){
			//判断手机号码是否是以1开头的11位数字或是8位数字
			if(MarketingCouponConstance.PHONE_VALIDATE_INVALID.
			        equals(CouponValidator.checkCellphoneValidity
			        (phones.get(i).getCellphone()))){
				phones.remove(i);
			}
		}
		return phones;
	}
	/**
	 * <p>
	 * Description: 删除重复手机号码实体类 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param filePath
	 * @return
	 * List<CouponCellphone> 返回列表,包括合法和非法的手机号码
	 */

	public static List<CouponCellphone> deleteRepeat(List<CouponCellphone> phones) {
		
		//对电话号码排序
		Collections.sort(phones);
		//记录删除记录个数
		int deleted = 0;
		//设置是否为集合第一个元素,也就是下表为0(0,1) 0 表示不是集合第一个元素 1表示是集合第一个元素
		int isFirst = 0;
		//循环删除重复号码
		for(int i = phones.size()-1 ; i > 0 ; i--){
			//判断是否为集合头一个元素如果是结束循环
			if( isFirst != 1){
				i = i- deleted;
				//设置删除标识为0
				deleted = 0;
				String phone = phones.get(i).getCellphone();
					for(int j = i-1 ; j >= 0 ; j--){
						if(phone.equals(phones.get(j).getCellphone())){
							phones.remove(j);
							deleted++;
						}else{
							break;
						}
						//判断内循环是否为集合头一个元素如果是设置标记字段值供跳出外层循环
						if( j == 0){
							isFirst = 1;
						}
					}
			}else{
				break;
			}
		}
		return phones;
	}
	/**
	 * <p>
	 * Description: 将CouponCellphoneMsgInfo短信发送实体转换成短信接口
	 * 				要求的数据类型SmsInfo<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param ccmi
	 * @return
	 * List<SmsInfo> 返回列表,短信接口要求的数据类型 
	 */
	public static List<SmsInformation> convertCouponMsgToSmsInfo(List<CouponCellphoneMsgInfo> ccmi){
		//创建短信发送对象的集合
		List<SmsInformation> sms = new ArrayList<SmsInformation>();
		//遍历短信发送实体中数据
		for( CouponCellphoneMsgInfo couponCell : ccmi ){
			//新建接口要求的短信发送实体
			SmsInformation info = new SmsInformation();
			//设置接口短信发送实体的手机号
			info.setMobile( couponCell.getPhoneNumber() );
			//设置短信内容
			info.setMsgContent( couponCell.getMsgContent() );
			//设置发送部门ID
			info.setSendDept( couponCell.getSendStandardDeptCode());
			//设置发送人ID
			info.setSender( couponCell.getSenderEmpCode() );
			//设置业务类型
			info.setMsgType(MarketingCouponConstance.COUPON_MSG_MSGTYPE);
			//单号 优惠券编号
			info.setWaybillNo( couponCell.getCouponNumber());
			//将封装好的实体添加到接口短信实体集合中
			sms.add(info);
		}
		return sms;
	}
}
