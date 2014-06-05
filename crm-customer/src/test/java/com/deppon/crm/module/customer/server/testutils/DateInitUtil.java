package com.deppon.crm.module.customer.server.testutils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.deppon.crm.module.customer.server.util.SpringTestHelper;

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
	
	public static Connection conn = null;
	static {
		try {
			conn = SpringTestHelper.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void executeInitSql() throws Exception{
		ScriptRunner runner = new ScriptRunner(conn);
		Reader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/sql/init.sql"));
		runner.runScript(reader);
//		SpringTestHelper.close(null, null, conn);
	}
	
	public static void executeCleanSql() throws Exception{
		ScriptRunner runner = new ScriptRunner(conn);
		Reader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/sql/clean.sql"));
		runner.runScript(reader);
//		SpringTestHelper.close(null, null, conn);
	}
	
}
