package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.ContractHandleType;

import junit.framework.TestCase;

public class IsContractCanOperateTest extends TestCase {
	private IContractManager contractManager = BeanUtil.contractManager;

	public void setUp() throws SQLException {
		clearData();
	}

	public void tearDown() throws SQLException {
		clearData();
	}
    /**
     * 
     * <p>
     * Description:合同已生效<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-3-7
     * @throws SQLException
     * void
     */
	public void test_isContractCanOperate_scan1() throws SQLException {
		init_scan1();
		boolean result = contractManager.isContractCanOperate("192168001",
				ContractHandleType.BINDING.getHandleType());
		assertTrue(result);
	}
	/**
	 * 
	 * <p>
	 * Description:合同待生效<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_isContractCanOperate_scan2() throws SQLException {
		init_scan2();
		boolean result = contractManager.isContractCanOperate("192168001",
				ContractHandleType.BINDING.getHandleType());
		assertTrue(result);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:合同已生效<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_isContractCanOperate_scan3() throws SQLException {
		init_scan2();
		boolean result = contractManager.isContractCanOperate("192168001",
				ContractHandleType.INSERT.getHandleType());
		
		assertTrue(result);
	}
	
	/**
	 * 
	 * <p>
	 * Description:合同已生效<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_isContractCanOperate_scan4() throws SQLException {
		init_scan2();
		boolean result = contractManager.isContractCanOperate("192168001",
				ContractHandleType.UPDATE.getHandleType());
		
		assertTrue(result);
	}
	
	/**
	 * 
	 * <p>
	 * Description:合同已生效<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-7
	 * @throws SQLException
	 * void
	 */
	public void test_isContractCanOperate_scan5() throws SQLException {
		init_scan5();
		boolean result = contractManager.isContractCanOperate("192168001",
				ContractHandleType.CHANGESIGN.getHandleType());
		
		assertTrue(result);
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
		sqlList.add(" insert into T_CUST_CONTRACTMONTHENDDAY(Fid,FCREATETIME,FCREATEUSERID,"
				+ "FDEFAULTDEBTDAYS,Fcontractdebtdayname,FSTATUS)"
				+ " values(2,sysdate-1,96301,100,'合同月结天数',1)");

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

		
		sqlList.add("insert into t_cust_contract (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, "
				+ " FPAYWAY, FARREARAMOUNT, FLINKMANNAME, FLINKMANPHONE, FLINKMANMOBILE, "
				+ " FLINKMANADDRESS, FCONTRACTBEGINDATE, FCONTRACTENDDATE, FAPPLICATION, FCUSTID,"
				+ " FDEPTID, FIDDISCOUNT, FCONTRACTSUBJECT, FREGICAPITAL, FBEFORECONTRACTNUM, "
				+ " FCONTRACTNUM, FGOODSNAME, FCUSTCOMPANY, FCONTACTID, FPREFERENTIALTYPE, "
				+ " FRECONDATE, FINVOICDATE, FRESULTDATE, FCONTRACTSTATUS, FCHARGEREBATE,"
				+ " FDEBTDAYS, FUSEABLEARREARAMOUNT)"
				+ " values (192168001, sysdate-1, null, null, null, "
				+ "'MONTH_END', 2000, null, null, null, "
				+ "null, sysdate+1, sysdate+60, '客户要求临欠！', 192168,"
				+ "19216812, null, null, null, null, "
				+ "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '1', null," + "100, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}
	
	
	private void init_scan2() throws SQLException {

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
				+ "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '1', null," + "100, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}
	
	private void init_scan5() throws SQLException {

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
				+ "'201303051483', null, null, null, 'MONTH_SEND',"
				+ " 0, 0, 17, '1', null," + "100, 0)");

		SpringTestHelper.executeBatch(sqlList);

	}

	private void clearData() throws SQLException {
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract where fcustid in (11,192168)");
		sqlList.add("delete t_cust_contractoperatorlog contractlog where contractlog.fcontractid =192168001");
		sqlList.add("delete t_cust_contract where fid=192168001");
		sqlList.add("delete T_CUST_CONTRACTDEBTDAY where fid=800000602");
		sqlList.add("delete t_cust_custlinkman where fid=19216801");
		sqlList.add("delete t_cust_custlinkman where fmobiletel='13825698548'");
		sqlList.add("delete t_cust_custbasedata where FID=192168");
		sqlList.add("delete T_CUST_CONTRACTMONTHENDDAY where Fid = 2");
		sqlList.add("delete t_org_department where fid in (19216812,92973,92974,86301)");
		SpringTestHelper.executeBatch(sqlList);

	}
}
