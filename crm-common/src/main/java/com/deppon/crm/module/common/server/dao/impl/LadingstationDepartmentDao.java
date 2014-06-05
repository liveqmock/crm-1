/**   
 * @title LadingstationDepartmentDao.java
 * @package com.deppon.crm.module.common.server.dao.impl
 * @description what to do
 * @author 安小虎
 * @update 2012-3-23 下午2:59:48
 * @version V1.0   
 */
package com.deppon.crm.module.common.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description 始发网点部门关系DAO
 * @author 安小虎
 * @version 0.1 2012-3-23
 * @date 2012-3-23
 */

public class LadingstationDepartmentDao extends iBatis3DaoImpl implements
		ILadingstationDepartmentDao {

	private static final String NAMESPACE_ = "com.deppon.crm.module.common.shared.domain.LadingstationDepartment.";
	private static final String NAMESPACE = "com.deppon.crm.module.common.shared.domain.BussinessDept.";
	private static final String SAVE = "save";
	private static final String UPDATE = "update";
	private static final String DELETEBYID = "deleteById";
	private static final String GETBYID = "getById";
	private static final String SEARCHBYCONDITION = "searchByCondition";
	private static final String GETCOUNTBYCONDITION = "getCountByCondition";
//	private static final String GETLEAVEBUSDEPTBYNAME = "getLeaveBusDeptByName";
	private static final String GETBUSDEPTBYCONDITION= "getBussinessDeptByCondition";
	private static final String GETACCEPTDEPTBYLEAVEBUSDEPTID = "getAcceptDeptByLeaveBusDeptName";
	private static final String GETBUSDEPTBYNAME = "getBusDeptByName";
	private static final String GETBUSDEPTBYID = "getBusDeptById";
	private static final String GETALLLADSTATION = "getAllLadStation";
	private static final String GETALLLADSTATIONCOUNT="getAllLadStationCount";
	private static final String GETBUSDEPTBYCODE = "getBusDeptByCode";
	private static final String GETBUSSINESSNUMBER = "getBussinessNumber";
	private static final String GETBUSDEPTBYERPID = "getBusdeptbyErpId";
	private static final String GETBUSDEPTBYSTANDARDCODE = "getBusdeptbyStandardCode";
	private static final String SEARCHEXISTSBYCONDITION="searchExistsByCondition";
	
	private static final String GETBUSDEPTBYDEPTIDORDEPTNAME = "getBusDeptByDeptIdOrDeptName";
	private static final String COUNTBUSDEPTBYDEPTIDORDEPTNAME = "countBusDeptByDeptIdOrDeptName";
	// 根据标杆编码修改网点信息(接口用)
	private static final String UPDATE_LADINGDEPT_STANDARDCODE = "updateBussinessDeptByStandardcode";
	// 新增网点信息(接口用)
	private static final String SAVE_LADINGDEPT = "insertBussinessDept";
	// 根据ERPID查询网点信息(接口用)
	private static final String QUERY_LADINGDEPT_ERPID ="queryLadingstationByERPID";
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：保存网点信息
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	public int saveLadingstationDepartment(BussinessDept bussinessDept){
		return this.getSqlSession().insert(NAMESPACE+SAVE_LADINGDEPT, bussinessDept);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据标杆编码修改网点信息
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	public int updateLadingstationDepartment(BussinessDept bussinessDept){
		return this.getSqlSession().update(NAMESPACE+UPDATE_LADINGDEPT_STANDARDCODE, bussinessDept);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据ERPID查询网点信息(现为FOSS的ID)
	 * @参数：ERPID(现为FOSS的ID)
	 * @返回：网点信息
	 * */
	public BussinessDept queryBussinessDeptByERPID(String erpId){
		return (BussinessDept)this.getSqlSession().selectOne(NAMESPACE+QUERY_LADINGDEPT_ERPID,erpId);
	}
	
	/**
	 * 
	 * @description 始发网点与受理部门关联DAO新增.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return int
	 * @update 2012-3-23 上午10:30:28
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#save
	 *      (com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@Override
	public int save(LadingstationDepartment ladingstationDepartment) {
		return this.getSqlSession().insert(NAMESPACE_ + SAVE,
				ladingstationDepartment);
	}

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO修改.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return int
	 * @update 2012-3-23 上午10:30:28
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#update
	 *      (com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@Override
	public int update(LadingstationDepartment ladingstationDepartment) {
		return this.getSqlSession().update(NAMESPACE_ + UPDATE,
				ladingstationDepartment);
	}

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据ID删除.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return int
	 * @update 2012-3-23 上午10:30:28
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#delete
	 *      (java.lang.String)
	 */
	@Override
	public int delete(String ladingstationDepartmentId) {
		return this.getSqlSession().delete(NAMESPACE_ + DELETEBYID,
				ladingstationDepartmentId);
	}

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据ID获得接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param ID
	 * @date 2012-3-23
	 * @return 对象
	 * @update 2012-3-23 上午9:18:50
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getById
	 *      (java.lang.String)
	 */
	@Override
	public LadingstationDepartment getById(String id) {
		return (LadingstationDepartment) this.getSqlSession().selectOne(
				NAMESPACE_ + GETBYID, id);
	}

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据条件查询.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return List
	 * @update 2012-3-23 上午10:30:28
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#
	 *      searchByCondition
	 *      (com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LadingstationDepartment> searchByCondition(
			LadingstationDepartment ladingstationDepartment,int start, int limit) {
		RowBounds bounds = new RowBounds(start, limit);
		return this.getSqlSession().selectList(NAMESPACE_ + SEARCHBYCONDITION,ladingstationDepartment, bounds);
	}
	
	/**
	 * <p>
	 * Description: 获取所有始发网点与受理部门关联对象<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-5-8
	 * @return
	 * List<LadingstationDepartment>
	 */
	public List<LadingstationDepartment> getAllLandingStationDepartment(int start, int limit) {
		RowBounds bounds = new RowBounds(start, limit);
		Map para = new HashMap();
		return this.getSqlSession().selectList(NAMESPACE_ + GETALLLADSTATION, para, bounds);
	}

	/**
	 * 获取所有始发网点与受理部门关联对象的总数
	 */
	public Long getAllLandingStationDepartmentCount() {
		Object obj=this.getSqlSession().selectOne(NAMESPACE_ + GETALLLADSTATIONCOUNT);
		Long count=new Long(0);
		if(obj!=null){
			count=(Long)obj;
		}
		return count;		
		
	}	
	
	
	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据条件查询记录数接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 条件
	 * @date 2012-3-23
	 * @return 条数
	 * @update 2012-3-23 上午9:19:54
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getCountByCondition
	 *      (com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@Override
	public Long getCountByCondition(LadingstationDepartment ladingstationDepartment) {
		Object obj= this.getSqlSession().selectOne(
				NAMESPACE_ + GETCOUNTBYCONDITION, ladingstationDepartment);
		Long count=new Long(0);
		if(obj!=null){
			count=(Long)obj;
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#
	 * getLeaveBusDeptByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BussinessDept> getLeaveBusDeptByName(BusDeptSearchCondition condition) {
		RowBounds bound = new RowBounds(condition.getStart(),condition.getLimit());
		return this.getSqlSession().selectList(
				NAMESPACE + GETBUSDEPTBYCONDITION, condition,bound);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#
	 * getBeginLadingByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BussinessDept> getArriveBusDeptByName(BusDeptSearchCondition condition) {
		RowBounds bound = new RowBounds(condition.getStart(),condition.getLimit());
		return this.getSqlSession().selectList(
				NAMESPACE + GETBUSDEPTBYCONDITION, condition,bound);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#
	 * getAcceptDeptByBeginLadingId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public  List <LadingstationDepartment> getAcceptDeptByConfigurator(String deptId,boolean isReceiveGoods,String resource) {
		Map map = new HashMap();
		map.put("deptId", deptId);
		map.put("isReceiveGoods", isReceiveGoods?Constant.CONFIG_ORDER_RECEIVE_GOODS_YES:Constant.CONFIG_ORDER_RECEIVE_GOODS_NO);
		map.put("resource", resource);
		return  getSqlSession().selectList(NAMESPACE_ + GETACCEPTDEPTBYLEAVEBUSDEPTID, map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getBusDeptByName(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptById(String deptId) {
		return (BussinessDept) this.getSqlSession().selectOne(
				NAMESPACE + GETBUSDEPTBYID, deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getBusDeptByName(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByName(String startStation) {
		return (BussinessDept) this.getSqlSession().selectOne(
				NAMESPACE + GETBUSDEPTBYNAME, startStation);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getBusDeptByCode(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByCode(String deptCode) {
		return (BussinessDept) this.getSqlSession().selectOne(
				NAMESPACE + GETBUSDEPTBYCODE, deptCode);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getBussinessNumber(com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition)
	 */
	@Override
	public Long getBussinessNumber(BusDeptSearchCondition condition) {
		return (Long) this.getSqlSession().selectOne(
				NAMESPACE + GETBUSSINESSNUMBER, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getBusDeptByErpId(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByErpId(String erpId) {
		List<BussinessDept> list = this.getSqlSession().selectList(
				NAMESPACE + GETBUSDEPTBYERPID, erpId);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Override
	public BussinessDept getBusDeptByStandardCode(String standardCode) {
		List<BussinessDept> list = this.getSqlSession().selectList(
				NAMESPACE + GETBUSDEPTBYSTANDARDCODE, standardCode);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#searchExistsLadingstationByCondition(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)
	 */
	@Override
	public int searchExistsLadingstationByCondition(LadingstationDepartment ls) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_ + SEARCHEXISTSBYCONDITION, ls);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getBusDeptByDeptId(java.lang.String)
	 */
	@Override
	public BussinessDept getBusDeptByDeptId(String deptId) {
		List<BussinessDept> list = this.getSqlSession().selectList(
				NAMESPACE + "getBusDeptByDeptId", deptId);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Map<String, Object>> getBusDeptByDeptIdOrDeptName(String deptId,
			String deptName, int start, int limit) {
		Map map = new HashMap();
		if (!StringUtils.isEmpty(deptName)) {
			map.put("deptName", "%"+deptName+"%");
		}
		map.put("deptId", deptId);
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE + GETBUSDEPTBYDEPTIDORDEPTNAME, map, rb);
	}

	@Override
	public int countBusDeptByDeptIdOrDeptName(String deptId, String deptName) {
		Map map = new HashMap();
		if (!StringUtils.isEmpty(deptName)) {
			map.put("deptName", "%"+deptName+"%");
		}
		map.put("deptId", deptId);
		return (Integer)this.getSqlSession().selectOne(NAMESPACE + COUNTBUSDEPTBYDEPTIDORDEPTNAME, map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#getAllBusDeptByDeptIdOrDeptName(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public List<Map<String, Object>> getAllBusDeptByDeptIdOrDeptName(
			String deptId, String deptName, int start, int limit) {
		Map map = new HashMap();
		if (!StringUtils.isEmpty(deptName)) {
			map.put("deptName", "%"+deptName+"%");
		}
		map.put("deptId", deptId);
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE + "getAllBusDeptByDeptIdOrDeptName", map, rb);

	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao#countAllBusDeptByDeptIdOrDeptName(java.lang.String, java.lang.String)
	 */
	@Override
	public int countAllBusDeptByDeptIdOrDeptName(String deptId, String deptName) {
		Map map = new HashMap();
		if (!StringUtils.isEmpty(deptName)) {
			map.put("deptName", "%"+deptName+"%");
		}
		map.put("deptId", deptId);
		return (Integer)this.getSqlSession().selectOne(NAMESPACE + "countAllBusDeptByDeptIdOrDeptName", map);
	}

}
