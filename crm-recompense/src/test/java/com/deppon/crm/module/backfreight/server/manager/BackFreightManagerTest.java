package com.deppon.crm.module.backfreight.server.manager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.manager.impl.BackFreightManager;
import com.deppon.crm.module.backfreight.server.service.IBackFreightService;
import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.util.TestUtil;

public class BackFreightManagerTest {

	private static BackFreightManager backFreightManager;
	static Connection conn = null;

	static BackFreight backFreight = new BackFreight();
	static BackFreight newBackFreight = null;
	static User user = null;
	static Mockery context = new Mockery();
	final static IBackFreightService backFreightService;

	static {
		backFreightService = context.mock(IBackFreightService.class);

		user = new User();
		Employee employee = new Employee();
		Department deparment = new Department();
		deparment.setId("49311");
		deparment.setDeptName("上海闵行区浦江镇营业部");
		employee.setId("18313");
		employee.setDeptId(deparment);
		user.setEmpCode(employee);
		backFreightManager = TestUtil.backFreightManager;
		backFreightManager.setBackFreightService(backFreightService);
		backFreight.setWaybillNumber("867867222");
		backFreight.setApplyAmount(1000);
		backFreight.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR);
		backFreight.setWaybillAmount(10000.0);
		backFreight.setCustomerNum("12154142");
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(new FileInfo());
		backFreight.setFileInfoList(fileInfoList);
		backFreight.setFinanceDept("14571");
		backFreight.setApplicant("18706");
		backFreight.setApplyDept("14571");
		backFreight.setStowageDept("14571");

	}

	/**
	 * 
	 * <p>
	 * Description:查询退运费<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-23 void
	 */
	@Test
	public void testFindValidBackFreightByNum() {
		Mockery context1 = new Mockery();

		context1.checking(new Expectations() {
			{
				// 当传入的运单号为某某时候
				oneOf(backFreightService).findValidBackFreightByNum("2222222");
				backFreight.setId(null);
				will(returnValue(backFreight));
			}
		});
		BackFreight bac1 = backFreightManager
				.findValidBackFreightByNum("2222222");
		System.out.println(bac1);
	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询退运费<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-23 void
	 */
	@Test
	public void testGetBackFreightById() {
		

		

		context.checking(new Expectations() {
			{
				// 当传入的运单号为某某时候
				exactly(1).of(backFreightService).getBackFreightById("121");
				backFreight.setId(null);
				will(returnValue(backFreight));
			}
		});
		try {
			backFreightManager.getBackFreightById("121");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-23 void
	 */
	@Test
	public void testExportBackFreightByCondition() {
		BackFreightSearchCondition condition = new BackFreightSearchCondition();
		List<String> waybillNumbers = new ArrayList<String>();
		waybillNumbers.add("867867222");
		waybillNumbers.add("55551001");
		condition.setWaybillNumbers(waybillNumbers);
		condition.setEndDate(new Date());
		condition.setStartDate(new Date(System.currentTimeMillis() - 24 * 60
				* 60 * 1000));
		backFreightManager.exportBackFreightByCondition(condition);

	}

	/**
	 * 
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-23 void
	 */
	@Test
	public void testCountBackFreightByCondition() {
		BackFreightSearchCondition condition = new BackFreightSearchCondition();
		List<String> waybillNumbers = new ArrayList<String>();
		waybillNumbers.add("867867222");
		waybillNumbers.add("55551001");
		condition.setWaybillNumbers(waybillNumbers);
		condition.setEndDate(new Date());
		condition.setStartDate(new Date(System.currentTimeMillis() - 24 * 60
				* 60 * 1000));
		backFreightManager.countBackFreightByCondition(condition);

	}

	/**
	 * 
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-23 void
	 */
	@Test
	public void testSearchBackFreightByCondition() {
		BackFreightSearchCondition condition = new BackFreightSearchCondition();
		List<String> waybillNumbers = new ArrayList<String>();
		waybillNumbers.add("867867222");
		waybillNumbers.add("55551001");
		condition.setWaybillNumbers(waybillNumbers);
		condition.setEndDate(new Date());
		condition.setStartDate(new Date(System.currentTimeMillis() - 24 * 60
				* 60 * 1000));
		backFreightManager.searchBackFreightByCondition(condition);

	}

	@Test
	public void testFindWaybillNumber() {

		

		backFreightManager.setBackFreightService(backFreightService);

		context.checking(new Expectations() {
			{
				// 当传入的运单号为某某时候
				//exactly(1).of(backFreightService).findWaybillByNum("201250901", user);
				backFreight.setId(null);
				will(returnValue(backFreight));
			}
		});

		try {
			backFreightManager.findWaybillByNum("201250901", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSubmitBackFreight() {
		backFreight.setWaybillNumber("867867222");
		backFreight.setApplyAmount(1000);
		backFreight.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR);
		backFreight.setWaybillAmount(10000.0);
		backFreight.setCustomerNum("12154142");
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(new FileInfo());
		backFreight.setFileInfoList(fileInfoList);
		backFreight.setFinanceDept("14571");
		backFreight.setApplicant("18706");
		backFreight.setApplyDept("14571");
		backFreight.setStowageDept("14571");
		backFreight
				.setApplyStatus("BackFreightConstant.BACK_FREIGHT_STATUS_WAIT_ACCEPT");
		try {
			backFreightManager.submitBackFreight(backFreight, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		backFreight.setCustomerNum(null);
		try {
			backFreightManager.submitBackFreight(backFreight, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO
	@Test
	public void testReturnBackFreightStatus() {

		backFreight
				.setApplyStatus(BackFreightConstant.BACK_FREIGHT_STATUS_WAIT_ACCEPT);
		context.checking(new Expectations() {
			{
				//
				oneOf(backFreightService).getBackFreightByOaWorkFlowNum(
						"6110253");
				will(returnValue(backFreight));
			}
			{
				//
				oneOf(backFreightService).updateBackFreight(backFreight);

				// will(returnValue(backFreight));
			}
			{
				//
				oneOf(backFreightService).ServiceChargeStatusUpdate(
						backFreight.getWaybillNumber(), false);

			}
			{
				//
				oneOf(backFreightService).submitBackFreightPayment(backFreight);
			}
		});
		try {

			backFreightManager.returnBackFreightStatus("6110253", "035036",
					true, new Date(), "aaaa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testsearchBackFreightByCondition() {
		BackFreightSearchCondition condition = new BackFreightSearchCondition();
		List<String> waybillNumbers = new ArrayList<String>();
		condition.setEndDate(new Date());
		condition.setStartDate(new Date());
		
		backFreightManager.searchBackFreightByCondition(condition);
		waybillNumbers.add("867867222");
		waybillNumbers.add("55551001");
		condition.setWaybillNumbers(waybillNumbers);
		condition.setEndDate(new Date());
		condition.setStartDate(new Date(System.currentTimeMillis() - 24 * 60
				* 60 * 1000));
		try {
			backFreightManager.searchBackFreightByCondition(condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
