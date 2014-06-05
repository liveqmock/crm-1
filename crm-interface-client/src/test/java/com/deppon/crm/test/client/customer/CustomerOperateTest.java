package com.deppon.crm.test.client.customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.Assert;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.customer.domain.AnalysisCustomerType;
import com.deppon.crm.module.client.customer.domain.CustomerCancel;
import com.deppon.crm.module.client.customer.domain.PathAnalysisInfo;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.erp.custAndOrder.ExceptionCust;

public class CustomerOperateTest extends OperaterTest {
	
	/**
	 * 网厅注册用户：xhb_paytest
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-6
	 *
	 */
	
	
	@Test
	public void bindToContactTest(){
		try {
			boolean result = customerOperate.bindToContact("","xhb_paytest");
			System.out.println(result);
			Assert.isTrue(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryCustomerCancel(){
		try {
			// SELECT FCUSTNUMBER FROM T_CUST_CUSTBASEDATA
	        //WHERE FCUSTSTATUS=2 AND FISFINOVER=0
			//001096 001365 003420
				List<String> customerNumberList=new ArrayList<String>();
				customerNumberList.add("S20130522-00001307");
				List<CustomerCancel> result = customerOperate.queryCustomerCancelOrNot(customerNumberList);
				System.out.println(result);
				//Assert.notNull(result);
//			List<String> customerNumberList=new ArrayList<String>();
//			customerNumberList.add("001096");
//			customerNumberList.add("001365");
//			customerNumberList.add("003420");
//			List<CustomerCancel> result = customerOperate.queryCustomerCancelOrNot(customerNumberList);
//			System.out.println(result);
//			Assert.notNull(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
	}
	}
	@Test
	public void queryCustInfoTest(){
		List<String> list = new ArrayList<String>();
		list.add("20081021-012776");
		list.add("20110226-06008702");
		for(int i =0;i< 50;i++){
			list.add("20081021-012776"+i);
			list.add("20110226-06008702"+i);
		}
		try {
			List<ExceptionCust>  elist = customerOperate.queryERPCustInfo(list);
			System.out.println(elist.size());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryRegisterUserTest(){
		try {
			RegisterInfo registerInfo = customerOperate.queryRegisterUser("zhujing13");
			System.out.println(registerInfo);
//			log.debug("网厅注册用户信息："+registerInfo.toString());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 20101218-05599001   20101218-05599686  20101218-05599111   20101218-06599686
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-25
	 *
	 */
	
	@Test
	public void queryPathAnalysisTest(){
		try {
			
			List<PathAnalysisInfo> infos = customerOperate.queryPathAnalysis("309032", 
					new SimpleDateFormat("yyyy-MM-dd").parse("2012-10-31"),AnalysisCustomerType.CUSTOMER_ARRIVED);
			
			Assert.notNull(infos);
			System.out.println(JsonMapperUtil.writeValue(infos));
			if (log.isDebugEnabled()) {
				log.debug(infos.size());
			}
			
			
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 20101218-05599001   20101218-05599686  20101218-05599111   20101218-06599686
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-25
	 *
	 */
	@Test
	public void queryPathAnalysisTest1(){
		try {
			customerOperate.queryPathAnalysis("20101218-06599686", new SimpleDateFormat("yyyy-MM-dd").parse("2012-05-24"),AnalysisCustomerType.CUSTOMER_ARRIVED);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
