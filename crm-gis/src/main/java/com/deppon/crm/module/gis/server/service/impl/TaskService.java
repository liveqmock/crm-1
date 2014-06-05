package com.deppon.crm.module.gis.server.service.impl;

import java.util.List;

import com.deppon.crm.module.gis.server.dao.ITaskDao;
import com.deppon.crm.module.gis.server.service.ITaskService;
import com.deppon.crm.module.gis.shared.domain.TaskEntity;

public class TaskService implements ITaskService {
	private ITaskDao taskDao;

	public ITaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(ITaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public void saveTask(TaskEntity task) {
		taskDao.saveTask(task);

	}

	@Override
	public void deleteAllFinishTask() {
		taskDao.deleteAllFinishTask();

	}

	@Override
	public TaskEntity queryTaskByType(String type) {
		return taskDao.queryTaskByType(type);
	}

	@Override
	public List<TaskEntity> queryAllUnDoTask() {
		return taskDao.queryAllUnDoTask();
	}

	@Override
	public void updateTaskById(String id) {
		taskDao.updateTaskById(id);
	}

}
