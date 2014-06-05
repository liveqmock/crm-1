package com.deppon.crm.module.client.common.util;    

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
   
/**    
 * @author liuchengwu</a>   
 * @version 1.0    
 */

public class DateUtils {

	public static final long ONE_DAY_MILLIS = 24 * 60 * 60 * (long)1000;
	
	public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat YYYYMM_FORMAT = new SimpleDateFormat("yyyy-MM");
	
	public static final SimpleDateFormat MMDD_FORMAT = new SimpleDateFormat("MM-dd");
	
	/**
	 * 时间区间内天数, 开始日期必须小于结束日期
	 * @param startDate 不能为null, 包含起始日期
	 * @param endDate 不能为null, 包含结束日期
	 * @return
	 */
	public static int getNumberOfDays(Date startDate, Date endDate){
		if(startDate == null){
			throw new IllegalArgumentException(" startDate can not be null");
		}
		
		if(endDate == null){
			throw new IllegalArgumentException(" endDate can not be null");
		}

		int numberOfDays = 0;
		numberOfDays = (int)((endDate.getTime() - startDate.getTime()) / ONE_DAY_MILLIS) + 1;
		
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
	public static Date parse(String strDate, DateFormat format) throws ParseException{
		return format.parse(strDate);
	}
	
	/**
	 * 从字符转换成日期格式
	 * @param strDate
	 * @param format 本类中有几个默认的format可以调用
	 * @return
	 * @throws ParseException 
	 * @see YYYYMMDDHHMMSS_FORMAT,YYYYMMDD_FORMAT,YYYYMM_FORMAT
	 */
	public static String format(Date date, DateFormat format){
		return format.format(date);
	}
	
	public static String getCurrentDate(){
		Date d = new Date();
		return YYYYMMDDHHMMSS_FORMAT.format(d);
	}
	
	public static String getCurrentYearMonth(){
		Date d = new Date();
		return YYYYMM_FORMAT.format(d);
	}
	
	public static String getCurrentYearMonthDay(){
		Date d = new Date();
		return YYYYMMDD_FORMAT.format(d);
	}
	
	/**
	 * 计算两个日期的时间差(小时)
	 * @param date1
	 * @param date2
	 * @return double
	 */
	public static double getHourDifference(String date1, String date2) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-M-d HH:mm:ss");
		java.util.Date start = sdf.parse(date1);
		java.util.Date end = sdf.parse(date2);
		long cha = end.getTime() - start.getTime();
		double result = cha * 1.0 / (1000 * 60 * 60);
		return result;
	}
	
	/**
	 * 指定的日期是否在指定区间内， 包含区间的开始日期和结束日期，如果开始日期在结束日期后，或者三个日期任何一个日期为null都返回false
	 * @param startDate
	 * @param endDate
	 * @param date
	 * @return
	 */
	public static boolean isBelongDateScope(Date startDate, Date endDate, Date date){
		if(date == null ||startDate == null || endDate == null){
			return false;
		}
		
		if(startDate.after(endDate)){
			return false;
		}
		
		if(date.before(startDate) || date.after(endDate)){
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 日期滚动
	 * @param date
	 * @param number
	 * @param flag, 1表示年， 2表示月， 3表示日，4表示小时
	 * @return
	 */
	public static Date add(Date date, int number, int flag){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (flag){
		case 1:
			calendar.add(Calendar.YEAR, number);
			break;
		case 2:
			calendar.add(Calendar.MONTH, number);
			break;
		case 3:
			calendar.add(Calendar.DATE, number);
			break;
		case 4:
			calendar.add(Calendar.HOUR, number);
			break;		
		default:
			throw new IllegalArgumentException(" flag " + flag + " can not be accept");			
		}
		return calendar.getTime();
	}	
	
	public static Date getShortCurrentDate() {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		String dateStr = YYYYMMDD_FORMAT.format(cal.getTime());
		try {
			currentDate = YYYYMMDD_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	
	public static Date getCurrentDate1() {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		String dateStr = YYYYMMDDHHMMSS_FORMAT.format(cal.getTime());
		try {
			currentDate = YYYYMMDDHHMMSS_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	
	/**
	 * 返回该天在一周中的位置1-周一;2-周二...以此类推
	 * @param currDate 当前的时间
	 * @return
	 */
	public static int getDayInWeek(Date currDate){
		Calendar ca = Calendar.getInstance();
		ca.setTime(currDate);
		int day_in_week = ca.get(Calendar.DAY_OF_WEEK);
		switch(day_in_week){
			case Calendar.SUNDAY:
				return 7;
			case Calendar.MONDAY:
				return 1;
			case Calendar.TUESDAY:
				return 2;
			case Calendar.WEDNESDAY:
				return 3;
			case Calendar.THURSDAY:
				return 4;
			case Calendar.FRIDAY:
				return 5;
			case Calendar.SATURDAY:
				return 6;
			default :
				return 0;
		}
	}
	
	/**
	 * 判断给定的日期是不是星期一，是星期一返回true , 否则返回false
	 * @param date
	 * @return
	 */
	public static boolean isMonday(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int day_in_week = ca.get(Calendar.DAY_OF_WEEK);
		if(Calendar.MONDAY == day_in_week) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取日期范围中有效的周数（只要一周中有一天工作日在列表中，该周就算有效）
	 * @param dates
	 * @param endDate 循环截止日期
	 * @return
	 */
	public static int checkWeekLen(Date[] dates, Date endDate) {
		int perWeekNum = 0;
		int totalWeekNum = 0;
		Calendar ca = Calendar.getInstance();
		ca.setFirstDayOfWeek(Calendar.MONDAY); // 设置周一为一周的起始天
		for(int i = 0; i < dates.length; i++){
			if(endDate != null && endDate.before(dates[i])){
				break;
			}
			ca.setTime(dates[i]);
			int weekNum = ca.get(Calendar.WEEK_OF_YEAR);
			if(perWeekNum != weekNum){
				totalWeekNum ++;
				perWeekNum = weekNum;
			}
		}
		return totalWeekNum;
	}

	/**
	 * 判断两个日期是否在同一个月
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isInSameMouth(Date date1, Date date2){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date1);
		int month1 = ca.get(Calendar.MONTH);
		ca.setTime(date2);
		int month2 = ca.get(Calendar.MONTH);
		return month1 == month2;
	}
	public static boolean isInSameWeek(Date date1, Date date2){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date1);
		int week1 = ca.get(Calendar.WEEK_OF_YEAR);
		ca.setTime(date2);
		int week2 = ca.get(Calendar.WEEK_OF_YEAR);
		return week1 == week2;
	}
	

	/**
	 * 取得当天的最小时间，如2012-11-11 00:00:00
	 * @param args
	 */
	
	public static Date getCurrentDayMixDate() {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		
		String dateStr = YYYYMMDD_FORMAT.format(cal.getTime());
		dateStr +=" 00:00:00";
		try {
			currentDate = YYYYMMDDHHMMSS_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	
	/**
	 * 取得当天的最小时间，如2012-11-11 00:00:00
	 * @param d 当前时间
	 */
	
	public static Date getCurrentDayMixDate(Date d) {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		String dateStr = YYYYMMDD_FORMAT.format(cal.getTime());
		dateStr +=" 00:00:00";
		try {
			currentDate = YYYYMMDDHHMMSS_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	
	/**
	 * 取得当天的最大时间，如2012-11-12 23:59:59
	 * @param args
	 */
	
	public static Date getCurrentDayMaxDate() {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		
		String dateStr = YYYYMMDD_FORMAT.format(cal.getTime());
		dateStr +=" 23:59:59";
		try {
			currentDate = YYYYMMDDHHMMSS_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	
	/**
	 * 取得当天的最大时间，如2012-11-12 23:59:59
	 * @param d : 当前时间
	 */
	
	public static Date getCurrentDayMaxDate(Date d) {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		String dateStr = YYYYMMDD_FORMAT.format(cal.getTime());
		dateStr +=" 23:59:59";
		try {
			currentDate = YYYYMMDDHHMMSS_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	
	/**
	 * 取得明天的最小时间，如2012-11-12 00:00:00
	 * @param args
	 */
	
	public static Date getTomorrowDayMixDate() {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String dateStr = YYYYMMDD_FORMAT.format(cal.getTime());
		dateStr +=" 00:00:00";
		try {
			currentDate = YYYYMMDDHHMMSS_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	
	/**
	 * 取得明天的最小时间，如2012-11-12 00:00:00
	 * @param d : 当前时间
	 */
	
	public static Date getTomorrowDayMixDate(Date d) {
		Date currentDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String dateStr = YYYYMMDD_FORMAT.format(cal.getTime());
		dateStr +=" 00:00:00";
		try {
			currentDate = YYYYMMDDHHMMSS_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return currentDate;
	}
	/**
	 * 判断两个日期是否相差1年
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return  两个日期相差：小于等于一年返回false,大于一年返回true
	 */
	public static boolean getOffDays(Date startDate,Date endDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.YEAR, +1);
		Calendar calend = Calendar.getInstance();
		calend.setTime(endDate);
		if (cal.before(calend)) {
			return true;
		}
		return false;
	}
	/**
	 * 得到两个日期之间相差的月份
	 * @param beginDate
	 * 			开始日期
	 * @param endDate
	 * 			结束日期
	 * @return 相差的月份
	 */
	public static int getMonth(Date beginDate,Date endDate){
		if (beginDate.after(endDate)) {
			Date temp = beginDate;
			beginDate = endDate;
			endDate  = temp;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(beginDate);
		cal2.setTime(endDate);
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(endDate);
		tempCal.add(Calendar.DATE, 1);
		int year = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		int month = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		if ((cal1.get(Calendar.DATE) == 1)&&(tempCal.get(Calendar.DATE)==1)) {
			return year * 12 + month +1;
		}else if ((cal1.get(Calendar.DATE) != 1)&& (tempCal.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		}else if ((cal1.get(Calendar.DATE) == 1)&& (tempCal.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		}else {
			return (year * 12 + month -1)<0 ? 0:(year *12 +month);
		}
	}
	public static void main(String[] args){
		Date d1 = DateUtils.getShortCurrentDate();
		System.out.println(getCurrentDayMaxDate(d1));
	}
}
   
