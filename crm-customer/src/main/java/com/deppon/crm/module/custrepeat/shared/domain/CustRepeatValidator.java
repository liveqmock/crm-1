package com.deppon.crm.module.custrepeat.shared.domain;


import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.custrepeat.shared.exception.CustRepeatException;
import com.deppon.crm.module.custrepeat.shared.exception.CustRepeatExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;


/**
 * 疑似重复客户验证
 * @author 120750
 *
 */
public class CustRepeatValidator {
	
	/**
	 * 验证部门编号是否为空
	 * @param searchCondition
	 */
	public static void validDeptId(SearchCondition searchCondition){
		/**
		 * 验证部门ID是否为空
		 */
		if(null == searchCondition.getDeptId() || "".equals(searchCondition.getDeptId().trim())){
			/**
			 * 抛出异常
			 */
			throw new CustRepeatException(CustRepeatExceptionType.DEPTID_ISNULL.getErrCode(),new Object[]{});
		}
	}
	
	
	/**
	 * 验证主客户编号是否为空
	 * @param searchCondition
	 */
	public static void validMainCustId(SearchCondition searchCondition){
		//主客户ID
		String mainCustId = searchCondition.getMainCustId();
		//是否随机查询一组疑似客户
		Integer isRandomOneGroup  =  searchCondition.getIsRandomOneGroup();
		if(
				(null == mainCustId || "".equals(mainCustId.trim()))
				&&
				(null == isRandomOneGroup || 1 != isRandomOneGroup.intValue())
		){
			/**
			 * 抛出异常
			 */
			throw new CustRepeatException(CustRepeatExceptionType.MAINCUSTID_ISNULL.getErrCode(),new Object[]{});
		}
	}
	
	/**
	 * 验证列表是否存在本部门客户
	 * @param deptName 本部门名称
	 * @param custList 列表
	 */
	public static void validExistCurrentDept(String deptName,List<RepeatedCustomer> custList){
		boolean isExist = false; // 默认不存在
		for (RepeatedCustomer rept : custList) {
			//存才本部门，跳出循环
			if(deptName.equals(rept.getDeptName())){
				isExist = true;break;
			}
		}
		//不存在本部门客户
		if(isExist==false){
			/**
			 * 抛出异常
			 */
			throw new CustRepeatException(CustRepeatExceptionType.CUST_NOTEXISTCUST_OFOURDEPT.getErrCode(),new Object[]{});
		}
	}
	/**
	 * <p>
	 *	Description: 验证工作流信息是否为空
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-8
	 * @param info
	 * void
	 */
	public static void validWorkFlowInfo(RepetitiveCustWorkFlowInfo info){
		if(null == info){
			/**
			 * 抛出异常
			 */
			throw new CustRepeatException(CustRepeatExceptionType.WORKFLOWINFO_ISNULL.getErrCode(),new Object[]{});
		}
	}
	
	/**
	 * 验证查询客户条件
	 * @param searchCondition
	 */
	public static void validCustomer(SearchCondition searchCondition){
		//客户编码为空验证
		if(null == searchCondition.getCustCode() || "".equals(searchCondition.getCustCode().trim())){
			/**
			 * 抛出异常
			 */
			throw new CustRepeatException(CustRepeatExceptionType.CUSTCODE_ISNULL.getErrCode(),new Object[]{});
		}
	}
	
	/**
	 * 验证当前登录人师父有权限查询部门的客户
	 * @param user 当前登录人
	 * @param dept 所选部门
	 */
	public static void validDepartment(User user,Department dept){
		if(null == dept){
			/**
			 * 抛出异常
			 */
			throw new CustRepeatException(CustRepeatExceptionType.CUSTCODE_ISNULL.getErrCode(),new Object[]{});
		}
	}
	
	/**
	 * 得到部门所属级别
	 * @param dept
	 * @return
	 */
	public static String getDeptLevel(Department dept){
		//部门名称
		String  deptName = dept.getDeptName();
		//大区
		if(deptName.lastIndexOf("大区") != -1){
			//返回大区标示
			return CustRepeatConstants.DEPT_BIG_REGION;
		}
		//营业区
		if(deptName.lastIndexOf("营业区") != -1){
			//返回营业区
			return CustRepeatConstants.DEPT_SMALL_REGION;
		}
		// 返回普通客户
		return CustRepeatConstants.DEPT_ORDINARY;
	}
	/**
	 * <p>
	 *	Description: 判断部门ID是否为空
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-17
	 * @param deptId
	 * void
	 */
	public static void validDeptId(String deptId){
		//部门ID 非空验证
		if(null == deptId || "".equals(deptId)){
			/**
			 * 抛出异常
			 */
			throw new CustRepeatException(CustRepeatExceptionType.DEPTID_ISNULL.getErrCode(),new Object[]{});
		}
	}
}
