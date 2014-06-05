package com.deppon.crm.module.logmoniting.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfoViewObject;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;

public interface ILogInfoManager {

	/**
	 * @Description:action 基础设置（保存）<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 * @param basicLoginfo
	 * void
	 */
	public void saveBaseLogInfo(BasicLoginfo basicLoginfo);

	/**
	 * @Description:通过ID去查询BasicLoginfo基础信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * @param basicLoginfo
	 * void
	 */
	public BasicLoginfo findBaseLogInfoById(BasicLoginfo basicLoginfo);
	
	/**
	 * @Description:action 基础设置（修改）<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 * @param basicLoginfo
	 * void
	 */
	public void updateBaseLogInfo(BasicLoginfo basicLoginfo);
	
	/**
	 * @Description:基础资料 action查询<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 * @param condition
	 * @return List<BasicLoginfo>
	 */
	public List<ActionLogInfoViewObject> integratedQueryInfosByCondition(LogInfoCondition condition);
	
	/**
	 * @Description:通过条件查询基础资料action的信息<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 * @param condition 查询条件
	 * @param type 是否分页的标示（Whether_Paging 分页--null为不分页）
	 * @return List<BasicLoginfo>
	 */
	public List<BasicLoginfo> getBasicLoginfos(LogInfoCondition condition,String type);
	
	/**
	 * @Description:查询action请求的详细请求信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 * @param condition
	 * @return List<LogStatistics>
	 */
	public List<LogStatistics> searchLogStatisticsByCondition(LogInfoCondition condition);

	/**
	 * @Description:提供定时器调用---1小时执行一次，记录和统计日志信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * void
	 */
	public void assemblingLogInfoDataForOneHour();
	
	/**
	 * @Description: 凌晨1点半
	 * 根据每天统计的前十位的Action请求次数和前一天前十位的Action请求次数进行对比，
	 * 如果两天前十位的Action名称不一样，则以邮件的方式通知开发人员确认是否存在问题。<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * void
	 */
	public void compareTheTopTenActionLogInfoByOneDay();
	
	/**
	 * @Description: 
	 * 每天凌晨1点跑
	 * 前提是黑名单
	 * 每天根据每个Action设置的请求时间的最大值，和请求次数的最大值，
	 * 对每天汇总的所有请求的请求时间和请求次数的统计进行对比，
	 * 如果发现请求次数或者请求时长超过设置的最大值，
	 * 则以邮件的形式通知开发人员说明Action请求时间过长或者请求次数过多。<br />
	 * @author CoCo
	 * @version 0.1 2013-7-19
	 * void
	 */
	public void compareActionAverageContrastByOneDay();
	
	/**
	 * @Description:根据条件 中的统计频率和统计方式以及时间 或者模块名称 查询前面的action请求信息<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 * @param logInfoCondition
	 * @return Map<String,List<LogStatistics>>
	 */
	public Map<String, List<LogStatistics>> getTopActionLogInfoByCondition(LogInfoCondition logInfoCondition);

	/**
	 * @Description:清除日志(请求日志和系统日志)--提供定时器调用,一周一次<br />
	 * @author CoCo
	 * @version 0.1 2013-7-26
	 * void
	 */
	public void removeLogForOneWeek();
	
	/**
	 * @Description:定时器执行。异常编码的收集，每半天执行一次，并相应的发送邮件
	 *  根据收集的非正常的业务异常和系统日志记录的日志中的异常进行比对，若发
		发现日志中存在这个异常，以邮件的方式通知开发人员进行问题跟踪及解决。
		把异常出现的次数，以及出现异常的详细信息一起发送。
		目前未实现：将出现的异常信息一起发送
	 * <br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 * void
	 */
	public void completeExceptionErrorCode();
	
	/**
	 * @Description:日志基础设置删除,以及统计信息删除<br />
	 * @author CoCo
	 * @version 0.1 2013-8-20
	 * @param url void
	 */
	public void removeLogInfoByUrl(String url);
}
