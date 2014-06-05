package com.deppon.crm.module.gis.server.service.impl;

import com.deppon.crm.module.gis.server.dao.IPolygonDao;
import com.deppon.crm.module.gis.server.service.IPointTransitionService;
import com.deppon.crm.module.gis.server.service.IPolygonService;
import com.deppon.crm.module.gis.server.service.ITaskService;
import com.deppon.crm.module.gis.shared.domain.PolygonEntity;
import com.deppon.crm.module.gis.shared.domain.TaskEntity;

public class PolygonService implements IPolygonService {

	private IPolygonDao polygonDao;
	private IPointTransitionService pointTransitionService;
	private ITaskService taskService;
	
	public ITaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	public IPointTransitionService getPointTransitionService() {
		return pointTransitionService;
	}
	public void setPointTransitionService(
			IPointTransitionService pointTransitionService) {
		this.pointTransitionService = pointTransitionService;
	}
	@Override
	public PolygonEntity savePolygon(PolygonEntity polygonEntity) {
		polygonEntity.setBaidu(pointTransitionService.transitionPointByOffset(polygonEntity.getGoogle()));
		polygonEntity = polygonDao.savePolygon(polygonEntity);
		TaskEntity task = new TaskEntity();
		task.setTaskParameter(polygonEntity.getPolygonID());
		task.setTaskType("POLYGON_CONVERT");
		taskService.saveTask(task);
		return polygonEntity;
	}
	@Override
	public PolygonEntity updatePolygon(PolygonEntity polygonEntity) {
		/**
		 * 更新前，先作废范围，然后再新增一个范围
		 */
		polygonDao.deletePolygonById(polygonEntity.getPolygonID());
		polygonEntity =savePolygon(polygonEntity);
		return polygonEntity;
	}

	@Override
	public String deletePolygonById(String id) {
		return polygonDao.deletePolygonById(id);
	}

	@Override
	public PolygonEntity selectPolygonById(String id) {
		return polygonDao.selectPolygonById(id);
	}
	
	public IPolygonDao getPolygonDao() {
		return polygonDao;
	}

	public void setPolygonDao(IPolygonDao polygonDao) {
		this.polygonDao = polygonDao;
	}
	@Override
	public void updatePolygonBaiduById(PolygonEntity polygon) {
		polygonDao.updatePolygonBaiduById(polygon);
		
	}

}
