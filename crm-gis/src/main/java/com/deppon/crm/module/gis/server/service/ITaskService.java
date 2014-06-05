package com.deppon.crm.module.gis.server.service;

import java.util.List;

import com.deppon.crm.module.gis.shared.domain.TaskEntity;

public interface ITaskService {
	/**
	 * 
	 * <p>TODO(保存任务数据到数据库)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-10 下午04:42:25
	 * @param task
	 * @see
	 */
	void saveTask(TaskEntity task);
	/**
	 * 
	 * <p>TODO(删除执行完成的任务根据任务的状态)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-10 下午04:42:45
	 * @param status
	 * @see
	 */
	void deleteAllFinishTask();
	/**
	 * 
	 * <p>TODO(根据任务参数更新任务状态)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-10 下午05:02:53
	 * @param param
	 * @see
	 */
	void updateTaskById(String id);
	/**
	 * 
	 * <p>TODO(根据任务类型查询任务)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-10 下午04:44:13
	 * @param type
	 * @return
	 * @see
	 */
	TaskEntity queryTaskByType(String type);
	/**
	 * 
	 * <p>TODO(查询所有没有做的任务)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-10 下午04:44:13
	 * @param type
	 * @return
	 * @see
	 */
	List<TaskEntity> queryAllUnDoTask();
	
}
