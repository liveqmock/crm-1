package com.deppon.crm.module.common.server.manager;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.foss.framework.server.context.UserContext;

public class LadingstationDepartmentRuleImplTest extends TestCase{
	static ClassPathXmlApplicationContext factory;
	private static ILadingstationDepartmentRule ladingstationDepartmentRuleImpl;
	private static User user;

	private static LadingstationDepartment ld = null;
	static {
		String resource = "LadingstationDepartmentDaoBeanTest.xml";
		factory = new ClassPathXmlApplicationContext(resource);
		ladingstationDepartmentRuleImpl = (ILadingstationDepartmentRule) factory
				.getBean("ladingstationDepartmentRule");
//		user = new User();
//		user.setId("106139");
//		UserContext.setCurrentUser(user);
	}
	public void testValidate(){
		Integer operationType = 1;
		try{
			ladingstationDepartmentRuleImpl.validate(ld, operationType);
		}catch(Exception e){}
		
	}
}
