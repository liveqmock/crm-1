package com.deppon.crm.module.authorization.service;

import org.junit.Test;

import com.deppon.crm.module.authorization.server.service.impl.TreeService;
import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.testutil.TestUtil;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title AuthorizeServiceTest.java
 * @package com.deppon.crm.module.authorization.service
 * @author 华龙
 * @version 0.1 2012-12-26
 */
public class TreeServiceTest extends TestCase {
	static Tree tree = new Tree();
	static Integer id = null;
	static TreeService treeService;
	static {
		treeService= (TreeService) TestUtil.treeService;
		tree.setFunctionCode("");
		tree.setNoteurl("/login/aa.action");
		tree.setUserid(106138);

	}

	/**
	 * 
	 * <p>
	 * Description:testGetTreeByUserId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testGetTreeByUserId() {
		treeService.getTreeByUserId(100);

	}

	/**
	 * 
	 * <p>
	 * Description:testGetTreeByUserId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testSaveTree() {
		treeService.saveTree(tree);

	}

	/**
	 * 
	 * <p>
	 * Description:testGetTreeByUserId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testDeleteTreeByUser() {
		treeService.deleteTreeByUser(11);

	}

}
