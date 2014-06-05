package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.server.dao.IAuthorizeDao;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

@SuppressWarnings("unchecked")
public class AuthorizeDao extends iBatis3DaoImpl implements IAuthorizeDao {

	public List<Role> getAllChooesRoleByUserId(String currentUserId,String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<Role> roleList = getSqlSession().selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getAllChooesRoleByUserId",map);
		return roleList;
	}

	public List<Role> getAllAuthorizedRoleByUserId(String currentUserId,String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<Role> roleList = getSqlSession().selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getAllAuthorizedRoleByUserId",
						map);
		return roleList;
	}

	public List<String> getAllDepartmentIdByUserId(String currentUserId,String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<String> list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllDepartmentIdByUserId",map);
		return list;
	}
	
	public List<String> getChildDepartmentIdByUserId(String currentUserId,String userId,String parentDeptId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		map.put("parentDeptId", parentDeptId);
		List<String> list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getChildDepartmentIdByUserId",map);
		return list;
	}
	
	@Override
	public List<Department> getAllDepartmentByUserId(String userId) {
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllDepartmentByUserId",userId);
		return list;
	}
	
	@Override
	public List<Department> getAllleafByParentId(List<String> deptIds,int start,int limit) {
		RowBounds rb = new RowBounds(start,limit);
		Map map = new HashMap();
		map.put("deptIds", deptIds);
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllleafByParentId",map,rb);
		return list;
	}
	
	public void insertUserDeptAuth(String userId, String deptId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("deptId", deptId);
		getSqlSession().insert("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.insert",map);
	}

	public void insertUserRoleAuth(String userId, String roleId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		getSqlSession().insert("com.deppon.crm.module.authorization.shared.domain.UserAuthRole.insert",map);
	}

	public void deleteUserDeptAuthByUserId(String userId) {
		getSqlSession().delete("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.deleteByUserId",userId);
	}

	public void deleteUserRoleAuthByUserId(String userId) {
		getSqlSession().delete("com.deppon.crm.module.authorization.shared.domain.UserAuthRole.deleteByUserId",userId);
	}

	@Override
	public int countAllleafByParentId(List<String> deptIds) {
		Map map = new HashMap();
		map.put("deptIds", deptIds);
		return (Integer) getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllleafByParentId",map);
	}

	@Override
	public List<Department> getAllDepartmentByUserId(String userId, int start,
			int limit) {
		RowBounds rb = new RowBounds(start,limit);
		List<Department> list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllDepartmentByUserId",userId,rb);
		return list;
	}

	@Override
	public int countAllDepartmentByUserId(String userId) {
		return (Integer) getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllDepartmentByUserId",userId);
	}

	@Override
	public List<Department> getAllDepartmentByUserIdAndLikeDeptName(String userId,
			String deptName, int start, int limit) {
		Map map = new HashMap();
		map.put("deptName", "%"+deptName+"%");
		map.put("userId", userId);
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllDepartmentByUserIdAndLikeDeptName",map,rb);
	}

	@Override
	public int countAllDepartmentByUserIdAndLikeDeptName(String userId, String deptName) {
		Map map = new HashMap();
		map.put("deptName", "%"+deptName+"%");
		map.put("userId", userId);
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllDepartmentByUserIdAndLikeDeptName",map);
	}

	@Override
	public List<Department> getAllleafByParentIdAndLikeDeptName(List<String> deptIds,
			String deptName, int start, int limit) {
		Map map = new HashMap();
		map.put("deptName", "%"+deptName+"%");
		map.put("deptIds", deptIds);
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllleafByParentIdAndLikeDeptName",map,rb);
	}

	@Override
	public int countAllleafByParentIdAndLikeDeptName(List<String> deptIds,
			String deptName) {
		Map map = new HashMap();
		map.put("deptName", "%"+deptName+"%");
		map.put("deptIds", deptIds);
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllleafByParentIdAndLikeDeptName",map);
	}

	@Override
	public List<Department> getAllDepartmentsByIds(List<String> deptIds) {
		if(deptIds==null||deptIds.size()<1){
			return null;
		}else{
			String seqIn = "(";
			for(int i = 0;i<deptIds.size();i++){
				if(i==deptIds.size()-1){
					seqIn = seqIn+"'"+deptIds.get(i)+"'"+")";
				}else{
					seqIn = seqIn+"'"+deptIds.get(i)+"'"+",";
				}
			}
			Map map = new HashMap();
			map.put("seqIn", seqIn);
			return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserDepartment.getAllDepartmentsByIds",map);
		}
		
	}

	@Override
	public List<Department> getWaybillRightsDepartmentsByDeptId(
			String deptId, int start, int limit) {
		RowBounds rb = new RowBounds(start, limit);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getWaybillRightsDepartmentsByDeptId"
				,deptId,rb );
	}

	@Override
	public List<Department> getWaybillRightsDepartmentsByDeptName(String deptId,
			String deptName, int start, int limit) {
		Map map = new HashMap();
		map.put("deptName",deptName);
		map.put("deptId", deptId);
		RowBounds rb = new RowBounds(start, limit);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getWaybillRightsDepartmentsByDeptName"
				,map,rb);
	}

	@Override
	public int countAllDepartmentsByDeptId(String deptId) {
		return (Integer)this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllDepartmentsByDeptId", deptId);
	}

	@Override
	public int countAllDepartmentsByDeptName(String deptId, String deptName) {
		Map map = new HashMap();
		map.put("deptName", deptName);
		map.put("deptId", deptId);
		return (Integer)this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllDepartmentsByDeptName", map);
	}

	@Override
	public List<Department> getAllDepartments(String deptName,int start, int limit) {
		RowBounds rb = new RowBounds(start, limit);
		//Ĭ��
		Map map = new HashMap();
		if (!StringUtils.isEmpty(deptName)) {
			map.put("deptName", '%'+deptName+'%');
		}else {
			map.put("deptName", deptName);
		}
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllDepartments",map,rb);
	}
	@Override
	public List<String> getAllSignCompanys(String companyName,int start, int limit) {
		RowBounds rb = new RowBounds(start, limit);
		//Ĭ��
		Map map = new HashMap();
		if (!StringUtils.isEmpty(companyName)) {
			map.put("companyName", '%'+companyName+'%');
		}else {
			map.put("companyName", companyName);
		}
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllSignCompanys",map,rb);
	}

	@Override
	public int countAllDepartments(String deptName) {
		//Ĭ��
		Map map = new HashMap();
		if (!StringUtils.isEmpty(deptName)) {
			map.put("deptName", '%'+deptName+'%');
		}else {
			map.put("deptName", deptName);
		}
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllDepartments",map);
	}
	@Override
	public int countAllSignCompanys(String companyName) {
		//Ĭ��
		Map map = new HashMap();
		if (!StringUtils.isEmpty(companyName)) {
			map.put("companyName", '%'+companyName+'%');
		}else {
			map.put("companyName", companyName);
		}
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllCompanys",map);
	}
	/**
	 * Description:增加校验是否有其子节点的<br />
	 * @version 0.1 2013-7-3 9:00
	 * @param deptId 当前校验部门ID
	 * @param deptIds 所有有权限部门ID
	 * @return Integer
	 */
	@Override
	public Integer checkIsHaveChildNode(String deptId,List<String> deptIds){
		Map map = new HashMap();
		map.put("deptId", deptId);
		map.put("deptIds", deptIds);
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.checkIsHaveChildNode",map);
	};
	/**
	 * Description:得到当前登录用户可分配，选择用户已拥有父节点是展开节点的部门的ID<br />
	 * @version 0.1 2013-7-3 10:00
	 * @param currentUserId 登录用户
	 * @param userId 选择用户
	 * @param parentDeptId 展开节点
	 * @return List<String>
	 */
	@Override
	public List<String> getHavedDepartmentId(String currentUserId,String userId,String parentDeptId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		map.put("parentDeptId", parentDeptId);
		List<String> list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getHavedDepartmentId",map);
		return list;
	}
	/**
	 * Description:根据条件删除<br />
	 * @version 0.1 2013-7-3 10:00
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 */
	@Override
	public void deleteUserDeptAuth(String userId, String deptId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("deptId", deptId);
		getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserDepartment.deleteUserDept",map);
	}
	
	
	/**
	 * Description:根据条件批量删除<br />
	 * @version 0.1 2013-7-18 10:00
	 * @param userId 用户ID
	 * @param deptIds 部门ID
	 */
	@Override
	public void deleteUserDeptAuthAll(String userId, List<String> deptIds) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("deptIds", deptIds);
		getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserDepartment.deleteUserDeptAuthAll",map);
	}

	@Override
	public List<Department> getAllDeptMappedExpress(String userId,
			String deptName, String deptStandardCode,int start, int limit) {
		RowBounds rb = new RowBounds(start,limit);
		Map<String,String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("deptName", deptName);
		map.put("deptStandardCode", deptStandardCode);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllDeptMappedExpress", map, rb);
	}

	@Override
	public int countAllDeptMappedExpress(String userId, String deptName,
			String deptStandardCode) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("deptName", deptName);
		map.put("deptStandardCode", deptStandardCode);
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllDeptMappedExpress",map);
	}

	@Override
	public List<Department> getAllDeptMappedExpressForMonitor(String userId, String deptName,
			int start, int limit) {
		RowBounds rb = new RowBounds(start, limit);
		Map<String,String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("deptName", deptName);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllDeptMappedExpressForMonitor", params, rb);
	}

	@Override
	public int countAllDeptMappedExpressForMonitor(String userId, String deptName) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("deptName", deptName);
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllDeptMappedExpressForMonitor",params);
	}

	@Override
	public List<Department> getAllRegionAndDivision(String deptName, int start,
			int limit) {
		Map<String,String> params = new HashMap<String, String>();
		RowBounds rb = new RowBounds(start,limit);
		params.put("deptName", deptName);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getAllRegionAndDivision", params, rb);
	}

	@Override
	public int countAllRegionAndDivision(String deptName) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("deptName", deptName);
		return (Integer) this.getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.countAllRegionAndDivision", params);
	}
	/**
	 * 
	 * <p>
	 * Description:根据下拉列表选择的部门，查找子部门及下级部门Id<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-1-15
	 * @param dep
	 * @param userId
	 * @return
	 * List<String>
	 */
	@Override
	public List<String> getChildDeptIds(String dep, String userId,String deptCode) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("dep", dep);
		params.put("userId", userId);
		params.put("deptCode", deptCode);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserAuthDepartment.getChildDeptIds", params);
	}
	
}
