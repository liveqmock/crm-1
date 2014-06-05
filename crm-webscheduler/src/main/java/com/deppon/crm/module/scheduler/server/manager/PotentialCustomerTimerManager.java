package com.deppon.crm.module.scheduler.server.manager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.map.IMapOperate;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.scheduler.server.service.ITimerPotentialCustomerService;

public class PotentialCustomerTimerManager {
	private ITimerPotentialCustomerService timerPotentialCustomerService;
	private IMapOperate mapOperate;
	private IBaseDataManager baseDataManager;
	private ILadingstationDepartmentManager ladingstationDepartmentManager; 
	
	Log logger = LogFactory.getLog(PotentialCustomerTimerManager.class);
	
	public void timerArrivalCustomerList(Date date) {
		//读取昨天的数据
		date.setDate(date.getDate()-1);
		UserUtil.setUserToAdmin();
		
		int potentialCustCount = 0;
		
		/**
		 * @修改描述：分为12次进行到达客户生成的处理，每次处理两个小时的数据
		 * @author 李盛
		 * @since 2012-10-10
		 */
		for(int i = 1; i <= 12; i++){
			
			try{
				List<PotentialCustomer> tempList = null;
				// 查询间隔两个小时的到达客户中间表数据
				tempList = timerPotentialCustomerService.getArrivalCustomerList(date,i);
				
				List<PotentialCustomer> potentialList = new ArrayList<PotentialCustomer>();
				
				int count = 0;
				logger.info("第" + i + "次处理" + tempList.size() + "条数据");
				for (PotentialCustomer potentialCustomer : tempList) {
					//如果地址为空直接跳过
					if(ValidateUtil.objectIsEmpty(potentialCustomer.getAddress())){
						continue;
					}
					//调用地图接口通过地址找得到对应部门ID
					String deptId = getDeptId(potentialCustomer.getAddress());
					if(deptId == null){
						continue;
					}
					potentialCustomer.setDeptId(deptId);
					
					//得到所属部门城市
					BussinessDept dept = ladingstationDepartmentManager.getBusDeptById(deptId);
					
					if(dept != null && dept.getCity() != null){
						String city = dept.getCity().getId();
						String cityName = dept.getCity().getName();
						potentialCustomer.setCity(city);
//						potentialCustomer.setCityName(cityName);
					}
					
					potentialList.add(potentialCustomer);
					
					count++;
					logger.info("第" + i + "次处理到" + count + "条数据");
				}
				
				//批量更新中间表中的部门ID和城市ID
				timerPotentialCustomerService.batchUpdateWaybillClient(potentialList);
				logger.info("第" + i + "次批量更新中间表部门信息完成");
				//根据中间关系表中数据进行批量插入潜在客户信息
				potentialCustCount += timerPotentialCustomerService.createPotentialCustByWaybillClient(date, i);
				logger.info(date.toLocaleString() + "第"+ i + "次到达客户生成潜在客户生成了"+potentialCustCount+"条数据");
			}catch(Exception e){
				// 把e.printStackTrace转换成字符串
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				logger.error(writer);
			}
		}
		
		logger.info(date.toLocaleString() + "到达客户生成潜在客户生成了"+potentialCustCount+"条数据");
	}
	
	
	/**
	 * 
	 * <p>根据地址，得到部门ID<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-29
	 * @param address
	 * @return
	 * String
	 */
	private String getDeptId(String address){
		UserUtil.setUserToAdmin();
		try {
			String code = mapOperate.findDeptCodeByAddress(address);
			if(ValidateUtil.objectIsEmpty(code)){
				return null;
			}
			Department dept  = baseDataManager.getDeptByStandardCode(code);
			if(ValidateUtil.objectIsEmpty(dept)){
				return null;
			}
			return  dept.getId();
		} catch (CrmBusinessException e) {
			logger.info(e.getMessage());
		} catch(RuntimeException e){
			logger.info(e.getMessage());
		}
		return null;
	}


	public void setTimerPotentialCustomerService(
			ITimerPotentialCustomerService timerPotentialCustomerService) {
		this.timerPotentialCustomerService = timerPotentialCustomerService;
	}


	public void setMapOperate(IMapOperate mapOperate) {
		this.mapOperate = mapOperate;
	}


	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}


	public void setLadingstationDepartmentManager(
			ILadingstationDepartmentManager ladingstationDepartmentManager) {
		this.ladingstationDepartmentManager = ladingstationDepartmentManager;
	}


	public ITimerPotentialCustomerService getTimerPotentialCustomerService() {
		return timerPotentialCustomerService;
	}


	public IMapOperate getMapOperate() {
		return mapOperate;
	}


	public IBaseDataManager getBaseDataManager() {
		return baseDataManager;
	}


	public ILadingstationDepartmentManager getLadingstationDepartmentManager() {
		return ladingstationDepartmentManager;
	}
	
}
