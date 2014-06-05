/**   
 * @title ContractManagerTest.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @description what to do
 * @author patern
 * @update 2012-7-1 下午1:11:43
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.manager.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.customer.server.manager.ContractValidator;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.server.utils.ExpressAuthDeptUtil;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;

/**
 * @description 合同Manager测试
 * @author patern
 * @version 0.1 2012-7-1
 * @date 2012-10-22
 */
public class ContractManagerNewTest extends BeanUtil  {
	ContractManager contractManager = new ContractManager();
	Contract contract = null;
	ContractOperatorLog log = null;
	List<ContractDept> depts=null;
	Contract contract1=null;

	
		
	
	/**
	 * @description 环境搭建.
	 * @author patern
	 * @version 0.1 2012-7-1
	 * @param b
	 *            true or false
	 * @date 2012-7-1
	 * @return none
	 * @update 2012-7-1 下午1:11:43
	 */
	@Before
	public void setUp() throws Exception {
		contract = TestUtils.createBean(Contract.class);
		contract.setId("111");
		contract.setPayWay("MONTH_END");
		contract.setPreferentialType("MONTH_SEND");
		contract.setExPayWay("MONTH_END");
		contract.setExPreferentialType("MONTH_SEND");
		// manger设值
		UserUtil.setUserToAdmin();
		log=new ContractOperatorLog();
		log.setContract(contract);
		log.setWorkflowId("333333");
		log.setApprovalState("1");
		
		ContractDept contractDept = TestUtils.createBean(ContractDept.class);
		Department de = new Department();
		de.setId("5");
		contractDept.setContract(contract);
		contractDept.setDept(false);
		depts = new ArrayList<ContractDept>();
		depts.add(contractDept);
		contract.setContractDepartList(depts);
//		contractManager.setContractValidator(new ContractValidator());
		 Mockery mock = new Mockery();
		 CacheManager.getInstance().shutdown();
			final ICache<String, Head> cache = mock.mock(ICache.class);
				mock.checking(new Expectations(){
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
		
		
		
		
		methodMock();
		
	
	}

	/**
	 * @description 恢复数据.
	 * @author patern
	 * @version 0.1 2012-7-1
	 * @param b
	 *            true or false
	 * @date 2012-7-1
	 * @return none
	 * @update 2012-7-1 下午1:11:43
	 */
	@After
	public void tearDown() throws Exception {
		CacheManager.getInstance().shutdown();
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#createContract(com.deppon.crm.module.customer.shared.domain.Contract)}
	 * .
	 */
	// @Test
	public void testCreateContract() {
		
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#changeContract(com.deppon.crm.module.customer.shared.domain.Contract)}
	 * .
	 */
	@Test
	public void testAlterContract() {
		// fail("Not yet implemented");
		System.out.println(DataHeadTypeEnum.RECKON_WAY.toString());
		System.out.println(DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.RECKON_WAY, contract.getPayWay()));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#boundContract(java.lang.String, java.lang.String, com.deppon.crm.module.common.file.domain.FileInfo)}
	 * .
	 */
	@Test
	public void testBoundContract() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#changeContractOwner(java.lang.String, java.lang.String, com.deppon.crm.module.common.file.domain.FileInfo)}
	 * .
	 */
	@Ignore
	public void testChangeContractOwner() {
		String deptId = "3";
		String ownDeptId = "1";
		String contractId1 = "30001";
		String contractId2 = "30002";
		//待生效合同有工作流id
		String contractId3="30004";
		//生效合同有工作流id
		String contractId4="30005";
		//生效合同有工作流id
		String contractId5="30006";
		Department dept = new Department();
		dept.setId("1");
		contract.setDept(dept);
		List<FileInfo> files = new ArrayList<FileInfo>();
		FileInfo file =TestUtils.createBean(FileInfo.class);
		files.add(file);
		// 取出合同为空，从数据库找不到合同信息
		try {
			contractManager.changeContractOwner(contractId1, deptId, files);
			Assert.fail("未抛出异常!");
		} catch (ContractException e) {
			Assert.assertTrue(e.getErrorCode().equals(
					ContractExceptionType.ContractIsNull.getErrCode()));
		}
		// 是合同归属部门
		try {
			contractManager.changeContractOwner(contractId2, ownDeptId, files);
			Assert.fail("未抛出异常!");
		} catch (ContractException e1) {
			Assert.assertTrue(e1.getErrorCode().equals(
					ContractExceptionType.DeptIsBelongDept.getErrCode()));
		}
		//客户不是归属客户
		Member m = new Member();
		m.setId("40001");
		contract.setMember(m);
		try {
			contractManager.changeContractOwner(contractId2, deptId, files);
			Assert.fail("未抛出异常!");
		} catch (ContractException e3) {
			Assert.assertTrue(e3.getErrorCode().equals(
					ContractExceptionType.CUSTOMERINVALIDE.getErrCode()));
		}
		Member m2 = new Member();
		m2.setId("40002");
		contract.setMember(m2);
		//待生效存在审批工作流
		try {
			contractManager.changeContractOwner(contractId3, deptId, files);
			Assert.fail("未抛出异常!");
		} catch (ContractException e3) {
			Assert.assertEquals(e3.getErrorCode(),
					ContractExceptionType.ContractStateIsError.getErrCode());
		}
		//生效存在审批工作流
		try {
			contractManager.changeContractOwner(contractId4, deptId, files);
			Assert.fail("未抛出异常!");
		} catch (ContractException e3) {
			Assert.assertTrue(e3.getErrorCode().equals(
					ContractExceptionType.ContractStateIsError.getErrCode()));
		}
		
		//情况正常
//		contractManager.changeContractOwner(contractId5, deptId, files);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#updateOwnerContract(java.lang.String, boolean, java.lang.String, java.util.Date)}
	 * .
	 */
	@Test
	public void testUpdateOwnerContract() throws Exception{
		ContractOperatorLog log = TestUtils.createBean(ContractOperatorLog.class);
		Department changedDept = new Department();
		changedDept.setId("4");
		log.setChangedDept(changedDept);
		Department previousDept = new Department();
		previousDept.setId("3");
		log.setPreviousDept(previousDept);
		
		Department changedDept2 = new Department();
		changedDept2.setId("5");
		
		log.setId("70001");
		log.setContract(contract1);
		ContractOperatorLog log1 = TestUtils.createBean(ContractOperatorLog.class);
		log1.setId("70002");
		log.setContract(contract);
		List<ContractOperatorLog> list = new ArrayList<ContractOperatorLog>();
		list.add(log);
		list.add(log1);
		
		Class clazz=((ContractManager)contractManager).getClass();
		//合同归属部门变更只会有一条工作流，如果超过1条就报错
			Method m =  clazz.getDeclaredMethod("updateOwnerContract", List.class,boolean.class);
			
			Method	m2 = clazz.getDeclaredMethod("setContractService", IContractService.class);
			
			Object instance = clazz.newInstance();
			
			m2.invoke(instance, contractService);
			if (!m.isAccessible()) {
				m.setAccessible(true);
			}
			try {
				m.invoke(instance, list,Boolean.TRUE);
			} catch (Exception e) {
				Assert.assertTrue(true);
//				assertEquals(e.getErrorCode(), ContractExceptionType.Data_Error.getErrCode());
			}
		
			List<ContractOperatorLog> list2 = new ArrayList<ContractOperatorLog>();
			log.setChangedDept(changedDept);
			list2.add(log);
			m.invoke(instance, list2,false);
			
			m.invoke(instance, list2,true);
			
			m.invoke(instance, list2,false);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#lookDetailContractInfo(java.lang.String)}
	 * .
	 */
	@Test
	public void testLookDetailContractInfo() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#getUpdateContractInfo(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUpdateContractInfo() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#searchContract(com.deppon.crm.module.customer.shared.domain.ContractCondition, int, int)}
	 * .
	 */
	@Test
	public void testSearchContract() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#cancelContract(java.lang.String)}
	 * .
	 */
	@Ignore
	public void testCancelContract() {
		try {
			contractManager.cancelContract("");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.Data_Error.getErrCode(),e.getErrorCode());

		}
		
		try {
			contractManager.cancelContract("192168002");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(ContractExceptionType.ContractIsNull.getErrCode(),e.getErrorCode());

		}
		
		
		//待生效，有审批中工作流
		try {
			contractManager.cancelContract("30004");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(e.getErrorCode(), ContractExceptionType.ContractStateIsError.getErrCode());

		}
		//生效，有审批中工作流
		try {
			contractManager.cancelContract("30005");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(e.getErrorCode(), ContractExceptionType.ContractStateIsError.getErrCode());
		}
		//无效,无审批中工作流
		try {
			contractManager.cancelContract("30007");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(e.getErrorCode(), ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode());
		}
		//审批中，无审批中工作流
		try {
			contractManager.cancelContract("30008");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(e.getErrorCode(), ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode());
		}
		//操作部门不是合同的归属部门
		try {
			contractManager.cancelContract("30009");
			Assert.fail();
		} catch (ContractException e) {
			Assert.assertEquals(e.getErrorCode(), ContractExceptionType.ContractCanNotCancell.getErrCode());
		}
		
//		String workflowId = contractManager.cancelContract("30006");
//		Assert.assertEquals("6666666", workflowId);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#countContract(com.deppon.crm.module.customer.shared.domain.ContractCondition)}
	 * .
	 */
	@Test
	public void testCountContract() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#getContractsByMemberId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetContractsByMemberId() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.customer.server.manager.impl.ContractManager#contractApprove(java.lang.String, boolean, java.lang.String, java.util.Date)}
	 * .
	 */
	@Test
	public void testContractApprove() {
		
	}

	private void methodMock() throws Exception {
		Mockery contractServiceMockey = new Mockery();
		contractService = contractServiceMockey
				.mock(IContractService.class);
		contractManager.setContractService(contractService);
		final Member m = TestUtils.createBean(Member.class);
		contract1 = TestUtils.createBean(Contract.class);
		Department dept = new Department();
		dept.setId("1");
		contract1.setDept(dept);
		contract1.setPayWay("MONTH_END");
		contract1.setPreferentialType("MONTH_SEND");
		contract1.setExPayWay("MONTH_END");
		contract1.setExPreferentialType("MONTH_REBATE");
		contract1.setDept(dept);
		Member m2 = new Member();
		m2.setId("40002");
		contract1.setMember(m2);
		contractServiceMockey.checking(new Expectations() {
			{
				contract.setContractStatus("1");
				allowing(contractService).getAllContractInfoById("30001");
				will(returnValue(null));
				//返回生效合同,无审批中的工作流
				allowing(contractService).getAllContractInfoById("30002");
				contract.setContractDepartList(depts);
				will(returnValue(contract));
				
				contract.setContractBeginDate(new Date(new Date().getTime()+24*3600*1000));
				//返回待生效合同,无审批中的工作流
				allowing(contractService).getAllContractInfoById("30003");
				contract.setContractSubject("德邦物流股份有限公司");
				will(returnValue(contract));
				
				//返回待生效合同,有审批中的工作流
				List<ContractOperatorLog> contractWorkflowList = new ArrayList<ContractOperatorLog>();
				contractWorkflowList.add(log);
				contract.setContractWorkflowList(contractWorkflowList);
				allowing(contractService).getAllContractInfoById("30004");
				will(returnValue(contract));
				
				contract.setContractBeginDate(new Date(new Date().getTime()-24*3600*1000));
				//返回生效合同,有审批中的工作流
				allowing(contractService).getAllContractInfoById("30005");
				will(returnValue(contract));
				
				//返回生效合同,无审批中的工作流
				allowing(contractService).getAllContractInfoById("30006");
				contract1.setContractSubject("德邦物流股份有限公司");
				will(returnValue(contract1));
							
				allowing(contractService).getAllContractInfoById("192168002");
				will(returnValue(null));
				
				contract.setContractStatus("1");
				
				Contract contract2 = TestUtils.createBean(Contract.class);
				Department dept = new Department();
				dept.setId("1");
				contract2.setDept(dept);
				contract2.setPayWay("MONTH_END");
				contract2.setPreferentialType("MONTH_SEND");
				contract2.setDept(dept);
				//返回无效合同,无审批中的工作流
				allowing(contractService).getAllContractInfoById("30007");
				contract2.setContractStatus("2");
				will(returnValue(contract2));
				
				//返回审批中的合同,无审批中的工作流
				allowing(contractService).getAllContractInfoById("30008");
				contract2.setContractStatus("0");
				will(returnValue(contract2));
				
				Contract contract3 = TestUtils.createBean(Contract.class);
				Department dept1 = new Department();
				dept1.setId("3");
				contract3.setDept(dept);
				contract3.setPayWay("MONTH_END");
				contract3.setPreferentialType("MONTH_SEND");
				contract3.setDept(dept1);
				//返回无效合同,无审批中的工作流
				allowing(contractService).getAllContractInfoById("30009");
				will(returnValue(contract3));
				
				allowing(contractService).getMember("40002");
				will(returnValue(m));
				
				allowing(contractService).getConOperaLogSequence();
				will(returnValue("1000000"));
				
				allowing(contractService).createContractOpeLog(with(any(ContractOperatorLog.class)));
				
				allowing(contractService).createContractDept(with(any(ContractDept.class)));
				will(returnValue(new ContractDept()));
				
				allowing(contractService).getContractDeptByDeptId(contract.getId(), "3");
				ContractDept dept3 = new ContractDept();
				dept3.setId("3");
				will(returnValue(dept3));
				
				allowing(contractService).getContractDeptByDeptId(contract.getId(), "4");
				ContractDept dept2 = new ContractDept();
				dept2.setId("4");
				will(returnValue(dept2));
				
				allowing(contractService).getContractDeptByDeptId(contract1.getId(), "5");
				will(returnValue(null));
				
				allowing(contractService).updateContract(with(any(Contract.class)));
				will(returnValue(true));
				
				allowing(contractService).updateContractDept(with(any(ContractDept.class)));
				will(returnValue(true));
			}
		});

		Mockery baseDateManagerMocky = new Mockery();
		final IBaseDataManager baseDataManager = baseDateManagerMocky
				.mock(IBaseDataManager.class);
		contractManager.setBaseDataManager(baseDataManager);
		final List<String> depts = new ArrayList<String>();
		depts.add("3");
		baseDateManagerMocky.checking(new Expectations() {
			{
				allowing(baseDataManager).getDataAuthorityDepts("3");
				will(returnValue(depts));
				
				Department dept = new Department();
				dept.setId("50001");
				dept.setDeptCode("DP50001");
				allowing(baseDataManager).getCauseDepartment("1");
				
				Department dept1 = new Department();
				dept1.setId("60001");
				dept1.setDeptCode("DP60001");
				allowing(baseDataManager).getDeptById("1");
				will(returnValue(dept1));
				
				
				Department d2 = new Department();
				d2.setCompanyName("德邦物流股份有限公司");
				d2.setId("2344");
				allowing(baseDataManager).getDeptByStandardCode("DP123456");
				will(returnValue(d2));
				
				List<Department> depts = new ArrayList<Department>();
				depts.add(d2);
				
				allowing(baseDataManager).getPointDepartment("DP123456");
				will(returnValue(depts));
				
			}
		});

		Mockery alterMemberManagerMockey = new Mockery();
		final IAlterMemberManager alManager = alterMemberManagerMockey
				.mock(IAlterMemberManager.class);
		contractManager.setAlterMemberManager(alManager);
		final MemberCondition searchCustCondition = new MemberCondition();
		//添加数据权限
		searchCustCondition.setMemberId("40001");
		searchCustCondition.setDeptIds(depts);
		searchCustCondition.setStart(0);
		searchCustCondition.setLimit(-1);//-1标识不用分页
		
		final MemberCondition searchCustCondition2 = new MemberCondition();
		//添加数据权限
		searchCustCondition2.setMemberId("40002");
		searchCustCondition2.setDeptIds(depts);
		searchCustCondition2.setStart(0);
		searchCustCondition2.setLimit(-1);//-1标识不用分页
		alterMemberManagerMockey.checking(new Expectations() {
			{
				allowing(alManager).searchMemberWithAuth(searchCustCondition);
				will(returnValue(null));
				
				List<Member> members = new ArrayList<Member>();
				members.add(m);
				
				allowing(alManager).searchMemberWithAuth(searchCustCondition2);
				will(returnValue(members));
			}
		});
		
		Mockery contractOpeMockey = new Mockery();
		final IContractApplyOperate ope = contractOpeMockey.mock(IContractApplyOperate.class);
		contractManager.setContractApplyOperate(ope	);
		try {
			contractOpeMockey.checking(new Expectations(){
				{
					allowing(ope).contractApply(with(any(ContractInfo.class)), with(any(ContractApplyType.class)));
					will(returnValue("6666666"));
				}
			});
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		
		//假造数据字典
		List<Detail> payWayDetails = new ArrayList<Detail>();
		Detail d = new Detail();
		d.setCode("MONTH_END");
		d.setCodeType("RECKON_WAY");
		d.setCodeDesc("月结");
		d.setId("1");
		
		Detail d2 = new Detail();
		d2.setCode("NOT_MONTH_END");
		d2.setCodeType("RECKON_WAY");
		d2.setCodeDesc("无");
		d2.setId("2");
		
		payWayDetails.add(d);
		payWayDetails.add(d2);
		
		Head payWayHead = new Head();
		payWayHead.setCodeType("RECKON_WAY");
		payWayHead.setDetailList(payWayDetails);
		
		List<Detail> preWayDetails = new ArrayList<Detail>();
		Detail d3 = new Detail();
		d3.setCode("MONTH_SEND");
		d3.setCodeType("PRIVILEGE_TYPE");
		d3.setCodeDesc("月发月送");
		d3.setId("3");
		
		Detail d4 = new Detail();
		d4.setCode("NOT_PREFERENTIAL");
		d4.setCodeType("PRIVILEGE_TYPE");
		d4.setCodeDesc("无");
		d4.setId("4");
		
		Detail d5 = new Detail();
		d5.setCode("PRICE_REBATE");
		d5.setCodeType("PRIVILEGE_TYPE");
		d5.setCodeDesc("价格折扣");
		d5.setId("5");
		
		preWayDetails.add(d3);
		preWayDetails.add(d4);
		preWayDetails.add(d5);
		
		Head preWayHead = new Head();
		preWayHead.setCodeType("PRIVILEGE_TYPE");
		preWayHead.setDetailList(preWayDetails);
		
		List<Detail> expreWayDetails = new ArrayList<Detail>();
		Detail d6 = new Detail();
		d6.setCode("MONTH_REBATE");
		d6.setCodeType("EXPRIVILEGE_TYPE");
		d6.setCodeDesc("月发月送");
		d6.setId("6");
		
		Detail d7 = new Detail();
		d7.setCode("NOT_PREFERENTIAL");
		d7.setCodeType("EXPRIVILEGE_TYPE");
		d7.setCodeDesc("无");
		d7.setId("7");
		
		Detail d8 = new Detail();
		d8.setCode("PRICE_REBATE");
		d8.setCodeType("EXPRIVILEGE_TYPE");
		d8.setCodeDesc("价格折扣");
		d8.setId("8");
		
		expreWayDetails.add(d3);
		expreWayDetails.add(d4);
		expreWayDetails.add(d5);
		
		Head exPreWayHead = new Head();
		preWayHead.setCodeType("EXPRIVILEGE_TYPE");
		preWayHead.setDetailList(expreWayDetails);
		
		
		final Map<String, Head> localCache = new HashMap<String, Head>();
		localCache.put(payWayHead.getCodeType(), payWayHead);
		localCache.put(preWayHead.getCodeType(), preWayHead);
		localCache.put(exPreWayHead.getCodeType(), exPreWayHead);

		Mockery fileManMock = new Mockery();
		final IFileManager fileManager = fileManMock.mock(IFileManager.class);
		contractManager.setFileManager(fileManager);
		fileManMock.checking(new Expectations(){
			{
				allowing(fileManager).saveFileInfo(with(any(FileInfo.class)));
				will(returnValue(true));
			}
		});

		ExpressAuthDeptUtil expressAuthDeptUtil = new ExpressAuthDeptUtil();
	}
}
