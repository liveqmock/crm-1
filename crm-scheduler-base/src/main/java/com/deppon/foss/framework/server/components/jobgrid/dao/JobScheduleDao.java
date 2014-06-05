package com.deppon.foss.framework.server.components.jobgrid.dao;

import java.util.List;

import com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleCondition;
/**
 * 
 * <p>
 * Description:任务调度<br />
 * </p>
 * @title JobScheduleDao.java
 * @package com.deppon.foss.framework.server.components.jobgrid.dao 
 * @author 侯斌飞
 * @version 0.1 2013-2-25
 */
public interface JobScheduleDao {
    /**
     * 
     * <p>
     * Description:任务调度 - 插入<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param js
     * void
     */
    void insert(JobSchedule js);
    /**
     * 
     * <p>
     * Description:任务调度 - 修改<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param js
     * void
     */
    void update(JobSchedule js);
    
    /**
     * 
     * <p>
     * Description:任务调度 - 删除<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @param jobName
     * @param triggerName
     * void
     */
    void deleteByJob(String jobName, String triggerName);
    /**
     * 
     * <p>
     * Description:任务调度 - 查询集合All<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @return
     * List<JobSchedule>
     */
    List<JobSchedule> queryAll();
    
	void deleteByTrigger(String triggerGroup, String triggerName);
	
	/**
	 * 
	 * <p>
	 * Description:任务调度 - 查询集合分页<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param condition
	 * @return
	 * List<JobSchedule>
	 */
	List<JobSchedule> queryJobSchedule(JobScheduleCondition condition);
	/**
	 * 
	 * <p>
	 * Description:任务调度 - 查询Count<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param condition
	 * @return
	 * int
	 */
	int queryJobScheduleCount(JobScheduleCondition condition);
	
	/**
	 * 
	 * <p>
	 * Description:任务调度 - 查询详情<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param scheduleId 任务Id
	 * @return
	 * JobSchedule
	 */
	JobSchedule queryJobScheduleById(String scheduleId);
	/**
	 * 
	 * <p>
	 * Description:任务调度 - 删除<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param scheduleId
	 * void
	 */
	void deleteJobScheduleById(String scheduleId);

	JobSchedule queryJobScheduleByName(String jobName, String jobGroup);

}
