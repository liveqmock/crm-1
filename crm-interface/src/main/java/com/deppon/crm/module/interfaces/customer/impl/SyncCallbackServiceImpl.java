package com.deppon.crm.module.interfaces.customer.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.deppon.crm.module.client.common.domain.WaitCustomerLogInfo;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.customer.dao.ISyncCallbackDao;
import com.deppon.crm.module.interfaces.customer.ISyncCallbackService;

/**
 * @作者：罗典
 * @时间：2012-7-23
 * @描述 数据同步错误接收回调接口
 */
public class SyncCallbackServiceImpl implements ISyncCallbackService {

	private ISyncCallbackDao syncCallbackDao;

	/**
	 * @作者：罗典
	 * @时间：2012-7-23
	 * @描述 数据同步错误处理
	 * @param requestId
	 *            数据同步请求标识（ESBJsonRequest.requestId）
	 * @param targetSystem
	 *            数据接收方（如果多个系统出错会分成多条发送）
	 * @param level
	 *            错误级别（100：业务异常；300：请求后端服务超时；500：网络错误；900：请求不匹配需改程序；）
	 * @param errorMsg
	 *            错误信息
	 */
	@Override
	public void handle(String requestId, String targetSystem, int level,
			String errorMsg)  throws CrmBusinessException{
		WaitCustomerLogInfo waitCustomerLogInfo = syncCallbackDao
				.queryWaitCustomerLogInfo(requestId);
		if (waitCustomerLogInfo != null) {
			// 超过三次未成功的数据，需人工干预处理
			int errNum = waitCustomerLogInfo.getERR_NUMBER().intValue();
			WaitCustomerLogInfo logInfo = new WaitCustomerLogInfo();
			logInfo.setHANDLE_TYPE("A");
			logInfo.setSTATUS("U");
			logInfo.setTRANSACTION_NO(requestId);
			logInfo.setERROR_CODE(level + "");
			logInfo.setERR_NUMBER(new BigDecimal(errNum + 1));
			//logInfo.setERR_TIME(new Date());
			logInfo.setERR_DESC(errorMsg);
			//logInfo.setTARGET(targetSystem);
			if("WDGH".equalsIgnoreCase(targetSystem)){
				logInfo.setERR_SYS(logInfo.getERR_SYS()|Constant.WDGH_CODE);
			}else if("FOSS".equalsIgnoreCase(targetSystem)){
				logInfo.setERR_SYS(logInfo.getERR_SYS()|Constant.FOSS_CODE);
			}else if("CC".equalsIgnoreCase(targetSystem)){
				logInfo.setERR_SYS(logInfo.getERR_SYS()|Constant.CC_CODE);
			}
			
			if(errorMsg.length()>333){
				logInfo.setERR_DESC(errorMsg.substring(0, 333));
			}
			if (errNum > 3 || level == 100 || level == 900) {
				logInfo.setHANDLE_TYPE("M");
			}
			syncCallbackDao.updateWaitCustomerLogInfo(logInfo);
		}else{
			throw new CrmBusinessException("0034");
		}
	}

	public ISyncCallbackDao getSyncCallbackDao() {
		return syncCallbackDao;
	}

	public void setSyncCallbackDao(ISyncCallbackDao syncCallbackDao) {
		this.syncCallbackDao = syncCallbackDao;
	}

}
