package com.deppon.crm.module.customer.server.manager.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;

import junit.framework.TestCase;

public class RemoveContractTest extends TestCase {
	private IContractManager contractManager = SpringTestHelper.getBean(ContractManager.class);
	
	public void setUp()throws Exception{
		clearData();
	}
	
	public void tearDown()throws Exception{
		
	}
	public void test_removeContract_scan1() throws SQLException{
		Contract contract = new Contract();
		contract.setId("97700282421");
		try{
		contractManager.removeContract(contract, "92974");
		fail();
		}catch(ContractException ce){
			assertEquals(ContractExceptionType.ContractIsNull.getErrCode(), ce.getErrorCode());
		}
	}
	
	
	public void test_removeContract() throws SQLException{
		
//		init_scan1();
//		Contract contract = new Contract();
//		contract.setId("97700282421");
//		try{
//		contractManager.removeContract(contract, "92974");
//		fail();
//		}catch(ContractException ce){
//			assertEquals(ContractExceptionType.DeptIsNotBondingDept.getErrCode(),ce.getErrorCode());
//		}
	}
	
	
	
	

	private void init_scan1() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_contract where fcontractnum ='201106007180'");
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
  " null,400193171, 'MONTH_SEND',0,0,"+
  " 0,'2',null,2000,0)");
		
		
		sqlList.add("insert into t_Cust_Contractoperatorlog"+
     "(fid,Fcreatetime,Fcreateuserid,Fcontractid,Foperatortype,"+
   "Fworkflowid,FPREVIOUSDEPTID,FCHANGEDDEPTID,FOPERATORDEPTID,fapprovalman,"+
   "Fapprovalstate)"+
     " values(23895234965,sysdate,83601,97700282421,'insert',"+
    " 2879432734,23345,34524,567,324838475,"+
    " 3)");
		
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID)"+
              " values(92973,'2374342523','上海徐泾镇开发测试事业部','13869854125',113186)");
		sqlList.add("insert into T_ORG_DEPARTMENT(fid,FDEPTCODE,FDEPTNAME,FPHONE,FPARENTID,FDEPTSEQ)"+
	              "  values(92974,'2374342523','上海徐泾镇开发测试营业部','13869854125',92973,92973)");
			
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
private void  clearData() throws SQLException{
	List<String> sqlList = new ArrayList<String>();
	
	sqlList.add("delete t_cust_contract where fcontractnum ='201106007180'");
	sqlList.add("delete t_cust_contract where fid = 97700282421");
	sqlList.add("delete t_cust_custbasedata where fid = 1000092974");
	sqlList.add("delete t_Cust_Contractoperatorlog where fid=23895234965");
	sqlList.add("delete T_ORG_DEPARTMENT where fid in(92973,92974)");
	SpringTestHelper.executeBatch(sqlList);
	
}

}
