package com.deppon.crm.module.scheduler.server.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao;
import com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity;

/**
 * 
 * <p>
 * 同步部门信息<br />
 * </p>
 * 
 * @title TimerSyncDepartment.java
 * @package com.deppon.crm.module.scheduler.server.manager
 * @author 苏玉军
 * @version 0.1 2012-7-3
 */
public class TimerSyncDepartment {
	private ISyncDepartmentDao syncDepartmentDao;
	private static final String FILENAME = "lastUpdateTime_department.txt";
	private Logger log = Logger.getLogger(TimerSyncDepartment.class);
	private Date lastUpdateTime = new Date();
	// 定义异常信息内容
	private static final String PREFIX = "ORGANIZATION:";
	private static final String JOB_MSG_INIT = "组织同步时间记录文件读取错误";
	private static final String JOB_MSG_MKDIR = "组织同步时间记录文件创建失败";
	private static final String JOB_MSG_INSERT = "新增部门数据时不能为空";
	private static final String JOB_MSG_INFO = "更新部门信息失败，部门ID为：";
	private static final String PATH = System.getProperty("user.dir");
	private String lastTime = "";
	// 从文件中加载最后更新时间
	private static File file = new File(PATH, FILENAME);

	public ISyncDepartmentDao getSyncDepartmentDao() {
		return syncDepartmentDao;
	}

	public void setSyncDepartmentDao(ISyncDepartmentDao syncDepartmentDao) {
		this.syncDepartmentDao = syncDepartmentDao;
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

	/**
	 * 
	 * <p>
	 * 同步数据主方法<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-3 void
	 */
	public void syncDepartment() {
		this.init();
		List<OaDepartmentEntity> list = new ArrayList<OaDepartmentEntity>();
		if (lastUpdateTime == null) {
			Calendar calendar = Calendar.getInstance(Locale.CHINA);
			calendar.add(Calendar.MONTH, -1);
			lastUpdateTime = calendar.getTime();
		}
		list = this.queryListByUpdateTime(lastUpdateTime);
		int count = 0;
		boolean result = false;
		for (OaDepartmentEntity dept : list) {
			String id = dept.getId();
			count = syncDepartmentDao.countDepartmentByDeptSeq(id);
			// 已存在更新
			if (count > 0) {
				result = syncDepartmentDao.updateDept(dept);
			} else if (count == 0) {
				// 新增组织
				result = syncDepartmentDao.insertDept(dept);
			}
			if (result == false) {
				log.debug(PREFIX + JOB_MSG_INFO + id);
			}
		}
		// 将更新时间写入文件
		try {
			Date now = syncDepartmentDao.getSysDate();
			Long time =now.getTime();
			FileUtils.writeStringToFile(file, time.toString());
		} catch (IOException e) {
			log.debug("更新时间回写失败", e);
		}
	}

	/**
	 * 
	 * <p>
	 * 新增一条组织数据<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-4
	 * @param entity
	 *            void
	 */
	public boolean insertDepartment(OaDepartmentEntity entity) {
		if (entity == null) {
			log.debug(PREFIX + JOB_MSG_INSERT);
		}
		return syncDepartmentDao.insertDept(entity);
	}

	/**
	 * 
	 * <p>
	 * 查询出需要同步的数据<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-3
	 * @param date
	 * @return List<OaDepartmentEntity>
	 */
	private List<OaDepartmentEntity> queryListByUpdateTime(Date date) {
		return syncDepartmentDao.queryUpdateListByTime(date);
	}

	private int countRecord(Date date) {
		return syncDepartmentDao.countRecord(date);
	}
}
