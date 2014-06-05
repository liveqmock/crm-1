package com.deppon.crm.module.scheduler.server.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao;
import com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao;
import com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity;
import com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity;

/**
 * <p>
 * OM_EMPLOYEE的信息转换为T_ORG_EMPLOYEE的数据格式，<br />
 * OM_EMPLOYEE OA系统职员表</br> T_ORG_EMPLOYEE CRM系统职员表
 * </p>
 * 
 * @title TimerSyncEmployee.java
 * @package com.deppon.crm.module.scheduler.server.manager
 * @author 苏玉军
 * @version 0.1 2012-7-3
 */

public class TimerSyncEmployee {
	private static final String PATH = System.getProperty("user.dir");
	private static final String FILENAME = "lastUpdTime_employee.txt";
	private String lastTime;
	private static Logger log = Logger.getLogger(TimerSyncEmployee.class);
	private static String PREFIX = "EMPLOYEE:";
	private static String JOB_MSG_INIT = "人员同步读取时间失败";
	private static String JOB_MSG_MKDIR = "创建文件失败";
	private static String JOB_MSG_FAILURE = "人员信息同步失败，工号为：";
	private static String JOB_MSG_NOTFOUNDDEPT = "未找到该员工的所在的部门，工号为：";
	private Date lastUpdateTime = null;
	// 从文件中加载最后更新时间
	private static File file = new File(PATH, FILENAME);
	private ISyncEmployeeDao employeeDao;
	private ISyncDepartmentDao departmentDao;

	public ISyncEmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(ISyncEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public ISyncDepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(ISyncDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	private void init() {
		if (file.exists()) {
			try {
				lastTime = FileUtils.readFileToString(file, "UTF-8");
			} catch (IOException e) {
				log.debug(PREFIX + JOB_MSG_INIT, e);
			}
			if (lastTime != null && !lastTime.equals("")) {
				long lon = Long.valueOf(lastTime);
				lastUpdateTime = new Date(lon);
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.debug(PREFIX + JOB_MSG_MKDIR, e);
			}
		}
	}

	/*
	 * 员工数据同步主方法
	 */
	public void syncEmployee() {
		this.init();
		if (lastUpdateTime == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			lastUpdateTime = calendar.getTime();
		}
		List<OaEmployeeEntity> list = new ArrayList<OaEmployeeEntity>();
		String empCode = "";
		list = employeeDao.queryEmployeeByDate(lastUpdateTime);
		boolean b = false;
		int i = 0;
		for (OaEmployeeEntity entity : list) {
			b = this.isExistsOrgForEmp(entity);
			if (b == false) {
				log.warn(PREFIX + JOB_MSG_NOTFOUNDDEPT + entity.getEmpCode());
				continue;
			}
			entity = this.changeFieldValue(entity);
			empCode = entity.getEmpCode();
			i = employeeDao.countEmpByEmpCode(empCode);
			try {
				if (i == 0) {
					// CRM人员表中不存在--新增
					b = employeeDao.insertEmployee(entity);
				} else {
					b = employeeDao.updateEmployee(entity);
				}
			} catch (Exception e) {
				log.debug("update employee has error,the empcode = "
						+ entity.getEmpCode());
			}
			if (b == false) {
				log.debug(PREFIX + JOB_MSG_FAILURE + entity.getEmpCode());
			}
		}
		// 将更新时间写入文件
		try {
			
			Date now = employeeDao.getSysDate();
			Long time = now.getTime();
			FileUtils.writeStringToFile(file,time.toString());
		} catch (IOException e) {
			log.debug("更新时间回写失败", e);
		}
	}

	private boolean isExistsOrgForEmp(OaEmployeeEntity entity) {
		OaDepartmentEntity dept = new OaDepartmentEntity();
		dept = departmentDao.queryDeptById(entity.getOrgId());
		String deptId = dept.getId();
		int result = departmentDao.countDepartmentByDeptSeq(deptId);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * <p>
	 * 对应的状态值转换<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @param entity
	 * @return OaEmployeeEntity
	 */
	private OaEmployeeEntity changeFieldValue(OaEmployeeEntity entity) {
		if (entity.getGender().equals("m")) {
			entity.setGender("1");
		}
		if (entity.getGender().equals("f")) {
			entity.setGender("0");
		}

		if (entity.getEmpStatus().equals("on")) {
			entity.setEmpStatus("1");
		}
		if (entity.getEmpStatus().equals("leave")) {
			entity.setEmpStatus("0");
		}
		return entity;
	}
}
