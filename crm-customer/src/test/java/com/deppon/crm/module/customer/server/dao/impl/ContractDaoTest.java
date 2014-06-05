package com.deppon.crm.module.customer.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.server.utils.CreateRandomNumber;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractDto;
import com.deppon.crm.module.customer.shared.domain.ContractMonthendDay;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.opensymphony.xwork2.interceptor.annotations.After;

/**
 * @author 赵斌
 * 
 */
public class ContractDaoTest extends BeanUtil{

	@Before
	public void setUp() throws Exception{
		DBUtils.clean();

		// 初始化DAO层测试数据
		DBUtils.initContract();
	}

	@After
	public void tearDown() throws Exception {
		// 清理DAO层测试数据
		DBUtils.clean();
	}
	
	/**
	 * 
	 * @得到合同所有信息
	 * @author 赵斌
	 * @2012-4-17
	 * @return
	 */
	@Test
	public void testGetAllContractInfoById() {
		String contractId = "1000001";
		Contract contract = contractDao.getAllContractInfoById(contractId);
//		Assert.assertNotNull("查询不为空",contract);
		
	}

	/**
	 * 
	 * @合同新增
	 * @author 赵斌
	 * @2012-3-28
	 * @return
	 */
	@Test
	public void testcreateContract() {
		User user = userDao.getUserRoleFunDeptById("019124");
		UserContext.setCurrentUser(user);

		// 获取四位随机验证码
		CreateRandomNumber randomNumber = new CreateRandomNumber();
		String rnumber = randomNumber.getRandomNumber();
		Contract con = new Contract();
		con.setId(contractDao.getSeqIdByTableName("Contract"));
		con.setPayWay("MONTH_END");
		con.setArrearaMount(Double.valueOf(50000));
		con.setLinkManName("赵斌");
		con.setLinkManMobile("13917090951");
		con.setLinkManPhone("021-55555555");
		con.setLinkManAddress("上海");
		Department deptart = new Department();
		deptart.setId("12638");
		con.setDept(deptart);
		con.setPreferentialType("MONTH_SEND");
		con.setContractNum(rnumber);
		ContractDept dept = new ContractDept();

		FileInfo fileInfo = new FileInfo();
		fileInfo.setSourceType("CONTRACT");
		fileInfo.setFileName("投名状");
		fileInfo.setSavePath("F:\\workspace_deppon\\crm-customer\\src\\main\\java\\com\\deppon\\crm\\module\\customer\\shared\\domain");
		List<FileInfo> list = new ArrayList<FileInfo>();
		list.add(fileInfo);
		con.setFileInfoList(list);

		Preferential pre = new Preferential();
		pre.setAgentgathRate(Double.valueOf("0.58"));
		pre.setChargeRebate(Double.valueOf("0.68"));
		pre.setDeliveryPriceRate(Double.valueOf("0.78"));
		pre.setInsuredPriceRate(Double.valueOf("0.88"));
		pre.setReceivePriceRate(Double.valueOf("0.90"));
		con.setPreferential(pre);
		con.setIfForeignGoods(true);
		contractDao.createContract(con);

	}

	/**
	 * 
	 * @工作流通过审批后更改合同状态
	 * @author 赵斌
	 * @2012-3-28
	 * @return
	 */
	@Test
	public void testupdateContract() {

		Contract con = new Contract();
		con.setPayWay("1");
		con.setLinkManName("赵斌");
		con.setLinkManMobile("13917090951");
		con.setLinkManPhone("021-55555555");
		con.setLinkManAddress("上海");
//		con.setDeptId("12");
		con.setId("31");
		UserUtil.setUserToAdmin();
		contractDao.updateContract(con);

	}

	/**
	 * 
	 * @创建合同关联部门
	 * @author 赵斌
	 * @2012-3-30
	 * @return
	 */
	@Test
	public void testCreateContractDept() {
		ContractDept contractdept = new ContractDept();
		contractdept.setId(contractDao.getSeqIdByTableName("ContractDept"));
		Department dp = new Department();
		dp.setId("1");
		contractdept.setContractDept(dp);
		contractdept.setContract(TestUtils.createBean(Contract.class));
//		contractdept.setDeptId("1123");
//		contractdept.setApprovalState("3");
		Contract contract = new Contract();
		contract.setId("12345");
		contractdept.setContract(contract);
		contractdept.setBoundTime(new Date());
		contractdept.setRemoveTime(new Date(new Date().getTime()+3000000));
		UserUtil.setUserToAdmin();
		contractDao.createContractDept(contractdept);
	}


	/**
	 * 
	 * @创建合同关联附件
	 * @author 赵斌
	 * @2012-3-30
	 * @return
	 */
	@Test
	public void testCreatePreferential() {
		Preferential preferential = new Preferential();
		preferential.setContractId("31");
		preferential.setAgentgathRate(3.2);
		preferential.setChargeRebate(3.2);
		preferential.setDeliveryPriceRate(3.4);
		preferential.setInsuredPriceRate(4.3);
		preferential.setReceivePriceRate(3.3);
		contractDao.createPreferential(preferential);

	}


	/**
	 * 
	 * @更新合同信息
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	@Test
	public void testupdateContract1() {
		Contract con = new Contract();
		con.setId("315");
		con.setLinkManName("落点小S");
		contractDao.updateContract(con);
	}
	/**
	 * 
	 * @修改合同部门
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	@Test
	public void testUpdateContractDept() {
		ContractDept contractDept = TestUtils.createBean(ContractDept.class);
		contractDao.updateContractDept(contractDept);
	}
	/**
	 * 
	 * @同意绑定合同
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	@Test
	public void testGetContract() {
		contractDao.getContract("1");
	}

	
	/**
	 * 
	 * @作废合同
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	@Test
	public void testGetContractsByMemberId() {
		//TODO 360的
//		contractDao.getContractsByMemberId("40000146");
	}
	@Test
	public void testGetArrive() {
		contractDao.getArrive("40000146");
	}

	/**
	 * 
	 * @根据查询条件查询合同主体信息
	 * @author 赵斌
	 * @2012-4-18
	 * @return
	 */
	@Test
	public void testsearchContract() {
		ContractCondition con = new ContractCondition();
		List<String> deptIds = new ArrayList<String>();
		deptIds.add("33");
		con.setDeptIds(deptIds);
		con.setCustNumber("20120526-PP-W914605");
//		Assert.assertTrue("查询结果错误",
//				(contractDao.searchContract(con, 0, 20)).size() > 0);
		List<Contract> list = contractDao.searchContract(con, 0, 20);
		for (Contract contract : list) {
			System.out.println(contract.getId());
		}
	}

	@Test
	public void testQueryContractByContractDeptIds() {
		List<String> deptIds = new ArrayList<String>();
		deptIds.add("4343");
		List<Contract> list = contractDao.queryContractByContractDeptIds(deptIds);
		for (Contract contract : list) {
			System.out.println(contract.getId());
		}
	}
	
	/**
	 * 
	 * @根据查询条件查询合同主体信息
	 * @author 赵斌
	 * @2012-4-18
	 * @return
	 */
	@Test
	public void testcountContract() {
		String custCompany = "大华";
		ContractCondition con = new ContractCondition();
		con.setCustCompany(custCompany);
		Assert.assertTrue("查询结果错误", (contractDao.countContract(con)) > 0);
	}

	@Test
	public void testGetDeliverMoneyByCondtion() {
//		List<String> years = new ArrayList<String>();
//		years.add("2009");
//		List<String> months = new ArrayList<String>();
//		months.add("10");
//		months.add("11");
//		months.add("12");
		String custId = "50403";
		Date beginDate =new Date();
		Date endDate =new Date();
		List<Map<String,String>> count = contractDao.getDeliverMoneyByCondtion(beginDate,
				endDate, custId);
		System.out.println(count.size());
	}
	@Ignore
	public void testGetDeliverMoneyByCondtion1(){
		String custId = "50403";
		Date beginDate =new Date(112,05,01,0,0,0);
		Date endDate =new Date(112,07,31,0,0,0);
//		contractService.getDeliverMoneyByCondtion(beginDate,
//				endDate, custId);
	}
	@Test
	public void testGetSeqIdByTableName(){
		String id = contractDao.getSeqIdByTableName("ContractOperatorLog");
		String id2 = contractDao.getSeqIdByTableName("ContractOperatorLog");
		Assert.assertFalse((id.equals(id2)));
	}
	
	@Test
	public void testBatchDeleteUneffectContract(){
		List<String> ids = new ArrayList<String>();
		ids.add("2");
		ids.add("3");
		ids.add("4");
		contractDao.batchDeleteUneffectContract(ids);
	}
	@Test
	public void testQueryAllWaitEffectContractByTime(){
		List<Contract> contracts =contractDao.queryAllWaitEffectContractByTime(new Date());
		Assert.assertTrue(0<=contracts.size());
	}
	@Test
	public void testQueryWaitEffectContractByContractId(){
		List<Contract> contracts = contractDao.queryWaitEffectContractByContractId("1000002");
//		Assert.assertEquals(1, contracts.size());
	}
	/**
	 * @description 生效待生效合同.
	 */
	@Test
	public void testEffectContract(){
		 contractDao.effectContract("1",new Date());
	}
	/**
	 * @删除合同部门
	 * @author 李国文
	 * @return
	 */
	@Test
	public void testDeleteContractDept() {
		String deptId = "1";
		contractDao.deleteContractById(deptId);
	}
	
	/**
	 * 
	 * @查询当前部门是否可以做出发
	 * @author 李国文
	 * @return String
	 * @param deptId
	 *            部门Id
	 */
	@Test
	public void testGetLeave() {
		String deptId = "323223";
		contractDao.getLeave(deptId);
	}
	/**
	 * 
	 * @创建合同操作日志
	 * @author 李国文
	 */
	@Test
	public void testCreateOperatorLog() {
		ContractOperatorLog operatorLog = new ContractOperatorLog();
		operatorLog.setId(contractDao.getSeqIdByTableName("contractoperatorlog"));
		operatorLog.setCreateUser("86301");
		operatorLog.setWorkflowId("11111");
		Contract contract = new Contract();
		contract.setId("8989898989");
		operatorLog.setContract(contract);
		Department department = new Department();
		department.setId("1111");
		operatorLog.setOperatorDept(department);
		operatorLog.setOperatorType("insert");
		contractDao.createOperatorLog(operatorLog);
		
	}
	/**
	 * 
	 * @得到序列
	 * @author 李国文
	 */
	@Test
	public void testGetSeqIdByTableName4Log() {
		String tableName = "CONTRACTDEPT";
		String id = contractDao.getSeqIdByTableName4Log(tableName);
		Assert.assertNotNull(id);
	}
	/**
	 * 
	 * @得到合同的操作日志
	 * @author 李国文
	 */
	@Test
	public void testSearchContractOperatorLogs() {
		ContractOperatorLog operatorLog = new ContractOperatorLog();
		operatorLog.setId("8989898989");
		Assert.assertNotNull(operatorLog);
	}
	/**
	 * 
	 * @修改合同的操作日志
	 * @author 李国文
	 */
	@Test
	public void testUpdateContractOperaLog() {
		ContractOperatorLog operatorLog = new ContractOperatorLog();
		operatorLog.setId("8989898989");
		contractDao.updateContractOperaLog(operatorLog);
	}
	/**
	 * 
	 * @通过合同ID得到合同部门
	 * @author 李国文
	 */
	@Test
	public void testSearchContractDeptByContractId() {
		String contractId = "400285349";
		//assertNotNull(contractDao.searchContractDeptByContractId(contractId));
	}
	/**
	 * 
	 * @通过合同ID得到合同操作日志
	 * @author 李国文
	 */
	@Test
	public void testGetContractOperatorLogByContractId() {
		String contractId = "8989898989";
		Assert.assertNotNull(contractDao.getContractOperatorLogByContractId(contractId));
 	}
	
	/**
	 * 根据条件查询360的合同信息 ----- userId,modifyId 为 employee Id
	 * @param condition
	 * @return
	 */
	@Test
	public void testQueryContract_360ByCondition() {
		ContractCondition condition = new ContractCondition();
		condition.setCustId("94889");
		Assert.assertNotNull(contractDao.queryContract_360ByCondition(condition));
	}
	/**
	 * @description 合同改签通过客户查询客户合同（包括合同部门）. 
	 * @param condition
	 * @return
	 */
	@Test
	public void testQueryContractWithDeptByCondition() {
		ContractCondition condition = new ContractCondition();
		condition.setCustId("94889");
		Assert.assertNotNull(contractDao.queryContractWithDeptByCondition(condition));
	}
	/**
	 * @description 根据合同id和部门id查询合同部门 
	 * @param condition
	 * @return
	 */
	@Test
	public void testGetContractDeptByDeptId() {
		String contractId = "123456";
		String deptId = "1";
		ContractDept contractDept =  contractDao.getContractDeptByDeptId(contractId, deptId);
		Assert.assertNull(contractDept);
	}
	/**
	 * @description 根据合同id查询合同部门. 
	 * @param condition
	 * @return
	 */
	@Test
	public void testQueryContractDeptByContractId() {
		String contractId = "12345";
		List<ContractDept> contractDepts = contractDao.queryContractDeptByContractId(contractId);
		Assert.assertNotNull(contractDepts);
	}
	/**
	 * @description 作废过期合同.
	 */
	@Test
	public void testCancelTimeOutContract() {
		contractDao.cancelTimeOutContract("1", new Date());
	}
	/**
	 * @description 根据id删除合同信息.
	 */
	@Test
	public void testDeleteContractById() {
		Contract contract = new Contract();
		contract.setApplication("Hi,girl,you are so beautiful");
		String id = contractDao.getSeqIdByTableName("CONTRACT");
		contract.setId(id);
		Department deptart = new Department();
		deptart.setId("12638");
		contract.setDept(deptart);
		contract.setIfForeignGoods(true);
		contractDao.createContract(contract);
		contractDao.deleteContractById(contract.getId());
	}
	/**
	 * @description 插入删除的合同id.
	 */
	@Test
	public void testInsertDeletedContractId() {
		String contractId = "111111";
//		contractDao.insertDeletedContractId(contractId);
	}
	/**
	 * @description 查询N天内到期的合同.
	 */
	@Test
	public void testQueryUneffectContractIn30Days() {
		int day = 999999999;
		List<Contract> list = contractDao.queryUneffectContractIn30Days(day);
		Assert.assertNotNull(list);
	}
	/**
	 * @description 终止待生效合同.
	 */
//	@Test
//	public void testUpdateContract4Wait() {
//		Contract contract = new Contract();
//		contract.setApplication("aaaaa");
//		contract.setContractBeginDate(new Date());
//		contract.setContractendDate(new Date());
//		contract.setId(contractDao.getSeqIdByTableName("Contract"));		contractDao.createContract(contract);
//		contractDao.updateContract4Wait(contract);
//	}
	/**
	 * @description 根据客户id查询客户合同的所有信息.
	 */
	@Test
	public void testQueryContractAllInfoByCustId() {
		String memberId = "94889";
		List<Contract> contracts = contractDao.queryContractAllInfoByCustId(memberId);
		Assert.assertNotNull(contracts);
	}
	@Test
	public void testDeleteCommonContractDebtDay(){
		String status = "0";
		contractDao.deleteCommonContractDebtDay(status);
	}
	
	@Test
	public void testSaveCommonContractDebtDay(){
		ContractMonthendDay c = new ContractMonthendDay();
		c.setCreateUser("123");
		c.setDefaultDebtDays(20);
		c.setContractDebtDayName("haha");
		c.setModifyDate(new Date());
		c.setModifyUser("123");
		c.setStatus("1");
		contractDao.saveCommonContractDebtDay(c);
		
	}
	
	@Test
	public void testSearchCommonContractMonthendDay(){
		String status = "0";
		List<ContractMonthendDay>  list = contractDao.searchContractMonthendDays(status);
		System.out.println(list.size());
	}
	
	@Test
	public void testGetSameTypeContractByDebtDay(){
		int debtDay = 50;
		List<Contract> t = contractDao.getSameTypeContractByDebtDay(debtDay);
		System.out.println(t.size());
	}
	
	@Test
	public void testGetContractsBasicInfoByCustNum(){
		String custNumber = "20120214-09359718";
		List<Contract> contracts = contractDao.getContractsBasicInfoByCustNum(custNumber);
		System.out.println(contracts.size());
	}
	
	@Test
	public void testGetContractDebtsForDay(){
		int day = 1;
		List<ContractDebtDay> lists = contractDao.getContractDebtsForDay(day);
		System.out.println(lists.size());
	}
	@Test
	public void testQueryEffectMonthSendContract(){
		//查询条件
		ContractCondition condition = new ContractCondition();
		condition.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		condition.setPrefrentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		condition.setExPrefrentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE);
		List<ContractDto> lists = contractDao.queryEffectMonthSendContract(condition,0,1);
		System.out.println(lists.size());
	}
	
	@Test
	public void testupdateContractTax(){
		ContractTax contractTax = new ContractTax();
		contractTax.setModifyUser("1");
		contractTax.setId("1");
		contractTax.setContractId("1");
		contractDao.updateContractTax(contractTax);
	}
	
	@Test
	public void testdeleteContractTax(){
		contractDao.deleteContractTax("1");
	}
	@Test
	public void testdeleteContractDept(){
		contractDao.deleteContractDept("1");
	}
	@Test
	public void testsearchContractDeptByContractId(){
		contractDao.searchContractDeptByContractId("1");
	}
	@Test
	public void testupdateContract4Wait(){
		Contract c = new Contract();
		c.setId("1");
		c.setModifyUser("1");
		c.setContactId("1");
		c.setModifyDate(new Date());
		contractDao.updateContract4Wait(c);
	}
	@Test
	public void testsearchContractMonthendDayByName(){
		contractDao.searchContractMonthendDayByName("1");
	}
	@Test
	public void testupdateContractMonthEnd(){
		ContractMonthendDay c = new ContractMonthendDay();
		c.setId("1");
		c.setModifyUser("1");
		c.setModifyDate(new Date());
		contractDao.updateContractMonthEnd(c);
	}
	@Test
	public void testgetContractMonthendDayById(){
		contractDao.getContractMonthendDayById("1");
	}
	@Test
	public void testgetContractDebtDayById(){
		contractDao.getContractDebtDayById("1");
	}
	@Test
	public void testgetContractDebtByCustNum(){
		contractDao.getContractDebtByCustNum("1");
	}
	@Test
	public void testupdateContractDebtDay(){
		ContractDebtDay c = new ContractDebtDay();
		c.setId("1");
		c.setModifyUser("1");
		c.setModifyDate(new Date());
		contractDao.updateContractDebtDay(c);
	}
	@Test
	public void testupdateContractDebtDayById(){
		Contract c = new Contract();
		c.setId("1");
		c.setModifyUser("1");
		c.setContactId("1");
		c.setModifyDate(new Date());
		contractDao.updateContractDebtDayById(c);
	}
	@Test
	public void testupdateAllContractDebt(){
		contractDao.updateAllContractDebt(1);
	}
	@Test
	public void testdeletePreferential(){
		contractDao.deletePreferential("1");
	}
	@Test
	public void testqueryContractOperatorLogsForDate(){
		contractDao.queryContractOperatorLogsForDate(new Date(), new Date());
	}
	@Test
	public void testcountInitContract(){
		ContractCondition c =new ContractCondition();
		c.setContractId("1");
		c.setCustId("1");
		contractDao.countInitContract(c);
	}
	@Test
	public void testupdateOperationContractMonthDay(){
		contractDao.updateOperationContractMonthDay(1,1);
	}
	@Test
	public void testupdateDuningDept(){
		contractDao.updateDuningDept("1", false, "1");
	}
	@Test
	public void testmodifyPriceVersionDate(){
		contractDao.modifyPriceVersionDate(new Date(), new Date(),"1");
	}
	@Test
	public void testgetCustInfoForArreaAmoutMessage(){
		contractDao.getContractDebtByCustNum("1");
	}
	@Test 
	public void testgetExCustMonthSumsByCustId(){
		contractDao.getExCustMonthSumsByCustId("1",new Date(),new Date());
	}
}
