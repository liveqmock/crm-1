package com.deppon.foss.framework.server.components.jobgrid.dao;

import java.sql.Connection;
import java.util.List;

import com.deppon.foss.framework.server.components.jobgrid.domain.JobPlanning;
/**
 * 
 * <p>
 * Description:规则管理<br />
 * </p>
 * @title JobPlanningDao.java
 * @package com.deppon.foss.framework.server.components.jobgrid.dao 
 * @author 侯斌飞
 * @version 0.1 2013-2-25
 */
public interface JobPlanningDao {
	
	List<JobPlanning> queryByInstance(String instanceId);

	List<JobPlanning> queryByInstance(Connection conn, String instanceId);
	
	/**
	 * 
	 * <p>
	 * Description:规则管理 - 分页查询<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param instanceGroup
	 * @param start
	 * @param limit
	 * @return
	 * List<JobPlanning>
	 */
	List<JobPlanning> queryJobPlanning(String instanceGroup, int start,
			int limit);
	/**
	 * 
	 * <p>
	 * Description:规则管理 - 查询Count<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param instanceGroup
	 * @return
	 * int
	 */
	int queryJobPlanningCount(String instanceGroup);
	/**
	 * 
	 * <p>
	 * Description:规则管理 - 查询详情<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param planningId 规则Id
	 * @return
	 * JobPlanning
	 */
	JobPlanning queryJobPlanningById(String planningId);
	/**
	 * 
	 * <p>
	 * Description:规则管理 - 删除<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param planningId 规则ID
	 * void
	 */
	void deleteJobPlanningById(String planningId);
	/**
	 * 
	 * <p>
	 * Description:规则管理 - 新增<br />
	 * </p>
	 * @author 侯斌 飞
	 * @version 0.1 2013-2-25
	 * @param jobPlanning 规则信息
	 * void
	 */
	void insertJobPlanning(JobPlanning jobPlanning);
	/**
	 * 
	 * <p>
	 * Description:规则管理 - 修改<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobPlanning 规则信息
	 * void
	 */
	void updateJobPlanning(JobPlanning jobPlanning);

}
