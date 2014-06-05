package com.deppon.foss.framework.server.components.jobgrid.dao;

import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;
/**
 * 
 * <p>
 * Description:预警时效<br />
 * </p>
 * @title JobWarnningDao.java
 * @package com.deppon.foss.framework.server.components.jobgrid.dao 
 * @author 侯斌飞
 * @version 0.1 2013-2-25
 */
public interface JobWarnningDao {
	/**
	 * 
	 * <p>
	 * Description:预警时效 - 新增<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jw
	 * void
	 */
	void insert(JobWarnning jw);
	/**
	 * 
	 * <p>
	 * Description:预警时效 - 删除<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * void
	 */
	void deleteJobWarnningByName(String jobName, String jobGroup);
	/**
	 * 
	 * <p>
	 * Description:预警时效 - 查询详情<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * JobWarnning
	 */
	JobWarnning queryJobWarnningByName(String jobName, String jobGroup);

}
