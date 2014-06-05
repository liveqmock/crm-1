package com.deppon.crm.module.marketing.server.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.cxf.common.util.StringUtils;

import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.RegionPartitionConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;
import com.deppon.crm.module.marketing.shared.exception.RegionPartitionException;
import com.deppon.crm.module.marketing.shared.exception.RegionPartitionExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:区域划分校验类<br />
 * </p>
 * @title RegionPartitionValidator.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhouYuan
 * @version 0.1 2013-04-20
 */
public class RegionPartitionValidator {
	/**
	 * <p>
	 * Description: 校验用户是否具有查询权限<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param user
	 * @return
	 * List<Department>
	 */
	public static void checkSearchDeptCondition(SearchDeptCondition sdc){
		//查询实体不能为空
		if( sdc == null){		
			//新建RegionPartitionException
			RegionPartitionException e = new RegionPartitionException(RegionPartitionExceptionType.searchDeptConditionDeptNotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//用户没有相应的权限 
		if( sdc.getAuthCharacter() == null || sdc.getAuthCharacter().equals("") ){
			//新建RegionPartitionException
			RegionPartitionException e = new RegionPartitionException(RegionPartitionExceptionType.searchDeptConditionDeptCharacterNotNull);
			//抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//查询部门ID不合法
		if( sdc.getDeptId()!= null && !sdc.getDeptId().matches("^\\d\\d*$") ){
			//新建RegionPartitionException
			RegionPartitionException e = new RegionPartitionException(RegionPartitionExceptionType.searchDeptConditionDeptCharacterNotNull);
			//抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**
	 * <p>
	 * Description: 校验用户是否有权相应的权限<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 2013-04-20
	 * @param region
	 */
	public static void checkRegionPartitionAuth(Set<String> roleIds,String auth){
		Set<String> auths = new HashSet<String>();
		//划分经营本部权限
		auths.add(RegionPartitionConstance.AUTH_HEADQUARTERS);
		//划分事业部权限
		auths.add(RegionPartitionConstance.AUTH_DIVISION);
		//划分权限
		auths.add(RegionPartitionConstance.AUTH_BIG_REGION);
		//划分权限
		auths.add(RegionPartitionConstance.AUTH_SMALL_REGION);
		//划分权限
		auths.add(RegionPartitionConstance.AUTH_SALES_DEPARTMENT);
		//新建roleId用于复制
		Set<String> colneRole = new HashSet<String>();
		//获得roleIds的iterator
		Iterator<String> it = roleIds.iterator();
		//复制roleIds
		while( it.hasNext() ){
			colneRole.add(it.next());
		}
		//取两个集合的并集
		colneRole.retainAll(auths);
		//如果集合中元素个数为0则该用户没有权限
		if(colneRole.size()<=0 && auth == null){
			//新建RegionPartitionException
			RegionPartitionException e = new RegionPartitionException(RegionPartitionExceptionType.regionPartitionWithoutAuth);
			//抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}		
	}
	/**
	 * <p>
	 * Description: 校验部门ＩＤ是否合法<br />
	 * </p>
	 * @author lvchongxin
	 * @version 2013-12-10
	 * @param deptId
	 */
	public static void checkUserAuth(String deptId){
		//判断部门ID是否合法
		if(!StringUtils.isEmpty(deptId)&&deptId.matches("^\\d\\d*$")){
				return;
		}else{
		RegionPartitionException e = new RegionPartitionException(RegionPartitionExceptionType.searchDeptConditionDeptIdIllegal);
		//抛出异常用户不合法
		throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
				new Object[] {}) {
		};}
	}
	/**
	 * <p>
	 * Description: 校验是否返回划分区域<br />
	 * </p>
	 * @author lvchongxin
	 * @version 2013-12-10
	 * @param regionPart
	 */
	public static void checkExistRegion(List<RegionPartition> regionPart){
		if(null==regionPart || regionPart.isEmpty()){
			RegionPartitionException e = new RegionPartitionException(RegionPartitionExceptionType.regionNotPartition);
			//抛出区域是否为空
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
}
