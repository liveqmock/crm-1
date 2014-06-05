package com.deppon.foss.framework.server.components.jobgrid;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.JobPersistenceException;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Trigger;
import org.quartz.core.SchedulingContext;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.quartz.utils.Key;

import com.deppon.foss.framework.server.components.jobgrid.impl.NOPAcquireStrategy;

public class GridJobStoreTX extends JobStoreTX {
    private AcquireStrategy acquireStrategy = null;
    private String acquireStrategyClass = null;

    public String getAcquireStrategyClass() {
        return acquireStrategyClass;
    }

    public void setAcquireStrategyClass(String acquireStrategyClass) {
        this.acquireStrategyClass = acquireStrategyClass;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Trigger acquireNextTrigger(Connection conn, SchedulingContext ctxt, long noLaterThan)
        throws JobPersistenceException {
        do {
            try {
                Trigger nextTrigger = null;
                List keys = getDelegate().selectTriggerToAcquire(conn, noLaterThan, getMisfireTime());
                if (keys == null || keys.size() == 0)
                    return null;

                Iterator itr = keys.iterator();
                while (itr.hasNext()) {
                    Key triggerKey = (Key) itr.next();

                    nextTrigger = retrieveTrigger(conn, ctxt, triggerKey.getName(), triggerKey.getGroup());
                    //
                    if (!getAcquireStrategy().isAcquire(getInstanceId(), conn, nextTrigger)) {
                        continue;
                    }
                    int rowsUpdated =
                        getDelegate().updateTriggerStateFromOtherState(conn, triggerKey.getName(),
                            triggerKey.getGroup(), STATE_ACQUIRED, STATE_WAITING);
                    if (rowsUpdated <= 0) {
                        continue;
                    }
                    if (nextTrigger == null) {
                        continue;
                    }
                    break;
                }
                if (nextTrigger == null) {
                    continue;
                }
                nextTrigger.setFireInstanceId(getFiredTriggerRecordId());
                getDelegate().insertFiredTrigger(conn, nextTrigger, STATE_ACQUIRED, null);
                return nextTrigger;
            } catch (Exception e) {
                throw new JobPersistenceException("Couldn't acquire next trigger: " + e.getMessage(), e);
            }
        } while (true);
    }

    @Override
    protected void storeTrigger(Connection conn, SchedulingContext ctxt, Trigger newTrigger, JobDetail job,
        boolean replaceExisting, String state, boolean forceState, boolean recovering)
        throws ObjectAlreadyExistsException, JobPersistenceException {
        if (null != job || getAcquireStrategy().isAcquire(getInstanceId(), conn, newTrigger)) {
            super.storeTrigger(conn, ctxt, newTrigger, job, replaceExisting, state, forceState, recovering);
        }
    }

    @Override
    protected JobDetail retrieveJob(Connection conn, SchedulingContext ctxt, String jobName, String groupName)
        throws JobPersistenceException {
        try {
            return super.retrieveJob(conn, ctxt, jobName, groupName);
        } catch (JobPersistenceException jpe) {
            if (jpe.getCause() instanceof ClassNotFoundException) {
                return null;
            }
            throw jpe;
        }
    }

    protected AcquireStrategy getAcquireStrategy() {
        if (null == acquireStrategy) {
            if (null == acquireStrategyClass) {
                acquireStrategy = new NOPAcquireStrategy();
            }
            try {
                Class<?> clazz = getClassLoadHelper().loadClass(acquireStrategyClass);
                acquireStrategy = (AcquireStrategy) clazz.newInstance();
            } catch (Exception e) {
                throw new GridJobException("Can't create acquireStrategy using " + acquireStrategyClass);
            }
        }
        return acquireStrategy;
    }

}
