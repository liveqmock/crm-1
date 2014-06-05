/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BAMPUtil.java
 * @package com.deppon.crm.module.frameworkimpl.server.util 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.util;


/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BAMPUtil.java
 * @package com.deppon.crm.module.frameworkimpl.server.util 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public class BAMPUtil {
	// 测试
	public void init(){
		BAMPActiveMQUtil bu = (BAMPActiveMQUtil) BAMPActiveMQUtil.getInstance();
		BAMPLogSwitchUtil ls = (BAMPLogSwitchUtil) BAMPLogSwitchUtil.getLocalReqLogSwitch();
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
//		ActiveMQUtil au2 = (ActiveMQUtil) ac.getBean("activeMQUtil");
		System.out.println();
	}
}
