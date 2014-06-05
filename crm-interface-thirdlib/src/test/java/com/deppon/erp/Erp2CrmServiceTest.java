package com.deppon.erp;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.erp.waybill.ERPBusinessException_Exception;
import com.deppon.erp.waybill.IErp2CrmService;
import com.deppon.erp.waybill.WayBillNotSign;
import com.deppon.erp.waybill.WayBillSimpleArray;

public class Erp2CrmServiceTest {

	IErp2CrmService erp2CrmService;
	@Before
	public void init(){
//		Erp2CrmServiceImplService service = new Erp2CrmServiceImplService();
//		erp2CrmService = service.getErp2CrmServiceImplPort();
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
//		factoryBean.setAddress("http://192.168.17.41:8080/eas2fin/services/erp2crmService");
		factoryBean.setAddress("http://192.168.11.31:8080/eas544ws/services/erp2crmService");
		factoryBean.setServiceClass(IErp2CrmService.class);
		erp2CrmService = (IErp2CrmService) factoryBean.create();
		
	}
	
	@Test
	public void queryNear3MonthAmountTest(){
		BigDecimal result;
		try {
//			result = erp2CrmService2.queryNear3MonthAmount("20120220-08933563");
			result = erp2CrmService.queryNear3MonthAmount("");
			System.out.println(result);
		} catch (ERPBusinessException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getFaultInfo().getErrorCode());
		}
	}
	
	/**
	 * ERP-5,联系人编码 AND 时间范围查询运单信息，未积分运单
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-26
	 * @throws ParseException 
	 *
	 */
	@Test
	public void queryWayBillSimpleTest() throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.parse("2012-04-01");
		
		try {
			WayBillSimpleArray wayBillSimples = erp2CrmService.queryWayBillSimple(
					"sadfasdf", dateFormat.parse("2012-04-07"), 
					dateFormat.parse("2012-04-14"));
			System.out.println(wayBillSimples.getItem().size());
		} catch (ERPBusinessException_Exception e) {
			e.printStackTrace();
			System.out.println(e.getFaultInfo().getErrorCode());
			
		}
		
	}
	/**
	 * ERP-9
	 * 定于单关联测试
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-26
	 *
	 */
	@Test
	public void linkWayBillAndOrderBillTest(){
		try {
			erp2CrmService.linkWayBillAndOrderBill(null,null, null);
			
		} catch (ERPBusinessException_Exception e) {
			System.out.println(e.getFaultInfo().getErrorCode());
		}
		
		try {
			erp2CrmService.linkWayBillAndOrderBill("", "asdfasdf", null);
		} catch (ERPBusinessException_Exception e) {
			System.out.println(e.getFaultInfo().getErrorCode());
		}
		try {
			//如果订单号为空值或者为null，需做检查
			boolean flag = erp2CrmService.linkWayBillAndOrderBill("12040701", "", null);
			System.out.println(flag);
		} catch (ERPBusinessException_Exception e) {
			System.out.println(e.getFaultInfo().getErrorCode());
		}
		
	}
	
	/**
	 * ERP-10客户发到货金额查询，时时创建会员
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-26
	 *
	 */
	@Test
	public void queryCustAmountTest(){
		
	}
	
	/**
	 * ERP-11
	 * 查询未签收运单
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-26
	 *
	 */
	@Test
	public void queryWayBillNotSignTest(){
		try {
			WayBillNotSign wayBillNotSign = erp2CrmService.queryWayBillNotSign("12040701");
			System.out.println(wayBillNotSign.getShipper());
			System.out.println(wayBillNotSign.getShipperAddr());
			System.out.println(wayBillNotSign.getShipperMobile());
			System.out.println(wayBillNotSign.getShipperPhone());
		} catch (ERPBusinessException_Exception e) {
			System.out.println(e.getFaultInfo().getErrorCode());
			e.printStackTrace();
		}
	}
	
	/**
	 * ERP-12，理赔上报的时候需要查询的运单信息
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-26
	 *
	 */
	@Test
	public void queryWayBillTest(){
		
	}
	
	/**
	 * ERP-13，在线理赔校验运单号是否可以理赔如果可以理赔则调用
	 * @param       
	 * @return 
	 * @Throws 
	 * @author davidcun 2012-4-26
	 *
	 */
	@Test
	public void queryWayBillRecompenseTest(){
		
	}
	
	/**
	 * ERP-2，走ESB此处不测
	 * 接货单新增
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-26
	 *
	 */
	@Test
	public void addReceiveGoodsBillTest(){
		
	}
	
	
	
}
