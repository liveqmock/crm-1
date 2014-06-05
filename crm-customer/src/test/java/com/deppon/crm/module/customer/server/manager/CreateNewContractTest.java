package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.crm.module.customer.server.data.ContractManagerData;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.mongodb.util.Hash;

/**
 * 
 * <p>
 * Description:测试创建合同<br />
 * </p>
 * 
 * @title CreateNewContractTest.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author 王明明
 * @version 0.1 2013-2-19
 */
public class CreateNewContractTest extends TestCase {

	private IContractManager contractManager = BeanUtil.contractManager;
	private DataDictionaryCacheProvider dataDictionaryCacheProvider = SpringTestHelper.getBean(DataDictionaryCacheProvider.class);
	private Mockery mock = new Mockery();

	@SuppressWarnings("unchecked")
	@Before
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
		Set<String> roleids = new HashSet<String>();
		user.setRoleids(roleids);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		CacheManager.getInstance().shutdown();

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
	}

	@After
	public void tearDown() throws SQLException {

		CacheManager.getInstance().shutdown();
	}

	/**
	 * 
	 * <p>
	 * Description:对应会员在数据库中不存在，报出ContractExceptionType.Data_Error 异常<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-2-19 void
	 */
	public void test_createNewContract_scan_1() {

		Contract contract = new Contract();
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);

		try {
			contractManager.createNewContract(contract, null, Constant.CONTRACT_TYPE_LINGDAN);
			fail("未抛出预设异常");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.Data_Error.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:会员所属部门不是当前登录用户的部门，报出ContractExceptionType.Not_Member_Dept 异常<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-2-19 void
	 * @throws SQLException
	 */
	public void test_createNewContract_scan_2() throws SQLException {

		init_scan();
		Contract contract = new Contract();
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setContractBeginDate(new Date());
		contract.setContractendDate(new Date());

		// UserContext.setCurrentUser(null);
		try {
			contractManager.createNewContract(contract, null, Constant.CONTRACT_TYPE_LINGDAN);
			fail("未抛出预设异常");
		} catch (ContractException ce) {
			// assertEquals(ContractExceptionType.Not_Member_Dept.getErrCode(),
			// ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:之前不存在合同信息，新增合同<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-2-19 void
	 * @throws SQLException
	 * @throws ParseException
	 */

	public void test_createNewContract_scan_3() throws SQLException, ParseException {
		init_scan();

		Contract contract = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);

		contract.setDept(ContextUtil.getCurrentUserDept());
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setContactId("2234");
		contract.setLinkManName("张三");
		contract.setLinkManMobile("1233345566");
		
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		FileInfo fileInfo = new FileInfo();
		fileInfoList.add(fileInfo);
		contract.setFileInfoList(fileInfoList);
		List<ContractTax> contractTaxList = new ArrayList<ContractTax>();
		ContractTax c = new ContractTax();
		c.setInvoiceType("INVOICE_TYPE_01");
		c.setSignCompany("陈爱春是畜生！");
		contractTaxList.add(c);
		contract.setContractTaxList(contractTaxList);
		//TODO
		try {
//			String flowId = contractManager.createNewContract(contract, fileInfoList, Constant.CONTRACT_TYPE_LINGDAN).get("workflowId");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * <p>
	 * Description:之前不存在合同信息，新增合同,带优惠信息的<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-2-19 void
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void test_createNewContract_scan_4() throws SQLException, ParseException {

		init_scan();

		Contract contract = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);
		Preferential preferential = ContractManagerData.createPreferential();
		preferential.setChargeRebate(0.8);
		contract.setPreferential(preferential);
		contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		contract.setDept(ContextUtil.getCurrentUserDept());
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setMember(member);
		contract.setContactId("2234");
		contract.setLinkManName("张三");
		contract.setLinkManMobile("1233345566");
		FileInfo fileInfo = new FileInfo();

		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		contract.setFileInfoList(fileInfoList);
		List<ContractTax> contractTaxList = new ArrayList<ContractTax>();
		ContractTax c = new ContractTax();
		c.setInvoiceType("1");
		c.setSignCompany("陈爱春是畜生！");
		contractTaxList.add(c);
		contract.setContractTaxList(contractTaxList);

		String flowId = contractManager.createNewContract(contract, fileInfoList, Constant.CONTRACT_TYPE_LINGDAN).get("workflowId");

		assertNull(flowId);

	}

	/**
	 * 
	 * <p>
	 * Description:之前存在合同信息，新增合同<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-2-19 void
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void test_createNewContract_scan_5() throws SQLException, ParseException {

		init_scan_5();

		Contract contract = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);

		contract.setDept(ContextUtil.getCurrentUserDept());
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setId("192168001");
		contract.setMember(member);
		contract.setContactId("2234");
		contract.setLinkManName("张三");
		contract.setLinkManMobile("1233345566");

		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		List<ContractTax> contractTaxList = new ArrayList<ContractTax>();
		ContractTax c = new ContractTax();
		c.setInvoiceType("INVOICE_TYPE_01");
		c.setSignCompany("陈爱春是畜生！");
		contractTaxList.add(c);
		contract.setContractTaxList(contractTaxList);
		System.out.println("-------------" + contract.getContractendDate());
		try {
			
			String flowId = contractManager.createNewContract(contract, fileInfoList, Constant.CONTRACT_TYPE_LINGDAN).get("workflowId");
		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	/**
	 * 
	 * <p>
	 * Description:之前存在合同信息，新增合同,原合同无效<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-2-19 void
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void test_createNewContract_scan_6() throws SQLException, ParseException {

		init_scan_5();

		Contract contract = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);

		contract.setDept(ContextUtil.getCurrentUserDept());
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setId("192168001");
		contract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		contract.setMember(member);
		contract.setContactId("2234");
		contract.setLinkManName("张三");
		contract.setLinkManMobile("1233345566");
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		List<ContractTax> contractTaxList = new ArrayList<ContractTax>();
		ContractTax c = new ContractTax();
		c.setInvoiceType("INVOICE_TYPE_01");
		c.setSignCompany("陈爱春是畜生！");
		contractTaxList.add(c);
		contract.setContractTaxList(contractTaxList);
		try {
			String flowId = contractManager.createNewContract(contract, fileInfoList, Constant.CONTRACT_TYPE_LINGDAN).get("workflowId");
			
		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	/**
	 * 
	 * <p>
	 * Description:测试得到新签合同信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 */
	public void test_getNewContract_scan1() {
		try {
			contractManager.getNewContract("19216801");
			fail("未抛出预知异常！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.Data_Error.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:测试得到新签合同信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_getNewContract_scan2() throws SQLException {
		init_getnew_scan2();

		// 缓存当前用户数据
		User user = new User();
		Department depart = new Department();
		depart.setId("19216813");
		depart.setDeptName("单元测试事业部");
		depart.setStandardCode("25465-asfsd-swerw-wew-wew");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		user.setRoleids(new HashSet<String>());
		UserContext.setCurrentUser(user);
		try {
			contractManager.getNewContract("192168");
			fail("未抛出预知异常！");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.Not_Member_Dept.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:测试得到新签合同信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_getNewContract_scan3() throws SQLException {
		init_getnew_scan2();
		Contract contract = contractManager.getNewContract("192168");
		assertNotNull(contract);
		assertEquals("192168001", contract.getId());
	}

	/**
	 * 
	 * <p>
	 * Description:测试得到新签合同信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_getNewContract_scan4() throws SQLException {
		init_getnew_scan3();
		Contract contract = contractManager.getNewContract("192168");
		assertNotNull(contract);
		assertNull(contract.getId());
	}

	private void init_getnew_scan3() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,fstatus)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,fstatus)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,fstatus)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301',1)");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216812," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137 ,'1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_getnew_scan2() throws SQLException {

		List<String> sqlList = new ArrayList<String>();

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,fstatus)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,fstatus)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ,fstatus)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301',1)");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216812," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137 ,'1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'NOT_MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+10, '客户要求临欠！', 192168," + "19216812, null, null, null, null, " + "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '1', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan_5() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301')");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216812," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137 ,'1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'NOT_MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, last_day(add_months(trunc(sysdate),3)), '客户要求临欠！', 192168," + "19216812, null, null, null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan() throws SQLException {
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301')");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216812," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137 ,'1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		SpringTestHelper.executeBatch(sqlList);
	}

	private void clear_data() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract where fcustid in (11,192168)");
		sqlList.add("delete t_cust_custlinkman where fid=19216801");
		sqlList.add("delete t_cust_custbasedata where FID=192168");
		sqlList.add("delete t_org_department where fid in (19216812,92973,92974,86301,19216813)");

		sqlList.add("delete t_cust_contract where fcustid=192168 and fdeptid=19216812");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid=192168001");
		sqlList.add("delete T_CUST_CONTRACTDEPT contractdept where contractdept.fdeptid=19216812");
		sqlList.add("delete T_CUST_PREFERENTIAL preferential where preferential.fcontractid=(select fid from t_cust_contract where fcustid=192168 and fdeptid=19216812)");
		sqlList.add("delete T_CUST_CONTRACTTAX t where t.fcontractid=(select fid from t_cust_contract where fcustid=192168 and fdeptid=19216812)");

		SpringTestHelper.executeBatch(sqlList);
	}

}
