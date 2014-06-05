package com.deppon.crm.module.logmoniting.server.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.deppon.crm.module.logmoniting.shared.domain.Constant;

/**   
 * @Description:时间使用工具<br />
 * @title DateUtil.java
 * @package com.deppon.crm.module.logmoniting.server.utils 
 * @author CoCo
 * @version 0.1 2013-6-26
 */
public class DateUtil {

	/**
	 * @Description:返回时间的-- 年<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 * @param date
	 * @return int
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * @Description:返回时间的-- 月<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 * @param date
	 * @return int
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * @Description:返回时间的-- 日<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 * @param date
	 * @return int
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @Description:返回时间的-- 时<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 * @param date
	 * @return int
	 */
	public static int getHours(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * @Description:得到当前时间的前day天的时间<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 * @param date
	 * @return Date
	 */
	public static Date returnPreviousDay(Date date,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, getDay(date) - day);
		return calendar.getTime();
	}
	/**
	 * @Description:得到当前时间的前hour小时的时间<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 * @param date
	 * @return Date
	 */
	public static Date returnPreviousHour(Date date,int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, getHours(date) - hour);
		return calendar.getTime();
	}
	/**
	 * @Description:得到当前时间的前month月的时间<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 * @param date
	 * @return Date
	 */
	public static Date returnPreviousMonth(Date date,int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, getMonth(date) - (month+1));
		return calendar.getTime();
	}
	
	/**
	 * @Description:将date类型转换成Long类型<br />
	 * @author CoCo
	 * @version 0.1 2013-6-28
	 * @param date
	 * @return long
	 */
	public static long dateToLong(Date date) {
		return date.getTime();
	}
	
	/**
	 * @Description:将long类型转换成Date类型<br />
	 * @author CoCo
	 * @version 0.1 2013-6-28
	 * @param date
	 * @return long
	 */
	public static Date longToDate(long date){
		return new Date(date);
	}
	
	/**
	 * @Description:得到指定格式的时间字符串<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 * @param date
	 * @return String
	 */
	public static String getDateStringByFormat(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @Description:根据时间计算当前时间属于星期几<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 * @param date
	 * void
	 */
	public static int getDayForWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = 0;
		if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 7) {
			day = Constant.Sunday;//星期天
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 6) {
			day = Constant.Saturday;//星期六
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 5) {
			day = Constant.Friday;//星期五
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 4) {
			day = Constant.Thursday;//星期四
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 3) {
			day = Constant.Wednesday;//星期三
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 2) {
			day = Constant.Tuesday;//星期二
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 1) {
			day = Constant.Monday;//星期一
		}
		return day;
	}
}
