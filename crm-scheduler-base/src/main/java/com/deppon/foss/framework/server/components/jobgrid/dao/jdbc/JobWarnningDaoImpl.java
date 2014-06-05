package com.deppon.foss.framework.server.components.jobgrid.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.foss.framework.server.components.jobgrid.dao.JobWarnningDao;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;

/**
 * TODO(描述类的职责)
 * 
 * @author bulong.xu
 * @date 2012-10-12 下午5:17:37
 * @since
 * @version
 */
public class JobWarnningDaoImpl extends JobDaoBase implements JobWarnningDao {
	private Log LOG = LogFactory.getLog(JobWarnningDaoImpl.class);

	/**
	 * <p>
	 * 创建一个新的实例 JobScheduleDaoImpl.TODO
	 * </p>
	 * 
	 * @param dataSourceName
	 */
	public JobWarnningDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public void insert(JobWarnning jw) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSRET_JOB_WARNNING);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, jw.getJobGroup());
			ps.setString(3, jw.getJobName());
			ps.setString(4, jw.getWarnType());
			ps.setInt(5, jw.getFailTime());
			ps.setInt(6, jw.getFailCount());
			ps.setString(7, jw.getEmail());
			ps.setString(8, jw.getMobile());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("insert JobWarnning error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
	}

	@Override
	public void deleteJobWarnningByName(String jobName, String jobGroup) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DELETE_WARNNING_BY_NAME);
			ps.setString(1, jobGroup);
			ps.setString(2, jobName);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("deleteJobWarnningByName error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
	}

	@Override
	public JobWarnning queryJobWarnningByName(String jobName, String jobGroup) {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		JobWarnning jw = new JobWarnning();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(QUERY_WARNNING_BY_NAME);
			ps.setString(1, jobGroup);
			ps.setString(2, jobName);

			rs = ps.executeQuery();
			while (rs.next()) {
				jw.setId(rs.getString(1));
				jw.setJobGroup(rs.getString(2));
				jw.setJobName(rs.getString(3));
				jw.setWarnType(rs.getString(4));
				jw.setFailTime(rs.getInt(5));
				jw.setFailCount(rs.getInt(6));
				jw.setEmail(rs.getString(7));
				jw.setMobile(rs.getString(8));

				break;
			}
		} catch (SQLException e) {
			LOG.error("queryJobWarnningByName error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return jw;
	}

}
