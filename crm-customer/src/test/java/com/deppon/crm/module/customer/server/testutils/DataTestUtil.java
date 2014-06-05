package com.deppon.crm.module.customer.server.testutils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.util.SpringTestHelper;

/**
 * @作者：罗典
 * @时间：2012-3-12
 * @描述：测试用工具类
 * */
public class DataTestUtil {

	//初始化审批中的实体数据
	public static void initApprovingWorkflowData() throws Exception {
//		ps = getConn().createStatement();
		System.out.println("初始化数据成功！");
		//
	}
	//清理审批中的实体数据
	public static void cleanApprovingWorkflowData() throws Exception {
		String[] sqls = new String[1];
		int index = 0;
		sqls[index++] = "delete from T_CUST_APPROVINGWORKFLOWDATA where fworkflowid='111'";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理数据成功！");
		//
	}
	//初始化客户标签测试数据
	public static void initCustLabelData() throws SQLException{
		List<String> sqls = new ArrayList<String>();
		sqls.add("INSERT INTO T_CUST_CUSTLABEL("+
			"FID,FCREATETIME,FCREATEUSERID,"+
			"FLASTUPDATETIME,FLASTMODIFYUSERID,"+
			"FLABELID,FCUSTID,FCUSTTYPE,FDEPTID)"+
			"VALUES(" +
			"101,sysdate,86301,sysdate,86301,101,105873,'PC_CUSTOMER',-100)");
		sqls.add("INSERT INTO T_CUST_CUSTLABEL("+
				"FID,FCREATETIME,FCREATEUSERID,"+
				"FLASTUPDATETIME,FLASTMODIFYUSERID,"+
				"FLABELID,FCUSTID,FCUSTTYPE,FDEPTID)"+
				"VALUES(" +
				"103,sysdate,86301,sysdate,86301,101,105872,'PC_CUSTOMER',-100)");
		sqls.add("INSERT INTO T_CUST_CUSTLABEL("+
			"FID,FCREATETIME,FCREATEUSERID,"+
			"FLASTUPDATETIME,FLASTMODIFYUSERID,"+
			"FLABELID,FCUSTID,FCUSTTYPE,FDEPTID)"+
			"VALUES(" +
			"102,sysdate,86301,sysdate,86301,102,105871,'SC_CUSTOMER',-100)");
		sqls.add("insert into T_CUST_LABEL(" +
				"FID,FCREATETIME,FCREATEUSERID," +
				"FLASTUPDATETIME,FLASTMODIFYUSERID," +
				"FLABELNAME,FDEPTID)" +
				"VALUES(" +
				"101，sysdate,86301,sysdate,86301,'IT民工',-100)");
		sqls.add("insert into T_CUST_LABEL(" +
				"FID,FCREATETIME,FCREATEUSERID," +
				"FLASTUPDATETIME,FLASTMODIFYUSERID," +
				"FLABELNAME,FDEPTID)" +
				"VALUES(" +
				"1，sysdate,86301,sysdate,86301,'IT民工',-100)");
		sqls.add("insert into T_CUST_LABEL(" +
				"FID,FCREATETIME,FCREATEUSERID," +
				"FLASTUPDATETIME,FLASTMODIFYUSERID," +
				"FLABELNAME,FDEPTID)" +
				"VALUES(" +
				"-123，sysdate,86301,sysdate,86301,'IT民工',105873)");
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化CustLabel测试数据成功！");
	}
	//清理客户标签测试数据
	public static void cleanCustLabelData() throws SQLException{
		List<String> sqls = new ArrayList<String>();
		sqls.add("delete from T_CUST_CUSTLABEL where fid = 101");
		sqls.add("delete from T_CUST_CUSTLABEL where fid = 102");
		sqls.add("delete from T_CUST_CUSTLABEL where fid = 103");			
		sqls.add("delete from T_CUST_LABEL where fid = 101");
		sqls.add("delete from T_CUST_LABEL where fid = 1");
		sqls.add("delete from T_CUST_LABEL where fid = -123");
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理CustLabel测试数据成功！");
	}
	//清理月发月送新优惠方案测试数据 
	public static void cleanPrefrentialDaoData() throws Exception{
		String[] sqls = new String [2];
		int index = 0;
		sqls[index++] = "delete t_cust_deals where fid in(1,2,3)";
		sqls[index++] = "delete  t_cust_dealsitem  where fdealid in (1,2,3)";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理PrefrentialDao层测试数据成功！！");
	}
	//初始化月发月送新优惠方案测试数据 
	public static void initPrefrentialDaoData() throws Exception{
		String[] sqls = new String[4];
		int index = 0;
		sqls[index++] = "INSERT INTO T_CUST_DEALS(FID,FCREATEUSERID,FCREATETIME,FMODIFYUSERID," +
				"FLASTUPDATETIME,FNUMBER,FBEGINTIME,FENDTIME,FNAME,FSTATUS) VALUES(" +
				"2,86301,sysdate,86301,sysdate,'000002',sysdate,sysdate,'第二套测试方案','1')";
		sqls[index++] = "INSERT INTO T_CUST_DEALSITEM (FID,FCREATEUSERID,FCREATETIME,FMODIFYUSERID," +
				"FLASTUPDATETIME,FDEALID,FDEGREE,FMINMONEY,FMAXMONEY,FRATE,FDESCRIPTION) VALUES(" +
				"2,86301,sysdate,86301,sysdate,2,'1',99,9999,0.98,'第二套测试方案的描述')";
		sqls[index++] = "INSERT INTO T_CUST_DEALS (FID,FCREATEUSERID,FCREATETIME,FMODIFYUSERID," +
				"FLASTUPDATETIME,FNUMBER,FBEGINTIME,FENDTIME,FNAME,FSTATUS,FPREFERTYPE) VALUES(" +
				"3,86301,sysdate,86301,sysdate,'000003',sysdate,sysdate,'第三套测试方案','3','MONTH_SEND')";
		sqls[index++] = "INSERT INTO T_CUST_DEALSITEM (FID,FCREATEUSERID,FCREATETIME,FMODIFYUSERID," +
				"FLASTUPDATETIME,FDEALID,FDEGREE,FMINMONEY,FMAXMONEY,FRATE,FDESCRIPTION) VALUES(" +
				"3,86301,sysdate,86301,sysdate,3,'1',99,9999,0.98,'第三套测试方案的描述')";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化PrefrentialDao层测试数据成功！！");
	}
	// 修改会员模块功能数据
	public static void initAlterMemberData() throws Exception {
		List<String> sqls = new ArrayList<String>();
		// 接送货地址信息
		sqls.add( "delete T_CUST_PREFERENCEADDRESS where fid = '11'");
		sqls.add(  "delete T_CUST_PREFERENCEADDRESS where fid = '12'");
		sqls.add( "delete from t_Cust_Preferenceaddress p where p.fshuttleaddressid='40001966'");
		sqls.add( "delete T_CUST_SHUTTLEADDRESS where fid = '11'");
		sqls.add( "delete T_CUST_SHUTTLEADDRESS where fmemberid='11'");
		// 偏好地址信息
		// 账户信息
		sqls.add( "delete T_CUST_ACCOUNT  where fid = '11' ");
		// 联系人信息
		sqls.add( "delete T_CUST_CUSTLINKMAN where fid = '1314521'");
		sqls.add(" delete t_cust_returnvisit where flinkmanid='11'");
		sqls.add( "delete T_CUST_CUSTLINKMAN where fid = '1314520'");
		sqls.add( "delete T_CUST_CUSTLINKMAN where fcustid = '11'");
		
		// 会员信息
		sqls.add( "delete t_cust_custbasedata where fid = '1314520' ");
		// 审批数据
		sqls.add( "delete T_CUST_APPROVEDATA where fworkflowid ='1'");
		sqls.add("delete T_CRM_TODOWORKFLOW where fid ='11'");
		sqls.add("delete T_CUST_CONTRACT where fid =192168001");
		sqls.add(" delete t_cust_contract where fcustid in (11,192168)");
		sqls.add("delete t_cust_custbasedata where fdeptid= '19216812'");
		sqls.add("delete t_cust_contract where fcustid in (11,192168)");
		sqls.add("delete t_org_department where fid ='19216812'");
		sqls.add("delete t_org_department where fid ='19216812'");
		
		sqls.add("insert into t_org_department(fid,fdeptcode,fdeptname,fcompanyname,fparentid,fstandardcode,fdeptseq,fstatus) " +
					"values(19216812,'wkedkjhsdiu-sdj','上海徐泾镇开发测试营业部','上海德邦物流有限公司',92973,'25465-asfsd-swerw-wew-wew','19216812.92973.92974.86301',1)");
		// 会员信息
		sqls.add( "insert into t_cust_custbasedata(fid,fcustname,fdegree, fcusttype,fisspecial,fistrangoods,fisredeempoints,fcuststatus,FCUSTNUMBER, FAREAID,FDEPTID,fisfinover,fversionnumber)"+
        " values('1314520','客户名称','1','1','1','1','1',0,1,19216812,19216812,0,1)");
		
		// 账户信息
				sqls.add( "insert into T_CUST_ACCOUNT(fid,fstatus,fbelongcustom) values('11',0,'11')");
		
		// 接送货地址信息
				sqls.add( "insert into T_CUST_SHUTTLEADDRESS(Fid,fstatus) values('11',0)");
		// 联系人信息
				sqls.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,fstatus,fcustid,fname,fmobiletel,foffertel,facceptdeptid)"+
				"values('1314520','1',sysdate,'1','1',0,'1314520','联系人姓名233','15214397058','021-12365479','19216812')");
				sqls.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,fstatus,fcustid,fname,fmobiletel,foffertel)"
				+ "values('1314521','2',sysdate,'1','1',0,'314520','联系人姓名33','15314397058','021-12365478')");
		// 偏好地址信息
				sqls.add( "insert into T_CUST_PREFERENCEADDRESS(Fid,fstatus) values('11',0)");
				sqls.add( "insert into T_CUST_PREFERENCEADDRESS(Fid,fstatus,faddresstype,flinkmanid,FISMAINADDRESS) values('12',0,'RECEIVE_GOODS','11','1')");
		// 审批数据
				sqls.add( "insert into T_CUST_APPROVEDATA (FID, FCLASSID, FCLASSNAME, "
				+ "FFIELDNAME, FNEWVALUE, FOLDVALUE, FWORKFLOWID,FHANDLETYPE)values "
				+ "(11, 1, 'Member', 'custName', '罗典', '李学兴', 1,2)");
		// 工作流信息
				sqls.add("insert into T_CRM_TODOWORKFLOW (FID, FCREATEUSERID, FLASTUPDATETIME, "
				+ "FLASTMODIFYUSERID, FCREATETIME, FWORKFLOWID, FWORKFLOWNAME, FAPPLICATIONID,"
				+ " FAPPLICATIONSTATUS, FROLEID, FDEPTID, FTODOFLAG)"
				+ "values (11, null, '', null, sysdate,"
				+ " 11, '12123', 1, '12123', 1, 1, '0')") ;
		
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化数据成功！");
	}

	// 清理修改会员模块功能数据
	public static void clearAlterMemberData() throws Exception {
		String[] sqls = new String[12];
		int index = 0;
		// 接送货地址信息
		sqls[index++] = "delete T_CUST_PREFERENCEADDRESS where fid in(11,12)";
		sqls[index++] = "delete T_CUST_SHUTTLEADDRESS where fid = '11'";
		sqls[index++] = "delete T_CUST_SHUTTLEADDRESS where fmemberid='11'";
		// 偏好地址信息
		// 账户信息
		sqls[index++] = "delete T_CUST_ACCOUNT  where fid = '11' ";
		// 联系人信息
		sqls[index++] = "delete T_CUST_CUSTLINKMAN where fid in(1314520,1314521)";
		
		
		// 会员信息
		sqls[index++] = "delete t_cust_custbasedata where fid = '1314520' ";
		// 审批数据
		sqls[index++] = "delete T_CUST_APPROVEDATA where fworkflowid ='1'";
		sqls[index++] = "delete T_CRM_TODOWORKFLOW where fid ='11'";
		sqls[index++] = "delete T_CUST_CONTRACT where fid =192168001";
		sqls[index++] = "delete t_cust_custbasedata where fid='1314520'";
		sqls[index++] = "delete t_org_department where fid ='19216812'";
		sqls[index++] = "commit";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理数据成功！");
	}

	// 初始化潜客功能数据
	public static void initPotentialCustomerDaoData() throws Exception {
		String[] sqls = new String[2];
		int index = 0;
		// 根据ID失效，条件查询
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,"
//				+ "FBUSINESSSTATE,FDEPTID,FLINKMANPHONE,FLINKMANMOBILE,FCUSTTYPE,FCREATETIME) "
//				+ "values(11,0,'罗典','FIXED_POSITION','12','5555555','13512345678','PC_CUSTOMER',add_months(sysdate,-13)) ";
//		// 失效一年的客户信息，根据ID删除
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,"
//				+ "FBUSINESSSTATE,FCREATETIME,FCUSTTYPE) values(12,0,'罗典','FIXED_POSITION',"
//				+ "add_months(sysdate,-13),'PC_CUSTOMER')";
//		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化DAO层测试数据成功！！");
	}

	// 初始化散客功能数据
	public static void initScatterCustomerDaoData() throws Exception {
		String[] sqls = new String[12];
		int index = 0;
		sqls[index++] = "delete t_cust_account where fid in (11,12)";
//		sqls[index++] = "DELETE T_CUST_POTENTIALSCATTER WHERE FID = 1";
//		sqls[index++] = "DELETE T_CUST_POTENTIALSCATTER WHERE FID IN (11,12,13)";
//		sqls[index++] = "DELETE T_CRM_DISPERSECUSTRADESTA WHERE fid in( '1','2','3','4') ";
		// 根据ID失效，条件查询
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,"
//				+ "FBUSINESSSTATE,FDEPTID,FLINKMANPHONE,FLINKMANMOBILE,FCUSTTYPE) "
//				+ "values(11,0,'罗典','FIXED_POSITION','12','5555555','13512345678','SC_CUSTOMER') ";
		// 失效一年的客户信息，根据ID删除
//		sqls[index++] = "INSERT INTO T_CUST_POTENTIALSCATTER(FID,FISCANCEL,FCUSTNAME,"
//				+ "FBUSINESSSTATE,FCREATETIME,FCUSTTYPE,FLASTUPDATETIME,FISLOCK) values(12,1,'罗典',"
//				+ "'FIXED_POSITION',add_months(sysdate,-13),'SC_CUSTOMER',add_months(sysdate,-13),1)";
//		sqls[index++] = "insert into t_cust_potentialscatter (FERPCUSTID, FID, FCREATETIME," +
//				" FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, FCUSTNAME," +
//				" FMEMBERNO, FMAINLINKMANID, FTRADE, FIDNUMBER, FCOMPANYSIZE," +
//				" FCOMPANYNATURE, FTAXREGISTNO, FCITY, FOFFICE, FCUSTPROPERTY, " +
//				"FLEADEPTID, FARRDEPTID, FBUSINESSSTATE, FCUSTNATURE, FREMARK," +
//				" FRECENTCOOP, FCOOPINTENTION, FVOLUMEPOTENTIAL, FRECENTGOODS," +
//				" FCUSTNEED, FDEVELOPSTATE, FFINALREVITIME, FREVITIMES, FCUSTTYPE," +
//				" FCUSTBASE, FPREHUMAN, FLINKMANNAME, FLINKMANMOBILE, " +
//				"FLINKMANPHONE, FPROGRAMMETIME, FINFOSTATE, FDEPTID, FADDRESS, " +
//				"FISCANCEL, FSCATTERID, FMEMBERID, FSCATTERCUSTTYPE, FISLOCK, " +
//				"FVERSION, FVELOCITYAMOUNT, FSCATTERNUM)" +
//				"values ('', 13, sysdate, 176179," +
//				" sysdate, 18706, '罗典', '', null," +
//				" 'ITRONIC_FURNITURE', '513021198708105395', 'E', 'CO_LTD'," +
//				" '310104132224030', 181, '总经理', 'ENTERPRISE', null, null, ''," +
//				" 'LEAVE_CUSTOMER', '罗典妹子', 'HUAYU', 'HIGH', '600-3000', '80000块'," +
//				" '好好干', '', '', 0, 'SC_CUSTOMER', 'ARRIVE_CUSTOMER', '', '罗典', " +
//				"'13524304327', '021-98909099', '', '', 11469, '湖北省-随州市-嘻嘻嘻好', " +
//				"'0', null, null, 'TEMP_SCATTERCUST', '0', 14, 0, " +
//				"'S20121115-09831849')";
//		sqls[index++] = "insert into t_cust_account (FID, FCREATEUSERID, FCREATEDEPTID, FCREATETIME, " +
//				"FLASTUPDATEUSERID, FLASTUPDATEDEPTID, FLASTUPDATETIME, FBANK, " +
//				"FISDEFAULTACCOUNT, FCOUNTNAME, FBANKPROVINCEID, FBANKAREA, FBANKCITYID, " +
//				"FFINANCELINKMAN, FLINKMANMOBILE, FSUBBANKNAME, FBANKACCOUNT, FRELATION, " +
//				"FBELONGCUSTOM, FSTATUS, FACCOUNTNATURE, FACCOUNTUSE, FLINKMANPHONE, " +
//				"FBANKID, FSUBBANKNAMEID, FFINANCELINKMANID, FBANKPROVINCENAME, " +
//				"FBANKCITYNAME) values (11, 86301, 10316,sysdate," +
//				" 86301, 10316, sysdate, '中国农业银行', '1', " +
//				"'朱美林', 'wwNiEgEuEADgA4V/wKjeATcYh+k=', '', 'wwNiEgEuEADgA4aVwKjeAU1UgE4=', " +
//				"'朱美林', '15099798029', '中国农业银行东莞樟木头新兴支行', '6228480605119184311', '同一人'," +
//				" 13, 0, 'PRIVATE_ACCOUNT', 'SUBSTITUTE_PAYMENT', '', 'SkRflAEcEADgC+qswKgCyQxdQ4c='," +
//				" 'nef8mVH5TxGff9iYQTHZxSgOU9M=', 5441, '广东省', '东莞市')";
//		// 散客统计信息
//		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,fcreatetime,"
//				+ "FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,"
//				+ "FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('1','罗典',ADD_MONTHS(sysdate,-1),"
//				+ "'13512345678','021-12345678','湖北武汉','13','600','6000','6000','2',1234) ";
//		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,fcreatetime,"
//				+ "FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,"
//				+ "FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('2','罗典',sysdate,"
//				+ "'13512345678','021-12345678','湖北武汉','13','600','6000','6000','1',1234)";
//		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,fcreatetime,"
//				+ "FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,"
//				+ "FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('3','罗典',ADD_MONTHS(sysdate,-1),"
//				+ "'13512345678','021-12345678','湖北武汉','13','600','6000','6000','1',1234) ";
//		sqls[index++] = "insert into T_CRM_DISPERSECUSTRADESTA(FID,FLINKMANNAME,fcreatetime,"
//				+ "FMOBILEPHONENUM,FTELEPHONENUMBER,FADDRESS,FDEPTNAME,FTOTALBILLCOUNT,"
//				+ "FTOTALAMOUNT,FTOTALWEIGHT,FLEAORARR,FDEPTID) values ('4','罗典',sysdate,"
//				+ "'13512345678','021-12345678','湖北武汉','13','600','6000','6000','2',1234)";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化DAO层测试数据成功！！");
	}

	public static void initApproveData() throws SQLException, Exception {
		String[] sqls = new String[47];
		int index = 0;
		sqls[index++] = "delete t_cust_approvedata where fid<=40";
		sqls[index++] = "delete t_cust_scatteraccount where fid <=39";
//		sqls[index++] = "delete T_CUST_POTENTIALSCATTER where FLINKMANMOBILE='13524304327'";
//		sqls[index++] = "insert into t_cust_potentialscatter (FERPCUSTID, FID, FCREATETIME," +
//				" FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID, FCUSTNAME," +
//				" FMEMBERNO, FMAINLINKMANID, FTRADE, FIDNUMBER, FCOMPANYSIZE," +
//				" FCOMPANYNATURE, FTAXREGISTNO, FCITY, FOFFICE, FCUSTPROPERTY, " +
//				"FLEADEPTID, FARRDEPTID, FBUSINESSSTATE, FCUSTNATURE, FREMARK," +
//				" FRECENTCOOP, FCOOPINTENTION, FVOLUMEPOTENTIAL, FRECENTGOODS," +
//				" FCUSTNEED, FDEVELOPSTATE, FFINALREVITIME, FREVITIMES, FCUSTTYPE," +
//				" FCUSTBASE, FPREHUMAN, FLINKMANNAME, FLINKMANMOBILE, " +
//				"FLINKMANPHONE, FPROGRAMMETIME, FINFOSTATE, FDEPTID, FADDRESS, " +
//				"FISCANCEL, FSCATTERID, FMEMBERID, FSCATTERCUSTTYPE, FISLOCK, " +
//				"FVERSION, FVELOCITYAMOUNT, FSCATTERNUM)" +
//				"values ('', 15,sysdate, 176179," +
//				" sysdate, 18706, '罗典', '', null," +
//				" 'ITRONIC_FURNITURE', '513021198708105395', 'E', 'CO_LTD'," +
//				" '310104132224030', 181, '总经理', 'ENTERPRISE', null, null, ''," +
//				" 'LEAVE_CUSTOMER', '罗典妹子', 'HUAYU', 'HIGH', '600-3000', '80000块'," +
//				" '好好干', '', '', 0, 'SC_CUSTOMER', 'ARRIVE_CUSTOMER', '', '罗典', " +
//				"'13524304327', '021-98909099', '', '', 11469, '湖北省-随州市-嘻嘻嘻好', " +
//				"'0', null, null, 'TEMP_SCATTERCUST', '1', 14, 1, " +
//				"'S20121115-09831849')";
//	sqls[index++] = "insert into t_cust_scatteraccount  (FID,   FCREATETIME,   FCREATEUSERID,   FLASTUPDATETIME," +
//				" FLASTMODIFYUSERID,   FBANK,   FBANKID,   FSUBBANKNAME,   FSUBBANKID," +
//				"   FISDEFAULTACCOUNT,   FBANKACCOUNT,   FACCOUNTNAME,   FRELATION,   FBANKPROVINCEID," +
//				"   FBANKPROVINCENAME,   FACCOUNTNATURE,   FACCOUNTUSE,   FBANKCITYID,   FBANKCITYNAME," +
//				"   FFINANCELINKMANNAME,   FLINKMANMOBILE,   FLINKMANPHONE,   FSTATUS,   FSCATTERID)" +
//				"values  (12,  sysdate,   null,  sysdate," +
//				"  null,   '中国农业发展银行',   'SezyXAE5EADh0alGwKgCzgxdQ4c=',   '中国农业发展银行分宜县支行'," +
//				"   'x9FjI+GKAvLgQ8CoAgwC8igOU9M=',   '0',   '6228480605119184311',   '朱美林',   '老板',   'wwNiEgEuEADgA4V8wKjeATcYh+k='," +
//				"  '江西省',   'OUT_UNIT_ACCOUNT',   'SEND_MONTH',   'wwNiEgEuEADgA4ZmwKjeAU1UgE4='," +
//				"   '新余市',   '罗典',   '13524304326',   '021-98909089',   '0',   '15')";
//	sqls[index++] = "insert into t_cust_scatteraccount  (FID,   FCREATETIME,   FCREATEUSERID,   FLASTUPDATETIME," +
//				" FLASTMODIFYUSERID,   FBANK,   FBANKID,   FSUBBANKNAME,   FSUBBANKID," +
//				"   FISDEFAULTACCOUNT,   FBANKACCOUNT,   FACCOUNTNAME,   FRELATION,   FBANKPROVINCEID," +
//				"   FBANKPROVINCENAME,   FACCOUNTNATURE,   FACCOUNTUSE,   FBANKCITYID,   FBANKCITYNAME," +
//				"   FFINANCELINKMANNAME,   FLINKMANMOBILE,   FLINKMANPHONE,   FSTATUS,   FSCATTERID)" +
//				"values  (2,  sysdate,   null,  sysdate," +
//				"  null,   '中国农业发展银行',   'SezyXAE5EADh0alGwKgCzgxdQ4c=',   '中国农业发展银行分宜县支行'," +
//				"   'x9FjI+GKAvLgQ8CoAgwC8igOU9M=',   '0',   '2222222',   '李学兴',   '老板',   'wwNiEgEuEADgA4V8wKjeATcYh+k='," +
//				"  '江西省',   'OUT_UNIT_ACCOUNT',   'SEND_MONTH',   'wwNiEgEuEADgA4ZmwKjeAU1UgE4='," +
//				"   '新余市',   '罗典',   '13524304327',   '021-98909099',   '0',   '15')";
//	sqls[index++] = "insert into t_cust_scatteraccount (FID,  FCREATETIME,  FCREATEUSERID,  FLASTUPDATETIME," +
//				"  FLASTMODIFYUSERID,   FBANK,   FBANKID,   FSUBBANKNAME,   FSUBBANKID,   FISDEFAULTACCOUNT," +
//				"  FBANKACCOUNT,   FACCOUNTNAME,   FRELATION,   FBANKPROVINCEID,   FBANKPROVINCENAME," +
//				"   FACCOUNTNATURE,   FACCOUNTUSE,   FBANKCITYID,   FBANKCITYNAME,   FFINANCELINKMANNAME," +
//				"   FLINKMANMOBILE,   FLINKMANPHONE,   FSTATUS,   FSCATTERID)values  (1,  sysdate," +
//				"null,  sysdate,   null,   '中国农业发展银行',   'SezyXAE5EADh0alGwKgCzgxdQ4c=',"+
//				"    '中国农业发展银行横峰县支行',   'x9FjI+A9AvLgQ8CoAgwC8igOU9M=',   '0',   '444444444',   '唐亮'," +
//				"   '经理',   'wwNiEgEuEADgA4V8wKjeATcYh+k=',   '江西省',   'OUT_UNIT_ACCOUNT',   'SEND_MONTH'," +
//				"   'wwNiEgEuEADgA4bHwKjeAU1UgE4=',   '上饶市',   '罗典',   '13524304327',   '021-98909099',   '1',   '15')";
	sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (1, 12, 'ScatterAccount', 'bankAccount', '5555555555555', '6228480605119184311',2, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (2, 12, 'ScatterAccount', 'accountName', '罗典111', '朱美林', 2, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (3, 1, 'ScatterAccount', 'bank', '中国农业发展银行', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (4, 1, 'ScatterAccount', 'bankId', 'SezyXAE5EADh0alGwKgCzgxdQ4c=', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (5, 1, 'ScatterAccount', 'subBankname', '中国农业发展银行横峰县支行', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (6, 1, 'ScatterAccount', 'subBankId', 'x9FjI+A9AvLgQ8CoAgwC8igOU9M=', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (7, 1, 'ScatterAccount', 'isDefaultAccount', '0', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (8, 1, 'ScatterAccount', 'bankAccount', '444444444', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (9, 1, 'ScatterAccount', 'accountName', '唐亮', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (10, 1, 'ScatterAccount', 'relation', '经理', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (11, 1, 'ScatterAccount', 'bankProvinceId', 'wwNiEgEuEADgA4V8wKjeATcYh+k=', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (12, 1, 'ScatterAccount', 'bankProvinceName', '江西省', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, " +
				"FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (13, 1, 'ScatterAccount', 'accountNature', 'OUT_UNIT_ACCOUNT', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (14, 1, 'ScatterAccount', 'accountUse', 'SEND_MONTH', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (15, 1, 'ScatterAccount', 'bankCityId', 'wwNiEgEuEADgA4bHwKjeAU1UgE4=', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
				"values (16, 1, 'ScatterAccount', 'bankCityName', '上饶市', '', 1, '111111111', 108)";
				sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (17, 1, 'ScatterAccount', 'financeLinkmanName', '罗典', '', 1, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (18, 1, 'ScatterAccount', 'linkmanMobile', '13524304327', '', 1, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (19, 1, 'ScatterAccount', 'linkmanPhone', '021-98909099', '', 1, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (20, 1, 'ScatterAccount', 'status', '2', '', 1, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (21, 1, 'ScatterAccount', 'scatterId', '409831849', '', 1, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (22, 2, 'ScatterAccount', 'bank', '', '中国农业发展银行', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (23, 2, 'ScatterAccount', 'bankId', '', 'SezyXAE5EADh0alGwKgCzgxdQ4c=', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (24, 2, 'ScatterAccount', 'subBankname', '', '中国农业发展银行分宜县支行', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (25, 2, 'ScatterAccount', 'subBankId', '', 'x9FjI+GKAvLgQ8CoAgwC8igOU9M=', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (26, 2, 'ScatterAccount', 'isDefaultAccount', '', '0', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (27, 2, 'ScatterAccount', 'bankAccount', '', '2222222', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (28, 2, 'ScatterAccount', 'accountName', '', '李学兴', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (29, 2, 'ScatterAccount', 'relation', '', '老板', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (30, 2, 'ScatterAccount', 'bankProvinceId', '', 'wwNiEgEuEADgA4V8wKjeATcYh+k=', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (40, 2, 'ScatterAccount', 'bankProvinceName', '', '江西省', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (31, 2, 'ScatterAccount', 'accountNature', '', 'OUT_UNIT_ACCOUNT', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (32, 2, 'ScatterAccount', 'accountUse', '', 'SEND_MONTH', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (33, 2, 'ScatterAccount', 'bankCityId', '', 'wwNiEgEuEADgA4ZmwKjeAU1UgE4=', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (34, 2, 'ScatterAccount', 'bankCityName', '', '新余市', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (35, 2, 'ScatterAccount', 'financeLinkmanName', '', '罗典', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (36, 2, 'ScatterAccount', 'linkmanMobile', '', '13524304327', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (37, 2, 'ScatterAccount', 'linkmanPhone', '', '021-98909099', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (38, 2, 'ScatterAccount', 'status', '', '0', 3, '111111111', 108)";
		sqls[index++] = "insert into t_cust_approvedata (FID, FCLASSID, FCLASSNAME, FFIELDNAME, FNEWVALUE, FOLDVALUE, FHANDLETYPE, FWORKFLOWID, FMEMBEROPERATIONLOGID)" +
		"values (39, 2, 'ScatterAccount', 'scatterId', '', '409831849', 3, '111111111', 108)";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化DAO层测试数据成功！！");
	}
	// 清理测试数据
	public static void cleanDaoData() throws Exception {
		String[] sqls = new String[8];
		int index = 0;
		sqls[index++] = "delete t_cust_account where fid in (11,12)";
//		sqls[index++] = "DELETE T_CUST_POTENTIALSCATTER WHERE FID = 1";
//		sqls[index++] = "DELETE T_CUST_POTENTIALSCATTER WHERE FID IN (11,12) ";
//		sqls[index++] = "DELETE T_CUST_POTENTIALSCATTER WHERE Flinkmanmobile in ('13512345678','13524304327') ";
//		sqls[index++] = "DELETE T_CRM_DISPERSECUSTRADESTA WHERE FID IN(1,2,3,4) ";
		sqls[index++] = "delete t_cust_approvedata where fid<40";
//		sqls[index++] = "delete t_cust_scatteraccount where fid in (1,2,11,12)";
//		sqls[index++] = "delete t_cust_potentialscatter where flinkmanmobile='-1'and fdeptid ='1'";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理DAO层测试数据成功！！");
	}
	
	
	//散客管理单元测试的初始化数据
	public static void initScatterCustomerManagerData() throws Exception { 
		String[] sqls = new String[1];
//		int index = 0;
//		sqls[index++] = "insert into t_cust_potentialscatter(fid,fislock) values('409831570','1')";
//		sqls[index++] = "insert into t_cust_potentialscatter(fid,fislock,fiscancel) values('400116084','0','1')";
//		sqls[index++] = "insert into t_cust_potentialscatter(fid,fislock,fiscancel) values('409831560','0','0')";
//		sqls[index++] = "insert into t_cust_potentialscatter(fid,fislock,fiscancel) values('400116182','0','1')";
//		sqls[index++] = "insert into t_cust_potentialscatter(fid,fscattercusttype) values('400116083','TEMP_SCATTERCUST')";
//		sqls[index++] = "insert into t_cust_potentialscatter(fid,fscattercusttype,fiscancel) values('400116095','NORMAL_SCATTERCUST','0')";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("初始化测试数据成功！！");
	}
	// 清理测试数据
	public static void cleanScatterCustomerManagerData() throws Exception {
		String[] sqls = new String[1];
		int index = 0;
		sqls[index++] = "delete from t_cust_potentialscatter where fid in( '409831570','400116084','409831560'" +
				",'400116182','400116083','400116095')";
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理测试数据成功！！");
	}
	
	// 清理测试数据
	public static void cleanWorkflow(String workflowId) throws Exception {
		String[] sqls = new String[3];
		int index = 0;
		sqls[index++] = "delete t_crm_todoworkflow a where a.fworkflowid="+workflowId;
		sqls[index++] = "delete t_cust_to_workflow b where b.workflowid="+workflowId;
		sqls[index++] = "delete t_cust_workflow c where c.fworkflowid="+workflowId;
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理DAO层测试数据成功！！");
	}	// 清理测试数据
	public static void cleanTodo(String deptId) throws Exception {

		String[] sqls = new String[1];
		sqls[0] = "delete  t_crm_todo f where ftasktype='CUSTMER_MESSAGE' and  f.fdeptid="+deptId;
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理DAO层测试数据成功！！");
	}
	public static void cleanTodoWorkflow(String appId) throws Exception {
		String[] sqls = new String[1];
		sqls[0] = "delete t_crm_todoworkflow a where a.fapplicationid="+appId;
		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理DAO层测试数据成功！！");
	}

	//清理联系人dao数据
	public static void cleanContactDaoData() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("delete from t_cust_custlinkman where fnumber='mbl23129536835'");
		list.add("delete from T_USER_CONTACTBIND where fcreateuserid='12344321'");
		list.add("delete from T_CUST_APPROVINGWORKFLOWDATA t where t.fcontactmobile ='113254654624'");
		SpringTestHelper.executeBatch(list);
		System.out.println("清理DAO层测试数据成功！！");
	}
	public static void cleanPCData() throws Exception {
		String[] sqls = new String[1];
		int index = 0;
//		sqls[index++] = "delete  from t_cust_potentialscatter p where p.flinkmanmobile ='13825698745'";
//		SpringTestHelper.executeBatch(sqls);
		System.out.println("清理数据成功！");
		//
	}
}
