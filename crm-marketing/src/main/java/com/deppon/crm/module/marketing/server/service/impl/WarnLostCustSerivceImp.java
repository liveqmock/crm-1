package com.deppon.crm.module.marketing.server.service.impl;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IWarnLostCustDao;
import com.deppon.crm.module.marketing.server.service.IWarnLostCustSerivce;
import com.deppon.crm.module.marketing.shared.WarnLostInfoFor360;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketing.shared.domain.WarnLostMailAccount;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
/**   
 * <p>
 * Description:流失预警客户service<br />
 * </p>
 * @title IWarnLostCustDao.java
 * @package com.deppon.crm.module.marketing.server.DAO 
 * @author zzw
 * @version 2014-3-11
 */
public class WarnLostCustSerivceImp implements IWarnLostCustSerivce{
	
	private IWarnLostCustDao warnLostCustDao;
	/**
     * <p>
     * Description:生成流失预警客户名单<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * @return
     */
	public void createWarnList() {
		warnLostCustDao.createWarnList();
	}

	/**
     * <p>
     * Description:根据部门获取流失客户(根据部门的等级)<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:deptStandardCode部门标杆编码，
     * 		level 部门等级：54321 按照营业部至本部的组织等级，
     * 		para：其他的参数，客户等级,客户类别等   暂时定为客户等级参数 String[]
     *  	beginTime   查询开始时间<createTime
     *      endTime     查询结束时间>createTime
     * @return  List<WarnLostCust>
     */
	public List<WarnLostCust> searchWarnLostCust(String deptStandardCode, String level, String[] para, Date beginTime,
			Date endTime) {
		return warnLostCustDao.searchWarnLostCust(deptStandardCode, level, para, beginTime, endTime);
	}
	/**
     * <p>
     * Description:获取符合条件的部门集合<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:
     * 		level 部门等级：54321 按照营业部至本部的组织等级，
     * 		para：其他的参数，客户等级,客户类别等   暂时定为客户等级参数  String[]
     *      position 职位信息
     * @return  List<WarnLostCust>
     */
	public List<WarnLostMailAccount> getDetpGroupFromWarnLost(String level, String[] para,String position) {
		return warnLostCustDao.getDetpGroupFromWarnLost(level, para,position);
	}
	
	public IWarnLostCustDao getWarnLostCustDao() {
		return warnLostCustDao;
	}

	public void setWarnLostCustDao(IWarnLostCustDao warnLostCustDao) {
		this.warnLostCustDao = warnLostCustDao;
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
	public List<WarnLostCust> searchWarnLostCust(String deptid, String custNumber,String custName, Date beginTime,Date endTime) {
		return warnLostCustDao.searchWarnLostCust(deptid,custNumber, custName,beginTime,endTime);
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
	public void updateWarnInfo(WarnStatusUpdateInfo info){
		
		warnLostCustDao.updateWarnInfo(info);
	}
	/**
     * <p>
     * Description:查看某客户的预警信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:deptStandardCode部门标杆编码，
     *      custNum 某客户编码
     * 		fcustId 客户id，
     * 		
     * @return  WarnLostCust
     */
	public WarnStatusUpdateInfo searchWarnLostCustInfo(int fcustId,String custNum) {
		return warnLostCustDao.searchWarnLostCustInfo(fcustId,custNum);
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
		warnLostCustDao.updateCustWarnStatusAndPreSendTime();
	}

	@Override
	public List<WarnLostCust> searchWarnLostCust(String deptid,
			String custNumber, String custName, Date beginTime, Date endTime,
			int StartRecord, int limitRecord) {
		return warnLostCustDao.searchWarnLostCust(deptid, custNumber, custName, beginTime, endTime, StartRecord, limitRecord);
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
	public int searchWarnLostCustCount(String deptid, String custNumber,String custName, Date beginTime,Date endTime){
		
		return warnLostCustDao.searchWarnLostCustCount(deptid, custNumber, custName, beginTime, endTime);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IWarnLostCustSerivce#searchWarnLostCustFor360View(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<WarnLostInfoFor360> searchWarnLostCustFor360View(String deptid,
			String custNumber, String custName, Date beginTime, Date endTime) {
		return warnLostCustDao.searchWarnLostCustFor360View(deptid,custNumber,custName,beginTime,endTime);
	}
}
