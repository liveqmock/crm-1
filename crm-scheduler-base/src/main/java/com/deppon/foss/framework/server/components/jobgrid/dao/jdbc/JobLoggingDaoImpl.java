package com.deppon.foss.framework.server.components.jobgrid.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.foss.framework.server.components.jobgrid.dao.JobLoggingDao;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobLogging;
/**
 * 
 * <p>
 * Description:日志 Dao 层 实现<br />
 * </p>
 * @title JobLoggingDaoImpl.java
 * @package com.deppon.foss.framework.server.components.jobgrid.dao.jdbc 
 * @author 侯斌飞
 * @version 0.1 2013-2-25
 */
public class JobLoggingDaoImpl extends JobDaoBase implements JobLoggingDao {
    private Log LOG = LogFactory.getLog(JobLoggingDaoImpl.class);
    
    public JobLoggingDaoImpl(String dataSourceName) {
        super(dataSourceName);
    }
    
    /**
     * 日志新增
     */
    @Override
    synchronized public void insert(JobLogging log) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(INSERT_JOB_LOGGING);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, log.getInstanceId());
            ps.setString(3, log.getTriggerGroup());
            ps.setString(4, log.getTriggerName());
            ps.setString(5, log.getJobGroup());
            ps.setString(6, log.getJobName());

            ps.setTimestamp(7, new Timestamp(log.getFiredTime().getTime()));
            ps.setString(8, log.getJobAction());
            ps.setString(9, log.getFlowUuid());
            ps.setString(10, log.getErrorMessage());

            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("insert log error", e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
    }
    
    @Override
    public List<JobLogging> queryByJob(String cluster, String jobName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<JobLogging> queryByTrigger(String cluster, String jobName) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public List<JobLogging> queryJobLogging(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate, int start,
			int limit) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		List<JobLogging> list = new ArrayList<JobLogging>();
		try {
			conn = getConnection();
			String sql = genQueryJobLogging(QUERY_LOGGING_BY_CONDITION,
					jobName, jobGroup, triggerName);
			ps = conn.prepareStatement(sql);
			ps = genQueryJobLoggingPs(ps, jobName, jobGroup, triggerName,
					startDate, endDate, start, limit);

			rs = ps.executeQuery();
			while (rs.next()) {
				JobLogging log = new JobLogging();
				log.setId(rs.getString(1));
				log.setInstanceId(rs.getString(2));
				log.setTriggerGroup(rs.getString(3));
				log.setTriggerName(rs.getString(4));
				log.setJobGroup(rs.getString(5));
				log.setJobName(rs.getString(6));
				log.setFiredTime(rs.getTimestamp(7));
				log.setJobAction(rs.getString(8));
				log.setFlowUuid(rs.getString(9));
				log.setErrorMessage(rs.getString(10));

				list.add(log);
			}
		} catch (SQLException e) {
			LOG.error("queryJobLogging error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return list;
	}

	public String genQueryJobLogging(String mainSql, String jobName,
			String jobGroup, String triggerName) {
		String rownumber = " ROW_NUMBER() OVER(ORDER BY ORDER_COL ) RN ";
		rownumber = rownumber.replace("ORDER_COL", COL_FIRED_TIME + " DESC");
		mainSql = mainSql.replace(COL_ROW_NUMBER, rownumber);

		String anycondition = " ";
		if (null != jobName && !"".equals(jobName)) {
			anycondition = anycondition + CONDITION_AND + COL_JOB_NAME + " =? ";
		}
		if (null != jobGroup && !"".equals(jobGroup)) {
			anycondition = anycondition + CONDITION_AND + COL_JOB_GROUP
					+ " =? ";
		}
		if (null != triggerName && !"".equals(triggerName)) {
			anycondition = anycondition + CONDITION_AND + COL_TRIGGER_NAME
					+ "=? ";
		}
		mainSql = mainSql.replace(ANY_CONDITION, anycondition);
		return mainSql;
	}

	public PreparedStatement genQueryJobLoggingPs(PreparedStatement ps,
			String jobName, String jobGroup, String triggerName,
			Date startDate, Date endDate, int start, int limit)
			throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
		ps.setString(++i, df.format(startDate));
		ps.setString(++i, df.format(endDate));
		if (null != jobName && !"".equals(jobName)) {
			ps.setString(++i, jobName);
		}
		if (null != jobGroup && !"".equals(jobGroup)) {
			ps.setString(++i, jobGroup);
		}
		if (null != triggerName && !"".equals(triggerName)) {
			ps.setString(++i, triggerName);
		}
		if (start == 0) {
			start = 1;
		}
		if (start >= 0 && limit > 0) {
			ps.setInt(++i, start);
			ps.setInt(++i, start + limit - 1);
		}
		return ps;
	}

	@Override
	public JobLogging queryJobLoggingById(String logId) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		JobLogging jobLogging = new JobLogging();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(QUERY_LOGGING_BY_ID);
			ps.setString(1, logId);

			rs = ps.executeQuery();
			while (rs.next()) {
				jobLogging.setId(rs.getString(1));
				jobLogging.setInstanceId(rs.getString(2));
				jobLogging.setTriggerName(rs.getString(3));
				jobLogging.setTriggerGroup(rs.getString(4));
				jobLogging.setJobGroup(rs.getString(5));
				jobLogging.setJobName(rs.getString(6));
				jobLogging.setFiredTime(rs.getTimestamp(7));
				jobLogging.setJobAction(rs.getString(8));
				jobLogging.setFlowUuid(rs.getString(9));
				jobLogging.setErrorMessage(rs.getString(10));
				break;
			}
		} catch (SQLException e) {
			LOG.error("queryJobLoggingById error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return jobLogging;
	}

	@Override
	public int queryJobLoggingCount(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			String sql = genQueryJobLogging(QUERY_LOGGING_COUNT_BY_CONDITION,
					jobName, jobGroup, triggerName);
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ps = genQueryJobLoggingPs(ps, jobName, jobGroup, triggerName,
					startDate, endDate, 0, 0);

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
				break;
			}
		} catch (SQLException e) {
			LOG.error("queryJobLoggingCount error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return count;
	}

	@Override
	public int queryVetoedCountByJobName(String jobGroup, String jobName,int minute) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(QUERY_VETOED_LOGGING_COUNT_BY_NAME);
			ps.setString(1, jobGroup);
			ps.setString(2, jobName);
			ps.setString(3, "VETOED");
			Date end = new Date();
			Date start = new Date(end.getTime() - minute * 60 * 1000);
			ps.setString(4, df.format(start));
			ps.setString(5, df.format(end));

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
				break;
			}
		} catch (SQLException e) {
			LOG.error("queryVetoedCountByJobName error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return count;
	}

}
