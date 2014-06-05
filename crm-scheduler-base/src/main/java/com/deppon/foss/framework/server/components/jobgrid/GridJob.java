package com.deppon.foss.framework.server.components.jobgrid;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.context.ApplicationContext;

import com.deppon.foss.framework.server.components.jobgrid.impl.GridJobListener;

public abstract class GridJob implements StatefulJob,Job {
    private ApplicationContext appContext;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.init(context);
        this.doExecute(context);
    }

    protected void init(JobExecutionContext context) {
        Object cxt = context.get(GridJobListener.APPLICATION_CONTEXT);
        if (null != cxt && ApplicationContext.class.isInstance(cxt)) {
            appContext = (ApplicationContext) cxt;
        }
    }

    protected Object getProperty(JobExecutionContext context, String key) {
        JobDataMap dataMap = context.getMergedJobDataMap();
        return dataMap.get(key);
    }

    protected <T> T getBean(String name, Class<T> clazz) {
        return getAppContext().getBean(name, clazz);
    }

    protected ApplicationContext getAppContext() {
        return appContext;
    }

    protected abstract void doExecute(JobExecutionContext context) throws JobExecutionException;
}
