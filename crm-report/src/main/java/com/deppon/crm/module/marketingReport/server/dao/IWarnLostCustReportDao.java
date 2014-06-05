package com.deppon.crm.module.marketingReport.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.report.shared.domain.WarnLostCustDaily;
import com.deppon.crm.module.report.shared.domain.WarnLostCustMonthly;
/**
 *  
 * <p>
 * 流失预警报表<br/>
 * </p>
 * 
 * @author zzw
 * @version 0.1 2014-4-23
 * @param 
 * @return
 */
public interface IWarnLostCustReportDao {
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
	 *  level:部门等级
	 * @return List<WarnLostCustDaily>  日统计数据
	 */
	public List<WarnLostCustDaily> getWarnCustReportDailyBatch(String deptid,Date BeginTime,Date EndTime,int start,int limit,String level);
	/**
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
	public int getWarnCustReportCount(String deptid,Date BeginTime,Date EndTime,String level);

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
	 * @return List<WarnLostCustDaily>  日统计数据
	 */
	public WarnLostCustDaily getWarnLostReportTotal(String deptid,
			Date BeginTime, Date EndTime,String level);
}
