/*package com.deppon.oa;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.deppon.oa.task.TaskService;

@ContextConfiguration({
	"classpath:"
})
public class TaskTest  extends AbstractJUnit4SpringContextTests{
	
	@Test
	public void test(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://192.168.17.25/portal/TaskService");
		factory.setServiceClass(TaskService.class);
		TaskService service = (TaskService)factory.create();
		service.sendMessageToPortal("", "");
	}
}
*/