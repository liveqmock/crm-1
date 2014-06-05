package com.deppon.crm.module.authorization.dao;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.impl.FunctionDao;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.authorization.testutil.TestUtil;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:FunctionDao测试类<br />
 * </p>
 * @title FunctionDaoTest.java
 * @package com.deppon.crm.module.authorization.dao 
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
public class FunctionDaoTest extends TestCase {
	static FunctionDao fnDao = null;
	private Function fn = null;
	private Function fnParent = null;
	static {
		fnDao = TestUtil.functionDao;
	}
	
	/**
	 * 测试之前执行
	 */
	@Before
	protected void setUp() throws Exception {
		fnParent = new Function();
		fnParent.setId("23");
		fnParent.setFunctionName("订单创建");
		fnParent.setFunctionCode("01003002");
		fnParent.setUri("/order/createOrderView.action");
		fnParent.setFunctionLevel(4);
		fnParent.setValidFlag(true);
		fnParent.setInvalidDate(new Date());
		fnParent.setValidDate(new Date());
		fnParent.setDisplayOrder(2);
		fnParent.setCheck(true);
		fnParent.setFunctionType(new Byte("1"));
		fnParent.setFunctionDesc("创建订单");
		fnParent.setFunctionSeq("0/01/003");
		
		fn = new Function();
		fn.setId(fnDao.getNewId());
		fn.setFunctionCode(System.currentTimeMillis()+"");
		fn.setFunctionName("订单管理测试");
		fn.setUri("/order_test");
		fn.setFunctionLevel(3);
		fn.setParentCode(null);
		fn.setValidFlag(true);
		fn.setInvalidDate(new Date());
		fn.setValidDate(new Date());
		fn.setDisplayOrder(2);
		fn.setCheck(true);
		fn.setFunctionType(new Byte("1"));
		fn.setFunctionDesc("订单管理");
		fn.setFunctionSeq("0/01/01008");
	}

	/**
	 * 测试之后执行
	 */
	@After
	protected void tearDown() throws Exception {
//		fnDao.deleteById(fn.getId());
//		fnDao.deleteByParentId(fn.getId()); // 删除自己及所有子集
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 查询所有功能<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllA(){
		fnDao.getAll();
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 查询所有功能-查询条件信息类<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllB(){
		fnDao.getAll(fn);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 根据父 code 查询子集<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetFunctionByParentCode(){
		fnDao.getFunctionByParentCode("01");
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 分页查询所有功能<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllC(){
		fnDao.getAll(null,2,1);
		
		fnDao.getAll(fn,2,1);
		
		fn.setId(null);
		fn.setFunctionCode(null);
		fn.setFunctionName(null);
		fn.setUri(null);
		fn.setFunctionDesc(null);
		fn.setFunctionSeq(null);
		fnDao.getAll(fn,2,1);
		
		fn.setId("");
		fn.setFunctionCode("");
		fn.setFunctionName("");
		fn.setUri("");
		fn.setFunctionDesc("");
		fn.setFunctionSeq("");
		fnDao.getAll(fn,2,1);
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过一些功能ID得到一个功能对象的集合<br />
	 * sql 无法测试//TODO
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetByIds(){
//		List<String> fnIds = new ArrayList<String>();
//		fnIds.add(fn.getId());
//		fnDao.getByIds(fnIds);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过功能编码得到功能对象<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetByCode(){
		fnDao.getByCode(fn.getFunctionCode());
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 查找属于该功能的子功能<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetDirectChildFunctions(){
		fnDao.getDirectChildFunctions(fn);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 更新功能信息<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testUpdate(){
		fnDao.insert(fn);
		
		fn.setFunctionName("测试的 functionName");
		fnDao.update(fn);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 保存功能信息<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testInsert(){
		fnDao.insert(fn);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过ID，得到功能对象<br />
	 * sql 无法测试//TODO
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetById(){
//		fnDao.insert(fn);
//		fnDao.getById(fn.getId());
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 得到功能树的根节点<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetRoot(){	
		fnDao.getRoot();
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过ID获得当前ID所对应的所有的子节点<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllChildFunctionById(){
		fnDao.getAllChildFunctionById(fnParent.getId());
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 统计功能的条数<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testCount(){
		fnDao.count(fnParent);
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 查询最后更新时间<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetLastModifyTime(){	
		fnDao.getLastModifyTime();
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过URI得到当前URL定位的功能树下的所有功能<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllChildFunctionByURI(){	
		fnDao.getAllChildFunctionByURI(fnParent.getUri());
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过功能编码得到当前功能编码定位的功能下的所有功能<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllChildFunctionByCode(){
		fnDao.getAllChildFunctionByCode(fnParent.getFunctionCode());
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过ID删除功能信息<br />
	 * sql 错误无法测试//TODO
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testDeleteById(){	
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过功能对像的ID集合，删除满足条件的功能信息<br />
	 * sql 错误无法测试//TODO
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testDeleteByIds(){	
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 删除功能信息集合中的所有功能信息<br />
	 * sql 错误无法测试//TODO
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testDeleteFunctions(){	
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过父功能ID删除父功能下的所有子功能<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testDeleteByParentId(){	
		fnDao.insert(fn);
		fnDao.deleteByParentId(fn.getId());
		
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过自增长序列得到序列的值<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetNewId(){	
		fnDao.getNewId();
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过父功能编码得到所有子功能中编码值最大的功能对象<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetMaxCodeFunctionByParentCode(){	
		fnDao.getMaxCodeFunctionByParentCode(fnParent.getFunctionCode());
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过角色ID，得到功能对象集合<br />
	 * sql 有问题无法覆盖//TODO
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllByRoleId(){	
		
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过角色ID，得到功能对象ID集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testGetAllIdByRoleId(){
		fnDao.getAllIdByRoleId("400000046");
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 更新该页面的页面元素的状态<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testUpdateChild(){
		fnDao.insert(fn);
		
		Function fnNew = new Function();
		fnNew.setId(fnDao.getNewId());
		fnNew.setFunctionCode(System.currentTimeMillis()+"");
		fnNew.setFunctionName("订单管理测试_child");
		fnNew.setUri("/order_test/child");
		fnNew.setFunctionLevel(3);
		fnNew.setParentCode(fn);
		fnNew.setValidFlag(true);
		fnNew.setInvalidDate(new Date());
		fnNew.setValidDate(new Date());
		fnNew.setDisplayOrder(2);
		fnNew.setCheck(true);
		fnNew.setFunctionType(new Byte("1"));
		fnNew.setFunctionDesc("订单管理_child");
		fnNew.setFunctionSeq("0/01/01008/01008004");
		fnDao.insert(fnNew);
		
		fnNew.setInvalidDate(new Date());
		fnDao.updateChild(fnNew);
	}
}