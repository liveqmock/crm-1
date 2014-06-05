package com.deppon.crm.module.recompense.server.workflow;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.frameworkimpl.server.cache.UserCacheProvider;
import com.deppon.crm.module.recompense.server.manager.workflow.condition.RecompenseStatusOnly;
import com.deppon.crm.module.recompense.server.manager.workflow.condition.RecompenseTypeOnly;
import com.deppon.crm.module.recompense.server.manager.workflow.condition.RoleDepartmentOnly;
import com.deppon.crm.module.recompense.server.manager.workflow.condition.RoleOnly;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.utils.Constants;

public class RecompenseConditionTest extends TestCase {

	private static UserCacheProvider userCacheProvider;

	static {
		userCacheProvider = TestUtil.userCacheProvider;
	}

	public void testGroupOnlyCondition() {

		RoleOnly cond = new RoleOnly();
		Map args = new HashMap<String, String>();
		args.put("role", "" + 3);
		Map appMap = new HashMap();

		User user = (User) userCacheProvider.get("18706");
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		assertFalse(cond.passesCondition(appMap, args, null));

		user = (User) userCacheProvider.get("21935");
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		assertFalse(cond.passesCondition(appMap, args, null));

		user = null;
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		//assertFalse(cond.passesCondition(appMap, args, null));
	}

	public void testRoleDepartmentOnly() {
		RoleDepartmentOnly condition = new RoleDepartmentOnly();

	}

	public void testRecompenseStatusOnly() {
		RecompenseStatusOnly condition = new RecompenseStatusOnly();

	}

	public void testRecompenseTypeOnly() {
		RecompenseTypeOnly condition = new RecompenseTypeOnly();

	}

}
