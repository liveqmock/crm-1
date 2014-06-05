package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IWarnLostCustDao;
import com.deppon.crm.module.marketing.shared.WarnLostInfoFor360;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketing.shared.domain.WarnLostMailAccount;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**   
 * <p>
 * Description:流失预警客户DAO实现类<br />
 * </p>
 * @title WarnLostCustDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl
 * @author zzw
 * @version 2014-3-12
 */
public class WarnLostCustDao  extends iBatis3DaoImpl  implements IWarnLostCustDao {
	private static final  String NAMESPACE="com.deppon.crm.module.marketing.shared.domain.";
	private static  final String SEARCHWARNLOSTCUST="searchWarnLostCust";
	private static  final String SEARCHWARNLOSTCUSTBYCUSTINFO="searchWarnLostCustByCustInfo";
	private static  final String CREATEWARNLOST="createWarnLostList";
	private static  final String GETDETPGROUPFROMEXPRESS="getDetpGroupFromExpress";
	private static final String GETDEPTGROUPFROMWARNLOSTCUST="getDetpGroupFromWarnLost";
	private static final String UPDATEWARNINFO="update";
	private static final String MONITORWARNSTATUS="monitorWarnStatus";
	private static final String SEARCHWARNLOSTCUSTINFO="searchWarnLostCustInfo";
	private static final String SEARCHWARNLOSTCOUNT="searchWarnLostCustByCustCount";
	/**
     * <p>
     * Description:生成流失预警客户名单<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * @return
     */
	public void createWarnList() {
		this.getSqlSession().selectOne(NAMESPACE+CREATEWARNLOST);
	}
	/**
     * <p>
     * Description:根据部门获取流失客户(根据部门的等级)<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:deptStandardCode部门标杆编码，
     * 		level 部门等级：54321 按照营业部至本部的组织等级，
     * 		para：其他的参数，客户等级,客户类别等   暂时定为客户等级参数
     *      custNumber 客户编码
     * @return  List<WarnLostCust>
     */
	@SuppressWarnings("unchecked")
	public List<WarnLostCust> searchWarnLostCust(String deptStandardCode,
			String level, String[] para,Date beginTime,Date endTime) {
		deptStandardCode=deptStandardCode.trim();
		Map<String,Object> m=new HashMap<String,Object>();
		if(beginTime!=null&&endTime!=null){
			m.put("beginTime", beginTime);
			m.put("endTime", endTime);
		}
		m.put("deptStandardCode", deptStandardCode);
		m.put("level", level);
		m.put("para", para);
		return this.getSqlSession().selectList(NAMESPACE+SEARCHWARNLOSTCUST,m);
	}
	/**
     * <p>
     * Description:查看客户预警信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * ，
     *      custNumber 某客户编码，用去取此用户下的权限部门（目前仅限于快递大区）
     * 		custName   客户名称，
     *       beginTime　开始时间
     *       endTime 结束时间
     *       deptid  部门ID
     * 		
     * @return  WarnLostCust
     */
	@SuppressWarnings("unchecked")
	public List<WarnLostCust> searchWarnLostCust(String deptid, String custNumber,String custName, Date beginTime,Date endTime) {
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("custNumber", custNumber);
		m.put("custName", custName);	
		m.put("beginTime", beginTime);	
		m.put("deptid", deptid);	
		m.put("endTime", endTime);
		return  this.getSqlSession().selectList(NAMESPACE+SEARCHWARNLOSTCUSTBYCUSTINFO,m);
	}
	/**
     * <p>
     * Description:获取符合条件的部门集合以及部门负责人的邮箱信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:
     * 		level 部门等级：54321 按照营业部至本部的组织等级，
     * 		para：其他的参数，客户等级,客户类别等   暂时定为客户等级参数
     * 
     * @return  List<WarnLostCust>
     */
	@SuppressWarnings("unchecked")
	public List<WarnLostMailAccount> getDetpGroupFromWarnLost(String level, String[] para,String position) {
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("level", level);
		m.put("para", para);
		m.put("position", position);
		if(level==WarnLostCust.EXPRESSBIGAREA){
			return this.getSqlSession().selectList(NAMESPACE+GETDETPGROUPFROMEXPRESS,m);
		}else{
			return this.getSqlSession().selectList(NAMESPACE+GETDEPTGROUPFROMWARNLOSTCUST,m);
		}
	}
	/**
     * <p>
     * Description:手动更新客户预警客户信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:
     * 		WarnStatusUpdateInfo 更新的详细信息
     * return void
     * @return  List<WarnLostCust>
     */
	public void updateWarnInfo(WarnStatusUpdateInfo info) {
		this.getSqlSession().selectOne(NAMESPACE+UPDATEWARNINFO, info);
	}
	/**
     * <p>
     * Description:查看某客户的预警信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     *      部门标杆编码，
     *      custNumber 客户编码
     * 		fcustId 客户id，
     * @return  WarnLostCust
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public WarnStatusUpdateInfo searchWarnLostCustInfo(int fcustId,String custNumber) {
		Map map=new HashMap();
		map.put("custId", fcustId);
		map.put("custNumber", custNumber);
		return (WarnStatusUpdateInfo) this.getSqlSession().selectOne(NAMESPACE+SEARCHWARNLOSTCUSTINFO,map);
	}
	/**
     * <p>
     * Description:自动更新客户的预警信息以及预发货时限<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-27

     * @return  
     */
	public void updateCustWarnStatusAndPreSendTime() {
		this.getSqlSession().selectOne(NAMESPACE+MONITORWARNSTATUS);
	}/**
     * <p>
     * Description:查看客户预警信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * ，
     *      custNumber 某客户编码，用去取此用户下的权限部门（目前仅限于快递大区）
     * 		custName   客户名称，
     *       beginTime　开始时间
     *       endTime 结束时间
     *       deptid  部门ID
     *       分页参数
     * 		int StartRecord 
     *      int limitRecord,
     *      int endRecord
     * @return  WarnLostCust
     */
	@Override
	public List<WarnLostCust> searchWarnLostCust(String deptid,
			String custNumber, String custName, Date beginTime, Date endTime,
			int startRecord, int limitRecord) {
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("custNumber", custNumber);
		m.put("custName", custName);	
		m.put("beginTime", beginTime);	
		m.put("deptid", deptid);	
		m.put("endTime", endTime);
		if(startRecord>0||limitRecord>0){//进行分页
			RowBounds rb = new RowBounds(startRecord, limitRecord);
			return  this.getSqlSession().selectList(NAMESPACE+SEARCHWARNLOSTCUSTBYCUSTINFO,m,rb);
		}else{//如果没有分页则查所有
			return  this.getSqlSession().selectList(NAMESPACE+SEARCHWARNLOSTCUSTBYCUSTINFO,m);
		}
	}
	/**
     * <p>
     * Description:查看客户预警的总数<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * ，
     *      custNumber 某客户编码，用去取此用户下的权限部门（目前仅限于快递大区）
     * 		custName   客户名称，
     *       beginTime　开始时间
     *       endTime 结束时间
     *       deptid  部门ID
     * @return  int 查询总数
     */
	@Override
	public int searchWarnLostCustCount(String deptid, String custNumber,
			String custName, Date beginTime, Date endTime) {
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("custNumber", custNumber);
		m.put("custName", custName);	
		m.put("beginTime", beginTime);	
		m.put("deptid", deptid);	
		m.put("endTime", endTime);
		int res=(Integer)this.getSqlSession().selectOne(NAMESPACE+SEARCHWARNLOSTCOUNT,m);
		return res;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IWarnLostCustDao#searchWarnLostCustFor360View(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WarnLostInfoFor360> searchWarnLostCustFor360View(String deptid,
			String custNumber, String custName, Date beginTime, Date endTime) {
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("custNumber", custNumber);
		m.put("custName", custName);	
		m.put("beginTime", beginTime);	
		m.put("deptid", deptid);	
		m.put("endTime", endTime);
		return  this.getSqlSession().selectList(NAMESPACE+"searchWarnLostCustFor360View",m);
	}
}
