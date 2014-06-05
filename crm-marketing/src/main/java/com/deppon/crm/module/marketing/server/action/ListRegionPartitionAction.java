package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IRegionPartitionManager;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**   
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title ListRegionPartitionAction.java
 * @package com.deppon.crm.module.marketing.server.action
 * @author ZhouYuan
 * @version 2013-04-19
 */
public class ListRegionPartitionAction extends AbstractAction{
	private IRegionPartitionManager regionPartitionManager;
	//下级部门列表
	private List<RegionPartition> deptList; 
	//权限性质
	private String authCharacter;
	//需要保存的部门ID
	private String deptId;
	//需要保存的区域编码
	private String regionId;
	//所查区域
	private RegionPartition region;
	/**
	 * <p>
	 * Description: 初始化区域划分页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * String 
	 */
	@JSON
	public String initRegionPartitionPage(){
		//获得当前用户
		User user = (User)UserContext.getCurrentUser();
		//根据当前用户查询下级部门列表,本部门区域
		 Map<String,Object> map = regionPartitionManager.initRegionPartitionPage(user);
		 List<RegionPartition> regions = (List<RegionPartition>)map.get("region");
		 if(regions.size()>0){
			 deptId = regions.get(0).getDeptId();
			 regionId = regions.get(0).getRegionId();
		 }
		 authCharacter = (String)map.get("authCharacter");
		 deptList = (List<RegionPartition>)map.get("deptList");
		 return SUCCESS;
	}
	/**
	 * <p>
	 * Description: 查询区域划分信息<br />
	 * </p>
	 * @author 盛诗庆
	 * @version 2013-12-16
	 * @param user
	 * @return
	 */
	@JSON
	public String searchRegionsByDeptId()
	{	
		//调用查询区域的方法
		deptList = regionPartitionManager.searchRegionsByDeptId(deptId);
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description: 保存区域划分信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * String 
	 */
	@JSON
	public String saveRegionPartition(){
		//获得当前用户
		User user = (User)UserContext.getCurrentUser();
		//调用保存区域方法保存区域信息
		regionPartitionManager.saveRegionPartition(deptId, regionId, user);
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description: 删除区域划分信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * String 
	 */
	@JSON
	public String deleteRegionPartition(){
		//获得当前用户
		User user = (User)UserContext.getCurrentUser();
		//调用保存区域方法删除区域信息
		regionPartitionManager.deleteRegionPartition(deptId,user);
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description: 根据部门	ID查询区域划分信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * String 
	 */
	@JSON
	public String searchRegionByDeptId(){
		//调用相应方法查询区域划分信息
		 List<RegionPartition> regions = 
				 regionPartitionManager.searchRegionByDeptId(deptId);
		 if( regions.size()>0 ){
			 region = regions.get(0);
		 }
		return SUCCESS;
	}
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
	@JSON
	public String searchRegionDept(){
		//获得当前用户
		User user = (User)UserContext.getCurrentUser();
		//查询部门列表
		deptList = regionPartitionManager.searchRegionDept(user);
		return SUCCESS;
	}
	
	
	public void setRegionPartitionManager(
			IRegionPartitionManager regionPartitionManager) {
		this.regionPartitionManager = regionPartitionManager;
	}

	public List<RegionPartition> getDeptList() {
		return deptList;
	}

	public String getAuthCharacter() {
		return authCharacter;
	}
	
	public String getDeptId() {
		return deptId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public RegionPartition getRegion() {
		return region;
	}
	public void setRegion(RegionPartition region) {
		this.region = region;
	}
	
}
