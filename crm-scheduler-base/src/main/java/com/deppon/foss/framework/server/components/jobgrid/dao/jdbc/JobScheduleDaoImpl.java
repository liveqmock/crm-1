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

import com.deppon.foss.framework.server.components.jobgrid.dao.JobScheduleDao;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleCondition;

/**
 * TODO(描述类的职责)
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0</p>
 * @author bulong.xu
 * @date 2012-10-12 下午5:17:37
 * @since
 * @version
 */
public class JobScheduleDaoImpl extends JobDaoBase implements JobScheduleDao {
    private Log LOG = LogFactory.getLog(JobScheduleDaoImpl.class);
    
    /**
     * <p>创建一个新的实例 JobScheduleDaoImpl.TODO</p>
     * @param dataSourceName
     */
    public JobScheduleDaoImpl(String dataSourceName) {
        super(dataSourceName);
    }

    /** 
     * <p>TODO(方法详细描述说明、方法参数的具体涵义)</p> 
     * @param js 
     * @see com.deppon.foss.framework.server.components.jobgrid.dao.JobScheduleDao#insert(com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule)
     */
    @Override
    public void insert(JobSchedule js) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(INSRET_JOB_SCHEDULE);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, js.getTriggerGroup());
            ps.setString(3, js.getTriggerName());
            ps.setString(4, js.getJobGroup());
            ps.setString(5, js.getJobName());
            ps.setString(6, js.getDescription());
            ps.setInt(7, js.getTriggerType());
            ps.setString(8, js.getTriggerExpression());
            ps.setString(9, js.getJobClass());
            ps.setString(10, js.getJobData());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("insert schedule error", e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
    }

    /** 
     * <p>TODO(方法详细描述说明、方法参数的具体涵义)</p> 
     * @param js 
     * @see com.deppon.foss.framework.server.components.jobgrid.dao.JobScheduleDao#update(com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule)
     */
    @Override
    public void update(JobSchedule js) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(UPDATE_SCHEDULE_BY_PK);
            ps.setString(1, js.getTriggerGroup());
            ps.setString(2, js.getTriggerName());
            ps.setString(3, js.getJobGroup());
            ps.setString(4, js.getJobName());
            ps.setString(5, js.getDescription());
            ps.setInt(6, js.getTriggerType());
            ps.setString(7, js.getTriggerExpression());
            ps.setString(8, js.getJobClass());
            ps.setString(9, js.getJobData());
            ps.setString(10, js.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("update schedule error", e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
    }

    /** 
     * <p>TODO(方法详细描述说明、方法参数的具体涵义)</p> 
     * @param cluster
     * @return 
     * @see com.deppon.foss.framework.server.components.jobgrid.dao.JobScheduleDao#queryByCluster(java.lang.String)
     */
    @Override
    public List<JobSchedule> queryAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        List<JobSchedule> list = new ArrayList<JobSchedule>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(QUERY_SCHEDULE_BY_CLUSTER);
            rs = ps.executeQuery();
            while (rs.next()) {
                JobSchedule js = new JobSchedule();
                js.setId(rs.getString(1));
                js.setTriggerGroup(rs.getString(2));
                js.setTriggerName(rs.getString(3));
                js.setJobGroup(rs.getString(4));
                js.setJobName(rs.getString(5));
                js.setDescription(rs.getString(6));
                js.setTriggerType(rs.getInt(7));
                js.setTriggerExpression(rs.getString(8));
                js.setJobClass(rs.getString(9));
                js.setJobData(rs.getString(10));
                list.add(js);
            }
        } catch (SQLException e) {
            LOG.error("queryAll schedule error", e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public void deleteByJob(String jobGroup, String jobName) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(DELETE_SCHEDLE_BY_JOB);
            ps.setString(1, jobGroup);
            ps.setString(2, jobName);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("deleteByJob schedule error", e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
    }
    
    @Override
    public void deleteByTrigger(String triggerGroup, String triggerName) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(DELETE_SCHEDLE_BY_TRIGGER);
            ps.setString(1, triggerGroup);
            ps.setString(2, triggerName);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("deleteByTrigger schedule error", e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
    }

	@Override
	public List<JobSchedule> queryJobSchedule(JobScheduleCondition condition) {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		List<JobSchedule> list = new ArrayList<JobSchedule>();
		try {
			conn = getConnection();
			String sql = genQueryJobSchedule(QUERY_SCHEDULE_BY_CONDITION,
					condition);
			ps = conn.prepareStatement(sql);
			ps = genQueryJobSchedulePs(ps, condition,
					condition.getStart(), condition.getLimit());

			rs = ps.executeQuery();
			while (rs.next()) {
				JobSchedule js = new JobSchedule();
				js.setId(rs.getString(1));
				js.setTriggerGroup(rs.getString(2));
				js.setTriggerName(rs.getString(3));
				js.setJobGroup(rs.getString(4));
				js.setJobName(rs.getString(5));
				js.setDescription(rs.getString(6));
				js.setTriggerType(rs.getInt(7));
				js.setTriggerExpression(rs.getString(8));
				js.setJobClass(rs.getString(9));
				js.setJobData(rs.getString(10));

				list.add(js);
			}
		} catch (SQLException e) {
			LOG.error("queryJobSchedule error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return list;
	}

	public String genQueryJobSchedule(String mainSql, JobScheduleCondition condition) {
		String rownumber = " ROW_NUMBER() OVER(ORDER BY ORDER_COL ) RN ";
		rownumber = rownumber.replace("ORDER_COL", COL_JOB_GROUP + ","
				+ COL_JOB_NAME);//升序排列
		mainSql = mainSql.replace(COL_ROW_NUMBER, rownumber);
		StringBuilder sb = new StringBuilder(" ");
		
		/**
		 * 分实例组查询
		 */
		if (null != condition.getJobInstance() && !"".equals(condition.getJobInstance())) {
			sb.append(CONDITION_AND).append(COL_JOB_GROUP).append(" in (");
			sb.append(" select ").append(COL_SCOPE_NAME).append(" from ").append(TABLE_JOB_PLANNING);
			sb.append(" where ").append(COL_INSTANCE_ID).append(" = ? )");
		}
		
		if (null != condition.getJobName() && !"".equals(condition.getJobName())) {
			sb.append(CONDITION_AND).append(COL_JOB_NAME).append(" =? ");
		}
		if (null != condition.getJobGroup() && !"".equals(condition.getJobGroup())) {
			sb.append(CONDITION_AND).append(COL_JOB_GROUP).append(" =? ");
		}
		if (null != condition.getTriggerName() && !"".equals(condition.getTriggerName())) {
			sb.append(CONDITION_AND).append(COL_TRIGGER_NAME).append(" =? ");
		}
//		anycondition = anycondition + CONDITION_AND + COL_TRIGGER_NAME
//				+ "=? ";
//		
		mainSql = mainSql.replace(ANY_CONDITION, sb.toString());
		return mainSql;
	}

	public PreparedStatement genQueryJobSchedulePs(PreparedStatement ps,
			JobScheduleCondition condition, int start,
			int limit) throws SQLException {
		int i = 0;
		/**
		 * 分实例组查询
		 */
		if (null != condition.getJobInstance() && !"".equals(condition.getJobInstance())) {
			ps.setString(++i, condition.getJobInstance());
		}
		
		if (null != condition.getJobName() && !"".equals(condition.getJobName())) {
			ps.setString(++i, condition.getJobName());
		}
		if (null != condition.getJobGroup() && !"".equals(condition.getJobGroup())) {
			ps.setString(++i, condition.getJobGroup());
		}
		if (null != condition.getTriggerName() && !"".equals(condition.getTriggerName())) {
			ps.setString(++i, condition.getTriggerName());
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
	public JobSchedule queryJobScheduleById(String scheduleId) {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		JobSchedule js = new JobSchedule();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(QUERY_SCHEDULE_BY_ID);
			ps.setString(1, scheduleId);

			rs = ps.executeQuery();
			while (rs.next()) {
				js.setId(rs.getString(1));
				js.setTriggerGroup(rs.getString(2));
				js.setTriggerName(rs.getString(3));
				js.setJobGroup(rs.getString(4));
				js.setJobName(rs.getString(5));
				js.setDescription(rs.getString(6));
				js.setTriggerType(rs.getInt(7));
				js.setTriggerExpression(rs.getString(8));
				js.setJobClass(rs.getString(9));
				js.setJobData(rs.getString(10));

				break;
			}
		} catch (SQLException e) {
			LOG.error("queryJobScheduleById error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return js;
	}

	@Override
	public void deleteJobScheduleById(String scheduleId) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(DELETE_SCHEDLE_BY_ID);
            ps.setString(1, scheduleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("deleteJobScheduleById error", e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
    }

	@Override
	public int queryJobScheduleCount(JobScheduleCondition condition) {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			String sql = genQueryJobSchedule(QUERY_SCHEDULE_COUNT_BY_CONDITION,
					condition);
			ps = conn.prepareStatement(sql);
			ps = genQueryJobSchedulePs(ps, condition, 0, 0);

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
				break;
			}
		} catch (SQLException e) {
			LOG.error("queryJobSchedule error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return count;
	}

	@Override
	public JobSchedule queryJobScheduleByName(String jobName, String jobGroup) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		JobSchedule js = new JobSchedule();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(QUERY_SCHEDULE_BY_NAME);
            ps.setString(1, jobName);
            ps.setString(2, jobGroup);

			rs = ps.executeQuery();
			while (rs.next()) {
				js.setId(rs.getString(1));
				js.setTriggerGroup(rs.getString(2));
				js.setTriggerName(rs.getString(3));
				js.setJobGroup(rs.getString(4));
				js.setJobName(rs.getString(5));
				js.setDescription(rs.getString(6));
				js.setTriggerType(rs.getInt(7));
				js.setTriggerExpression(rs.getString(8));
				js.setJobClass(rs.getString(9));
				js.setJobData(rs.getString(10));

				break;
			}
		} catch (SQLException e) {
			LOG.error("queryJobScheduleByName error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return js;	}

}
