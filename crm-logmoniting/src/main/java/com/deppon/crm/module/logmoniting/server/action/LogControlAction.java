/**
 * @Title: LogControlAction.java
 * @Package com.deppon.crm.module.logmoniting.server.action
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2013-6-27 下午4:32:40
 * @version V1.0
 */
package com.deppon.crm.module.logmoniting.server.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.logmoniting.server.manager.IExceptionErrorCodeManager;
import com.deppon.crm.module.logmoniting.server.manager.ILogInfoManager;
import com.deppon.crm.module.logmoniting.server.manager.ISynchroLogManager;
import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfoViewObject;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.ErrorCodeCondition;
import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.SynchronizeLogInfo;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * <p>
 * 日志监控Action
 * </p>
 * @title LogControlAction.java
 * @package com.deppon.crm.module.logmoniting.server.action
 * @author 唐亮
 * @version 0.1 2013-6-27
 */
public class LogControlAction extends AbstractAction{
	private BasicLoginfo basicLoginfo;
	private ILogInfoManager logInfoManager;
	
	//Action ID
	private String actionId;
	//总条数
	private Long totalCount;
	//分页
	private int start;
	private int limit;
	//日志查询条件
	private LogInfoCondition logInfoCondition;
	//action列表
	private List<ActionLogInfoViewObject> actionResultList = new ArrayList<ActionLogInfoViewObject>();
	//action请求时间、请求次数详细数据
	private List<LogStatistics> logStatisticsList = new ArrayList<LogStatistics>();
	//开始时间
	private Date beginDate; 
	//结束时间
	private Date endDate; 
	//统计频率
	private String frequency;
	//Action URL
	private String url;
	//logInfoId
	private String logInfoId;
	//前X位action数据源
	private List<Object> topChartDataList = new ArrayList<Object>();
	
	/**
	 * @author 陈爱春
	 */
	private IExceptionErrorCodeManager exceptionErrorCodeManager;
	//异常查询返回结果列表
	private List<ExceptionErrorCode> exceptionResultList = new ArrayList<ExceptionErrorCode>();
	//查询条件
	private ErrorCodeCondition errorCodeCondition;
	//新增ErrorCode实体（参数）
	private ExceptionErrorCode exceptionErrorCode;
	//异常eroorCode主键Id
	private String id;
	/**
	 * 同步日志管理
	 * @author 陈爱春
	 */
	private ISynchroLogManager synchroLogManager;
	private SynchronizeLogInfo synchronizeLogInfo;
	private List<SynchronizeLogInfo> synchronizeLogList = new ArrayList<SynchronizeLogInfo>();
	public SynchronizeLogInfo getSynchronizeLogInfo() {
		return synchronizeLogInfo;
	}
	public void setSynchronizeLogInfo(SynchronizeLogInfo synchronizeLogInfo) {
		this.synchronizeLogInfo = synchronizeLogInfo;
	}
	public List<SynchronizeLogInfo> getSynchronizeLogList() {
		return synchronizeLogList;
	}
	public void setSynchroLogManager(ISynchroLogManager synchroLogManager) {
		this.synchroLogManager = synchroLogManager;
	}
	/**
	 * 同步日志管理(查询)
	 * @author 陈爱春
	 */
	public String searchSynLogList(){
		synchronizeLogList = synchroLogManager.searchSynchroLogInfos(start, limit, synchronizeLogInfo);
		totalCount = (long)synchroLogManager.countSynchroData(synchronizeLogInfo);
		return SUCCESS;
	}
	/**
	 * 同步日志管理（修改）
	 * @author  陈爱春
	 * @return
	 */
	public String updateSynchronizeLog(){
		synchroLogManager.modifySynchroLog(synchronizeLogInfo);
		return SUCCESS;
	}
	/**
	 * 
	 * @Title: setAction
	 *  <p>
	 * @Description: Acttion设置
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-6-27
	 * @return void
	 * @throws
	 */
	public String logSetAction(){
		logInfoManager.saveBaseLogInfo(basicLoginfo);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Title: logUpdateAction
	 *  <p>
	 * @Description: Action修改
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-15
	 * @return String
	 * @throws
	 */
	public String logUpdateAction(){
		logInfoManager.updateBaseLogInfo(basicLoginfo);
		return SUCCESS;
	}
	/**
	 * 
	 * @Title: getActionInfo
	 *  <p>
	 * @Description: 点击修改Action时通过ActionID获取Action信息
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-16
	 * @return String
	 * @throws
	 */
	public String receiptActionInfo(){
		if(actionId != null){
			BasicLoginfo basicLoginfoData = new BasicLoginfo();
			basicLoginfoData.setId(actionId);
			basicLoginfo = logInfoManager.findBaseLogInfoById(basicLoginfoData);
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String deleteAction(){
		if(!StringUtils.isEmpty(url)){
			logInfoManager.removeLogInfoByUrl(url);
			return SUCCESS;
		}
		return ERROR;
	}
	/**
	 * 
	 * @Title: searchActionList
	 *  <p>
	 * @Description: 查询Action列表
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-17
	 * @return String
	 * @throws
	 */
	public String searchActionList(){
		if (logInfoCondition != null) {
			logInfoCondition.setLimit(limit);
			logInfoCondition.setStart(start);
			actionResultList = logInfoManager.integratedQueryInfosByCondition(logInfoCondition);
			totalCount = Long.valueOf( logInfoManager.getBasicLoginfos(logInfoCondition, null).size());
			return SUCCESS;
		}
		return ERROR;
	}
	/**
	 * 
	 * @Title: getLogStatisticsByCondition
	 *  <p>
	 * @Description: 获取Action的单位时间请求总次数/单位时间平均请求时间
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 * @return String
	 * @throws
	 */
	public String searchLogStatisticsByCondition(){
		LogInfoCondition logInfoCondition = new LogInfoCondition();
		logInfoCondition.setBeginDate(beginDate);
		logInfoCondition.setEndDate(endDate);
		logInfoCondition.setUri(url);
		logInfoCondition.setStatisticalFrequency(frequency);
		logInfoCondition.setBaseLoginfoId(logInfoId);
		logStatisticsList = logInfoManager.searchLogStatisticsByCondition(logInfoCondition);
		return SUCCESS;
	}
	/**
	 * 
	 * @Title: saerchChartTopData
	 *  <p>
	 * @Description: 获取前X位Action的数据
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-27
	 * @return String
	 * @throws
	 */
	public String saerchChartTopData(){
		if(logInfoCondition!=null){
			Map<String, List<LogStatistics>> topActionChartMap = logInfoManager.getTopActionLogInfoByCondition(logInfoCondition);
			if(topActionChartMap != null && topActionChartMap.size() > 0){
				for (String key  : topActionChartMap.keySet()) {
					topChartDataList.add(key);
					topChartDataList.add(topActionChartMap.get(key));
				}
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	
	
	/**
	 * @author 陈爱春
	 * Exception新增
	 */
	public String addExceptionErrorCode(){
		exceptionErrorCodeManager.saveExceptionErrorCode(exceptionErrorCode);
		return SUCCESS;
	}
	/**
	 * @author 陈爱春
	 * Exception删除
	 */
	public String delExceptionErrorCode(){
		String result = null;
		//判断Id是否为空
		if(id != null){
			exceptionErrorCodeManager.deleteExceptionErrorCodeById(id);
			result=  SUCCESS;
		}else{
			result = ERROR;
		}
			return result;
	}
	/**
	 * @author 陈爱春
	 * Exception信息修改
	 */
	public String updateExceptionErrorCode(){
		exceptionErrorCodeManager.updateExceptionErrorCode(exceptionErrorCode);
		return SUCCESS;
	}
	/**
	 * @author 陈爱春
	 * Exception查询列表
	 */
	public String searchExceptionList(){
		//判断是否有参数传入
			if(StringUtils.isEmpty(errorCodeCondition.getModuleName()) && StringUtils.isEmpty(errorCodeCondition.getErrorCode())
					&& StringUtils.isEmpty(errorCodeCondition.getExceptionInfo())){
				errorCodeCondition = new ErrorCodeCondition();
			}
		errorCodeCondition.setStart(start);
		errorCodeCondition.setLimit(limit);
		exceptionResultList = exceptionErrorCodeManager.searchExceptionErrorCodesByCondition(errorCodeCondition,"Whether_Paging");
		totalCount = Long.valueOf(exceptionErrorCodeManager.searchExceptionErrorCodesByCondition(errorCodeCondition, null).size());
		return SUCCESS;
	}
	
	/**
	 * @author 陈爱春
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * @author 陈爱春
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @author 陈爱春
	 * @return
	 */
	public List<ExceptionErrorCode> getExceptionResultList() {
		return exceptionResultList;
	}
	/**
	 * @author 陈爱春
	 * @param exceptionResultList
	 */
	public void setExceptionResultList(List<ExceptionErrorCode> exceptionResultList) {
		this.exceptionResultList = exceptionResultList;
	}
	/**
	 * @author 陈爱春
	 * @return
	 */
	public ExceptionErrorCode getExceptionErrorCode() {
		return exceptionErrorCode;
	}
	/**
	 * @author 陈爱春
	 * @param exceptionErrorCode
	 */
	public void setExceptionErrorCode(ExceptionErrorCode exceptionErrorCode) {
		this.exceptionErrorCode = exceptionErrorCode;
	}
	/**
	 * @author 陈爱春
	 * @param exceptionErrorCodeManager
	 */
	public void setExceptionErrorCodeManager(
			IExceptionErrorCodeManager exceptionErrorCodeManager) {
		this.exceptionErrorCodeManager = exceptionErrorCodeManager;
	}
	/**
	 * @author 陈爱春
	 * @return
	 */
	public ErrorCodeCondition getErrorCodeCondition() {
		return errorCodeCondition;
	}
	/**
	 * @author 陈爱春
	 * @param errorCodeCondition
	 */
	public void setErrorCodeCondition(ErrorCodeCondition errorCodeCondition) {
		this.errorCodeCondition = errorCodeCondition;
	}
	
	/**
	 * 
	 * Description:topChartDataList<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-30
	 */
	public List<Object> getTopChartDataList() {
		return topChartDataList;
	}

	/**
	 * 
	 * Description:logInfoId<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public String getLogInfoId() {
		return logInfoId;
	}

	/**
	 * 
	 * Description:logInfoId<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public void setLogInfoId(String logInfoId) {
		this.logInfoId = logInfoId;
	}

	/**
	 * 
	 * Description:beginDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 
	 * Description:beginDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 
	 * Description:endDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 
	 * Description:endDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 
	 * Description:frequency<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * 
	 * Description:frequency<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * 
	 * Description:url<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * Description:url<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * Description:logStatisticsList<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public List<LogStatistics> getLogStatisticsList() {
		return logStatisticsList;
	}

	/**
	 * 
	 * Description:logStatisticsList<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-25
	 */
	public void setLogStatisticsList(List<LogStatistics> logStatisticsList) {
		this.logStatisticsList = logStatisticsList;
	}

	/**
	 * 
	 * Description:start<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-24
	 */
	public int getStart() {
		return start;
	}

	/**
	 * 
	 * Description:start<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-24
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 
	 * Description:limit<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-24
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 
	 * Description:limit<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-24
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 
	 * Description:totalCount<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-24
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * Description:totalCount<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-24
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 
	 * Description:actionResultList<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-17
	 */
	public List<ActionLogInfoViewObject> getActionResultList() {
		return actionResultList;
	}

	/**
	 * 
	 * Description:actionResultList<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-17
	 */
	public void setActionResultList(List<ActionLogInfoViewObject> actionResultList) {
		this.actionResultList = actionResultList;
	}

	/**
	 * 
	 * Description:logInfoCondition<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-17
	 */
	public LogInfoCondition getLogInfoCondition() {
		return logInfoCondition;
	}

	/**
	 * 
	 * Description:logInfoCondition<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-17
	 */
	public void setLogInfoCondition(LogInfoCondition logInfoCondition) {
		this.logInfoCondition = logInfoCondition;
	}

	/**
	 * 
	 * Description:actionId<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-16
	 */
	public String getActionId() {
		return actionId;
	}

	/**
	 * 
	 * Description:actionId<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-16
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	/**
	 * 
	 * Description:logInfoManager<br />
	 * @author 唐亮
	 * @version 0.1 2013-6-28
	 */
	public void setLogInfoManager(ILogInfoManager logInfoManager) {
		this.logInfoManager = logInfoManager;
	}

	/**
	 * 
	 * Description:basicLoginfo<br />
	 * @author 唐亮
	 * @version 0.1 2013-6-27
	 */
	public BasicLoginfo getBasicLoginfo() {
		return basicLoginfo;
	}

	/**
	 * 
	 * Description:basicLoginfo<br />
	 * @author 唐亮
	 * @version 0.1 2013-6-27
	 */
	public void setBasicLoginfo(BasicLoginfo basicLoginfo) {
		this.basicLoginfo = basicLoginfo;
	}
	
}
