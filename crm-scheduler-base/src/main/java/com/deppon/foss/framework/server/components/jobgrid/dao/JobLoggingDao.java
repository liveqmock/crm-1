package com.deppon.foss.framework.server.components.jobgrid.dao;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.server.components.jobgrid.domain.JobLogging;
/**
 * 
 * <p>
 * Description:日志管理<br />
 * </p>
 * @title JobLoggingDao.java
 * @package com.deppon.foss.framework.server.components.jobgrid.dao 
 * @author 侯斌飞
 * @version 0.1 2013-2-25
 */
public interface JobLoggingDao {
	/**
	 * 
	 * <p>
	 * Description:日志管理 - 新增<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param log
	 * void
	 */
    void insert(JobLogging log);
    
	List<JobLogging> queryByJob(String cluster, String jobName);

	List<JobLogging> queryByTrigger(String cluster, String jobName);
	/**
	 * 
	 * <p>
	 * Description:日志管理 - 分页查询<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @param startDate 开始时间-必要条件
	 * @param endDate   结束时间-必要条件
	 * @param start
	 * @param limit
	 * @return
	 * List<JobLogging>
	 */
	List<JobLogging> queryJobLogging(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate, int start,
			int limit);
	/**
	 * 
	 * <p>
	 * Description:日志管理 - 查询Count<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @param startDate
	 * @param endDate
	 * @return
	 * int
	 */
	int queryJobLoggingCount(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate);
	
	/**
	 * 
	 * <p>
	 * Description:日志管理 - 查询详情<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param logId 日志Id
	 * @return
	 * JobLogging
	 */
	JobLogging queryJobLoggingById(String logId);
	
	int queryVetoedCountByJobName(String jobGroup, String jobName, int minute);

}
