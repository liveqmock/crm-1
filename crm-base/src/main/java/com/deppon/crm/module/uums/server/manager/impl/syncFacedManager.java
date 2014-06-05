package com.deppon.crm.module.uums.server.manager.impl;

import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.shared.domain.BaseUUEntity;
import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
import com.deppon.crm.module.uums.shared.domain.OrgInfo;
import com.deppon.crm.module.uums.shared.domain.PostionInfo;
import com.deppon.crm.module.uums.shared.domain.UserInfo;
/**
 * 
 * @description CRM同步uums 组织架外观类，供接口调用：通过check方法判断调用哪一个同步实现类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class syncFacedManager  implements ISyncUumsDataManager{
	/*public static final String INSERT="1";
	public static final String UPDATE="1";
	public static final String INSERT="1";
	public static final String INSERT="1";*/

     /* user   1、新增；2、修改；3、删除；4、返聘；*/
   /* postion  1、已撤销 2、未撤销
 * ，company 1、新增；2、修改；3、删除；
*/	private ISyncUumsDataManager companyManager;
	private ISyncUumsDataManager employeeManager;
	private ISyncUumsDataManager orgManager;
	private ISyncUumsDataManager positionManager;
	private ISyncUumsDataManager userManager;
	
	public ISyncUumsDataManager getCompanyManager() {
		return companyManager;
	}

	public void setCompanyManager(ISyncUumsDataManager companyManager) {
		this.companyManager = companyManager;
	}

	public ISyncUumsDataManager getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(ISyncUumsDataManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public ISyncUumsDataManager getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(ISyncUumsDataManager orgManager) {
		this.orgManager = orgManager;
	}

	public ISyncUumsDataManager getPositionManager() {
		return positionManager;
	}

	public void setPositionManager(ISyncUumsDataManager positionManager) {
		this.positionManager = positionManager;
	}

	public ISyncUumsDataManager getUserManager() {
		return userManager;
	}

	public void setUserManager(ISyncUumsDataManager userManager) {
		this.userManager = userManager;
	}
	@Override
	public int insert(BaseUUEntity entity) {
		return 0;
	}

	@Override
	public int update(BaseUUEntity entity) {
		return 0;
	}

	@Override
	public int delete(BaseUUEntity entity) {
		
		return 0;
	}

	@Override
	public int check(BaseUUEntity entity) throws Exception {
		ISyncUumsDataManager facedManager=null;
		int res=0;
		if(entity instanceof CompanyInfo){	
			facedManager=companyManager;
		}else if(entity instanceof EmployeeInfo){
			SyncUumsEmployeeInfoManager emy=((SyncUumsEmployeeInfoManager)employeeManager);
			facedManager=emy;
		}
		else if(entity instanceof OrgInfo){
			SyncUumsOrgInfoManager org=((SyncUumsOrgInfoManager)orgManager);
			facedManager=org;
		}
		else if(entity instanceof PostionInfo){
			facedManager=positionManager;
		}
		else if(entity instanceof UserInfo){
			SyncUumsUserInfoManager user=(SyncUumsUserInfoManager)userManager;
			facedManager=user;
		}else{
			throw new SyncException("the para entity is not legeal");
		}
		res=facedManager.check(entity);
		return res;
	}
}
