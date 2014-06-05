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

import com.deppon.foss.framework.server.components.jobgrid.dao.JobPlanningDao;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobPlanning;

public class JobPlanningDaoImpl extends JobDaoBase implements JobPlanningDao {
    private Log LOG = LogFactory.getLog(JobPlanningDaoImpl.class);
    
    public JobPlanningDaoImpl(String dataSourceName) {
        super(dataSourceName);
    }

    @Override
    public List<JobPlanning> queryByInstance(Connection conn, String instanceId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JobPlanning> list = new ArrayList<JobPlanning>();
        try {            
            ps = conn.prepareStatement(QUERY_PLANNING_BY_CLUSTER);
            ps.setString(1, instanceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                JobPlanning plan = new JobPlanning();
                plan.setId(rs.getString(1));
                plan.setInstanceId(rs.getString(2));
                plan.setScopeType(rs.getInt(3));
                plan.setScopeName(rs.getString(4));
                plan.setAccessRule(rs.getInt(5));
                list.add(plan);
            }
        } catch (SQLException e) {
            LOG.error("queryByInstance error", e);
        } finally {
            closeStatement(ps);
        }
        return list;
    }

    @Override
    public List<JobPlanning> queryByInstance(String instanceId) {
        Connection conn = null;
        try {
            conn = getConnection();
            return queryByInstance(conn, instanceId);
        } catch (SQLException e) {
            LOG.error("queryByInstance error", e);
        } finally {
            closeConnection(conn);
        }
        return new ArrayList<JobPlanning>();
    }

	@Override
	public List<JobPlanning> queryJobPlanning(String instanceGroup, int start,
			int limit) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		List<JobPlanning> list = new ArrayList<JobPlanning>();
		try {
			conn = getConnection();
			String sql = genQueryJobPlanning(QUERY_PLANNING_BY_CONDITION,
					instanceGroup);
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ps = genQueryJobPlanningPs(ps, instanceGroup, start, limit);

			rs = ps.executeQuery();
			while (rs.next()) {
				JobPlanning plan = new JobPlanning();
				plan.setId(rs.getString(1));
				plan.setInstanceId(rs.getString(2));
				plan.setScopeType(rs.getInt(3));
				plan.setScopeName(rs.getString(4));
				plan.setAccessRule(rs.getInt(5));
				list.add(plan);
			}
		} catch (SQLException e) {
			LOG.error("queryJobPlanning error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return list;
	}

	public String genQueryJobPlanning(String mainSql, String instanceGroup) {
		String rownumber = " ROW_NUMBER() OVER(ORDER BY ORDER_COL ) RN ";
		rownumber = rownumber.replace("ORDER_COL", COL_INSTANCE_ID + " DESC");
		mainSql = mainSql.replace(COL_ROW_NUMBER, rownumber);

		String anycondition = " ";
		if (null != instanceGroup && !"".equals(instanceGroup)) {
			anycondition = anycondition + CONDITION_AND + COL_INSTANCE_ID + " =? ";
		}
		mainSql = mainSql.replace(ANY_CONDITION, anycondition);
		return mainSql;
	}

	public PreparedStatement genQueryJobPlanningPs(PreparedStatement ps,
			String instanceGroup, int start, int limit) throws SQLException {
		int i = 0;
		if (null != instanceGroup && !"".equals(instanceGroup)) {
			ps.setString(++i, instanceGroup);
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
	public int queryJobPlanningCount(String instanceGroup) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			String sql = genQueryJobPlanning(QUERY_PLANNING_COUNT_BY_CONDITION,
					instanceGroup);
			ps = conn.prepareStatement(sql);
			ps = genQueryJobPlanningPs(ps, instanceGroup, 0, 0);

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
				break;
			}
		} catch (SQLException e) {
			LOG.error("queryJobPlanningCount error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return count;
	}

	@Override
	public JobPlanning queryJobPlanningById(String planningId) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		JobPlanning plan = new JobPlanning();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(QUERY_PLANNING_BY_ID);
			ps.setString(1, planningId);
			rs = ps.executeQuery();
			while (rs.next()) {
				plan.setId(rs.getString(1));
				plan.setInstanceId(rs.getString(2));
				plan.setScopeType(rs.getInt(3));
				plan.setScopeName(rs.getString(4));
				plan.setAccessRule(rs.getInt(5));
				break;
			}
		} catch (SQLException e) {
			LOG.error("queryByInstance error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
		return plan;
	}

	@Override
	public void deleteJobPlanningById(String planningId) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DELETE_PLANNING_BY_ID);
			ps.setString(1, planningId);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("deleteJobPlanningById error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
	}

	@Override
	public void insertJobPlanning(JobPlanning jobPlanning) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_JOB_PLANNING);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, jobPlanning.getInstanceId());
			ps.setInt(3, jobPlanning.getScopeType());
			ps.setString(4, jobPlanning.getScopeName());
			ps.setInt(5, jobPlanning.getAccessRule());

			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("insertJobPlanning error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
	}

	@Override
	public void updateJobPlanning(JobPlanning jobPlanning) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_JOB_PLANNING);
			ps.setString(1, jobPlanning.getInstanceId());
			ps.setInt(2, jobPlanning.getScopeType());
			ps.setString(3, jobPlanning.getScopeName());
			ps.setInt(4, jobPlanning.getAccessRule());
			ps.setString(5, jobPlanning.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("updateJobPlanning error", e);
		} finally {
			closeStatement(ps);
			closeConnection(conn);
		}
	}

}
