package com.deppon.crm.module.customer.server.manager.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.customer.server.dao.ICustLabelDao;
import com.deppon.crm.module.customer.server.dao.impl.CustLabelDao;
import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;

public class CustLabelManagerTest extends TestCase{
	private ICustLabelManager custLabelManager
		=SpringTestHelper.getBean(CustLabelManager.class);
	private ICustLabelDao custLabelDao
		=SpringTestHelper.getBean(CustLabelDao.class);
	//初始化测试数据
	public void setUp() throws SQLException{
		UserUtil.setUserToAdmin();
		DataTestUtil.cleanCustLabelData();
		DataTestUtil.initCustLabelData();
	}
	//清理测试数据
	public void tearDown()throws SQLException{
		DataTestUtil.cleanCustLabelData();
	}
	/**
	 * 
	 * @Title: testSaveCustLabel
	 *  <p>
	 * @Description: 保存客户CustLabel标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-18
	 * @return void
	 * @throws
	 */
	@Test
	public void testSaveCustLabel(){
		List<CustLabel> custLabels = new ArrayList<CustLabel>();
		CustLabel custLabel1 = new CustLabel();
		Label label = new Label();
		Date date = new Date();
		//模拟传入custLabels为空list的情况
		custLabelManager.saveCustLabel(custLabels);
		//模拟客户Id为空的情况
		custLabels.add(custLabel1);
		try {
			//执行保存操作
			custLabelManager.saveCustLabel(custLabels);
			//断言会保存失败
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.CustId_Miss_Error.getErrCode());
		}
		//模拟客户类型为空的情况
		try {
			custLabels.remove(0);
			custLabel1.setCustId("105873");
			custLabels.add(custLabel1);
			custLabelManager.saveCustLabel(custLabels);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.CustType_Miss_Error.getErrCode());
		}
		//模拟Label为空的情况
		try {
			custLabel1.setCustType("PC_CUSTOMER_TT");
			custLabelManager.saveCustLabel(custLabels);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.Label_Miss_Error.getErrCode());
		}
		//模拟标签内容ID为空的情况
		try {
			custLabels.get(0).setLabel(label);
			custLabelManager.saveCustLabel(custLabels);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelId_Miss_Error.getErrCode());
		}
		//模拟部门ID为空的情况
		try {
			custLabels.get(0).getLabel().setId("1");
			custLabelManager.saveCustLabel(custLabels);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelDeptId_Miss_Error.getErrCode());
		}
		//模拟Label标签名字为空的情况
		try {
			custLabels.get(0).getLabel().setDeptId("-100");
			custLabelManager.saveCustLabel(custLabels);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelName_Miss_Error.getErrCode());
		}
		//模拟正常输入的情况
		custLabels.get(0).getLabel().setLabelName("亮亮测试数据");
		custLabels.get(0).setCreateDate(date);
		custLabels.get(0).setCreateUser("105873");
		//执行保存操作
		custLabelManager.saveCustLabel(custLabels);
		custLabels.removeAll(custLabels);
		custLabels = custLabelDao.queryCustLabel("105873", "PC_CUSTOMER_TT");
		Assert.assertTrue(
				custLabels != null && custLabels.size() > 0);
		//清理测试数据
		custLabelDao.deleteCustLabel("105873", "PC_CUSTOMER_TT");
		//模拟当标签个数超过10个的情况（前面已经存了一个进去了，所以这里只需要再放10个就好）
		try {
			for(int i = 0;i < 10;i++){
				custLabels.add(custLabel1);			
			}
			//执行保存操作
			custLabelManager.saveCustLabel(custLabels);
			//断言会保存失败
			Assert.fail();
		} catch (MemberException e) {
			//校验是否抛出了预期的异常信息
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.Label_OutOf_Range_Error.getErrCode());
		}
	}
	/**
	 * 
	 * @Title: testUpdateCustLabel
	 *  <p>
	 * @Description: 修改客户CustLabel标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-18
	 * @return void
	 * @throws
	 */
	@Test
	public void testUpdateCustLabel(){
		List<CustLabel> custLabels = new ArrayList<CustLabel>();
		CustLabel custLabel = new CustLabel();
		Label label = new Label();
		custLabels.add(custLabel);
		//模拟当客户ID为空的时候
		try {
			custLabelManager.updateCustLabel(null, null, null, null);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.CustId_Miss_Error.getErrCode());
		}
		//模拟客户类型为空的 时候
		try {
			custLabelManager.updateCustLabel(null, "105873", null, null);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.CustType_Miss_Error.getErrCode());
		}
		//模拟部门ID为空的时候
		try {
			custLabelManager.updateCustLabel(null, "105873", "PC_CUSTOMER_CC", null);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelDeptId_Miss_Error.getErrCode());
		}
		/*模拟传入标签不为空List的时候*/
		//模拟Label为空的情况
		try {
			custLabelManager.updateCustLabel(custLabels, "105873", "PC_CUSTOMER_CC","105873");
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.Label_Miss_Error.getErrCode());
		}
		//模拟标签内容ID为空的情况
		try {
			custLabels.get(0).setLabel(label);
			custLabelManager.updateCustLabel(custLabels, "105873", "PC_CUSTOMER_CC","105873");
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelId_Miss_Error.getErrCode());
		}
		//模拟Label标签名字为空的情况
		try {
			custLabels.get(0).getLabel().setDeptId("105873");
			custLabels.get(0).getLabel().setId("-123");
			custLabelManager.updateCustLabel(custLabels, "105873", "PC_CUSTOMER_CC","105873");
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelName_Miss_Error.getErrCode());
		}
		//模拟正常输入的情况
		custLabels.get(0).getLabel().setLabelName("亮亮测试数据");
		custLabels.get(0).getLabel().setCreateUser("86301");
		//执行修改操作
		custLabelManager.updateCustLabel(custLabels, "105873", "PC_CUSTOMER_CC","105873");
		custLabels.removeAll(custLabels);
		custLabels = custLabelDao.queryCustLabel("105873", "PC_CUSTOMER_CC");
		Assert.assertTrue(
				custLabels != null && custLabels.size() > 0);
		/*模拟传入的标签为空List的时候看是否删除成功*/
		custLabelManager.updateCustLabel(null, "105873", "PC_CUSTOMER_CC", "-100");	
		custLabels = custLabelDao.queryCustLabel("105873", "PC_CUSTOMER_CC");
		Assert.assertTrue(
				custLabels != null && custLabels.size() == 0);
		List<CustLabel> list = new ArrayList<CustLabel>();
		custLabelManager.updateCustLabel(list,"105873", "PC_CUSTOMER_CC", "-100");
		
	}
	/**
	 * 
	 * @Title: testSearchLabel
	 *  <p>
	 * @Description: 查询本部门的Label测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-18
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchLabel(){
		//执行查询操作
		List<Label> labels = custLabelManager.searchLabel("-100");
		boolean flag = true;
		//如果结果为空，返回false
		if (labels.size()==0) {
			flag = false;
		}
		//如果查询出来的数据的所属部门和查询条件不一致，返回false
		for(Label label:labels){
			if (!"-100".equals(label.getDeptId())) {
				flag = false;
			}
		}
		Assert.assertTrue(flag);
		try {
			custLabelManager.searchLabel(null);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 
	 * @Title: testSaveLabel
	 *  <p>
	 * @Description: 保存客户Label标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-18
	 * @return void
	 * @throws
	 */
	@Test
	public void testSaveLabel(){
		//模拟传入空对象的情况
		Label label = null;
		try {
			custLabelManager.saveLabel(label);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.Label_Miss_Error.getErrCode());
		}
		//模拟传入部门ID为空的情况
		label = new Label();
		try {
			custLabelManager.saveLabel(label);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelDeptId_Miss_Error.getErrCode());
		}
		//模拟传入的标签名字为空的情况
		try {
			label.setDeptId("-100");
			custLabelManager.saveLabel(label);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelName_Miss_Error.getErrCode());
		}
		//模拟传入的标签名字超过6个字的情况
		try {
			label.setLabelName("一二三四五六七");
			custLabelManager.saveLabel(label);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelName_Miss_Error.getErrCode());
		}
		//模拟传入的标签名已经存在的情况
		try {
			label.setLabelName("IT民工");
			custLabelManager.saveLabel(label);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelName_Exist_Error.getErrCode());
		}
		//模拟输入正常数据的情况
		label.setLabelName("拜拜，爱过");
		label.setCreateUser("86301");
		//执行保存操作
		custLabelManager.saveLabel(label);
		//校验是否保存成功
		List<Label> labels = 
		custLabelDao.queryLabelByDeptId("-100");
		boolean flag = false;
		//若是数据和预期的改变不一致，则返回false
		for(Label label2:labels){
			if ("拜拜，爱过".equals(label2.getLabelName())) {
				flag = true;
				//清理测试数据
				custLabelDao.deleteLabel(label2.getId());		
			}
		}
		Assert.assertTrue(flag);
	}
	/**
	 * 
	 * @Title: testDeleteLabel
	 *  <p>
	 * @Description: 删除客户Label标签测试
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-18
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeleteLabel(){
		//模拟标签ID为空的情况
		try {
			custLabelManager.deleteLabel(null);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelId_Miss_Error.getErrCode());
		}
		//执行正常操作
		custLabelManager.deleteLabel("101");
		//校验结果
		Assert.assertNull(custLabelDao.queryLabelById("101"));
	}
	/**
	 * 
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description: 删除客户custLabel标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-3
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeleteCustLabel(){
		//模拟传如空的客户ID的情况
		try {
			custLabelManager.deleteCustLabel(null, null);
			Assert.fail("客户ID为空，没抛异常？oh，shit！");
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.CustId_Miss_Error.getErrCode());
		}
		//模拟传入空的客户类型的情况
		try {
			custLabelManager.deleteCustLabel("105873", null);
			Assert.fail("客户类型为空，没抛异常？oh，shit！");
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.CustType_Miss_Error.getErrCode());
		}
		//模拟正常传值的情况下执行删除操作
		custLabelManager.deleteCustLabel("105873","PC_CUSTOMER" );
		//校验是否删除成功
		Assert.assertTrue(custLabelDao.queryCustLabel("105873", "PC_CUSTOMER").size()==0);
	}
	/**
	 * 
	 * @Title: testModifyLabel
	 *  <p>
	 * @Description: 客户标签基础资料修改测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-4
	 * @return void
	 * @throws
	 */
	@Test
	public void testModifyLabel(){
		Label label = null;
		//测试当label为空的时候
		try {
			custLabelManager.modifyLabel(label);
			Assert.fail("标签基础资料为空，应该出异常");
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.Label_Miss_Error.getErrCode());
		}
		//部门id为空的时候
		label = new Label();
		try {
			custLabelManager.modifyLabel(label);
			Assert.fail("标签基础资料部门ID为空，应该出异常");
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.LabelDeptId_Miss_Error.getErrCode());
		}
		//标签名字超过六个字长度的时候
		label.setDeptId("-100");
		label.setLabelName("1234567");
		try {
			custLabelManager.modifyLabel(label);
			Assert.fail("标签基础资料名字长度超过6，应出异常");
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.LabelName_Miss_Error.getErrCode());
		}
		//标签ID为空的时候
		label.setLabelName("许二叉基友");
		try {
			custLabelManager.modifyLabel(label);
			Assert.fail("标签ID为空，应出异常");
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.Label_Id_Error.getErrCode());
		}
		//正常输入的情况下
		label.setId("101");
		custLabelManager.modifyLabel(label);
		Label label2 = custLabelDao.queryLabelById("101");
		Assert.assertNotNull(label);
		Assert.assertEquals(label2.getLabelName(), label.getLabelName());
	}
}
