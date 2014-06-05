package com.deppon.crm.module.marketingReport.server.manager;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.report.shared.domain.WarnLostCustDaily;

/**
 *  
 * <p>
 * 流失预警报表Manager<br/>
 * </p>
 * 
 * @author zzw
 * @version 0.1 2014-4-23
 * @param 
 * @return
 */
public interface IWarnLostCustReportManager {
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
	 * @return List<WarnLostCustDaily>  月统计数据
	 */
	public List<WarnLostCustDaily> getWarnCustReportDailyBatch(String deptid,Date BeginTime,Date EndTime,int start,int limit,String level);
	
	/**
	 *  
	 * <p>
	 *导出日报表数据<br/>
	 * </p>
	 * 
	 * @author zzw
	 * @version 0.1 2014-4-23
	 * @param deptid 部门ID  BeginTime 查询开始时间 EndTime 查询结束时间
	 * excelName 导出文件名
	 *  
	 * @return FileInputStream  导出的文件流
	 */
	FileInputStream exportExcel(String deptid, Date BeginTime, Date EndTime,String level) throws Exception; 

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
			Date BeginTime, Date EndTime,String level);
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

}
