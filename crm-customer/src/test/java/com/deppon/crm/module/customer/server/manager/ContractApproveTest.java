package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.ContractHandleType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class ContractApproveTest extends TestCase {
private IContractManager contractManager = BeanUtil.contractManager;
	
	public void setUp() throws SQLException {
		clearData();

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
	}
	
	public void tearDown() throws SQLException {
		clearData();
	}
	
	/**
	 * 
	 * <p>
	 * Description:审批的工作流不存在<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * void
	 * @throws SQLException 
	 */
	public void test_contractApprove_scan1() throws SQLException{
		init_scan1();
		try{
		   contractManager.contractApprove("192168001", false, "", new Date());
		    fail("未抛出异常");
		}catch(RuntimeException re){
			assertEquals("workflow Data exception! appdata not exist !", re.getMessage());
		}
		}
	
	/**
	 * 
	 * <p>
	 * Description:审批新签合同 不同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan2() throws SQLException{
		init_scan1();
		boolean result = contractManager.contractApprove("2879432734", false, "oa用户", new Date());
	    assertTrue(result);
	}
	/**
	 * 
	 * <p>
	 * Description:审批新签合同同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan3() throws SQLException{
		init_scan1();
		try {
			
			boolean result = contractManager.contractApprove("2879432734", true, "oa用户", new Date());
			assertTrue(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批绑定不同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan4() throws SQLException{
		init_scan2();
		boolean result = contractManager.contractApprove("2879432734", false, "oa用户", new Date());
		assertTrue(result);
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批绑定同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan5() throws SQLException{
		init_scan2();
		boolean result = contractManager.contractApprove("2879432734", true, "oa用户", new Date());
		assertTrue(result);
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批变更部门不同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan6() throws SQLException{
		init_scan3();
		boolean result = contractManager.contractApprove("2879432734", false, "oa用户", new Date());
		assertTrue(result);
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批变更部门同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan7() throws SQLException{
		init_scan3();
		boolean result = contractManager.contractApprove("2879432734", true, "oa用户", new Date());
		assertTrue(result);
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批作废合同不同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan8() throws SQLException{
		init_scan4();
		boolean result = contractManager.contractApprove("2879432734", false, "oa用户", new Date());
		assertTrue(result);
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批作废同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan9() throws SQLException{
		init_scan4();
		try {
			boolean result = contractManager.contractApprove("2879432734", true, "oa用户", new Date());
			
			assertTrue(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/***
	 * 
	 * <p>
	 * Description:审批合同改签不同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan10() throws SQLException{
		init_scan5();
		boolean result = contractManager.contractApprove("2879432734", false, "oa用户", new Date());
		assertTrue(result);
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批合同改签同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan11() throws SQLException{
		init_scan5();
		try {
			boolean result = contractManager.contractApprove("2879432734", true, "oa用户", new Date());
			assertTrue(result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批合同修改不同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan12() throws SQLException{
		init_scan6();
		boolean result = contractManager.contractApprove("2879432734", false, "oa用户", new Date());
		assertTrue(result);
	}
	
	
	/***
	 * 
	 * <p>
	 * Description:审批合同修改同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan13() throws SQLException{
		init_scan6();
		try {
			boolean result = contractManager.contractApprove("2879432734", true, "oa用户", new Date());
			assertTrue(result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/***
	 * 
	 * <p>
	 * Description:审批错误的数据类型<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_contractApprove_scan14() throws SQLException{
		init_scan7();
		boolean result = contractManager.contractApprove("2879432734", true, "oa用户", new Date());
		assertTrue(result);
	}
	private void init_scan7() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete T_ORG_DEPARTMENT where fid in (19216812,92973,92974,86301)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

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
		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,"
				+ "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0',"
				+ "0,192168,'联系人姓名','13825698548','021-12365478')");

		
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");

		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234965,sysdate,83601,192168001,'cuowu',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	private void init_scan6() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

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
		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,"
				+ "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0',"
				+ "0,192168,'联系人姓名','13825698548','021-12365478')");

		
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");
		
		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168002, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, '201303051483', "
				+ "'201303051484', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");

		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234965,sysdate,83601,192168001,'"+ContractHandleType.UPDATE.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234966,sysdate,83601,192168002,'"+ContractHandleType.UPDATE.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		
		
		  sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime,"+
                  "fendtime,fworkflowid,fisdept,fstate)"+
              "values(192168103,86301,192168001,19216812,sysdate-40,"+
              "sysdate+40,'329474859439534',1,1)");
		  
		  
		  
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	
	
	private void init_scan5() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

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
		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,"
				+ "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0',"
				+ "0,192168,'联系人姓名','13825698548','021-12365478')");

		
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");
		
		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168002, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, '201303051483', "
				+ "'201303051484', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");

		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234965,sysdate,83601,192168001,'"+ContractHandleType.CHANGESIGN.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234966,sysdate,83601,192168002,'"+ContractHandleType.CHANGESIGN.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92975,'系统管理员', 3)");
		
		
		
		  sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime,"+
                  "fendtime,fworkflowid,fisdept,fstate)"+
              "values(192168103,86304,192168001,19216812,sysdate-40,"+
              "sysdate+40,'329474859439534',1,1)");
		  
		  
		  
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	
	private void init_scan4() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

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

		
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");

		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234965,sysdate,83601,192168001,'"+ContractHandleType.OBSOLETE.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		  sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime,"+
                  "fendtime,fworkflowid,fisdept,fstate)"+
              "values(192168103,86301,192168001,19216812,sysdate-40,"+
              "sysdate+40,'329474859439534',1,1)");
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	
	
	private void init_scan3() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

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

	
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");

		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234965,sysdate,83601,192168001,'"+ContractHandleType.CHANGEBELONGDEPT.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		  sqlList.add("insert into t_cust_contractdept(fid,fcreateuserid,fcontractid,fdeptid,fbegintime,"+
                  "fendtime,fworkflowid,fisdept,fstate)"+
              "values(192168103,86301,192168001,19216812,sysdate-40,"+
              "sysdate+40,'329474859439534',1,1)");
		SpringTestHelper.executeBatch(sqlList);
	}
	
	private void init_scan2() throws SQLException {

		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");
		sqlList.add("insert into t_org_department(fid,fdeptcode,fdeptname,fparentid,fstandardcode,fdeptseq) "
				+ "values(19216813,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301')");

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

	
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");

		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234965,sysdate,83601,192168001,'"+ContractHandleType.BINDING.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	private void init_scan1() throws SQLException {

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
		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,"
				+ "fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('19216801','19216834',sysdate,'1','0,1,0,0,0',"
				+ "0,192168,'联系人姓名','13825698548','021-12365478')");

	
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate-1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, 19216801, 'MONTH_SEND',"
				+ " 0, 0, 17, '0', null," 
				+ " 100, 0)");

		sqlList.add("insert into t_Cust_Contractoperatorlog"
				+ "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"
				+ "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"
				+ "Fapprovalstate)"
				+ " values(23895234965,sysdate,83601,192168001,'"+ContractHandleType.INSERT.getHandleType()+"',"
				+ " 2879432734,19216812,19216813,92973,'系统管理员', 3)");
		
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
    private void clearData() throws SQLException{
		
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract where fcustid in (11,192168)");
		sqlList.add("delete t_cust_custlinkman where fid=19216801");
		sqlList.add("delete t_cust_custbasedata where FID=192168");
	    sqlList.add("delete t_org_department where fid in (19216812,92973,92974,86301,19216813)");
		sqlList.add("delete T_CUST_CONTRACTMONTHENDDAY where Fid = 2");
		sqlList.add("delete t_cust_contract where fid in (192168001,192168002)");
		sqlList.add("delete T_CUST_CONTRACTDEBTDAY where fid=800000602");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid =192168001");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid =192168002");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fid =23895234965");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fid =23895234966");
		sqlList.add("delete T_CUST_CONTRACTDEPT where fcontractid=192168001");
		SpringTestHelper.executeBatch(sqlList);
		
	}
}
