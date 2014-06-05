package com.service;

import com.deppon.crm.module.gis.server.service.ITaskService;
import com.deppon.crm.module.gis.server.service.impl.TaskService;
import com.deppon.crm.module.gis.shared.domain.TaskEntity;
import com.util.SpringTestHelper;

public class TaskServiceTest {
ITaskService taskService = null;
	
//	@Test
	public void test(){
		
		taskService= SpringTestHelper.get().getBean(TaskService.class);
		TaskEntity task =  new TaskEntity();
		task.setTaskParameter("123");
		task.setTaskType("22");
		task.setTaskStatus(1);
		taskService.saveTask(task);
		taskService.queryAllUnDoTask();
		taskService.queryTaskByType("22");
		taskService.updateTaskById(task.getId());
		taskService.deleteAllFinishTask();
		
	}
}
