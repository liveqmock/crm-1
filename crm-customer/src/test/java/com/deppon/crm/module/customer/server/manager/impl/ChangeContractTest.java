package com.deppon.crm.module.customer.server.manager.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;
public class ChangeContractTest extends BeanUtil {
    
	@Before
	public void setUp() throws Exception{
		clearData();
	}
	@After
	public void tearDown(){
		
	}
	
	public void test_scan_1() throws SQLException{
        User user = new User();
		Department depart = new Department();
		depart.setId("92974");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		init_scan1();
		Contract contract = new Contract();
		contract.setId("97700282421");
		contract.setArrearaMount(239847.23d);
		contract.setPayWay("NOT_MONTH_END");
		contract.setPreferentialType("MONTH_SEND");
		contract.setContractendDate(getLastMonthDay(new Date()));
		Member member = new Member();
		member.setId("1000092974");
		contract.setMember(member);
		
		Preferential pf = new Preferential();		
		pf.setContractId(contract.getId());
		pf.setChargeRebate(0.9);
		pf.setAgentgathRate(1d);
		pf.setInsuredPriceRate(0.9);
		pf.setReceivePriceRate(0.9);
		pf.setDeliveryPriceRate(1d);

		contract.setPreferential(pf);
		try{
	     contractManager.changeContract(contract,Constant.CONTRACT_TYPE_LINGDAN);
		}catch(Exception cce){
			
		}

	}
	
	

	private void init_scan1() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		
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
	
	sqlList.add("delete t_cust_contract where fid = 97700282421");
	sqlList.add("delete t_cust_custbasedata where fid = 1000092974");
	sqlList.add("delete t_Cust_Contractoperatorlog where fid=23895234965");
	sqlList.add("delete T_ORG_DEPARTMENT where fid in(92973,92974)");
	SpringTestHelper.executeBatch(sqlList);
	
}

private static Date getLastMonthDay(Date date) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
	calendar.set(Calendar.DAY_OF_MONTH, 0);
	return calendar.getTime();
}


}
