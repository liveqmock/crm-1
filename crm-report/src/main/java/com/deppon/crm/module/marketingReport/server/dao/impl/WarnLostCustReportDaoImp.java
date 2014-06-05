package com.deppon.crm.module.marketingReport.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.marketingReport.server.dao.IWarnLostCustReportDao;
import com.deppon.crm.module.report.shared.domain.WarnLostCustDaily;
import com.deppon.crm.module.report.shared.domain.WarnLostCustMonthly;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * WarnLostCustReportDaoImp 流失预警报表DAO
 * getWarnCustReport      导出部门各种统计方法
 * getWarnCustReportDaily 流失预警日报表方法
 * getWarnCustReportMonthly 流失预警月报表方法
 * 
 * @author zzw
 * @version 1.0
 * 2014-4-23
 * **/
public class WarnLostCustReportDaoImp extends iBatis3DaoImpl implements IWarnLostCustReportDao {
	private static final String SPACENAME="com.deppon.crm.module.marketingReport.shared.domain.WarnLostCustPub.";
	private static final String GETWARNCUSTREPORTDAILY="getReportWarnLostByDaily";
	private static final String GETWARNCUSTREPORTDAILYTOTAL="getReportWarnLostByDailyTotal";
	private static final String GETWARNCUSTLCOUNT="getReportWarnLostByDailyCount";
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
	 * @return List<WarnLostCustDaily>  月统计数据
	 */
	@Override
	public List<WarnLostCustDaily> getWarnCustReportDailyBatch(String deptid,
			Date BeginTime, Date EndTime, int start, int limit, String level) {
		Map map=new HashMap();
		map.put("deptid", deptid);
		map.put("BeginTime", BeginTime);
		map.put("EndTime", EndTime);
		map.put("level", level);
		List<WarnLostCustDaily> warnLostCustDaily;//=new ArrayList<WarnLostCustDaily>();
		if(start<=0&&limit<=0){
			warnLostCustDaily=this.getSqlSession().selectList(SPACENAME+GETWARNCUSTREPORTDAILY, map);
		}
		else{
			RowBounds rb = new RowBounds(start, limit);
			warnLostCustDaily=this.getSqlSession().selectList(SPACENAME+GETWARNCUSTREPORTDAILY, map,rb);
		}
		if(warnLostCustDaily==null||warnLostCustDaily.size()<=0){
			return null;
		}
		return warnLostCustDaily;
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
		Map map=new HashMap();
		map.put("deptid", deptid);
		map.put("BeginTime", BeginTime);
		map.put("EndTime", EndTime);
		map.put("level", level);
		WarnLostCustDaily total=(WarnLostCustDaily) this.getSqlSession().selectOne(SPACENAME+GETWARNCUSTREPORTDAILYTOTAL, map);
		return  total;
	}
	/**
	 *  
	 * <p>
	 *生成日报表数据总数<br/>
	 * </p>
	 * 
	 * @author zzw
	 * @version 0.1 2014-4-23
	 * @param deptid 部门ID  BeginTime 查询开始时间 EndTime 查询结束时间
	 *  level 部门等级
	 * @return int  统计数据总数
	 */
	@Override
	public int getWarnCustReportCount(String deptid, Date BeginTime,
			Date EndTime, String level) {
		Map map=new HashMap();
		map.put("deptid", deptid);
		map.put("BeginTime", BeginTime);
		map.put("EndTime", EndTime);
		map.put("level", level);
		Integer res=(Integer)this.getSqlSession().selectOne(SPACENAME+GETWARNCUSTLCOUNT,map);
		if(res==null){
			return 0;
		}
		return res;
	}
}
