package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;

/**   
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title IRegionPartitionManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author ZhouYuan
 * @version 2013-04-19
 */
public interface IRegionPartitionManager {
	/**
	 * <p>
	 * Description: 初始化区域划分页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * List<Department>
	 */
	Map<String,Object> initRegionPartitionPage(User user);
	/**
	 * <p>
	 * Description: 根据部门id保存所划区域信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * boolean
	 */
	boolean saveRegionPartition(String deptId,String regionId,User user);
	/**
	 * <p>
	 * Description: 根据部门id删除所划区域信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * boolean
	 */
	boolean deleteRegionPartition(String deptId,User user);
	/**
	 * <p>
	 * Description: 根据部门id查询所划区域信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * boolean
	 */
	List<RegionPartition> searchRegionByDeptId(String deptId);
	/**
	 * <p>
	 * Description: 根据用户权限查询部门信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * List<RegionPartition>
	 */
	List<RegionPartition> searchRegionDept(User user);
	/**
	 * <p>
	 * Description: 根据部门id查询所辖区域信息<br />
	 * </p>
	 * @author lvchongxin
	 * @version 2013-12-6
	 * @param deptId
	 * @return List<RegionPartition>
	 * 
	 */
	List<RegionPartition> searchRegionsByDeptId(String deptId);
}


