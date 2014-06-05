/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.server.dao.IRegionPartitionDao;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title IRegionPartitionDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public class RegionPartitionDao extends iBatis3DaoImpl implements IRegionPartitionDao {

	private static final String NAMESPACE_REGIONPARTITION = "com.deppon.crm.module.marketing.shared.domain.RegionPartition.";
	//查询区域划分部门信息
	private static final String SEARCHREGIONDEPT = "searchRegionDept";
	//根据部门ID查询区域划分
	private static final String SEARCHREGIONBYDEPTID = "searchRegionByDeptId";
	//根据上级部门ID查询区域划分
	private static final String SEARCHREGIONBYPARENTID = "searchRegionByParentId";
	//插入区域划分信息
	private static final String INSERTREGIONPARTITION = "insertRegionPartition";
	//删除区域划分信息
	private static final String DELETEREGIONPARTITIONBYDEPTID = "deleteRegionPartitionByDeptId";
	//根据部门ID查询部门性质
	private static final String SEARCHDEPTCHARACTERBYDEPTID = "searchDpetCharacterByDeptId";
	//根据部门ID查询所辖区域划分
	private static final String SEARCHREGIONSBYDEPTID="searchRegionsByDeptId";
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
	@Override
	public List<RegionPartition> searchRegionDept(SearchDeptCondition sdc) {		
		return this.getSqlSession().selectList(NAMESPACE_REGIONPARTITION+SEARCHREGIONDEPT, sdc);
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
		return this.getSqlSession().selectList(NAMESPACE_REGIONPARTITION+SEARCHREGIONBYDEPTID, deptId);
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
		return this.getSqlSession().selectList(NAMESPACE_REGIONPARTITION+SEARCHREGIONBYPARENTID, parentId);
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
	public int insertRegionPartition(RegionPartition region){
		return this.getSqlSession().insert(NAMESPACE_REGIONPARTITION+INSERTREGIONPARTITION,region);
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
	public int deleteRegionPartition(String deptId){
		return this.getSqlSession().delete(NAMESPACE_REGIONPARTITION+DELETEREGIONPARTITIONBYDEPTID,deptId);
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
	public String searchDpetCharacterByDeptId(Map<String,Object> map){
		return (String)this.getSqlSession().selectOne(NAMESPACE_REGIONPARTITION+SEARCHDEPTCHARACTERBYDEPTID,map);
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
		return this.getSqlSession().selectList(NAMESPACE_REGIONPARTITION+SEARCHREGIONSBYDEPTID, deptId);
	}
}
