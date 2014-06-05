package com.deppon.foss.framework.server.components.jobgrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import com.deppon.foss.framework.server.components.jobgrid.JobGridManager;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobLogging;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobPlanning;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleCondition;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleView;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;

public class JobGridManagerTest extends TestCase {
	public JobGridManager manager;

	final String INSTANCEID = "JUNITTEST_INSTANCE";
	final String NEXTJOB = "JUNITTEST_NEXTJOB";
	final String NEXTTRIGGER = "JUNITTEST_NEXTTRIGGER";
	final String HELLOJOB = "JUNITTEST_HELLOJOB";
	final String HELLOTRIGGER = "JUNITTEST_HELLOTRIGGER";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	protected void setUp() throws Exception {
		manager = TestUtil.jobGridManager;
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryJobLogging() {
		Date end = new Date();
		Date start = new Date(end.getTime() - 1000 * 60 * 60 * 24);
		System.out.println(start);
		System.out.println(end);
		List<JobLogging> jl = manager.queryJobLogging(null, null, null, start,
				end, 1, 15);
		int count = manager.queryJobLoggingCount(null, null, null, start, end);
		System.out.println("count:" + count);
		for (int i = 0; i < jl.size(); i++) {
			JobLogging jobLogging = jl.get(i);
			System.out.println(i + ":" + jobLogging.getInstanceId());
		}

		if (jl.size() > 0) {
			JobLogging jobLogging = manager.queryJobLoggingById(jl.get(0)
					.getId());
			System.out.println(jobLogging.getInstanceId());
		}
	}

	@Test
	public void testQueryJobPlanning() {
		List<JobPlanning> jl = manager.queryJobPlanning(null, 1, 15);
		int count = manager.queryJobPlanningCount(null);
		System.out.println("count：" + count);
		for (JobPlanning jobPlanning : jl) {
			System.out.println("id:" + jobPlanning.getId() + ",InstanceId:"
					+ jobPlanning.getInstanceId());
			System.out.println(manager
					.queryJobPlanningById(jobPlanning.getId()).getId());
		}
	}

	@Test
	public void testSaveDeleteJobPlanning() {
		JobPlanning jp = new JobPlanning();
		jp.setInstanceId(INSTANCEID);
		jp.setScopeType(1);
		jp.setScopeName("11111111111");
		jp.setAccessRule(1);
		manager.saveJobPlanning(jp);

		List<JobPlanning> jl = manager.queryJobPlanning(INSTANCEID, 1, 15);
		int count = manager.queryJobPlanningCount(INSTANCEID);
		System.out.println("count：" + count);
		for (JobPlanning jobPlanning : jl) {
			System.out.println("id:" + jobPlanning.getId() + ",InstanceId:"
					+ jobPlanning.getInstanceId() + ",ScopeName:"
					+ jobPlanning.getScopeName());
			System.out.println(manager
					.queryJobPlanningById(jobPlanning.getId()).getId());
		}
		jp = jl.get(0);
		jp.setScopeName("22222222222");
		manager.saveJobPlanning(jp);

		jp = manager.queryJobPlanningById(jp.getId());
		System.out.println("id:" + jp.getId() + ",InstanceId:"
				+ jp.getInstanceId() + ",ScopeName:" + jp.getScopeName());

		manager.deleteJobPlanningById(jp.getId());
		count = manager.queryJobPlanningCount(INSTANCEID);
		System.out.println("count：" + count);

	}

	@Test
	public void testQueryJobSchedule() throws SchedulerException {
		JobScheduleCondition condition = new JobScheduleCondition();
		condition.setStart(1);
		condition.setLimit(15);
		List<JobScheduleView> jsvl = manager.queryJobScheduleView(condition);
		for (int i = 0; i < jsvl.size(); i++) {
			JobScheduleView jobScheduleView = jsvl.get(i);
			System.out.println(i + ":"
					+ jobScheduleView.getJobSchedule().getJobClass());
		}
		if (jsvl.size() > 0) {
			JobScheduleView jobScheduleView = manager
					.queryJobScheduleViewById(jsvl.get(0).getJobSchedule()
							.getId());
			System.out.println(jobScheduleView.getJobSchedule().getJobClass());
		}
	}

	@Test
	public void testSaveDeleteJobSchedule() throws SchedulerException {
		JobScheduleCondition condition = new JobScheduleCondition();
		condition.setStart(1);
		condition.setLimit(15);
		// 停止所有已有計時器
		List<JobScheduleView> jsvl = manager.queryJobScheduleView(condition);
		int count = manager.queryJobScheduleCount(condition);
		stopAll(jsvl);
		sleepSecond(3);
		System.out.println(currentTime() + "count:" + count);
		System.out.println(currentTime() + "stopAll:停止所有已有計時器");

		JobWarnning jw = new JobWarnning();
		jw.setWarnType("1");
		jw.setFailTime(1);
		jw.setFailCount(1);
		jw.setMobile("13472528129");
		jw.setEmail("dphzm@deppon.com");

		// 新建插入时长较长定时器
		JobSchedule nextJob = createNextJob();
		nextJob.setId(null);
		nextJob.setJobName(NEXTJOB);
		nextJob.setTriggerName(NEXTTRIGGER);
		nextJob.setTriggerExpression("0 0 0 1 1 ?");
		manager.saveJobSchedule(nextJob, jw);

		JobSchedule helloJob = createHelloJob();
		helloJob.setId(null);
		helloJob.setJobName(HELLOJOB);
		helloJob.setTriggerName(HELLOTRIGGER);
		helloJob.setTriggerExpression("0 0 0/5 * * ?");
		manager.saveJobSchedule(helloJob, jw);
		System.out.println(currentTime() + "saveJobSchedule:新建插入时长较长定时器");
		sleepSecond(10);
		
		condition.setTriggerName(HELLOTRIGGER);
		// 修改定时器的时间频率0/3 * * * * ?
		jsvl = manager.queryJobScheduleView(condition);
		helloJob = jsvl.get(0).getJobSchedule();
		helloJob.setTriggerExpression("0/3 * * * * ?");
		manager.saveJobSchedule(helloJob, jw);
		System.out.println(currentTime()
				+ "saveJobSchedule:修改定时器的时间频率0/3 * * * * ?");
		sleepSecond(10);
		
		condition.setTriggerName(NEXTTRIGGER);
		// 删除新增的定时器
		jsvl = manager.queryJobScheduleView(condition);
		nextJob = jsvl.get(0).getJobSchedule();
		manager.deleteJobScheduleById(nextJob.getId());
		condition.setTriggerName(HELLOTRIGGER);
		jsvl = manager.queryJobScheduleView(condition);
		helloJob = jsvl.get(0).getJobSchedule();
		manager.deleteJobScheduleById(helloJob.getId());
		System.out.println(currentTime() + "deleteJobScheduleById：删除新增的定时器");
		sleepSecond(10);

		condition.setTriggerName(null);
		// 启动停止的定时器
		jsvl = manager.queryJobScheduleView(condition);
		startAll(jsvl);
		System.out.println(currentTime() + "startAll：动停止的定时器");
		sleepSecond(10);
	}

	@Test
	public void testOperationJobSchedule() throws SchedulerException {
		JobScheduleCondition condition = new JobScheduleCondition();
		condition.setStart(1);
		condition.setLimit(15);
		List<JobScheduleView> jsvl = manager.queryJobScheduleView(condition);
		int count = manager.queryJobScheduleCount(condition);
		stopAll(jsvl);
		sleepSecond(3);
		System.out.println(currentTime() + "count:" + count);
		System.out.println(currentTime() + "stopAll");

		JobWarnning jw = new JobWarnning();
		jw.setWarnType("1");
		jw.setFailTime(1);
		jw.setFailCount(1);
		jw.setMobile("13472528129");
		jw.setEmail("dphzm@deppon.com");

		JobSchedule nextJob = createNextJob();
		nextJob.setId(null);
		nextJob.setJobName(NEXTJOB);
		nextJob.setTriggerName(NEXTTRIGGER);
		nextJob.setTriggerExpression("0 0 0 1 1 ?");
		manager.saveJobSchedule(nextJob, jw);

		JobSchedule helloJob = createHelloJob();
		helloJob.setId(null);
		helloJob.setJobName(HELLOJOB);
		helloJob.setTriggerName(HELLOTRIGGER);
		helloJob.setTriggerExpression("0 0 0/5 * * ?");
		manager.saveJobSchedule(helloJob, jw);
		System.out.println(currentTime() + "saveJobSchedule");
		// sleepSecond(60 * 1);
		
		condition.setJobName(HELLOJOB);
		condition.setTriggerName(HELLOTRIGGER);
		jsvl = manager.queryJobScheduleView(condition);
		helloJob = jsvl.get(0).getJobSchedule();
		manager.executeJobScheduleById(helloJob.getId());
		System.out.println(currentTime() + "executeJobScheduleById");
		sleepSecond(10);

		helloJob.setTriggerExpression("0/2 * * * * ?");
		manager.saveJobSchedule(helloJob, jw);
		System.out.println(currentTime() + "saveJobSchedule");
		sleepSecond(10);

		jsvl = manager.queryJobScheduleView(condition);
		helloJob = jsvl.get(0).getJobSchedule();
		manager.stopJobScheduleById(helloJob.getId());
		System.out.println(currentTime() + "stopJobScheduleById");
		sleepSecond(10);

		manager.startJobScheduleById(helloJob.getId());
		System.out.println(currentTime() + "startJobScheduleById");
		sleepSecond(10);
		
		condition.setJobName(null);
		condition.setTriggerName(NEXTTRIGGER);
		jsvl = manager.queryJobScheduleView(condition);
		nextJob = jsvl.get(0).getJobSchedule();
		manager.deleteJobScheduleById(nextJob.getId());
		condition.setJobName(null);
		condition.setTriggerName(HELLOTRIGGER);
		jsvl = manager.queryJobScheduleView(condition);
		helloJob = jsvl.get(0).getJobSchedule();
		manager.deleteJobScheduleById(helloJob.getId());
		System.out.println(currentTime() + "deleteJobScheduleById");
		sleepSecond(10);

		condition.setJobName(null);
		condition.setJobGroup(null);
		condition.setTriggerName(null);
		jsvl = manager.queryJobScheduleView(condition);
		startAll(jsvl);
		System.out.println(currentTime() + "startAll");
		sleepSecond(3);

	}

	@Test
	public void testQueryJobMessage() {
		List<JobMessage> jl = manager.queryAllJobMessageUnsend();
		System.out.println("count：" + jl.size());
		JobMessage jobMessage = new JobMessage();
		jobMessage.setEmail("dphzm@deppon.com");
		jobMessage.setMobile("13472528129");
		jobMessage.setSubject(currentTime() + ": Testing...");
		jobMessage.setContent(currentTime() + ": This is a testing message !");
		jobMessage.setSend(0);
		manager.insertJobMessage(jobMessage);

		jl = manager.queryAllJobMessageUnsend();
		System.out.println("count：" + jl.size());
		for (JobMessage jm : jl) {
			System.out.println(jm.getId() + " " + jm.getContent());
			manager.updateJobMessageSend(jm.getId());
		}

	}

	@Test
	public void testSleep() {
		// sleepSecond(60 * 60 * 2);
	}

	private String currentTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date()) + ":============>>> ";
	}

	private void sleepSecond(int i) {
		try {
			Thread.sleep(1000 * i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void stopAll(List<JobScheduleView> jsvl) throws SchedulerException {
		for (JobScheduleView jsv : jsvl) {
			if (jsv.getTriggerState() == Trigger.STATE_NORMAL) {
				JobSchedule js = jsv.getJobSchedule();
				manager.stopJobScheduleById(js.getId());
				System.out.println("Id:" + js.getId() + " state:"
						+ jsv.getTriggerState());
			}
		}

	}

	private void startAll(List<JobScheduleView> jsvl) throws SchedulerException {
		for (JobScheduleView jsv : jsvl) {
			if (jsv.getTriggerState() == Trigger.STATE_PAUSED) {
				JobSchedule js = jsv.getJobSchedule();
				manager.startJobScheduleById(js.getId());
				System.out.println("Id:" + js.getId() + " state:"
						+ jsv.getTriggerState());
			}
		}
	}
	private JobSchedule createHelloJob() {
		JobSchedule js = new JobSchedule();
		js.setTriggerName("helloJobTrigger");
		js.setTriggerGroup("CrmTriggerOne");
		js.setJobName("helloJob");
		js.setJobGroup("CrmJobOne");
		js.setDescription("helloJob");
		js.setTriggerType(1);
		js.setTriggerExpression("0/1 * * * * ?");
		js.setJobClass(HelloJob.class.getName());
		String jobdata = "#" + currentTime()
				+ System.getProperty("line.separator") + "username=admin"
				+ System.getProperty("line.separator") + "password=123456"
				+ System.getProperty("line.separator") + "nextjob=nextJob";
		js.setJobData(jobdata);
		return js;
	}

	private JobSchedule createNextJob() {
		JobSchedule js = new JobSchedule();
		js.setTriggerName("nextJobTrigger");
		js.setTriggerGroup("CrmTriggerTwo");
		js.setJobName("nextJob");
		js.setJobGroup("CrmJobTwo");
		js.setDescription("nextJob");
		js.setTriggerType(1);
		js.setTriggerExpression("0 0 0 1 1 ?");
		js.setJobClass(NextJob.class.getName());
		String jobdata = "#" + currentTime()
				+ System.getProperty("line.separator") + "username=admin"
				+ System.getProperty("line.separator") + "password=123456";
		js.setJobData(jobdata);
		return js;
	}
}
