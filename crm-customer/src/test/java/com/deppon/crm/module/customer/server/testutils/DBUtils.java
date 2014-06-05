/**
 * @description
 * @author 赵斌
 * @2012-3-24
 * @return
 */
package com.deppon.crm.module.customer.server.testutils;

import com.deppon.crm.module.customer.server.util.SpringTestHelper;

/**
 * @作者：赵斌
 * @功能：潜散客测试工具类
 * @时间：2012-3-24 11:17
 * */
public class DBUtils {

	// 初始化潜客功能数据
	public static void initPotentialCustomer() throws Exception {
//	     String[] sqls = new String[2];
//		 int index = 0;
//		// 根据ID失效，条件查询
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,FBUSINESSSTATE,FDEPTID,FLINKMANPHONE,FLINKMANMOBILE,FCUSTTYPE,FCUSTNATURE) values(1311,0,'赵斌','FIXED_POSITION','12','021-5555555','13917090951','PC_CUSTOMER','LEAVE_CUSTOMER') ";
//		// 失效一年的客户信息，根据ID删除
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,FBUSINESSSTATE,FCREATETIME,FLASTUPDATETIME,FCUSTTYPE,FCUSTNATURE) values(1211,1,'赵斌','FIXED_POSITION',add_months(sysdate,-13),add_months(sysdate,-13),'PC_CUSTOMER','LEAVE_CUSTOMER')";
//		SpringTestHelper.executeBatch(sqls);
//		System.out.println("初始化DAO层测试数据成功！！");
	}
	
	// 初始化散客功能数据
	public static void initScatterCustomer() throws Exception {
		 String[] sqls = new String[6];
		 int index = 0;
		// 根据ID失效，条件查询
//		sqls[index++] = "DELETE T_CUST_POTENTIALSCATTER WHERE FID IN (30,31,32)";
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,FBUSINESSSTATE,FDEPTID,FLINKMANPHONE,FLINKMANMOBILE,FLINKMANNAME,FCUSTTYPE,FCUSTNEED,FCUSTNATURE) values(30,0,'赵斌','FIXED_POSITION','12','021-55555555','13917090951','赵斌','SC_CUSTOMER','LEAVE_CUSTOMER','LEAVE_CUSTOMER')";
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,FBUSINESSSTATE,FDEPTID,FLINKMANPHONE,FLINKMANMOBILE,FLINKMANNAME,FCUSTTYPE,FCUSTNEED,FCUSTNATURE,FSCATTERNUM) values(32,0,'赵斌','FIXED_POSITION','12','021-55555565','13917090952','赵斌','SC_CUSTOMER','LEAVE_CUSTOMER','LEAVE_CUSTOMER','S20121115-09831849')";
//		// 失效一年的客户信息，根据ID删除
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,FBUSINESSSTATE,FCREATETIME,FCUSTTYPE,FLASTUPDATETIME) values(31,1,'赵斌','FIXED_POSITION',add_months(sysdate,-13),'SC_CUSTOMER',add_months(sysdate,-13))";
		// 散客统计信息
		sqls[index++] = "DELETE T_CRM_DISPERSECUSTRADESTA WHERE FID IN (11,22,33,44)";
		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,FCREATETIME,FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('11','赵斌',ADD_MONTHS(sysdate,-1),'13917090951','021-55555555','甘肃天水','13','600','6000','6000','2',1)";
		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,FCREATETIME,FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('22','赵斌',sysdate,'13917090951','021-55555555','甘肃天水','13','600','6000','6000','1',1)";
		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,FCREATETIME,FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('33','赵斌',ADD_MONTHS(sysdate,-1),'13917090951','021-55555555','甘肃天水','13','600','6000','6000','1',1)";
		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,FCREATETIME,FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('44','赵斌',sysdate,'13917090951','021-55555555','甘肃天水','13','600','6000','6000','2',1)";
		sqls[index++] = "commit";
		
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化DAO层测试数据成功！！");
		
	}
	//初始化合同信息
	public static void initContract() throws Exception
	{
		String[] sqls = new String[25];
		int index = 0;
		//先删除id存在的数据
		sqls[index++] = "DELETE t_cust_integ  WHERE fcontactid=1000001  ";
		sqls[index++] = "DELETE t_cust_memberpoint WHERE Fmemberid in ( 1000001,1000002,1000003,1000004)";
		sqls[index++] = "DELETE t_cust_custlinkman   WHERE FCUSTID in( 1000001,1000002,1000003,1000004)";
		sqls[index++] = "  delete from t_cust_custbasedata_extend where fcustid in ( 1000001,1000002,1000003,1000004)";
		sqls[index++] = "DELETE t_cust_custbasedata WHERE FID in ( 1000001,1000002,1000003,1000004)";
		sqls[index++] = "DELETE t_cust_gift WHERE FID = 1000001";
		sqls[index++] = "DELETE t_cust_integrule WHERE FID = 1000001";
		sqls[index++] = "DELETE t_cust_contracttax WHERE FID = 10000001";
		sqls[index++] = "DELETE T_ORG_DEPARTMENT WHERE FID = 100111";
		sqls[index++] = "DELETE t_cust_contract WHERE FID = 31 ";
		sqls[index++] = "DELETE t_cust_contract WHERE fid in ( 1000001,1000002,1000003,1000004,111111111111132)";
//		
		//插入礼品
		sqls[index++] = "insert into t_cust_gift (FID, FCREATETIME, FCREATEUSERID, " +
				"FLASTUPDATETIME, FLASTMODIFYUSERID, FGIFTNAME, FNEEDPOINTS, " +
				"FINVENTNUMBER, FGIFTVALUE, FGIFTDESC, FGIFTNUMBER, FREALVALUE, " +
				"FBEGINTIME, FENDTIME, FDEPARTMENTID, FISSTART)values (1000001, " +
				"sysdate, 86301, '', null, 'df', null, 1, 4, 'dfdf', 'df', 1, '', '', null, '0')";
		//插入会员信息
		
		sqls[index++] = "insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, " +
        "FLASTUPDATETIME, FLASTMODIFYUSERID, FCUSTNAME, FCUSTNUMBER, FSIMPLENAME," +
         "FDEGREE, FCUSTTYPE, FTAXREGNUMBER, FISTRANGOODS, FISREDEEMPOINTS, " +
       " FISIMPORTCUSTOR, FAREAID, FDEPTID,FTRADEID, FCOMPANYPROPERTY, FREMARK," +
        " FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, " +
      "  FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, " +
       " FCUSTSTATUS, FRESPONSIBILLITY, FVERSIONNUMBER " +
       ",FPROVINCE, FCITY, FCOMPADDR, " +
      "  FBRANDWORTH, FBILLTITLE, FCUSTNATURE, FPREFERENCESERVICE," +
       "  FCHANNELSOURCE, FISFOCUSPAY, FFOCUSDEPTID, FREGISTERFUND, FISPARENTCOMPANY," +
       "  FADDRTYPE, FRECOMMENDCUST, FPROVINCEID, FCITYID, FISSPECIAL, FPARENTNUMBER, " +
       " FFOCUSDEPTNAME, FREGISTADDRESS,FISFINOVER) values (1000001, sysdate, 86301, '', null, 1," +
       "  '20120526-PP-W914607', '111', 'DIAMOND', 'PERSONAL', '111', '1', '1', " +
       " '1', 1, 1, 'CLOTH_SPIN', '111', '111', '111', 2, 2, 1, 2, 111, '', 623, '111', " +
       " 111, 1, '111', '111', '111', '111', '111', 'arrive_customer', " +
       " '111', '111', '1', '111', 2, '1', '', '111', 111, 111, '', '', '', '','1')";
		sqls[index++] = "insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, " +
		        "FLASTUPDATETIME, FLASTMODIFYUSERID, FCUSTNAME, FCUSTNUMBER, FSIMPLENAME," +
		         "FDEGREE, FCUSTTYPE, FTAXREGNUMBER, FISTRANGOODS, FISREDEEMPOINTS, " +
		       " FISIMPORTCUSTOR, FAREAID, FDEPTID,FTRADEID, FCOMPANYPROPERTY, FREMARK," +
		        " FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, " +
		      "  FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, " +
		       " FCUSTSTATUS, FRESPONSIBILLITY, FVERSIONNUMBER " +
		       ",FPROVINCE, FCITY, FCOMPADDR, " +
		      "  FBRANDWORTH, FBILLTITLE, FCUSTNATURE, FPREFERENCESERVICE," +
		       "  FCHANNELSOURCE, FISFOCUSPAY, FFOCUSDEPTID, FREGISTERFUND, FISPARENTCOMPANY," +
		       "  FADDRTYPE, FRECOMMENDCUST, FPROVINCEID, FCITYID, FISSPECIAL, FPARENTNUMBER, " +
		       " FFOCUSDEPTNAME, FREGISTADDRESS,FISFINOVER) values (1000002, sysdate, 86301, '', null, 1," +
		       "  '20120526-PP-W914605', '111', 'DIAMOND', 'PERSONAL', '111', '1', '1', " +
		       " '1', 1, 1, 'CLOTH_SPIN', '111', '111', '111', 2, 2, 1, 2, 111, '', 623, '111', " +
		       " 111, 1, '111', '111', '111', '111', '111', 'arrive_customer', " +
		       " '111', '111', '1', '111', 2, '1', '', '111', 111, 111, '', '', '', '','1')";
		sqls[index++] = "insert into t_cust_custbasedata (FID, FCREATETIME, FCREATEUSERID, " +
		        "FLASTUPDATETIME, FLASTMODIFYUSERID, FCUSTNAME, FCUSTNUMBER, FSIMPLENAME," +
		         "FDEGREE, FCUSTTYPE, FTAXREGNUMBER, FISTRANGOODS, FISREDEEMPOINTS, " +
		       " FISIMPORTCUSTOR, FAREAID, FDEPTID,FTRADEID, FCOMPANYPROPERTY, FREMARK," +
		        " FCOMPANYSCOP, FLASTYEARINCOME, FLASTYEARPROFIT, FISACCEPTMARKET, " +
		      "  FPROCREDIT, FPARENTCOMPANYID, FCHANNEL, FCONTACTID, " +
		       " FCUSTSTATUS, FRESPONSIBILLITY, FVERSIONNUMBER " +
		       ",FPROVINCE, FCITY, FCOMPADDR, " +
		      "  FBRANDWORTH, FBILLTITLE, FCUSTNATURE, FPREFERENCESERVICE," +
		       "  FCHANNELSOURCE, FISFOCUSPAY, FFOCUSDEPTID, FREGISTERFUND, FISPARENTCOMPANY," +
		       "  FADDRTYPE, FRECOMMENDCUST, FPROVINCEID, FCITYID, FISSPECIAL, FPARENTNUMBER, " +
		       " FFOCUSDEPTNAME, FREGISTADDRESS,FISFINOVER) values (1000003, sysdate, 86301, '', null, 1," +
		       "  '20120526-PP-W914606', '111', 'DIAMOND', 'PERSONAL', '111', '1', '1', " +
		       " '1', 1, 1, 'CLOTH_SPIN', '111', '111', '111', 2, 2, 1, 2, 111, '', 623, '111', " +
		       " 111, 1, '111', '111', '111', '111', '111', 'arrive_customer', " +
		       " '111', '111', '1', '111', 2, '1', '', '111', 111, 111, '', '', '', '','1')";
		
		//插入联系人
		sqls[index++] = "insert into t_cust_custlinkman (FID, FCREATETIME, FCREATEUSERID,  " +
         "FLASTUPDATETIME, FLASTUPDATEUSERID, FNUMBER, FIDCARD, FNAME, " +
          "FSEX, FBORNDATE, FDUTY, FMOBILETEL, FOFFERTEL, FEMAIL, FWW, FMSN,  " +
        " FQQNUMBER, FACCEPTDEPTID,  " +
        " FDECISIONRIGHT, FGAINAVE, FLINKMANTYPE, FLOVEGIFT, FSTATUS,  " +
        " FDEFAULTID, FVERSIONID, FCUSTID, FLINKMANADDRESS, FPERSONLOVE, " +
        "  FDUTYDEPT, FNATIVEPLACE, FFOLK, FISMAINLINKMAN,  " +
        " FERPLINKMANCREATETIME, FERPLMFID)values (1000001, sysdate, 86301, sysdate, 86301,  " +
         "'sspps13524052214', '135240522141111', '张智13', 'MAN', '', '', '13524052214'," +  
        " '', '', '', '', '', null, '', '', '0,0,1,1,0', '', '1', null, '', 1000001, '', '', '', '', '', '0', '', '') ";
			
		//插入积分规则
		sqls[index++] = "insert into t_cust_integrule (FID, FCREATEUSERID, FCREATETIME, " +
				"FLASTUPDATETIME, FLASTMODIFYUSERID, FNUMBER, FRULENAME, FPOINTTARGET, " +
				"FPOINTTYPE, FTRANSPORTTYPE, FLEADEPTID, FARRDEPTID, FCHANNELTYPE, " +
				"FPOINTVALUE, FPOINTBEGINTIME, FPOINTENDTIME, FPOINTDESC, FINTEGFORM, " +
				"FMEMLEVELREST, FLIMITPERSON, FLIMITFREQUENCY, FLIMITFREUNIT, FLIMITTIMES, " +
				"FLIMITRESTFREQ, FLIMITTIMEUNIT, FPRODUCT, FRULETYPE, FINTEGTYPE, FFRACTION)"+
				"values (1000001, 86301, sysdate, sysdate, 86301, '', '1', '', 'RewardIntegRule', null, '', '', '', " +
				"1, sysdate, sysdate, '1', '', '1', '1', '1', '1', '1', '1', '1', '', '1', '1', 3123123)";
		// 根据ID失效，条件查询
		sqls[index++] = "insert into t_cust_contract(fid,fcontractstatus,fcreateuserid,fpayWay,farrearaMount,flinkManName,flinkManPhone,flinkManMobile,flinkManAddress,fcustId,fdeptId,fregicapital,fcustcompany,fcontractbegindate) " +
						"values(1000001,'1','29988','1','1000000','赵斌','021-55555555','13917090951','上海青浦',1000001,33,'100000000','大华',sysdate-1)";
		sqls[index++] = "insert into t_cust_contract(fid,fcontractstatus,fcreateuserid,fpayWay,farrearaMount,flinkManName,flinkManPhone,flinkManMobile,flinkManAddress,fcustId,fdeptId,fregicapital,fcustcompany,fcontractbegindate) " +
				"values(1000002,'0','29988','1','1000000','赵斌','021-55555555','13917090951','上海青浦',1000002,33,'100000000','大华',sysdate-1)";
		sqls[index++] = "insert into t_cust_contract(fid,fcontractstatus,fcreateuserid,fpayWay,farrearaMount,flinkManName,flinkManPhone,flinkManMobile,flinkManAddress,fcustId,fdeptId,fregicapital,fcustcompany,fcontractbegindate) " +
				"values(1000003,'1','29988','1','1000000','赵斌','021-55555555','13917090951','上海青浦',1000002,33,'100000000','大华',sysdate+1)";
		
//		//插入税务信息
	sqls[index++] = "insert into t_cust_contracttax  (FID,   FCREATETIME,   FCREATEUSERID,   FLASTUPDATETIME,   FLASTMODIFYUSERID,   FCONTRACTID,   FINVOICETYPE,   SIGNCOMPANY,   FBEGINTIME,   FENDTIME)"+
 "values (10000001,   sysdate,   '29988',   sysdate,  '29988',   '1000001',   'INVOICE_TYPE_01',   '上海德邦物流有限公司',   sysdate,   sysdate + 1)  ";
		
		sqls[index++] = "insert into t_cust_contract(fid,fcontractstatus,fcontractsubject,fcreateuserid,fpayWay,farrearaMount,flinkManName,flinkManPhone,flinkManMobile,flinkManAddress,fcustId,fdeptId,fregicapital,fcustcompany,fcontractbegindate) " +
				"values(111111111111132,'0','德邦物流股份有限公司','29988','1','1000000','赵斌','021-55555555','13917090951','上海青浦',1000003,33,'100000000','大华',sysdate+1)";
		
		sqls[index++] = "insert into T_ORG_DEPARTMENT  (FID , FDEPTCODE , FDEPTNAME , FCOMPANYNAME , FPRINCIPAL,FPHONE , FFAX , FPARENTID , FZIPCODE , FADDRESS , FSTATUS , FVALIDDATE , FINVALIDDATE , FDISPLAYORDER, FDEPTLEVEL , FDEPTDESC , FDEPTSEQ , FCREATEUSERID ,FCREATETIME ,FLASTMODIFYUSERID, FSTANDARDCODE, FLASTUPDATETIME )" +
			"values(100111,'','','XXXX有限公司','','','','','','','1',sysdate-2,sysdate-3,'','','','','',sysdate-5,'','DP123456',sysdate)";
		
		sqls[index++] = "insert into T_CUST_CUSTLINKMAN (FID,FNAME,FOFFERTEL,FMOBILETEL,Fnumber,Flinkmantype,FSTATUS) values(1,'测试联系人','021-12354521','13545621245',123123,'ff',1)";
		sqls[index++] = "commit";
		
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化DAO层测试数据成功！！");
	}
		
	// 清理测试数据
	public static void clean() throws Exception {

		String[] sqls = new String[20];
		int index = 0;
		sqls[index++] = "DELETE t_cust_integ WHERE FREWARDINTEGRALID = 1000001";
		sqls[index++] = "DELETE t_cust_integ  WHERE fgiftid=1000001 ";
		sqls[index++] = "DELETE t_cust_integ  WHERE fcontactid=1000001 ";
		sqls[index++] = "DELETE t_cust_linkmanpoint WHERE FLINKMANID=1000001 ";
		sqls[index++] = "DELETE t_cust_contractnoun WHERE FCONTRACTID=1000001 ";
		sqls[index++] = "DELETE t_cust_contractdept WHERE FDEPTID = '12638' ";
		sqls[index++] = "DELETE t_cust_contractdept WHERE FCREATEUSERID = '29988' ";
		sqls[index++] = "DELETE t_cust_preferential WHERE FINSUREDPRICERATE = '0.8800' ";
		sqls[index++] = "DELETE t_cust_contractoperatorlog WHERE FCREATEUSERID = '29988' ";
		sqls[index++] = "DELETE t_cust_contract WHERE FCREATEUSERID = '29988' ";
		sqls[index++] = "DELETE t_cust_contract WHERE fid in ( 1000001,1000002,1000003,1000004,111111111111132)";
		sqls[index++] = "DELETE t_crm_fileinfo  WHERE FCREATEUSERID = '29988' ";
		sqls[index++] = "DELETE t_cust_custbasedata WHERE FID in ( 1000001,1000002,1000003,1000004)";
		sqls[index++] = "DELETE t_cust_custlinkman WHERE FID = 1000001 ";
		sqls[index++] = "DELETE t_cust_custlinkman WHERE FID = 1000002 ";
		sqls[index++] = "DELETE t_cust_custlinkman WHERE FID = 1000003 ";
		sqls[index++] = "DELETE t_cust_custlinkman WHERE FID = 1000003 ";
		sqls[index++] = "DELETE t_cust_custbasedata WHERE FID = 1000004 ";
		sqls[index++] = "DELETE T_ORG_DEPARTMENT WHERE FSTANDARDCODE = 'DP123456' ";
		sqls[index++] = "DELETE T_CUST_CUSTLINKMAN WHERE FID = 1 ";
		
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理DAO层测试数据成功！！");
		
	}
	
	// 初始化散客功能数据
	public static void deleteContactOnlineNum() throws Exception {
		 String[] sqls = new String[1];
		 int index = 0;
		// 根据ID失效，条件查询
		sqls[index++] = "DELETE t_user_contactbind WHERE FUSERNAMe IN ('patern')";
		
		SpringTestHelper.executeBatch(sqls);
		System.out.println("删除数据成功DAO层测试数据成功！！");
		
	}

}
