package com.deppon.crm.module.frameworkimpl.server.dao.impl;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.deppon.crm.module.frameworkimpl.server.dao.ILogDao;
import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;

/**
 * 日志处理DAO
 * 
 * @author ztjie
 * @modified by WANGWENHU
 */
public class LogDao implements ILogDao {
	private static final String sql_log = "insert into T_LOG_INFO(REQUEST_ID,LOG_TYPE,LOG_INFO,LOG_LEVEL,CREATE_DATE)values(?,?,?,?,?)";
	private static final String sql_reqLog = "insert into T_LOG_REQLOG(FREQUESTID,FCLIENTIP,FCLIENTUSERAGENT,FSERVERIP,FSERVERPORT,FREQUESTURI,FUSER,FAPPNAME,FMODULENAME,FACTION,FREQPARAMETERS,FREQSTARTTIME,FREQENDTIME,FREQDURATION)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// private DriverManagerDataSource dataSourceLog;
	private DataSource dataSourceLog;

	public void setDataSourceLog(DataSource dataSourceLog) {
		this.dataSourceLog = dataSourceLog;
		// System.out.println(dataSourceLog);
		// org.springframework.jndi.JndiObjectFactoryBean jofb;
		// jofb.getObject();
	}

	@Override
	public void insert(Object logInfo) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSourceLog.getConnection();
			ps = con.prepareStatement(sql_log);

			if (logInfo instanceof LogInfo) {
				insertLogInfo(ps, (LogInfo) logInfo, con);
			} else if (logInfo instanceof String) {
				// LogInfo info = new LogInfo();
				// info.setLogInfo((String) logInfo);
				// info.setLogLevel("UNKNOW");
				// info.setLogType("other");
				// info.setCreateDate(new Date());
				// info.setRequestId("---");
				// insertLogInfo(ps, info);
			}
			// System.out.println("write log===>> ["+ sql+"]" +
			// logInfo.toString());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
		}
	}

	@Override
	public void insert(List<?> logInfoList) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSourceLog.getConnection();

			for (Object logInfo : logInfoList) {
				if (logInfo instanceof ReqLogInfo) {
					ps = con.prepareStatement(sql_reqLog);

					insertReqLogInfo(ps, (ReqLogInfo) logInfo);
				} else if (logInfo instanceof LogInfo) {
					ps = con.prepareStatement(sql_log);

					insertLogInfo(ps, (LogInfo) logInfo, con);
				} // else if (logInfo instanceof String) {
					// ps = con.prepareStatement(sql_log);
					//
					// LogInfo info = new LogInfo();
					// info.setLogInfo((String) logInfo);
					// info.setLogLevel("UNKNOW");
					// info.setLogType("other");
					// info.setCreateDate(new Date());
					// info.setRequestId("---");
					// insertLogInfo(ps, info);
					// }
				// System.out.println("write log===>> ["+ sql+"]" +
				// logInfo.toString());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
		}
	}

	/**
	 * @description 请求日志保存.
	 * @author 安小虎
	 * @version 0.1
	 * @date 2012-7-18
	 */
	private void insertReqLogInfo(PreparedStatement ps, ReqLogInfo reqLogInfo)
			throws SQLException {
		ps.setString(1, reqLogInfo.getRequestID());
		ps.setString(2, reqLogInfo.getClientIP());
		ps.setString(3, reqLogInfo.getClientUserAgent());
		ps.setString(4, reqLogInfo.getServerIP());
		ps.setInt(5, reqLogInfo.getServerPort());
		ps.setString(6, reqLogInfo.getRequestURI());
		ps.setString(7, reqLogInfo.getUser());
		ps.setString(8, reqLogInfo.getAppName());
		ps.setString(9, reqLogInfo.getModuleName());
		ps.setString(10, reqLogInfo.getAction());
		ps.setString(11, reqLogInfo.getReqParameters());
		ps.setTimestamp(12, new Timestamp(reqLogInfo.getReqStartTime()));
		ps.setTimestamp(13, new Timestamp(reqLogInfo.getReqEndTime()));
		ps.setLong(14, reqLogInfo.getReqDuration());
		ps.execute();
	}

	private void insertLogInfo(PreparedStatement ps, LogInfo logInfo,
			Connection con) throws Exception {
		ps.setString(1, logInfo.getRequestId());
		ps.setString(2, logInfo.getLogType());
		ps.setString(3, logInfo.getLogInfo());
		ps.setString(4, logInfo.getLogLevel());
		ps.setTimestamp(5, new Timestamp(logInfo.getCreateDate()));
		ps.execute();
	}

	public static Clob createOracleLob(Connection conn, String strValue)
			throws Exception {
		// oracle.sql.CLOB clob = oracle.sql.CLOB.createTemporary(conn, false,
		// oracle.sql.CLOB.DURATION_SESSION);
		// clob.open(oracle.sql.CLOB.MODE_READWRITE);

		oracle.sql.CLOB clob = oracle.sql.CLOB.createTemporary(conn, false,
				oracle.sql.CLOB.DURATION_CALL);
		// Writer writer = null;
		// try {
		// writer = clob.getCharacterOutputStream();
		// writer.write(strValue.toCharArray());
		// writer.flush();
		// } finally {
		// if (writer != null)
		// writer.close();
		// }

		clob.putString(3, strValue);
		return clob;
	}
}