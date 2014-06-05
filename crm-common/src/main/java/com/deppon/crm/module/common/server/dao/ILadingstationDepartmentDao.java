/**   
 * @title ILadingstationDepartment.java
 * @package com.deppon.crm.module.common.server.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-3-22 下午2:50:16
 * @version V1.0   
 */
package com.deppon.crm.module.common.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;

/**
 * @description 始发网点与受理部门关系 DAO接口
 * @author 安小虎
 * @version 0.1 2012-3-22
 * @date 2012-3-22
 */

public interface ILadingstationDepartmentDao {
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：保存网点信息
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	public int saveLadingstationDepartment(BussinessDept bussinessDept);
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据标杆编码修改网点信息
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	public int updateLadingstationDepartment(BussinessDept bussinessDept);
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据ERPID查询网点信息(现为FOSS的ID)
	 * @参数：ERPID(现为FOSS的ID)
	 * @返回：网点信息
	 * */
	public BussinessDept queryBussinessDeptByERPID(String erpId);
	
	/**
	 * 
	 * @description 始发网点与受理部门关联DAO新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-22
	 * @return int
	 * @update 2012-3-22 下午5:13:28
	 */
	public int save(
			LadingstationDepartment ladingstationDepartment);

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO修改接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-22
	 * @return int
	 * @update 2012-3-22 下午5:13:28
	 */
	public int update(
			LadingstationDepartment ladingstationDepartment);

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据ID删除接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-22
	 * @return int
	 * @update 2012-3-22 下午5:13:28
	 */
	public int delete(String ladingstationDepartmentId);

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据ID获得接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param ID
	 * @date 2012-3-23
	 * @return 对象
	 * @update 2012-3-23 上午9:18:50
	 */
	public LadingstationDepartment getById(String id);

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据条件查询接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-22
	 * @return List
	 * @update 2012-3-22 下午5:13:28
	 */
	public List<LadingstationDepartment> searchByCondition(
			LadingstationDepartment ladingstationDepartment,int start, int limit);

	/**
	 * 
	 * @description 始发网点与受理部门关联DAO根据条件查询记录数接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 条件
	 * @date 2012-3-23
	 * @return 条数
	 * @update 2012-3-23 上午9:19:54
	 */
	public Long getCountByCondition(LadingstationDepartment ladingstationDepartment);

	/**
	 * 
	 * @description 根据始发网点名称模糊查询始发网点接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-24
	 * @param 始发网点名称
	 * @date 2012-3-24
	 * @return 始发网点集合
	 * @update 2012-3-24 上午10:21:52
	 */
	public List<BussinessDept> getLeaveBusDeptByName(BusDeptSearchCondition condition) ;

	/**
	 * 
	 * @description 根据到达网点名称模糊查询到达网点接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-24
	 * @param 到达网点名称
	 * @date 2012-3-24
	 * @return 到达网点集合
	 * @update 2012-3-24 上午10:21:52
	 */
	public List<BussinessDept> getArriveBusDeptByName(BusDeptSearchCondition condition) ;

	/**
	 * 
	 * @description 根据始发网点ID查询受理部门接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-24
	 * @param 始发网点ID
	 * @date 2012-3-24
	 * @return 受理部门对象
	 * @update 2012-3-24 上午10:25:37
	 */
	public List <LadingstationDepartment> getAcceptDeptByConfigurator(String deptId,boolean isReceiveGoods,String resource);
	
	/**
	 * 
	 * @description 根据网点id查询网点信息.  
	 * @author 潘光均
	 * @version 0.1 2012-3-26
	 * @param String
	 *@date 2012-3-26
	 * @return BussinessDept
	 * @update 2012-3-26 下午5:05:37
	 */
	public BussinessDept getBusDeptById(String deptId);

	/**
	 * 
	 * @description 根据到名称查询网点.
	 * @author 安小虎
	 * @version 0.1 2012-3-24
	 * @param String
	 * @date 2012-3-24
	 * @return BussinessDept
	 * @update 2012-3-24 上午10:21:52
	 */
	public BussinessDept getBusDeptByName(String startStation);
	
	/**
	 * <p>
	 * Description:获取所有始发网点与受理部门关联对象<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-5-8
	 * @param start
	 * @param limit
	 * @return
	 * List<LadingstationDepartment>
	 */
	public List<LadingstationDepartment> getAllLandingStationDepartment(int start, int limit);

	
	/**
	 * 获取所有始发网点与受理部门关联对象的总数
	 * @return
	 */
	public Long getAllLandingStationDepartmentCount(); 	
	
	
	/**
	 * @description 通过部门编码查询网点信息.  
	 * @author 潘光均
	 * @version 0.1 2012-5-23
	 * @param 
	 *@date 2012-5-23
	 * @return BussinessDept
	 * @update 2012-5-23 下午7:29:45
	 */
	public BussinessDept getBusDeptByCode(String deptCode);

	/**
	 * @description 根据条件查询网点个数.  
	 * @author 潘光均
	 * @version 0.1 2012-5-30
	 * @param 
	 *@date 2012-5-30
	 * @return Long
	 * @update 2012-5-30 下午12:31:54
	 */
	public Long getBussinessNumber(BusDeptSearchCondition condition);

	/**
//	 * @description 通过erpid查询网点信息.  
	 * @author 潘光均
	 * @version 0.1 2012-6-7
	 * @param 
	 *@date 2012-6-7
	 * @return BussinessDept
	 * @update 2012-6-7 下午4:52:59
	 */
	public BussinessDept getBusDeptByErpId(String erpId);
	
	/**
	 * 
	 * <p>
	 * Description:通过标杆编码查询营业部门<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-1-8
	 * @param standardCode 标杆编码
	 * @return
	 * BussinessDept 
	 */
	public BussinessDept getBusDeptByStandardCode(String standardCode);
	/**
	 * @description 通过部门id查询网点信息.  
	 * @author 潘光均
	 * @version 0.1 2012-6-7
	 * @param 
	 *@date 2012-6-7
	 * @return BussinessDept
	 * @update 2012-6-7 下午4:52:59
	 */
	public BussinessDept getBusDeptByDeptId(String deptId);

	/**
	 * <p>
	 * Description: 根据条件查询是否有匹配的网点信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-6-19
	 * @param ls
	 * @return
	 * int
	 */
	public int searchExistsLadingstationByCondition(LadingstationDepartment ls);
	
	/**
	 * <p>
	 * Description:通过ID或者Id和名字 去查询本部门下  所有网点营业部门实体及相关信息---创建特殊会员<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-20
	 * @param deptId
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 * List<BussinessDept>
	 */
	public List<Map<String, Object>> getBusDeptByDeptIdOrDeptName(String deptId,String deptName,int start,int limit);
	
	/**
	 * <p>
	 * Description:算出总量  通过ID或者Id和名字 去查询本部门下  所有网点营业部门实体及相关信息---创建特殊会员 <br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-20
	 * @param deptId
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 * List<BussinessDept>
	 */
	public int countBusDeptByDeptIdOrDeptName(String deptId,String deptName);

	/**
	 * <p>
	 * Description:查询营业部<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-26
	 * @param deptId
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getAllBusDeptByDeptIdOrDeptName(
			String deptId, String deptName, int start, int limit);

	/**
	 * <p>
	 * Description:计数营业部<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-26
	 * @param deptId
	 * @param deptName
	 * @return
	 * int
	 */
	public int countAllBusDeptByDeptIdOrDeptName(String deptId, String deptName);
}
