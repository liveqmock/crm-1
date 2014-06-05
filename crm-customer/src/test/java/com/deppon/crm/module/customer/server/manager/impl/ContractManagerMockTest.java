package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.customer.server.manager.ContractValidator;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IExamineRecordService;
import com.deppon.crm.module.customer.server.service.impl.ExamineRecordService;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.ContractMonthendDay;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;

public class ContractManagerMockTest extends TestCase {
	private static ContractManager contractManager = new ContractManager();
	Contract contract;

	@Override
	protected void setUp() throws Exception {
		contract = TestUtils.createBean(Contract.class);
		contract.setId("111");
		contract.setPayWay("MONTH_END");
		contract.setPreferentialType("MONTH_SEND");
		contract.setExPayWay("MONTH_END");
		contract.setExPreferentialType("MONTH_REBATE");
		Member m = new Member();
		m.setId("1");
		contract.setMember(m);
		contract.setBeforeContractNum("1");
		contract.setContractNum("1");
		contract.setCustCompany("1");
		contract.setArrearaMount((double) 0);
		User user = new User();
		Department depart = new Department();
		depart.setId("92974");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setEmpCode("111");
		e.setEmpName("1111");
		user.setEmpCode(e);
		Set<String> s = new HashSet<String>();
		s.add("10");
		user.setRoleids(s);
		UserContext.setCurrentUser(user);
		Mockery examineRecordServiceMockery = new Mockery();
		final IExamineRecordService examineRecordService = examineRecordServiceMockery.mock(IExamineRecordService.class);
		examineRecordServiceMockery.checking(new Expectations() {{
			allowing(examineRecordService).getExamineRecordByWorkflowId(with(any(long.class)));
			will(returnValue(new ArrayList<ExamineRecord>()));
			allowing(examineRecordService).getCurrentPeople(with(any(long.class)));
			will(returnValue("1111"));

		}
		});
		contractManager.setExamineRecordService(examineRecordService);
		Mockery contractServiceMockery = new Mockery();
		final IContractService contractService = contractServiceMockery.mock(IContractService.class);
		contractServiceMockery.checking(new Expectations() {
			{	
				allowing(contractService).effectContract(with(any(String.class)),with(any(Date.class)));
				allowing(contractService).getLatelyDeliverMoney(with(any(String.class)),with(any(int.class)));
				List<Double> moneys=new ArrayList<Double>();
				moneys.add((double) 10000);
				moneys.add((double) 10000);
				moneys.add((double) 10000);
				will(returnValue(moneys));
				allowing(contractService).getExLatelyDeliverMoney(with(any(String.class)),with(any(int.class)));
		
				will(returnValue(moneys));
				

				allowing(contractService).batchDeleteUneffectContract(with(any(List.class)));
				allowing(contractService).searchUneffectContractIn30Days(30);
				allowing(contractService).searchAllWaitEffectContractByTime(with(any(Date.class)));
				allowing(contractService).updateContractDebtDayById(with(any(Contract.class)));
				allowing(contractService).searchContractMonthendDayByName(with(any(String.class)));
				allowing(contractService).saveCommonContractDebtDay(with(any(ContractMonthendDay.class)));
				allowing(contractService).updateContractMonthEnd(with(any(ContractMonthendDay.class)));
				allowing(contractService).getContractMonthendDayById("1");
				ContractMonthendDay contractMonthendDay=new ContractMonthendDay();
				will(returnValue(contractMonthendDay));
				allowing(contractService).updateAllContractDebt(0);
				allowing(contractService).getContractDebtsForDay(Constant.CONTRACTDEBTDAY_FOR_ONE);
				List<ContractDebtDay> contractDebtDaylist=new ArrayList<ContractDebtDay>();
				ContractDebtDay contractDebtDay=new ContractDebtDay();
				contractDebtDaylist.add(contractDebtDay);
				contractDebtDay.setCustNum("1");
				allowing(contractService).getContractsBasicInfoByCustNum("1");
				List<Contract> contractss =new ArrayList<Contract>();
				Contract con1=new Contract();
				Member member1=new Member();
				member1.setCustNumber(1+"");
				con1.setMember(member1);
				con1.setArrearaMount((double) 0);
				con1.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH);
				con1.setExPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH);
				con1.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
				con1.setDebtDays(0);
				Calendar cc = Calendar.getInstance();
				cc.add(Calendar.DAY_OF_MONTH, -1);
				con1.setContractBeginDate(cc.getTime());
				contractss.add(con1);
				will(returnValue(contractss));
				allowing(contractService).searchContract(with(any(ContractCondition.class)),with(any(int.class)), with(any(int.class)));
				will(returnValue(new ArrayList<Contract>()));
				
				allowing(contractService).queryContractOperatorLogsForDate(with(any(Date.class)),with(any(Date.class)));
				allowing(contractService).searchInitContract(with(any(ContractCondition.class)),with(any(int.class)),with(any(int.class)));
				allowing(contractService).countInitContract(with(any(ContractCondition.class)));
				allowing(contractService).deleteCommonContractDebtDay(with(any(String.class)));
				allowing(contractService).updateOperationContractMonthDay(with(any(Integer.class)),with(any(Integer.class)));
				allowing(contractService).updateContractDebtDay(with(any(ContractDebtDay.class)));
				allowing(contractService).updateContractBaseInfo(with(any(Contract.class)));
				allowing(contractService).getContractDebtByCustNum("1");
				ContractDebtDay cdd=new ContractDebtDay();
				cdd.setUsedAmount(Constant.USERAMOUNT_DEFAULT);
				will(returnValue(cdd));
				allowing(contractService).getSameTypeContractByDebtDay(1);
				List<Contract> contracts =new ArrayList<Contract>();
				Contract con=new Contract();
				Member member=new Member();
				member.setCustNumber(1+"");
				con.setMember(member);
				con.setArrearaMount((double) 0);
				contracts.add(con);
				will(returnValue(contracts));
				allowing(contractService).searchCommonContractMonthendDay(with(any(String.class)));
				List<ContractMonthendDay> conDays=new ArrayList<ContractMonthendDay>();
				ContractMonthendDay ccc=new ContractMonthendDay();
				ccc.setDefaultDebtDays(1);
				conDays.add(ccc);
				will(returnValue(conDays));
				allowing(contractService).modifyDuningDept(with(any(String.class)),with(any(Boolean.class)),with(any(String.class)));
				allowing(contractService).modifyPriceVersionDate(with(any(Date.class)),with(any(Date.class)),with(any(String.class)));
				allowing(contractService).getAllContractInfoById(with(any(String.class)));
				Contract contract=new Contract();
				
				
				contract.setPriceVersionDate(cc.getTime());
				contract.setExPriceVersionDate(cc.getTime());
				contract.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
				contract.setIfForeignGoods(true);
				contract.setDunningDeptCode("1111");
				contract.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH);
				contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
				contract.setExPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE);
				contract.setContractBeginDate(cc.getTime());
				contract.setMember(member1);
				Department d=new Department();
				d.setId("92974");
				contract.setDept(d);
				will(returnValue(contract));
				allowing(contractService).getExArrearaMountByCustId(with(any(String.class)));
				will(returnValue("111111"));
				allowing(contractService).getArrearaMountByCustId(with(any(String.class)));
				will(returnValue("111111"));
				allowing(contractService).getMember("1");
				Member m = new Member();
				m.setCustNumber("1");
				m.setCustName("1");
				Contact c = new Contact();
				c.setName("1");
				c.setMobilePhone("1");
				c.setTelPhone("1");
				m.setMainContact(c);
				will(returnValue(m));
				allowing(contractService).getCustInfoForArreaAmoutMessage();
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				Map<String, String> map = new HashMap<String, String>();
				map.put("DEPART", "11");
				map.put("CUST", "1");
				list.add(map);
				will(returnValue(list));
			}
		});
		contractManager.setContractService(contractService);

		Mockery messageManagerMockery = new Mockery();
		final IMessageManager messageManager = messageManagerMockery.mock(IMessageManager.class);
		messageManagerMockery.checking(new Expectations() {
			{
				allowing(messageManager).addMessageList(with(any(List.class)));

			}
		});
		contractManager.setMessageManager(messageManager);
		Mockery baseDataManagerMockery = new Mockery();
		final IBaseDataManager baseDataManager = baseDataManagerMockery.mock(IBaseDataManager.class);
		baseDataManagerMockery.checking(new Expectations() {
			{
				allowing(baseDataManager).getCauseDepartment(with(any(String.class)));
				Department d = new Department();
				d.setStandardCode("1");
				d.setDeptName("1");
				will(returnValue(d));
				allowing(baseDataManager).getPointDepartment(with(any(String.class)));
				List<Department> pointDepts = new ArrayList<Department>();
				pointDepts.add(d);
				will(returnValue(pointDepts));
				allowing(baseDataManager).getDeptById(with(any(String.class)));
				will(returnValue(d));
			}
		});
		contractManager.setBaseDataManager(baseDataManager);

	}

	@Override
	protected void tearDown() throws Exception {
	}

	public void createContract() {
	}

	public void getNewContract() {
	}

	public void searchContract() {
	}

	public void changeContract() {
	}

	public void boundContract() {
	}

	public void removeContract() {
	}

	public void changeContractOwner() {

	}

	public void lookDetailContractInfo() {

	}

	public void getUpdateContractInfo() {

	}

	public void cancelContract() {
	}

	public void countContract() {
	}

	public void getContractsByMemberId() {
	}

	public void getContractsByCustNumber() {
	}

	public void getLastestContract() {
	}

	public void getContractDetailById() {
	}

	public void contractApprove() {
	}

	public void getContractOperatorLogByContractId() {
	}

	public void isContractCanOperate() {
//		contractManager.isContractCanOperate(contractId, handleType);
	}

	public void cancelTimeOutContract() {
		try {
			
			contractManager.cancelContract("1");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void testcreateContractAddExaminView() {
		contractManager.createContractAddExaminView("1", 1);
	}

	public void testeffectWaitEffectContract() {
		contractManager.effectWaitEffectContract();

	}

	public void testupdateContract() {
		try {
			contractManager.updateContract(contract, "1");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void testcheckAmountByCustId() {
		contractManager.checkAmountByCustId(contract, "1", (double) 3000);
		
	}
	@Test
	public void testDeleteUneffectContract() {
		try {
			contractManager.deleteUneffectContract("1");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void testCreateNewContract() {
//		List<FileInfo> files=new ArrayList<FileInfo>();
//		contractManager.createNewContract(contract, files, contractType);

	}

	public void testbatchDeleteUneffectContract() {
		List<String> s=new ArrayList<String>();
		s.add("1");
		try {
			
			contractManager.batchDeleteUneffectContract(s);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void testsearchUneffectContractIn30Days() {
		contractManager.searchUneffectContractIn30Days();
	}

	public void testsearchAllWaitEffectContractByTime() {
		contractManager.searchAllWaitEffectContractByTime(new Date());
	}

	public void testUpdateContractDebtDaysById() {
		try {
			
			contractManager.updateContractDebtDaysById("1", 1);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void testsearchAllContractDebtDays() {
		contractManager.searchAllContractDebtDays();

	}

	public void testsearchContractDebtDayByName() {
		contractManager.searchContractDebtDayByName("1");
	}

	public void testUpdateCommonContractMonthEndDayById() {
		contractManager.updateCommonContractMonthEndDayById("1", 1);
		

	}

	public void testCheckCurrentTime() {
		try {
			
			contractManager.checkCurrentTime();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void testContractDebtDaysManager() {
		contractManager.contractDebtDaysManager();

	}

	public void testSaveContractDebt() {
		ContractDebtDay contractDebtDay=new ContractDebtDay();
		contractDebtDay.setCustNum("1");
		contractDebtDay.setUsedAmount(Constant.USERAMOUNT_DEFAULT);
		contractManager.saveContractDebt(contractDebtDay);

	}

	public void testgetUpdateMonthSendInfo() {
		contractManager.getUpdateMonthSendInfo("1");
	}

	public void testCheckCreateContractDate() {
		ContractDetailView c=new ContractDetailView();
		c.setCustId("11111");
		contractManager.checkCreateContractDate(c);

	}

	public void testqueryContractOperatorLogsForDate() {
		contractManager.queryContractOperatorLogsForDate(new Date(), new Date());
	}

	public void testsearchInitContract() {
		ContractCondition c=new ContractCondition();

		contractManager.searchInitContract(c, 1, 1);
	}

	public void testCountInitContract() {
		ContractCondition c=new ContractCondition();
		contractManager.countInitContract(c);
	}

	public void testupdateCommonContractDebtDay() {
		contractManager.updateCommonContractDebtDay();

	}

	public void testModifyDuningDept() {
		contractManager.modifyDuningDept("1", true, "1");

	}

	public void testModifyPriceVersionDate() {
		try {

			contractManager.modifyPriceVersionDate(null, new Date(), "1");
		} catch (Exception e) {
		}
		try {

			contractManager.modifyPriceVersionDate(new Date(), null, "1");
		} catch (Exception e) {
		}
		try {

			contractManager.modifyPriceVersionDate(new Date(), new Date(), null);
		} catch (Exception e) {
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		contractManager.modifyPriceVersionDate(new Date(), new Date(), "1");

	}

	public void testSaveCustInfoForArreaAmoutMessage() {
		contractManager.saveCustInfoForArreaAmoutMessage();

	}

	public void testCreateContractInfo() {
		Mockery mock = new Mockery();
		final ICache<String, Head> cache = mock.mock(ICache.class);
		mock.checking(new Expectations() {
			{
				allowing(cache).getUUID();
				will(returnValue(Head.class.getName()));
				DataDictionaryCacheProvider dataDictionaryCacheProvider = SpringTestHelper.getBean(DataDictionaryCacheProvider.class);

				allowing(cache).get(DataHeadTypeEnum.RECKON_WAY.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.RECKON_WAY.toString())));

				allowing(cache).get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString())));
				allowing(cache).get(DataHeadTypeEnum.EXPRIVILEGE_TYPE.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.EXPRIVILEGE_TYPE.toString())));

			}
		});
		CacheManager.getInstance().registerCacheProvider(cache);
		contract = TestUtils.createBean(Contract.class);
		contract.setId("111");
		contract.setPayWay("MONTH_END");
		contract.setPreferentialType("MONTH_SEND");
		contract.setExPayWay("MONTH_END");
		contract.setExPreferentialType("MONTH_REBATE");
		Member m = new Member();
		m.setId("1");
		contract.setMember(m);
		contract.setBeforeContractNum("1");
		contract.setContractNum("1");
		contract.setCustCompany("1");
		contract.setArrearaMount((double) 0);
		Contract oldContract = new Contract();
		List<ContractTax> contractTaxList = new ArrayList<ContractTax>();
		ContractTax c = new ContractTax();

		contract.setContractTaxList(contractTaxList);
		Map<String, String> extendMap = new HashMap<String, String>();
		extendMap.put("applyType", ContractApplyType.NEW.toString());
		extendMap.put("isStatified", "2");
		List<FileInfo> info = new ArrayList<FileInfo>();
		info.add(new FileInfo());
		contract.setFileInfoList(info);
		contractManager.createContractInfo(contract, extendMap);

	}

}
