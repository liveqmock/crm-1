package com.deppon.foss.framework.server.components.jobgrid.impl;

import java.sql.Connection;

import org.quartz.Trigger;

import com.deppon.foss.framework.server.components.jobgrid.AcquireStrategy;

public class NOPAcquireStrategy implements AcquireStrategy {

    @Override
    public boolean isAcquire(String instanceId, Connection conn, Trigger trigger) {
        return true;
    }

}
