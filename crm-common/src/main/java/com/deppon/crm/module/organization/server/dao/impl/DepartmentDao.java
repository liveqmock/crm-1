package com.deppon.crm.module.organization.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.organization.server.dao.IDepartmentDao;
import com.deppon.crm.module.organization.shared.domain.BizDept;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 作 者：张斌 最后修改时间：2011年10月20日 描 述： T_ORG_DEPARTMENT部门表的DAO实现（得到当前部门的子部门、
 * 通过部门名称模糊查询部门的deptSeq）
 */
public class DepartmentDao extends iBatis3DaoImpl implements IDepartmentDao {
	/**
	 * @作者：罗典
	 * @时间：2012-4-24
	 * @描述：通过部门标杆编码获取部门信息
	 * @参数：StandardCode 部门标杆编码
	 * @返回值：Department 部门信息
	 * */
	public Department getDeptByStandardCode(String standardCode) {
		return (Department) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserDepartment.getDeptByStandardCode",
				standardCode);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-24
	 * @描述：通过部门编码获取部门信息
	 * @参数：deptCode 部门编码
	 * @返回值：Department 部门信息
	 * */
	public Department getDeptByCode(String deptCode) {
		return (Department) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserDepartment.getDeptByCode", deptCode);
	}

	/**
	 * 得到当前部门的子部门 getDirectChildDepartments
	 * 
	 * @param dept
	 * @return List<Department>
	 * @since JDK1.6
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getDirectChildDepartments(Department dept) {
		List<Department> list = null;
		list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getDirectChildDepartments", dept);
		return list;
	}

	/**
	 * 通过部门名称模糊查询部门的deptSeq querySeq
	 * 
	 * @param deptName
	 * @return List<String>
	 * @since JDK1.6
	 */
	@SuppressWarnings("unchecked")
	public List<String> querySeq(String deptName) {
		List<String> list = null;
		list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllSeq", deptName);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Department> getLeftDepts(String currentUserId, String userId, String deptName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		if (deptName != null && !"".equals(deptName.trim())) {
			deptName = "%" + deptName + "%";
		}
		map.put("deptName", deptName);
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getLeftDeptsByUserId", map);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Department> queryLeftDeptsByEmpCode(String currentUserId, String empCode, String deptName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("currentUserId", currentUserId);
		map.put("empCode", empCode);
		if (deptName != null && !"".equals(deptName.trim())) {
			deptName = "%" + deptName + "%";
		}
		map.put("deptName", deptName);
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getLeftDeptsByEmpCode", map);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Department> getAuthedDepts(String currentUserId, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAuthedDeptsByUserId", map);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Department> getAllAuthDepts(String currentUserId, String deptName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("currentUserId", currentUserId);
		if (deptName != null && !"".equals(deptName.trim())) {
			deptName = "%" + deptName + "%";
		}
		map.put("deptName", deptName);
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllAuthDepts", map);
		return list;
	}

	/*---------------------------------------备用方法-------------------------------------------------------*/
	/**
	 * 查找符合条件的所有部门，并分页 getAll
	 * 
	 * @param department
	 *            ,limit,start
	 * @return List<Department>
	 * @since JDK1.6
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getAll(Department department, int limit, int start) {
		RowBounds rowBounds = new RowBounds(start, limit);
		List<Department> list = null;
		Department model = new Department();
		if (department != null) {
			if (department.getId() != null && !"".equals(department.getId())) {
				String id = "%" + department.getId() + "%";
				model.setId(id);
			}
			if (department.getDeptCode() != null && !"".equals(department.getDeptCode())) {
				String deptCode = "%" + department.getDeptCode() + "%";
				model.setDeptCode(deptCode);
			}
			if (department.getDeptName() != null && !"".equals(department.getDeptName())) {
				String deptName = "%" + department.getDeptName() + "%";
				model.setDeptName(deptName);
			}
			model.setPrincipal(department.getPrincipal());
			if (department.getPhone() != null && !"".equals(department.getPhone())) {
				String phone = "%" + department.getPhone() + "%";
				model.setPhone(phone);
			}
			if (department.getFax() != null && !"".equals(department.getFax())) {
				String fax = "%" + department.getFax() + "%";
				model.setFax(fax);
			}
			if (department.getCompanyName() != null && !"".equals(department.getCompanyName())) {
				String companyName = "%" + department.getCompanyName() + "%";
				model.setCompanyName(companyName);
			}
			model.setParentCode(department.getParentCode());
			if (department.getZipCode() != null && !"".equals(department.getZipCode())) {
				String zipCode = "%" + department.getZipCode() + "%";
				model.setZipCode(zipCode);
			}
			if (department.getAddress() != null && !"".equals(department.getAddress())) {
				String address = "%" + department.getAddress() + "%";
				model.setAddress(address);
			}
			model.setStatus(department.getStatus());
			model.setValidDate(department.getValidDate());
			model.setInvalidDate(department.getInvalidDate());
			model.setDisplayOrder(department.getDisplayOrder());
			model.setDeptLevel(department.getDeptLevel());
			if (department.getDeptDesc() != null && !"".equals(department.getDeptDesc())) {
				String deptDesc = "%" + department.getDeptDesc() + "%";
				model.setDeptDesc(deptDesc);
			}
			if (department.getDeptSeq() != null && !"".equals(department.getDeptSeq())) {
				String deptSeq = "%" + department.getDeptSeq() + "%";
				model.setDeptSeq(deptSeq);
			}
		}
		list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAll", model, rowBounds);
		return list;
	}

	/**
	 * 更新
	 */
	public void update(Department department) {
		getSqlSession().update("com.deppon.crm.module.organization.shared.domain.Department.update", department);
	}

	/**
	 * 保存对象
	 */
	public void insert(Department department) {
		getSqlSession().insert("com.deppon.crm.module.organization.shared.domain.Department.insert", department);
	}

	/**
	 * 删除，通过id
	 */
	public void deleteById(List<String> idList) {
		getSqlSession().delete("com.deppon.crm.module.organization.shared.domain.Department.deleteById", idList);

	}

	/**
	 * 获得根节点对象
	 */
	public Department getRoot() {
		return (Department) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getRoot");
	}

	/**
	 * 查找符合条件的个数 count
	 * 
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	public Long count(Department department) {
		Department model = new Department();
		if (department != null) {
			if (department.getId() != null && !"".equals(department.getId())) {
				String id = "%" + department.getId() + "%";
				model.setId(id);
			}
			if (department.getDeptCode() != null && !"".equals(department.getDeptCode())) {
				String deptCode = "%" + department.getDeptCode() + "%";
				model.setDeptCode(deptCode);
			}
			if (department.getDeptName() != null && !"".equals(department.getDeptName())) {
				String deptName = "%" + department.getDeptName() + "%";
				model.setDeptName(deptName);
			}
			model.setPrincipal(department.getPrincipal());
			if (department.getPhone() != null && !"".equals(department.getPhone())) {
				String phone = "%" + department.getPhone() + "%";
				model.setPhone(phone);
			}
			if (department.getFax() != null && !"".equals(department.getFax())) {
				String fax = "%" + department.getFax() + "%";
				model.setFax(fax);
			}
			model.setParentCode(department.getParentCode());
			if (department.getCompanyName() != null && !"".equals(department.getCompanyName())) {
				String companyName = "%" + department.getCompanyName() + "%";
				model.setCompanyName(companyName);
			}
			if (department.getZipCode() != null && !"".equals(department.getZipCode())) {
				String zipCode = "%" + department.getZipCode() + "%";
				model.setZipCode(zipCode);
			}
			if (department.getAddress() != null && !"".equals(department.getAddress())) {
				String address = "%" + department.getAddress() + "%";
				model.setAddress(address);
			}
			model.setStatus(department.getStatus());
			model.setValidDate(department.getValidDate());
			model.setInvalidDate(department.getInvalidDate());
			model.setDisplayOrder(department.getDisplayOrder());
			model.setDeptLevel(department.getDeptLevel());
			if (department.getDeptDesc() != null && !"".equals(department.getDeptDesc())) {
				String deptDesc = "%" + department.getDeptDesc() + "%";
				model.setDeptDesc(deptDesc);
			}
			if (department.getDeptSeq() != null && !"".equals(department.getDeptSeq())) {
				String deptSeq = "%" + department.getDeptSeq() + "%";
				model.setDeptSeq(deptSeq);
			}
		}
		Long count = (Long) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.count", model);
		return count;
	}

	/**
	 * 查找符合条件的所有部门 getAll
	 * 
	 * @param department
	 * @return List<Department>
	 * @since JDK1.6
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getAll(Department department) {
		List<Department> list = null;
		Department model = new Department();
		list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAll", model);
		return list;
	}

	/**
	 * 通过id获得所有的子节点的ID getAllChildIdById
	 * 
	 * @param id
	 * @return List<String>
	 * @since JDK1.6
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllChildIdById(String id) {
		List<String> list = null;
		list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllChildIdById", id);
		return list;
	}

	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getAll(Integer start, Integer limit) {
		List<Department> list = null;
		// if (null != start && null != limit) {
		RowBounds bound = new RowBounds(start, limit);
		list = this.getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllDept", bound);
		// } else {
		// list =
		// getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllDept");
		// }
		return list;

	}

	/**
	 * 通过ID，得到对象
	 */
	public Department getById(String id) {
		return (Department) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getById", id);
	}

	public List<Department> getByLikeDeptName(String deptName) {
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getByLikeDeptName", deptName);
	}

	public List<Department> getByLikeDeptNameRow(String deptName, int rows) {
		Map map = new HashMap();
		map.put("deptName", deptName);
		map.put("rows", rows);
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getByLikeDeptNameRow", map);
	}

	public Department getByLikeDeptNameByDeptId(String deptName, String deptId) {
		if (deptId == null) {
			return null;
		}
		Map map = new HashMap();
		map.put("deptName", deptName);
		map.put("deptId", deptId);
		return (Department) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getByLikeDeptNameByDeptId", map);
	}

	public Department getByLikeDeptNameByDeptCode(String deptName, String deptCode) {
		if (deptCode == null) {
			return null;
		}
		Map map = new HashMap();
		map.put("deptName", deptName);
		map.put("deptCode", deptCode);
		return (Department) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getByLikeDeptNameByDeptCode", map);
	}

	public List<Department> getAllChildDeptByDeptId(String id) {
		@SuppressWarnings("unchecked")
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllChildDeptByDeptId", id);
		return list;
	}

	/**
	 * 通过id获得所有的子节点 getAllChildById
	 * 
	 * @param id
	 * @return List<Department>
	 * @since JDK1.6
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getAllChildById(String id) {
		List<Department> list = null;
		list = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllChildById", id);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDeptIdsNoCurrentUserAuthDept(String currentUserId, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<String> ids = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getDeptIdsNoCurrentUserAuthDept", map);
		return ids;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BizDept> getAllBizDept(String id) {
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllBizDept", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAllBizDeptList(String id) {
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllBizDeptList", id);
	}

	@Override
	public String getDeptIdByStandardCode(String standardCode) {
		@SuppressWarnings("unchecked")
		List<String> st = getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getDeptIdByStandardCode", standardCode);
		if (!st.isEmpty()) {
			return st.get(0);
		}
		return null;
	}

	@Override
	public Department getBizDeptByDeptId(String parentId, String deptId) {
		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("deptId", deptId);
		return (Department) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getBizDeptByDeptId", map);
	}

	/**
	 * @作者：钟琼
	 * @时间：2012-11-03
	 * @描述：对集合进行分组 没组不超过 900 个
	 * @参数：depIds 授权部门集合
	 * @返回值：Set<Set> 部门对象集合
	 * */
	public Set<Set> getExecuteDep(Set<String> deptIds) {
		int splitNum = 900; // 一组 900 个
		// int inArrayNum = deptIds.size() % splitNum == 0 ? deptIds.size()
		// / splitNum : deptIds.size() / splitNum + 1;// 参数值一共能分割成多少组，记录总组数
		Set<Set> deptIdsSet = new HashSet<Set>();
		// String[] obj = new String[]{};
		// 对部门集合进行迭代
		Iterator<String> deptIdsIterator = deptIds.iterator();
		// 迭代次数
		int num = 0;
		// 每迭代一次，对象都会放在此集合。
		Set<String> set = new HashSet<String>();
		while (deptIdsIterator.hasNext()) {
			set.add(deptIdsIterator.next());
			num++;
			// 当迭代次数满足一组最大个数的时候
			if (num == splitNum) {
				// 将第一组集合放进 集合当中
				deptIdsSet.add(set);
				// 将迭代次数置0
				num = 0;
				// 当第一组集合置0
				set = new HashSet<String>();
				continue;
			} else if (!deptIdsIterator.hasNext()) {
				deptIdsSet.add(set);
			}
		}
		return deptIdsSet;
	}

	/**
	 * @作者：钟琼
	 * @时间：2012-11-03
	 * @描述：通过授权部门id 找出部门对象集合
	 * @参数：depIds 授权部门集合、deptName 模糊查询名字、start 起始条数、limit 每页条数
	 * @返回值：List<Department> 部门对象集合
	 * */
	@Override
	public List<Department> searchExecuteDep(Set<String> deptIds, String deptName, int start, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		RowBounds boud = new RowBounds(start, limit);
		Set<Set> deptIdsSet = getExecuteDep(deptIds);
		map.put("deptIdsSet", deptIdsSet);
		map.put("deptName", deptName);
		return this.getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.searchExecuteDep", map, boud);
	}

	/**
	 * @作者：钟琼
	 * @时间：2012-11-03
	 * @描述：通过授权部门id 找出部门对象总条数
	 * @参数：depIds 授权部门集合、deptName 模糊查询名字
	 * @返回值：String 部门总条数
	 * */
	@Override
	public String searchExecuteDepCount(Set<String> deptIds, String deptName) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Set> deptIdsSet = getExecuteDep(deptIds);
		map.put("deptIdsSet", deptIdsSet);
		map.put("deptName", deptName);
		return (String) this.getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.searchExecuteDepCount", map);
	}

	/**
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有部门
	 * @参数：deptName 部门名字
	 * @返回值：List<Department> 部门集合
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> queryDeptListByName(String deptName, int start, int limit) {
		RowBounds boud = new RowBounds(start, limit);
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getByLikeDeptName", deptName, boud);
	}

	/**
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有部门 条数
	 * @参数：deptName 部门名字
	 * @返回值：int 条数
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public int queryDeptListByNameCount(String deptName) {
		return (Integer) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getByLikeDeptNameCount", deptName);
	}

	/**
	 * @作者：钟琼
	 * @时间：2013-1-31
	 * @描述：通过部门名称模糊查询所有外场
	 * @参数：deptName 部门名字
	 * @返回值：List<Department> 外场集合
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> queryOutFieldListByName(String deptName, int start, int limit) {
		RowBounds boud = new RowBounds(start, limit);
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.queryOutFieldListByName", deptName, boud);
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
		return (Integer) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.queryOutFieldListByCount", deptName);
	}

	/**
	 * 
	 * <p>
	 * Description:通过部门名称(模糊)与标杆编码查询<br />
	 * </p>
	 * 
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @param deptName
	 * @param standardCode
	 * @return List<Department>
	 */
	@Override
	public List<Department> queryDeptByNameAndStandardCode(String deptName, String standardCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptName", deptName);
		map.put("standardCode", standardCode);
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.queryDeptByNameAndStandardCode", map);
	}

	/**
	 * 
	 * <p>
	 * Description:根据标杆编码查询大区下面的子部门的子部门的名字查询<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-9-5
	 * @param deptName
	 * @param standardCode
	 * @return List<Department>
	 */
	public List<Department> queryDeptByNameAndStandardCodeForEx(String deptName, String standardCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptName", deptName);
		map.put("standardCode", standardCode);
		return getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.queryDeptByNameAndStandardCodeForEx", map);
	}

	@Override
	public Department getByLikeDeptNameByStandardCode(String deptName, String deptCode) {
		if (deptCode == null) {
			return null;
		}
		Map map = new HashMap();
		map.put("deptName", deptName);
		map.put("deptCode", deptCode);
		return (Department) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getByLikeDeptNameByStandardCode", map);
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
		if (deptId == null) {
			return null;
		}
		return (Department) this.getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.queryDeptByParentDeptId", deptId);
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
		if (deptId == null) {
			return null;
		}
		return this.getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllParentDeptByDeptId", deptId);
	}

	/**
	 * 同步UUMS部门数据，进行相应的数据同步，数据权限分配
	 * 
	 * @author ZhangZW
	 * @update 2014-1-6
	 * @param DEPTSTANDCODE
	 *            changeType oldParentStandCode parentStandCode
	 * @return
	 */
	@Override
	public int syncDep(String deptStandCode, String changeType, String oldParentStandCode) {
		int res = 0;
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptStandCode", deptStandCode);
		map.put("changeType", changeType);
		map.put("oldParentStandCode", oldParentStandCode);
		res = getSqlSession().update("com.deppon.crm.module.organization.shared.domain.Department.syncDep", map);
		return res;
	}

	@Override
	public Department getDeptByStandCode(String StandardCode) {
		Department dep = (Department) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department.getDeptByStandCode", StandardCode);
		return dep;
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
	@Override
	public Department getAllParentDeptBystandCode(Map<String, String> map) {
		if (map.get("lastWord") != null) {
			map.put("lastWord", "%" + map.get("lastWord"));
		}
		return (Department) this.getSqlSession().selectOne(
				"com.deppon.crm.module.organization.shared.domain.Department.getAllParentDeptBystandCodeAndLastWord", map);
	}

	/**
	 * 
	 * <p>
	 * Description:模糊查找部门列表<br />
	 * </p>
	 * 
	 * @author andy
	 * @version 0.1 2014-3-28
	 * @param deptName
	 *            模糊部门名称
	 * @return List<Department>
	 */
	@Override
	public List<Department> getDepartmentListByLikeDeptName(String deptName) {
		return this.getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getDepartmentListByLikeDeptName", deptName);
	}

	@Override
	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession().selectOne("com.deppon.crm.module.organization.shared.domain.Department." + "getLastModifyTime");
		return lastModyfyTime;
	}

	@Override
	public List<Department> getAllDepartments() {

		List<Department> departments=this.getSqlSession().selectList("com.deppon.crm.module.organization.shared.domain.Department.getAllDepartments");
		return departments;
	}

}
