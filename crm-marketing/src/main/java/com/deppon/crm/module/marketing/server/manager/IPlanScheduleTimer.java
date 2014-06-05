/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPlanScheduleTimer.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author Administrator
 * @version 0.1 2012-5-10
 */
package com.deppon.crm.module.marketing.server.manager;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPlanScheduleTimer.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-5-10
 */

public interface IPlanScheduleTimer {
	/**
	 * 
	 * <p>
	 * 处理开发计划和日程状态的定时器<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-5-10
	 * void
	 */
	 void processPlanScheduleStatus();
	
	/**
	 * 
	 * <p>
	 * 创建每月客户维护计划创建<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-5-10
	 * void
	 */
	 void processMonthlyMaintainCustomer();
	
	/**
	 * 
	 * <p>
	 * 创建每日维护计划<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-5-10
	 * void
	 */
	 void processDailyMaintainCustomer();
}
