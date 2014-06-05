package com.deppon.crm.module.authorization.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.dao.IAuthorizeDao;
import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.server.util.AuthorizeUtil;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.shared.exception.UserException;
import com.deppon.crm.module.authorization.shared.exception.UserExceptionType;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

@Transactional()
public class AuthorizeService implements IAuthorizeService {
	//修改：增加超级管理的工号定义 修改人：ztjie 修改时间：2012-04-17
	private static final String CURRENT_USER_ID = "sysadmin";
	private static final String CURRENT_USER_172 = "000000";
	private IAuthorizeDao authorizeDao;
	
	@SuppressWarnings("serial")
	@Transactional(readOnly = true)
	public List<Role> queryAllChooesRole(String currentUserId,String userId) {
		if(userId==null&&"".equals(userId)){
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		List<Role> roleList  = authorizeDao.getAllChooesRoleByUserId(currentUserId,userId);
		return roleList;
	}

	@SuppressWarnings("serial")
	@Transactional(readOnly = true)
	public List<Role> queryAllAuthorizeRole(String currentUserId,String userId) {
		if(userId==null&&"".equals(userId)){
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		List<Role> roleList  = authorizeDao.getAllAuthorizedRoleByUserId(currentUserId,userId);
		return roleList;
	}

	@SuppressWarnings("serial")
	@Transactional(readOnly = true)
	public List<String> queryAllDepartmentIdByUserId(String userId) {
		if(userId==null&&"".equals(userId)){
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		List<String> departmentIds  = authorizeDao.getAllDepartmentIdByUserId(null,userId);
		return departmentIds;
	}
	
	@SuppressWarnings("serial")
	@Transactional(readOnly = true)
	public List<String> queryChildDepartmentIdByUserId(String currentUserId,String userId,String parentDeptId) {
		if(userId==null&&"".equals(userId)){
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		List<String> departmentIds  = authorizeDao.getChildDepartmentIdByUserId(currentUserId,userId,parentDeptId);
		return departmentIds;
	}

	@SuppressWarnings("serial")
	public void save(String userId, List<String> departmentIds,List<String> departmentIdsRemove,
			List<String> roleIds) {
		//得到当前登录用户
		User currentUser = (User)(UserContext.getCurrentUser());
		//得到当前用户工号
		String currentUserEmpCode = currentUser.getEmpCode().getEmpCode();
		if(userId==null&&"".equals(userId)){
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		/*修改人：张斌
		修改时间：2013-7-3 14:00
		修改内容：判断当前登录用户是否为超级管理员，如果是超级管理员，则非当前用户授权的角色信息为空*/
		List<Role> roleList = new ArrayList<Role>();
		if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
			
		}else{
			//修改人:ztjie
			//修改时间：2012-04-17
			//修改说明：得到非当前用户授权与角色信息
			//修改前：
			//修改后:
			//得到非当前用户授权的部门信息
			//得到非当前用户授权的角色信息
			roleList = authorizeDao.getAllChooesRoleByUserId(userId,UserContext.getCurrentUser().getId());
			//修改结束
		}
		//删除用户角色授权信息
		authorizeDao.deleteUserRoleAuthByUserId(userId);
		if(departmentIds!=null&&departmentIds.size()>0){
			//修改结束
			for(String deptId:departmentIds){
				authorizeDao.insertUserDeptAuth(userId,deptId);
			}
		}
		if(departmentIdsRemove!=null&&departmentIdsRemove.size()>0){//批量删除，每次1000条
			int begin = 0 ;
			int end =0;
			int size = 1000;//每次删除1000条
			while (true) {
				end =(begin+size)>departmentIdsRemove.size()?departmentIdsRemove.size():begin+size;
				if(end == 0){
					break;
				}
				//修改人:zhangbin
				//修改时间：2013-07-18 9:15
				List<String> depts = departmentIdsRemove.subList(begin,end);
				authorizeDao.deleteUserDeptAuthAll(userId,depts);
				if(end>=departmentIdsRemove.size()){
					break;
				}
				begin+=size;
			}
		}
		
		if(roleIds!=null&&roleIds.size()>0){
			//修改人:ztjie
			//修改时间：2012-04-17
			//修改说明：把非当前用户授权的角色ID加入到待授权的角色信息表中
			//修改前：
			//修改后:
			//把非当前用户授权的角色ID加入到待授权的角色信息表中
			for(Role role : roleList){
				roleIds.add(role.getId());
			}
			//修改结束
			for(String roleId:roleIds){
				authorizeDao.insertUserRoleAuth(userId,roleId);
			}
		}
	}
	@Override
	public List<Department> getMyAuthDepts() {
		IUser user = UserContext.getCurrentUser();
		if(user==null){
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		String userId = user.getId();
		List<Department> departments  = authorizeDao.getAllDepartmentByUserId(userId);
		return departments;
	}
	
	@Override
	public List<Department> getAllleafByParentId(String deptId,int start,int limit) {
		if (Constant.CUSTOMER_ALLAUTHORIZE_DEPARTMENT.equals(deptId)) {
			deptId = Constant.CUSTOMER_ALLAUTHOR_DEPTIDS;
		}
		List<String> deptIds = AuthorizeUtil.parseStringToList(deptId);
		List<Department> list = null;
		if (null != deptIds && 0 < deptIds.size()) {
			list = authorizeDao.getAllleafByParentId(deptIds,start,limit);
		}
		return list;
	}

	@Override
	public int countAllleafByParentId(String deptId) {
		if (Constant.CUSTOMER_ALLAUTHORIZE_DEPARTMENT.equals(deptId)) {
			deptId = Constant.CUSTOMER_ALLAUTHOR_DEPTIDS;
		}
		List<String> deptIds = AuthorizeUtil.parseStringToList(deptId);
		int count = 0;
		if (null != deptIds && 0 < deptIds.size()) {
			count = authorizeDao.countAllleafByParentId(deptIds);
		}
		return count;
	}

	@Override
	public List<Department> getAllleafByParentIdAndLikeDeptName(String deptId,
			String deptName, int start, int limit) {
		if (Constant.CUSTOMER_ALLAUTHORIZE_DEPARTMENT.equals(deptId)) {
			deptId = Constant.CUSTOMER_ALLAUTHOR_DEPTIDS;
		}
		List<String> deptIds = AuthorizeUtil.parseStringToList(deptId);
		List<Department> list = null;
		if (null != deptIds && 0 < deptIds.size()) {
			list = authorizeDao.getAllleafByParentIdAndLikeDeptName(deptIds, deptName, start, limit);
		}
		return list;
	}

	@Override
	public int countAllleafByParentIdAndLikeDeptName(String deptId,
			String deptName) {
		if (Constant.CUSTOMER_ALLAUTHORIZE_DEPARTMENT.equals(deptId)) {
			deptId = Constant.CUSTOMER_ALLAUTHOR_DEPTIDS;
		}
		List<String> deptIds = AuthorizeUtil.parseStringToList(deptId);
		int count = 0;
		if (null != deptIds && 0 < deptIds.size()) {
			count = authorizeDao.countAllleafByParentIdAndLikeDeptName(deptIds, deptName);
		}
		return count;
	}

	public IAuthorizeDao getAuthorizeDao() {
		return authorizeDao;
	}

	public void setAuthorizeDao(IAuthorizeDao authorizeDao) {
		this.authorizeDao = authorizeDao;
	}

	@Override
	public List<Department> getAllDepartmentByUserId(String userId, int start,
			int limit) {
		return authorizeDao.getAllDepartmentByUserId(userId, start, limit);
	}

	@Override
	public int countAllDepartmentByUserId(String userId) {
		return authorizeDao.countAllDepartmentByUserId(userId);
	}

	@Override
	public List<Department> getAllDepartmentByUserIdAndLikeDeptName(String userId,
			String deptName, int start, int limit) {
		return authorizeDao.getAllDepartmentByUserIdAndLikeDeptName(userId, deptName, start, limit);
	}

	@Override
	public int countAllDepartmentByUserIdAndLikeDeptName(String userId, String deptName) {
		return authorizeDao.countAllDepartmentByUserIdAndLikeDeptName(userId, deptName);
	}

	@Override
	public List<Department> getAllDepartmentsByIds(List<String> deptIds) {
		return authorizeDao.getAllDepartmentsByIds(deptIds);
	}
	@Override
	public List<Department> getWaybillRightsDepartmentsByDeptId(String deptId,
			int start, int limit) {
		List<Department> departments = null;
		if (null != deptId && !"".equals(deptId)) {
			departments = authorizeDao.getWaybillRightsDepartmentsByDeptId(deptId, start, limit);
		}
		return departments;
	}

	@Override
	public List<Department> getWaybillRightsDepartmentsByDeptName(String deptId,
			String deptName, int start, int limit) {
		List<Department> departments = null;
		if (null != deptName && !"".equals(deptName)) {
			departments = authorizeDao.getWaybillRightsDepartmentsByDeptName(deptId,deptName, start, limit);
		}
		return departments;
	}


	@Override
	public int countAllDepartmentsByDeptId(String deptId) {
		return authorizeDao.countAllDepartmentsByDeptId(deptId);
	}

	@Override
	public int countAllDepartmentsByDeptName(String deptId, String deptName) {
		return authorizeDao.countAllDepartmentsByDeptName(deptId, deptName);
	}

	@Override
	public List<Department> getAllDepartments(String deptName,int start, int limit) {
		return authorizeDao.getAllDepartments( deptName, start,  limit);
	}
	@Override
	public List<String> getAllSignCompanys(String companyName,int start, int limit) {
		return authorizeDao.getAllSignCompanys(companyName, start,  limit);
	}

	@Override
	public int countAllDepartments(String deptName) {
		return authorizeDao.countAllDepartments(deptName);
	}
	
	@Override
	public int countAllSignCompanys(String companyName) {
		return authorizeDao.countAllSignCompanys(companyName);
	}

	/**
	 * Description:增加校验是否有其子节点的<br />
	 * @version 0.1 2013-7-3 9:00
	 * @param deptId 当前校验部门ID
	 * @param deptIds 所有有权限部门ID
	 * @return Integer
	 */
	@Override
	public Integer checkIsHaveChildNode(String deptId, List<String> deptIds){
		return authorizeDao.checkIsHaveChildNode(deptId,deptIds);
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
	public List<String> getHavedDepartmentId(String currentUserId, String userId,
			String parentDeptId){
		return authorizeDao.getHavedDepartmentId(currentUserId,userId,parentDeptId);
	}

	/**
	 * 查询点部/分部/快递大区/映射的营业部
	 */
	@Override
	public List<Department> getAllDeptMappedExpress(String userId,
			String deptName, String deptStandardCode, int start, int limit) {
		return authorizeDao.getAllDeptMappedExpress(userId,deptName, deptStandardCode, start, limit);
	}
	/**
	 * 查询点部/分部/快递大区映射的营业部数量
	 */
	@Override
	public int countAllDeptMappedExpress(String userId, String deptStandardCode,
			String position) {
		return authorizeDao.countAllDeptMappedExpress(userId, deptStandardCode,position);
	}

	@Override
	public 	List<Department> getAllDeptMappedExpressForMonitor(String userId,String deptName,
			int start, int limit) {
		return authorizeDao.getAllDeptMappedExpressForMonitor(userId,deptName,start,limit);
	}

	@Override
	public int countAllDeptMappedExpressForMonitor(String userId,String deptName) {
		return authorizeDao.countAllDeptMappedExpressForMonitor(userId,deptName);
	}

	@Override
	public List<Department> getAllRegionAndDivision(String deptName, int start,
			int limit) {
		return authorizeDao.getAllRegionAndDivision(deptName,start,limit);
	}

	@Override
	public int countAllRegionAndDivision(String deptName) {
		return authorizeDao.countAllRegionAndDivision(deptName);
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
		return authorizeDao.getChildDeptIds(dep,userId,deptCode);
	}
}
