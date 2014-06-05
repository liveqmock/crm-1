package com.deppon.foss.framework.server.components.jobgrid.dao;

import java.util.List;

import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;
/**
 * 
 * <p>
 * Description:消息管理<br />
 * </p>
 * @title JobMessageDao.java
 * @package com.deppon.foss.framework.server.components.jobgrid.dao 
 * @author 侯斌飞
 * @version 0.1 2013-2-25
 */
public interface JobMessageDao {
	/**
	 * 
	 * <p>
	 * Description:消息管理 - 新增<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jm
	 * void
	 */
	void insertJobMessage(JobMessage jm);
	/**
	 * 
	 * <p>
	 * Description:消息管理 - 修改<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2013-2-25
	 * @param messageId
	 * void
	 */
	void updateJobMessageSend(String messageId);
    /**
     * 
     * <p>
     * Description:消息管理 - 查询集合<br />
     * </p>
     * @author 侯斌飞
     * @version 0.1 2013-2-25
     * @return
     * List<JobMessage>
     */
	List<JobMessage> queryAllUnsend();

}
