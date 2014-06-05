package com.deppon.crm.module.common.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.ITreeDao;
import com.deppon.crm.module.authorization.server.dao.impl.TreeDao;
import com.deppon.crm.module.authorization.shared.domain.Tree;


public class TreeDaoTest extends TestCase{
	private ITreeDao treeDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	private Tree tree=null;
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			treeDao = ctx.getBean(TreeDao.class);
			tree=new Tree();
			tree.setNoteid(82);
			tree.setNoteurl("action");
			tree.setUserid(0);
			tree.setNotename("工单处理");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTreeByUserId() {
		int fid=treeDao.saveTree(tree);
		List<Tree> tree=this.treeDao.getTreeByUserId(0);
		assertEquals(tree.size()>0, true);
		treeDao.deleteTreeById(fid);
	}

	@Test
	public void testSaveTree() { 
		int fid=treeDao.saveTree(tree);
		List<Tree> tree=this.treeDao.getTreeByUserId(0);
		assertEquals(tree.size()>0, true);
		treeDao.deleteTreeById(fid);
	}

	@Test
	public void testDeleteTree() {
		int fid=treeDao.saveTree(tree);
		treeDao.deleteTreeById(fid);
		Tree t=treeDao.getTreeById(fid);
		assertEquals(t==null, true);
	}

}
