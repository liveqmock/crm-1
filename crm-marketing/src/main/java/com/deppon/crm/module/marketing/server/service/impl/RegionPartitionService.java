/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.server.dao.IRegionPartitionDao;
import com.deppon.crm.module.marketing.server.service.IRegionPartitionService;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;

/**   
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title RegionPartitionService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public class RegionPartitionService implements IRegionPartitionService {

	private IRegionPartitionDao regionPartitionDao;

	public IRegionPartitionDao getRegionPartitionDao() {
		return regionPartitionDao;
	}

	public void setRegionPartitionDao(IRegionPartitionDao regionPartitionDao) {
		this.regionPartitionDao = regionPartitionDao;
	}
	/**
	 * 
	 * <p>
	 * Description:查询区域划分部门<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param SearchDeptCondition
	 * @return
	 * List<Department>
	 */
	@Override
	public List<RegionPartition> searchRegionDept(SearchDeptCondition sdc) {
		return regionPartitionDao.searchRegionDept(sdc);
	}
	/**
	 * 
	 * <p>
	 * Description:区域划分根据部门ID查找区域<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param deptId
	 * @return
	 * List<RegionPartition>
	 */
	@Override
	public List<RegionPartition> searchRegionByDeptId(String deptId){
		return regionPartitionDao.searchRegionByDeptId(deptId);
	}
	/**
	 * 
	 * <p>
	 * Description:区域划分根据上级部门ID查找区域<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param parentId
	 * @return
	 * List<RegionPartition>
	 */
	@Override
	public List<RegionPartition> searchRegionByParentId(String parentId){
		return regionPartitionDao.searchRegionByParentId(parentId);
	}
	/**
	 * 
	 * <p>
	 * Description:插入区域划分信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param region
	 * @return
	 * int
	 */
	@Override
	public boolean insertRegionPartition(RegionPartition region){
		return regionPartitionDao.insertRegionPartition(region)>0?true:false;
	}
	/**
	 * 
	 * <p>
	 * Description:根据部门ID删除区域划分<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param region
	 * @return
	 * int
	 */
	@Override
	public boolean deleteRegionPartition(String deptId){
		return regionPartitionDao.deleteRegionPartition(deptId)>0?true:false;
	}
	/**
	 * 
	 * <p>
	 * Description:根据部门ID查询部门性质<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param sdc
	 * @return
	 * String
	 */
	@Override
	public String searchDpetCharacterByDeptId(Map<String,Object> map ){
		return regionPartitionDao.searchDpetCharacterByDeptId(map);
	}
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
	@Override
	public List<RegionPartition> searchRegionsByDeptId(String deptId) {
		// TODO Auto-generated method stub
		return regionPartitionDao.searchRegionsByDeptId(deptId);
	}
}
