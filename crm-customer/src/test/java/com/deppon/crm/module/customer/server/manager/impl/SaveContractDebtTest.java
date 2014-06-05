package com.deppon.crm.module.customer.server.manager.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;

import junit.framework.TestCase;

public class SaveContractDebtTest extends TestCase {
	
	private IContractManager contractManager = SpringTestHelper.getBean(ContractManager.class);
	
	@Override
	public void setUp()throws Exception{
		clearData();
	}
	
	@Override
	public void tearDown() throws Exception{
		clearData();
	}
	
	/**
	 * 
	 * <p>
	 * Description:test空操作，抛出异常 ContractExceptionType.CONTRACT_DATA_ERROR
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-1-28
	 * void
	 */
	public void test_saveContractDebt_scan_1(){
		try{
		contractManager.saveContractDebt(null);
		fail();
		}catch(ContractException ce){
			assertEquals(ContractExceptionType.CONTRACT_DATA_ERROR.getErrCode(), ce.getErrorCode());
		}
		
		try{
			ContractDebtDay contractDebtDay = new ContractDebtDay ();
			contractManager.saveContractDebt(contractDebtDay);
			fail();
			}catch(ContractException ce){
				assertEquals(ContractExceptionType.CONTRACT_DATA_ERROR.getErrCode(), ce.getErrorCode());
			}
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:没有合同数据<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-1-28
	 * void
	 */
	public void test_saveContractDebt_scan_2(){
		
		ContractDebtDay contractDebtDay = new ContractDebtDay ();
		contractDebtDay.setCustNum("9823574956");
		contractDebtDay.setUsedAmount(0.0);
		contractManager.saveContractDebt(contractDebtDay);
	}
	
	
	public void test_saveContractDept_scan_3() throws SQLException{
//		init_scan3();
//		
//		ContractDebtDay contractDebtDay = new ContractDebtDay ();
//		contractDebtDay.setCustNum("20130128-05381259");
//		contractDebtDay.setUsedAmount(0.0);
//		contractManager.saveContractDebt(contractDebtDay);
		
	}
	
	
	public void test_saveContractDept_scan_4() throws SQLException{
//		init_scan4();
//		
//		ContractDebtDay contractDebtDay = new ContractDebtDay ();
//		contractDebtDay.setCustNum("20130128-05381259");
//		contractDebtDay.setUsedAmount(0.0);
//		contractManager.saveContractDebt(contractDebtDay);
		
	}
	
	public void test_saveContractDept_scan_5() throws SQLException{
//		init_scan5();
//		
//		ContractDebtDay contractDebtDay = new ContractDebtDay ();
//		contractDebtDay.setCustNum("20130128-05381259");
//		contractDebtDay.setUsedAmount(5.0);
//		contractDebtDay.setLongestDebtDate(getSysDate(-20));
//		contractManager.saveContractDebt(contractDebtDay);
		
	}
	
	public void test_saveContractDept_scan_6() throws SQLException{
//		init_scan6();
//		
//		ContractDebtDay contractDebtDay = new ContractDebtDay ();
//		contractDebtDay.setCustNum("20130128-05381259");
//		contractDebtDay.setUsedAmount(5.0);
//		contractDebtDay.setLongestDebtDate(getSysDate(60));
//		contractManager.saveContractDebt(contractDebtDay);
		
	}
	
	
	private void init_scan6() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		
		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "+
                     "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, "+
                     "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "+
                     "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL,"+
                     "FCONTACTID, FUPGRADESOURCE, FCUSTSTATUS, FBECOMEMEMTIME, FRESPONSIBILLITY,"+
                     "FVERSIONNUMBER, FNEXTLEVEL, FLASTCHANGTIME, FPROVINCE, FCITY,"+
                     "FCOMPADDR, FCUSTPOTENTIALTYPE, FBRANDWORTH, FBILLTITLE, FCREDITRATE,"+
                     "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"+
                     "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID)"+
 " values (1000092974, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"+
 "  'NORMAL', 'PERSONAL', '0', '1', 10411,"+
 "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, "+
 "  0, 1, 20000, null, 'HALL',"+
 "  400098137, null, '2', sysdate-101, null,"+
 "  1, null, null, '江苏', '无锡市',"+
 "   '宜兴是创业园A3', 'MIDDLE', null, '宜兴迪爱普光电科技有限公司', null,"+
 "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"+
 "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=')");
		
		sqlList.add("insert into t_cust_contract"+
  "(FID,FCREATETIME, FCREATEUSERID,FPAYWAY, FARREARAMOUNT,"+
   "FLINKMANNAME,FLINKMANPHONE,FLINKMANMOBILE,FLINKMANADDRESS,FCONTRACTBEGINDATE,"+
  " FCONTRACTENDDATE, FAPPLICATION,FCUSTID,FDEPTID,FIDDISCOUNT,"+
  " FCONTRACTSUBJECT,FREGICAPITAL,FBEFORECONTRACTNUM,FCONTRACTNUM,FGOODSNAME,"+
  " FCUSTCOMPANY,FCONTACTID,FPREFERENTIALTYPE,FRECONDATE, FINVOICDATE,"+
  " FRESULTDATE,FCONTRACTSTATUS,FCHARGEREBATE,FDEBTDAYS,FUSEABLEARREARAMOUNT)"+
" values"+
  "(97700282421,sysdate,86301,'MONTH_END',30000,"+
  " '赵斌','021-55555555','13917090951','上海',sysdate-100,"+
  " sysdate+100,null,1000092974,92974,'0',"+
  " 32446,null,null,'201106007180',null,"+
  " null,400193171, null,0,0,"+
  " 0,'2',null,2000,0)");
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	private void init_scan5() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		
		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "+
                     "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, "+
                     "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "+
                     "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL,"+
                     "FCONTACTID, FUPGRADESOURCE, FCUSTSTATUS, FBECOMEMEMTIME, FRESPONSIBILLITY,"+
                     "FVERSIONNUMBER, FNEXTLEVEL, FLASTCHANGTIME, FPROVINCE, FCITY,"+
                     "FCOMPADDR, FCUSTPOTENTIALTYPE, FBRANDWORTH, FBILLTITLE, FCREDITRATE,"+
                     "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"+
                     "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID)"+
 " values (1000092974, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"+
 "  'NORMAL', 'PERSONAL', '0', '1', 10411,"+
 "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, "+
 "  0, 1, 20000, null, 'HALL',"+
 "  400098137, null, '2', sysdate-101, null,"+
 "  1, null, null, '江苏', '无锡市',"+
 "   '宜兴是创业园A3', 'MIDDLE', null, '宜兴迪爱普光电科技有限公司', null,"+
 "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"+
 "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=')");
		
		sqlList.add("insert into t_cust_contract"+
  "(FID,FCREATETIME, FCREATEUSERID,FPAYWAY, FARREARAMOUNT,"+
   "FLINKMANNAME,FLINKMANPHONE,FLINKMANMOBILE,FLINKMANADDRESS,FCONTRACTBEGINDATE,"+
  " FCONTRACTENDDATE, FAPPLICATION,FCUSTID,FDEPTID,FIDDISCOUNT,"+
  " FCONTRACTSUBJECT,FREGICAPITAL,FBEFORECONTRACTNUM,FCONTRACTNUM,FGOODSNAME,"+
  " FCUSTCOMPANY,FCONTACTID,FPREFERENTIALTYPE,FRECONDATE, FINVOICDATE,"+
  " FRESULTDATE,FCONTRACTSTATUS,FCHARGEREBATE,FDEBTDAYS,FUSEABLEARREARAMOUNT)"+
" values"+
  "(97700282421,sysdate,86301,'MONTH_END',30000,"+
  " '赵斌','021-55555555','13917090951','上海',sysdate-100,"+
  " sysdate+100,null,1000092974,92974,'0',"+
  " 32446,null,null,'201106007180',null,"+
  " null,400193171, null,0,0,"+
  " 0,'1',null,2000,0)");
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	
	

	private void init_scan4() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		
		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "+
                     "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, "+
                     "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "+
                     "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL,"+
                     "FCONTACTID, FUPGRADESOURCE, FCUSTSTATUS, FBECOMEMEMTIME, FRESPONSIBILLITY,"+
                     "FVERSIONNUMBER, FNEXTLEVEL, FLASTCHANGTIME, FPROVINCE, FCITY,"+
                     "FCOMPADDR, FCUSTPOTENTIALTYPE, FBRANDWORTH, FBILLTITLE, FCREDITRATE,"+
                     "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"+
                     "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID)"+
 " values (1000092974, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"+
 "  'NORMAL', 'PERSONAL', '0', '1', 10411,"+
 "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, "+
 "  0, 1, 20000, null, 'HALL',"+
 "  400098137, null, '2', sysdate-101, null,"+
 "  1, null, null, '江苏', '无锡市',"+
 "   '宜兴是创业园A3', 'MIDDLE', null, '宜兴迪爱普光电科技有限公司', null,"+
 "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"+
 "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=')");
		
		sqlList.add("insert into t_cust_contract"+
  "(FID,FCREATETIME, FCREATEUSERID,FPAYWAY, FARREARAMOUNT,"+
   "FLINKMANNAME,FLINKMANPHONE,FLINKMANMOBILE,FLINKMANADDRESS,FCONTRACTBEGINDATE,"+
  " FCONTRACTENDDATE, FAPPLICATION,FCUSTID,FDEPTID,FIDDISCOUNT,"+
  " FCONTRACTSUBJECT,FREGICAPITAL,FBEFORECONTRACTNUM,FCONTRACTNUM,FGOODSNAME,"+
  " FCUSTCOMPANY,FCONTACTID,FPREFERENTIALTYPE,FRECONDATE, FINVOICDATE,"+
  " FRESULTDATE,FCONTRACTSTATUS,FCHARGEREBATE,FDEBTDAYS,FUSEABLEARREARAMOUNT)"+
" values"+
  "(97700282421,sysdate,86301,'1',30000,"+
  " '赵斌','021-55555555','13917090951','上海',sysdate-100,"+
  " sysdate+100,null,1000092974,92974,'0',"+
  " 32446,null,null,'201106007180',null,"+
  " null,400193171, null,0,0,"+
  " 0,'1',null,2000,0)");
		
		SpringTestHelper.executeBatch(sqlList);
	}

	
	private void init_scan3() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete  t_cust_custbasedata where fid in (1000092974)");
		sqlList.add("delete  t_cust_contract where fid in (97700282421)");
		sqlList.add("insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, FCUSTNAME, FCUSTNUMBER, "+
                     "FDEGREE, FCUSTTYPE, FISREDEEMPOINTS, FISIMPORTCUSTOR, FDEPTID, "+
                     "FTRADEID, FCOMPANYPROPERTY, FREMARK, FCOMPANYSCOP, FLASTYEARINCOME, "+
                     "FLASTYEARPROFIT, FISACCEPTMARKET, FPROCREDIT, FPARENTCOMPANYID, FCHANNEL,"+
                     "FCONTACTID, FUPGRADESOURCE, FCUSTSTATUS, FBECOMEMEMTIME, FRESPONSIBILLITY,"+
                     "FVERSIONNUMBER, FNEXTLEVEL, FLASTCHANGTIME, FPROVINCE, FCITY,"+
                     "FCOMPADDR, FCUSTPOTENTIALTYPE, FBRANDWORTH, FBILLTITLE, FCREDITRATE,"+
                     "FCUSTNATURE, FPREFERENCESERVICE, FCHANNELSOURCE,"+
                     "FREGISTERFUND, FPROVINCEID, FCITYID, FISSPECIAL,FERPID)"+
 " values (1000092974, sysdate, 86301, '宜兴迪爱普', '20130128-05381259',"+
 "  'NORMAL', 'PERSONAL', '0', '1', 10411,"+
 "  'ITRONIC_FURNITURE', 'NULL', null, 'A', 0, "+
 "  0, 1, 20000, null, 'HALL',"+
 "  400098137, null, '2', sysdate-101, null,"+
 "  1, null, null, '江苏', '无锡市',"+
 "   '宜兴是创业园A3', 'MIDDLE', null, '宜兴迪爱普光电科技有限公司', null,"+
 "  'LEAVE_CUSTOMER', 'TRUCK_TRANS', 'HALL',"+
 "  0, 11, 76, '0', 'X7nFWQEsEADgPGHRwKgCzkeF39U=')");
		
		sqlList.add("insert into t_cust_contract"+
  "(FID,FCREATETIME, FCREATEUSERID,FPAYWAY, FARREARAMOUNT,"+
   "FLINKMANNAME,FLINKMANPHONE,FLINKMANMOBILE,FLINKMANADDRESS,FCONTRACTBEGINDATE,"+
  " FCONTRACTENDDATE, FAPPLICATION,FCUSTID,FDEPTID,FIDDISCOUNT,"+
  " FCONTRACTSUBJECT,FREGICAPITAL,FBEFORECONTRACTNUM,FCONTRACTNUM,FGOODSNAME,"+
  " FCUSTCOMPANY,FCONTACTID,FPREFERENTIALTYPE,FRECONDATE, FINVOICDATE,"+
  " FRESULTDATE,FCONTRACTSTATUS,FCHARGEREBATE,FDEBTDAYS,FUSEABLEARREARAMOUNT)"+
" values"+
  "(97700282421,sysdate,86301,'1',30000,"+
  " '赵斌','021-55555555','13917090951','上海',sysdate-100,"+
  " sysdate+100,null,1000092974,92974,'0',"+
  " 32446,null,null,'201106007180',null,"+
  " null,400193171, null,0,0,"+
  " 0,'2',null,2000,0)");
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
private void  clearData() throws SQLException{
	List<String> sqlList = new ArrayList<String>();
	
	sqlList.add("delete t_cust_contract where fid = 97700282421");
	sqlList.add("delete t_cust_custbasedata where fid = 1000092974");
	SpringTestHelper.executeBatch(sqlList);
	
}

 private Date getSysDate(int addDays){
	 
  Calendar calendar = Calendar.getInstance();
  calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+addDays);
  
  return calendar.getTime();
 }

}
