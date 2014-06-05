package com.deppon.foss.framework.server.components.jobgrid.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.deppon.foss.framework.server.components.jobgrid.dao.JobLoggingDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.JobMessageDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.JobWarnningDao;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobLogging;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;

public class GridJobListener implements JobListener, ApplicationContextAware {
    public static final String APPLICATION_CONTEXT = "SPRING_CONTEXT";
    private static final String LISTENER_NAME = "GRID_JOB_LISTENER";
    private Log LOG = LogFactory.getLog(GridJobListener.class);
    private ApplicationContext appContext;
    private JobLoggingDao loggingDao;
    private JobMessageDao messageDao;
    private JobWarnningDao warnningDao;

	public void setLoggingDao(JobLoggingDao loggingDao) {
		this.loggingDao = loggingDao;
	}

	public void setMessageDao(JobMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public void setWarnningDao(JobWarnningDao warnningDao) {
		this.warnningDao = warnningDao;
	}

	@Override
    public String getName() {
        return LISTENER_NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        context.put(APPLICATION_CONTEXT, appContext);
        try {
            String flowId = UUID.randomUUID().toString();
            context.put("FLOW_UUID", flowId);
            JobLogging log = createLogging(context, "EXECUTING", flowId, null);
            loggingDao.insert(log);
        } catch (Exception e) {
            LOG.error("jobToBeExecuted", e);
        }
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String flowId = (String) context.get("FLOW_UUID");
        JobLogging log = createLogging(context, "VETOED", flowId, null);
        loggingDao.insert(log);
		JobWarnning jw = warnningDao.queryJobWarnningByName(context
				.getJobDetail().getName(), context.getJobDetail().getGroup());
		if (null != jw && "" != jw.getWarnType()
				&& "1".equals(jw.getWarnType())) {
			messageDao.insertJobMessage(createJobMessage(context, jw));
		}
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String flowId = (String) context.get("FLOW_UUID");
        String errMessage = null;
        if (null != jobException) {
            errMessage = jobException.getMessage();
        }
        JobLogging log = createLogging(context, "EXECUTED", flowId, errMessage);
        loggingDao.insert(log);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

	private JobMessage createJobMessage(JobExecutionContext ctx, JobWarnning jw) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JobMessage jm = new JobMessage();
		jm.setEmail(jw.getEmail());
		jm.setMobile(jw.getMobile());
		String subject = df.format(new Date()) + ":JobGroup is:"
				+ jw.getJobGroup() + ",JobName is:" + jw.getJobName()
				+ " execution vetoed !";
		jm.setSubject(subject);
		String content = subject
				+ " Ps:This is auto message, don't reply to the message !";
		jm.setContent(content);
		return jm;

	}

    private JobLogging createLogging(JobExecutionContext ctx, String action, String flowId, String errMessage) {
        JobLogging log = new JobLogging();
        Scheduler sch = ctx.getScheduler();
        log.setInstanceId(GridJobUtils.getInstanceId(sch));
        log.setFiredTime(new Date());
        log.setJobAction(action);
        log.setJobGroup(ctx.getJobDetail().getGroup());
        log.setJobName(ctx.getJobDetail().getName());
        log.setTriggerGroup(ctx.getTrigger().getGroup());
        log.setTriggerName(ctx.getTrigger().getName());
        log.setErrorMessage(errMessage);
        log.setFlowUuid(flowId);
        return log;
    }

}
