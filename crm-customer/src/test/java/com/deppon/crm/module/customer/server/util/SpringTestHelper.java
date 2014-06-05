package com.deppon.crm.module.customer.server.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestHelper {

	private static final ApplicationContext appContext ;
	private static final DataSource dataSource;
	
	
    // 加载Spring 配置文件
	static {
		try {
			appContext = new ClassPathXmlApplicationContext(
						"com/deppon/crm/module/customer/server/META-INF/SpringTest.xml",
						"com/deppon/crm/module/keycustomer/server/META-INF/spring.xml",
						"com/deppon/crm/module/custrepeat/server/META-INF/spring.xml"
//						"com/deppon/crm/module/bps/server/META-INF/spring_beansTest.xml"
					);
			    dataSource = (DataSource) appContext.getBean("dataSource");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private SpringTestHelper() {
		
	}
     /**
      * 
      * <p>
      * Description:根据 Class 类 来获取配置Bean <br />
      *     对传入Class 的类名，首字母最小化取spring配置bean
      * </p>
      * @author 王明明
      * @version 0.1 2012-12-17
      * @param clazz
      * @return
      * T
      */
	public static <T> T getBean(Class<T> clazz){
		if(appContext != null){
			String className = clazz.getSimpleName();
			String startName = className.substring(0,1);
			String otherName = className.substring(1);
			
			String beanName =StringUtils.lowerCase(startName) + otherName;
			return (T) appContext.getBean(beanName);
		}
		return null;
	}
	/**
	 * 
	 * <p>
	 * Description:根据spring 配置bean的 ID 来获取配置bean信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-17
	 * @param beanName
	 * @return
	 * Object 返回Bean，获取后请强制转换为需要的类型
	 */
	public static Object getBean(String beanName){
		return appContext.getBean(beanName);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取数据库连接<br />
	 *   主要用于单元测试初始化数据时获取数据库连接时调用
	 *   连接会绑定在本线程里，如果
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-17
	 * @return
	 *  Connection
	 */
	public static Connection getConnection() throws SQLException {
	return  dataSource.getConnection();
	}
	/**
	 * 
	 * <p>
	 * Description:关闭数据库连接对象<br />
	 *    主要用于单元测试初始化数据后释放数据库连接时调用
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-17
	 * @param rs
	 * @param stat
	 * @param conn
	 * void
	 * @throws SQLException 
	 */
	public static void close(ResultSet rs,Statement stat,Connection conn) throws SQLException{
		if(rs!=null){
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat!=null){
			try {
				stat.close();
				stat = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null && !conn.isClosed()){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
/**
 * 
 * <p>
 * Description:执行数据库批量操作<br />
 * </p>
 * @author 王明明
 * @version 0.1 2012-12-18
 * @param sqlList sql语句集合
 * @throws Exception
 * void
 */
	public static void executeBatch(List<String> sqlList) throws SQLException{

		Connection conn = null;
		Statement ps = null;
		try{
			conn = SpringTestHelper.getConnection();;
			conn.setAutoCommit(false);
			ps = conn.createStatement();
			int batchSize = 1;
			for (String sql : sqlList) {
				ps.addBatch(sql);
				batchSize++;
				if(batchSize>=50){
					batchSize = 1;
					ps.executeBatch();
				}
			}
			if(batchSize!=1){
			  ps.executeBatch();
			}
			conn.commit();
		}catch(SQLException se){
			if(conn!=null){
			  conn.rollback();
			}
			throw se;
		}finally{
			SpringTestHelper.close(null,ps,conn);
		}
	
	}
	
	/**
	 * 
	 * <p>
	 * Description:执行数据库批量操作<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-17
	 * @param sqls  sql语句数组
	 * @throws Exception
	 * void
	 */
	public static void executeBatch(String[] sqls) throws Exception{
		Connection conn = null;
		Statement ps = null;
		try{
			conn = SpringTestHelper.getConnection();;
			conn.setAutoCommit(false);
			ps = conn.createStatement();
			for (String sql : sqls) {
				ps.addBatch(sql);
			}
			ps.executeBatch();
			conn.commit();
		}catch(SQLException se){
			if(conn!=null){
			  conn.rollback();
			}
			throw se;
		}finally{
			SpringTestHelper.close(null,ps,conn);
		}
	}
	
	

}
