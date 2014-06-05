package com.deppon.crm.module.scheduler.server.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 
	 * <p>
	 * 得到统计时间--月<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-30
	 * @param date
	 * @return
	 * String
	 */
	public static String getMoth(Date date) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM");
		return formate.format(date);
	}
	/**
	 * 
	 * <p>
	 * 得到统计时间--年<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-30
	 * @param date
	 * @return
	 * String
	 */
	public static String getYear(Date date) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy");
		return formate.format(date);
	}

}
