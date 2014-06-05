/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DateUTilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author 043260
 * @version 0.1 2014年4月15日
 */
package com.deppon.crm.module.marketing.utilstest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.DateUtils;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.foss.framework.cache.CacheUtils;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DateUTilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author 043260
 * @version 0.1 2014年4月15日
 */

public class DateUtilsTest {

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.utils.DateUtils#daysBetween(java.util.Date, java.util.Date)}.
	 */
	@Test
	public void testDaysBetween() {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		Date date = new Date();
		start.setTime(date);
		end.setTime(date);
		end.add(Calendar.YEAR, 1);
		
		try {
			int amount = DateUtils.daysBetween(start.getTime(), end.getTime());
			Assert.assertNotNull(amount);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCalendar(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		System.out.println(calendar.getTime());
	}
	@Test
	public void testJsonString(){
		MarketActivity ma = new MarketActivity();
		System.out.println(CacheUtils.toJsonString(ma));
		String aa = "abcdef";
		System.out.println(CacheUtils.toJsonString(aa));
	}
}
