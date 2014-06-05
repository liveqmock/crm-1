package com.deppon.crm.module.recompense.server.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * <p>
 * Description:日期util<br />
 * </p>
 * @title DateUtil.java
 * @package com.deppon.crm.module.recompense.server.utils 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class DateUtil {
	/**
	 * 
	 * <p>
	 * Description:获取日期<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param date
	 * @param addDay
	 * @return
	 * Date
	 */
	public static Date getDateMidnight(Date date, int addDay) {
		try {
			//转换规则
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			//日历
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, addDay);
			date = df.parse(df.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
