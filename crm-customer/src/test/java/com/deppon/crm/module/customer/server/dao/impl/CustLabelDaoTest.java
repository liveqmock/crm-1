package com.deppon.crm.module.customer.server.dao.impl;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.dao.ICustLabelDao;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
/**
 * 
 * <p>
 * 客户标签测试
 * </p>
 * @title CustLabelDaoTest.java
 * @package com.deppon.crm.module.customer.server.dao.impl
 * @author 唐亮
 * @version 0.1 2013-4-12
 */
public class CustLabelDaoTest extends TestCase{
	private ICustLabelDao custLabelDao
	 	= SpringTestHelper.getBean(CustLabelDao.class);
	@Before
	public void setUp() throws Exception{
		//初始化客户标签测试数据
		DataTestUtil.cleanCustLabelData();
		DataTestUtil.initCustLabelData();
	} 
	@After
	public void tearDown() throws Exception{
		//清理客户标签测试数据
		DataTestUtil.cleanCustLabelData();
	}
	/**
	 * 
	 * @Title: testDeleteCustLabel
	 *  <p>
	 * @Description: 删除客户标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-12
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeleteCustLabel(){
		//执行删除操作
		custLabelDao.deleteCustLabel("105873","PC_CUSTOMER");
		//校验数据是否已经删除
		Assert.assertTrue(
				custLabelDao.queryCustLabel("105873", "PC_CUSTOMER").size()==0);
	}
	/**
	 * 
	 * @Title: testDeleteCustLabelById
	 *  <p>
	 * @Description: 根据客户Id和客户类型查询客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-16
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeleteCustLabelById(){
		//执行删除操作
		custLabelDao.deleteCustLabelById("101");
		//校验是否删除
		Assert.assertTrue(
				custLabelDao.queryCustLabel("105873", "PC_CUSTOMER").size()==0);
	}
	
	/**
	 * 
	 * @Title: queryCustLabel
	 *  <p>
	 * @Description: 根据客户Id和客户类型查询客户custLabel标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	@Test
	public void testQueryCustLabel(){
		Assert.assertNotNull(custLabelDao.queryCustLabel("105873", "PC_CUSTOMER").size()>0);
	}
	/**
	 * 
	 * @Title: queryCustLabelByDeptId
	 *  <p>
	 * @Description: 通过部门Id查询客户标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return CustLabel
	 * @throws
	 */
	@Test
	public void testQueryLabelByDeptId(){
		Assert.assertTrue(
				custLabelDao.queryLabelByDeptId("49311").size() > 0);
	}
	/**
	 * 
	 * @Title: testAddLabel
	 *  <p>
	 * @Description: 保存客户标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-16
	 * @return void
	 * @throws
	 */
	@Test
	public void testAddLabel(){
		Label label = new Label();
		Date date = new Date();
		label.setCreateDate(date);
		label.setCreateUser("123654");
		label.setDeptId("02586");
		label.setLabelName("唐亮专用测试");
		label.setModifyDate(date);
		label.setModifyUser("123654");
		custLabelDao.addLabel(label);
		List<Label> labels = custLabelDao.queryLabelByDeptId("02586");
		Assert.assertTrue(
				labels.size()>0);
		//测试完成，清理掉测试造成的垃圾数据
		for(Label labellLabel : labels){
			if("唐亮专用测试".equals(labellLabel.getLabelName())){
				custLabelDao.deleteLabel(labellLabel.getId());
			}
		}
		
		
	}
	/**
	 * 
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description: 通过标签Id删除客户标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeleteLabel(){
		//执行删除操作
		custLabelDao.deleteLabel("101");
		//校验数据是否删除成功
		Assert.assertNull(
				custLabelDao.queryLabelById("101"));
	}
	/**
	 * 
	 * @Title: testSaveCustLabel
	 *  <p>
	 * @Description: 保存客户CUSTLABEL标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-16
	 * @return void
	 * @throws
	 */
	@Test
	public void testSaveCustLabel(){
		CustLabel custLabel = new CustLabel();
		Date date = new Date();
		custLabel.setCreateDate(date);
		custLabel.setCreateUser("105873");
		custLabel.setCustId("105873");
		custLabel.setCustType("PC_CUSTOMER_TEST");
		custLabel.setModifyDate(date);
		custLabel.setModifyUser("105873");
		Label label = new Label();
		label.setId("101");
		label.setDeptId("-100");
		custLabel.setLabel(label);
		//执行保存操作
		custLabelDao.saveCustLabel(custLabel);
		//校验是否保存成功
		Assert.assertTrue(
				custLabelDao.queryCustLabel("105873", "PC_CUSTOMER_TEST").size()>0);
		//清理测试造成的垃圾数据
		custLabelDao.deleteCustLabel("105873", "PC_CUSTOMER_TEST");
	}
	/**
	 * 
	 * @Title: testQueryLabelById
	 *  <p>
	 * @Description: 通过LabelId查询对应的label标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testQueryLabelById(){
		Assert.assertNotNull(
				custLabelDao.queryLabelById("101"));
	}
}
