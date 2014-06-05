package com.deppon.crm.module.uums.server.service.Imp;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.dao.IEmployeeDao;
import com.deppon.crm.module.uums.server.dao.IOrgDao;
import com.deppon.crm.module.uums.server.dao.IPositionDao;
import com.deppon.crm.module.uums.server.service.IEmployeeService;
import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
import com.deppon.crm.module.uums.shared.domain.OrgInfo;
import com.deppon.crm.module.uums.shared.domain.PostionInfo;
import com.deppon.crm.module.uums.shared.exception.invalidIdException;

public class EmployeeService implements IEmployeeService{
	private IEmployeeDao employeeDao;
	private IOrgDao orgDao;	
	private IPositionDao positionDao;
	public IEmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public IOrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(IOrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public IPositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(IPositionDao positionDao) {
		this.positionDao = positionDao;
	}
	@Override
	public int insert(EmployeeInfo entity) {
		String positon=entity.getPosition();
		List<PostionInfo> postionInfo=positionDao.searchByName(positon);
		int positionSize=0;
		if(postionInfo!=null){
			positionSize=postionInfo.size();
		}else{
			throw new SyncException("EmployeeService.insert 中员工的职位无效");	
		}
		int res=0;
		if(positionSize>0){
			res=employeeDao.insert(entity);
		}else{
				throw new SyncException("EmployeeService.insert 中员工的部门或者职位无效");	
		}
		return res;
	}

	@Override
	public int updateById(EmployeeInfo entity, String employeeID) {
		String positon=entity.getPosition();
		List<PostionInfo> postionInfo=positionDao.searchByName(positon);
		int positionSize=0;
		if(postionInfo!=null){
			positionSize=postionInfo.size();
		}else{
			throw new SyncException("EmployeeService.insert 中员工的职位无效");	
		}
		int res=0;
		if(positionSize>0){
			res=employeeDao.updateById(entity, employeeID);
		}else{
				throw new SyncException("EmployeeService.updateById 中员工的部门或者职位无效");
		}
		return res;
	}

	@Override
	public int updateByCode(EmployeeInfo entity, String employeeCode) {
		String positon=entity.getPosition();
		List<PostionInfo> postionInfo=positionDao.searchByName(positon);
		int positionSize=0;
		if(postionInfo!=null){
			positionSize=postionInfo.size();
		}else{
			throw new SyncException("EmployeeService.insert 中员工的职位无效");	
		}
		int res=0;
		if(positionSize>0){
			res=employeeDao.updateByCode(entity, employeeCode);
		}else{
				throw new SyncException("EmployeeService.updateByCode 中员工的部门或者职位无效");
		}
		return res;
	}

	@Override
	public int deleteById(String employeeID) {
		
		if(StringUtils.isEmpty(employeeID)){
				throw new SyncException("EmployeeService.deleteById(String employeeID) 无效employeeID");
		}
		int res=employeeDao.deleteById(employeeID);
		return res;
	}
	@Override
	public int deleteByCode(Map<String,String> empMap) {
		String employeeCode=empMap.get("employeeCode");
		if(StringUtils.isEmpty(employeeCode)){
				throw new SyncException("EmployeeService.deleteByCode(String employeeCode) 无效employeeCode");
		}
		int res=employeeDao.deleteByCode(empMap);
		return res;
	}
	@Override
	public EmployeeInfo searchById(String employeeID) {
		if(StringUtils.isEmpty(employeeID)){
				throw new SyncException("EmployeeService.searchById(String employeeID) 无效employeeID");
		}
		EmployeeInfo employeeInfo=employeeDao.searchById(employeeID);
		return employeeInfo;
	}
	@Override
	public EmployeeInfo searchByCode(String employeeCode) {
		if(StringUtils.isEmpty(employeeCode)){
				throw new SyncException("EmployeeService.searchByCode(String employeeCode) 无效employeeCode");
		}
		EmployeeInfo employeeInfo=employeeDao.searchByCode(employeeCode);
		return employeeInfo;
	}

	@Override
	public int reuseByCode(EmployeeInfo entity) {
		String employeeCode=entity.getTheMainCode();
		if(StringUtils.isEmpty(employeeCode)){
				throw new SyncException("EmployeeService.reuseByCode(String employeeCode) 无效employeeCode");
		}
		return employeeDao.reuseByCode(entity);
	}

	@Override
	public int reuseById(String employeeId) {
		if(StringUtils.isEmpty(employeeId)){
				throw new SyncException("EmployeeService.reuseById(String employeeId) 无效employeeId");
		}
		return employeeDao.reuseById(employeeId);
	}
}
