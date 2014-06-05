package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IRegionPartitionManager;
import com.deppon.crm.module.marketing.server.service.IRegionPartitionService;
import com.deppon.crm.module.marketing.server.utils.RegionPartitionUtils;
import com.deppon.crm.module.marketing.server.utils.RegionPartitionValidator;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.RegionPartitionConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;

/**
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title IRegionPartitionManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author ZhouYuan
 * @version 2013-04-19
 */
public class RegionPartitionManager implements IRegionPartitionManager{
	private IRegionPartitionService regionPartitionService;

	public void setRegionPartitionService(
			IRegionPartitionService regionPartitionService) {
		this.regionPartitionService = regionPartitionService;
	}
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
	@Override
	public Map<String,Object> initRegionPartitionPage(User user){
		//如果是部门负责人则返回相应部门性质
		String character = searchUserAuth(user);
		//根据用户权限创建查询条件
		SearchDeptCondition sdc =RegionPartitionUtils
				.createSearchDeptConditionByAuth(user, character);
		// 校验有没有相应权限
		RegionPartitionValidator.checkSearchDeptCondition(sdc);
		// 查询下级部门区域信息
		List<RegionPartition> deptList = regionPartitionService
				.searchRegionDept(sdc);
		// 查询上级部门给本部门划分的区域
		List<RegionPartition> region = regionPartitionService
				.searchRegionByDeptId(user.getEmpCode().getDeptId().getId());
		// 权限性质
		String authCharacter = RegionPartitionUtils.getDeptCharacterByAuth(
				user.getRoleids(), character);
		// 封装返回实体
		Map<String, Object> map = new HashMap<String, Object>();
		// 本级部门区域
		map.put("region", region);
		// 下级部门
		map.put("deptList", deptList);
		// 部门性质
		map.put("authCharacter", authCharacter);
		return map;
	}
	/**
	 * <p>
	 * Description: 根据用户权限查询部门信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return List<RegionPartition>
	 */
	public List<RegionPartition> searchRegionDept(User user) {
		// 根据用户权限创建查询条件
		SearchDeptCondition sdc = RegionPartitionUtils
				.createSearchDeptConditionByAuth(user, searchUserAuth(user));
		// 根据部门查询实体查询部门列表信息
		return regionPartitionService.searchRegionDept(sdc);
	}

	/**
	 * <p>
	 * Description: 根据部门id保存所划区域信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean saveRegionPartition(String deptId, String regionId, User user) {
		// 检查用户是否具有该权限
		RegionPartitionValidator.checkRegionPartitionAuth(user.getRoleids(),
				searchUserAuth(user));
		// 获得区域划分实体
		RegionPartition region = RegionPartitionUtils.createRegionPartition(
				deptId, regionId, user);
		// 保存区域划分信息
		return regionPartitionService.insertRegionPartition(region);
	}

	/**
	 * <p>
	 * Description: 根据部门id删除所划区域信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean deleteRegionPartition(String deptId, User user) {
		// 检查用户是否具有该权限
		RegionPartitionValidator.checkRegionPartitionAuth(user.getRoleids(),
				searchUserAuth(user));
		// 调用相应方法根据部门ID删除区域划分信息
		return regionPartitionService.deleteRegionPartition(deptId);
	}

	/**
	 * <p>
	 * Description: 根据部门id查询所划区域信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return boolean
	 */
	@Override
	public List<RegionPartition> searchRegionByDeptId(String deptId) {
		// 根据部门ID查询区域信息
		return regionPartitionService.searchRegionByDeptId(deptId);
	}

	/**
	 * <p>
	 * Description: 根据部门id查询所划区域信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return boolean
	 */
	public String searchUserAuth(User user) {
		// 创建查询MAP
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置用户的用户编码
		map.put("empCode", user.getEmpCode().getEmpCode());
		// 设置查询模块
		map.put("owner", RegionPartitionConstance.OWNER);
		// 设置部门ID
		map.put("deptId", user.getEmpCode().getDeptId().getId());
		return regionPartitionService.searchDpetCharacterByDeptId(map);
	}

	/**
	 * <p>
	 * Description: 根据部门id查询所辖区域信息<br />
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 2013-12-6
	 * @param deptId
	 * @return List<RegionPartition>
	 * 
	 */
	@Override
	public List<RegionPartition> searchRegionsByDeptId(String deptId) {
		// 验证用户ＩＤ是否为空部门ＩＤ是否合法
		RegionPartitionValidator.checkUserAuth(deptId);
		List<RegionPartition> regionPart = regionPartitionService
				.searchRegionsByDeptId(deptId);
		RegionPartitionValidator.checkExistRegion(regionPart);
		return regionPartitionService.searchRegionsByDeptId(deptId);
	}
}
