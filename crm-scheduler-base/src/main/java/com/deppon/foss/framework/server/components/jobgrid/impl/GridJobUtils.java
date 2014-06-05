package com.deppon.foss.framework.server.components.jobgrid.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public final class GridJobUtils {

    public static String getInstanceId(Scheduler sch) {
        try {
            return sch.getSchedulerInstanceId();
        } catch (SchedulerException e) {
            return "@UNKNOWN-CLUSTER";
        }
    }

    @SuppressWarnings("unchecked")
    public static Properties toProperties(JobDataMap jdm) {
        Properties p = new Properties();
        p.putAll(jdm);
        return p;
    }

    public static JobDataMap fromString(String text) {
        if (null == text || 0 == text.length()) {
            return null;
        }
        try {
            Properties p = new Properties();
            StringReader sr = new StringReader(text);
            p.load(sr);
            return toJobDataMap(p);
        } catch (IOException e) {
            return null;
        }
    }

    public static JobDataMap toJobDataMap(Properties p) {
        return new JobDataMap(p);
    }

    public static String toPropertiesString(JobDataMap jdm) {
        Properties p = toProperties(jdm);
        return toPropertiesString(p);
    }

    public static String toPropertiesString(Properties p) {
        if (null == p || 0 == p.size()) {
            return "";
        }
        try {
            StringWriter sw = new StringWriter();
            p.store(sw, null);
            sw.close();
            return sw.toString();
        } catch (IOException e) {
        }
        return "";
    }
}
