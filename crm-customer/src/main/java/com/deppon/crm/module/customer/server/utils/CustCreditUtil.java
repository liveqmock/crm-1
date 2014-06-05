package com.deppon.crm.module.customer.server.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;


import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;
import com.deppon.crm.module.customer.shared.domain.CustCreditDetail;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：andy
 * @时间：2014-3-7
 * @功能：客户信用util
 * @修改：
 * @功能：客户信用util
 * @时间：2014-3-7
 * 
 */
public class CustCreditUtil {

	public static final String MAXOVERDRAFTDAY_CONTENT      = "临时欠款超过 {0} 天未还临时欠款客户";
	public static final String MONTHENDOVERTAKE_CONTENT     = "按照月结合同规定时间后 {0} 天未还款的月结客户";
	public static final String OVERDUEMONTH_CONTENT         = "逾期 {0} 天未还款的客户";
	public static final String EARLIESTDAY_CONTENT          = "最长一笔临欠达到 {0} 天（含）的临时欠款客户";
	public static final String BEFOREPAYMENTDATEDAY_CONTENT = "距离规定结款日期前 {0} 天未还款的月结客户";
	
	public static final String BEFOREMONTHPAYMENTDAY_CONTENT = "最长还款周期（月结天数）到期前{0}天未还款的月结客户";
	public static final String USEDCREDITRATE_CONTENT        = "信用额度使用率达到{0}%的月结客户";
	public static final String DEPTUSEDRATE_CONTENT          = "营业部临时欠款总额使用率达到{0}%";
	
	public static final String EARLIESTDAY_DOTO_CONTENT           = "客户编码{0}（客户名称：{1}），最长一笔临欠已超过 {2} 天（含），请及时提醒客户还款。";
	public static final String BEFOREPAYMENTDATEDAY_DOTO_CONTENT  = "客户编码{0}（客户名称：{1}），即将到结款日，请及时提醒客户还款。";
	public static final String BEFOREMONTHPAYMENTDAY_DOTO_CONTENT = "客户编码{0}（客户名称：{1}）， 即将无法开单月结，请及时提醒客户还款。";
	public static final String USEDCREDITRATE_DOTO_CONTENT        = "客户编码{0}（客户名称：{1}），信用额度使用率达到 {2}%的月结客户，请及时提醒客户还款。";
	public static final String DEPTUSEDRATE_DOTO_CONTENT          = "贵部临时欠款总额使用率达到{0}%，请及时做好应收工作。";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 
	 * <p>
	 * 获取前  month 个月1号日期<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getBeginDate(int month, int day) {
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.set(Calendar.MONTH, ca.get(Calendar.MONDAY)-month);
		ca.set(Calendar.DATE, day);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * 获取上个月最后一天日期<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getEndDate() {
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.set(Calendar.DAY_OF_MONTH, 0);
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.MILLISECOND, 59);
		return ca.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * 获取本月15号日期<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getHalfMonthDate(int day) {
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.set(Calendar.DATE, day);
		return ca.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * 获取当前是几号<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return int
	 * 
	 */
	public static int getDay() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		return cal.get(Calendar.DATE); 
	}
	
	/**
	 * 
	 * <p>
	 * 获取指定日期是几号<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return int
	 * 
	 */
	public static int getDayForStr(String dateString, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateString);
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.setTime(date);
		return ca.get(Calendar.DATE);
	}
	
	/**
	 * 
	 * <p>
	 * 获取前  month 个月day号日期<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getBeginDate(String dateString, String format, int month, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateString);
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.setTime(date);
		
		ca.set(Calendar.MONTH, ca.get(Calendar.MONDAY)-month);
		ca.set(Calendar.DATE, day);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * 获取上个月最后一天日期<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getEndDate(String dateString, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateString);
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.setTime(date);
		
		ca.set(Calendar.DAY_OF_MONTH, 0);
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.MILLISECOND, 59);
		return ca.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * 获取dateString day号日期<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getHalfMonthDate(String dateString, String format, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateString);
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.setTime(date);
		
		ca.set(Calendar.DATE, day);
		return ca.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * 获取当前日期<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getNowDate() {
		return new Date();
	}
	
	/**
	 * 
	 * <p>
	 * 日期格式转换<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return String
	 * 
	 */
	public static String dateFormat(String format, 
			Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 
	 * <p>
	 * 计算两个日期之间相差的天数<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return int
	 * 
	 */
	public static int daysBetween(Date smdate, 
								Date bdate) throws ParseException {    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        smdate = sdf.parse(sdf.format(smdate));  
        bdate  = sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long sTime = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long bTime = cal.getTimeInMillis();         
        long betweenDays = (bTime-sTime)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(betweenDays));           
    }
	
	/**
	 * 
	 * <p>
	 * 日期 date 加上 month 个月
	 * 指定具体的日期 day
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return Date
	 * 
	 */
	public static Date getAddMonthDate(Date date, int month, int day) {
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.setTime(date);
		ca.add(Calendar.MONTH, month);
		if(day > 0) {
			ca.set(Calendar.DATE, day);
		}
		return ca.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * 分批操作，每1000/次
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return List<List<CustCredit>>
	 * 
	 */
	public static List<List<CustCredit>> batchList(List<CustCredit> list) {
		int len = (list.size() + 999) / 1000;
        List<List<CustCredit>> strList = new ArrayList<List<CustCredit>>();
        for (int i = 0; i < len; i++) {
        	strList.add(new ArrayList<CustCredit>());
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < 1000; j++) {
            	strList.get(i).add(list.get((i * 1000) + j));
            }
        }
        for (int i = 0; i < list.size() % 1000; i++) {
        	strList.get(len - 1).add(list.get(((len - 1) * 1000) + i));
        }
        return strList;
	}
	
	/**
	 * 
	 * @Title: changeCustCredit
	 *  <p>
	 * @Description: 将CustCreditConfig转换成CustCreditDetail<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-4-1
	 * @return List<CustCreditDetail>
	 * @throws
	 */
	public static List<CustCreditDetail> changeCustCredit(CustCreditConfig custCreditConfig){
		List<CustCreditDetail> custCreditInfo = new ArrayList<CustCreditDetail>();
		Field[] fields =  CustCreditConfig.class.getDeclaredFields();
		Field[] fields1 = BaseEntity.class.getDeclaredFields();
		//定义序号
		int rank=0;
		for(int i =0;i < fields.length;i++){
			//去掉serialVersionUID
			if ("serialVersionUID".equals(fields[i].getName())) {
				continue;
			}
			//去掉不需要的字段
			for (int j = 0; j < fields1.length; j++) {
				if (fields[i].getName().equals(fields1[j].getName())) {
					continue;
				}
			}
			try {
				//根据对象的属性拼装get方法拿值
				Method method = CustCreditConfig.class.getMethod("get"
						+ fields[i].getName().substring(0, 1).toUpperCase()
						+ fields[i].getName().substring(1,
								fields[i].getName().length()));
				rank++;
				CustCreditDetail custCreditDetail = new CustCreditDetail();
				custCreditDetail.setActValue(String.valueOf(method.invoke(custCreditConfig)));
				custCreditDetail.setRankNumber(rank);
				custCreditInfo.add(custCreditDetail);
			} catch (Exception e) {
			}
		}
		return custCreditInfo;
	}
	
	/**
	 * 
	 * <p>
	 * 分割字符串
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return ArrayList
	 * 
	 */
	public static List<String> TokenizerString(String str, String dim) {
		return TokenizerString(str, dim, false);
    }
	
	public static List<String> TokenizerString(String str, String dim, boolean returndim) {
		str = nullToString(str);
        dim = nullToString(dim);
        List<String> strlist = new ArrayList<String>();
        StringTokenizer strtoken = new StringTokenizer(str, dim, returndim);
        while (strtoken.hasMoreTokens()) {
        	strlist.add(strtoken.nextToken());
        }
        return strlist;
    }
	
	public static String nullToString(String s) {
        return s == null ? "" : s;
    }
	
	/** 
     * 提供精确的乘法运算
     * @param v1 被乘数 
     * @param v2 乘数 
     * @return 两个参数的积 
     * @author andy
     * @version 0.1 2014-4-17
     * @return double
     * @author andy
     * @version 0.1 2014-4-17
     * @return double
     * 
     */
    public static double getMultiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /** 
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 
     * 定精度，以后的数字四舍五入
     * @param v1 被除数 
     * @param v2 除数 
     * @param scale 表示表示需要精确到小数点以后几位。 
     * @return 两个参数的商 
     * @author andy
     * @version 0.1 2014-4-17
     * @return double
     * 
     */
    public static double getDivide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	
}
