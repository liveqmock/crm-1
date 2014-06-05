package com.deppon.crm.module.gis.server.service.impl;

import java.util.List;

import com.deppon.crm.module.gis.server.service.IGoogleRegion2BaiduService;
import com.deppon.crm.module.gis.server.service.IPointTransitionService;
import com.deppon.crm.module.gis.server.service.IPolygonService;
import com.deppon.crm.module.gis.server.service.ITaskService;
import com.deppon.crm.module.gis.shared.domain.PolygonEntity;
import com.deppon.crm.module.gis.shared.domain.TaskEntity;

public class GoogleRegion2BaiduService implements IGoogleRegion2BaiduService{
	
	private ITaskService taskService;
	private IPolygonService polygonService;
	private IPointTransitionService pointTransitionService;
	
	public IPointTransitionService getPointTransitionService() {
		return pointTransitionService;
	}
	public void setPointTransitionService(
			IPointTransitionService pointTransitionService) {
		this.pointTransitionService = pointTransitionService;
	}
	public IPolygonService getPolygonService() {
		return polygonService;
	}
	public void setPolygonService(IPolygonService polygonService) {
		this.polygonService = polygonService;
	}
	public ITaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	@Override
	public void executeGoogleRegion2Baidu() {
		List<TaskEntity> taskList = taskService.queryAllUnDoTask();
		for(TaskEntity task:taskList){
			String polygonId = task.getTaskParameter();
			if(polygonId==null||"".equals(polygonId)){
				return;
			}
			PolygonEntity polygon =polygonService.selectPolygonById(polygonId);	
			if(polygon==null||polygon.getGoogle()==null||"".equals(polygon.getGoogle())){
				return;
			}
			String baiduRegion = pointTransitionService.googleConvertBaiduByWs(polygon.getGoogle());
			polygon.setBaidu(baiduRegion);
			polygonService.updatePolygonBaiduById(polygon);
			taskService.updateTaskById(task.getId());
//			taskService.deleteAllFinishTask();
		}
	}

}
