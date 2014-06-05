package com.deppon.crm.module.servicerecovery.server.manager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.servicerecovery.server.dao.impl.ServiceRecoveryDao;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.servicerecovery.server.util.ServiceRecoveryConstant;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * Description:服务补救DAO测试 Create on 2012-11-1 下午3:04:04
 * 
 * @author 华龙
 * @version 1.0
 */
public class ServiceRecoveryManagerTest {
	private static IServiceRecoveryManager serviceRecoveryManager;
	static ServiceRecovery serviceRecovery = new ServiceRecovery();
	static User user = new User();
	static ServiceRecoveryDao serviceRecoveryDao = null;
	static {

		Employee e = new Employee();
		e.setId("18313");
		e.setPosition("经理");
		Department d = new Department();
		d.setId("49311");
		e.setDeptId(d);
		user.setEmpCode(e);
	
		serviceRecoveryManager = TestUtil.serviceRecoveryManager;
		
		serviceRecovery.setWaybillNumber("201242410");
		serviceRecovery
				.setCustomerType(ServiceRecoveryConstant.SERVICE_RECOVERY_CUSTOMER_TYPE_CONSIGNEE);
		serviceRecovery.setCustomerNum("123456");
		serviceRecovery.setCustomerName("上海德邦物流有限公司");
		serviceRecovery.setWaybillAmount(99d);
		serviceRecovery.setReductionAmount(88d);
		serviceRecovery.setOutboundTime(new Date());
		serviceRecovery.setFinanceDept("123456");
		serviceRecovery.setOperator("106138");
		serviceRecovery
				.setReductionType(ServiceRecoveryConstant.SERVICE_RECOVERY_REDUCTION_DAMAGE);
		serviceRecovery.setTotalPackage(4);
		serviceRecovery.setDamagePackage(3);
		serviceRecovery.setFinanceDept("354");
		serviceRecovery.setOperator("531096");
		serviceRecoveryDao=TestUtil.serviceRecoveryDao;
	}

	@Before
	public void setUp() throws Exception {

	}

	/**
	 * 
	 * <p>
	 * Description:为退运费提高根据运单号查询运单实体<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-25 void
	 */
	@Test
	public void testFindWaybillByNumForBackFreight() {
		// TODO 返回的数据还是假借口
		try{
			serviceRecoveryManager.findWaybillByNumForBackFreight("111111112");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * <p>
	 * Description:根据运单号查询运单实体测试<br />
	 * </p>
	 *  
	 * @author 华龙
	 * @version 0.1 2012-12-25 void
	 */
	@Test
	public void testFindWaybillByNum() {
		User user = new User();
		Employee e = new Employee();
		Department d = new Department();
		d.setStandardCode("DP01991");
		e.setDeptId(d);
		user.setEmpCode(e);
		try {
			serviceRecoveryManager.findWaybillByNum("111111112", user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:提交服务补救测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-25 void
	 */
	@Test
	public void testSubmitServiceRecovery() {
		try {
		serviceRecoveryManager.submitServiceRecovery(serviceRecovery, user);
		System.out.println(serviceRecovery.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		serviceRecovery.setCustomerNum("");
		serviceRecovery.setWaybillNumber("201250901");
		serviceRecovery.setCustomerType(
				ServiceRecoveryConstant.SERVICE_RECOVERY_CUSTOMER_TYPE_SHIPPER);
		try {
			serviceRecoveryManager.submitServiceRecovery(serviceRecovery, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		serviceRecovery.setWaybillNumber("10552365");
//		try {
//			serviceRecoveryManager.submitServiceRecovery(serviceRecovery, user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * 
	 * <p>
	 * Description:OA回调测试<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-25 void
	 */
	@Test
	public void testReturnServiceRecoveryStatus() {
		serviceRecovery.setApplyStatus(
				ServiceRecoveryConstant.SERVICE_RECOVERY_STATUS_WAIT_ACCEPT);
		serviceRecovery.setVerifyTime(new Date());
		serviceRecovery.setVerifier("18313");
		serviceRecovery.setId("50");
		serviceRecovery.setWaybillNumber("111111232");
		serviceRecovery.setOaWorkflowNum("6105626");
		try {
			serviceRecoveryDao.updateServiceRecovery(serviceRecovery);
			serviceRecoveryManager.returnServiceRecoveryStatus("6105626",
					"035036", true, new Date(), "hha");
			serviceRecoveryManager.returnServiceRecoveryStatus("00",
					"035036", true, new Date(), "hha");
			serviceRecoveryManager.returnServiceRecoveryStatus("6105626",
					"035036", true, new Date(), "hha");
		} catch (Exception e) {
			e.printStackTrace();
		}
		serviceRecovery.setApplyStatus(ServiceRecoveryConstant.SERVICE_RECOVERY_STATUS_WAIT_ACCEPT);
		try {
			serviceRecoveryManager.returnServiceRecoveryStatus("6105628",
					"035036", true, new Date(), "hha");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetServiceRecoveryById() {

		serviceRecoveryManager
				.getServiceRecoveryById("6");
	}
	@Test
	public void testSearchServiceRecoveryByCondition(){
		ServiceRecoverySearchCondition condition = new ServiceRecoverySearchCondition();
		try{
			serviceRecoveryManager.searchServiceRecoveryByCondition(condition );
		}catch(Exception e){
			e.printStackTrace();
		}
		List<String> waybillNumbers = new ArrayList<String>();
		waybillNumbers.add("201242408");
		waybillNumbers.add("201250219");
		condition.setWaybillNumbers(waybillNumbers);
		condition.setEndDate(new Date());
		condition.setStartDate(new Date(System.currentTimeMillis()-24*60*60*1000*30));
		try{
			serviceRecoveryManager.searchServiceRecoveryByCondition(condition );
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testCountServiceRecoveryByCondition(){
		ServiceRecoverySearchCondition condition=new ServiceRecoverySearchCondition();
		condition.setStartDate(new Date());
		condition.setEndDate(new Date());
		serviceRecoveryManager.countServiceRecoveryByCondition(condition);
	}
	@Test
	public void testExportServiceRecoveryByCondition(){
		ServiceRecoverySearchCondition condition=new ServiceRecoverySearchCondition();
		condition.setStartDate(new Date());
		condition.setEndDate(new Date());
		serviceRecoveryManager.exportServiceRecoveryByCondition(condition);
	}
	@Test
	public void testFindValidServiceRecoveryByNum(){
		serviceRecoveryManager.findValidServiceRecoveryByNum("201242408");
	}
	@Test
	public void testGetSubsidiaryByDeptStandardCode(){
		try{
		serviceRecoveryManager.getSubsidiaryByDeptStandardCode("DP00097");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
