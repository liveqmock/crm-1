package com.deppon.foss.framework.server.components.jobgrid.impl;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quartz.Trigger;

import com.deppon.foss.framework.server.components.jobgrid.AcquireStrategy;
import com.deppon.foss.framework.server.components.jobgrid.dao.JobPlanningDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JobPlanningDaoImpl;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobPlanning;

public class PlannedAcquireStrategy implements AcquireStrategy {
    private static final int RULES_ALLOW = 1;
    private static final int SCOPE_GROUP = 1;
    private Set<String> allowedJobGroup;
    private Set<String> allowedJob;
    private Set<String> deniedJobGroup;
    private Set<String> deniedJob;

    @Override
    public boolean isAcquire(String instanceId, Connection conn, Trigger trigger) {
        initAccessRules(conn, instanceId);
        if (deniedJob.contains(trigger.getJobName()) || deniedJobGroup.contains(trigger.getJobGroup())) {
            return false;
        }
        if (allowedJob.contains(trigger.getJobName()) || allowedJobGroup.contains(trigger.getJobGroup())) {
            return true;
        }
        return false;
    }

    protected void initAccessRules(Connection conn, String instanceId) {
        if (null == allowedJobGroup) {
            JobPlanningDao planDao = new JobPlanningDaoImpl("");
            List<JobPlanning> rules = planDao.queryByInstance(conn, instanceId);

            allowedJobGroup = new HashSet<String>();
            allowedJob = new HashSet<String>();
            deniedJobGroup = new HashSet<String>();
            deniedJob = new HashSet<String>();
            for (JobPlanning rule : rules) {
                if (SCOPE_GROUP == rule.getScopeType()) {
                    if (RULES_ALLOW == rule.getAccessRule()) {
                        allowedJobGroup.add(rule.getScopeName());
                    } else {
                        deniedJobGroup.add(rule.getScopeName());
                    }
                } else {
                    if (RULES_ALLOW == rule.getAccessRule()) {
                        allowedJob.add(rule.getScopeName());
                    } else {
                        deniedJob.add(rule.getScopeName());
                    }
                }
            }
        }
    }
}
