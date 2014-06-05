package com.deppon.foss.framework.server.components.jobgrid;

import java.sql.Connection;

import org.quartz.Trigger;

public interface AcquireStrategy {
    /**
     * <p>TODO(方法详细描述说明、方法参数的具体涵义)</p> 
     * @param instanceId 
     * @param conn
     * @param trigger
     * @return
     */
    boolean isAcquire(String instanceId, Connection conn, Trigger trigger);
}
