/**

 * @Title: SynchroLogManager.java
 * @Package com.deppon.crm.module.logmoniting.server.manager.impl
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-9-16 下午4:26:32
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.struts2.views.util.ContextUtil;

//import com.alibaba.fastjson.JSONWriter.Context;
import com.deppon.crm.module.logmoniting.server.manager.ISynchroLogManager;
import com.deppon.crm.module.logmoniting.server.service.ISynchroLogService;
import com.deppon.crm.module.logmoniting.server.utils.SynchronizeLogConValidator;
import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * TODO<br />
 * </p>
 * @title SynchroLogManager.java
 * @package com.deppon.crm.module.logmoniting.server.manager.impl
 * @author 唐亮
 * @version 0.1 2013-9-16
 */
public class SynchroLogManager implements ISynchroLogManager{
	
	private ISynchroLogService synchroLogService;
	private SynchronizeLogConValidator synchronizeLogConValidator;
	public void setSynchroLogService(ISynchroLogService synchroLogService) {
		this.synchroLogService = synchroLogService;
	}

	public void setSynchronizeLogConValidator(
			SynchronizeLogConValidator synchronizeLogConValidator) {
		this.synchronizeLogConValidator = synchronizeLogConValidator;
	}

	
	
	@Override
	public List<SynchronizeLogInfo> searchSynchroLogInfos(int start, int limit,
			SynchronizeLogInfo synchronizeLogInfo) {
		//校验查询条件是否有误
		synchronizeLogConValidator.ValidateSearchCondition(synchronizeLogInfo);
		return synchroLogService.searchDataSynchro(start, limit, synchronizeLogInfo);
	}
	
	@Override
	public void modifySynchroLog(SynchronizeLogInfo synchronizeLogInfo) {
		//校验传入的数据是否完整
		synchronizeLogConValidator.ValidateUpdateCondition(synchronizeLogInfo);
		Date date = new Date();
		synchronizeLogInfo.setModifyDate(date);
		synchronizeLogInfo.setModifyUser(UserContext.getCurrentUser().getId());
		synchroLogService.modifyDataSynchro(synchronizeLogInfo);
	}

	
	/* (非 Javadoc)
	* <p>Title: countSynchroData</p>
	* <p>Description: </p>
	* @param synchronizeLogInfo
	* @return
	* @see com.deppon.crm.module.logmoniting.server.manager.ISynchroLogManager#countSynchroData(com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo)
	*/
	@Override
	public int countSynchroData(SynchronizeLogInfo synchronizeLogInfo) {
		//校验查询条件是否有误
		synchronizeLogConValidator.ValidateSearchCondition(synchronizeLogInfo);
		return synchroLogService.countSynchroData(synchronizeLogInfo);
	}
}
