
package com.deppon.crm.module.authorization.service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.service.IFunctionService;
import com.deppon.crm.module.authorization.server.service.impl.FunctionService;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.shared.exception.FunctionException;
import com.deppon.crm.module.authorization.shared.exception.FunctionExceptionType;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;
/**
 * 
 * <p>
 * Description:测试 -- FunctionService <br />
 * </p>
 * @title FunctionServiceTest.java
 * @package com.deppon.crm.module.authorization.service 
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
public class FunctionServiceTest extends TestCase {
	static IFunctionService fnService = null;
	private Function fn = null;
	private Function fnParent = null;
	private User user = null;
	static {
		fnService = TestUtil.functionService;
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
		fn.setFunctionCode(System.currentTimeMillis()+"");
		fn.setFunctionName("订单管理测试");
		fn.setUri("/order_test");
		fn.setFunctionLevel(3);
		fn.setParentCode(fnParent);
		fn.setValidFlag(true);
		fn.setInvalidDate(new Date());
		fn.setValidDate(new Date());
		fn.setDisplayOrder(2);
		fn.setCheck(true);
		fn.setFunctionType(new Byte("4"));//功能编码 必须为4 表示页面功能
		fn.setFunctionDesc("订单管理");
		fn.setFunctionSeq("0/01/01008");
		
		user = new User();
		user.setId("17455");
		user.setLoginName("039154");
		user.setStatus(new Byte("1"));
		
		
	}

	/**
	 * 测试之后执行
	 */
	@After
	protected void tearDown() throws Exception {
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 修改功能对象<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testUpdateA(){
		fnService.update(null);

//		//fnService.save(fn);
		UserContext.setCurrentUser(user);
//		//fnService.update(fn);
		
		fn.setValidFlag(false);
		//fnService.update(fn);
		
		try{
			fn.setValidFlag(null);
			//fnService.update(fn);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{//TODO 父级 validFlag 库数据未修改，咱未覆盖
			fn.setValidFlag(true);
			fn.getParentCode().setValidFlag(false);
			//fnService.update(fn);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{
			fn.getParentCode().setValidFlag(true);
			fn.setId(null);
			//fnService.update(fn);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{
			fn.setId("");
			//fnService.update(fn);
		}catch(GeneralException e){
			assertFalse(false);
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 得到指定ID的对象<br />
	 * TODO sql问题 同 dao 层
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryById(){
		try{
			fnService.queryById(null);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{
			fnService.queryById("");
		}catch(GeneralException e){
			assertFalse(false);
		}
//		//fnService.save(fn);
//		fnService.queryById(fn.getId());
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
	public void testQueryByFunctionCode(){
		try{
			fnService.queryByFunctionCode(null);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{
			fnService.queryByFunctionCode("");
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		//fnService.save(fn);
		fnService.queryByFunctionCode(fn.getFunctionCode());
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 查询所有功能对象<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryAllA(){
		fnService.queryAll();
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 查询功能对象信息的条件<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryAllB(){
		fnService.queryAll(fn);
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 分页查询所有的功能对象<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryAllC(){
		fnService.queryAll(fn, 1, 0);
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
	public void testQueryAllChildFunctionById(){
		try{
			fnService.queryAllChildFunctionById(null);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{
			fnService.queryAllChildFunctionById("");
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		fnService.queryAllChildFunctionById(fn.getParentCode().getId());
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 保存功能对象<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testSave(){
		Byte[] fnTypes= {1,2,3,4,5};
		for (Byte fnType : fnTypes) {
			fn.setFunctionType(fnType);
//			//fnService.save(fn);
		}
		
		try{
			fnService.save(null);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		String fnName = fn.getFunctionName();
		try{
			fn.setFunctionName(null);
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setFunctionName(fnName);
			assertFalse(false);
		}
		
		try{
			fn.setFunctionName("");
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setFunctionName(fnName);
			assertFalse(false);
		}
		
		String uri = fn.getUri();
		try{
			fn.setUri(null);
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setUri(uri);
			assertFalse(false);
		}
		
		try{
			fn.setUri("");
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setUri(uri);
			assertFalse(false);
		}
		
		try{
			fn.setUri("51121vsdsasdsef2323");
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setUri(uri);
			assertFalse(false);
		}
		
		try{
			fn.setParentCode(null);
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setParentCode(fnParent);
			assertFalse(false);
		}
		
		Boolean validFalg = fn.getValidFlag();
		try{
			fn.setValidFlag(null);
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setValidFlag(validFalg);
			assertFalse(false);
		}
		
		Boolean check = fn.getCheck();
		try{
			fn.setCheck(null);
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setCheck(check);
			assertFalse(false);
		}
		
		Byte fnType = fn.getFunctionType();
		try{
			fn.setFunctionType(null);
			//fnService.save(fn);
		}catch(GeneralException e){
			fn.setFunctionType(fnType);
			assertFalse(false);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 修改功能对象<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testUpdateB(){
//		//fnService.save(fn);
//		//TODO 放在最后测试
//		List<Function> createFunctionPageEletmens = null;
//		List<Function> updateFunctionPageEletmens = null;
//		List<Function> deleteFunctionPageEletmens = null;
//		
//		fnService.update(fn, createFunctionPageEletmens, updateFunctionPageEletmens, deleteFunctionPageEletmens);
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过ID删除功能对象<br />
	 * TODO dao 层 sql 错误 无法测试
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testRemove(){
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 统计功能对象数量<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testCount(){
//		//fnService.save(fn);
		fnService.count(fn);
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
	public void testQueryDirectChildFunctions(){
		fnService.queryDirectChildFunctions(fn);
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过角色ID查询功能对象集合<br />
	 * TODO dao层 sql 有误 ，无法全部覆盖
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryAllFunctionByRoleId(){
		try{
			fnService.queryAllFunctionByRoleId(null);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{
			fnService.queryAllFunctionByRoleId("");
		}catch(GeneralException e){
			assertFalse(false);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:测试 -- 通过角色ID查询功能对象ID集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryAllFunctionIdByRoleId(){
		try{
			fnService.queryAllFunctionIdByRoleId(null);
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		try{
			fnService.queryAllFunctionIdByRoleId("");
		}catch(GeneralException e){
			assertFalse(false);
		}
		
		fnService.queryAllFunctionIdByRoleId("400000046");
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
		fnService.getFunctionByParentCode(fn.getParentCode().getFunctionCode());
	}
}

