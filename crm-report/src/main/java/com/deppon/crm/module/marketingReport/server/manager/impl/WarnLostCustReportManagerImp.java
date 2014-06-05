package com.deppon.crm.module.marketingReport.server.manager.impl;


import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.axis.utils.StringUtils;

import com.deppon.crm.module.marketing.server.utils.ExportExcel;
import com.deppon.crm.module.marketingReport.server.manager.IWarnLostCustReportManager;
import com.deppon.crm.module.marketingReport.server.service.IWarnLostCustReportService;
import com.deppon.crm.module.report.shared.domain.WarnLostCustDaily;
/**
 * @author zzw
 * @version 1.0
 * 2014-4-23
 * **/
public class WarnLostCustReportManagerImp  implements IWarnLostCustReportManager {

	private IWarnLostCustReportService warnReportService;
	
	public IWarnLostCustReportService getWarnReportService() {
		return warnReportService;
	}
	public void setWarnReportService(IWarnLostCustReportService warnReportService) {
		this.warnReportService = warnReportService;
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
		if(BeginTime==null||EndTime==null){
			return null;
		}
		//如果deptid为null则表示查询全国

		return warnReportService.getWarnCustReportDailyBatch(deptid, BeginTime, EndTime, start, limit, level);
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
		if(BeginTime==null||EndTime==null){
			return null;
		}
		//如果deptid为null则表示查询全国
		return warnReportService.getWarnLostReportTotal(deptid, BeginTime, EndTime, level);
	}
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
	public FileInputStream exportExcel(String deptid, Date BeginTime, Date EndTime,String level) throws Exception {
		String excelName=generateFileName();
		List<WarnLostCustDaily> warnLostList=getWarnCustReportDailyBatch( deptid, BeginTime, EndTime,0,0,level);
		File f=ExportExcel.resultSetToExcel(WarnLostCustDaily.FILEDDES, WarnLostCustDaily.FILEDSNAME, warnLostList, excelName, "sheet1");
		FileInputStream fi=new FileInputStream(f);
		return fi;
	}
	private String generateFileName() {
		return UUID.randomUUID().toString();
	}
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
	public int getWarnCustReportCount(String deptid,Date BeginTime,Date EndTime,String level){
		if(BeginTime==null||EndTime==null){
			return 0;
		}
		//如果deptid为null则表示查询全国

		return warnReportService.getWarnCustReportCount(deptid, BeginTime, EndTime, level);
	}
}
