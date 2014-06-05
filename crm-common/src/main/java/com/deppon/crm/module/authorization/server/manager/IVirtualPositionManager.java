package com.deppon.crm.module.authorization.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.EhrPosition;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.VirtualPosition;
/**
 * 
 * <p>
 * 虚拟岗位管理<br />
 * </p>
 * @title IVirtualPositionManager.java
 * @package com.deppon.crm.module.authorization.server.manager 
 * @author suyujun
 * @version 0.1 2013-11-26
 */
public interface IVirtualPositionManager {
	/**
	 * 
	 * <p>
	 * 新增虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param vp
	 * @return
	 * VirtualPosition
	 */
	VirtualPosition addVirtualPosition(VirtualPosition vp,List<String> newList);
	
	/**
	 * 
	 * <p>
	 * 更新虚拟岗位及映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param vp
	 * @param newList
	 * void
	 */
	void updateVirtualPosition(VirtualPosition vp,List<String> newList);
	
	/**
	 * 
	 * <p>
	 * 删除虚拟岗位及映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param vp
	 * void
	 */
	void deleteVirtualPositionById(VirtualPosition vp);
	
	/**
	 * 
	 * <p>
	 * 根据ID查询虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param id
	 * @return
	 * VirtualPosition
	 */
	VirtualPosition selectVirtualPositionById(String id);
	
	/**
	 * 
	 * <p>
	 * 判断虚拟岗位是否存在<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param positonName
	 * void
	 */
	void isExistVirtualPositionName(String positonName,String vpId);
	/**
	 * 
	 * <p>
	 * 判断能否删除虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param vp
	 * @return
	 * boolean
	 */
	boolean canDeleteVirtualPosition(VirtualPosition vp);
	/**
	 * 
	 * <p>
	 * 查询和虚拟岗位已经映射的标准岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param vpId
	 * @return
	 * List<EhrPosition>
	 */
	//已映射标准岗位
	List<EhrPosition> getStaPositionHaveMappedWithVP(String vpId);
	/**
	 * 
	 * <p>
	 * 查询和虚拟岗位未映射的标准岗位</br>
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param vpId
	 * @return
	 * List<EhrPosition>
	 */
	//待映射标准岗位
	List<EhrPosition> getStaPositionWaitToMapWithVP(String vpId,String ehrPositionName);
	/**
	 * 
	 * <p>
	 * 虚拟岗位默认查询<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName
	 * @param ehrPositionName
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,Object>
	 */
	//虚拟岗位管理默认查询(查询)
	Map<String,Object> queryVirtualPosition(String virtualPositionName,String ehrPositionName,int start,int limit);
	/**
	 * 
	 * <p>
	 * 虚拟岗位与角色关系查询<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName
	 * @param roleName
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,Object>
	 */
	Map<String,Object> queryVirPositionRole(String virtualPositionName,String roleName,int start,int limit);
	/**
	 * 
	 * <p>
	 * 根据岗位名称模糊查询未映射任何角色的虚拟岗位<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param virtualPositionName
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,Object>
	 */
	Map<String,Object> queryVirtualPositionByName(String virtualPositionName,int start,int limit);
	/**
	 * 
	 * <p>
	 * 根据角色名称模糊查询角色<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param roleName
	 * @param isMap  NO 未映射  YES已映射
	 * @return
	 * Map<String,Object>
	 */
	List<Role> queryRoleByVirtualPositionName(String virtualPositionId,String roleName,String isMap);
	
	/**
	 * 
	 * <p>
	 * 新增虚拟岗位 角色 关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * @param roleIds
	 * void
	 */
	void saveVirtualPostionRole(String virtualPositionId,List<String> roleIds);
	
	/**
	 * 
	 * <p>
	 * 删除映射关系<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * void
	 */
	void deleteVirtualPositionRole(String virtualPositionId);
	
	/**
	 * 
	 * <p>
	 * 人员角色刷新<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-30
	 * @param virtualPositionId
	 * void
	 */
	String refreshUserRole(String virtualPositionId);
}
