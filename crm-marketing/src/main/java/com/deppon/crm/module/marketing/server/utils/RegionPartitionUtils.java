/**
 * 
 */
package com.deppon.crm.module.marketing.server.utils;

import java.util.Date;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.RegionPartitionConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;

/**   
 * <p>
 * Description:区域划分工具类<br />
 * </p>
 * @title RegionPartitionUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhouYuan
 * @version 0.1 2013-04-20
 */
public class RegionPartitionUtils {
	/**
	 * <p>
	 * Description: 根据权限分装查询实体<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return 
	 * SearchDeptCondition
	 */
	public static SearchDeptCondition createSearchDeptConditionByAuth(User user ,String character){
		//根据权限查询部门性质
		String authCharacter = 
				getDeptCharacterByAuth( user.getRoleids(),character );
		//如果部门
		if( authCharacter == null || "".equals(authCharacter)){
			//将部门性质转换为权限性质
			authCharacter = changeDeptCharacterToAuthCharacter(character);
		}
		//创建查询实体
		SearchDeptCondition sdc = new SearchDeptCondition();
		//设置查询部门ID
		sdc.setParentId( user.getEmpCode().getDeptId().getId());
		//设置权限性质
		sdc.setAuthCharacter(authCharacter);
		//设置所属模块
		sdc.setOwner( RegionPartitionConstance.OWNER );
		return sdc;
	}
	/**
	 * <p>
	 * Description: 根据权限查询部门性质<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return 
	 * String
	 */
	public static String getDeptCharacterByAuth(Set<String> roleIds,String character){
		String deptAuth = null;
		//如果权限列表中包含经营本部权限则
		if( roleIds.contains(RegionPartitionConstance.AUTH_HEADQUARTERS)){
			//将部门性质设置为经营本部
			deptAuth = RegionPartitionConstance.HEADQUARTERS;
		//如果权限列表中包含事业部权限则
		}else if( roleIds.contains(RegionPartitionConstance.AUTH_DIVISION)){
			//将部门性质设置为事业部
			deptAuth = RegionPartitionConstance.DIVISION;
		//如果权限列表中包含大区权限则
		}else if( roleIds.contains(RegionPartitionConstance.AUTH_BIG_REGION)){
			//将部门性质设置为大区部
			deptAuth = RegionPartitionConstance.BIG_REGION;
		//如果权限列表中包含小区权限则
		}else if( roleIds.contains(RegionPartitionConstance.AUTH_SMALL_REGION)){
			//将部门性质设置为小区部
			deptAuth = RegionPartitionConstance.SMALL_REGION;
		//如果权限列表中包含营业部权限则
		}else if( roleIds.contains(RegionPartitionConstance.AUTH_SALES_DEPARTMENT)){
			//将部门性质设置为营业部
			deptAuth = RegionPartitionConstance.SALES_DEPARTMENT;
		}
		//如果该用户没有分配权限则如果是部门负责人分配相关部门权限
		if(deptAuth ==  null || "".equals(deptAuth)){
			deptAuth = changeDeptCharacterToAuthCharacter(character);
		}
		return deptAuth;
	}
	/**
	 * <p>
	 * Description: 保存区域划分时创建区域划分实体<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param deptId
	 * @param regionId
	 * @param user
	 * @return 
	 * RegionPartition
	 */
	public static RegionPartition createRegionPartition(String deptId,String regionId,User user){
		RegionPartition region = new RegionPartition();
		//设置部门ID
		region.setDeptId(deptId);
		//设置区域ID
		region.setRegionId(regionId);
		//设置创建人ID
		region.setCreateUser(user.getId());
		//设置创建时间
		region.setCreateDate(new Date());
		//设置修改人ID
		region.setModifyUser(user.getId());
		//设置修改时间
		region.setModifyDate(new Date());
		
		return region;
	}
	/**
	 * <p>
	 * Description: 根据部门性质得到对应的权限性质<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param deptCharacter
	 * @return 
	 * String
	 */
	public static String changeDeptCharacterToAuthCharacter(String deptCharacter){
		//如果部门性质为经营本部
		if( RegionPartitionConstance.HEADQUARTERS.equals(deptCharacter)){
			//具有划分事业部权限
			return RegionPartitionConstance.DIVISION;
		//如果部门性质为事业部
		}else if(RegionPartitionConstance.DIVISION.equals(deptCharacter)){
			//具有划分大区权限
			return RegionPartitionConstance.BIG_REGION;
		//如果部门性质为大区
		}else if(RegionPartitionConstance.BIG_REGION.equals(deptCharacter)){
			//具有划分小区权限
			return RegionPartitionConstance.SMALL_REGION;
		//如果部门性质为小区
		}else if(RegionPartitionConstance.SMALL_REGION.equals(deptCharacter)){
			//具有划分营业部权限
			return RegionPartitionConstance.SALES_DEPARTMENT;
		//其他则无权限
		}else{
			return null;
		}
	}
}
