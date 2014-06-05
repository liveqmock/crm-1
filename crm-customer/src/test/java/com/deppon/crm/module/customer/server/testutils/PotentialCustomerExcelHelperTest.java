package com.deppon.crm.module.customer.server.testutils;

import org.junit.Assert;
import org.junit.Test;
import com.deppon.crm.module.customer.server.utils.PotentialCustomerExcelHelper;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.exception.ExcelImportException;
import com.deppon.crm.module.customer.shared.exception.ExcelImportExceptionType;

public class PotentialCustomerExcelHelperTest {

	/**
	 * 
	 * <p>
	 * Description:测试模板头验证<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_headValidate(){
		
		 String[] headers = new String[]{
				 PotentialCustomerExcelHelper.head_1 ,
				 PotentialCustomerExcelHelper.head_2 ,
				 PotentialCustomerExcelHelper.head_3,
				 PotentialCustomerExcelHelper.head_4,
				 PotentialCustomerExcelHelper.head_5 ,
				 PotentialCustomerExcelHelper.head_6 ,
				 PotentialCustomerExcelHelper.head_7 ,
				 PotentialCustomerExcelHelper.head_8 ,
				 PotentialCustomerExcelHelper.head_9 ,
				 PotentialCustomerExcelHelper.head_10,
				 PotentialCustomerExcelHelper.head_11 ,
				 PotentialCustomerExcelHelper.head_12 ,
		 };
//		boolean r_v = PotentialCustomerExcelHelper.headValidate(headers);
//		Assert.assertTrue(r_v);
		
		
		
		try{
			  headers = new String[]{
					 PotentialCustomerExcelHelper.head_1 ,
					 PotentialCustomerExcelHelper.head_2 ,
					 PotentialCustomerExcelHelper.head_3,
					 PotentialCustomerExcelHelper.head_4,
					 PotentialCustomerExcelHelper.head_5 ,
					 PotentialCustomerExcelHelper.head_6 ,
					 PotentialCustomerExcelHelper.head_7 ,
					 PotentialCustomerExcelHelper.head_8 ,
					 PotentialCustomerExcelHelper.head_9 ,
					 PotentialCustomerExcelHelper.head_10
					 };
				PotentialCustomerExcelHelper.headValidate(headers);
				
		}catch(ExcelImportException eie){
			Assert.assertEquals(ExcelImportExceptionType.HeadSizeError.getErrCode(), eie.getErrorCode());
		}
		
		try{
			  headers = new String[]{
					 PotentialCustomerExcelHelper.head_1 ,
					 PotentialCustomerExcelHelper.head_2 ,
					 PotentialCustomerExcelHelper.head_3,
					 PotentialCustomerExcelHelper.head_4,
					 PotentialCustomerExcelHelper.head_5 ,
					 PotentialCustomerExcelHelper.head_6 ,
					 PotentialCustomerExcelHelper.head_7 ,
					 PotentialCustomerExcelHelper.head_8 ,
					 PotentialCustomerExcelHelper.head_9 ,
					 PotentialCustomerExcelHelper.head_10,
					 "测试异常"
					 };
				PotentialCustomerExcelHelper.headValidate(headers);
				
		}catch(ExcelImportException eie){
			Assert.assertEquals(ExcelImportExceptionType.HeadSizeError.getErrCode(), eie.getErrorCode());
		}
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述
	 *    需要大量缓存，暂时放弃测试此工具方法
	 * <br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 * 
	 */
	
	public void test_getPotenCustomerByPotenRule(){
        
		Object[] datas = new Object[]{
				 PotentialCustomerExcelHelper.head_1 ,
				 PotentialCustomerExcelHelper.head_2 ,
				 PotentialCustomerExcelHelper.head_3,
				 PotentialCustomerExcelHelper.head_4,
				 PotentialCustomerExcelHelper.head_5 ,
				 PotentialCustomerExcelHelper.head_6 ,
				 PotentialCustomerExcelHelper.head_7 ,
				 PotentialCustomerExcelHelper.head_8 ,
				 PotentialCustomerExcelHelper.head_9 ,
				 PotentialCustomerExcelHelper.head_10,
				 PotentialCustomerExcelHelper.head_11 ,
		};
		
		PotentialCustomer pc = PotentialCustomerExcelHelper.getPotenCustomerByPotenRule(datas);
	     
		Assert.assertEquals(PotentialCustomerExcelHelper.head_1, pc.getCustName());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_2, pc.getTrade());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_3, pc.getCustbase());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_4, pc.getLinkManName());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_5, pc.getLinkManMobile());
		
		Assert.assertEquals(PotentialCustomerExcelHelper.head_6, pc.getLinkManPhone());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_7, pc.getPost());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_8, pc.getRecentcoop());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_9, pc.getCoopIntention());
		Assert.assertEquals(PotentialCustomerExcelHelper.head_10,pc.getVolumePotential() );
		Assert.assertEquals(PotentialCustomerExcelHelper.head_11, pc.getCustNeed());
		
	
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试得到字符串类型的值<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_getStringTypeValue(){
		
		String custName = PotentialCustomerExcelHelper.getStringTypeValue("custName", "客户名称");
		Assert.assertEquals("custName", custName);
		
		long phone = 13789564285l;
		try{
		 PotentialCustomerExcelHelper.getStringTypeValue(phone, "联系电话");
		}catch(ExcelImportException eie){
			Assert.assertEquals(ExcelImportExceptionType.TypeValueError.getErrCode(), eie.getErrorCode());
		}
		
	
	}
}
