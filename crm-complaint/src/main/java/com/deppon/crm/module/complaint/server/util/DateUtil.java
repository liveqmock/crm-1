package com.deppon.crm.module.complaint.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 功能说明： 日期处理类,引用于OMS系统,其中getDate和getDateOfWeek是PMS系统新增的公用方法.
 * @author：OMS
 * @version：N/A
 */
public class DateUtil {

	/**
	 * fromat for yyyy-MM-dd
	 * 日期格式
	 */
	public static final String DATE_YYYYMMDD = "yyyy-MM-dd";
	/**
	 * fromat for yyyyMMdd
	 * 日期格式
	 */
	public static final String DATE_YYMMDD_NOLINK = "yyyyMMdd";
	/**
	 * fromat for yyyy-MM-dd HH:mm:ss
	 * 日期格式
	 */
	public static final String DATETIME_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * fromat for yyyyMMddHHmmssSS
	 * 日期格式
	 */
	public static final String DATETIME_YYYYMMDD_HHMMSS_NOLINK = "yyyyMMddHHmmssSS";
	/**
	 * fromat for yyyy-MM-dd HH:mm
	 * 日期格式
	 */
	public static final String DATETIME_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
	/**
	 * fromat for HH:mm:ss
	 * 日期格式
	 */
	public static final String TIME_HHMMSS = "HH:mm:ss";
	// 一天的毫秒数
	public static final long ONE_DAY_MILLIS = 24 * 60 * 60 * (long)1000;
	/**
	 * fromat for yyyy-MM-dd
	 * 获取当前时间
	 */
	public static Date getCurrentDate(String format){
		// 当前时间
		Date currentDate = null;
		// 系统现在时间毫秒
		Calendar cal = Calendar.getInstance();
		// 格式化
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		// 转换格式
		String dateStr = formatter.format(cal.getTime());
		try {
			// 转换格式
			currentDate = formatter.parse(dateStr);
		} catch (ParseException e) {
			// 转换异常
		}
		// 结果返回
		return currentDate;
	}
	/**
	 * fromat for DATE_YYYYMMDD
	 * 获取当前时间
	 */
	public static Date getCurrentDate() {
		// 当前时间
		Date currentDate = null;
		// 系统现在时间毫秒
		Calendar cal = Calendar.getInstance();
		// 格式化
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_YYYYMMDD);
		// 转换格式
		String dateStr = formatter.format(cal.getTime());
		try {
			// 转换格式
			currentDate = formatter.parse(dateStr);
		} catch (ParseException e) {
			// 转换异常
		}
		// 结果返回
		return currentDate;
	}
	/**
	 * fromat for 
	 * 获取当前时间
	 */
	public static String getCurrentDateTime() {
		// 系统现在时间毫秒
		Calendar cal = Calendar.getInstance();
		// 格式化
		SimpleDateFormat formatter = new SimpleDateFormat(
				DATETIME_YYYYMMDD_HHMMSS);
		// 结果返回
		return formatter.format(cal.getTime());
	}
	/**
	 * 获得当前不需要格式链接的日期时间
	 * @return
	 */
	public static String getCurrentDateTimeNoLink() {
		// 系统现在时间毫秒
		Calendar cal = Calendar.getInstance();
		// 格式化
		SimpleDateFormat formatter = new SimpleDateFormat(
				DATETIME_YYYYMMDD_HHMMSS_NOLINK);
		// 结果返回
		return formatter.format(cal.getTime());
	}
	/**
	 * fromat for 
	 * 获取时间
	 */
	public static String getDate(Date date) {
		// 如果时间参数不为空
		if (date != null) {
			// 格式化
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_YYYYMMDD);
			// 结果返回
			return formatter.format(date);
		}
		// 结果返回
		return null;
	}

	/**
	 * fromat for 
	 * 获取时间
	 */
	public static String getDate(Date date,String format){
		// 如果时间参数不为空
		if (date != null) {
			// 格式化
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			// 结果返回
			return formatter.format(date);
		}
		// 结果返回
		return null;
	}
	/**
	 * fromat for 
	 * 获取时间
	 */
	public static String getTime(Date date) {
		// 如果时间参数不为空
		if (date != null) {
			// 格式化
			SimpleDateFormat formatter = new SimpleDateFormat(TIME_HHMMSS);
			// 结果返回
			return formatter.format(date);
		}
		// 结果返回
		return null;
	}
	
	/**
	 * 时间区间内天数, 开始日期必须小于结束日期
	 * @param startDate 不能为null, 包含起始日期
	 * @param endDate 不能为null, 包含结束日期
	 * @return
	 */
	public static int getNumberOfDays(Date startDate, Date endDate){
		// 如果时间参数为空
		if(startDate == null){
			// 异常抛出
			throw new IllegalArgumentException(" startDate can not be null");
		}
		// 如果时间参数为空
		if(endDate == null){
			// 异常抛出
			throw new IllegalArgumentException(" endDate can not be null");
		}
		//天数
		int numberOfDays = 0;
		numberOfDays = (int)((endDate.getTime() - startDate.getTime()) / ONE_DAY_MILLIS) + 1;
		// 结果返回
		return numberOfDays;
	}
	
	/**
	 * 从字符转换成日期格式
	 * @param strDate
	 * @param format 本类中有几个默认的format可以调用
	 * @return
	 * @throws ParseException 
	 * @see YYYYMMDDHHMMSS_FORMAT,YYYYMMDD_FORMAT,YYYYMM_FORMAT
	 */
	public static Date parse(String strDate, String format) {
		try {
			// 格式化
			return new SimpleDateFormat(format).parse(strDate);
		} catch (ParseException e) {
			// 异常抛出
			throw new IllegalArgumentException(e);
		}
	}
	
	
	
	/**
	 * 从字符转换成日期格式
	 * @param strDate
	 * @param format 本类中有几个默认的format可以调用
	 * @return
	 * @throws ParseException 
	 * @see YYYYMMDDHHMMSS_FORMAT,YYYYMMDD_FORMAT,YYYYMM_FORMAT
	 */
	public static String format(Date date, String format){
		// 格式化
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 把日期转换成当天的最后时间， 即取到当天参数之前的日期
	 * @param date
	 * @return
	 */
	public static java.util.Date parse2EndDate(java.util.Date date) {
		// 如果时间参数为空
		if (date == null){
			// 结果返回
			return null;
		}
		// 系统现在时间毫秒
		Calendar cal = Calendar.getInstance();
		// Date转换为calendar
		cal.setTime(date);
		// 小时数
		cal.set(Calendar.HOUR_OF_DAY, 23);
		// 分钟
		cal.set(Calendar.MINUTE, 59);
		// 秒数
		cal.set(Calendar.SECOND, 59);
		// 毫秒
		cal.set(Calendar.MILLISECOND, 999);
		// 结果返回
		return cal.getTime();
	}

	/**
	 * 得到下一个星期几的日期
	 * @param dayOfWeek 1到7代表星期一到星期日
	 * @return
	 */
	public static Date getNextDayOfWeekDate(int dayOfWeek){
		// 如果参数为  0 到 8 之间
		if(dayOfWeek<8&&dayOfWeek>0){
			// 结果返回
			dayOfWeek++;
		}else{
			// 结果返回
			return new Date();
		}
		// 如果参数为  == 8
		if(dayOfWeek==8){
			// 结果返回
			dayOfWeek = 1;
		}
		// 系统现在时间
		Date currentDate = new Date();
		// 系统现在时间毫秒
		Calendar calendar = Calendar.getInstance();
		// 一周的第一天
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		// Date转换为calendar
		calendar.setTime(currentDate);
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		// 结果返回
		return calendar.getTime();
	}
	
	/**
     * @see 得到传入日期n天后的日期,如果传入日期为null，则表示当前日期n天后的日期
     * 
     * @author SQJ(Kira.Sun)
     * @param Date dt
     * @param days 可以为任何整数，负数表示前days天，正数表示后days天
     * @return Date
     */
    public static Date getAddDayDate(Date dt, int days) {
    	// 如果时间参数为空
        if (dt == null){
        	// 结果返回
        	 dt = new Date(System.currentTimeMillis());
        }
        // 系统现在时间毫秒
        Calendar cal = Calendar.getInstance();
        // Date转换为calendar
        cal.setTime(dt);
    	// 天数
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + days);
        // 小时数
        cal.set(Calendar.HOUR_OF_DAY, 0);
		// 分钟
        cal.set(Calendar.MINUTE, 0);
        // 秒数
        cal.set(Calendar.SECOND, 0);
        // 毫秒
        cal.set(Calendar.MILLISECOND, 0);
        // 结果返回
        return cal.getTime();
    }
    
    /**
     * 
     * @version 2010-11-18 下午05:30:23
     * @author  SQJ(Kira.Sun)
     * @see		计算2个时间的 时间间隔  
     * @param      {引入参数名}   {引入参数说明}
     * @return      boolean true 则d1时间晚，false则d2时间晚
     * @exception   {说明在某情况下,将发生什么异常}
     */
    public static boolean computeInterval(Date d1,Date d2){
    	// 大于
    	if((d1.getTime() - d2.getTime())>0){
    		// 返回true
    		return true;
    		// 小于
    	}else if((d1.getTime() - d2.getTime())<0){
    		// 返回false
    		return false;
    	}	
    	return true;
    }
	
    
    /**
	 * 
	 * @version 2010-11-18 下午03:44:22
	 * @author SQJ(Kira.Sun)
	 * @see 根据类型得到某个周期第一天   
	 * @param type:1 年 2 半年 3季度 4月 5旬 6周 7日   model:1当前周期 2上个周期
	 * @return Date {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	public static  Date getFirstDayOfCycle(int type,int model) {
		// 现在时间
		Date date = new Date();
		// 系统当前时间毫秒
		Calendar calendar = Calendar.getInstance();
		//年
		int year = calendar.get(Calendar.YEAR);
		// 月
		int month = calendar.get(Calendar.MONTH);
		// 天
		int day = calendar.get(Calendar.DATE);
		//  如果选择第一种类型
		if(model==1){
			//第一种比较时间方法
			date=computeDayByType(date,calendar,type,year,month,day);
		//  如果选择第二种类型
		}else{
			//第二种比较时间方法
			date=computeDayByType2(date,calendar,type,year,month,day);
		}
		// 时间返回
		return date;
	}
	
	
	/**
	 * 
	 * @version 2010-11-18 下午03:44:22
	 * @author SQJ(Kira.Sun)
	 * @see 根据类型得到某个周期第一天   
	 * @param type:1 年 2 半年 3季度 4月 5旬 6周 7日   model:1当前周期 2上个周期 3下个周期
	 * @return Date {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	public static  Date getFirstDayOfCycle(Calendar calendar,int type,int model) {
		// 现在时间
		Date date = new Date();
		// 年
		int year = calendar.get(Calendar.YEAR);
		// 月
		int month = calendar.get(Calendar.MONTH);
		// 日
		int day = calendar.get(Calendar.DATE);
		//  如果选择第一种类型
		if(model==1){
			//第一种比较时间方法
			date=computeDayByType(date,calendar,type,year,month,day);
		}else if(model==2){
		//  如果选择第二种类型
			date=computeDayByType2(date,calendar,type,year,month,day);
		}else{
		//  如果选择第其他类型
			date=computeDayByType3(date,calendar,type,year,month,day);
		}
		// 结果返回
		return date;
	}

	/**
	 * 
	 * @version 2010-11-18 下午03:44:22
	 * @author SQJ(Kira.Sun)
	 * @see 根据类型得到某个周期第一天   
	 * @param type:1 年 2 半年 3季度 4月 5旬 6周 7日   model:1当前周期 2上个周期
	 * @return Date {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	public static  Date getFirstDayOfCycle(int type,int model,Date date) {
		// 系统当前市
		Calendar calendar=Calendar.getInstance(); 
		// 将Date类型转换为Calendar
		calendar.setTime(date); 
		// 年
		int year = calendar.get(Calendar.YEAR);
		//  月
		int month = calendar.get(Calendar.MONTH);
		// 日
		int day = calendar.get(Calendar.DATE);
		//  如果选择第一种类型
		if(model==1){
			//第一种比较时间方法
			date=computeDayByType(date,calendar,type,year,month,day);
		//  如果选择第二种类型
		}else{
			//第二种比较时间方法
			date=computeDayByType2(date,calendar,type,year,month,day);
		}
		// 结果返回
		return date;
	}
	
	
	
	/**
	 * 
	 * @version 2010-11-19 下午01:58:12
	 * @author  SQJ(Kira.Sun)
	 * @see		{方法的功能/动作描述}
	 * @param      {引入参数名}   {引入参数说明}
	 * @return      Date {返回参数说明}
	 * @exception   {说明在某情况下,将发生什么异常}
	 */
	private static Date computeDayByType(Date date, Calendar calendar, int type, int year,
			int month, int day) {
		//字符格式
		String strDate = "";
		switch (type) {
		// 第一种类型
		case 1:
			//字符格式 -01-01
			strDate = String.valueOf(year) + "-01-01";
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
		case 2:
			// 第二种类型
			if (month <= 5) {
				//字符格式 -01-01
				strDate = String.valueOf(year) + "-01-01";
			} else {
				//字符格式 -07-01
				strDate = String.valueOf(year) + "-07-01";
			}
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
			// 第三种类型
		case 3:
			if (month <= 2) {
				//字符格式 -01-01
				strDate = String.valueOf(year) + "-01-01";
			} else if (month <= 5) {
				//字符格式 -04-01
				strDate = String.valueOf(year) + "-04-01";
				//字符格式 -07-01
			} else if (month <= 8) {
				strDate = String.valueOf(year) + "-07-01";
			} else if (month <= 11) {
				//字符格式 -10-01
				strDate = String.valueOf(year) + "-10-01";
			}
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
			// 第四种类型
		case 4:
			// 天数 1
			calendar.set(Calendar.DATE, 1);
			// calendar 转换为 Date
			date = calendar.getTime();
			break;
			// 第五种类型
		case 5:
			// 转换为 String类型
			String monthNo = String.valueOf(month);
			if (month <= 8) {
				// 日期格式转换
				monthNo = "0" + monthNo;
			}
			if (day <= 10) {
				//字符格式 -01
				strDate = String.valueOf(year) + "-" + monthNo + "-01";
			} else if (day <= 20) {
				//字符格式 -11
				strDate = String.valueOf(year) + "-" + monthNo + "-11";
			} else if (day <= 31) {
				//字符格式 -21
				strDate = String.valueOf(year) + "-" + monthNo + "-21";
			}
			break;
			// 第六种类型
		case 6:
/*			int n = calendar.get(Calendar.DAY_OF_WEEK);
			calendar.roll(Calendar.DATE, -n);
			date = calendar.getTime();*/
			break;
			// 第七种类型
		case 7:
			// calendar 转换为 Date
			date = calendar.getTime();
			break;
			// 默认类型
		default:
			// 时间为空
			calendar = null;
			break;
		}
		// 结果返回
		return date;
	}
	
	
	/**
	 * 
	 * @version 2010-11-19 下午01:58:12
	 * @author  SQJ(Kira.Sun)
	 * @see		{方法的功能/动作描述}
	 * @param      {引入参数名}   {引入参数说明}
	 * @return      Date {返回参数说明}
	 * @exception   {说明在某情况下,将发生什么异常}
	 */
	private static Date computeDayByType2(Date date, Calendar calendar, int type, int year,
			int month, int day) {
		//字符格式
		String strDate = "";
		switch (type) {
		// 第一种类型
		case 1:
			//字符格式 -01-01
			strDate = String.valueOf(year-1) + "-01-01";
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
		case 2:
			if (month <= 5) {
				//字符格式 -07-01
				strDate = String.valueOf(year-1) + "-07-01";
			} else {
				//字符格式 -01-01
				strDate = String.valueOf(year) + "-01-01";
			}
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
		case 3:
			if (month <= 2) {
				//字符格式 -10-01
				strDate = String.valueOf(year-1) + "-10-01";
			} else if (month <= 5) {
				//字符格式 -01-01
				strDate = String.valueOf(year) + "-01-01";
			} else if (month <= 8) {
				//字符格式 -04-01
				strDate = String.valueOf(year) + "-04-01";
			} else if (month <= 11) {
				//字符格式 -07-01
				strDate = String.valueOf(year) + "-07-01";
			}
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
		case 4:
			// 天数 1
			calendar.set(Calendar.DATE, 1);
			if(month==0){
				// 月份 11
				calendar.set(Calendar.MONTH, 11);
				// 年份-1
				calendar.set(Calendar.YEAR, year-1);
			}else{
				// 月份-1
				calendar.set(Calendar.MONTH, month-1);
			}
			// calendar 转换为 Date
			date = calendar.getTime();
			break;
		case 5:
			// 转换为 String类型
			String monthNo = String.valueOf(month);
			if (month <= 8) {
				// 日期格式转换
				monthNo = "0" + monthNo;
			}
			// 如果天数 小于 10
			if (day <= 10) {
				// 如果月份等于 0 
				if(month==0){
					// 年份-1
					year=year-1;
					// 月份=11
					month=11;
				}
				//字符格式 -21
				strDate = String.valueOf(year) + "-" + month + "-21";
			} else if (day <= 20) {
				//字符格式 -01
				strDate = String.valueOf(year) + "-" + monthNo + "-01";
			} else if (day <= 31) {
				//字符格式 -11
				strDate = String.valueOf(year) + "-" + monthNo + "-11";
			}
			break;
		case 6:
/*			int n = calendar.get(Calendar.DAY_OF_WEEK);
			calendar.roll(Calendar.DATE, -n);
			date = calendar.getTime();*/
			break;
		case 7:
			// calendar 转换为 Date
			date = calendar.getTime();
			break;
		default:
			// 时间为空
			calendar = null;
			break;
		}
		// 时间返回
		return date;
	}
	
	
	
	/**
	 * 
	 * @version 2010-11-19 下午01:58:12
	 * @author  SQJ(Kira.Sun)
	 * @see		{方法的功能/动作描述}
	 * @param      {引入参数名}   {引入参数说明}
	 * @return      Date {返回参数说明}
	 * @exception   {说明在某情况下,将发生什么异常}
	 */
	private static Date computeDayByType3(Date date, Calendar calendar, int type, int year,
			int month, int day) {
		//字符格式
		String strDate = "";
		switch (type) {
		// 第一种类型
		case 1:
			//字符格式 -01-01
			strDate = String.valueOf(year+1) + "-01-01";
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
			// 第二种类型	
		case 2:
			if (month <= 5) {
				//字符格式 -07-01
				strDate = String.valueOf(year) + "-07-01";
			} else {
				//字符格式 -01-01
				strDate = String.valueOf(year+1) + "-01-01";
			}
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
			// 第三种类型
		case 3:
			// 如果月份小于等于2
			if (month <= 2) {
				//字符格式 -04-01
				strDate = String.valueOf(year) + "-04-01";
				// 如果月份小于等于5
			} else if (month <= 5) {
				//字符格式 -07-01
				strDate = String.valueOf(year) + "-07-01";
				// 如果月份小于等于8
			} else if (month <= 8) {
				//字符格式 -010-01
				strDate = String.valueOf(year) + "-010-01";
				// 如果月份小于等于11
			} else if (month <= 11) {
				//字符格式 -01-01
				strDate = String.valueOf(year+1) + "-01-01";
			}
			//从字符转换成日期格式
			date = DateUtil.parse(strDate, "yyyy-MM-dd");
			break;
			// 第四种类型
		case 4:
			// 如果月份等于11
			if(month==11){
				// 月份 1
				calendar.set(Calendar.MONTH, 1);
				// 年份 +1
				calendar.set(Calendar.YEAR, year+1);
			}else{
				// 月 份 +1
				calendar.set(Calendar.MONTH, month+1);
			}
			// 天数 1
			calendar.set(Calendar.DATE, 1);
			// calendar 转换为 Date
			date = calendar.getTime();
			break;
			// 第五种类型
		case 5:
			// 转换为 String类型
			String monthNo = String.valueOf(month);
			// 如果月份小于等于8
			if (month <= 8) {
				// 日期格式转换
				monthNo = "0" + monthNo;
			}
			// 如果天数小于等于10
			if (day <= 10) {
				//字符格式 -11
				strDate = String.valueOf(year) + "-" + monthNo + "-11";
				// 如果天数小于等于20
			} else if (day <= 20) {
				//字符格式 -21
				strDate = String.valueOf(year) + "-" + monthNo + "-21";
				// 如果天数小于等于31
			} else if (day <= 31) {
				// 如果月份等于11
				if(month==11){
					monthNo="01";
				}else{
					month=month+1;
				}
				// 如果月份小于等于8
				if (month <= 8) {
					monthNo = "0" + monthNo;
				}else{
					monthNo = String.valueOf(month);
				}
				//字符格式 -01
				strDate = String.valueOf(year) + "-" + monthNo + "-01";
			}
			break;
			// 第六种类型
		case 6:
/*			int n = calendar.get(Calendar.DAY_OF_WEEK);
			calendar.roll(Calendar.DATE, -n);
			date = calendar.getTime();*/
			break;
			// 第七种类型
		case 7:
			date = calendar.getTime();
			break;
			// 默认类型
		default:
			// 时间为空
			calendar = null;
			break;
		}
		// 时间返回
		return date;
	}
	
	/**  
     * 将CST的时间字符串转换成需要的日期格式字符串<br>  
     *   
     * @param cststr  
     *            The source to be dealed with. <br>  
     *            (exp:Fri Jan 02 00:00:00 CST 2009)  
     * @param fmt  
     *            The format string  
     * @return string or <code>null</code> if the cststr is unpasrseable or is  
     *         null return null,else return the string.  
     * @author Kira.Sun  
     */  
    public static String getDateFmtStrFromCST(String cststr, String fmt) {   
    	// 参数为空
        if ((null == cststr) || (null == fmt)) {   
        	// 返回空
            return null;   
        }   
        String str = null; 
        // 格式1   fmt
        SimpleDateFormat sdfy = new SimpleDateFormat(fmt.trim());   
     // 格式2  EEE MMM dd HH:mm:ss 'CST' yyyy
        SimpleDateFormat sdf = new SimpleDateFormat(   
                "EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);   
        try {   
        	// 格式化
            str = sdfy.format(sdf.parse(cststr.trim()));   
            // 捕捉转换异常
        } catch (ParseException e) {   
            return null;   
        }   
        return str;   
    }

    /**
  　　 * 获取当前日期是星期几<br>
  　　 * @author James.Ou
  　　 * @param N/A
  　　 * @return 当前日期是星期几
  　　 */
	public static String getWeekOfDate() {
	// 系统当前时间
	Date dt=new Date();
	// 星期
	String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	// 现在时间毫秒
	Calendar cal = Calendar.getInstance();
	// Date转换为 Calendar
	cal.setTime(dt);
	// 天数 -1
	int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	// 如果天数 小于0
	if (w < 0){
		// 置为0
		w = 0;
	}
	// 返回星期数
	return weekDays[w];
	}
    /**
  　　 * 获取当前日期和星期的組合<br>
  　　 * @author James.Ou
  　　 * @param dt
  　　 * @return 当前日期.e.g:2011-08-11
  　　 */
	public static String getDate() {
		// 系统当前时间
		Date date=new Date();
		//fromat for 获取时间
    	return getDate(date);
	}

/*    public static void main(String[] args) {
    System.out.println(getCurrentDateTimeNoLink());
    	Date date=getFirstDayOfCycle(4, 2);
    	String str=format(date, "yyyy-MM-dd");
    	System.out.println(str);
	}*/
}
