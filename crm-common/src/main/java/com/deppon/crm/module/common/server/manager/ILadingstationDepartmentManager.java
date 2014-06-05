/**   
 * @title ILadingstationDepartmentManager.java
 * @package com.deppon.crm.module.common.server.manager
 * @description what to do
 * @author 安小虎
 * @update 2012-3-23 上午8:44:04
 * @version V1.0   
 */
package com.deppon.crm.module.common.server.manager;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * @description 始发网点与受理部门 MANAGER接口
 * @author 安小虎
 * @version 0.1 2012-3-23
 * @date 2012-3-23
 */

public interface ILadingstationDepartmentManager {
	
	public static final String LANDINGSTATIONDEPARTMENT_LIST="LANDINGSTATIONDEPARTMENT_LIST";
	
	public static final String LANDINGSTATIONDEPARTMENT_TOTAL_COUNT="LANDINGSTATIONDEPARTMENT_TOTAL_COUNT";		
	
	/**
	 * 
	 * @description 始发网点与受理部门关联MANAGER新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return int
	 * @update 2012-3-23 上午10:13:28
	 */
	public LadingstationDepartment save(
			LadingstationDepartment ladingstationDepartment);

	/**
	 * 
	 * @description 始发网点与受理部门关联MANAGER修改接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return int
	 * @update 2012-3-23 上午10:13:28
	 */
	public LadingstationDepartment update(
			LadingstationDepartment ladingstationDepartment);

	/**
	 * 
	 * @description 始发网点与受理部门关联MANAGER根据ID删除接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return int
	 * @update 2012-3-23 上午10:13:28
	 */
	public int delete(String ladingstationDepartmentId);

	/**
	 * 
	 * @description 始发网点与受理部门关联MANAGER根据条件查询接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return List
	 * @update 2012-3-23 上午10:13:28
	 */
	public Map<String,Object> searchByCondition(
			LadingstationDepartment ladingstationDepartment,int start, int limit);

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
	public List<BussinessDept> getLeaveBusDeptByName(BusDeptSearchCondition condition);

	/**
	 * 
	 * @description 根据到id查询网点.
	 * @author 安小虎
	 * @version 0.1 2012-3-24
	 * @param String
	 * @date 2012-3-24
	 * @return BussinessDept
	 * @update 2012-3-24 上午10:21:52
	 */
	public BussinessDept getBusDeptById(String deptId);

	/**
	 * @description 根据到名称查询网点.
	 * @author 潘光均
	 * @version 0.1 2012-3-28
	 * @param b true or false   
	 *@date 2012-3-28
	 * @return none
	 * @update 2012-3-28 上午11:20:24
	 */
	public BussinessDept getBusDeptByName(String startStation);

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
	public List<BussinessDept> getArriveBusDeptByName(BusDeptSearchCondition condition);

	/**
	 * 
	 * <p>
	 * Description:从始发网点与受理部门配置信息中取得信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-3-29
	 * @param deptId 始发网点ID
	 * @param isReceiveGoods 是否接货
	 * @param resource 订单渠道
	 * @return
	 * List<LadingstationDepartment>
	 */
	public List<LadingstationDepartment> getAcceptDeptByConfigurator(String deptId,boolean isReceiveGoods,String resource);
	
	/**
	 * <p>
	 * Description:获取所有始发网点与受理部门关联对象<br />
	 * </p>
	 * @author 邢悦
	 * @version 0.1 2012-5-8
	 * @param start
	 * @param limit
	 * @return
	 * List<LadingstationDepartment>
	 */
	public Map<String,Object> getAllLandingStationDepartment(int start, int limit);
	
	/**
	 * 获取所有始发网点与受理部门关联对象的总数
	 * @author 邢悦
	 * @return
	 */	
	public Long getAllLandingStationDepartmentCount();	
	
	/**
	 * 创建始发网点与受理部门关系
	 * @author 邢悦
	 * @param startNetId
	 * @param acceptDept
	 * @param orderResource
	 * @param ifReceive
	 */
	public void createLadingstationDepartment(String startNetId, String acceptDept,String orderResource,String ifReceive,User user);
	
	
	/**
	 * 根据部门名称模糊查询部门，用于始发网点和受理部门的查询
	 * @author 邢悦
	 * @param deptName
	 * @return
	 */
	public List<Department> getDepartmentByDeptName(String deptName);

	/**
	 * @description 通过网点名称查询网点信息.  
	 * @author 潘光均
	 * @version 0.1 2012-5-23
	 * @param 
	 *@date 2012-5-23
	 * @return BussinessDept
	 * @update 2012-5-23 下午7:27:58
	 */
	BussinessDept getBusDeptByCode(String deptCode);

	/**
	 * @description 通过网点名称查询网点总书目.  
	 * @author 潘光均
	 * @version 0.1 2012-5-30
	 * @param 
	 *@date 2012-5-30
	 * @return Long
	 * @update 2012-5-30 下午12:30:01
	 */
	Long getBussinessNumber(BusDeptSearchCondition condition);
	
	/**
	 * @description 批量导入始发网点与受理部门关系.  
	 * @author 邢悦
	 * @param filePath
	 * @return
	 */
	public String importLadingtationDepartments(File importFile);
	
	/**
	 * @description 通过erpid查询网点信息.  
	 * @author 潘光均
	 * @version 0.1 2012-6-7
	 * @param 
	 *@date 2012-6-7
	 * @return BussinessDept
	 * @update 2012-6-7 下午4:51:29
	 */
	BussinessDept getBusDeptByErpId(String ErpId);
	
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
	BussinessDept getBusDeptByStandardCode(String standardCode);
	
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
	 * 
	 * @description 始发网点与受理部门.
	 * @author 安小虎
	 * @version 0.1 2012-3-23
	 * @param 始发网点与受理部门关联对象
	 * @date 2012-3-23
	 * @return int
	 * @update 2012-3-23 上午10:13:28
	 */	
	public void edit(LadingstationDepartment ladingstationDepartment);	
	
	/**
	 * 废除一个始发网点与受理部门关系
	 * @author 邢悦
	 * @version 0.1 2012-7-10	  
	 * @param ladingstationDepartment
	 */
	public void invalidLadingstationRelation(String ldId);	
	
	/**
	 * <p>
	 * Description:通过部门Id得到部门所在的城市  0表示大陆，1表示香港<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-21
	 * @return
	 * String
	 */
	public String getDeptCityLocation(String deptId);
	
	/**
	 * <p>
	 * Description:通过ID或者Id和名字 去查询本部门下  所有网点营业部门实体及相关信息--创建特殊会员使用<br />
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
	 * Description:算出总量  通过ID或者Id和名字 去查询本部门下  所有网点营业部门实体及相关信息--创建特殊会员使用 <br />
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
	 * Description:只查询营业部，并且排除快递营业部<br />
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
	List<Map<String, Object>> getAllBusDeptByDeptIdOrDeptName(String deptId,
			String deptName, int start, int limit);

	/**
	 * <p>
	 * Description:只查询营业部<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-26
	 * @param deptId
	 * @param deptName
	 * @return
	 * int
	 */
	int countAllBusDeptByDeptIdOrDeptName(String deptId, String deptName);
}
