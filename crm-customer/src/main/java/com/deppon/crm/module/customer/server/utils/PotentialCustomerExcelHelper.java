package com.deppon.crm.module.customer.server.utils;

import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.exception.ExcelImportException;
import com.deppon.crm.module.customer.shared.exception.ExcelImportExceptionType;
import com.deppon.foss.framework.server.web.message.IMessageBundle;

public class PotentialCustomerExcelHelper {
	
	public static final String rule_String ="string";
	public static final String rule_DD ="data dictionary";
	
	public static final String head_1 = "客户名称";
	public static final String head_2 = "行业";
	public static final String head_3 = "客户来源";
	public static final String head_4 = "联系人姓名";
	public static final String head_5 = "联系人性别";
	public static final String head_6 = "联系人手机";
	public static final String head_7 = "联系人电话";
	public static final String head_8 = "职位";
	public static final String head_9 = "客户类型";
	public static final String head_10 = "客户类别";
	public static final String head_11 = "客户属性";
	public static final String head_12 = "最近合作物流公司";
	public static final String head_13 = "合作意向";
	public static final String head_14 = "货量潜力";
	public static final String head_15 = "客户需求";
	public static final String head_16 = "客户地址";
	//潜客模板头
	protected static final String[] heads = {head_1,head_2,head_3,head_4,head_5,head_6,head_7,head_8,head_9,head_10,head_11,head_12,head_13,head_14,head_15,head_16};
	
	public IMessageBundle messageBundle;
	
	
	/**
	 * 
	 * <p>
	 * 模板头验证<br />
	 * </p>
	 * @author bxj
	 * @version 0.1 2012-3-8
	 * @param headList
	 * void
	 */
	public static boolean headValidate(String[] headers){
		int i=0;
		if(headers.length != heads.length){
			throw new ExcelImportException(ExcelImportExceptionType.HeadSizeError.getErrCode(),new Object[]{heads.length,headers.length});
		}
		for (String head : heads) {
			if(!head.equals(headers[i++])){
				throw new ExcelImportException(ExcelImportExceptionType.HeadValueError.getErrCode(),new Object[]{i,head,headers[i-1]});
			}
		}
		return true;
	}
	
	/**
	 * 
	 * <p>
	 * 根据潜客导入规则，获得潜客信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.1 2012-3-8
	 * @param dataList
	 * @return
	 * PotentialCustomer
	 * @throws ExcelImportValidateException 
	 */
	public static PotentialCustomer getPotenCustomerByPotenRule(Object[] datas){
		PotentialCustomer pc = new PotentialCustomer();
		
		int i=0;
		String name = getStringTypeValue(datas[i++],head_1);
		pc.setCustName(name);
		
		String tradeValue = getStringTypeValue(datas[i++],head_2);
		String trade = DataDictionaryUtil.getCode(DataHeadTypeEnum.TRADE, tradeValue);
		pc.setTrade(trade);
		
		String custbaseValue = getStringTypeValue(datas[i++],head_3);
		String custbase =DataDictionaryUtil.getCode(DataHeadTypeEnum.CUST_SOURCE, custbaseValue);
		pc.setCustbase(custbase);
		
		String linkManName = getStringTypeValue(datas[i++],head_4);
		pc.setLinkManName(linkManName);
		
		String sexValue = getStringTypeValue(datas[i++],head_5);
		String sex =DataDictionaryUtil.getCode(DataHeadTypeEnum.GENDER, sexValue);
		pc.setSex(sex);
		
		String linkManMobile = getStringTypeValue(datas[i++],head_6);
		pc.setLinkManMobile(linkManMobile);
		
		String linkManPhone = getStringTypeValue(datas[i++],head_7);
		pc.setLinkManPhone(linkManPhone);
		
		String post = getStringTypeValue(datas[i++],head_8);
		pc.setPost(post);
		
		String customerTypeValue = getStringTypeValue(datas[i++],head_9);
		String customerType = DataDictionaryUtil.getCode(DataHeadTypeEnum.CUSTOMER_TYPE, customerTypeValue);
		pc.setCustomerType(customerType);
		
		//TODO:客户类别
		String custCategoryValue = getStringTypeValue(datas[i++],head_10);
		String custCategory = DataDictionaryUtil.getCode(DataHeadTypeEnum.CUST_CATEGORY, custCategoryValue);
		pc.setCustCategory(custCategory);
		
		String custNatrueValue = getStringTypeValue(datas[i++],head_11);
		String custNatrue = DataDictionaryUtil.getCode(DataHeadTypeEnum.CUSTOMER_NATURE, custNatrueValue);
		pc.setCustNature(custNatrue);
		
		String recentcoopValue = getStringTypeValue(datas[i++],head_12);
		String recentcoop = DataDictionaryUtil.getCode(DataHeadTypeEnum.COOPERATION_LOGISTICS, recentcoopValue);
		pc.setRecentcoop(recentcoop);
		
		String coopIntentionValue = getStringTypeValue(datas[i++],head_13);
		String coopIntention =  DataDictionaryUtil.getCode(DataHeadTypeEnum.COOPERATION_INTENTION, coopIntentionValue);
		pc.setCoopIntention(coopIntention);
		
		String volumePotentialValue = getStringTypeValue(datas[i++],head_14);
		String volumePotential =  DataDictionaryUtil.getCode(DataHeadTypeEnum.CARGO_POTENTIAL, volumePotentialValue);
		pc.setVolumePotential(volumePotential);
		
		String custNeed = getStringTypeValue(datas[i++],head_15);
		pc.setCustNeed(custNeed);
		
		String custAddress = getStringTypeValue(datas[i],head_16);
		pc.setAddress(custAddress);
		
		return pc;
	}
	/**
	 * 
	 * <p>
	 * 得到字符串类型的值<br />
	 * </p>
	 * @author bxj
	 * @version 0.1 2012-3-11
	 * @param obj 被转换的对象
	 * @param headName excel表头信息
	 * @return
	 * @throws ExcelImportValidateException 如果不是String类型的，抛出类型不一致异常。
	 * String
	 */
	public static String getStringTypeValue(Object obj,String headName) throws ExcelImportException{
		try {
			return(String)obj;
		} catch (ClassCastException e) {
			throw new ExcelImportException(ExcelImportExceptionType.TypeValueError.getErrCode(),new Object[]{headName,obj.getClass().getSimpleName()});
		}
	}

}
