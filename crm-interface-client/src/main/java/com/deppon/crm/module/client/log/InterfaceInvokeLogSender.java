package com.deppon.crm.module.client.log;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.client.log.domain.InterfaceInvokeLog;
import com.deppon.foss.framework.server.components.logger.ILogSender;

public class InterfaceInvokeLogSender implements ILogSender {

	//value中第一个问号应该设置为序列的获取
	private static final String SQL_INSERT = "insert into t_if_log" +
			"(fid,fclassName,fmethod,finvokeTime,fuseTime,fexception," +
			"fexceptionMsg,frequestMsg,fresponseMsg,floggerMsg) " +
			"values(SEQ_T_IF_LOG.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	
	private JdbcTemplate jdbcTemplate;
	
	public InterfaceInvokeLogSender() {
		
	}
	
	public InterfaceInvokeLogSender(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void send(List<Object> msg) {
		if (msg instanceof List) {
			final ArrayList<Object> logs = 
				(ArrayList<Object>) msg;
			
			jdbcTemplate.execute(SQL_INSERT, new CallableStatementCallback() {

				@Override
				public Object doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
						int i =0;
						for (Object invokeLogObj : logs) {
							InterfaceInvokeLog invokeLog = (InterfaceInvokeLog)invokeLogObj;
							cs.setString(1, invokeLog.getClassName());
							cs.setString(2, invokeLog.getMethod());
//							cs.setDate(3, new Date(invokeLog.getInvokeTime()));
							cs.setTimestamp(3, new Timestamp(invokeLog.getInvokeTime().getTime()));
							cs.setLong(4, invokeLog.getUseTime());
							cs.setBoolean(5, invokeLog.isException());
							cs.setString(6, invokeLog.getExceptionMsg());
							cs.setString(7,invokeLog.getRequestMsg());
							cs.setString(8,invokeLog.getResponseMsg());
							cs.setString(9,invokeLog.getLoggerMsg());
							cs.addBatch();
							i++;
							if (i%10==0) {
								cs.executeBatch();
							}
						}
						cs.executeBatch();
						return null;
						
				}
			});
		}
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
