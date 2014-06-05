package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.jmock.Expectations;
import org.jmock.Mockery;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.customer.server.data.ContractManagerData;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.context.UserContext;
import junit.framework.TestCase;

public class BoundContractTest extends TestCase {
	private IContractManager contractManager = SpringTestHelper.getBean(ContractManager.class);
	private DataDictionaryCacheProvider dataDictionaryCacheProvider = SpringTestHelper.getBean(DataDictionaryCacheProvider.class);
	private Mockery mock = new Mockery();

	public void setUp() throws SQLException {
		clear_data();
		// 缓存当前用户数据
		User user = new User(); 
		Department depart = new Department();
		depart.setId("19216812");
		depart.setDeptName("单元测试事业部");
		depart.setStandardCode("25465-asfsd-swerw-wew-wew");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setRoleids(new HashSet<String>());
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);

	}

	public void tearDown() throws SQLException {
		CacheManager.getInstance().shutdown();
		clear_data();
	}

	/**
	 * 
	 * <p>
	 * Description:解除合同绑定<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_removeContract_scan1() throws SQLException {
		Contract contractInfo = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		Member member = new Member();
		member.setId("192168");
		contractInfo.setMember(member);
		contractInfo.setId("192168002");

		List<ContractDept> contractDepartList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		Department depart = new Department();
		depart.setId("19216812");
		contractDept.setContractDept(depart);
		contractDept.setDept(true);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);

		contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		depart = new Department();
		depart.setId("19216813");
		contractDept.setContractDept(depart);
		contractDept.setDept(false);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);
		contractInfo.setContractDepartList(contractDepartList);
		try {
			contractManager.removeContract(contractInfo, "19216813");
			fail("异常没有抛出！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.ContractIsNull.getErrCode(), ce.getErrorCode());
		}

	}

	/**
	 * 
	 * <p>
	 * Description:解除合同绑定<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_removeContract_scan2() throws SQLException {
		init_remove2();

		Contract contractInfo = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		Member member = new Member();
		member.setId("192168");// 客户ID
		contractInfo.setMember(member);
		contractInfo.setId("192168002");

		List<ContractDept> contractDepartList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		Department depart = new Department();
		depart.setId("19216812");// 上海徐泾镇开发测试营业部
		contractDept.setContractDept(depart);
		contractDept.setDept(true);// 是归属部门
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);

		contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		depart = new Department();
		depart.setId("19216813");// 上海徐泾镇开发测试营业部2
		contractDept.setContractDept(depart);
		contractDept.setDept(false);// 不是归属部门
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);
		contractInfo.setContractDepartList(contractDepartList);
		try {
			contractManager.removeContract(contractInfo, "19216813");
			fail("异常没有抛出！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.DeptIsNotBondingDept.getErrCode(), ce.getErrorCode());
		}

	}

	/**
	 * 
	 * <p>
	 * Description:解除合同绑定,合同在审批中<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_removeContract_scan3() throws SQLException {
		init_remove3();

		Contract contractInfo = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		Member member = new Member();
		member.setId("192168");
		contractInfo.setMember(member);
		contractInfo.setId("192168001");

		List<ContractDept> contractDepartList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		Department depart = new Department();
		depart.setId("19216812");
		contractDept.setContractDept(depart);
		contractDept.setDept(true);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);

		contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		depart = new Department();
		depart.setId("19216813");
		contractDept.setContractDept(depart);
		contractDept.setDept(false);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);
		contractInfo.setContractDepartList(contractDepartList);
		try {
			contractManager.removeContract(contractInfo, "19216813");
			fail("异常没有抛出！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode(), ce.getErrorCode());
		}

	}

	/**
	 * 
	 * <p>
	 * Description:解除合同绑定,合同在审批中<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_removeContract_scan4() throws SQLException {
		init_remove3();

		Contract contractInfo = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		Member member = new Member();
		member.setId("192168");
		contractInfo.setMember(member);
		contractInfo.setId("192168001");

		List<ContractDept> contractDepartList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		Department depart = new Department();
		depart.setId("19216812");
		contractDept.setContractDept(depart);
		contractDept.setDept(true);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);

		contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		depart = new Department();
		depart.setId("19216813");
		contractDept.setContractDept(depart);
		contractDept.setDept(false);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);
		contractInfo.setContractDepartList(contractDepartList);
		try {
			contractManager.removeContract(contractInfo, null);
			fail("异常没有抛出！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.DeptIsNotBondingDept.getErrCode(), ce.getErrorCode());
		}

		try {
			contractManager.removeContract(contractInfo, "19216814");
			fail("异常没有抛出！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.DeptIsNotBondingDept.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:解除合同绑定,合同在审批中<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_removeContract_scan5() throws SQLException {
		init_remove3();

		Contract contractInfo = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		Member member = new Member();
		member.setId("192168");
		contractInfo.setMember(member);
		contractInfo.setId("192168001");

		List<ContractDept> contractDepartList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		Department depart = new Department();
		depart.setId("19216812");
		contractDept.setContractDept(depart);
		contractDept.setDept(true);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);

		contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		depart = new Department();
		depart.setId("19216813");
		contractDept.setContractDept(depart);
		contractDept.setDept(false);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);
		contractInfo.setContractDepartList(contractDepartList);

		try {
			contractManager.removeContract(contractInfo, "19216814");
			fail("异常没有抛出！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.DeptIsNotBondingDept.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:解除合同绑定<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_removeContract_scan6() throws SQLException {
		init_remove1();

		Contract contractInfo = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		Member member = new Member();
		member.setId("192168");
		contractInfo.setMember(member);
		contractInfo.setId("192168001");

		List<ContractDept> contractDepartList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		Department depart = new Department();
		depart.setId("19216812");
		contractDept.setContractDept(depart);
		contractDept.setDept(true);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);

		contractDept = new ContractDept();
		contractDept.setContract(contractInfo);
		depart = new Department();
		depart.setId("19216813");
		contractDept.setContractDept(depart);
		contractDept.setDept(false);
		contractDept.setState(true);
		contractDept.setVersionNumber(0);
		contractDepartList.add(contractDept);
		contractInfo.setContractDepartList(contractDepartList);
		boolean result = contractManager.removeContract(contractInfo, "19216813");

		assertTrue(result);

	}

	/**
	 * 
	 * <p>
	 * Description:绑定合同，要绑定部门不属于同一个子公司<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-5 void
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void test_boundCon_scan1() throws SQLException {
		init_scan1();

		// 获取数据字典放入缓存
		final ICache<String, Head> cache = mock.mock(ICache.class);
		mock.checking(new Expectations() {
			{
				allowing(cache).getUUID();
				will(returnValue(Head.class.getName()));

				allowing(cache).get(DataHeadTypeEnum.RECKON_WAY.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.RECKON_WAY.toString())));

				allowing(cache).get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString())));

			}
		});
		CacheManager.getInstance().registerCacheProvider(cache);
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {
			contractManager.boundContract("192168001", "19216812", fileInfoList);
			fail();
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.CONTRACTSUBJECTS_IS_DIFFERENTCOMPANIES.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:绑定合同，合同是待生效的<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-5 void
	 * @throws SQLException
	 */
	public void test_boundCon_scan2() throws SQLException {
		init_scan2();

		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {
			contractManager.boundContract("192168001", "19216813", fileInfoList);
			fail("为抛出异常！");
		} catch (ContractException e) {
			assertEquals(ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode(), e.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:绑定合同，要绑定部门属于同一个子公司<br />
	 * 绑定成功
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-5 void
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void test_boundCon_scan3() throws SQLException {
		init_scan3();

		// 获取数据字典放入缓存
		final ICache<String, Head> cache = mock.mock(ICache.class);
		mock.checking(new Expectations() {
			{
				allowing(cache).getUUID();
				will(returnValue(Head.class.getName()));

				allowing(cache).get(DataHeadTypeEnum.RECKON_WAY.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.RECKON_WAY.toString())));

				allowing(cache).get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString())));

			}
		});
		CacheManager.getInstance().registerCacheProvider(cache);
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);

		String flowId = contractManager.boundContract("192168001", "19216812", fileInfoList);
		assertNotNull(flowId);

	}

	/**
	 * 
	 * <p>
	 * Description:绑定合同，要绑定部门不属于同一个子公司<br />
	 * 合同主体是德邦总公司 绑定成功
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-5 void
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void test_boundCon_scan4() throws SQLException {
		init_scan4();

		// 获取数据字典放入缓存
		final ICache<String, Head> cache = mock.mock(ICache.class);
		mock.checking(new Expectations() {
			{
				allowing(cache).getUUID();
				will(returnValue(Head.class.getName()));

				allowing(cache).get(DataHeadTypeEnum.RECKON_WAY.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.RECKON_WAY.toString())));

				allowing(cache).get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString());
				will(returnValue(dataDictionaryCacheProvider.get(DataHeadTypeEnum.PRIVILEGE_TYPE.toString())));

			}
		});
		CacheManager.getInstance().registerCacheProvider(cache);
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);

		String flowId = contractManager.boundContract("192168001", "19216812", fileInfoList);
		assertNotNull(flowId);

	}

	private void init_remove1() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216813.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301')");

		sqlList.add("    insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY, FCOMPADDR, FBRANDWORTH, FBILLTITLE,    FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ " FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER) "
				+ " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259', "
				+ " 'NORMAL', 'PERSONAL', '0', '1', 19216812, "
				+ "'ITRONIC_FURNITURE', 'NULL', null, 'A', 0,  "
				+ "0, 1, 20000, null, 'HALL' "
				+ " , null, '2', null, "
				+ " 1, '江苏', '无锡市', "
				+ "  '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司',"
				+ " 'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL', "
				+ " 0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1') ");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168," + "19216812, null, null, null, null, " + "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '1', null," + " 0, 0)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168102,86301,192168001,19216812,sysdate-60," + "sysdate+60,'329474859439534',1,1)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168103,86301,192168001,19216813,sysdate-40," + "sysdate+40,'329474859439534',0,1)");
		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_remove2() throws SQLException {

		List<String> sqlList = new ArrayList<String>();

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216813.92973.92974.86301',1)");

		sqlList.add("    insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY, FCOMPADDR, FBRANDWORTH, FBILLTITLE,    FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ " FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER) "
				+ " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259', "
				+ " 'NORMAL', 'PERSONAL', '0', '1', 19216812, "
				+ "'ITRONIC_FURNITURE', 'NULL', null, 'A', 0,  "
				+ "0, 1, 20000, null, 'HALL' "
				+ " , null, '2', null, "
				+ " 1, '江苏', '无锡市', "
				+ "  '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司',"
				+ " 'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL', "
				+ " 0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1') ");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168002, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168," + "19216812, null, null, null, null, " + "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '1', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_remove3() throws SQLException {

		List<String> sqlList = new ArrayList<String>();

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216813.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301')");

		sqlList.add("    insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY, FCOMPADDR, FBRANDWORTH, FBILLTITLE,    FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ " FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER) "
				+ " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259', "
				+ " 'NORMAL', 'PERSONAL', '0', '1', 19216812, "
				+ "'ITRONIC_FURNITURE', 'NULL', null, 'A', 0,  "
				+ "0, 1, 20000, null, 'HALL' "
				+ " , null, '2', null, "
				+ " 1, '江苏', '无锡市', "
				+ "  '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司',"
				+ " 'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL', "
				+ " 0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1') ");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168," + "19216812, null, null, null, null, " + "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," + " 0, 0)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168102,86301,192168001,19216812,sysdate-60," + "sysdate+60,'329474859439534',1,1)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168103,86301,192168001,19216813,sysdate-40," + "sysdate+40,'329474859439534',0,1)");
		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan1() throws SQLException {

		List<String> sqlList = new ArrayList<String>();

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,FSTATUS) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,FSTATUS) "
				+ "values(19216813,'wkedkjhsdiu-sdj344','南京市市外高桥开发测试营业部','南京德邦物流有限公司',92974,'DPTEST893423','19216812.92973.92974.86301',1)");

		sqlList.add("    insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY, FCOMPADDR, FBRANDWORTH, FBILLTITLE,    FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ " FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER) "
				+ " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259', "
				+ " 'NORMAL', 'PERSONAL', '0', '1', 19216812, "
				+ "'ITRONIC_FURNITURE', 'NULL', null, 'A', 0,  "
				+ "0, 1, 20000, null, 'HALL' "
				+ " , null, '2', null, "
				+ " 1, '江苏', '无锡市', "
				+ "  '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司',"
				+ " 'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL', "
				+ " 0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1') ");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'NOT_MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168," + "19216813, null, '南京德邦物流有限公司', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan2() throws SQLException {

		List<String> sqlList = new ArrayList<String>();

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,FSTATUS) "
				+ "values(19216813,'wkedkjhsdiu-sdj344','南京市市外高桥开发测试营业部','上海德邦物流有限公司',92974,'DPTEST893423','19216812.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,fcompanyname,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(86301,'237434253453','德邦物流','上海德邦物流有限公司','1386984545',0,'86301')");

		sqlList.add("    insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY, FCOMPADDR, FBRANDWORTH, FBILLTITLE,    FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ " FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER) "
				+ " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259', "
				+ " 'NORMAL', 'PERSONAL', '0', '1', 19216812, "
				+ "'ITRONIC_FURNITURE', 'NULL', null, 'A', 0,  "
				+ "0, 1, 20000, null, 'HALL' "
				+ " , null, '2', null, "
				+ " 1, '江苏', '无锡市', "
				+ "  '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司',"
				+ " 'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL', "
				+ " 0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1') ");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13739262614','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'NOT_MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168," + "19216812, null, '上海德邦物流有限公司', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '0', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan4() throws SQLException {

		List<String> sqlList = new ArrayList<String>();

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,FSTATUS) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,FSTATUS) "
				+ "values(19216813,'wkedkjhsdiu-sdj344','南京市市外高桥开发测试营业部','上海德邦物流有限公司',92974,'DPTEST893423','19216812.92973.92974.86301',1)");
		sqlList.add("    insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY, FCOMPADDR, FBRANDWORTH, FBILLTITLE,    FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ " FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER) "
				+ " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259', "
				+ " 'NORMAL', 'PERSONAL', '0', '1', 19216812, "
				+ "'ITRONIC_FURNITURE', 'NULL', null, 'A', 0,  "
				+ "0, 1, 20000, null, 'HALL' "
				+ " , null, '2', null, "
				+ " 1, '江苏', '无锡市', "
				+ "  '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司',"
				+ " 'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL', "
				+ " 0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1') ");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'NOT_MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168," + "19216813, null, '" + Constant.DEPPON_COMPANY + "', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan3() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,FSTATUS) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,FSTATUS)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,FSTATUS) "
				+ "values(19216813,'wkedkjhsdiu-sdj344','南京市市外高桥开发测试营业部','上海德邦物流有限公司',92974,'DPTEST893423','19216812.92973.92974.86301',1)");
		sqlList.add("    insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY, FCOMPADDR, FBRANDWORTH, FBILLTITLE,    FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ " FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER) "
				+ " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259', "
				+ " 'NORMAL', 'PERSONAL', '0', '1', 19216812, "
				+ "'ITRONIC_FURNITURE', 'NULL', null, 'A', 0,  "
				+ "0, 1, 20000, null, 'HALL' "
				+ " , null, '2', null, "
				+ " 1, '江苏', '无锡市', "
				+ "  '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司',"
				+ " 'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL', "
				+ " 0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1') ");
		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'NOT_MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168," + "19216813, null, '上海德邦物流有限公司', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void clear_data() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract where fcustid in (11,192168)");
		sqlList.add("delete t_cust_custlinkman where fid=19216801");
		sqlList.add("delete t_cust_custbasedata where FID=192168");
		sqlList.add("delete t_org_department where fid in (19216812,92973,92974,86301,19216813)");
		sqlList.add("delete t_cust_contract where fid=192168001");
		sqlList.add("delete t_cust_contract where fcustid=192168 and fdeptid=19216812");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid=19216812");
		sqlList.add("delete T_CUST_CONTRACTDEPT contractdept where contractdept.fdeptid=19216812");
		sqlList.add("delete T_CUST_PREFERENTIAL preferential where preferential.fcontractid=(select fid from t_cust_contract where fcustid=192168 and fdeptid=19216812)");
		sqlList.add("delete t_cust_contractdept where fid in(192168102,192168103)");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid =192168001");
		SpringTestHelper.executeBatch(sqlList);

	}
}
