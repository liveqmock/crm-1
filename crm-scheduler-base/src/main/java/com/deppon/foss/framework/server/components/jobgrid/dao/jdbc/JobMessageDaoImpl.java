package com.deppon.foss.framework.server.components.jobgrid.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.foss.framework.server.components.jobgrid.dao.JobMessageDao;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;

public class JobMessageDaoImpl extends JobDaoBase implements JobMessageDao {
	private Log LOG = LogFactory.getLog(JobMessageDaoImpl.class);

	public JobMessageDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public void insertJobMessage(JobMessage jm) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSRET_JOB_MESSAGE);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, jm.getEmail());
			ps.setString(3, jm.getMobile());
			ps.setString(4, jm.getSubject());
			ps.setString(5, jm.getContent());
			ps.setInt(6, jm.getSend());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("insert JobMessage error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
	}

	@Override
	public void updateJobMessageSend(String messageId) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_JOB_MESSAGE);
			ps.setInt(1, 1);
			ps.setString(2, messageId);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("updateJobMessageSend error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
	}

	@Override
	public List<JobMessage> queryAllUnsend() {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		List<JobMessage> jml = new ArrayList<JobMessage>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(QUERY_ALL_MESSAGE);
			ps.setInt(1, 0);

			rs = ps.executeQuery();
			while (rs.next()) {
				JobMessage jm = new JobMessage();
				jm.setId(rs.getString(1));
				jm.setEmail(rs.getString(2));
				jm.setMobile(rs.getString(3));
				jm.setSubject(rs.getString(4));
				jm.setContent(rs.getString(5));
				jm.setSend(rs.getInt(6));
				jml.add(jm);
			}

		} catch (SQLException e) {
			LOG.error("queryAllUnsend error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return jml;
	}

}
