package com.deppon.crm.module.marketingReport.server.service.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketingReport.server.dao.IWarnLostCustReportDao;
import com.deppon.crm.module.marketingReport.server.service.IWarnLostCustReportService;
import com.deppon.crm.module.report.shared.domain.WarnLostCustDaily;
/**
 * WarnLostCustReportServiceImp 流失预警报表Service
 * getWarnCustReportDaily 流失预警日报表方法
 * getWarnCustReportMonthly 流失预警月报表方法
 * 
 * @author zzw
 * @version 1.0
 * 2014-4-23
 * **/
public class WarnLostCustReportServiceImp implements IWarnLostCustReportService {
	private IWarnLostCustReportDao warnReportDao;
	
	public IWarnLostCustReportDao getWarnReportDao() {
		return warnReportDao;
	}
	public void setWarnReportDao(IWarnLostCustReportDao warnReportDao) {
		this.warnReportDao = warnReportDao;
	}
	/**
	 *  
	 * <p>
	 *生成日报表数据<br/>
	 * </p>
	 * 
	 * @author zzw
	 * @version 0.1 2014-4-23
	 * @param deptid 部门ID  BeginTime 查询开始时间 EndTime 查询结束时间
	 *  start, limit 分页参数
	 *  level 部门等级
	 * @return List<WarnLostCustDaily>  月统计数据
	 */
	@Override
	public List<WarnLostCustDaily> getWarnCustReportDailyBatch(String deptid,
			Date BeginTime, Date EndTime, int start, int limit, String level) {
		return warnReportDao.getWarnCustReportDailyBatch(deptid, BeginTime, EndTime, start, limit, level);
	}
	/**
	 *  
	 * <p>
	 *生成日报表数据总统计<br/>
	 * </p>
	 * 
	 * @author zzw
	 * @version 0.1 2014-4-23
	 * @param deptid 部门ID  BeginTime 查询开始时间 EndTime 查询结束时间
	 *  level 部门等级
	 * @return List<WarnLostCustDaily>  月统计数据
	 */
	public WarnLostCustDaily getWarnLostReportTotal(String deptid,
			Date BeginTime, Date EndTime,String level){
		return warnReportDao.getWarnLostReportTotal(deptid, BeginTime, EndTime, level);
	}/**
	 *  
	 * <p>
	 *查询日报表数据总数<br/>
	 * </p>
	 * 
	 * @author zzw
	 * @version 0.1 2014-4-23
	 * @param deptid 部门ID  BeginTime 查询开始时间 EndTime 查询结束时间
	 *  start, limit 分页参数
	 *  level:部门等级
	 * @return int 日统计数据总数
	 */
	@Override
	public int getWarnCustReportCount(String deptid, Date BeginTime,
			Date EndTime, String level) {
		return warnReportDao.getWarnCustReportCount(deptid, BeginTime, EndTime,level);
	}


}
