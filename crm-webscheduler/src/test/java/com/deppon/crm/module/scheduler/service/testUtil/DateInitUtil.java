package com.deppon.crm.module.scheduler.service.testUtil;

import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * 
 * <p>
 * Dao执行init.sql脚本的工具类<br />
 * </p>
 * @title DateInitUtil.java
 * @package com.deppon.crm.module.customer.server.testutils 
 * @author bxj
 * @version 0.2 2012-3-31
 */
public class DateInitUtil {
	private static Connection conn;
	private static Statement ps;
//	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:depcrm";
	private static final String url = "jdbc:oracle:thin:@192.168.17.161:1521:depcrm";
	private static final String driver = "oracle.jdbc.driver.OracleDriver";

	private static Connection getConn() throws Exception {
		if (conn == null) {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "develop", "develop");
		}
		return conn;
	}
	
	public static void executeInitSql() throws Exception{
		ScriptRunner runner = new ScriptRunner(getConn());
		Reader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/sql/init.sql"));
		runner.runScript(reader);
	}
	
	public static void executeCleanSql() throws Exception{
		ScriptRunner runner = new ScriptRunner(getConn());
		Reader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/sql/clean.sql"));
		runner.runScript(reader);
	}
	
}
