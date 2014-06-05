package com.deppon.crm.module.organization.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.exception.UserException;
import com.deppon.crm.module.authorization.shared.exception.UserExceptionType;
import com.deppon.crm.module.organization.server.dao.IDepartmentDao;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.BizDept;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.exception.DepartmentException;
import com.deppon.crm.module.organization.shared.exception.DepartmentExceptionType;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.entity.IFunction;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 作 者：张斌 最后修改时间：2011年10月20日 描 述：
 * T_ORG_DEPARTMENT部门信息Service层实现（得到所查部门的子部门、得到所要展开部门的子部门、通过部门名称模糊查询部门的deptSeq）
 */
@Transactional
public class DepartmentService implements IDepartmentService {

	private IDepartmentDao departmentDao;

	/**
	 * @作者：罗典
	 * @时间：2012-4-24
	 * @描述：通过部门编码获取部门信息
	 * @参数：deptCode 部门编码
	 * @返回值：Department 部门信息
	 * */
	public Department getDeptByStandardCode(String standardCode) {
		Department dept = departmentDao.getDeptByStandardCode(standardCode);
		return dept;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-24
	 * @描述：通过部门编码获取部门信息
	 * @参数：deptCode 部门编码
	 * @返回值：Department 部门信息
	 * */
	public Department getDeptByCode(String deptCode) {
		Department dept = departmentDao.getDeptByCode(deptCode);
		return dept;
	}

	/**
	 * 得到所查部门的子部门
	 * 
	 * @return List<Department>
	 * @param String
	 *            id
	 */
	@SuppressWarnings("serial")
	@Transactional(readOnly = true)
	public List<Department> queryAllChild(String id) {
		if (id == null || "".equals(id)) {
			DepartmentException e = new DepartmentException(
					DepartmentExceptionType.DepartmentIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		List<Department> depts = departmentDao.getAllChildById(id);
		return depts;
	}

	/**
	 * 得到所要展开部门的子部门 queryDirectChildDepts
	 * 
	 * @return List<Department>
	 * @param Department
	 *            dept
	 */
	public List<Department> queryDirectChildDepts(Department dept) {
		if (dept == null) {
			DepartmentException e = new DepartmentException(
					DepartmentExceptionType.DepartmentIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
				private static final long serialVersionUID = 1L;
			};
		}
		List<Department> departments = departmentDao
				.getDirectChildDepartments(dept);
		return departments;
	}

	/**
	 * 通过部门名称模糊查询部门的deptSeq querySeq
	 * 
	 * @param deptName
	 * @return List<String>
	 * @since JDK1.6
	 */
	public List<String> querySeq(String deptName) {
		if(checkSearchCondiction(deptName)){
			return new ArrayList<String>();
		}//部门名称不得超过60位！
		deptName = "%" + deptName + "%";
		List<String> seqs = departmentDao.querySeq(deptName);
		for (int i = 0; i < seqs.size(); i++) {
			for (int j = 0; j < seqs.size(); j++) {
				if (seqs.get(i).startsWith(seqs.get(j))
						&& !seqs.get(i).equals(seqs.get(j))) {
					seqs.remove(j);
					i--;
					break;
				}
			}
		}
		return seqs;

	}

	public List<Department> queryLeftDepts(String currentUserId, String userId,
			String deptName) {
		List<Department> depts = null;
		if (userId == null) {
			depts = departmentDao.getAllAuthDepts(currentUserId, deptName);
		} else {
			depts = departmentDao.getLeftDepts(currentUserId, userId, deptName);
		}
		return depts;
	}

	public List<Department> queryLeftDeptsByEmpCode(String currentUserId,
			String empCode, String deptName) {
		List<Department> depts = null;
		if (empCode == null) {
			depts = departmentDao.getAllAuthDepts(currentUserId, deptName);
		} else {
			depts = departmentDao.queryLeftDeptsByEmpCode(currentUserId,
					empCode, deptName);
		}
		return depts;
	}

	@SuppressWarnings("serial")
	public List<Department> queryAuthedDepts(String currentUserId, String userId) {
		if (userId == null || "".equals(userId.trim())) {
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		List<Department> depts = departmentDao.getAuthedDepts(currentUserId,
				userId);
		return depts;
	}

	/**
	 * 通过授权部门名称模糊查询部门对象集合
	 * 
	 * @param depIds
	 *            、deptName、start、limit
	 * @return Map<String,Object>
	 * @since JDK1.6
	 */
	@Override
	public Map<String, Object> getExecuteDep(Set<String> depIds,
			String deptName, int start, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Department> executeDeplist = departmentDao.searchExecuteDep(
				depIds, deptName, start, limit);
		String totalcount = departmentDao.searchExecuteDepCount(depIds,
				deptName);
		map.put("executeDeplist", executeDeplist);
		map.put("totalcount", totalcount);
		return map;
	}

	/*---------------------------------------备用方法-------------------------------------------------------*/
	/**
	 * 查询所有部门，条件department 分页查询
	 */
	@Transactional(readOnly = true)
	public List<Department> queryAll(Department department, int limit, int start) {
		return departmentDao.getAll(department, limit, start);
	}

	/**
	 * 查询所有大区
	 */
	@Transactional(readOnly = true)
	public List<Department> getBigAreaList() {
		String deptName = "%大区";
		List bigAreaList = departmentDao.getByLikeDeptName(deptName);
		deptName = "%转运中心";
		List transCenterList = departmentDao.getByLikeDeptName(deptName);
		bigAreaList.addAll(transCenterList);
		deptName = "%外场";
		List outAreaList = departmentDao.getByLikeDeptName(deptName);
		bigAreaList.addAll(outAreaList);
		deptName = "%转运场";
		List tranSeqList = departmentDao.getByLikeDeptName(deptName);
		bigAreaList.addAll(tranSeqList);
		deptName = "%枢纽中心";
		List hubCenterList = departmentDao.getByLikeDeptName(deptName);
		bigAreaList.addAll(hubCenterList);
		deptName = "%快递%分部";
		List expressList = departmentDao.getByLikeDeptName(deptName);
		bigAreaList.addAll(expressList);
		return bigAreaList;
	}

	@Transactional(readOnly = true)
	public List<Department> getBigAreaListByName(String deptName) {
		String deptNames = "%" + deptName + "%大区";
		List bigAreaList = departmentDao.getByLikeDeptName(deptNames);
		deptNames = "%" + deptName + "%转运中心";
		List transCenterList = departmentDao.getByLikeDeptName(deptNames);
		bigAreaList.addAll(transCenterList);
		deptNames = "%" + deptName + "%外场";
		List outAreaList = departmentDao.getByLikeDeptName(deptNames);
		bigAreaList.addAll(outAreaList);
		deptNames = "%" + deptName + "%转运场";
		List tranSeqList = departmentDao.getByLikeDeptName(deptNames);
		bigAreaList.addAll(tranSeqList);
		deptNames = "%" + deptName + "%枢纽中心";
		List hubCenterList = departmentDao.getByLikeDeptName(deptNames);
		bigAreaList.addAll(hubCenterList);
		deptNames = "%" + deptName + "%分部";
		List expressDeptList = departmentDao.getByLikeDeptName(deptNames);
		bigAreaList.addAll(expressDeptList);
		return bigAreaList;
	}

	/**
	 * 查询所在大区
	 */
	@Transactional(readOnly = true)
	public Department getBigAreaByDeptId(String deptId) {
		if (deptId == null) {
			return null;
		}
		String deptName = "%大区";
		Department bigArea = departmentDao.getByLikeDeptNameByDeptId(deptName,
				deptId);
		if (bigArea == null) {
			deptName = "%转运中心";
			bigArea = departmentDao.getByLikeDeptNameByDeptId(deptName, deptId);
		}
		if (bigArea == null) {
			deptName = "%外场";
			bigArea = departmentDao.getByLikeDeptNameByDeptId(deptName, deptId);
		}
		if (bigArea == null) {
			deptName = "%转运场";
			bigArea = departmentDao.getByLikeDeptNameByDeptId(deptName, deptId);
		}
		if (bigArea == null) {
			deptName = "%枢纽中心";
			bigArea = departmentDao.getByLikeDeptNameByDeptId(deptName, deptId);
		}

		return bigArea;
	}

	/**
	 * 查询所在大区
	 */
	@Transactional(readOnly = true)
	public Department getBigAreaByDeptCode(String deptCode) {
		if (deptCode == null) {
			return null;
		}
		String deptName = "%大区";
		Department bigArea = departmentDao.getByLikeDeptNameByDeptCode(
				deptName, deptCode);
		if (bigArea == null) {
			deptName = "%转运中心";
			bigArea = departmentDao.getByLikeDeptNameByDeptCode(deptName,
					deptCode);
		}
		if (bigArea == null) {
			deptName = "%外场";
			bigArea = departmentDao.getByLikeDeptNameByDeptCode(deptName,
					deptCode);
		}
		if (bigArea == null) {
			deptName = "%转运场";
			bigArea = departmentDao.getByLikeDeptNameByDeptCode(deptName,
					deptCode);
		}
		if (bigArea == null) {
			deptName = "%枢纽中心";
			bigArea = departmentDao.getByLikeDeptNameByDeptCode(deptName,
					deptCode);
		}
		return bigArea;
	}

	/**
	 * 
	 * @Description: 查询所有事业部
	 * @author huangzhanming
	 * @return
	 * @date 2012-11-28 上午10:45:42
	 * @version V1.0
	 */
	@Transactional(readOnly = true)
	public List<Department> getBizDeptList() {
		String deptName = "%事业部";
		List bizDeptList = departmentDao.getByLikeDeptName(deptName);
		return bizDeptList;
	}

	/**
	 * 
	 * <p>
	 * 查询事业部与枢纽中心<br />
	 * </p>
	 * 
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @return List<Department>
	 */
	@Transactional(readOnly = true)
	public List<Department> getBizAndHubCenterDeptList() {
		String deptName = "%事业部";
		List<Department> bizDeptList = departmentDao
				.getByLikeDeptName(deptName);
		deptName = "%枢纽中心";
		List<Department> hubCenterList = departmentDao
				.getByLikeDeptName(deptName);
		bizDeptList.addAll(hubCenterList);
		return bizDeptList;
	}

	/**
	 * 
	 * @Description: 查询所在事业部
	 * @author huangzhanming
	 * @param deptId
	 * @return
	 * @date 2012-11-28 上午10:46:28
	 * @version V1.0
	 */
	@Transactional(readOnly = true)
	public Department getBizDeptByDeptId(String deptId) {
		if (deptId == null) {
			return null;
		}
		String deptName = "%事业部";
		Department bizDept = departmentDao.getByLikeDeptNameByDeptId(deptName,
				deptId);
		return bizDept;
	}

	/**
	 * 添加部门
	 */
	@SuppressWarnings("serial")
	public void add(Department department) {
		if (department == null) {
			DepartmentException e = new DepartmentException(
					DepartmentExceptionType.DepartmentIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		if (department.getParentCode() == null
				|| "".equals(department.getParentCode())) {
			Department root = this.departmentDao.getRoot();
			if (root != null && root.getId() != null
					&& !"".equals(root.getId())) {
				department.setParentCode(root);
			} else {
				department.setParentCode(null);
			}
		}
		departmentDao.insert(department);
	}

	/**
	 * 修改部门信息
	 */
	@SuppressWarnings("serial")
	public void update(Department department) {
		if (department == null) {
			DepartmentException e = new DepartmentException(
					DepartmentExceptionType.DepartmentIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		if (department.getId() == null || "".equals(department.getId())) {
			DepartmentException e = new DepartmentException(
					DepartmentExceptionType.DepartmentIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		departmentDao.update(department);
	}

	/**
	 * 删除部门
	 */
	@SuppressWarnings("serial")
	public void remove(String id) {
		if (id == null || "".equals(id)) {
			DepartmentException e = new DepartmentException(
					DepartmentExceptionType.DepartmentIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		List<String> removeList = new ArrayList<String>();
		removeList.add(id);
		List<String> deleteList = departmentDao.getAllChildIdById(id);
		if (deleteList != null)
			removeList.addAll(deleteList);
		departmentDao.deleteById(removeList);
	}

	/**
	 * 查询有几条，条件department
	 */
	public Long count(Department department) {
		return departmentDao.count(department);
	}

	/**
	 * 查询所有部门，条件department
	 */
	@Transactional(readOnly = true)
	public List<Department> queryAll(Department department) {
		return departmentDao.getAll(department);
	}

	/**
	 * 查询全部部门
	 */
	@Transactional(readOnly = true)
	public List<Department> queryAll(Integer start, Integer limit) {
		return departmentDao.getAll(start, limit);
	}

	/**
	 * 得到指定ID的对象
	 */
	@Transactional(readOnly = true)
	public Department queryById(String id) {
		return departmentDao.getById(id);
	}

	@SuppressWarnings("serial")
	public List<Department> queryAllChildDeptByDeptId(String id) {
		if (id == null || "".equals(id)) {
			DepartmentException e = new DepartmentException(
					DepartmentExceptionType.DepartmentIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		List<Department> departments = departmentDao
				.getAllChildDeptByDeptId(id);
		return departments;
	}

	/**
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有部门
	 * @参数：deptName 部门名字
	 * @返回值：List<Department> 部门集合
	 * */
	@Override
	public List<Department> queryDeptListByName(String deptName, int start,
			int limit) {
		if(checkSearchCondiction(deptName)){
			return new ArrayList<Department>();
		}//部门名称不得超过60位！
		return departmentDao.queryDeptListByName(deptName, start, limit);
	}

	/**
	 * @作者：钟琼
	 * @时间：2013-1-31
	 * @描述：通过部门名称模糊查询所有外场
	 * @参数：deptName 部门名字
	 * @返回值：List<Department> 外场集合
	 * */
	@Override
	public List<Department> queryOutFieldListByName(String deptName, int start,
			int limit) {
		if(checkSearchCondiction(deptName)){
			return new ArrayList<Department>();
		}//部门名称不得超过60位！
		return departmentDao.queryOutFieldListByName(deptName, start, limit);
	}

	/**
	 * @作者：钟琼
	 * @时间：2013-1-31
	 * @描述：通过部门名称模糊查询所有部门 外场
	 * @参数：deptName 外场名字
	 * @返回值：int 条数
	 * */
	@Override
	public int queryOutFieldListByCount(String deptName) {
		if(checkSearchCondiction(deptName)){
			return 0;
		}//部门名称不得超过60位！
		return departmentDao.queryOutFieldListByCount(deptName);
	}

	/**
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有部门 条数
	 * @参数：deptName 部门名字
	 * @返回值：int 条数
	 * */
	@Override
	public int queryDeptListByNameCount(String deptName) {
		return departmentDao.queryDeptListByNameCount(deptName);
	}

	public IDepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public Department getBizDeptByDeptId(String parentId, String deptId) {
		return departmentDao.getBizDeptByDeptId(parentId, deptId);
	}

	@Override
	public List<BizDept> getAllBizDept(String id) {
		return departmentDao.getAllBizDept(id);
	}

	@Override
	public List<Department> getDepartmentByDeptName(String deptName) {
		if(checkSearchCondiction(deptName)){
			return new ArrayList<Department>();
		}//部门名称不得超过60位！
		return this.departmentDao.getByLikeDeptNameRow(deptName, 30);
	}

	@Override
	public List<Department> getDepartmentByDeptNameRow(String deptName, int rows) {
		if(checkSearchCondiction(deptName)){
			return new ArrayList<Department>();
		}//部门名称不得超过60位！
		return this.departmentDao.getByLikeDeptNameRow(deptName, rows);
	}

	@Override
	public Department getDepartmentById(String id) {
		ICache<String, Department> departmentCache = CacheManager.getInstance().getCache(Department.class.getName());
		Department dept=departmentCache.get(id);
		return dept;
	}

	@Override
	public List<Department> getAllBizDeptList(String id) {
		return departmentDao.getAllBizDeptList(id);
	}

	/**
	 * 
	 * <p>
	 * Description:通过标杆编码大区办公室<br />
	 * </p>
	 * 
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @param standardCode
	 * @return List<Department>
	 */
	@Override
	public List<Department> queryDeptByNameAndStandardCode(String standardCode) {
		String deptName = "%快递%统计组";
		List<Department> departments = new ArrayList<Department>();
		departments = departmentDao.queryDeptByNameAndStandardCode(deptName,
				standardCode);

		if (null == departments || departments.size() < 1) {
			departments = departmentDao.queryDeptByNameAndStandardCodeForEx(
					deptName, standardCode);
		}

		return departments;

	}

	/**
	 * 
	 * <p>
	 * Description:根据部门标杆编码查询<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-8-8
	 * @param string
	 * @return Department
	 */
	public Department getBigAreaByStandardCode(String deptCode) {
		if (deptCode == null) {
			return null;
		}
		String deptName = "%大区";
		Department bigArea = departmentDao.getByLikeDeptNameByStandardCode(
				deptName, deptCode);
		if (bigArea == null) {
			deptName = "%转运中心";
			bigArea = departmentDao.getByLikeDeptNameByStandardCode(deptName,
					deptCode);
		}
		if (bigArea == null) {
			deptName = "%外场";
			bigArea = departmentDao.getByLikeDeptNameByStandardCode(deptName,
					deptCode);
		}
		if (bigArea == null) {
			deptName = "%转运场";
			bigArea = departmentDao.getByLikeDeptNameByStandardCode(deptName,
					deptCode);
		}
		if (bigArea == null) {
			deptName = "%枢纽中心";
			bigArea = departmentDao.getByLikeDeptNameByStandardCode(deptName,
					deptCode);
		}
		return bigArea;
	}

	/**
	 * 
	 * <p>
	 * Description:通过部门ID查询上级部门信息<br />
	 * </p>
	 * 
	 * @author andy
	 * @version 0.1 2013-8-14
	 * @param deptId
	 *            部门Id
	 * @return Department
	 */
	@Override
	public Department queryDeptByParentDeptId(String deptId) {
		return departmentDao.queryDeptByParentDeptId(deptId);
	}

	/**
	 * 
	 * <p>
	 * Description:通过部门ID递归上级部门信息<br />
	 * </p>
	 * 
	 * @author andy
	 * @version 0.1 2013-8-14
	 * @param deptId
	 *            部门Id
	 * @return Department
	 */
	@Override
	public List<Department> getAllParentDeptByDeptId(String deptId) {
		return departmentDao.getAllParentDeptByDeptId(deptId);
	}

	@Override
	public void syncDep(String DEPTSTANDCODE, String changeType,
			String oldParentStandCode) {
		departmentDao.syncDep(DEPTSTANDCODE, changeType, oldParentStandCode);
	}

	@Override
	public Department getDeptByStandCode(String standCode) {
		return departmentDao.getDeptByStandCode(standCode);
	}
	/**
	 * 
	 * <p>
	 * Description:通过map.get("standCode")部门ID递归上级部门末尾带map.get("lastWord")字的部门<br />
	 * </p>
	 * 
	 * @author 杨伟
	 * @version 0.1 2014-2-25
	 * @param map
	 * @return Department
	 */
	public Department getAllParentDeptBystandCode(Map<String,String> map) {
		if(map.get("standCode")==null||map.get("lastWord")==null
				||"".equals(map.get("standCode"))||"".equals(map.get("lastWord"))){
			return null;
		}
		return departmentDao.getAllParentDeptBystandCode(map);
	}

	/**
	 * 
	 * <p>
	 * Description:模糊查找部门列表<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-28
	 * @param deptName 模糊部门名称
	 * @return
	 * List<Department>
	 */
	@Override
	public List<Department> getDepartmentListByLikeDeptName(String deptName) {
		if(checkSearchCondiction(deptName)){
			return new ArrayList<Department>();
		}//部门名称不得超过60位！
		return departmentDao.getDepartmentListByLikeDeptName("%"+deptName);
	}
	/**
	 * 
	 * <p>
	 * Description:获得所有有效部门<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年5月6日
	 * @return
	 *
	 */
	@Override
	public List<Department> getAllDepartments() {
		return departmentDao.getAllDepartments();
	}
	/**
	 * 
	 * <p>
	 * Description:获得最后修改时间<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年5月6日
	 * @return
	 *
	 */
	@Override
	public Date getLastModifyTime() {
		return departmentDao.getLastModifyTime();
	}
	/**
	 * 
	* @Title: checkSearchCondiction 
	* @Description: TODO(验证部门模糊查询长度)
	* @author lvchongxin 
	* @param @param deptName    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public boolean checkSearchCondiction(String deptName){
		if(deptName.length()>60){
//			DepartmentException e = new DepartmentException(
//					DepartmentExceptionType.DeptNameTooLong);
//			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
//					new Object[] {}) {
//			};
			return true;
		}
		return false;
	}
}
