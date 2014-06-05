/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICustomerGroupService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;

/**   
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title IRegionPartitionService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public interface IRegionPartitionService {
	
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
	public List<RegionPartition> searchRegionDept(SearchDeptCondition sdc);
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
	public List<RegionPartition> searchRegionByDeptId(String deptId);
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
	public List<RegionPartition> searchRegionByParentId(String parentId);
	/**
	 * 
	 * <p>
	 * Description:插入区域划分信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param region
	 * @return
	 * boolean
	 */
	public boolean insertRegionPartition(RegionPartition region);
	/**
	 * 
	 * <p>
	 * Description:根据部门ID删除区域划分<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param region
	 * @return
	 * boolean
	 */
	public boolean deleteRegionPartition(String deptId);
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
	public String searchDpetCharacterByDeptId( Map<String,Object> map );
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
	public List<RegionPartition> searchRegionsByDeptId(String deptId);
}
