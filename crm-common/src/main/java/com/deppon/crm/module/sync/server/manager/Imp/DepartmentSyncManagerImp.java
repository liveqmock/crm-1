package com.deppon.crm.module.sync.server.manager.Imp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.shared.SyncException;
public class DepartmentSyncManagerImp implements ISyncManager{
	private IDepartmentService department;
	//插入
	public static final String INSERT="1";
	//修改
	public static final String UPDATE="2";
	//异动
	public static final String CHANGED="3";
	//待撤销 如何处理？
	public static final String EXIST="4";
	//已撤销
	public static final String DELETED="5";
	public int check(Map<String,String> entity) {
		String changType=entity.get(CHANGETYPE);
		String standardCode=entity.get(DEPSTANDCODE);
		String parentStandCode=entity.get(PARENTSTANDCODE);
		Department oldPar=department.getDeptByStandCode(standardCode);
		String oldPartCode="";
		if(StringUtils.isEmpty(parentStandCode)||StringUtils.isEmpty(standardCode)||StringUtils.isEmpty(changType)){
			throw new SyncException("the map is illegal");
		}
		if(changType.equals(DELETED)){
			changType=DELETEDES;
			if(oldPar==null){
				return SUCCESS;
			}
		}else{
			if(oldPar==null)
				{
					changType=INSERTDES;
				}
			else {
					changType=UPDATEDES; 
					String parentId=oldPar.getParentCode().getId();
					if(StringUtils.isEmpty(parentId)){
						throw new SyncException("can't find the dept "+ standardCode+"'s parentId");
					}
					Department	oldP=department.getDepartmentById(parentId);
					if(oldP==null){
						throw new SyncException("can't find the dept "+ standardCode+"'s parent");
					}
					oldPartCode=oldP.getStandardCode();
					if(StringUtils.isEmpty(oldPartCode)||parentStandCode.equals(oldPartCode)){
						oldPartCode=null;
					}
			}
		}
		department.syncDep(standardCode, changType, oldPartCode);
		return SUCCESS;
	}
	public IDepartmentService getDepartment() {
		return department;
	}
	public void setDepartment(IDepartmentService department) {
		this.department = department;
	}
}
