package com.deppon.crm.module.marketing.server.manager;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.shared.WarnLostInfoFor360;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketing.shared.domain.WarnLostMailAccount;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;

/**   
 * <p>
 * Description:流失预警客户<br />
 * </p>
 * @title IWarnLostCustDao.java
 * @package com.deppon.crm.module.marketing.server.DAO 
 * @author zzw
 * @version 2014-3-11
 */
public interface IWarnLostCustManager {
	/**
     * <p>
     * Description:生成流失预警客户名单<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * @return
     */
	public void createWarnList();
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
	public List<WarnLostCust> searchWarnLostCust(String deptStandardCode,
			 String level, String[] para, Date beginTime,
			Date endTime);	/**
     * <p>
     * Description:获取符合条件的部门集合<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:
     * 		level 部门等级：54321 按照营业部至本部的组织等级，
     * 		para：其他的参数，客户等级,客户类别等   暂时定为客户等级参数
     * @return  List<WarnLostCust>
     */
	public List<WarnLostMailAccount>  getDetpGroupFromWarnLost(String level,
			String[] para,String position);

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
	public List<WarnLostCust> searchWarnLostCust(String deptid, String custNumber,String custName, Date beginTime,Date endTime);
	
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
     *       分页参数
     * 		int StartRecord 
     *      int limitRecord,
     * @return  WarnLostCust
     */
	public List<WarnLostCust> searchWarnLostCust(String deptid, String custNumber,String custName, Date beginTime,Date endTime,int StartRecord, int limitRecord);
	
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
	public void updateWarnInfo(WarnStatusUpdateInfo info);
	/**
     * <p>
     * Description:查看某客户的预警信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * para:deptStandardCode部门标杆编码，
     *      empCode 某用户编码，用去取此用户下的权限部门（目前仅限于快递大区）
     * 		custName 客户名称，
     * 		
     * @return  WarnLostCust
     */
	public WarnStatusUpdateInfo searchWarnLostCustInfo(int fcustId,String custNum);
	/**
     * <p>
     * Description:自动更新客户的预警信息以及预发货时限<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-27

     * @return  
     */
	public void updateCustWarnStatusAndPreSendTime();
	/**
     * <p>
     * Description: 流失客户明细数据导出<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-4-27
       @param String deptid 部门ID, Date beginTime 开始时间,Date endTime 结束时间
     * @return  
     */
	public FileInputStream searchWarnLostCust(String deptid, Date beginTime,Date endTime) throws Exception;
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
	public int searchWarnLostCustCount(String deptid, String custNumber,String custName, Date beginTime,Date endTime);

	/**
	 * 
	 * <p>
	 * 提供给360视图的查询客户流失预警信息<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月29日
	 * @param deptid
	 * @param custNumber
	 * @param custName
	 * @param beginTime
	 * @param endTime
	 * @return
	 * List<WarnLostInfoFor360>
	 */
	List<WarnLostInfoFor360> searchWarnLostCustFor360View(String deptid, String custNumber, String custName, Date beginTime, Date endTime);
}
