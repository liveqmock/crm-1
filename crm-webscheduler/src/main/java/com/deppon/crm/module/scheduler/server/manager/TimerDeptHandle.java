/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TimerDeptHandle.java
 * @package com.deppon.crm.module.scheduler.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-5-30
 */
package com.deppon.crm.module.scheduler.server.manager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao;
import com.deppon.crm.module.scheduler.shared.domain.LadingStation;
import com.opensymphony.util.FileUtils;

/**   
 * <p>
 * Description: 部门处理类，用于处理OA及ERP中部门信息的统一<br />
 * </p>
 * @title TimerDeptHandle.java
 * @package com.deppon.crm.module.scheduler.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-5-30
 */

public class TimerDeptHandle {
	public static final String SYSTEM_ADMIN = "86301";
	
	private static List<LadingStation> errorId = new ArrayList<LadingStation>();
	private static Logger logger = Logger.getLogger(TimerDeptHandle.class);
	// 数据加工类别：网点、部门
	private static String ERP_LADINGSTATION_DATA_TYPE 	= "ladingstation_data";
	private static String ERP_BUSINESSDEPT_DATA_TYPE 	= "BusinessDept_data";
	// MSG List
	private static String JOB_MSG_START 	= "网点数据加工任务开始:%s";			// 任务开始
	private static String JOB_MSG_END 		= "网点数据加工任务结束:%s 结果: %s";	// 任务结束
	private static String JOB_MSG_LS_COUNT 	= "获得网点数据总数 :%s";				// 获得网点数据总数 
	private static String JOB_MSG_BD_COUNT 	= "获得部门数据总数:%s";				// 获得部门数据总数
	
	private ILadingStationJobDao ladingStationJobDao;
	// 最后更新时间
	private static Date lastUpdTime;
	private static File file;
	private static String filename = "lastUpdTime_ladingstation.txt";
	static{
		String path = System.getProperty("user.dir");
		
//		// 初始化信息，jar包路径，记录文件名		
//		URL url = TimerDeptHandle.class.getProtectionDomain().getCodeSource().getLocation();
//		String path = url.getPath();

		file = new File(path, filename);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {				
			}
		}
		String time = FileUtils.readFile(file);		
		if (StringUtils.isEmpty(time)){
			// 无数据时，默认半年前
			lastUpdTime = DateUtils.add(new Date(), Calendar.MONTH, -6);
			FileUtils.write(file, String.valueOf(lastUpdTime.getTime()));
		}else{
			// 有数据时，作为最后更新时间
			lastUpdTime = new Date(Long.valueOf(time.trim()));
		}
	}

	public ILadingStationJobDao getLadingStationJobDao() {
		return ladingStationJobDao;
	}

	public void setLadingStationJobDao(ILadingStationJobDao ladingStationJobDao) {
		this.ladingStationJobDao = ladingStationJobDao;
	}
	
	/**
	 * <p>
	 * Description: ERP网点数据处理<br />
	 * 1.从ERP 网点表中查询出满足条件的记录(分为车队和非车队2中情况)
	 * 	根据查询结果中的ERPID去CRM网点表进行匹配，
	 *  对不存在的数据进行创建，
	 *  对已存在的数据的部分字段进行更新：
	 *  不更新字段：（IsOutfield, IsTransit, IsEnable, Area, IsOpen, 
	 *  		IsLeave, IsTeam）
	 * 2.从ERP 部门表中查询出满足条件的记录
	 *  根据查询结果中的ERPID去CRM网点表进行匹配，
	 *  对不存在的数据进行创建，
	 *  对已存在的数据的部分字段进行更新：
	 *  不更新字段：（DeptName, DetpAddress, Remark, Contact, 
 *  			IsHomeDelivery, IsSelfDelivery,IsArrive）
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-6-2
	 * void
	 */
	public void processLadingStation(){
		logger.debug(String.format(JOB_MSG_START, new Date()));
		List<LadingStation> addList = new ArrayList<LadingStation>();
		List<LadingStation> updList = new ArrayList<LadingStation>();
		
		// 取得最近更新的ERP部门信息(不包含车队)
		List<LadingStation> bdList = this.getERPBusinessDeptByDate();
		for (LadingStation ls : bdList){
			// 处理部门数据
			LadingStation tmp = this.getCRMLadingStation(ls.getErpId());
			if (tmp != null){
				updList.add(processLadingStationData(ls, ERP_BUSINESSDEPT_DATA_TYPE));	
			}else{
				addList.add(ls);				
			}
		}
		logger.debug(String.format(JOB_MSG_BD_COUNT, bdList.size()));

		// 保存第一部分数据并清空List数据
		boolean rs0 = this.saveLadingStationData(addList, updList);

		// 取得最近更新的ERP部门信息(不包含车队)
		List<LadingStation> carList = this.getERPBusinessDeptCarByDate();
		for (LadingStation ls : carList){
			// 处理部门数据
			LadingStation tmp = this.getCRMLadingStation(ls.getErpId());
			if (tmp != null){
				updList.add(processLadingStationData(ls, ERP_BUSINESSDEPT_DATA_TYPE));	
			}else{
				addList.add(ls);				
			}
		}
		logger.debug(String.format(JOB_MSG_BD_COUNT, carList.size()));

		// 保存第一部分数据并清空List数据
		boolean rs1 = this.saveLadingStationData(addList, updList);

		
		// 取得最近更新的ERP网点信息
		List<LadingStation> lsList = this.getERPLadingStationByDate();
		for (LadingStation ls : lsList){
			// 处理网点数据
			LadingStation tmp = this.getCRMLadingStation(ls.getErpId());
			if (tmp != null){
				updList.add(processLadingStationData(ls, ERP_LADINGSTATION_DATA_TYPE));	
			}else{
				addList.add(ls);
			}
		}
		logger.debug(String.format(JOB_MSG_LS_COUNT, lsList.size()));
		
		// 保存第二部分数据并清空List数据
		boolean rs2 = this.saveLadingStationData(addList, updList);
		
		
		
		
		if (rs0 && rs1 && rs2){
			// 操作成功后最后更新时间
			this.saveLastUpdTime();
		}
		logger.debug(String.format(JOB_MSG_END, new Date(), rs0 && rs1 && rs2));

	}
	
	/**
	 * <p>
	 * Description: 更新网点数据加工<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-6-2
	 * @param updList
	 * @param type
	 * void
	 */
	private LadingStation processLadingStationData(LadingStation ls, String type){
		// 设置相关数据为null时，该字段内容不会被更新至数据库中
		if (type.equals(ERP_LADINGSTATION_DATA_TYPE)){
			// 网点数据
			ls.setIsOutfield(null);
			ls.setIsTransit(null);
			ls.setIsEnable(null);
			ls.setArea(null);
			ls.setIsOpen(null);
			ls.setIsLeave(null);
			ls.setIsTeam(null);
		}else if (type.equals(ERP_BUSINESSDEPT_DATA_TYPE)){
			// 部门数据
			ls.setDeptName(null);
			ls.setDetpAddress(null);
			ls.setRemark(null);
			ls.setContact(null);
			ls.setIsHomeDelivery(null);
			ls.setIsSelfDelivery(null);
			ls.setIsArrive(null);
		}
		return ls;
	}
	
	/**
	 * <p>
	 * Description: 保存数据<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-6-2
	 * @param addList
	 * @param updList
	 * @return
	 * boolean
	 */
	@Transactional
	private boolean saveLadingStationData(List<LadingStation> addList, List<LadingStation> updList){
		boolean rs1 = true;
		boolean rs2 = true;
		for (LadingStation ls : addList){
			try {
				ls.setCreateUser(SYSTEM_ADMIN);
				ls.setModifyUser(SYSTEM_ADMIN);
				rs1 = this.ladingStationJobDao.createLadingStation(ls) && rs1;
			} catch (Exception e) {
				errorId.add(ls);
			}
		}
		for (LadingStation ls : updList){
			try {
				ls.setCreateUser(SYSTEM_ADMIN);
				ls.setModifyUser(SYSTEM_ADMIN);
				rs2 = this.ladingStationJobDao.updateLadingStation(ls) && rs2;
			} catch (Exception e) {
				errorId.add(ls);
			}
		}
		addList.clear();
		updList.clear();
		return rs1 && rs2;
	}
	
	/**
	 * <p>
	 * Description: 获取ERP网点数据<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-30
	 * List<LadingStation>
	 */
	private List<LadingStation> getERPLadingStationByDate(){
		// 根据日期，获取最新部门信息
		return ladingStationJobDao.queryERPLadingStationByDate(lastUpdTime);
	}
	
	/**
	 * <p>
	 * Description: 获取ERP部门信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-30
	 * List<LadingStation>
	 */
	private List<LadingStation> getERPBusinessDeptByDate(){
		return ladingStationJobDao.queryERPBusinessDeptByDate(lastUpdTime);		
	}
	
	/**
	 * <p>
	 * Description: 获取ERP部门信息-车队<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-30
	 * List<LadingStation>
	 */
	private List<LadingStation> getERPBusinessDeptCarByDate(){
		return ladingStationJobDao.queryERPBusinessDeptCarByDate(lastUpdTime);		
	}	
	
	/**
	 * <p>
	 * Description: 根据ERPID 查询网点<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-6-2
	 * @param id
	 * @return
	 * LadingStation
	 */
	private LadingStation getCRMLadingStation(String id){
		return ladingStationJobDao.queryCRMLadingStationByErpID(id);
	}
	/**
	 * <p>
	 * Description: 保存最后更新时间<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-30
	 * void
	 */
	private void saveLastUpdTime(){
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {				
			}
		}
		lastUpdTime = new Date();
		FileUtils.write(file, String.valueOf(lastUpdTime.getTime()));
	}
	
	public static void main(String[] args) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time=df.format(lastUpdTime);
		System.out.println(time);
		
	}
}
