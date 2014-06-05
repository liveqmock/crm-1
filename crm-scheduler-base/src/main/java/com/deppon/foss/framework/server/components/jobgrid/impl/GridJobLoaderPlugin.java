package com.deppon.foss.framework.server.components.jobgrid.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.SchedulerPlugin;

import com.deppon.foss.framework.server.components.jobgrid.dao.JobScheduleDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JDBCContants;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JobScheduleDaoImpl;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule;

public class GridJobLoaderPlugin implements SchedulerPlugin {
    private Scheduler scheduler;
    private JobScheduleDao jobScheduleDao;
    private String dataSource;
    private Log LOG = LogFactory.getLog(GridJobLoaderPlugin.class);
    @Override
    public void initialize(String name, Scheduler scheduler) throws SchedulerException {
        this.scheduler = scheduler;
    }

    @Override
    public void start() {
        JobScheduleDao jobScheduleDao = getJobScheduleDao();
        List<JobSchedule> jss = jobScheduleDao.queryAll();
        for (JobSchedule js : jss) {
            // 更新scheduler中的任务数据
            JobDetail jd = fromJobSchedule(js);
            if (null == jd) {
                continue;
            }
            try {
                Trigger oldTrigger = scheduler.getTrigger(js.getTriggerName(), js.getTriggerGroup());
                if (null != oldTrigger) {
                    scheduler.addJob(jd, true);
                    continue;
                }
                // 如果任务没有运行加入到计划中去
                Trigger newTrigger = createTrigger(js);
                if (null != newTrigger) {
                    scheduler.scheduleJob(jd, newTrigger);
                }
            } catch (SchedulerException e) {
                LOG.error("schedule job error", e);
            }
        }

    }

    private Trigger createTrigger(JobSchedule js) {
        try {
            if (JDBCContants.TRIGGER_TYPE_SIMPLE == js.getTriggerType()) {
                String expr = js.getTriggerExpression();
                int splitIndex = expr.indexOf(":");
                int repeatCount = Integer.valueOf(expr.substring(0, splitIndex));
                long repeatInterval = Long.valueOf(expr.substring(splitIndex + 1));
                return new SimpleTrigger(js.getTriggerName(), js.getTriggerGroup(), repeatCount, repeatInterval);
            } else if (JDBCContants.TRIGGER_TYPE_CRON == js.getTriggerType()) {
                return new CronTrigger(js.getTriggerName(), js.getTriggerGroup(), js.getTriggerExpression());
            }
        } catch (NumberFormatException e) {
        } catch (ParseException e) {
            LOG.error("invaild cron expression", e);
        }
        return null;
    }

    private JobDetail fromJobSchedule(JobSchedule js) {
        try {
            JobDetail jd = new JobDetail();
            jd.setJobClass(Class.forName(js.getJobClass()));
            jd.setDescription(js.getDescription());
            jd.setGroup(js.getJobGroup());
            jd.setName(js.getJobName());
            jd.setJobDataMap(GridJobUtils.fromString(js.getJobData()));
            return jd;
        } catch (ClassNotFoundException e) {
            LOG.error("can't find job class:" + js.getJobClass());
            return null;
        }
    }

    @Override
    public void shutdown() {

    }

    public void setJobScheduleDao(JobScheduleDao jobScheduleDao) {
        this.jobScheduleDao = jobScheduleDao;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSourceName) {
        this.dataSource = dataSourceName;
    }

    public JobScheduleDao getJobScheduleDao() {
        if (null == jobScheduleDao && null != dataSource) {
            jobScheduleDao = new JobScheduleDaoImpl(dataSource);
        }
        return jobScheduleDao;
    }

}
