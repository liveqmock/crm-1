package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;

@SuppressWarnings("unchecked")
public class ChangeContractOwnerTest extends TestCase {

	private IContractManager contractManager = SpringTestHelper.getBean(ContractManager.class);
	public void setUp() throws SQLException {
		CacheManager.getInstance().shutdown();
		Mockery mock = new Mockery() ;
		// 获取数据字典放入缓存
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

			}
		});
		CacheManager.getInstance().registerCacheProvider(cache);
		clear_data();

		// 缓存当前用户数据
		User user = new User();
		Department depart = new Department();
		depart.setId("19216813");
		depart.setDeptName("上海徐泾镇开发测试营业部2");
		depart.setStandardCode("25465-asfsd-swerw-wew-wew-1");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);

		Set<String> roleids = new TreeSet<String>();
		roleids.add(Constant.MarketingId);
		user.setRoleids(roleids);
		UserContext.setCurrentUser(user);

	}

	public void tearDown() throws SQLException {
		CacheManager.getInstance().shutdown();
	}

	/**
	 * 
	 * <p>
	 * Description:变更合同所属部门,变更成功，成功生成工作流<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6
	 * @throws SQLException
	 *             void
	 */
	public void test_changeContractOwner_scan1() throws SQLException {
		init_scan();
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {

			String result = contractManager.changeContractOwner("192168001", "19216813", fileInfoList);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * <p>
	 * Description:变更合同所属部门,对应合同不存在<br />
	 * 抛出 ContractExceptionType.ContractIsNull
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_changeContractOwner_scan2() throws SQLException {
		init_scan();
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {
			contractManager.changeContractOwner("192168002", "19216813", fileInfoList);
			fail("未抛出异常");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.ContractIsNull.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:变更合同所属部门,合同存在，对应部门是当前部门<br />
	 * 抛出
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_changeContractOwner_scan3() throws SQLException {
		init_scan();
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {
			contractManager.changeContractOwner("192168001", "19216812", fileInfoList);
			fail("未抛出异常");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.DeptIsBelongDept.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:变更合同所属部门,合同存在，对应部门不是当前部门,固定客户不属于变更部门<br />
	 * 抛出 ContractExceptionType.CUSTOMERINVALIDE
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_changeContractOwner_scan4() throws SQLException {
		init_scan4();
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {
			contractManager.changeContractOwner("192168001", "19216813", fileInfoList);
			fail("未抛出异常");
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.CUSTOMERINVALIDE.getErrCode(), ce.getErrorCode());
		}
	}

	/**
	 * 
	 * <p>
	 * Description:变更合同所属部门,合同存在，对应部门不是当前部门,固定客户不属于变更部门<br />
	 * 抛出 ContractExceptionType.CUSTOMERINVALIDE
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6 void
	 * @throws SQLException
	 */
	public void test_changeContractOwner_scan5() throws SQLException {
		init_scan5();
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {

			String result = contractManager.changeContractOwner("192168001", "19216813", fileInfoList);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * <p>
	 * Description:变更合同所属部门<br />
	 * 会员已变更，部门不同属一个子公司 报错
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6
	 * @throws SQLException
	 *             void
	 */
	public void test_changeContractOwner_scan6() throws SQLException {
		init_scan6();
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {
			contractManager.changeContractOwner("192168001", "19216813", fileInfoList);
		} catch (ContractException ce) {
			// assertEquals(ContractExceptionType.CONTRACTSUBJECTS_IS_DIFFERENTCOMPANIES_FOR_CHANGEDEPT.getErrCode(),
			// ce.getErrorCode());
		}

	}

	/**
	 * 
	 * <p>
	 * Description:变更合同所属部门<br />
	 * 会员已变更，部门不同属一个子公司 报错
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-6
	 * @throws SQLException
	 *             void
	 */
	public void test_changeContractOwner_scan7() throws SQLException {
		init_scan7();
		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		try {

			String result = contractManager.changeContractOwner("192168001", "19216813", fileInfoList);
		} catch (Exception e) {
		}

	}

	private void init_scan() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew-1','19216813.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'25465-asfsd-swerw-wew-wew-2','92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',86301,'25465-asfsd-swerw-wew-wew-3','92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'25465-asfsd-swerw-wew-wew-4','86301',1)");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216813," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137 ,'1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+10, '客户要求临欠！', 192168," + "19216812, null, '上海德邦物流有限公司', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168102,86301,192168001,19216812,sysdate-60," + "sysdate+60,'329474859439534',1,1)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan6() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2','南京德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew-1','19216813.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'25465-asfsd-swerw-wew-wew-2','92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',86301,'25465-asfsd-swerw-wew-wew-3','92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'25465-asfsd-swerw-wew-wew-4','86301',1)");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER,FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216813," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137, '1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+10, '客户要求临欠！', 192168," + "19216812, null, '上海德邦物流有限公司', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168102,86301,192168001,19216812,sysdate-60," + "sysdate+60,'329474859439534',1,1)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan7() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2','南京德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew-1','19216813.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'25465-asfsd-swerw-wew-wew-2','92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',86301,'25465-asfsd-swerw-wew-wew-3','92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'25465-asfsd-swerw-wew-wew-4','86301',1)");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216813," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137, '1', null," + "  1,  '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+10, '客户要求临欠！', 192168," + "19216812, null, '德邦物流股份有限公司', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168102,86301,192168001,19216812,sysdate-60," + "sysdate+60,'329474859439534',1,1)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan4() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2',92973,'25465-asfsd-swerw-wew-wew-1','19216813.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'25465-asfsd-swerw-wew-wew-2','92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',86301,'25465-asfsd-swerw-wew-wew-3','92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'25465-asfsd-swerw-wew-wew-4','86301',1)");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216812," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137, '1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+10, '客户要求临欠！', 192168," + "19216812, null, null, null, null, " + "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '1', null," + " 0, 0)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168102,86301,192168001,19216812,sysdate-60," + "sysdate+60,'329474859439534',1,1)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan5() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");

		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部2','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew-1','19216813.92973.92974.86301',1)");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'25465-asfsd-swerw-wew-wew-2','92973.92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',86301,'25465-asfsd-swerw-wew-wew-3','92974.86301',1)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,fstandardcode,FDEPTSEQ,fstatus)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'25465-asfsd-swerw-wew-wew-4','86301',1)");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "
				+ "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, " + "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "
				+ "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL," + "FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
				+ "FVERSIONNUMBER, FPROVINCE, FCITY," + "FCOMPADDR, FBRANDWORTH, FBILLTITLE," + "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"
				+ "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID,FISFINOVER)" + " values (192168, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"
				+ "  'NORMAL', 'PERSONAL', '0', '1', 19216813," + "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, " + "  0, 1, 20000, null, 'HALL',"
				+ "  400098137, '1', null," + "  1, '江苏', '无锡市'," + "   '宜兴是创业园A3', null, '宜兴迪爱普光电科技有限公司'," + "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"
				+ "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=','1')");

		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype," + "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825690000','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate+1, sysdate+10, '客户要求临欠！', 192168," + "19216812, null, '德邦物流股份有限公司', null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND'," + " 0, 0, 17, '1', null," + " 0, 0)");

		sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime," + "fendtime,fworkflowid,fisdept,fstate)"
				+ "values(192168102,86301,192168001,19216812,sysdate-60," + "sysdate+60,'329474859439534',1,1)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void clear_data() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract where fcustid in (11,192168)");
		sqlList.add("delete T_CUST_CUSTLINKMAN where FID=19216801");
		sqlList.add(" delete T_CUST_CONTRACT where fid =192168001");
		sqlList.add("delete t_cust_custbasedata where FID=192168");
		sqlList.add("delete t_org_department where fid in (19216812,19216813,92973,92974,86301)");
		sqlList.add("delete t_cust_contract where fid=192168001");
		sqlList.add("delete t_cust_contract where fcustid=192168 and fdeptid=19216812");
		sqlList.add("delete T_CUST_CONTRACTDEPT contractdept where contractdept.fdeptid=19216812");
		sqlList.add("delete T_CUST_CONTRACTDEPT contractdept where contractdept.fdeptid=19216813");
		sqlList.add("delete T_CUST_PREFERENTIAL preferential where preferential.fcontractid=(select fid from t_cust_contract where fcustid=192168 and fdeptid=19216812)");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid=192168001");
		SpringTestHelper.executeBatch(sqlList);
	}

}
