package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.customer.server.data.ContractManagerData;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCacheProvider;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.context.UserContext;

public class UpdateContractTest extends TestCase {
	private IContractManager contractManager = SpringTestHelper.getBean(ContractManager.class);
	private DataDictionaryCacheProvider dataDictionaryCacheProvider = SpringTestHelper.getBean(DataDictionaryCacheProvider.class);
	private Mockery mock = new Mockery();

	@Override
	public void setUp() throws SQLException {
		CacheManager.getInstance().shutdown();
		clear_data();
		// 缓存当前用户数据
		User user = new User();
		Department depart = new Department();
		depart.setId("19216812");
		depart.setDeptName("单元测试事业部");
		depart.setStandardCode("25465-asfsd-swerw-wew-wew");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);

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

	@Override
	public void tearDown() throws SQLException {
		CacheManager.getInstance().shutdown();
		clear_data();
	}

	/**
	 * 
	 * <p>
	 * Description:修改合同,合同为月结合同<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-5
	 * @throws SQLException
	 *             void
	 */
	public void test_updateContract_scan1() throws SQLException {
		init_scan();

		Contract contract = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		contract.setDept(ContextUtil.getCurrentUserDept());
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setId("192168001");

		Preferential preferential = ContractManagerData.createPreferential();
		preferential.setChargeRebate(0.8);
		contract.setPreferential(preferential);

		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		fileInfoList.add(fileInfo);
		try {
			contractManager.updateContract(contract, Constant.CONTRACT_TYPE_LINGDAN);
			fail();
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode(), ce.getErrorCode());
		}

	}

	/**
	 * 
	 * <p>
	 * Description:修改合同,合同为非月结合同<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-5
	 * @throws SQLException
	 *             void
	 */
	public void test_updateContract_scan2() throws SQLException {
		clear_data();
		init_scan2();

		Contract contract = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);

		contract.setDept(ContextUtil.getCurrentUserDept());
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setId("192168001");
		contract.setArrearaMount(3000d);
		contract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		contract.setContactId("19216801");

		Preferential preferential = ContractManagerData.createPreferential();
		preferential.setChargeRebate(1d);
		Preferential exPreferential = ContractManagerData.createPreferential();
		preferential.setChargeRebate(1d);
		contract.setPreferential(preferential);
		contract.setExPreferential(exPreferential);

		contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);

		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);
		fileInfoList.add(fileInfo);
		try {
			contractManager.updateContract(contract, Constant.CONTRACT_TYPE_LINGDAN);
			// fail();
		} catch (ContractException ce) {
			// assertEquals(ContractExceptionType.CONTRACT_FILE_IS_INCOMPLETE.getErrCode(),
			// ce.getErrorCode());
		}

		try {
			contract.setFileInfoList(fileInfoList);
			contractManager.updateContract(contract, Constant.CONTRACT_TYPE_LINGDAN);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * <p>
	 * Description:修改合同,合同为月结合同<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2013-3-5
	 * @throws SQLException
	 *             void
	 */
	public void test_updateContract_scan3() throws SQLException {
		init_scan();

		Contract contract = ContractManagerData.createContract(Constant.CONTRACT_PAYWAY_NOT_MONTH);

		contract.setDept(ContextUtil.getCurrentUserDept());
		Member member = new Member();
		member.setId("192168");
		contract.setMember(member);
		contract.setId("192168001");

		Preferential preferential = ContractManagerData.createPreferential();
		preferential.setChargeRebate(0.8);
		contract.setPreferential(preferential);

		FileInfo fileInfo = new FileInfo();
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		fileInfoList.add(fileInfo);

		try {
			contractManager.updateContract(contract, Constant.CONTRACT_TYPE_LINGDAN);
			fail();
		} catch (ContractException ce) {
			assertEquals(ContractExceptionType.ContractWaitEffectCannotOperate.getErrCode(), ce.getErrorCode());
		}

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

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
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
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825698548','021-12365478')");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE," + " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, " + "'NOT_MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+10, '客户要求临欠！', 192168," + "19216812, null, null, null, null, " + "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," + " 0, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void init_scan2() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract  where fid in(192168001)");
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',92974,'92973.92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(92974,'2374342523','开发测试事业部','13869854125',92973,'92974.86301')");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"
				+ "  values(86301,'237434253453','德邦物流','1386984545',0,'86301')");

		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, FCUSTSTATUS, FRESPONSIBILLITY,"
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
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0'," + "0,192168,'联系人姓名','13825698548','021-12365478')");

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

	private void clear_data() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract where fcustid in (11,192168)");
		sqlList.add("delete t_cust_custlinkman where fid=19216801");
		sqlList.add("delete t_cust_contract where fid=192168001");
		sqlList.add("delete t_cust_custbasedata where FID=192168");
		sqlList.add("delete t_org_department where fid in (19216812,92973,92974,86301)");
		sqlList.add("delete t_cust_contract where fcustid=192168 and fdeptid=19216812");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid=19216812");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.foperatordeptid=19216812");
		sqlList.add("delete T_CUST_CONTRACTDEPT contractdept where contractdept.fdeptid=19216812");
		sqlList.add("delete T_CUST_PREFERENTIAL preferential where preferential.fcontractid=(select fid from t_cust_contract where fcustid=192168 and fdeptid=19216812)");
		SpringTestHelper.executeBatch(sqlList);

	}
}
