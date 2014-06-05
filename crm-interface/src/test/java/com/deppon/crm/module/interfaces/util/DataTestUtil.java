package com.deppon.crm.module.interfaces.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @作者：罗典
 * @时间：2012-3-12
 * @描述：测试用工具类
 * */
public class DataTestUtil {
	private static Connection conn;
	private static Statement ps;
	private static final String url = "jdbc:oracle:thin:@192.168.17.161:1521:depcrm";
	private static final String driver = "oracle.jdbc.driver.OracleDriver";

	private static Connection getConn() throws Exception {
		if (conn == null) {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "develop", "develop");
		}
		return conn;
	}

	// 修改会员模块功能数据
	public static void initOrderServiceData() throws Exception {
		ps = getConn().createStatement();
		// 账户信息
		ps.addBatch("insert into T_ORD_ORDER (FORDERNUMBER, FCHANNELNUMBER," +
				" FACCEPTDEPTID, FORDERSTATUS, FBEGINACCEPTIME, FWAYBILLNUMBER, " +
				"FRESOURCE, FID,FCONTACTNAME,FCONTACTPROVINCE,FCONTACTCITY,FCONTACTAREA," +
				"FISRECEIVEGOOD,FISSENDMS,FPROVINCE,FCITY,FAREA,FTRANSPORTMODE,FGOODSNAME)" +
				" values ('C11020537', 'C12037', 14721, " +
				"'WAIT_ACCEPT', '', '2322311', 'CALLCENTER', 237,'罗典','北京'," +
				"'朝阳区','天津路',1,1,'北京','朝阳区','天津路','111','叶子')" );
		ps.executeBatch();
		System.out.println("初始化数据成功！");
	}

	// 清理修改会员模块功能数据
	public static void clearOrderServiceData() throws Exception {
		ps = getConn().createStatement();
		// 账户信息
		ps.addBatch("delete T_ORD_ORDER  where fid = 237 ");
		ps.executeBatch();
		System.out.println("清理数据成功！");
	}
}
