/**   
 * @title LadingstationDepartmentService.java
 * @package com.deppon.crm.module.common.server.service.impl
 * @description what to do
 * @author 安小虎
 * @update 2012-3-23 上午8:42:26
 * @version V1.0   
 */
package com.deppon.crm.module.common.server.service.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao;
import com.deppon.crm.module.common.server.service.ILadingstationDepartmentService;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;

/**
 * @description 始发网点与受理部门SERVICE
 * @author 安小虎
 * @version 0.1 2012-3-23
 * @date 2012-3-23
 */

public class LadingstationDepartmentService implements
		ILadingstationDepartmentService {

	private ILadingstationDepartmentDao ladingstationDepartmentDao;
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：保存网点信息
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	public int saveLadingstationDepartment(BussinessDept bussinessDept){
		return ladingstationDepartmentDao.saveLadingstationDepartment(bussinessDept);
	}
	

	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据标杆编码修改网点信息
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	public int updateLadingstationDepartment(BussinessDept bussinessDept){
		return ladingstationDepartmentDao.updateLadingstationDepartment(bussinessDept);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据ERPID查询网点信息(现为FOSS的ID)
	 * @参数：ERPID(现为FOSS的ID)
	 * @返回：网点信息
	 * */
	public BussinessDept queryBussinessDeptByERPID(String erpId){
		return ladingstationDepartmentDao.queryBussinessDeptByERPID(erpId);
	}

	public ILadingstationDepartmentDao getLadingstationDepartmentDao() {
		return ladingstationDepartmentDao;
	}

	public void setLadingstationDepartmentDao(
			ILadingstationDepartmentDao ladingstationDepartmentDao) {
		this.ladingstationDepartmentDao = ladingstationDepartmentDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #save(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@Override
	public int save(LadingstationDepartment ladingstationDepartment) {
		return ladingstationDepartmentDao.save(ladingstationDepartment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #
	 * update(com.deppon.crm.module.common.shared.domain.LadingstationDepartment
	 * )
	 */
	@Override
	public int update(LadingstationDepartment ladingstationDepartment) {
		return ladingstationDepartmentDao.update(ladingstationDepartment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #delete(java.lang.String)
	 */
	@Override
	public int delete(String ladingstationDepartmentId) {
		return ladingstationDepartmentDao.delete(ladingstationDepartmentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #getById(java.lang.String)
	 */
	@Override
	public LadingstationDepartment getById(String id) {
		return ladingstationDepartmentDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #searchByCondition(com.deppon.crm.module.common.shared.domain.
	 * LadingstationDepartment)
	 */
	@Override
	public List<LadingstationDepartment> searchByCondition(
			LadingstationDepartment ladingstationDepartment,int start, int limit) {
		return ladingstationDepartmentDao.searchByCondition(ladingstationDepartment,start,limit);
	}

	@Override
	public Long searchByConditionCount(LadingstationDepartment ladingstationDepartment){
		return ladingstationDepartmentDao.getCountByCondition(ladingstationDepartment);
	}
	
	
		/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #getAcceptDeptByBeginLadingId(java.lang.String)
	 */
	@Override
	public List<LadingstationDepartment> getAcceptDeptByConfigurator(String deptId,boolean isReceiveGoods,String resource) {
		return  ladingstationDepartmentDao	.getAcceptDeptByConfigurator(deptId,isReceiveGoods,resource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #getLeaveBusDeptByName(java.lang.String)
	 */
	@Override
	public List<BussinessDept> getLeaveBusDeptByName(BusDeptSearchCondition condition)  {
		return this.ladingstationDepartmentDao.getLeaveBusDeptByName(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.service.ILadingstationDepartmentService
	 * #getArriveBusDeptByName(java.lang.String)
	 */
	@Override
	public List<BussinessDept> getArriveBusDeptByName(BusDeptSearchCondition condition) {
		return this.ladingstationDepartmentDao.getArriveBusDeptByName(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#getBusDeptByName(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptById(String deptId) {
		return ladingstationDepartmentDao.getBusDeptById(deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#getBusDeptByName(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByName(String startStation) {
		return ladingstationDepartmentDao.getBusDeptByName(startStation);
	}
	
	@Override
	public List<LadingstationDepartment> getAllLandingStationDepartment(int start, int limit) {
		return ladingstationDepartmentDao.getAllLandingStationDepartment(start, limit);
	}
	
	@Override
	public Long getAllLandingStationDepartmentCount(){
		return ladingstationDepartmentDao.getAllLandingStationDepartmentCount();
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#getBusDeptByCode(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByCode(String deptCode) {
		return ladingstationDepartmentDao.getBusDeptByCode(deptCode);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#getBussinessNumber(com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition)
	 */
	@Override
	public Long getBussinessNumber(BusDeptSearchCondition condition) {
		return ladingstationDepartmentDao.getBussinessNumber(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#getBusDeptByErpId(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByErpId(String erpId) {
		return ladingstationDepartmentDao.getBusDeptByErpId(erpId);
	}
	
	@Override
	public BussinessDept getBusDeptByStandardCode(String standardCode) {
		return ladingstationDepartmentDao.getBusDeptByStandardCode(standardCode);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#searchExistsLadingstationByCondition(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@Override
	public int searchExistsLadingstationByCondition(LadingstationDepartment ls) {
		return ladingstationDepartmentDao.searchExistsLadingstationByCondition(ls);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#getBusDeptByDeptId(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByDeptId(String deptId) {
		return ladingstationDepartmentDao.getBusDeptByDeptId(deptId);
	}
	

	public int invalidLadingstationRelation(String ldId){
		LadingstationDepartment ladingstationDepartment=new LadingstationDepartment();
		ladingstationDepartment.setIsValid(Constant.LADINGSTATION_DEPARTMENT_INVALID);
		ladingstationDepartment.setId(ldId);
		return ladingstationDepartmentDao.update(ladingstationDepartment);
	}

	@Override
	public List<Map<String, Object>> getBusDeptByDeptIdOrDeptName(String deptId,
			String deptName, int start, int limit) {
		return ladingstationDepartmentDao.getBusDeptByDeptIdOrDeptName(deptId, deptName, start, limit);
	}

	@Override
	public int countBusDeptByDeptIdOrDeptName(String deptId, String deptName) {
		return ladingstationDepartmentDao.countBusDeptByDeptIdOrDeptName(deptId, deptName);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#getAllBusDeptByDeptIdOrDeptName(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public List<Map<String, Object>> getAllBusDeptByDeptIdOrDeptName(
			String deptId, String deptName, int start, int limit) {
		return ladingstationDepartmentDao.getAllBusDeptByDeptIdOrDeptName(deptId, deptName, start, limit);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.ILadingstationDepartmentService#countAllBusDeptByDeptIdOrDeptName(java.lang.String, java.lang.String)
	 */
	@Override
	public int countAllBusDeptByDeptIdOrDeptName(String deptId, String deptName) {
		return ladingstationDepartmentDao.countAllBusDeptByDeptIdOrDeptName(deptId, deptName);
	}	
	
}
