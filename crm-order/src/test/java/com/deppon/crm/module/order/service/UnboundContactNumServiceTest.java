/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundContactNumServiceTest.java
 * @package com.deppon.crm.module.order.service 
 * @author Administrator
 * @version 0.1 2012-9-10
 */
package com.deppon.crm.module.order.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.order.server.service.impl.UnboundContactNumService;
import com.deppon.foss.framework.server.context.AppContext;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundContactNumServiceTest.java
 * @package com.deppon.crm.module.order.service 
 * @author 苏玉军
 * @version 0.1 2012-9-10
 */

public class UnboundContactNumServiceTest {
	private static ClassPathXmlApplicationContext factory;
	private static UnboundContactNumService service;
	static {
		AppContext.initAppContext("application", "", "");
		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
		factory = new ClassPathXmlApplicationContext(resource);
		service = (UnboundContactNumService) factory.getBean("unboundContactNumService");
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deppon.crm.module.order.server.service.impl.UnboundContactNumService#getBoundInfoList(java.lang.String)}.
	 */
	@Test
	public void testGetBoundInfoList() {
		String contactId = "1234";
		List<RegisterInfo> list = new ArrayList<RegisterInfo>();
		try {
			list = service.getBoundInfoList(contactId);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(list);
	}
	
	
	
}
