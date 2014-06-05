package com.deppon.crm.module.authorization.dao;

import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.impl.TreeDao;
import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.testutil.TestUtil;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:TreeDao测试<br />
 * </p>
 * 
 * @title TreeDaoTest.java
 * @package com.deppon.crm.module.authorization.dao
 * @author 华龙
 * @version 0.1 2012-12-26
 */

public class TreeDaoTest extends TestCase {
	static TreeDao treeDao = null;
	static Tree tree=new Tree();
	static Integer id=null;
	static {
		treeDao = TestUtil.treeDao;
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
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testSaveTree() {
		id=treeDao.saveTree(tree);
		

	}

	/**
	 * 
	 * <p>
	 * Description:testGetTreeByUserId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testGetTreeByUserId() {
		treeDao.getTreeByUserId(106138);

	}

	/**
	 * 
	 * <p>
	 * Description:testDeleteTreeByUser<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testDeleteTreeByUser() {
		treeDao.deleteTreeByUser(tree.getUserid());
		

	}

	/**
	 * 
	 * <p>
	 * Description:testDeleteTreeById<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testDeleteTreeById() {
		treeDao.deleteTreeById(id);


	}

	/**
	 * 
	 * <p>
	 * Description:testGetTreeById<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testGetTreeById() {
		treeDao.getTreeById(id);

	}
}
