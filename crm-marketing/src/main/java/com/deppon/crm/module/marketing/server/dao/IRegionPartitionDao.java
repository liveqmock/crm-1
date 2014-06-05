/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICustomerGroupDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;

/**   
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title IRegionPartitionDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public interface IRegionPartitionDao {

	/**
	 * 
	 * <p>
	 * Description:区域划分查询部门列表 如果是经营本部<br />
	 * 				按照部门性质表中经营本部字段查询<br />
	 * 				如果是其他则按照上级部门ID查询
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param deptId
	 * @return
	 * List<RegionPartition>
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
	 * int
	 */
	public int insertRegionPartition(RegionPartition region);
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
	public int deleteRegionPartition(String deptId);
	/**
	 * 
	 * <p>
	 * Description:根据部门ID查询部门性质<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-19
	 * @param region
	 * @return
	 * String
	 */
	public String searchDpetCharacterByDeptId(Map<String,Object> map );
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
