package com.deppon.crm.module.gis.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.gis.server.dao.ITaskDao;
import com.deppon.crm.module.gis.shared.domain.TaskEntity;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class TaskDao extends iBatis3DaoImpl implements ITaskDao{
	private static final String NAMESPACE="com.deppon.crm.module.gis.shared.domain.TaskEntity.";

	@Override
	public void saveTask(TaskEntity task) {
		getSqlSession().insert(NAMESPACE + "saveTask", task);
		
	}

	@Override
	public void deleteAllFinishTask() {
		getSqlSession().delete(NAMESPACE + "deleteTaskByStatus");
		
	}

	@Override
	public TaskEntity queryTaskByType(String type) {
		return (TaskEntity) getSqlSession().selectOne(NAMESPACE + "queryTaskByType", type);
	}

	@Override
	public List<TaskEntity> queryAllUnDoTask() {
		return  (List<TaskEntity>) getSqlSession().selectList(NAMESPACE + "queryAllUnDoTask");
	}

	@Override
	public void updateTaskById(String id) {
		getSqlSession().update(NAMESPACE + "updateTaskById", id);
	}

}
