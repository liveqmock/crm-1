package com.deppon.crm.module.customer.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.shared.exception.UserException;
import com.deppon.crm.module.authorization.shared.exception.UserExceptionType;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;
/**
 * 
 * <p>
 * 上下文操作工具类<br />
 * </p>
 * @title ContextUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author bxj
 * @version 0.2 2012-3-20
 */
public class ContextUtil {
	/**
	 * 
	 * <p>
	 * 得到当前用户的部门Id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-20
	 * @return
	 * String
	 */
	public static String getCurrentUserDeptId(){
		return getCurrentUserDept().getId();
	}
	/**
	 * 
	 * <p>
	 * 得到当前用户所属部门<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @return
	 * Department
	 */
	public static Department getCurrentUserDept(){
		User user = (User)UserContext.getCurrentUser();
		if(user == null){
			Department dept = new Department();
			dept.setId("104");
			dept.setDeptName("总裁");
			dept.setStandardCode("DP00003");
			return dept;
		}
		return  ((User) user).getEmpCode().getDeptId();
	}
	
	/**
	 * 
	 * <p>
	 * 得到当前用户的EmpId<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-20
	 * @return
	 * String
	 */
	public static String getCurrentUserEmpId(){
		IUser user = getCurrentUser();
		return ((User)user).getEmpCode().getId();
	}
	/**
	 * 
	 * <p>
	 * 得到创建人或修改人userId，当没有人登陆系统时，默认为超级管理员<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-18
	 * @return
	 * String
	 */
	public static String getCreateOrModifyUserId(){
		User user = (User)UserContext.getCurrentUser();
		if(user==null){
			//默认为超级管理员
			return Constant.SuperManUserId;
		}else{
			return user.getEmpCode().getId();
		}
	}
	/**
	 * 
	 * <p>
	 * 得到创建人或修改人userId，当没有人登陆系统时，默认为超级管理员<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-18
	 * @return
	 * String
	 */
	public static String getCreateOrModifyUserName(){
		User user = (User)UserContext.getCurrentUser();
		if(user==null){
			//默认为超级管理员
			return Constant.SuperManUserName;
		}else{
			return user.getEmpCode().getEmpName();
		}
	}
	
	/**
	 * 
	 * <p>
	 * 得到当前用户的数据权限部门Id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-20
	 * @return
	 * List<String>
	 */
	public static List<String> getCurrentUserDeptIds(){
		IUser user = getCurrentUser();
		
		Set<String> deptIds = ((User) user).getDeptids();
		List<String> deptIdList = new ArrayList<String>();
		for (String deptId : deptIds) {
			deptIdList.add(deptId);
		}
		return deptIdList;
	}
	/**
	 * 
	 * <p>
	 * 得到当前用户的可阅读的数据的部门id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-3-20
	 * @return
	 * List<String>
	 */
	public static List<String> getCurrentUserDataAuthorityDept(){
		List<String> deptIds = getCurrentUserDeptIds();
		if(ValidateUtil.objectIsEmpty(deptIds)){
			deptIds = new ArrayList<String>();
			String deptId = getCurrentUserDeptId();
			deptIds.add(deptId);
		}
		
		return deptIds;
	}
	/**
	 * 
	 * <p>
	 * 得到当前用户的全部角色id<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-12
	 * @return
	 * List<String>
	 */
	public static Set<String> getCurrentUserRoleIds(){
		IUser user = getCurrentUser();
		return user.getRoleids();
	}
	/**
	 * 
	 * <p>
	 * 查询当前用户的名称<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-18
	 * @return
	 * String
	 */
	public static String getCurrentUserName() {
		IUser user = getCurrentUser();
		return  ((User) user).getEmpCode().getEmpName();
	}
	
	public static User getCurrentUser(){
		User user = (User)UserContext.getCurrentUser();
		
		if(user == null){
			throw new UserException(UserExceptionType.UserIsNull);
		}
		return user;
	}
	/**
	 * 
	 * <p>
	 * 得到数据权限<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-11
	 * @param deptId
	 * @return
	 * List<String>
	 */
	public static List<String> getDataAuthorityDepts(String dept) {
		List<String> depts = null;
		if(ValidateUtil.objectIsEmpty(dept)){
			depts = ContextUtil.getCurrentUserDataAuthorityDept();
		}else{
			depts = new ArrayList<String>();
			depts.add(dept);
		}
		return depts;
	}
	/**
	 * 
	 * <p>
	 * 得到当前用户所属部门名称<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-2
	 * @return
	 * String
	 */
	public static String getCurrentUserDeptName() {
		return getCurrentUserDept().getDeptName();
	}
	/**
	 * 
	 * <p>
	 * 得到当前登录用户的角色<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-13
	 * @return
	 * Set<String>
	 */
	public static Set<String> getRoles(){
		return getCurrentUser().getRoleids();
	}
	
}
