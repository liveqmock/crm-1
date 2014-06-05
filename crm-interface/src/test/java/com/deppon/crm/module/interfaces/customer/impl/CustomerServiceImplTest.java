package com.deppon.crm.module.interfaces.customer.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.client.common.util.XmlJaxbMapper;
import com.deppon.crm.module.client.sync.domain.OrderRightInfo;
import com.deppon.crm.module.client.sync.domain.OrderRightRequest;
import com.deppon.crm.module.interfaces.customer.IBHOCustomerService;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindRequest;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindResponse;
import com.deppon.crm.module.interfaces.customer.domain.Member;
import com.deppon.crm.module.interfaces.customer.domain.QueryClaimInfoResponse;

/**
 * @作者：罗典
 * @描述：接口-客户模块测试
 * @时间：2012-4-25
 * */
public class CustomerServiceImplTest {

	
	IBHOCustomerService bHOCustomerService;
	
	@Before
	public void setUp(){
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.setAddress("http://localhost:8088/crm/ws/bhoCustomerService");
		jax.setServiceClass(IBHOCustomerService.class);
		bHOCustomerService = (IBHOCustomerService)jax.create();
	}
	
	/**
	 * @throws CrmBusinessException 
	 * @throws UnsupportedEncodingException 
	 * @作者：罗典
	 * @时间：2012-4-24
	 * @功能描述：根据联系人编码查询联系人信息
	 */ 
	@Test
	public void testQueryContactByContactNum() throws CrmBusinessException, UnsupportedEncodingException {
		// 应付单申请QName;
		final QName _ClaimsPayBill_QNAME = new QName(
				"http://www.deppon.com/crm/inteface/foss/domain",
				"orderRightRequest");
		OrderRightRequest request = new OrderRightRequest();
		OrderRightInfo info = new OrderRightInfo();
		info.setDepartment("ddd");
		info.setOperateTime(new Date()); 
		info.setOperateType("dddd");
		info.setOrderTeam("dddd");
		request.setOrderRightInfo(info);
		String sss = XmlJaxbMapper.writeValue(request, _ClaimsPayBill_QNAME);
		System.out.println(sss);
		request = (OrderRightRequest) XmlJaxbMapper 
				.readValue(sss, OrderRightRequest.class);
		bHOCustomerService.queryContactByContactNum(sss);
	}
	@Test
	public void testQueryClaimInfo()throws CrmBusinessException{
		
			QueryClaimInfoResponse response = bHOCustomerService
					.queryClaimInfos("13524455216", 1, 1);
			System.out.println(response.getClamInfos());
			
		
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-24
	 * @功能描述：根据网单号和部门编码和联系人编码查询会员信息
	 */
	@Test
	public void testQueryCustomerByCode() {
		
		try {
			Member member = bHOCustomerService.queryCustomerById("DP01430", "46687");
			System.out.println(member.getId());
		} catch (CrmBusinessException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	} 
 
	@Test 
	public void test() throws CrmBusinessException {
		ContactCustBindResponse response=new ContactCustBindResponse();
		ContactCustBindRequest request=new ContactCustBindRequest();
		request.setAddress("东浦");
		request.setRealName("吴根");
		request.setUserName("wugenbin");
		request.setCustsource("ONLINE");
		request.setCusCode("125448");
		request.setTelephone("12345678");
		request.setFixedPhone("20");
		request.setOperateType("1");
		response=bHOCustomerService.bindContact(request); 
		
	} 
}   
 