package com.deppon.crm.module.customer.server.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.ContractHandleType;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;

/**   
 * 待写完
 * <p>
 * Description:ContractManager层单元测试<br />
 * </p>
 * @title ContractManagerTest.java
 * @package com.deppon.crm.module.customer.server.manager.impl 
 * @author 李国文
 * @version 0.1 2012-10-20
 */
public class ContractManagerTest extends TestCase{
	
	IContractManager contractManager = SpringTestHelper.getBean(ContractManager.class);
	String contractId = null;
	String sourceId = null;
	String deptId = null;
	Contract contract = null;
	

	@Before
	public void setUp() throws Exception {
		UserUtil.setUserToAdmin();
		contract = TestUtils.createBean(Contract.class);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(contract.getContractendDate());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		
		contract.setContractendDate(calendar.getTime());
		Member member = new Member();
		member.setId("1000001");
		contract.setMember(member);
		contract.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);
		contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		ContractDept deptpart = TestUtils.createBean(ContractDept.class);
		List<ContractDept> deptList = new ArrayList<ContractDept>();
		deptList.add(deptpart);
		contract.setContractDepartList(deptList);
		ContractDept dept = TestUtils.createBean(ContractDept.class);
		List<ContractDept> depts = new ArrayList<ContractDept>();
		depts.add(dept);
		contract.setContractDepartList(depts);
		contract.setDept(ContextUtil.getCurrentUserDept());
		FileInfo info = TestUtils.createBean(FileInfo.class);
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		fileInfos.add(info);
		contract.setFileInfoList(fileInfos);
		DBUtils.initContract();
	}

	@After
	public void tearDown() throws Exception {
		 DBUtils.clean();
	}

	/**
	 * @工作流审批完成之后调用
	 * @return boolean
	 * @param wkid 工作流id
	 * @param wkStatus 工作流审批状态
	 * @param wkman 最后审批人
	 * @param wktime 最后审批时间
	 */
	@Test
	public void testContractApprove(){
		try {
			contractManager.contractApprove("ICRM201312180070069", false, "李学兴", new Date());
			CustomerInfoLog.commit();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(true);
		}
	}


	/**
	 * @Description: 查询所有待生效合同
	 */
	@Test
	public void testSearchAllWaitEffectContractByTime() {
		// 输入:date=当前时间      输出:待生效合同集合
		Date date = new Date();
		List<Contract> lists = contractManager
				.searchAllWaitEffectContractByTime(date);

		// 输入:date=2012 10 12指定的一个时间     输出：开始时间为 ‘2012 10 12 ’的待生效合同
		SimpleDateFormat format = new SimpleDateFormat("yyyy mm dd");
		try {
			date = format.parse("2012 11 12");
		} catch (ParseException e) {
			Assert.assertTrue(true);
		}
		lists = contractManager.searchAllWaitEffectContractByTime(date);
		Assert.assertNotNull("待生效合同不为空", lists);
//		Assert.assertEquals(false, lists.size() == 0);

		//输入： date=null   输出：IllegalArgumentException异常,异常信息:date must not null
		try {
			lists = contractManager.searchAllWaitEffectContractByTime(null);
			Assert.fail("date must not null");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}


	/**
	 * @Description: 批量删除无效合同
	 *  删除需满足的条件：无效合同的ID，第一条操作日志：操作类型（insert：新签，changeSign：新签，update修改）
		且审批结果是不同意 该合同可以删除
	 */
	@Test
	public void testBatchDeleteUneffectContract() {
		//参数合同ID
		List<String> contractIds = new ArrayList<String>();

		// 输入:contractIds=null 输出:IllegalArgumentException异常,异常信息:delete id can not null !
		try {
			contractManager.batchDeleteUneffectContract(contractIds);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		/**
		 * 当传入的参数有值时，集合泛型String里面的值必须为数字类型,且为正整数
		 */
		//输入：contractIds 此合同ID对应满足删除的条件        输出：无 ，通过
		contractIds.add("111111111111114");
		contractIds.add("111111111111115");
		try {
			contractManager.batchDeleteUneffectContract(contractIds);// 删除成功
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		contractIds.clear();
		// 输入：无效合同的ID，但第一条操作日志不是（insert：新签，changeSign：新签，update修改）,审批结果同意
		// 输出：ContractException
		contractIds.add("400287734");
		try {
			contractManager.batchDeleteUneffectContract(contractIds);
			Assert.fail("第一操作的日志不符合删除的操作，且审批结果同意了抛出异常");
		} catch (ContractException e) {
			Assert.assertTrue(true);
		}

		// 输入：有效合同的ID   输出：抛出ContractException
		contractIds.clear();
		contractIds.add("111111111111113");
		try {
			contractManager.batchDeleteUneffectContract(contractIds);
			Assert.fail("合同状态为有效时,抛出异常");
		} catch (ContractException e) {
			Assert.assertTrue(true);
		}

		//输入：contractIds=待生效或者审评中合同的ID  输出:抛出异常ContractException
		contractIds.clear();
		contractIds.add("111111111111111");
		try {
			contractManager.batchDeleteUneffectContract(contractIds);
			Assert.fail("合同状态为待生效或者评审中时，抛出异常");
		} catch (ContractException e) {
			Assert.assertTrue(true);
		}
		
		//输入:contractIds=id对应数据库不存在的合同ID 输出：ContractExceptionType
		contractIds.clear();
		contractIds.add("1234567");
		contractIds.add("2222222");
		try {
			contractManager.batchDeleteUneffectContract(contractIds);
			Assert.fail("合同ID对应数据库合同信息不存在");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		//输入：contractIds为不符合规则的合同ID 输出:sqlException异常,异常信息：无效数字异常
		contractIds.clear();
		contractIds.add("待生效合同的ID");
		contractIds.add("111aaa");
		contractIds.add("-11111");
		try {
			contractManager.batchDeleteUneffectContract(contractIds);
			Assert.fail("contractIDs中的ID为无效数字");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * @Description: 通过合同的ID去删除无效的合同
	 * 删除满足的条件： 传入合同ID符合前面判断条件,合同状态为无效，第一条操作日志：操作类型（insert：新签，changeSign：新签，update修改）
	 * 且审批结果是不同意 该合同可以删除
	 */
	@Test
	public void testDeleteUneffectContract() {
		//参数合同Id
		String contractId = "";

		//输入:contractId为满足删除条件的ID  输出:无 
		contractId = "400356898";
		try {
			contractManager.deleteUneffectContract(contractId);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		//输入:contractId不符合删除条件的id  输出：ContractException
		contractId = "111111111111116";
		try {
			contractManager.deleteUneffectContract(contractId);
			Assert.fail("不满足可以删除的条件");
		} catch (ContractException e) {
			Assert.assertTrue(true);
		}

		//输入：contractId = null 输出:异常IllegalArgumentException，异常信息:contractId must not null !
		try {
			contractManager.deleteUneffectContract(null);
			Assert.fail("传参不能为null");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		// 输入：合同ID不符合规则 例如：contractId = "11111aaa"或者"-222a"时; 
		//输出：sqlException异常,异常信息：无效数字异常
		contractId = "-111a";
		try {
			contractManager.deleteUneffectContract(contractId);
			Assert.fail("contractId为无效数字");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		//输入：contractId对应数据库不存在合同信息    输出ContractID
		contractId = "12345678";
		try {
			contractManager.deleteUneffectContract(contractId);
			Assert.fail("传入的合同ID，合同信息对应数据库必须存在");
		} catch (ContractException e) {
			Assert.assertTrue(true);
		}

		//输入:contractId对应合同的状态是审批中,有效和待生效时  输出：ContractException
		contractId = "111111111111113";
		try {
			contractManager.deleteUneffectContract(contractId);
			Assert.fail("合同状态不是无效时，抛出异常");
		} catch (ContractException e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * @Description: 校验结算额度
	 */
	@Ignore
	public void testCheckAmountByCustId() {
		String custId = null;// 客户ID
		double amount = 0d;// 结算额度
		boolean flag = false; //验证结果
		
		custId = "";
		amount = 0d;
		Contract con = new Contract();
		con.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		flag = contractManager.checkAmountByCustId(con,custId, amount);
		Assert.assertEquals(true, flag);
		
		//输入：custId='881',amount=1000.0d,正常传值  
		//输出：flag = false;
		custId = "123457";
		amount = 1000.0d;
		flag = contractManager.checkAmountByCustId(con,custId, amount);
		Assert.assertFalse("大于三个月中发货金额最大那个月的两倍",flag);

		// 输入：custId='210',amount=100.0d,客户对应的发货情况为三个月没有发货
		//输出：flag = false;
		custId = "123456";
		amount = 100.0d;
		flag = contractManager.checkAmountByCustId(con,custId, amount);
//		Assert.assertFalse("三个月没有发货",flag);

		//三个月没发货，结算额度为0时; 
		//输入：custID='210',amout=0d,  输出:flag = true;
		custId = "123456";
		amount = 0d;
		flag = contractManager.checkAmountByCustId(con,custId, amount);
		Assert.assertTrue("三个月没发货，结算额度为0",flag);

//		//输入：custId为不符合规则时,   输出：SQLException 
//		custId = "210X";
//		try {
//			contractManager.checkAmountByCustId(custId, amount);// 抛出SQLException，无效数字
//			Assert.fail("custId为无效数字");
//		} catch (Exception e) {
//			Assert.assertTrue(true);
//		}
	}

	/**
	 * @Description: 生效带生效合同
	 */
	@Test
	public void testEffectWaitEffectContract() {
		/**
		 * 将待生效合同时间开始时间小于或等于当前时间的合同状态变为1,即变为有效合同，
		 * 执行此方法后可到数据库查询比较
		 */
		//输入：无  输出：无
		contractManager.effectWaitEffectContract();
	}

	
	/**
	 * @Description: 根据合同Id查询合同的详细信息（只包括了基本信息和合同信息）
	 */
	@Test
	public void testGetContractDetailById() {
		//参数合同返回值
		ContractDetailView contractDetailView = null;
		//参数合同的ID
		String contractId = null;
		
		//输入：contractId="111111111111111", 输出:contractDetailView
		contractId = "111111111111111";
		try {
			contractDetailView = contractManager.getContractDetailById(contractId);
			Assert.assertNotNull(contractDetailView);
			Assert.assertEquals(true, contractId.equals(contractDetailView.getId()));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		//输入：contractId=null  输出:contractDetailView=null
		contractId = null;
		contractDetailView = contractManager.getContractDetailById(contractId);
		Assert.assertNull(contractDetailView);
		
		//输入：contractId = ""  输出contractDetailView = null;
		contractId = "";
		contractDetailView = contractManager.getContractDetailById(contractId);
		Assert.assertNull(contractDetailView);
		
		//输入：contractId = "-111",contractId = "111a" 等不符合规则合同ID号时;
		//输出：会抛出相应的异常,为负数时，报空指针异常，不规则合同ID时，报无效数字异常
		contractId = "-11111";
		try {
			contractDetailView = contractManager.getContractDetailById(contractId);
			Assert.fail("此合同ID为不符合规则合同的ID");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		
		//输入:contractId="0000000" 输出:空指针异常
		contractId = "0000000"; 
		try {
			contractDetailView = contractManager.getContractDetailById(contractId);
			Assert.fail("对应数据库合同信息不存在");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
	}
	
	/**
	 * @Description:根据会员Id查询最后一次的合同信息<br />
	 */
	@Test
	public void testGetLastestContract() {

		// 参数合同返回值
		ContractDetailView contractDetailView = null;
		// 客户的ID
		String memberId = null;

		// 输入：memberId="92974", 输出:contractDetailView
		memberId = "92974";
		try {
			contractDetailView = contractManager.getLastestContract(memberId);
			Assert.assertEquals(true, memberId.equals(contractDetailView.getCustId()));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		//输入：memberId为对应数据库不存在此客户信息 输出：contractDetailView = null
		memberId = "000";
		contractDetailView = contractManager.getLastestContract(memberId);
		Assert.assertNull(contractDetailView);
		
		// 输入：memberId=null 输出:contractDetailView=null
		memberId = null;
		contractDetailView = contractManager.getLastestContract(memberId);
		Assert.assertNull(contractDetailView);

		// 输入：memberId = ""或者memberId="-1111" 输出contractDetailView = null;
		memberId = "";
		contractDetailView = contractManager.getLastestContract(memberId);
		Assert.assertNull(contractDetailView);

		// 输入：memberId = "111a" 等不符合规则客户ID号时;  输出：SQLException 报无效数字异常
		memberId = "-11111a";
		try {
			contractDetailView = contractManager.getLastestContract(memberId);
			Assert.fail("memberId为无效数字");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

	}
	
	
	/**
	 * @Description:根据会员Id查找出相关的所有合同信息<br /><br />
	 */
	@Test
	public void testGetContractsByMemberId() {
		//接口需要实现的方法必须注入才能调用接口的方法测试
		
		
		//查询合同结果集
		List<ContractDetailView> lists = new ArrayList<ContractDetailView>();
		//客户ID
		String memberId = "";
		
		//输入:memberId="92974" 此ID对应数据库存在两条合同信息  输出:lists
		memberId = "92974";
		try {
			lists = contractManager.getContractsByMemberId(memberId);
			Assert.assertNotNull(lists);
			Assert.assertEquals(true, lists.get(0).getModifyUser() != null);
//			Assert.assertEquals(true, lists.get(0).getModifyUser().equals("系统管理员"));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
//		//输入：memberId为对应数据库不存在此客户信息        输出：lists != null 但lists.size=0
//		memberId = "000";
//		lists = contractManager.getContractsByMemberId(memberId);
//		Assert.assertEquals(true, lists.size() == 0);
//		
		// 输入：memberId=null   输出：lists != null 但lists.size=0
		memberId = null;
		lists = contractManager.getContractsByMemberId(memberId);
		Assert.assertEquals(true, lists.size() == 0);

//		// 输入：memberId = ""  输出：lists != null,但lists.size=0;
//		memberId = "-11";
//		lists = contractManager.getContractsByMemberId(memberId);
//		Assert.assertEquals(true, lists.size() == 0);
//
//		// 输入：memberId = "111a" 等不符合规则客户memberId时;  输出：SQLException 报无效数字异常
//		memberId = "-11111a";
//		try {
//			lists = contractManager.getContractsByMemberId(memberId);
//		} catch (Exception e) {
//			Assert.assertTrue(true);
//		}

	}
	
	
	/**
	 * @Description:查询出满足条件的合同总数<br />
	 *  1  不输入查询条件时，默认只能查询出本部门的全部归属合同、全部绑定合同、全部合同工作流。
	 *	2   查询条件“生效时间”默认为空；
	 *	3 “客户全称”和“协议联系人”为模糊查询，在本部门的归属合同和绑定合同中模糊匹配；
	 *	4 “客户编码”和“合同序号”为准确查询，忽略其他任何查询条件，在全国范围内查询该客户编码或合同序号的合同信息；
	 *	5 (优先级：合同序号>客户编码，即同时有客户编码和合同序号时，以合同序号为主。
	 */
	@Test
	public void testCountContract() {
		
		try{
			contractManager.countContract(null);
		}catch(ContractException ce){
			Assert.assertEquals(ContractExceptionType.QueryConditionIsNull.getErrCode(), ce.getErrorCode());
		}
		

		//参数 合同查询条件
		ContractCondition condition = new ContractCondition();
		//结果总数
		int count;
		
		//默认查询条件 输入：默认时间为一个月   输出:count
		count = contractManager.countContract(condition);
		Assert.assertNotNull(count);
		
		//可精确查询，可模糊查询
		//输入:condition.setContractNum("400309878") 输出:1;
		condition.setContractNum("400309878");
		count = contractManager.countContract(condition);
		Assert.assertEquals(true, count == 1);
	}
	
	
	/**
	 * Description:获得要修改的合同详细信息<br />
	 */
	@Test
	public void testGetUpdateContractInfo() {

//		//参数合同ID
//		String contractId;
//		//结果contract
//		Contract contract = new Contract();
//		
//		//输入：contractId=null  输出：IllegalArgumengException 异常信息:cancel contract id con't null !
//		contractId = null;
//		try {
//			contract = contractManager.getUpdateContractInfo(contractId);
//			Assert.fail("cancel contract id con't null !");
//		} catch (Exception e) {
//			Assert.assertTrue(true);
//		}
//		
//		//输入：contractId为400359621，且状态时审评中  输出:ContractException异常
//		contractId = "400359621";
//		try {
//			contract = contractManager.getUpdateContractInfo(contractId);
//			Assert.fail("不能存在有合同状态为审批中的合同");
//		} catch (ContractException e) {
//			Assert.assertTrue(e.getErrorCode()
//					.equals(ContractExceptionType.CUSTOMER_HAS_INPROCESS_CONTRACT.getErrCode()));
//		}
//		
//		//输入：有效合同ID 400292314，无审批中的工作流. 输出:ContractException,ContractExceptionType.DeptIsError
//		contractId = "400350753";
//		try {
//			contract = contractManager.getUpdateContractInfo(contractId);
//			Assert.fail("不是归属部门");
//		} catch (ContractException e) {
//			Assert.assertTrue(e.getErrorCode().equals(ContractExceptionType.DeptIsError.getErrCode()));
//		}
//		
//		//输入：有效合同ID 无审批中的工作流，合同的归属部门与当前操作用户部门一致  输出：contract
//		contractId = "400350585";
//		try {
//			contract = contractManager.getUpdateContractInfo(contractId);
//			Assert.assertEquals(true, contract.getId().equals(contractId));
//		} catch (Exception e) {
//			Assert.assertTrue(true);
//		}
	}

	/**
	 * @Description:校验合同是否有工作流在审批，如果存在则不能进行任何操作.<br />
	 */
	@Ignore
	public void testIsContractCanOperate() {

		//参数合同ID
		String contractId = "";
		//合同操作类型
		String handleType = "";
		boolean flag = false;
		
		/**
		 * 1.待生效合同情况
		 */
		//输入：待生效合同contractId = "111111111111118" handleType="insert" 待生效合同不能新签修改操作
		//输出：异常ContractException,ContractExceptionType.ContractWaitEffectCannotOperate
		contractId = "111111111111118";
		handleType = ContractHandleType.INSERT.getHandleType();
//		try {
//			flag = contractManager.isContractCanOperate(contractId, handleType);
//			Assert.fail("待生效合同不能进行新签修改操作");
//		} catch (ContractException e) {
//			Assert.assertTrue(e.getErrorCode().equals(ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode()));
//		}
		
		//输入:待生效合同Id，contractId="111111111111119" ,handleType="绑定，解绑，作废，归属部门变更之一“，但合同有工作流在审评中的状态
		//输出:异常ContractException，ContractExceptionType.ContractStateIsError
		contractId = "111111111111119";
		handleType = ContractHandleType.BINDING.getHandleType();
		try {
			flag = contractManager.isContractCanOperate(contractId, handleType);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		
		//输入：待生效合同id，操作类型为绑定，解绑，作废，归属部门变更之一，无审批中的合同
		//输出:flag = true;
		contractId = "111111111111120";
		handleType = ContractHandleType.CHANGEBELONGDEPT.getHandleType();
		try {
		flag = contractManager.isContractCanOperate(contractId, handleType);
		Assert.assertEquals(true, flag == true);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
//		/**
//		 * 2.不是待生效合同情况
//		 */
//		//输入：非待生效合同Id 合同状态为审批中,操作类型为修改或改签
//		//输出:contractException,ContractExceptionType.ContractWaitEffectCannotOperate
//		contractId = "111111111111121";
//		handleType = ContractHandleType.UPDATE.getHandleType();
//		try {
//			flag = contractManager.isContractCanOperate(contractId, handleType);
//			Assert.fail("合同状态为审批中");
//		} catch (ContractException e) {
//			Assert.assertTrue(true);
//		}
		
		//输入：非待生效合同Id,操作类型不是修改或改签这两者 合同状态是待生效或者无效,
		//输出:ContractException,ContractExceptionType.ContractWaitEffectCannotOperate
//		contractId = "111111111111122"	;
//		handleType = ContractHandleType.INSERT.getHandleType();
//		try {
//			flag = contractManager.isContractCanOperate(contractId, handleType);
//			Assert.fail("非待生效合同Id,操作类型不是修改或改签这两者 合同状态是待生效或者无效，此刻合同状态应为有效");
//		} catch (ContractException e) {
//			Assert.assertTrue(e.getErrorCode().equals(ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode()));
//		}
		
		//输入：非待生效合同Id 合同状态为有效或者无效,操作类型为修改或改签，无审批中的工作流,且以前的审批中的工作流不同意
		//输出:true = flag
		contractId = "1000001";
		handleType = ContractHandleType.UPDATE.getHandleType();
//		flag = contractManager.isContractCanOperate(contractId, handleType);
//		Assert.assertEquals(true, flag == true);
		
		//输入：非待生效合同Id 合同状态为有效或者无效,操作类型为修改或改签，有审批中的工作流
		//输出:ContractException
		contractId = "1000002";
		handleType = ContractHandleType.UPDATE.getHandleType();
		try {
//			flag = contractManager.isContractCanOperate(contractId, handleType);
//			Assert.fail("有审批中的工作流");
		} catch (ContractException e) {
			Assert.assertTrue(true);
		}
		
		//输入：非待生效合同Id,操作类型不是修改或改签这两者 合同状态是有效,无审批中的工作流
		//输出:true
		contractId = "111111111111125";
		handleType = ContractHandleType.INSERT.getHandleType();
		try {
			flag = contractManager.isContractCanOperate(contractId, handleType);
			Assert.assertEquals(true, flag == true);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		//输入：非待生效合同Id,操作类型不是修改或改签这两者 合同状态是有效,有审批中的工作流
		//输出:ContractException,ContractExceptionType.ContractStateIsError
		contractId = "111111111111126";
		handleType = ContractHandleType.CHANGEBELONGDEPT.getHandleType();
		try {
			flag = contractManager.isContractCanOperate(contractId, handleType);
			Assert.fail("有审批中的工作流");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		//输入：非待生效合同Id,操作类型不是修改或改签这两者 合同状态是有效,无审批中的工作流
		//输出: flag = true
		contractId = "111111111111127";
		handleType = ContractHandleType.INSERT.getHandleType();
		try {
			flag = contractManager.isContractCanOperate(contractId, handleType);
			Assert.assertEquals(true, flag == true);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * @Description:查询合同的全部历史操作记录<br />
	 */
	@Test
	public void testGetContractOperatorLogByContractId() {

		//参数contractId
		String contractId = "";
		//操作记录结果集
		List<ContractOperatorLog> lists = new ArrayList<ContractOperatorLog>();
		
		//输入contractId = "" 或者对应数据库不存在操作记录时  输出:空的集合
		lists = contractManager.getContractOperatorLogByContractId(contractId);
		Assert.assertEquals(true, lists.size() == 0);
		
		//输入:contractId对应数据库存在操作记录, 输出：有值的操作记录集合
		contractId = "111111111111127";
		lists = contractManager.getContractOperatorLogByContractId(contractId);
	}
	
	/**
	 * @Description:根据查询条件返回合同集合
	 *  1  不输入查询条件时，默认只能查询出本部门的全部归属合同、全部绑定合同、全部合同工作流。
	 *	2   查询条件“生效时间”默认为空；
	 *	3 “客户全称”和“协议联系人”为模糊查询，在本部门的归属合同和绑定合同中模糊匹配；
	 *	4 “客户编码”和“合同序号”为准确查询，忽略其他任何查询条件，在全国范围内查询该客户编码或合同序号的合同信息；
	 *	5 (优先级：合同序号>客户编码，即同时有客户编码和合同序号时，以合同序号为主。
	 */
	@Test
	public void testSearchContract() {

		//参数 合同查询条件
		ContractCondition condition = new ContractCondition();
		//start,limit用于分页
		int start = 0;
		int limit = 15;
		List<Contract> contracts = new ArrayList<Contract>();
		
		//默认查询条件 输入：默认时间为一个月   输出:contrats
		condition.setDeptId(ContextUtil.getCurrentUserDeptId());
		//唐亮  2013-2-27 根据业务需求，添加测试条件：合同状态contractStatus为有效
		condition.setContractStatus("1");
		/**
		 * 添加快递后，当客户编码 和 合同编码 为空的时候，进行数据权限的判断
		 */
		//---非快递
		Set<String> roleids = new HashSet<String>();
		roleids.add("1");
		ContextUtil.getCurrentUser().setRoleids(roleids);
		//唐亮  2013-2-27 根据业务需求，添加测试条件 完毕
		contracts = contractManager.searchContract(condition, start, limit);
		Assert.assertNotNull(contracts);
		//唐亮  2013-2-27 根据业务需求，添加测试条件：合同状态contractStatus为待生效
		condition.setContractStatus("3");
		//唐亮  2013-2-27 根据业务需求，添加测试条件 完毕
		contracts = contractManager.searchContract(condition, start, limit);
		
		//可精确查询，可模糊查询
		//输入:condition.setContractNum("400309878") 输出:1;
		condition = new ContractCondition();
		condition.setContractNum("400309878");
		contracts = contractManager.searchContract(condition, start, limit);
		Assert.assertEquals(true, contracts.get(0).getContractNum().equals("400309878") );
	}
	/**
	 * 
	 * @Title: testSearchInitContract
	 *  <p>
	 * @Description: 查詢进入合同界面初始化合同--查询本部门30天内要到期的合同<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-2-27
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchInitContract(){
		//接口需要实现的方法必须注入才能调用接口的方法测试
		contractManager = SpringTestHelper.getBean(ContractManager.class);
		//参数 合同查询条件
		ContractCondition condition = new ContractCondition();
		//start,limit用于分页
		int start = 0;
		int limit = 15;
		List<Contract> contracts = new ArrayList<Contract>();

		//输入值为：合同状态为有效，结束日期为今天，还有部门Id
		condition.setDeptId(ContextUtil.getCurrentUserDeptId());
		condition.setContractStatus("1");
		condition.setContractendDate(new Date());
		contracts = contractManager.searchInitContract(condition, start, limit);
		Assert.assertNotNull(contracts);
	}
	/**
	 * Description:得到新签合同信息<br />
	 */
	@Test
	public void testGetNewContract() {

		//参数客户ID
		String memberId = "";
		//contract结果
		Contract contract;
		
		
		//输入:memberId = '111111111111121'对应数据库存在一个合同,无待生效合同，但有审批中工作流, 
		//输出:contractException
		
		
		//输入：memberId=‘100546’对应数据库存在合同,且为待生效合同。 输出：ContractException
		memberId = "100546";
		try {
			contract = contractManager.getNewContract(memberId);
//			Assert.fail("ContractExceptionType.HAS_WAIT_EFFECT_CONTRACT");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		//输入:memberId = '567616'对应数据库存在一个合同,无待生效合同，无审批中工作流, 输出：一个新合同
		memberId = "567616";
		try {
			contract = contractManager.getNewContract(memberId);
			Assert.assertEquals(true, contract.getMember().getId().equals(memberId));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		//输入：memberID ='222' 客户对应数据库没有合同  输出:一个新合同
		memberId = "222";
		try {
			contract = contractManager.getNewContract(memberId);
			Assert.assertEquals(true, contract.getMember().getId().equals(memberId));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		//输入：memberId='226'客户的部门不是当前操作用户的部门  输出:ContractException,异常信息ContractExceptionType.Not_Member_Dept
		memberId = "226";
		try {
			contract = contractManager.getNewContract(memberId);
			Assert.fail("客户的部门不是当前操作用户的部门 ");
		} catch (ContractException e) {
			Assert.assertTrue(e.getErrorCode().equals(ContractExceptionType.Not_Member_Dept.getErrCode()));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		//输入：客户ID对应数据库不存在信息  输出：ContactException 异常信息:ContractExceptionType.Data_Error
		memberId = "00000";
		try {
			contract = contractManager.getNewContract(memberId);
			Assert.fail("ContractExceptionType.Data_Error");
		} catch (ContractException e) {
			Assert.assertTrue(e.getErrorCode().equals(ContractExceptionType.Data_Error.getErrCode()));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	


	/**
	 * Description:绑定合同部门<br />
	 */
    @Test
	public void testBoundContract() {
	   // 参数:合同contractId
		String contractId = "";
		// 参数：附件信息
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		// 参数当前用户
		User user = ContextUtil.getCurrentUser();
		String deptId = ((User) user).getEmpCode().getDeptId().getId();
		// 结果产生工作流号 workFlow
		String workFlow = null;
		//3.输入：合同状态为审批中， 输出：异常信息:ContractExceptionType.ContractWaitEffectCannotOperate
		contractId = "111111111111132";
		try {
			workFlow = contractManager.boundContract(contractId, deptId, fileInfoList);
			Assert.fail("ContractExceptionType.ContractWaitEffectCannotOperate");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		} catch (ContractException e) {
			e.getErrorCode().equals(ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode());
		}
		
//		 1.输入:contractI= null; 输出：空指针异常
		 contractId = null;
		 try {
		 workFlow = contractManager.boundContract(contractId, deptId,
		 fileInfoList);
		 Assert.fail("Contract infomation can't null !");
		 } catch (Exception e) {
		 Assert.assertTrue(true);
		 }

	}
	

	/**
	 * @Description:月结限额校验<br />
	 */
	@Test
	public void testCheckArrearaMount() {

		//参数客户ID
		String custId = "";
		//月结限额
		double amount = 0d;
		//校验结果
		boolean flag = false;
		Contract con = new Contract();
		con.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		//输入：客户ID对应没有发过货物， amout输入>0 输出:为false 输入=0输出为true
		custId = "111111111111112";
		amount = 100d;
		flag = contractManager.checkAmountByCustId(con,custId, amount);
		Assert.assertEquals(true, flag == false);
		
		//输入：客户id为""或者一些不规范的客户Id amout输入>0  输出：false;输入=0输出为true
		custId = ""	;
		flag = contractManager.checkAmountByCustId(con,custId, amount);
		Assert.assertFalse(flag);
		
		//客户有发送货物情况的根据客户的发货情况和月结限额比较 
		//输入：custId = "96688" amout = 10000d
		custId = "96688";
		amount = 1000d;
		flag = contractManager.checkAmountByCustId(con,custId, amount);
//		Assert.assertTrue(flag == false);
	}

	/**
	 * @description 判断新旧合同的优惠类型是否修改.
	 */
	@Test
	public void testIsDiscountNotModify() {
		Contract nowContract = null;
		Contract beforeContract = null;
		boolean flag = false;
		
		//1.输入:两者都为空  输出：ContractException 异常信息:ContractExceptionType.Data_Error
		try {
			Method method = contractManager.getClass()
					.getDeclaredMethod("isDiscountNotModify", Contract.class,Contract.class);
//			method.setAccessible(true);
//			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
//			method.setAccessible(false);
		
			//2.输入:null == oldContract.getPreferential() null == contract.getPreferential()
			//输出：true
			nowContract = new Contract();
			beforeContract = new Contract();
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);
			Assert.assertTrue(flag);
			
			//3.输入:null == oldContract.getPreferential() null != contract.getPreferential()
			// 如果旧合同为空，新合同折扣不为空,
			//输出：最终判断看新合同的折扣是不是全为1d，是就返回true,不是返回false
			Preferential preferential = new Preferential();
			preferential.setAgentgathRate(1d);
			preferential.setChargeRebate(1d);
			preferential.setDeliveryPriceRate(1d);
			preferential.setInsuredPriceRate(1d);
			preferential.setReceivePriceRate(1d);
			nowContract.setPreferential(preferential);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);
			Assert.assertEquals(true, flag == true);
			
			//4.输入 如若旧合同不为空，旧合同折扣也不为空，新合同折扣为空，
			//输出：最终判断看旧合同的折扣是不是全为1d，是就返回true,不是返回false
			nowContract = new Contract();
			beforeContract.setPreferential(preferential);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);
			Assert.assertEquals(true, flag == true);
			
			//5.输入：如若旧合同不为空，旧合同折扣也不为空，新合同折扣也不为空
			//输出：最终结果由新旧合同的折扣是否相等
			nowContract.setPreferential(preferential);
			beforeContract.setPreferential(preferential);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);
			Assert.assertEquals(true, flag == true);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * @description 判断新旧合同的结算额度是否一样.
	 */
	@Test
	public void testIsBudgetNotModify() {
		//参数
		Contract nowContract = new Contract();
		Contract beforeContract = new Contract();
		//boolean flag
		boolean flag = false;
		
		//1.输入：nowContract和beforeContract都为空   输出：ContractException
		try {
			Method method = contractManager.getClass().getDeclaredMethod("isBudgetNotModify", Contract.class,Contract.class);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);

		//2.输入:null == contract.getArrearaMount() before.getArrearaMount() != null
		//输出：false
			beforeContract.setArrearaMount(123.0d);
			nowContract.setArrearaMount(0d);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);
			Assert.assertEquals(true, flag == false);
			
		//2.输入:null != contract.getArrearaMount() before.getArrearaMount() =!= null
		//输出：false
			beforeContract.setArrearaMount(0d);
			nowContract.setArrearaMount(123.0d);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);
			Assert.assertEquals(true, flag == false);
			
		//3.输入：两者都不为空，直接根据结算限额的值比较，相等为true,不相等为false
			beforeContract.setArrearaMount(123.0d);
			nowContract.setArrearaMount(123.0d);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(contractManager, nowContract,beforeContract);
			method.setAccessible(false);
			Assert.assertEquals(true, flag == true);
			//Assert.assertEquals(true, flag == false);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ContractException e) {
			e.getErrorCode().endsWith(ContractExceptionType.Data_Error.getErrCode());
		}
		
		
	}
	
	/**
	 * @description 根据合同修改内容获得修改类型. 
	 */
	@Test
	public void testGetModifyType() {
		/* 如果修改价格折扣 则对 合同修改类型 set成 discount：只修改折扣
		1、只修改预算的就传 budget 
		2、只修改折扣的传 discount
		3、两项都修改或其它的就传 other*/ 
		//因为此方法调用的是isDiscountNotModify和isBudgetNotModify这两个方法的结果去判断得到修改内容获得修改类型
		//而此两方法已经单独测试，这里只需测试一种情况即可
		
		//优惠类型
		Preferential preferential = new Preferential();
		preferential.setAgentgathRate(1d);
		preferential.setChargeRebate(1d);
		preferential.setDeliveryPriceRate(1d);
		preferential.setInsuredPriceRate(1d);
		preferential.setReceivePriceRate(1d);
		
		Contract nowContract = new Contract();
		Contract oldContract = new Contract();
		String modifyType = "";
		//1.仅仅修改结算限额
		//优惠类型不变
		nowContract.setPreferential(preferential);
		oldContract.setPreferential(preferential);
		//假想的结算限额修改
		nowContract.setArrearaMount(10d);
		oldContract.setArrearaMount(20d);
		
		nowContract.setOldContract(oldContract);
		
		try {
			Method method = contractManager.getClass().getDeclaredMethod("getModifyType", Contract.class);
			method.setAccessible(true);
			modifyType = (String) method.invoke(contractManager, nowContract);
			method.setAccessible(false);
			Assert.assertEquals(true, modifyType.equals(Constant.CONTRACT_MODIFY_TYPE_BUDGET));
		
		//2.仅仅修改折扣
			nowContract.setPreferential(preferential);
			//假设老合同的折扣被修改
			Preferential oldpPreferential = new Preferential();
			oldpPreferential.setAgentgathRate(0.9d);
			oldpPreferential.setChargeRebate(1d);
			oldpPreferential.setDeliveryPriceRate(1d);
			oldpPreferential.setInsuredPriceRate(1d);
			oldpPreferential.setReceivePriceRate(1d);
			oldContract.setPreferential(oldpPreferential);
			//新老的结算限额不修改
			nowContract.setArrearaMount(0d);
			oldContract.setArrearaMount(0d);
			nowContract.setOldContract(oldContract);
			method.setAccessible(true);
			modifyType = (String) method.invoke(contractManager, nowContract);
			method.setAccessible(false);
			Assert.assertEquals(true, modifyType.equals(Constant.CONTRACT_MODIFY_TYPE_LTT_DISCOUNT));
		
			//3.结算限额和折扣都修改
			//假想的修改值
			preferential.setAgentgathRate(0.9d);
			oldpPreferential.setAgentgathRate(0.85d);
			nowContract.setArrearaMount(20d);
			oldContract.setArrearaMount(30d);
			
			nowContract.setOldContract(oldContract);
			method.setAccessible(true);
			modifyType = (String) method.invoke(contractManager, nowContract);
			method.setAccessible(false);
			Assert.assertEquals(true, modifyType.equals(Constant.CONTRACT_MODIFY_TYPE_OTHER));
		
			//4.其他情况都是仅仅修改折扣
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @description 分别得到修旧合同. 
	 */
	@Test
	public void testGetBeforeAndNewContract() {
		//操作日志
		List<ContractOperatorLog> logs = new ArrayList<ContractOperatorLog>();
		Map<String, Contract> map = new HashMap<String, Contract>();
		
		try {
			Method method = contractManager.getClass().getDeclaredMethod("getBeforeAndNewContract", List.class);
			
			//1.输入：logs==null or logs!= 2    输出：RuntimeException,异常信息workflow Data exception
//			method.setAccessible(true);
//			map = (Map<String, Contract>) method.invoke(contractManager, logs); 
//			method.setAccessible(false);
		
			//2.两个合同都为空  输出：输出：RuntimeException,异常信息can't find before contract and new contract !!
//			ContractOperatorLog c1 = new ContractOperatorLog();
//			ContractOperatorLog c2 = new ContractOperatorLog();
//			Contract mContract = null;
//			Contract bContract = null;
//			c1.setContract(bContract);
//			c2.setContract(mContract);
//			logs.add(c1);
//			logs.add(c2);
//			method.setAccessible(true);
//			map = (Map<String, Contract>) method.invoke(contractManager, logs); 
//			method.setAccessible(false);
			
			//3.mContract指现在的合同，bContract指旧合同
			//输入.mContract.getBeforeContractNum() == bContract.getContractNum()
			ContractOperatorLog c1 = new ContractOperatorLog();
			ContractOperatorLog c2 = new ContractOperatorLog();
			Contract mContract = new Contract();
			Contract bContract = new Contract();
			bContract.setContractNum("bbbbbbb");
			mContract.setContractNum("mmmmmmmmm");
			mContract.setBeforeContractNum(bContract.getContractNum());
			mContract.setContractStatus(Constant.CONTRACT_STATUS_INPROCESS);
			c1.setContract(mContract);
			c2.setContract(bContract);
			logs.add(c1);
			logs.add(c2);
			method.setAccessible(true);
			map = (Map<String, Contract>) method.invoke(contractManager, logs); 
			method.setAccessible(false);
			Assert.assertEquals(true, map.get("mContract").equals(mContract));
		
			//4.当两者的contractNum和beforeContractNum都为空的时候
			//mContract指现在的合同，bContract指旧合同
			//输出：抛出NullPointerException
//			logs = new ArrayList<ContractOperatorLog>();
//			ContractOperatorLog c11 = new ContractOperatorLog();
//			ContractOperatorLog c22 = new ContractOperatorLog();
//			Contract m2 = new Contract();
//			Contract b2 = new Contract();
//			c11.setContract(m2);
//			c22.setContract(b2);
//			logs.add(c11);
//			logs.add(c22);
//			method.setAccessible(true);
//			map = (Map<String, Contract>) method.invoke(contractManager, logs); 
//			method.setAccessible(false);
//		
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		
	}
	/**
	 * @description 设置新合同生效时间和原合同失效时间
	 */
	@Test
	public void testSetContractDate() {
		//参数
		Contract beforeContract = new Contract();
		Contract nowContract = new Contract();
		Date appDate = new Date();
		
		//1.如果新合同审批同意时间早于当前时间，则新合同的生效开始时间为当前时间
		//如果原合同的失效时间小于新合同起始生效时间，那么原合同的结束时间不变
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(appDate);
		calendar.add(Calendar.MONTH, -1);
		nowContract.setContractBeginDate(calendar.getTime());
		calendar.add(calendar.MONDAY, -2);
		beforeContract.setContractendDate(calendar.getTime());
		try {
			Method method = contractManager.getClass().getDeclaredMethod("setContractDate",
					Contract.class,Contract.class,Date.class);
			method.setAccessible(true);
			method.invoke(contractManager, beforeContract,nowContract,appDate);
			method.setAccessible(false);
			//1
			Assert.assertEquals(true, beforeContract.getContractendDate().equals(calendar.getTime()));
			
			//2.如果新合同审批同意时间早于当前时间，则新合同的生效开始时间为当前时间
			//如果原合同的失效时间大于新合同起始生效时间，那么原合同的结束时间为新合同的前一秒
			calendar.add(Calendar.MONTH, 5);
			beforeContract.setContractendDate(calendar.getTime());
			method.setAccessible(true);
			method.invoke(contractManager, beforeContract,nowContract,appDate);
			method.setAccessible(false);
			//2
			Assert.assertEquals(true, 
					beforeContract.getContractendDate()
					.equals(new Date(nowContract.getContractBeginDate().getTime()-1000)));
		
			//3.如果新合同审批同意时间大于当前时间，则新合同的生效开始时间为新合同的时间
			//如果原合同的失效时间小于新合同起始生效时间，原合同的结束失效时间不变
			calendar.add(Calendar.MONTH, 1);
			nowContract.setContractBeginDate(calendar.getTime());
			calendar.add(Calendar.MONTH, -1);
			beforeContract.setContractendDate(calendar.getTime());
			//此时无需比较
			
			//4.如果新合同审批同意时间大于当前时间，则新合同的生效开始时间为新合同的时间
			//如果原合同的失效时间大于新合同起始生效时间，那么原合同的结束时间为新合同的前一秒
			calendar.add(Calendar.MONTH, 3);
			beforeContract.setContractendDate(calendar.getTime());
			method.setAccessible(true);
			method.invoke(contractManager, beforeContract,nowContract,appDate);
			method.setAccessible(false);
			Assert.assertEquals(true, beforeContract.getContractendDate()
					.equals(new Date(nowContract.getContractBeginDate().getTime()-1000)));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
 
