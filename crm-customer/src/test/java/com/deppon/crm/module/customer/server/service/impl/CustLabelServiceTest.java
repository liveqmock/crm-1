package com.deppon.crm.module.customer.server.service.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.customer.server.dao.ICustLabelDao;
import com.deppon.crm.module.customer.server.dao.impl.CustLabelDao;
import com.deppon.crm.module.customer.server.service.ICustLabelService;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;

public class CustLabelServiceTest extends TestCase{
	private ICustLabelService custLabelService
		= SpringTestHelper.getBean(CustLabelService.class);
	private ICustLabelDao custLabelDao
		=SpringTestHelper.getBean(CustLabelDao.class);
	//初始化测试数据	
	public void setUp() throws SQLException{
		DataTestUtil.cleanCustLabelData();
		DataTestUtil.initCustLabelData();
	}
	//清理测试数据
	public void tearDown() throws SQLException{
		DataTestUtil.cleanCustLabelData();
	}
	/**
	 * 
	 * @Title: testSearchLabelByDeptId
	 *  <p>
	 * @Description: 通过部门Id查询客户Label标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchLabelByDeptId(){
		Assert.assertTrue(
				custLabelService.searchLabelByDeptId("-100").size()>0);
	}
	/**
	 * 
	 * @Title: testSaveLabel
	 *  <p>
	 * @Description: 保存客户Label标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testSaveLabel(){
		Label label = new Label();
		Date date = new Date();
		label.setCreateDate(date);
		label.setCreateUser("105873");
		label.setDeptId("1");
		label.setLabelName("糖糖测试数据");
		label.setModifyDate(date);
		label.setModifyUser("105873");
		//执行保存Label方法
		custLabelService.saveLabel(label);
		//校验数据是否保存成功
		List<Label> labels = custLabelDao.queryLabelByDeptId("1");
		Assert.assertTrue(
				labels!=null && labels.size()>0);
	
		//清理测试产生的垃圾数据
		for(Label labell:labels){
			if ("1".equals(labell.getDeptId())) {
				custLabelDao.deleteLabel(labell.getId());
			}
		}
	}
	/**
	 * 
	 * @Title: testDeleteLabel
	 *  <p>
	 * @Description: 删除客户Label标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeleteLabel(){
		//执行删除操作
		custLabelService.deleteLabel("101");
		// 校验是否删除成功
		Assert.assertNull(custLabelDao.queryLabelById("101"));
		
		List<CustLabel> labels 
			= custLabelDao.queryCustLabel("105873", "PC_CUSTOMER");
		for(CustLabel custLabel:labels){
			Assert.assertTrue(!"101".equals(custLabel.getLabel().getId()));
		}
	}
	/**
	 * 
	 * @Title: testSaveCustLabel
	 *  <p>
	 * @Description: 保存客户CUSTLABEL标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testSaveCustLabel(){
		List<CustLabel> labels = new ArrayList<CustLabel>();
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
		label.setDeptId("-200");
		custLabel.setLabel(label);
		labels.add(custLabel);
		//执行操作
		custLabelService.saveCustLabel(labels);
		//校验数据是否保存成功
		labels = custLabelDao.queryCustLabel("105873", "PC_CUSTOMER_TEST");
		Assert.assertTrue(labels != null && labels.size() > 0);
		//清理测试产生的垃圾数据
		custLabelDao.deleteCustLabel("105873", "PC_CUSTOMER_TEST");
	}
	/**
	 * 
	 * @Title: testUpdateCustLabel
	 *  <p>
	 * @Description: 修改客户CUSTLABEL标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testUpdateCustLabel(){
		List<CustLabel> labels = new ArrayList<CustLabel>();
		Date date = new Date();
		CustLabel custLabel = new CustLabel();
		custLabel.setCustId("105873");
		custLabel.setCustType("PC_CUSTOMER");
		custLabel.setCreateDate(date);
		custLabel.setCreateUser("105873");
		Label label = new Label();
		label.setId("1");
		label.setDeptId("-200");
		custLabel.setLabel(label);
		labels.add(custLabel);
		//执行update操作
		custLabelService.updateCustLabel(labels);
		//校验update结果是否正常
		labels = custLabelDao.queryCustLabel("105873", "PC_CUSTOMER");
		boolean i = false;
		for(CustLabel custLabelData:labels){
			if("1".equals(custLabelData.getLabel().getId())){
				i = true;
			}
		}
		Assert.assertTrue(i);
	}
	/**
	 * 
	 * @Title: testSearchCustLabel
	 *  <p>
	 * @Description: 根据客户Id和客户类型查询客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchCustLabel(){
		//执行查询操作
		List<CustLabel> labels = custLabelService.searchCustLabel("105873", "PC_CUSTOMER");
		//校验查询结果是否正常
		Assert.assertTrue(labels!=null && labels.size()>0);
	}
	/**
	 * 
	 * @Title: testDeleteCustLabel
	 *  <p>
	 * @Description: 根据客户Id和客户类型删除客户CustLabel标签测试方法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	@Test
	public void testDeleteCustLabel(){
		//执行删除操作
		 custLabelService.deleteCustLabel("105873", "PC_CUSTOMER");
		//校验是否删除成功
		 List<CustLabel> labels 
			= custLabelDao.queryCustLabel("105873", "PC_CUSTOMER");
		Assert.assertTrue(labels != null && labels.size()==0);	
	}
}